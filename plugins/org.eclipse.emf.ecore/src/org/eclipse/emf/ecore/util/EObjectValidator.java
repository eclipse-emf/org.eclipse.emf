/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EObjectValidator.java,v 1.1 2004/05/05 19:51:42 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * A validity checker for basic EObject constraints.
 */
public class EObjectValidator implements EValidator
{
  public static final EObjectValidator INSTANCE = new EObjectValidator();

  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore";

  public static final int EOBJECT__EVERY_MULTIPCITY_CONFORMS = 1;
  public static final int EOBJECT__EVERY_DATA_VALUE_CONFORMS = 2;
  public static final int EOBJECT__EVERY_REFERENCE_IS_CONTAINED = 3;
  public static final int EOBJECT__EVERY_PROXY_RESOLVES = 4;
  public static final int DATA_VALUE__VALUE_IN_RANGE = 5;
  public static final int DATA_VALUE__LENGTH_IN_RANGE = 6;

  public EObjectValidator()
  {
  }

  protected EPackage getEPackage()
  {
    return EcorePackage.eINSTANCE;
  }

  protected EValidator getRootEValidator(Map context)
  {
    EValidator result = (EValidator)context.get(EValidator.class);
    if (result == null)
    {
      result = Diagnostician.INSTANCE;
    }
   return result;
  }

  protected String getObjectLabel(EObject eObject, Map context)
  {
    SubstitutionLabelProvider substitutionlabelProvider = (SubstitutionLabelProvider)context.get(SubstitutionLabelProvider.class);
    return 
      substitutionlabelProvider == null ?
        EcoreUtil.getIdentification(eObject) :
        substitutionlabelProvider.getObjectLabel(eObject);
  }

  protected String getFeatureLabel(EStructuralFeature eStructuralFeature, Map context)
  {
    SubstitutionLabelProvider substitutionlabelProvider = (SubstitutionLabelProvider)context.get(SubstitutionLabelProvider.class);
    return 
      substitutionlabelProvider == null ?
        eStructuralFeature.getName() :
        substitutionlabelProvider.getFeatureLabel(eStructuralFeature);
  }

  protected String getValueLabel(EDataType eDataType, Object value, Map context)
  {
    SubstitutionLabelProvider substitutionlabelProvider = (SubstitutionLabelProvider)context.get(SubstitutionLabelProvider.class);
    return 
      substitutionlabelProvider == null ?
        EcoreUtil.convertToString(eDataType, value) :
        substitutionlabelProvider.getValueLabel(eDataType, value);
  }

