/**
 * <copyright>
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDUtil.java,v 1.8 2009/04/08 17:44:46 emerks Exp $
 */
package org.eclipse.xsd.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.impl.XSDNamedComponentImpl;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 */
public final class XSDUtil extends XSDConstants
{
  /**
   * Returns the singleton instance of the schema for schemas.
   */
  public static XSDSchema getSchemaForSchema(String schemaForSchemaURI)
  {
    return XSDSchemaImpl.getSchemaForSchema(schemaForSchemaURI);
  }

  /**
   * A cross referencer that finds each usage of an EObject or collection of EObjects,
   * excluding uninteresting derived references.
   */
  public static class UsageCrossReferencer extends EcoreUtil.UsageCrossReferencer 
  {
    private static final long serialVersionUID = 1L;

    protected Collection<?> eObjectsOfInterest;

    protected UsageCrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    protected UsageCrossReferencer(Resource resource)
    {
      super(resource);
    }

    protected UsageCrossReferencer(ResourceSet resourceSet)
    {
      super(resourceSet);
    }

    protected UsageCrossReferencer(Collection<?> emfObjects)
    {
      super(emfObjects);
    }

    @Override
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return
         !eReference.isVolatile() &&
           eReference.isChangeable() &&
           eObjectsOfInterest.contains(crossReferencedEObject);
    }

    @Override
    protected Collection<EStructuralFeature.Setting> findUsage(EObject eObject)
    {
      eObjectsOfInterest = Collections.singleton(eObject);
      crossReference();
      this.eObjectsOfInterest = null;
      done();
      return getCollection(eObject);
    }

    @Override
    protected Map<EObject, Collection<EStructuralFeature.Setting>> findAllUsage(Collection<?> eObjectsOfInterest)
    {
      this.eObjectsOfInterest = eObjectsOfInterest;
      crossReference();
      this.eObjectsOfInterest = null;
      done();
      return this;
    }

