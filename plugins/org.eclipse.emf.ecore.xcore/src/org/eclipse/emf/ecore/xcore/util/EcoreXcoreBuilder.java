/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XAttribute;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnum;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XModelElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XTypedElement;
import org.eclipse.emf.ecore.xcore.XcoreFactory;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XEnumLiteralMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XPackageMapping;
import org.eclipse.emf.ecore.xcore.mappings.XParameterMapping;
import org.eclipse.emf.ecore.xcore.mappings.XTypeParameterMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.scoping.XcoreImportedNamespaceAwareScopeProvider;
import org.eclipse.xtext.xbase.XBlockExpression;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


public class EcoreXcoreBuilder
{
  @Inject
  private XcoreMapper mapper;

  @Inject
  private XcoreJvmInferrer jvmInferrer;

  protected List<Runnable> runnables = new ArrayList<Runnable>();

  protected GenModel genModel;

  protected Map<EGenericType, XGenericType> genericTypeMap = Maps.newIdentityHashMap();

  public Map<EGenericType, XGenericType> getGenericTypeMap()
  {
    return genericTypeMap;
  }

  public void initialize(GenModel genModel)
  {
    this.genModel = genModel;
  }

  public XPackage getXPackage(EPackage ePackage)
  {
    XPackage xPackage = XcoreFactory.eINSTANCE.createXPackage();
    XPackageMapping mapping = mapper.getMapping(xPackage);
    mapping.setEPackage(ePackage);
    mapper.getToXcoreMapping(ePackage).setXcoreElement(xPackage);
    handleAnnotations(ePackage, xPackage);
    String name = ePackage.getName();
    String nsPrefix = ePackage.getNsPrefix();
    if (name.equals(nsPrefix))
    {
      nsPrefix = null;
    }
    GenPackage genPackage = genModel.findGenPackage(ePackage);
    mapping.setGenPackage(genPackage);
    mapper.getToXcoreMapping(genPackage).setXcoreElement(xPackage);
    String basePackage = genPackage.getBasePackage();
    if (basePackage != null && basePackage.length() > 0)
    {
      name = basePackage + "." + name;
    }
    xPackage.setName(name);
    String nsURI = ePackage.getNsURI();
    if (name.equals(nsURI))
    {
      nsURI = null;
    }

    if (nsPrefix != null || nsURI != null)
    {
      XAnnotation ecoreAnnotation = null;
      for (XAnnotation xAnnotation : xPackage.getAnnotations())
      {
        if (EcorePackage.eNS_URI.equals(xAnnotation.getSource()))
        {
          ecoreAnnotation = xAnnotation;
          break;
        }
      }
      if (ecoreAnnotation == null)
      {
        ecoreAnnotation = XcoreFactory.eINSTANCE.createXAnnotation();
        ecoreAnnotation.setSource(getXAnnotationDirective(xPackage, EcorePackage.eNS_URI));
      }
      if (nsPrefix != null)
      {
        ecoreAnnotation.getDetails().put("nsPrefix", nsPrefix);
      }
      if (nsURI != null)
      {
        ecoreAnnotation.getDetails().put("nsURI", nsURI);
      }
      xPackage.getAnnotations().add(ecoreAnnotation);
    }

    EAnnotation eAnnotation = ePackage.getEAnnotation(XcorePackage.eNS_URI);
    if (eAnnotation != null)
    {
      for (Map.Entry<String, String> detail : eAnnotation.getDetails())
      {
        XAnnotationDirective xAnnotationDirective = XcoreFactory.eINSTANCE.createXAnnotationDirective();
        xAnnotationDirective.setName(detail.getKey());
        xAnnotationDirective.setSourceURI(detail.getValue());
        xPackage.getAnnotationDirectives().add(xAnnotationDirective);
      }
    }

    for (EClassifier eClassifier : ePackage.getEClassifiers())
    {
      XClassifier xClassifier = getXClassifier(eClassifier);
      xPackage.getClassifiers().add(xClassifier);
    }
    return xPackage;
  }

