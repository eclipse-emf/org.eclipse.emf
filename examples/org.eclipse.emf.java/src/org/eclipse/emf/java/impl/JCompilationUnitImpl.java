/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JCompilationUnitImpl.java,v 1.2 2004/10/20 15:18:58 marcelop Exp $
 */
package org.eclipse.emf.java.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.eclipse.jdt.core.jdom.IDOMImport;
import org.eclipse.jdt.core.jdom.IDOMNode;
import org.eclipse.jdt.core.jdom.IDOMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JCompilationUnit;
import org.eclipse.emf.java.JPackage;
import org.eclipse.emf.java.JavaFactory;
import org.eclipse.emf.java.JavaPackage;
import org.eclipse.emf.java.util.JavaUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JCompilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.impl.JCompilationUnitImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JCompilationUnitImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JCompilationUnitImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JCompilationUnitImpl#getImportedPackages <em>Imported Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JCompilationUnitImpl#getImportedTypes <em>Imported Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JCompilationUnitImpl#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JCompilationUnitImpl extends JModelElementImpl implements JCompilationUnit
{
  /**
   * The cached value of the '{@link #getImports() <em>Imports</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImports()
   * @generated
   * @ordered
   */
  protected EList imports = null;

  /**
   * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected static final String COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected String comment = COMMENT_EDEFAULT;

  /**
   * The cached value of the '{@link #getTypes() <em>Types</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypes()
   * @generated
   * @ordered
   */
  protected EList types = null;

  /**
   * The cached value of the '{@link #getImportedPackages() <em>Imported Packages</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportedPackages()
   * @generated
   * @ordered
   */
  protected EList importedPackages = null;

  /**
   * The cached value of the '{@link #getImportedTypes() <em>Imported Types</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportedTypes()
   * @generated
   * @ordered
   */
  protected EList importedTypes = null;

  /**
   * The cached value of the '{@link #getPackage() <em>Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPackage()
   * @generated
   * @ordered
   */
  protected JPackage package_ = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JCompilationUnitImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return JavaPackage.eINSTANCE.getJCompilationUnit();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JPackage getPackage()
  {
    if (package_ != null && package_.eIsProxy())
    {
      JPackage oldPackage = package_;
      package_ = (JPackage)eResolveProxy((InternalEObject)package_);
      if (package_ != oldPackage)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaPackage.JCOMPILATION_UNIT__PACKAGE, oldPackage, package_));
      }
    }
    return package_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JPackage basicGetPackage()
  {
    return package_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPackage(JPackage newPackage)
  {
    JPackage oldPackage = package_;
    package_ = newPackage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCOMPILATION_UNIT__PACKAGE, oldPackage, package_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getImports()
  {
    if (imports == null)
    {
      imports = new EDataTypeUniqueEList(String.class, this, JavaPackage.JCOMPILATION_UNIT__IMPORTS);
    }
    return imports;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    String oldComment = comment;
    comment = newComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCOMPILATION_UNIT__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getTypes()
  {
    if (types == null)
    {
      types = new EObjectContainmentWithInverseEList(JClass.class, this, JavaPackage.JCOMPILATION_UNIT__TYPES, JavaPackage.JCLASS__UNIT);
    }
    return types;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getImportedPackages()
  {
    if (importedPackages == null)
    {
      importedPackages = new EObjectResolvingEList(JPackage.class, this, JavaPackage.JCOMPILATION_UNIT__IMPORTED_PACKAGES);
    }
    return importedPackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getImportedTypes()
  {
    if (importedTypes == null)
    {
      importedTypes = new EObjectResolvingEList(JClass.class, this, JavaPackage.JCOMPILATION_UNIT__IMPORTED_TYPES);
    }
    return importedTypes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case JavaPackage.JCOMPILATION_UNIT__TYPES:
          return ((InternalEList)getTypes()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case JavaPackage.JCOMPILATION_UNIT__TYPES:
          return ((InternalEList)getTypes()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case JavaPackage.JCOMPILATION_UNIT__NAME:
        return getName();
      case JavaPackage.JCOMPILATION_UNIT__JNODE:
        return getJNode();
      case JavaPackage.JCOMPILATION_UNIT__IMPORTS:
        return getImports();
      case JavaPackage.JCOMPILATION_UNIT__COMMENT:
        return getComment();
      case JavaPackage.JCOMPILATION_UNIT__TYPES:
        return getTypes();
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_PACKAGES:
        return getImportedPackages();
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_TYPES:
        return getImportedTypes();
      case JavaPackage.JCOMPILATION_UNIT__PACKAGE:
        if (resolve) return getPackage();
        return basicGetPackage();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case JavaPackage.JCOMPILATION_UNIT__NAME:
        setName((String)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__JNODE:
        setJNode((Object)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__IMPORTS:
        getImports().clear();
        getImports().addAll((Collection)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__COMMENT:
        setComment((String)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__TYPES:
        getTypes().clear();
        getTypes().addAll((Collection)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_PACKAGES:
        getImportedPackages().clear();
        getImportedPackages().addAll((Collection)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_TYPES:
        getImportedTypes().clear();
        getImportedTypes().addAll((Collection)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__PACKAGE:
        setPackage((JPackage)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case JavaPackage.JCOMPILATION_UNIT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case JavaPackage.JCOMPILATION_UNIT__JNODE:
        setJNode(JNODE_EDEFAULT);
        return;
      case JavaPackage.JCOMPILATION_UNIT__IMPORTS:
        getImports().clear();
        return;
      case JavaPackage.JCOMPILATION_UNIT__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case JavaPackage.JCOMPILATION_UNIT__TYPES:
        getTypes().clear();
        return;
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_PACKAGES:
        getImportedPackages().clear();
        return;
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_TYPES:
        getImportedTypes().clear();
        return;
      case JavaPackage.JCOMPILATION_UNIT__PACKAGE:
        setPackage((JPackage)null);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case JavaPackage.JCOMPILATION_UNIT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case JavaPackage.JCOMPILATION_UNIT__JNODE:
        return JNODE_EDEFAULT == null ? jNode != null : !JNODE_EDEFAULT.equals(jNode);
      case JavaPackage.JCOMPILATION_UNIT__IMPORTS:
        return imports != null && !imports.isEmpty();
      case JavaPackage.JCOMPILATION_UNIT__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case JavaPackage.JCOMPILATION_UNIT__TYPES:
        return types != null && !types.isEmpty();
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_PACKAGES:
        return importedPackages != null && !importedPackages.isEmpty();
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_TYPES:
        return importedTypes != null && !importedTypes.isEmpty();
      case JavaPackage.JCOMPILATION_UNIT__PACKAGE:
        return package_ != null;
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (imports: ");
    result.append(imports);
    result.append(", comment: ");
    result.append(comment);
    result.append(')');
    return result.toString();
  }

  protected void changeAttribute(Notification notification)
  {
    switch (notification.getFeatureID(JCompilationUnit.class))
    {
      case JavaPackage.JCOMPILATION_UNIT__JNODE:
      {
        JDOMHelper.handleJNode(this);
        resolveIdentifiers();
        break;
      }
      case JavaPackage.JCOMPILATION_UNIT__IMPORTS:
      {
        List theImportedPackages = new ArrayList();
        List theImportedTypes = new ArrayList();
        for (Iterator i = getImports().iterator(); i.hasNext(); )
        {
          String theImport = (String)i.next();
          if (theImport.endsWith(".*"))
          {
            theImportedPackages.add(JavaUtil.createJPackageProxy(theImport.substring(0, theImport.length() - 2)));
          }
          else
          {
            theImportedTypes.add(JavaUtil.createJClassProxy(theImport));
          }
        }

        EcoreUtil.setEList(getImportedPackages(), theImportedPackages);
        EcoreUtil.setEList(getImportedTypes(), theImportedTypes);

        break;
      }
    }
  }

  protected static class JDOMHelper
  {
    protected static void handleJNode(JCompilationUnit jCompilationUnit)
    {
      IDOMCompilationUnit iDOMCompilationUnit = (IDOMCompilationUnit)jCompilationUnit.getJNode();
      if (iDOMCompilationUnit != null)
      {
        jCompilationUnit.setName(iDOMCompilationUnit.getName());
        jCompilationUnit.setComment(iDOMCompilationUnit.getHeader());
        Collection theImports = new ArrayList();
        Collection theTypes = new ArrayList();
        for (IDOMNode child = iDOMCompilationUnit.getFirstChild(); child != null; child = child.getNextNode())
        {
          if (child.getNodeType() == IDOMNode.PACKAGE)
          {
            IDOMPackage iDOMPackage = (IDOMPackage)child;
            jCompilationUnit.setPackage(JavaUtil.createJPackageProxy(iDOMPackage.getName()));
          }
          else if (child.getNodeType() == IDOMNode.IMPORT)
          {
            theImports.add(((IDOMImport)child).getName());
          }
          else if (child.getNodeType() == IDOMNode.TYPE)
          {
            JClass jClass = JavaFactory.eINSTANCE.createJClass();
            jClass.setJNode(child);
            theTypes.add(jClass);
          }
        }

        if (jCompilationUnit.getPackage() == null)
        {
          jCompilationUnit.setPackage(JavaUtil.createJPackageProxy(""));
        }

        jCompilationUnit.getImports().addAll(theImports);
        jCompilationUnit.getTypes().addAll(theTypes);
      }
    }
  }

  public void resolveIdentifiers()
  {
    List theTypes = getTypes();
    if (!theTypes.isEmpty())
    {
      getPackage().getTypes().add(theTypes.get(0));
    }

    for (Iterator i = getTypes().iterator(); i.hasNext(); )
    {
      JModelElementImpl jModelElement = (JModelElementImpl)i.next();
      jModelElement.resolveIdentifiers();
    }

    for (Iterator i = getImportedTypes().iterator(); i.hasNext(); )
    {
      JClass jClass = (JClass)i.next();
    }
  }

  public JClass resolveJClass(String fullName)
  {
    ResourceSet resourceSet = eResource().getResourceSet();

    for (Iterator i = getTypes().iterator(); i.hasNext(); )
    {
      JClass jClass = (JClass)i.next();
      String name = jClass.getName();
      if (JavaUtil.isPrefixOf(name, fullName))
      {
        if (fullName.length() > name.length())
        {
          return jClass.resolveJClass(fullName.substring(name.length() + 1));
        }
        else
        {
          return jClass;
        }
      }
    }

    for (Iterator i = getImports().iterator(); i.hasNext(); )
    {
      String theImport = (String)i.next();
      if (theImport.endsWith(".*"))
      {
        JClass result = handlePackageImport(resourceSet, theImport.substring(0, theImport.length() - 2), fullName);
        if (result != null)
        {
          return result;
        }
      }
      else 
      {
        String className = fullName;
        int index = fullName.indexOf(".");
        if (index != -1)
        {
          className = fullName.substring(0, index);
        }
        String arrayIndices = "";
        int braceIndex = className.indexOf("[");
        if (braceIndex != -1)
        {
          arrayIndices = className.substring(braceIndex);
          className = className.substring(0, braceIndex);
        }
        if (theImport.endsWith(className))
        {
          int baseIndex = theImport.length() - className.length();
          if (baseIndex == 0 || theImport.charAt(baseIndex - 1) == '.')
          {
            if (index == -1)
            {
              return JavaUtil.createJClassProxy(theImport + arrayIndices);
            }
            else 
            {
              return 
                JavaUtil.createJClassProxy
                  (theImport.substring(0, baseIndex) + fullName.replace('.', '$') + arrayIndices);
            }
          }
        }
      }
    }

    JClass jClass = handlePackageImport(resourceSet, getPackage().getName(), fullName);
    if (jClass == null)
    {
      jClass = handlePackageImport(resourceSet, "java.lang", fullName);
      if (jClass == null)
      {
        for (int i = fullName.lastIndexOf("."); i != -1;  i = fullName.lastIndexOf(".", i - 1))
        {
          jClass = handlePackageImport(resourceSet, fullName.substring(0, i), fullName.substring(i + 1));
          if (jClass != null)
          {
            break;
          }
        }
      }
    }
    return jClass;
  }

  protected JClass handlePackageImport(ResourceSet resourceSet, String packageName, String fullName)
  {
    if (packageName == null)
    {
      packageName = "";
    } 
    else if (packageName.length() != 0)
    {
      packageName += '.';
    }
    String className = fullName;
    int index = fullName.indexOf(".");
    if (index != -1)
    {
      className = fullName.substring(0, index);
    }
    URI proxyURI = JavaUtil.createJClassProxyURI(packageName + className);
    JClass result = (JClass)resourceSet.getEObject(proxyURI, true);

    if (result != null && index != -1)
    {
      result = JavaUtil.createJClassProxy(packageName + fullName.replace('.','$'));
    }

    return result;
  }

} //JCompilationUnitImpl

