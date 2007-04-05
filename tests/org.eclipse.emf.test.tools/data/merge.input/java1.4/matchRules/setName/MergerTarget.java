/**
 * @name
 * @uuid 000
 */
protected class Target
{
  /**
   * @name
   * @uuid 001
   */
  protected int targetField = 10;  

  /**
   * @name
   * @uuid 002
   */
  protected String targetMethod(float targetF)
  {
    return Float.toString(targetF);
  }  

  /**
   * @name
   * @uuid 003
   */
  protected static class TargetInnerClass
  {
    /**
     * @name
     * @uuid 004
     */
    protected int targetInnerClassField = 10;  

    /**
     * @name
     * @uuid 005
     */
    protected String targetInnerClassMethod(float targetF)
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
  protected String targetMethod2(float targetF2)
  {
    return Float.toString(targetF2);
  }  
}