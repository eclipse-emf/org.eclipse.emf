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
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EClassifierImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
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
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.interpreter.IClassLoaderProvider;
import org.eclipse.emf.ecore.xcore.interpreter.XcoreConversionDelegate;
import org.eclipse.emf.ecore.xcore.interpreter.XcoreInterpreter;
import org.eclipse.emf.ecore.xcore.interpreter.XcoreInvocationDelegate;
import org.eclipse.emf.ecore.xcore.interpreter.XcoreSettingDelegate;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.services.XcoreGrammarAccess;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.XBlockExpression;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class XcoreEcoreBuilder
{
  private static final Pattern COMMENT_LINE_BREAK_PATTERN = Pattern.compile("([ \t]*((\n\r?)|(\r\n?))(\\s*\\*\\s?)?)|\\s*$", Pattern.MULTILINE);

  @Inject
  private XcoreMapper mapper;

  @Inject
  private Provider<XcoreInvocationDelegate> operationDelegateProvider;

  @Inject
  private Provider<XcoreSettingDelegate> settingDelegateProvider;

  @Inject
  private Provider<XcoreConversionDelegate> conversionDelegateProvider;

  @Inject
  private Provider<XcoreInterpreter> interpreterProvider;

  @Inject
  private IClassLoaderProvider classLoaderProvider;

  @Inject
  private XcoreGrammarAccess xcoreGrammarAccess;

  protected List<Runnable> runnables = new ArrayList<Runnable>();

  protected List<Runnable> instanceTypeRunnables = new ArrayList<Runnable>();

  protected XcoreInterpreter interpreter;

  protected ClassLoader classLoader;

  public void link()
  {
    // Hook up local references.
    //
    List<Runnable> currentRunnables = new ArrayList<Runnable>(runnables);
    runnables.clear();
    for (Runnable runnable : currentRunnables)
    {
      runnable.run();
    }
    for (Runnable runnable : runnables)
    {
      runnable.run();
    }
  }

  public void linkInstanceTypes()
  {
    // Hook up instance types.
    //
    for (Runnable runnable : instanceTypeRunnables)
    {
      runnable.run();
    }
  }

  public static void setQualifiedPackageName(EPackage ePackage, String name)
  {
    String basePackage = null;
    if (name != null)
    {
      int index = name.lastIndexOf(".");
      if (index == -1)
      {
        basePackage = null;
        ePackage.setName(name);
      }
      else
      {
        basePackage = name.substring(0, index);
        ePackage.setName(name.substring(index + 1));
      }
    }
    else
    {
      ePackage.setName("_");
    }
    EcoreUtil.setAnnotation(ePackage, GenModelPackage.eNS_URI, "basePackage", basePackage);
  }

  protected String nonNullName(String name)
  {
    return name == null ? "_" : name;
  }

  public EPackage getEPackage(XPackage xPackage)
  {
    interpreter = interpreterProvider.get();
    classLoader = classLoaderProvider.getClassLoader(xPackage.eResource().getResourceSet());
    if (classLoader != null)
    {
      interpreter.setClassLoader(classLoader);
    }
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    mapper.getMapping(xPackage).setEPackage(ePackage);
    mapper.getToXcoreMapping(ePackage).setXcoreElement(xPackage);
    handleAnnotations(xPackage, ePackage);
    String name = xPackage.getName();
    setQualifiedPackageName(ePackage, name);
    if (name != null)
    {
      // The pre linking phase model won't process the annotations and won't pick up the nsURI in the annotation.
      // That's particularly problematic because the nsURI is something indexed from this prelinked model.
      // At least to this for Ecore to avoid inducing a model with circular inheritance.
      // TODO
      //
      if (ePackage.getNsURI() == null)
      {
        ePackage.setNsURI("org.eclipse.emf.ecore".equals(name) ? EcorePackage.eNS_URI : name);
      }
      if (ePackage.getNsPrefix() == null)
      {
        ePackage.setNsPrefix(ePackage.getName());
      }
    }

    EList<EClassifier> eClassifiers = ePackage.getEClassifiers();
    for (XClassifier xClassifier : xPackage.getClassifiers())
    {
      EClassifier eClassifier = getEClassifier(xClassifier);
      eClassifiers.add(eClassifier);
    }

    for (XAnnotationDirective xAnnotationDirective : xPackage.getAnnotationDirectives())
    {
      EcoreUtil.setAnnotation(ePackage, XcorePackage.eNS_URI, xAnnotationDirective.getName(), xAnnotationDirective.getSourceURI());
    }

    return ePackage;
  }

  void handleAnnotations(final XModelElement xModelElement, final EModelElement eModelElement)
  {
    ICompositeNode node = NodeModelUtils.getNode(xModelElement);
    if (node != null)
    {
      for (ILeafNode child : node.getLeafNodes())
      {
        if (child.isHidden())
        {
          if (child.getGrammarElement() == xcoreGrammarAccess.getML_COMMENTRule())
          {
            String text = child.getText();
            int length = text.length();
            if (length > 4)
            {
              String[] lines = COMMENT_LINE_BREAK_PATTERN.split(text.subSequence(2, length - 2), 0);
              StringBuilder comment = null;
              for (String line : lines)
              {
                if (line.length() != 0)
                {
                  if (comment == null)
                  {
                    comment = new StringBuilder();
                    comment.append(line);
                  }
                  else
                  {
                    comment.append('\n');
                    comment.append(line);
                  }
                }
                else if (comment != null)
                {
                  comment.append('\n');
                  comment.append(line);
                }
              }
              if (comment != null)
              {
                EcoreUtil.setDocumentation(eModelElement, comment.toString());
              }
            }
            break;
          }
        }
        else
        {
          break;
        }
      }
    }
    handleAnnotations(xModelElement, eModelElement, true);
    runnables.add
      (new Runnable()
       {
         public void run()
         {
           handleAnnotations(xModelElement, eModelElement, false);
         }
       });
  }

  protected void handleAnnotations(final XModelElement xModelElement, final EModelElement eModelElement, boolean preIndexing)
  {
    EList<EAnnotation> eAnnotations = eModelElement.getEAnnotations();
    for (XAnnotation xAnnotation : xModelElement.getAnnotations())
    {
      //      map(eAnnotation, xAnnotation);
      String sourceURI = null;

      if (preIndexing)
      {
        List<INode> nodes = NodeModelUtils.findNodesForFeature(xAnnotation, XcorePackage.Literals.XANNOTATION__SOURCE);
        StringBuilder annotationLiteral = new StringBuilder();
        for (INode node : nodes)
        {
          annotationLiteral.append(NodeModelUtils.getTokenText(node));
        }
        int index = annotationLiteral.lastIndexOf(" ");
        if (index != -1)
        {
          annotationLiteral.delete(0, index + 1);
        }
        index = annotationLiteral.lastIndexOf(".");
        if (index != -1)
        {
          annotationLiteral.delete(0, index + 1);
        }
        String simpleAnnotationName = annotationLiteral.toString();
        if ("GenModel".equals(simpleAnnotationName))
        {
          sourceURI = GenModelPackage.eNS_URI;
        }
        else if ("Ecore".equals(simpleAnnotationName))
        {
          sourceURI = EcorePackage.eNS_URI;
        }
        else if ("ExtendedMetaData".equals(simpleAnnotationName))
        {
          sourceURI = ExtendedMetaData.ANNOTATION_URI;
        }
      }
      else
      {
        XAnnotationDirective source = xAnnotation.getSource();
        if (source != null)
        {
          sourceURI = source.getSourceURI();
        }
      }

      if (sourceURI != null)
      {
        EClass eClass = EcorePackage.eNS_URI.equals(sourceURI) ? eModelElement.eClass() : null;
   
        EMap<String, String> details = xAnnotation.getDetails();
        if (details.isEmpty())
        {
          EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
          eAnnotation.setSource(sourceURI);
          eAnnotations.add(eAnnotation);
        }
        else
        {
          for (Map.Entry<String, String> detail : details)
          {
            String key = detail.getKey();
            String value = detail.getValue();
            if (eClass != null)
            {
              EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(key);
              if (eStructuralFeature instanceof EAttribute)
              {
                // Be more careful about exceptions.
                // TODO
                //
                eModelElement.eSet(eStructuralFeature, value);
                continue;
              }
            }
            if (EcoreUtil.getAnnotation(eModelElement, sourceURI, key) == null)
            {
              EcoreUtil.setAnnotation(eModelElement, sourceURI, key, value);
            }
          }
        }
      }
    }
  }

  EClassifier getEClassifier(final XClassifier xClassifier)
  {
    final EClassifier eClassifier = xClassifier instanceof XClass ? getEClass((XClass)xClassifier) : xClassifier instanceof XEnum
      ? getEEnum((XEnum)xClassifier) : getEDataType((XDataType)xClassifier);
    handleAnnotations(xClassifier, eClassifier);
    eClassifier.setName(nonNullName(xClassifier.getName()));

    // Test if there is an instance type name but avoid resolving proxies.
    //
    if (xClassifier.eIsSet(XcorePackage.Literals.XCLASSIFIER__INSTANCE_TYPE))
    {
      if (eClassifier instanceof EClass)
      {
        // For classes that wrap interfaces, it's important the instance type name isn't null when the XcoreJvmInferrer infers whether to generate an interface,
        // so set a dummy result earlier and allow it to be updated later.
        //
        eClassifier.setInstanceTypeName("java.lang.Cloneable");
      }
      instanceTypeRunnables.add
        (new Runnable()
         {
           public void run()
           {
             JvmTypeReference instanceType = xClassifier.getInstanceType();
             if (instanceType != null)
             {
               String instanceTypeName = instanceType.getIdentifier();
               String normalizedInstanceTypeName = EcoreUtil.toJavaInstanceTypeName((EGenericType)EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(instanceTypeName).getData().get(0));
               eClassifier.setInstanceTypeName(normalizedInstanceTypeName);
               if (classLoader != null && eClassifier instanceof EClassifierImpl)
               {
                 try
                 {
                   Class<?> instanceClass = classLoader.loadClass(eClassifier.getInstanceClassName());
                   ((EClassifierImpl)eClassifier).setInstanceClassGen(instanceClass);
                 }
                 catch (Throwable throwable)
                 {
                   // Ignore.
                 }
               }
             }
           }
         });
    }
    return eClassifier;
  }

  EClass getEClass(final XClass xClass)
  {
    final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    mapper.getMapping(xClass).setEClass(eClass);
    mapper.getToXcoreMapping(eClass).setXcoreElement(xClass);
    if (xClass.isInterface())
    {
      eClass.setInterface(true);
      eClass.setAbstract(true);
    }
    else if (xClass.isAbstract())
    {
      eClass.setAbstract(true);
    }
    EList<EGenericType> eGenericSuperTypes = eClass.getEGenericSuperTypes();
    for (XGenericType superType : xClass.getSuperTypes())
    {
      eGenericSuperTypes.add(getEGenericType(superType));
    }
    EList<ETypeParameter> eTypeParameters = eClass.getETypeParameters();
    for (XTypeParameter xTypeParameter : xClass.getTypeParameters())
    {
      ETypeParameter eTypeParameter = getETypeParameter(xTypeParameter);
      eTypeParameters.add(eTypeParameter);
    }
    EList<EOperation> eOperations = eClass.getEOperations();
    EList<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
    for (XMember xMember : xClass.getMembers())
    {
      if (xMember instanceof XOperation)
      {
        EOperation eOperation = getEOperation((XOperation)xMember);
        eOperations.add(eOperation);
      }
      else if (xMember instanceof XReference)
      {
        EReference eReference = getEReference((XReference)xMember);
        eStructuralFeatures.add(eReference);
      }
      else
      {
        EAttribute eAttribute = getEAttribute((XAttribute)xMember);
        eStructuralFeatures.add(eAttribute);
      }
    }
    return eClass;
  }

  EOperation getEOperation(XOperation xOperation)
  {
    EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
    mapper.getMapping(xOperation).setEOperation(eOperation);
    mapper.getToXcoreMapping(eOperation).setXcoreElement(xOperation);
    eOperation.setUnique(false);
    handleETypedElement(eOperation, xOperation);
    EList<ETypeParameter> eTypeParameters = eOperation.getETypeParameters();
    for (XTypeParameter xTypeParameter : xOperation.getTypeParameters())
    {
      ETypeParameter eTypeParameter = getETypeParameter(xTypeParameter);
      eTypeParameters.add(eTypeParameter);
    }
    EList<EParameter> eParameters = eOperation.getEParameters();
    for (XParameter xParameter : xOperation.getParameters())
    {
      EParameter eParameter = getEParameter(xParameter);
      eParameters.add(eParameter);
    }
    for (XGenericType exception : xOperation.getExceptions())
    {
      EGenericType eException = getEGenericType(exception);
      eOperation.getEGenericExceptions().add(eException);
    }
    XBlockExpression body = xOperation.getBody();
    if (body != null)
    {
      final XcoreInvocationDelegate invocationDelegate = operationDelegateProvider.get();
      invocationDelegate.initialize(body, eOperation, interpreter);
      ((EOperation.Internal)eOperation).setInvocationDelegate(invocationDelegate);

      // Set a body annotation so that GenOperation.hasBody will return true.
      //
      EcoreUtil.setAnnotation(eOperation, GenModelPackage.eNS_URI, "body", "");
    }
    return eOperation;
  }

  EParameter getEParameter(XParameter xParameter)
  {
    EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
    mapper.getMapping(xParameter).setEParameter(eParameter);
    mapper.getToXcoreMapping(eParameter).setXcoreElement(xParameter);
    eParameter.setUnique(false);
    handleETypedElement(eParameter, xParameter);
    return eParameter;
  }

  ETypeParameter getETypeParameter(XTypeParameter xTypeParameter)
  {
    ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
    mapper.getMapping(xTypeParameter).setETypeParameter(eTypeParameter);
    mapper.getToXcoreMapping(eTypeParameter).setXcoreElement(xTypeParameter);
    handleAnnotations(xTypeParameter, eTypeParameter);
    eTypeParameter.setName(nonNullName(xTypeParameter.getName()));
    EList<EGenericType> eBounds = eTypeParameter.getEBounds();
    for (XGenericType xGenericType : xTypeParameter.getBounds())
    {
      eBounds.add(getEGenericType(xGenericType));
    }
    return eTypeParameter;
  }

  void handleETypedElement(ETypedElement eTypedElement, XTypedElement xTypedElement)
  {
    eTypedElement.setName(nonNullName(xTypedElement.getName()));
    handleAnnotations(xTypedElement, eTypedElement);
    eTypedElement.setEGenericType(getEGenericType(xTypedElement.getType()));
    if (xTypedElement.isUnordered())
    {
      eTypedElement.setOrdered(false);
    }
    if (xTypedElement.isUnique())
    {
      eTypedElement.setUnique(true);
    }
    int[] multiplicity = xTypedElement.getMultiplicity();
    if (multiplicity == null)
    {
      // optional is the default
      //
    }
    else if (multiplicity.length == 0)
    {
      // []
      //
      eTypedElement.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
    }
    else if (multiplicity.length == 1)
    {
      if (multiplicity[0] == -3)
      {
        // [?]
        //
      }
      else if (multiplicity[0] == -2)
      {
        // [+]
        //
        eTypedElement.setLowerBound(1);
        eTypedElement.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
      }
      else if (multiplicity[0] == -1)
      {
        // [*]
        //
        eTypedElement.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
      }
      else
      {
        // [n]
        //
        eTypedElement.setLowerBound(multiplicity[0]);
        eTypedElement.setUpperBound(multiplicity[0]);
      }
    }
    else
    {
      eTypedElement.setLowerBound(multiplicity[0]);
      eTypedElement.setUpperBound(multiplicity[1]);
    }
  }

  EGenericType getEGenericType(final XGenericType xGenericType)
  {
    if (xGenericType == null)
    {
      return null;
    }
    else
    {
      final EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      //      map(eGenericType, xGenericType);
      XGenericType lowerBound = xGenericType.getLowerBound();
      if (lowerBound != null)
      {
        eGenericType.setELowerBound(getEGenericType(lowerBound));
      }
      XGenericType upperBound = xGenericType.getUpperBound();
      if (upperBound != null)
      {
        eGenericType.setEUpperBound(getEGenericType(upperBound));
      }
      EList<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
      for (XGenericType typeArgument : xGenericType.getTypeArguments())
      {
        eTypeArguments.add(getEGenericType(typeArgument));
      }

      runnables.add(new Runnable()
        {
          public void run()
          {
            GenBase type = xGenericType.getType();
            if (type instanceof GenTypeParameter)
            {
              eGenericType.setETypeParameter(((GenTypeParameter)type).getEcoreTypeParameter());
            }
            else if (type instanceof GenClassifier)
            {
              eGenericType.setEClassifier(((GenClassifier)type).getEcoreClassifier());
            }
          }
        });
      return eGenericType;
    }
  }

  EReference getEReference(final XReference xReference)
  {
    final EReference eReference = EcoreFactory.eINSTANCE.createEReference();
    mapper.getMapping(xReference).setEStructuralFeature(eReference);
    mapper.getToXcoreMapping(eReference).setXcoreElement(xReference);
    if (xReference.isContainment())
    {
      eReference.setContainment(true);
      if (!xReference.isResolveProxies())
      {
        eReference.setResolveProxies(false);
      }
    }
    if (xReference.isLocal())
    {
      eReference.setResolveProxies(false);
    }

    handleEStructuralFeature(eReference, xReference);

    runnables.add(new Runnable()
      {
        public void run()
        {
          GenFeature opposite = xReference.getOpposite();
          if (opposite != null)
          {
            eReference.setEOpposite((EReference)opposite.getEcoreFeature());
          }
          runnables.add(new Runnable()
            {
              public void run()
              {
                for (GenFeature key : xReference.getKeys())
                {
                  EStructuralFeature eAttribute = key.getEcoreFeature();
                  if (eAttribute instanceof EAttribute)
                  {
                    eReference.getEKeys().add((EAttribute)eAttribute);
                  }
                }
              }});
        }
      });

    return eReference;
  }

  EAttribute getEAttribute(final XAttribute xAttribute)
  {
    final EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    mapper.getMapping(xAttribute).setEStructuralFeature(eAttribute);
    mapper.getToXcoreMapping(eAttribute).setXcoreElement(xAttribute);
    eAttribute.setUnique(false);
    if (xAttribute.isID())
    {
      eAttribute.setID(true);
    }
    eAttribute.setDefaultValueLiteral(xAttribute.getDefaultValueLiteral());
    handleEStructuralFeature(eAttribute, xAttribute);
    return eAttribute;
  }

  void handleEStructuralFeature(EStructuralFeature eStructuralFeature, XStructuralFeature xStructuralFeature)
  {
    eStructuralFeature.setName(nonNullName(xStructuralFeature.getName()));
    handleETypedElement(eStructuralFeature, xStructuralFeature);
    if (xStructuralFeature.isReadonly())
    {
      eStructuralFeature.setChangeable(false);
    }
    if (xStructuralFeature.isTransient())
    {
      eStructuralFeature.setTransient(true);
    }
    if (xStructuralFeature.isVolatile())
    {
      eStructuralFeature.setVolatile(true);
    }
    if (xStructuralFeature.isUnsettable())
    {
      eStructuralFeature.setUnsettable(true);
    }
    if (xStructuralFeature.isDerived())
    {
      eStructuralFeature.setDerived(true);
    }

    XBlockExpression getBody = xStructuralFeature.getGetBody();
    XBlockExpression setBody = xStructuralFeature.getSetBody();
    XBlockExpression isSetBody = xStructuralFeature.getIsSetBody();
    XBlockExpression unsetBody = xStructuralFeature.getUnsetBody();
    if (getBody != null || setBody != null || isSetBody != null || unsetBody != null)
    {
      XcoreSettingDelegate settingDelegate = settingDelegateProvider.get();
      settingDelegate.initialize(getBody, setBody, isSetBody, unsetBody, eStructuralFeature, interpreter);
      ((EStructuralFeature.Internal)eStructuralFeature).setSettingDelegate(settingDelegate);
    }

    if (getBody != null)
    {
      eStructuralFeature.setTransient(true);
      eStructuralFeature.setVolatile(true);
      eStructuralFeature.setDerived(true);
      if (setBody == null)
      {
        eStructuralFeature.setChangeable(false);
      }
    }
  }

  EDataType getEDataType(XDataType xDataType)
  {
    EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
    mapper.getMapping(xDataType).setEDataType(eDataType);
    mapper.getToXcoreMapping(eDataType).setXcoreElement(xDataType);
    EList<ETypeParameter> eTypeParameters = eDataType.getETypeParameters();
    for (XTypeParameter xTypeParameter : xDataType.getTypeParameters())
    {
      ETypeParameter eTypeParameter = getETypeParameter(xTypeParameter);
      eTypeParameters.add(eTypeParameter);
    }
    XBlockExpression createBody = xDataType.getCreateBody();
    if (createBody != null)
    {
      XcoreConversionDelegate conversionDelegate = conversionDelegateProvider.get();
      conversionDelegate.initialize(createBody, xDataType.getConvertBody(), eDataType, interpreter);
      ((EDataType.Internal)eDataType).setConversionDelegate(conversionDelegate);
    }
    return eDataType;
  }

  EEnum getEEnum(XEnum xEnum)
  {
    EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
    mapper.getMapping(xEnum).setEDataType(eEnum);
    mapper.getToXcoreMapping(eEnum).setXcoreElement(xEnum);
    EList<EEnumLiteral> eLiterals = eEnum.getELiterals();
    for (XEnumLiteral xEnumLiteral : xEnum.getLiterals())
    {
      eLiterals.add(getEEnumLiteral(xEnumLiteral));
    }
    return eEnum;
  }

  EEnumLiteral getEEnumLiteral(XEnumLiteral xEnumLiteral)
  {
    EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
    mapper.getToXcoreMapping(eEnumLiteral).setXcoreElement(xEnumLiteral);
    mapper.getMapping(xEnumLiteral).setEEnumLiteral(eEnumLiteral);
    handleAnnotations(xEnumLiteral, eEnumLiteral);
    eEnumLiteral.setName(nonNullName(xEnumLiteral.getName()));
    eEnumLiteral.setLiteral(xEnumLiteral.getLiteral());
    eEnumLiteral.setValue(xEnumLiteral.getValue());
    return eEnumLiteral;
  }
}
