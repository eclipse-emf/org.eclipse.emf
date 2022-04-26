/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.util;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;


/**
 *  An annotation validator for {@link ExtendedMetaData#ANNOTATION_URI ExtendedMetadata} annotations.
 *
 * @since 2.14
 */
public final class ExtendedMetaDataAnnotationValidator extends BasicEAnnotationValidator
{
  public static final ExtendedMetaDataAnnotationValidator INSTANCE = new ExtendedMetaDataAnnotationValidator();

  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.annotation.extended.meta.data";

  static
  {
    // Check that the instance is registered properly...
    if (!EAnnotationValidator.Registry.INSTANCE.containsKey(ExtendedMetaData.ANNOTATION_URI))
    {
      // In a stand alone application, we'll need to explicitly register it.
      EAnnotationValidator.Registry.INSTANCE.put(ExtendedMetaData.ANNOTATION_URI, INSTANCE);
    }

    // Force eager initialization; in a stand alone application, the dynamic model won't be registered until this class is initialized.
    PropertySwitch.GETTERS.isEmpty();
  }

  /**
   * @see SpecializedExtendedMetaData#reportBadValue(Object, DiagnosticChain, int, String, Object...)
   */
  public static final int INVALID_VALUE_LITERAL = BasicEAnnotationValidator.INVALID_VALUE_LITERAL;

  /**
   * @see SpecializedExtendedMetaData#reportIgnoredEntry(Map.Entry, DiagnosticChain, String, Object...)
   */
  public static final int IGNORED_ENTRY = BasicEAnnotationValidator.IGNORED_ENTRY;

  private static String INSTANCE_VALUE = "extended_meta_data_instance_value";

  public ExtendedMetaDataAnnotationValidator()
  {
    super(ExtendedMetaData.ANNOTATION_URI, "ExtendedMetaData", DIAGNOSTIC_SOURCE);
  }

  @Override
  protected ResourceLocator getResourceLocator()
  {
    return getEcoreResourceLocator();
  }

  @Override
  protected EObject initialize(EObject eObject, EAnnotation eAnnotation)
  {
    ModelObject result = (ModelObject)eObject;
    result.setElement((ENamedElement)eAnnotation.getEModelElement());
    return result;
  }

  @Override
  protected boolean isApplicable(EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return ((ModelObject)eObject).isApplicable(eStructuralFeature);
  }

  @Override
  protected boolean isValidLocation(EAnnotation eAnnotation, EModelElement eModelElement)
  {
    return getPropertyClass(eModelElement) != null;
  }

  @Override
  protected List<EClass> getPropertyClasses(EModelElement eModelElement)
  {
    EClass propertyClass = getPropertyClass(eModelElement);
    return propertyClass == null ? Collections.<EClass> emptyList() : Collections.<EClass> singletonList(propertyClass);
  }

  private EClass getPropertyClass(EModelElement eModelElement)
  {
    final AtomicReference<EClass> result = new AtomicReference<EClass>();
    new PropertySwitch()
      {
        @Override
        protected void addFeatures(EClass eClass)
        {
          result.set(eClass);
        }
      }.doSwitch(eModelElement);
    return result.get();
  }

  @Override
  protected boolean validateDetails(EAnnotation eAnnotation, EModelElement eModelElement, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (context == null)
    {
      context = new HashMap<Object, Object>();
    }

    EClass propertyClass = getPropertyClass(eModelElement);
    ModelObject modelObject = (ModelObject)createInstance(propertyClass, eAnnotation);
    try
    {
      context.put(INSTANCE_VALUE, modelObject);
      return super.validateDetails(eAnnotation, eModelElement, diagnostics, context);
    }
    finally
    {
      context.remove(INSTANCE_VALUE);
    }
  }

  @Override
  protected boolean validateFeatureDetail(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EStructuralFeature feature,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    boolean result = getModelObject(context).validateEntry(this, entry, diagnostics, context);
    if (result)
    {
      result = super.validateFeatureDetail(eAnnotation, eModelElement, entry, feature, diagnostics, context);
    }
    return result;
  }

  @Override
  protected boolean validateReferenceDetailValueLiteral(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EReference reference,
    String literalValue,
    List<Object> referenceValues,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    return getModelObject(context).validateEntryValueLiteral(this, entry, literalValue, referenceValues, diagnostics, context);
  }

  @Override
  protected boolean validateFeatureDetailValue(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EStructuralFeature feature,
    List<Object> values,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    return getModelObject(context).validateEntryValue(this, entry, values, diagnostics, context);
  }

  private ModelObject getModelObject(Map<Object, Object> context)
  {
    return (ModelObject)context.get(INSTANCE_VALUE);
  }

  public Collection<?> filterChoiceOfValues(EObject eObject, EStructuralFeature eStructuralFeature, Collection<?> choiceOfValues)
  {
    return ((ModelObject)eObject).filterChoiceOfValues(eStructuralFeature, choiceOfValues);
  }

  static class SpecializedExtendedMetaData extends BasicExtendedMetaData
  {
    private static final Map<ResourceSet, SpecializedExtendedMetaData> EXTENDED_META_DATA_CACHE = new WeakHashMap<ResourceSet, SpecializedExtendedMetaData>();

