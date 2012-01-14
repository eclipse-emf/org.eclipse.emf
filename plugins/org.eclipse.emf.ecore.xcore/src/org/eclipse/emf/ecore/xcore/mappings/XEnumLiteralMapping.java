/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;

import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.xtext.common.types.JvmField;

public class XEnumLiteralMapping extends AbstractMapping
{
	private GenEnumLiteral genEnumLiteral;
	private JvmField field;
	private EEnumLiteral eEnumLiteral;
	
	public GenEnumLiteral getGenEnumLiteral()
  {
  	return genEnumLiteral;
  }
	public void setGenEnumLiteral(GenEnumLiteral genEnumLiteral)
  {
  	this.genEnumLiteral = genEnumLiteral;
  }
	public JvmField getField()
  {
  	return field;
  }
	public void setField(JvmField field)
  {
  	this.field = field;
  }
	
	public EEnumLiteral getEEnumLiteral()
  {
	  return eEnumLiteral;
  }
	
	public void setEEnumLiteral(EEnumLiteral eEnumLiteral)
  {
	  this.eEnumLiteral = eEnumLiteral;
  }
}
