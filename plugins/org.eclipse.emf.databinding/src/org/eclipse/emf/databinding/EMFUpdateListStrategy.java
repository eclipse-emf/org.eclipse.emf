/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 */
public class EMFUpdateListStrategy extends UpdateListStrategy
{
  /**
   * A list update strategy with default update policy
   * {@link UpdateListStrategy#POLICY_UPDATE} and default converters and
   * validators
   */
  public EMFUpdateListStrategy()
  {
    this(true, POLICY_UPDATE);
  }

  /**
   * A list update strategy with a specific update policy but with default
   * converters and validators
   * 
   * @param updatePolicy
   *            the policy
   */
  public EMFUpdateListStrategy(int updatePolicy)
  {
    this(true, updatePolicy);
  }

  /**
   * A list update strategy with a specific update policy and with the
   * possibility to turn of default converters and validators
   * 
   * @param provideDefaults
   *            <code>false</code> to turn of default converters and
   *            validators
   * @param updatePolicy
   *            the policy
   */
  public EMFUpdateListStrategy(boolean provideDefaults, int updatePolicy)
  {
    super(provideDefaults, updatePolicy);
  }

  @Override
  protected IConverter createConverter(Object fromType, Object toType)
  {
    if (fromType == String.class)
    {
      if (toType instanceof EAttribute)
      {
        final EAttribute eAttribute = (EAttribute)toType;
        final EDataType eDataType = eAttribute.getEAttributeType();
        final EFactory eFactory = eDataType.getEPackage().getEFactoryInstance();
        return new Converter(fromType, toType)
          {
            public Object convert(Object fromObject)
            {
              return eFactory.createFromString(eDataType, (String)fromObject);
            }
          };
      }
    }
    else if (toType == String.class)
    {
      if (fromType instanceof EAttribute)
      {
        final EAttribute eAttribute = (EAttribute)fromType;
        final EDataType eDataType = eAttribute.getEAttributeType();
        final EFactory eFactory = eDataType.getEPackage().getEFactoryInstance();
        return new Converter(fromType, toType)
          {
            public Object convert(Object fromObject)
            {
              return eFactory.convertToString(eDataType, fromObject);
            }
          };
      }
    }
    return super.createConverter(fromType, toType);
  }
}
