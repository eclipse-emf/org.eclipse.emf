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
  int Deleted_a4 = 4;
//  /**
//   * @comment
//   */ int a5;
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
  public void Deleted_method4()
  {
    
  }
  
  public class Inner1
  {
//  /** @comment
//    */
//    int a1; 

//    /**
//     * @comment
//     */
//    int a3;
//    /**
//     * @comment
//     */
//    int a4;
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
    Deleted_C4(",")
//    ,/**
//     * @comment
//     */ C5(";", ",")
     ;}    
  
  /**
   * @commentAll
   */
  protected enum Enum2
  { 
//  C1,
   
//  C2,
   
//  C3,
   
//  C4(","),
   
//  C5(";", ",")
  ;}    
  
  /**
   * @commentAll
   */  
  protected enum Enum3
  {
//    C1,
     
//    // line comment after C1
//    C2,
     
//    // line comment after C2
//    C3("") {
//      // anonymous class declaration
//    } /* block comment after C3 before comma */ ,
     
//    // line comment after C3
//    C4,
     
//    C5,
//    C6 /* block comment after C6 before comma */,
//    C7() // line coment after C7
//    ,
     
//    // line comment after comma after C7
//    C8,
     
//    C9,
//    C10 /* block comment after C10 before comma */,
     
//    C11/* block comment after C11 */,
//    C12 /* block comment after C12 before comma */,
//    C13/* block comment after C13 */
    ;
//    private int a1 = 1;
     
//    int a2;
     
//    private void method1() {} // line comment after method 1
  }

  /**
   * @commentAll
   */  
  protected enum Enum4
  {;
//  private int a1 = 1;
   
//  int a2;
   
//  private void method1() {} // line comment after method 1
  }  
}
