/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper;
import org.junit.Test;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

public class EcoreTest
{
  public static class NotificationCollector extends AdapterImpl
  {
    private List<Notification> notifications = new ArrayList<Notification>();

    public List<Notification> getNotifications()
    {
      return notifications;
    }

    @Override
    public void notifyChanged(Notification msg)
    {
      notifications.add(msg);
    }
  }

  /*
   * <a href="http://bugs.eclipse.org/169926">Bug 169926</a>
   * This must be run before any other tests using Ecore, since it will always pass if EcorePackage has been initialized.
   */
  @Test
  public void testCreateAnnotationOnInitialization()
  {
    EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
    annotation.setSource("XTest");
    annotation.getDetails().put("Test", "true");
    assertEquals("true", annotation.getDetails().get("Test"));
  }

  /*
   * <a href="http://bugs.eclipse.org/170549">Bugzilla 170549</a>
   */
  @Test
  public void testESuperTypeNotificationCount() throws Exception
  {
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("Class1");
    EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
    eClass2.setName("Class2");

    NotificationCollector notificationCollector = new NotificationCollector();
    eClass1.eAdapters().add(notificationCollector);
    eClass2.eAdapters().add(notificationCollector);

    eClass2.getESuperTypes().add(eClass1);

    assertEquals(2, notificationCollector.getNotifications().size());
  }

  /**
   * <a href="http://bugs.eclipse.org/212903">Bugzilla 212903</a>
   */
  @Test
  public void testESuperTypeLastIndexOf() throws Exception
  {
    EClass eClass1 = EcoreFactory.eINSTANCE.createEClass();
    eClass1.setName("Class1");
    EClass eClass2 = EcoreFactory.eINSTANCE.createEClass();
    eClass2.setName("Class2");

    eClass2.getESuperTypes().add(eClass1);

    assertEquals(0, eClass2.getESuperTypes().lastIndexOf(eClass1));
  }

  /*
   * <a href="http://bugs.eclipse.org/170549">Bugzilla 170549</a>
   */
  @Test
  public void testEExceptionNotificationCount() throws Exception
  {
    EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
    eOperation.setName("operation");
    EClass aException = EcoreFactory.eINSTANCE.createEClass();
    aException.setName("AException");

    NotificationCollector notificationCollector = new NotificationCollector();
    eOperation.eAdapters().add(notificationCollector);
    aException.eAdapters().add(notificationCollector);

    eOperation.getEExceptions().add(aException);

    assertEquals(2, notificationCollector.getNotifications().size());
  }

  /**
   * <a href="http://bugs.eclipse.org/235992">Bugzilla 235992</a>
   */
  @Test
  public void testFrozenModelChange()
  {
    try
    {
      assert false;
      fail("Assertions must be enabled via JVM flag -ea or -enableassertions");
    }
    catch (AssertionError exception)
    {
      // Ignore
    }
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    EcoreUtil.freeze(ePackage);
    boolean assertionFailure;
    try
    {
      new ResourceImpl().getContents().add(ePackage);
      assertionFailure = false;
    }
    catch (AssertionError exception)
    {
      assertionFailure = true;
    }
    assertTrue(assertionFailure);
  }

  /**
   * <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=540041">Bugzilla 540041</a>
   */
  @Test
  public void testCircularProxy()
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    URI uri = URI.createURI("CircularProxy.ecore");
    Resource resource = resourceSet.createResource(uri);
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    resource.getContents().add(ePackage);

    EClass circularProxyEClass = EcoreFactory.eINSTANCE.createEClass();
    ((InternalEObject)circularProxyEClass).eSetProxyURI(uri.appendFragment("//Class"));
    ePackage.getEClassifiers().add(circularProxyEClass);

    EClass validEClass = EcoreFactory.eINSTANCE.createEClass();
    validEClass.setName("Valid");
    ePackage.getEClassifiers().add(validEClass);

    EPackage circularSubPackage = EcoreFactory.eINSTANCE.createEPackage();
    ((InternalEObject)circularSubPackage).eSetProxyURI(uri.appendFragment("//package"));
    ePackage.getESubpackages().add(circularSubPackage);

    EPackage validSubPackage = EcoreFactory.eINSTANCE.createEPackage();
    validSubPackage.setName("valid");
    ePackage.getESubpackages().add(validSubPackage);

