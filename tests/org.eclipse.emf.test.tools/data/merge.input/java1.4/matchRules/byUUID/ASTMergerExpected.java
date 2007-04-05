import java.util.Date;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.examples.extlibrary.Item;
import java.util.List;

public class Example
{
  /**
   * Target 1
   * @uuid 002
   */
  protected int b1 = 1;  

  /**
   * Source 3
   */
  public int a3 = 3;

  /**
   * Target 2
   * @uuid 001
   */
  private int b2 = 2;
  
  /**
   * Target 3
   */
  public int b3 = 3;
  
  /**
   * Target 4
   * @uuid 005
   */
  int n4(String t4)
  {
    return t4.toLowerCase();
  }

  /**
   * Source 6
   * @uuid 006
   */
  private int m6(String s6)
  {
    return s6.toUpperCase();
  }

  /**
   * Target 5
   * @uuid 004
   */
  public int n5(String t5)
  {
    return t5.toLowerCase();
  }
  
  /**
   * Target 6
   * @uuid 010
   */
  public int n6(String t6)
  {
    return t6.toLowerCase();
  }
  
  /**
   * This is a source comment 1
   * <!-- begin-user-doc -->
   * This is a target user comment 2
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   * @uuid 0100
   */
  protected String targetString2 = new String("Source1"); //$NON-NLS$    

  /**
   * This is a source comment 2
   * <!-- begin-user-doc -->
   * This is a target user comment 1
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   * @uuid 0101
   */
  protected String targetString1 = new String("Source2"); //$NON-NLS$  

  /**
   * This is a source comment 3
   * <!-- begin-user-doc -->
   * This is a target user comment 3
   * <!-- end-user-doc -->
   * @generated
   * @uuid 0102
   */
  protected String targetString3 = new String("Target3"); //$NON-NLS$    
}