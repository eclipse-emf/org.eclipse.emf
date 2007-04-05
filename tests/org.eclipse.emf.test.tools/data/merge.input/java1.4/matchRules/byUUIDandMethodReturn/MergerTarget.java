public class Example
{
  /**
   * Target 2
   * @byid
   * @comment
   * @uuid 002
   */
  private int b2 = 200;
  
  /**
   * Target 1
   * @byid
   * @uuid 001
   * @body 
   */
  protected int b1 = 100;  

  /**
   * Target 3
   * @body
   */
  public float a3 = 300.5;

  /**
   * Target 4
   * @byid
   * @comment
   * @flags
   * @uuid 004
   */
  protected int b4 = 400;
  
  /**
   * Target 5
   * @byid
   * @comment
   * @flags
   * @body
   * @uuid 005
   */
  protected String toString(int targetI)
  {
    return Integer.toString(targetI); 
  }
  
  /**
   * Target 6
   * @byid
   * @uuid 006
   * @flags
   */
  private float b6 = 400.5;  

  /**
   * Target 7
   * @byid
   * @uuid 007
   * @comment
   * @flags
   */
  private float b7 = 500.5; 
  
  /**
   * Target 8
   * @byid
   * @uuid 008
   * @comment
   * @flags
   */
  private float a8 = 500.5;  
  
  /**
   * Target 9
   * @byid2
   * @flags
   * @body
   */
  StringBuffer targetToStringBuffer(int targetI)
  {
    return new StringBuffer(targetI);
  }  
  
  /**
   * Target 10
   * @byid2
   * @comment
   * @flags
   */
  private String targetToString(float targetF)
  {
    return Float.toString(targetF);
  }   
}
