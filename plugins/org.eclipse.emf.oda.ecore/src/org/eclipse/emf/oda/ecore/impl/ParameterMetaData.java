/**
 * <copyright>
 *
 * Copyright (c) 2010 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ParameterMetaData.java,v 1.1 2010/12/05 01:42:04 khussey Exp $
 */
package org.eclipse.emf.oda.ecore.impl;

import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification.ParameterIdentifier;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EcorePackage;


/**
 * Implementation of IParameterMetaData for EMF ODA runtime driver.
 */
public class ParameterMetaData implements IParameterMetaData
{
  public static final String TARGET_PARAMETER_NAME = "@target"; //$NON-NLS-1$

  public static final String DEFAULT_PARAMETER_VALUE = "<unset>"; //$NON-NLS-1$

  protected final Query query;

  protected ParameterMetaData(Query query)
  {
    super();

    this.query = query;
  }

  public int getParameterCount() throws OdaException
  {
    return query.getSpecification().getParameterValues().size();
  }

  public int getParameterMode(int param) throws OdaException
  {
    // only input parameters supported
    return IParameterMetaData.parameterModeIn;
  }

  public String getParameterName(int param) throws OdaException
  {
    if (param < 1 || param > getParameterCount())
    {
      throw new OdaException(new IllegalArgumentException(String.valueOf(param)));
    }

    for (Map.Entry<ParameterIdentifier, ? > entry : query.getSpecification().getParameterValues().entrySet())
    {
      ParameterIdentifier identifier = entry.getKey();

      if (identifier.hasId() && identifier.getParameterId().equals(param))
      {
        return identifier.getParameterName();
      }
    }

    throw new OdaException(new IllegalArgumentException(String.valueOf(param)));
  }

  public Object getParameterDefaultValue(String name) throws OdaException
  {
    Object defaultValue = getParameterClassifier(name).getDefaultValue();
    return defaultValue == null ? DEFAULT_PARAMETER_VALUE : String.valueOf(defaultValue);
  }

  protected EClassifier getParameterClassifier(String name) throws OdaException
  {
    if (TARGET_PARAMETER_NAME.equals(name))
    {
      return EcorePackage.Literals.EOBJECT;
    }
    else
    {
      Map<String, EClassifier> variables = query.getVariables();

      if (!variables.isEmpty())
      {
        EClassifier classifier = variables.get(name);

        if (classifier != null)
        {
          return classifier;
        }
      }

      return EcorePackage.Literals.EJAVA_OBJECT;
    }
  }

  /**
   * Returns the data provider specific type for the specified parameter.
   * @param param 1-based index of the parameter
   * @return the native data type of the parameter
   * @throws OdaException if data source error occurs
   */
  protected EClassifier getParameterClassifier(int param) throws OdaException
  {
    return getParameterClassifier(getParameterName(param));
  }

  public int getParameterType(int param) throws OdaException
  {
    return DataTypes.getType(getParameterClassifier(param));
  }

  public String getParameterTypeName(int param) throws OdaException
  {
    return DataTypes.getTypeName(getParameterClassifier(param));
  }

  public int getPrecision(int param) throws OdaException
  {
    return DataTypes.getPrecision(getParameterClassifier(param));
  }

  public int getScale(int param) throws OdaException
  {
    return DataTypes.getScale(getParameterClassifier(param));
  }

  public int isNullable(int param) throws OdaException
  {
    switch (DataTypes.isNullable(getParameterClassifier(param)))
    {
      case DataTypes.noNulls:
        return IParameterMetaData.parameterNoNulls;
      case DataTypes.nullable:
        return IParameterMetaData.parameterNullable;
      default:
        return IParameterMetaData.parameterNullableUnknown;
    }
  }
}