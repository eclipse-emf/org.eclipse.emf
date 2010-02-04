/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * $Id: BaseFacadeTest.java,v 1.4 2010/02/04 20:56:33 emerks Exp $
 */
package org.eclipse.emf.test.tools.merger.facade;

import java.io.File;
import java.util.Hashtable;

import junit.framework.TestCase;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;

import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;


public class BaseFacadeTest extends TestCase
{
  protected String baseDataDirectory = "data" + File.separator + "facade.ast";

  protected String testDirectoryName = getClass().getSimpleName();

  protected String compilationUnitFileName = "Source.java";

  protected FacadeHelper facadeHelper;

  protected File compilationUnitFile;
  protected JCompilationUnit compilationUnit;

  protected static enum Operation {
    ADD,
    REMOVE
  }

  /**
   * Initializes attributes from another test.
   * 
   * @param anotherTest
   */
  public BaseFacadeTest(BaseFacadeTest anotherTest)
  {
    this.facadeHelper = anotherTest.facadeHelper;
    this.compilationUnit = anotherTest.compilationUnit;
  }
  
  public BaseFacadeTest()
  {
    super();
  }
  
  @Override
  protected void setUp() throws Exception
  {
    super.setUp();
    
    adjustJavaCoreOptions();

    facadeHelper = instanciateFacadeHelper();

    File dataDirectory = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID), baseDataDirectory);
    assertTrue("Directory " + dataDirectory.getAbsolutePath() + " can not be read.", dataDirectory.isDirectory());

    File testDirectory = new File(dataDirectory, testDirectoryName);
    assertTrue("Directory " + testDirectory.getAbsolutePath() + " can not be read.", testDirectory.isDirectory());

    compilationUnitFile = new File(testDirectory, compilationUnitFileName);
    assertTrue("File " + compilationUnitFile + " can not be read.", compilationUnitFile.isFile());

    String content = TestUtil.readFile(compilationUnitFile, false);
    compilationUnit = facadeHelper.createCompilationUnit(compilationUnitFile.getName(), content);
  }

  protected FacadeHelper instanciateFacadeHelper()
  {
    return new org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper();
  }

  protected void rewriteAndCompare()
  {
    // default name is the name of the test method with capitalized first letter
    rewriteAndCompare(getName().substring(0, 1).toUpperCase() + getName().substring(1) + ".java");
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void adjustJavaCoreOptions()
  {
    Hashtable options = JavaCore.getDefaultOptions();
    
    options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
    options.put(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR, JavaCore.SPACE);
    options.put(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE, "2");
    options.put(DefaultCodeFormatterConstants.FORMATTER_INDENTATION_SIZE, "2");
    
    JavaCore.setOptions(options);
  }

  protected void rewriteAndCompare(String expectedOutputFileName)
  {
    File outputFile = new File(compilationUnitFile.getParentFile(), expectedOutputFileName);
    assertTrue("Output file " + outputFile.getAbsolutePath() + " can not be read.", outputFile.isFile());

    String content = TestUtil.readFile(outputFile, false);
    assertEquals(content, compilationUnit.getContents());
  }

  protected void testNoChildren(JNode parentNode, int noChildren)
  {
    testNoChildren(parentNode, noChildren, JNode.class);
  }
  
  protected void testNoChildren(JNode parentNode, int noChildren, Class<? extends JNode> childrenClass)
  {
    assertEquals("Number of children of " + parentNode.getClass().getSimpleName() + " does not match", noChildren, facadeHelper.getChildren(parentNode, childrenClass).size());
  }
  
  protected int updateNoChildren(JNode parentNode, JNode node, Operation operation, int noChildrenBefore)
  {
    return updateNoChildren(parentNode, node, operation, noChildrenBefore, JNode.class);
  }

  protected int updateNoChildren(
    JNode parentNode,
    JNode node,
    Operation operation,
    int noChildrenBefore,
    Class<? extends JNode> childrenClass)
  {
    switch (operation)
    {
      case ADD:
        noChildrenBefore++;

        assertEquals(
          "Number of children after adding " + node.getClass().getSimpleName() + " does not match",
          noChildrenBefore,
          facadeHelper.getChildren(parentNode, childrenClass).size());
        assertTrue(
          "Children must contain added " + node.getClass().getSimpleName(),
          facadeHelper.getChildren(parentNode, childrenClass).contains(node));
        break;
        
      case REMOVE:
        noChildrenBefore--;

        assertEquals(
          "Number of children after removing " + node.getClass().getSimpleName() + " does not match",
          noChildrenBefore,
          facadeHelper.getChildren(parentNode, childrenClass).size());
        assertFalse("Children must node contain removed " + node.getClass().getSimpleName(), facadeHelper.getChildren(
          parentNode,
          childrenClass).contains(node));
    }
    return noChildrenBefore;
  }

  protected void removeAllChildren(JNode node, int noChildrenBefore)
  {
    removeAllChildren(node, noChildrenBefore, JNode.class);
  }

  protected void removeAllChildren(JNode node, int noChildrenBefore, Class<? extends JNode> childrenClass)
  {
    assertEquals(
      "Number of children of " + node.getClass().getSimpleName() + " does not match",
      noChildrenBefore,
      facadeHelper.getChildren(node, childrenClass).size());

    int n = facadeHelper.getChildren(node, childrenClass).size();
    for (JNode child : facadeHelper.getChildren(node, childrenClass))
    {
      assertTrue(facadeHelper.remove(child));
      assertFalse(facadeHelper.remove(child));
      n = updateNoChildren(node, child, Operation.REMOVE, n, childrenClass);
    }

    assertEquals(
      "Number of children of " + node.getClass().getSimpleName() + " after removing children does not match",
      0,
      facadeHelper.getChildren(node, childrenClass).size());
  }
  
  protected int insertSibling(JNode node, JNode newSibling, boolean before, int noChildrenBefore)
  {
    JNode parent = node.getParent();
    assertTrue("insertSibling() on " + newSibling.getClass().getSimpleName() + " returned false", facadeHelper.insertSibling(node, newSibling, before));
    assertFalse("Repeated insertSibling() on " + newSibling.getClass().getSimpleName() + " returned true", facadeHelper.insertSibling(node, newSibling, before));
    return updateNoChildren(parent, newSibling, Operation.ADD, noChildrenBefore);
  }
  
  protected int addChild(JNode node, JNode child, int noChildrenBefore)
  {
    assertTrue("addChild() on " + child.getClass().getSimpleName() + " returned false", facadeHelper.addChild(node, child));
    assertFalse("Repeated addChild() on " + child.getClass().getSimpleName() + " returned true", facadeHelper.addChild(node, child));
    return updateNoChildren(node, child, Operation.ADD, noChildrenBefore);
  }  
  
  protected int remove(JNode node, int noChildrenBefore)
  {
    JNode parent = node.getParent();
    assertTrue("remove() on " + node.getClass().getSimpleName() + " returned false", facadeHelper.remove(node));
    assertFalse("Repeated remove() on " + node.getClass().getSimpleName() + " returned true", facadeHelper.remove(node));
    return updateNoChildren(parent, node, Operation.REMOVE, noChildrenBefore);
  }   
}
