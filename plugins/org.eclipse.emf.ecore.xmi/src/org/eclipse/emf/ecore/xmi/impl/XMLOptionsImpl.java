package org.eclipse.emf.ecore.xmi.impl;


import java.util.Map;

import org.eclipse.emf.ecore.xmi.EcoreBuilder;
import org.eclipse.emf.ecore.xmi.XMLOptions;


public class XMLOptionsImpl implements XMLOptions
{
  protected EcoreBuilder ecoreBuilder;

  protected Map externalSchemaLocation;

  protected boolean anyXML;

  protected boolean processSchemaLocations;

  public EcoreBuilder getEcoreBuilder()
  {
    return ecoreBuilder;
  }

  public Map getExternalSchemaLocations()
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

  public int hashCode()
  {
    int hashCode = externalSchemaLocation != null ? externalSchemaLocation.hashCode() : 0;
    hashCode ^= (ecoreBuilder != null) ? ecoreBuilder.hashCode() : 0;
    return hashCode + (anyXML ? 1 : 0) + (processSchemaLocations ? 2 : 0);
  }
}
