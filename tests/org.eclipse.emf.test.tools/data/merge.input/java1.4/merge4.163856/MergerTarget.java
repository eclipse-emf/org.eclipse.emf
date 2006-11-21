/* 
 * target header 
 */
import java.awt.*;

/**
 * target javadoc
 * set super interfaces
 * set this comment
 * set these flags
 * set this comment
 */
public class EchoSoapBindingImpl   implements echo.EchoTarget 
{
  /*
   * set flags test
   */
  
  // target: no flags in target, should be brought from source
  int noFlagsInTarget;
  
  // target: many flags, should be removed
  private static final transient volatile int manyFlagsInTarget = 0;
  
  // target: split1..3 must be split, all change flags
  public int split1 = 1, split2 = 2, split3 = 3;
  
  /*
   * In JDOM multiple variable declarations can not have javadoc comment 
   * 
   * target non-javadoc: split4..6, split5 must be separated as it changes flag
   * new line of comment
   *  
   * line after empty line
   */
  public int split4 = 4, split5 = 5, split6 = 6;  
  
  /*
   * set type test
   */
  
  // target: should change to Object
  int noFlagsInTargetT;
  
  // target: should change to double
  private static final transient volatile int manyFlagsInTargetT = 0;
  
  // target: splitT1..T3 must split, all change types
  public int splitT1 = 1, splitT2 = 2, splitT3 = 3;
  
  /*
   * target non-javadoc: splitT6 must be separated as it changes type
   */
  public int splitT4 = 4, splitT5 = 5, splitT6 = 6;      
  
  // target: change type
  Object stringT = new String();

  // target: change type
  Object stringLiteralT = "test"; 
  
  // Arrays
  int[]      simpleArrayT = { 1,
    2, 
    3};
  
  /*
   * set initializer test
   */

  /**
   * Target initializer comment
   * set this comment
   * set these flags
   */  
  {
    System.out.println("Target class level initializer 1 line 1");    
    // begin-user-code
    // target line comment in initializer 1
    System.out.println("Target class level initializer 1 line 2");
    // end-user-code
    System.out.println("Target class level initializer 1 line 3");    
  }
  
  // target: 
  int noFlagsInTargetI;
  
  // target: 
  private static final transient volatile int manyFlagsInTargetI = 0;
  
  // target: should not be split, only initializers change
  public int splitI1 = 1, splitI2 = 2, splitI3 = 3;
  
  /**
   *  target javadoc: split due to setComment
   *  set this comment
   */
  public int splitI4 = 4, splitI5 = 5, splitI6 = 6;      
  
  // Objects test
  Object stringI = new String();

  // Objects test 2
  Object stringLiteralI = "test"; 
  
  // Arrays - no values changed
  int[] simpleArrayI1 = {1,
    2, /* comment between values */
    3};

  // Arrays - only last value changed
  int[] simpleArrayI2 = {1,
    2,
    3};  
    
  /**
   * Note: JDOM removes final keyword from parameter
   * 
   * target comment
   * @param name
   * @throws java.rmi.RemoteException
   * @generated
   */
  public void hello(java.lang.Source targetName1, final int[][][] sourceTripleArray,  
      String paramOnNewLine) throws 
    java.rmi.RemoteException,
    BadAttributeValueExpException,
//  begin-user-code  
    NullPointerException,
    IllegalArgumentException,
//  end-user-code  
    TestS
    
  {
    // begin-user-code
    System.out.println("Target user code 1");
    // end-user-code    
    System.out.println("Target code 2");
    // source code to be overwritten
    return name;
  }
  
  /**
   * target comment 1
   * this comment should not be modified
   * set this comment
   */
  // public void hello1(java.lang.String name) throws java.rmi.RemoteException {/** target */}
  /**
   * @param name
   * @throws java.rmi.RemoteException
   *
   * target comment 2
   * set this comment
   */
  public void hello1(java.lang.String name) throws java.rmi.RemoteException
  {
//  not generated method, line comment starts at the beginning of the line
    // target comment
    return name;
  }
  
  /**
   * target comment 3
   * @param name
   * @throws java.rmi.RemoteException
   */
  public void hello2(java.lang.String name) throws java.rmi.RemoteException
  {
    // not generated, line comment indented
    // target comment
    return name;
  }
}
