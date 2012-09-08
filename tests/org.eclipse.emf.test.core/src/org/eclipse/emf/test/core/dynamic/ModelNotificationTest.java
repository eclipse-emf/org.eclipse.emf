/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.dynamic;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;


public class ModelNotificationTest extends TestCase
{
  private EClass valueHolderClass;

  public ModelNotificationTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ModelNotificationTest");
    ts.addTest(new ModelNotificationTest("testBoolean"));
    ts.addTest(new ModelNotificationTest("testByte"));
    ts.addTest(new ModelNotificationTest("testChar"));
    ts.addTest(new ModelNotificationTest("testDouble"));
    ts.addTest(new ModelNotificationTest("testFloat"));
    ts.addTest(new ModelNotificationTest("testInt"));
    ts.addTest(new ModelNotificationTest("testLong"));
    ts.addTest(new ModelNotificationTest("testShort"));
    return ts;
  }

  public static final EDataType[] PRIMITIVE_TYPES =
    new EDataType[] 
    { 
      EcorePackage.eINSTANCE.getEBoolean(),
      EcorePackage.eINSTANCE.getEByte(),
      EcorePackage.eINSTANCE.getEChar(),
      EcorePackage.eINSTANCE.getEDouble(),
      EcorePackage.eINSTANCE.getEFloat(),
      EcorePackage.eINSTANCE.getEInt(),
      EcorePackage.eINSTANCE.getELong(),
      EcorePackage.eINSTANCE.getEShort(),
    };

  public void testBoolean()
  {
    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewBooleanValue() != msg.getOldBooleanValue());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EBooleanValue");
      eObject.eSet(eStructuralFeature, true);
      eObject.eSet(eStructuralFeature, false);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewBooleanValue() == msg.getOldBooleanValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EBooleanValue");
      eObject.eSet(eStructuralFeature, false);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewBooleanValue() == msg.getOldBooleanValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EBooleanValue");
      eObject.eSet(eStructuralFeature, false);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             if (msg.getEventType() != Notification.REMOVING_ADAPTER)
             {  
               assertTrue(msg.getNewBooleanValue() == msg.getOldBooleanValue());
               assertFalse(msg.wasSet());
             }
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EBooleanValueUnsettable");
      eObject.eSet(eStructuralFeature, false);
      eObject.eAdapters().clear();
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewBooleanValue() == msg.getOldBooleanValue());
             assertTrue(msg.wasSet());
           }
         });
      eObject.eUnset(eStructuralFeature);
    }
  }    

  public void testByte()
  {
    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewByteValue() != msg.getOldByteValue());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EByteValue");
      eObject.eSet(eStructuralFeature, (byte)1);
      eObject.eSet(eStructuralFeature, (byte)0);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewByteValue() == msg.getOldByteValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EByteValue");
      eObject.eSet(eStructuralFeature, (byte)0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewByteValue() == msg.getOldByteValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EByteValue");
      eObject.eSet(eStructuralFeature, (byte)0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             if (msg.getEventType() != Notification.REMOVING_ADAPTER)
             {  
               assertTrue(msg.getNewByteValue() == msg.getOldByteValue());
               assertFalse(msg.wasSet());
             }
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EByteValueUnsettable");
      eObject.eSet(eStructuralFeature, (byte)0);
      eObject.eAdapters().clear();
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewByteValue() == msg.getOldByteValue());
             assertTrue(msg.wasSet());
           }
         });
      eObject.eUnset(eStructuralFeature);
    }
  }    

  public void testChar()
  {
    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewCharValue() != msg.getOldCharValue());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("ECharValue");
      eObject.eSet(eStructuralFeature, (char)1);
      eObject.eSet(eStructuralFeature, (char)0);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewCharValue() == msg.getOldCharValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("ECharValue");
      eObject.eSet(eStructuralFeature, (char)0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewCharValue() == msg.getOldCharValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("ECharValue");
      eObject.eSet(eStructuralFeature, (char)0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             if (msg.getEventType() != Notification.REMOVING_ADAPTER)
             {  
               assertTrue(msg.getNewCharValue() == msg.getOldCharValue());
               assertFalse(msg.wasSet());
             }
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("ECharValueUnsettable");
      eObject.eSet(eStructuralFeature, (char)0);
      eObject.eAdapters().clear();
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewCharValue() == msg.getOldCharValue());
             assertTrue(msg.wasSet());
           }
         });
      eObject.eUnset(eStructuralFeature);
    }
  }    

  public void testDouble()
  {
    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewDoubleValue() != msg.getOldDoubleValue());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EDoubleValue");
      eObject.eSet(eStructuralFeature, 1.0);
      eObject.eSet(eStructuralFeature, 0.0);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewDoubleValue() == msg.getOldDoubleValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EDoubleValue");
      eObject.eSet(eStructuralFeature, 0.0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewDoubleValue() == msg.getOldDoubleValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EDoubleValue");
      eObject.eSet(eStructuralFeature, 0.0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             if (msg.getEventType() != Notification.REMOVING_ADAPTER)
             {  
               assertTrue(msg.getNewDoubleValue() == msg.getOldDoubleValue());
               assertFalse(msg.wasSet());
             }
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EDoubleValueUnsettable");
      eObject.eSet(eStructuralFeature, 0.0);
      eObject.eAdapters().clear();
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewDoubleValue() == msg.getOldDoubleValue());
             assertTrue(msg.wasSet());
           }
         });
      eObject.eUnset(eStructuralFeature);
    }
  }    

  public void testFloat()
  {
    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewFloatValue() != msg.getOldFloatValue());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EFloatValue");
      eObject.eSet(eStructuralFeature, 1.0F);
      eObject.eSet(eStructuralFeature, 0.0F);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewFloatValue() == msg.getOldFloatValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EFloatValue");
      eObject.eSet(eStructuralFeature, 0.0F);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewFloatValue() == msg.getOldFloatValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EFloatValue");
      eObject.eSet(eStructuralFeature, 0.0F);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             if (msg.getEventType() != Notification.REMOVING_ADAPTER)
             {  
               assertTrue(msg.getNewFloatValue() == msg.getOldFloatValue());
               assertFalse(msg.wasSet());
             }
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EFloatValueUnsettable");
      eObject.eSet(eStructuralFeature, 0.0F);
      eObject.eAdapters().clear();
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewFloatValue() == msg.getOldFloatValue());
             assertTrue(msg.wasSet());
           }
         });
      eObject.eUnset(eStructuralFeature);
    }
  }    

  public void testInt()
  {
    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewIntValue() != msg.getOldIntValue());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EIntValue");
      eObject.eSet(eStructuralFeature, 1);
      eObject.eSet(eStructuralFeature, 0);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewIntValue() == msg.getOldIntValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EIntValue");
      eObject.eSet(eStructuralFeature, 0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewIntValue() == msg.getOldIntValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EIntValue");
      eObject.eSet(eStructuralFeature, 0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             if (msg.getEventType() != Notification.REMOVING_ADAPTER)
             {  
               assertTrue(msg.getNewIntValue() == msg.getOldIntValue());
               assertFalse(msg.wasSet());
             }
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EIntValueUnsettable");
      eObject.eSet(eStructuralFeature, 0);
      eObject.eAdapters().clear();
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewIntValue() == msg.getOldIntValue());
             assertTrue(msg.wasSet());
           }
         });
      eObject.eUnset(eStructuralFeature);
    }
  }    

  public void testLong()
  {
    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewLongValue() != msg.getOldLongValue());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("ELongValue");
      eObject.eSet(eStructuralFeature, 1L);
      eObject.eSet(eStructuralFeature, 0L);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewLongValue() == msg.getOldLongValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("ELongValue");
      eObject.eSet(eStructuralFeature, 0L);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewLongValue() == msg.getOldLongValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("ELongValue");
      eObject.eSet(eStructuralFeature, 0L);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             if (msg.getEventType() != Notification.REMOVING_ADAPTER)
             {  
               assertTrue(msg.getNewLongValue() == msg.getOldLongValue());
               assertFalse(msg.wasSet());
             }
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("ELongValueUnsettable");
      eObject.eSet(eStructuralFeature, 0L);
      eObject.eAdapters().clear();
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewLongValue() == msg.getOldLongValue());
             assertTrue(msg.wasSet());
           }
         });
      eObject.eUnset(eStructuralFeature);
    }
  }    

  public void testShort()
  {
    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewShortValue() != msg.getOldShortValue());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EShortValue");
      eObject.eSet(eStructuralFeature, (short)1);
      eObject.eSet(eStructuralFeature, (short)0);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewShortValue() == msg.getOldShortValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EShortValue");
      eObject.eSet(eStructuralFeature, (short)0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewShortValue() == msg.getOldShortValue());
             assertFalse(msg.wasSet());
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EShortValue");
      eObject.eSet(eStructuralFeature, (short)0);
      eObject.eUnset(eStructuralFeature);
    }

    {
      EObject eObject = EcoreUtil.create(valueHolderClass);
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             if (msg.getEventType() != Notification.REMOVING_ADAPTER)
             {  
               assertTrue(msg.getNewShortValue() == msg.getOldShortValue());
               assertFalse(msg.wasSet());
             }
           }
         });
      EStructuralFeature eStructuralFeature = valueHolderClass.getEStructuralFeature("EShortValueUnsettable");
      eObject.eSet(eStructuralFeature, (short)0);
      eObject.eAdapters().clear();
      eObject.eAdapters().add
        (new AdapterImpl() 
         {
           @Override
           public void notifyChanged(Notification msg) 
           {
             assertTrue(msg.getNewShortValue() == msg.getOldShortValue());
             assertTrue(msg.wasSet());
           }
         });
      eObject.eUnset(eStructuralFeature);
    }
  }    

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

    valueHolderClass = ecoreFactory.createEClass();
    valueHolderClass.setName("ValueHolder");

    EList<EStructuralFeature> valueHolderFeatures = valueHolderClass.getEStructuralFeatures();
    for (EDataType eDataType : PRIMITIVE_TYPES)
    {
      {
        EAttribute eAttribute = ecoreFactory.createEAttribute();
        eAttribute.setName(eDataType.getName() + "Value");
        eAttribute.setEType(eDataType);
        valueHolderFeatures.add(eAttribute);
      }
      {
        EAttribute eAttribute = ecoreFactory.createEAttribute();
        eAttribute.setName(eDataType.getName() + "ValueUnsettable");
        eAttribute.setEType(eDataType);
        eAttribute.setUnsettable(true);
        valueHolderFeatures.add(eAttribute);
      }
    }
    
    EPackage testPackage = ecoreFactory.createEPackage();
    testPackage.setName("notificationTest");
    testPackage.setNsPrefix("notificationTest");
    testPackage.setNsURI("http:///com.example.notification.test");
    testPackage.getEClassifiers().add(valueHolderClass);
    EPackage.Registry.INSTANCE.put(testPackage.getNsURI(), testPackage);
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    valueHolderClass.getEStructuralFeatures().clear();
    valueHolderClass = null;
  }
}