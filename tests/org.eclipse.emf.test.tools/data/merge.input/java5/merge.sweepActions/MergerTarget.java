public class Example
{
  int a1 = 1;
  
  /**
   * @remove
   */
  int a2 = 2;

  /**
   * @comment
   */
  int a3 = 3;

  /**
   * @rename
   */
  int a4 = 4;
  
  public void method1()
  {
    
  }

  /**
   * @remove
   */
  public void method2()
  {
    
  }

  /**
   * @comment
   */
  public void method3()
  {
    
  }

  /**
   * @rename
   */
  public void method4()
  {
    
  }
  
  public class Inner1
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

  /**
   * @remove
   */
  public class Inner2
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
  
  /**
   * @comment
   */
  public class Inner3
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
  
  /**
   * @rename
   */
  public class Inner4
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
  
  /**
   * @remove
   */
  protected enum Enum2
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

  /**
   * @comment
   */
  protected enum Enum3
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
  
  /**
   * @rename
   */
  protected enum Enum4
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
