/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.scoping.LazyCreationProxyURIConverter;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmEnumerationLiteral;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmLowerBound;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeParameterDeclarator;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmUpperBound;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.XBlockExpression;

import com.google.common.collect.Lists;
import com.google.inject.Inject;


public class XcoreJvmInferrer
{
  public static List<JvmIdentifiableElement> getInferredElements(GenBase genBase)
  {
    List<JvmIdentifiableElement> result = Lists.newArrayList();
    Adapter adapter = EcoreUtil.getAdapter(genBase.eAdapters(), InferenceAdapter.class);
    if (adapter != null)
    {
      for (JvmElementInferrer<?> jvmElementInferrer : ((InferenceAdapter)adapter).jvmElementInferrers)
      {
        result.add(jvmElementInferrer.getInferredElement());
      }
    }
    return result;
  }

  public static <T extends JvmIdentifiableElement> T getInferredElement(GenBase genBase, String qualifiedName)
  {
    Adapter adapter = EcoreUtil.getAdapter(genBase.eAdapters(), InferenceAdapter.class);
    if (adapter != null)
    {
      for (JvmElementInferrer<?> jvmElementInferrer : ((InferenceAdapter)adapter).jvmElementInferrers)
      {
        JvmIdentifiableElement jvmIdentifiableElement = jvmElementInferrer.getInferredElement();
        if (qualifiedName.equals(jvmIdentifiableElement.getQualifiedName()))
        {
          @SuppressWarnings("unchecked")
          T result = (T)jvmIdentifiableElement;
          return result;
        }
      }
    }
    return null;
  }

  public static void inferName(GenBase genBase)
  {
    Adapter adapter = EcoreUtil.getAdapter(genBase.eAdapters(), InferenceAdapter.class);
    if (adapter != null)
    {
      for (JvmElementInferrer<?> jvmElementInferrer : ((InferenceAdapter)adapter).jvmElementInferrers)
      {
        jvmElementInferrer.inferName();
      }
    }
  }

  protected static class InferenceAdapter extends AdapterImpl
  {
    protected List<JvmElementInferrer<? extends JvmIdentifiableElement>> jvmElementInferrers = Lists.newArrayList();

    @Override
    public boolean isAdapterForType(Object type)
    {
      return type == InferenceAdapter.class;
    }
  }

  protected static final int X_VERY_LOW = 2;
  protected static final int X_LOW = 1;
  protected static final int X_MEDIUM = 0;
  protected static final int X_HIGH = -1;
  protected static final int X_VERY_HIGH = -2;

  protected static abstract class JvmElementInferrer<T extends JvmIdentifiableElement>
  {
    protected T inferredElement;

    protected int priority;

    public JvmElementInferrer(int priority)
    {
      this.priority = priority;
      inferredElement = inferStructure();
      inferName();
    }

    public final T getInferredElement()
    {
      return inferredElement;
    }