  public void link()
  {
    // Hook up local references.
    //
    for (Runnable runnable : runnables)
    {
      runnable.run();
    }
    runnables.clear();
  }

  void handleAnnotations(final EModelElement eModelElement, final XModelElement xModelElement)
  {
    runnables.add(new Runnable()
      {
        public void run()
        {
          for (EAnnotation eAnnotation : eModelElement.getEAnnotations())
          {
            if (xModelElement instanceof XPackage && GenModelPackage.eNS_URI.equals(eAnnotation.getSource()))
            {
              XAnnotation xAnnotation = null;
              for (Map.Entry<String, String> detail : eAnnotation.getDetails())
              {
                if ("basePackage".equals(detail.getKey()))
                {
                  // This is already handled from the GenPackage.
                  // XPackage xPackage = (XPackage)xModelElement;
                  // xPackage.setName(detail.getValue() + "." + xPackage.getName());
                }
                else
                {
                  if (xAnnotation == null)
                  {
                    xAnnotation = XcoreFactory.eINSTANCE.createXAnnotation();
                    // TODO
                    // map(xAnnotation, eAnnotation);
                    xAnnotation.setSource(getXAnnotationDirective(xModelElement, GenModelPackage.eNS_URI));
                  }
                  xAnnotation.getDetails().put(detail.getKey(), detail.getValue());
                }
              }
              if (xAnnotation != null)
              {
                xModelElement.getAnnotations().add(xAnnotation);
              }
            }
            else if (xModelElement instanceof XPackage && EcorePackage.eNS_URI.equals(eAnnotation.getSource()))
            {
              XAnnotation xAnnotation = null;
              for (Map.Entry<String, String> detail : eAnnotation.getDetails())
              {
                String key = detail.getKey();
                if (!"nsPrefix".equals(key) && !"nsURI".equals(key))
                {
                  if (xAnnotation == null)
                  {
                    xAnnotation = XcoreFactory.eINSTANCE.createXAnnotation();
                    // TODO
                    // map(xAnnotation, eAnnotation);
                    xAnnotation.setSource(getXAnnotationDirective(xModelElement, EcorePackage.eNS_URI));
                  }
                  xAnnotation.getDetails().put(key, detail.getValue());
                }
              }
              if (xAnnotation != null)
              {
                xModelElement.getAnnotations().add(xAnnotation);
              }
            }
            else if (XcorePackage.eNS_URI.equals(eAnnotation.getSource()))
            {
              // Ignore
            }
            // Ignore the empty annotations, such as those that store the body.
            //
            else if (!EcorePackage.eNS_URI.equals(eAnnotation.getSource()) || !eAnnotation.getDetails().isEmpty())
            {
              XAnnotation xAnnotation = XcoreFactory.eINSTANCE.createXAnnotation();
              // TODO
              // map(xAnnotation, eAnnotation);
              String source = eAnnotation.getSource();
              xAnnotation.setSource(getXAnnotationDirective(xModelElement, source));
              for (Map.Entry<String, String> detail : eAnnotation.getDetails())
              {
                xAnnotation.getDetails().put(detail.getKey(), detail.getValue());
              }
              xModelElement.getAnnotations().add(xAnnotation);
            }
          }
        }
      });
  }

