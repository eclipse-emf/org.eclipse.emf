public class Example
{
  /**
   * Source 1
   * @modify
   * @uuid 001
   */
  public int a1 = 1;

  /**
   * Target 1
   * @modify
   * @uuid 001
   */
  private long b1 = 10l;  

  /**
   * Source 2
   * @modify
   * @uuid 002
   */
  public int a2 = 20l;
  
  /**
   * Source 3
   * @modify
   * @uuid 003
   */
  int a3 = 30l;

  /**
   * Source 6
   * @modify
   * @uuid 005
   */
  public StringBuffer postValidateTarget1(int sourcePostValidate1)
  {
    return new StringBuffer(sourcePostValidate1);//source
  }  

  /**
   * Source 4
   * @modify
   * @uuid 004
   */
  StringBuffer targetToStringBuffer(int sourceI)
  {
    return new StringBuffer(sourceI);//source
  }
  
  /**
   * Source 5
   * @modify
   * @uuid 005
   */
  public StringBuffer preValidateTarget1(int sourcePreValidate1)
  {
    return new StringBuffer(sourcePreValidate1);//source
  }  
}