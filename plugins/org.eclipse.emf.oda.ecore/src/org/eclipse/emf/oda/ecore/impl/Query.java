/**
 * Copyright (c) 2010 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 */
package org.eclipse.emf.oda.ecore.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification.ParameterIdentifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.QueryDelegate;
import org.eclipse.emf.oda.ecore.util.StringUtil;


/**
 * Implementation of IQuery for EMF ODA runtime driver.
 */
public class Query implements IQuery
{
  public static final String DELEGATE_PROPERTY_NAME = "delegate"; //$NON-NLS-1$
  public static final String CONTEXT_PROPERTY_NAME = "context"; //$NON-NLS-1$
  public static final String VARIABLES_PROPERTY_NAME = "variables"; //$NON-NLS-1$
  public static final String TYPE_PROPERTY_NAME = "type"; //$NON-NLS-1$

  protected final Connection connection;

  protected QuerySpecification specification = null;

  protected QueryDelegate.Factory factory = null;
  protected QueryDelegate delegate = null;
  protected EClassifier context = null;
  protected Map<String, EClassifier> variables = null;
  protected EClassifier type = null;

  protected String queryText = null;
  protected int maxRows = 0;

  protected Query(Connection connection)
  {
    super();

    this.connection = connection;
  }

  /**
   * Asserts that a specification has been provided for this query.
   * @throws OdaException if a specification has not been provided
   */
  protected void assertSpecified() throws OdaException
  {
    if (specification == null)
    {
      throw new OdaException(new IllegalStateException());
    }
  }

  /**
   * Asserts that this query has been prepared.
   * @throws OdaException if the query has not been prepared
   */
  protected void assertPrepared() throws OdaException
  {
    if (delegate == null || context == null || type == null)
    {
      throw new OdaException(new IllegalStateException());
    }
  }

  /**
   * Returns the expression variables specified for this query.
   * @return a map of variable names to types
   * @throws OdaException if a specification has not been provided
   */
  protected Map<String, EClassifier> getVariables() throws OdaException
  {
    assertSpecified();

    if (variables == null)
    {
      variables = new HashMap<String, EClassifier>();

      String string = (String)specification.getProperty(VARIABLES_PROPERTY_NAME);

      if (!StringUtil.isEmpty(string))
      {
        ResourceSet resourceSet = connection.getResourceSet();
        String[] strings = string.split(Pattern.quote(" ")); //$NON-NLS-1$

        for (int i = 0; i < strings.length; i += 2)
        {
          variables.put(strings[i], (EClassifier)resourceSet.getEObject(URI.createURI((strings[i + 1])), true));
        }
      }
    }

    return variables;
  }

  public void prepare(String queryText) throws OdaException
  {
    assertSpecified();

    if (StringUtil.isEmpty(queryText))
    {
      throw new OdaException(new IllegalArgumentException(queryText));
    }

    this.queryText = queryText;

    try
    {
      factory = (QueryDelegate.Factory)QueryDelegate.Factory.Registry.INSTANCE.get((String)specification.getProperty(DELEGATE_PROPERTY_NAME));

      ResourceSet resourceSet = connection.getResourceSet();

      context = (EClassifier)resourceSet.getEObject(URI.createURI(((String)specification.getProperty(CONTEXT_PROPERTY_NAME))), true);

      Map<String, EClassifier> variables = getVariables();
      delegate = factory.createQueryDelegate(context, variables.isEmpty() ? null : variables, queryText);

      type = (EClassifier)resourceSet.getEObject(URI.createURI(((String)specification.getProperty(TYPE_PROPERTY_NAME))), true);

      delegate.prepare();
    }
    catch (Exception e)
    {
      throw new OdaException(e);
    }
  }

  public void setAppContext(Object context) throws OdaException
  {
    // do nothing; no support for pass-through context
  }

  public void close() throws OdaException
  {
    delegate = null;
    context = null;
    type = null;
  }

  public IResultSetMetaData getMetaData() throws OdaException
  {
    assertPrepared();
    return ResultSetMetaData.create(type);
  }

  protected EList<Object> getAllObjectsByType(EList<Object> objects, EClassifier type)
  {
    for (TreeIterator<Object> allContents = EcoreUtil.getAllContents(connection.getResourceSet(), true); allContents.hasNext();)
    {
      Object next = allContents.next();

      if (type.isInstance(next))
      {
        objects.add(next);
      }
    }

    return objects;
  }

  protected EList<Object> getResults(
    EList<Object> results,
    QueryDelegate delegate,
    EList<Object> targets,
    Map<String, Object> arguments,
    EClassifier type) throws InvocationTargetException
  {
    for (Object target : targets)
    {
      Object result = delegate.execute(target, arguments);

      if (result instanceof Collection< ? >)
      {
        results.addAll(EcoreUtil.getObjectsByType((Collection< ? >)result, type));
      }
      else if (type.isInstance(result))
      {
        results.add(result);
      }
    }

    return results;
  }

