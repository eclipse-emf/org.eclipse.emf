/**
 * @name
 * @uuid 000
 */
protected class Source
{
  /**
   * @name
   * @uuid 001
   */
  protected int sourceField = 10;  

  /**
   * @name
   * @uuid 002
   */
  protected String sourceMethod(float targetF)
  {
    return Float.toString(targetF);
  }  

  /**
   * @name
   * @uuid 003
   */
  protected static class SourceInnerClass
  {
    /**
     * @name
     * @uuid 004
     */
    protected int sourceInnerClassField = 10;  

    /**
     * @name
     * @uuid 005
     */
    protected String sourceInnerClassMethod(float targetF)
    {
      return Float.toString(targetF);
    }    
  }
  
  /**
   * @name
   * @uuid 006
   */
  {
    System.out.println("Target Initializer"); 
  }

  /**
   * @name
   * @uuid 007
   */
  protected String sourceMethod2(float targetF2)
  {
    return Float.toString(targetF2);
  }  
}