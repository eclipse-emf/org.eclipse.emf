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
package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.w3c.dom.Document;


/**
 * This defines the methods for the interface that XMLResourceImpl
 * uses to save the resource.
 */
public interface XMLSave 
{

  /**
   * This defines the methods for the interface used to determine
   * whether type information should be saved when the
   * XMLResource.OPTION_SAVE_TYPE_INFORMATION save option is enabled.
   */
  interface XMLTypeInfo
  {

    /**
     * Determines whether type information should be saved for an object of the
     * specified type in a feature of the specified type.
     * 
     * @param objectType The object's type.
     * @param featureType The feature's type.
     * @param feature The feature in which the object is stored.
     * @return <code>true</code> if the type should be saved; <code>false</code>
     *         otherwise.
     */
    boolean shouldSaveType(EClass objectType, EClassifier featureType, EStructuralFeature feature);

    /**
     * Determines whether type information should be saved for an object of the
     * specified type in a feature of the specified type.
     * 
     * @param objectType The object's type.
     * @param featureType The feature's type.
     * @param feature The feature in which the object is stored.
     * @return <code>true</code> if the type should be saved; <code>false</code>
     *         otherwise.
     */
    boolean shouldSaveType(EClass objectType, EClass featureType, EStructuralFeature feature);

  }

  void save(XMLResource resource, OutputStream outputStream, Map<?, ?> options) throws IOException;

  /**
   * @param resource a resource 
   * @param document a {@link org.w3c.dom.Document} (must not be null)
   * @param options options
   * @param handler a {@link DOMHandler} (must not be null)
   * @return the document
   * @since 2.1.0
   */
  Document save(XMLResource resource, Document document, Map<?, ?> options, DOMHandler handler);
  
  /** Saves the resource to the writer using the specified options.
   * @param resource
   * @param writer
   * @param options
   * @throws IOException
   */
  void save(XMLResource resource, Writer writer, Map<?, ?> options) throws IOException;
}