  public IResultSet executeQuery() throws OdaException
  {
    assertPrepared();

    EList<Object> targets = new UniqueEList.FastCompare<Object>();
    Map<String, Object> arguments = new HashMap<String, Object>();

    Map<ParameterIdentifier, ? > parameterValues = specification.getParameterValues();

    if (!parameterValues.isEmpty())
    {
      Object targetArgument = null;

      Map<String, EClassifier> dataTypeParameters = new HashMap<String, EClassifier>();
      Map<String, Object> dataTypeArguments = new HashMap<String, Object>();

      Map<String, Object> classArguments = new HashMap<String, Object>();

      for (Map.Entry<ParameterIdentifier, ? > entry : parameterValues.entrySet())
      {
        String name = entry.getKey().getParameterName();

        if (!StringUtil.isEmpty(name))
        {
          Object value = entry.getValue();

          if (value instanceof ResultSet.JavaObject)
          {
            value = ((ResultSet.JavaObject)value).getObject();
          }

          if (value != null)
          {
            if (ParameterMetaData.TARGET_PARAMETER_NAME.equals(name))
            {
              targetArgument = value;
            }
            else
            {
              EClassifier type = variables.get(name);

              if (type instanceof EClass)
              {
                classArguments.put(name, value);
              }
              else
              {
                dataTypeParameters.put(name, type);
                dataTypeArguments.put(name, value);
              }
            }
          }
        }
      }

      if (targetArgument == null || ParameterMetaData.DEFAULT_PARAMETER_VALUE.equals(targetArgument))
      {
        getAllObjectsByType(targets, context);
      }
      else if (targetArgument instanceof String)
      {
        try
        {
          QueryDelegate delegate = factory.createQueryDelegate(context, dataTypeParameters, (String)targetArgument);
          getResults(targets, delegate, getAllObjectsByType(new UniqueEList.FastCompare<Object>(), context), dataTypeArguments, context);
        }
        catch (Exception e)
        {
          Throwable cause = e.getCause();
          throw new OdaException(ParameterMetaData.TARGET_PARAMETER_NAME
            + ": " + (cause == null ? e.getLocalizedMessage() : cause.getLocalizedMessage())); //$NON-NLS-1$
        }
      }
      else if (targetArgument instanceof Collection< ? >)
      {
        targets.addAll((Collection< ? >)targetArgument);
      }
      else
      {
        targets.add(targetArgument);
      }

      arguments.putAll(dataTypeArguments);

      for (Map.Entry<String, Object> entry : classArguments.entrySet())
      {
        String name = entry.getKey();
        Object value = entry.getValue();

        if (value instanceof String)
        {
          EClassifier type = variables.get(name);
          EList<Object> values = getAllObjectsByType(new UniqueEList.FastCompare<Object>(), type);

          if (!ParameterMetaData.DEFAULT_PARAMETER_VALUE.equals(value))
          {
            try
            {
              QueryDelegate delegate = factory.createQueryDelegate(type, dataTypeParameters, (String)value);
              values = getResults(new UniqueEList.FastCompare<Object>(), delegate, values, dataTypeArguments, type);
            }
            catch (Exception e)
            {
              Throwable cause = e.getCause();
              throw new OdaException(name + ": " + (cause == null ? e.getLocalizedMessage() : cause.getLocalizedMessage())); //$NON-NLS-1$
            }
          }

          value = values.isEmpty() ? null : values.iterator().next();
        }

        arguments.put(name, value);
      }
    }

    try
    {
      IResultSet resultSet = ResultSet.create(type, getResults(new UniqueEList<Object>(), delegate, targets, arguments, type));
      resultSet.setMaxRows(getMaxRows());
      return resultSet;
    }
    catch (Exception e)
    {
      Throwable cause = e.getCause();

      throw new OdaException(cause == null ? e.getLocalizedMessage() : cause.getLocalizedMessage());
    }
  }

  public void setProperty(String name, String value) throws OdaException
  {
    assertSpecified();
    specification.setProperty(name, value);
  }

  public void setMaxRows(int max) throws OdaException
  {
    maxRows = max;
  }

  public int getMaxRows() throws OdaException
  {
    return maxRows;
  }

  public void clearInParameters() throws OdaException
  {
    throw new OdaException(new UnsupportedOperationException());
  }

  public void setInt(String parameterName, int value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setInt(int parameterId, int value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setDouble(String parameterName, double value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setDouble(int parameterId, double value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setBigDecimal(String parameterName, BigDecimal value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setBigDecimal(int parameterId, BigDecimal value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setString(String parameterName, String value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setString(int parameterId, String value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setDate(String parameterName, Date value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setDate(int parameterId, Date value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setTime(String parameterName, Time value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setTime(int parameterId, Time value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setTimestamp(String parameterName, Timestamp value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setTimestamp(int parameterId, Timestamp value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setBoolean(String parameterName, boolean value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setBoolean(int parameterId, boolean value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setObject(String parameterName, Object value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, value);
  }

  public void setObject(int parameterId, Object value) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, value);
  }

  public void setNull(String parameterName) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterName, null);
  }

  public void setNull(int parameterId) throws OdaException
  {
    assertSpecified();
    specification.setParameterValue(parameterId, null);
  }

  public int findInParameter(String parameterName) throws OdaException
  {
    assertSpecified();

    for (Map.Entry<ParameterIdentifier, ? > entry : specification.getParameterValues().entrySet())
    {
      ParameterIdentifier identifier = entry.getKey();

      if (identifier.hasName() && identifier.getParameterName().equals(parameterName))
      {
        return identifier.getParameterId();
      }
    }

    throw new OdaException(new IllegalArgumentException(parameterName));
  }

  public IParameterMetaData getParameterMetaData() throws OdaException
  {
    assertSpecified();
    return new ParameterMetaData(this);
  }

  public void setSortSpec(SortSpec sortBy) throws OdaException
  {
    // sorting not supported
    throw new OdaException(new UnsupportedOperationException());
  }

  public SortSpec getSortSpec() throws OdaException
  {
    // only applies to sorting
    return null;
  }

  public void setSpecification(QuerySpecification querySpec) throws OdaException, UnsupportedOperationException
  {
    specification = querySpec;
  }

  public QuerySpecification getSpecification()
  {
    return specification;
  }

  public String getEffectiveQueryText()
  {
    return queryText;
  }

  public void cancel() throws OdaException, UnsupportedOperationException
  {
    // unable to cancel while executing a query
    throw new UnsupportedOperationException();
  }
}