import java.util.Date;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.examples.extlibrary.Item;

/**
 * This test shows merging of comments by AST Facade implementation.
 * <p>
 * This is Example 1 in document "Changes to the EMF Code Merge" (http://www.eclipse.org/emf/docs/2.x/whatsnew/merge2.3.html)  
 */
public abstract class BookImpl extends EObjectImpl implements Book
{
  /**
   * The default value of the '{@link #getPublicationDate() <em>Publication Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final Date PUBLICATION_DATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPublicationDate() <em>Publication Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected Date publicationDate = PUBLICATION_DATE_EDEFAULT;
}
