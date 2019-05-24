/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;


/**
 *  An annotation validator for {@link EcorePackage#eNS_URI Ecore} annotations.
 *
 * @since 2.14
 */
public final class EcoreAnnotationValidator extends BasicEAnnotationValidator
{
  public static final EcoreAnnotationValidator INSTANCE = new EcoreAnnotationValidator();

  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.annotation";

  static
  {
    // Check that the instance is registered properly...
    if (!EAnnotationValidator.Registry.INSTANCE.containsKey(EcorePackage.eNS_URI))
    {
      // In a stand alone application, we'll need to explicitly register it.
      EAnnotationValidator.Registry.INSTANCE.put(EcorePackage.eNS_URI, INSTANCE);
    }

    // Force eager initialization; in a stand alone application, the dynamic model won't be registered until this class is initialized.
    PropertySwitch.VALID_KEYS.isEmpty();
  }

  public EcoreAnnotationValidator()
  {
    super(EcorePackage.eNS_URI, "Ecore", DIAGNOSTIC_SOURCE);

    //    int xxx;
    //    // This can be used to test an annotation validator that supports annotations on annotations.
    //    EAnnotationValidator.Registry.INSTANCE.put("Testing", new BasicEAnnotationValidator("Testing", "Testing", "..testing")
    //      {
    //        @Override
    //        protected ResourceLocator getResourceLocator()
    //        {
    //          return EcorePlugin.INSTANCE;
    //        }
    //
    //        @Override
    //        protected boolean isValidLocation(EAnnotation eAnnotation, EModelElement eModelElement)
    //        {
    //          return eModelElement instanceof EAnnotation;
    //        }
    //
    //        @Override
    //        protected boolean isDuplicateValid(EModelElement eModelElement, EAnnotation primaryEAnnotation, EAnnotation secondaryEAnnotation)
    //        {
    //          return true;
    //        }
    //
    //        @Override
    //        protected boolean isContentsSupported(EAnnotation eAnnotation, EModelElement eModelElement)
    //        {
    //          return true;
    //        }
    //
    //        @Override
    //        protected Collection<? extends EObject> getValidContents(EAnnotation eAnnotation, EModelElement eModelElement, Collection<? extends EObject> contents)
    //        {
    //          ArrayList<EObject> result = new ArrayList<EObject>(contents);
    //          for (EObject eObject : contents)
    //          {
    //            if (!(eObject instanceof EClass))
    //            {
    //              result.remove(eObject);
    //            }
    //          }
    //          result.add(EcoreFactory.eINSTANCE.createEClass());
    //          return result;
    //        }
    //
    //        @Override
    //        protected boolean isReferencesSupported(EAnnotation eAnnotation, EModelElement eModelElement)
    //        {
    //          return true;
    //        }
    //
    //        @Override
    //        protected Collection<?> getValidReferences(EAnnotation eAnnotation, EModelElement eModelElement, Collection<?> references)
    //        {
    //          ArrayList<Object> result = new ArrayList<Object>(references);
    //          for (Object object : references)
    //          {
    //            if (!(object instanceof EDataType))
    //            {
    //              result.remove(object);
    //            }
    //          }
    //          return result;
    //        }
    //
    //        @Override
    //        protected List<EClass> getPropertyClasses(EModelElement eModelElement)
    //        {
    //          return Collections.emptyList();
    //        }
    //      });
  }

  @Override
  protected ResourceLocator getResourceLocator()
  {
    return getEcoreResourceLocator();
  }

  @Override
  protected boolean isValidLocation(EAnnotation eAnnotation, EModelElement eModelElement)
  {
    return !getProperties(eModelElement).isEmpty();
  }

  @Override
  protected List<EClass> getPropertyClasses(EModelElement eModelElement)
  {
    final List<EClass> result = new ArrayList<EClass>(1);
    new PropertySwitch()
      {
        @Override
        protected void addFeatures(EClass eClass)
        {
          result.add(eClass);
        }
      }.doSwitch(eModelElement);
    return result.isEmpty() ? Collections.<EClass> emptyList() : Collections.singletonList(result.get(0));
  }

  @Override
  protected boolean validateAttributeDetailValueLiteral(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EAttribute attribute,
    String literalValue,
    List<Object> dataValues,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    List<Object> newValues = new ArrayList<Object>(1);
    boolean result = super.validateAttributeDetailValueLiteral(eAnnotation, eModelElement, entry, attribute, literalValue, newValues, diagnostics, context);
    dataValues.addAll(newValues);
    if (result || diagnostics != null)
    {
      Set<String> validKeys = PropertySwitch.getValidKeys(attribute, context);
      if (validKeys != null)
      {
        @SuppressWarnings("unchecked")
        List<String> values = (List<String>)(List<?>)newValues;
        for (String value : values)
        {
          if (!validKeys.contains(value) && EcoreValidator.isWellFormedURI(value))
          {
            result = false;
            if (diagnostics == null)
            {
              break;
            }
            else
            {
              diagnostics.add(
                createDiagnostic(
                  Diagnostic.WARNING,
                  0,
                  getString(
                    getResourceLocator(),
                    "_UI_EcoreAnnotationUnregisteredDelegate_diagnostic",
                    value,
                    getString(getResourceLocator(), "_UI_EcoreAnnotationUnregisteredDelegate_" + attribute.getName() + "_substitution")),
                  value));
            }
          }
        }
      }
    }
    return result;
  }

  private static abstract class PropertySwitch extends EcoreSwitch<Void>
  {
    private static final String ANNOTATION_NS_URI = "http:///org/eclipse/emf/ecore/util/EcoreAnnotation";

