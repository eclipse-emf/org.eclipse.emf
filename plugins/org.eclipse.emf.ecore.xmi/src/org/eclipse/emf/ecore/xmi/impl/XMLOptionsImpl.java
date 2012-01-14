/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.EcoreBuilder;
import org.eclipse.emf.ecore.xmi.XMLOptions;


public class XMLOptionsImpl implements XMLOptions
{
  protected EcoreBuilder ecoreBuilder;

  protected Map<String, URI> externalSchemaLocation;

  protected boolean anyXML;

  protected boolean processSchemaLocations;

  public EcoreBuilder getEcoreBuilder()
  {
    return ecoreBuilder;
  }

  public Map<String, URI> getExternalSchemaLocations()
  {
    return externalSchemaLocation;
  }

  public boolean isProcessAnyXML()
  {
    return anyXML;
  }

  public boolean isProcessSchemaLocations()
  {
    return processSchemaLocations;
  }

  public void setEcoreBuilder(EcoreBuilder ecoreBuilder)
  {
    this.ecoreBuilder = ecoreBuilder;
  }

  public void setExternalSchemaLocations(Map<String, URI> schemaLocations)
  {
    this.externalSchemaLocation = schemaLocations;
  }

  public void setProcessAnyXML(boolean anyXML)
  {
    this.anyXML = anyXML;
  }

  public void setProcessSchemaLocations(boolean processSchemaLocations)
  {
    this.processSchemaLocations = processSchemaLocations;
  }

  @Override
  public int hashCode()
  {
    int hashCode = externalSchemaLocation != null ? externalSchemaLocation.hashCode() : 0;
    hashCode ^= (ecoreBuilder != null) ? ecoreBuilder.hashCode() : 0;
    return hashCode + (anyXML ? 1 : 0) + (processSchemaLocations ? 2 : 0);
  }
}
