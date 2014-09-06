/**
 * Copyright (c) 2014 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.imports.DefaultImportsConfiguration;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XImportSection;
import org.eclipse.xtext.xtype.XtypeFactory;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class XcoreImportsConfiguration extends DefaultImportsConfiguration
{
  @Inject
  private IJvmModelAssociations associations;

  @Inject
  private IResourceScopeCache cache;

  @Override
  public XImportSection getImportSection(final XtextResource resource)
  {
    return
      cache.get
        ("synthetic.import.section",
         resource,
         new Provider<XImportSection>()
         {
           public XImportSection get()
           {
             XImportSection importSection = XtypeFactory.eINSTANCE.createXImportSection();
             EList<XImportDeclaration> importDeclarations = importSection.getImportDeclarations();

             XPackage xPackage = (XPackage) resource.getContents().get(0);
             EList<XImportDirective> importDirectives = xPackage.getImportDirectives();
             for (XImportDirective importDirective : importDirectives)
             {
               JvmDeclaredType importedType = null;
               String importedNamespace = importDirective.getImportedNamespace();
               boolean isWildcard = importedNamespace.endsWith(".*");
                 
               if (!isWildcard)
               {
                 EObject importedObject = importDirective.getImportedObject();
                 if (importedObject instanceof JvmDeclaredType)
                 {
                   importedType = (JvmDeclaredType) importedObject;
                 }
                 else if (importedObject != null)
                 {
                   EObject primaryJvmElement = associations.getPrimaryJvmElement(importedObject);
                   if (primaryJvmElement instanceof JvmDeclaredType)
                   {
                     importedType = (JvmDeclaredType) primaryJvmElement;
                   }
                 }
               }

               if (isWildcard || importedType != null)
               {
                 XImportDeclaration importDeclaration = XtypeFactory.eINSTANCE.createXImportDeclaration();
                 importDeclaration.setImportedType(importedType);
                 importDeclaration.setImportedNamespace(importedNamespace);
                 importDeclaration.setExtension(false);
                 importDeclaration.setStatic(false);
                 importDeclarations.add(importDeclaration);
               }
             }

             return importSection;
           }
         });
  }

  @Override
  public Iterable<JvmDeclaredType> getLocallyDefinedTypes(XtextResource resource)
  {
    List<EObject> contents = resource.getContents();
    String packageName = getPackageName(resource);
    List<JvmDeclaredType> knownTypes = Lists.newArrayListWithExpectedSize(contents.size() / 2);
    for (JvmDeclaredType content : Iterables.filter(contents, JvmDeclaredType.class))
    {
      if (Strings.equal(packageName, content.getPackageName()))
      {
        knownTypes.add(content);
      }
    }
    return knownTypes;
  }

  @Override
  public String getPackageName(XtextResource resource)
  {
    XPackage xPackage = (XPackage) resource.getContents().get(0);
    return xPackage.getName();
  }

  @Override
  public int getImportSectionOffset(XtextResource resource)
  {
    return 0;
  }
}