    public static SpecializedExtendedMetaData getExtendedMetaData(EObject eObject)
    {
      synchronized (EXTENDED_META_DATA_CACHE)
      {
        ResourceSet resourceSet = null;
        Resource resource = eObject.eResource();
        if (resource != null)
        {
          resourceSet = resource.getResourceSet();
        }

        SpecializedExtendedMetaData specializedExtendedMetaData = EXTENDED_META_DATA_CACHE.get(resourceSet);
        if (specializedExtendedMetaData == null)
        {
          if (resourceSet == null)
          {
            EPackageRegistryImpl registry = new EPackageRegistryImpl(EPackage.Registry.INSTANCE);
            specializedExtendedMetaData = new SpecializedExtendedMetaData(registry);
          }
          else
          {
            final ResourceSet theResourceSet = resourceSet;
            EPackage.Registry ePackageRegistry = new EPackageRegistryImpl(resourceSet.getPackageRegistry())
                {
                  private static final long serialVersionUID = 1L;

                  @Override
                  public EPackage getEPackage(String nsURI)
                  {
                    EPackage result = super.getEPackage(nsURI);
                    if (result == null)
                    {
                      for (TreeIterator<Notifier> allContents = theResourceSet.getAllContents(); allContents.hasNext(); )
                      {
                        Notifier content = allContents.next();
                        if (content instanceof EPackage)
                        {
                          EPackage ePackage = (EPackage)content;
                          String ePackageNsURI = ePackage.getNsURI();
                          if (ePackageNsURI == null ? nsURI == null : ePackageNsURI.equals(nsURI))
                          {
                            return ePackage;
                          }
                        }
                        else if (content instanceof EObject)
                        {
                          allContents.prune();
                        }
                      }
                    }
                    return result;
                  }
                };
            specializedExtendedMetaData = new SpecializedExtendedMetaData(ePackageRegistry);
          }
          EXTENDED_META_DATA_CACHE.put(resourceSet, specializedExtendedMetaData);
        }

        return specializedExtendedMetaData;
      }
    }

    public SpecializedExtendedMetaData(EPackage.Registry registry)
    {
      super(registry);
      extendedMetaDataHolderCache = new HashMap<EModelElement, Object>()
        {
          private static final long serialVersionUID = 1L;

          @Override
          public Object put(EModelElement key, Object value)
          {
            // Don't cache anything.
            return null;
          }
        };
      putPackage(ExtendedMetaData.XML_SCHEMA_URI, getPackage(XMLTypePackage.eNS_URI));
    }

    @Override
    protected boolean isFeatureKindSpecific()
    {
      return false;
    }

    @Override
    protected boolean isFeatureNamespaceMatchingLax()
    {
      return true;
    }

    public void setDocumentRoot(EClass eClass, boolean documentRoot)
    {
      if (documentRoot)
      {
        setDocumentRoot(eClass);

        EReference xmlnsPrefixMapFeature = getXMLNSPrefixMapFeature(eClass);
        if (xmlnsPrefixMapFeature == null)
        {
          eClass.getEStructuralFeatures().add(EcoreUtil.copy(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP));
        }
        EReference xsiSchemaLocationMapFeature = getXSISchemaLocationMapFeature(eClass);
        if (xsiSchemaLocationMapFeature == null)
        {
          eClass.getEStructuralFeatures().add(EcoreUtil.copy(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION));
        }
      }
      else
      {
        EAnnotation eAnnotation = getAnnotation(eClass, false);
        if (eAnnotation != null)
        {
          eAnnotation.getDetails().removeKey("name");
        }
      }
    }

    @Override
    public void setContentKind(EClass eClass, int kind)
    {
      super.setContentKind(eClass, kind);
      if (kind == MIXED_CONTENT)
      {
        EAttribute mixedFeature = getMixedFeature(eClass);
        if (mixedFeature == null)
        {
          eClass.getEStructuralFeatures().add(EcoreUtil.copy(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__MIXED));
        }

        for (EStructuralFeature eStructuralFeature : getElements(eClass))
        {
          eStructuralFeature.setUpperBound(ETypedElement.UNSPECIFIED_MULTIPLICITY);
          eStructuralFeature.setDerived(true);
          eStructuralFeature.setVolatile(true);
          eStructuralFeature.setTransient(true);
        }
      }
      else if (kind == SIMPLE_CONTENT)
      {
        EStructuralFeature simpleFeature = getSimpleFeature(eClass);
        if (simpleFeature == null)
        {
          simpleFeature = EcoreFactory.eINSTANCE.createEAttribute();
          simpleFeature.setName("simple");
          simpleFeature.setEType(XMLTypePackage.Literals.STRING);
          setFeatureKind(simpleFeature, SIMPLE_FEATURE);
          eClass.getEStructuralFeatures().add(simpleFeature);
        }
      }
    }

    @Override
    public void setFeatureKind(EStructuralFeature eStructuralFeature, int kind)
    {
      super.setFeatureKind(eStructuralFeature, kind);
      if (kind == ELEMENT_WILDCARD_FEATURE || kind == ATTRIBUTE_WILDCARD_FEATURE || kind == GROUP_FEATURE)
      {
        if (eStructuralFeature instanceof EAttribute)
        {
          eStructuralFeature.setEType(EcorePackage.Literals.EFEATURE_MAP_ENTRY);
        }
      }
    }

    @Override
    public void setNamespace(EStructuralFeature eStructuralFeature, String namespace)
    {
      super.setNamespace(eStructuralFeature, "".equals(namespace) ? null : namespace);
    }

    @Override
    public void setItemType(EDataType eDataType, EDataType itemType)
    {
      super.setItemType(eDataType, itemType);
      if (itemType != null)
      {
        super.setBaseType(eDataType, null);
        super.setMemberTypes(eDataType, Collections.<EDataType> emptyList());
      }
    }

    @Override
    public void setBaseType(EDataType eDataType, EDataType baseType)
    {
      super.setBaseType(eDataType, baseType);
      if (baseType != null)
      {
        super.setItemType(eDataType, null);
        super.setMemberTypes(eDataType, Collections.<EDataType> emptyList());
      }
    }

