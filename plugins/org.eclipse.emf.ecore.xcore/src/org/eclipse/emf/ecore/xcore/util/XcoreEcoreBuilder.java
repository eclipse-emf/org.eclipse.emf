/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.util;


import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EDataTypeImpl;
import org.eclipse.emf.ecore.impl.EGenericTypeImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
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
  private static final Pattern COMMENT_LINE_BREAK_PATTERN = Pattern.compile("([ \t]*(\\r?\\n)(\\s*\\*\\s?)?)|\\s*$", Pattern.MULTILINE);

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

  protected final List<DeferredInitializer> deferredInitializers = new ArrayList<DeferredInitializer>();

  protected XcoreInterpreter interpreter;

  protected ClassLoader classLoader;

  public void link()
  {
    for (DeferredInitializer deferredInitializer : deferredInitializers)
    {
      deferredInitializer.setNeedsInitialization(true);
    }

    for (DeferredInitializer deferredInitializer : deferredInitializers)
    {
      deferredInitializer.initialize();
    }

    deferredInitializers.clear();
  }

  public static void setQualifiedPackageName(EPackage ePackage, String name)
  {
    String basePackage = null;
    if (name != null)
    {
      int index = name.lastIndexOf(".");
      if (index == -1)
      {
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
        String packageName = ePackage.getName();
        ePackage.setNsPrefix(packageName.toLowerCase().startsWith("xml") ? "_" + packageName : packageName);
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

  protected void handleAnnotations(final XModelElement xModelElement, final EModelElement eModelElement)
  {
    Map<String, DeferredEAnnotationImpl> specialAnnotations = new LinkedHashMap<String, XcoreEcoreBuilder.DeferredEAnnotationImpl>();
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
                DeferredEAnnotationImpl eAnnotation = new DeferredEAnnotationImpl(null);
                eAnnotation.setSource(GenModelPackage.eNS_URI);
                eModelElement.getEAnnotations().add(eAnnotation);
                eAnnotation.getDetails().put("documentation", comment.toString());
                specialAnnotations.put(GenModelPackage.eNS_URI, eAnnotation);
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

    EList<EAnnotation> eAnnotations = eModelElement.getEAnnotations();
    for (XAnnotation xAnnotation : xModelElement.getAnnotations())
    {
      //      map(eAnnotation, xAnnotation);
      String sourceURI = null;

        List<INode> nodes = NodeModelUtils.findNodesForFeature(xAnnotation, XcorePackage.Literals.XANNOTATION__SOURCE);
        StringBuilder annotationLiteral = new StringBuilder();
        for (INode sourceNode : nodes)
        {
          annotationLiteral.append(NodeModelUtils.getTokenText(sourceNode));
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

        EClass eClass = EcorePackage.eNS_URI.equals(sourceURI) ? eModelElement.eClass() : null;

        DeferredEAnnotationImpl eAnnotation = specialAnnotations.get(sourceURI);
        if (eAnnotation == null)
        {
          eAnnotation = new DeferredEAnnotationImpl(xAnnotation);
          if (sourceURI != null)
          {
            specialAnnotations.put(sourceURI, eAnnotation);
          }
        }
        eAnnotation.setSource(sourceURI);

        EMap<String, String> details = eAnnotation.getDetails();
        for (Map.Entry<String, String> detail : xAnnotation.getDetails())
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
              EDataType eDataType = (EDataType)eStructuralFeature.getEType();
              eModelElement.eSet(eStructuralFeature, EcoreUtil.createFromString(eDataType, value));
              continue;
            }
          }

          details.put(key,  value);
        }

        // Add it if it's not an Ecore annotation or if it is an Ecore annotation but isn't empty.
        if (eClass == null || !details.isEmpty())
        {
          eAnnotations.add(eAnnotation);
          deferredInitializers.add(eAnnotation);
        }
      }
  }

  protected EClassifier getEClassifier(final XClassifier xClassifier)
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

      // Populate the instance type early because GenOperation IDs depend on the data type's unqualified instance type and it might not be set so early.
      List<INode> nodes = NodeModelUtils.findNodesForFeature(xClassifier, XcorePackage.Literals.XCLASSIFIER__INSTANCE_TYPE);
      if (!nodes.isEmpty())
      {
        StringBuilder instanceTypeLiteral = new StringBuilder();
        for (INode node : nodes)
        {
          instanceTypeLiteral.append(NodeModelUtils.getTokenText(node));
        }
        eClassifier.setInstanceTypeName(instanceTypeLiteral.toString());
      }

      if (eClassifier instanceof DeferredInitializer)
      {
        deferredInitializers.add((DeferredInitializer)eClassifier);
      }
    }
    return eClassifier;
  }

  protected EClass getEClass(final XClass xClass)
  {
    final EClass eClass = new DeferredEClassImpl(xClass, classLoader);
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

  protected EOperation getEOperation(XOperation xOperation)
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

  protected EParameter getEParameter(XParameter xParameter)
  {
    EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
    mapper.getMapping(xParameter).setEParameter(eParameter);
    mapper.getToXcoreMapping(eParameter).setXcoreElement(xParameter);
    eParameter.setUnique(false);
    handleETypedElement(eParameter, xParameter);
    return eParameter;
  }

  protected ETypeParameter getETypeParameter(XTypeParameter xTypeParameter)
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

  protected void handleETypedElement(ETypedElement eTypedElement, XTypedElement xTypedElement)
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

  protected EGenericType getEGenericType(final XGenericType xGenericType)
  {
    if (xGenericType == null)
    {
      return null;
    }
    else
    {
      DeferredEGenericTypeImpl eGenericType = new DeferredEGenericTypeImpl(xGenericType);
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
      deferredInitializers.add(eGenericType);
      return eGenericType;
    }
  }

  protected EReference getEReference(final XReference xReference)
  {
    final DeferredEReferenceImpl eReference = new DeferredEReferenceImpl(xReference);
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
    deferredInitializers.add(eReference);
    return eReference;
  }

  protected EAttribute getEAttribute(final XAttribute xAttribute)
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

  protected void handleEStructuralFeature(EStructuralFeature eStructuralFeature, XStructuralFeature xStructuralFeature)
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

  protected EDataType getEDataType(XDataType xDataType)
  {
    EDataType eDataType = new DeferredEDataTypeImpl(xDataType, classLoader);
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

  protected EEnum getEEnum(XEnum xEnum)
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

  protected EEnumLiteral getEEnumLiteral(XEnumLiteral xEnumLiteral)
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

  /**
   * This was introduced to address the problems uncovered by https://bugs.eclipse.org/bugs/show_bug.cgi?id=514561.
   */
  public interface DeferredInitializer
  {
    void setNeedsInitialization(boolean needsInitialization);
    void initialize();
  }

  private static final class DeferredEDataTypeImpl extends EDataTypeImpl implements DeferredInitializer
  {
    private final XClassifier xClassifier;

    private final ClassLoader classLoader;

    private boolean needsInitialization;

    public DeferredEDataTypeImpl(XClassifier xClassifier, ClassLoader classLoader)
    {
      this.xClassifier = xClassifier;
      this.classLoader = classLoader;
    }

    public void setNeedsInitialization(boolean needsInitialization)
    {
      this.needsInitialization = needsInitialization;
    }

    public void initialize()
    {
      if (needsInitialization)
      {
        needsInitialization = false;

        JvmTypeReference instanceType = xClassifier.getInstanceType();
        if (instanceType != null)
        {
          String instanceTypeName = instanceType.getIdentifier();
          String normalizedInstanceTypeName = EcoreUtil.toJavaInstanceTypeName((EGenericType)EcoreValidator.EGenericTypeBuilder.INSTANCE.buildEGenericType(instanceTypeName));
          setInstanceTypeName(normalizedInstanceTypeName);
          if (classLoader != null)
          {
            try
            {
              Class<?> instanceClass = classLoader.loadClass(getInstanceClassName());
              setInstanceClassGen(instanceClass);
            }
            catch (Throwable throwable)
            {
              // Ignore.
            }
          }
        }
      }
    }

    @Override
    public boolean isSetInstanceClassName()
    {
      initialize();
      return super.isSetInstanceClassName();
    }

    @Override
    public String getInstanceClassName()
    {
      initialize();
      return super.getInstanceClassName();
    }

    @Override
    public boolean isSetInstanceTypeName()
    {
      initialize();
      return super.isSetInstanceTypeName();
    }

    @Override
    public String getInstanceTypeName()
    {
      initialize();
      return super.getInstanceTypeName();
    }

    @Override
    public Class<?> getInstanceClass()
    {
      initialize();
      return super.getInstanceClass();
    }
  }

  private static final class DeferredEClassImpl extends EClassImpl implements DeferredInitializer
  {
    private final XClassifier xClassifier;

    protected boolean needsInitialization;

    private ClassLoader classLoader;

    public DeferredEClassImpl(XClassifier xClassifier, ClassLoader classLoader)
    {
      this.xClassifier = xClassifier;
      this.classLoader = classLoader;
    }

    public void setNeedsInitialization(boolean needsInitialization)
    {
      this.needsInitialization = needsInitialization;
    }

    public void initialize()
    {
      if (needsInitialization)
      {
        needsInitialization = false;

        JvmTypeReference instanceType = xClassifier.getInstanceType();
        if (instanceType != null)
        {
          String instanceTypeName = instanceType.getIdentifier();
          String normalizedInstanceTypeName = EcoreUtil.toJavaInstanceTypeName(EcoreValidator.EGenericTypeBuilder.INSTANCE.buildEGenericType(instanceTypeName));
          setInstanceTypeName(normalizedInstanceTypeName);
          if (classLoader != null)
          {
            try
            {
              Class<?> instanceClass = classLoader.loadClass(getInstanceClassName());
              setInstanceClassGen(instanceClass);
            }
            catch (Throwable throwable)
            {
              // Ignore.
            }
          }
        }
      }
    }

    @Override
    public boolean isSetInstanceClassName()
    {
      initialize();
      return super.isSetInstanceClassName();
    }

    @Override
    public String getInstanceClassName()
    {
      initialize();
      return super.getInstanceClassName();
    }

    @Override
    public boolean isSetInstanceTypeName()
    {
      initialize();
      return super.isSetInstanceTypeName();
    }

    @Override
    public String getInstanceTypeName()
    {
      initialize();
      return super.getInstanceTypeName();
    }

    @Override
    public Class<?> getInstanceClass()
    {
      initialize();
      return super.getInstanceClass();
    }
  }

  private static class DeferredEReferenceImpl extends EReferenceImpl implements DeferredInitializer
  {
    private final XReference xReference;

    private boolean needsInitialization;

    public DeferredEReferenceImpl(XReference xReference)
    {
      this.xReference = xReference;
    }

    public void setNeedsInitialization(boolean needsInitialization)
    {
      this.needsInitialization = needsInitialization;
    }

    public void initialize()
    {
      if (needsInitialization)
      {
        needsInitialization = false;
        GenFeature opposite = xReference.getOpposite();
        if (opposite != null)
        {
          setEOpposite((EReference)opposite.getEcoreFeature());
        }
        for (GenFeature key : xReference.getKeys())
        {
          EStructuralFeature eAttribute = key.getEcoreFeature();
          if (eAttribute instanceof EAttribute)
          {
            getEKeys().add((EAttribute)eAttribute);
         }
        }
      }
    }

    @Override
    public boolean eIsSet(int featureID)
    {
      switch (featureID)
      {
        case EcorePackage.EREFERENCE__EREFERENCE_TYPE:
        case EcorePackage.EREFERENCE__EKEYS:
          initialize();
          break;
      }

      return super.eIsSet(featureID);
    }

    @Override
    public EList<EAttribute> getEKeys()
    {
      initialize();
      return super.getEKeys();
    }

    @Override
    public EReference getEOpposite()
    {
      initialize();
      return super.getEOpposite();
    }

    @Override
    public EReference basicGetEOpposite()
    {
      initialize();
      return super.basicGetEOpposite();
    }
  }

  private static final class DeferredEGenericTypeImpl extends EGenericTypeImpl implements DeferredInitializer
  {
    private final XGenericType xGenericType;

    private boolean needsInitialization;

    private DeferredEGenericTypeImpl(XGenericType xGenericType)
    {
      this.xGenericType = xGenericType;
    }

    public void setNeedsInitialization(boolean needsInitialization)
    {
      this.needsInitialization = needsInitialization;
    }

    public void initialize()
    {
      if (needsInitialization)
      {
        GenBase type = xGenericType.getType();
        // It's possible that we recursively reach this point again while resolving the type proxy.
        // This does not cause stack overflow because it succeeds the next time, but there's no point in processing the type again if it's already been processed.
        if (needsInitialization)
        {
          needsInitialization = false;
          if (type != null)
          {
            if (type instanceof GenTypeParameter)
            {
              setETypeParameter(((GenTypeParameter)type).getEcoreTypeParameter());
            }
            else if (type instanceof GenClassifier)
            {
              setEClassifier(((GenClassifier)type).getEcoreClassifier());
            }
            else
            {
              setEClassifier(getERawType());
            }
          }
        }
      }
    }

    @Override
    public EClassifier getERawType()
    {
      initialize();
      return super.getERawType();
    }

    @Override
    public EClassifier basicGetERawType()
    {
      initialize();
      return super.basicGetERawType();
    }

    @Override
    public EClassifier getEClassifier()
    {
      initialize();
      return super.getEClassifier();
    }

    @Override
    public EClassifier basicGetEClassifier()
    {
      initialize();
      return super.basicGetEClassifier();
    }

    @Override
    public ETypeParameter getETypeParameter()
    {
      initialize();
      return super.getETypeParameter();
    }

    @Override
    public boolean eIsSet(int featureID)
    {
      switch (featureID)
      {
        case EcorePackage.EGENERIC_TYPE__ERAW_TYPE:
        case EcorePackage.EGENERIC_TYPE__ETYPE_PARAMETER:
        case EcorePackage.EGENERIC_TYPE__ECLASSIFIER:
          initialize();
          break;
      }

      return super.eIsSet(featureID);
    }

    @Override
    public String toString()
    {
      if (needsInitialization)
      {
        return "* needs initialization *";
      }

      return super.toString();
    }
  }

  private static final class DeferredEAnnotationImpl extends EAnnotationImpl implements DeferredInitializer
  {
    private final XAnnotation xAnnotation;

    private boolean needsInitialization;

    private DeferredEAnnotationImpl(XAnnotation xAnnotation)
    {
      this.xAnnotation = xAnnotation;
    }

    public void setNeedsInitialization(boolean needsInitialization)
    {
      this.needsInitialization = needsInitialization;
    }

    public void initialize()
    {
      if (needsInitialization && xAnnotation != null)
      {
        needsInitialization = false;
        XAnnotationDirective source = xAnnotation.getSource();
        if (source != null)
        {
         setSource(source.getSourceURI());
        }
      }
    }

    @Override
    public boolean eIsSet(int featureID)
    {
      switch (featureID)
      {
        case EcorePackage.EANNOTATION__SOURCE:
          initialize();
          break;
      }

      return super.eIsSet(featureID);
    }

    @Override
    public String getSource()
    {
      initialize();
      return super.getSource();
    }
  }
}