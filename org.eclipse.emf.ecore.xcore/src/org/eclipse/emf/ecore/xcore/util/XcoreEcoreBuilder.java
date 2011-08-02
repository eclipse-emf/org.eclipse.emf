package org.eclipse.emf.ecore.xcore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
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
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XTypedElement;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.xbase.XBlockExpression;

public class XcoreEcoreBuilder
{
  List<Runnable> runnables = new ArrayList<Runnable>();
  
  static class Mapping extends AdapterImpl
  {
    public EObject eObject;
    Mapping(EObject eObject)
    {
      this.eObject = eObject;
    }
    @Override
    public boolean isAdapterForType(Object type)
    {
      return type == Mapping.class;
    }
  }
  
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

  public static void map(EObject eObject1, EObject eObject2)
  {
    eObject1.eAdapters().add(new Mapping(eObject2));
    eObject2.eAdapters().add(new Mapping(eObject1));
  }
  
  public static EObject get(EObject eObject)
  {
    return ((Mapping)EcoreUtil.getAdapter(eObject.eAdapters(), Mapping.class)).eObject;
  }

  public EPackage getEPackage(XPackage xPackage)
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    map(ePackage, xPackage);
    handleAnnotations(xPackage, ePackage);
    String name = xPackage.getName();
    String basePackage;
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
      map(eAnnotation, xAnnotation);
      String source = xAnnotation.getSource().getSourceURI();
      eAnnotation.setSource(source);
      for (Map.Entry<String, String> detail : xAnnotation.getDetails())
      {
        eAnnotation.getDetails().put(detail.getKey(), detail.getValue());
      }
      eModelElement.getEAnnotations().add(eAnnotation);
    }
  }
  
  EClassifier getEClassifier(XClassifier xClassifier)
  {
    EClassifier eClassifier;
    if (xClassifier instanceof XClass)
    {
      eClassifier = getEClass((XClass)xClassifier);
    }
    else if (xClassifier instanceof XEnum)
    {
      eClassifier = getEEnum((XEnum)xClassifier);
    }
    else
    {
      eClassifier = getEDataType((XDataType)xClassifier);
    }
    handleAnnotations(xClassifier, eClassifier);
    eClassifier.setName(xClassifier.getName());
    String instanceTypeName = xClassifier.getInstanceTypeName();
    if (instanceTypeName != null)
    {
      eClassifier.setInstanceTypeName(instanceTypeName);
    }
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
    map(eClass, xClass);
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
    runnables.add
      (new Runnable()
       {
         public void run()
         {
           for (XMember xMember : xClass.getMembers())
           {
             if (xMember instanceof XOperation)
             {
               EOperation eOperation = getEOperation((XOperation)xMember);
               eClass.getEOperations().add(eOperation);
             }
             else
             {
               EStructuralFeature eStructuralFeature = getEStructuralFeature((XStructuralFeature)xMember);
               eClass.getEStructuralFeatures().add(eStructuralFeature);
             }
           }
         }
       });
    return eClass;
  }

  EOperation getEOperation(XOperation xOperation)
  {
    EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
    map(eOperation, xOperation);
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
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      eAnnotation.setSource(EcorePackage.eNS_URI);
      eAnnotation.getContents().add(EcoreUtil.copy(body));
      eOperation.getEAnnotations().add(eAnnotation);
    }
    return eOperation;
  }
  
  EParameter getEParameter(XParameter xParameter)
  {
    EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
    map(eParameter, xParameter);
    eParameter.setUnique(false);
    handleETypedElement(eParameter, xParameter);
    return eParameter;
  }

  ETypeParameter getETypeParameter(XTypeParameter xTypeParameter)
  {
    ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
    map(eTypeParameter, xTypeParameter);
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
      // required is the default
      //
      eTypedElement.setLowerBound(1);
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
    final EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
    map(eGenericType, xGenericType);
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
           ENamedElement type = xGenericType.getType();
           if (type instanceof ETypeParameter)
           {
             eGenericType.setETypeParameter((ETypeParameter)type);
           }
           else if (type instanceof EClassifier)
           {
             eGenericType.setEClassifier((EClassifier)type);
           }
         }
       });
    return eGenericType;
  }

  EStructuralFeature getEStructuralFeature(final XStructuralFeature xStructuralFeature)
  {
    EStructuralFeature eStructuralFeature;
    ENamedElement type = xStructuralFeature.getType().getType();
    boolean isReference;
    if (type instanceof ETypeParameter)
    {
      isReference = 
        xStructuralFeature.isReference() || 
          xStructuralFeature.isLocal() || 
          xStructuralFeature.isContainment() || 
          !xStructuralFeature.getKeys().isEmpty();
    }
    else if (type instanceof EClass)
    {
      isReference = true;
    }
    else if (type instanceof EDataType)
    {
      isReference = false;
    }
    else
    {
      isReference = 
        xStructuralFeature.isReference() || 
          xStructuralFeature.isLocal() || 
          xStructuralFeature.isContainment() || 
          !xStructuralFeature.getKeys().isEmpty();
      type = isReference ? EcorePackage.Literals.EOBJECT : EcorePackage.Literals.ESTRING;
    }
    if (isReference)
    {
      final EReference eReference = EcoreFactory.eINSTANCE.createEReference();
      map(eReference, xStructuralFeature);
      if (xStructuralFeature.isContainment())
      {
        eReference.setContainment(true);
      }
      handleETypedElement(eReference, xStructuralFeature);
      runnables.add
        (new Runnable()
         {
           public void run()
           {
             EReference eOpposite = xStructuralFeature.getOpposite();
             if (eOpposite != null)
             {
               eReference.setEOpposite(eOpposite);
             }
             for (EAttribute key : xStructuralFeature.getKeys())
             {
               eReference.getEKeys().add(key);
             }
           }
         });
      /*
      for (final XStructuralFeature key : xStructuralFeature.getKeys())
      {
        runnables.add
          (new Runnable()
           {
             public void run()
             {
               // Resolve the feature name in the type.
               // TODO
               eReference.getEKeys().add((EAttribute)eReference.getEReferenceType().getEStructuralFeature(key.getName()));
             }
           });
      }
      */
      if (xStructuralFeature.isContainment())
      {
        eReference.setContainment(true);
        if (!xStructuralFeature.isResolveProxies())
        {
          eReference.setResolveProxies(false);
        }
      }
      if (xStructuralFeature.isLocal())
      {
        eReference.setResolveProxies(false);
      }
      eStructuralFeature = eReference;
    }
    else
    {
      final EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      map(eAttribute, xStructuralFeature);
      eAttribute.setUnique(false);
      handleETypedElement(eAttribute, xStructuralFeature);
      if (xStructuralFeature.isID())
      {
        eAttribute.setID(true);
      }
      eStructuralFeature = eAttribute;
    }
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
    eStructuralFeature.setDefaultValueLiteral(xStructuralFeature.getDefaultValueLiteral());
    eStructuralFeature.setName(xStructuralFeature.getName());
    return eStructuralFeature;
  }

  EDataType getEDataType(XDataType xDataType)
  {
    EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
    map(eDataType, xDataType);
    return eDataType;
  }

  EEnum getEEnum(XEnum xEnum)
  {
    EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
    map(eEnum, xEnum);
    for (XEnumLiteral xEnumLiteral : xEnum.getLiterals())
    {
      eEnum.getELiterals().add(getEEnumLiteral(xEnumLiteral));
    }
    return eEnum;
  }

  EEnumLiteral getEEnumLiteral(XEnumLiteral xEnumLiteral)
  {
    EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
    map(eEnumLiteral, xEnumLiteral);
    handleAnnotations(xEnumLiteral, eEnumLiteral);
    eEnumLiteral.setName(xEnumLiteral.getName());
    eEnumLiteral.setLiteral(xEnumLiteral.getLiteral());
    eEnumLiteral.setValue(xEnumLiteral.getValue());
    return eEnumLiteral;
  }
}
