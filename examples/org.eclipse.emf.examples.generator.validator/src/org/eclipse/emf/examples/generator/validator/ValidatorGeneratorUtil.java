/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.examples.generator.validator;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypedElement;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.ecore.ETypedElement;

public class ValidatorGeneratorUtil
{
  private ValidatorGeneratorUtil()
  {
    super();
  }

  public final static String TEMPLATE_LOCATION = ValidatorGeneratorPlugin.INSTANCE.getBaseURL().toString() + "templates";

  public final static String CLASSPATH_VARIABLE_NAME = "EMF_EXAMPLES_GENERATOR_VALIDATOR";

  public static String getValidationPackageName(GenPackage genPackage)
  {
    String basePackage = genPackage.getInterfacePackageName();
    return basePackage.length() > 0 ? basePackage + ".validation" : "validation";
  }

  public static String getValidatorInterfaceName(GenClass genClass)
  {
    return genClass.getName() + "Validator";
  }

  public static String getQualifiedValidatorInterfaceName(GenClass genClass)
  {
    return getValidationPackageName(genClass.getGenPackage()) + "." + getValidatorInterfaceName(genClass);
  }

  public static String getImportedValidatorInterfaceName(GenClass genClass)
  {
    return genClass.getGenModel().getImportedName(getQualifiedValidatorInterfaceName(genClass));
  }

  protected static String getImportedName(String qualifiedName, ImportManager importManager)
  {
    int index = qualifiedName.indexOf("$");
    importManager.addImport(index == -1 ? qualifiedName : qualifiedName.substring(0, index));
    return importManager.getImportedName(qualifiedName);
  }

  public static String getSuperTypesExpression(GenClass genClass)
  {
    List<GenClass> extendsList = genClass.getBaseGenClasses();
    if (!extendsList.isEmpty())
    {
      StringBuffer result = new StringBuffer();
      result.append(" -> ");
      for (Iterator<GenClass> i = extendsList.iterator(); i.hasNext(); )
      {
        result.append(i.next().getName());
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
      return result.toString();
    }
    return "";
  }

  public static String getTypeExpression(GenTypedElement genTypedElement)
  {
    StringBuffer result = new StringBuffer();
    GenClassifier genClassifier = genTypedElement.getTypeGenClassifier();
    result.append(genClassifier == null ? "void" : genClassifier.getName());

    ETypedElement eTypedElement = (ETypedElement)genTypedElement.getEcoreModelElement();
    int lowerBound = eTypedElement.getLowerBound();
    int upperBound = eTypedElement.getUpperBound();

    if (lowerBound != 0 || upperBound != 1)
    {
      result.append("<<");
      result.append(lowerBound);
      result.append("..");

      if (upperBound == ETypedElement.UNSPECIFIED_MULTIPLICITY)
      {
        result.append('?');
      }
      else if (upperBound == ETypedElement.UNBOUNDED_MULTIPLICITY)
      {
        result.append('*');
      }
      else
      {
        result.append(upperBound);
      }
      result.append(">>");
    }
    return result.toString();
  }

  public static String getParameterExpression(GenOperation genOperation)
  {
    StringBuffer result = new StringBuffer();
    result.append('(');

    for (Iterator<GenParameter> i = genOperation.getGenParameters().iterator(); i.hasNext(); )
    {
      GenParameter genParameter = i.next();
      result.append(getTypeExpression(genParameter));
      if (i.hasNext())
      {
        result.append(", ");
      }
    }
    result.append(')');
    return result.toString();
  }
  
}
