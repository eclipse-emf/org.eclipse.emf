/**
 * Copyright (c) 2004-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.util;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.TreeIterator;

import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * A validity checker for basic EObject constraints.
 */
public class Diagnostician implements EValidator.SubstitutionLabelProvider, EValidator
{
  /**
   * A Boolean key to be used in <code>context</code> maps to indicate whether {@link #validate(EObject, DiagnosticChain, Map)}
   * should directly call {@link #validate(EClass, EObject, DiagnosticChain, Map)} once and expect that method to recursively call {@link #doValidateContents(EObject, DiagnosticChain, Map)}
   * or whether it should iterate over all contents and call {@link #validate(EClass, EObject, DiagnosticChain, Map)} for each
   * with the expectation that that method <b>not</b> call {@link #doValidateContents(EObject, DiagnosticChain, Map)}.
   * In either case, {@link #doValidate(EValidator, EClass, EObject, DiagnosticChain, Map)} is ultimately called for each object in the tree.
   * The default behavior is controlled by {@link #isValidateContentsRecursively()} which is {@code false} in the default implementation.
   * So as of EMF 2.14, the default behavior is to use iteration instead of recursion to validate all objects in the tree.
   *
   * @see #validate(EObject, DiagnosticChain, Map)
   * @see #validate(EClass, EObject, DiagnosticChain, Map)
   * @see #doValidateContents(EObject, DiagnosticChain, Map)
   * @see #isValidateContentsRecursively()
   *
   * @since 2.14
   */
  public static final String VALIDATE_RECURSIVELY = "VALIDATE_RECURSIVELY";

  public static final Diagnostician INSTANCE = new Diagnostician();

  private boolean validateContentsRecursively;

  protected EValidator.Registry eValidatorRegistry;

  public Diagnostician(EValidator.Registry eValidatorRegistry)
  {
    this.eValidatorRegistry = eValidatorRegistry;
    validateContentsRecursively = OverrideChecker.hasDoValidateContentsOverride(getClass());
  }

  public Diagnostician()
  {
    this(EValidator.Registry.INSTANCE);
  }

  public String getObjectLabel(EObject eObject)
  {
    return EcoreUtil.getIdentification(eObject);
  }

  public String getFeatureLabel(EStructuralFeature eStructuralFeature)
  {
    return eStructuralFeature.getName();
  }

  public String getValueLabel(EDataType eDataType, Object value)
  {
    return EcoreUtil.convertToString(eDataType, value);
  }

  /**
   * @since 2.4
   */
  public Map<Object, Object> createDefaultContext()
  {
    Map<Object, Object> context = new HashMap<Object, Object>();
    context.put(EValidator.SubstitutionLabelProvider.class, this);
    context.put(EValidator.class, this);
    return context;
  }

  /**
   * @since 2.4
   */
  public BasicDiagnostic createDefaultDiagnostic(EObject eObject)
  {
    return
      new BasicDiagnostic
        (EObjectValidator.DIAGNOSTIC_SOURCE,
         0,
         EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic", new Object[] { getObjectLabel(eObject) }),
         new Object [] { eObject });    
  }

  /**
   * @since 2.4
   */
  public BasicDiagnostic createDefaultDiagnostic(EDataType eDataType, Object value)
  {
    return
      new BasicDiagnostic
        (EObjectValidator.DIAGNOSTIC_SOURCE,
         0,
         EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic", new Object [] { getValueLabel(eDataType, value) }),
         new Object [] { value, eDataType });    
  }

  public Diagnostic validate(EObject eObject)
  {
    BasicDiagnostic diagnostics = createDefaultDiagnostic(eObject);
    validate(eObject, diagnostics, createDefaultContext());
    return diagnostics;
  }

  /**
   * @since 2.4
   */
  public Diagnostic validate(EObject eObject, Map<?, ?> contextEntries)
  {
    BasicDiagnostic diagnostics = createDefaultDiagnostic(eObject);
    Map<Object, Object> context = createDefaultContext();
    context.putAll(contextEntries);
    validate(eObject, diagnostics, context);
    return diagnostics;    
  }

  /**
   * Validates the object in the given context, optionally producing diagnostics.
   * @param eObject the object to validate.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @return whether the object is valid.
   */
  public boolean validate(EObject eObject, DiagnosticChain diagnostics)
  {
    return validate(eObject, diagnostics, createDefaultContext());
  }

