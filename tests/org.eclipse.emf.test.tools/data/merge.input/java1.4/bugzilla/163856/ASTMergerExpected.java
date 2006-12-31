/* 
 * source header 
 * 
 */
import java.util.*;
import java.awt.*;

/**
 * Source javadoc
 * set super interfaces
 * set this comment
 * set these flags
 * set this comment
 */
private class EchoSoapBindingImpl extends echo.EchoBindingImpl   implements echo.EchoSource1, EchoSource2 
{
  /*
   * set flags test
   */
  
  /**
   * source javadoc Non-existing fields in target
   */
  public static final double firstNotInTarget = Math.PI;

  public int splitN1 = Math.abs(-1);

  public int splitN2 = 3;

  public int splitN3;

  /**
   * source javadoc: Non-existing field with flags in reversed order
   * (starts with volatile)
   */
  volatile  transient  final static private     double reversedFlagsNotInTarget = 0;

  // target: no flags in target, should be brought from source
  public static final transient volatile int noFlagsInTarget;
  
  // target: many flags, should be removed
  protected final int manyFlagsInTarget = 0;
  
  private int split1 = 1;

  private int split2 = 2;

  // target: split1..3 must be split, all change flags
  private int split3 = 3;
  
  private int split5 = 5;

  /*
   * In JDOM multiple variable declarations can not have javadoc comment 
   * 
   * target non-javadoc: split4..6, split5 must be separated as it changes flag
   * new line of comment
   *  
   * line after empty line
   */
  public int split4 = 4, split6 = 6;  
  
  /*
   * set type test
   */
  
  // target: should change to Object
  Object noFlagsInTargetT;
  
  // target: should change to double
  private static final transient volatile double manyFlagsInTargetT = 0;
  
  public long splitT1 = 1;

  public long splitT2 = 2;

  // target: splitT1..T3 must split, all change types
  public long splitT3 = 3;
  
  public long splitT6 = 6;

  /*
   * target non-javadoc: splitT6 must be separated as it changes type
   */
  public int splitT4 = 4, splitT5 = 5;      
  
  // target: change type
  String stringT = new String();

  // target: change type
  java.lang.String stringLiteralT = "test"; 
  
  // Arrays
  double[]      simpleArrayT = { 1,
    2, 
    3};
  
  /*
   * set initializer test
   */

  /**
   * Source initializer comment
   * set this comment
   * set these flags
   */  
  static {
    System.out.println("Source class level initializer 1 line 1");    
    // begin-user-code
    // target line comment in initializer 1
    System.out.println("Target class level initializer 1 line 2");
    // end-user-code
    System.out.println("Source class level initializer 1 line 3");    
  }
  
  int noFlagsInsourceI = 1;

  // target: 
  int noFlagsInTargetI;
  
  // target: 
  private static final transient volatile int manyFlagsInTargetI;
  
  // target: should not be split, only initializers change
  public int splitI1 = 100, splitI2 = 200, splitI3 = 300;
  
  /**
   *  source javadoc: split I5 since it has a comment
   */
  public int splitI5 = 500;

  /**
   *  target javadoc: split due to setComment
   *  set this comment
   */
  public int splitI4 = 4, splitI6;      
  
  // Objects test
  Object stringI = "1" +
        "2" +
        "3";

  // Objects test 2
  java.lang.Object stringLiteralI = new String("test"); 
  
  // Arrays - no values changed
  int[] simpleArrayI1 = {1, 2,  3};

  // Arrays - only last value changed
  int[] simpleArrayI2 = {1, 2,  4};  
    
  /**
   * Note: JDOM removes final keyword from parameter
   * 
   * target comment
   * @param name
   * @throws java.rmi.RemoteException
   * @generated
   */
  public void hello(java.lang.Source sourceName1, final int[][][] sourceTripleArrayWithSourceName,  
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
    System.out.println("Source user code 1");
    // end-user-code    
    System.out.println("Source code 2");
    // source code to be overwritten
    return name;
  }
  
  /**
   * Method same as hello(..) but has different parameters
   * 
   * source method comment 1
   * @param sourceName1
   * @throws java.rmi.RemoteException
   * @throws BadAttributeValueExpException
   * @throws NullPointerException
   * @throws IllegalArgumentException
   * @throws TestS
   * 
   */
  public void hello(java.lang.Source sourceName1) throws 
    java.rmi.RemoteException,
    BadAttributeValueExpException,
  //begin-user-code  
    NullPointerException,
    IllegalArgumentException,
  //end-user-code  
    TestS {
    // begin-user-code
    System.out.println("Source user code 3");
    // end-user-code    
    System.out.println("Source code 4");
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
   * source method comment 2
   * 
   * @param name
   * @throws java.rmi.RemoteException
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
