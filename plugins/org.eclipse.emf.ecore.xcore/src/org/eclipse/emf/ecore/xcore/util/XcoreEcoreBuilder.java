package org.eclipse.emf.ecore.xcore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.eclipse.emf.ecore.xcore.interpreter.XcoreConversionDelegate;
import org.eclipse.emf.ecore.xcore.interpreter.XcoreInvocationDelegate;
import org.eclipse.emf.ecore.xcore.interpreter.XcoreSettingDelegate;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XBlockExpression;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class XcoreEcoreBuilder
{
	@Inject
	private XcoreMapper mapper;
	
	@Inject
	private Provider<XcoreInvocationDelegate> operationDelegateProvider;
	
	@Inject
	private Provider<XcoreSettingDelegate> settingDelegateProvider;

	@Inject
	private Provider<XcoreConversionDelegate> conversionDelegateProvider;
	
  List<Runnable> runnables = new ArrayList<Runnable>();
  
  public void link()
  {
    // Hook up local references.
    //
    List<Runnable>  currentRunnables = new ArrayList<Runnable>(runnables);
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

  public EPackage getEPackage(XPackage xPackage)
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    mapper.getMapping(xPackage).setEPackage(ePackage);
    mapper.getToXcoreMapping(ePackage).setXcoreElement(xPackage);
    handleAnnotations(xPackage, ePackage);
    String name = xPackage.getName();
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
      ePackage.setNsURI(name);
      ePackage.setNsPrefix(ePackage.getName());
    }

    for (XClassifier xClassifier : xPackage.getClassifiers())
    {
      EClassifier eClassifier = getEClassifier(xClassifier);
      ePackage.getEClassifiers().add(eClassifier);
    }
    
    for (XAnnotationDirective xAnnotationDirective : xPackage.getAnnotationDirectives())
    {
      EcoreUtil.setAnnotation(ePackage, XcorePackage.eNS_URI, xAnnotationDirective.getName(), xAnnotationDirective.getSourceURI());
    }

    if (basePackage != null)
    {
      EcoreUtil.setAnnotation(ePackage, GenModelPackage.eNS_URI, "basePackage", basePackage);
    }

    return ePackage;
  }
  
  void handleAnnotations(XModelElement xModelElement, EModelElement eModelElement)
  {
    for (XAnnotation xAnnotation : xModelElement.getAnnotations())
    {
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
//      map(eAnnotation, xAnnotation);
      XAnnotationDirective source = xAnnotation.getSource();
      if (source != null)
      {
			  String sourceURI = source.getSourceURI();
        eAnnotation.setSource(sourceURI);
      }
      for (Map.Entry<String, String> detail : xAnnotation.getDetails())
      {
        eAnnotation.getDetails().put(detail.getKey(), detail.getValue());
      }
      eModelElement.getEAnnotations().add(eAnnotation);
    }
  }
  
  EClassifier getEClassifier(final XClassifier xClassifier)
  {
    final EClassifier eClassifier =
      xClassifier instanceof XClass ?
        getEClass((XClass)xClassifier) :
        xClassifier instanceof XEnum ?
          getEEnum((XEnum)xClassifier) :
          getEDataType((XDataType)xClassifier);
    handleAnnotations(xClassifier, eClassifier);
    eClassifier.setName(xClassifier.getName());
    runnables.add
      (new Runnable()
       {
         public void run()
         {
           JvmTypeReference instanceType = xClassifier.getInstanceType();
           if (instanceType != null)
           {
             String instanceTypeName = instanceType.getIdentifier();
             eClassifier.setInstanceTypeName(instanceTypeName);
           }
         }
       });
    for (XTypeParameter xTypeParameter : xClassifier.getTypeParameters())
    {
      ETypeParameter eTypeParameter = getETypeParameter(xTypeParameter);
      eClassifier.getETypeParameters().add(eTypeParameter);
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
    for (XGenericType superType : xClass.getSuperTypes())
    {
      eClass.getEGenericSuperTypes().add(getEGenericType(superType));
    }
    for (XMember xMember : xClass.getMembers())
    {
      if (xMember instanceof XOperation)
      {
        EOperation eOperation = getEOperation((XOperation)xMember);
        eClass.getEOperations().add(eOperation);
      }
      else if (xMember instanceof XReference)
      {
        EReference eReference = getEReference((XReference)xMember);
        eClass.getEStructuralFeatures().add(eReference);
      }
      else
      {
        EAttribute eAttribute = getEAttribute((XAttribute)xMember);
        eClass.getEStructuralFeatures().add(eAttribute);
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
    for (XTypeParameter xTypeParameter : xOperation.getTypeParameters())
    {
      ETypeParameter eTypeParameter = getETypeParameter(xTypeParameter);
      eOperation.getETypeParameters().add(eTypeParameter);
    }
    for (XParameter xParameter : xOperation.getParameters())
    {
      EParameter eParameter = getEParameter(xParameter);
      eOperation.getEParameters().add(eParameter);
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
    	invocationDelegate.initialize(body, eOperation);
			((EOperation.Internal)eOperation).setInvocationDelegate(invocationDelegate);
			
//      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
//      eAnnotation.setSource(EcorePackage.eNS_URI);
//      eAnnotation.getReferences().add(body);
//      eOperation.getEAnnotations().add(eAnnotation);
    }
    return eOperation;
  }
  
  EParameter getEParameter(XParameter xParameter)
  {
    EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
    // TODO
//    map(eParameter, xParameter);
    eParameter.setUnique(false);
    handleETypedElement(eParameter, xParameter);
    return eParameter;
  }

  ETypeParameter getETypeParameter(XTypeParameter xTypeParameter)
  {
    ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
    mapper.getToXcoreMapping(eTypeParameter).setXcoreElement(xTypeParameter);
    //TODO
//    map(eTypeParameter, xTypeParameter);
    handleAnnotations(xTypeParameter, eTypeParameter);
    eTypeParameter.setName(xTypeParameter.getName());
    for (XGenericType xGenericType : xTypeParameter.getBounds())
    {
      eTypeParameter.getEBounds().add(getEGenericType(xGenericType));
    }
    return eTypeParameter;
  }

  void handleETypedElement(ETypedElement eTypedElement, XTypedElement xTypedElement)
  {
    eTypedElement.setName(xTypedElement.getName());
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
      for (XGenericType typeArgument : xGenericType.getTypeArguments())
      {
        eGenericType.getETypeArguments().add(getEGenericType(typeArgument));
      }
       
      runnables.add
        (new Runnable()
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
    
    runnables.add
      (new Runnable()
       {
         public void run()
         {
           GenFeature opposite = xReference.getOpposite();
           if (opposite != null)
           {
             eReference.setEOpposite((EReference)opposite.getEcoreFeature());
           }
           for (GenFeature key : xReference.getKeys())
           {
             EStructuralFeature eAttribute = key.getEcoreFeature();
             if (eAttribute instanceof EAttribute)
             {
						   eReference.getEKeys().add((EAttribute)eAttribute);
						 }
           }
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
    eStructuralFeature.setName(xStructuralFeature.getName());
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
    	settingDelegate.initialize(getBody, setBody, isSetBody, unsetBody, eStructuralFeature);
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
   	XcoreConversionDelegate conversionDelegate = conversionDelegateProvider.get();
   	conversionDelegate.initialize(xDataType.getCreateBody(), xDataType.getConvertBody(), eDataType);
    ((EDataType.Internal)eDataType).setConversionDelegate(conversionDelegate);
    return eDataType;
  }

  EEnum getEEnum(XEnum xEnum)
  {
    EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
    mapper.getMapping(xEnum).setEDataType(eEnum);
    mapper.getToXcoreMapping(eEnum).setXcoreElement(xEnum);
    for (XEnumLiteral xEnumLiteral : xEnum.getLiterals())
    {
      eEnum.getELiterals().add(getEEnumLiteral(xEnumLiteral));
    }
    return eEnum;
  }

  EEnumLiteral getEEnumLiteral(XEnumLiteral xEnumLiteral)
  {
    EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
    mapper.getToXcoreMapping(eEnumLiteral).setXcoreElement(xEnumLiteral);
    mapper.getMapping(xEnumLiteral).setEEnumLiteral(eEnumLiteral);
    handleAnnotations(xEnumLiteral, eEnumLiteral);
    eEnumLiteral.setName(xEnumLiteral.getName());
    eEnumLiteral.setLiteral(xEnumLiteral.getLiteral());
    eEnumLiteral.setValue(xEnumLiteral.getValue());
    return eEnumLiteral;
  }
}
