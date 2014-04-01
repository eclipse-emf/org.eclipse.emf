/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimePlatform;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#isImage <em>Image</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#isDynamic <em>Dynamic</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getEcoreClass <em>Ecore Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getGenFeatures <em>Gen Features</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getGenOperations <em>Gen Operations</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getLabelFeature <em>Label Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenClassImpl extends GenClassifierImpl implements GenClass
{
  /**
   * The default value of the '{@link #getProvider() <em>Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvider()
   * @generated
   * @ordered
   */
  protected static final GenProviderKind PROVIDER_EDEFAULT = GenProviderKind.SINGLETON_LITERAL;

  /**
   * The cached value of the '{@link #getProvider() <em>Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvider()
   * @generated
   * @ordered
   */
  protected GenProviderKind provider = PROVIDER_EDEFAULT;

  /**
   * The default value of the '{@link #isImage() <em>Image</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isImage()
   * @generated
   * @ordered
   */
  protected static final boolean IMAGE_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isImage() <em>Image</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isImage()
   * @generated
   * @ordered
   */
  protected boolean image = IMAGE_EDEFAULT;

  /**
   * The default value of the '{@link #isDynamic() <em>Dynamic</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDynamic()
   * @generated
   * @ordered
   */
  protected static final boolean DYNAMIC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDynamic() <em>Dynamic</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDynamic()
   * @generated
   * @ordered
   */
  protected boolean dynamic = DYNAMIC_EDEFAULT;

  /**
   * The cached value of the '{@link #getEcoreClass() <em>Ecore Class</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreClass()
   * @generated
   * @ordered
   */
  protected EClass ecoreClass;

  /**
   * The cached value of the '{@link #getGenFeatures() <em>Gen Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenFeatures()
   * @generated
   * @ordered
   */
  protected EList<GenFeature> genFeatures;

  /**
   * The cached value of the '{@link #getGenOperations() <em>Gen Operations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenOperations()
   * @generated
   * @ordered
   */
  protected EList<GenOperation> genOperations;

  /**
   * The cached value of the '{@link #getLabelFeature() <em>Label Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabelFeature()
   * @generated
   * @ordered
   */
  protected GenFeature labelFeature;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenClassImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return GenModelPackage.Literals.GEN_CLASS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenProviderKind getProvider()
  {
    return provider;
  }

  public boolean isProviderSingleton()
  {
    return provider == GenProviderKind.SINGLETON_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProvider(GenProviderKind newProvider)
  {
    GenProviderKind oldProvider = provider;
    provider = newProvider == null ? PROVIDER_EDEFAULT : newProvider;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__PROVIDER, oldProvider, provider));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isImage()
  {
    return image;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImage(boolean newImage)
  {
    boolean oldImage = image;
    image = newImage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__IMAGE, oldImage, image));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDynamic()
  {
    return dynamic;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDynamic(boolean newDynamic)
  {
    boolean oldDynamic = dynamic;
    dynamic = newDynamic;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__DYNAMIC, oldDynamic, dynamic));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEcoreClass()
  {
    if (ecoreClass != null && ecoreClass.eIsProxy())
    {
      InternalEObject oldEcoreClass = (InternalEObject)ecoreClass;
      ecoreClass = (EClass)eResolveProxy(oldEcoreClass);
      if (ecoreClass != oldEcoreClass)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_CLASS__ECORE_CLASS, oldEcoreClass, ecoreClass));
      }
    }
    return ecoreClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass basicGetEcoreClass()
  {
    return ecoreClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreClass(EClass newEcoreClass)
  {
    EClass oldEcoreClass = ecoreClass;
    ecoreClass = newEcoreClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__ECORE_CLASS, oldEcoreClass, ecoreClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenFeature> getGenFeatures()
  {
    if (genFeatures == null)
    {
      genFeatures = new EObjectContainmentWithInverseEList<GenFeature>(GenFeature.class, this, GenModelPackage.GEN_CLASS__GEN_FEATURES, GenModelPackage.GEN_FEATURE__GEN_CLASS);
    }
    return genFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenOperation> getGenOperations()
  {
    if (genOperations == null)
    {
      genOperations = new EObjectContainmentWithInverseEList<GenOperation>(GenOperation.class, this, GenModelPackage.GEN_CLASS__GEN_OPERATIONS, GenModelPackage.GEN_OPERATION__GEN_CLASS);
    }
    return genOperations;
  }

  @Override
  public EClassifier getEcoreClassifier()
  {
    return getEcoreClass();
  }

  @Override
  protected EModelElement basicGetEcoreModelElement()
  {
    return ecoreClass;
  }

  @Override
  public String getImportedMetaType()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.EClass");
  }

  public String getInterfaceName()
  {
    return getGenModel().isSuppressInterfaces() ? getName() : getInterfaceName(getName());
  }

  public String getQualifiedInterfaceName()
  {
    return getInternalQualifiedInterfaceName().replace('$', '.');
  }

  protected String getInternalQualifiedInterfaceName()
  {
    return getInternalQualifiedInterfaceName(getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50);
  }

  protected String getInternalQualifiedInterfaceName(boolean includeTemplateArguments)
  {
    if (isDynamic())
    {
      GenClass genClass = getBaseGenClass();
      return 
        genClass == null ?
          "org.eclipse.emf.ecore.EObject" :
          ((GenClassImpl)genClass).getInternalQualifiedInterfaceName();
    }

    return getEcoreClass().getInstanceClassName() != null ?
      includeTemplateArguments ? getEcoreClass().getInstanceTypeName() : getEcoreClass().getInstanceClassName() :
      getGenPackage().getInterfacePackageName() + "." + getInterfaceName();
  }
  
  public String getRawImportedInstanceClassName()
  {
    return getRawImportedInterfaceName();
  }

  public String getRawInstanceClassName()
  {
    return getQualifiedInterfaceName();
  }

  public String getImportedInstanceClassName()
  {
    return getImportedInterfaceName();
  }

  public String getImportedInterfaceName()
  {
    return getGenModel().getImportedName(getInternalQualifiedInterfaceName());
  }

  public String getRawImportedInterfaceName()
  {
    return getGenModel().getImportedName(getInternalQualifiedInterfaceName(false));
  }

  public String getClassName()
  {
    return getGenModel().isSuppressInterfaces() ? getName() : getImplClassName(getName());
  }

  public String getQualifiedClassName()
  {
    return getGenPackage().getClassPackageName() + "." + getClassName();
  }

  public String getImportedClassName()
  {
    return getGenModel().getImportedName(getQualifiedClassName());
  }

  public List<GenClass> getBaseGenClasses()
  {
    return collectGenClasses(getEcoreClass().getESuperTypes(), null);
  }

  public List<GenClass> getAllBaseGenClasses()
  {
    return collectGenClasses(getEcoreClass().getEAllSuperTypes(), null);
  }

  public boolean isRawBaseClass(GenClass baseClass)
  {
    EClass eClass = baseClass.getEcoreClass();
    EList<ETypeParameter> eTypeParameters = eClass.getETypeParameters();
    if (!eTypeParameters.isEmpty())
    {
      for (EGenericType eGenericSuperType : getEcoreClass().getEGenericSuperTypes())
      {
        if (eGenericSuperType.getEClassifier() == eClass)
        {
          return eGenericSuperType.getETypeArguments().isEmpty();
        }
      }
    }
    return false;
  }

  public List<GenClass> getSwitchGenClasses()
  { 
    // Traverse the supertypes to find the maximum depths.
    // Exclude this class itself, which is handled separately in the template.
    //
    Map<EClass, Integer> maxDepths = new LinkedHashMap<EClass, Integer>();
    findMaxSuperTypeDepths(maxDepths, getEcoreClass(), 0);
    maxDepths.remove(getEcoreClass());

    // Order the results in a list of lists, indexed by maximum depth.
    // Exclude EObject, which is handled by the default case.
    //
    List<List<GenClass>> ordered = new ArrayList<List<GenClass>>();
    int resultSize = 0;
    for (Map.Entry<EClass, Integer> entry : maxDepths.entrySet())
    {
      GenClass genClass = findGenClass(entry.getKey());
      if (genClass != null && !genClass.isEObject())
      {
        int depth = entry.getValue();
        while (ordered.size() <= depth)
        {
          ordered.add(new ArrayList<GenClass>());
        }
        ordered.get(depth).add(genClass);
        resultSize++;
      }
    }

    // Traverse the list of lists to build a final ordered result.
    //
    List<GenClass> result = new ArrayList<GenClass>(resultSize);
    for (List<GenClass> genClasses : ordered)
    {
      result.addAll(genClasses);
    }
    return result;
  }

  private void findMaxSuperTypeDepths(Map<EClass, Integer> maxDepths, EClass eClass, int depth)
  {
    Integer existing = maxDepths.get(eClass);
    if (existing != null)
    {
      if (depth > existing)
      {
        maxDepths.put(eClass, depth);
      }
    }
    else
    {
      maxDepths.put(eClass, depth);
      for (EClass base : eClass.getESuperTypes())
      {
        findMaxSuperTypeDepths(maxDepths, base, depth + 1);
      }
    }
  }

  public void addClassPsuedoImports()
  {
    GenModel genModel = getGenModel();
    for (GenClass rootGenClass = this;;)
    {
      GenClass baseGenClass = rootGenClass.getBaseGenClass();
      if (baseGenClass == null)
      {
        String rootExtendsClass = rootGenClass.getGenModel().getRootExtendsClass();
        if ("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container".equals(rootExtendsClass) ||
                   "org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container$Dynamic".equals(rootExtendsClass))
        {
          genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container");
          genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container.Dynamic");
        }
        else if (!"org.eclipse.emf.ecore.impl.EObjectImpl".equals(rootExtendsClass))
        {
          genModel.addPseudoImport(rootExtendsClass + ".Container");
        }
        break;
      }
      else
      {
        rootGenClass = baseGenClass;
      }
    }
  }

  public GenClass getBaseGenClass()
  {
    List<EClass> s = getEcoreClass().getESuperTypes();
    return s.isEmpty() ? null : findGenClass(s.get(0));
  }

  public GenClass getClassExtendsGenClass()
  {
    GenClass base = getBaseGenClass();
    while (base != this)
    {
      if (base == null || !base.isInterface()) return base; 
      base = base.getBaseGenClass();
    }
    return null;
  }

  public String getClassExtends()
  {
    String result = getQualifiedClassExtends();
    return "".equals(result) ? "" : " extends " + getGenModel().getImportedName(result);
  }

  public String getQualifiedClassExtends()
  {
    GenClass extendsClass = getClassExtendsGenClass();
    if (extendsClass != null)
    {   
      StringBuilder result = new StringBuilder();
      result.append(extendsClass.getQualifiedClassName());
      if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
      {
        EClass extendsEClass = extendsClass.getEcoreClass();
        for (EGenericType eGenericType : getEcoreClass().getEAllGenericSuperTypes())
        {
          if (eGenericType.getEClassifier() == extendsEClass)
          {
            result.append(getTypeArguments(this, eGenericType.getETypeArguments(), false));
            break;
          }
        }
      }
      return result.toString();
    }
    else if (!isEObject())
    {
      String rootExtendsClass = getGenModel().getRootExtendsClass();
      if (!isBlank(rootExtendsClass))
      {
        return rootExtendsClass;
      }
    }
    return "";
  }

  public boolean needsRootImplementsInterfaceOperations()
  {
    if (!isMapEntry())
    {
      String rootImplementsInterface = getGenModel().getRootImplementsInterface();
      if (!isBlank(rootImplementsInterface))
      {
        GenClass extendsClass = getClassExtendsGenClass();

        // We assume that the rootExtendsClass already implements it.
        //
        if (extendsClass != null && !rootImplementsInterface.equals(extendsClass.getGenModel().getRootImplementsInterface()))
        {
          return true;
        }
      }
    }
    return false;
  }

  public List<String> getClassImplementsList()
  {
    List<String> result = new UniqueEList<String>();
    GenModel genModel = getGenModel();
    for (String classImplements : getQualifiedClassImplementsList())
    {
      result.add(genModel.getImportedName(classImplements));
    }
    return result;
  }

  public List<String> getQualifiedClassImplementsList()
  {
    List<String> result = new UniqueEList<String>();
    if (isMapEntry())
    {
      if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
      {
        result.add
          ("org.eclipse.emf.common.util.BasicEMap$Entry" + "<" + 
              getMapEntryKeyFeature().getQualifiedObjectType(this)+ ","  +
              getMapEntryValueFeature().getQualifiedObjectType(this) + ">");
      }
      else
      {
        result.add("org.eclipse.emf.common.util.BasicEMap$Entry");
      }
    }
    else
    {
      if (isExternalInterface() || !getGenModel().isSuppressInterfaces())
      {
        if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50 && !getGenTypeParameters().isEmpty())
        {
          StringBuilder stringBuilder = new StringBuilder(getInternalQualifiedInterfaceName());
          stringBuilder.append('<');
          for (Iterator<GenTypeParameter> i = getGenTypeParameters().iterator(); i.hasNext(); )
          {
            GenTypeParameter genTypeParameter = i.next();
            stringBuilder.append(genTypeParameter.getName());
            if (i.hasNext())
            {
              stringBuilder.append(", ");
            }
          }
          stringBuilder.append('>');
          result.add(stringBuilder.toString());
        }
        else
        {
          result.add(getInternalQualifiedInterfaceName());
        }
      }
      String rootImplementsInterface = getGenModel().getRootImplementsInterface();
      if (!isBlank(rootImplementsInterface))
      {
        GenClass extendsClass = getClassExtendsGenClass();

        // We assume that the rootExtendsClass already implements it.
        //
        if (extendsClass != null && !rootImplementsInterface.equals(extendsClass.getGenModel().getRootImplementsInterface()))
        {
          result.add(rootImplementsInterface);
        }
      }
    }

    if (getGenModel().isSuppressInterfaces())
    {
      List<String> interfaceExtends = getQualifiedInterfaceExtendsList();
      GenClassImpl classExtendsClass = (GenClassImpl)getClassExtendsGenClass();
      if (classExtendsClass != null)
      {
        interfaceExtends.removeAll(classExtendsClass.getQualifiedClassImplementsList());
      }
      result.addAll(interfaceExtends);
    }

    return result;
  }

  public String getClassImplements()
  {
    List<String> classImplements = getClassImplementsList();
    if (classImplements.isEmpty())
    {
      return "";
    }

    StringBuffer result = new StringBuffer(" implements ");
    for (Iterator<String> i = classImplements.iterator(); i.hasNext(); )
    {
      result.append(i.next());
      if (i.hasNext()) result.append(", ");
    } 
    return result.toString();
  }

  public boolean needsRootExtendsInterfaceExtendsTag()
  {
    String rootExtendsInterface = getGenModel().getRootExtendsInterface();
    if (rootExtendsInterface == null)
    {
      rootExtendsInterface = "";
    }
    if (isBlank(rootExtendsInterface) || getBaseGenClasses().isEmpty() && getGenPackage().isEcorePackage())
    {
      return false;
    }

    for (GenClass genClass : getAllBaseGenClasses())
    {
      if (genClass.getEcoreClass().getInstanceClassName() == null &&
            rootExtendsInterface.equals(genClass.getGenModel().getRootExtendsInterface()))
      {
        return false;
      }
    }

    return !rootExtendsInterface.equals("org.eclipse.emf.ecore.EObject");
  }

  public List<String> getInterfaceExtendsList()
  {
    List<String> result = new UniqueEList<String>();
    GenModel genModel = getGenModel();
    for (String interfaceExtends : getQualifiedInterfaceExtendsList())
    {
      result.add(genModel.getImportedName(interfaceExtends));
    }
    return result;
  }

  public List<String> getQualifiedInterfaceExtendsList()
  {
    List<String> result = new UniqueEList<String>();

    if (!isExternalInterface())
    {
      String rootExtendsInterface = getGenModel().getRootExtendsInterface();
      if (rootExtendsInterface == null)
      {
        rootExtendsInterface = "";
      } 
      if (getBaseGenClasses().isEmpty())
      {
        if (!isEObject() && !isBlank(rootExtendsInterface))
        {
          result.add(rootExtendsInterface);
        }
        return result;
      }

      boolean needsRootExtendsInterface = true;
      for (GenClass genClass : getAllBaseGenClasses())
      {
        if (genClass.getEcoreClass().getInstanceClassName() == null &&
              rootExtendsInterface.equals(genClass.getGenModel().getRootExtendsInterface()))
        {
          needsRootExtendsInterface = false;
          break;
        }
      }

      if (needsRootExtendsInterface && !isBlank(rootExtendsInterface))
      {
        result.add(rootExtendsInterface);
      }
    }

    boolean includeTypeArguments = getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    for (int i = 0, size = getBaseGenClasses().size(); i < size; i++)
    {
      GenClassImpl genClass = (GenClassImpl)getBaseGenClasses().get(i);
      EGenericType eGenericType = getEcoreClass().getEGenericSuperTypes().get(i);
      if (genClass.isExternalInterface() || genClass.isInterface() || !genClass.getGenModel().isSuppressInterfaces())
      {
        if (includeTypeArguments && !eGenericType.getETypeArguments().isEmpty())
        {
          result.add(genClass.getInternalQualifiedInterfaceName() + getTypeArguments(this, eGenericType.getETypeArguments(), false));
        }
        else
        {
          result.add(genClass.getInternalQualifiedInterfaceName());
        }
      }
    } 

    return result;
  }

  public String getInterfaceExtends()
  {
    List<String> interfaceExtends = getInterfaceExtendsList();
    if (interfaceExtends.isEmpty())
    {
      return "";
    }

    StringBuffer result = new StringBuffer(" extends ");
    for (Iterator<String> i = interfaceExtends.iterator(); i.hasNext(); )
    {
      result.append(i.next());
      if (i.hasNext()) result.append(", ");
    } 
    return result.toString();
  }
  
  public boolean hasGenericSuperTypes()
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      for (EGenericType eGenericType : getEcoreClass().getEGenericSuperTypes())
      {
        if (eGenericType.getETypeParameter() != null || !eGenericType.getETypeArguments().isEmpty())
        {
          return true;
        }
      }
    }
    return false;
  }

  public String getTypeParameters()
  {
    if (!getGenTypeParameters().isEmpty() && getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      StringBuilder result = new StringBuilder("<");
      for (Iterator<GenTypeParameter> i = getGenTypeParameters().iterator(); i.hasNext(); )
      {
        GenTypeParameter genTypeParameter = i.next();
        result.append(genTypeParameter.getName());
        List<EGenericType> eBounds = genTypeParameter.getEcoreTypeParameter().getEBounds();
        if (!eBounds.isEmpty())
        {
          result.append(" extends ");
          for (Iterator<EGenericType> j = genTypeParameter.getEcoreTypeParameter().getEBounds().iterator(); j.hasNext(); )
          {
            EGenericType eBound = j.next();
            result.append(getTypeArgument(this, eBound, true, false));
            if (j.hasNext())
            {
              result.append(" & ");
            }
          }
        }
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
      
      result.append("> ");
      return result.toString();
    }
    else
    {
      return "";
    }
  }

  public String getClassTypeArguments()
  {
    return getTypeArguments(false, false);
  }

  public String getInterfaceTypeArguments()
  {
    return getTypeArguments(true, false);
  }
  
  @Override
  public String getImportedWildcardInstanceClassName()
  {
    String result = getImportedInstanceClassName();
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      if (isMapEntry())
      {
        result += "<?, ?>";
      }
      else
      {
        result += getTypeArguments(false, true);
      }
    }
    return result;
  }

  public String getInterfaceWildTypeArguments()
  {
    return getTypeArguments(true, true);
  }
  
  protected String getTypeArguments(boolean isInterface, boolean isWild)
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      if (!getGenTypeParameters().isEmpty())
      {
        StringBuilder result = new StringBuilder("<");
        for (Iterator<GenTypeParameter> i = getGenTypeParameters().iterator(); i.hasNext(); )
        {
          GenTypeParameter genTypeParameter = i.next();
          result.append(isWild ? "?" : genTypeParameter.getName());
          if (i.hasNext())
          {
            result.append(", ");
          }
        }
        
        result.append('>');
        return result.toString();
      }
      else if (isMapEntry() && isInterface)
      {
        return "<" + getMapEntryKeyFeature().getObjectType(this) + ", " + getMapEntryValueFeature().getObjectType(this) + ">";
      }
    }
    return "";
  }

  public List<GenFeature> getAllGenFeatures()
  {
    return collectGenFeatures(getAllBaseGenClasses(), getGenFeatures(), null);
  }

  public List<GenFeature> getReifiedGenFeatures()
  {
    List<GenFeature> result = new ArrayList<GenFeature>();
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      GenClass classExtendsGenClass = getClassExtendsGenClass();
      if (classExtendsGenClass != null)
      {
        List<GenFeature> inheritedGenFeatures = classExtendsGenClass.getImplementedGenFeatures();
        GenModel genModel = getGenModel();
        for (GenFeature genFeature : inheritedGenFeatures)
        {
          if ((genFeature.isSet() || 
                 genFeature.isBasicSet() && !genModel.isReflectiveDelegation() || 
                 genFeature.isGet() && 
                   genFeature.isListType() && 
                   !genModel.isDynamicDelegation() && 
                   !genModel.isReflectiveDelegation() &&
                   !genFeature.hasSettingDelegate() &&
                   !genFeature.isVolatile()) &&
                isReifiedType(classExtendsGenClass, genFeature.getEcoreFeature().getEGenericType()))
          {
            result.add(genFeature);
          }
        }
      }
    }
    return result;
  }

  public boolean isReifiedType(GenClass sourceContext, EGenericType eGenericType)
  {
    String baseType = getTypeArgument(sourceContext, eGenericType, false, true);
    String reifiedType = getTypeArgument(this, eGenericType, false, true);
    return !baseType.equals(reifiedType);
  }

  public List<GenFeature> getInheritedGenFeatures()
  {
    return collectGenFeatures(getAllBaseGenClasses(), null, null);
  }

  public List<GenOperation> getAllGenOperations()
  {
    return getAllGenOperations(true);
  }

  public List<GenOperation> getAllGenOperations(boolean excludeOverrides)
  {
    return collectGenOperations(this, getAllBaseGenClasses(), getGenOperations(), null, excludeOverrides);
  }

  public String getFeatureID(GenFeature genFeature)
  {
    return getClassifierID() + "__" + format(genFeature.getName(), '_', null, false, false).toUpperCase(getGenModel().getLocale());
  }

  public String getQualifiedFeatureID(GenFeature genFeature)
  {
    return getGenPackage().getImportedPackageInterfaceName() + "." + getFeatureID(genFeature);
  }

  public String getOperationID(GenOperation genOperation)
  {
    return getOperationID(genOperation, true);
  }

  public String getFeatureValue(GenFeature genFeature)
  {
    List<GenFeature> allFeatures = getAllGenFeatures();
    int i = allFeatures.indexOf(genFeature);
    GenClass base = getBaseGenClass();

    if (base == null)
    {
      return Integer.toString(i);
    }

    int baseCount = base.getFeatureCount();    
    if (i < baseCount)
    {
      return getGenPackage() == base.getGenPackage() ?
        base.getFeatureID(genFeature) : base.getQualifiedFeatureID(genFeature);
    }

    String baseCountID = getGenPackage() == base.getGenPackage() ?
      base.getFeatureCountID() : base.getQualifiedFeatureCountID();
    return baseCountID + " + " + Integer.toString(i - baseCount);
  }

  public String getLocalFeatureIndex(GenFeature genFeature)
  {
    return Integer.toString(getEcoreClass().getEStructuralFeatures().indexOf(genFeature.getEcoreFeature()));
  }

  private class OperationHelper extends GenBaseImpl.UniqueNameHelper
  {
    @Override
    protected String getName(Object o)
    {
      GenOperation genOperation = (GenOperation)o;
      return genOperation.getCapName() + (genOperation.getGenParameters().size() > 0 ? "__" : "") + genOperation.getParameterTypes("_", false);
    }
  }

  private OperationHelper operationHelper = new OperationHelper();
  
  public void clearCache()
  {
    operationHelper = new OperationHelper();
    
    // Need to ensure that the cached names are computed in the same order and manner as they are in the generated package interface.
    //
    for (GenOperation genOperation : getAllGenOperations(false))
    {
      if (getOverrideGenOperation(genOperation) == null)
      {
        operationHelper.getUniqueName(genOperation);
      }
    }
  }

  public String getUniqueName(GenOperation genOperation)
  {
    return operationHelper.getUniqueName(genOperation);
  }

  public String getOperationID(GenOperation genOperation, boolean diagnosticCode)
  {
    if (diagnosticCode)
    {
      return getClassifierID() + "__" + format(genOperation.getName(), '_', null, false, false).toUpperCase(getGenModel().getLocale());
    }
    else
    {
      String uniqueName = getUniqueName(genOperation);
      return getClassifierID() + "___" + (format(genOperation.getName(), '_', null, false, false) + (genOperation.getGenParameters().size() > 0 ? uniqueName.substring(uniqueName.indexOf("__")) : "")).toUpperCase(getGenModel().getLocale());
    }
  }

  public String getQualifiedOperationID(GenOperation genOperation)
  {
    return getGenPackage().getImportedPackageInterfaceName() + "." + getOperationID(genOperation, false);
  }

  public String getOperationValue(GenOperation genOperation)
  {
    List<GenOperation> allOperations = getAllGenOperations(false);
    int i = allOperations.indexOf(genOperation);
    GenClass base = getBaseGenClass();

    if (base == null)
    {
      return Integer.toString(i);
    }

    int baseCount = base.getOperationCount();    
    if (i < baseCount)
    {
      return getGenPackage() == base.getGenPackage() ?
        base.getOperationID(genOperation, false) : base.getQualifiedOperationID(genOperation);
    }

    String baseCountID = getGenPackage() == base.getGenPackage() ?
      base.getOperationCountID() : base.getQualifiedOperationCountID();
    return baseCountID + " + " + Integer.toString(i - baseCount);
  }

  public String getLocalOperationIndex(GenOperation genOperation)
  {
    return Integer.toString(getEcoreClass().getEOperations().indexOf(genOperation.getEcoreOperation()));
  }

  public String getFlagsField(GenFeature genFeature)
  {
    if (isFlag(genFeature))
    {
      String flagsField = getImplementingGenModel(genFeature).getBooleanFlagsField();
      if (!isBlank(flagsField))
      {
        int flagIndex = getFlagIndex(genFeature);
        if (flagIndex / 32 > 0)
        {
          flagsField += String.valueOf(flagIndex / 32);
        }
        return flagsField;
      }
    }
    return null;
  }

  public int getFlagIndex(GenFeature genFeature)
  {
    if (isFlag(genFeature))
    {
      int reservedBooleanFlags = getImplementingGenModel(genFeature).getBooleanFlagsReservedBits();
      int index = reservedBooleanFlags > 0 ? reservedBooleanFlags : 0;

      for (GenFeature otherGenFeature : getAllGenFeatures())
      {
        if (isFlag(otherGenFeature))
        {
          // If the flag will straddle two fields, bump it to the next one.
          //
          int flagSize = getFlagSize(otherGenFeature);
          if (index / 32 != (index + flagSize - 1) / 32)
          {
            index = ((index / 32) + 1) * 32;
          }

          if (otherGenFeature.getEcoreFeature() == genFeature.getEcoreFeature())
          {
            return index;
          }

          index += flagSize;
        }
        if (isESetFlag(otherGenFeature))
        {
          index++;
        }
      }
    }
    return -1;
  }

  public int getFlagSize(GenFeature genFeature)
  {
    if (isFlag(genFeature))
    {
      if (genFeature.isBooleanType())
      {
        return 1;
      }
      else if (genFeature.isEnumType())
      {
        int choices = genFeature.getTypeGenEnum().getEcoreEnum().getELiterals().size();
        switch (choices)
        {
          case 0:
          case 1:
            return choices;
          default:
            int size = 0;
            for (choices--; choices >= 1; choices >>= 1)
            {
              size++;
            }
            return size;
        }
      }
    }
    return 0;
  }

  public String getFlagMask(GenFeature genFeature)
  {
    int size = getFlagSize(genFeature);
    if (size == 0)
    {
      return "0";
    }

    int mask = (1 << size) - 1;
    return mask == 1 ? "1" : "0x" + Integer.toHexString(mask);
  }

  public String getESetFlagsField(GenFeature genFeature)
  {
    if (isESetFlag(genFeature))
    {
      String isSetFlagsField = getImplementingGenModel(genFeature).getBooleanFlagsField();
      if (!isBlank(isSetFlagsField))
      {
        int isSetFlagIndex = getESetFlagIndex(genFeature);
        if (isSetFlagIndex / 32 > 0)
        {
          isSetFlagsField += String.valueOf(isSetFlagIndex / 32);
        }
        return isSetFlagsField;
      }
    }
    return null;
  }

  public int getESetFlagIndex(GenFeature genFeature)
  {
    if (isESetFlag(genFeature))
    {
      int reservedBooleanFlags = getImplementingGenModel(genFeature).getBooleanFlagsReservedBits();
      int index = reservedBooleanFlags > 0 ? reservedBooleanFlags : 0;

      for (GenFeature otherGenFeature : getAllGenFeatures())
      {
        if (isFlag(otherGenFeature))
        {
          // If the flag will straddle two fields, bump it to the next one.
          //
          int flagSize = getFlagSize(otherGenFeature);
          if (index / 32 != (index + flagSize - 1) / 32)
          {
            index = ((index / 32) + 1) * 32;
          }
          index += flagSize;
        }
        if (isESetFlag(otherGenFeature))
        {
          if (otherGenFeature.getEcoreFeature() == genFeature.getEcoreFeature())
          {
            return index;
          }
          index++;
        }
      }
    }
    return -1;
  }

  public String getFeatureCountID()
  {
    return getClassifierID() + "_FEATURE_COUNT";
  }

  public String getQualifiedFeatureCountID()
  {
    return getGenPackage().getImportedPackageInterfaceName() + "." + getFeatureCountID();
  }

  public String getFeatureCountValue()
  {
    GenClass base = getBaseGenClass();
    if (base == null)
    {
      return Integer.toString(getFeatureCount());
    }

    String baseCountID = getGenPackage() == base.getGenPackage() ?
      base.getFeatureCountID() : base.getQualifiedFeatureCountID();
    return baseCountID + " + " + Integer.toString(getFeatureCount() - base.getFeatureCount());
  }

  public int getFeatureCount()
  {
    return getAllGenFeatures().size();
  }

  public String getOperationCountID()
  {
    return getClassifierID() + "_OPERATION_COUNT";
  }

  public String getQualifiedOperationCountID()
  {
    return getGenPackage().getImportedPackageInterfaceName() + "." + getOperationCountID();
  }

  public String getOperationCountValue()
  {
    GenClass base = getBaseGenClass();
    if (base == null)
    {
      return Integer.toString(getOperationCount());
    }

    String baseCountID = getGenPackage() == base.getGenPackage() ?
      base.getOperationCountID() : base.getQualifiedOperationCountID();
    return baseCountID + " + " + Integer.toString(getOperationCount() - base.getOperationCount());
  }

  public int getOperationCount()
  {
    return getAllGenOperations(false).size();
  }

  public GenOperation getOverrideGenOperation(GenOperation genOperation)
  {
    List<GenOperation> allGenOperations = getAllGenOperations(false);
    int index = allGenOperations.indexOf(genOperation);
    if (index != -1)
    {
      for (int i = allGenOperations.size() - 1; i > index; --i)
      {
        GenOperation otherGenOperation = allGenOperations.get(i);
        if (otherGenOperation.isOverrideOf(this, genOperation))
        {
          return otherGenOperation;
        }
      }
    }
    return null;
  }

  public List<GenOperation> getOverrideGenOperations(final List<GenOperation> baseGenOperations, List<GenOperation> derivedGenOperations)
  {
    return collectGenOperations(this, null, derivedGenOperations, new GenOperationFilter()
    {
      public boolean accept(GenOperation genOperation)
      {
        for (GenOperation baseGenOperation : baseGenOperations)
        {
          if (genOperation.isOverrideOf(GenClassImpl.this, baseGenOperation))
          {
            return true;
          }
        }
        return false;
      }
    }, false);
  }

  public boolean isEObject()
  {
    return "EObject".equals(getName()) && getGenPackage().isEcorePackage();
  }

  public boolean isEObjectExtension()
  {
    if (isMapEntry())
    {
      return false;
    }
    else
    {
      if (isExternalInterface())
      {
        return false;
      }

      for (GenClass genClass : getAllBaseGenClasses())
      {
        if (genClass.isEObjectExtension())
        {
          return true;
        }
      }
  
      return getGenPackage().isEcorePackage() ||
        (!isExternalInterface() && "org.eclipse.emf.ecore.EObject".equals(getGenModel().getRootExtendsInterface()));
    }
  }

  public String getCastFromEObject()
  {
    String qualifiedInterfaceName = getQualifiedInterfaceName();
    return 
      !"org.eclipse.emf.ecore.EObject".equals(qualifiedInterfaceName) ?
      "(" + getGenModel().getImportedName(qualifiedInterfaceName) + ")" :
      "";
  }

  public boolean isAbstract()
  {
    // An interface should be abstract, but this makes sure of that fact.
    //
    return getEcoreClass().isAbstract() || getEcoreClass().isInterface();
  }

  public String getAbstractFlag()
  {
    String result = !isAbstract() ? "!" : "";
    return result + "IS_ABSTRACT";
  }

  public boolean isInterface()
  {
    return getEcoreClass().isInterface();
  }

  public String getInterfaceFlag()
  {
    String result = !getEcoreClass().isInterface() ? "!" : "";
    return result + "IS_INTERFACE";
  }

  public String getGeneratedInstanceClassFlag()
  {
    String result = isExternalInterface() || isDynamic() ? "!" : "";
    return result + "IS_GENERATED_INSTANCE_CLASS";
  }

  public boolean isExternalInterface()
  {
    EClass eClass = getEcoreClass();
    return 
      eClass.eIsSet(EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME) || 
        eClass.eIsSet(EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME);
  }

  public boolean isMapEntry()
  {
    return 
      isJavaUtilMapEntry(getEcoreClass().getInstanceClassName()) &&
      getEcoreClass().getEStructuralFeature("key") != null &&
      getEcoreClass().getEStructuralFeature("value") != null;
  }

  public GenFeature getMapEntryKeyFeature()
  {
    return findGenFeature(getEcoreClass().getEStructuralFeature("key"));
  }

  public GenFeature getMapEntryValueFeature()
  {
    return findGenFeature(getEcoreClass().getEStructuralFeature("value"));
  }

  public List<GenClass> getImplementedGenClasses()
  {
    List<GenClass> allBases = getAllBaseGenClasses();
    GenClass extendedBase = getClassExtendsGenClass();
    List<GenClass> result = 
      extendedBase == null ?
        new ArrayList<GenClass>(allBases) :
        new ArrayList<GenClass>(allBases.subList(allBases.indexOf(extendedBase) + 1, allBases.size()));
    result.add(this);
    return result;
  }

  public List<GenFeature> getImplementedGenFeatures()
  {
    return collectGenFeatures(getImplementedGenClasses(), null, null);
  }

  public GenModel getImplementingGenModel(GenFeature genFeature)
  {
    if (getImplementedGenFeatures().contains(genFeature))
    {
      return getGenModel();
    }
    else
    {
      GenClass classExtendsGenClass = getClassExtendsGenClass();
      return classExtendsGenClass == null ? genFeature.getGenModel() : classExtendsGenClass.getImplementingGenModel(genFeature);
    }
  }

  public List<GenOperation> getImplementedGenOperations()
  {
    EList<GenClass> implementedGenClasses = new UniqueEList<GenClass>(getImplementedGenClasses());
    ECollections.reverse(implementedGenClasses);
    if (needsRootImplementsInterfaceOperations())
    {
      GenClass rootImplementsInterface = getGenModel().getRootImplementsInterfaceGenClass();
      if (rootImplementsInterface != null)
      {
        List<GenClass> allBaseClasses = new UniqueEList<GenClass>(rootImplementsInterface.getAllBaseGenClasses());
        for (Iterator<GenClass> i = allBaseClasses.iterator(); i.hasNext(); )
        {
          GenClass genClass = i.next();
          if (genClass.isEObject())
          {
            i.remove();
          }
        }
        allBaseClasses.add(rootImplementsInterface);
        implementedGenClasses.addAll(allBaseClasses);
      }
    }
    return
      collectGenOperations
        (this,
         implementedGenClasses,
         null, 
         new CollidingGenOperationFilter());
  }

  public boolean hasImplementedToStringGenOperation()
  {
    for (GenOperation genOperation : getImplementedGenOperations())
    {
      if ("toString".equals(genOperation.getName()) && genOperation.getGenParameters().isEmpty())
      {
        return true;
      }
    }
    return false;
  }

  public List<GenClass> getExtendedGenClasses()
  {
    List<GenClass> allBases = getAllBaseGenClasses();
    GenClass extendedBase = getClassExtendsGenClass();
    int i = extendedBase == null ? 0 : allBases.indexOf(extendedBase) + 1;
    return new ArrayList<GenClass>(allBases.subList(0, i));
  }

  public List<GenFeature> getExtendedGenFeatures()
  {
    return collectGenFeatures(getExtendedGenClasses(), null, null);
  }

  public List<GenOperation> getExtendedGenOperations()
  {
    return
      collectGenOperations
        (this,
         getExtendedGenClasses(),
         null, 
         new CollidingGenOperationFilter());
  }

  public List<GenFeature> getDeclaredGenFeatures()
  {
    return getGenFeatures();
  }

  public List<GenOperation> getDeclaredGenOperations()
  {
    return
      collectGenOperations
        (this,
         Collections.<GenClass>singletonList(this),
         null, 
         new GenOperationFilter()
         {
           public boolean accept(GenOperation genOperation)
           {
             return !genOperation.isSuppressedVisibility();
           }
         });
  }

  public List<GenFeature> getFlagGenFeatures()
  {
    return collectGenFeatures(null, getImplementedGenFeatures(), new GenFeatureFilter()
      {
        public boolean accept(GenFeature genFeature)
        {
          return isFlag(genFeature);
        }
      });
  }

  public List<GenFeature> getFlagGenFeatures(final String staticDefaultValue)
  {
    return collectGenFeatures(null, getFlagGenFeatures(), new GenFeatureFilter()
      {
        public boolean accept(GenFeature genFeature)
        {
          return staticDefaultValue.equalsIgnoreCase(genFeature.getStaticDefaultValue());
        }
      });
  }

  public List<GenFeature> getFlagGenFeaturesWithDefault()
  {
    return collectGenFeatures(null, getFlagGenFeatures(), new GenFeatureFilter()
      {
        public boolean accept(GenFeature genFeature)
        {
          if (genFeature.hasEDefault())
          {
            if (genFeature.isBooleanType())
            {
              return "true".equals(genFeature.getStaticDefaultValue());
            }
            else if (genFeature.isEnumType())
            {
              EEnum eEnum = genFeature.getTypeGenEnum().getEcoreEnum();
              return eEnum.getELiterals().indexOf(eEnum.getEEnumLiteralByLiteral(genFeature.getEcoreFeature().getDefaultValueLiteral())) > 0;
            }
          }
          return false;
        }
      });
  }

  public List<GenFeature> getEGetGenFeatures()
  {
    return getGenModel().isMinimalReflectiveMethods() ? getImplementedGenFeatures() : getAllGenFeatures();
  }

  public List<GenFeature> getEIsSetGenFeatures()
  {
    return getEGetGenFeatures();
  }

  public List<GenFeature> getESetGenFeatures()
  {
    return 
      collectGenFeatures
        (null, 
         getGenModel().isMinimalReflectiveMethods() ? getImplementedGenFeatures() : getAllGenFeatures(), 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             return genFeature.isChangeable();
           }
         });
  }

  public List<GenFeature> getEUnsetGenFeatures()
  {
    return getESetGenFeatures();
  }

  public List<GenFeature> getEInverseAddGenFeatures()
  {
    return 
     collectGenFeatures
       (null, 
        getGenModel().isMinimalReflectiveMethods() ? getImplementedGenFeatures() : getAllGenFeatures(), 
        new GenFeatureFilter() 
        {
          public boolean accept(GenFeature genFeature) 
          {
            return genFeature.isBidirectional() && (!genFeature.isVolatile() || genFeature.hasDelegateFeature());
          }
        });
  }

  public List<GenFeature> getEInverseRemoveGenFeatures()
  {
    return 
      collectGenFeatures
        (null, 
         getGenModel().isMinimalReflectiveMethods() ? getImplementedGenFeatures() : getAllGenFeatures(), 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             return genFeature.isEffectiveContains() ||
               (genFeature.isBidirectional() &&
                  (!genFeature.getReverse().isVolatile() || genFeature.getReverse().hasDelegateFeature())) ||
               genFeature.isFeatureMapType();
           }
         });
  }

  public List<GenFeature> getEBasicRemoveFromContainerGenFeatures()
  {
    return 
      collectGenFeatures
        (null, 
         getGenModel().isMinimalReflectiveMethods() ? getImplementedGenFeatures() : getAllGenFeatures(), 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             return genFeature.isContainer(); 
           }
         });
  }

  public List<GenFeature> getToStringGenFeatures()
  {
    return 
      collectGenFeatures
        (getImplementedGenClasses(), 
         null, 
         new GenFeatureFilter()
         {
           public boolean accept(GenFeature genFeature)
           {
             return genFeature.isField() && !genFeature.isReferenceType();
           }
         });
  }

  public List<GenClass> getMixinGenClasses()
  {
    // Simple cases: no mix-ins for no inheritance or for a single base class.
    //
    List<EClass> superTypes = getEcoreClass().getESuperTypes();
    if (superTypes.isEmpty() || (superTypes.size() == 1 && !superTypes.get(0).isInterface()))
    {
      return Collections.emptyList();
    }

    List<GenClass> allBases = getAllBaseGenClasses();
    List<GenClass> result = new ArrayList<GenClass>(allBases.size());

    // If extending an interface, its mix-ins must be included, since there is no implementation to handle them.
    //
    GenClass baseGenClass = getBaseGenClass();
    if (baseGenClass.isInterface())
    {
      result.addAll(baseGenClass.getMixinGenClasses());
    }

    // Mix-ins are everything after the base class.
    //
    int i = allBases.indexOf(baseGenClass) + 1;
    result.addAll(allBases.subList(i, allBases.size()));
    return result;
  }

  public List<GenFeature> getMixinGenFeatures()
  {
      return collectGenFeatures(getMixinGenClasses(), null, null);  
  }

  public List<GenOperation> getMixinGenOperations()
  {
    return collectGenOperations(this, getMixinGenClasses(), null, new CollidingGenOperationFilter());
  }

  public void initialize(EClass eClass)
  {
    if (eClass != getEcoreClass())
    {
      setEcoreClass(eClass);

      if (getLabelFeatureGen() != null && getLabelFeatureGen().eIsProxy())
      {
        setLabelFeature(null);
      }

      setImage(!eClass.isAbstract());
    }
    
    List<ETypeParameter> typeParameters = eClass.getETypeParameters();
    LOOP:
    for (int i = 0; i < typeParameters.size(); ++i) 
    {
      ETypeParameter typeParameter = typeParameters.get(i);

      for (int j = 0; j < getGenTypeParameters().size(); ++j)
      {
        GenTypeParameter genTypeParameter = getGenTypeParameters().get(j);
        if (genTypeParameter.getEcoreTypeParameter() == typeParameter)
        {
          genTypeParameter.initialize(typeParameter);
          if (i != j)
          {
            getGenTypeParameters().move(i, j);
          }

          continue LOOP;
        }
      }

      GenTypeParameter genTypeParameter = getGenModel().createGenTypeParameter();
      getGenTypeParameters().add(i, genTypeParameter);
      genTypeParameter.initialize(typeParameter);
    }

    int localFeatureIndex = 0;
    LOOP:
    for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures())
    {
      if (eStructuralFeature instanceof EAttribute)
      {
        EAttribute attribute = (EAttribute)eStructuralFeature;
        for (GenFeature genFeature : getGenFeatures())
        {
          if (genFeature.getEcoreFeature() == attribute)
          {
            genFeature.initialize(attribute);
            getGenFeatures().move(localFeatureIndex++, genFeature);
            continue LOOP;
          }
        }

        GenFeature genFeature = getGenModel().createGenFeature();
        getGenFeatures().add(localFeatureIndex++, genFeature);
        genFeature.initialize(attribute);
      }
      else
      {
        EReference reference = (EReference)eStructuralFeature;
        for (GenFeature genFeature : getGenFeatures())
        {
          if (genFeature.getEcoreFeature() == reference)
          {
            genFeature.initialize(reference);
            getGenFeatures().move(localFeatureIndex++, genFeature);
            continue LOOP;
          }
        }

        GenFeature genFeature = getGenModel().createGenFeature();
        getGenFeatures().add(localFeatureIndex++, genFeature);
        genFeature.initialize(reference);
      }
    }
    
    int localOperationIndex = 0;
    OPERATION_LOOP:
    for (EOperation operation : eClass.getEOperations())
    {
      for (GenOperation genOperation : getGenOperations())
      {
        if (genOperation.getEcoreOperation() == operation)
        {
          genOperation.initialize(operation);
          getGenOperations().move(localOperationIndex++, genOperation);
          continue OPERATION_LOOP;
        }
      }

      GenOperation genOperation = getGenModel().createGenOperation();
      getGenOperations().add(localOperationIndex++, genOperation);
      genOperation.initialize(operation);
    }
  }

  @Override
  protected boolean hasModelContribution()
  {
    return true;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Override
  @Deprecated
  public void generate(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerate()) return;

      int fileCount = isInterface() ? 1 : 2;
      if (isExternalInterface()) fileCount--;

      progressMonitor.beginTask("", fileCount);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object [] { getFormattedName() }));

      if (!isExternalInterface() && (!getGenModel().isSuppressInterfaces() || isInterface()))
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaInterface_message", 
              new Object [] { getGenPackage().getInterfacePackageName() + "." + getInterfaceName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE, 
           getGenModel().getEffectiveModelPluginVariables(), 
           getGenModel().getModelDirectory(), 
           getGenPackage().getInterfacePackageName(), 
           getInterfaceName(), 
           getGenModel().getClassEmitter(),
           new Object [] { new Object [] { this, Boolean.TRUE, Boolean.FALSE }});
      }

      if (!isInterface())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedClassName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE, 
           getGenModel().getEffectiveModelPluginVariables(), 
           getGenModel().getModelDirectory(), 
           getGenPackage().getClassPackageName(), 
           getClassName(), 
           getGenModel().getClassEmitter(),
           new Object [] { new Object [] { this, getGenModel().isSuppressInterfaces() ? Boolean.TRUE : Boolean.FALSE, Boolean.TRUE }});
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  @Override
  public String getModelInfo()
  {
    EClass eClass = getEcoreClass();

    StringBuffer result = new StringBuffer();
    if (isMapEntry())
    {
      StringBuffer names = new StringBuffer();
      StringBuffer body = new StringBuffer();
      for (GenFeature genFeature : getGenFeatures())
      {
        appendLineBreak(body);
        body.append(genFeature.getQualifiedModelInfo());
        body.append(' ');
        names.append(genFeature.getEcoreFeature().getName());
        names.append(' ');
      }

      String features = names.toString().trim();
      if (!features.equals("key value"))
      {
        appendLineBreak(result);
        appendModelSetting(result, "features", features);
      }
      result.append(body);
    }
    else if (isExternalInterface())
    {
      appendModelSetting
        (result, 
         "instanceClass", 
         getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50 ? eClass.getInstanceClassName() : eClass.getInstanceTypeName());

      if (!getGenTypeParameters().isEmpty())
      {
        StringBuilder typeParameterNames = new StringBuilder();
        for (Iterator<GenTypeParameter> i = getGenTypeParameters().iterator(); i.hasNext(); )
        {
          typeParameterNames.append(i.next().getName());
          if (i.hasNext())
          {
            typeParameterNames.append(' ');
          }
        }
        appendModelSetting(result, "typeParameters", typeParameterNames.toString());
      }
    }
    else
    {
      if (getGenModel().isSuppressInterfaces())
      {
        appendModelSetting(result, "kind", "class");
      }

      if (isInterface())
      {
        appendModelSetting(result, "interface", "true");
      }
      if (isAbstract())
      {
        appendModelSetting(result, "abstract", "true");
      }

      StringBuffer suppressedNames = new StringBuffer();
      StringBuffer suppressedInfo = new StringBuffer();
      for (GenFeature genFeature : getGenFeatures())
      {
        if (genFeature.isSuppressedGetVisibility())
        {
          suppressedNames.append(genFeature.getName());
          suppressedNames.append(' ');
          appendLineBreak(suppressedInfo);
          suppressedInfo.append(genFeature.getQualifiedModelInfo());
          suppressedInfo.append(' ');
        }
      }

      if (suppressedNames.length() > 0)
      {
        appendLineBreak(result);
        appendModelSetting(result, "features", suppressedNames.toString().trim());
        result.append(suppressedInfo);
      }
    }

    if (hasReferenceToClassifierWithInstanceTypeName(eClass.getEGenericSuperTypes()))
    {
      StringBuilder superTypes = new StringBuilder();
      for (EGenericType eGenericType : eClass.getEGenericSuperTypes())
      {
        superTypes.append(getEcoreType(eGenericType));
        superTypes.append(' ');
      }
      appendModelSetting(result, "superTypes", superTypes.toString().trim());
    }
    
    for (GenTypeParameter genTypeParameter : getGenTypeParameters())
    {
      String info = genTypeParameter.getQualifiedModelInfo();
      if (info.length() != 0)
      {
        result.append(info);
        result.append(' ');
      }
    }

    appendAnnotationInfo(result, eClass);

    return result.toString().trim();
  }

  //
  // EMFEdit generation
  //

  public String getProviderClassName()
  {
    return getName() + "ItemProvider";
  }

  public String getQualifiedProviderClassName()
  {
    return getGenPackage().getProviderPackageName() + "." + getProviderClassName();
  }

  public String getImportedProviderClassName()
  {
    return getGenModel().getImportedName(getQualifiedProviderClassName());
  }

  public String getItemIconFileName()
  {
    GenModel genModel = getGenModel();
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      return genModel.getEditDirectory() + "/" + genModel.getEditPluginPackageName().replace(".", "/") + "/icons/full/obj16/" + 
        getName() + ".gif";
    }
    else
    {
      return genModel.getEditIconsDirectory() + "/full/obj16/" + getName() + ".gif";
    }
  }
  
  private static final Set<String> OBJECT_METHODS = 
    new HashSet<String>()
    {
      private static final long serialVersionUID = 1L;

      {
        this.add("clone");
        this.add("getClass");
        this.add("hashCode");
        this.add("finalize");
        this.add("notify");
        this.add("notifyAll");
        this.add("toString");
        this.add("wait");
      }
    };

  public String getItemIconAccessorName()
  {
    String result = safeName(getUncapName());
    if (OBJECT_METHODS.contains(result))
    {
      result += "_";
    }
    return result;
  }

  public String getCreateChildIconFileName(GenFeature feature, GenClass childClass)
  {
    return getCreateChildIconFileName(getGenModel(), feature, childClass);
  }

  public String getCreateChildIconFileName(GenModel genModel, GenFeature feature, GenClass childClass)
  {
    GenClass parentClass = feature.getGenClass();
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT)
    {
      return genModel.getEditDirectory() + "/" + genModel.getEditPluginPackageName().replace(".", "/") + "/icons/full/ctool16/" + 
        "Create" + parentClass.getName() + "_" + feature.getName() + "_" + childClass.getName() + ".gif";
    }
    else
    {
      return genModel.getEditIconsDirectory() + "/full/ctool16/" + 
        "Create" + parentClass.getName() + "_" + feature.getName() + "_" + childClass.getName() + ".gif";
    }
  }

  public GenClass getProviderExtendsGenClass()
  {
    GenClass baseClass = getClassExtendsGenClass();
    while (baseClass != null && 
             (baseClass.getProvider() == GenProviderKind.NONE_LITERAL ||
                !baseClass.getGenModel().hasEditSupport()))
    {
      baseClass = baseClass.getClassExtendsGenClass();
    }
    return baseClass;
  }

  public List<String> getProviderImplementsClassNames()
  {
    List<String> result = new ArrayList<String>(getGenPackage().getProviderSupportedTypes());
    GenClass classExtendsGenClass = getClassExtendsGenClass();
    if (classExtendsGenClass != null)
    {
      result.removeAll(classExtendsGenClass.getGenPackage().getProviderSupportedTypes());
    }
    return result;
  }

  public String getProviderBaseClassName()
  {
    GenClass baseClass = getProviderExtendsGenClass();
    return 
      baseClass != null ? 
        baseClass.getImportedProviderClassName() : 
          isBlank(getGenModel().getProviderRootExtendsClass()) ?
            null:
            getGenModel().getImportedName(getGenModel().getProviderRootExtendsClass());
  }

  public List<GenClass> getProviderImplementedGenClasses()
  {
    List<GenClass> allBases = getAllBaseGenClasses();
    GenClass extendedBase = getProviderExtendsGenClass();
    int i = extendedBase == null ? 0 : allBases.indexOf(extendedBase) + 1;
    List<GenClass> result = new ArrayList<GenClass>(allBases.subList(i, allBases.size()));
    result.add(this);
    return result;
  }

  protected List<GenFeature> getProviderImplementedGenFeatures()
  {
    return collectGenFeatures(getProviderImplementedGenClasses(), null, null);
  }

  public List<GenFeature> getLabelFeatureCandidates()
  {
    return 
      collectGenFeatures
        (getAllBaseGenClasses(), 
         getGenFeatures(), 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             return !genFeature.isReferenceType() && !genFeature.isListType() && !genFeature.isMapType();
           }
         });
  }

  public List<GenFeature> getPropertyFeatures()
  {
    return 
      collectGenFeatures
        (getProviderImplementedGenClasses(), 
         null, 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             //FB TBD filter out volatile and other inappropriate links?
             return genFeature.isProperty();
           }
         });
  }

  public List<GenFeature> getNotifyFeatures()
  {
    return 
     collectGenFeatures
       (getProviderImplementedGenClasses(), 
        null, 
        new GenFeatureFilter()
        {
          public boolean accept(GenFeature genFeature)
          {
            return genFeature.isNotify();
          }
        });
  }

  public List<GenFeature> getLabelNotifyFeatures()
  {
    return collectGenFeatures(getProviderImplementedGenClasses(), null,
      new GenFeatureFilter()
      {
        public boolean accept(GenFeature genFeature)
        {
          return genFeature.isNotify() && !genFeature.isChildren();
        }
      });
  }

  public List<GenFeature> getContentNotifyFeatures()
  {
    return collectGenFeatures(getProviderImplementedGenClasses(), null,
      new GenFeatureFilter()
      {
        public boolean accept(GenFeature genFeature)
        {
          return genFeature.isNotify() && genFeature.isChildren() && genFeature != getLabelFeature();
        }
      });
  }

  public List<GenFeature> getLabelAndContentNotifyFeatures()
  {
    GenFeature feature = getLabelFeature();
    return feature != null && feature.isNotify() && feature.isChildren() ?
      Collections.singletonList(feature) :
      Collections.<GenFeature>emptyList();
  }

  public List<GenFeature> getChildrenFeatures()
  {
    return collectGenFeatures
       (getProviderImplementedGenClasses(), 
        null, 
        new GenFeatureFilter()
        {
          public boolean accept(GenFeature genFeature) 
          {
            return genFeature.isChildren();
          }
        });
  }

  public List<GenFeature> getAllChildrenFeatures()
  {
    return 
      collectGenFeatures
        (getAllBaseGenClasses(), 
         getGenFeatures(), 
         new GenFeatureFilter()
         {
           public boolean accept(GenFeature genFeature) 
           {
            return genFeature.isChildren();
           }
         });
  }

  public List<GenFeature> getCreateChildFeatures()
  {
    List<GenFeature> result = new ArrayList<GenFeature>();

    // If this is class has mixed content, the mixed feature should always be included, even if inherited, and come first.
    //
    final GenFeature mixed = getMixedGenFeature();

    if (mixed != null && mixed.isCreateChild())
    {
      result.add(mixed);
    }

    // Add all other create child features that this item provider is responsible for.
    //
    result.addAll(collectGenFeatures(getProviderImplementedGenClasses(), null,
        new GenFeatureFilter()
        {
          public boolean accept(GenFeature genFeature)
          {
            return genFeature.isCreateChild() && genFeature != mixed;
          }
        }));

    return result;
  }
  
  public List<GenFeature> getAllCreateChildFeatures()
  {
    return collectGenFeatures(getAllBaseGenClasses(), getGenFeatures(),
      new GenFeatureFilter()
      {
        public boolean accept(GenFeature genFeature) 
        {
          return genFeature.isCreateChild();
        }
      });
  }

  public List<GenFeature> getAllCreateChildFeaturesIncludingDelegation()
  {
    return getCreateChildFeaturesIncludingDelegation(getAllBaseGenClasses());
  }

  public List<GenFeature> getCreateChildFeaturesIncludingDelegation()
  {
    return getCreateChildFeaturesIncludingDelegation(null);
  }

  private List<GenFeature> getCreateChildFeaturesIncludingDelegation(List<GenClass> genClasses)
  {
    return collectGenFeatures(genClasses, getGenFeatures(),
      new GenFeatureFilter()
      {
        public boolean accept(GenFeature genFeature)
        {
          while (genFeature != null)
          {
            if (genFeature.isCreateChild()) return true;
            genFeature = genFeature.getDelegateFeature();
          }
          return false;
        }
      });
  }

  public List<GenFeature> getCrossPackageCreateChildFeatures()
  {
    GenClass base = getProviderExtendsGenClass();

    // If there is a provider base class from outside this class that has already been generated, get the create
    // child references from it. We'll check for any type-compatible classes introduced in this package.
    //
    if (base == null || base.getGenPackage() == getGenPackage() ||
        getGenModel().getAllGenPackagesWithClassifiers().contains(base.getGenPackage()))
    {
      return Collections.emptyList();
    }

    return collectGenFeatures(base.getProviderImplementedGenClasses(), null,
      new GenFeatureFilter()
      {
        public boolean accept(GenFeature genFeature)
        {
          return genFeature.isCreateChild() && genFeature.isReferenceType();
        }
      });
  }

  public List<GenFeature> getSharedClassCreateChildFeatures()
  {
    List<GenFeature> childrenFeatures = getAllCreateChildFeatures();
    
    // build mapping from classes to list of features that use them
    Map<GenClass, List<GenFeature>> classToFeatureMap = new LinkedHashMap<GenClass, List<GenFeature>>();
    for (GenFeature childGenFeature : childrenFeatures)
    {
      List<GenFeature> childGenFeatureList = childGenFeature.isFeatureMapType() ? childGenFeature.getDelegatedFeatures() : Collections.singletonList(childGenFeature);
      for (GenFeature genFeature : childGenFeatureList)
      {
        List<GenClass> genClasses = getChildrenClasses(genFeature);
        for (GenClass genClass : genClasses)
        {
          List<GenFeature> genFeatures = classToFeatureMap.get(genClass);
          if (genFeatures == null)
          {
            genFeatures = new ArrayList<GenFeature>(5);
            classToFeatureMap.put(genClass, genFeatures);
          }
          genFeatures.add(genFeature);
        }
      }
    }

    // scan feature lists for those with multiple elements and return them
    List<GenFeature> result = new UniqueEList<GenFeature>(childrenFeatures.size());
    for (List<GenFeature> genFeatures : classToFeatureMap.values())
    {
      if (genFeatures.size() > 1) result.addAll(genFeatures);
    }
    return result;
  }

  public boolean hasFeatureMapCreateChildFeatures()
  {
    for (GenFeature genFeature : getAllCreateChildFeatures())
    {
      if (genFeature.isFeatureMapType()) return true;
    }
    return false;
  }

  public List<GenClass> getChildrenClasses(GenFeature genFeature)
  {
    EStructuralFeature feature = genFeature.getEcoreFeature();
    EClassifier eType = feature.getEType();
    List<GenClass> result = getTypeGenClasses(eType, getGenPackage(), getGenModel().getAllGenAndUsedGenPackagesWithClassifiers(), -1);
    if (eType == EcorePackage.Literals.EOBJECT && feature.getEAnnotation(ExtendedMetaData.ANNOTATION_URI) != null)
    {
      result.add(findGenClass(XMLTypePackage.Literals.ANY_TYPE));
    }
    return result;
  }

  public List<GenClass> getCrossPackageChildrenClasses(GenFeature genFeature)
  {
    return getTypeGenClasses(genFeature.getEcoreFeature().getEType(), getGenPackage(), getGenModel().getAllGenPackagesWithClassifiers(), -1);
  }

  public List<ChildCreationData> getChildCreationData()
  {
    return getChildCreationData(null);
  }

  public List<ChildCreationData> getAllChildCreationData(GenModel context)
  {
    UniqueEList<ChildCreationData> result = new UniqueEList<ChildCreationData>();
    List<GenFeature> allCreateChildGenFeatures =
      collectGenFeatures
        (null,
         getAllGenFeatures(),
         new GenFeatureFilter()
         {
           public boolean accept(GenFeature genFeature) 
           {
             return genFeature.isCreateChild();
           }
         });

    GenModel genModel = context == null ? getGenModel() : context;
    List<GenPackage> allGenAndUsedGenPackagesWithClassifiers = genModel.getAllGenAndUsedGenPackagesWithClassifiers();
    for (GenFeature createFeature : allCreateChildGenFeatures)
    {
      if (createFeature.isFeatureMapType())
      {
        for (GenFeature delegatedFeature : createFeature.getDelegatedFeatures(genModel))
        {
          if (delegatedFeature.isReferenceType())
          {
            EStructuralFeature eStructuralFeature = delegatedFeature.getEcoreFeature();
            EClassifier eType = eStructuralFeature.getEType();
            List<GenClass> genClasses = getTypeGenClasses(eType, getGenPackage(), allGenAndUsedGenPackagesWithClassifiers, -1);
            if (eType == EcorePackage.Literals.EOBJECT && eStructuralFeature.getEAnnotation(ExtendedMetaData.ANNOTATION_URI) != null)
            {
              genClasses.add(findGenClass(XMLTypePackage.Literals.ANY_TYPE));
            }
            for (GenClass createClass : genClasses)
            {
              result.add(new ChildCreationData(createFeature, delegatedFeature, createClass));
            }
          }
          else
          {
            result.add(new ChildCreationData(createFeature, delegatedFeature, delegatedFeature.getTypeGenClassifier()));
          }
        }
      }
      else if (createFeature.isReferenceType())
      {
        EStructuralFeature eStructuralFeature = createFeature.getEcoreFeature();
        EClassifier eType = eStructuralFeature.getEType();
        List<GenClass> genClasses = getTypeGenClasses(eType, getGenPackage(), allGenAndUsedGenPackagesWithClassifiers, -1);
        if (eType == EcorePackage.Literals.EOBJECT && eStructuralFeature.getEAnnotation(ExtendedMetaData.ANNOTATION_URI) != null)
        {
          genClasses.add(findGenClass(XMLTypePackage.Literals.ANY_TYPE));
        }
        for (GenClass createClass : genClasses)
        {
          result.add(new ChildCreationData(createFeature, null, createClass));
        }
      }
      else
      {
        result.add(new ChildCreationData(createFeature, null, createFeature.getTypeGenClassifier()));
      }
    }
    return result;
  }

  public List<ChildCreationData> getChildCreationData(GenModel context)
  {
    UniqueEList<ChildCreationData> result = new UniqueEList<ChildCreationData>(getAllChildCreationData(context));

    GenClassImpl baseClass = (GenClassImpl)getProviderExtendsGenClass();
    if (baseClass != null)
    {
      result.removeAll(baseClass.getAllChildCreationData(context));
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenFeature getLabelFeatureGen()
  {
    if (labelFeature != null && labelFeature.eIsProxy())
    {
      InternalEObject oldLabelFeature = (InternalEObject)labelFeature;
      labelFeature = (GenFeature)eResolveProxy(oldLabelFeature);
      if (labelFeature != oldLabelFeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_CLASS__LABEL_FEATURE, oldLabelFeature, labelFeature));
      }
    }
    return labelFeature;
  }

  public GenFeature getLabelFeature()
  {
    GenFeature labelFeature = getLabelFeatureGen();
    if (labelFeature != null)
      return labelFeature;
    
    Locale locale = getGenModel().getLocale();
    //FB TBD can we come up with a better algorithm for choosing the default label feature?
    for (GenFeature feature : getLabelFeatureCandidates())
    {
      if (!feature.isListType())
      {
        String featureName = feature.getName();
        if (featureName != null)
        {
          if (featureName.equalsIgnoreCase("name")) 
          {
            labelFeature = feature;
          }
          else if (featureName.equalsIgnoreCase("id")) 
          {
            if (labelFeature == null || !labelFeature.getName().toLowerCase(locale).endsWith("name"))
            {
              labelFeature = feature;
            }
          }
          else if (featureName.toLowerCase(locale).endsWith("name")) 
          {
            if (labelFeature == null || 
                 !labelFeature.getName().toLowerCase(locale).endsWith("name") && !labelFeature.getName().equalsIgnoreCase("id"))
            {
              labelFeature = feature;
            }
          }
          else if (featureName.toLowerCase(locale).indexOf("name") != -1)
          {
            if (labelFeature == null || 
                  labelFeature.getName().toLowerCase(locale).indexOf("name") == -1 && !labelFeature.getName().equalsIgnoreCase("id"))
            {
              labelFeature = feature;
            }
          }
          else if (labelFeature == null) 
          {
            labelFeature = feature;
          }
        }
      }
    }

    return labelFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenFeature basicGetLabelFeature()
  {
    return labelFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLabelFeature(GenFeature newLabelFeature)
  {
    GenFeature oldLabelFeature = labelFeature;
    labelFeature = newLabelFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__LABEL_FEATURE, oldLabelFeature, labelFeature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getGenFeatures()).basicAdd(otherEnd, msgs);
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getGenOperations()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        return ((InternalEList<?>)getGenFeatures()).basicRemove(otherEnd, msgs);
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        return ((InternalEList<?>)getGenOperations()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_CLASS__PROVIDER:
        return getProvider();
      case GenModelPackage.GEN_CLASS__IMAGE:
        return isImage();
      case GenModelPackage.GEN_CLASS__DYNAMIC:
        return isDynamic();
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
        if (resolve) return getEcoreClass();
        return basicGetEcoreClass();
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        return getGenFeatures();
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        return getGenOperations();
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        if (resolve) return getLabelFeature();
        return basicGetLabelFeature();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_CLASS__PROVIDER:
        setProvider((GenProviderKind)newValue);
        return;
      case GenModelPackage.GEN_CLASS__IMAGE:
        setImage((Boolean)newValue);
        return;
      case GenModelPackage.GEN_CLASS__DYNAMIC:
        setDynamic((Boolean)newValue);
        return;
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
        setEcoreClass((EClass)newValue);
        return;
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        getGenFeatures().clear();
        getGenFeatures().addAll((Collection<? extends GenFeature>)newValue);
        return;
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        getGenOperations().clear();
        getGenOperations().addAll((Collection<? extends GenOperation>)newValue);
        return;
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        setLabelFeature((GenFeature)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_CLASS__PROVIDER:
        setProvider(PROVIDER_EDEFAULT);
        return;
      case GenModelPackage.GEN_CLASS__IMAGE:
        setImage(IMAGE_EDEFAULT);
        return;
      case GenModelPackage.GEN_CLASS__DYNAMIC:
        setDynamic(DYNAMIC_EDEFAULT);
        return;
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
        setEcoreClass((EClass)null);
        return;
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        getGenFeatures().clear();
        return;
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        getGenOperations().clear();
        return;
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        setLabelFeature((GenFeature)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_CLASS__PROVIDER:
        return provider != PROVIDER_EDEFAULT;
      case GenModelPackage.GEN_CLASS__IMAGE:
        return image != IMAGE_EDEFAULT;
      case GenModelPackage.GEN_CLASS__DYNAMIC:
        return dynamic != DYNAMIC_EDEFAULT;
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
        return ecoreClass != null;
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        return genFeatures != null && !genFeatures.isEmpty();
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        return genOperations != null && !genOperations.isEmpty();
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        return labelFeature != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (provider: ");
    result.append(provider);
    result.append(", image: ");
    result.append(image);
    result.append(", dynamic: ");
    result.append(dynamic);
    result.append(')');
    return result.toString();
  }

  public String getItemProviderAdapterFactoryClassName()
  {
    return getGenPackage().getItemProviderAdapterFactoryClassName();
  }

  public String getTestCaseClassName()
  {
    return getName() + "Test";
  }

  public String getQualifiedTestCaseClassName()
  {
    return getGenPackage().getTestsPackageName() + "." + getTestCaseClassName();
  }

  public String getImportedTestCaseClassName()
  {
    return getGenModel().getImportedName(getQualifiedTestCaseClassName());
  }

  @Override
  public boolean canGenerate()
  {
    return super.canGenerate() && !isDynamic();
  }

  @Override
  public boolean canGenerateEdit()
  {
    return super.canGenerateEdit() && !isInterface() && getProvider() != GenProviderKind.NONE_LITERAL;
  }

  @Override
  public boolean canGenerateEditor()
  {
    return false;
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @SuppressWarnings("rawtypes")
  @Override
  @Deprecated
  public void generateEdit(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerateEdit()) return;

      progressMonitor.beginTask("", 2 + getAllCreateChildFeatures().size());
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingProvider_message", new Object [] { getFormattedName() }));

      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingJavaClass_message", new Object [] { getQualifiedProviderClassName() }));
      generate
        (createMonitor(progressMonitor, 1), 
         Generator.EMF_EDIT_PROJECT_STYLE, 
         getGenModel().getEffectiveModelPluginVariables(), 
         getGenModel().getEditDirectory(), 
         getGenPackage().getProviderPackageName(),
         getProviderClassName(), 
         getGenModel().getItemProviderEmitter());

      if (isImage())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingItemIcon_message", new Object [] { getItemIconFileName() }));
        generate
          (createMonitor(progressMonitor, 1), 
           Generator.EMF_EDIT_PROJECT_STYLE, 
           getGenModel().getEffectiveModelPluginVariables(), 
           getItemIconFileName(), 
           ((GenModelImpl)getGenModel()).getItemGIFEmitter(),
           getName());
      }

      if (getGenModel().isCreationCommands() && getGenModel().isCreationIcons())
      {
        for (Iterator iter = getAllCreateChildFeaturesIncludingDelegation().iterator(); iter.hasNext(); )
        {
          GenFeature feature = (GenFeature)iter.next();
          for (Iterator cIter = getChildrenClasses(feature).iterator(); cIter.hasNext(); )
          {
            GenClass childClass = (GenClass)cIter.next();
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_GeneratingCreateChildIcon_message", new Object [] { getCreateChildIconFileName(feature, childClass) }));
            generate
              (createMonitor(progressMonitor, 1), 
               Generator.EMF_EDIT_PROJECT_STYLE, 
               getGenModel().getEffectiveModelPluginVariables(), 
               getCreateChildIconFileName(feature, childClass), 
               ((GenModelImpl)getGenModel()).getCreateChildGIFEmitter(),
               getName(),
               childClass.getName());
          }
        }
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public boolean hasTests()
  {
    for (GenFeature genFeature : getAllGenFeatures())
    {
      if (((genFeature.isGet() && !genFeature.isSuppressedGetVisibility())
        || (genFeature.isSet() && !genFeature.isSuppressedSetVisibility())
        || (genFeature.isUnset() && !genFeature.isSuppressedUnsetVisibility())
        || (genFeature.isIsSet() && !genFeature.isSuppressedIsSetVisibility()))
          && (genFeature.isVolatile() || genFeature.isDerived()))
      {
        return true;
      }
    }

    return !getAllGenOperations().isEmpty();
  }

  @Override
  public boolean canGenerateTests()
  {
    return getGenModel().canGenerateTests();
  }

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Override
  @Deprecated
  public void generateTests(Monitor progressMonitor)
  {
    try
    {
      if (!canGenerateTests())
        return;

      progressMonitor.beginTask("", 1);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_GeneratingTestCase_message", new Object [] { getFormattedName() }));

      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString(
        "_UI_GeneratingJavaClass_message", new Object []{ getQualifiedTestCaseClassName() }));
      generate(
        createMonitor(progressMonitor, 1),
        Generator.EMF_TESTS_PROJECT_STYLE,
        Collections.EMPTY_LIST,
        getGenModel().getTestsDirectory(),
        getGenPackage().getTestsPackageName(),
        getTestCaseClassName(),
        getGenModel().getTestCaseEmitter());
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public boolean reconcile(GenClass oldGenClassVersion)
  {
    if (getEcoreClass().getName().equals(oldGenClassVersion.getEcoreClass().getName()))
    {
      for (GenFeature genFeature : getGenFeatures())
      {
        for (GenFeature oldGenFeatureVersion : oldGenClassVersion.getGenFeatures())
        {
          if (genFeature.reconcile(oldGenFeatureVersion))
          {
            break;
          }
        }
      }

      for (GenOperation genOperation : getGenOperations())
      {
        for (GenOperation oldGenOperation : oldGenClassVersion.getGenOperations())
        {
          if (genOperation.reconcile(oldGenOperation))
          {
            break;
          }
        }
      }

      for (int i = 0, size = Math.min(getGenTypeParameters().size(), oldGenClassVersion.getGenTypeParameters().size()); i < size; i++)
      {
        GenTypeParameter genTypeParameter = getGenTypeParameters().get(i);
        GenTypeParameter oldGenTypeParameterVersion = oldGenClassVersion.getGenTypeParameters().get(i);
        genTypeParameter.reconcile(oldGenTypeParameterVersion);
      }

      reconcileSettings(oldGenClassVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenClass oldGenClassVersion)
  {
    setProvider(oldGenClassVersion.getProvider());
    setImage(oldGenClassVersion.isImage());
    GenFeature oldLabelFeature = ((GenClassImpl)oldGenClassVersion).getLabelFeatureGen();
    if (oldLabelFeature != null)
    {
      EStructuralFeature newLabelFeature =  getEcoreClass().getEStructuralFeature(oldLabelFeature.getEcoreFeature().getName());
      if (newLabelFeature != null)
      {
        setLabelFeature(findGenFeature(newLabelFeature));
      } 
    }
    setDynamic(oldGenClassVersion.isDynamic());
    reconcileGenAnnotations(oldGenClassVersion);
  }

  public boolean reconcile()
  {
    try
    {
      EClass eClass = getEcoreClass();
      if (eClass == null || eClass.eIsProxy() || eClass.eResource() == null)
      {
        return false;
      }
      else
      {
        for (Iterator<GenTypeParameter> i = getGenTypeParameters().iterator(); i.hasNext(); )
        {
          GenTypeParameter genTypeParameter = i.next();
          if (!genTypeParameter.reconcile())
          {
            i.remove();
          }
        }

        for (Iterator<GenFeature> i = getGenFeatures().iterator(); i.hasNext(); )
        {
          GenFeature genFeature = i.next();
          if (!genFeature.reconcile())
          {
            i.remove();
          }
        }
  
        for (Iterator<GenOperation> i = getGenOperations().iterator(); i.hasNext(); )
        {
          GenOperation genOperation = i.next();
          if (!genOperation.reconcile())
          {
            i.remove();
          }
        }
  
        return true;
      }
    }
    catch (RuntimeException exception)
    {
      return false;
    }
  }

  @Override
  public List<String> getGenConstraints()
  {
    List<String> result = new UniqueEList<String>(super.getGenConstraints());
    for (GenOperation genOperation : getInvariantOperations())
    {
      result.add(genOperation.getName());
    }
    return result;
  }

  /**
   * @deprecated since 2.5
   * @see #getIntrinsicConstraints()
   */
  @Deprecated
  public static final List<String> INTRINSIC_CONSTRAINTS = 
    Arrays.asList
      (new String [] 
       {
        "NoCircularContainment",
        "EveryMultiplicityConforms", 
        "EveryDataValueConforms", 
        "EveryReferenceIsContained", 
        "EveryBidirectionalReferenceIsPaired",
        "EveryProxyResolves",
        "UniqueID",
        "EveryKeyUnique",
        "EveryMapEntryUnique"
       });

  protected List<String> getIntrinsicConstraints()
  {
    List<String> result = new ArrayList<String>(INTRINSIC_CONSTRAINTS);
    int runtimeVersion = getGenModel().getRuntimeVersion().getValue();
	if (runtimeVersion <= GenRuntimeVersion.EMF22_VALUE)
    {
      result.remove("EveryKeyUnique");
      result.remove("EveryMapEntryUnique");
    }
	if (runtimeVersion < GenRuntimeVersion.EMF25_VALUE)
	{
      result.remove("NoCircularContainment");
	}
	if (runtimeVersion < GenRuntimeVersion.EMF26_VALUE)
	{
      result.remove("EveryBidirectionalReferenceIsPaired");
	}
    return result;
  }

  @Override
  public List<String> getAllGenConstraints()
  {
    List<String> result = new ArrayList<String>(getIntrinsicConstraints());
    result.addAll(collectGenConstraints(getAllBaseGenClasses(), getGenConstraints(), null));
    return result;
  }

  @Override
  public GenClassifier getConstraintImplementor(String constraint)
  {
    if (getGenConstraints().contains(constraint))
    {
      return this;
    }
    else
    {
      for (GenClass baseGenClass : getBaseGenClasses())
      {
        if (baseGenClass.getGenConstraints().contains(constraint))
        {
          return baseGenClass;
        }
        else if (baseGenClass.getAllGenConstraints().contains(constraint))
        {
          return baseGenClass.getConstraintImplementor(constraint);
        }
      }
      return null;
    }
  }

  public GenClassifier getConstraintDelegate(String constraint)
  {
    for (GenClass baseGenClass : getBaseGenClasses())
    {
      if (baseGenClass.getGenConstraints().contains(constraint))
      {
        return baseGenClass;
      }
      else if (baseGenClass.getAllGenConstraints().contains(constraint))
      {
        return baseGenClass.getConstraintImplementor(constraint);
      }
    }
    return null;
  }

  @Override
  public boolean hasOnlyDefaultConstraints()
  {
    for (String genConstraint : getAllGenConstraints())
    {
      if (getConstraintImplementor(genConstraint) != null)
      {
        return false;
      }
    }
    return true;
  }

  public List<GenOperation> getInvariantOperations()
  {
    return collectGenOperations(this, null, getGenOperations(), new GenOperationFilter()
      {
        public boolean accept(GenOperation genOperation)
        {
          return genOperation.isInvariant();
        }
      });
  }

  public GenOperation getInvariantOperation(String constraint)
  {
    for (GenOperation genOperation : getInvariantOperations())
    {
      if (genOperation.getName().equals(constraint))
      {
        return genOperation;
      }
    }
    return null;
  }

  public boolean isDocumentRoot()
  {
    return getExtendedMetaData().getDocumentRoot(getEcoreClass().getEPackage()) == getEcoreClass();
  }

  protected boolean isMixed()
  {
    return getExtendedMetaData().getContentKind(getEcoreClass()) == ExtendedMetaData.MIXED_CONTENT;
  }

  public GenFeature getMixedGenFeature()
  {
    if (!isMixed()) return null;
    EAttribute mixedFeature = getExtendedMetaData().getMixedFeature(getEcoreClass());
    return mixedFeature != null ? findGenFeature(mixedFeature) : null;
  }

  public String getListConstructor(GenFeature genFeature)
  {
    StringBuffer sb = new StringBuffer();

    String unsettable = genFeature.isUnsettable() ? ".Unsettable" : "";
    String offsetCorrectionField = hasOffsetCorrection () ? " + " + getOffsetCorrectionField(null) : "";

    boolean isJava5 = getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    if (genFeature.isMapType())
    {
      GenClass mapGenClass = genFeature.getMapEntryTypeGenClass();
      sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EcoreEMap"));
      sb.append(unsettable);
      if (isJava5)
      {
        sb.append('<');
        sb.append(genFeature.getImportedMapKeyType(this));
        sb.append(',');
        sb.append(genFeature.getImportedMapValueType(this));
        sb.append('>');
      }
      sb.append("(");
      sb.append(mapGenClass.getQualifiedClassifierAccessor());
      sb.append(", ");
      sb.append(genFeature.getImportedMapEntryType());
      sb.append(".class, this, ");
      sb.append(getQualifiedFeatureID(genFeature));
      sb.append(offsetCorrectionField);
      if (genFeature.isBidirectional() && getGenModel().getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF27_VALUE)
      {
        sb.append(", ");
        sb.append(genFeature.getReverse().getGenClass().getQualifiedFeatureID(genFeature.getReverse()));
      }
      sb.append(")");
    }
    else if (genFeature.isFeatureMapType())
    {
      if (genFeature.isWrappedFeatureMapType())
      {
        sb.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
        sb.append("(new ");
      }
      sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.BasicFeatureMap"));
      sb.append("(this, ");
      sb.append(getQualifiedFeatureID(genFeature));
      sb.append(offsetCorrectionField);
      sb.append(")");
      if (genFeature.isWrappedFeatureMapType())
      {
        sb.append(")");
      }
    }
    else
    {
      EGenericType eGenericType = genFeature.getEcoreFeature().getEGenericType();
      if (getGenModel().isSuppressNotification())
      {
        sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.BasicInternalEList"));
        if (getGenModel().getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF28_VALUE)
        {
          sb.append(unsettable);
        }
        if (isJava5)
        {
          sb.append('<');
          sb.append(genFeature.getListItemType(this));
          sb.append('>');
        }
        sb.append("(");
        sb.append(getTypeArgument(this, eGenericType, true, true));
        sb.append(".class)");
      }
      else if (genFeature.isEffectiveContains())
      {
        if (genFeature.isBidirectional())
        {
          GenFeature reverseFeature = genFeature.getReverse();
          sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList"));
          sb.append(unsettable);
          if (genFeature.isResolveProxies())
          {
            sb.append(".Resolving");
          }
          if (isJava5)
          {
            sb.append('<');
            sb.append(genFeature.getListItemType(this));
            sb.append('>');
          }
          sb.append("(");
          sb.append(getTypeArgument(this, eGenericType, true, true));
          sb.append(".class, this, ");
          sb.append(getQualifiedFeatureID(genFeature));
          sb.append(offsetCorrectionField);
          sb.append(", ");
          sb.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
          if (reverseFeature.getGenClass().hasOffsetCorrection())
          {
            sb.append(" + ");
            sb.append(getOffsetCorrectionField(genFeature));
          }
          sb.append(")");
          appendReifiedFeatureInverseOverride(sb, genFeature);
        }
        else
        {
          sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EObjectContainmentEList"));
          sb.append(unsettable);
          if (genFeature.isResolveProxies())
          {
            sb.append(".Resolving");
          }
          if (isJava5)
          {
            sb.append('<');
            sb.append(genFeature.getListItemType(this));
            sb.append('>');
          }
          sb.append("(");
          sb.append(getTypeArgument(this, eGenericType, true, true));
          sb.append(".class, this, ");
          sb.append(getQualifiedFeatureID(genFeature));
          sb.append(offsetCorrectionField);
          sb.append(")");
        }
      }
      else if (genFeature.isReferenceType())
      {
        if (genFeature.isBidirectional())
        {
          GenFeature reverseFeature = genFeature.getReverse();
          if (genFeature.isResolveProxies())
          {
            sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList"));
          }
          else
          {
            sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseEList"));
          }
          sb.append(unsettable);
          if (reverseFeature.isListType())
          {
            sb.append(".ManyInverse");
          }
          if (isJava5)
          {
            sb.append('<');
            sb.append(genFeature.getListItemType(this));
            sb.append('>');
          }
          sb.append("(");
          sb.append(getTypeArgument(this, eGenericType, true, true));
          sb.append(".class, this, ");
          sb.append(getQualifiedFeatureID(genFeature));
          sb.append(offsetCorrectionField);
          sb.append(", ");
          sb.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
          if (reverseFeature.getGenClass().hasOffsetCorrection())
          {
            sb.append(" + ");
            sb.append(getOffsetCorrectionField(genFeature));
          }
          sb.append(")");
          appendReifiedFeatureInverseOverride(sb, genFeature);
        }
        else
        {
          if (genFeature.isResolveProxies())
          {
            sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EObjectResolvingEList"));
          }
          else
          {
            sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EObjectEList"));
          }
          sb.append(unsettable);
          if (isJava5)
          {
            sb.append('<');
            sb.append(genFeature.getListItemType(this));
            sb.append('>');
          }
          sb.append("(");
          sb.append(getTypeArgument(this, eGenericType, true, true));
          sb.append(".class, this, ");
          sb.append(getQualifiedFeatureID(genFeature));
          sb.append(offsetCorrectionField);
          sb.append(")");
        }
      }
      else
      { //data type
        if (genFeature.isUnique())
        {
          sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EDataTypeUniqueEList"));
        }
        else
        {
          sb.append(getGenModel().getImportedName("org.eclipse.emf.ecore.util.EDataTypeEList"));
        }
        sb.append(unsettable);
        if (isJava5)
        {
          sb.append('<');
          sb.append(genFeature.getListItemType(this));
          sb.append('>');
        }
        sb.append("(");
        sb.append(isPrimitiveType(eGenericType.getERawType()) ? genFeature.getRawListItemType() : getTypeArgument(this, eGenericType, true, true));
        sb.append(".class, this, ");
        sb.append(getQualifiedFeatureID(genFeature));
        sb.append(offsetCorrectionField);
        sb.append(")");
      }
    }
    return sb.toString();
  }

  private void appendReifiedFeatureInverseOverride(StringBuffer sb, GenFeature genFeature)
  {
    GenClass sourceGenClass = genFeature.getGenClass();
    EGenericType eGenericType = genFeature.getEcoreFeature().getEGenericType();
    if (isReifiedType(sourceGenClass, eGenericType))
    {
      sb.append(" { private static final long serialVersionUID = 1L; @Override public ");
      sb.append(getGenModel().getImportedName("java.lang.Class"));
      sb.append("<?> getInverseFeatureClass() { return ");
      sb.append(getTypeArgument(sourceGenClass, eGenericType, true, true));
      sb.append(".class; } }");
    }
  }

  public boolean isModelRoot()
  {
    return getClassExtendsGenClass() == null || getClassExtendsGenClass().getGenModel() != getGenModel();
  }

  public List<GenFeature> getDeclaredFieldGenFeatures()
  {
    return getImplementedGenFeatures();
  }

  public boolean isFlag(GenFeature genFeature)
  {
    GenModel genModel = getImplementingGenModel(genFeature);
    return genModel.isBooleanFlagsEnabled() && !genFeature.isVolatile() &&
      (genFeature.isBooleanType() || genModel.isPackedEnums() && genFeature.isEnumType());
  }

  public boolean isESetFlag(GenFeature genFeature)
  {
    GenModel genModel = getImplementingGenModel(genFeature);
    return (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) && genModel.isBooleanFlagsEnabled() && genFeature.isESetFlag();
  }

  public boolean isField(GenFeature genFeature)
  {
    GenModel genModel = getImplementingGenModel(genFeature);
    return
      !genModel.isReflectiveDelegation() &&
        !genModel.isDynamicDelegation() &&
        (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) &&
        genFeature.isField();
  }

  public boolean isESetField(GenFeature genFeature)
  {
    GenModel genModel = getImplementingGenModel(genFeature);
    return
      !genModel.isReflectiveDelegation() &&
        !genModel.isDynamicDelegation() &&
        (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) &&
        genFeature.isESetField();
  }

  public class CollidingGenOperationFilter implements GenOperationFilter
  {
    protected List<GenFeature> allGenFeatures = getAllGenFeatures();
    protected List<GenOperation> extendsGenClassOperations;
    protected List<GenFeature> extendsGenClassFeatures;
    
    public CollidingGenOperationFilter()
    {
      GenClass extendsClass = getClassExtendsGenClass();
      if (extendsClass != null)
      {
        extendsGenClassOperations = extendsClass.getAllGenOperations();
        extendsGenClassFeatures = extendsClass.getAllGenFeatures();
      }
      else
      {
        extendsGenClassOperations = Collections.emptyList();
        extendsGenClassFeatures = Collections.emptyList();
      }
    }

    public boolean accept(GenOperation genOperation)
    {
      boolean hasBody = genOperation.hasBody() || genOperation.hasInvocationDelegate();

      if (genOperation.getName().startsWith("isSet") && genOperation.getGenParameters().isEmpty())
      {
        for (GenFeature genFeature : allGenFeatures)
        {
          if (genFeature.isChangeable() && 
                genFeature.isUnsettable() && 
                genOperation.getName().equals("isSet" + genFeature.getAccessorName()) &&
                (!hasBody || genFeature.isVolatile() && !genFeature.hasDelegateFeature() && !extendsGenClassFeatures.contains(genFeature)))
          {
            return false;
          }
        }
      }
      else if ((genOperation.getName().startsWith("get") || genOperation.getName().startsWith("is")) && 
                 genOperation.getGenParameters().isEmpty())
      {
        String operationType = genOperation.getType(GenClassImpl.this);
        for (GenFeature genFeature : allGenFeatures)
        {
          if (genFeature.getGetAccessor().equals(genOperation.getName()) &&
                (genFeature.getType(GenClassImpl.this).equals(operationType) || !extendsGenClassFeatures.contains(genFeature)) &&
                (!hasBody || 
                    genFeature.isVolatile() && 
                      (!genFeature.isResolveProxies() || genFeature.isListType()) && 
                      !genFeature.hasDelegateFeature() && 
                      !extendsGenClassFeatures.contains(genFeature)))
          {
            return false;
          }
        }
      }
      else if (genOperation.getName().startsWith("set") && genOperation.getGenParameters().size() == 1)
      {
        GenParameter genParameter = genOperation.getGenParameters().get(0);
        for (GenFeature genFeature : allGenFeatures)
        {
          if (genFeature.isChangeable() && 
                !genFeature.isListType() && 
                genOperation.getName().equals("set" + genFeature.getAccessorName()) && 
                genParameter.getType(GenClassImpl.this).equals(genFeature.getType(GenClassImpl.this)) &&
                (!hasBody || genFeature.isVolatile() && !genFeature.hasDelegateFeature() && !extendsGenClassFeatures.contains(genFeature)))
          {
            return false;
          }
        }
      }
      else if (genOperation.getName().startsWith("unset") && genOperation.getGenParameters().isEmpty())
      {
        for (GenFeature genFeature : allGenFeatures)
        {
          if (genFeature.isChangeable() && genFeature.isUnsettable() && 
                genOperation.getName().equals("unset" + genFeature.getAccessorName()) &&
                (!hasBody || genFeature.isVolatile() && !genFeature.hasDelegateFeature() && !extendsGenClassFeatures.contains(genFeature)))
          {
            return false;
          }
        }
      }

      if (!hasBody)
      {
        String operationType = genOperation.getType(GenClassImpl.this);
        for (GenOperation baseOperation : extendsGenClassOperations)
        {
          if (baseOperation.isOverrideOf(GenClassImpl.this, genOperation))
          {
            String baseOperationType = baseOperation.getType(GenClassImpl.this);
            if (operationType== null ? baseOperationType == null : operationType.equals(baseOperationType))
            {
              return false;
            }
          }
        }
      }

      return !genOperation.getGenClass().isEObject();
    }
  }

  // Returns whether this class implements any of the given features.
  public boolean implementsAny(Collection<GenFeature> genFeatures)
  {
    List<GenFeature> implementedGenFeatures = getImplementedGenFeatures();
    if (!implementedGenFeatures.isEmpty())
    {  
      for (GenFeature genFeature : genFeatures)
      {
        if (implementedGenFeatures.contains(genFeature))
        {
          return true;
        }
      }
    }
    return false;
  }

  protected int getNonPrimitiveFeatureCount()
  {
    return 
    collectGenFeatures
      (getAllBaseGenClasses(), 
       getGenFeatures(), 
       new GenFeatureFilter() 
       {
         public boolean accept(GenFeature genFeature) 
         {
           return !genFeature.isPrimitiveType();
         }
       }).size();
  }

  public String getEVirtualValuesField()
  {
    String eVirtualValuesField = null;

    for (GenClass classExtendsGenClass = getClassExtendsGenClass(); eVirtualValuesField == null && classExtendsGenClass != null; classExtendsGenClass = classExtendsGenClass.getClassExtendsGenClass())
    {
      eVirtualValuesField = classExtendsGenClass.getEVirtualValuesField();
    }

    return eVirtualValuesField == null && getGenModel().isVirtualDelegation() && getNonPrimitiveFeatureCount() > 0 ? "eVirtualValues" : null;
  }

  public List<String> getEVirtualIndexBitFields(List<String> eVirtualIndexBitFields)
  {
    if (getGenModel().isVirtualDelegation() && getNonPrimitiveFeatureCount() > 0)
    {
      GenClass classExtendsGenClass = getClassExtendsGenClass();

      for (int i = (classExtendsGenClass == null ? 0 : classExtendsGenClass.getAllEVirtualIndexBitFields(new ArrayList<String>()).size()); i < (getFeatureCount() / 32) + 1; i++)
      {
        eVirtualIndexBitFields.add("eVirtualIndexBits" + i);
      }
    }

    return eVirtualIndexBitFields;
  }

  public List<String> getAllEVirtualIndexBitFields(List<String> allEVirtualIndexBitFields)
  {
    GenClass classExtendsGenClass = getClassExtendsGenClass();
    return getEVirtualIndexBitFields(classExtendsGenClass == null
      ? allEVirtualIndexBitFields : classExtendsGenClass.getAllEVirtualIndexBitFields(allEVirtualIndexBitFields));
  }
  
  public boolean isJavaIOSerializable()
  {
    for (GenClass baseGenClass : getAllBaseGenClasses())
    {
      if ("java.io.Serializable".equals(baseGenClass.getQualifiedInterfaceName()))
      {
        return true;
      }
    }
    GenClass rootImplementsInterfaceGenClass = getGenModel().getRootImplementsInterfaceGenClass();
    if (rootImplementsInterfaceGenClass != null)
    {
      for (GenClass baseGenClass : rootImplementsInterfaceGenClass.getAllBaseGenClasses())
      {
        if ("java.io.Serializable".equals(baseGenClass.getQualifiedInterfaceName()))
        {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean hasFactoryInterfaceCreateMethod()
  {
    return !isAbstract() && !isMapEntry() && !(getGenModel().isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(getQualifiedInterfaceName()));
  }

  public boolean hasOffsetCorrection()
  {
    GenModel genModel = getGenModel();
    if (genModel.isBinaryCompatibleReflectiveMethods() && genModel.isMinimalReflectiveMethods())
    {
      for (GenClass baseGenClass : getAllBaseGenClasses())
      {
        if (!baseGenClass.isEObject() && baseGenClass.getGenModel()  != genModel)
        {
          return true;
        }
      }
    }
    return false;
  }
  
  public String getOffsetCorrectionField(GenFeature genFeature)
  {
    return 
      genFeature == null ?
        "EOFFSET_CORRECTION" :
        "EOFFSET_CORRECTION_" + genFeature.getUpperName() + "_OPPOSITE";
  }

  public boolean needsHasChildrenMethodOverride()
  {
    boolean optimized = getGenModel().isOptimizedHasChildren();
    GenClass providerExtendsGenClass = getProviderExtendsGenClass();
    boolean optimizedBase =  providerExtendsGenClass != null && providerExtendsGenClass.getGenModel().isOptimizedHasChildren();
    return optimized != optimizedBase;
  }

  protected GenOperation getAccesorOperation(final GenFeature genFeature, final String accessor)
  {
    if (genFeature.isVolatile() &&
          (!accessor.startsWith("get") || !genFeature.isResolveProxies() || genFeature.isListType()) && 
          !genFeature.hasDelegateFeature())
    {
      List<GenOperation> accessorOperations = 
        collectGenOperations
          (this,
           getImplementedGenClasses(),
           null,
           new CollidingGenOperationFilter()
           {
             @Override
             public boolean accept(GenOperation genOperation)
             {
               return accessor.equals(genOperation.getName()) && genOperation.hasBody() && !super.accept(genOperation);
             }
           });
      return accessorOperations.isEmpty() ? null : accessorOperations.get(0);
    }
    else
    {
      return null;
    }
  }

  public GenOperation getGetAccessorOperation(GenFeature genFeature)
  {
    return getAccesorOperation(genFeature, genFeature.getGetAccessor());
  }

  public GenOperation getSetAccessorOperation(GenFeature genFeature)
  {
    return getAccesorOperation(genFeature, "set" + genFeature.getAccessorName());
  }

  public GenOperation getIsSetAccessorOperation(GenFeature genFeature)
  {
    return getAccesorOperation(genFeature, "isSet" + genFeature.getAccessorName());
  }

  public GenOperation getUnsetAccessorOperation(GenFeature genFeature)
  {
    return getAccesorOperation(genFeature, "unset" + genFeature.getAccessorName());
  }

  protected boolean hasCollidingAccessorOperation(final GenFeature genFeature, final String accessor)
  {
    if (genFeature.isVolatile() &&
          (!accessor.startsWith("get") || !genFeature.isResolveProxies() || genFeature.isListType()) && 
          !genFeature.hasDelegateFeature())
    {
      return false;
    }
    else
    {
      for (GenOperation genOperation : getImplementedGenOperations())
      {
        if (accessor.equals(genOperation.getName()) && genOperation.hasBody())
        {
          EList<GenParameter> genParameters = genOperation.getGenParameters();
          if (accessor.startsWith("set") ? 
                genParameters.size() == 1 && genParameters.get(0).getType(this).equals(genFeature.getType(this)) : 
                genParameters.isEmpty())
          {
            return true;
          }
        }
      }
      return false;
    }
  }

  public boolean hasCollidingGetAccessorOperation(GenFeature genFeature)
  {
    return hasCollidingAccessorOperation(genFeature, genFeature.getGetAccessor());
  }
  
  public boolean hasCollidingSetAccessorOperation(GenFeature genFeature)
  {
    return hasCollidingAccessorOperation(genFeature, "set" + genFeature.getAccessorName());
  }
  
  public boolean hasCollidingIsSetAccessorOperation(GenFeature genFeature)
  {
    return hasCollidingAccessorOperation(genFeature, "isSet" + genFeature.getAccessorName());
  }
  
  public boolean hasCollidingUnsetAccessorOperation(GenFeature genFeature)
  {
    return hasCollidingAccessorOperation(genFeature, "unset" + genFeature.getAccessorName());
  }

  public boolean hasStaticFeatures()
  {
    switch (getGenModel().getFeatureDelegation())
    {
      case DYNAMIC_LITERAL:
      case REFLECTIVE_LITERAL:
      {
        GenClass classExtendsGenClass = getClassExtendsGenClass();
        return classExtendsGenClass != null && classExtendsGenClass.hasStaticFeatures();
      }
      default:
      {
        return getAllGenFeatures().size() > 0;
      }
    }
  }
}