    protected void resolveTypeParameterReferences()
    {
      for (Iterator<EObject> i = inferredElement.eAllContents(); i.hasNext(); )
      {
        EObject eObject = i.next();
        if (eObject instanceof JvmParameterizedTypeReference)
        {
          InternalEObject type = (InternalEObject)eObject.eGet(TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE, false);
          if (type != null)
          {
            URI eProxyURI = type.eProxyURI();
            if (eProxyURI != null && eProxyURI.scheme() ==  TYPE_PARAMETER_REFERENCE_SCHEME)
            {
              String name = eProxyURI.fragment();
              for (EObject eContainer = eObject.eContainer(); eContainer != null; eContainer = eContainer.eContainer())
              {
                if (eContainer instanceof JvmTypeParameterDeclarator)
                {
                  for (JvmTypeParameter jvmTypeParameter : ((JvmTypeParameterDeclarator)eContainer).getTypeParameters())
                  {
                    if (name.equals(jvmTypeParameter.getName()))
                    {
                      ((JvmParameterizedTypeReference)eObject).setType(jvmTypeParameter);
                      break;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    protected void inferDeepStructure()
    {
      // There is no deep structure by default.
    }

    protected abstract T inferStructure();

    public abstract void inferName();
  }

  @Inject
  private LazyCreationProxyURIConverter proxyUriConverter;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private XcoreMapper mapper;

  @Inject
  TypeReferences typeReferences;

  public List<? extends JvmDeclaredType> inferElements(GenModel genModel)
  {
    final ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    for (GenPackage genPackage : genModel.getGenPackages())
    {
      result.addAll(getDeclaredTypes(genPackage));
    }
    return result;
  }

  public void inferDeepStructure(GenModel genModel)
  {
    for (GenPackage genPackage : genModel.getGenPackages())
    {
      inferDeepStructure(genPackage);
      for (GenClassifier genClassifier : genPackage.getGenClassifiers())
      {
        inferDeepStructure(genClassifier);
      }
    }
  }

  protected void inferDeepStructure(GenBase genBase)
  {
    Adapter adapter = EcoreUtil.getAdapter(genBase.eAdapters(), InferenceAdapter.class);
    if (adapter != null)
    {
      for (JvmElementInferrer<?> jvmElementInferrer : Lists.newArrayList(((InferenceAdapter)adapter).jvmElementInferrers))
      {
        jvmElementInferrer.inferDeepStructure();
        jvmElementInferrer.resolveTypeParameterReferences();
      }
    }
  }

  protected List<? extends JvmDeclaredType> getDeclaredTypes(final GenPackage genPackage)
  {
    final ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();

    GenModel genModel = genPackage.getGenModel();
    if (genPackage.hasClassifiers())
    {
      if (!genModel.isSuppressEMFMetaData() && !genModel.isSuppressInterfaces())
      {
        JvmGenericType packageInterface = getPackage(genPackage, true, false);
        result.add(packageInterface);
      }
      JvmGenericType packageClass = getPackage(genPackage, genModel.isSuppressEMFMetaData() || genModel.isSuppressInterfaces(), true);
      result.add(packageClass);
    }

    if (genPackage.hasClassifiers())
    {
      if (!genModel.isSuppressInterfaces())
      {
        JvmGenericType factoryInterface = getFactory(genPackage, true, false);
        result.add(factoryInterface);
      }
      JvmGenericType factoryClass = getFactory(genPackage, genModel.isSuppressInterfaces(), true);
      result.add(factoryClass);
    }

    if (genPackage.hasClassifiers() && genPackage.isAdapterFactory() && !genPackage.getGenClasses().isEmpty())
    {
      JvmElementInferrer<JvmGenericType> switchClassInferrer =
        new JvmElementInferrer<JvmGenericType>(X_MEDIUM)
        {
          @Override
          protected JvmGenericType inferStructure()
          {
            JvmGenericType switchClass = TypesFactory.eINSTANCE.createJvmGenericType();
            switchClass.setInterface(false);
            switchClass.setVisibility(JvmVisibility.PUBLIC);
            return switchClass;
          }

          @Override
          protected void inferDeepStructure()
          {
            inferredElement.getSuperTypes().add(getJvmTypeReference("org.eclipse.emf.ecore.util.Switch", genPackage));
            EList<JvmMember> members = inferredElement.getMembers();
            for (final GenClass genClass : genPackage.getAllSwitchGenClasses())
            {
              if (!genClass.isAbstract())
              {
                JvmElementInferrer<JvmOperation> caseMethodInferrer =
                  new JvmElementInferrer<JvmOperation>(X_MEDIUM)
                  {
                    @Override
                    protected JvmOperation inferStructure()
                    {
                      JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
                      jvmOperation.setVisibility(JvmVisibility.PUBLIC);
                      jvmOperation.setReturnType(getJvmTypeReference("org.eclipse.emf.common.notify.Adapter", genClass));

                      JvmFormalParameter jvmFormalParameter = createJvmFormalParameter(genClass, "object", getJvmTypeReference(genClass.getQualifiedInterfaceName(), genPackage));
                      jvmOperation.getParameters().add(jvmFormalParameter);
                      populateTypeParameters(null, genClass.getGenTypeParameters(), jvmOperation.getTypeParameters());
                      return jvmOperation;
                    }

                    protected boolean rename = false;

                    @Override
                    public void inferName()
                    {
                      if (rename)
                      {
                        genPackage.clearCache();
                      }
                      else
                      {
                        rename = true;
                      }
                      inferredElement.setSimpleName("case" + genPackage.getClassUniqueName(genClass));
                    }
                  };
                associate(genClass, caseMethodInferrer);
                members.add(caseMethodInferrer.getInferredElement());
              }
            }
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName(genPackage.getSwitchClassName());
            inferredElement.setPackageName(genPackage.getUtilitiesPackageName());
          }
        };
      associate(genPackage, switchClassInferrer);
      result.add(switchClassInferrer.getInferredElement());
    }

    if (genPackage.hasClassifiers() && genPackage.isAdapterFactory() && !genPackage.getGenClasses().isEmpty())
    {
      JvmElementInferrer<JvmGenericType> adapterFactoryClassInferrer =
        new JvmElementInferrer<JvmGenericType>(X_MEDIUM)
        {
          @Override
          protected JvmGenericType inferStructure()
          {
            JvmGenericType adapterFactoryClass = TypesFactory.eINSTANCE.createJvmGenericType();
            adapterFactoryClass.setInterface(false);
            adapterFactoryClass.setVisibility(JvmVisibility.PUBLIC);
            return adapterFactoryClass;
          }

          @Override
          protected void inferDeepStructure()
          {
            for (GenClassifier genClassifier : genPackage.getGenClassifiers())
            {
              if (genClassifier instanceof GenClass)
              {
                final GenClass genClass = (GenClass)genClassifier;
                if (!genClass.isAbstract())
                {
                  JvmElementInferrer<JvmOperation> createMethodInferrer =
                    new JvmElementInferrer<JvmOperation>(X_MEDIUM)
                    {
                      @Override
                      protected JvmOperation inferStructure()
                      {
                        JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
                        jvmOperation.setVisibility(JvmVisibility.PUBLIC);
                        jvmOperation.setReturnType(getJvmTypeReference("org.eclipse.emf.common.notify.Adapter", genClass));
                        return jvmOperation;
                      }

                      @Override
                      public void inferName()
                      {
                        inferredElement.setSimpleName("create" + genClass.getName() + "Adapter");
                      }
                    };
                  associate(genClass, createMethodInferrer);
                  inferredElement.getMembers().add(createMethodInferrer.getInferredElement());
                }
              }
            }
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName(genPackage.getAdapterFactoryClassName());
            inferredElement.setPackageName(genPackage.getUtilitiesPackageName());
          }
        };
      associate(genPackage, adapterFactoryClassInferrer);
      result.add(adapterFactoryClassInferrer.getInferredElement());
    }

    if (genPackage.getGenModel().canGenerateEdit() && !genPackage.getGenClasses().isEmpty())
    {
      JvmElementInferrer<JvmGenericType> itemProviderAdapterFactoryClassInferrer =
        new JvmElementInferrer<JvmGenericType>(X_MEDIUM)
        {
          @Override
          protected JvmGenericType inferStructure()
          {
            JvmGenericType itemProviderAdapterFactoryClass = TypesFactory.eINSTANCE.createJvmGenericType();
            itemProviderAdapterFactoryClass.setInterface(false);
            itemProviderAdapterFactoryClass.setVisibility(JvmVisibility.PUBLIC);
            return itemProviderAdapterFactoryClass;
          }

          @Override
          protected void inferDeepStructure()
          {
            EList<JvmMember> members = inferredElement.getMembers();
            for (GenClassifier genClassifier : genPackage.getGenClassifiers())
            {
              if (genClassifier instanceof GenClass)
              {
                final GenClass genClass = (GenClass)genClassifier;
                if (!genClass.isAbstract() && genClass.getProvider() != GenProviderKind.NONE_LITERAL)
                {
                  if (genClass.isProviderSingleton())
                  {
                    JvmElementInferrer<JvmField> fieldInferrer =
                      new JvmElementInferrer<JvmField>(X_VERY_LOW)
                      {
                        @Override
                        protected JvmField inferStructure()
                        {
                          JvmField jvmField = TypesFactory.eINSTANCE.createJvmField();
                          jvmField.setVisibility(JvmVisibility.PROTECTED);
                          jvmField.setType(getJvmTypeReference(genClass.getQualifiedProviderClassName(), genClass));
                          return jvmField;
                        }

                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName(genClass.getUncapName() + "ItemProvider");
                        }
                      };
                    associate(genClass, fieldInferrer);
                    members.add(fieldInferrer.getInferredElement());
                  }

                  JvmElementInferrer<JvmOperation> createMethodInferrer =
                    new JvmElementInferrer<JvmOperation>(X_LOW)
                    {
                      @Override
                      protected JvmOperation inferStructure()
                      {
                        JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
                        jvmOperation.setVisibility(JvmVisibility.PUBLIC);
                        jvmOperation.setReturnType(getJvmTypeReference("org.eclipse.emf.common.notify.Adapter", genClass));
                        return jvmOperation;
                      }

                      @Override
                      public void inferName()
                      {
                        inferredElement.setSimpleName("create" + genClass.getName() + "Adapter");
                      }
                    };
                  associate(genClass, createMethodInferrer);
                  members.add(createMethodInferrer.getInferredElement());
                }
              }
            }
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName(genPackage.getItemProviderAdapterFactoryClassName());
            inferredElement.setPackageName(genPackage.getProviderPackageName());
          }
        };
      associate(genPackage, itemProviderAdapterFactoryClassInferrer);
      result.add(itemProviderAdapterFactoryClassInferrer.getInferredElement());

      for (GenClassifier genClassifier : genPackage.getGenClassifiers())
      {
        if (genClassifier instanceof GenClass)
        {
          final GenClass genClass = (GenClass)genClassifier;
          if (!genClass.isAbstract() && genClass.getProvider() != GenProviderKind.NONE_LITERAL)
          {
            JvmElementInferrer<JvmGenericType> itemProviderClassInferrer =
              new JvmElementInferrer<JvmGenericType>(X_MEDIUM)
              {
                @Override
                protected JvmGenericType inferStructure()
                {
                  JvmGenericType itemProviderClass = TypesFactory.eINSTANCE.createJvmGenericType();
                  itemProviderClass.setInterface(false);
                  itemProviderClass.setVisibility(JvmVisibility.PUBLIC);
                  return itemProviderClass;
                }

                @Override
                public void inferName()
                {
                  inferredElement.setSimpleName(genClass.getProviderClassName());
                  inferredElement.setPackageName(genPackage.getProviderPackageName());
                }
              };
            associate(genClass, itemProviderClassInferrer);
            result.add(itemProviderClassInferrer.getInferredElement());
          }
        }
      }
    }

    if (genPackage.canGenerateTests() && genPackage.hasClassifiers())
    {
      JvmElementInferrer<JvmGenericType> testSuiteClassInferrer =
        new JvmElementInferrer<JvmGenericType>(X_LOW)
        {
          @Override
          protected JvmGenericType inferStructure()
          {
            JvmGenericType adapterFactoryClass = TypesFactory.eINSTANCE.createJvmGenericType();
            adapterFactoryClass.setInterface(false);
            adapterFactoryClass.setVisibility(JvmVisibility.PUBLIC);
            return adapterFactoryClass;
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName(genPackage.getTestSuiteClassName());
            inferredElement.setPackageName(genPackage.getTestsPackageName());
          }
        };
      associate(genPackage, testSuiteClassInferrer);
      result.add(testSuiteClassInferrer.getInferredElement());

      for (GenClassifier genClassifier : genPackage.getGenClassifiers())
      {
        if (genClassifier instanceof GenClass)
        {
          final GenClass genClass = (GenClass)genClassifier;
          JvmElementInferrer<JvmGenericType> testCaseClassInferrer =
            new JvmElementInferrer<JvmGenericType>(X_VERY_LOW)
            {
              @Override
              protected JvmGenericType inferStructure()
              {
                JvmGenericType testCaseClass = TypesFactory.eINSTANCE.createJvmGenericType();
                testCaseClass.setInterface(false);
                testCaseClass.setVisibility(JvmVisibility.PUBLIC);
                return testCaseClass;
              }

              @Override
              protected void inferDeepStructure()
              {
                EList<JvmMember> members = inferredElement.getMembers();
                for (final GenOperation genOperation : genClass.getImplementedGenOperations())
                {
                  JvmElementInferrer<JvmOperation> operationTestInferrer =
                    new JvmElementInferrer<JvmOperation>(X_VERY_LOW)
                    {
                      @Override
                      protected JvmOperation inferStructure()
                      {
                        JvmOperation jvmOperation = createJvmOperation(genOperation, JvmVisibility.PUBLIC, false, getJvmTypeReference("void", genOperation));
                        return jvmOperation;
                      }

                      protected boolean rename = false;

                      @Override
                      public void inferName()
                      {
                        if (rename)
                        {
                          genClass.clearCache();
                        }
                        else
                        {
                          rename = true;
                        }
                        inferredElement.setSimpleName("test" + genClass.getUniqueName(genOperation));
                      }
                    };
                  associate(genOperation, operationTestInferrer);
                  for (GenParameter genParameter : genOperation.getGenParameters())
                  {
                    associate(genParameter.getTypeGenClassifier(), operationTestInferrer);
                  }
                  members.add(operationTestInferrer.getInferredElement());
                }
              }

              @Override
              public void inferName()
              {
                inferredElement.setSimpleName(genClass.getTestCaseClassName());
                inferredElement.setPackageName(genPackage.getTestsPackageName());
              }
            };
          associate(genClass, testCaseClassInferrer);
          result.add(testCaseClassInferrer.getInferredElement());
        }
      }
    }

    for (GenClassifier genClassifier : genPackage.getGenClassifiers())
    {
      result.addAll(getDeclaredTypes(genClassifier));
    }

    return result;
  }

  protected JvmGenericType getFactory(final GenPackage genPackage, final boolean isInterface, final boolean isImplementation)
  {
    JvmElementInferrer<JvmGenericType> factoryClassInferrer =
      new JvmElementInferrer<JvmGenericType>(isInterface ? X_VERY_HIGH : X_HIGH)
      {
        @Override
        protected JvmGenericType inferStructure()
        {
          JvmGenericType factoryClass = TypesFactory.eINSTANCE.createJvmGenericType();
          factoryClass.setInterface(!isImplementation);
          factoryClass.setVisibility(JvmVisibility.PUBLIC);
          return factoryClass;
        }

        @Override
        protected void inferDeepStructure()
        {
          GenModel genModel = genPackage.getGenModel();
          EList<JvmTypeReference> superTypes = inferredElement.getSuperTypes();
          if (isImplementation)
          {
            superTypes.add(getJvmTypeReference("org.eclipse.emf.ecore.impl.EFactoryImpl", genPackage));
            if (!genModel.isSuppressInterfaces())
            {
              superTypes.add(getJvmTypeReference(genPackage.getQualifiedFactoryInterfaceName(), genPackage));
            }
          }
          else if (!genModel.isSuppressEMFMetaData())
          {
            superTypes.add(getJvmTypeReference("org.eclipse.emf.ecore.EFactory", genPackage));
          }

          EList<JvmMember> members = inferredElement.getMembers();
          if (isImplementation && (genModel.isSuppressEMFMetaData() || genModel.isSuppressInterfaces()))
          {
            members.add(createJvmField(genPackage, JvmVisibility.PUBLIC, true, "INSTANCE", getJvmTypeReference(genPackage.getQualifiedFactoryClassName(), genPackage)));
          }
          if (isInterface)
          {
            if (genModel.isSuppressEMFMetaData())
            {
              members.add(createJvmField(genPackage, JvmVisibility.PUBLIC, true, "INSTANCE", getJvmTypeReference(genPackage.getQualifiedFactoryInterfaceName(), genPackage)));
            }
            else if (!genModel.isSuppressInterfaces())
            {
              members.add(createJvmField(genPackage, JvmVisibility.PUBLIC, true, "eINSTANCE", getJvmTypeReference(genPackage.getQualifiedFactoryInterfaceName(), genPackage)));
            }
          }

          boolean isDataTypeConverters = genPackage.isDataTypeConverters();
          for (GenClassifier genClassifier : genPackage.getGenClassifiers())
          {
           if (genClassifier instanceof GenDataType)
            {
              final GenDataType genDataType = (GenDataType)genClassifier;

              // During prelinking inference, the instance class name won't be set.
              //
              if (genDataType.isSerializable() && genDataType.getRawInstanceClassName() != null)
              {
               if (isDataTypeConverters || isImplementation)
                {
                  XDataType xDataType = mapper.getXDataType(genClassifier);
                  final XBlockExpression createBody = xDataType.getCreateBody();
                  if (createBody != null || isDataTypeConverters)
                  {
                    JvmElementInferrer<JvmOperation> createMethodInferrer =
                      new JvmElementInferrer<JvmOperation>(isInterface ? X_VERY_HIGH : X_HIGH)
                      {
                        @Override
                        protected JvmOperation inferStructure()
                        {
                          JvmOperation jvmOperation = createJvmOperation(genPackage,JvmVisibility.PUBLIC, false, getJvmTypeReference(genDataType.getImportedParameterizedInstanceClassName(), genDataType));
                          JvmFormalParameter jvmFormalParameter = createJvmFormalParameter(genPackage, createBody == null ? "literal" : "it", getJvmTypeReference("java.lang.String", genPackage));
                          jvmOperation.getParameters().add(jvmFormalParameter);
                          return jvmOperation;
                        }

                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName("create" + genDataType.getName());
                        }
                      };
                    associate(genDataType, createMethodInferrer);
                    members.add(createMethodInferrer.getInferredElement());
                  }
                  if (isImplementation)
                  {
                    JvmElementInferrer<JvmOperation> genericCreateMethodInferrer =
                      new JvmElementInferrer<JvmOperation>(X_MEDIUM)
                      {
                        @Override
                        protected JvmOperation inferStructure()
                        {
                          JvmOperation jvmOperation = createJvmOperation(genPackage,JvmVisibility.PUBLIC, false, getJvmTypeReference(genDataType.getImportedParameterizedObjectInstanceClassName(), genDataType));
                          EList<JvmFormalParameter> parameters = jvmOperation.getParameters();
                          parameters.add(createJvmFormalParameter(genPackage, "eDataType", getJvmTypeReference("org.eclipse.emf.ecore.EDataType", genPackage)));
                          parameters.add(createJvmFormalParameter(genPackage, "initialValue", getJvmTypeReference("java.lang.String", genPackage)));
                          return jvmOperation;
                        }

                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName("create" + genDataType.getName() + "FromString");
                        }
                      };
                    associate(genDataType, genericCreateMethodInferrer);
                    members.add(genericCreateMethodInferrer.getInferredElement());
                  }
                  final XBlockExpression convertBody = xDataType.getConvertBody();
                  if (convertBody != null || isDataTypeConverters)
                  {
                    JvmElementInferrer<JvmOperation> convertMethodInferrer =
                      new JvmElementInferrer<JvmOperation>(isInterface ? X_VERY_HIGH: X_HIGH)
                      {
                        @Override
                        protected JvmOperation inferStructure()
                        {
                          JvmOperation jvmOperation = createJvmOperation(genPackage,JvmVisibility.PUBLIC, false, getJvmTypeReference("java.lang.String", genPackage));
                          JvmFormalParameter jvmFormalParameter = createJvmFormalParameter(genPackage, convertBody == null ? "instanceValue" : "it", getJvmTypeReference(genDataType.getImportedBoundedWildcardInstanceClassName(), genDataType));
                          jvmOperation.getParameters().add(jvmFormalParameter);
                          return jvmOperation;
                        }

                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName("convert" + genDataType.getName());
                        }
                      };
                    associate(genDataType, convertMethodInferrer);
                    members.add(convertMethodInferrer.getInferredElement());
                  }
                  if (isImplementation)
                  {
                    JvmElementInferrer<JvmOperation> convertMethodInferrer =
                      new JvmElementInferrer<JvmOperation>(X_MEDIUM)
                      {
                        @Override
                        protected JvmOperation inferStructure()
                        {
                          JvmOperation jvmOperation = createJvmOperation(genPackage,JvmVisibility.PUBLIC, false, getJvmTypeReference("java.lang.String", genPackage));
                          EList<JvmFormalParameter> parameters = jvmOperation.getParameters();
                          parameters.add(createJvmFormalParameter(genPackage, "eDataType", getJvmTypeReference("org.eclipse.emf.ecore.EDataType", genPackage)));
                          parameters.add(createJvmFormalParameter(genPackage, "instanceValue", getJvmTypeReference("java.lang.Object", genPackage)));
                          return jvmOperation;
                        }

                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName("convert" + genDataType.getName() + "ToString");
                        }
                      };
                    associate(genDataType, convertMethodInferrer);
                    members.add(convertMethodInferrer.getInferredElement());
                  }
                }
              }
            }
            else
            {
              final GenClass genClass = (GenClass)genClassifier;
              if (!genClass.isAbstract())
              {
                if (isImplementation || genClass.hasFactoryInterfaceCreateMethod())
                {
                  JvmElementInferrer<JvmOperation> createMethodInferrer =
                    new JvmElementInferrer<JvmOperation>(isInterface ? X_HIGH : X_LOW)
                    {
                      @Override
                      protected JvmOperation inferStructure()
                      {
                        JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
                        jvmOperation.setVisibility(JvmVisibility.PUBLIC);
                        jvmOperation.setReturnType(getJvmTypeReference(genClass.getQualifiedInterfaceName() + genClass.getInterfaceTypeArguments(), genClass));
                        populateTypeParameters(null, genClass.getGenTypeParameters(), jvmOperation.getTypeParameters());
                        return jvmOperation;
                      }

                      @Override
                      public void inferName()
                      {
                        inferredElement.setSimpleName("create" + genClass.getName());
                      }
                    };

                  associate(genClass, createMethodInferrer);
                  members.add(createMethodInferrer.getInferredElement());
                }
              }
            }
          }
        }

        @Override
        public void inferName()
        {
          if (isImplementation)
          {
            inferredElement.setSimpleName(genPackage.getFactoryClassName());
            inferredElement.setPackageName(genPackage.getReflectionClassPackageName());
          }
          else
          {
            inferredElement.setSimpleName(genPackage.getFactoryInterfaceName());
            inferredElement.setPackageName(genPackage.getReflectionPackageName());
          }
        }
      };
    associate(genPackage, factoryClassInferrer);
    return factoryClassInferrer.getInferredElement();
  }

  protected JvmGenericType getPackage(final GenPackage genPackage, final boolean isInterface, final boolean isImplementation)
  {
    JvmElementInferrer<JvmGenericType> packageClassInferrer =
      new JvmElementInferrer<JvmGenericType>(X_HIGH)
      {
        JvmElementInferrer<JvmGenericType> packageLiteralsInterfaceInferrer;

        @Override
        protected JvmGenericType inferStructure()
        {
          JvmGenericType packageClass = TypesFactory.eINSTANCE.createJvmGenericType();
          packageClass.setInterface(!isImplementation);
          packageClass.setVisibility(JvmVisibility.PUBLIC);

          if (isInterface && genPackage.isLiteralsInterface())
          {
            packageLiteralsInterfaceInferrer =
              new JvmElementInferrer<JvmGenericType>(X_LOW)
              {
                @Override
                protected JvmGenericType inferStructure()
                {
                  JvmGenericType packageLiteralsInterface = TypesFactory.eINSTANCE.createJvmGenericType();
                  packageLiteralsInterface.setInterface(true);
                  packageLiteralsInterface.setVisibility(JvmVisibility.PUBLIC);
                  return packageLiteralsInterface;
                }

                @Override
                protected void inferDeepStructure()
                {
                  for (final GenClassifier genClassifier : genPackage.getOrderedGenClassifiers())
                  {
                    JvmElementInferrer<JvmField> classifierFieldInferrer =
                      new JvmElementInferrer<JvmField>(X_MEDIUM)
                      {
                        @Override
                        protected JvmField inferStructure()
                        {
                          return createJvmField(genClassifier, JvmVisibility.PUBLIC, true, getJvmTypeReference(genClassifier.getImportedMetaType(), genClassifier));
                        }

                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName(genPackage.getClassifierID(genClassifier));
                        }
                      };
                    associate(genClassifier, classifierFieldInferrer);
                    EList<JvmMember> members = inferredElement.getMembers();
                    members.add(classifierFieldInferrer.getInferredElement());

                    if (genClassifier instanceof GenClass)
                    {
                      final GenClass genClass = (GenClass)genClassifier;

                      for (final GenFeature genFeature : genClass.getGenFeatures())
                      {
                        JvmElementInferrer<JvmField> featureFieldInferrer =
                          new JvmElementInferrer<JvmField>(X_MEDIUM)
                          {
                            @Override
                            protected JvmField inferStructure()
                            {
                              return createJvmField(genClassifier, JvmVisibility.PUBLIC, true, getJvmTypeReference(genFeature.getImportedMetaType(), genClassifier));
                            }

                            @Override
                            public void inferName()
                            {
                              inferredElement.setSimpleName(genClass.getFeatureID(genFeature));
                            }
                          };
                        associate(genFeature, featureFieldInferrer);
                        associate(genClass, featureFieldInferrer);
                        members.add(featureFieldInferrer.getInferredElement());
                      }

                      if (genPackage.getGenModel().isOperationReflection())
                      {
                        for (final GenOperation genOperation : genClass.getGenOperations())
                        {
                          JvmElementInferrer<JvmField> operationFieldInferrer =
                            new JvmElementInferrer<JvmField>(X_LOW)
                            {
                              @Override
                              protected JvmField inferStructure()
                              {
                                return createJvmField(genClassifier, JvmVisibility.PUBLIC, true, getJvmTypeReference(genOperation.getImportedMetaType(), genClassifier));
                              }

                              protected boolean rename = false;

                              @Override
                              public void inferName()
                              {
                                if (rename)
                                {
                                  genClass.clearCache();
                                }
                                else
                                {
                                  rename = true;
                                }
                                inferredElement.setSimpleName(genClass.getOperationID(genOperation, false));
                              }
                            };
                          associate(genOperation, operationFieldInferrer);
                          associate(genClass, operationFieldInferrer);
                          for (GenParameter genParameter : genOperation.getGenParameters())
                          {
                            associate(genParameter.getTypeGenClassifier(), operationFieldInferrer);
                          }
                          members.add(operationFieldInferrer.getInferredElement());
                        }
                      }
                    }
                  }
                }

                @Override
                public void inferName()
                {
                  inferredElement.setSimpleName("Literals");
                }
              };
            packageClass.getMembers().add(packageLiteralsInterfaceInferrer.getInferredElement());
          }

          return packageClass;
        }

        @Override
        protected void inferDeepStructure()
        {
          EList<JvmTypeReference> superTypes = inferredElement.getSuperTypes();
          if (isImplementation)
          {
            superTypes.add(getJvmTypeReference("org.eclipse.emf.ecore.impl.EPackageImpl", genPackage));
            superTypes.add(getJvmTypeReference(genPackage.getQualifiedPackageInterfaceName(), genPackage));
          }
          else
          {
            superTypes.add(getJvmTypeReference("org.eclipse.emf.ecore.EPackage", genPackage));
          }

          EList<JvmMember> members = inferredElement.getMembers();
          if (isInterface)
          {
            members.add(createJvmField(genPackage, JvmVisibility.PUBLIC, true, "eNS_URI", getJvmTypeReference("java.lang.String", genPackage)));
            members.add(createJvmField(genPackage, JvmVisibility.PUBLIC, true, "eNAME", getJvmTypeReference("java.lang.String", genPackage)));
            members.add(createJvmField(genPackage, JvmVisibility.PUBLIC, true, "eNS_PREFIX", getJvmTypeReference("java.lang.String", genPackage)));
            if (genPackage.isContentType())
            {
              members.add(createJvmField(genPackage, JvmVisibility.PUBLIC, true, "eCONTENT_TYPE", getJvmTypeReference("java.lang.String", genPackage)));
            }
            members.add(createJvmField(genPackage, JvmVisibility.PUBLIC, true, "eINSTANCE", getJvmTypeReference(genPackage.getQualifiedPackageInterfaceName(), genPackage)));
          }

          for (final GenClassifier genClassifier : genPackage.getOrderedGenClassifiers())
          {
            if (isInterface)
            {
              JvmElementInferrer<JvmField> classifierFieldInferrer =
                new JvmElementInferrer<JvmField>(X_LOW)
                {
                  @Override
                  protected JvmField inferStructure()
                  {
                    return createJvmField(genClassifier, JvmVisibility.PUBLIC, true, getJvmTypeReference("int", genClassifier));
                  }

                  @Override
                  public void inferName()
                  {
                    inferredElement.setSimpleName(genPackage.getClassifierID(genClassifier));
                  }
                };
              associate(genClassifier, classifierFieldInferrer);
              members.add(classifierFieldInferrer.getInferredElement());
            }

            if (genClassifier instanceof GenClass)
            {
              final GenClass genClass = (GenClass)genClassifier;

              if (isInterface)
              {
                JvmElementInferrer<JvmField> featureCountFieldInferrer =
                  new JvmElementInferrer<JvmField>(X_VERY_LOW)
                  {
                    @Override
                    protected JvmField inferStructure()
                    {
                      return createJvmField(genClassifier, JvmVisibility.PUBLIC, true, getJvmTypeReference("int", genClassifier));
                    }

                    @Override
                    public void inferName()
                    {
                      inferredElement.setSimpleName(genClass.getFeatureCountID());
                    }
                  };
                associate(genClass, featureCountFieldInferrer);
                members.add(featureCountFieldInferrer.getInferredElement());

                for (final GenFeature genFeature : genClass.getAllGenFeatures())
                {
                  JvmElementInferrer<JvmField> featureFieldInferrer =
                    new JvmElementInferrer<JvmField>(X_LOW)
                    {
                      @Override
                      protected JvmField inferStructure()
                      {
                        return createJvmField(genClassifier, JvmVisibility.PUBLIC, true, getJvmTypeReference("int", genClassifier));
                      }

                      @Override
                      public void inferName()
                      {
                        inferredElement.setSimpleName(genClass.getFeatureID(genFeature));
                      }
                    };
                  associate(genFeature, featureFieldInferrer);
                  associate(genClass, featureFieldInferrer);
                  members.add(featureFieldInferrer.getInferredElement());

                  JvmElementInferrer<JvmOperation> featureAccessorInferrer =
                    new JvmElementInferrer<JvmOperation>(X_VERY_LOW)
                    {
                      @Override
                      protected JvmOperation inferStructure()
                      {
                        JvmOperation featureAccessor = createJvmOperation(genFeature, JvmVisibility.PUBLIC, false, getJvmTypeReference(genFeature.getImportedMetaType(), genFeature));
                        return featureAccessor;
                      }

                      @Override
                      public void inferName()
                      {
                        inferredElement.setSimpleName("get" + genFeature.getFeatureAccessorName());
                      }
                    };
                  associate(genFeature, featureAccessorInferrer);
                  associate(genClass, featureAccessorInferrer);
                  members.add(featureAccessorInferrer.getInferredElement());
                }
              }

              if (isInterface && genPackage.getGenModel().isOperationReflection())
              {
                JvmElementInferrer<JvmField> operationCountFieldInferrer =
                  new JvmElementInferrer<JvmField>(X_VERY_LOW)
                  {
                    @Override
                    protected JvmField inferStructure()
                    {
                      return createJvmField(genClassifier, JvmVisibility.PUBLIC, true, getJvmTypeReference("int", genClassifier));
                    }

                    @Override
                    public void inferName()
                    {
                      inferredElement.setSimpleName(genClass.getOperationCountID());
                    }
                  };
                associate(genClass, operationCountFieldInferrer);
                members.add(operationCountFieldInferrer.getInferredElement());

                for (final GenOperation genOperation : genClass.getAllGenOperations(false))
                {
                  if (genClass.getOverrideGenOperation(genOperation) == null)
                  {
                    JvmElementInferrer<JvmField> operationFieldInferrer =
                      new JvmElementInferrer<JvmField>(X_LOW)
                      {
                        @Override
                        protected JvmField inferStructure()
                        {
                          return createJvmField(genClassifier, JvmVisibility.PUBLIC, true, getJvmTypeReference("int", genClassifier));
                        }

                        protected boolean rename = false;

                        @Override
                        public void inferName()
                        {
                          if (rename)
                          {
                            genClass.clearCache();
                          }
                          else
                          {
                            rename = true;
                          }
                          inferredElement.setSimpleName(genClass.getOperationID(genOperation, false));
                        }
                      };
                    associate(genOperation, operationFieldInferrer);
                    associate(genClass, operationFieldInferrer);
                    for (GenParameter genParameter : genOperation.getGenParameters())
                    {
                      associate(genParameter.getTypeGenClassifier(), operationFieldInferrer);
                    }
                    members.add(operationFieldInferrer.getInferredElement());

                    JvmElementInferrer<JvmOperation> operationAccessorInferrer =
                      new JvmElementInferrer<JvmOperation>(X_VERY_LOW)
                      {
                        @Override
                        protected JvmOperation inferStructure()
                        {
                          JvmOperation featureAccessor = createJvmOperation(genClass, JvmVisibility.PUBLIC, false, getJvmTypeReference(genOperation.getImportedMetaType(), genClass));
                          return featureAccessor;
                        }

                        protected boolean rename = false;

                        @Override
                        public void inferName()
                        {
                          if (rename)
                          {
                            genClass.clearCache();
                          }
                          else
                          {
                            rename = true;
                          }
                          inferredElement.setSimpleName("get" + genOperation.getOperationAccessorName());
                        }
                      };
                    associate(genOperation, operationAccessorInferrer);
                    associate(genClass, operationAccessorInferrer);
                    for (GenParameter genParameter : genOperation.getGenParameters())
                    {
                      associate(genParameter.getTypeGenClassifier(), operationAccessorInferrer);
                    }
                    members.add(operationAccessorInferrer.getInferredElement());
                  }
                }
              }
            }

            JvmElementInferrer<JvmOperation> classifierAccessorInferrer =
              new JvmElementInferrer<JvmOperation>(X_VERY_LOW)
              {
                @Override
                protected JvmOperation inferStructure()
                {
                  return createJvmOperation(genClassifier, JvmVisibility.PUBLIC, false, getJvmTypeReference(genClassifier.getImportedMetaType(), genClassifier));
                }

                @Override
                public void inferName()
                {
                  inferredElement.setSimpleName("get" + genClassifier.getClassifierAccessorName());
                }
              };
            associate(genClassifier, classifierAccessorInferrer);
            members.add(classifierAccessorInferrer.getInferredElement());
          }

          if (packageLiteralsInterfaceInferrer != null)
          {
            packageLiteralsInterfaceInferrer.inferDeepStructure();
          }
        }

        @Override
        public void inferName()
        {
          if (isImplementation)
          {
            inferredElement.setSimpleName(genPackage.getPackageClassName());
            inferredElement.setPackageName(genPackage.getReflectionClassPackageName());
          }
          else
          {
            inferredElement.setSimpleName(genPackage.getPackageInterfaceName());
            inferredElement.setPackageName(genPackage.getReflectionPackageName());
          }
        }
      };
    associate(genPackage, packageClassInferrer);
    JvmGenericType packageClass = packageClassInferrer.getInferredElement();

    return packageClass;
  }

  protected List<? extends JvmDeclaredType> getDeclaredTypes(GenClassifier genClassifier)
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

  protected JvmDeclaredType getDeclaredType(final GenEnum genEnum)
  {
    if (genEnum.getGenModel().useGenerics())
    {
      JvmElementInferrer<JvmEnumerationType> enumTypeInferrer =
       new JvmElementInferrer<JvmEnumerationType>(X_VERY_HIGH)
       {
         @Override
         protected JvmEnumerationType inferStructure()
         {
           final JvmEnumerationType jvmEnumerationType = TypesFactory.eINSTANCE.createJvmEnumerationType();
           jvmEnumerationType.getSuperTypes().add(getJvmTypeReference("java.lang.Enum<" + genEnum.getQualifiedInstanceClassName() + ">", genEnum));
           jvmEnumerationType.getSuperTypes().add(getJvmTypeReference("org.eclipse.emf.common.util.Enumerator", genEnum));
           jvmEnumerationType.setFinal(true);
           jvmEnumerationType.setVisibility(JvmVisibility.PUBLIC);
           final EList<JvmMember> members = jvmEnumerationType.getMembers();

           for (final GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals())
           {
             JvmElementInferrer<JvmEnumerationLiteral> enumLiteralInferrer =
               new JvmElementInferrer<JvmEnumerationLiteral>(X_VERY_HIGH)
               {
                 @Override
                 protected JvmEnumerationLiteral inferStructure()
                 {
                   JvmEnumerationLiteral jvmEnumerationLiteral = TypesFactory.eINSTANCE.createJvmEnumerationLiteral();
                   jvmEnumerationLiteral.setStatic(true);
                   jvmEnumerationLiteral.setFinal(true);
                   jvmEnumerationLiteral.setVisibility(JvmVisibility.PUBLIC);
                   JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
                   jvmParameterizedTypeReference.setType(jvmEnumerationType);
                   jvmEnumerationLiteral.setType(jvmParameterizedTypeReference);
                   return jvmEnumerationLiteral;
                 }

                 @Override
                 public void inferName()
                 {
                   inferredElement.setSimpleName(genEnumLiteral.getEnumLiteralInstanceConstantName());
                 }
               };
             associate(genEnumLiteral, enumLiteralInferrer);
             members.add(enumLiteralInferrer.getInferredElement());

             JvmElementInferrer<JvmEnumerationLiteral> enumLiteralValueInferrer =
               new JvmElementInferrer<JvmEnumerationLiteral>(X_VERY_HIGH)
               {
                 @Override
                 protected JvmEnumerationLiteral inferStructure()
                 {
                   JvmEnumerationLiteral enumLiteralValue = TypesFactory.eINSTANCE.createJvmEnumerationLiteral();
                   enumLiteralValue.setStatic(true);
                   enumLiteralValue.setFinal(true);
                   enumLiteralValue.setVisibility(JvmVisibility.PUBLIC);
                   JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
                   jvmParameterizedTypeReference.setType(jvmEnumerationType);
                   enumLiteralValue.setType(getJvmTypeReference("int", genEnumLiteral));
                   return enumLiteralValue;
                 }

                 @Override
                 public void inferName()
                 {
                   inferredElement.setSimpleName(genEnumLiteral.getEnumLiteralValueConstantName());
                 }
               };
             associate(genEnumLiteral, enumLiteralValueInferrer);
             members.add(enumLiteralValueInferrer.getInferredElement());
           }
           return jvmEnumerationType;
         }

         @Override
         public void inferName()
         {
           inferredElement.setSimpleName(genEnum.getName());
           inferredElement.setPackageName(genEnum.getGenPackage().getInterfacePackageName());
         }
       };
      associate(genEnum, enumTypeInferrer);
      return enumTypeInferrer.getInferredElement();
    }
    else
    {
      JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
      // TODO
      return jvmGenericType;
    }
  }

  protected List<? extends JvmDeclaredType> getDeclaredTypes(GenClass genClass)
  {
    ArrayList<JvmDeclaredType> result = new ArrayList<JvmDeclaredType>();
    boolean isSuppressInterfaces = genClass.getGenModel().isSuppressInterfaces();
    boolean isInterface = genClass.isInterface();
    if (!genClass.isExternalInterface() && (!isSuppressInterfaces || isInterface))
    {
      JvmGenericType jvmGenericType = getDeclaredType(genClass, true, false);
      result.add(jvmGenericType);
    }
    if (!isInterface)
    {
      JvmGenericType jvmGenericType = getDeclaredType(genClass, isSuppressInterfaces, true);
      result.add(jvmGenericType);
    }
    return result;
  }

  protected JvmGenericType getDeclaredType(final GenClass genClass, final boolean isInterface, final boolean isImplementation)
  {
    JvmElementInferrer<JvmGenericType> modelClassInferrer =
      new JvmElementInferrer<JvmGenericType>(isInterface ? X_VERY_HIGH : X_HIGH)
      {
        @Override
        protected JvmGenericType inferStructure()
        {
          JvmGenericType modelClass = TypesFactory.eINSTANCE.createJvmGenericType();
          modelClass.setInterface(!isImplementation);
          modelClass.setVisibility(JvmVisibility.PUBLIC);
          return modelClass;
        }

        @Override
        protected void inferDeepStructure()
        {
          populateTypeParameters(null, genClass.getGenTypeParameters(), inferredElement.getTypeParameters());

          EList<JvmTypeReference> superTypes = inferredElement.getSuperTypes();
          if (isImplementation)
          {
            String qualifiedClassExtends = genClass.getQualifiedClassExtends();
            if ("".equals(qualifiedClassExtends))
            {
                superTypes.add(getJvmTypeReference("org.eclipse.emf.ecore.impl.BasicEObjectImpl", genClass));
            }
            else
            {
              superTypes.add(getJvmTypeReference(qualifiedClassExtends, genClass));
            }
            List<String> qualifiedClassImplementsList = genClass.getQualifiedClassImplementsList();
            for (String instanceTypeName : qualifiedClassImplementsList)
            {
              superTypes.add(getJvmTypeReference(instanceTypeName, genClass));
            }
          }
          else
          {
            List<String> qualifiedInterfaceExtendsList = genClass.getQualifiedInterfaceExtendsList();
            for (String instanceTypeName : qualifiedInterfaceExtendsList)
            {
              superTypes.add(getJvmTypeReference(instanceTypeName, genClass));
            }

            if (superTypes.isEmpty())
            {
              if (genClass.isEObject())
              {
                superTypes.add(getJvmTypeReference("org.eclipse.emf.common.notify.Notifier", genClass));
              }
              else
              {
                superTypes.add(getJvmTypeReference("java.lang.Object", genClass));
              }
            }
          }

          EList<JvmMember> members = inferredElement.getMembers();

          GenModel genModel = genClass.getGenModel();
          if (isImplementation && !genModel.isReflectiveDelegation())
          {
            for (final GenFeature genFeature : genClass.getDeclaredFieldGenFeatures())
            {
              if (genFeature.getEcoreFeature().getEType() != null)
              {
                if (genFeature.hasSettingDelegate())
                {
                  JvmElementInferrer<JvmField> settingDelegateFieldInferrer =
                    new JvmElementInferrer<JvmField>(X_LOW)
                    {
                      @Override
                      protected JvmField inferStructure()
                      {
                        JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, false, getJvmTypeReference("org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate", genFeature));
                        return settingDelegateField;
                      }
  
                      @Override
                      public void inferName()
                      {
                        inferredElement.setSimpleName(genFeature.getUpperName() + "__ESETTING_DELEGATE");
                      }
                    };
                  associate(genFeature, settingDelegateFieldInferrer);
                  members.add(settingDelegateFieldInferrer.getInferredElement());
                }
                else if (genFeature.isListType() || genFeature.isReferenceType())
                {
                  if (genClass.isField(genFeature))
                  {
                    JvmElementInferrer<JvmField> fieldInferrer =
                      new JvmElementInferrer<JvmField>(X_LOW)
                      {
                        @Override
                        protected JvmField inferStructure()
                        {
                          JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, false, getJvmTypeReference(genFeature.getImportedInternalType(genClass), genFeature));
                          return settingDelegateField;
                        }
  
                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName(genFeature.getSafeName());
                        }
                      };
                    associate(genFeature, fieldInferrer);
                    members.add(fieldInferrer.getInferredElement());
                  }
                  if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType())
                  {
                    JvmElementInferrer<JvmField> emptyArrayFieldInferrer =
                      new JvmElementInferrer<JvmField>(X_LOW)
                      {
                        @Override
                        protected JvmField inferStructure()
                        {
                          JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, true, getJvmTypeReference(genFeature.getRawListItemType() + "[]", genFeature));
                          return settingDelegateField;
                        }
  
                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName(genFeature.getUpperName() + "_EEMPTY_ARRAY");
                        }
                      };
                    associate(genFeature, emptyArrayFieldInferrer);
                    members.add(emptyArrayFieldInferrer.getInferredElement());
                  }
                }
                else
                {
                  if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable())))
                  {
                    JvmElementInferrer<JvmField> defaultFieldInferrer =
                      new JvmElementInferrer<JvmField>(X_LOW)
                      {
                        @Override
                        protected JvmField inferStructure()
                        {
                          JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, true, getJvmTypeReference(genFeature.getImportedType(genClass), genFeature));
                          return settingDelegateField;
                        }
  
                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName(genFeature.getEDefault());
                        }
                      };
                    associate(genFeature, defaultFieldInferrer);
                    members.add(defaultFieldInferrer.getInferredElement());
                  }
                  if (genClass.isField(genFeature))
                  {
                    if (genClass.isFlag(genFeature))
                    {
                      int flagIndex = genClass.getFlagIndex(genFeature);
                      if (flagIndex > 31 && flagIndex % 32 == 0)
                      {
                        JvmElementInferrer<JvmField> flagFieldInferrer =
                          new JvmElementInferrer<JvmField>(X_LOW)
                          {
                            @Override
                            protected JvmField inferStructure()
                            {
                              JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, false, getJvmTypeReference("int", genFeature));
                              return settingDelegateField;
                            }
  
                            @Override
                            public void inferName()
                            {
                              inferredElement.setSimpleName(genClass.getFlagsField(genFeature));
                            }
                          };
                        associate(genFeature, flagFieldInferrer);
                        members.add(flagFieldInferrer.getInferredElement());
                      }
                      if (genFeature.isEnumType())
                      {
                        JvmElementInferrer<JvmField> flagOffsetFieldInferrer =
                          new JvmElementInferrer<JvmField>(X_LOW)
                          {
                            @Override
                            protected JvmField inferStructure()
                            {
                              JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, true, getJvmTypeReference("int", genFeature));
                              return settingDelegateField;
                            }
  
                            @Override
                            public void inferName()
                            {
                              inferredElement.setSimpleName(genFeature.getUpperName() + "_EFLAG_OFFSET");
                            }
                          };
                        associate(genFeature, flagOffsetFieldInferrer);
                        members.add(flagOffsetFieldInferrer.getInferredElement());
  
                        JvmElementInferrer<JvmField> flagsDefaultFieldInferrer =
                          new JvmElementInferrer<JvmField>(X_LOW)
                          {
                            @Override
                            protected JvmField inferStructure()
                            {
                              JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, true, getJvmTypeReference("int", genFeature));
                              return settingDelegateField;
                            }
  
                            @Override
                            public void inferName()
                            {
                              inferredElement.setSimpleName(genFeature.getUpperName() + "_EFLAG_DEFAULT");
                            }
                          };
                        associate(genFeature, flagsDefaultFieldInferrer);
                        members.add(flagsDefaultFieldInferrer.getInferredElement());
  
                        JvmElementInferrer<JvmField> flagValuesFieldInferrer =
                          new JvmElementInferrer<JvmField>(X_LOW)
                          {
                            @Override
                            protected JvmField inferStructure()
                            {
                              JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PRIVATE, true, getJvmTypeReference("int", genFeature));
                              return settingDelegateField;
                            }
  
                            @Override
                            public void inferName()
                            {
                              inferredElement.setSimpleName(genFeature.getUpperName() + "_EFLAG_VALUES");
                            }
                          };
                        associate(genFeature, flagValuesFieldInferrer);
                        members.add(flagValuesFieldInferrer.getInferredElement());
                      }
  
                      JvmElementInferrer<JvmField> flagOffsetFieldInferrer =
                        new JvmElementInferrer<JvmField>(X_LOW)
                        {
                          @Override
                          protected JvmField inferStructure()
                          {
                            JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, true, getJvmTypeReference("int", genFeature));
                            return settingDelegateField;
                          }
  
                          @Override
                          public void inferName()
                          {
                            inferredElement.setSimpleName(genFeature.getUpperName() + "_EFLAG");
                          }
                        };
                      associate(genFeature, flagOffsetFieldInferrer);
                      members.add(flagOffsetFieldInferrer.getInferredElement());
                    }
                    else
                    {
                      JvmElementInferrer<JvmField> fieldInferrer =
                        new JvmElementInferrer<JvmField>(X_LOW)
                        {
                          @Override
                          protected JvmField inferStructure()
                          {
                            JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, false, getJvmTypeReference(genFeature.getImportedType(genClass), genFeature));
                            return settingDelegateField;
                          }
  
                          @Override
                          public void inferName()
                          {
                            inferredElement.setSimpleName(genFeature.getSafeName());
                          }
                        };
                      associate(genFeature, fieldInferrer);
                      members.add(fieldInferrer.getInferredElement());
                    }
                  }
                }
                if (genClass.isESetField(genFeature))
                {
                  if (genClass.isESetFlag(genFeature))
                  {
                    int flagIndex = genClass.getESetFlagIndex(genFeature);
                    if (flagIndex > 31 && flagIndex % 32 == 0)
                    {
                      JvmElementInferrer<JvmField> eSetFlagsFieldInferrer =
                        new JvmElementInferrer<JvmField>(X_LOW)
                        {
                          @Override
                          protected JvmField inferStructure()
                          {
                            JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, false, getJvmTypeReference("int", genFeature));
                            return settingDelegateField;
                          }
  
                          @Override
                          public void inferName()
                          {
                            inferredElement.setSimpleName(genClass.getESetFlagsField(genFeature));
                          }
                        };
                      associate(genFeature, eSetFlagsFieldInferrer);
                      members.add(eSetFlagsFieldInferrer.getInferredElement());
                    }
  
                    JvmElementInferrer<JvmField> eSetFlagFieldInferrer =
                      new JvmElementInferrer<JvmField>(X_LOW)
                      {
                        @Override
                        protected JvmField inferStructure()
                        {
                          JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, true, getJvmTypeReference("int", genFeature));
                          return settingDelegateField;
                        }
  
                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName(genFeature.getUpperName() + "_ESETFLAG");
                        }
                      };
                    associate(genFeature, eSetFlagFieldInferrer);
                    members.add(eSetFlagFieldInferrer.getInferredElement());
                  }
                  else
                  {
                    JvmElementInferrer<JvmField> eSetFlagFieldInferrer =
                      new JvmElementInferrer<JvmField>(X_LOW)
                      {
                        @Override
                        protected JvmField inferStructure()
                        {
                          JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PROTECTED, false, getJvmTypeReference("boolean", genFeature));
                          return settingDelegateField;
                        }
  
                        @Override
                        public void inferName()
                        {
                          inferredElement.setSimpleName(genFeature.getUncapName() + "ESet");
                        }
                      };
                    associate(genFeature, eSetFlagFieldInferrer);
                    members.add(eSetFlagFieldInferrer.getInferredElement());
                  }
                }
              }
            }
          }

          if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty())
          {
            // private static final int <%=genClass.getOffsetCorrectionField(null)%>
          }

          if (isImplementation && !genModel.isReflectiveDelegation())
          {
            for (final GenFeature genFeature : genClass.getImplementedGenFeatures())
            {
              final GenFeature reverseFeature = genFeature.getReverse();
              if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection())
              {
                // private static final int <%=genClass.getOffsetCorrectionField(genFeature)%>
                JvmElementInferrer<JvmField> offsetCorrectionFieldInferrer =
                  new JvmElementInferrer<JvmField>(X_LOW)
                  {
                    @Override
                    protected JvmField inferStructure()
                    {
                      JvmField settingDelegateField = createJvmField(genFeature, JvmVisibility.PRIVATE, true, getJvmTypeReference("int", genFeature));
                      return settingDelegateField;
                    }

                    @Override
                    public void inferName()
                    {
                      inferredElement.setSimpleName(genClass.getOffsetCorrectionField(genFeature));
                    }
                  };
                associate(genFeature, offsetCorrectionFieldInferrer);
                members.add(offsetCorrectionFieldInferrer.getInferredElement());
              }
            }
          }

          if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty())
          {
            // private static final int "EOPERATION_OFFSET_CORRECTION";
          }

          for (GenFeature genFeature : isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())
          {
            EStructuralFeature eStructuralFeature = genFeature.getEcoreFeature();
            if (eStructuralFeature.getName() != null && eStructuralFeature.getEGenericType() != null)
            {
              members.addAll(getJvmFeatureAccessors(genClass, genFeature, isInterface, isImplementation));
            }
          }

          for (GenOperation genOperation : isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())
          {
            members.add(getJvmOperation(genClass, genOperation, isInterface, isImplementation));
          }
        }

        @Override
        public void inferName()
        {
          if (isImplementation)
          {
            inferredElement.setSimpleName(genClass.getClassName());
            inferredElement.setPackageName(genClass.getGenPackage().getClassPackageName());

          }
          else
          {
            inferredElement.setSimpleName(genClass.getInterfaceName());
            inferredElement.setPackageName(genClass.getGenPackage().getInterfacePackageName());
          }
        }
      };

    associate(genClass, modelClassInferrer);
    return modelClassInferrer.getInferredElement();
  }

  protected List<JvmOperation> getJvmFeatureAccessors(final GenClass genClass, final GenFeature genFeature, final boolean isInterface, final boolean isImplementation)
  {
    List<JvmOperation> result = new ArrayList<JvmOperation>();

    GenModel genModel = genFeature.getGenModel();

    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType())
    {
      // public <%=genFeature.getListItemType(genClass)%>[] <%=genFeature.getGetArrayAccessor()%>()
      // public <%=genFeature.getListItemType(genClass)%> get<%=genFeature.getAccessorName()%>(int index)
      // public int get<%=genFeature.getAccessorName()%>Length()
      // public void set<%=genFeature.getAccessorName()%>(<%=genFeature.getListItemType(genClass)%>[] new<%=genFeature.getCapName()%>)
      // public void set<%=genFeature.getAccessorName()%>(int index, <%=genFeature.getListItemType(genClass)%> element)
    }

    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility()))
    {
      JvmElementInferrer<JvmOperation> getAccessorInferrer =
        new JvmElementInferrer<JvmOperation>(isInterface ? X_VERY_HIGH : X_HIGH)
        {
          @Override
          protected JvmOperation inferStructure()
          {
            JvmOperation jvmOperation = createJvmOperation(genFeature, JvmVisibility.PUBLIC, false,  getJvmTypeReference(genFeature.getType(genFeature.getGenClass()), genFeature));
            return jvmOperation;
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName(genFeature.getGetAccessor() + (isImplementation && genClass.hasCollidingGetAccessorOperation(genFeature) ? "_" : ""));
          }
        };
      associate(genFeature, getAccessorInferrer);
      result.add(getAccessorInferrer.getInferredElement());
    }

    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet())
    {
      JvmElementInferrer<JvmOperation> basicGetAccessorInferrer =
        new JvmElementInferrer<JvmOperation>(X_MEDIUM)
        {
          @Override
          protected JvmOperation inferStructure()
          {
            JvmOperation jvmOperation = createJvmOperation(genFeature, JvmVisibility.PUBLIC, false,  getJvmTypeReference(genFeature.getType(genFeature.getGenClass()), genFeature));
            return jvmOperation;
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName("basicGet" + genFeature.getAccessorName());
          }
        };
      associate(genFeature, basicGetAccessorInferrer);
      result.add(basicGetAccessorInferrer.getInferredElement());
    }

    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet())
    {
      JvmElementInferrer<JvmOperation> basicSetAccessorInferrer =
        new JvmElementInferrer<JvmOperation>(X_MEDIUM)
        {
          @Override
          protected JvmOperation inferStructure()
          {
            JvmOperation jvmOperation = createJvmOperation(genFeature, JvmVisibility.PUBLIC, false,  getJvmTypeReference("org.eclipse.emf.common.notify.NotificationChain", genFeature));
            EList<JvmFormalParameter> parameters = jvmOperation.getParameters();
            JvmElementInferrer<JvmFormalParameter> valueParameterInferrefer =
              new JvmElementInferrer<JvmFormalParameter>(X_MEDIUM)
              {
                @Override
                protected JvmFormalParameter inferStructure()
                {
                  return createJvmFormalParameter(genFeature, getJvmTypeReference(genFeature.getImportedInternalType(genClass), genFeature));
                }

                @Override
                public void inferName()
                {
                  inferredElement.setName("new" + genFeature.getCapName());
                }
              };
            associate(genFeature, valueParameterInferrefer);
            parameters.add(valueParameterInferrefer.getInferredElement());
            parameters.add(createJvmFormalParameter(genFeature, "msgs", getJvmTypeReference("org.eclipse.emf.common.notify.NotificationChain", genFeature)));
            return jvmOperation;
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName("basicSet" + genFeature.getAccessorName());
          }
        };
      associate(genFeature, basicSetAccessorInferrer);
      result.add(basicSetAccessorInferrer.getInferredElement());
    }

    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility()))
    {
      JvmElementInferrer<JvmOperation> setAccessorInferrer =
        new JvmElementInferrer<JvmOperation>(isInterface ? X_VERY_HIGH : X_HIGH)
        {
          @Override
          protected JvmOperation inferStructure()
          {
            JvmOperation jvmOperation = createJvmOperation(genFeature, JvmVisibility.PUBLIC, false,  getJvmTypeReference("void", genFeature));
            JvmElementInferrer<JvmFormalParameter> valueParameterInferrefer =
              new JvmElementInferrer<JvmFormalParameter>(X_MEDIUM)
              {
                @Override
                protected JvmFormalParameter inferStructure()
                {
                  return createJvmFormalParameter(genFeature, getJvmTypeReference(genFeature.getImportedInternalType(genClass), genFeature));
                }

                @Override
                public void inferName()
                {
                  inferredElement.setName(isImplementation ? "new" + genFeature.getCapName() : "value");
                }
              };
            if (isImplementation)
            {
              associate(genFeature, valueParameterInferrefer);
            }
            jvmOperation.getParameters().add(valueParameterInferrefer.getInferredElement());
            return jvmOperation;
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName("set" + genFeature.getAccessorName()  + (isImplementation && genClass.hasCollidingSetAccessorOperation(genFeature) ? "_" : ""));
          }
        };
      associate(genFeature, setAccessorInferrer);
      result.add(setAccessorInferrer.getInferredElement());
    }

    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset())
    {
      JvmElementInferrer<JvmOperation> basicSetAccessorInferrer =
        new JvmElementInferrer<JvmOperation>(X_MEDIUM)
        {
          @Override
          protected JvmOperation inferStructure()
          {
            JvmOperation jvmOperation = createJvmOperation(genFeature, JvmVisibility.PUBLIC, false,  getJvmTypeReference("org.eclipse.emf.common.notify.NotificationChain", genFeature));
            EList<JvmFormalParameter> parameters = jvmOperation.getParameters();
            parameters.add(createJvmFormalParameter(genFeature, "msgs", getJvmTypeReference("org.eclipse.emf.common.notify.NotificationChain", genFeature)));
            return jvmOperation;
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName("basicUnset" + genFeature.getAccessorName());
          }
        };
      associate(genFeature, basicSetAccessorInferrer);
      result.add(basicSetAccessorInferrer.getInferredElement());
    }

    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility()))
    {
      JvmElementInferrer<JvmOperation> unsetAccessorInferrer =
        new JvmElementInferrer<JvmOperation>(isInterface ? X_VERY_HIGH : X_HIGH)
        {
          @Override
          protected JvmOperation inferStructure()
          {
            JvmOperation jvmOperation = createJvmOperation(genFeature, JvmVisibility.PUBLIC, false,  getJvmTypeReference("void", genFeature));
            return jvmOperation;
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName("unset" + genFeature.getAccessorName() + (isImplementation && genClass.hasCollidingUnsetAccessorOperation(genFeature) ? "_" : ""));
          }
        };
      associate(genFeature, unsetAccessorInferrer);
      result.add(unsetAccessorInferrer.getInferredElement());
    }

    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility()))
    {
      JvmElementInferrer<JvmOperation> isSetAccessorInferrer =
        new JvmElementInferrer<JvmOperation>(isInterface ? X_VERY_HIGH : X_HIGH)
        {
          @Override
          protected JvmOperation inferStructure()
          {
            JvmOperation jvmOperation = createJvmOperation(genFeature, JvmVisibility.PUBLIC, false, getJvmTypeReference("boolean", genFeature));
            return jvmOperation;
          }

          @Override
          public void inferName()
          {
            inferredElement.setSimpleName("isSet" + genFeature.getAccessorName() + (isImplementation && genClass.hasCollidingIsSetAccessorOperation(genFeature) ? "_" : ""));
          }
        };
      associate(genFeature, isSetAccessorInferrer);
      result.add(isSetAccessorInferrer.getInferredElement());
    }

    return result;
  }

  protected JvmOperation getJvmOperation(final GenClass genClass, final GenOperation genOperation, boolean isInterface, boolean isImplementation)
  {
    if (isImplementation) {
      if (genOperation.isInvariant() && genOperation.hasInvariantExpression())
      {
        // protected static final <%=genModel.getImportedName("java.lang.String")%> <%=CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale())%>__EEXPRESSION
      }
      else if (genOperation.hasInvocationDelegate())
      {
        // protected static final <%=genModel.getImportedName("org.eclipse.emf.ecore.EOperation")%>.Internal.InvocationDelegate <%=CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale())%>__EINVOCATION_DELEGATE = ((<%=genModel.getImportedName("org.eclipse.emf.ecore.EOperation")%>.Internal)<%=genOperation.getQualifiedOperationAccessor()%>).getInvocationDelegate();
      }
    }
    JvmElementInferrer<JvmOperation> operationInferrer =
      new JvmElementInferrer<JvmOperation>(X_HIGH)
      {
        @Override
        protected JvmOperation inferStructure()
        {
          JvmOperation jvmOperation = createJvmOperation(genOperation, JvmVisibility.PUBLIC, false, getJvmTypeReference(genOperation.getType(genClass), genClass));
          populateTypeParameters(genOperation.getTypeParameters(genClass), genOperation.getGenTypeParameters(), jvmOperation.getTypeParameters());
          EList<JvmFormalParameter> parameters = jvmOperation.getParameters();
          for (GenParameter genParameter : genOperation.getGenParameters())
          {
            parameters.add(getJvmFormalParameter(genParameter));
          }
          EList<JvmTypeReference> exceptions = jvmOperation.getExceptions();
          for (EGenericType eGenericException : genOperation.getEcoreOperation().getEGenericExceptions())
          {
            exceptions.add(getJvmTypeReference(eGenericException, genOperation));
          }
          return jvmOperation;
        }

        @Override
        public void inferName()
        {
          inferredElement.setSimpleName(genOperation.getName());
        }
      };
    associate(genOperation, operationInferrer);
    return operationInferrer.getInferredElement();
  }

  protected JvmFormalParameter getJvmFormalParameter(final GenParameter genParameter)
  {
    JvmElementInferrer<JvmFormalParameter> parameterInferrer =
      new JvmElementInferrer<JvmFormalParameter>(X_HIGH)
      {
        @Override
        protected JvmFormalParameter inferStructure()
        {
          JvmFormalParameter jvmFormalParameter = createJvmFormalParameter(genParameter,  getJvmTypeReference(genParameter.getType(genParameter.getGenOperation().getGenClass()), genParameter));
          return jvmFormalParameter;
        }

        @Override
        public void inferName()
        {
          inferredElement.setName(genParameter.getName());
        }
      };
    associate(genParameter, parameterInferrer);
    return parameterInferrer.getInferredElement();
  }

  protected static final String TYPE_PARAMETER_REFERENCE_SCHEME = "type_parameter_reference";
  protected static final URI TYPE_PARAMETER_REFERENCE_SCHEME_BASE_URI = URI.createURI(TYPE_PARAMETER_REFERENCE_SCHEME + "://");

  protected JvmTypeReference getJvmTypeReference(String instanceTypeName, EObject context)
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
      if ("void".equals(instanceTypeName) || CodeGenUtil.isJavaPrimitiveType(instanceTypeName))
      {
        JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
        JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
        proxyUriConverter.installProxyURI(context.eResource().getURI(), jvmGenericType, QualifiedName.create(instanceTypeName));
        jvmParameterizedTypeReference.setType(jvmGenericType);
        return jvmParameterizedTypeReference;
      }
      else if ("?".equals(instanceTypeName))
      {
        JvmWildcardTypeReference jvmWildcardTypeReference = TypesFactory.eINSTANCE.createJvmWildcardTypeReference();
        return jvmWildcardTypeReference;
      }
      else if (instanceTypeName.indexOf('.') == -1)
      {
        JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
        JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
        jvmGenericType.setSimpleName(instanceTypeName);
        ((InternalEObject)jvmGenericType).eSetProxyURI(TYPE_PARAMETER_REFERENCE_SCHEME_BASE_URI.appendFragment(instanceTypeName));
        jvmParameterizedTypeReference.setType(jvmGenericType);
        return jvmParameterizedTypeReference;
      }
      else
      {
        QualifiedName qualifiedName = nameConverter.toQualifiedName(instanceTypeName);
        JvmParameterizedTypeReference jvmParameterizedTypeReference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
        JvmGenericType jvmGenericType = TypesFactory.eINSTANCE.createJvmGenericType();
        proxyUriConverter.installProxyURI(context.eResource().getURI(), jvmGenericType, qualifiedName);
        jvmParameterizedTypeReference.setType(jvmGenericType);
        return jvmParameterizedTypeReference;
      }
    }
  }

  protected JvmTypeReference getJvmTypeReference(EGenericType eGenericType, EObject context)
  {
    EClassifier eClassifier = eGenericType.getEClassifier();
    if (eClassifier == null)
    {
      ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
      if (eTypeParameter == null)
      {
        JvmWildcardTypeReference jvmWildcardTypeReference = TypesFactory.eINSTANCE.createJvmWildcardTypeReference();
        EGenericType eLowerBound = eGenericType.getELowerBound();
        if (eLowerBound != null)
        {
          JvmLowerBound jvmLowerBound = TypesFactory.eINSTANCE.createJvmLowerBound();
          jvmLowerBound.setTypeReference(getJvmTypeReference(eLowerBound, context));
          jvmWildcardTypeReference.getConstraints().add(jvmLowerBound);
        }
        EGenericType eUpperBound = eGenericType.getEUpperBound();
        if (eUpperBound != null)
        {
          JvmUpperBound jvmUpperBound = TypesFactory.eINSTANCE.createJvmUpperBound();
          jvmUpperBound.setTypeReference(getJvmTypeReference(eUpperBound, context));
          jvmWildcardTypeReference.getConstraints().add(jvmUpperBound);
        }
        return jvmWildcardTypeReference;
      }
      else
      {
        return getJvmTypeReference(eTypeParameter.getName(), context);
      }
    }
    else
    {
      List<JvmTypeReference> arguments = getJvmTypeReferences(eGenericType.getETypeArguments(), context);
      String instanceTypeName = eClassifier.getInstanceTypeName();
      JvmTypeReference jvmTypeReference =
          instanceTypeName == null ?
             getJvmTypeReference((GenClassifier)mapper.getGen(mapper.getToXcoreMapping(eClassifier).getXcoreElement())):
             getJvmTypeReference(instanceTypeName, context);
      if (jvmTypeReference instanceof JvmParameterizedTypeReference)
      {
        ((JvmParameterizedTypeReference)jvmTypeReference).getArguments().addAll(arguments);
      }
      return jvmTypeReference;
    }
  }

  protected JvmTypeReference getJvmTypeReference(GenClassifier genClassifier)
  {
    if (genClassifier == null)
    {
      return null;
    }
    else
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
      return getJvmTypeReference(instanceTypeName, genClassifier);
    }
  }

  protected List<JvmTypeReference> getJvmTypeReferences(List<EGenericType> eGenericTypes, EObject context)
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

  protected JvmFormalParameter createJvmFormalParameter(EObject context, JvmTypeReference jvmTypeReference)
  {
    JvmFormalParameter jvmFormalParameter = TypesFactory.eINSTANCE.createJvmFormalParameter();
    jvmFormalParameter.setParameterType(jvmTypeReference);
    return jvmFormalParameter;
  }

  protected JvmFormalParameter createJvmFormalParameter(EObject context, String name, JvmTypeReference jvmTypeReference)
  {
    JvmFormalParameter jvmFormalParameter = createJvmFormalParameter(context, jvmTypeReference);
    jvmFormalParameter.setName(name);
    return jvmFormalParameter;
  }

  protected JvmField createJvmField(EObject context, JvmVisibility jvmVisibility, boolean isStatic, JvmTypeReference jvmTypeReference)
  {
    JvmField jvmField = TypesFactory.eINSTANCE.createJvmField();
    jvmField.setVisibility(JvmVisibility.PUBLIC);
    jvmField.setStatic(isStatic);
    jvmField.setType(jvmTypeReference);
    return jvmField;
  }

  protected JvmField createJvmField(EObject context, JvmVisibility jvmVisibility, boolean isStatic, String name, JvmTypeReference jvmTypeReference)
  {
    JvmField jvmField = createJvmField(context, jvmVisibility, isStatic, jvmTypeReference);
    jvmField.setSimpleName(name);
    return jvmField;
  }

  protected JvmOperation createJvmOperation(EObject context, JvmVisibility jvmVisibility, boolean isStatic, JvmTypeReference jvmTypeReference)
  {
    JvmOperation jvmOperation = TypesFactory.eINSTANCE.createJvmOperation();
    jvmOperation.setVisibility(JvmVisibility.PUBLIC);
    jvmOperation.setStatic(isStatic);
    jvmOperation.setReturnType(jvmTypeReference);
    return jvmOperation;
  }

  protected JvmOperation createJvmOperation(EObject context, JvmVisibility jvmVisibility, boolean isStatic, String name, JvmTypeReference jvmTypeReference)
  {
    JvmOperation jvmOperation = createJvmOperation(context, jvmVisibility, isStatic, jvmTypeReference);
    jvmOperation.setSimpleName(name);
    return jvmOperation;
  }

  protected void populateTypeParameters(String typeParameters, EList<GenTypeParameter> genTypeParameters,  EList<JvmTypeParameter> jvmTypeParameters)
  {
    if (!genTypeParameters.isEmpty())
    {
      if (typeParameters != null && !"".equals(typeParameters))
      {
        // For operations, we need to ensure that we use the type-substituted result.
        //
        Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseTypeParameterList(typeParameters);
        @SuppressWarnings("unchecked")
        List<ETypeParameter> typeParameterList = (List<ETypeParameter>)diagnostic.getData().get(0);
        if (typeParameterList.size() == genTypeParameters.size())
        {
          int i = 0;
          for (GenTypeParameter genTypeParameter : genTypeParameters)
          {
            jvmTypeParameters.add(getJvmTypeParameter(typeParameterList.get(i), genTypeParameter));
            ++i;
          }
        }
      }
      else
      {
        for (GenTypeParameter genTypeParameter : genTypeParameters)
        {
          jvmTypeParameters.add(getJvmTypeParameter(genTypeParameter.getEcoreTypeParameter(), genTypeParameter));
        }
      }
    }
  }

  protected JvmTypeParameter getJvmTypeParameter(final ETypeParameter eTypeParameter, final GenTypeParameter genTypeParameter)
  {
    JvmElementInferrer<JvmTypeParameter> typeParameterInferrer =
      new JvmElementInferrer<JvmTypeParameter>(X_HIGH)
      {
        @Override
        protected JvmTypeParameter inferStructure()
        {
          JvmTypeParameter jvmTypeParameter = TypesFactory.eINSTANCE.createJvmTypeParameter();
          EList<JvmTypeConstraint> constraints = jvmTypeParameter.getConstraints();
          ETypeParameter eTypeParameter = genTypeParameter.getEcoreTypeParameter();
          for (EGenericType eBound : eTypeParameter.getEBounds())
          {
            JvmTypeReference jvmTypeReference = getJvmTypeReference(eBound, genTypeParameter);
            if (!(jvmTypeReference instanceof JvmWildcardTypeReference))
            {
              JvmUpperBound jvmUpperBound = TypesFactory.eINSTANCE.createJvmUpperBound();
              jvmUpperBound.setTypeReference(jvmTypeReference);
              constraints.add(jvmUpperBound);
            }
          }
          return jvmTypeParameter;
        }

        @Override
        public void inferName()
        {
          inferredElement.setName(genTypeParameter.getName());
        }
      };
    associate(genTypeParameter, typeParameterInferrer);
    return typeParameterInferrer.getInferredElement();
  }


  protected void associate(GenBase genBase, JvmElementInferrer<? extends JvmIdentifiableElement> jvmElementInferrer)
  {
    InferenceAdapter inferenceAdapter = (InferenceAdapter)EcoreUtil.getAdapter(genBase.eAdapters(), InferenceAdapter.class);
    if (inferenceAdapter == null)
    {
        inferenceAdapter = new InferenceAdapter();
        genBase.eAdapters().add(inferenceAdapter);
    }

    JvmIdentifiableElement inferredElement = jvmElementInferrer.getInferredElement();
    ToXcoreMapping toXcoreMapping = mapper.getToXcoreMapping(inferredElement);
    XNamedElement xNamedElement = toXcoreMapping.getXcoreElement();
    if (xNamedElement == null)
    {
      xNamedElement = (XNamedElement)mapper.getXcoreElement(genBase);
      toXcoreMapping.setXcoreElement(xNamedElement);
    }

    int priority = jvmElementInferrer.priority;
    for (int i = 0, size = inferenceAdapter.jvmElementInferrers.size(); i < size; ++i)
    {
      if (inferenceAdapter.jvmElementInferrers.get(i).priority > priority)
      {
        inferenceAdapter.jvmElementInferrers.add(i, jvmElementInferrer);
        return;
      }
    }
    inferenceAdapter.jvmElementInferrers.add(jvmElementInferrer);
  }
}