  XAnnotationDirective getXAnnotationDirective(XModelElement xModelElement, String source)
  {
    if (source == null)
    {
      source = "";
    }

    XPackage xPackage = (XPackage)EcoreUtil.getRootContainer(xModelElement);
    for (XAnnotationDirective xAnnotationDirective : xPackage.getAnnotationDirectives())
    {
      if (source.equals(xAnnotationDirective.getSourceURI()))
      {
        return xAnnotationDirective;
      }
    }

    Resource xcoreLangResource = XcoreImportedNamespaceAwareScopeProvider.getXcoreLangResource(genModel.eResource().getResourceSet());
    for (XAnnotationDirective xAnnotationDirective : ((XPackage)xcoreLangResource.getContents().get(0)).getAnnotationDirectives())
    {
      if (source.equals(xAnnotationDirective.getSourceURI()))
      {
        return xAnnotationDirective;
      }
    }

    Set<String> names = Sets.newHashSet();
    for (XAnnotationDirective xAnnotationDirective : xPackage.getAnnotationDirectives())
    {
      names.add(xAnnotationDirective.getName());
    }

    XAnnotationDirective xAnnotationDirective = XcoreFactory.eINSTANCE.createXAnnotationDirective();
    xAnnotationDirective.setSourceURI(source);

    // Try to compute a reasonable name to use as an identifier.
    //
    String name = source;
    try
    {
      // Consider the case that the source isn't a well-formed URI.
      // This will throw a runtime exception, in which case we'll use the source itself as the basis for the name.
      //
      URI sourceURI = URI.createURI(source);

      // Use the last segment if there is one and it's not empty.
      //
      name = sourceURI.lastSegment();
      if (name == null || name.length() == 0)
      {
        // Use the URI's if it exists and it's not empty.
        //
        name = sourceURI.path();
        if (name == null || name.length() == 0)
        {
          // Consider the case that's it's hierarchical or not.
          // If it is, use the authority, otherwise use the opaque part.
          //
          boolean hierarchical = sourceURI.isHierarchical();
          name = hierarchical ? sourceURI.authority() : sourceURI.opaquePart();

          // If that's null use the entire source after all.
          //
          if (name == null)
          {
            name = source;
          }
          // Otherwise, if we have the a hierarchical URI...
          //
          else if (hierarchical)
          {
            // Strip off the "www" prefix is there is one.
            //
            if (name.startsWith("www."))
            {
              name = name.substring(4);
            }

            // Strip off what most likely is the domain suffix.
            //
            int index = name.lastIndexOf('.');
            if (index != -1)
            {
              name = name.substring(0, index);
            }
          }
        }
      }
    }
    catch (RuntimeException exception)
    {
      // Just use the resource URI itself as the basis for the name.
    }

    // Coerce the name into a well formed Java identifiers.
    //
    name = CodeGenUtil.javaIdentifier(name, genModel.getLocale());

    // Ensure that the name doesn't collide with that of another annotation.
    //
    String uniqueName = name;
    for (int i = 1; names.contains(uniqueName); ++i)
    {
      uniqueName = name + "_" + i;
    }
    xAnnotationDirective.setName(uniqueName);
    xPackage.getAnnotationDirectives().add(xAnnotationDirective);
    return xAnnotationDirective;
  }

  XClassifier getXClassifier(final EClassifier eClassifier)
  {
    final XClassifier xClassifier = eClassifier instanceof EClass ? getXClass((EClass)eClassifier) : eClassifier instanceof EEnum
      ? getXEnum((EEnum)eClassifier) : getXDataType((EDataType)eClassifier);
    handleAnnotations(eClassifier, xClassifier);
    xClassifier.setName(eClassifier.getName());
    String instanceTypeName = eClassifier.getInstanceTypeName();
    if (instanceTypeName != null)
    {
      final String finalInstanceTypeName = instanceTypeName;
      runnables.add(new Runnable()
        {
          public void run()
          {
            Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(finalInstanceTypeName);
            xClassifier.setInstanceType(jvmInferrer.getJvmTypeReference((EGenericType)diagnostic.getData().get(0), eClassifier));
          }
        });
    }
    GenClassifier genClassifier = genModel.findGenClassifier(eClassifier);
    EList<GenTypeParameter> genTypeParameters = genClassifier.getGenTypeParameters();
    int index = 0;
    for (ETypeParameter eTypeParameter : eClassifier.getETypeParameters())
    {
      XTypeParameter xTypeParameter = getXTypeParameter(eTypeParameter);
      XTypeParameterMapping xTypeParameterMapping = mapper.getMapping(xTypeParameter);
      GenTypeParameter genTypeParameter = genTypeParameters.get(index++);
      xTypeParameterMapping.setETypeParameter(eTypeParameter);
      xTypeParameterMapping.setGenTypeParameter(genTypeParameter);
      mapper.getToXcoreMapping(eTypeParameter).setXcoreElement(xTypeParameter);
      mapper.getToXcoreMapping(genTypeParameter).setXcoreElement(xTypeParameter);
      xClassifier.getTypeParameters().add(xTypeParameter);
    }
    return xClassifier;
  }