    @Override
    public void setMemberTypes(EDataType eDataType, List<EDataType> memberTypes)
    {
      super.setMemberTypes(eDataType, memberTypes);
      if (!memberTypes.isEmpty())
      {
        super.setBaseType(eDataType, null);
        super.setItemType(eDataType, null);
      }
    }

    @Override
    public void setMinExclusiveFacet(EDataType eDataType, String literal)
    {
      super.setMinExclusiveFacet(eDataType, literal);
      if (literal != null)
      {
        super.setMinInclusiveFacet(eDataType, null);
      }
    }

    @Override
    public void setMinInclusiveFacet(EDataType eDataType, String literal)
    {
      super.setMinInclusiveFacet(eDataType, literal);
      if (literal != null)
      {
        super.setMinExclusiveFacet(eDataType, literal);
      }
    }

    @Override
    public void setMaxExclusiveFacet(EDataType eDataType, String literal)
    {
      super.setMaxExclusiveFacet(eDataType, literal);
      if (literal != null)
      {
        super.setMaxInclusiveFacet(eDataType, null);
      }
    }

    @Override
    public void setMaxInclusiveFacet(EDataType eDataType, String literal)
    {
      super.setMaxInclusiveFacet(eDataType, literal);
      if (literal != null)
      {
        super.setMaxExclusiveFacet(eDataType, literal);
      }
    }

    @Override
    public void setLengthFacet(EDataType eDataType, int length)
    {
      super.setLengthFacet(eDataType, length);
      if (length != -1)
      {
        super.setMinLengthFacet(eDataType, -1);
        super.setMaxLengthFacet(eDataType, -1);
      }
    }

    @Override
    public void setMinLengthFacet(EDataType eDataType, int length)
    {
      super.setMinLengthFacet(eDataType, length);
      if (length != -1)
      {
        super.setLengthFacet(eDataType, -1);
      }
    }

    @Override
    public void setMaxLengthFacet(EDataType eDataType, int length)
    {
      super.setMaxLengthFacet(eDataType, length);
      if (length != -1)
      {
        super.setLengthFacet(eDataType, -1);
      }
    }

    protected ExtendedMetaDataAnnotationValidator getValidator(Map<Object, Object> context)
    {
      return (ExtendedMetaDataAnnotationValidator)context.get("EXTENDED_META_DATA_ANNOTATION_VALIDATOR");
    }

    protected BasicDiagnostic createDiagnostic(int severity, int code, String message, Object... data)
    {
      return new BasicDiagnostic(severity, DIAGNOSTIC_SOURCE, code, message, data);
    }

    protected void reportIgnoredEntry(Map.Entry<String, String> entry, DiagnosticChain diagnostics, String key, Object... substitutions)
    {
      diagnostics.add(
        createDiagnostic(Diagnostic.WARNING, IGNORED_ENTRY, EcorePlugin.INSTANCE.getString(key, substitutions), entry, EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY));
    }

    protected void reportBadValue(Object value, DiagnosticChain diagnostics, int severity, String key, Object... substitutions)
    {
      diagnostics.add(createDiagnostic(severity, INVALID_VALUE_LITERAL, EcorePlugin.INSTANCE.getString(key, substitutions), value));
    }

