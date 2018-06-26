/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.ecore.genmodel.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * An annotation validator for {@link EcoreUtil#GEN_MODEL_ANNOTATION_URI GenModel} annotations.
 *
 * @since 2.14
 * @see GenModelPackage#eNS_URI
 */
public final class GenModelAnnotatonValidator extends BasicEAnnotationValidator
{
  /**
   * @see #validateReferenceDetailLiteralValue(EAnnotation, EModelElement, Entry, EReference, List, DiagnosticChain, Map)
   */
  public static final int INVALID_VALUE_LITERAL = BasicEAnnotationValidator.INVALID_VALUE_LITERAL;

  public static final GenModelAnnotatonValidator INSTANCE = new GenModelAnnotatonValidator();

  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.codegen.ecore.genmodel.annotation";

  static
  {
    // Check that the instance is registered properly...
    if (!EAnnotationValidator.Registry.INSTANCE.containsKey(GenModelPackage.eNS_URI))
    {
      // In a stand alone application, we'll need to explicitly register it.
      EAnnotationValidator.Registry.INSTANCE.put(GenModelPackage.eNS_URI, INSTANCE);

      // For the GenModel to be registered as well.
      GenModelPackage.eINSTANCE.eClass();
    }
  }

  public GenModelAnnotatonValidator()
  {
    super(GenModelPackage.eNS_URI, "GenModel", DIAGNOSTIC_SOURCE);
  }

  @Override
  protected boolean isValidLocation(EAnnotation eAnnotation, EModelElement eModelElement)
  {
    return !getProperties(eModelElement).isEmpty();
  }

  @Override
  protected boolean validateReferenceDetailLiteralValue(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Entry<String, String> entry,
    EReference reference,
    List<Object> values,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    String value = entry.getValue();
    boolean result = false;
    if (value != null)
    {
      EClass eClass = (EClass)eModelElement;
      for (EAttribute eAttribute : eClass.getEAllAttributes())
      {
        if (value.equals(eAttribute.getName()))
        {
          values.add(eAttribute);
          result = true;
          break;
        }
      }
    }

    if (!result && diagnostics != null)
    {
      diagnostics.add(
        createDiagnostic(
          Diagnostic.WARNING,
          INVALID_VALUE_LITERAL,
          getString(getResourceLocator(), "_UI_InvalidAnnotationLabelEntryValue_diagnostic", value),
          value,
          reference.getEReferenceType(),
          reference));
    }

    return result;
  }

  @Override
  protected List<EClass> getPropertyClasses(EModelElement eModelElement)
  {
    final List<EClass> result = new ArrayList<EClass>();
    new PropertySwitch()
      {
        @Override
        protected void addFeatures(EClass eClass)
        {
          result.add(eClass);
        }
      }.doSwitch(eModelElement);
    return Collections.unmodifiableList(result);
  }

  @Override
  protected boolean isIncludedProperty(EModelElement eModelElement, EClass eClass, EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature instanceof EAttribute || eStructuralFeature == GenModelPackage.Literals.GEN_CLASS__LABEL_FEATURE;
  }

  @Override
  protected EObject initialize(EObject eObject, EAnnotation eAnnotation)
  {
    final GenBase genBase = (GenBase)eObject;
    final EModelElement eModelElement = eAnnotation.getEModelElement();
    EClass eClass = genBase.eClass();
    EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature("ecore" + eClass.getName().substring(3));
    if (eStructuralFeature != null)
    {
      genBase.eSet(eStructuralFeature, eModelElement);
      if (eModelElement instanceof EPackage)
      {
        EPackage ePackage = (EPackage)eModelElement;
        String name = ePackage.getName();
        if (name != null)
        {
          ((GenPackage)genBase).setPrefix(CodeGenUtil.capName(name));
        }
      }
    }
    else
    {
      GenPackage genPackage = (GenPackage)createInstance(GenModelPackage.Literals.GEN_PACKAGE, eAnnotation);
      GenModel genModel = (GenModel)genBase;
      genModel.getGenPackages().add(genPackage);
      Resource resource = eModelElement.eResource();
      if (resource != null)
      {
        URI uri = resource.getURI();
        if (uri != null)
        {
          Resource genModelResource = new ResourceImpl(uri.trimFileExtension().appendFileExtension("genmodel"));
          genModelResource.getContents().add(genModel);
        }
      }
      genModel.initialize(false);
    }

    new GenModelImpl()
      {
        {
          handleAnnotations((GenBase)genBase, eModelElement);
        }
      };

    return genBase;
  }

  @Override
  protected String convertPropertyReferenceValueToLiteralItem(EObject eObject, EReference eReference, Object value)
  {
    if (value instanceof GenFeature)
    {
      GenFeature genFeature = (GenFeature)value;
      return genFeature.getEcoreFeature().getName();
    }
    else
    {
      return super.convertPropertyReferenceValueToLiteralItem(eObject, eReference, value);
    }
  }

  @Override
  protected ResourceLocator getResourceLocator()
  {
    return CodeGenEcorePlugin.INSTANCE;
  }

  private static abstract class PropertySwitch extends EcoreSwitch<Void>
  {
    protected abstract void addFeatures(EClass eClass);

    @Override
    public Void caseEStructuralFeature(EStructuralFeature eStructuralFeature)
    {
      addFeatures(GenModelPackage.Literals.GEN_FEATURE);
      return null;
    }

    @Override
    public Void caseEClass(EClass eClass)
    {
      addFeatures(GenModelPackage.Literals.GEN_CLASS);
      return null;
    }

    @Override
    public Void caseEDataType(EDataType eDataType)
    {
      if (!(eDataType instanceof EEnum))
      {
        addFeatures(GenModelPackage.Literals.GEN_DATA_TYPE);
      }
      return null;
    }

    @Override
    public Void caseEParameter(EParameter eParameter)
    {
      addFeatures(GenModelPackage.Literals.GEN_PARAMETER);
      return null;
    }

    @Override
    public Void caseEOperation(EOperation eOperation)
    {
      addFeatures(GenModelPackage.Literals.GEN_OPERATION);
      return null;
    }

    @Override
    public Void caseEPackage(EPackage ePackage)
    {
      addFeatures(GenModelPackage.Literals.GEN_PACKAGE);
      addFeatures(GenModelPackage.Literals.GEN_MODEL);
      return null;
    }

    @Override
    public Void caseEEnumLiteral(EEnumLiteral eEnumLiteral)
    {
      addFeatures(GenModelPackage.Literals.GEN_ENUM_LITERAL);
      return null;
    }

    @Override
    public Void caseEEnum(EEnum eEnum)
    {
      addFeatures(GenModelPackage.Literals.GEN_ENUM);
      return null;
    }

    @Override
    public Void caseETypeParameter(ETypeParameter eTypeParameter)
    {
      addFeatures(GenModelPackage.Literals.GEN_TYPE_PARAMETER);
      return null;
    }
  }
}