  /**
   * Returns whether
   * {@link #validate(EObject, DiagnosticChain, Map)} should call {@link #validate(EClass, EObject, DiagnosticChain, Map)} once
   * with {@link #VALIDATE_RECURSIVELY} mapped to {@code Boolean.TRUE} in the {@code context},
   * or should iterator over {@link EcoreUtil#getAllContents(java.util.Collection) all contents},
   * including the given object,
   * and call {@link #validate(EClass, EObject, DiagnosticChain, Map)} for each
   * with {@link #VALIDATE_RECURSIVELY} mapped to {@code Boolean.FALSE} in the {@code context}.
   *
   * @since 2.14
   */
  protected boolean isValidateContentsRecursively()
  {
    return validateContentsRecursively;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation will ultimately recursive validate all objects in the containment tree.
   * But, depending on {@link #isValidateContentsRecursively()} and the value of {@link #VALIDATE_RECURSIVELY} in the {@code context},
   * it will do so either iteratively or recursively.
   * </p>
   */
  public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean needsRecursion = isValidateContentsRecursively();
    Object validateRecursively = context.put(VALIDATE_RECURSIVELY, needsRecursion);
    try
    {
      if (needsRecursion || Boolean.TRUE.equals(validateRecursively))
      {
        context.put(VALIDATE_RECURSIVELY, Boolean.TRUE);
        return validate(eObject.eClass(), eObject, diagnostics, context);
      }
      else
      {
        context.put(VALIDATE_RECURSIVELY, Boolean.FALSE);
        boolean result = true;
        for (TreeIterator<? extends EObject> i = EcoreUtil.getAllContents(Collections.singleton(eObject)); i.hasNext();)
        {
          EObject child = i.next();
          boolean circular = context.get(EObjectValidator.ROOT_OBJECT) == child;
          result &= validate(child.eClass(), child, diagnostics, context);
          if (circular)
          {
            i.prune();
          }
          else if (!result && diagnostics == null)
          {
            break;
          }
        }
        return result;
      }
    }
    finally
    {
      context.put(VALIDATE_RECURSIVELY, validateRecursively);
    }
  }

  /**
   * Validates the object.
   * If the {@code context} does not contain {@link #VALIDATE_RECURSIVELY} mapped to {@code Boolean.FALSE},
   * this method will call {@link #doValidateContents(EObject, DiagnosticChain, Map)},
   * i.e., it will recursively validate all contents of the tree unless the context explicitly indicates not to do that.
   */
  public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    try
    {
      Object eValidator;
      EClass eType = eClass;
      while ((eValidator = eValidatorRegistry.get(eType.eContainer())) == null)
      {
        List<EClass> eSuperTypes = eType.getESuperTypes();
        if (eSuperTypes.isEmpty())
        {
          eValidator = eValidatorRegistry.get(null);
          break;
        }
        else
        {
          eType = eSuperTypes.get(0);
        }
      }
      boolean circular = context.get(EObjectValidator.ROOT_OBJECT) == eObject;
      boolean result = doValidate((EValidator)eValidator, eClass, eObject, diagnostics, context);
      if (!Boolean.FALSE.equals(context.get(VALIDATE_RECURSIVELY)) && (result || diagnostics != null) && !circular)
      {
        result &= doValidateContents(eObject, diagnostics, context);
      }
      return result;
    }
    catch (RuntimeException e)
    {
      if (!handleThrowable(eClass, eObject, diagnostics, context, e))
      {
        throw e;
      }
    }
    catch (Error e)
    {
      if (!handleThrowable(eClass, eObject, diagnostics, context, e))
      {
        throw e;
      }
    }
    return false;
  }

  /**
   * Called by {@link #validate(EClass, EObject, DiagnosticChain, Map)} when an exception is thrown during validation.
   * If diagnostics are being recorded and the exception is a {@link RuntimeException} or an {@link AssertionError},
   * a diagnostic is created and added to the diagnostics to record the exception.
   * 
   * Returns true if the throwable has been adequately handled, false if the caller should rethrow it.
   *
   * @since 2.22
   */
  protected boolean handleThrowable(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, Throwable throwable)
  {
    if (diagnostics != null && (throwable instanceof RuntimeException || throwable instanceof AssertionError))
    {
      Object[] messageSubstitutions = new Object []{ EObjectValidator.getObjectLabel(eObject, context) };
      String message = EcorePlugin.INSTANCE.getString("_UI_ValidationFailed_diagnostic", messageSubstitutions);
      Object[] data = new Object []{ eObject, throwable };
      diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EObjectValidator.DIAGNOSTIC_SOURCE, -1, message, data));
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * @since 2.9
   */
  protected boolean doValidate(EValidator eValidator, EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return eValidator.validate(eClass, eObject, diagnostics, context);
  }

  protected boolean doValidateContents(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    List<EObject> eContents = eObject.eContents();
    if (!eContents.isEmpty())
    {
      Iterator<EObject> i = eContents.iterator(); 
      EObject child = i.next();
      boolean result = validate(child, diagnostics, context);
      while (i.hasNext() && (result || diagnostics != null))
      {
        child = i.next();
        result &= validate(child, diagnostics, context);
      }
      return result;
    }
    else
    {
      return true;
    }
  }

  public Diagnostic validate(EDataType eDataType, Object value)
  {
    BasicDiagnostic diagnostics = createDefaultDiagnostic(eDataType, value);
    validate(eDataType, value, diagnostics, createDefaultContext());
    return diagnostics;
  }

  public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    Object eValidator = eValidatorRegistry.get(eDataType.eContainer());
    if (eValidator == null)
    {
      eValidator = eValidatorRegistry.get(null);
    }

    return doValidate((EValidator)eValidator, eDataType, value, diagnostics, context);
  }

  /**
   * @since 2.9
   */
  protected boolean doValidate(EValidator eValidator, EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return eValidator.validate(eDataType, value, diagnostics, context);
  }

  private static class OverrideChecker
  {
    private static final Map<Class<?>, Boolean> CLASSES_WITH_OVERRIDES = new ConcurrentHashMap<Class<?>, Boolean>();

    public static boolean hasDoValidateContentsOverride(Class<?> diagnosticianClass)
    {
      Boolean result = CLASSES_WITH_OVERRIDES.get(diagnosticianClass);
      if (result == null)
      {
        try
        {
          result = Boolean.FALSE;
          for (Class<?> theClass = diagnosticianClass; theClass != Diagnostician.class; theClass = theClass.getSuperclass())
          {
            try
            {
              theClass.getDeclaredMethod("doValidateContents", EObject.class, DiagnosticChain.class, Map.class);
              result = Boolean.TRUE;
              break;
            }
            catch (NoSuchMethodException noSuchMethodException)
            {
            }
          }
        }
        catch (Exception exception)
        {
          result = Boolean.TRUE;
        }

        CLASSES_WITH_OVERRIDES.put(diagnosticianClass, result);
      }
      return result;
    }
  }
}
