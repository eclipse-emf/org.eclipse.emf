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
package org.eclipse.emf.java.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.merge.java.facade.FacadeVisitor;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
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
  protected EList<String> imports;

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
  protected EList<JClass> types;

  /**
   * The cached value of the '{@link #getImportedPackages() <em>Imported Packages</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportedPackages()
   * @generated
   * @ordered
   */
  protected EList<JPackage> importedPackages;

  /**
   * The cached value of the '{@link #getImportedTypes() <em>Imported Types</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportedTypes()
   * @generated
   * @ordered
   */
  protected EList<JClass> importedTypes;

  /**
   * The cached value of the '{@link #getPackage() <em>Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPackage()
   * @generated
   * @ordered
   */
  protected JPackage package_;

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
  @Override
  protected EClass eStaticClass()
  {
    return JavaPackage.Literals.JCOMPILATION_UNIT;
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
      InternalEObject oldPackage = (InternalEObject)package_;
      package_ = (JPackage)eResolveProxy(oldPackage);
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
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case JavaPackage.JCOMPILATION_UNIT__TYPES:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getTypes()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case JavaPackage.JCOMPILATION_UNIT__TYPES:
        return ((InternalEList<?>)getTypes()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getImports()
  {
    if (imports == null)
    {
      imports = new EDataTypeUniqueEList<String>(String.class, this, JavaPackage.JCOMPILATION_UNIT__IMPORTS);
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
  public EList<JClass> getTypes()
  {
    if (types == null)
    {
      types = new EObjectContainmentWithInverseEList<JClass>(JClass.class, this, JavaPackage.JCOMPILATION_UNIT__TYPES, JavaPackage.JCLASS__UNIT);
    }
    return types;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<JPackage> getImportedPackages()
  {
    if (importedPackages == null)
    {
      importedPackages = new EObjectResolvingEList<JPackage>(JPackage.class, this, JavaPackage.JCOMPILATION_UNIT__IMPORTED_PACKAGES);
    }
    return importedPackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<JClass> getImportedTypes()
  {
    if (importedTypes == null)
    {
      importedTypes = new EObjectResolvingEList<JClass>(JClass.class, this, JavaPackage.JCOMPILATION_UNIT__IMPORTED_TYPES);
    }
    return importedTypes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
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
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case JavaPackage.JCOMPILATION_UNIT__IMPORTS:
        getImports().clear();
        getImports().addAll((Collection<? extends String>)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__COMMENT:
        setComment((String)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__TYPES:
        getTypes().clear();
        getTypes().addAll((Collection<? extends JClass>)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_PACKAGES:
        getImportedPackages().clear();
        getImportedPackages().addAll((Collection<? extends JPackage>)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__IMPORTED_TYPES:
        getImportedTypes().clear();
        getImportedTypes().addAll((Collection<? extends JClass>)newValue);
        return;
      case JavaPackage.JCOMPILATION_UNIT__PACKAGE:
        setPackage((JPackage)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
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
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
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
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
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

  @Override
  protected void changeAttribute(Notification notification)
  {
    switch (notification.getFeatureID(JCompilationUnit.class))
    {
      case JavaPackage.JCOMPILATION_UNIT__JNODE:
      {
        JHelper.handleJNode(this);
        resolveIdentifiers();
        break;
      }
      case JavaPackage.JCOMPILATION_UNIT__IMPORTS:
      {
        List<JPackage> theImportedPackages = new ArrayList<JPackage>();
        List<JClass> theImportedTypes = new ArrayList<JClass>();
        for (String theImport : getImports())
        {
          if (theImport.endsWith(".*"))
          {
            theImportedPackages.add(JavaUtil.createJPackageProxy(theImport.substring(0, theImport.length() - 2)));
          }
          else
          {
            theImportedTypes.add(JavaUtil.createJClassProxy(theImport));
          }
        }

        ECollections.setEList(getImportedPackages(), theImportedPackages);
        ECollections.setEList(getImportedTypes(), theImportedTypes);

        break;
      }
    }
  }

  protected static class JHelper
  {
    protected static void handleJNode(final JCompilationUnit compilationUnit)
    {
      org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit jCompilationUnit = (org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit)compilationUnit.getJNode();
      if (jCompilationUnit != null)
      {
        compilationUnit.setName(jCompilationUnit.getName());
        compilationUnit.setComment(jCompilationUnit.getHeader());
        final Collection<String> theImports = new ArrayList<String>();
        final Collection<JClass> theTypes = new ArrayList<JClass>();
        
        FacadeVisitor facadeVisitor = new FacadeVisitor()
        {
          @Override
          protected boolean visit(org.eclipse.emf.codegen.merge.java.facade.JPackage jPackage)
          {
            compilationUnit.setPackage(JavaUtil.createJPackageProxy(jPackage.getName()));
            return false;
          }
          
          @Override
          protected boolean visit(JImport jImport)
          {
            theImports.add(jImport.getName());
            return false;
          }
          
          @Override
          protected boolean visit(JAbstractType abstractType)
          {
            if (abstractType instanceof JType)
            {
              JClass cls = JavaFactory.eINSTANCE.createJClass();
              cls.setJNode(abstractType);
              theTypes.add(cls);              
            }
            return false;
          }
        };
        facadeVisitor.start(jCompilationUnit);

        if (compilationUnit.getPackage() == null)
        {
          compilationUnit.setPackage(JavaUtil.createJPackageProxy(""));
        }

        compilationUnit.getImports().addAll(theImports);
        compilationUnit.getTypes().addAll(theTypes);
      }
    }
  }

  @Override
  public void resolveIdentifiers()
  {
    for (JClass jClass : getTypes())
    {
      getPackage().getTypes().add(jClass);
      
      JModelElementImpl jModelElement = (JModelElementImpl)jClass;
      jModelElement.resolveIdentifiers();
    }

    for (Iterator<JClass> i = getImportedTypes().iterator(); i.hasNext(); )
    {
      i.next();
    }
  }

  public JClass resolveJClass(String fullName)
  {
    ResourceSet resourceSet = eResource().getResourceSet();

    for (JClass jClass : getTypes())
    {
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

    for (String theImport : getImports())
    {
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

