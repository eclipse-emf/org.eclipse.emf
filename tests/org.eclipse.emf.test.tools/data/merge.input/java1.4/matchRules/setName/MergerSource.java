/**
 * @name
 * @uuid 000
 */
public class Source
{
  /**
   * @name
   * @uuid 001
   */
  public int sourceField = 1;  

  /**
   * @name
   * @uuid 002
   */
  public String sourceMethod(float sourceF)
  {
    return Float.toString(sourceF);
  }  

  /**
   * @name
   * @uuid 003
   */
  public static class SourceInnerClass
  {
    /**
     * @name
     * @uuid 004
     */
    public int sourceInnerClassField = 1;  

    /**
     * @name
     * @uuid 005
     */
    public String sourceInnerClassMethod(float sourceF)
    {
      return Float.toString(sourceF);
    }    
  }
  
  /**
   * @name
   * @uuid 006
   */
  {
    System.out.println("Source Initializer"); 
  }

  /**
   * @name
   * @uuid 007
   */
  public String sourceMethod2(float sourceF2)
  {
    return Float.toString(sourceF2);
  }  
}
