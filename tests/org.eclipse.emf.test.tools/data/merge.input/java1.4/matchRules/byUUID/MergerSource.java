import java.util.Date;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.examples.extlibrary.Item;

public class Example
{
  /**
   * Source 1
   * @uuid 001
   */
  protected int a1 = 1;  

  /**
   * Source 2
   * @uuid 002
   */
  private int a2 = 2;
  
  /**
   * Source 3
   */
  public int a3 = 3;
  
  /**
   * Source 4
   * @uuid 004
   */
  int m4(String s4)
  {
    return s4.toUpperCase();
  }

  /**
   * Source 5
   * @uuid 005
   */
  public int m5(String s5)
  {
    return s5.toUpperCase();
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
   * This is a source comment 1
   * <!-- begin-user-doc -->
   * This is a source user comment 1
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   * @uuid 0100
   */
  protected String sourceString1 = new String("Source1"); //$NON-NLS$  
  
  /**
   * This is a source comment 2
   * <!-- begin-user-doc -->
   * This is a source user comment 2
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   * @uuid 0101
   */
  protected String sourceString2 = new String("Source2"); //$NON-NLS$ 
  
  /**
   * This is a source comment 3
   * <!-- begin-user-doc -->
   * This is a source user comment 3
   * <!-- end-user-doc -->
   * @generated
   * @uuid 0102
   */
  private String getSourceString1()
  {
    return "getSourceString1";
  }
}
