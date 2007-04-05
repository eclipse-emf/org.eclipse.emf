public class Example
{
  /**
   * Source 1
   * @modify
   * @uuid 001
   */
  public int a1 = 1;  

  /**
   * Source 2
   * @modify
   * @uuid 002
   */
  public int a2 = 2;
  
  /**
   * Source 3
   * @modify
   * @uuid 003
   */
  int a3 = 3;

  /**
   * Source 4
   * @modify
   * @uuid 004
   */
  StringBuffer sourceToStringBuffer(int sourceI)
  {
    return new StringBuffer(sourceI);//source
  }
  
  /**
   * Source 5
   * @modify
   * @uuid 005
   */
  public StringBuffer preValidateSource1(int sourcePreValidate1)
  {
    return new StringBuffer(sourcePreValidate1);//source
  }  

  /**
   * Source 6
   * @modify
   * @uuid 005
   */
  public StringBuffer postValidateSource1(int sourcePostValidate1)
  {
    return new StringBuffer(sourcePostValidate1);//source
  }  
}
