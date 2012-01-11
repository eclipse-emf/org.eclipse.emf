package org.eclipse.emf.ecore.xcore.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnum;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.scoping.LazyCreationProxyUriConverter;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmEnumerationLiteral;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.inject.Inject;

public class XcoreJvmInferrer
{
  @Inject
  private TypeReferences typeReferences;
  
  @Inject
  private LazyCreationProxyUriConverter proxyUriConverter;
  
  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private XcoreMapper mapper;
  
//  public static void map(EObject eObject, GenBase genBase)
//  {
//    XcoreEcoreBuilder.map(eObject, genBase);
//  }
//  
//  public static GenBase getGen(EObject eObject)
//  {
//    return XcoreEcoreBuilder.getGen(eObject);
//  }
  
  public List<? extends JvmDeclaredType> getDeclaredTypes(XPackage xPackage)
  {
    GenPackage genPackage = mapper.getMapping(xPackage).getGenPackage();
    return getDeclaredTypes(genPackage);
  }

  public List<? extends JvmDeclaredType> getDeclaredTypes(GenPackage genPackage)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();

    JvmGenericType factoryClass = TypesFactory.eINSTANCE.createJvmGenericType();
    factoryClass.setSimpleName(genPackage.getFactoryClassName());
    factoryClass.setPackageName(genPackage.getClassPackageName());
    factoryClass.setVisibility(JvmVisibility.PUBLIC);
    result.add(factoryClass);

    for (GenClassifier genClassifier : genPackage.getGenClassifiers())
    {
      result.addAll(getDeclaredTypes(genClassifier));
      if (genClassifier instanceof GenDataType)
      {
        GenDataType genDataType = (GenDataType)genClassifier;
        String instanceTypeName = genDataType.getEcoreDataType().getInstanceTypeName();
        if (instanceTypeName != null)
         {
          final XDataType xDataType = mapper.getXDataType(genClassifier);
          final XDataTypeMapping mapping = mapper.getMapping(xDataType);
          if (xDataType.getCreateBody() != null)
          {
            JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
            jvmOperation.setVisibility(JvmVisibility.PUBLIC);
            jvmOperation.setSimpleName("create" + genDataType.getName());
            JvmFormalParameter jvmFormalParameter = TypesFactory.eINSTANCE.createJvmFormalParameter();
            jvmFormalParameter.setName("it");
            jvmFormalParameter.setParameterType(typeReferences.getTypeForName("java.lang.String", genPackage));
            jvmOperation.getParameters().add(jvmFormalParameter);
            JvmTypeReference returnType = getJvmTypeReference(instanceTypeName, genDataType);
            jvmOperation.setReturnType(returnType);
            mapping.setCreator(jvmOperation);
            factoryClass.getMembers().add(jvmOperation);
          }
          if (xDataType.getConvertBody() != null)
          {
            JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
            jvmOperation.setVisibility(JvmVisibility.PUBLIC);
            jvmOperation.setSimpleName("convert" + genDataType.getName());
            JvmFormalParameter jvmFormalParameter = TypesFactory.eINSTANCE.createJvmFormalParameter();
            jvmFormalParameter.setName("it");
            JvmTypeReference parameterType = getJvmTypeReference(instanceTypeName, genDataType);
            jvmFormalParameter.setParameterType(parameterType);
            jvmOperation.getParameters().add(jvmFormalParameter);
            jvmOperation.setReturnType(typeReferences.getTypeForName("java.lang.String", genPackage));
            mapping.setConverter(jvmOperation);
            factoryClass.getMembers().add(jvmOperation);
          }
        }
      }
    }

