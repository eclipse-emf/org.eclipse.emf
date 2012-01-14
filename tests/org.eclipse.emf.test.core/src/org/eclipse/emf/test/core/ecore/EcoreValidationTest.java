/**
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


public class EcoreValidationTest extends TestCase
{
  protected static final boolean SYSOUT = false;
  
  public EcoreValidationTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("EcoreValidationTest");
    ts.addTest(new EcoreValidationTest("testEcoreValidator"));
    ts.addTest(new EcoreValidationTest("testMatching"));
    ts.addTest(new EcoreValidationTest("validateAllRegisteredModels"));
    return ts;
  }

  @Override
  protected void setUp()
  {
    EcorePackage.eINSTANCE.eClass();
  }

  public void testEcoreValidator()
  {
    // Validate that the Ecore package instance itself is okay.
    {
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(EcorePackage.eINSTANCE);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // An annotation with an empty string for the source isn't valid.
    {
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      eAnnotation.setSource("");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAnnotation);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_SOURCE_URI,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAnnotation },
         diagnostic.getChildren().get(0));
    }

    // An annotation with a source containing a space isn't valid.
    {
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      eAnnotation.setSource("a b");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAnnotation);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_SOURCE_URI,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAnnotation },
         diagnostic.getChildren().get(0));
    }

    // Two map entries with the same key are not valid.
    //
    {
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      {
        EObject entry = EcoreFactory.eINSTANCE.create(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY);
        entry.eSet(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY, "a");
        @SuppressWarnings("unchecked")
        Map.Entry<String, String> stringToStringMapEntry = (Map.Entry<String, String>)entry;
        eAnnotation.getDetails().add(stringToStringMapEntry);
      }
      {
        EObject entry = EcoreFactory.eINSTANCE.create(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY);
        entry.eSet(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY, "a");
        @SuppressWarnings("unchecked")
        Map.Entry<String, String> stringToStringMapEntry = (Map.Entry<String, String>)entry;
        eAnnotation.getDetails().add(stringToStringMapEntry);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAnnotation);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EObjectValidator.EOBJECT__EVERY_MAP_ENTRY_UNIQUE,
         EObjectValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAnnotation, EcorePackage.Literals.EANNOTATION__DETAILS, eAnnotation.getDetails().get(1), eAnnotation.getDetails().get(0) },
         diagnostic.getChildren().get(0));
    }

    // A named element without a name is invalid, unless strict element names are disabled.
    //
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_NAME,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass },
         diagnostic.getChildren().get(0));

      diagnostic = Diagnostician.INSTANCE.validate(eClass, Collections.singletonMap(EcoreValidator.STRICT_NAMED_ELEMENT_NAMES, Boolean.FALSE));
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // An instance type name with type arguments is valid.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      eClass.setInstanceTypeName("_<_>");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // An instance type name that's just an empty string is not valid.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      eClass.setInstanceTypeName("");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_INSTANCE_TYPE_NAME,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass },
         diagnostic.getChildren().get(0));
    }

    // An instance type name with unbalanced "<" is not valid.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      eClass.setInstanceTypeName("_<_");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_INSTANCE_TYPE_NAME,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass },
         diagnostic.getChildren().get(0));
    }

    // An instance type name with unbalanced "[" is not valid.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      eClass.setInstanceTypeName("_[");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_INSTANCE_TYPE_NAME,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass },
         diagnostic.getChildren().get(0));
    }

    // A class that is an interface must also be abstract.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      eClass.setInterface(true);
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.INTERFACE_IS_ABSTRACT,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass },
         diagnostic.getChildren().get(0));
    }

    // A class can't have more than one attribute that is an ID.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a");
        eAttribute.setID(true);
        eAttribute.setEType(EcorePackage.Literals.ESTRING);
        eClass.getEStructuralFeatures().add(eAttribute);
      }
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("b");
        eAttribute.setID(true);
        eAttribute.setEType(EcorePackage.Literals.ESTRING);
        eClass.getEStructuralFeatures().add(eAttribute);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.AT_MOST_ONE_ID,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass, eClass.getEAttributes().get(1), eClass.getEAttributes().get(0)},
         diagnostic.getChildren().get(0));
    }

    // A class can't have two attributes with the same name.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a");
        eAttribute.setEType(EcorePackage.Literals.ESTRING);
        eClass.getEStructuralFeatures().add(eAttribute);
      }
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a");
        eAttribute.setEType(EcorePackage.Literals.ESTRING);
        eClass.getEStructuralFeatures().add(eAttribute);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_FEATURE_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass, eClass.getEAttributes().get(1), eClass.getEAttributes().get(0)},
         diagnostic.getChildren().get(0));
    }

    // A class can't have two attributes with the matching names.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a");
        eAttribute.setEType(EcorePackage.Literals.ESTRING);
        eClass.getEStructuralFeatures().add(eAttribute);
      }
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("A_");
        eAttribute.setEType(EcorePackage.Literals.ESTRING);
        eClass.getEStructuralFeatures().add(eAttribute);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.WARNING,
         EcoreValidator.UNIQUE_FEATURE_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass, eClass.getEAttributes().get(1), eClass.getEAttributes().get(0)},
         diagnostic.getChildren().get(0));
    }

    // A class can't have two operations with matching signatures.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
        eOperation.setName("a");
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eParameter.setEType(XMLTypePackage.Literals.HEX_BINARY);
          eOperation.getEParameters().add(eParameter);
        }
        eClass.getEOperations().add(eOperation);
      }
      {
        EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
        eOperation.setName("a");
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eParameter.setEType(XMLTypePackage.Literals.BASE64_BINARY);
          eOperation.getEParameters().add(eParameter);
        }
        eClass.getEOperations().add(eOperation);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_OPERATION_SIGNATURES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass, eClass.getEOperations().get(1), eClass.getEOperations().get(0)},
         diagnostic.getChildren().get(0));
    }

    // A class can't have circular super types.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.getESuperTypes().add(eClass);
      eClass.setName("_");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.NO_CIRCULAR_SUPER_TYPES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass },
         diagnostic.getChildren().get(0));
    }

    // A map entry class must have a key feature and a value feature.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      eClass.setInstanceClassName("java.util.Map$Entry");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(2, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_MAP_ENTRY_CLASS,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass },
         diagnostic.getChildren().get(0));
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_MAP_ENTRY_CLASS,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass },
         diagnostic.getChildren().get(1));
    }

    // Two enum literals can't have the same name nor matching names.
    {
      EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
      eEnum.setName("_");
      {
        EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
        eEnumLiteral.setName("a");
        eEnum.getELiterals().add(eEnumLiteral);
      }
      {
        EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
        eEnumLiteral.setName("a");
        eEnum.getELiterals().add(eEnumLiteral);
      }
      {
        EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
        eEnumLiteral.setName("A");
        eEnum.getELiterals().add(eEnumLiteral);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eEnum);
      assertEquals(2, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_ENUMERATOR_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eEnum, eEnum.getELiterals().get(1), eEnum.getELiterals().get(0) },
         diagnostic.getChildren().get(0));
      assertDiagnostic
        (Diagnostic.WARNING,
         EcoreValidator.UNIQUE_ENUMERATOR_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eEnum, eEnum.getELiterals().get(2), eEnum.getELiterals().get(0) },
         diagnostic.getChildren().get(1));
    }

    // Two enum literals can't have the same literals.
    {
      EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
      eEnum.setName("_");
      {
        EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
        eEnumLiteral.setName("a");
        eEnumLiteral.setLiteral("x");
        eEnum.getELiterals().add(eEnumLiteral);
      }
      {
        EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
        eEnumLiteral.setName("b");
        eEnumLiteral.setLiteral("x");
        eEnum.getELiterals().add(eEnumLiteral);
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eEnum);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_ENUMERATOR_LITERALS,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eEnum, eEnum.getELiterals().get(1), eEnum.getELiterals().get(0) },
         diagnostic.getChildren().get(0));
    }

    // Two parameters cannot have the same name.
    {
      EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
      eOperation.setName("_");
      {
        EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
        eParameter.setName("a");
        eParameter.setEType(EcorePackage.Literals.ESTRING);
        eOperation.getEParameters().add(eParameter);
      }
      {
        EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
        eParameter.setName("a");
        eParameter.setEType(EcorePackage.Literals.ESTRING);
        eOperation.getEParameters().add(eParameter);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eOperation);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_PARAMETER_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eOperation, eOperation.getEParameters().get(1), eOperation.getEParameters().get(0) },
         diagnostic.getChildren().get(0));
    }

    // Two type parameters cannot have the same name.
    {
      EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
      eOperation.setName("_");
      {
        ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
        eTypeParameter.setName("A");
        eOperation.getETypeParameters().add(eTypeParameter);
      }
      {
        ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
        eTypeParameter.setName("A");
        eOperation.getETypeParameters().add(eTypeParameter);
      }
      
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eOperation);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_TYPE_PARAMETER_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eOperation, eOperation.getETypeParameters().get(1), eOperation.getETypeParameters().get(0) },
         diagnostic.getChildren().get(0));
    }

    // Two type parameters cannot have the same name.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
        eTypeParameter.setName("A");
        eClass.getETypeParameters().add(eTypeParameter);
      }
      {
        ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
        eTypeParameter.setName("A");
        eClass.getETypeParameters().add(eTypeParameter);
      }
      
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_TYPE_PARAMETER_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass, eClass.getETypeParameters().get(1), eClass.getETypeParameters().get(0) },
         diagnostic.getChildren().get(0));
    }

    // A void operation must have upper bound 1.
    {
      EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
      eOperation.setName("_");
      eOperation.setUpperBound(2);
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eOperation);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.NO_REPEATING_VOID,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eOperation },
         diagnostic.getChildren().get(0));
    }

    // A package must have an nsURI.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsPrefix("");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_NS_URI,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage },
         diagnostic.getChildren().get(0));
    }

    // A null string is not a well formed nsURI for a package.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("");
      ePackage.setNsPrefix("");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_NS_URI,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage },
         diagnostic.getChildren().get(0));
    }

    // A string with a space is not a well formed nsURI for a package.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("a b");
      ePackage.setNsPrefix("");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_NS_URI,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage },
         diagnostic.getChildren().get(0));
    }

    // A prefix that starts with "xml" is not a well formed nsPrefix for a package.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("_");
      ePackage.setNsPrefix("xml");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_NS_PREFIX,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage },
         diagnostic.getChildren().get(0));
    }

    // A prefix that isn't an NCName because it contains a ":" is not a well formed nsPrefix for a package.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("_");
      ePackage.setNsPrefix("a:b");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_NS_PREFIX,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage },
         diagnostic.getChildren().get(0));
    }

    // A null prefix that an NCName and is not a well formed nsPrefix for a package.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("_");
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.WELL_FORMED_NS_PREFIX,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage },
         diagnostic.getChildren().get(0));
    }

    // Two subpackages cannot have the same name.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("_");
      ePackage.setNsPrefix("");
      {
        EPackage eSubpackage = EcoreFactory.eINSTANCE.createEPackage();
        eSubpackage.setName("a");
        eSubpackage.setNsURI("a");
        eSubpackage.setNsPrefix("");
        ePackage.getESubpackages().add(eSubpackage);
      }
      {
        EPackage eSubpackage = EcoreFactory.eINSTANCE.createEPackage();
        eSubpackage.setName("a");
        eSubpackage.setNsURI("b");
        eSubpackage.setNsPrefix("");
        ePackage.getESubpackages().add(eSubpackage);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_SUBPACKAGE_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage, ePackage.getESubpackages().get(1), ePackage.getESubpackages().get(0) },
         diagnostic.getChildren().get(0));
    }

    // Two classifiers cannot have the same name nor matching names.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("_");
      ePackage.setNsPrefix("");
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("a");
        ePackage.getEClassifiers().add(eClass);
      }
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("a");
        ePackage.getEClassifiers().add(eClass);
      }
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("A");
        ePackage.getEClassifiers().add(eClass);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(2, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_CLASSIFIER_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage, ePackage.getEClassifiers().get(1), ePackage.getEClassifiers().get(0) },
         diagnostic.getChildren().get(0));
      assertDiagnostic
        (Diagnostic.WARNING,
         EcoreValidator.UNIQUE_CLASSIFIER_NAMES,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage, ePackage.getEClassifiers().get(2), ePackage.getEClassifiers().get(0) },
         diagnostic.getChildren().get(1));
    }

    // Two packages cannot have the same namespace URI.
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("_");
      ePackage.setNsPrefix("");
      {
        EPackage eSubpackage = EcoreFactory.eINSTANCE.createEPackage();
        eSubpackage.setName("a");
        eSubpackage.setNsURI("_");
        eSubpackage.setNsPrefix("");
        ePackage.getESubpackages().add(eSubpackage);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
      assertEquals(2, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_NS_URIS,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage, ePackage.getESubpackages().get(0) },
         diagnostic.getChildren().get(0));
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.UNIQUE_NS_URIS,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { ePackage.getESubpackages().get(0), ePackage },
         diagnostic.getChildren().get(1));
    }
    
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("_");
      eAttribute.setEType(EcorePackage.Literals.ERESOURCE);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TRANSIENT,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute },
         diagnostic.getChildren().get(0));
    }

    // There are many ways for opposites to be inconsistent so we'll test them all.
    LOOP:
    for (int i = 0; i < 100; ++i)
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      EClass A;
      EReference a;
      EClass B;
      EReference b;
      ePackage.setName("_");
      ePackage.setNsURI("_");
      ePackage.setNsPrefix("");
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("A");
        ePackage.getEClassifiers().add(eClass);
        {
          EReference eReference = EcoreFactory.eINSTANCE.createEReference();
          eReference.setName("b");
          eClass.getEStructuralFeatures().add(eReference);
          b = eReference;
        }
        A = eClass;
      }
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("B");
        ePackage.getEClassifiers().add(eClass);
        {
          EReference eReference = EcoreFactory.eINSTANCE.createEReference();
          eReference.setName("a");
          eClass.getEStructuralFeatures().add(eReference);
          a = eReference;
        }
        B = eClass;
      }

      switch (i)
      {
        // Valid unidirectional references.
        case 0:
        {
          a.setEType(A);
          b.setEType(B);

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        // Valid bidirectional references.
        case 1:
        {
          a.setEType(A);
          a.setEOpposite(b);
          b.setEType(B);
          b.setEOpposite(a);

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        // The opposite must be a feature of the reference's type.
        case 2:
        {
          a.setEType(B); // <-- Bad type that doesn't have a b feature
          a.setEOpposite(b);
          b.setEType(B);
          b.setEOpposite(a);

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_OPPOSITE_NOT_FROM_TYPE,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] { a, b, B },
             diagnostic.getChildren().get(0));
          break;
        }
        // The opposite of the opposite must be the feature itself.
        case 3:
        {
          a.setEType(A);
          a.setEOpposite(b);
          b.setEType(B);
          //  b.setEOpposite(a); <-- No opposite for the opposite

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_OPPOSITE_NOT_MATCHING,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] { a, b, null },
             diagnostic.getChildren().get(0));
          break;
        }
        // Valid bidirectional container/containment references.
        case 4:
        {
          a.setEType(A);
          a.setEOpposite(b);
          a.setContainment(true);
          b.setEType(B);
          b.setEOpposite(a);

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        // A container reference must have upper bound 1.
        case 5:
        {
          a.setEType(A);
          a.setEOpposite(b);
          a.setContainment(true);
          b.setEType(B);
          b.setEOpposite(a);
          b.setUpperBound(2); // <-- Bad upper bound.

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.SINGLE_CONTAINER,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] { b },
             diagnostic.getChildren().get(0));
          break;
        }
        // Valid bidirectional references that are transient at both ends.
        case 6:
        {
          a.setEType(A);
          a.setEOpposite(b);
          a.setTransient(true);
          b.setEType(B);
          b.setEOpposite(a);
          b.setTransient(true);

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        // Valid bidirectional references with one transient end that doesn't resolve proxy.
        case 7:
        {
          a.setEType(A);
          a.setEOpposite(b);
          a.setTransient(true);
          b.setEType(B);
          b.setEOpposite(a);
          b.setResolveProxies(false);

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        // Valid bidirectional references with on transient end that's a container.
        case 8:
        {
          a.setEType(A);
          a.setEOpposite(b);
          a.setContainment(true);
          b.setEType(B);
          b.setEOpposite(a);
          b.setTransient(true);

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        // The opposite of a transient bidirectional reference must either be transient as, non-proxy resolving, or a container.
        case 9:
        {
          a.setEType(A);
          a.setEOpposite(b);
          a.setTransient(true);
          // a.setContainment(true); <-- Must be a containment for b to be a container and hence be valid.
          b.setEType(B);
          b.setEOpposite(a);
          // b.setTransient(true);  <-- Must be transient.
          // b.setResolveProxies(false); <-- Or must not resolve proxies.

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_OPPOSITE_BAD_TRANSIENT,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] { a, b },
             diagnostic.getChildren().get(0));
          break;
        }
        // Both sides can't be containment.
        case 10:
        {
          a.setEType(A);
          a.setEOpposite(b);
          a.setContainment(true);
          b.setEType(B);
          b.setEOpposite(a);
          b.setContainment(true);

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(2, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_OPPOSITE_BOTH_CONTAINMENT,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] { b , a },
             diagnostic.getChildren().get(0));
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_OPPOSITE_BOTH_CONTAINMENT,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] { a , b },
             diagnostic.getChildren().get(1));
          break;
        }
        default:
        {
          break LOOP;
        }
      }
    }

    // The lower bound of a typed element must be >= 0.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      eAttribute.setEType(EcorePackage.Literals.ESTRING);
      eAttribute.setLowerBound(-1);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.VALID_LOWER_BOUND,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute },
         diagnostic.getChildren().get(0));
    }

    // The upper bound of a typed elements must be -2, -1, or >= 1 not 3.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      eAttribute.setEType(EcorePackage.Literals.ESTRING);
      eAttribute.setUpperBound(-3);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.VALID_UPPER_BOUND,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute },
         diagnostic.getChildren().get(0));
    }

    // The upper bound of a typed element cannot be 0.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      eAttribute.setEType(EcorePackage.Literals.ESTRING);
      eAttribute.setUpperBound(0);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.VALID_UPPER_BOUND,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute },
         diagnostic.getChildren().get(0));
    }

    // The lower bound of a typed element must be less than or equal to the upper bound, unless the upper bound is -1, or -2.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      eAttribute.setEType(EcorePackage.Literals.ESTRING);
      eAttribute.setLowerBound(2);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_BOUNDS,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute },
         diagnostic.getChildren().get(0));
    }

    // An attribute must have a type that's a data type.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      eAttribute.setEType(EcorePackage.Literals.EOBJECT); // <-- Bad class type.

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EObjectValidator.EOBJECT__EVERY_MULTIPCITY_CONFORMS,
         EObjectValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute, EcorePackage.Literals.EATTRIBUTE__EATTRIBUTE_TYPE },
         diagnostic.getChildren().get(0));
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_CLASS_NOT_PERMITTED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute.getEGenericType() },
         diagnostic.getChildren().get(1));
    }

    // A generic type used as the type of an attribute must not refer to a class.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      eGenericType.setEClassifier(EcorePackage.Literals.EOBJECT); // <-- Bad class type.
      eAttribute.setEGenericType(eGenericType);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EObjectValidator.EOBJECT__EVERY_MULTIPCITY_CONFORMS,
         EObjectValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute, EcorePackage.Literals.EATTRIBUTE__EATTRIBUTE_TYPE },
         diagnostic.getChildren().get(0));
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_CLASS_NOT_PERMITTED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eGenericType },
         diagnostic.getChildren().get(1));
    }

    // A reference must have a type that's a class.
    {
      EReference eReference = EcoreFactory.eINSTANCE.createEReference();
      eReference.setName("r");
      eReference.setEType(EcorePackage.Literals.ESTRING);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eReference);
      assertEquals(2, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EObjectValidator.EOBJECT__EVERY_MULTIPCITY_CONFORMS,
         EObjectValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eReference, EcorePackage.Literals.EREFERENCE__EREFERENCE_TYPE },
         diagnostic.getChildren().get(0));
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_DATA_TYPE_NOT_PERMITTED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eReference.getEGenericType() },
         diagnostic.getChildren().get(1));
    }

    // A generic type used as the type of a reference must not refer to a data type.
    {
      EReference eReference = EcoreFactory.eINSTANCE.createEReference();
      eReference.setName("r");
      EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
      eReference.setEGenericType(eGenericType);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eReference);
      assertEquals(2, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EObjectValidator.EOBJECT__EVERY_MULTIPCITY_CONFORMS,
         EObjectValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eReference, EcorePackage.Literals.EREFERENCE__EREFERENCE_TYPE },
         diagnostic.getChildren().get(0));
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_DATA_TYPE_NOT_PERMITTED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eReference.getEGenericType() },
         diagnostic.getChildren().get(1));
    }


    // A reference with a valid key that's a feature of the reference's type.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");

      EAttribute a;
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a");
        eAttribute.setEType(EcorePackage.Literals.ESTRING);
        eClass.getEStructuralFeatures().add(eAttribute);
        a = eAttribute;
      }

      {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setName("r");
        eReference.setEType(eClass);
        eReference.getEKeys().add(a);
        eClass.getEStructuralFeatures().add(eReference);
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // Reference with a key that isn't a feature of the type.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");

      EAttribute a;
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a");
        eAttribute.setEType(EcorePackage.Literals.ESTRING);
        eClass.getEStructuralFeatures().add(eAttribute);
        a = eAttribute;
      }

      EReference r;
      {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setName("r");
        eReference.setEType(EcorePackage.Literals.EOBJECT); // <-- Reference's type doesn't have the key attribute as a feature.
        eReference.getEKeys().add(a);
        eClass.getEStructuralFeatures().add(eReference);
        r = eReference;
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_KEYS,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { r, a },
         diagnostic.getChildren().get(0));
    }

    // A generic type can act as a wildcard only when used as the type argument of a generic type, not when used as the type of a type element.
    {
      EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
      eOperation.setName("_");
      EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      eOperation.setEGenericType(eGenericType);
      // eGenericType.setETypeParameter(...); // <-- Neither a type parameter
      // eGenericType.setEClassifier(...); // <-- Nor a classifier.

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eOperation);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_WILDCARD_NOT_PERMITTED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eGenericType },
         diagnostic.getChildren().get(0));
    }

    // A generic type must not reference both a classifier and a type parameter.
    {
      ETypeParameter T;
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
        eTypeParameter.setName("T");
        eClass.getETypeParameters().add(eTypeParameter);
        T = eTypeParameter;
      }
      {
        ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
        eTypeParameter.setName("U");
        eClass.getETypeParameters().add(eTypeParameter);
        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setETypeParameter(T); // <-- Both a type parameter
          eGenericType.setEClassifier(EcorePackage.Literals.EOBJECT); // <-- And a classifier.
          eTypeParameter.getEBounds().add(eGenericType);
        }
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_NO_TYPE_PARAMETER_AND_CLASSIFIER,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass.getETypeParameters().get(1).getEBounds().get(0) },
         diagnostic.getChildren().get(0));
    }

    // A generic super type must refer to a class.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT); // <-- Bad reference to data type.
        eClass.getEGenericSuperTypes().add(eGenericType);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_CLASS_REQUIRED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass.getEGenericSuperTypes().get(0) },
         diagnostic.getChildren().get(0));
    }

    // No two generic super types may reference the same class.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EOBJECT); // <-- Both refer to EObject.
        eClass.getEGenericSuperTypes().add(eGenericType);
      }
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EOBJECT); // <-- Both refer to EObject.
        eClass.getEGenericSuperTypes().add(eGenericType);
      }
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_SUPER_TYPES_DUPLICATE,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass, eClass.getEGenericSuperTypes().get(1), eClass.getEGenericSuperTypes().get(0) },
         diagnostic.getChildren().get(0));
    }

    // A generic type used as a type argument can be a wildcard.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_CLASS);
        eAttribute.setEGenericType(eGenericType);
        {
          EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.getETypeArguments().add(eGenericTypeArgument);
        }
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // A generic type used as a valid wildcard can have a lower bound.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_CLASS);
        eAttribute.setEGenericType(eGenericType);
        {
          EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
          {
            EGenericType eGenericTypeLowerBound = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeLowerBound.setEClassifier(EcorePackage.Literals.EOBJECT);
            eGenericTypeArgument.setEUpperBound(eGenericTypeLowerBound);
          }
          eGenericType.getETypeArguments().add(eGenericTypeArgument);
        }
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // A generic type used as a valid wildcard can have an upper bound.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_CLASS);
        eAttribute.setEGenericType(eGenericType);
        {
          EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
          {
            EGenericType eGenericTypeUpperBound = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeUpperBound.setEClassifier(EcorePackage.Literals.EOBJECT);
            eGenericTypeArgument.setEUpperBound(eGenericTypeUpperBound);
          }
          eGenericType.getETypeArguments().add(eGenericTypeArgument);
        }
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // A generic type can't have both an upper and lower bound.
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_CLASS);
        eAttribute.setEGenericType(eGenericType);
        {
          EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
          {
            EGenericType eGenericTypeLowerBound = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeLowerBound.setEClassifier(EcorePackage.Literals.EOBJECT);
            eGenericTypeArgument.setELowerBound(eGenericTypeLowerBound); // <-- A lower bound.
          }
          {
            EGenericType eGenericTypeUpperBound = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeUpperBound.setEClassifier(EcorePackage.Literals.EOBJECT);
            eGenericTypeArgument.setEUpperBound(eGenericTypeUpperBound); // <-- And an upper bound.
          }
          eGenericType.getETypeArguments().add(eGenericTypeArgument);
        }
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_BOUNDS_NO_LOWER_AND_UPPER,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute.getEGenericType().getETypeArguments().get(0) },
         diagnostic.getChildren().get(0));
    }

    // A generic type can't have an upper bound except when used as a type argument.
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EOBJECT);
        {
          EGenericType eGenericTypeUpperBound = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericTypeUpperBound.setEClassifier(EcorePackage.Literals.EOBJECT);
          eGenericType.setEUpperBound(eGenericTypeUpperBound); // <-- Can't have bounds in this context.
        }
        eClass.getEGenericSuperTypes().add(eGenericType);
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_BOUNDS_NOT_ALLOWED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass.getEGenericSuperTypes().get(0) },
         diagnostic.getChildren().get(0));
    }

    // A generic type with bounds can't also refer to a type parameter or classifier
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      eAttribute.setName("a");
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_CLASS);
        eAttribute.setEGenericType(eGenericType);
        {
          EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericTypeArgument.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT); // <-- Can't have classifier with bounds.
          {
            EGenericType eGenericTypeUpperBound = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeUpperBound.setEClassifier(EcorePackage.Literals.EOBJECT);
            eGenericTypeArgument.setEUpperBound(eGenericTypeUpperBound); // <-- Can't have bounds with classifier.
          }
          eGenericType.getETypeArguments().add(eGenericTypeArgument);
        }
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAttribute);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_BOUNDS_NO_BOUNDS_WITH_TYPE_PARAMETER_OR_CLASSIFIER,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eAttribute.getEGenericType().getETypeArguments().get(0) },
         diagnostic.getChildren().get(0));
    }

    // Generic type with arguments must have a classifier.
    {
      EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      {
        EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericTypeArgument.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
        eGenericType.getETypeArguments().add(eGenericTypeArgument); // Can't have arguments because there is no classifier.
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eGenericType);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_ARGUMENTS_NONE_ALLOWED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eGenericType },
         diagnostic.getChildren().get(0));
    }

    // Generic type with classifier that specifies type parameters must have corresponding arguments,
    // but it's only a warning, analogous to a raw type warning, to have no arguments when there are type parameters.
    {
      EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_CLASS);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eGenericType);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.WARNING,
         EcoreValidator.CONSISTENT_ARGUMENTS_NONE,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eGenericType },
         diagnostic.getChildren().get(0));
    }

    // Generic type's classifier cannot specify a primitive type except as the generic type of a typed element.
    {
      EClass aClass = EcoreFactory.eINSTANCE.createEClass();
      aClass.setName("AClass"); 
      ETypeParameter e = EcoreFactory.eINSTANCE.createETypeParameter();
      e.setName("E");
      aClass.getETypeParameters().add(e);
      
      EClass bClass = EcoreFactory.eINSTANCE.createEClass();
      bClass.setName("BClass");
      EGenericType superType = EcoreFactory.eINSTANCE.createEGenericType();
      superType.setEClassifier(aClass);      
      EGenericType typeArgument = EcoreFactory.eINSTANCE.createEGenericType();
      typeArgument.setEClassifier(EcorePackage.Literals.EINT);
      superType.getETypeArguments().add(typeArgument);
      bClass.getEGenericSuperTypes().add(superType);

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(bClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_PRIMITIVE_TYPE_NOT_PERMITTED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { typeArgument },
         diagnostic.getChildren().get(0));
    }
    
    // A generic type with classifier that specifies type parameters and that has arguments must have a matching number of them.
    {
      EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      eGenericType.setEClassifier(EcorePackage.Literals.EJAVA_CLASS);
      {
        EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericTypeArgument.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
        eGenericType.getETypeArguments().add(eGenericTypeArgument);
      }
      {
        EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericTypeArgument.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
        eGenericType.getETypeArguments().add(eGenericTypeArgument); // <-- A second argument is not allowed.
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eGenericType);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_ARGUMENTS_INCORRECT_NUMBER,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eGenericType },
         diagnostic.getChildren().get(0));
    }

    // Generic type must not reference an out of scope type parameter
    {
      ETypeParameter T;
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
        eOperation.setName("f");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eOperation.getETypeParameters().add(eTypeParameter);
          T = eTypeParameter;
        }
        eClass.getEOperations().add(eOperation);
      }
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName("a");
        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setETypeParameter(T);
          eAttribute.setEGenericType(eGenericType);
        }
        eClass.getEStructuralFeatures().add(eAttribute);
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_TYPE_PARAMETER_NOT_IN_SCOPE,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass.getEStructuralFeatures().get(0).getEGenericType() },
         diagnostic.getChildren().get(0));
    }

    // Generic type must not make a forward reference to a type parameter
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
        eTypeParameter.setName("T");
        eClass.getETypeParameters().add(eTypeParameter);
        {
          EGenericType eBound = EcoreFactory.eINSTANCE.createEGenericType();
          eBound.setETypeParameter(eTypeParameter);
          eTypeParameter.getEBounds().add(eBound);
        }
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_TYPE_PARAMETER_NOT_IN_SCOPE,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { eClass.getETypeParameters().get(0).getEBounds().get(0) },
         diagnostic.getChildren().get(0));
    }

    // Type parameter can be used as the type argument of the
    // bound
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName("_");
      {
        ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
        eTypeParameter.setName("T");
        eClass.getETypeParameters().add(eTypeParameter);
        {
          EGenericType eBound = EcoreFactory.eINSTANCE.createEGenericType();
          eBound.setEClassifier(eClass);
          eTypeParameter.getEBounds().add(eBound);
          
          EGenericType typeArgument = EcoreFactory.eINSTANCE.createEGenericType();
          typeArgument.setETypeParameter(eTypeParameter);
          eBound.getETypeArguments().add(typeArgument);
        }
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }
    
    // A generic super type can validly use type arguments.
    {
      EClass A;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("A");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        A = eClass;
      }
      EClass B;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("B");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }

        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(A);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setETypeParameter(eClass.getETypeParameters().get(0));
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }

        B = eClass;
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(B);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // A generic super type must not have wildcard type arguments.
    {
      EClass A;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("A");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        A = eClass;
      }
      EClass B;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("B");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }

        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(A);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }

        B = eClass;
      }

      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(B);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_TYPE_WILDCARD_NOT_PERMITTED,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { B.getEGenericSuperTypes().get(0).getETypeArguments().get(0) },
         diagnostic.getChildren().get(0));
    }

    // Generic super types can pass template parameters in complex ways with redundant diamond inheritance.
    {
      EClass A;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("A");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        A = eClass;
      }
      EClass B;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("B");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }

        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(A);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setETypeParameter(eClass.getETypeParameters().get(0));
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }

        B = eClass;
      }

      EClass C;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("C");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }

        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(A);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setETypeParameter(eClass.getETypeParameters().get(0));
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }
        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(B);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setETypeParameter(eClass.getETypeParameters().get(0));
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }

        C = eClass;
      }

      assertEquals(2, C.getEAllGenericSuperTypes().size());
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(C);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // Generic super types can pass instantiate template parameters in complex ways with redundant diamond inheritance.
    {
      EClass A;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("A");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        A = eClass;
      }
      EClass B;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("B");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }

        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(A);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setETypeParameter(eClass.getETypeParameters().get(0));
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }

        B = eClass;
      }

      EClass C;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("C");

        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(A);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setEClassifier(EcorePackage.Literals.ESTRING); // <-- Use EString for A here.
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }
        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(B);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setEClassifier(EcorePackage.Literals.ESTRING); // <-- Both use EString for B and hence indirectly for A.
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }

        C = eClass;
      }

      assertEquals(2, C.getEAllGenericSuperTypes().size());
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(C);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
    }

    // Generic super types can pass instantiate template parameters in complex ways with redundant diamond inheritance.
    {
      EClass A;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("A");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        A = eClass;
      }
      EClass B;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("B");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
        }

        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(A);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setETypeParameter(eClass.getETypeParameters().get(0));
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }

        B = eClass;
      }

      EClass C;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("C");

        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(A);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setEClassifier(EcorePackage.Literals.ESTRING); // <-- Use EString for A here.
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }
        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(B);
          eClass.getEGenericSuperTypes().add(eGenericType);
          {
            EGenericType eGenericTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericTypeArgument.setEClassifier(EcorePackage.Literals.EINTEGER_OBJECT); // <-- Both use EInteger for B and hence indirectly for A.
            eGenericType.getETypeArguments().add(eGenericTypeArgument);
          }
        }

        C = eClass;
      }

      assertEquals(3, C.getEAllGenericSuperTypes().size());
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(C);
      assertEquals(1, diagnostic.getChildren().size());
      assertDiagnostic
        (Diagnostic.ERROR,
         EcoreValidator.CONSISTENT_SUPER_TYPES_CONFLICT,
         EcoreValidator.DIAGNOSTIC_SOURCE,
         new Object [] { C, C.getEAllGenericSuperTypes().get(1), C.getEAllGenericSuperTypes().get(0) },
         diagnostic.getChildren().get(0));
    }
    
    LOOP:
    for (int i = 0; i < 100; ++i)
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("_");
      ePackage.setNsPrefix("");

      // interface X {}
      //
      EClass X;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("X");
        
        ePackage.getEClassifiers().add(eClass);
        X = eClass;
      }

      // interface Y extends X {}
      //
      EClass Y;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("Y");
        eClass.getESuperTypes().add(X);
        
        ePackage.getEClassifiers().add(eClass);
        Y = eClass;
      }

      // interface Container<E> {}
      //
      EClass Container;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("Container");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("E");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        ePackage.getEClassifiers().add(eClass);
        Container = eClass;
      }

      // interface A<T extends Container<?>> {}
      //
      EClass A;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("A");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");
          eClass.getETypeParameters().add(eTypeParameter);
          {
            EGenericType eBound = EcoreFactory.eINSTANCE.createEGenericType();
            eBound.setEClassifier(Container);
            {
              EGenericType eTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
              if (i == 2 || i == 3)
              {
                eTypeArgument.setEClassifier(X);  // <-- Container<?> or Container<X> 
              }
              eBound.getETypeArguments().add(eTypeArgument);
            }
            eTypeParameter.getEBounds().add(eBound);
          }
        }
        ePackage.getEClassifiers().add(eClass);
        A = eClass;
      }

      // interface B<T extends Container<EString>> extends A<T> {}
      //
      EClass B;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("B");
        ETypeParameter T;
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("T");

          // <-- If we don't set a bound, that satisfies A's bound, which requires a Container, 
          // then it won't be valid to pass T as the argument to A<T>.
          //
          if (i != 1) 
          {
            EGenericType eBound = EcoreFactory.eINSTANCE.createEGenericType();
            eBound.setEClassifier(Container);
            {
              EGenericType eTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
              if (i == 2)
              {
                eTypeArgument.setEClassifier(X);   // <-- Container<X> is fine
              }
              else if (i == 3)
              {
                eTypeArgument.setEClassifier(Y); // <-- Container<Y> is an error
              }
              else
              {
                eTypeArgument.setEClassifier(EcorePackage.Literals.ESTRING);  // <-- Container<EString> is fine.
              }
              eBound.getETypeArguments().add(eTypeArgument);
            }
            eTypeParameter.getEBounds().add(eBound);
          }
          eClass.getETypeParameters().add(eTypeParameter);
          T = eTypeParameter;
        }
        {
          EGenericType eGenericSuperType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericSuperType.setEClassifier(A);
          {
            EGenericType eTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eTypeArgument.setETypeParameter(T);
            eGenericSuperType.getETypeArguments().add(eTypeArgument);
          }
          eClass.getEGenericSuperTypes().add(eGenericSuperType);
        }
        ePackage.getEClassifiers().add(eClass);
        B = eClass;
      }

      switch (i)
      {
        case 0:
        {
          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        case 1:
        {
          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_ARGUMENTS_INVALID_SUBSTITUTION,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] 
             { 
               B.getEGenericSuperTypes().get(0), 
               B.getEGenericSuperTypes().get(0).getETypeArguments().get(0),
               A.getETypeParameters().get(0)
             },
             diagnostic.getChildren().get(0));
          break;
        }
        case 2:
        {
          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        case 3:
        {
          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_ARGUMENTS_INVALID_SUBSTITUTION,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] 
             { 
               B.getEGenericSuperTypes().get(0), 
               B.getEGenericSuperTypes().get(0).getETypeArguments().get(0),
               A.getETypeParameters().get(0)
             },
             diagnostic.getChildren().get(0));
          break;
        }
        default:
        {
          break LOOP;
        }
      }
    }
  }
  
  interface X
  {
    //
  }

  interface Y extends X
  {
    //
  }
  
  interface Container<E>
  {
    //
  }
  
  interface DerivedContainer<E> extends Container<E>
  {
    //
  }
  
  interface Holder<E, F extends E, H extends Container<E>>
  {
    <E1, F1 extends E1, G1 extends Container<E1>> void foo();
  }
      
  public void testMatching()
  {
    LOOP:
    for (int i = 0; i < 100; ++i)
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setName("_");
      ePackage.setNsURI("_");
      ePackage.setNsPrefix("");

      // interface X {}
      //
      EClass eClassX;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("X");
        
        ePackage.getEClassifiers().add(eClass);
        eClassX = eClass;
      }

      // interface Y extends X {}
      //
      // EClass eClassY;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("Y");
        eClass.getESuperTypes().add(eClassX);
        
        ePackage.getEClassifiers().add(eClass);
        // eClassY = eClass;
      }

      // interface Container<E> {}
      //
      EClass eClassContainer;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("Container");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("E");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        ePackage.getEClassifiers().add(eClass);
        eClassContainer = eClass;
      }

      // interface DerivedContainer<E>  extends Container<E> {}
      //
      // EClass eClassDerivedContainer;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("DerivedContainer");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("E");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        {
          EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
          eGenericType.setEClassifier(eClassContainer);
          {
            EGenericType eTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
            eTypeArgument.setETypeParameter(eClass.getETypeParameters().get(0));
            eGenericType.getETypeArguments().add(eTypeArgument);
          }
          eClass.getEGenericSuperTypes().add(eGenericType);
        }
        ePackage.getEClassifiers().add(eClass);
        // eClassDerivedContainer = eClass;
      }

      // interface Holder<E, F extends E, G extends Container<E>> 
      // {
      //    <E1, F1 extends E1, G1 extends Container<E1>> void foo();
      // }
      //
      EClass eClassHolder;
      EOperation eOperationFoo;
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("Holder");
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("E");
          eClass.getETypeParameters().add(eTypeParameter);
        }
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("F");
          {
            EGenericType eBound = EcoreFactory.eINSTANCE.createEGenericType();
            eBound.setETypeParameter(eClass.getETypeParameters().get(0));
            eTypeParameter.getEBounds().add(eBound);
          }
          eClass.getETypeParameters().add(eTypeParameter);
        }
        {
          ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
          eTypeParameter.setName("G");
          {
            EGenericType eBound = EcoreFactory.eINSTANCE.createEGenericType();
            eBound.setEClassifier(eClassContainer);
            if (i <= 5)   //  Container<E> or Container<? extends E> or <Container<? super E>>
            {
              EGenericType eTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeArgument.setETypeParameter(eClass.getETypeParameters().get(0));
              eBound.getETypeArguments().add(eTypeArgument);
            }
            else
            {
              EGenericType eTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
              if (i < 8)
              {
                EGenericType eUpperBound = EcoreFactory.eINSTANCE.createEGenericType();
                eUpperBound.setETypeParameter(eClass.getETypeParameters().get(0));
                eTypeArgument.setEUpperBound(eUpperBound);
              }
              else
              {
                EGenericType eLowerBound = EcoreFactory.eINSTANCE.createEGenericType();
                eLowerBound.setETypeParameter(eClass.getETypeParameters().get(0));
                eTypeArgument.setELowerBound(eLowerBound);
              }
              eBound.getETypeArguments().add(eTypeArgument);
            }
            eTypeParameter.getEBounds().add(eBound);
          }
          eClass.getETypeParameters().add(eTypeParameter);
        }
        ePackage.getEClassifiers().add(eClass);
        eClassHolder = eClass;
        
        {
          EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
          eOperation.setName("foo");
          {
            ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
            eTypeParameter.setName("E1");
            eOperation.getETypeParameters().add(eTypeParameter);
          }
          {
            ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
            eTypeParameter.setName("F1");
            {
              EGenericType eBound = EcoreFactory.eINSTANCE.createEGenericType();
              eBound.setETypeParameter(eOperation.getETypeParameters().get(0));
              eTypeParameter.getEBounds().add(eBound);
            }
            eOperation.getETypeParameters().add(eTypeParameter);
          }
          {
            ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
            eTypeParameter.setName("G1");
            {
              EGenericType eBound = EcoreFactory.eINSTANCE.createEGenericType();
              eBound.setEClassifier(eClassContainer);
              {
                EGenericType eTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
                eTypeArgument.setETypeParameter(eOperation.getETypeParameters().get(0));
                eBound.getETypeArguments().add(eTypeArgument);
              }
              eTypeParameter.getEBounds().add(eBound);
            }
            eOperation.getETypeParameters().add(eTypeParameter);
          }
          eClass.getEOperations().add(eOperation);
          eOperationFoo = eOperation;
        }
      }

      switch (i)
      {
        case 0:
        {
          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        case 1:
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eOperationFoo.getEParameters().add(eParameter);
          {
            EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(eClassHolder);
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(0));
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(1));
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(2));
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            eParameter.setEGenericType(eGenericType);
          }

          class HolderCase1<E, F extends E, H extends Container<E>>
          {
            @SuppressWarnings("unused")
            <E1, F1 extends E1, G1 extends Container<E1>> void foo(HolderCase1<E1, F1, G1> x)
            {
              this.foo(x);
            }
          }

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        case 2:
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eOperationFoo.getEParameters().add(eParameter);
          {
            EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(eClassHolder);
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            eParameter.setEGenericType(eGenericType);
          }

          class HolderCase2<E, F extends E, H extends Container<E>>
          {
            @SuppressWarnings("unused")
            <E1, F1 extends E1, G1 extends Container<E1>> void foo(HolderCase2<?, ?, ?> x)
            {
              this.foo(x);
            }
          }

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        case 3:
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eOperationFoo.getEParameters().add(eParameter);
          {
            EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(eClassHolder);
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              {
                EGenericType eUpperBound = EcoreFactory.eINSTANCE.createEGenericType();
                eUpperBound.setETypeParameter(eOperationFoo.getETypeParameters().get(1));  // <-- ? extends E1
                eTypeParameter.setEUpperBound(eUpperBound);
              }
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            eParameter.setEGenericType(eGenericType);
          }

          @SuppressWarnings("unused")
          class HolderCase3<E, F extends E, H extends Container<E>>
          {
            <E1, F1 extends E1, G1 extends Container<E1>> void foo(/* --> HolderCase3<?, ? extends E1, ?> x <-- */)
            {
              this.foo(/*x*/);
            }
          }

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_ARGUMENTS_INVALID_SUBSTITUTION,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] 
             { 
               eOperationFoo.getEParameters().get(0).getEGenericType(),
               eOperationFoo.getEParameters().get(0).getEGenericType().getETypeArguments().get(1),
               eClassHolder.getETypeParameters().get(1)
             },
             diagnostic.getChildren().get(0));
          break;
        }
        case 4:
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eOperationFoo.getEParameters().add(eParameter);
          {
            EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(eClassHolder);
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(0)); 
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(1)); 
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setEClassifier(eClassContainer);  // <-- Container
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            eParameter.setEGenericType(eGenericType);
          }

          @SuppressWarnings("unused")
          class HolderCase4<E, F extends E, H extends Container<E>>
          {
            <E1, F1 extends E1, G1 extends Container<E1>> void foo(/* --> HolderCase4<E1, F1, Container> x <-- */)
            {
              this.foo(/*x*/);
            }
          }

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(2, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_ARGUMENTS_INVALID_SUBSTITUTION,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] 
             { 
               eOperationFoo.getEParameters().get(0).getEGenericType(),
               eOperationFoo.getEParameters().get(0).getEGenericType().getETypeArguments().get(2),
               eClassHolder.getETypeParameters().get(2)
             },
             diagnostic.getChildren().get(0));
          assertDiagnostic
            (Diagnostic.WARNING,
             EcoreValidator.CONSISTENT_ARGUMENTS_NONE,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] 
             { 
               eOperationFoo.getEParameters().get(0).getEGenericType().getETypeArguments().get(2),
             },
             diagnostic.getChildren().get(1));
          break;
        }
        case 5:
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eOperationFoo.getEParameters().add(eParameter);
          {
            EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(eClassHolder);
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(0)); 
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(1)); 
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setEClassifier(eClassContainer);  // <-- Container
              {
                EGenericType eTypeParameterU1 = EcoreFactory.eINSTANCE.createEGenericType();
                eTypeParameterU1.setETypeParameter(eOperationFoo.getETypeParameters().get(1));  // <-- F1
                eTypeParameter.getETypeArguments().add(eTypeParameterU1);
              }
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            eParameter.setEGenericType(eGenericType);
          }

          @SuppressWarnings("unused")
          class HolderCase5<E, F extends E, H extends Container<E>>
          {
            <E1, F1 extends E1, G1 extends Container<E1>> void foo(/* --> HolderCase5<E1, F1, Container<F1>> x <-- */)
            {
              this.foo(/*x*/);
            }
          }

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_ARGUMENTS_INVALID_SUBSTITUTION,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] 
             { 
               eOperationFoo.getEParameters().get(0).getEGenericType(),
               eOperationFoo.getEParameters().get(0).getEGenericType().getETypeArguments().get(2),
               eClassHolder.getETypeParameters().get(2)
             },
             diagnostic.getChildren().get(0));
          break;
        }
        case 6:
        {
          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          break;
        }
        case 7:
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eOperationFoo.getEParameters().add(eParameter);
          {
            EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(eClassHolder);
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(0)); 
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(1)); 
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setEClassifier(eClassContainer);  // <-- Container
              {
                EGenericType eTypeParameterU1 = EcoreFactory.eINSTANCE.createEGenericType();
                eTypeParameterU1.setETypeParameter(eOperationFoo.getETypeParameters().get(1));  // <-- F1
                eTypeParameter.getETypeArguments().add(eTypeParameterU1);
              }
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            eParameter.setEGenericType(eGenericType);
          }

          class HolderCase7<E, F extends E, H extends Container<? extends E>>
          {
            @SuppressWarnings("unused")
            <E1, F1 extends E1, G1 extends Container<E1>> void foo(HolderCase7<E1, F1, Container<F1>> x)
            {
              this.foo(x);
            }
          }

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(Diagnostic.OK, diagnostic.getSeverity());
          /*
          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_ARGUMENTS_INVALID_SUBSTITUTION,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] 
             { 
               eOperationFoo.getEParameters().get(0).getEGenericType(),
               eOperationFoo.getEParameters().get(0).getEGenericType().getETypeArguments().get(2),
               eClassHolder.getETypeParameters().get(2)
             },
             diagnostic.getChildren().get(0));
             */
          break;
        }
        case 8:
        {
          EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
          eParameter.setName("x");
          eOperationFoo.getEParameters().add(eParameter);
          {
            EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
            eGenericType.setEClassifier(eClassHolder);
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(0)); 
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setETypeParameter(eOperationFoo.getETypeParameters().get(1)); 
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            {
              EGenericType eTypeParameter = EcoreFactory.eINSTANCE.createEGenericType();
              eTypeParameter.setEClassifier(eClassContainer);  // <-- Container
              {
                EGenericType eTypeParameterU1 = EcoreFactory.eINSTANCE.createEGenericType();
                eTypeParameterU1.setETypeParameter(eOperationFoo.getETypeParameters().get(1));  // <-- F1
                eTypeParameter.getETypeArguments().add(eTypeParameterU1);
              }
              eGenericType.getETypeArguments().add(eTypeParameter);
            }
            eParameter.setEGenericType(eGenericType);
          }

          @SuppressWarnings("unused")
          class HolderCase8<E, F extends E, H extends Container<? super E>>
          {
            <E1, F1 extends E1, G1 extends Container<E1>> void foo(/* --> HolderCase8<E1, F1, Container<F1>> x <--*/)
            {
              this.foo(/*x*/);
            }
          }

          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          assertEquals(1, diagnostic.getChildren().size());
          assertDiagnostic
            (Diagnostic.ERROR,
             EcoreValidator.CONSISTENT_ARGUMENTS_INVALID_SUBSTITUTION,
             EcoreValidator.DIAGNOSTIC_SOURCE,
             new Object [] 
             { 
               eOperationFoo.getEParameters().get(0).getEGenericType(),
               eOperationFoo.getEParameters().get(0).getEGenericType().getETypeArguments().get(2),
               eClassHolder.getETypeParameters().get(2)
             },
             diagnostic.getChildren().get(0));
          break;
        }
        default:
        {
          break LOOP;
        }
      }
    }
  }

  public void validateAllRegisteredModels()
  {
    // Validate all registered models.
    //
    if (!"false".equals(System.getProperty(EcoreValidationTest.class.getName() + ".validateAllRegisteredModels", "false")))
    {
      for (String nsURI : new HashSet<String>(EPackage.Registry.INSTANCE.keySet()))
      {
        EPackage ePackage = null;
        try
        {
          ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
        }
        catch (Throwable exception)
        {
          // Ignore
        }
        if (ePackage != null)
        {
          Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
          System.err.println("Validating " + nsURI);
          for (Diagnostic child : diagnostic.getChildren())
          {
            switch (child.getSeverity())
            {
              case Diagnostic.ERROR:
              {
                System.err.print("error  : ");
                break;
              }
              case Diagnostic.WARNING:
              {
                System.err.print("warning: ");
                break;
              }
              default:
              {
                System.err.print("other  : ");
                break;
              }
            }
            System.err.println(child.getMessage());
          }
        }
      }
    }
  }

  public void assertDiagnostic(int severity, int code, String source, Object [] data, Diagnostic diagnostic)
  {
    if (SYSOUT) System.out.println(diagnostic.getMessage());
    assertEquals(severity, diagnostic.getSeverity());
    assertEquals(code, diagnostic.getCode());
    assertEquals(source, diagnostic.getSource());
    assertEquals(Arrays.asList(data), diagnostic.getData());
  }
} 