    private static final Map<EAttribute, Set<String>> VALID_KEYS = new HashMap<EAttribute, Set<String>>();

    private static final EClass ANNOTATION_PACKAGE_CLASS;

    private static final EClass ANNOTATION_CLASSIFIER_CLASS;

    private static final EClass ANNOTATION_OPERATION_CLASS;

    static
    {
      EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(ANNOTATION_NS_URI);
      if (ePackage == null)
      {
        // If the package isn't registered, as well be the case in a stand alone application, try to load it dynamically.
        // This will ensure that the package is registered as well.
        ePackage = loadEPackage(EcorePlugin.INSTANCE.getBaseURL().toString() + "model/EcoreAnnotation.ecore");
      }

      if (ePackage == null)
      {
        ANNOTATION_PACKAGE_CLASS = null;
        ANNOTATION_CLASSIFIER_CLASS = null;
        ANNOTATION_OPERATION_CLASS = null;
      }
      else
      {
        ANNOTATION_PACKAGE_CLASS = (EClass)ePackage.getEClassifier("Package");
        VALID_KEYS.put(
          (EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("settingDelegates"),
          EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.keySet());
        VALID_KEYS.put((EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("validationDelegates"), EValidator.ValidationDelegate.Registry.INSTANCE.keySet());
        VALID_KEYS.put(
          (EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("invocationDelegates"),
          EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.keySet());
        VALID_KEYS.put((EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("conversionDelegates"), EDataType.Internal.ConversionDelegate.Factory.Registry.INSTANCE.keySet());
        VALID_KEYS.put((EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("queryDelegates"), QueryDelegate.Factory.Registry.INSTANCE.keySet());

        ANNOTATION_CLASSIFIER_CLASS = (EClass)ePackage.getEClassifier("Classifier");

        ANNOTATION_OPERATION_CLASS = (EClass)ePackage.getEClassifier("Operation");

        final EDataType javaIdentifier = (EDataType)ePackage.getEClassifier("JavaIdentifier");
        final EDataType uriDataType = (EDataType)ePackage.getEClassifier("WellFormedURI");

        EValidator.Registry.INSTANCE.put(ePackage, new EObjectValidator()
          {
            @Override
            public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
            {
              boolean result = super.validate(eDataType, value, diagnostics, context);
              if (result)
              {
                if (eDataType == javaIdentifier)
                {
                  if (javaIdentifier != null)
                  {
                    result = EcoreValidator.isWellFormedJavaIdentifier((String)value);
                    if (!result && diagnostics != null)
                    {
                      diagnostics.add(
                        createDiagnostic(
                          Diagnostic.ERROR,
                          DIAGNOSTIC_SOURCE,
                          0,
                          "_UI_NameNotWellFormedJavaIdentifier_diagnostic",
                          new Object []{ value },
                          new Object []{ value, eDataType },
                          context));
                    }
                  }
                }
                else if (eDataType == uriDataType)
                {
                  if (value != null)
                  {
                    result = EcoreValidator.isWellFormedURI((String)value);
                    if (!result && diagnostics != null)
                    {
                      diagnostics.add(
                        createDiagnostic(
                          Diagnostic.ERROR,
                          DIAGNOSTIC_SOURCE,
                          0,
                          "_UI_EAnnotationSourceURINotWellFormed_diagnostic",
                          new Object []{ value },
                          new Object []{ value, eDataType },
                          context));
                    }
                  }
                }
              }
              return result;
            }
          });
      }
    }

    protected abstract void addFeatures(EClass eClass);

    public static Set<String> getValidKeys(EAttribute attribute, Map<Object, Object> context)
    {
      @SuppressWarnings("unchecked")
      Map<EAttribute, Set<String>> validKeys = context == null ? null : (Map<EAttribute, Set<String>>)context.get("VALID_DELEGATE_KEYS");
      if (validKeys == null)
      {
        validKeys = new HashMap<EAttribute, Set<String>>();

        validKeys.put(
          (EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("settingDelegates"),
          EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.getTargetPlatformFactories());
        validKeys.put(
          (EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("validationDelegates"),
          EValidator.ValidationDelegate.Registry.INSTANCE.getTargetPlatformFactories());
        validKeys.put(
          (EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("invocationDelegates"),
          EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.getTargetPlatformFactories());
        validKeys.put(
          (EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("conversionDelegates"),
          EDataType.Internal.ConversionDelegate.Factory.Registry.INSTANCE.getTargetPlatformFactories());
        validKeys.put((EAttribute)ANNOTATION_PACKAGE_CLASS.getEStructuralFeature("queryDelegates"), QueryDelegate.Factory.Registry.INSTANCE.getTargetPlatformFactories());

        if (context != null)
        {
          context.put("VALID_DELEGATE_KEYS", validKeys);
        }
      }
      return validKeys.get(attribute);
    }

    @Override
    public Void caseEClassifier(EClassifier eClassifier)
    {
      if (ANNOTATION_CLASSIFIER_CLASS != null)
      {
        addFeatures(ANNOTATION_CLASSIFIER_CLASS);
      }
      return null;
    }

    @Override
    public Void caseEOperation(EOperation eOperation)
    {
      if (ANNOTATION_OPERATION_CLASS != null)
      {
        addFeatures(ANNOTATION_OPERATION_CLASS);
      }
      return null;
    }

    @Override
    public Void caseEPackage(EPackage object)
    {
      if (ANNOTATION_PACKAGE_CLASS != null)
      {
        addFeatures(ANNOTATION_PACKAGE_CLASS);
      }
      return null;
    }
  }
}