    public boolean validateDataTypeEntry(EDataType eDataType, Map.Entry<String, String> entry, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      String key = entry.getKey();

      Class<?> instanceClass = eDataType.getInstanceClass();
      if (instanceClass == null && eDataType instanceof EEnum)
      {
        instanceClass = Enumerator.class;
      }

      if ("totalDigits".equals(key) || "fractionDigits".equals(key))
      {
        if (instanceClass != BigDecimal.class)
        {
          if (diagnostics != null)
          {
            reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotBigDecimal_diagnostic", key);
          }
          return false;
        }
        else
        {
          return true;
        }
      }
      else if ("length".equals(key) || "maxLength".equals(key) || "minLength".equals(key))
      {
        boolean result = hasLength(instanceClass);
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotHasLength_diagnostic", key);
        }
        return result;
      }
      else if ("itemType".equals(key))
      {
        boolean result = instanceClass == List.class;
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotList_diagnostic", key);
        }
        return result;
      }
      else if ("baseType".equals(key))
      {
        boolean result = !(eDataType instanceof EEnum);
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotRestrictEnum_diagnostic", key);
        }
        return result;
      }
      else if ("memberTypes".equals(key))
      {
        boolean result = !(eDataType instanceof EEnum);
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotRestrictEnum_diagnostic", key);
        }
        return result;
      }
      else if ("maxInclusive".equals(key) || "maxExclusive".equals(key) || "minInclusive".equals(key) || "minExclusive".equals(key))
      {
        boolean result = instanceClass == null || isComparable(instanceClass);
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotComparable_diagnostic", key);
        }
        else if (eDataType instanceof EEnum)
        {
          result = false;
          if (!result && diagnostics != null)
          {
            reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotRestrictEnum_diagnostic", key);
          }
        }
        return result;
      }
      else if ("enumeration".equals(key))
      {
        boolean result = !(eDataType instanceof EEnum);
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotRestrictEnum_diagnostic", key);
        }
        return result;
      }
      else if ("pattern".equals(key))
      {
        boolean result = !(eDataType instanceof EEnum);
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotRestrictEnum_diagnostic", key);
        }
        return result;
      }
      else if ("whiteSpace".equals(key))
      {
        boolean result = !(eDataType instanceof EEnum);
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotRestrictEnum_diagnostic", key);
        }
        return result;
      }
      else
      {
        return true;
      }
    }

    private boolean isComparable(Class<?> instanceClass)
    {
      if (instanceClass == null)
      {
        return true;
      }
      else
      {
        return instanceClass == Enumerator.class || Comparable.class.isAssignableFrom(EcoreUtil.wrapperClassFor(instanceClass));
      }
    }

    private boolean hasLength(Class<?> instanceClass)
    {
      if (instanceClass == null)
      {
        return true;
      }
      else
      {
        return instanceClass == String.class || Collection.class.isAssignableFrom(instanceClass) || instanceClass.isArray();
      }
    }

    public boolean validatePackageEntry(EPackage ePackage, Map.Entry<String, String> entry, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      return true;
    }

    public boolean validateClassEntry(EClass eClass, Map.Entry<String, String> entry, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      return true;
    }

    public boolean validateStructuralFeatureEntry(EStructuralFeature eStructuralFeature, Map.Entry<String, String> entry, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      String key = entry.getKey();
      if ("affiliation".equals(key))
      {
        boolean result = true;
        int featureKind = getFeatureKind(eStructuralFeature);
        if (featureKind != ELEMENT_FEATURE || eStructuralFeature.getEContainingClass() == null || !isDocumentRoot(eStructuralFeature.getEContainingClass()))
        {
          result = false;
        }
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotGlobalElement_diagnostic", key);
        }
        return result;
      }
      else if ("processing".equals(key))
      {
        boolean result = true;
        int featureKind = getFeatureKind(eStructuralFeature);
        if (featureKind != ATTRIBUTE_WILDCARD_FEATURE && featureKind != ELEMENT_WILDCARD_FEATURE)
        {
          result = false;
        }
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotWildcard_diagnostic", key);
        }
        return result;
      }
      else if ("wildcards".equals(key))
      {
        boolean result = true;
        int featureKind = getFeatureKind(eStructuralFeature);
        if (featureKind != ATTRIBUTE_WILDCARD_FEATURE && featureKind != ELEMENT_WILDCARD_FEATURE)
        {
          result = false;
        }
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotWildcard_diagnostic", key);
        }
        return result;
      }
      else if ("namespace".equals(key))
      {
        boolean result = true;
        int featureKind = getFeatureKind(eStructuralFeature);
        if (featureKind != ELEMENT_FEATURE && featureKind != ATTRIBUTE_FEATURE && featureKind != GROUP_FEATURE)
        {
          result = false;
        }
        if (!result && diagnostics != null)
        {
          reportIgnoredEntry(entry, diagnostics, "_UI_ExtendeMetaDataAnnotationDetailNotElementAttributeOrGroup_diagnostic", key);
        }
        return result;
      }
      else
      {
        return true;
      }
    }

    public boolean validateDataTypeEntryValueLiteral(
      EDataType eDataType,
      Map.Entry<String, String> entry,
      String literalValue,
      List<Object> values,
      DiagnosticChain diagnostics,
      Map<Object, Object> context)
    {
      String key = entry.getKey();
      if ("baseType".equals(key) || "itemType".equals(key) || "memberTypes".equals(key))
      {
        EClassifier eClassifier = getEClassifier(eDataType.getEPackage(), literalValue);
        if (eClassifier == null)
        {
          if (diagnostics != null)
          {
            reportBadValue(literalValue, diagnostics, Diagnostic.WARNING, "_UI_ExtendeMetaDataAnnotationDetailNotTypeResolved_diagnostic", literalValue);
          }
          return false;
        }
        else if (!(eClassifier instanceof EDataType))
        {
          if (diagnostics != null)
          {
            reportBadValue(literalValue, diagnostics, Diagnostic.WARNING, "_UI_ExtendeMetaDataAnnotationDetailNotTypeResolvedCorrectly_diagnostic", literalValue);
          }
          return false;
        }
        else
        {
          values.add(eClassifier);
          return true;
        }
      }
      else
      {
        return true;
      }
    }

    public boolean validatePackageEntryValueLiteral(
      EPackage ePackage,
      Map.Entry<String, String> entry,
      String literalValue,
      List<Object> values,
      DiagnosticChain diagnostics,
      Map<Object, Object> context)
    {
      return true;
    }

    public boolean validateClassEntryValueLiteral(
      EClass eClass,
      Map.Entry<String, String> entry,
      String literalValue,
      List<Object> values,
      DiagnosticChain diagnostics,
      Map<Object, Object> context)
    {
      return true;
    }

    public boolean validateStructuralFeatureEntryValueLiteral(
      EStructuralFeature eStructuralFeature,
      Map.Entry<String, String> entry,
      String literalValue,
      List<Object> values,
      DiagnosticChain diagnostics,
      Map<Object, Object> context)
    {
      String key = entry.getKey();
      if ("group".equals(key))
      {
        EStructuralFeature group = getGroup(eStructuralFeature);
        if (group == null)
        {
          reportBadValue(literalValue, diagnostics, Diagnostic.WARNING, "_UI_ExtendeMetaDataAnnotationDetailNotGroupResolved_diagnostic", literalValue);
          return false;
        }
        else
        {
          values.add(group);
          return true;
        }
      }
      else if ("affiliation".equals(key))
      {
        EStructuralFeature affiliation = getAffiliation(eStructuralFeature);
        if (affiliation == null)
        {
          reportBadValue(literalValue, diagnostics, Diagnostic.WARNING, "_UI_ExtendeMetaDataAnnotationDetailNotAffiliationResolved_diagnostic", literalValue);
          return false;
        }
        else
        {
          values.add(affiliation);
          return true;
        }
      }
      else
      {
        return true;
      }
    }

    public boolean validateDataTypeEntryValue(EDataType eDataType, Map.Entry<String, String> entry, List<Object> values, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      String key = entry.getKey();
      boolean result = true;
      if ("baseType".equals(key) || "itemType".equals(key) || "memberTypes".equals(key))
      {
        for (Object value : values)
        {
          EDataType otherEDataType = (EDataType)value;
          Set<EDataType> inheritedTypes = getInheritedTypes(otherEDataType);
          if (inheritedTypes.contains(eDataType))
          {
            result = false;
            if (diagnostics == null)
            {
              break;
            }
            else
            {
              reportBadValue(
                inheritedTypes,
                diagnostics,
                Diagnostic.ERROR,
                "_UI_ExtendeMetaDataAnnotationDetailTypeCircular_diagnostic",
                getQualifiedName(getNamespace(eDataType), otherEDataType));
            }
          }
        }
      }
      return result;
    }

    protected Set<EDataType> getInheritedTypes(EDataType eDataType)
    {
      Set<EDataType> typesToVisit = new LinkedHashSet<EDataType>();
      Set<EDataType> result = new LinkedHashSet<EDataType>();
      typesToVisit.add(eDataType);
      do
      {
        Iterator<EDataType> it = typesToVisit.iterator();
        EDataType dataType = it.next();
        it.remove();
        if (result.add(dataType))
        {
          EDataType baseType = getBaseType(dataType);
          if (baseType != null)
          {
            typesToVisit.add(baseType);
          }
          EDataType itemType = getItemType(dataType);
          if (itemType != null)
          {
            typesToVisit.add(itemType);
          }
          typesToVisit.addAll(getMemberTypes(dataType));
        }
      }
      while (!typesToVisit.isEmpty());
      return result;
    }

    public boolean validatePackageEntryValue(EPackage ePackage, Map.Entry<String, String> entry, List<Object> values, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      return true;
    }

    public boolean validateClassEntryValue(EClass eClass, Map.Entry<String, String> entry, List<Object> values, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      return true;
    }

    public boolean validateStructuralFeatureEntryValue(
      EStructuralFeature eStructuralFeature,
      Map.Entry<String, String> entry,
      List<Object> values,
      DiagnosticChain diagnostics,
      Map<Object, Object> context)
    {
      String key = entry.getKey();
      if ("group".equals(key))
      {
        EStructuralFeature group = (EStructuralFeature)values.get(0);
        Set<EStructuralFeature> groups = getAffiliations(group);
        if (groups.contains(eStructuralFeature))
        {
          reportBadValue(groups, diagnostics, Diagnostic.ERROR, "_UI_ExtendeMetaDataAnnotationDetailGroupCircular_diagnostic", getQualifiedName(getPackageNamespace(group), group));
          return false;
        }
        else
        {
          return true;
        }
      }
      else if ("affiliation".equals(key))
      {
        EStructuralFeature affiliation = (EStructuralFeature)values.get(0);
        Set<EStructuralFeature> affiliations = getAffiliations(affiliation);
        if (affiliations.contains(eStructuralFeature))
        {
          reportBadValue(
            affiliations,
            diagnostics,
            Diagnostic.ERROR,
            "_UI_ExtendeMetaDataAnnotationDetailAffiliationCircular_diagnostic",
            getQualifiedName(getPackageNamespace(affiliation), affiliation));
          return false;
        }
        else
        {
          return true;
        }
      }
      else
      {
        return true;
      }
    }

    protected Set<EStructuralFeature> getAffiliations(EStructuralFeature eStructuralFeature)
    {
      Set<EStructuralFeature> result = new LinkedHashSet<EStructuralFeature>();
      for (EStructuralFeature affiliation = eStructuralFeature; affiliation != null; affiliation = getAffiliation(affiliation))
      {
        if (!result.add(affiliation))
        {
          break;
        }
      }
      return result;
    }

    protected Set<EStructuralFeature> getGroups(EStructuralFeature eStructuralFeature)
    {
      Set<EStructuralFeature> result = new LinkedHashSet<EStructuralFeature>();
      for (EStructuralFeature group = eStructuralFeature; group != null; group = getGroup(group))
      {
        if (!result.add(group))
        {
          break;
        }
      }
      return result;
    }
  }

  private static class ModelObject extends MinimalEObjectImpl.Container.Dynamic
  {
    protected SpecializedExtendedMetaData extendedMetaData;

    protected ENamedElement eNamedElement;

    public void setElement(ENamedElement eNamedElement)
    {
      if (ExtendedMetaDataAnnotationValidator.INSTANCE.getPropertyClass(eNamedElement) != eClass())
      {
        throw new IllegalArgumentException();
      }

      extendedMetaData = SpecializedExtendedMetaData.getExtendedMetaData(eNamedElement);

      this.eNamedElement = eNamedElement;
    }

    @Override
    public Object eGet(EStructuralFeature eStructuralFeature)
    {
      EClassifier eType = eStructuralFeature.getEType();
      String name = eStructuralFeature.getName();
      Method method = PropertySwitch.GETTERS.get(eStructuralFeature);
      try
      {
        Object result = method.invoke(extendedMetaData, eNamedElement);
        if (name.equals("wildcards"))
        {
          @SuppressWarnings("unchecked")
          EList<String> wildcards = ECollections.asEList((List<String>)result);
          int index = wildcards.indexOf(null);
          if (index != -1)
          {
            wildcards.set(index, "##local");
          }
          return wildcards;
        }
        else
        {
          return eType instanceof EEnum ? ((EEnum)eType).getEEnumLiteral((Integer)result) : eStructuralFeature.isMany() ? ECollections.asEList((List<?>)result) : result;
        }
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
    }

    @Override
    public void eSet(EStructuralFeature eStructuralFeature, Object value)
    {
      Method method = PropertySwitch.SETTERS.get(eStructuralFeature);
      try
      {
        if (value instanceof Enumerator)
        {
          method.invoke(extendedMetaData, eNamedElement, ((Enumerator)value).getValue());
        }
        else
        {
          method.invoke(extendedMetaData, eNamedElement, value);
        }
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
    }

    @Override
    public void eUnset(EStructuralFeature eStructuralFeature)
    {
      EAnnotation eAnnotation = eNamedElement.getEAnnotation(ExtendedMetaData.ANNOTATION_URI);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().removeKey(eStructuralFeature.getName());
      }
    }

    @Override
    public boolean eIsSet(EStructuralFeature eStructuralFeature)
    {
      EAnnotation eAnnotation = eNamedElement.getEAnnotation(ExtendedMetaData.ANNOTATION_URI);
      return eAnnotation != null && eAnnotation.getDetails().get(eStructuralFeature.getName()) != null;
    }

    public boolean isApplicable(EStructuralFeature eStructuralFeature)
    {
      Method method = PropertySwitch.ENTRY_VALIDATORS.get(eClass);
      EMap<String, String> details = EcoreFactory.eINSTANCE.createEAnnotation().getDetails();
      details.put(eStructuralFeature.getName(), null);
      try
      {
        return (Boolean)method.invoke(extendedMetaData, eNamedElement, details.get(0), null, null);
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
    }

    public Collection<?> filterChoiceOfValues(EStructuralFeature eStructuralFeature, Collection<?> choiceOfValues)
    {
      List<Object> result = new ArrayList<Object>();
      if (choiceOfValues != null)
      {
        result.addAll(choiceOfValues);
      }

      String name = eStructuralFeature.getName();
      if (name.equals("kind"))
      {
        if (eStructuralFeature.getEContainingClass().getName().equals("StructuralFeature"))
        {
          if (eNamedElement instanceof EReference)
          {
            for (Object object : choiceOfValues)
            {
              String literal = object.toString();
              if (literal.equals("group") || literal.equals("elementWildcard") || literal.equals("attributeWildcard"))
              {
                result.remove(object);
              }
            }
          }
        }
      }
      else if (name.equals("namespace"))
      {
        EStructuralFeature targetFeature = (EStructuralFeature)eNamedElement;
        for (Object object : choiceOfValues)
        {
          result.remove(object);
          if (object instanceof EPackage)
          {
            EPackage ePackage = (EPackage)object;
            String namespace = extendedMetaData.getNamespace(ePackage);
            if (namespace != null)
            {
              int featureKind = extendedMetaData.getFeatureKind(targetFeature);
              String targetName = extendedMetaData.getName(targetFeature);
              EStructuralFeature element = featureKind == ExtendedMetaData.ELEMENT_FEATURE
                ? extendedMetaData.getElement(namespace, targetName)
                : featureKind == ExtendedMetaData.ATTRIBUTE_FEATURE ? extendedMetaData.getAttribute(namespace, targetName) : null;
              if (element != null)
              {
                result.add(namespace);
              }
            }
          }
        }
        String targetNamespace = extendedMetaData.getNamespace(targetFeature);
        if (targetNamespace != null && !"".equals(targetNamespace) && !result.contains(targetNamespace))
        {
          result.add(targetNamespace);
        }
        EClass eContainingClass = targetFeature.getEContainingClass();
        if (eContainingClass != null)
        {
          String namespace = extendedMetaData.getNamespace(eContainingClass);
          if (namespace != null && !"".equals(namespace) && !result.contains(namespace))
          {
            result.add(namespace);
          }
        }
        result.add("");
      }
      else if (name.equals("wildcards"))
      {
        for (Object object : choiceOfValues)
        {
          result.remove(object);
          if (object instanceof EPackage)
          {
            EPackage ePackage = (EPackage)object;
            if (extendedMetaData.getDocumentRoot(ePackage) != null)
            {
              String namespace = extendedMetaData.getNamespace(ePackage);
              if (!XMLTypePackage.eNS_URI.equals(namespace))
              {
                result.add(namespace);
              }
            }
          }
        }

        EStructuralFeature targetFeature = (EStructuralFeature)eNamedElement;
        EClass eContainingClass = targetFeature.getEContainingClass();
        if (eContainingClass != null)
        {
          String namespace = extendedMetaData.getNamespace(eContainingClass);
          result.add("!##" + namespace);
        }

        result.add("##any");
        result.add("##local");
      }
      else if (name.equals("group"))
      {
        result.clear();
        for (EAttribute eAttribute : ((EStructuralFeature)eNamedElement).getEContainingClass().getEAllAttributes())
        {
          if (extendedMetaData.getFeatureKind(eAttribute) == ExtendedMetaData.GROUP_FEATURE && eAttribute != eNamedElement)
          {
            result.add(eAttribute);
          }
        }
        result.add(null);
      }
      else if (name.equals("affiliation"))
      {
        EStructuralFeature targetFeature = (EStructuralFeature)eNamedElement;
        EClassifier eType = targetFeature.getEType();
        for (Object object : choiceOfValues)
        {
          if (object instanceof EStructuralFeature)
          {
            EStructuralFeature feature = (EStructuralFeature)object;
            EClass eContainingClass = feature.getEContainingClass();
            if (eType == null || eContainingClass == null || !extendedMetaData.isDocumentRoot(eContainingClass)
              || XMLTypePackage.eNS_URI.equals(extendedMetaData.getNamespace(eContainingClass)) || extendedMetaData.getFeatureKind(feature) != ExtendedMetaData.ELEMENT_FEATURE
              || !isSuperTypeOf(feature.getEType(), eType))
            {
              result.remove(object);
            }
            else
            {
              Set<EStructuralFeature> affiliations = new LinkedHashSet<EStructuralFeature>();
              for (EStructuralFeature affiliation = feature; affiliation != null && affiliations.add(affiliation); affiliation = extendedMetaData.getAffiliation(affiliation))
              {
                if (targetFeature == affiliation)
                {
                  result.remove(object);
                  break;
                }
              }
            }
          }
        }
        // Always add the current value, even if it's not valid.
        EStructuralFeature affiliation = extendedMetaData.getAffiliation(targetFeature);
        if (affiliation != null && !result.contains(affiliation))
        {
          result.add(affiliation);
        }
      }
      else if (name.equals("baseType"))
      {
        EDataType eDataType = (EDataType)eNamedElement;
        for (Object object : choiceOfValues)
        {
          if (object instanceof EDataType)
          {
            EDataType dataType = (EDataType)object;
            if (!isSuperTypeOf(eDataType, dataType))
            {
              result.remove(object);
            }
            else if (eDataType.isSerializable() && !dataType.isSerializable())
            {
              result.remove(object);
            }
            else if (extendedMetaData.getInheritedTypes(dataType).contains(eDataType))
            {
              result.remove(object);
            }
          }
        }
      }
      else if (name.equals("itemType"))
      {
        EDataType eDataType = (EDataType)eNamedElement;
        for (Object object : choiceOfValues)
        {
          if (object instanceof EDataType)
          {
            EDataType dataType = (EDataType)object;
            Class<?> instanceClass = dataType.getInstanceClass();
            if (instanceClass != null && Collection.class.isAssignableFrom(instanceClass))
            {
              result.remove(object);
            }
            else if (eDataType.isSerializable() && !dataType.isSerializable())
            {
              result.remove(object);
            }
            else if (extendedMetaData.getInheritedTypes(dataType).contains(eDataType))
            {
              result.remove(object);
            }
          }
        }
      }
      else if (name.equals("memberTypes"))
      {
        EDataType eDataType = (EDataType)eNamedElement;
        for (Object object : choiceOfValues)
        {
          if (object instanceof EDataType)
          {
            EDataType dataType = (EDataType)object;
            if (!isSuperTypeOf(eDataType, dataType))
            {
              result.remove(object);
            }
            else if (eDataType.isSerializable() && !dataType.isSerializable())
            {
              result.remove(object);
            }
            else if (extendedMetaData.getInheritedTypes(dataType).contains(eDataType))
            {
              result.remove(object);
            }
          }
        }
      }

      return choiceOfValues == null && result.isEmpty() ? null : result;
    }

    private boolean isSuperTypeOf(EClassifier eClassifier1, EClassifier eClassifier2)
    {
      if (eClassifier1 instanceof EClass && eClassifier2 instanceof EClass)
      {
        EClass eClass1 = (EClass)eClassifier1;
        EClass eClass2 = (EClass)eClassifier2;
        return eClass1.isSuperTypeOf(eClass2);
      }
      else if (eClassifier1 instanceof EDataType && eClassifier2 instanceof EDataType)
      {
        EDataType eDataType1 = (EDataType)eClassifier1;
        EDataType eDataType2 = (EDataType)eClassifier2;
        Class<?> instanceClass1 = eDataType1.getInstanceClass();
        Class<?> instanceClass2 = eDataType2.getInstanceClass();
        if (instanceClass1 == null || instanceClass2 == null)
        {
          return true;
        }
        else
        {
          return instanceClass1.isAssignableFrom(instanceClass2);
        }
      }
      else
      {
        return false;
      }
    }

    private boolean isSuperTypeOf(EDataType eDataType1, EDataType eDataType2)
    {
      Class<?> instanceClass1 = eDataType1.getInstanceClass();
      if (instanceClass1 == null && eDataType1 instanceof EEnum)
      {
        instanceClass1 = Enumerator.class;
      }
      Class<?> instanceClass2 = eDataType2.getInstanceClass();
      if (instanceClass2 == null && eDataType2 instanceof EEnum)
      {
        instanceClass2 = Enumerator.class;
      }
      if (instanceClass1 == null || instanceClass2 == null)
      {
        return true;
      }
      else
      {
        return instanceClass1.isAssignableFrom(instanceClass2) || instanceClass1.isAssignableFrom(EcoreUtil.wrapperClassFor(instanceClass2));
      }
    }

    public boolean validateEntry(
      ExtendedMetaDataAnnotationValidator extendedMetaDataAnnotationValidator,
      Map.Entry<String, String> entry,
      DiagnosticChain diagnostics,
      Map<Object, Object> context)
    {
      boolean result = true;
      try
      {
        Method method = PropertySwitch.ENTRY_VALIDATORS.get(eClass);
        result = (Boolean)method.invoke(extendedMetaData, eNamedElement, entry, diagnostics, context);
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
      return result;
    }

    public boolean validateEntryValueLiteral(
      ExtendedMetaDataAnnotationValidator extendedMetaDataAnnotationValidator,
      Map.Entry<String, String> entry,
      String literalValue,
      List<Object> referenceValues,
      DiagnosticChain diagnostics,
      Map<Object, Object> context)
    {
      boolean result = true;
      try
      {
        Method method = PropertySwitch.ENTRY_VALUE_LITERAL_VALIDATORS.get(eClass);
        result = (Boolean)method.invoke(extendedMetaData, eNamedElement, entry, literalValue, referenceValues, diagnostics, context);
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
      return result;
    }

    public boolean validateEntryValue(
      ExtendedMetaDataAnnotationValidator extendedMetaDataAnnotationValidator,
      Map.Entry<String, String> entry,
      List<Object> values,
      DiagnosticChain diagnostics,
      Map<Object, Object> context)
    {
      boolean result = true;
      try
      {
        Method method = PropertySwitch.ENTRY_VALUE_VALIDATORS.get(eClass);
        result = (Boolean)method.invoke(extendedMetaData, eNamedElement, entry, values, diagnostics, context);
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
      return result;
    }
  }

  private static abstract class PropertySwitch extends EcoreSwitch<Void>
  {
    private static final EPackage EXTENDED_META_DATA_PACKAGE;

    private static final Map<EStructuralFeature, Method> GETTERS = new HashMap<EStructuralFeature, Method>();

    private static final Map<EStructuralFeature, Method> SETTERS = new HashMap<EStructuralFeature, Method>();

    private static final Map<EClass, Method> ENTRY_VALIDATORS = new HashMap<EClass, Method>();

    private static final Map<EClass, Method> ENTRY_VALUE_LITERAL_VALIDATORS = new HashMap<EClass, Method>();

    private static final Map<EClass, Method> ENTRY_VALUE_VALIDATORS = new HashMap<EClass, Method>();

    static
    {
      EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(ExtendedMetaData.ANNOTATION_URI);

      if (ePackage == null)
      {
        // If the package isn't registered, as well be the case in a stand alone application, try to load it dynamically.
        // This will ensure that the package is registered as well.
        ePackage = loadEPackage(EcorePlugin.INSTANCE.getBaseURL().toString() + "model/ExtendedMetaData.ecore");
      }

      EXTENDED_META_DATA_PACKAGE = ePackage;
      if (ePackage != null)
      {
        final EClassifier xmlPatternDataType = ePackage.getEClassifier("Pattern");
        EObjectValidator eObjectValidator = new EObjectValidator()
          {
            @Override
            public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
            {
              boolean result = super.validate(eDataType, value, diagnostics, context);
              if (eDataType == xmlPatternDataType && result && value != null)
              {
                try
                {
                  XMLTypeUtil.createPatternMatcher((String)value);
                }
                catch (RuntimeException exception)
                {
                  result = false;
                  if (diagnostics != null)
                  {
                    diagnostics.add(
                      createDiagnostic(
                        Diagnostic.ERROR,
                        ExtendedMetaDataAnnotationValidator.DIAGNOSTIC_SOURCE,
                        0,
                        "_UI_BadXMLPattern_diagnostic",
                        new Object []{ getValueLabel(eDataType, value, context), exception.getLocalizedMessage() },
                        new Object []{ value, eDataType },
                        context));
                  }
                }
              }
              return result;
            }
          };
        EValidator.Registry.INSTANCE.put(ePackage, eObjectValidator);
        ePackage.setEFactoryInstance(new EFactoryImpl()
          {
            @Override
            protected EObject basicCreate(EClass eClass)
            {
              ModelObject result = new ModelObject();
              result.eSetClass(eClass);
              return result;
            }
          });

        Method[] methods = SpecializedExtendedMetaData.class.getMethods();
        for (EClassifier eClassifier : ePackage.getEClassifiers())
        {
          if (eClassifier instanceof EClass)
          {
            EClass eClass = (EClass)eClassifier;
            if (!eClass.isAbstract())
            {
              ENTRY_VALIDATORS.put(eClass, getMethod(methods, eClass, "validate" + eClass.getName() + "Entry", 4));
              ENTRY_VALUE_LITERAL_VALIDATORS.put(eClass, getMethod(methods, eClass, "validate" + eClass.getName() + "EntryValueLiteral", 6));
              ENTRY_VALUE_VALIDATORS.put(eClass, getMethod(methods, eClass, "validate" + eClass.getName() + "EntryValue", 5));
            }
            for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures())
            {
              EClass eContainingClass = eStructuralFeature.getEContainingClass();
              String accessor = EcoreUtil.getAnnotation(eStructuralFeature, "Reflection", "accessor");
              GETTERS.put(
                eStructuralFeature,
                getMethod(methods, eContainingClass, ("boolean".equals(eStructuralFeature.getEType().getInstanceClassName()) ? "is" : "get") + accessor, 1));
              SETTERS.put(eStructuralFeature, getMethod(methods, eContainingClass, "set" + accessor, 2));
            }
          }
        }
      }
    }

    private static Method getMethod(Method[] methods, EClass eClass, String expectedName, int expectedCount)
    {
      for (Method method : methods)
      {
        String name = method.getName();
        if (name.equals(expectedName) && method.getParameterTypes().length == expectedCount && method.getParameterTypes()[0].getName().endsWith(eClass.getName()))
        {
          return method;
        }
      }

      throw new IllegalStateException();
    }

    protected abstract void addFeatures(EClass eClass);

    @Override
    public Void caseEPackage(EPackage object)
    {
      if (EXTENDED_META_DATA_PACKAGE != null)
      {
        addFeatures((EClass)EXTENDED_META_DATA_PACKAGE.getEClassifier("Package"));
      }
      return null;
    }

    @Override
    public Void caseEClass(EClass eClass)
    {
      if (EXTENDED_META_DATA_PACKAGE != null)
      {
        addFeatures((EClass)EXTENDED_META_DATA_PACKAGE.getEClassifier("Class"));
      }
      return null;
    }

    @Override
    public Void caseEDataType(EDataType eDataType)
    {
      if (EXTENDED_META_DATA_PACKAGE != null)
      {
        addFeatures((EClass)EXTENDED_META_DATA_PACKAGE.getEClassifier("DataType"));
      }
      return null;
    }

    @Override
    public Void caseEStructuralFeature(EStructuralFeature eStructuralFeature)
    {
      if (EXTENDED_META_DATA_PACKAGE != null)
      {
        addFeatures((EClass)EXTENDED_META_DATA_PACKAGE.getEClassifier("StructuralFeature"));
      }
      return null;
    }
  }
}
