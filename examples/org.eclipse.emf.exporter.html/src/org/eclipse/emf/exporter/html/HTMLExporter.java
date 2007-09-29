/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * $Id: HTMLExporter.java,v 1.7 2007/09/29 16:44:21 emerks Exp $
 */
package org.eclipse.emf.exporter.html;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.exporter.ModelExporter;

/**
 * <p>This example shows how the Export API and JET can be used to generate a
 * HTML documentation for Ecore packages.</p>
 * 
 * <p>As any example, this code was <b>NOT</b> extensively tested and is not
 * necessary to cover all the possible nuances of Ecore models.</p>
 *  
 * @since 2.2.0
 */
public class HTMLExporter extends ModelExporter
{
  private ModelExporter.ExportData exportData;
  private Map<EPackage, GenPackage> ePackageToGenPackage;
  
  private GenPackage currentGenPackage;
  private URI currentArtifactURI;
  
  @Override
  public String getID()
  {
    return "org.eclipse.emf.exporter.html";
  }
  
  @Override
  protected String getDefaultArtifactLocation(EPackage ePackage)
  {
    return getDefaultArtifactFileName(ePackage) + ".html";
  }
  
  @Override
  protected String doCheckEPackageArtifactLocation(String location, String packageName)
  {
    if (!location.endsWith(".html"))
    {
      return HTMLExporterPlugin.INSTANCE.getString("_UI_InvalidArtifactFileNameExtension_message");
    }
    return super.doCheckEPackageArtifactLocation(location, packageName);
  }
  
  @Override
  protected Diagnostic doExport(Monitor monitor, ModelExporter.ExportData exportData) throws Exception
  {
    this.exportData = exportData;
    List<GenPackage> entries = new ArrayList<GenPackage>(exportData.genPackageToArtifactURI.keySet());
    entries.addAll(exportData.referencedGenPackagesToArtifactURI.keySet());
    ePackageToGenPackage = new HashMap<EPackage, GenPackage>();
    for (GenPackage genPackage : entries)
    {
      ePackageToGenPackage.put(genPackage.getEcorePackage(), genPackage);
    }
    
    for (Map.Entry<GenPackage, URI> entry : exportData.genPackageToArtifactURI.entrySet())
    {
      currentGenPackage = entry.getKey();
      currentArtifactURI = entry.getValue();
      
      String content = new PackageHTML().generate(this);
      save(content);
    }
    return Diagnostic.OK_INSTANCE;
  }
  
  public GenPackage getCurrentGenPackage()
  {
    return currentGenPackage;
  }
  
  public URI getPackageArtifacttURI(EPackage ePackage)
  {
    GenPackage eClassifierGenPackage = ePackageToGenPackage.get(ePackage);
    if (eClassifierGenPackage != null)
    {
      URI artifactURI = exportData.genPackageToArtifactURI.get(eClassifierGenPackage);
      if (artifactURI == null)
      {
        artifactURI = exportData.referencedGenPackagesToArtifactURI.get(eClassifierGenPackage);
      }
      
      if (artifactURI != null)
      {
        return artifactURI.deresolve(currentArtifactURI);        
      }  
    }
    return null;
  }
  
  public String computeClassifierLabel(EClassifier classifier)
  {
    StringBuilder label = new StringBuilder();
    
    label.append("<a name=\"");
    label.append(classifier.getName());
    label.append("\">");
    label.append(classifier.getName());
    label.append("</a>");
    
    if (!classifier.getETypeParameters().isEmpty())
    {
      label.append("&lt;");
      for (Iterator<ETypeParameter> i = classifier.getETypeParameters().iterator(); i.hasNext();)
      {
        ETypeParameter typeParameter = i.next();
        label.append(computeTypeParameterLabel(typeParameter));
        if (i.hasNext())
        {
          label.append(", ");
        }
      }
      label.append("&gt;");
    }
    return label.toString();
  }
  
