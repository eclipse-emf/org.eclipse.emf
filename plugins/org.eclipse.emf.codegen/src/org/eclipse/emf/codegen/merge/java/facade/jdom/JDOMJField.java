/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JDOMJField.java,v 1.1 2006/01/18 20:42:15 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMField;

import org.eclipse.emf.codegen.merge.java.facade.JField;


/**
 * @since 2.2.0
 */
public class JDOMJField extends JDOMJMember implements JField
{
  public JDOMJField(IDOMField field)
  {
    super(field);
  }

  protected IDOMField getIDOMField()
  {
    return (IDOMField)getIDOMNode();
  }

  public String getInitializer()
  {
    return getIDOMField().getInitializer();
  }
  
  public void setInitializer(String initializer)
  {
    getIDOMField().setInitializer(initializer);
  }

  public String getType()
  {
    return getIDOMField().getType();
  }
  
  public void setType(String typeName)
  {
    getIDOMField().setType(typeName);
  }
}
