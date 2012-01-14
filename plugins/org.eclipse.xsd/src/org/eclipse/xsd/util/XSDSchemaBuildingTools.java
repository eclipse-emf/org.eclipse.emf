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
package org.eclipse.xsd.util;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDInclude;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNotationDeclaration;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.XSDXPathDefinition;


/**
 * XSDSchemaBuildingTools is a collection of convenience methods 
 * for building simple schemas.  
 *
 * <p>These are suitable to show examples of how to build convenience 
 * methods to more easily manipulate components in a schema.  They also 
 * serve as useful examples of usage of the library's functions.</p>
 *
 * <p>Note that these methods are somewhat simplistic, especially 
 * in terms of support for namespaces.  To simplify the calling 
 * signatures, and to account for the 80% case where you don't have 
 * multiple namespaces, most method simply take a localName that's 
 * assumed to be correct in the current context.  However it should 
 * be simple to copy & paste these methods and add namespaceURI's to 
 * each method if desired.</p>
 *
 * @author Shane_Curcuru@us.ibm.com
 */
public abstract class XSDSchemaBuildingTools
{
  /**
   * Add named simpleTypeDefinition to the schema. 
   *
   * <p>This method shows the simplest way to add a global named 
   * XSDSimpleTypeDefinition to an existing schema object.</p>
   *
   * @param schema to add the simpleTypeDeclaration to
   * @param localName for the type
   * @param type localName of base of type within this schema
   * @param userInfo if non-null, will be added as a documentation
   * element in an annotation element 
   * @return the simpleTypeDefinition created, after having been 
   * added to the schema; null if any error occoured
   */
  public static XSDSimpleTypeDefinition addSimpleTypeDefinition(XSDSchema schema, String localName, String type, String userInfo)
  {
    if ((null == schema) || (null == localName) || (null == type))
    {
      throw new IllegalArgumentException("addSimpleTypeDefinition called with null schema/type/name");
    }

    // Get the factory and create the type
    XSDFactory xsdFactory = XSDFactory.eINSTANCE;
    XSDSimpleTypeDefinition simpleType = xsdFactory.createXSDSimpleTypeDefinition();

    // Set the name and set it's baseType by resolving 
    // a definition from the local schema
    simpleType.setName(localName); 
    simpleType.setBaseTypeDefinition(schema.resolveSimpleTypeDefinition(type));

    // Add the simpleTypeDefinition to the object
    schema.getContents().add(simpleType);

    // Optionally add an userInfo if asked, since this 
    // is a freqent operation as well; note that you must add 
    // the type to the schema *first* before adding this
    if (null != userInfo)
    {
      addUserInformation(simpleType, null, userInfo);
    }

    // Return the simpleTypeDefinition itself, so the user can 
    // make further modifications if desired
    return simpleType;
  }


  /**
   * Add a named complexTypeDefinition to a schema. 
   *
   * <p>This method shows the simplest way to add a global named 
   * XSDComplexTypeDefinition to an existing schema object. It 
   * also provides basic attribute and type support. Note that 
   * this method does not handle namespaces although that support 
   * would be simple to add.</p>
   *
   * @param schema to add the complexTypeDefinition to
   * @param localName localName for the type
   * @param type localName of base of type; in this method must be 
   * resolveable within this schema
   * @param attrs HashMap of attributes to add; keys are names 
   * and values are the type of each one; if null, none added
   * @param userInfo if non-null, will be added as a documentation
   * element in an annotation element 
   * @return the complexTypeDefinition created, after having been 
   * added to the schema; null if any error occoured
   */
  public static XSDComplexTypeDefinition addComplexTypeDefinition
    (XSDSchema schema, String localName, String type, HashMap<String, Object> attrs, String userInfo)
  {
    if ((null == schema) || (null == localName) || (null == type))
    {
      throw new IllegalArgumentException("addComplexTypeDefinition called with null schema/type/name");
    }

    // Get the factory and create the type
    XSDFactory xsdFactory = XSDFactory.eINSTANCE;

    // Create the type and set name, etc
    XSDComplexTypeDefinition complexType = xsdFactory.createXSDComplexTypeDefinition();
    complexType.setName(localName);
    complexType.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);