  /**
   * Validates the object in the given context, optionally producing diagnostics.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether the object is valid.
   */
  public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map context)
  {
    return validate(eObject.eClass(), eObject, diagnostics, context);
  }

  public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map context)
  {
    if (eClass.eContainer() == getEPackage()) 
    {
      return validate(eClass.getClassifierID(), eObject, diagnostics, context);
    }
    else 
    {
      List eSuperTypes = eClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          validate_EveryDefaultConstraint(eObject, diagnostics, context) :
          validate((EClass)eSuperTypes.get(0), eObject, diagnostics, context);
    }
  }

  protected boolean validate(int classifierID, Object object, DiagnosticChain diagnostics, Map context)
  {
    return classifierID != EcorePackage.EOBJECT || validate_EveryDefaultConstraint((EObject)object, diagnostics, context);
  }

  public boolean validate_EveryDefaultConstraint(EObject object, DiagnosticChain theDiagnostics, Map context) 
  {
    boolean result = validate_EveryMultiplicityConforms(object, theDiagnostics, context);
    if (result || theDiagnostics != null)
    {
      result &= validate_EveryProxyResolves(object, theDiagnostics, context);
    }
    if (result || theDiagnostics != null)
    {
      result &= validate_EveryReferenceIsContained(object, theDiagnostics, context);
    }
    if (result || theDiagnostics != null)
    {
      result &= validate_EveryDataValueConforms(object, theDiagnostics, context);
    }
    return result;
  }

  public boolean validate_EveryMultiplicityConforms(EObject eObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = true;
    for (Iterator i = eObject.eClass().getEAllStructuralFeatures().iterator(); i.hasNext(); )
    {
      result &= validate_MultiplicityConforms(eObject, (EStructuralFeature)i.next(), diagnostics, context);
      if (!result && diagnostics == null)
      {
        return false;
      }
    }
    return result;
  }

  protected boolean validate_MultiplicityConforms
    (EObject eObject, EStructuralFeature eStructuralFeature, DiagnosticChain diagnostics, Map context)
  {
    boolean result = true;
    if (eStructuralFeature.isMany())
    {
      int lowerBound = eStructuralFeature.getLowerBound();
      if (lowerBound > 0)
      {
        int size = ((List)eObject.eGet(eStructuralFeature)).size();
        if (size < lowerBound)
        {
          result = false;
          if (diagnostics != null)
          {
            diagnostics.add
              (new BasicDiagnostic
                (Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 EOBJECT__EVERY_MULTIPCITY_CONFORMS,
                 EcorePlugin.INSTANCE.getString
                   ("_UI_FeatureHasTooFewValues_diagnostic", 
                    new Object [] 
                      { 
                        getFeatureLabel(eStructuralFeature, context), 
                        getObjectLabel(eObject, context), 
                        new Integer(size), 
                        new Integer(lowerBound) 
                      }),
                 new Object [] { eObject, eStructuralFeature }));
          }
        }
        int upperBound = eStructuralFeature.getUpperBound();
        if (upperBound > 0 && size > upperBound)
        {
          result = false;
          if (diagnostics != null)
          {
            diagnostics.add
              (new BasicDiagnostic
                (Diagnostic.ERROR,
                 DIAGNOSTIC_SOURCE,
                 EOBJECT__EVERY_MULTIPCITY_CONFORMS,
                 EcorePlugin.INSTANCE.getString
                   ("_UI_FeatureHasTooManyValues_diagnostic", 
                    new Object [] 
                      { 
                        getFeatureLabel(eStructuralFeature, context), 
                        getObjectLabel(eObject, context), 
                        new Integer(size), 
                        new Integer(upperBound) 
                      }),
                 new Object [] { eObject, eStructuralFeature }));
          }
        }
      }
      else
      {
        int upperBound = eStructuralFeature.getUpperBound();
        if (upperBound > 0)
        {
          int size = ((List)eObject.eGet(eStructuralFeature)).size();
          if (size > upperBound)
          {
            result = false;
            if (diagnostics != null)
            {
              diagnostics.add
                (new BasicDiagnostic
                  (Diagnostic.ERROR,
                   DIAGNOSTIC_SOURCE,
                   EOBJECT__EVERY_MULTIPCITY_CONFORMS,
                   EcorePlugin.INSTANCE.getString
                     ("_UI_FeatureHasTooManyValues_diagnostic", 
                      new Object [] 
                        { 
                          getFeatureLabel(eStructuralFeature, context), 
                          getObjectLabel(eObject, context), 
                          new Integer(size), 
                          new Integer(upperBound) 
                        }),
                   new Object [] { eObject, eStructuralFeature }));
            }
          }
        }
      }
    }
    else if (eStructuralFeature.isRequired())
    {
      if (!eObject.eIsSet(eStructuralFeature))
      {
        result = false;
        if (diagnostics != null)
        {
          diagnostics.add
            (new BasicDiagnostic
              (Diagnostic.ERROR,
               DIAGNOSTIC_SOURCE,
               EOBJECT__EVERY_MULTIPCITY_CONFORMS,
               EcorePlugin.INSTANCE.getString
                 ("_UI_RequiredFeatureMustBeSet_diagnostic", 
                  new Object [] { getFeatureLabel(eStructuralFeature, context), getObjectLabel(eObject, context) }),
               new Object [] { eObject, eStructuralFeature }));
        }
      }
    }

    return result;
  }

  public boolean validate_EveryProxyResolves(EObject eObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = true;
    for (EContentsEList.FeatureIterator i = (EContentsEList.FeatureIterator)eObject.eCrossReferences().iterator(); i.hasNext(); )
    {
      EObject eCrossReferenceObject = (EObject)i.next();
      if (!eCrossReferenceObject.eIsProxy())
      {
        result = false;
        if (diagnostics != null)
        {
          diagnostics.add
            (new BasicDiagnostic
              (Diagnostic.ERROR,
               DIAGNOSTIC_SOURCE,
               EOBJECT__EVERY_PROXY_RESOLVES,
               EcorePlugin.INSTANCE.getString
                 ("_UI_UnresolvedProxy_diagnostic", 
                  new Object [] 
                    { 
                      getFeatureLabel(i.feature(), context), 
                      getObjectLabel(eObject, context), 
                      getObjectLabel(eCrossReferenceObject, context) 
                    }),
               new Object [] { eObject, i.feature(), eCrossReferenceObject }));
        }
        else
        {
          break;
        }
      }
    }
    return result;
  }

  public boolean validate_EveryReferenceIsContained(EObject eObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = true;
    for (EContentsEList.FeatureIterator i = (EContentsEList.FeatureIterator)eObject.eCrossReferences().iterator(); i.hasNext(); )
    {
      EObject eCrossReferenceObject = (EObject)i.next();
      if (eCrossReferenceObject.eResource() == null)
      {
        result = false;
        if (diagnostics != null)
        {
          diagnostics.add
            (new BasicDiagnostic
              (Diagnostic.ERROR,
               DIAGNOSTIC_SOURCE,
               EOBJECT__EVERY_REFERENCE_IS_CONTAINED,
               EcorePlugin.INSTANCE.getString
                 ("_UI_DanglingReference_diagnostic", 
                  new Object [] 
                    { 
                      getFeatureLabel(i.feature(), context), 
                      getObjectLabel(eObject, context), 
                      getObjectLabel(eCrossReferenceObject, context) 
                    }),
               new Object [] { eObject, i.feature(), eCrossReferenceObject }));
        }
        else
        {
          break;
        }
      }
    }
    return result;
  }

  public boolean validate_EveryDataValueConforms(EObject eObject, DiagnosticChain diagnostics, Map context)
  {
    boolean result = true;
    for (Iterator i = eObject.eClass().getEAllAttributes().iterator(); i.hasNext(); )
    {
      result &= validate_DataValueConforms(eObject, (EAttribute)i.next(), diagnostics, context);
      if (!result && diagnostics == null)
      {
        return false;
      }
    }
    return result;
  }

  protected boolean validate_DataValueConforms
    (EObject eObject, EAttribute eAttribute, DiagnosticChain diagnostics, Map context)
  {
    boolean result = true;
    EDataType eDataType = eAttribute.getEAttributeType();
    EValidator rootValidator = getRootEValidator(context);
    Object value = eObject.eGet(eAttribute);
    if (eAttribute.isMany())
    {
      for (Iterator i = ((List)value).iterator(); i.hasNext() && result; )
      {
        result &= rootValidator.validate(eDataType, i.next(), null, context);
      }

      if (!result && diagnostics != null)
      {
        DiagnosticChain diagnostic = createBadDataValueDiagnostic(eObject, eAttribute, diagnostics, context);
        for (Iterator i = ((List)value).iterator(); i.hasNext(); )
        {
          rootValidator.validate(eDataType, i.next(), diagnostic, context);
        }
      }
    }
    else if (value != null)
    {
      result = rootValidator.validate(eDataType, value, null, context);
      if (!result && diagnostics != null)
      {
        DiagnosticChain diagnostic = createBadDataValueDiagnostic(eObject, eAttribute, diagnostics, context);
        rootValidator.validate(eDataType, value, diagnostic, context);
      }
    }

    return result;
  }

  protected DiagnosticChain createBadDataValueDiagnostic
    (EObject eObject, EAttribute eAttribute, DiagnosticChain diagnostics, Map context)
  {
    BasicDiagnostic diagnostic = 
      new BasicDiagnostic
        (Diagnostic.ERROR,
         DIAGNOSTIC_SOURCE,
         EOBJECT__EVERY_DATA_VALUE_CONFORMS,
         EcorePlugin.INSTANCE.getString
           ("_UI_BadDataValue_diagnostic",
            new Object []
              {
                getFeatureLabel(eAttribute, context),
                getObjectLabel(eObject, context)
              }),
         new Object [] { eObject, eAttribute });
    diagnostics.add(diagnostic);
    return diagnostic;
  }

  public class DynamicEDataTypeValidator
  {
    protected List effectiveEnumeration; 
    protected List effectivePattern; 
    protected int effectiveTotalDigits; 
    protected int effectiveFractionDigits; 
    protected int effectiveMinLength; 
    protected int effectiveMaxLength; 
    protected Object effectiveMin; 
    protected boolean effectiveMinIsInclusive;
    protected Object effectiveMax; 
    protected boolean effectiveMaxIsInclusive;
    protected EDataType itemType;
    protected List memberTypes;

    public DynamicEDataTypeValidator(EDataType eDataType)
    {
      ExtendedMetaData extendedMetaData = ExtendedMetaData.INSTANCE;
      Resource resource = eDataType.eResource();
      if (resource != null)
      {
        ResourceSet resourceSet = resource.getResourceSet();
        if (resourceSet != null)
        {
          extendedMetaData = new BasicExtendedMetaData(resourceSet.getPackageRegistry());
        }
      }

      for (;;)
      {
        if (effectiveEnumeration == null)
        {
          List enumeration = extendedMetaData.getEnumerationFacet(eDataType);
          if (!enumeration.isEmpty())
          {
            effectiveEnumeration = enumeration;
          }
        }
        if (effectivePattern == null)
        {
          List pattern = extendedMetaData.getPatternFacet(eDataType);
          if (!pattern.isEmpty())
          {
            effectivePattern = pattern;
          }
        }
        if (effectiveTotalDigits == -1)
        {
          effectiveTotalDigits = extendedMetaData.getTotalDigitsFacet(eDataType);
        }
        if (effectiveFractionDigits == -1)
        {
          effectiveFractionDigits = extendedMetaData.getFractionDigitsFacet(eDataType);
        }
        if (effectiveMinLength == -1)
        {
          effectiveMinLength = extendedMetaData.getLengthFacet(eDataType);
          if (effectiveMinLength == -1)
          {
            effectiveMinLength = extendedMetaData.getMinLengthFacet(eDataType);
          }
        }
        if (effectiveMaxLength == -1)
        {
          effectiveMaxLength = extendedMetaData.getLengthFacet(eDataType);
          if (effectiveMaxLength == -1)
          {
            effectiveMaxLength = extendedMetaData.getMaxLengthFacet(eDataType);
          }
        }
        if (effectiveMin == null)
        {
          effectiveMin = extendedMetaData.getMinExclusiveFacet(eDataType);
          if (effectiveMin == null)
          {
            effectiveMin = extendedMetaData.getMinInclusiveFacet(eDataType);
            if (effectiveMin != null)
            {
              effectiveMin = EcoreUtil.createFromString(eDataType, (String)effectiveMin);
              effectiveMinIsInclusive = true;
            }
          }
          else
          {
            effectiveMin = EcoreUtil.createFromString(eDataType, (String)effectiveMin);
            effectiveMinIsInclusive = false;
          }
        }
        if (effectiveMax == null)
        {
          effectiveMax = extendedMetaData.getMaxExclusiveFacet(eDataType);
          if (effectiveMax == null)
          {
            effectiveMax = extendedMetaData.getMaxInclusiveFacet(eDataType);
            if (effectiveMax != null)
            {
              effectiveMax = EcoreUtil.createFromString(eDataType, (String)effectiveMax);
              effectiveMaxIsInclusive = true;
            }
          }
          else
          {
            effectiveMax = EcoreUtil.createFromString(eDataType, (String)effectiveMax);
            effectiveMaxIsInclusive = false;
          }
        }

        EDataType baseType = extendedMetaData.getBaseType(eDataType);
        if (baseType != null)
        {
          eDataType = baseType;
          continue;
        }
        else
        {
          itemType = extendedMetaData.getItemType(eDataType);
          memberTypes = extendedMetaData.getMemberTypes(eDataType);
          break;
        }
      }
    }

    public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map context)
    {
      boolean result = true;
      if (effectiveEnumeration != null)
      {
        if (!effectiveEnumeration.contains(value))
        {
          // TODO diagnostic.
        }
      }

      if (effectiveMin != null)
      {
        if (effectiveMinIsInclusive ? 
              ((Comparable)effectiveMin).compareTo(value) < 0:
              ((Comparable)effectiveMin).compareTo(value) <= 0)
        {
          if (diagnostics != null) reportMinViolation(eDataType, value, effectiveMin, effectiveMinIsInclusive, diagnostics, context);
          result = false;
        }
      }

      if (effectiveMax != null)
      {
        if (effectiveMaxIsInclusive ? 
              ((Comparable)effectiveMax).compareTo(value) > 0:
              ((Comparable)effectiveMax).compareTo(value) >= 0)
        {
          if (diagnostics != null) reportMaxViolation(eDataType, value, effectiveMax, effectiveMaxIsInclusive, diagnostics, context);
          result = false;
        }
      }

      if (effectiveMinLength != -1)
      {
        int length =
          value instanceof String ?
            ((String)value).length() :
             value instanceof Object[] ?
               ((Object[])value).length :
               ((Collection)value).size();
        if (length < effectiveMinLength)
        {
          // TODO Diagnostic
        }
      }

      if (effectiveMaxLength != -1)
      {
        int length =
          value instanceof String ?
            ((String)value).length() :
             value instanceof Object[] ?
               ((Object[])value).length :
               ((Collection)value).size();
        if (length < effectiveMaxLength)
        {
          // TODO Diagnostic
        }
      }

      if (itemType != null)
      {
        EValidator rootValidator = getRootEValidator(context);
        for (Iterator i = ((List)value).iterator(); i.hasNext() && result || diagnostics != null; )
        {
          result &= rootValidator.validate(itemType, i.next(), diagnostics, context);
        }
        return result;
      }
      else if (!memberTypes.isEmpty())
      {
        EValidator rootValidator = getRootEValidator(context);
        for (Iterator i = memberTypes.iterator(); i.hasNext(); )
        {
          EDataType memberType = (EDataType)i.next();
          if (rootValidator.validate(memberType, value, null, context))
          {
            return true;
          }
        }
        for (Iterator i = memberTypes.iterator(); i.hasNext(); )
        {
          EDataType memberType = (EDataType)i.next();
          if (memberType.isInstance(value))
          {
            return rootValidator.validate(memberType, value, diagnostics, context);
          }
        }
        return false;
      }
      else
      {
        return result;
      }
    }
  }

  public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map context)
  {
    if (!eDataType.isInstance(value))
    {
      // TODO type violation.
      return false;
    }

    if (eDataType.eContainer() == getEPackage()) 
    {
      return validate(eDataType.getClassifierID(), value, diagnostics, context);
    }
    else 
    {
      return 
        new DynamicEDataTypeValidator(eDataType)
        {
        }.validate(eDataType, value, diagnostics, context);
    }
  }

  protected void reportMinViolation
    (EDataType eDataType, Object value, Object bound, boolean isInclusive, DiagnosticChain diagnostics, Map context)
  {
    diagnostics.add
      (new BasicDiagnostic
        (Diagnostic.ERROR,
         DIAGNOSTIC_SOURCE,
         DATA_VALUE__VALUE_IN_RANGE,
         EcorePlugin.INSTANCE.getString
           (isInclusive ? "_UI_MinInclusiveConstraint_diagnostic" : "_UI_MinExclusiveConstraint_diagnostic",
            new Object []
              {
                getValueLabel(eDataType, value, context),
                isInclusive ? ">=" : ">",
                getValueLabel(eDataType, bound, context)
              }),
         new Object [] { value, bound, isInclusive ? Boolean.TRUE : Boolean.FALSE }));
  }

  protected void reportMaxViolation
    (EDataType eDataType, Object value, Object bound, boolean isInclusive, DiagnosticChain diagnostics, Map context)
  {
    diagnostics.add
      (new BasicDiagnostic
        (Diagnostic.ERROR,
         DIAGNOSTIC_SOURCE,
         DATA_VALUE__VALUE_IN_RANGE,
         EcorePlugin.INSTANCE.getString
           (isInclusive ? "_UI_MaxInclusiveConstraint_diagnostic" : "_UI_MaxExclusiveConstraint_diagnostic",
            new Object []
              {
                getValueLabel(eDataType, value, context),
                "<",
                getValueLabel(eDataType, bound, context)
              }),
         new Object [] { value, bound, isInclusive ? Boolean.TRUE : Boolean.FALSE }));
  }

  protected void reportMinLengthViolation
    (EDataType eDataType, Object value, int length, int bound, DiagnosticChain diagnostics, Map context)
  {
    diagnostics.add
      (new BasicDiagnostic
        (Diagnostic.ERROR,
         DIAGNOSTIC_SOURCE,
         DATA_VALUE__LENGTH_IN_RANGE,
         EcorePlugin.INSTANCE.getString
           ("_UI_MinLengthConstraint_diagnostic",
            new Object []
              {
                getValueLabel(eDataType, value, context),
                Integer.toString(length),
                Integer.toString(bound)
              }),
         new Object [] { eDataType, value, new Integer(length), new Integer(bound) }));
  }

  protected void reportMaxLengthViolation
    (EDataType eDataType, Object value, int length, int bound, DiagnosticChain diagnostics, Map context)
  {
    diagnostics.add
      (new BasicDiagnostic
        (Diagnostic.ERROR,
         DIAGNOSTIC_SOURCE,
         DATA_VALUE__LENGTH_IN_RANGE,
         EcorePlugin.INSTANCE.getString
           ("_UI_MaxLengthConstraint_diagnostic",
            new Object []
              {
                getValueLabel(eDataType, value, context),
                Integer.toString(length),
                Integer.toString(bound)
              }),
         new Object [] { eDataType, value, new Integer(length), new Integer(bound) }));
  }
}
