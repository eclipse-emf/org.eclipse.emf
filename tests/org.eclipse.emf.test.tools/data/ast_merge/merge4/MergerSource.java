/* 
 * source header 
 * 
 */
import java.util.*;
/**
 * Source javadoc
 * set super interfaces
 * set this comment
 * set these flags
 * set this comment
 */
private class EchoSoapBindingImpl extends  echo.EchoBindingImpl implements echo.EchoSource1, EchoSource2
{
  // source first line comment, not copied to target
  
  /**
   * source javadoc Non-existing fields in target
   */
  public static final double firstNotInTarget = Math.PI; 

  // Non-existing multiple decl in source (splitN1..N3) - comment not copied to target
  public int splitN1 = Math.abs(-1), splitN2 = 3, splitN3;  

  // source line comment before source javadoc
  
  /**
   * source javadoc: Non-existing field with flags in reversed order
   * (starts with volatile)
   */
  volatile  transient  final static private     double reversedFlagsNotInTarget = 0;
  
  /*
   * set flags test
   */
  
  // source: no flags in target
  public static final transient volatile int noFlagsInTarget;
  
  // source: many flags
  protected final int manyFlagsInTarget = 0;
  
  // source: split, all change flags
  private int split1 = 1, split2 = 2, split3 = 3;
  
  /*
   *  source non-javadoc: split, one changes flag
   */
  public int split4 = 4, split6 = 6;
  private int split5 = 5;
  
  /*
   * set type test
   */
  
  // source: no flags in target
  Object noFlagsInTargetT;
  
  // source: set type
  private static final transient volatile double manyFlagsInTargetT = 0;
  
  // source: split, all change types
  public long splitT1 = 1, splitT2 = 2, splitT3 = 3;
  
  /**
   *  source javadoc: split, one changes type
   */
  //public int splitT4 = 4, splitT5 = 5;
  public long splitT6 = 6;      
  
  // Objects test
  String stringT = new String();

  // Objects test 2
  java.lang.String stringLiteralT = "test"; 
  
  // Arrays
  double[]      simpleArrayT = { 1,
    2, 
    3};
  
  /*
   * Initializer.setBody test
   */
  
  /**
   * Source initializer comment
   * set this comment
   * set these flags
   */
  static {
    System.out.println("Source class level initializer 1 line 1");    
    // begin-user-code
    // source line comment in initializer 1
    System.out.println("Source class level initializer 1 line 2");
    // end-user-code
    System.out.println("Source class level initializer 1 line 3");    
  }  
  
  // source: add initializer
  int noFlagsInsourceI = 1;
  
  // source: remove initializer
  volatile private static final transient  int manyFlagsInTargetI;
  
  // source: should not be split, only initializers change
  public int splitI1 = 100, splitI2 = 200, splitI3 = 300;
  
  /**
   *  source javadoc: split I5 since it has a comment
   */
  public int splitI5 = 500;
  public int splitI6;   
  
  // Objects test
  Object stringI = "1" +
        "2" +
        "3";

  // Objects test 2
  java.lang.Object stringLiteralI = new String("test"); 
  
  // Arrays
  int[] simpleArrayI1 = {1, 2,  3};
  
  int[] simpleArrayI2 = {1, 2,  4};
  
  /**
   * JDOM removed final keyword from parameter type
   * 
   * Source comment
   * @param name
   * @throws java.rmi.RemoteException
   * @generated
   */
  public void hello(java.lang.Source sourceName1, final int[][] sourceTripleArrayWithSourceName[],  
      String paramOnNewLine) throws 
    java.rmi.RemoteException,
    BadAttributeValueExpException,
    NullPointerException,
    IllegalArgumentException,
//  comment between exceptions  
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
    TestS
  
  {
    // begin-user-code
    System.out.println("Source user code 3");
    // end-user-code    
    System.out.println("Source code 4");
    // source code to be overwritten
    return name;
  }  
  
  /**
   * source method comment 2
   * 
   * @param name
   * @throws java.rmi.RemoteException
   */
  public void hello1(java.lang.String name) throws java.rmi.RemoteException
  {
//  not generated method, line comment starts at the beginning of the line
    // source comment 1
  }
  
  /**
   * source method comment 3
   * 
   * @param name
   * @throws java.rmi.RemoteException
   */
  public void hello2(java.lang.String name) throws java.rmi.RemoteException
  {
    // not generated, line comment indented
    // source comment 2
  }
}