  protected String computeTypeParameterLabel(ETypeParameter typeParameter)
  {
    StringBuilder label = new StringBuilder();
    
    String name = typeParameter.getName();
    EObject container = typeParameter.eContainer();
    if (container instanceof EClassifier)
    {
      EClassifier classifier = (EClassifier)container;
      label.append("<a name=\"");
      label.append(classifier.getName());
      label.append("@@");
      label.append(name);
      label.append("\">");
      label.append(name);
      label.append("</a>");
    }
    else
    {
      label.append(name);
    }
    
    if (!typeParameter.getEBounds().isEmpty())
    {
      label.append(" extends ");
      for (Iterator<EGenericType> j = typeParameter.getEBounds().iterator(); j.hasNext();)
      {
        EGenericType bound = j.next();
        label.append(computeLabel(bound));            
        if (j.hasNext())
        {
          label.append(" &amp; ");
        }
      }
    }
    return label.toString();
  }
  
  
  
  public String computeLabel(EClassifier classifier)
  {
    String name = classifier.getName().trim();
    EPackage eClassifierEPackage = classifier.getEPackage();

    String classifierType = classifier instanceof EClass ?
      "Class" :
        classifier instanceof EEnum ?
          "Enumeration" : "DataType";
          
    if (getCurrentGenPackage().getEcorePackage() == eClassifierEPackage
        || getCurrentGenPackage().getEcorePackage().getNsURI().equals(eClassifierEPackage.getNsURI()))
    {
      return new StringBuffer()
        .append("<a href=\"#").append(name).append("\"")
        .append(" title=\"").append(classifierType).append(":").append(name)
        .append("\">")
        .append(name)
        .append("</a>")
        .toString(); 
    }
    
    URI packageArtifactURI = getPackageArtifacttURI(eClassifierEPackage);
    if (packageArtifactURI != null)
    {
      return new StringBuffer()
      .append("<a href=\"").append(packageArtifactURI.toString()).append("#").append(name).append("\"")
      .append(" title=\"").append(classifierType).append(":").append(name)
      .append("\">")
      .append(name)
      .append("</a>")
      .toString();
    }
    
    if (EcorePackage.eNS_URI.equals(eClassifierEPackage.getNsURI()))
    {
      return new StringBuffer()
      .append("<i><b>")
      .append(name)
      .append("</b></i>")
      .toString();        
    }
    
    return name;
  }
  
  protected String computeLabel(ENamedElement namedElement)
  {
    boolean addedHyperlink = false;
    StringBuilder label = new StringBuilder();
    
    String titleType = namedElement.eClass().getName().substring(1);
    String name = namedElement.getName();
    EObject container = namedElement.eContainer();
    if (container instanceof EClassifier)
    {
      String classifierLabel = computeLabel((EClassifier)container);
      
      int index = classifierLabel.indexOf("href=\"");
      if (index > 0)
      {
        index = classifierLabel.indexOf('\"', index+"href=\"".length());
        label.append(classifierLabel.substring(0, index));
        if (namedElement instanceof ETypeParameter)
        {
          label.append("@@");
        }
        else
        {
          label.append("@");
        }
        label.append(name);        
      }
      
      int currentPos = index;
      index = classifierLabel.indexOf("title=\"", currentPos);
      if (index > 0)
      {
        index += "title=\"".length();
        label.append(classifierLabel.substring(currentPos, index));
        label.append(titleType).append(":").append(name);
        currentPos = classifierLabel.indexOf('\"', index+1);        
      }
      
      index = classifierLabel.indexOf('>', currentPos);
      if (index > 0)
      {
        addedHyperlink = true;
        label.append(classifierLabel.substring(currentPos, ++index));        
      }
    }
    
    label.append(name);
    
    if (addedHyperlink)
    {
      label.append("</a>");
    }    
    return label.toString();
  }

