public class Example
{
  protected enum Enum1
  {
    /**
     *
     */
    C1
//    ,
//    /**
//     * @comment
//     */
//    C2,
//    /**
//     * @comment
//     */ 
//    C3
    ;
  }  
  
  protected enum Enum2
  {
//    /**
//     * @comment
//     */
//    C1,
//    /**
//     * @comment
//     */
//    C2,
    /**
     *
     */ 
    C3;
  }
  
  protected enum Enum3
  {
    /**
     *
     */
    C1
//    , // line comment C1
//    
//    /**
//     * @comment
//     */
//    C2,
     
//    // line comment C2
//    
//    /**
//     * @comment
//     */ 
//    C3
    ; // line comment C3
  }
  
  protected enum Enum4
  {
    /**
     *
     */
    C1 // line comment C1
//    ,
//    
//    /**
//     * @comment
//     */
//    C2 // line comment C2
//    ,
    
//    /**
//     * @comment
//     */ 
//    C3 // line comment C3
//    
    ;
  }
  
  protected enum Enum5
  {
    /**
     *
     */
    C1 // line comment C1
    
    // line comment C1
    
    /* block comment C1 */
//    , /* block comment C2 */ /**
//     * @comment
//     */
//    C2 // line comment C2
//    
//    // line comment C2
//    
//    /* block comment C2 */,
//    /* block comment C3*//**
//     * @comment
//     */ 
//    C3 /* block comment C3 */
//    // line comment C3
//    
    ;
  }  
  
  protected enum Enum6
  {
    C1,
    C2
//    , /**
//     * @comment
//     */ C3,
//     /**
//     * @comment
//     */ C4(","),
//     /**
//     * @comment
//     */ C5(";", ",")
     ;
  }
  
  protected enum Enum7
  {
//    /**
//     * @comment
//     */
//    C1,
    C2
//    , 
//    /**
//     * @comment
//     */ 
//    C3,
//    /**
//     * @comment
//     */ 
//    C4()
//    {
//      
//    }
//    ,
//    /**
//     * @comment
//     */ 
//    C5(";", ",")
//    {
//      
//    }
    ;
  }
  
  protected enum Enum8
  {
    /**
     *
     */
    C1
    
//    ,
//    
//    /**
//     * @comment
//     */
//    C2
//    
//    ,
    
//    /**
//     * @comment
//     */ 
//    C3
//    
//    
//    
    ;
  }  
  
  protected enum Enum9
  {
    /**
     *
     */
    C1
    
//    , /**
//     * @comment
//     */
//    C2
//    
//    ,
//    /**
//     * @comment
//     */ 
//    C3
//    
    ;
  }
  
  protected enum Enum10
  {
    C1,
//    /**
//     * @comment
//     */ C3,
     /**
     * @rename
     */ C4_Deleted(","),
//     /**
//     * @comment
//     */ C5(";", ","),
     /**
     * @rename
     */ C6_Deleted;}
}
