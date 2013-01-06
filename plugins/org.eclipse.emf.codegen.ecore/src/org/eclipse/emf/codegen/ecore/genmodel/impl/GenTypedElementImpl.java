/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypedElement;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Typed Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class GenTypedElementImpl extends GenBaseImpl implements GenTypedElement
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GenTypedElementImpl()
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
    return GenModelPackage.Literals.GEN_TYPED_ELEMENT;
  }

  public abstract ETypedElement getEcoreTypedElement();

  @Override
  public EModelElement getEcoreModelElement()
  {
    return getEcoreTypedElement();
  }

  public GenPackage getTypeGenPackage()
  {
    return findGenPackage(getEcoreTypedElement().getEType().getEPackage());
  }

  public String getTypeClassifierAccessorName()
  {
    return findGenClassifier(getEcoreTypedElement().getEType()).getClassifierAccessorName();
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeClassifierAccessorName}. 
   */
  @Deprecated
  public String getTypeClassifier()
  {
    return getTypeClassifierAccessorName();
  }

  protected GenClass getContext()
  {
    for (EObject context = eContainer(); context != null; context = context.eContainer())
    {
      if (context instanceof GenClass)
      {
        return (GenClass)context;
      }
    }
    return null;
  }

  public boolean hasGenericType()
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      EGenericType eGenericType = getEcoreTypedElement().getEGenericType();
      return eGenericType != null && (eGenericType.getETypeParameter() != null || !eGenericType.getETypeArguments().isEmpty());
    }
    return false;
  }

  public String getRawType()
  {
    if (isFeatureMapType()) return getEffectiveFeatureMapWrapperInterface();
    if (isMapType()) return getEffectiveMapType();
    if (isListType()) return getEffectiveListType();
    if (isMapEntryType()) return getEffectiveMapType();
    if (isEObjectType()) return getEffectiveEObjectType();
    return getType(getContext(), getEcoreTypedElement().getEType(), false, true);
  }

  public String getRawBoundType()
  {
    if (isFeatureMapType()) return getEffectiveFeatureMapWrapperInterface();
    if (isMapType()) return getEffectiveMapType();
    if (isListType()) return getEffectiveListType();
    if (isMapEntryType()) return getEffectiveMapEntryType();
    if (isEObjectType()) return getEffectiveEObjectType();
    return getType(getContext(), getBoundType(getEcoreTypedElement().getEGenericType()), false, true);
  }

  @Deprecated
  public String getType()
  {
    return getType(getContext());
  }

  public String getType(GenClass context)
  {
    if (isFeatureMapType()) return getEffectiveFeatureMapWrapperInterface();
    if (isMapType()) return getEffectiveMapType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass());
    if (isMapEntryType()) 
      if (isListType())
        if (getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50) return getEffectiveListType();
        else return getEffectiveListType() + "<" + getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()) + ">";
      else return getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass());
    if (isListType()) return getEffectiveListType(context, getEcoreTypedElement().getEGenericType());
    if (isEObjectType()) return getEffectiveEObjectType();
    if (isListDataType() && getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      return "java.util.List<" + getType(context, getListDataType().getEcoreDataType(), true) + ">";
    }
    return getType(context, getEcoreTypedElement().getEGenericType(), false);
  }

  public String getRawImportedType()
  {
    if (isFeatureMapType()) return getGenModel().getImportedName(getEffectiveFeatureMapWrapperInterface());
    if (isMapType()) return getGenModel().getImportedName(getEffectiveMapType());
    if (isListType()) return getGenModel().getImportedName(getEffectiveListType());
    if (isMapEntryType()) return getGenModel().getImportedName(getEffectiveMapEntryType());
    if (isEObjectType()) return getGenModel().getImportedName(getEffectiveEObjectType());
    return getImportedType(null, getEcoreTypedElement().getEType(), false);
  }

  public String getRawImportedBoundType()
  {
    if (isFeatureMapType()) return getGenModel().getImportedName(getEffectiveFeatureMapWrapperInterface());
    if (isMapType()) return getGenModel().getImportedName(getEffectiveMapType());
    if (isListType()) return getGenModel().getImportedName(getEffectiveListType());
    if (isMapEntryType()) return getGenModel().getImportedName(getEffectiveMapEntryType());
    if (isEObjectType()) return getGenModel().getImportedName(getEffectiveEObjectType());
    return getImportedType(null, getBoundType(getEcoreTypedElement().getEGenericType()), false, true);
  }

  @Deprecated
  public String getImportedType()
  {
    return getImportedType(getContext());
  }

  public String getImportedType(GenClass context)
  {
    if (isFeatureMapType()) return getGenModel().getImportedName(getEffectiveFeatureMapWrapperInterface());
    if (isMapType()) return getGenModel().getImportedName(getEffectiveMapType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()));
    if (isMapEntryType()) 
      if (isListType())
        if (getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50) return getGenModel().getImportedName(getEffectiveListType());
        else return getGenModel().getImportedName(getEffectiveListType() + "<" + getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()) + ">");
      else return getGenModel().getImportedName(getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()));
    if (isListType()) return getGenModel().getImportedName(getEffectiveListType(context, getEcoreTypedElement().getEGenericType()));
    if (isEObjectType()) return getGenModel().getImportedName(getEffectiveEObjectType());
    if (isListDataType() && getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      return getGenModel().getImportedName("java.util.List") + "<" + getImportedType(context, getListDataType().getEcoreDataType(), true) + ">";
    }
    return getImportedType(context, getEcoreTypedElement().getEGenericType(), false);
  }

  @Deprecated
  public String getObjectType()
  {
    return getObjectType(getContext()); 
  }

  public String getObjectType(GenClass context)
  {
    if (isFeatureMapType()) return getGenModel().getImportedName(getEffectiveFeatureMapWrapperInterface());
    if (isMapType()) return getGenModel().getImportedName(getEffectiveMapType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()));
    if (isMapEntryType())
      if (isListType())
        if (getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50) return getGenModel().getImportedName(getEffectiveListType());
        else return getGenModel().getImportedName(getEffectiveListType() + "<" + getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()) + ">");
      else return getGenModel().getImportedName(getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()));
    if (isListType()) return getGenModel().getImportedName(getEffectiveListType(context, getEcoreTypedElement().getEGenericType()));
    if (isEObjectType()) return getGenModel().getImportedName(getEffectiveEObjectType());
    if (isListDataType() && getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      return getGenModel().getImportedName("java.util.List") + "<" + getImportedType(context, getListDataType().getEcoreDataType(), true) + ">";
    }
    return getImportedType(context, getEcoreTypedElement().getEGenericType(), true);
  }

  public String getQualifiedObjectType(GenClass context)
  {
    if (isFeatureMapType()) return getEffectiveFeatureMapWrapperInterface();
    if (isMapType()) return getEffectiveMapType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass());
    if (isMapEntryType())
      if (isListType())
        if (getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50) return getEffectiveListType();
        else return getEffectiveListType() + "<" + /*x*/getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()) + ">";
      else return getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass());
    if (isListType()) return getEffectiveListType(context, getEcoreTypedElement().getEGenericType());
    if (isEObjectType()) return getEffectiveEObjectType();
    if (isListDataType() && getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      return "java.util.List<" + getType(context, getListDataType().getEcoreDataType(), true) + ">";
    }
    return getType(context, getEcoreTypedElement().getEGenericType(), true);
  }

  @Deprecated
  public String getImportedInternalType()
  {
    return getImportedInternalType(getContext());
  }

  public String getImportedInternalType(GenClass context)
  {
    if (isFeatureMapType())
       return
         isBlank(getGenModel().getFeatureMapWrapperInternalInterface()) ?
             getImportedEffectiveFeatureMapWrapperClass() :
             getImportedEffectiveFeatureMapWrapperInternalInterface();
    if (isMapType()) return getGenModel().getImportedName("org.eclipse.emf.common.util.EMap") + getImportedMapTemplateArguments(context); 
    if (isMapEntryType())
      if (isListType())
        if (getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50) return getGenModel().getImportedName("org.eclipse.emf.common.util.EList");
        else return getGenModel().getImportedName("org.eclipse.emf.common.util.EList") + "<" + getGenModel().getImportedName("java.util.Map$Entry") + getImportedMapTemplateArguments(context) + ">"; 
      else return getGenModel().getImportedName("java.util.Map$Entry") + getImportedMapTemplateArguments(context); 
    if (isListType()) return getGenModel().getImportedName("org.eclipse.emf.common.util.EList") + getListTemplateArguments(context);
    if (isListDataType() && getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      return getGenModel().getImportedName("java.util.List") + "<" + getImportedType(context, getListDataType().getEcoreDataType(), true) + ">";
    }
    return getImportedType(context, getEcoreTypedElement().getEGenericType(), false);
  }

  public boolean isFeatureMapType()
  {
    EClassifier type = getEcoreTypedElement().getEType();
    return type != null && isFeatureMapEntry(getEcoreTypedElement().getEType().getInstanceClassName());
  }

  protected static boolean isFeatureMapEntry(String name)
  {
    return "org.eclipse.emf.ecore.util.FeatureMap.Entry".equals(name) || "org.eclipse.emf.ecore.util.FeatureMap$Entry".equals(name);
  } 

  public boolean isWrappedFeatureMapType()
  {
    return 
      isFeatureMapType() &&  
        !isBlank(getGenModel().getFeatureMapWrapperInterface()) &&
        !isBlank(getGenModel().getFeatureMapWrapperClass());    
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #isWrappedFeatureMapType}. 
   */
  @Deprecated
  public boolean isFeatureMapWrapped()
  {
    return isWrappedFeatureMapType();
  }

  @Override
  public GenJDKLevel getEffectiveComplianceLevel()
  {
    return super.getEffectiveComplianceLevel();
  }

  @Override
  public boolean isEffectiveSuppressEMFTypes()
  {
    return super.isEffectiveSuppressEMFTypes();
  }

  @Override
  public String getImportedEffectiveFeatureMapWrapperInternalInterface()
  {
    return super.getImportedEffectiveFeatureMapWrapperInternalInterface();
  }

  @Override
  public String getImportedEffectiveFeatureMapWrapperClass()
  {
    return super.getImportedEffectiveFeatureMapWrapperClass();
  }

  public boolean isListType()
  {
    ETypedElement eTypedElement = getEcoreTypedElement();
    return 
      eTypedElement.isMany() || 
        isFeatureMapType() ||
        eTypedElement.getUpperBound() == ETypedElement.UNSPECIFIED_MULTIPLICITY &&
          eTypedElement instanceof EStructuralFeature && 
          XMLTypePackage.eNS_URI.equals(getExtendedMetaData().getNamespace((EStructuralFeature)eTypedElement));
  }

  public boolean isListDataType()
  {
    GenDataType genDataType = getTypeGenDataType();
    return genDataType != null && ((GenDataTypeImpl)genDataType).getEffectiveItemType() != null;
  }

  public GenDataType getListDataType()
  {
    GenDataType genDataType = getTypeGenDataType();
    return genDataType == null ? null : ((GenDataTypeImpl)genDataType).getEffectiveItemType();
  }

  public String getListTemplateArguments(GenClass context)
  {
    return getEffectiveComplianceLevel().getValue() <= GenJDKLevel.JDK14 ? "" : "<" + getListItemType(context) + ">";
  }

  @Deprecated
  public String getListItemType()
  {
    return getListItemType(getContext());
  }

  public String getListItemType(GenClass context)
  {
    if (isMapEntryType()) return getGenModel().getImportedName(getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()));
    return getImportedType(context, getEcoreTypedElement().getEGenericType(), true);
  }

  public String getRawListItemType()
  {
    return getImportedType(getContext(), getEcoreTypedElement().getEType(), true, true);
  }

  public String getRawListItemType(GenClass context)
  {
    return getGenModel().useGenerics() ? getTypeArgument(context, getEcoreTypedElement().getEGenericType(), true, true) : getImportedType(context, getEcoreTypedElement().getEType(), true, true);
  }

  public String getArrayItemType(GenClass context)
  {
    if (getGenModel().useGenerics())
    {
      EGenericType eGenericType = getEcoreTypedElement().getEGenericType();
      eGenericType = EcoreUtil.getReifiedType(context.getEcoreClass(), eGenericType);
      ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
      if (eTypeParameter != null)
      {
        EList<EGenericType> eBounds = eTypeParameter.getEBounds();
        if (eBounds.isEmpty())
        {
          return getImportedType(context, eGenericType.getERawType(), true, true);
        }
        else
        {
          return getTypeArgument(context, eBounds.get(0), true, false);
        }
      }
      else
      {
        return getTypeArgument(context, eGenericType, true, false);
      }
    }
    else
    {
      return getRawListItemType(null);
    }
  }

  @Deprecated
  public String getQualifiedListItemType()
  {
    return getQualifiedListItemType(getContext());
  }

  public String getQualifiedListItemType(GenClass context)
  {
    if (isMapEntryType()) return getEffectiveMapEntryType(context, getEcoreTypedElement().getEGenericType(), getMapEntryTypeGenClass()).replace('$', '.');
    return getType(context, getEcoreTypedElement().getEGenericType(), true).replace('$', '.');
  }

  public boolean isMapType()
  {
    return isListType() && getMapGenClass() != null;
  }

  public boolean isMapEntryType()
  {
    return getMapEntryTypeGenClass() != null;
  }

  public GenClass getMapEntryTypeGenClass()
  {
    EClassifier eType = getEcoreTypedElement().getEType();
    if (eType instanceof EClass && isJavaUtilMapEntry(eType.getInstanceClassName()))
    {
      GenClass genClass = findGenClass((EClass)eType);
      if (genClass.isMapEntry())
      {
        return genClass;
      }
    }
    return null;
  }

  public String getImportedMapEntryType()
  {
    GenClass genClass = getMapEntryTypeGenClass();
    return genClass == null ? null : genClass.getImportedClassName();
  }
  
  public String getImportedMapKeyType(GenClass context)
  {
    EGenericType eGenericType = getEcoreTypedElement().getEGenericType();
    if (eGenericType.getETypeArguments().size() == 2)
    {
      return getTypeArgument(context,eGenericType.getETypeArguments().get(0), true, false);
    }
    else
    {
      GenClass genClass = getMapEntryTypeGenClass();
      return genClass == null ? null : genClass.getMapEntryKeyFeature().getImportedType(context);
    }
  }

  public String getImportedMapValueType(GenClass context)
  {
    EGenericType eGenericType = getEcoreTypedElement().getEGenericType();
    if (eGenericType.getETypeArguments().size() == 2)
    {
      return getTypeArgument(context,eGenericType.getETypeArguments().get(1), true, false);
    }
    else
    {
      GenClass genClass = getMapEntryTypeGenClass();
      return genClass == null ? null : genClass.getMapEntryValueFeature().getImportedType(context);
    }
  }
  
  public String getImportedMapTemplateArguments(GenClass context)
  {
    return 
      getEffectiveComplianceLevel().getValue() <= GenJDKLevel.JDK14 ? 
        ""  : 
        "<" + getImportedMapKeyType(context) + ", " + getImportedMapValueType(context) + ">";
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getMapEntryTypeGenClass}.
   */
  @Deprecated
  public GenClass getMapGenClass()
  {
    return getMapEntryTypeGenClass();
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getImportedMapEntryType}.
   */
  @Deprecated
  public String getMapItemType()
  {
    return getImportedMapEntryType();
  }

  protected boolean isEObjectType()
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      EGenericType eGenericType = getEcoreTypedElement().getEGenericType();
      if (eGenericType.getETypeParameter() != null)
      {
        return false;
      }
    }
    EClassifier type = getEcoreTypedElement().getEType();
    GenClassifier genClassifier = findGenClassifier(type);
    return genClassifier instanceof GenClass &&  ((GenClass)genClassifier).isEObject();
  }

  protected boolean hasEObjectBound(ETypeParameter eTypeParameter)
  {
    for (EGenericType eBound : eTypeParameter.getEBounds())
    {
      if (eBound.getETypeParameter() != null && hasEObjectExtensionBound(eBound.getETypeParameter()) ||
            eBound.getEClassifier() instanceof EClass  && findGenClass((EClass)eBound.getEClassifier()).isEObject())
      {
        return true;
      }
    }
    return false;
  }

  protected boolean isEObjectExtensionType()
  {
    if (getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50)
    {
      EGenericType eGenericType = getEcoreTypedElement().getEGenericType();
      if (eGenericType.getETypeParameter() != null)
      {
        return hasEObjectExtensionBound(eGenericType.getETypeParameter());
      }
    }
    EClassifier type = getEcoreTypedElement().getEType();
    return type instanceof EClass && findGenClass((EClass)type).isEObjectExtension();
  }

  protected boolean hasEObjectExtensionBound(ETypeParameter eTypeParameter)
  {
    for (EGenericType eBound : eTypeParameter.getEBounds())
    {
      if (eBound.getETypeParameter() != null ? 
            hasEObjectExtensionBound(eBound.getETypeParameter()) :
            eBound.getEClassifier() instanceof EClass && findGenClass((EClass)eBound.getEClassifier()).isEObjectExtension())
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean isUncheckedCast()
  {
    return isUncheckedCast(getContext());
  }

  public boolean isUncheckedCast(GenClass context)
  {
    GenClassifier type = getTypeGenClassifier();
    if (type != null && type.isUncheckedCast())
    {
      return true;
    }
    if (isListType() || isListDataType() || isMapEntryType())
    {
      return true;
    }
    EGenericType actualEGenericType = getEcoreTypedElement().getEGenericType();
    if (actualEGenericType == null)
    {
      return false;
    }
    String substitutedType = getTypeArgument(context, actualEGenericType, false, false);
    Diagnostic diagnostic = EcoreValidator.EGenericTypeBuilder.INSTANCE.parseInstanceTypeName(substitutedType);
    EGenericType eGenericType = (EGenericType)diagnostic.getData().get(0);
    if (eGenericType != null)
    {
      // Type parameter casts can't be checked.
      //
      if (eGenericType.getETypeParameter() != null)
      {
        return true;
      }
      else
      {
        EClassifier eClassifier = eGenericType.getEClassifier();
        if (eClassifier != null)
        {
          String instanceTypeName = eClassifier.getInstanceTypeName();
          if (instanceTypeName != null)
          {
            int index = instanceTypeName.indexOf('[');
            if (index != -1)
            {
              instanceTypeName = instanceTypeName.substring(0, index);
            }
            if (instanceTypeName.indexOf('.') == -1 && !CodeGenUtil.isJavaPrimitiveType(instanceTypeName))
            {
              return true;
            }
          }
          // If there are any arguments and they are not unbounded wildcards, casts can't be checked.
          //
          for (EGenericType eTypeArgument : eGenericType.getETypeArguments())
          {
            if (eTypeArgument.getETypeParameter() != null ||
                  eTypeArgument.getEClassifier() != null ||
                  eTypeArgument.getEUpperBound() != null ||
                  eTypeArgument.getELowerBound() != null)
            {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  public String getEObjectCast()
  {
    return !isEObjectExtensionType() || (isEObjectType() && isEffectiveSuppressEMFTypes()) ?
      "(" + getGenModel().getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  }

  public String getInternalTypeCast()
  {
    return isEObjectType() && isEffectiveSuppressEMFTypes() ? 
      "(" + getGenModel().getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  }

  @Deprecated
  public String getNonEObjectInternalTypeCast()
  {
    return getNonEObjectInternalTypeCast(getContext());
  }

  public String getNonEObjectInternalTypeCast(GenClass context)
  {
    return isEObjectType() ? "" : "(" + getImportedInternalType(context) + ")";
  }
  
  public String getRawTypeCast()
  {
    return getType(null).equals(getRawType()) ? "" : "(" + getImportedType(null) + ")";
  }

  public boolean isPrimitiveType()
  {
    return !isListType() && isPrimitiveType(getEcoreTypedElement().getEType());
  }

  public String getPrimitiveValueFunction()
  {
    Class<?> instanceClass = getInstanceClass(getEcoreTypedElement().getEType());
    if (instanceClass == java.lang.Boolean.TYPE)
      return "booleanValue";
    if (instanceClass == java.lang.Byte.TYPE)
      return "byteValue";
    if (instanceClass == java.lang.Character.TYPE)
      return "charValue";
    if (instanceClass == java.lang.Double.TYPE)
      return "doubleValue";
    if (instanceClass == java.lang.Float.TYPE)
      return "floatValue";
    if (instanceClass == java.lang.Integer.TYPE)
      return "intValue";
    if (instanceClass == java.lang.Long.TYPE)
      return "longValue";
    if (instanceClass == java.lang.Short.TYPE)
      return "shortValue";
    return null;
  }

  public boolean isBooleanType()
  {
    return isPrimitiveType() && getInstanceClass(getEcoreTypedElement().getEType()) == java.lang.Boolean.TYPE;
  }

  public boolean isStringType()
  {
    return !isListType() && getInstanceClass(getEcoreTypedElement().getEType()) == java.lang.String.class;
  }

  // Like isStringType(), but still returns true even if multiplicity-many.
  //
  public boolean isStringBasedType()
  {
    return getInstanceClass(getEcoreTypedElement().getEType()) == java.lang.String.class;
  }

  public boolean isEnumType()
  {
    return !isListType() && getEcoreTypedElement().getEType() instanceof EEnum;
  }

  public boolean isEnumBasedType()
  {
    return getEcoreTypedElement().getEType() instanceof EEnum;
  }

  public GenEnum getTypeGenEnum()
  {
    EClassifier eType = getEcoreTypedElement().getEType();
    return eType instanceof EEnum ? findGenEnum((EEnum)eType) : null;
  }

  public GenDataType getTypeGenDataType()
  {
    EClassifier eType = getEcoreTypedElement().getEType();
    return eType instanceof EDataType ? findGenDataType((EDataType)eType) : null;
  }

  public GenClass getTypeGenClass()
  {
    EClassifier eType = getEcoreTypedElement().getEType();
    return eType instanceof EClass ? findGenClass((EClass)eType) : null;
  }

  public GenClassifier getTypeGenClassifier()
  {
    EClassifier eType = getEcoreTypedElement().getEType();
    return eType == null ? null : findGenClassifier(eType);
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenEnum}.
   */
  @Deprecated
  public GenEnum getGenEnumType()
  {
    return getTypeGenEnum();
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenDataType}.
   */
  @Deprecated
  public GenDataType getGenDataTypeType()
  {
    return getTypeGenDataType();
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenClass}.
   */
  @Deprecated
  public GenClass getGenClassType()
  {
    return getTypeGenClass();
  }

  public String getLowerBound()
  {
    return String.valueOf(getEcoreTypedElement().getLowerBound());
  }

  public String getUpperBound()
  {
    return String.valueOf(getEcoreTypedElement().getUpperBound());
  }

  public boolean isUnique()
  {
    return getEcoreTypedElement().isUnique();
  }

  public String getUniqueFlag()
  {
    String result = !isUnique() ? "!" : "";
    return result + "IS_UNIQUE";
  }

  public String getOrderedFlag()
  {
    String result = !getEcoreTypedElement().isOrdered() ? "!" : "";
    return result + "IS_ORDERED";
  }

  protected void appendModelSetting(StringBuffer result, boolean qualified, String name, String value)
  {
    appendModelSetting(result, qualified ? getEcoreTypedElement().getName() : null, name, value);
  }

  protected String getMapModelInfo(boolean qualified, boolean includeFeatures)
  {
    GenClass mapGenClass = getMapEntryTypeGenClass();
    if (mapGenClass != null)
    {
      StringBuffer result = new StringBuffer();

      GenFeature keyFeature = mapGenClass.getMapEntryKeyFeature();
      GenFeature valueFeature = mapGenClass.getMapEntryValueFeature();
      String mapType = mapGenClass.getGenPackage().getInterfacePackageName() + '.' + mapGenClass.getEcoreClass().getName();
      boolean useGenerics = getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50;
      if (useGenerics)
      {
        mapType += 
           '<' + 
             getEcoreType(keyFeature.getEcoreFeature().getEGenericType()) + ", " + 
             getEcoreType(valueFeature.getEcoreFeature().getEGenericType()) + '>';
      }
      appendModelSetting(result, qualified, "mapType", mapType);

      if (includeFeatures && !useGenerics)
      {
        appendModelSetting(result, qualified, "keyType", getType(getContext(), keyFeature.getEcoreFeature().getEType(), false));
  
        appendModelSetting(result, qualified, "valueType", getType(getContext(), valueFeature.getEcoreFeature().getEType(), false));
      }

      return result.toString();
    }

    return null;
  }

  protected String getMultiplicityModelInfo(boolean qualified)
  {
    ETypedElement eTypedElement = getEcoreTypedElement();
    StringBuffer result = new StringBuffer();

    int lowerBound = eTypedElement.getLowerBound();
    if (lowerBound == 1)
    {
      appendModelSetting(result, qualified, "required", "true");
    }
    else if (lowerBound > 1)
    {
      appendModelSetting(result, qualified, "lower", Integer.toString(lowerBound));
    }
    
    int upperBound = eTypedElement.getUpperBound();
    if (upperBound > 1 || upperBound < -1)
    {
      appendModelSetting(result, qualified, "upper", Integer.toString(eTypedElement.getUpperBound()));
    }
    else if (upperBound == 1)
    {
      String typeName = getType(getContext(), eTypedElement.getEType(), false);
      if ("org.eclipse.emf.common.util.EList".equals(typeName) || "java.util.List".equals(typeName) || "org.eclipse.emf.ecore.util.FeatureMap$Entry".equals(typeName)) 
      {
        appendModelSetting(result, qualified, "many", "false");
      }
    }
    else if ((qualified || isFeatureMapType()) && upperBound == -1)
    {
      appendModelSetting(result, qualified, "many", "true");
    }

    return result.toString();
  }

} //GenTypedElementImpl
