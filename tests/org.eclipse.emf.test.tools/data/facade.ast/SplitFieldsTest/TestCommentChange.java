public class Source
{
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
  
  /** Javadoc for split1 **/
  @Version("1.1"
// line comment
// line comment
  )
  @Deprecated
  protected final T<?> split1;

  /**
   * Javadoc for split1 and split2
   * Another line of javadoc
   */    
  @Version("1.1"
// line comment
// line comment
  )
  @Deprecated
  protected final T< ? > split2 = "" +
    // line comment in initializer
    "";
}

private enum Source1
{
}