    public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, EObject eObject)
    {
      return new UsageCrossReferencer(eObject).findUsage(eObjectOfInterest);
    }

    public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, Resource resource)
    {
      return new UsageCrossReferencer(resource).findUsage(eObjectOfInterest);
    }

    public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, ResourceSet resourceSet)
    {
      return new UsageCrossReferencer(resourceSet).findUsage(eObjectOfInterest);
    }

    public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, Collection<?> emfObjectsToSearch)
    {
      return new UsageCrossReferencer(emfObjectsToSearch).findUsage(eObjectOfInterest);
    }
  
    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, EObject eObject)
    {
      return new UsageCrossReferencer(eObject).findAllUsage(eObjectsOfInterest);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, Resource resource)
    {
      return new UsageCrossReferencer(resource).findAllUsage(eObjectsOfInterest);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, ResourceSet resourceSet)
    {
      return new UsageCrossReferencer(resourceSet).findAllUsage(eObjectsOfInterest);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, Collection<?> emfObjectsToSearch)
    {
      return new UsageCrossReferencer(emfObjectsToSearch).findAllUsage(eObjectsOfInterest);
    }
  }

  /**
   * A cross referencer that finds all XSDNamedComponents and each usage of an XSDNamedComponent.
   */
  public static class XSDNamedComponentCrossReferencer extends EcoreUtil.CrossReferencer 
  {
    private static final long serialVersionUID = 1L;

    protected XSDNamedComponentCrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    protected XSDNamedComponentCrossReferencer(Resource resource)
    {
      super(resource);
    }

    protected XSDNamedComponentCrossReferencer(ResourceSet resourceSet)
    {
      super(resourceSet);
    }

    protected XSDNamedComponentCrossReferencer(Collection<?> emfObjects)
    {
      super(emfObjects);
    }

    @Override
    protected boolean containment(EObject eObject)
    {
      // Create an empty setting collection for any named component.
      //
      if (eObject instanceof XSDNamedComponent)
      {
        getCollection(eObject);
      }
      return true;
    }
   
    @Override
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      // Add a setting for any named component in an interesting reference.
      //
      return
         !eReference.isVolatile() &&
           eReference.isChangeable() &&
           crossReferencedEObject instanceof XSDNamedComponent;
    }

    /**
     * Returns a map of all XSDNamedComponent cross references in the content tree.
     */
    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(EObject eObject)
    {
      XSDNamedComponentCrossReferencer result = new XSDNamedComponentCrossReferencer(eObject);
      result.crossReference();
      result.done();
      return result;
    }

    /**
     * Returns a map of all XSDNamedComponent cross references in the content tree.
     */
    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Resource resource)
    {
      XSDNamedComponentCrossReferencer result = new XSDNamedComponentCrossReferencer(resource);
      result.crossReference();
      result.done();
      return result;
    }

    /**
     * Returns a map of all XSDNamedComponent cross references in the content tree.
     */
    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(ResourceSet resourceSet)
    {
      XSDNamedComponentCrossReferencer result = new XSDNamedComponentCrossReferencer(resourceSet);
      result.crossReference();
      result.done();
      return result;
    }
  }

  /**
   * A cross referencer that finds each usage of URI or collection of URIs.
   */
  public static class URICrossReferencer extends EcoreUtil.CrossReferencer
  {
    private static final long serialVersionUID = 1L;

    protected String [] uris;

    protected URICrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    protected URICrossReferencer(Resource resource)
    {
      super(resource);
    }

    protected URICrossReferencer(ResourceSet resourceSet)
    {
      super(resourceSet);
    }

    protected URICrossReferencer(Collection<?> emfObjects)
    {
      super(emfObjects);
    }

    @Override
    protected boolean containment(EObject eObject)
    {
      // Create an empty setting collection for any named component.
      //
      if (eObject instanceof XSDNamedComponent)
      {
        XSDNamedComponent xsdNamedComponent = (XSDNamedComponent)eObject;
        for (int i = 0; i < uris.length; ++i)
        {
          String uri = uris[i];
          if (xsdNamedComponent.hasURI(uri))
          {
            getCollection(eObject);
            break;
          }
        }
      }
      return true;
    }
   
    @Override
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      if (crossReferencedEObject instanceof XSDNamedComponent && 
            !eReference.isVolatile() && 
            eReference.isChangeable())
      {
        XSDNamedComponent xsdNamedComponent = (XSDNamedComponent)crossReferencedEObject;
        for (int i = 0; i < uris.length; ++i)
        {
          String uri = uris[i];
          if (xsdNamedComponent.hasURI(uri))
          {
            return true;
          }
        }
      }

      return false;
    }

    protected Map<EObject, Collection<EStructuralFeature.Setting>> findURI(String uri)
    {
      uris = new String [] { uri };
      crossReference();
      uris = null;
      done();
      return this;
    }

    protected Map<EObject, Collection<EStructuralFeature.Setting>> findAllURI(Collection<String> uris)
    {
      this.uris = uris.toArray(new String [uris.size()]);
      crossReference();
      uris = null;
      done();
      return this;
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(String uri, EObject eObject)
    {
      return new URICrossReferencer(eObject).findURI(uri);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(String uri, Resource resource)
    {
      return new URICrossReferencer(resource).findURI(uri);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(String uri, ResourceSet resourceSet)
    {
      return new URICrossReferencer(resourceSet).findURI(uri);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(String uri, Collection<?> emfObjectsToSearch)
    {
      return new URICrossReferencer(emfObjectsToSearch).findURI(uri);
    }
  
    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<String> uris, EObject eObject)
    {
      return new URICrossReferencer(eObject).findAllURI(uris);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<String> uris, Resource resource)
    {
      return new URICrossReferencer(resource).findAllURI(uris);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<String> uris, ResourceSet resourceSet)
    {
      return new URICrossReferencer(resourceSet).findAllURI(uris);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<String> uris, Collection<?> emfObjectsToSearch)
    {
      return new URICrossReferencer(emfObjectsToSearch).findAllURI(uris);
    }
  }

  /**
   * This is a wrapper type for representing values of type hexBin or base64Binary.
   */
  public static interface ByteSequence
  {
    byte[] getBytes();
  }

  /**
   * Returns the matching named component in the sorted list of named components, 
   * or <code>null</null> if there isn't one.
   * This list is expected to be one of a {@link XSDSchema schema's} list of named components.
   * @param xsdNamedComponents a sorted lists of named components.
   * @param namespace the namespace to search
   * @param name the name to search
   * @return the matching component, if any.
   */
  public static XSDNamedComponent findInSortedList(List<? extends XSDNamedComponent> xsdNamedComponents, String namespace, String name)
  {
    return XSDNamedComponentImpl.findInSortedList(xsdNamedComponents, namespace, name);
  }

  /**
   * Checks the given element's content against the complex type with a given name retrieved from the given schema.
   * @param schema the schema used to retrieve the complex type definition.
   * @param complexTypeName the name of the complex type.
   * @param part annotation part.
   * @param anchor annotation anchor.
   * @param element the element whose content to check.
   * @return a list of partially populated XSDDiagnostics---the primary component is not set---or the empty list if the content is valid.
   * @since 2.5
   */
  public static List<XSDDiagnostic> checkComplexContent(XSDSchema schema, String complexTypeName, String part, String anchor, Element element)
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = schema.resolveComplexTypeDefinition(complexTypeName);
    return checkComplexContent(xsdComplexTypeDefinition, part, anchor, element);
  }

  /**
   * Checks the given element's content against the given complex type.
   * @param xsdComplexTypeDefinition the reference type.
   * @param part annotation part.
   * @param anchor annotation anchor.
   * @param element the element whose content to check.
   * @return a list of partially populated XSDDiagnostics---the primary component is not set---or the empty list if the content is valid.
   * @since 2.5
   */
  public static List<XSDDiagnostic> checkComplexContent(XSDComplexTypeDefinition xsdComplexTypeDefinition, String part, String anchor, Element element)
  {
    List<XSDDiagnostic> result = new ArrayList<XSDDiagnostic>();
    XSDParticle complexType = xsdComplexTypeDefinition.getComplexType();
    boolean mixed = xsdComplexTypeDefinition.isMixed();
    XSDParticle.DFA dfa = complexType.getDFA();
    XSDParticle.DFA.State state = dfa.getInitialState();
    XSDFactory xsdFactory = XSDPackage.eINSTANCE.getXSDFactory();
    boolean invalidContentDetected = false;
    for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
    {
      switch (child.getNodeType())
      {
        case Node.ELEMENT_NODE:
        {
          if (invalidContentDetected)
          {
            continue;
          }
          XSDParticle.DFA.Transition transition = state.accept(child.getNamespaceURI(), child.getLocalName());
          if (transition != null)
          {
            state = transition.getState();
          }
          else
          {
            XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
            xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
            xsdDiagnostic.setMessage
              (XSDPlugin.INSTANCE.getString
                 ("_UI_XSDError_message", 
                  new Object [] 
                  {
                    populateDiagnostic
                      (xsdDiagnostic,
                       "content-valid.1", 
                       new Object [] { XSDConstants.uri(child), xsdComplexTypeDefinition.getURI(), getExpected(state) })
                  }));
            xsdDiagnostic.setAnnotationURI(part + "#" + anchor);
            xsdDiagnostic.setNode(child);            
            result.add(xsdDiagnostic);
            invalidContentDetected = true;
          }
          break;
        }
        case Node.TEXT_NODE:
        case Node.CDATA_SECTION_NODE:
        {
          if (!mixed)
          {
            String text = child.getNodeValue();
            if (text != null)
            {
              for (int i = 0, length = text.length(); i < length; ++i)
              {
                char character = text.charAt(i);
                if (character != '\n' && character != '\r' && character != ' ' && character != '\t')
                {
                  XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
                  xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
                  xsdDiagnostic.setMessage
                    (XSDPlugin.INSTANCE.getString
                       ("_UI_XSDError_message", 
                        new Object [] 
                        {
                          populateDiagnostic
                            (xsdDiagnostic,
                             "content-valid.3", 
                             new Object [] { text.substring(i), xsdComplexTypeDefinition.getURI() })
                        }));
                  xsdDiagnostic.setAnnotationURI(part + "#" + anchor);
                  xsdDiagnostic.setNode(child);            
                  result.add(xsdDiagnostic);
                  break;
                }
              }
            }
          }
          break;
        }
      }
    }

    boolean incompleteContent = !invalidContentDetected && !state.isAccepting();
    if (incompleteContent)
    {
      XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
      xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
      xsdDiagnostic.setMessage
        (XSDPlugin.INSTANCE.getString
           ("_UI_XSDError_message", 
            new Object [] 
            { 
              populateDiagnostic
                (xsdDiagnostic,
                 "content-valid.2", 
                 new Object [] { xsdComplexTypeDefinition.getURI(), getExpected(state) }) 
            }));
      xsdDiagnostic.setAnnotationURI(part + "#" + anchor);
      xsdDiagnostic.setNode(element);   
      result.add(xsdDiagnostic);
    }
    return result;
  }

  private static String getExpected(XSDParticle.DFA.State state)
  {
    StringBuffer result = new StringBuffer();
    for (XSDParticle.DFA.Transition transition : state.getTransitions())
    {
      XSDParticle xsdParticle = transition.getParticle();
      XSDTerm xsdTerm = xsdParticle.getTerm();
      if (xsdTerm instanceof XSDElementDeclaration)
      {
        XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;
        if (result.length() != 0)
        {
          result.append(" | ");
        }
        result.append(xsdElementDeclaration.getName());
      }
      else if (xsdTerm instanceof XSDWildcard)
      {
        XSDWildcard xsdWildcard = (XSDWildcard)xsdTerm;
        if (result.length() != 0)
        {
          result.append(" | ");
        }
        result.append(xsdWildcard.getStringLexicalNamespaceConstraint());
      }
    }

    if (state.isAccepting()) 
    {
      if (result.length() != 0)
      {
        result.append(" | ");
      }

      result.append(XSDPlugin.INSTANCE.getString("expecting_nothing"));
    }

    return result.length() == 0 ? XSDPlugin.INSTANCE.getString("expecting_nothing") : result.toString();
  }

  private static String populateDiagnostic(XSDDiagnostic xsdDiagnostic, String key, Object [] substitutions)
  {
    xsdDiagnostic.setKey(key);
    if (substitutions != null)
    {
      List<String> values = xsdDiagnostic.getSubstitutions();
      for (int i = 0; i < substitutions.length; ++i)
      {
        Object value = substitutions[i];
        values.add(value == null ? null : value.toString());
      }
      return XSDPlugin.INSTANCE.getString(key, substitutions);
    }
    else
    {
      return XSDPlugin.INSTANCE.getString(key);
    }
  }
}
