/**
 * <copyright>
 * </copyright>
 *
 * $Id: ProjectImpl.java,v 1.1 2009/05/29 15:03:43 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core.model.project.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;
import org.eclipse.emf.examples.databinding.project.core.model.project.Project;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl#getShortname <em>Shortname</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl#getSubprojects <em>Subprojects</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl#getCommitters <em>Committers</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl#getProjectleads <em>Projectleads</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.ProjectImpl#getLongname <em>Longname</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProjectImpl extends EObjectImpl implements Project
{
  /**
   * The default value of the '{@link #getShortname() <em>Shortname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShortname()
   * @generated
   * @ordered
   */
  protected static final String SHORTNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getShortname() <em>Shortname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShortname()
   * @generated
   * @ordered
   */
  protected String shortname = SHORTNAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getSubprojects() <em>Subprojects</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubprojects()
   * @generated
   * @ordered
   */
  protected EList<Project> subprojects;

  /**
   * The cached value of the '{@link #getCommitters() <em>Committers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommitters()
   * @generated
   * @ordered
   */
  protected EList<CommitterShip> committers;

  /**
   * The cached value of the '{@link #getProjectleads() <em>Projectleads</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProjectleads()
   * @generated
   * @ordered
   */
  protected EList<Person> projectleads;

  /**
   * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStart()
   * @generated
   * @ordered
   */
  protected static final Date START_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStart()
   * @generated
   * @ordered
   */
  protected Date start = START_EDEFAULT;

  /**
   * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected static final Date END_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected Date end = END_EDEFAULT;

  /**
   * The default value of the '{@link #getLongname() <em>Longname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLongname()
   * @generated
   * @ordered
   */
  protected static final String LONGNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLongname() <em>Longname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLongname()
   * @generated
   * @ordered
   */
  protected String longname = LONGNAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProjectImpl()
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
    return ProjectPackage.Literals.PROJECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getShortname()
  {
    return shortname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setShortname(String newShortname)
  {
    String oldShortname = shortname;
    shortname = newShortname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.PROJECT__SHORTNAME, oldShortname, shortname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Project> getSubprojects()
  {
    if (subprojects == null)
    {
      subprojects = new EObjectContainmentWithInverseEList<Project>(
        Project.class,
        this,
        ProjectPackage.PROJECT__SUBPROJECTS,
        ProjectPackage.PROJECT__PARENT);
    }
    return subprojects;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<CommitterShip> getCommitters()
  {
    if (committers == null)
    {
      committers = new EObjectContainmentWithInverseEList<CommitterShip>(
        CommitterShip.class,
        this,
        ProjectPackage.PROJECT__COMMITTERS,
        ProjectPackage.COMMITTER_SHIP__PROJECT);
    }
    return committers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Project getParent()
  {
    if (eContainerFeatureID() != ProjectPackage.PROJECT__PARENT)
      return null;
    return (Project)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param newParent the new parent
   * @param msgs the message chain
   * @return the message chain
   * @generated
   */
  public NotificationChain basicSetParent(Project newParent, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newParent, ProjectPackage.PROJECT__PARENT, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParent(Project newParent)
  {
    if (newParent != eInternalContainer() || (eContainerFeatureID() != ProjectPackage.PROJECT__PARENT && newParent != null))
    {
      if (EcoreUtil.isAncestor(this, newParent))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newParent != null)
        msgs = ((InternalEObject)newParent).eInverseAdd(this, ProjectPackage.PROJECT__SUBPROJECTS, Project.class, msgs);
      msgs = basicSetParent(newParent, msgs);
      if (msgs != null)
        msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.PROJECT__PARENT, newParent, newParent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Person> getProjectleads()
  {
    if (projectleads == null)
    {
      projectleads = new EObjectResolvingEList<Person>(Person.class, this, ProjectPackage.PROJECT__PROJECTLEADS);
    }
    return projectleads;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Date getStart()
  {
    return start;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStart(Date newStart)
  {
    Date oldStart = start;
    start = newStart;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.PROJECT__START, oldStart, start));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Date getEnd()
  {
    return end;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnd(Date newEnd)
  {
    Date oldEnd = end;
    end = newEnd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.PROJECT__END, oldEnd, end));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLongname()
  {
    return longname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLongname(String newLongname)
  {
    String oldLongname = longname;
    longname = newLongname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.PROJECT__LONGNAME, oldLongname, longname));
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
      case ProjectPackage.PROJECT__SUBPROJECTS:
        return ((InternalEList<InternalEObject>)(InternalEList< ? >)getSubprojects()).basicAdd(otherEnd, msgs);
      case ProjectPackage.PROJECT__COMMITTERS:
        return ((InternalEList<InternalEObject>)(InternalEList< ? >)getCommitters()).basicAdd(otherEnd, msgs);
      case ProjectPackage.PROJECT__PARENT:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetParent((Project)otherEnd, msgs);
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
      case ProjectPackage.PROJECT__SUBPROJECTS:
        return ((InternalEList< ? >)getSubprojects()).basicRemove(otherEnd, msgs);
      case ProjectPackage.PROJECT__COMMITTERS:
        return ((InternalEList< ? >)getCommitters()).basicRemove(otherEnd, msgs);
      case ProjectPackage.PROJECT__PARENT:
        return basicSetParent(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case ProjectPackage.PROJECT__PARENT:
        return eInternalContainer().eInverseRemove(this, ProjectPackage.PROJECT__SUBPROJECTS, Project.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case ProjectPackage.PROJECT__SHORTNAME:
        return getShortname();
      case ProjectPackage.PROJECT__SUBPROJECTS:
        return getSubprojects();
      case ProjectPackage.PROJECT__COMMITTERS:
        return getCommitters();
      case ProjectPackage.PROJECT__PARENT:
        return getParent();
      case ProjectPackage.PROJECT__PROJECTLEADS:
        return getProjectleads();
      case ProjectPackage.PROJECT__START:
        return getStart();
      case ProjectPackage.PROJECT__END:
        return getEnd();
      case ProjectPackage.PROJECT__LONGNAME:
        return getLongname();
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
      case ProjectPackage.PROJECT__SHORTNAME:
        setShortname((String)newValue);
        return;
      case ProjectPackage.PROJECT__SUBPROJECTS:
        getSubprojects().clear();
        getSubprojects().addAll((Collection< ? extends Project>)newValue);
        return;
      case ProjectPackage.PROJECT__COMMITTERS:
        getCommitters().clear();
        getCommitters().addAll((Collection< ? extends CommitterShip>)newValue);
        return;
      case ProjectPackage.PROJECT__PARENT:
        setParent((Project)newValue);
        return;
      case ProjectPackage.PROJECT__PROJECTLEADS:
        getProjectleads().clear();
        getProjectleads().addAll((Collection< ? extends Person>)newValue);
        return;
      case ProjectPackage.PROJECT__START:
        setStart((Date)newValue);
        return;
      case ProjectPackage.PROJECT__END:
        setEnd((Date)newValue);
        return;
      case ProjectPackage.PROJECT__LONGNAME:
        setLongname((String)newValue);
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
      case ProjectPackage.PROJECT__SHORTNAME:
        setShortname(SHORTNAME_EDEFAULT);
        return;
      case ProjectPackage.PROJECT__SUBPROJECTS:
        getSubprojects().clear();
        return;
      case ProjectPackage.PROJECT__COMMITTERS:
        getCommitters().clear();
        return;
      case ProjectPackage.PROJECT__PARENT:
        setParent((Project)null);
        return;
      case ProjectPackage.PROJECT__PROJECTLEADS:
        getProjectleads().clear();
        return;
      case ProjectPackage.PROJECT__START:
        setStart(START_EDEFAULT);
        return;
      case ProjectPackage.PROJECT__END:
        setEnd(END_EDEFAULT);
        return;
      case ProjectPackage.PROJECT__LONGNAME:
        setLongname(LONGNAME_EDEFAULT);
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
      case ProjectPackage.PROJECT__SHORTNAME:
        return SHORTNAME_EDEFAULT == null ? shortname != null : !SHORTNAME_EDEFAULT.equals(shortname);
      case ProjectPackage.PROJECT__SUBPROJECTS:
        return subprojects != null && !subprojects.isEmpty();
      case ProjectPackage.PROJECT__COMMITTERS:
        return committers != null && !committers.isEmpty();
      case ProjectPackage.PROJECT__PARENT:
        return getParent() != null;
      case ProjectPackage.PROJECT__PROJECTLEADS:
        return projectleads != null && !projectleads.isEmpty();
      case ProjectPackage.PROJECT__START:
        return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
      case ProjectPackage.PROJECT__END:
        return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
      case ProjectPackage.PROJECT__LONGNAME:
        return LONGNAME_EDEFAULT == null ? longname != null : !LONGNAME_EDEFAULT.equals(longname);
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
    if (eIsProxy())
      return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (shortname: ");
    result.append(shortname);
    result.append(", start: ");
    result.append(start);
    result.append(", end: ");
    result.append(end);
    result.append(", longname: ");
    result.append(longname);
    result.append(')');
    return result.toString();
  }

} //ProjectImpl
