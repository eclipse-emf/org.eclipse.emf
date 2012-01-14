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
package org.eclipse.emf.test.tools.merger.facade;

import java.util.Arrays;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JEnum;
import org.eclipse.emf.codegen.merge.java.facade.JMember;

public class EnumsTest extends TypesTest
{
  @Override
  protected void modifyType(JAbstractType abstractType, String modificationId, int flags)
  {
    JEnum type = (JEnum)abstractType;
    type.setComment("/** OperationEnum javadoc " + modificationId + "\n// line comment\n */");
    type.setComment("/** OperationEnum javadoc " + modificationId + "_1\n// line comment\n */");
    type.setFlags(flags);
    type.setSuperInterfaces(new String[] {"Interface" + modificationId, "Interface" + modificationId + "_1" });
    type.addSuperInterface("Interface" + modificationId + "_2");
    type.addSuperInterface("Interface" + modificationId + "_3");
    type.setName("Operation");
    type.setName("OperationEnum" + modificationId);
  }
  
  @Override
  protected void readOriginalType(JAbstractType abstractType)
  {
    JEnum type = (JEnum)abstractType;
    assertEquals("/**\n * \n * OperationEnum javadoc\n * Second line of javadoc\n */", type.getComment());
    assertEquals(FacadeFlags.DEFAULT, type.getFlags());
    assertTrue(Arrays.equals(new String [0], type.getSuperInterfaces()));
    assertEquals("OperationEnum", type.getName());
  }  
  
  @Override
  protected void readType(JAbstractType abstractType, String modificationId, int flags)
  {
    JEnum type = (JEnum)abstractType;
    assertEquals("/** OperationEnum javadoc " + modificationId + "_1\n// line comment\n */", type.getComment());
    assertEquals(FacadeFlags.DEFAULT, type.getFlags());
    assertTrue(Arrays.equals(new String []{
      "Interface" + modificationId,
      "Interface" + modificationId + "_1",
      "Interface" + modificationId + "_2",
      "Interface" + modificationId + "_3" }, type.getSuperInterfaces()));
    assertEquals("OperationEnum" + modificationId, type.getName());
  }

  @Override
  protected void clearAllPropertiesOfType(JAbstractType abstractType)
  {
    JEnum type = (JEnum)abstractType;
    type.setComment(null);
    type.setFlags(FacadeFlags.DEFAULT);
    type.setSuperInterfaces(new String[0]);
    type.setName("ClearedOperationEnum");

    // keep annotations    
    removeAllChildren(type, 2, JMember.class);
  }
}