    try
    {
      EObject eObject = resourceSet.getEObject(uri.appendFragment("//Valid"), false);
      assertSame(validEClass, eObject);

      eObject = resourceSet.getEObject(uri.appendFragment("//valid"), false);
      assertSame(validSubPackage, eObject);
    }
    catch (StackOverflowError error)
    {
      fail("Stack overflow");
    }

    try
    {
      validEClass.setName("Valid1");
      EObject eObject = resourceSet.getEObject(uri.appendFragment("//Valid1"), false);
      assertSame(validEClass, eObject);

      validSubPackage.setName("valid1");
      eObject = resourceSet.getEObject(uri.appendFragment("//valid1"), false);
      assertSame(validSubPackage, eObject);
    }
    catch (StackOverflowError error)
    {
      fail("Stack overflow");
    }
  }

  /**
   * <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=545978">Bugzilla 545978</a>
   */
  @Test
  public void testDuplicateNameProxyResolution()
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    URI uri = URI.createURI("Duplicate.ecore");
    Resource resource = resourceSet.createResource(uri);
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("duplicate");
    ePackage.setNsURI("duplicate");
    ePackage.setNsPrefix("duplicate");
    resource.getContents().add(ePackage);

    for (int i = 0; i < 10; ++i)
    {
      for (String name : new String []{ "bogus1", "bogus2" })
      {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName(name);
        ePackage.getEClassifiers().add(eClass);
      }
    }

    for (int i = 0; i < 10; ++i)
    {
      EPackage eSubPackage = EcoreFactory.eINSTANCE.createEPackage();
      for (String name : new String []{ "bogus1", "bogus2" })
      {
        eSubPackage.setName(name);
        eSubPackage.setNsURI(name);
        eSubPackage.setNsPrefix(name);
        ePackage.getESubpackages().add(eSubPackage);
      }
    }

    for (EObject eObject : ePackage.eContents())
    {
      URI eProxyURI = EcoreUtil.getURI(eObject);
      EObject otherEObject = resourceSet.getEObject(eProxyURI, false);
      assertSame("The proxy URI " + eProxyURI + " fails to resolve to the correct object", eObject, otherEObject);
    }
  }

  @Test
  public void testGeneratedPackageInstanceEquality()
  {
    compareDynamicEPackageWithGeneratedEPackage(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore"));
    compareDynamicEPackageWithGeneratedEPackage(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/XMLType.ecore"));
    compareDynamicEPackageWithGeneratedEPackage(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/XMLNamespace.ecore"));
    compareDynamicEPackageWithGeneratedEPackage(URI.createURI("platform:/plugin/org.eclipse.emf.ecore.change/model/Change.ecore"));
  }

  public static void compareDynamicEPackageWithGeneratedEPackage(final URI uri)
  {
    ResourceSet resourceSet = new ResourceSetImpl()
      {
        URI ecoreURI = URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore");

        @Override
        protected Resource delegatedGetResource(URI otherURI, boolean loadOnDemand)
        {
          if (!uri.equals(ecoreURI) && otherURI.equals(ecoreURI))
          {
            return EcorePackage.eINSTANCE.eResource();
          }
          else
          {
            return super.delegatedGetResource(otherURI, loadOnDemand);
          }
        }
      };
    EPackage ePackage = (EPackage)resourceSet.getResource(uri, true).getContents().get(0);
    resourceSet.getURIConverter().getURIMap().put(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore"), URI.createURI(EcorePackage.eNS_URI));
    EcoreUtil.resolveAll(resourceSet);
    List<EObject> genModelAnnotations = new ArrayList<EObject>();
    for (Iterator<Notifier> i = resourceSet.getAllContents(); i.hasNext();)
    {
      Notifier eObject = i.next();
      if (eObject instanceof EAnnotation && EcoreUtil.GEN_MODEL_ANNOTATION_URI.equals(((EAnnotation)eObject).getSource()))
      {
        genModelAnnotations.add((EObject)eObject);
      }
    }

    for (EObject eObject : genModelAnnotations)
    {
      EcoreUtil.delete(eObject);
    }

    EqualityHelper equalityHelper = new EcoreUtil.EqualityHelper()
      {
        private static final long serialVersionUID = 1L;

        @Override
        protected boolean haveEqualReference(EObject eObject1, EObject eObject2, EReference reference)
        {
          if (reference == EcorePackage.Literals.EPACKAGE__ECLASSIFIERS)
          {
            EPackage ePackage1 = (EPackage)eObject1;
            EList<EClassifier> eClassifiers1 = ePackage1.getEClassifiers();
            EPackage ePackage2 = (EPackage)eObject2;
            EList<EClassifier> eClassifiers2 = ePackage2.getEClassifiers();

            int size = eClassifiers1.size();
            if (size != eClassifiers2.size())
            {
              return false;
            }

            for (EClassifier eClassifier : eClassifiers1)
            {
              if (!equals(eClassifier, ePackage2.getEClassifier(eClassifier.getName())))
              {
                return false;
              }
            }

            return true;
          }
          else
          {
            return super.haveEqualReference(eObject1, eObject2, reference);
          }
        }

        @Override
        protected boolean haveEqualFeature(EObject eObject1, EObject eObject2, EStructuralFeature feature)
        {
          boolean result = super.haveEqualFeature(eObject1, eObject2, feature);
          if (!result)
          {
            if (feature == EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME)
            {
              EClassifier eClassifer1 = (EClassifier)eObject1;
              String instanceClassName1 = eClassifer1.getInstanceClassName();
              EClassifier eClassifer2 = (EClassifier)eObject2;
              String instanceClassName2 = eClassifer2.getInstanceClassName();
              if ("org.eclipse.emf.common.util.Enumerator".equals(instanceClassName1) && "org.eclipse.emf.common.util.AbstractEnumerator".equals(instanceClassName2))
              {
                return true;
              }
            }
            else if (feature == EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES)
            {
               EReference eReference1 = (EReference)eObject1;
               EReference eReference2 = (EReference)eObject2;
               if (eReference1.isContainment() && eReference2.isContainment() && eReference1.isResolveProxies() && !eReference2.isRequired()) {
                 return true;
               }
            }
          }
          return result;
        }
      };

    boolean equal = equalityHelper.equals(ePackage, EPackage.Registry.INSTANCE.getEPackage(ePackage.getNsURI()));
    assertTrue("The dynamic and static instances of '" + uri + "' should be structurally equivalent", equal);
  }

  @Test
  public void testEcoreEMap()
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.getResource(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore"), true);
    EPackage dynamicEcoreEPackage = (EPackage)resource.getContents().get(0);
    EClass eAnnotationEClass = (EClass)dynamicEcoreEPackage.getEClassifier("EAnnotation");
    EStructuralFeature  detailsEStructuralFeature= eAnnotationEClass.getEStructuralFeature("details");
    Map.Entry<?, ?> badMapEntry = (Entry<?, ?>)ChangeFactory.eINSTANCE.create(ChangePackage.Literals.EOBJECT_TO_CHANGES_MAP_ENTRY);
    assertFalse("The dynamic Map.Entry type should check the dynamic type of the instance.", detailsEStructuralFeature.getEType().isInstance(badMapEntry));
  }

  @Test
  public void testProvideCapabilityRegistration()
  {
    if (FrameworkUtil.getBundle(Platform.class).getVersion().compareTo(Version.parseVersion("3.17.100")) >=0)
    {
      Map<String, URI> ePackageNsURIToGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap(true);
      assertTrue(
        "The nsURI registered by Provide-Capability in the org.eclipse.emf.test.common's MANIFEST.MF should be available",
        ePackageNsURIToGenModelLocationMap.containsKey("http:///org.eclipse.emf.test.models/provide-capability"));
      ExtensibleURIConverterImpl uriConverter = new ExtensibleURIConverterImpl();
      Map<URI, URI> uriMap = uriConverter.getURIMap();
      uriMap.putAll(EcorePlugin.computePlatformURIMap(true));
      URI uri = ePackageNsURIToGenModelLocationMap.get("http:///org.eclipse.emf.test.models/provide-capability");
      assertTrue(
        "The nsURI registered by Provide-Capability in the org.eclipse.emf.test.common's MANIFEST.MF should refer to an existing GenModel: '" + uri + "'",
        uriConverter.exists(uri, null));
    }
  }
}
