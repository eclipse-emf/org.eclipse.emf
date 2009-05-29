/**
 * <copyright>
 * </copyright>
 *
 * $Id: CommitterShipImpl.java,v 1.1 2009/05/29 15:03:43 tschindl Exp $
 */
package org.eclipse.emf.examples.databinding.project.core.model.project.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.examples.databinding.project.core.model.project.CommitterShip;
import org.eclipse.emf.examples.databinding.project.core.model.project.Person;
import org.eclipse.emf.examples.databinding.project.core.model.project.Project;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Committer Ship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.CommitterShipImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.CommitterShipImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.CommitterShipImpl#getProject <em>Project</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.databinding.project.core.model.project.impl.CommitterShipImpl#getPerson <em>Person</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommitterShipImpl extends EObjectImpl implements CommitterShip
{
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
   * The cached value of the '{@link #getPerson() <em>Person</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPerson()
   * @generated
   * @ordered
   */
  protected Person person;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CommitterShipImpl()
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
    return ProjectPackage.Literals.COMMITTER_SHIP;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.COMMITTER_SHIP__START, oldStart, start));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.COMMITTER_SHIP__END, oldEnd, end));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Project getProject()
  {
    if (eContainerFeatureID() != ProjectPackage.COMMITTER_SHIP__PROJECT)
      return null;
    return (Project)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param newProject the new project
   * @param msgs the message chain
   * @return the modified notification chain
   * @generated
   */
  public NotificationChain basicSetProject(Project newProject, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newProject, ProjectPackage.COMMITTER_SHIP__PROJECT, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProject(Project newProject)
  {
    if (newProject != eInternalContainer() || (eContainerFeatureID() != ProjectPackage.COMMITTER_SHIP__PROJECT && newProject != null))
    {
      if (EcoreUtil.isAncestor(this, newProject))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newProject != null)
        msgs = ((InternalEObject)newProject).eInverseAdd(this, ProjectPackage.PROJECT__COMMITTERS, Project.class, msgs);
      msgs = basicSetProject(newProject, msgs);
      if (msgs != null)
        msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.COMMITTER_SHIP__PROJECT, newProject, newProject));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Person getPerson()
  {
    if (person != null && person.eIsProxy())
    {
      InternalEObject oldPerson = (InternalEObject)person;
      person = (Person)eResolveProxy(oldPerson);
      if (person != oldPerson)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProjectPackage.COMMITTER_SHIP__PERSON, oldPerson, person));
      }
    }
    return person;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the person
   * @generated
   */
  public Person basicGetPerson()
  {
    return person;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param newPerson the new person
   * @param msgs the message chain
   * @return the notification chain
   * @generated
   */
  public NotificationChain basicSetPerson(Person newPerson, NotificationChain msgs)
  {
    Person oldPerson = person;
    person = newPerson;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(
        this,
        Notification.SET,
        ProjectPackage.COMMITTER_SHIP__PERSON,
        oldPerson,
        newPerson);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPerson(Person newPerson)
  {
    if (newPerson != person)
    {
      NotificationChain msgs = null;
      if (person != null)
        msgs = ((InternalEObject)person).eInverseRemove(this, ProjectPackage.PERSON__COMMITTERSHIPS, Person.class, msgs);
      if (newPerson != null)
        msgs = ((InternalEObject)newPerson).eInverseAdd(this, ProjectPackage.PERSON__COMMITTERSHIPS, Person.class, msgs);
      msgs = basicSetPerson(newPerson, msgs);
      if (msgs != null)
        msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProjectPackage.COMMITTER_SHIP__PERSON, newPerson, newPerson));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ProjectPackage.COMMITTER_SHIP__PROJECT:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetProject((Project)otherEnd, msgs);
      case ProjectPackage.COMMITTER_SHIP__PERSON:
        if (person != null)
          msgs = ((InternalEObject)person).eInverseRemove(this, ProjectPackage.PERSON__COMMITTERSHIPS, Person.class, msgs);
        return basicSetPerson((Person)otherEnd, msgs);
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
      case ProjectPackage.COMMITTER_SHIP__PROJECT:
        return basicSetProject(null, msgs);
      case ProjectPackage.COMMITTER_SHIP__PERSON:
        return basicSetPerson(null, msgs);
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
      case ProjectPackage.COMMITTER_SHIP__PROJECT:
        return eInternalContainer().eInverseRemove(this, ProjectPackage.PROJECT__COMMITTERS, Project.class, msgs);
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
      case ProjectPackage.COMMITTER_SHIP__START:
        return getStart();
      case ProjectPackage.COMMITTER_SHIP__END:
        return getEnd();
      case ProjectPackage.COMMITTER_SHIP__PROJECT:
        return getProject();
      case ProjectPackage.COMMITTER_SHIP__PERSON:
        if (resolve)
          return getPerson();
        return basicGetPerson();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ProjectPackage.COMMITTER_SHIP__START:
        setStart((Date)newValue);
        return;
      case ProjectPackage.COMMITTER_SHIP__END:
        setEnd((Date)newValue);
        return;
      case ProjectPackage.COMMITTER_SHIP__PROJECT:
        setProject((Project)newValue);
        return;
      case ProjectPackage.COMMITTER_SHIP__PERSON:
        setPerson((Person)newValue);
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
      case ProjectPackage.COMMITTER_SHIP__START:
        setStart(START_EDEFAULT);
        return;
      case ProjectPackage.COMMITTER_SHIP__END:
        setEnd(END_EDEFAULT);
        return;
      case ProjectPackage.COMMITTER_SHIP__PROJECT:
        setProject((Project)null);
        return;
      case ProjectPackage.COMMITTER_SHIP__PERSON:
        setPerson((Person)null);
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
      case ProjectPackage.COMMITTER_SHIP__START:
        return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
      case ProjectPackage.COMMITTER_SHIP__END:
        return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
      case ProjectPackage.COMMITTER_SHIP__PROJECT:
        return getProject() != null;
      case ProjectPackage.COMMITTER_SHIP__PERSON:
        return person != null;
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
    result.append(" (start: ");
    result.append(start);
    result.append(", end: ");
    result.append(end);
    result.append(')');
    return result.toString();
  }

} //CommitterShipImpl
