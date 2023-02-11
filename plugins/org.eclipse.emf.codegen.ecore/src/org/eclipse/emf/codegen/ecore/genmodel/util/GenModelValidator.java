/**
 * Copyright (c) 2007-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.util;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.Literals;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator.ValidationContext;
import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * @since 2.4
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
 * @generated
 */
public class GenModelValidator extends EObjectValidator
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final GenModelValidator INSTANCE = new GenModelValidator();

  /**
   * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.common.util.Diagnostic#getSource()
   * @see org.eclipse.emf.common.util.Diagnostic#getCode()
   * @generated
   */
  public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.codegen.ecore.genmodel";

  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;
  
  /**
   * @see #validateGenEnum_NoEcoreDataType(GenEnum, DiagnosticChain, Map)
   */
  public static final int NO_ECORE_DATA_TYPE = GENERATED_DIAGNOSTIC_CODE_COUNT + 1;
  
  /**
   * @see #validatePath_WellFormedPath(String, DiagnosticChain, Map)
   */
  public static final int WELL_FORMED_PATH = GENERATED_DIAGNOSTIC_CODE_COUNT + 2;

  /**
   * @see #validatePath_WellFormedPath(String, DiagnosticChain, Map)
   */
  public static final int VALID_PROPERTY_EDIT_FACTORY = GENERATED_DIAGNOSTIC_CODE_COUNT + 3;
  
  /**
   * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected static final int DIAGNOSTIC_CODE_COUNT = VALID_PROPERTY_EDIT_FACTORY;

  /**
   * This supports delegation to the validator of the IPropertyEditorFactory.
   * It is done reflectively avoid avoid a hard dependency on EMF.Edit.
   *
   * @since 2.14
   */
  protected abstract static class PropertyEditorFactoryValidator
  {
    public abstract boolean validatePropertyEditorFactory(EModelElement eModelElement, String propertyEditorFactory, DiagnosticChain diagnostics, Map<Object, Object> context);
  }

  /**
   * The one instance of the {@link PropertyEditorFactoryValidator}.
   *
   * @since 2.14
   */
  protected static final PropertyEditorFactoryValidator PROPERTY_EDITOR_FACTORY_VALIDATOR;

  static
  {
    PropertyEditorFactoryValidator propertyEditorFactoryValidator = null;
    try
    {
      final Class<?> propertyEditorFactoryRegistryClass = CommonPlugin.loadClass("org.eclipse.emf.edit", "org.eclipse.emf.edit.provider.IPropertyEditorFactory$Registry");
      final Object registryInstance = propertyEditorFactoryRegistryClass.getField("INSTANCE").get(null);

      final Method getPropertyEditorFactoryMethod = propertyEditorFactoryRegistryClass.getMethod("getPropertyEditorFactory", Object.class);
      final Method getTargetPlatformFactoriesMethod = propertyEditorFactoryRegistryClass.getMethod("getTargetPlatformFactories");

      Class<?> propertyEditorFactoryClass = CommonPlugin.loadClass("org.eclipse.emf.edit", "org.eclipse.emf.edit.provider.PropertyEditorFactory");
      final Method validateMethod = propertyEditorFactoryClass.getMethod("validate", EModelElement.class, URI.class, DiagnosticChain.class, Map.class);

      propertyEditorFactoryValidator = new PropertyEditorFactoryValidator()
        {
          @Override
          public boolean validatePropertyEditorFactory(EModelElement eModelElement, String propertyEditorFactory, DiagnosticChain diagnostics, Map<Object, Object> context)
          {
            if (propertyEditorFactory != null && propertyEditorFactory.trim().length() != 0)
            {
              try
              {
                URI propertyEditorFactoryURI = URI.createURI(propertyEditorFactory);
                Object propertyEditorFactoryInstance = getPropertyEditorFactoryMethod.invoke(registryInstance, propertyEditorFactoryURI);
                if (propertyEditorFactoryInstance != null)
                {
                  return (Boolean)validateMethod.invoke(propertyEditorFactoryInstance, eModelElement, propertyEditorFactoryURI, diagnostics, context);
                }
                else
                {
                  // We cache this set of IPropertyFactoryEditors registered in the target platform because it's relatively expensive to compute.
                  //
                  @SuppressWarnings("unchecked")
                  Set<URI> targetPlatformPropertyEditorFactories = context == null ? null : (Set<URI>)context.get("TARGET_PLATFORM_PROPERTY_EDITOR_FACTORIES");
                  if (targetPlatformPropertyEditorFactories == null)
                  {
                    @SuppressWarnings("unchecked")
                    Set<URI> computedTargetPlatformPropertyEditorFactories = (Set<URI>)getTargetPlatformFactoriesMethod.invoke(registryInstance);
                    targetPlatformPropertyEditorFactories = computedTargetPlatformPropertyEditorFactories;
                    if (context != null)
                    {
                      context.put("TARGET_PLATFORM_PROPERTY_EDITOR_FACTORIES", targetPlatformPropertyEditorFactories);
                    }
                  }

                  // This uses the same algorithm for looking up the registered property editor factory.
                  //
                  URI baseURI = propertyEditorFactoryURI.trimQuery().trimFragment();
                  for (int i = 0, count = baseURI.segmentCount(); i <= count; ++i)
                  {
                    URI uri = baseURI.trimSegments(i);
                    if (targetPlatformPropertyEditorFactories.contains(uri))
                    {
                      return true;
                    }
                  }

                  if (diagnostics != null)
                  {
                    diagnostics.add(
                      new BasicDiagnostic(
                        Diagnostic.WARNING,
                        DIAGNOSTIC_SOURCE,
                        VALID_PROPERTY_EDIT_FACTORY,
                        CodeGenEcorePlugin.INSTANCE.getString("_UI_ValidPropertyFactoryEditor_diagnostic", new Object[] { propertyEditorFactoryURI }),
                        new Object []{ propertyEditorFactory }));
                  }

                  return false;
                }
              }
              catch (Exception exception)
              {
                // Ignore.
              }
            }
            return true;
          }
        };
    }
    catch (Exception exception)
    {
      // Ignore.
    }
    PROPERTY_EDITOR_FACTORY_VALIDATOR = propertyEditorFactoryValidator;
  }

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenModelValidator()
  {
    super();
  }

  /**
   * Returns the package of this validator switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EPackage getEPackage()
  {
    return GenModelPackage.eINSTANCE;
  }

  /**
   * Calls <code>validateXXX</code> for the corresponding classifier of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    switch (classifierID)
    {
      case GenModelPackage.GEN_MODEL:
        return validateGenModel((GenModel)value, diagnostics, context);
      case GenModelPackage.GEN_PACKAGE:
        return validateGenPackage((GenPackage)value, diagnostics, context);
      case GenModelPackage.GEN_CLASS:
        return validateGenClass((GenClass)value, diagnostics, context);
      case GenModelPackage.GEN_FEATURE:
        return validateGenFeature((GenFeature)value, diagnostics, context);
      case GenModelPackage.GEN_BASE:
        return validateGenBase((GenBase)value, diagnostics, context);
      case GenModelPackage.GEN_ENUM:
        return validateGenEnum((GenEnum)value, diagnostics, context);
      case GenModelPackage.GEN_ENUM_LITERAL:
        return validateGenEnumLiteral((GenEnumLiteral)value, diagnostics, context);
      case GenModelPackage.GEN_CLASSIFIER:
        return validateGenClassifier((GenClassifier)value, diagnostics, context);
      case GenModelPackage.GEN_DATA_TYPE:
        return validateGenDataType((GenDataType)value, diagnostics, context);
      case GenModelPackage.GEN_OPERATION:
        return validateGenOperation((GenOperation)value, diagnostics, context);
      case GenModelPackage.GEN_PARAMETER:
        return validateGenParameter((GenParameter)value, diagnostics, context);
      case GenModelPackage.GEN_TYPED_ELEMENT:
        return validateGenTypedElement((GenTypedElement)value, diagnostics, context);
      case GenModelPackage.GEN_ANNOTATION:
        return validateGenAnnotation((GenAnnotation)value, diagnostics, context);
      case GenModelPackage.GEN_TYPE_PARAMETER:
        return validateGenTypeParameter((GenTypeParameter)value, diagnostics, context);
      case GenModelPackage.GEN_PROVIDER_KIND:
        return validateGenProviderKind((GenProviderKind)value, diagnostics, context);
      case GenModelPackage.GEN_PROPERTY_KIND:
        return validateGenPropertyKind((GenPropertyKind)value, diagnostics, context);
      case GenModelPackage.GEN_RESOURCE_KIND:
        return validateGenResourceKind((GenResourceKind)value, diagnostics, context);
      case GenModelPackage.GEN_DELEGATION_KIND:
        return validateGenDelegationKind((GenDelegationKind)value, diagnostics, context);
      case GenModelPackage.GEN_JDK_LEVEL:
        return validateGenJDKLevel((GenJDKLevel)value, diagnostics, context);
      case GenModelPackage.GEN_RUNTIME_VERSION:
        return validateGenRuntimeVersion((GenRuntimeVersion)value, diagnostics, context);
      case GenModelPackage.GEN_RUNTIME_PLATFORM:
        return validateGenRuntimePlatform((GenRuntimePlatform)value, diagnostics, context);
      case GenModelPackage.GEN_DECORATION:
        return validateGenDecoration((GenDecoration)value, diagnostics, context);
      case GenModelPackage.GEN_ECLIPSE_PLATFORM_VERSION:
        return validateGenEclipsePlatformVersion((GenEclipsePlatformVersion)value, diagnostics, context);
      case GenModelPackage.GEN_CODE_STYLE:
        return validateGenCodeStyle((GenCodeStyle)value, diagnostics, context);
      case GenModelPackage.GEN_OS_GI_STYLE:
        return validateGenOSGiStyle((GenOSGiStyle)value, diagnostics, context);
      case GenModelPackage.PATH:
        return validatePath((String)value, diagnostics, context);
      case GenModelPackage.PROPERTY_EDITOR_FACTORY:
        return validatePropertyEditorFactory((String)value, diagnostics, context);
      default:
        return true;
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenModel(GenModel genModel, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genModel, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenPackage(GenPackage genPackage, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genPackage, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenClass(GenClass genClass, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genClass, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenFeature(GenFeature genFeature, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genFeature, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenBase(GenBase genBase, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genBase, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenEnum(GenEnum genEnum, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (!validate_NoCircularContainment(genEnum, diagnostics, context)) return false;
    boolean result = validate_EveryMultiplicityConforms(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryDataValueConforms(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryProxyResolves(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_UniqueID(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryKeyUnique(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(genEnum, diagnostics, context);
    if (result || diagnostics != null) result &= validateGenEnum_NoEcoreDataType(genEnum, diagnostics, context);
    return result;
  }

  /**
   * Validates the NoEcoreDataType constraint of '<em>Gen Enum</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean validateGenEnum_NoEcoreDataType(GenEnum genEnum, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (genEnum.eIsSet(GenModelPackage.Literals.GEN_DATA_TYPE__ECORE_DATA_TYPE))
    {
      if (diagnostics != null)
      {
        diagnostics.add
          (createDiagnostic
            (Diagnostic.ERROR,
             DIAGNOSTIC_SOURCE,
             NO_ECORE_DATA_TYPE,
             "_UI_GenEnumNoEcoreDataType_diagnostic",
             new String[] { getObjectLabel(genEnum.getEcoreDataType(), context) },
             new Object[] { genEnum, genEnum.getEcoreDataType() },
             context));
      }
      return false;
    }
    return true;
  }
  
  @Override
  protected boolean validate_MultiplicityConforms(EObject eObject, EStructuralFeature eStructuralFeature, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return
      eStructuralFeature == GenModelPackage.Literals.GEN_DATA_TYPE__ECORE_DATA_TYPE && eObject instanceof GenEnum ||
      super.validate_MultiplicityConforms(eObject, eStructuralFeature, diagnostics, context);
  }

  @Override
  protected boolean validate_DataValueConforms(EObject eObject, EAttribute eAttribute, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = super.validate_DataValueConforms(eObject, eAttribute, diagnostics, context);
    if (result && eAttribute.getEType() == GenModelPackage.Literals.PROPERTY_EDITOR_FACTORY)
    {
      String propertyEditorFactory = (String)eObject.eGet(eAttribute);
      result = validatePropertyEditorFactory(((GenBase)eObject).getEcoreModelElement(), propertyEditorFactory, diagnostics, context);
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenEnumLiteral(GenEnumLiteral genEnumLiteral, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genEnumLiteral, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenClassifier(GenClassifier genClassifier, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genClassifier, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenDataType(GenDataType genDataType, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genDataType, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenOperation(GenOperation genOperation, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genOperation, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenParameter(GenParameter genParameter, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genParameter, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenTypedElement(GenTypedElement genTypedElement, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genTypedElement, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenAnnotation(GenAnnotation genAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genAnnotation, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenTypeParameter(GenTypeParameter genTypeParameter, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return validate_EveryDefaultConstraint(genTypeParameter, diagnostics, context);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenProviderKind(GenProviderKind genProviderKind, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenPropertyKind(GenPropertyKind genPropertyKind, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenResourceKind(GenResourceKind genResourceKind, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenDelegationKind(GenDelegationKind genDelegationKind, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenJDKLevel(GenJDKLevel genJDKLevel, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenRuntimeVersion(GenRuntimeVersion genRuntimeVersion, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenRuntimePlatform(GenRuntimePlatform genRuntimePlatform, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean validateGenDecoration(GenDecoration genDecoration, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean validateGenEclipsePlatformVersion(GenEclipsePlatformVersion genEclipsePlatformVersion, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.19
   * @generated
   */
  public boolean validateGenCodeStyle(GenCodeStyle genCodeStyle, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.33
   * @generated
   */
  public boolean validateGenOSGiStyle(GenOSGiStyle genOSGiStyle, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean validatePath(String path, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = validatePath_WellFormedPath(path, diagnostics, context);
    return result;
  }

  /**
   * Validates the WellFormedPath constraint of '<em>Path</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public boolean validatePath_WellFormedPath(String path, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if ("".equals(path))
    {
      return true;
    }

    IStatus status = Status.OK_STATUS;
    try
    {
      IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
      if (workspaceRoot != null)
      {
        IPath workspacePath = new Path(path).makeAbsolute();
        status = workspaceRoot.getWorkspace().validatePath(workspacePath.toString(), IResource.FOLDER);
      }
      if (status.isOK())
      {
        for (char c : path.toCharArray())
        {
          if (c < ' ' || c == '\\')
          {
            status = new Status(
              IStatus.ERROR,
              CodeGenEcorePlugin.ID,
              CodeGenEcorePlugin.INSTANCE.getString("_UI_BadCharacterInPath_message", new Object []{ Literals.toCharLiteral(c, null) }));
            break;
          }
        }
      }
    }
    catch (RuntimeException exception)
    {
      status = new Status(IStatus.ERROR, CodeGenEcorePlugin.ID, exception.getMessage());
    }

    if (diagnostics != null && !status.isOK())
    {
      diagnostics.add(
        createDiagnostic(
          Diagnostic.ERROR,
          DIAGNOSTIC_SOURCE,
          WELL_FORMED_PATH,
          "_UI_WellFormedPath_diagnostic",
          new Object []{ getValueLabel(GenModelPackage.Literals.PATH, path, context), status.getMessage() },
          new Object []{ path },
          context));
    }
    
    return status.getSeverity() != IStatus.ERROR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated NOT
   */
  public boolean validatePropertyEditorFactory(String propertyEditorFactory, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    BasicEAnnotationValidator.ValidationContext validationContext = (ValidationContext)context.get(BasicEAnnotationValidator.ValidationContext.CONTEXT_KEY);
    if (validationContext != null)
    {
      EModelElement eModelElement = validationContext.getEModelElement();
      return validatePropertyEditorFactory(eModelElement, propertyEditorFactory, diagnostics, context);
    }
    return true;
  }

  /**
   * @since 2.14
   */
  protected boolean validatePropertyEditorFactory(EModelElement eModelElement, String propertyEditorFactory, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (propertyEditorFactory != null && PROPERTY_EDITOR_FACTORY_VALIDATOR != null)
    {
        return PROPERTY_EDITOR_FACTORY_VALIDATOR.validatePropertyEditorFactory(eModelElement, propertyEditorFactory, diagnostics, context);
    }
    else
    {
      return true;
    }
  }
  
  

  /**
   * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return CodeGenEcorePlugin.INSTANCE;
  }
} //GenModelValidator
