/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd;


import java.util.List;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object 
 * '<a href="http://www.w3.org/TR/xmlschema-1/#cParticles"><em><b>Particle</b></em></a>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xsd.XSDParticle#getMinOccurs <em>Min Occurs</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDParticle#getMaxOccurs <em>Max Occurs</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDParticle#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.xsd.XSDParticle#getTerm <em>Term</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xsd.XSDPackage#getXSDParticle()
 * @model
 * @generated
 */
public interface XSDParticle extends XSDComplexTypeContent
{
  /**
   * Returns the value of the '<em><b>Min Occurs</b></em>' attribute.
   * The default value is <code>"1"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#p-min_occurs">min occurs</a>
   * infoset property.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Occurs</em>' attribute.
   * @see #isSetMinOccurs()
   * @see #unsetMinOccurs()
   * @see #setMinOccurs(int)
   * @see org.eclipse.xsd.XSDPackage#getXSDParticle_MinOccurs()
   * @model default="1" unsettable="true"
   * @generated
   */
  int getMinOccurs();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDParticle#getMinOccurs <em>Min Occurs</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Occurs</em>' attribute.
   * @see #isSetMinOccurs()
   * @see #unsetMinOccurs()
   * @see #getMinOccurs()
   * @generated
   */
  void setMinOccurs(int value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDParticle#getMinOccurs <em>Min Occurs</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetMinOccurs()
   * @see #getMinOccurs()
   * @see #setMinOccurs(int)
   * @generated
   */
  void unsetMinOccurs();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDParticle#getMinOccurs <em>Min Occurs</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Min Occurs</em>' attribute is set.
   * @see #unsetMinOccurs()
   * @see #getMinOccurs()
   * @see #setMinOccurs(int)
   * @generated
   */
  boolean isSetMinOccurs();

  /**
   * The value <code>-1</code> representing a max occurs of unbounded.
   * @see #getMaxOccurs
   */
  int UNBOUNDED = -1;

  /**
   * Returns the value of the '<em><b>Max Occurs</b></em>' attribute.
   * The default value is <code>"1"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#p-max_occurs">max occurs</a>
   * infoset property.
   * The <code>unbounded</code> value is represented by {@link #UNBOUNDED}, i.e., <code>-1</code>.
   * @see #UNBOUNDED
   * @
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Occurs</em>' attribute.
   * @see #isSetMaxOccurs()
   * @see #unsetMaxOccurs()
   * @see #setMaxOccurs(int)
   * @see org.eclipse.xsd.XSDPackage#getXSDParticle_MaxOccurs()
   * @model default="1" unsettable="true"
   * @generated
   */
  int getMaxOccurs();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDParticle#getMaxOccurs <em>Max Occurs</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Occurs</em>' attribute.
   * @see #isSetMaxOccurs()
   * @see #unsetMaxOccurs()
   * @see #getMaxOccurs()
   * @generated
   */
  void setMaxOccurs(int value);

  /**
   * Unsets the value of the '{@link org.eclipse.xsd.XSDParticle#getMaxOccurs <em>Max Occurs</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetMaxOccurs()
   * @see #getMaxOccurs()
   * @see #setMaxOccurs(int)
   * @generated
   */
  void unsetMaxOccurs();

  /**
   * Returns whether the value of the '{@link org.eclipse.xsd.XSDParticle#getMaxOccurs <em>Max Occurs</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Max Occurs</em>' attribute is set.
   * @see #unsetMaxOccurs()
   * @see #getMaxOccurs()
   * @see #setMaxOccurs(int)
   * @generated
   */
  boolean isSetMaxOccurs();

  /**
   * Returns the value of the '<em><b>Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * This concrete reference represents the {@link org.eclipse.xsd.XSDParticleContent particle content} of the particle.
   * It is used to compute the {@link #getTerm term}.
   * </p>
   * @see #getTerm()
   * @ <!-- end-user-doc -->
   * @return the value of the '<em>Content</em>' containment reference.
   * @see #setContent(XSDParticleContent)
   * @see org.eclipse.xsd.XSDPackage#getXSDParticle_Content()
   * @model containment="true" required="true"
   * @generated
   */
  XSDParticleContent getContent();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDParticle#getContent <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Content</em>' containment reference.
   * @see #getContent()
   * @generated
   */
  void setContent(XSDParticleContent value);

  /**
   * Returns the value of the '<em><b>Term</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * This represents the
   * <a href="http://www.w3.org/TR/xmlschema-1/#term">term</a>
   * infoset property.
   * It is computed from the {@link #getContent() content} and should typically not be modified directly.
   * </p>
   * @see #getContent()
   * @<!-- end-user-doc -->
   * @return the value of the '<em>Term</em>' reference.
   * @see #setTerm(XSDTerm)
   * @see org.eclipse.xsd.XSDPackage#getXSDParticle_Term()
   * @model resolveProxies="false" required="true"
   * @generated
   */
  XSDTerm getTerm();

  /**
   * Sets the value of the '{@link org.eclipse.xsd.XSDParticle#getTerm <em>Term</em>}' reference.
   * <!-- begin-user-doc -->
   * Since the term is computed from the {@link #getContent() content}, this reference should typically not be modified directly.
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Term</em>' reference.
   * @see #getTerm()
   * @generated
   */
  void setTerm(XSDTerm value);

  /**
   * A deterministic finite state automaton as described for 
   * '<a href="http://www.w3.org/TR/xmlschema-1/#non-ambig"><em><b>Unique Particle Attribution</b></em></a>'.
   */
  public interface DFA
  {
    /**
     * A state of a deterministic finite state automaton.
     */
    public interface State
    {
      /**
       * Returns whether this state is an accepting state.
       * @return whether this state is an accepting state.
       */
      boolean isAccepting();

      /**
       * Returns the list of transitions that leave this state.
       * @return the list of transitions that leave this state.
       */
      List<Transition> getTransitions();

      /**
       * Return the transition applicable for the given input.
       * An unrecognized input will yield <code>null</code>.
       * @return the transition applicable for the given input
       */
      Transition accept(String namespaceURI, String localName);
    }

    /**
     * A transition of a deterministic finite state automaton.
     */
    public interface Transition
    {
      /**
       * Returns the particle associated with this transition.
       * @return the particle associated with this transition.
       */
      XSDParticle getParticle();

      /**
       * Returns the target state of this transition.
       * @return the target state of this transition.
       */
      State getState();
    }

    /**
     * Returns whether this DFA is an approximation of the content model.
     * A large maxOccurs may be treated as unbounded 
     * and a large all model group may be treated as a repeating choice.
     * @return <code>true</code> if this DFA is an approximation of the content model.
     */
    boolean isApproximate();

    /**
     * Returns the list of states in this automaton.
     * @return the list of states in this automaton.
     */
    List<State> getStates();

    /**
     * Returns the initial state of this automaton.
     * @return the initial states in this automaton.
     */
    State getInitialState();

    /**
     * Creates a clone of the automaton.
     * @return a clone of the automaton.
     */
    DFA cloneDFA();
  }

  /**
   * Returns the deterministic finite state automaton that implements this particle's content model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the deterministic finite state automaton that implements this particle's content model.
   */
  DFA getDFA();

  /**
   * Returns whether the particle is
   * <a href="http://www.w3.org/TR/xmlschema-1/#cos-group-emptiable">emptiable</a>.
   * @return whether the particle is emptiable.
   */
  boolean isEmptiable();

  /**
   * This returns whether this particle accepts a subset of the content accepted by the other particle,
   * i.e., whether this is a 
   * <a href="http://www.w3.org/TR/xmlschema-1/#cos-particle-restrict">valid restriction</a>.
   * @param otherParticle some other particle.
   * @return whether this is a valid restriction.
   */
  boolean isSubset(XSDParticle otherParticle);
}