    return result;
  }
  
  public List<? extends JvmDeclaredType> getDeclaredTypes(GenClassifier genClassifier)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    if (genClassifier instanceof GenClass)
    {
      result.addAll(getDeclaredTypes((GenClass)genClassifier));
    }
    else if (genClassifier instanceof GenEnum)
    {
      result.add(getDeclaredType((GenEnum)genClassifier));
    }
    return result;
  }

  public JvmDeclaredType getDeclaredType(GenEnum genEnum)
  {
    final XEnum xEnum = mapper.getXEnum(genEnum);
    final XDataTypeMapping mapping = mapper.getMapping(xEnum);
    JvmDeclaredType jvmDeclaredType;
    if (genEnum.getGenModel().useGenerics())
    {
      JvmEnumerationType jvmEnumerationType = TypesFactory.eINSTANCE.createJvmEnumerationType();
      jvmEnumerationType.getSuperTypes().add(getJvmTypeReference("java.lang.Enum<" + genEnum.getQualifiedInstanceClassName() + ">", genEnum));
      jvmEnumerationType.getSuperTypes().add(typeReferences.getTypeForName("org.eclipse.emf.common.util.Enumerator", genEnum));
      jvmEnumerationType.setFinal(true);
      jvmDeclaredType = jvmEnumerationType;
      for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals())
      {
        XEnumLiteral xEnumLiteral = mapper.getXEnumLiteral(genEnumLiteral);
        JvmEnumerationLiteral jvmEnumerationLiteral = TypesFactory.eINSTANCE.createJvmEnumerationLiteral();
        jvmEnumerationLiteral.setStatic(true);
        jvmEnumerationLiteral.setFinal(true);
        jvmEnumerationLiteral.setSimpleName(genEnumLiteral.getEnumLiteralInstanceConstantName());
        jvmEnumerationLiteral.setVisibility(JvmVisibility.PUBLIC);
        JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
        jvmParameterizedTypeReference.setType(jvmDeclaredType);
        jvmEnumerationLiteral.setType(jvmParameterizedTypeReference);
        jvmEnumerationType.getMembers().add(jvmEnumerationLiteral);
        mapper.getToXcoreMapping(jvmEnumerationLiteral).setXcoreElement(xEnumLiteral);
      }
    }
    else
    {
      JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
      jvmDeclaredType = jvmGenericType;
      // TODO
      return jvmGenericType;
    }
    jvmDeclaredType.setSimpleName(genEnum.getName());
    jvmDeclaredType.setPackageName(genEnum.getGenPackage().getInterfacePackageName());
    jvmDeclaredType.setVisibility(JvmVisibility.PUBLIC);
    JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
    jvmParameterizedTypeReference.setType(jvmDeclaredType);
    mapping.setDataType(jvmParameterizedTypeReference);
    mapping.setEDataType(genEnum.getEcoreEnum());
    mapper.getToXcoreMapping(jvmDeclaredType).setXcoreElement(xEnum);
    return jvmDeclaredType;
  }

  public List<? extends JvmDeclaredType> getDeclaredTypes(GenClass genClass)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    final XClass xClass = mapper.getXClass(genClass);
    final XClassMapping mapping = mapper.getMapping(xClass);
    if (!genClass.isExternalInterface() && (!genClass.getGenModel().isSuppressInterfaces() || genClass.isInterface()))
    {
      JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
      mapping.setInterfaceType(jvmGenericType);
      mapper.getToXcoreMapping(jvmGenericType).setXcoreElement(xClass);
      jvmGenericType.setSimpleName(genClass.getName());
      jvmGenericType.setPackageName(genClass.getGenPackage().getInterfacePackageName());
      jvmGenericType.setVisibility(JvmVisibility.PUBLIC);
      
      EList<GenTypeParameter> genTypeParameters = genClass.getGenTypeParameters();
      if (!genTypeParameters.isEmpty())
      {
        EList<JvmTypeParameter> typeParameters = jvmGenericType.getTypeParameters();
        for (GenTypeParameter genTypeParameter : genTypeParameters)
        {
          JvmTypeParameter jvmTypeParameter = TypesFactory.eINSTANCE.createJvmTypeParameter();
          //TODO
//          map(jvmTypeParameter, genTypeParameter);
          jvmTypeParameter.setName(genTypeParameter.getName());
          typeParameters.add(jvmTypeParameter);
          // TODO Need to handle the bounds.
        }
      }
      
      // TODO  should deal with EClass.getEGenericSuperTypes().
      EList<JvmTypeReference> superTypes = jvmGenericType.getSuperTypes();
      try {
        List<String> qualifiedInterfaceExtendsList = genClass.getQualifiedInterfaceExtendsList();
        for (String instanceTypeName : qualifiedInterfaceExtendsList)
        {
          superTypes.add(getJvmTypeReference(instanceTypeName, genClass));
        }
      }
      catch (NullPointerException e)
      {
        //TODO get rid of me
        e.printStackTrace();
      }
      
      if (superTypes.isEmpty())
      {
        if (genClass.isEObject())
        {
           superTypes.add(typeReferences.getTypeForName("org.eclipse.emf.common.notify.Notifier", genClass));
        }
        else
        {
           superTypes.add(typeReferences.getTypeForName("java.lang.Object", genClass));
        }
      }

      EList<JvmMember> members = jvmGenericType.getMembers();
      for (GenOperation genOperation : genClass.getDeclaredGenOperations())
      {
        members.add(getJvmOperation(genOperation));
      }
      
      for (GenFeature genFeature : genClass.getGenFeatures())
      {
        EStructuralFeature eStructuralFeature = genFeature.getEcoreFeature();
        if (eStructuralFeature.getName() != null && eStructuralFeature.getEGenericType() != null)
        {
          members.addAll(getJvmFeatureAccessors(genFeature));
        }
      }
      
      result.add(jvmGenericType);
    }
    return result;
  }
  
  List<JvmOperation> getJvmFeatureAccessors(GenFeature genFeature)
  {
    XStructuralFeature xStructuralFeature = mapper.getXFeature(genFeature);
    List<JvmOperation> result = new ArrayList<JvmOperation>();
    if (genFeature.isGet() && !genFeature.isSuppressedGetVisibility())
    {
      JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
      jvmOperation.setVisibility(JvmVisibility.PUBLIC);
      mapper.getMapping(xStructuralFeature).setGetter(jvmOperation);
      mapper.getToXcoreMapping(jvmOperation).setXcoreElement(xStructuralFeature);
      jvmOperation.setSimpleName(genFeature.getGetAccessor());
      jvmOperation.setReturnType(getJvmTypeReference(genFeature.getType(genFeature.getGenClass()), genFeature));
      result.add(jvmOperation);
    }
    if (genFeature.isSet() && !genFeature.isSuppressedSetVisibility())
    {
      JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
      jvmOperation.setVisibility(JvmVisibility.PUBLIC);
      mapper.getMapping(xStructuralFeature).setSetter(jvmOperation);
      mapper.getToXcoreMapping(jvmOperation).setXcoreElement(xStructuralFeature);
      
      JvmFormalParameter jvmFormalParameter = TypesFactory.eINSTANCE.createJvmFormalParameter();
      jvmFormalParameter.setName("value");
      jvmFormalParameter.setParameterType(getJvmTypeReference(genFeature.getType(genFeature.getGenClass()), genFeature));
      jvmOperation.getParameters().add(jvmFormalParameter);
      
      jvmOperation.setSimpleName("set" + genFeature.getAccessorName());
      jvmOperation.setReturnType(typeReferences.getTypeForName("void", genFeature));
      result.add(jvmOperation);
    }
    
    return result;
  }

  
  JvmOperation getJvmOperation(GenOperation genOperation)
  {
    XOperation xOperation = mapper.getXOperation(genOperation);
    JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
    jvmOperation.setVisibility(JvmVisibility.PUBLIC);
    mapper.getMapping(xOperation).setJvmOperation(jvmOperation);
    mapper.getToXcoreMapping(jvmOperation).setXcoreElement(xOperation);
    jvmOperation.setSimpleName(genOperation.getName());
    for (GenParameter genParameter : genOperation.getGenParameters())
    {
      jvmOperation.getParameters().add(getJvmFormalParameter(genParameter));
    }
    for (EGenericType eGenericException : genOperation.getEcoreOperation().getEGenericExceptions())
    {
      // TODO this might not handle type substitution; not sure if that matters for exceptions.
      jvmOperation.getExceptions().add(getJvmTypeReference(eGenericException, genOperation));
    }
    if (genOperation.isVoid())
    {
      jvmOperation.setReturnType(typeReferences.getTypeForName("void", genOperation));
    }
    else
    {
      jvmOperation.setReturnType(getJvmTypeReference(genOperation.getType(genOperation.getGenClass()), genOperation));
    }
    EList<GenTypeParameter> genTypeParameters = genOperation.getGenTypeParameters();
    if (!genTypeParameters.isEmpty())
    {
      EList<JvmTypeParameter> typeParameters = jvmOperation.getTypeParameters();
      for (GenTypeParameter genTypeParameter : genTypeParameters)
      {
        JvmTypeParameter jvmTypeParameter = TypesFactory.eINSTANCE.createJvmTypeParameter();
        //TODO
//        map(jvmTypeParameter, genTypeParameter);
        jvmTypeParameter.setName(genTypeParameter.getName());
        typeParameters.add(jvmTypeParameter);
        // TODO Need to handle the bounds.
      }
    }
    return jvmOperation;
  }

  JvmFormalParameter getJvmFormalParameter(GenParameter genParameter)
  {
    JvmFormalParameter jvmFormalParameter = TypesFactory.eINSTANCE.createJvmFormalParameter();
    //TODO
//    map(jvmFormalParameter, genParameter);
    jvmFormalParameter.setName(genParameter.getName());
    // jvmFormalParameter.setParameterType(getJvmTypeReference(genParameter.getEcoreParameter().getEGenericType(), genParameter));
    jvmFormalParameter.setParameterType(getJvmTypeReference(genParameter.getType(genParameter.getGenOperation().getGenClass()), genParameter));
    return jvmFormalParameter;
  }
  
  JvmTypeReference getJvmTypeReference(String instanceTypeName, EObject context)
  {
    if (instanceTypeName.contains("<"))
    {
      Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(instanceTypeName);
      return getJvmTypeReference((EGenericType)diagnostic.getData().get(0), context);
    }
    else if (instanceTypeName.endsWith("[]"))
    {
      JvmGenericArrayTypeReference jvmGenericArrayTypeReference = TypesFactory.eINSTANCE.createJvmGenericArrayTypeReference();
      jvmGenericArrayTypeReference.setComponentType(getJvmTypeReference(instanceTypeName.substring(0, instanceTypeName.length() - 2), context));
      return jvmGenericArrayTypeReference;
    }
    else
    {
      QualifiedName qualifiedName = nameConverter.toQualifiedName(instanceTypeName);
      JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
      proxyUriConverter.installProxyURI(context.eResource().getURI(), jvmGenericType, qualifiedName);
      JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
      jvmParameterizedTypeReference.setType(jvmGenericType);
      return jvmParameterizedTypeReference;
    }
  }

  JvmTypeReference getJvmTypeReference(EGenericType eGenericType, EObject context)
  {
    EClassifier eClassifier = eGenericType.getEClassifier();
    if (eClassifier == null)
    {
      ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
      if (eTypeParameter == null)
      {
         JvmWildcardTypeReference jvmWildcardTypeReference = TypesFactory.eINSTANCE.createJvmWildcardTypeReference();
         // TODO Needs the bounds.
         return jvmWildcardTypeReference;
      }
      else
      {
        QualifiedName qualifiedName = QualifiedName.create(eTypeParameter.getName());
        JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
        proxyUriConverter.installProxyURI(context.eResource().getURI(), jvmGenericType, qualifiedName);
        JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
        jvmParameterizedTypeReference.setType(jvmGenericType);
        return jvmParameterizedTypeReference;
      }
    }
    else
    {
      List<JvmTypeReference> arguments = getJvmTypeReferences(eGenericType.getETypeArguments(), context);
      String instanceTypeName = eClassifier.getInstanceTypeName();
      JvmParameterizedTypeReference jvmParameterizedTypeReference;
      if (instanceTypeName == null) 
      {
        GenClassifier genClassifier = (GenClassifier)mapper.getGen(mapper.getToXcoreMapping(eClassifier).getXcoreElement());
        jvmParameterizedTypeReference =  getJvmParameterizedTypeReference(genClassifier);
      }
      else
      {
        JvmTypeReference jvmTypeReference = getJvmTypeReference(instanceTypeName, context);
        if (jvmTypeReference instanceof JvmParameterizedTypeReference)
        {
          jvmParameterizedTypeReference = (JvmParameterizedTypeReference)jvmTypeReference;
        }
        else
        {
          return jvmTypeReference;
        }
      }
      jvmParameterizedTypeReference.getArguments().addAll(arguments);
      return jvmParameterizedTypeReference;
    }
  }
  
  JvmParameterizedTypeReference getJvmParameterizedTypeReference(GenClassifier genClassifier)
  {
     String instanceTypeName;
    if (genClassifier instanceof GenClass)
    {
      instanceTypeName = ((GenClass)genClassifier).getQualifiedInterfaceName();
    }
    else
    {
      instanceTypeName = ((GenDataType)genClassifier).getQualifiedInstanceClassName();
    }
    QualifiedName qualifiedName = nameConverter.toQualifiedName(instanceTypeName);
    JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
    proxyUriConverter.installProxyURI(genClassifier.eResource().getURI(), jvmGenericType, qualifiedName);
    JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
     jvmParameterizedTypeReference.setType(jvmGenericType);
    return jvmParameterizedTypeReference;
  }

  List<JvmTypeReference> getJvmTypeReferences(List<EGenericType> eGenericTypes, EObject context)
  {
    if (eGenericTypes.isEmpty())
    {
      return Collections.emptyList();
    }
    else
    {
      List<JvmTypeReference> result = new ArrayList<JvmTypeReference>();
      for (EGenericType eGenericType : eGenericTypes)
      {
        result.add(getJvmTypeReference(eGenericType, context));
      }
      return result;
    }
  }
}
