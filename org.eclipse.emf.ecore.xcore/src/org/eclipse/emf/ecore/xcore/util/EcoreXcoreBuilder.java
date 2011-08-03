package org.eclipse.emf.ecore.xcore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
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
import org.eclipse.emf.ecore.xcore.XcoreFactory;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder.Mapping;
import org.eclipse.xtext.xbase.XBlockExpression;

public class EcoreXcoreBuilder
{
  List<Runnable> runnables = new ArrayList<Runnable>();
  
  public static void map(EObject eObject1, EObject eObject2)
  {
    XcoreEcoreBuilder.map(eObject2, eObject1);
  }
  
  public static EObject get(EObject eObject)
  {
    return XcoreEcoreBuilder.get(eObject);
  }
  
  public XPackage getXPackage(EPackage ePackage)
  {
    XPackage xPackage = XcoreFactory.eINSTANCE.createXPackage();
    map(xPackage, ePackage);
    handleAnnotations(ePackage, xPackage);
    String name = ePackage.getName();
    xPackage.setName(name);
    // TODO
    // xPackage.setNsURI(ePackage.getNsURI());
    // xPackage.setNsPrefix(ePackage.getNsPrefix());
    
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
    
    // Hook up local references.
    //
    for (Runnable runnable : runnables)
    {
      runnable.run();
    }
    runnables.clear();

    return xPackage;
  }

