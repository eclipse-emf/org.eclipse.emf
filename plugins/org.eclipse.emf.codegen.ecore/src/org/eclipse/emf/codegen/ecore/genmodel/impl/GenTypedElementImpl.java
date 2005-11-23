/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenTypedElementImpl.java,v 1.5 2005/11/23 13:32:11 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypedElement;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ETypedElement;

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
  protected EClass eStaticClass()
  {
    return GenModelPackage.eINSTANCE.getGenTypedElement();
  }

  public abstract ETypedElement getEcoreTypedElement();

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
  public String getTypeClassifier()
  {
    return getTypeClassifierAccessorName();
  }

  public String getType()
  {
    if (isFeatureMapType()) return getEffectiveFeatureMapWrapperInterface();
    if (isMapType()) return getEffectiveMapType();
    if (isListType()) return getEffectiveListType();
    if (isEObjectType()) return getEffectiveEObjectType();
    return getType(getEcoreTypedElement().getEType(), false);
  }

  public String getImportedType()
  {
    if (isFeatureMapType()) return getGenModel().getImportedName(getEffectiveFeatureMapWrapperInterface());
    if (isMapType()) return getGenModel().getImportedName(getEffectiveMapType());
    if (isListType()) return getGenModel().getImportedName(getEffectiveListType());
    if (isEObjectType()) return getGenModel().getImportedName(getEffectiveEObjectType());
    return getImportedType(getEcoreTypedElement().getEType(), false);
  }

  public String getObjectType()
  {
    if (isFeatureMapType()) return getGenModel().getImportedName(getEffectiveFeatureMapWrapperInterface());
    if (isMapType()) return getGenModel().getImportedName(getEffectiveMapType());
    if (isListType()) return getGenModel().getImportedName(getEffectiveListType());
    if (isEObjectType()) return getGenModel().getImportedName(getEffectiveEObjectType());
    return getImportedType(getEcoreTypedElement().getEType(), true);
  }

  public String getImportedInternalType()
  {
    if (isFeatureMapType())
       return
         isBlank(getGenModel().getFeatureMapWrapperInternalInterface()) ?
             getImportedEffectiveFeatureMapWrapperClass() :
             getImportedEffectiveFeatureMapWrapperInternalInterface();
    if (isMapType()) return getGenModel().getImportedName("org.eclipse.emf.common.util.EMap");
    if (isListType()) return getGenModel().getImportedName("org.eclipse.emf.common.util.EList");
    return getImportedType(getEcoreTypedElement().getEType(), false);
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
  public boolean isFeatureMapWrapped()
  {
    return isWrappedFeatureMapType();
  }

  public boolean isEffectiveSuppressEMFTypes()
  {
    return super.isEffectiveSuppressEMFTypes();
  }

  public String getImportedEffectiveFeatureMapWrapperInternalInterface()
  {
    return super.getImportedEffectiveFeatureMapWrapperInternalInterface();
  }

  public String getImportedEffectiveFeatureMapWrapperClass()
  {
    return super.getImportedEffectiveFeatureMapWrapperClass();
  }

  public boolean isListType()
  {
    return getEcoreTypedElement().isMany() || isFeatureMapType();
  }

  public String getListItemType()
  {
    return getImportedType(getEcoreTypedElement().getEType(), true);
  }

  public String getQualifiedListItemType()
  {
    return getType(getEcoreTypedElement().getEType(), true).replace('$', '.');
  }

  public boolean isMapType()
  {
    return isListType() && getMapGenClass() != null;
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

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getMapEntryTypeGenClass}.
   */
  public GenClass getMapGenClass()
  {
    return getMapEntryTypeGenClass();
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getImportedMapEntryType}.
   */
  public String getMapItemType()
  {
    return getImportedMapEntryType();
  }

  protected boolean isEObjectType()
  {
    EClassifier type = getEcoreTypedElement().getEType();
    return type instanceof EClass && findGenClass((EClass)type).isEObject();
  }

  protected boolean isEObjectExtensionType()
  {
    EClassifier type = getEcoreTypedElement().getEType();
    return type instanceof EClass && findGenClass((EClass)type).isEObjectExtension();
  }

  public String getEObjectCast()
  {
    return isEObjectExtensionType() && !isEffectiveSuppressEMFTypes() ? 
      "" : "(" + getGenModel().getImportedName("org.eclipse.emf.ecore.EObject") + ")";
  }

  public String getInternalTypeCast()
  {
    return isEObjectType() && isEffectiveSuppressEMFTypes() ? 
      "(" + getGenModel().getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  }

  public String getNonEObjectInternalTypeCast()
  {
    return isEObjectType() ? "" : "(" + getImportedInternalType() + ")";
  }

  public boolean isPrimitiveType()
  {
    return !isListType() && isPrimitiveType(getEcoreTypedElement().getEType());
  }

  public String getPrimitiveValueFunction()
  {
    Class instanceClass = getInstanceClass(getEcoreTypedElement().getEType());
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

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenEnum}.
   */
  public GenEnum getGenEnumType()
  {
    return getTypeGenEnum();
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenDataType}.
   */
  public GenDataType getGenDataTypeType()
  {
    return getTypeGenDataType();
  }

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenClass}.
   */
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

      appendModelSetting(result, qualified, "mapType",
        mapGenClass.getGenPackage().getInterfacePackageName() + '.' + mapGenClass.getEcoreClass().getName());

      if (includeFeatures)
      {
        GenFeature keyFeature = mapGenClass.getMapEntryKeyFeature();
        appendModelSetting(result, qualified, "keyType", getType(keyFeature.getEcoreFeature().getEType(), false));
  
        GenFeature valueFeature = mapGenClass.getMapEntryValueFeature();
        appendModelSetting(result, qualified, "valueType", getType(valueFeature.getEcoreFeature().getEType(), false));
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
      String typeName = getType(eTypedElement.getEType(), false);
      if ("org.eclipse.emf.common.util.EList".equals(typeName) || "java.util.List".equals(typeName)) 
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
