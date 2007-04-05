public class Example
{
  /**
   * Target 1
   * @modify
   * @uuid 001
   */
  private long b1 = 10l;  

  /**
   * Target 2
   * @modify
   */
  private long a2 = 20l;
  
  /**
   * Source 3
   * @modify
   * @uuid 003
   */
  long a3 = 30l;

  /**
   * Target 4
   * @modify
   * @uuid 005
   */
  private StringBuffer postValidateTarget1(int targetPostValidate1)
  {
    return new StringBuffer(targetPostValidate1);
  }  

  /**
   * Target 5
   * @modify
   * @uuid 004
   */
  private StringBuffer targetToStringBuffer(int targetI)
  {
    return new StringBuffer(targetI);
  }
  
  /**
   * Target 6
   * @modify
   * @uuid 005
   */
  private StringBuffer preValidateTarget1(int targetPreValidate1)
  {
    return new StringBuffer(targetPreValidate1);
  }  
}