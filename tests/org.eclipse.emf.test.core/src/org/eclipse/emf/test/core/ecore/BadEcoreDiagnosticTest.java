package org.eclipse.emf.test.core.ecore;

import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;

public class BadEcoreDiagnosticTest extends TestCase
{
  protected Diagnostic diagnostic;
  
  public BadEcoreDiagnosticTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("DianosticTest");
    ts.addTest(new BadEcoreDiagnosticTest("testDiagnostic"));
    return ts;
  }
  
  @Override
  protected void setUp() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    URI logicalURI = URI.createPlatformPluginURI("platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore", false);
    URI physicalURI = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.core") + "/data/Bad.ecore");
    resourceSet.getURIConverter().getURIMap().put(logicalURI, physicalURI);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource ecoreResource = resourceSet.getResource(logicalURI, true);
    EPackage badPackage = (EPackage)ecoreResource.getContents().get(0);
    
    diagnostic =  Diagnostician.INSTANCE.validate(badPackage);
    
    assertNotNull(diagnostic);
  }
  
  public void testDiagnostic() throws Exception
  {
    TreeIterator<Diagnostic> diagnosticIterator = new AbstractTreeIterator<Diagnostic>(diagnostic)
    {
      private static final long serialVersionUID = 1L;
      @Override
      protected Iterator<? extends Diagnostic> getChildren(Object object)
      {
        return ((Diagnostic)object).getChildren().iterator();
      }
    };

  
    Diagnostic diagnostic1 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic1.getSeverity());
    assertEquals("org.eclipse.emf.ecore", diagnostic1.getSource());
    assertEquals("Diagnosis of org.eclipse.emf.ecore.impl.EPackageImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#/}", removeObjectHashCode(diagnostic1.getMessage()));
    assertEquals(0, diagnostic1.getCode());
    assertEquals(111, diagnostic1.getChildren().size());
    assertEquals(1, diagnostic1.getData().size());
    assertNull(diagnostic1.getException());

    Diagnostic diagnostic2 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic2.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic2.getSource());
    assertEquals("The source URI '' is not well formed", removeObjectHashCode(diagnostic2.getMessage()));
    assertEquals(47, diagnostic2.getCode());
    assertEquals(0, diagnostic2.getChildren().size());
    assertEquals(1, diagnostic2.getData().size());
    assertNull(diagnostic2.getException());

    Diagnostic diagnostic3 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic3.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic3.getSource());
    assertEquals("The source URI 'A source URI with spaces is not well formed' is not well formed", removeObjectHashCode(diagnostic3.getMessage()));
    assertEquals(47, diagnostic3.getCode());
    assertEquals(0, diagnostic3.getChildren().size());
    assertEquals(1, diagnostic3.getData().size());
    assertNull(diagnostic3.getException());

    Diagnostic diagnostic4 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic4.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic4.getSource());
    assertEquals("The source URI 'AnUnencoded%IsNotWellFormed' is not well formed", removeObjectHashCode(diagnostic4.getMessage()));
    assertEquals(47, diagnostic4.getCode());
    assertEquals(0, diagnostic4.getChildren().size());
    assertEquals(1, diagnostic4.getData().size());
    assertNull(diagnostic4.getException());

    Diagnostic diagnostic5 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic5.getSeverity());
    assertEquals("org.eclipse.emf.ecore", diagnostic5.getSource());
    assertEquals("The feature 'details' has a map entry at index 1 with a key that collides with that of the map entry at index 0", removeObjectHashCode(diagnostic5.getMessage()));
    assertEquals(14, diagnostic5.getCode());
    assertEquals(0, diagnostic5.getChildren().size());
    assertEquals(4, diagnostic5.getData().size());
    assertNull(diagnostic5.getException());

    Diagnostic diagnostic6 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic6.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic6.getSource());
    assertEquals("The name 'null' is not well formed", removeObjectHashCode(diagnostic6.getMessage()));
    assertEquals(44, diagnostic6.getCode());
    assertEquals(0, diagnostic6.getChildren().size());
    assertEquals(1, diagnostic6.getData().size());
    assertNull(diagnostic6.getException());

    Diagnostic diagnostic7 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic7.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic7.getSource());
    assertEquals("The name 'invalid.dot.qualified.name' is not well formed", removeObjectHashCode(diagnostic7.getMessage()));
    assertEquals(44, diagnostic7.getCode());
    assertEquals(0, diagnostic7.getChildren().size());
    assertEquals(1, diagnostic7.getData().size());
    assertNull(diagnostic7.getException());

    Diagnostic diagnostic8 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic8.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic8.getSource());
    assertEquals("The name '1' is not well formed", removeObjectHashCode(diagnostic8.getMessage()));
    assertEquals(44, diagnostic8.getCode());
    assertEquals(0, diagnostic8.getChildren().size());
    assertEquals(1, diagnostic8.getData().size());
    assertNull(diagnostic8.getException());

    Diagnostic diagnostic9 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic9.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic9.getSource());
    assertEquals("The name 'name with spaces' is not well formed", removeObjectHashCode(diagnostic9.getMessage()));
    assertEquals(44, diagnostic9.getCode());
    assertEquals(0, diagnostic9.getChildren().size());
    assertEquals(1, diagnostic9.getData().size());
    assertNull(diagnostic9.getException());

    Diagnostic diagnostic10 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic10.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic10.getSource());
    assertEquals("The instance type name '' is not well formed", removeObjectHashCode(diagnostic10.getMessage()));
    assertEquals(42, diagnostic10.getCode());
    assertEquals(1, diagnostic10.getChildren().size());
    assertEquals(1, diagnostic10.getData().size());
    assertNull(diagnostic10.getException());

    Diagnostic diagnostic11 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic11.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic11.getSource());
    assertEquals("Expecting an identifier at index 0", removeObjectHashCode(diagnostic11.getMessage()));
    assertEquals(42, diagnostic11.getCode());
    assertEquals(0, diagnostic11.getChildren().size());
    assertEquals(1, diagnostic11.getData().size());
    assertNull(diagnostic11.getException());

    Diagnostic diagnostic12 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic12.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic12.getSource());
    assertEquals("The instance type name 'T[' is not well formed", removeObjectHashCode(diagnostic12.getMessage()));
    assertEquals(42, diagnostic12.getCode());
    assertEquals(1, diagnostic12.getChildren().size());
    assertEquals(1, diagnostic12.getData().size());
    assertNull(diagnostic12.getException());

    Diagnostic diagnostic13 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic13.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic13.getSource());
    assertEquals("A ']' is expected at index 2", removeObjectHashCode(diagnostic13.getMessage()));
    assertEquals(42, diagnostic13.getCode());
    assertEquals(0, diagnostic13.getChildren().size());
    assertEquals(1, diagnostic13.getData().size());
    assertNull(diagnostic13.getException());

    Diagnostic diagnostic14 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic14.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic14.getSource());
    assertEquals("The instance type name 'T<>' is not well formed", removeObjectHashCode(diagnostic14.getMessage()));
    assertEquals(42, diagnostic14.getCode());
    assertEquals(1, diagnostic14.getChildren().size());
    assertEquals(1, diagnostic14.getData().size());
    assertNull(diagnostic14.getException());

    Diagnostic diagnostic15 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic15.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic15.getSource());
    assertEquals("A type argument is expected at index 2", removeObjectHashCode(diagnostic15.getMessage()));
    assertEquals(42, diagnostic15.getCode());
    assertEquals(0, diagnostic15.getChildren().size());
    assertEquals(1, diagnostic15.getData().size());
    assertNull(diagnostic15.getException());

    Diagnostic diagnostic16 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic16.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic16.getSource());
    assertEquals("The instance type name 'T<' is not well formed", removeObjectHashCode(diagnostic16.getMessage()));
    assertEquals(42, diagnostic16.getCode());
    assertEquals(1, diagnostic16.getChildren().size());
    assertEquals(1, diagnostic16.getData().size());
    assertNull(diagnostic16.getException());

    Diagnostic diagnostic17 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic17.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic17.getSource());
    assertEquals("The '<' at index 1 must be terminated before the end of the string", removeObjectHashCode(diagnostic17.getMessage()));
    assertEquals(42, diagnostic17.getCode());
    assertEquals(0, diagnostic17.getChildren().size());
    assertEquals(1, diagnostic17.getData().size());
    assertNull(diagnostic17.getException());

    Diagnostic diagnostic18 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic18.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic18.getSource());
    assertEquals("The instance type name '.name' is not well formed", removeObjectHashCode(diagnostic18.getMessage()));
    assertEquals(42, diagnostic18.getCode());
    assertEquals(1, diagnostic18.getChildren().size());
    assertEquals(1, diagnostic18.getData().size());
    assertNull(diagnostic18.getException());

    Diagnostic diagnostic19 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic19.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic19.getSource());
    assertEquals("The '.' at index 0 must be preceded by an identifier", removeObjectHashCode(diagnostic19.getMessage()));
    assertEquals(42, diagnostic19.getCode());
    assertEquals(0, diagnostic19.getChildren().size());
    assertEquals(1, diagnostic19.getData().size());
    assertNull(diagnostic19.getException());

    Diagnostic diagnostic20 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic20.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic20.getSource());
    assertEquals("The instance type name 'name..name' is not well formed", removeObjectHashCode(diagnostic20.getMessage()));
    assertEquals(42, diagnostic20.getCode());
    assertEquals(1, diagnostic20.getChildren().size());
    assertEquals(1, diagnostic20.getData().size());
    assertNull(diagnostic20.getException());

    Diagnostic diagnostic21 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic21.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic21.getSource());
    assertEquals("The '.' at index 5 must be preceded by an identifier", removeObjectHashCode(diagnostic21.getMessage()));
    assertEquals(42, diagnostic21.getCode());
    assertEquals(0, diagnostic21.getChildren().size());
    assertEquals(1, diagnostic21.getData().size());
    assertNull(diagnostic21.getException());

    Diagnostic diagnostic22 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic22.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic22.getSource());
    assertEquals("The instance type name 'name.' is not well formed", removeObjectHashCode(diagnostic22.getMessage()));
    assertEquals(42, diagnostic22.getCode());
    assertEquals(1, diagnostic22.getChildren().size());
    assertEquals(1, diagnostic22.getData().size());
    assertNull(diagnostic22.getException());

    Diagnostic diagnostic23 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic23.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic23.getSource());
    assertEquals("Expecting an identifier at index 5", removeObjectHashCode(diagnostic23.getMessage()));
    assertEquals(42, diagnostic23.getCode());
    assertEquals(0, diagnostic23.getChildren().size());
    assertEquals(1, diagnostic23.getData().size());
    assertNull(diagnostic23.getException());

    Diagnostic diagnostic24 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic24.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic24.getSource());
    assertEquals("The instance type name 'T[]<X>' is not well formed", removeObjectHashCode(diagnostic24.getMessage()));
    assertEquals(42, diagnostic24.getCode());
    assertEquals(1, diagnostic24.getChildren().size());
    assertEquals(1, diagnostic24.getData().size());
    assertNull(diagnostic24.getException());

    Diagnostic diagnostic25 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic25.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic25.getSource());
    assertEquals("A '[' is expected at index 3 not '<'", removeObjectHashCode(diagnostic25.getMessage()));
    assertEquals(42, diagnostic25.getCode());
    assertEquals(0, diagnostic25.getChildren().size());
    assertEquals(1, diagnostic25.getData().size());
    assertNull(diagnostic25.getException());

    Diagnostic diagnostic26 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic26.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic26.getSource());
    assertEquals("The instance type name 'T<X Y>' is not well formed", removeObjectHashCode(diagnostic26.getMessage()));
    assertEquals(42, diagnostic26.getCode());
    assertEquals(1, diagnostic26.getChildren().size());
    assertEquals(1, diagnostic26.getData().size());
    assertNull(diagnostic26.getException());

    Diagnostic diagnostic27 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic27.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic27.getSource());
    assertEquals("A '.' is expected before the start of another identifier at index 4 ", removeObjectHashCode(diagnostic27.getMessage()));
    assertEquals(42, diagnostic27.getCode());
    assertEquals(0, diagnostic27.getChildren().size());
    assertEquals(1, diagnostic27.getData().size());
    assertNull(diagnostic27.getException());

    Diagnostic diagnostic28 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic28.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic28.getSource());
    assertEquals("The instance type name 'T <X>' is not well formed", removeObjectHashCode(diagnostic28.getMessage()));
    assertEquals(42, diagnostic28.getCode());
    assertEquals(1, diagnostic28.getChildren().size());
    assertEquals(1, diagnostic28.getData().size());
    assertNull(diagnostic28.getException());

    Diagnostic diagnostic29 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic29.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic29.getSource());
    assertEquals("Unexpected ' ' at index 1", removeObjectHashCode(diagnostic29.getMessage()));
    assertEquals(42, diagnostic29.getCode());
    assertEquals(0, diagnostic29.getChildren().size());
    assertEquals(1, diagnostic29.getData().size());
    assertNull(diagnostic29.getException());

    Diagnostic diagnostic30 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic30.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic30.getSource());
    assertEquals("The instance type name 'T<?extends X>' is not well formed", removeObjectHashCode(diagnostic30.getMessage()));
    assertEquals(42, diagnostic30.getCode());
    assertEquals(1, diagnostic30.getChildren().size());
    assertEquals(1, diagnostic30.getData().size());
    assertNull(diagnostic30.getException());

    Diagnostic diagnostic31 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic31.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic31.getSource());
    assertEquals("Expecting ' ' at index 3", removeObjectHashCode(diagnostic31.getMessage()));
    assertEquals(42, diagnostic31.getCode());
    assertEquals(0, diagnostic31.getChildren().size());
    assertEquals(1, diagnostic31.getData().size());
    assertNull(diagnostic31.getException());

    Diagnostic diagnostic32 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic32.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic32.getSource());
    assertEquals("The instance type name 'T ' is not well formed", removeObjectHashCode(diagnostic32.getMessage()));
    assertEquals(42, diagnostic32.getCode());
    assertEquals(1, diagnostic32.getChildren().size());
    assertEquals(1, diagnostic32.getData().size());
    assertNull(diagnostic32.getException());

    Diagnostic diagnostic33 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic33.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic33.getSource());
    assertEquals("Unexpected ' ' at index 1", removeObjectHashCode(diagnostic33.getMessage()));
    assertEquals(42, diagnostic33.getCode());
    assertEquals(0, diagnostic33.getChildren().size());
    assertEquals(1, diagnostic33.getData().size());
    assertNull(diagnostic33.getException());

    Diagnostic diagnostic34 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic34.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic34.getSource());
    assertEquals("A class that is an interface must also be abstract", removeObjectHashCode(diagnostic34.getMessage()));
    assertEquals(25, diagnostic34.getCode());
    assertEquals(0, diagnostic34.getChildren().size());
    assertEquals(1, diagnostic34.getData().size());
    assertNull(diagnostic34.getException());

    Diagnostic diagnostic35 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic35.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic35.getSource());
    assertEquals("The features 'a' and 'b' cannot both be IDs", removeObjectHashCode(diagnostic35.getMessage()));
    assertEquals(1, diagnostic35.getCode());
    assertEquals(0, diagnostic35.getChildren().size());
    assertEquals(3, diagnostic35.getData().size());
    assertNull(diagnostic35.getException());

    Diagnostic diagnostic36 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic36.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic36.getSource());
    assertEquals("The features 'a' and 'b' cannot both be IDs", removeObjectHashCode(diagnostic36.getMessage()));
    assertEquals(1, diagnostic36.getCode());
    assertEquals(0, diagnostic36.getChildren().size());
    assertEquals(3, diagnostic36.getData().size());
    assertNull(diagnostic36.getException());

    Diagnostic diagnostic37 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic37.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic37.getSource());
    assertEquals("There may not be two features named 'a'", removeObjectHashCode(diagnostic37.getMessage()));
    assertEquals(32, diagnostic37.getCode());
    assertEquals(0, diagnostic37.getChildren().size());
    assertEquals(3, diagnostic37.getData().size());
    assertNull(diagnostic37.getException());

    Diagnostic diagnostic38 = diagnosticIterator.next();
    assertEquals(Diagnostic.WARNING, diagnostic38.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic38.getSource());
    assertEquals("There should not be a feature named 'A' as well a feature named 'a'", removeObjectHashCode(diagnostic38.getMessage()));
    assertEquals(32, diagnostic38.getCode());
    assertEquals(0, diagnostic38.getChildren().size());
    assertEquals(3, diagnostic38.getData().size());
    assertNull(diagnostic38.getException());

    Diagnostic diagnostic39 = diagnosticIterator.next();
    assertEquals(Diagnostic.WARNING, diagnostic39.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic39.getSource());
    assertEquals("There should not be a feature named 'A_' as well a feature named 'a'", removeObjectHashCode(diagnostic39.getMessage()));
    assertEquals(32, diagnostic39.getCode());
    assertEquals(0, diagnostic39.getChildren().size());
    assertEquals(3, diagnostic39.getData().size());
    assertNull(diagnostic39.getException());

    Diagnostic diagnostic40 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic40.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic40.getSource());
    assertEquals("There may not be two operations 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/TwoOperationsWithMatchingSignatures/f.1}' and 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/TwoOperationsWithMatchingSignatures/f}' with the same signature", removeObjectHashCode(diagnostic40.getMessage()));
    assertEquals(34, diagnostic40.getCode());
    assertEquals(0, diagnostic40.getChildren().size());
    assertEquals(3, diagnostic40.getData().size());
    assertNull(diagnostic40.getException());

    Diagnostic diagnostic41 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic41.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic41.getSource());
    assertEquals("A class may not be a super type of itself", removeObjectHashCode(diagnostic41.getMessage()));
    assertEquals(26, diagnostic41.getCode());
    assertEquals(0, diagnostic41.getChildren().size());
    assertEquals(1, diagnostic41.getData().size());
    assertNull(diagnostic41.getException());

    Diagnostic diagnostic42 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic42.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic42.getSource());
    assertEquals("A class may not be a super type of itself", removeObjectHashCode(diagnostic42.getMessage()));
    assertEquals(26, diagnostic42.getCode());
    assertEquals(0, diagnostic42.getChildren().size());
    assertEquals(1, diagnostic42.getData().size());
    assertNull(diagnostic42.getException());

    Diagnostic diagnostic43 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic43.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic43.getSource());
    assertEquals("A class may not be a super type of itself", removeObjectHashCode(diagnostic43.getMessage()));
    assertEquals(26, diagnostic43.getCode());
    assertEquals(0, diagnostic43.getChildren().size());
    assertEquals(1, diagnostic43.getData().size());
    assertNull(diagnostic43.getException());

    Diagnostic diagnostic44 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic44.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic44.getSource());
    assertEquals("A map entry class must have a feature called 'key'", removeObjectHashCode(diagnostic44.getMessage()));
    assertEquals(43, diagnostic44.getCode());
    assertEquals(0, diagnostic44.getChildren().size());
    assertEquals(1, diagnostic44.getData().size());
    assertNull(diagnostic44.getException());

    Diagnostic diagnostic45 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic45.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic45.getSource());
    assertEquals("A map entry class must have a feature called 'value'", removeObjectHashCode(diagnostic45.getMessage()));
    assertEquals(43, diagnostic45.getCode());
    assertEquals(0, diagnostic45.getChildren().size());
    assertEquals(1, diagnostic45.getData().size());
    assertNull(diagnostic45.getException());

    Diagnostic diagnostic46 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic46.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic46.getSource());
    assertEquals("There may not be an operation 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/isA}' with the same signature as an accessor method for feature 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/a}'", removeObjectHashCode(diagnostic46.getMessage()));
    assertEquals(48, diagnostic46.getCode());
    assertEquals(0, diagnostic46.getChildren().size());
    assertEquals(3, diagnostic46.getData().size());
    assertNull(diagnostic46.getException());

    Diagnostic diagnostic47 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic47.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic47.getSource());
    assertEquals("There may not be an operation 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/isSetA}' with the same signature as an accessor method for feature 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/a}'", removeObjectHashCode(diagnostic47.getMessage()));
    assertEquals(48, diagnostic47.getCode());
    assertEquals(0, diagnostic47.getChildren().size());
    assertEquals(3, diagnostic47.getData().size());
    assertNull(diagnostic47.getException());

    Diagnostic diagnostic48 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic48.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic48.getSource());
    assertEquals("There may not be an operation 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/setA}' with the same signature as an accessor method for feature 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/a}'", removeObjectHashCode(diagnostic48.getMessage()));
    assertEquals(48, diagnostic48.getCode());
    assertEquals(0, diagnostic48.getChildren().size());
    assertEquals(3, diagnostic48.getData().size());
    assertNull(diagnostic48.getException());

    Diagnostic diagnostic49 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic49.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic49.getSource());
    assertEquals("There may not be an operation 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/unsetA}' with the same signature as an accessor method for feature 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/a}'", removeObjectHashCode(diagnostic49.getMessage()));
    assertEquals(48, diagnostic49.getCode());
    assertEquals(0, diagnostic49.getChildren().size());
    assertEquals(3, diagnostic49.getData().size());
    assertNull(diagnostic49.getException());

    Diagnostic diagnostic50 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic50.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic50.getSource());
    assertEquals("There may not be an operation 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/getB}' with the same signature as an accessor method for feature 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/b}'", removeObjectHashCode(diagnostic50.getMessage()));
    assertEquals(48, diagnostic50.getCode());
    assertEquals(0, diagnostic50.getChildren().size());
    assertEquals(3, diagnostic50.getData().size());
    assertNull(diagnostic50.getException());

    Diagnostic diagnostic51 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic51.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic51.getSource());
    assertEquals("There may not be an operation 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/isSetB}' with the same signature as an accessor method for feature 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/b}'", removeObjectHashCode(diagnostic51.getMessage()));
    assertEquals(48, diagnostic51.getCode());
    assertEquals(0, diagnostic51.getChildren().size());
    assertEquals(3, diagnostic51.getData().size());
    assertNull(diagnostic51.getException());

    Diagnostic diagnostic52 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic52.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic52.getSource());
    assertEquals("There may not be an operation 'org.eclipse.emf.ecore.impl.EOperationImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/unsetB}' with the same signature as an accessor method for feature 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//class/ClassWithCollidingFeatureAndOperationSignatures/b}'", removeObjectHashCode(diagnostic52.getMessage()));
    assertEquals(48, diagnostic52.getCode());
    assertEquals(0, diagnostic52.getChildren().size());
    assertEquals(3, diagnostic52.getData().size());
    assertNull(diagnostic52.getException());

    Diagnostic diagnostic53 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic53.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic53.getSource());
    assertEquals("A class that inherits from a map entry class must have instance class name 'java.util.Map$Entry'", removeObjectHashCode(diagnostic53.getMessage()));
    assertEquals(49, diagnostic53.getCode());
    assertEquals(0, diagnostic53.getChildren().size());
    assertEquals(1, diagnostic53.getData().size());
    assertNull(diagnostic53.getException());

    Diagnostic diagnostic54 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic54.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic54.getSource());
    assertEquals("There may not be two enumerators named 'A'", removeObjectHashCode(diagnostic54.getMessage()));
    assertEquals(31, diagnostic54.getCode());
    assertEquals(0, diagnostic54.getChildren().size());
    assertEquals(3, diagnostic54.getData().size());
    assertNull(diagnostic54.getException());

    Diagnostic diagnostic55 = diagnosticIterator.next();
    assertEquals(Diagnostic.WARNING, diagnostic55.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic55.getSource());
    assertEquals("There should not be an enumerator named 'A' as well an enumerator named 'a'", removeObjectHashCode(diagnostic55.getMessage()));
    assertEquals(31, diagnostic55.getCode());
    assertEquals(0, diagnostic55.getChildren().size());
    assertEquals(3, diagnostic55.getData().size());
    assertNull(diagnostic55.getException());

    Diagnostic diagnostic56 = diagnosticIterator.next();
    assertEquals(Diagnostic.WARNING, diagnostic56.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic56.getSource());
    assertEquals("There should not be an enumerator named 'A_' as well an enumerator named 'a'", removeObjectHashCode(diagnostic56.getMessage()));
    assertEquals(31, diagnostic56.getCode());
    assertEquals(0, diagnostic56.getChildren().size());
    assertEquals(3, diagnostic56.getData().size());
    assertNull(diagnostic56.getException());

    Diagnostic diagnostic57 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic57.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic57.getSource());
    assertEquals("There may not be two enumerators with literal value 'x'", removeObjectHashCode(diagnostic57.getMessage()));
    assertEquals(30, diagnostic57.getCode());
    assertEquals(0, diagnostic57.getChildren().size());
    assertEquals(3, diagnostic57.getData().size());
    assertNull(diagnostic57.getException());

    Diagnostic diagnostic58 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic58.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic58.getSource());
    assertEquals("There may not be two parameters named 'x'", removeObjectHashCode(diagnostic58.getMessage()));
    assertEquals(35, diagnostic58.getCode());
    assertEquals(0, diagnostic58.getChildren().size());
    assertEquals(3, diagnostic58.getData().size());
    assertNull(diagnostic58.getException());

    Diagnostic diagnostic59 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic59.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic59.getSource());
    assertEquals("There may not be two type parameters named 'T'", removeObjectHashCode(diagnostic59.getMessage()));
    assertEquals(37, diagnostic59.getCode());
    assertEquals(0, diagnostic59.getChildren().size());
    assertEquals(3, diagnostic59.getData().size());
    assertNull(diagnostic59.getException());

    Diagnostic diagnostic60 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic60.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic60.getSource());
    assertEquals("There may not be two type parameters named 'A'", removeObjectHashCode(diagnostic60.getMessage()));
    assertEquals(37, diagnostic60.getCode());
    assertEquals(0, diagnostic60.getChildren().size());
    assertEquals(3, diagnostic60.getData().size());
    assertNull(diagnostic60.getException());

    Diagnostic diagnostic61 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic61.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic61.getSource());
    assertEquals("An operation with void return type must have an upper bound of 1 not -1", removeObjectHashCode(diagnostic61.getMessage()));
    assertEquals(27, diagnostic61.getCode());
    assertEquals(0, diagnostic61.getChildren().size());
    assertEquals(1, diagnostic61.getData().size());
    assertNull(diagnostic61.getException());

    Diagnostic diagnostic62 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic62.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic62.getSource());
    assertEquals("There may not be two packages named 'duplicateName'", removeObjectHashCode(diagnostic62.getMessage()));
    assertEquals(36, diagnostic62.getCode());
    assertEquals(0, diagnostic62.getChildren().size());
    assertEquals(3, diagnostic62.getData().size());
    assertNull(diagnostic62.getException());

    Diagnostic diagnostic63 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic63.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic63.getSource());
    assertEquals("The namespace URI 'null' is not well formed", removeObjectHashCode(diagnostic63.getMessage()));
    assertEquals(46, diagnostic63.getCode());
    assertEquals(0, diagnostic63.getChildren().size());
    assertEquals(1, diagnostic63.getData().size());
    assertNull(diagnostic63.getException());

    Diagnostic diagnostic64 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic64.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic64.getSource());
    assertEquals("The namespace URI '' is not well formed", removeObjectHashCode(diagnostic64.getMessage()));
    assertEquals(46, diagnostic64.getCode());
    assertEquals(0, diagnostic64.getChildren().size());
    assertEquals(1, diagnostic64.getData().size());
    assertNull(diagnostic64.getException());

    Diagnostic diagnostic65 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic65.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic65.getSource());
    assertEquals("The namespace URI 'NSUI With Space' is not well formed", removeObjectHashCode(diagnostic65.getMessage()));
    assertEquals(46, diagnostic65.getCode());
    assertEquals(0, diagnostic65.getChildren().size());
    assertEquals(1, diagnostic65.getData().size());
    assertNull(diagnostic65.getException());

    Diagnostic diagnostic66 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic66.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic66.getSource());
    assertEquals("The namespace URI 'NsURIWithUnencoded%' is not well formed", removeObjectHashCode(diagnostic66.getMessage()));
    assertEquals(46, diagnostic66.getCode());
    assertEquals(0, diagnostic66.getChildren().size());
    assertEquals(1, diagnostic66.getData().size());
    assertNull(diagnostic66.getException());

    Diagnostic diagnostic67 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic67.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic67.getSource());
    assertEquals("The namespace prefix 'xml.prefix' is not well formed", removeObjectHashCode(diagnostic67.getMessage()));
    assertEquals(45, diagnostic67.getCode());
    assertEquals(0, diagnostic67.getChildren().size());
    assertEquals(1, diagnostic67.getData().size());
    assertNull(diagnostic67.getException());

    Diagnostic diagnostic68 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic68.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic68.getSource());
    assertEquals("The namespace prefix 'bad.package:nonNSNamePrefix' is not well formed", removeObjectHashCode(diagnostic68.getMessage()));
    assertEquals(45, diagnostic68.getCode());
    assertEquals(0, diagnostic68.getChildren().size());
    assertEquals(1, diagnostic68.getData().size());
    assertNull(diagnostic68.getException());

    Diagnostic diagnostic69 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic69.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic69.getSource());
    assertEquals("There may not be two classifiers named 'a'", removeObjectHashCode(diagnostic69.getMessage()));
    assertEquals(29, diagnostic69.getCode());
    assertEquals(0, diagnostic69.getChildren().size());
    assertEquals(3, diagnostic69.getData().size());
    assertNull(diagnostic69.getException());

    Diagnostic diagnostic70 = diagnosticIterator.next();
    assertEquals(Diagnostic.WARNING, diagnostic70.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic70.getSource());
    assertEquals("There may not be a classifier named 'A_' as well a classifier named 'a'", removeObjectHashCode(diagnostic70.getMessage()));
    assertEquals(29, diagnostic70.getCode());
    assertEquals(0, diagnostic70.getChildren().size());
    assertEquals(3, diagnostic70.getData().size());
    assertNull(diagnostic70.getException());

    Diagnostic diagnostic71 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic71.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic71.getSource());
    assertEquals("There may not be two packages with namespace URI 'platform:/plugin/org.eclipse.emf.test.core/models/Bad.ecore#//package/duplicateNsURIs/a1'", removeObjectHashCode(diagnostic71.getMessage()));
    assertEquals(33, diagnostic71.getCode());
    assertEquals(0, diagnostic71.getChildren().size());
    assertEquals(2, diagnostic71.getData().size());
    assertNull(diagnostic71.getException());

    Diagnostic diagnostic72 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic72.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic72.getSource());
    assertEquals("There may not be two packages with namespace URI 'platform:/plugin/org.eclipse.emf.test.core/models/Bad.ecore#//package/duplicateNsURIs/a1'", removeObjectHashCode(diagnostic72.getMessage()));
    assertEquals(33, diagnostic72.getCode());
    assertEquals(0, diagnostic72.getChildren().size());
    assertEquals(2, diagnostic72.getData().size());
    assertNull(diagnostic72.getException());

    Diagnostic diagnostic73 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic73.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic73.getSource());
    assertEquals("The opposite of the opposite may not be a reference different from this one", removeObjectHashCode(diagnostic73.getMessage()));
    assertEquals(14, diagnostic73.getCode());
    assertEquals(0, diagnostic73.getChildren().size());
    assertEquals(3, diagnostic73.getData().size());
    assertNull(diagnostic73.getException());

    Diagnostic diagnostic74 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic74.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic74.getSource());
    assertEquals("The opposite must be a feature of the reference's type", removeObjectHashCode(diagnostic74.getMessage()));
    assertEquals(13, diagnostic74.getCode());
    assertEquals(0, diagnostic74.getChildren().size());
    assertEquals(3, diagnostic74.getData().size());
    assertNull(diagnostic74.getException());

    Diagnostic diagnostic75 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic75.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic75.getSource());
    assertEquals("The opposite of a transient reference must transient if it is proxy resolving", removeObjectHashCode(diagnostic75.getMessage()));
    assertEquals(11, diagnostic75.getCode());
    assertEquals(0, diagnostic75.getChildren().size());
    assertEquals(2, diagnostic75.getData().size());
    assertNull(diagnostic75.getException());

    Diagnostic diagnostic76 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic76.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic76.getSource());
    assertEquals("A container reference must have upper bound of 1 not 2", removeObjectHashCode(diagnostic76.getMessage()));
    assertEquals(28, diagnostic76.getCode());
    assertEquals(0, diagnostic76.getChildren().size());
    assertEquals(1, diagnostic76.getData().size());
    assertNull(diagnostic76.getException());

    Diagnostic diagnostic77 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic77.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic77.getSource());
    assertEquals("The opposite of a containment reference must not be a containment reference", removeObjectHashCode(diagnostic77.getMessage()));
    assertEquals(12, diagnostic77.getCode());
    assertEquals(0, diagnostic77.getChildren().size());
    assertEquals(2, diagnostic77.getData().size());
    assertNull(diagnostic77.getException());

    Diagnostic diagnostic78 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic78.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic78.getSource());
    assertEquals("The opposite of a containment reference must not be a containment reference", removeObjectHashCode(diagnostic78.getMessage()));
    assertEquals(12, diagnostic78.getCode());
    assertEquals(0, diagnostic78.getChildren().size());
    assertEquals(2, diagnostic78.getData().size());
    assertNull(diagnostic78.getException());

    Diagnostic diagnostic79 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic79.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic79.getSource());
    assertEquals("The lower bound -1 must be greater than or equal to 0", removeObjectHashCode(diagnostic79.getMessage()));
    assertEquals(39, diagnostic79.getCode());
    assertEquals(0, diagnostic79.getChildren().size());
    assertEquals(1, diagnostic79.getData().size());
    assertNull(diagnostic79.getException());

    Diagnostic diagnostic80 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic80.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic80.getSource());
    assertEquals("The upper bound 0 must be -2, -1, or greater than 0", removeObjectHashCode(diagnostic80.getMessage()));
    assertEquals(41, diagnostic80.getCode());
    assertEquals(0, diagnostic80.getChildren().size());
    assertEquals(1, diagnostic80.getData().size());
    assertNull(diagnostic80.getException());

    Diagnostic diagnostic81 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic81.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic81.getSource());
    assertEquals("The upper bound -3 must be -2, -1, or greater than 0", removeObjectHashCode(diagnostic81.getMessage()));
    assertEquals(41, diagnostic81.getCode());
    assertEquals(0, diagnostic81.getChildren().size());
    assertEquals(1, diagnostic81.getData().size());
    assertNull(diagnostic81.getException());

    Diagnostic diagnostic82 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic82.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic82.getSource());
    assertEquals("The lower bound 2 must be less than or equal to the upper bound 1", removeObjectHashCode(diagnostic82.getMessage()));
    assertEquals(6, diagnostic82.getCode());
    assertEquals(0, diagnostic82.getChildren().size());
    assertEquals(1, diagnostic82.getData().size());
    assertNull(diagnostic82.getException());

    Diagnostic diagnostic83 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic83.getSeverity());
    assertEquals("org.eclipse.emf.ecore", diagnostic83.getSource());
    assertEquals("The required feature 'eAttributeType' of 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//genericType/AttributeClassType/a}' must be set", removeObjectHashCode(diagnostic83.getMessage()));
    assertEquals(1, diagnostic83.getCode());
    assertEquals(0, diagnostic83.getChildren().size());
    assertEquals(2, diagnostic83.getData().size());
    assertNull(diagnostic83.getException());

    Diagnostic diagnostic84 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic84.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic84.getSource());
    assertEquals("The generic attribute type must not refer to a class", removeObjectHashCode(diagnostic84.getMessage()));
    assertEquals(19, diagnostic84.getCode());
    assertEquals(0, diagnostic84.getChildren().size());
    assertEquals(1, diagnostic84.getData().size());
    assertNull(diagnostic84.getException());

    Diagnostic diagnostic85 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic85.getSeverity());
    assertEquals("org.eclipse.emf.ecore", diagnostic85.getSource());
    assertEquals("The required feature 'eReferenceType' of 'org.eclipse.emf.ecore.impl.EReferenceImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//genericType/ReferenceDataType/r}' must be set", removeObjectHashCode(diagnostic85.getMessage()));
    assertEquals(1, diagnostic85.getCode());
    assertEquals(0, diagnostic85.getChildren().size());
    assertEquals(2, diagnostic85.getData().size());
    assertNull(diagnostic85.getException());

    Diagnostic diagnostic86 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic86.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic86.getSource());
    assertEquals("The generic reference type must not refer to a data type", removeObjectHashCode(diagnostic86.getMessage()));
    assertEquals(20, diagnostic86.getCode());
    assertEquals(0, diagnostic86.getChildren().size());
    assertEquals(1, diagnostic86.getData().size());
    assertNull(diagnostic86.getException());

    Diagnostic diagnostic87 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic87.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic87.getSource());
    assertEquals("A generic type can't refer to both a type parameter and a classifier", removeObjectHashCode(diagnostic87.getMessage()));
    assertEquals(21, diagnostic87.getCode());
    assertEquals(0, diagnostic87.getChildren().size());
    assertEquals(1, diagnostic87.getData().size());
    assertNull(diagnostic87.getException());

    Diagnostic diagnostic88 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic88.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic88.getSource());
    assertEquals("A generic super type must refer to a class", removeObjectHashCode(diagnostic88.getMessage()));
    assertEquals(18, diagnostic88.getCode());
    assertEquals(0, diagnostic88.getChildren().size());
    assertEquals(1, diagnostic88.getData().size());
    assertNull(diagnostic88.getException());

    Diagnostic diagnostic89 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic89.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic89.getSource());
    assertEquals("A generic super type must refer to a class", removeObjectHashCode(diagnostic89.getMessage()));
    assertEquals(18, diagnostic89.getCode());
    assertEquals(0, diagnostic89.getChildren().size());
    assertEquals(1, diagnostic89.getData().size());
    assertNull(diagnostic89.getException());

    Diagnostic diagnostic90 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic90.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic90.getSource());
    assertEquals("The generic super types at index '1' and '0' must not be duplicates", removeObjectHashCode(diagnostic90.getMessage()));
    assertEquals(16, diagnostic90.getCode());
    assertEquals(0, diagnostic90.getChildren().size());
    assertEquals(3, diagnostic90.getData().size());
    assertNull(diagnostic90.getException());

    Diagnostic diagnostic91 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic91.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic91.getSource());
    assertEquals("A generic type in this context must refer to a classifier or a type parameter", removeObjectHashCode(diagnostic91.getMessage()));
    assertEquals(24, diagnostic91.getCode());
    assertEquals(0, diagnostic91.getChildren().size());
    assertEquals(1, diagnostic91.getData().size());
    assertNull(diagnostic91.getException());

    Diagnostic diagnostic92 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic92.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic92.getSource());
    assertEquals("A generic type must not have both a lower and an upper bound", removeObjectHashCode(diagnostic92.getMessage()));
    assertEquals(9, diagnostic92.getCode());
    assertEquals(0, diagnostic92.getChildren().size());
    assertEquals(1, diagnostic92.getData().size());
    assertNull(diagnostic92.getException());

    Diagnostic diagnostic93 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic93.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic93.getSource());
    assertEquals("A generic type may have bounds only when used as a type argument", removeObjectHashCode(diagnostic93.getMessage()));
    assertEquals(7, diagnostic93.getCode());
    assertEquals(0, diagnostic93.getChildren().size());
    assertEquals(1, diagnostic93.getData().size());
    assertNull(diagnostic93.getException());

    Diagnostic diagnostic94 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic94.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic94.getSource());
    assertEquals("A generic type with bounds must not also refer to a type parameter or classifier", removeObjectHashCode(diagnostic94.getMessage()));
    assertEquals(8, diagnostic94.getCode());
    assertEquals(0, diagnostic94.getChildren().size());
    assertEquals(1, diagnostic94.getData().size());
    assertNull(diagnostic94.getException());

    Diagnostic diagnostic95 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic95.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic95.getSource());
    assertEquals("A generic type may have arguments only if it refers to a classifier", removeObjectHashCode(diagnostic95.getMessage()));
    assertEquals(5, diagnostic95.getCode());
    assertEquals(0, diagnostic95.getChildren().size());
    assertEquals(1, diagnostic95.getData().size());
    assertNull(diagnostic95.getException());

    Diagnostic diagnostic96 = diagnosticIterator.next();
    assertEquals(Diagnostic.WARNING, diagnostic96.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic96.getSource());
    assertEquals("The generic type associated with the 'EJavaClass' classifier should have 1 type argument(s) to match the number of type parameter(s) of the classifier ", removeObjectHashCode(diagnostic96.getMessage()));
    assertEquals(4, diagnostic96.getCode());
    assertEquals(0, diagnostic96.getChildren().size());
    assertEquals(1, diagnostic96.getData().size());
    assertNull(diagnostic96.getException());

    Diagnostic diagnostic97 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic97.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic97.getSource());
    assertEquals("The generic type associated with the 'EJavaClass' classifier must not have 2 argument(s) when the classifier has 1 type parameter(s)", removeObjectHashCode(diagnostic97.getMessage()));
    assertEquals(2, diagnostic97.getCode());
    assertEquals(0, diagnostic97.getChildren().size());
    assertEquals(1, diagnostic97.getData().size());
    assertNull(diagnostic97.getException());

    Diagnostic diagnostic98 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic98.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic98.getSource());
    assertEquals("A generic type may only refer to a type parameter that is in scope", removeObjectHashCode(diagnostic98.getMessage()));
    assertEquals(23, diagnostic98.getCode());
    assertEquals(0, diagnostic98.getChildren().size());
    assertEquals(1, diagnostic98.getData().size());
    assertNull(diagnostic98.getException());

    Diagnostic diagnostic99 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic99.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic99.getSource());
    assertEquals("The generic super types instantiate 'org.eclipse.emf.ecore.impl.EClassImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//genericType/BaseClass}' inconsistently", removeObjectHashCode(diagnostic99.getMessage()));
    assertEquals(15, diagnostic99.getCode());
    assertEquals(0, diagnostic99.getChildren().size());
    assertEquals(3, diagnostic99.getData().size());
    assertNull(diagnostic99.getException());

    Diagnostic diagnostic100 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic100.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic100.getSource());
    assertEquals("A generic type may only refer to a type parameter that is in scope", removeObjectHashCode(diagnostic100.getMessage()));
    assertEquals(23, diagnostic100.getCode());
    assertEquals(0, diagnostic100.getChildren().size());
    assertEquals(1, diagnostic100.getData().size());
    assertNull(diagnostic100.getException());

    Diagnostic diagnostic101 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic101.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic101.getSource());
    assertEquals("A generic type may only refer to a type parameter that is in scope", removeObjectHashCode(diagnostic101.getMessage()));
    assertEquals(23, diagnostic101.getCode());
    assertEquals(0, diagnostic101.getChildren().size());
    assertEquals(1, diagnostic101.getData().size());
    assertNull(diagnostic101.getException());

    Diagnostic diagnostic102 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic102.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic102.getSource());
    assertEquals("The primitive type 'int' cannot be used in this context", removeObjectHashCode(diagnostic102.getMessage()));
    assertEquals(22, diagnostic102.getCode());
    assertEquals(0, diagnostic102.getChildren().size());
    assertEquals(1, diagnostic102.getData().size());
    assertNull(diagnostic102.getException());

    Diagnostic diagnostic103 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic103.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic103.getSource());
    assertEquals("The primitive type 'int' cannot be used in this context", removeObjectHashCode(diagnostic103.getMessage()));
    assertEquals(22, diagnostic103.getCode());
    assertEquals(0, diagnostic103.getChildren().size());
    assertEquals(1, diagnostic103.getData().size());
    assertNull(diagnostic103.getException());

    Diagnostic diagnostic104 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic104.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic104.getSource());
    assertEquals("The primitive type 'int' cannot be used in this context", removeObjectHashCode(diagnostic104.getMessage()));
    assertEquals(22, diagnostic104.getCode());
    assertEquals(0, diagnostic104.getChildren().size());
    assertEquals(1, diagnostic104.getData().size());
    assertNull(diagnostic104.getException());

    Diagnostic diagnostic105 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic105.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic105.getSource());
    assertEquals("The primitive type 'int' cannot be used in this context", removeObjectHashCode(diagnostic105.getMessage()));
    assertEquals(22, diagnostic105.getCode());
    assertEquals(0, diagnostic105.getChildren().size());
    assertEquals(1, diagnostic105.getData().size());
    assertNull(diagnostic105.getException());

    Diagnostic diagnostic106 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic106.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic106.getSource());
    assertEquals("The primitive type 'int' cannot be used in this context", removeObjectHashCode(diagnostic106.getMessage()));
    assertEquals(22, diagnostic106.getCode());
    assertEquals(0, diagnostic106.getChildren().size());
    assertEquals(1, diagnostic106.getData().size());
    assertNull(diagnostic106.getException());

    Diagnostic diagnostic107 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic107.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic107.getSource());
    assertEquals("The key 'org.eclipse.emf.ecore.impl.EAttributeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//key/ReferenceWithKeyNotFromReferenceType/a}' must be feature of the reference's type", removeObjectHashCode(diagnostic107.getMessage()));
    assertEquals(10, diagnostic107.getCode());
    assertEquals(0, diagnostic107.getChildren().size());
    assertEquals(2, diagnostic107.getData().size());
    assertNull(diagnostic107.getException());

    Diagnostic diagnostic108 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic108.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic108.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container/f/b//.1}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container/U}'", removeObjectHashCode(diagnostic108.getMessage()));
    assertEquals(3, diagnostic108.getCode());
    assertEquals(0, diagnostic108.getChildren().size());
    assertEquals(3, diagnostic108.getData().size());
    assertNull(diagnostic108.getException());

    Diagnostic diagnostic109 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic109.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic109.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container/f/d//.1}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container/U}'", removeObjectHashCode(diagnostic109.getMessage()));
    assertEquals(3, diagnostic109.getCode());
    assertEquals(0, diagnostic109.getChildren().size());
    assertEquals(3, diagnostic109.getData().size());
    assertNull(diagnostic109.getException());

    Diagnostic diagnostic110 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic110.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic110.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container/f/g//.2}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container/V}'", removeObjectHashCode(diagnostic110.getMessage()));
    assertEquals(3, diagnostic110.getCode());
    assertEquals(0, diagnostic110.getChildren().size());
    assertEquals(3, diagnostic110.getData().size());
    assertNull(diagnostic110.getException());

    Diagnostic diagnostic111 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic111.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic111.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container2/g/b//.1}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container2/U}'", removeObjectHashCode(diagnostic111.getMessage()));
    assertEquals(3, diagnostic111.getCode());
    assertEquals(0, diagnostic111.getChildren().size());
    assertEquals(3, diagnostic111.getData().size());
    assertNull(diagnostic111.getException());

    Diagnostic diagnostic112 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic112.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic112.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container2/g/d//.1}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container2/U}'", removeObjectHashCode(diagnostic112.getMessage()));
    assertEquals(3, diagnostic112.getCode());
    assertEquals(0, diagnostic112.getChildren().size());
    assertEquals(3, diagnostic112.getData().size());
    assertNull(diagnostic112.getException());

    Diagnostic diagnostic113 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic113.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic113.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/g/b//.1}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/U}'", removeObjectHashCode(diagnostic113.getMessage()));
    assertEquals(3, diagnostic113.getCode());
    assertEquals(0, diagnostic113.getChildren().size());
    assertEquals(3, diagnostic113.getData().size());
    assertNull(diagnostic113.getException());

    Diagnostic diagnostic114 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic114.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic114.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/g/d//.1}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/U}'", removeObjectHashCode(diagnostic114.getMessage()));
    assertEquals(3, diagnostic114.getCode());
    assertEquals(0, diagnostic114.getChildren().size());
    assertEquals(3, diagnostic114.getData().size());
    assertNull(diagnostic114.getException());

    Diagnostic diagnostic115 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic115.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic115.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/g/g//.2}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/V}'", removeObjectHashCode(diagnostic115.getMessage()));
    assertEquals(3, diagnostic115.getCode());
    assertEquals(0, diagnostic115.getChildren().size());
    assertEquals(3, diagnostic115.getData().size());
    assertNull(diagnostic115.getException());

    Diagnostic diagnostic116 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic116.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic116.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/g/j//.2}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/V}'", removeObjectHashCode(diagnostic116.getMessage()));
    assertEquals(3, diagnostic116.getCode());
    assertEquals(0, diagnostic116.getChildren().size());
    assertEquals(3, diagnostic116.getData().size());
    assertNull(diagnostic116.getException());

    Diagnostic diagnostic117 = diagnosticIterator.next();
    assertEquals(Diagnostic.WARNING, diagnostic117.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic117.getSource());
    assertEquals("The generic type associated with the 'A' classifier should have 1 type argument(s) to match the number of type parameter(s) of the classifier ", removeObjectHashCode(diagnostic117.getMessage()));
    assertEquals(4, diagnostic117.getCode());
    assertEquals(0, diagnostic117.getChildren().size());
    assertEquals(1, diagnostic117.getData().size());
    assertNull(diagnostic117.getException());

    Diagnostic diagnostic118 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic118.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic118.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/h/b//.1}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/U}'", removeObjectHashCode(diagnostic118.getMessage()));
    assertEquals(3, diagnostic118.getCode());
    assertEquals(0, diagnostic118.getChildren().size());
    assertEquals(3, diagnostic118.getData().size());
    assertNull(diagnostic118.getException());

    Diagnostic diagnostic119 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic119.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic119.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/h/d//.1}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/U}'", removeObjectHashCode(diagnostic119.getMessage()));
    assertEquals(3, diagnostic119.getCode());
    assertEquals(0, diagnostic119.getChildren().size());
    assertEquals(3, diagnostic119.getData().size());
    assertNull(diagnostic119.getException());

    Diagnostic diagnostic120 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic120.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic120.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/h/g//.2}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/V}'", removeObjectHashCode(diagnostic120.getMessage()));
    assertEquals(3, diagnostic120.getCode());
    assertEquals(0, diagnostic120.getChildren().size());
    assertEquals(3, diagnostic120.getData().size());
    assertNull(diagnostic120.getException());

    Diagnostic diagnostic121 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic121.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic121.getSource());
    assertEquals("The generic type 'org.eclipse.emf.ecore.impl.EGenericTypeImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/h/j//.2}' is not a valid substitution for type parameter 'org.eclipse.emf.ecore.impl.ETypeParameterImpl{platform:/plugin/platform:/plugin/org.eclipse.emf.test.core/data/Bad.ecore#//substitution/Container3/V}'", removeObjectHashCode(diagnostic121.getMessage()));
    assertEquals(3, diagnostic121.getCode());
    assertEquals(0, diagnostic121.getChildren().size());
    assertEquals(3, diagnostic121.getData().size());
    assertNull(diagnostic121.getException());

    Diagnostic diagnostic122 = diagnosticIterator.next();
    assertEquals(Diagnostic.WARNING, diagnostic122.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic122.getSource());
    assertEquals("The generic type associated with the 'B' classifier should have 1 type argument(s) to match the number of type parameter(s) of the classifier ", removeObjectHashCode(diagnostic122.getMessage()));
    assertEquals(4, diagnostic122.getCode());
    assertEquals(0, diagnostic122.getChildren().size());
    assertEquals(1, diagnostic122.getData().size());
    assertNull(diagnostic122.getException());

    Diagnostic diagnostic123 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic123.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic123.getSource());
    assertEquals("The default value literal '256' must be a valid literal of the attribute's type", removeObjectHashCode(diagnostic123.getMessage()));
    assertEquals(39, diagnostic123.getCode());
    assertEquals(1, diagnostic123.getChildren().size());
    assertEquals(1, diagnostic123.getData().size());
    assertNull(diagnostic123.getException());

    Diagnostic diagnostic124 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic124.getSeverity());
    assertEquals("org.eclipse.emf.ecore", diagnostic124.getSource());
    assertEquals("The value '256' must be less than or equal to '255' ", removeObjectHashCode(diagnostic124.getMessage()));
    assertEquals(5, diagnostic124.getCode());
    assertEquals(0, diagnostic124.getChildren().size());
    assertEquals(3, diagnostic124.getData().size());
    assertNull(diagnostic124.getException());

    Diagnostic diagnostic125 = diagnosticIterator.next();
    assertEquals(Diagnostic.ERROR, diagnostic125.getSeverity());
    assertEquals("org.eclipse.emf.ecore.model", diagnostic125.getSource());
    assertEquals("The default value literal '' must be a valid literal of the attribute's type", removeObjectHashCode(diagnostic125.getMessage()));
    assertEquals(39, diagnostic125.getCode());
    assertEquals(0, diagnostic125.getChildren().size());
    assertEquals(1, diagnostic125.getData().size());
    assertNull(diagnostic125.getException());
    
    assertFalse(diagnosticIterator.hasNext());
  }
  
  protected String toString(Throwable throwable)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(throwable.getClass().getName());
    sb.append("#").append(throwable.getMessage());

    Throwable cause = throwable.getCause();
    if (cause != null && cause != throwable)
    {
      sb.append("--").append(toString(cause));
    }
    return sb.toString();
  }
  
  protected String removeObjectHashCode(String string)
  {
    return string.replaceAll("@\\w+", "");
  }
}





