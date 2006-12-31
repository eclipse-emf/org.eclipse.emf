private enum Source1
{
  ;

  /** Javadoc 1_3 **/
  @Deprecated
  @Version("1.1"
// line comment
// line comment
  )
  public static transient volatile Type_1_3 field_modified_1_3 = 
    new Object() {
      // line comment 1_3
      // line comment
    };
}

public class Source
{
  /**
   * Javadoc for field2
   * Another line of javadoc
   */    
  @Version("1.2"
// line comment
// line comment
  )
  @Deprecated
  protected final T field2;

  /**
   * Javadoc for field1
   * Another line of javadoc
   */
  @Version("1.1"
// line comment
// line comment
  )
  @Deprecated
  public static transient volatile List< ? extends Serializable > field1 = 
    new Object() {
      // line comment
      // line comment
    };
}