    // Add the complexType to the schema; it's typically a good 
    // practice to do this sooner rather than later
    schema.getContents().add(complexType);

    // Create simple anonymous type to extend
    XSDSimpleTypeDefinition anonSimpleType = xsdFactory.createXSDSimpleTypeDefinition();
    complexType.setBaseTypeDefinition
        (schema.resolveSimpleTypeDefinition(type));

    // Be sure to set the contents as well (obviously if this 
    // were a reference it would be different)
    complexType.setContent(anonSimpleType);

    // If asked, create a number of attributes on this type
    if (null != attrs)
    {
      for (Iterator<String> iter = attrs.keySet().iterator(); 
          iter.hasNext(); /* no-op */)
      {
        String attrName = iter.next();
        Object attrType = attrs.get(attrName);
        addAttributeDeclaration(complexType, attrName, attrType);
      }
    }

    // Optionally add an userInfo if asked, since this 
    // be a freqent operation as well; note that you must add 
    // the type to the schema *first* before adding this
    if (null != userInfo)
    {
      addUserInformation(complexType, null, userInfo);
    }

    // Return the complexTypeDefinition itself, so the user can 
    // make further modifications if desired
    return complexType;
  }


  /**
   * Add a an attribute declaration to a component. 
   *
   * <p>Either adds a global attributeDeclaration to the schema, 
   * or a specific one (and a basic useage thereof) to a complex 
   * type. Note that users must add any other constraints 
   * or the like themselves. This method also does not handle 
   * namespaces, although that support would be simple to add.</p>
   *
   * @param component to add annotation to; must be a XSDSchema or 
   * a XSDComplexTypeDefinition object
   * @param localName of the attributeDeclaration
   * @param type either an XSDAttributeDeclaration, in which case 
   * we set a ref= to it, or a String localName of the base type 
   * @return the attributeDeclaration created, after having been 
   * added to the schema; null if any error occoured
   */
  public static XSDAttributeDeclaration addAttributeDeclaration(XSDConcreteComponent component, String localName, Object type)
  {
    if ((null == component) || (null == localName) || (null == type))
    {
      throw new IllegalArgumentException("addAttributeDeclaration called with null component/type/name");
    }

    // Get the factory and create the attributeDeclaration itself
    XSDFactory xsdFactory = XSDFactory.eINSTANCE;
    XSDAttributeDeclaration attrDecl = xsdFactory.createXSDAttributeDeclaration();

    // Set it's localName
    attrDecl.setName(localName);

    // Set it's type depending on argument
    if (type instanceof XSDAttributeDeclaration)
    {
      attrDecl.setResolvedAttributeDeclaration((XSDAttributeDeclaration)type);
    }
    else if (type instanceof String)
    {
      // Note that this will only correctly resolve to a 
      // local name within this schema; most users would 
      // want to resolve from perhaps a namespace *and* 
      // a localName 
      attrDecl.setTypeDefinition
        (component.getSchema().resolveSimpleTypeDefinition((String)type));
    }
    else
    {
      throw new IllegalArgumentException("addAttributeDeclaration illegal type, is: " + type);
    }

    // Add the attribute to the component, depending on type
    if (component instanceof XSDSchema)
    {
      ((XSDSchema)component).getContents().add(attrDecl);
    }
    else if (component instanceof XSDComplexTypeDefinition)
    {
      // Note that complexTypes must have an attributeUse 
      // container for the attribute itself; we then add 
      // the attribute to the concrete contents of the type
      XSDAttributeUse attrUse = xsdFactory.createXSDAttributeUse();
      attrUse.setContent(attrDecl);
      ((XSDComplexTypeDefinition)component).getAttributeContents().add(attrUse);
    }
    else
    {
      throw new IllegalArgumentException("Unable to addAttributeDeclaration to type: " + component);
    }

    return attrDecl;
  }


  /**
   * Add a local annotation with userInfo to the given item. 
   *
   * <p>Note: We take an XSDConcreteComponent, however we must 
   * then cast it to one of the types that has a setAnnotation
   * call defined, since it doesn't have a clear 'parent' 
   * interface for annotations.</p>
   *
   * <p>Also note that UserInformation and ApplicationInformation 
   * objects can only be added <b>after</b> the parent of the 
   * annotation has been added to an XSDSchema object. This is 
   * because these objects are modeled in the concrete DOM 
   * layer only, and otherwise will throw a DOMException.<p>
   * 
   * @param component to add annotation to; may be any kind of 
   * XSDConcreteComponent object including an XSDSchema
   * @param sourceURI to set for the userInformation
   * @param text text to add as the userInformation 
   * (xsd:documentation) node to the annotation
   * @return the XSDAnnotation object created, after having been 
   * added to the component; null if any error occoured
   */
  public static XSDAnnotation addUserInformation(XSDConcreteComponent component, String sourceURI, String text)
  {
    // Note that null is a legal value for the sourceURI
    if ((null == component) || (null == text))
    {
      throw new IllegalArgumentException("addUserInformation called with null component or text");
    }
    try
    {
      // First get the factory from the component: this is 
      // roundabout, but saves the user from having to 
      // pass it in
      XSDFactory xsdFactory = XSDFactory.eINSTANCE;

      // Create an XSDAnnotation object to hold everything
      XSDAnnotation xsdAnnotation = xsdFactory.createXSDAnnotation();

      // Depending on the XSDConcreteComponent type, cast to
      // the appropriate type and add the annotation
      if (component instanceof XSDAttributeDeclaration)
      {
        ((XSDAttributeDeclaration)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDAttributeGroupDefinition)
      {
        ((XSDAttributeGroupDefinition)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDElementDeclaration)
      {
        ((XSDElementDeclaration)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDFacet)
      {
        ((XSDFacet)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDIdentityConstraintDefinition)
      {
        ((XSDIdentityConstraintDefinition)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDImport)
      {
        ((XSDImport)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDInclude)
      {
        ((XSDInclude)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDModelGroup)
      {
        ((XSDModelGroup)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDModelGroupDefinition)
      {
        ((XSDModelGroupDefinition)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDNotationDeclaration)
      {
        ((XSDNotationDeclaration)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDTypeDefinition)
      {
        ((XSDTypeDefinition)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDWildcard)
      {
        ((XSDWildcard)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDXPathDefinition)
      {
        ((XSDXPathDefinition)component).setAnnotation(xsdAnnotation);
      }
      else if (component instanceof XSDSchema)
      {
        // Note that this adds a global annotation to the 
        // schema itself, not to any subcomponent
        ((XSDSchema)component).getContents().add(xsdAnnotation);
      }
      else
      {
        // Whoops, asked us to annotate an unannotateable item
        throw new IllegalArgumentException("Unable to addUserInformation onto type: " + component);
      }

      // Now that the xsdAnnotation is added to a parent 
      // XSDConcreteComponent, go ahead and create the 
      // UserInformation node (xsd:documentation) and 
      // add a DOM textNode to it containing the information
      Element userInfo = xsdAnnotation.createUserInformation(sourceURI);
      userInfo.appendChild(userInfo.getOwnerDocument().createTextNode(text));

      // Add the finished userInfo object to the concrete 
      // element of the xsdAnnotation
      xsdAnnotation.getElement().appendChild(userInfo);
      return xsdAnnotation;
    } 
    catch (Exception e)
    {
      System.err.println("addUserInformation threw an Exception:");
      e.printStackTrace();
      return null;
    }
  }


  /**
   * Add a named modelGroup(Definition) to a container.  
   *
   * <p>This method creates a single XSDModelGroup that contains 
   * a list of XSDParticles for each item in the list.  It can 
   * simplify your code by performing the creation of each particle 
   * for each group item.  Note that this method does not set any 
   * additional items for the particles, like min/maxOccours etc.</p>
   *
   * <p>If passed an XSDSchema, we then add this as a global 
   * XSDModelGroupDefinition.  If passed an XSDModelGroup, add this to the contents,
   * If passed an XSDModelGroupDefinition, set this as the model group,
   * Otherwise, if passed an 
   * XSDComplexTypeDefinition we set this group to be the 
   * single content of the typedef.</p>
   *
   * @param component to add the modelGroupDefinition to
   * @param localName for the group
   * @param compositor to use for the group
   * @param groupTerms List of XSDTerm objects to put in the 
   * group, in list.iterator() order; we throw an exception 
   * if any of these objects are not valid
   * @return the modelGroup created, after having been 
   * added to the schema; null if any error occoured
   */
  public static XSDModelGroup addModelGroupDefinition(XSDConcreteComponent component, 
          String localName, XSDCompositor compositor, List<? extends XSDTerm> groupTerms)
  {
    if ((null == component) || (null == localName) || (null == groupTerms))
    {
      throw new IllegalArgumentException("addModelGroupDefinition called with null component/name/list");
    }

    // Get the factory and create the type
    XSDFactory xsdFactory = XSDFactory.eINSTANCE;
    XSDModelGroup modelGroup = xsdFactory.createXSDModelGroup();

    // Also set the compositor for the group itself
    modelGroup.setCompositor(compositor);

    // Iterate through the list, creating XSDParticles and 
    //  adding them to the group itself
    for (Iterator<? extends XSDTerm> terms = groupTerms.iterator();
            terms.hasNext(); 
            /*no-op */ )
    {
      Object tmp = terms.next();
      try
      {
        // Get each XSDTerm object (a wildcard, model group, 
        //  or element) and add it to a particle
        XSDTerm termItem = (XSDTerm)tmp;
        XSDParticle termParticle = xsdFactory.createXSDParticle();
        termParticle.setContent(termItem);
        // Add the particle to the modelGroup
        modelGroup.getContents().add(termParticle);
      }
      catch (ClassCastException cce)
      {
        throw new IllegalArgumentException("addModelGroupDefinition illegal list type: " + tmp);
      }
    }

    // Add the group either to a type by adding it as a 
    //  particle or to a schema by adding a definition
    if (component instanceof XSDComplexTypeDefinition)
    {
      XSDParticle modelGroupParticle = xsdFactory.createXSDParticle();
      modelGroupParticle.setContent(modelGroup);
      ((XSDComplexTypeDefinition)component).setContent(modelGroupParticle);
      
    }
    else if (component instanceof XSDSchema)
    {
      XSDModelGroupDefinition modelGroupDef = xsdFactory.createXSDModelGroupDefinition();
      modelGroupDef.setName(localName);
      modelGroupDef.setModelGroup(modelGroup);
      ((XSDSchema)component).getContents().add(modelGroupDef);
    }
    else if(component instanceof XSDModelGroupDefinition)
    {
      ((XSDModelGroupDefinition)component).setModelGroup(modelGroup);
    }
    else if(component instanceof XSDModelGroup)
    {
      XSDParticle particle = xsdFactory.createXSDParticle();
      particle.setContent(modelGroup);
      ((XSDModelGroup)component).getContents().add(particle);
    }
    else
    {
      throw new IllegalArgumentException("addModelGroupDefinition doesn't know how to add it to component: " + component);
    }


    // Return the modelGroup itself, so the user can 
    //  make further modifications if desired
    return modelGroup;
  }


  /**
   * Remove a named *TypeDefinition. 
   *
   * <p>This method shows the simplest way to remove a named 
   * type definition of any type (we automatically search for 
   * both simple and complex types). Note that removing or 
   * replacing an actual schema component requires working in 
   * the concrete model directly.</p>
   *
   * @param schema to remove the *TypeDefinition from
   * @param namespace for the type
   * @param localName for the type
   * @return true if successful; false otherwise
   */
  public static boolean removeTypeDefinition(XSDSchema schema, String namespace, String localName)
  {
    if ((null == schema) || (null == localName))
    {
      throw new IllegalArgumentException("removeTypeDefinition called with null schema/localName");
    }

    // Let the schema try to find an appropriate type definition 
    // for you - this will either return the actual type if it 
    // already exists, or a placeholder for one if it does not
    XSDTypeDefinition typeDefinition = 
        schema.resolveTypeDefinition(namespace, localName);

    // Get the specific container of the typeDefinition 
    XSDConcreteComponent container = typeDefinition.getContainer();

    // Check to ensure that this is a real type that lives in 
    // the schema, and not just a placeholder that was created
    if (null == container)
    {
      // It's just a placeholder; nothing to do
      return false;
    }
    // else, continue to remove the real component

    // Note that each type of container must be typecast to 
    // get the appropriate contents method
    if (container instanceof XSDSchema)
    {
      return ((XSDSchema)container).getContents().remove(typeDefinition);
    }
    else if (container instanceof XSDRedefine)
    {
      return ((XSDRedefine)container).getContents().remove(typeDefinition);
    }
    else
    {
      // NB: can any other objects be containers for 
      // typeDefinitions?
      return false;
    }

  }

  /**
   * Worker method to get a simple 'blank' schema. 
   *
   * <p>This creates a simple schema with just some namespace 
   * information attached. More detailed programs may also 
   * wish to set other schema attributes.</p>
   *
   * @param factory to create objects from; if null we will use 
   * our own sample {@link #getXSDFactory() getXSDFactory} method
   * @param targetPrefix to use
   * @param targetNS to use
   * @param annotationText to set as a documentation element
   * on an annotation (if null is not set)
   * @param annotationSource to use if annotationText is not null
   * @return false if we should abort the test; true otherwise
   */
  public static XSDSchema getBlankSchema
    (XSDFactory factory, String targetPrefix, String targetNS, String annotationText, String annotationSource)
  {
    if (factory == null)
    {
      factory = getXSDFactory();
    }
    XSDSchema schema = factory.createXSDSchema();

    // Set target namespace
    schema.setTargetNamespace(targetNS);

    // Choose the prefix used for this schema's namespace,
    // and the schema for schema's namespace
    schema.setSchemaForSchemaQNamePrefix("xsd");
    Map<String, String> namespaces = schema.getQNamePrefixToNamespaceMap();
    namespaces.put(targetPrefix, schema.getTargetNamespace());
    namespaces.put(schema.getSchemaForSchemaQNamePrefix(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);

    // Add an annotation if asked to
    if (null != annotationText)
    {
      addUserInformation(schema, annotationSource, annotationText); 
    }

    return schema;
  }
 
  /**
   * A cached XSDPackage: one per lifetime. 
   * @see #getXSDFactory()
   */
  protected static XSDPackage m_xsdPackage = null;

  /**
   * A cached XSDFactory: one per lifetime. 
   * @see #getXSDFactory()
   */
  protected static XSDFactory m_xsdFactory = null;

  /** 
   * Worker method to initialize various XSD and etools prereqs. 
   *
   * <p>This is SAMPLE CODE ONLY, SUBJECT TO CHANGE!</p>
   *
   * <p>The initialization sequence depends on some specific 
   * versions of Eclipse and may depend on how your application 
   * is run (as an Eclipse plugin or as a headless program, etc.). 
   * Note this caches the package and factory objects, and does 
   * not bother to re-call init() unless needed. This actual 
   * implementation is meant to be run as a standalone headless 
   * program although it may be suitable for other applications.</p>
   *  
   * @return an XSDFactory already initialized; or a cached one 
   * if we've been previously called
   */
  public static XSDFactory getXSDFactory()
  {
    // If cached, return previous factory
    if (null != m_xsdFactory)
    {
      return m_xsdFactory;
    }

    // This is needed because we can't have the following in the plugin.xml
    //
    //  <extension point = "com.eclipse.emf.ecore.extension_parser">
    //   <parser type="xsd" class="org.eclipse.xsd.util.XSDResourceFactoryImpl"/>
    //  </extension>
    //
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());

    // Also get the factory implementation here as well
    m_xsdPackage = XSDPackage.eINSTANCE;
    m_xsdFactory = XSDFactory.eINSTANCE;
    return m_xsdFactory;
  }
}
