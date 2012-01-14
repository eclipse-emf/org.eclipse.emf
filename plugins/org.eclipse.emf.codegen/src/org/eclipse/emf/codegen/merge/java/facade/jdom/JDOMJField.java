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
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMField;

import org.eclipse.emf.codegen.merge.java.facade.JField;


/**
 * @since 2.2.0
 */
@SuppressWarnings({"deprecation", "unchecked"})
public class JDOMJField extends JDOMJMember implements JField
{
  public JDOMJField(IDOMField field)
  {
    super(field);
  }

  @Override
  protected IDOMField getWrappedObject()
  {
    return (IDOMField)super.getWrappedObject();
  }

  public String getInitializer()
  {
    return getWrappedObject().getInitializer();
  }
  
  public void setInitializer(String initializer)
  {
    getWrappedObject().setInitializer(initializer);
  }

  public String getType()
  {
    return getWrappedObject().getType();
  }
  
  public void setType(String typeName)
  {
    getWrappedObject().setType(typeName);
  }
}