  protected String computeLabel(EGenericType genericType)
  {
    EObject container = genericType.eContainer();
    if (container == null || !container.eIsSet(genericType.eContainingFeature()))
    {
      return computeLabel(genericType.getERawType());
    }
    else
    {
      StringBuilder label = new StringBuilder();
      if (genericType.getEClassifier() != null)
      {
        label.append(computeLabel(genericType.getERawType()));
        
        if (!genericType.getETypeArguments().isEmpty())
        {
          label.append("&lt;");
          for (Iterator<EGenericType> i = genericType.getETypeArguments().iterator(); i.hasNext();)
          {
            EGenericType typeArgument = i.next();
            label.append(computeLabel(typeArgument));
            if (i.hasNext())
            {
              label.append(", ");
            }
          }
          label.append("&gt;");
        }
      }
      else
      {
        ETypeParameter typeParameter = genericType.getETypeParameter();
        String name = typeParameter != null ? computeLabel(typeParameter) : "?";
        label.append(name);
        
        if (genericType.getELowerBound() != null)
        {
          label.append(" super ");
          label.append(computeLabel(genericType.getELowerBound()));
        }
        else if (genericType.getEUpperBound() != null)
        {
          label.append(" extends ");
          label.append(computeLabel(genericType.getEUpperBound()));
        }
      }
      return label.toString();
    }
  }
    
  protected String computeLabel(ETypedElement typedElement)
  {
    StringBuilder label = new StringBuilder();
    EGenericType genericType = typedElement.getEGenericType();
    if (genericType != null)
    {
      label.append(computeLabel(genericType));
      if (typedElement.isMany())
      {
        label.append("*");
      }
      label.append(" ");
    }
    
    String name = typedElement.getName();
    EObject container = typedElement.eContainer();
    if (container instanceof EClassifier)
    {
      EClassifier classifier = (EClassifier)container;
      label.append("<a name=\"");
      label.append(classifier.getName());
      label.append("@");
      label.append(name);
      label.append("\">");
      label.append(name);
      label.append("</a>");      
    }
    else
    {
      label.append(name);
    }
    return label.toString();
  }
  
  protected String computeLabel(EStructuralFeature feature)
  {
    StringBuilder label = new StringBuilder(computeLabel((ETypedElement)feature));
    if (feature.isDerived())
    {
      label.append(" /");
    }    
    return label.toString();
  }

  public String computeLabel(EReference reference)
  {
    StringBuilder label = new StringBuilder(computeLabel((EStructuralFeature)reference));
    if (!reference.getEKeys().isEmpty())
    {
      label.append("<ul><li>key(s): ");
      for (Iterator<EAttribute> i = reference.getEKeys().iterator(); i.hasNext();)
      {
        EAttribute attribute = i.next();
        label.append(computeLabel((ENamedElement)attribute));
        if (i.hasNext())
        {
          label.append(", ");
        }
      }
      label.append("</li></ul>");
    }
    return label.toString();
  }
  
  public String computeLabel(EOperation operation)
  {
    StringBuilder label = new StringBuilder();
    
    if (!operation.getETypeParameters().isEmpty())
    {
      label.append("&lt;");
      for (Iterator<ETypeParameter> i = operation.getETypeParameters().iterator(); i.hasNext(); )
      {
        ETypeParameter typeParameter = i.next();
        label.append(computeTypeParameterLabel(typeParameter));
        if (i.hasNext())
        {
          label.append(", ");
        }
      }
      label.append("&gt; ");
    }
    
    if (operation.getEGenericType() == null)
    {
      label.append("void ");
    }
    label.append(computeLabel((ETypedElement)operation));
    
    if (!operation.getEParameters().isEmpty())
    {
      label.append("(");      
      for (Iterator<EParameter> i = operation.getEParameters().iterator(); i.hasNext(); )
      {
        EParameter parameter = i.next();
        label.append(computeLabel(parameter));
        if (i.hasNext())
        {
          label.append(", ");
        }
      }
      label.append(")");
    }
    
    return label.toString();
  }
  
  public String computeLabel(EEnumLiteral enumLiteral)
  {
    StringBuilder label = new StringBuilder();
    label.append(enumLiteral.getName());
    return label.toString();
  }

  protected void save(String content) throws IOException
  {
    OutputStream outputStream = URIConverter.INSTANCE.createOutputStream(currentArtifactURI, null);
    outputStream.write(content.getBytes("UTF-8"));
    outputStream.close();
  }
}