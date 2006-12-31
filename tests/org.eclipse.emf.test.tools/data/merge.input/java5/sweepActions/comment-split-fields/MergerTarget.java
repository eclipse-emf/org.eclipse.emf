class A
{
  /**
   * @comment
   */
  protected int a = 1, b = 2;

  /**
   * @comment
   */
  @Annotation( /* comment */ )
  protected int c = 1, d = 2, e = 3;
}

class B
{
  /**
   * @comment
   */
  protected int a = 1, b = 2;
}

/**
 * This class should be commented out
 * @comment
 */
class C
{
  /**
   * @comment
   */
  protected int a = 1, b = 2;
  
  /**
   * @comment
   */
  @Annotation( /* comment */ )
  protected int c = 1, d = 2, e = 3;  
}