public class Example
{
  int a1 = 1;
  
//  /**
//   * @comment
//   */
//  int a3 = 3;

  /**
   * @rename
   */
  int a4_Deleted = 4;
  
  public void method1()
  {
    
  }

//  /**
//   * @comment
//   */
//  public void method3()
//  {
//    
//  }

  /**
   * @rename
   */
  public void method4_Deleted()
  {
    
  }
  
  public class Inner1
  {
    int a1; 

//    /**
//     * @comment
//     */
//    int a3; 

    /**
     * @rename
     */
    int a4_Deleted; 
  }

//  /**
//   * @comment
//   */
//  public class Inner3
//  {
//    int a1; 
//
//    /**
//     * @remove
//     */
//    int a2; 
//
//    /**
//     * @comment
//     */
//    int a3; 
//
//    /**
//     * @rename
//     */
//    int a4; 
//  }
  
  /**
   * @rename
   */
  public class Inner4_Deleted
  {
    int a1; 

    /**
     * @remove
     */
    int a2; 

    /**
     * @comment
     */
    int a3; 

    /**
     * @rename
     */
    int a4; 
  }
  
  protected enum Enum1
  {
    C1,

//    /**
//     * @comment
//     */
//    C3,

    /**
     * @rename
     */
    C4_Deleted;
  }
  
//  /**
//   * @comment
//   */
//  protected enum Enum3
//  {
//    C1,
//
//    /**
//     * @remove
//     */
//    C2,
//
//    /**
//     * @comment
//     */
//    C3,
//
//    /**
//     * @rename
//     */
//    C4;
//  } 
  
  /**
   * @rename
   */
  protected enum Enum4_Deleted
  {
    C1,

    /**
     * @remove
     */
    C2,

    /**
     * @comment
     */
    C3,

    /**
     * @rename
     */
    C4;
  }  
}