  XClass getXClass(EClass eClass)
  {
    XClass xClass = XcoreFactory.eINSTANCE.createXClass();
    XClassMapping mapping = mapper.getMapping(xClass);
    mapping.setEClass(eClass);
    GenClass genClass = (GenClass)genModel.findGenClassifier(eClass);
    mapping.setGenClass(genClass);
    mapper.getToXcoreMapping(eClass).setXcoreElement(xClass);
    mapper.getToXcoreMapping(genClass).setXcoreElement(xClass);
    if (eClass.isInterface())
    {
      xClass.setInterface(true);
    }
    else if (eClass.isAbstract())
    {
      xClass.setAbstract(true);
    }

    for (EGenericType eGenericSuperType : eClass.getEGenericSuperTypes())
    {
      xClass.getSuperTypes().add(getXGenericType(eGenericSuperType));
    }

    for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures())
    {
      XStructuralFeature xStructuralFeature;
      if (eStructuralFeature instanceof EReference)
      {
        xStructuralFeature = getXReference((EReference)eStructuralFeature);

      }
      else
      {
        xStructuralFeature = getXAttribute((EAttribute)eStructuralFeature);
      }
      xClass.getMembers().add(xStructuralFeature);
    }

    for (EOperation eOperation : eClass.getEOperations())
    {
      XOperation xOperation = getXOperation(eOperation);
      xClass.getMembers().add(xOperation);
    }
    return xClass;
  }

  XOperation getXOperation(EOperation eOperation)
  {
    final XOperation xOperation = XcoreFactory.eINSTANCE.createXOperation();
    XOperationMapping mapping = mapper.getMapping(xOperation);
    mapping.setEOperation(eOperation);
    GenOperation genOperation = genModel.findGenOperation(eOperation);
    mapping.setGenOperation(genOperation);
    mapper.getToXcoreMapping(eOperation).setXcoreElement(xOperation);
    mapper.getToXcoreMapping(genOperation).setXcoreElement(xOperation);
    handleXTypedElement(xOperation, eOperation);
    EList<GenParameter> genParameters = genOperation.getGenParameters();
    int index = 0;
    for (EParameter eParameter : eOperation.getEParameters())
    {
      XParameter xParameter = getXParameter(eParameter);
      XParameterMapping xParameterMapping = mapper.getMapping(xParameter);
      GenParameter genParameter = genParameters.get(index++);
      xParameterMapping.setEParameter(eParameter);
      xParameterMapping.setGenParameter(genParameter);
      mapper.getToXcoreMapping(eParameter).setXcoreElement(xParameter);
      mapper.getToXcoreMapping(genParameter).setXcoreElement(xParameter);
      xOperation.getParameters().add(xParameter);
    }
    EList<GenTypeParameter> genTypeParameters = genOperation.getGenTypeParameters();
    index = 0;
    for (ETypeParameter eTypeParameter : eOperation.getETypeParameters())
    {
      XTypeParameter xTypeParameter = getXTypeParameter(eTypeParameter);
      XTypeParameterMapping xTypeParameterMapping = mapper.getMapping(xTypeParameter);
      GenTypeParameter genTypeParameter = genTypeParameters.get(index++);
      xTypeParameterMapping.setETypeParameter(eTypeParameter);
      xTypeParameterMapping.setGenTypeParameter(genTypeParameter);
      mapper.getToXcoreMapping(eTypeParameter).setXcoreElement(xTypeParameter);
      mapper.getToXcoreMapping(genTypeParameter).setXcoreElement(xTypeParameter);
      xOperation.getTypeParameters().add(xTypeParameter);
    }
    for (EGenericType eException : eOperation.getEGenericExceptions())
    {
      XGenericType xException = getXGenericType(eException);
      xOperation.getExceptions().add(xException);
    }
    EAnnotation ecoreAnnotation = eOperation.getEAnnotation(EcorePackage.eNS_URI);
    if (ecoreAnnotation != null && !ecoreAnnotation.getContents().isEmpty())
    {
      EObject body = ecoreAnnotation.getContents().get(0);
      if (body instanceof XBlockExpression)
      {
        xOperation.setBody((XBlockExpression)body);
      }
    }

    if (genOperation.isInvariant())
    {
      runnables.add
        (new Runnable()
         {
           public void run()
           {
             XAnnotation xAnnotation = xOperation.getAnnotation(EcorePackage.eNS_URI);
             if (xAnnotation == null)
             {
               xAnnotation = XcoreFactory.eINSTANCE.createXAnnotation();
               xAnnotation.setSource(getXAnnotationDirective(xOperation, EcorePackage.eNS_URI));
               xOperation.getAnnotations().add(xAnnotation);
             }
             xAnnotation.getDetails().put("invariant", "true");
          }
        });
    }
    return xOperation;
  }

  XParameter getXParameter(EParameter eParameter)
  {
    XParameter xParameter = XcoreFactory.eINSTANCE.createXParameter();
    handleXTypedElement(xParameter, eParameter);
    return xParameter;
  }

  XTypeParameter getXTypeParameter(ETypeParameter eTypeParameter)
  {
    XTypeParameter xTypeParameter = XcoreFactory.eINSTANCE.createXTypeParameter();
    mapper.getToXcoreMapping(eTypeParameter).setXcoreElement(xTypeParameter);
    //TODO
    // map(xTypeParameter, eTypeParameter);
    xTypeParameter.setName(eTypeParameter.getName());
    for (EGenericType eGenericType : eTypeParameter.getEBounds())
    {
      xTypeParameter.getBounds().add(getXGenericType(eGenericType));
    }
    return xTypeParameter;
  }

  void handleXTypedElement(XTypedElement xTypedElement, ETypedElement eTypedElement)
  {
    handleAnnotations(eTypedElement, xTypedElement);
    xTypedElement.setName(eTypedElement.getName());
    xTypedElement.setType(getXGenericType(eTypedElement.getEGenericType()));
    if (eTypedElement.isUnique() && !(eTypedElement instanceof EReference) && eTypedElement.isMany())
    {
      xTypedElement.setUnique(true);
    }
    if (!eTypedElement.isOrdered())
    {
      xTypedElement.setUnordered(true);
    }
    int lowerBound = eTypedElement.getLowerBound();
    int upperBound = eTypedElement.getUpperBound();
    if (lowerBound == 0)
    {
      if (upperBound == EStructuralFeature.UNBOUNDED_MULTIPLICITY)
      {
        xTypedElement.setMultiplicity(new int []{});
      }
      else if (upperBound == 1)
      {
        // xTypedElement.setMultiplicity(new int [] {-3});
        // This is the default.
      }
      else
      {
        xTypedElement.setMultiplicity(new int []{ 0, upperBound });
      }
    }
    else if (lowerBound == upperBound)
    {
      xTypedElement.setMultiplicity(new int []{ lowerBound });
    }
    else if (lowerBound == 1)
    {
      if (upperBound == EStructuralFeature.UNBOUNDED_MULTIPLICITY)
      {
        xTypedElement.setMultiplicity(new int []{ -2 });
      }
      else
      {
        xTypedElement.setMultiplicity(new int []{ 1, upperBound });
      }
    }
    else
    {
      xTypedElement.setMultiplicity(new int []{ lowerBound, upperBound });
    }
  }

  XGenericType getXGenericType(final EGenericType eGenericType)
  {
    if (eGenericType == null)
    {
      return null;
    }
    else
    {
      final XGenericType xGenericType = XcoreFactory.eINSTANCE.createXGenericType();
      genericTypeMap.put(eGenericType, xGenericType);
      EGenericType lowerBound = eGenericType.getELowerBound();
      if (lowerBound != null)
      {
        xGenericType.setLowerBound(getXGenericType(lowerBound));
      }
      EGenericType upperBound = eGenericType.getEUpperBound();
      if (upperBound != null)
      {
        xGenericType.setUpperBound(getXGenericType(upperBound));
      }
      for (EGenericType typeArgument : eGenericType.getETypeArguments())
      {
        xGenericType.getTypeArguments().add(getXGenericType(typeArgument));
      }

      EClassifier eClassifier = eGenericType.getEClassifier();
      if (eClassifier != null)
      {
        xGenericType.setType(genModel.findGenClassifier(eClassifier));
      }
      else
      {
        ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
        if (eTypeParameter != null)
        {
          xGenericType.setType(genModel.findGenTypeParameter(eTypeParameter));
        }
      }

      return xGenericType;
    }
  }

  XClassifier getClassifier(XPackage xPackage, String name)
  {
    for (XClassifier xClassifier : xPackage.getClassifiers())
    {
      if (name.equals(xClassifier.getName()))
      {
        return xClassifier;

      }
    }
    return null;
  }

  XStructuralFeature getStructuralFeature(XClass xClass, String name)
  {
    for (XMember xMember : xClass.getMembers())
    {
      if (xMember instanceof XStructuralFeature && name.equals(xMember.getName()))
      {
        return (XStructuralFeature)xMember;
      }
    }
    return null;
  }

  XReference getXReference(EReference eReference)
  {
    final XReference xReference = XcoreFactory.eINSTANCE.createXReference();
    XFeatureMapping mapping = mapper.getMapping(xReference);
    mapping.setEStructuralFeature(eReference);
    GenFeature genFeature = genModel.findGenFeature(eReference);
    mapping.setGenFeature(genFeature);
    mapper.getToXcoreMapping(eReference).setXcoreElement(xReference);
    mapper.getToXcoreMapping(genFeature).setXcoreElement(xReference);
    if (eReference.isContainment())
    {
      xReference.setContainment(true);
      if (genFeature.isResolveProxies())
      {
        xReference.setResolveProxies(true);
      }
    }
    else if (eReference.isContainer())
    {
      xReference.setContainer(true);
      if (genFeature.isResolveProxies())
      {
        xReference.setResolveProxies(true);
      }
    }
    else if (!eReference.isResolveProxies())
    {
      xReference.setLocal(true);
    }
    EReference opposite = eReference.getEOpposite();
    if (opposite != null)
    {
      xReference.setOpposite(genModel.findGenFeature(opposite));
    }
    for (EAttribute eKey : eReference.getEKeys())
    {
      xReference.getKeys().add(genModel.findGenFeature(eKey));
    }

    handleXStructuralFeature(xReference, eReference);
    return xReference;
  }

  XAttribute getXAttribute(EAttribute eAttribute)
  {
    final XAttribute xAttribute = XcoreFactory.eINSTANCE.createXAttribute();
    XFeatureMapping mapping = mapper.getMapping(xAttribute);
    mapping.setEStructuralFeature(eAttribute);
    GenFeature genFeature = genModel.findGenFeature(eAttribute);
    mapping.setGenFeature(genFeature);
    mapper.getToXcoreMapping(eAttribute).setXcoreElement(xAttribute);
    mapper.getToXcoreMapping(genFeature).setXcoreElement(xAttribute);
    if (eAttribute.isID())
    {
      xAttribute.setID(true);
    }
    String defaultValueLiteral = eAttribute.getDefaultValueLiteral();
    if (defaultValueLiteral != null)
    {
      xAttribute.setDefaultValueLiteral(defaultValueLiteral);
    }
    handleXStructuralFeature(xAttribute, eAttribute);
    return xAttribute;
  }

  void handleXStructuralFeature(XStructuralFeature xStructuralFeature, EStructuralFeature eStructuralFeature)
  {
    if (!eStructuralFeature.isChangeable())
    {
      xStructuralFeature.setReadonly(true);
    }
    if (eStructuralFeature.isTransient())
    {
      xStructuralFeature.setTransient(true);
    }
    if (eStructuralFeature.isVolatile())
    {
      xStructuralFeature.setVolatile(true);
    }
    if (eStructuralFeature.isDerived())
    {
      xStructuralFeature.setDerived(true);
    }
    if (eStructuralFeature.isUnsettable())
    {
      xStructuralFeature.setUnsettable(true);
    }
    handleXTypedElement(xStructuralFeature, eStructuralFeature);
  }

  XDataType getXDataType(EDataType eDataType)
  {
    XDataType xDataType = XcoreFactory.eINSTANCE.createXDataType();
    XDataTypeMapping mapping = mapper.getMapping(xDataType);
    mapping.setEDataType(eDataType);
    GenDataType genDataType = (GenDataType)genModel.findGenClassifier(eDataType);
    mapping.setGenDataType(genDataType);
    mapper.getToXcoreMapping(eDataType).setXcoreElement(xDataType);
    mapper.getToXcoreMapping(genDataType).setXcoreElement(xDataType);
    return xDataType;
  }

  XEnum getXEnum(EEnum eEnum)
  {
    XEnum xEnum = XcoreFactory.eINSTANCE.createXEnum();
    XDataTypeMapping mapping = mapper.getMapping(xEnum);
    mapping.setEDataType(eEnum);
    GenEnum genEnum = (GenEnum)genModel.findGenClassifier(eEnum);
    mapping.setGenDataType(genEnum);
    mapper.getToXcoreMapping(eEnum).setXcoreElement(xEnum);
    mapper.getToXcoreMapping(genEnum).setXcoreElement(xEnum);
    for (EEnumLiteral eEnumLiteral : eEnum.getELiterals())
    {
      XEnumLiteral xEnumLiteral = getXEnumLiteral(eEnumLiteral);
      GenEnumLiteral genEnumLiteral = genEnum.getGenEnumLiteral(eEnumLiteral.getLiteral());
      XEnumLiteralMapping xEnumLiteralMapping = mapper.getMapping(xEnumLiteral);
      xEnumLiteralMapping.setEEnumLiteral(eEnumLiteral);
      xEnumLiteralMapping.setGenEnumLiteral(genEnumLiteral);
      mapper.getToXcoreMapping(eEnumLiteral).setXcoreElement(xEnumLiteral);
      mapper.getToXcoreMapping(genEnumLiteral).setXcoreElement(xEnumLiteral);
      xEnum.getLiterals().add(xEnumLiteral);
    }
    return xEnum;
  }

  XEnumLiteral getXEnumLiteral(EEnumLiteral eEnumLiteral)
  {
    XEnumLiteral xEnumLiteral = XcoreFactory.eINSTANCE.createXEnumLiteral();
    handleAnnotations(eEnumLiteral, xEnumLiteral);
    xEnumLiteral.setName(eEnumLiteral.getName());
    if (!eEnumLiteral.getName().equals(eEnumLiteral.getLiteral()))
    {
      xEnumLiteral.setLiteral(eEnumLiteral.getLiteral());
    }
    xEnumLiteral.setValue(eEnumLiteral.getValue());
    return xEnumLiteral;
  }
}
