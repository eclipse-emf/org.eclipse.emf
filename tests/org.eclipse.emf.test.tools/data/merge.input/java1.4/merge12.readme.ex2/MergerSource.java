import java.util.Date;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.examples.extlibrary.Item;

/**
 * This test shows handling of comments by AST Facade implementation
 * when nodes are moved or removed.
 * <p>
 * This is Example 2 in document "Changes to the EMF Code Merge" (http://www.eclipse.org/emf/docs/2.x/whatsnew/merge2.3.html)  
 */
public abstract class BookImpl extends EObjectImpl implements Book
{
  /**
   * @generated
   * @ordered
   */
  protected static final Date PUBLICATION_DATE_EDEFAULT = null;

  /**
   * @generated
   * @ordered
   */
  protected Date publicationDate = PUBLICATION_DATE_EDEFAULT;
  
  /**
   * @generated
   * @ordered
   */
  protected static final String AUTHOR_EDEFAULT = null;

  /**
   * @generated
   * @ordered
   */
  protected String author = AUTHOR_EDEFAULT;  
  
  /**
   * @generated
   * @ordered
   */
  protected static final String TITLE_EDEFAULT = null;

  /**
   * @generated
   * @ordered
   */
  protected String title = TITLE_EDEFAULT;
}
