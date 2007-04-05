public class Example {
  /**
   * Source 2
   * @comment
   * @uuid 002
   * @byid
   */
  private int b2 = 200;
  
  /**
   * Target 1
   * @byid
   * @uuid 001
   * @body 
   */
  protected int b1 = 1;  

  /**
   * Target 3
   * @body
   */
  public float a3 = 3.0;

  /**
   * Source 4
   * @comment
   * @flags
   * @uuid 004
   * @byid
   */
  private int b4 = 400;
  
  /**
   * Source 5
   * @byid
   * @uuid 005
   */
  public String toString(int sourceI)
  {
    // Comment from source
    return new StringBuffer(sourceI);
  }  
  
  /**
   * Target 6
   * @byid
   * @uuid 006
   * @flags
   */
  public float b6 = 400.5;  

  /**
   * Source 7
   * @byid
   * @uuid 007
   */
  public float b7 = 500.5; 
  
  /**
   * Source 8
   * @uuid 008
   * @comment
   * @flags
   */
  private float a8 = 5.5;  
  
  /**
   * Target 8
   * @byid
   * @uuid 008
   * @comment
   * @flags
   */
  private float a8_DELETED = 500.5;  
  
  /**
   * Target 9
   * @byid2
   * @flags
   * @body
   */
  public StringBuffer targetToStringBuffer(int sourceI)
  {
    return new StringBuffer(sourceI);
  }  
  /**
   * Source 9
   * @byid2
   */
  public String targetToString(float sourceD)
  {
    return Float.toString(targetF);
  }   
}
