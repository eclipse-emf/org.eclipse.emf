/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;


/**
 *  An annotation validator for {@link EcorePackage#eNS_URI Ecore} annotations.
 *
 * @since 2.14
 */
public final class EcoreAnnotationValidator extends BasicEAnnotationValidator
{
  public static final EcoreAnnotationValidator INSTANCE = new EcoreAnnotationValidator();

  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.annotation";

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
    return result.size() == 0 ? Collections.<EClass> emptyList() : Collections.singletonList(result.get(0));
  }

  @Override
  protected boolean validateAttributeDetailValueLiteral(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Entry<String, String> entry,
    EAttribute attribute,
    String literalValue,
    List<Object> dataValues,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    boolean result = super.validateAttributeDetailValueLiteral(eAnnotation, eModelElement, entry, attribute, literalValue, dataValues, diagnostics, context);
    if (result || diagnostics != null)
    {
      Set<String> validKeys = PropertySwitch.VALID_KEYS.get(attribute);
      if (validKeys != null)
      {
        @SuppressWarnings("unchecked")
        List<String> values = (List<String>)(List<?>)dataValues;
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
    private static final EClass ANNOTATION_PACKAGE_CLASS;

    private static final EClass ANNOTATION_CLASSIFIER_CLASS;

    private static final EClass ANNOTATION_OPERATION_CLASS;

    private static final Map<EAttribute, Set<String>> VALID_KEYS = new HashMap<EAttribute, Set<String>>();

    static
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("annotation");
      ePackage.setNsPrefix("annotation");
      ePackage.setNsURI("annotation");
      EList<EClassifier> eClassifiers = ePackage.getEClassifiers();

      final EDataType javaIdentifier = EcoreFactory.eINSTANCE.createEDataType();
      javaIdentifier.setName("JavaIdentifier");
      javaIdentifier.setInstanceClass(String.class);
      ePackage.getEClassifiers().add(javaIdentifier);

      final EDataType uriDataType = EcoreFactory.eINSTANCE.createEDataType();
      uriDataType.setName("WellFormedURI");
      uriDataType.setInstanceClass(String.class);
      ePackage.getEClassifiers().add(uriDataType);

      ANNOTATION_PACKAGE_CLASS = EcoreFactory.eINSTANCE.createEClass();
      ANNOTATION_PACKAGE_CLASS.setName("Package");
      eClassifiers.add(ANNOTATION_PACKAGE_CLASS);

      EAttribute schemaLocationAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      schemaLocationAttribute.setName("schemaLocation");
      schemaLocationAttribute.setEType(uriDataType);
      ANNOTATION_PACKAGE_CLASS.getEStructuralFeatures().add(schemaLocationAttribute);

      EAttribute settingDelegatesAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      settingDelegatesAttribute.setName("settingDelegates");
      settingDelegatesAttribute.setUpperBound(-1);
      settingDelegatesAttribute.setEType(uriDataType);
      ANNOTATION_PACKAGE_CLASS.getEStructuralFeatures().add(settingDelegatesAttribute);
      VALID_KEYS.put(settingDelegatesAttribute, EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.keySet());

      EAttribute validationDelegatesAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      validationDelegatesAttribute.setName("validationDelegates");
      validationDelegatesAttribute.setUpperBound(-1);
      validationDelegatesAttribute.setEType(uriDataType);
      ANNOTATION_PACKAGE_CLASS.getEStructuralFeatures().add(validationDelegatesAttribute);
      VALID_KEYS.put(validationDelegatesAttribute, EValidator.ValidationDelegate.Registry.INSTANCE.keySet());

      EAttribute invocationDelegatesAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      invocationDelegatesAttribute.setName("invocationDelegates");
      invocationDelegatesAttribute.setUpperBound(-1);
      invocationDelegatesAttribute.setEType(uriDataType);
      ANNOTATION_PACKAGE_CLASS.getEStructuralFeatures().add(invocationDelegatesAttribute);
      VALID_KEYS.put(invocationDelegatesAttribute, EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.keySet());

      EAttribute conversionDelegatesAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      conversionDelegatesAttribute.setName("conversionDelegates");
      conversionDelegatesAttribute.setUpperBound(-1);
      conversionDelegatesAttribute.setEType(uriDataType);
      ANNOTATION_PACKAGE_CLASS.getEStructuralFeatures().add(conversionDelegatesAttribute);
      VALID_KEYS.put(conversionDelegatesAttribute, EDataType.Internal.ConversionDelegate.Factory.Registry.INSTANCE.keySet());

      ANNOTATION_CLASSIFIER_CLASS = EcoreFactory.eINSTANCE.createEClass();
      ANNOTATION_CLASSIFIER_CLASS.setName("Classifier");
      eClassifiers.add(ANNOTATION_CLASSIFIER_CLASS);

      EAttribute constraintsAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      constraintsAttribute.setName("constraints");
      constraintsAttribute.setEType(javaIdentifier);
      constraintsAttribute.setUpperBound(-1);
      ANNOTATION_CLASSIFIER_CLASS.getEStructuralFeatures().add(constraintsAttribute);

      ANNOTATION_OPERATION_CLASS = EcoreFactory.eINSTANCE.createEClass();
      ANNOTATION_OPERATION_CLASS.setName("Operation");
      eClassifiers.add(ANNOTATION_OPERATION_CLASS);

      EAttribute invariantAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      invariantAttribute.setName("invariant");
      invariantAttribute.setEType(EcorePackage.Literals.EBOOLEAN);
      ANNOTATION_OPERATION_CLASS.getEStructuralFeatures().add(invariantAttribute);

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

    protected abstract void addFeatures(EClass eClass);

    @Override
    public Void caseEClassifier(EClassifier eClassifier)
    {
      addFeatures(ANNOTATION_CLASSIFIER_CLASS);
      return null;
    }

    @Override
    public Void caseEOperation(EOperation eOperation)
    {
      addFeatures(ANNOTATION_OPERATION_CLASS);
      return null;
    }

    @Override
    public Void caseEPackage(EPackage object)
    {
      addFeatures(ANNOTATION_PACKAGE_CLASS);
      return null;
    }
  }
}
