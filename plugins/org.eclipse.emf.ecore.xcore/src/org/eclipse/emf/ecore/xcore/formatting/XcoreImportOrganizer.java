/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.formatting;


import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newLinkedHashSet;
import static com.google.common.collect.Maps.newHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.conversion.XcoreValueConverterService;
import org.eclipse.emf.ecore.xcore.scoping.XcoreImportedNamespaceAwareScopeProvider;
import org.eclipse.emf.ecore.xcore.util.XcoreUtil;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.formatting.IWhitespaceInformationProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.TextRegion;

import com.google.inject.Inject;


/**
 */
public class XcoreImportOrganizer
{
  @Inject
  private IWhitespaceInformationProvider whitespaceInformationProvider;

  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private XcoreValueConverterService valueConverterService;

  private Map<String, QualifiedName> implicitAliases;

  protected Map<String, QualifiedName> getImplicitAliases()
  {
    if (implicitAliases == null)
    {
      implicitAliases = newHashMap();
      for (final EDataType eDataType : XcoreImportedNamespaceAwareScopeProvider.IMPLICIT_ALIASES)
      {
        String instanceClassName = eDataType.getInstanceClassName();
        final QualifiedName actualQualifiedName = QualifiedName.create("org", "eclipse", "emf", "ecore", eDataType.getName());
        final QualifiedName qualifiedName = nameConverter.toQualifiedName(instanceClassName);
        implicitAliases.put(instanceClassName, actualQualifiedName);
        implicitAliases.put(qualifiedName.getLastSegment(), actualQualifiedName);
      }
    }
    return implicitAliases;
  }

  protected XPackage getXPackage(XtextResource resource)
  {
    if (!resource.getContents().isEmpty())
    {
      EObject eObject = resource.getContents().get(0);
      if (eObject instanceof XPackage)
      {
        return (XPackage)eObject;
      }
    }
    return null;
  }

  public TextRegion getImportRegion(XtextResource xtextResource)
  {
    XPackage xPackage = getXPackage(xtextResource);
    if (xPackage != null)
    {
      List<INode> xPackageNodes = NodeModelUtils.findNodesForFeature(xPackage, XcorePackage.Literals.XNAMED_ELEMENT__NAME);
      int begin;
      int size = xPackageNodes.size();
      if (size == 0)
      {
        begin = 0;
      }
      else
      {
        INode lastNode = xPackageNodes.get(size - 1);
        begin = lastNode.getOffset() + lastNode.getLength();
      }
      int end;
      EList<XAnnotationDirective> annotationDirectives = xPackage.getAnnotationDirectives();
      if (!annotationDirectives.isEmpty())
      {
        ICompositeNode node = NodeModelUtils.getNode(annotationDirectives.get(0));
        end = node.getTotalOffset();
      }
      else
      {
        EList<XClassifier> classifiers = xPackage.getClassifiers();
        if (!classifiers.isEmpty())
        {
          ICompositeNode node = NodeModelUtils.getNode(classifiers.get(0));
          end = node.getTotalOffset();
        }
        else
        {
          end = xtextResource.getParseResult().getRootNode().getTotalEndOffset();
        }
      }
      return new TextRegion(begin, end - begin);
    }
    else
    {
      return null;
    }
  }

  public String getOrganizedImportSection(XtextResource xtextResource)
  {
    String lineSeparator = whitespaceInformationProvider.getLineSeparatorInformation(xtextResource.getURI()).getLineSeparator();
    StringBuilder importsSection = new StringBuilder();
    ArrayList<QualifiedName> importedNames = newArrayList(getImportedNames(xtextResource));
    Collections.sort(importedNames);
    if (!importedNames.isEmpty())
    {
      importsSection.append(lineSeparator);
      IValueConverter<String> qualifiedNameValueConverter = valueConverterService.getQualifiedNameValueConverter();
      for (QualifiedName qualifiedName : importedNames)
      {
        String qualifiedNameValue = qualifiedNameValueConverter.toString(nameConverter.toString(qualifiedName));
        importsSection.append(lineSeparator).append("import ").append(qualifiedNameValue);
      }
    }
    return importsSection.toString();
  }

  public Set<QualifiedName> getImportedNames(XtextResource resource)
  {
    XPackage xPackage = getXPackage(resource);
    String packageName = xPackage.getName();
    List<String> implicitPackageImports = newArrayList(packageName, "java.lang");
    Set<QualifiedName> importedNames = newLinkedHashSet();
    Map<String, QualifiedName> implicitAliases = getImplicitAliases();
    for (INode node : XcoreUtil.importableCrossReferences(xPackage))
    {
      CrossReference grammarElement = (CrossReference)node.getGrammarElement();
      EObject container = grammarElement.eContainer();
      if (container instanceof Assignment)
      {
        String name = node.getText().trim();
        if (name.endsWith("::"))
        {
          name = name.substring(0, name.length() - 2);
        }
        QualifiedName actualQualifiedName = nameConverter.toQualifiedName(name);
        Assignment assignment = (Assignment)container;
        String feature = assignment.getFeature();
        EObject semanticObject = NodeModelUtils.findActualSemanticObjectFor(node);
        EStructuralFeature eStructuralFeature = semanticObject.eClass().getEStructuralFeature(feature);
        if (!eStructuralFeature.isMany())
        {
          EObject eCrossReference = (EObject)semanticObject.eGet(eStructuralFeature);
          EObject eContainer = eCrossReference.eContainer();
          if (eContainer != xPackage &&
              !(eContainer instanceof XPackage && "xcore.lang".equals(((XPackage)eContainer).getName())) &&
              !(eContainer instanceof GenPackage && packageName.equals(((GenPackage)eContainer).getQualifiedPackageName())) &&
              !(eCrossReference instanceof JvmDeclaredType && implicitPackageImports.contains(((JvmDeclaredType)eCrossReference).getPackageName())))
          {
            QualifiedName fullyQualifiedName = nameProvider.getFullyQualifiedName(eCrossReference);
            if (fullyQualifiedName != null && !actualQualifiedName.equals(fullyQualifiedName) && !fullyQualifiedName.equals(implicitAliases.get(name)))
            {
              importedNames.add(fullyQualifiedName);
            }
          }
        }
        else
        {
          throw new RuntimeException("Not expecting multi-valued cross references in these models");
        }
      }
      else
      {
        throw new RuntimeException("Expecting all cross references to be part of an assignment in these models");
      }
    }
    return importedNames;
  }

}