  void handleAnnotations(final EModelElement eModelElement, final XModelElement xModelElement)
  {
    runnables.add
      (new Runnable()
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
                   XPackage xPackage = (XPackage)xModelElement;
                   xPackage.setName(detail.getValue() + "." + xPackage.getName());
                 }
                 else
                 {
                   if (xAnnotation == null)
                   {
                     xAnnotation = XcoreFactory.eINSTANCE.createXAnnotation();
                     map(xAnnotation, eAnnotation);
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
             else if (XcorePackage.eNS_URI.equals(eAnnotation.getSource()))
             {
               // Ignore
             }
             // Ignore the empty annotations, such as those that store the body.
             //
             else if (!EcorePackage.eNS_URI.equals(eAnnotation.getSource()) || !eAnnotation.getDetails().isEmpty())
             {
               XAnnotation xAnnotation = XcoreFactory.eINSTANCE.createXAnnotation();
               map(xAnnotation, eAnnotation);
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
    XPackage xPackage = (XPackage)EcoreUtil.getRootContainer(xModelElement);
    for (XAnnotationDirective xAnnotationDirective : xPackage.getAnnotationDirectives())
    {
      if (source.equals(xAnnotationDirective.getSourceURI()))
      {
        return xAnnotationDirective;
        
      }
    }
    XAnnotationDirective xAnnotationDirective = XcoreFactory.eINSTANCE.createXAnnotationDirective();
    xAnnotationDirective.setSourceURI(source);
    // TODO
    URI sourceURI = URI.createURI(source);
    String name = sourceURI.lastSegment();
    if (name == null)
    {
      name = sourceURI.authority().replaceAll("\\.", "_");
    }
    xAnnotationDirective.setName(name);
    xPackage.getAnnotationDirectives().add(xAnnotationDirective);
    return xAnnotationDirective;
  }

  XClassifier getXClassifier(EClassifier eClassifier)
  {
    XClassifier xClassifier;
    if (eClassifier instanceof EClass)
    {
      xClassifier = getXClass((EClass)eClassifier);
    }
    else if (eClassifier instanceof EEnum)
    {
      xClassifier = getXEnum((EEnum)eClassifier);
    }
    else
    {
      xClassifier = getXDataType((EDataType)eClassifier);
    }
    handleAnnotations(eClassifier, xClassifier);
    xClassifier.setName(eClassifier.getName());
    String instanceTypeName = eClassifier.getInstanceTypeName();
    if (instanceTypeName != null)
    {
      // TODO
      int index = instanceTypeName.indexOf('[');
      if (index != -1)
      {
        instanceTypeName = instanceTypeName.substring(0, index);
      }
      xClassifier.setInstanceTypeName(instanceTypeName);
    }
    for (ETypeParameter eTypeParameter : eClassifier.getETypeParameters())
    {
      XTypeParameter xTypeParameter = getXTypeParameter(eTypeParameter);
      xClassifier.getTypeParameters().add(xTypeParameter);
    }
    return xClassifier;
  }

  XClass getXClass(EClass eClass)
  {
    XClass xClass = XcoreFactory.eINSTANCE.createXClass();
    map(xClass, eClass);
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
      XStructuralFeature xStructuralFeature = getXStructuralFeature(eStructuralFeature);
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
    XOperation xOperation = XcoreFactory.eINSTANCE.createXOperation();
    map(xOperation, eOperation);
    handleXTypedElement(xOperation, eOperation);
    for (EParameter eParameter : eOperation.getEParameters())
    {
      XParameter xParameter = getXParameter(eParameter);
      xOperation.getParameters().add(xParameter);
    }
    for (ETypeParameter eTypeParameter : eOperation.getETypeParameters())
    {
      XTypeParameter xTypeParameter = getXTypeParameter(eTypeParameter);
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
    return xOperation;
  }
  
  XParameter getXParameter(EParameter eParameter)
  {
    XParameter xParameter = XcoreFactory.eINSTANCE.createXParameter();
    map(xParameter, eParameter);
    handleXTypedElement(xParameter, eParameter);
    return xParameter;
  }

  XTypeParameter getXTypeParameter(ETypeParameter eTypeParameter)
  {
    XTypeParameter xTypeParameter = XcoreFactory.eINSTANCE.createXTypeParameter();
    map(xTypeParameter, eTypeParameter);
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
    if (eTypedElement.isUnique() && !(eTypedElement instanceof EReference))
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
        xTypedElement.setMultiplicity(new int [] {});
      }
      else if (upperBound == 1)
      {
        xTypedElement.setMultiplicity(new int [] {-3});
      }
      else
      {
        xTypedElement.setMultiplicity(new int [] {0, upperBound});
      }
    }
    else if (lowerBound == 1)
    {
      if (upperBound == EStructuralFeature.UNBOUNDED_MULTIPLICITY)
      {
        xTypedElement.setMultiplicity(new int [] {-2});
      }
      else if (lowerBound != 1)
      {
        xTypedElement.setMultiplicity(new int [] {1, upperBound});
      }
    }
    else if (lowerBound == upperBound)
    {
      xTypedElement.setMultiplicity(new int [] {lowerBound});
    }
    else
    {
      xTypedElement.setMultiplicity(new int [] {lowerBound, upperBound});
    }
  }

  XGenericType getXGenericType(EGenericType eGenericType)
  {
    if (eGenericType == null)
    {
      return null;
    }
    else
    {
      final XGenericType xGenericType = XcoreFactory.eINSTANCE.createXGenericType();
      map(xGenericType, eGenericType);
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
        xGenericType.setType(eClassifier);
      }
      else
      {
        xGenericType.setType(eGenericType.getETypeParameter());
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

  XStructuralFeature getXStructuralFeature(EStructuralFeature eStructuralFeature)
  {
    final XStructuralFeature xStructuralFeature = XcoreFactory.eINSTANCE.createXStructuralFeature();
    map(xStructuralFeature, eStructuralFeature);
    if (eStructuralFeature instanceof EReference)
    {
      final EReference eReference = (EReference)eStructuralFeature;
      if (eReference.isContainment())
      {
        xStructuralFeature.setContainment(true);
        if (eReference.isResolveProxies())
        {
          xStructuralFeature.setResolveProxies(true);
        }
      }
      else if (!eReference.isResolveProxies())
      {
        xStructuralFeature.setLocal(true);
      }
      EReference opposite = eReference.getEOpposite();
      if (opposite != null)
      {
        xStructuralFeature.setOpposite(opposite);
      }
      xStructuralFeature.getKeys().addAll(eReference.getEKeys());
    }
    else
    {
      EAttribute eAttribute = (EAttribute)eStructuralFeature;
      if (eAttribute.isID())
      {
        xStructuralFeature.setID(true);
      }
    }
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
    xStructuralFeature.setName(eStructuralFeature.getName());
    return xStructuralFeature;
  }

  XDataType getXDataType(EDataType eDataType)
  {
    XDataType xDataType = XcoreFactory.eINSTANCE.createXDataType();
    map(xDataType, eDataType);
    return xDataType;
  }

  XEnum getXEnum(EEnum eEnum)
  {
    XEnum xEnum = XcoreFactory.eINSTANCE.createXEnum();
    map(xEnum, eEnum);
    for (EEnumLiteral eEnumLiteral : eEnum.getELiterals())
    {
      xEnum.getLiterals().add(getXEnumLiteral(eEnumLiteral));
    }
    return xEnum;
  }

  XEnumLiteral getXEnumLiteral(EEnumLiteral eEnumLiteral)
  {
    XEnumLiteral xEnumLiteral = XcoreFactory.eINSTANCE.createXEnumLiteral();
    map(xEnumLiteral, eEnumLiteral);
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
