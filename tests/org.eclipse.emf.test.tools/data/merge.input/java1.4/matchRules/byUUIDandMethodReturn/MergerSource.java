public class Example
{
  /**
   * Source 1
   * @byid
   * @uuid 001
   */
  protected int a1 = 1;  

  /**
   * Source 2
   * @comment
   * @uuid 002
   * @byid
   */
  private int a2 = 2;
  
  /**
   * Source 3
   * @body
   * @byid
   */
  public float a3 = 3.0;

  /**
   * Source 4
   * @comment
   * @flags
   * @uuid 004
   * @byid
   */
  private int a4 = 4;
  
  /**
   * Source 5
   * @byid
   * @uuid 005
   */
  public StringBuffer toStringBuffer(int sourceI)
  {
    // Comment from source
    return new StringBuffer(sourceI);
  }  
  
  /**
   * Source 6
   * @byid
   * @uuid 006
   */
  public String toString(int sourceI)
  {
    return Integer.toString(sourceI);
  }  

  /**
   * Source 7
   * @byid
   * @uuid 007
   */
  public String toString(float sourceF)
  {
    return Float.toString(sourceF);
  }  
  
  /**
   * Source 8
   * @uuid 008
   * @comment
   * @flags
   */
  private float a8 = 5.5;  
  
  /**
   * Source 9
   * @byid2
   */
  public String sourceToString(double sourceD)
  {
    return Double.toString(sourceD);
  }
  
  /**
   * Source 10
   * @byid2
   */
  public StringBuffer sourceToStringBuffer(int sourceI)
  {
    return new StringBuffer(sourceI);
  }  
}
