package org.eclipse.emf.test.tools.merger;

import java.io.FileInputStream;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.jdom.JDOMFacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.test.tools.TestUtil;

/**
 * Tests and finds differences between 
 * output of JMerger when JDOM or AST Facade is used.
 * <p>
 * Since there are differences in most cases, 
 * this test should NOT be included in the build.
 * <p>  
 * This uses /data/ast_merge/mergeN directories for input files.
 */
public class JMergerCompareTest extends DeprecatedJMergerTest
{
  public JMergerCompareTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("JMergerCompareTest");
    
    for (int i = 0; i < 8; i++)
    {
      ts.addTest(new JMergerCompareTest("merge" + i));
    }
    return ts;
  }  

  protected String getDataDirectory()
  {
    return TestUtil.getPluginDirectory() + "/data/ast_merge/" + getName();
  }
  
  protected String getJDOMFacadeHelperName()
  {
    return JDOMFacadeHelper.class.getCanonicalName();
  }

  protected String getASTFacadeHelperName()
  {
    return ASTFacadeHelper.class.getCanonicalName();
  }
  
  protected String mergeFiles(String facadeHelperName) throws Exception
  {
    //long startTime = System.currentTimeMillis();
    FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(facadeHelperName);
    
    JControlModel controlModel = new JControlModel();
    assertFalse(controlModel.canMerge());
    controlModel.initialize(facadeHelper, mergeXML.getAbsolutePath());
    assertTrue(controlModel.canMerge());
    JMerger jMerger = new JMerger(controlModel);

    // set source
    jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(TestUtil.readFile(source, false)));
    
    // set target
    if (target.isFile())
    {
      jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(new FileInputStream(target)));
    }
    
    // merge source and target
    jMerger.merge();
    
    String result = jMerger.getTargetCompilationUnitContents();
    //System.out.println("Time: " + (System.currentTimeMillis() - startTime) + "-" + facadeHelperName);
    return result;
  }  

  public void merge0() throws Exception
  {
    assertEquals(mergeFiles(getJDOMFacadeHelperName()), mergeFiles(getASTFacadeHelperName()));
  }  
  
  public void merge1() throws Exception
  {
    assertEquals(mergeFiles(getJDOMFacadeHelperName()), mergeFiles(getASTFacadeHelperName()));
  }
  
  public void merge2() throws Exception
  {
    assertEquals(mergeFiles(getJDOMFacadeHelperName()), mergeFiles(getASTFacadeHelperName()));
  }
  
  public void merge3() throws Exception
  {
    assertEquals(mergeFiles(getJDOMFacadeHelperName()), mergeFiles(getASTFacadeHelperName()));
  }
  
  public void merge4() throws Exception
  {
    assertEquals(mergeFiles(getJDOMFacadeHelperName()), mergeFiles(getASTFacadeHelperName()));
  } 
  
  public void merge5() throws Exception
  {
    assertEquals(mergeFiles(getJDOMFacadeHelperName()), mergeFiles(getASTFacadeHelperName()));
  }
  
  public void merge6() throws Exception
  {
    assertEquals(mergeFiles(getJDOMFacadeHelperName()), mergeFiles(getASTFacadeHelperName()));
  }
  
  public void merge7() throws Exception
  {
    assertEquals(mergeFiles(getJDOMFacadeHelperName()), mergeFiles(getASTFacadeHelperName()));
  }  
}
