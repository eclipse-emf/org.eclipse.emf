package org.eclipse.emf.ecore.xmi.impl;


import java.util.Map;

import org.eclipse.emf.ecore.xmi.EcoreBuilder;
import org.eclipse.emf.ecore.xmi.XMLOptions;


public class XMLOptionsImpl implements XMLOptions
{
  EcoreBuilder ecoreBuilder = null;

  Map externalSchemaLocation = null;

  boolean anyXML = false;

  private boolean processSchemaLocations = false;

  public EcoreBuilder getEcoreBuilder()
  {
    return ecoreBuilder;
  }

  public Map getExternalSchemaLocations()
  {
    return externalSchemaLocation;
  }

  public boolean getProcessAnyXML()
  {
    return anyXML;
  }

  public boolean getProcessSchemaLocations()
  {
    return processSchemaLocations;
  }

  public void setEcoreBuilder(EcoreBuilder ecoreBuilder)
  {
    this.ecoreBuilder = ecoreBuilder;
  }

  public void setExternalSchemaLocations(Map schemaLocations)
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

}
