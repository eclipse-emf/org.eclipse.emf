/**
 * Copyright (c) 2006-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.exporter.html;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
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
  public static String escape(String string)
  {
    return string == null ? 
      "" : 
      string.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
  }
  
  private ModelExporter.ExportData exportData;
  private Map<EPackage, GenPackage> ePackageToGenPackage;
  
  private GenPackage currentGenPackage;
  private URI currentArtifactURI;
  
  private List<EStructuralFeature> defaultEStructuralFeatureDetails;
  
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
  
  /**
   * Returns the list of 'details', ie {@link EStructuralFeature}s, that should 
   * presented for each {@link EClass}. 
   * @return a list of {@link EStructuralFeature}s
   */
  public List<EStructuralFeature> getDetails(EClass eClass)
  {
    List<EStructuralFeature> details = new ArrayList<EStructuralFeature>();
    details.add(EcorePackage.Literals.ECLASS__ABSTRACT);
    details.add(EcorePackage.Literals.ECLASS__INTERFACE);
    return details;
  }
  
  /**
   * Returns the list of 'details', ie {@link EStructuralFeature}s, that should 
   * presented for each {@link EDataType}. 
   * @return a list of {@link EStructuralFeature}s
   */
  public List<EStructuralFeature> getDetails(EDataType dataType)
  {
    List<EStructuralFeature> details = new ArrayList<EStructuralFeature>();
    details.add(EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME);
    details.add(EcorePackage.Literals.ECLASSIFIER__INSTANCE_TYPE_NAME);
    details.add(EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE);
    return details;
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
      EClassifier classifier = (EClassifier)container;
      String classifierLabel = computeLabel(classifier);
      
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
        label.append(titleType).append(':').append(classifier.getName()).append('.').append(name);
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
    
  public String computeTypedElementLabel(ETypedElement typedElement)
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
  
  /**
   * Returns the list of 'details', ie {@link EStructuralFeature}s, that should 
   * presented for each {@link EAttribute}. 
   * @return a list of {@link EStructuralFeature}s
   */
  public List<EStructuralFeature> getDetails(EAttribute attribute)
  {
    List<EStructuralFeature> details = new ArrayList<EStructuralFeature>(getDefaultEStructuralFeatureDetails());
    details.add(EcorePackage.Literals.EATTRIBUTE__ID);
    return details;
  }

  /**
   * Returns the list of 'details', ie {@link EStructuralFeature}s, that should 
   * presented for each {@link EReference}. 
   * @return a list of {@link EStructuralFeature}s
   */
  public List<EStructuralFeature> getDetails(EReference reference)
  {
    List<EStructuralFeature> details = new ArrayList<EStructuralFeature>(getDefaultEStructuralFeatureDetails());
    details.add(0,EcorePackage.Literals.EREFERENCE__CONTAINMENT);
    details.add(1,EcorePackage.Literals.EREFERENCE__CONTAINER);
    details.add(2,EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES);
    return details;
  }
  
  protected List<EStructuralFeature> getDefaultEStructuralFeatureDetails()
  {
    if (defaultEStructuralFeatureDetails == null)
    {
      defaultEStructuralFeatureDetails = new ArrayList<EStructuralFeature>();
      defaultEStructuralFeatureDetails.add(EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND);
      defaultEStructuralFeatureDetails.add(EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND);
      defaultEStructuralFeatureDetails.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__CHANGEABLE);
      defaultEStructuralFeatureDetails.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE);
      defaultEStructuralFeatureDetails.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED);
      defaultEStructuralFeatureDetails.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT);
      defaultEStructuralFeatureDetails.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE);
      defaultEStructuralFeatureDetails.add(EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE);
    }
    return Collections.unmodifiableList(defaultEStructuralFeatureDetails);    
  }

  public String computeEKeys(EReference reference)
  {
    if (!reference.getEKeys().isEmpty())
    {
      StringBuilder label = new StringBuilder();
      for (Iterator<EAttribute> i = reference.getEKeys().iterator(); i.hasNext();)
      {
        EAttribute attribute = i.next();
        label.append(", ").append(computeLabel(attribute));
      }
      return label.substring(", ".length());
    }
    else
    {
      return "";
    }
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
    label.append(computeTypedElementLabel(operation));
    
    if (!operation.getEParameters().isEmpty())
    {
      label.append("(");      
      for (Iterator<EParameter> i = operation.getEParameters().iterator(); i.hasNext(); )
      {
        EParameter parameter = i.next();
        label.append(computeTypedElementLabel(parameter));
        if (i.hasNext())
        {
          label.append(", ");
        }
      }
      label.append(")");
    }

    if (!operation.getEExceptions().isEmpty())
    {
      label.append(" throws ");      
      for (Iterator<EClassifier> i = operation.getEExceptions().iterator(); i.hasNext(); )
      {
        EClassifier exception = i.next();
        label.append(computeLabel(exception));
        if (i.hasNext())
        {
          label.append(", ");
        }
      }
    }
    
    return label.toString();
  }
  
  /**
   * Returns the list of 'details', ie {@link EStructuralFeature}s, that should 
   * presented for each {@link EOperation}. 
   * @return a list of {@link EStructuralFeature}s
   */
  public List<EStructuralFeature> getDetails(EOperation operation)
  {
    List<EStructuralFeature> details = new ArrayList<EStructuralFeature>();
    details.add(EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND);
    details.add(EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND);
    return details;
  }
  
  public String computeLabel(EEnumLiteral enumLiteral)
  {
    StringBuilder label = new StringBuilder();
    label.append(enumLiteral.getName());
    return label.toString();
  }
  
  /**
   * Returns the list of 'details', ie {@link EStructuralFeature}s, that should 
   * presented for each {@link EEnumLiteral}. 
   * @return a list of {@link EStructuralFeature}s
   */
  public List<EStructuralFeature> getDetails(EEnumLiteral enumLiteral)
  {
    List<EStructuralFeature> details = new ArrayList<EStructuralFeature>();
    details.add(EcorePackage.Literals.EENUM_LITERAL__LITERAL);
    details.add(EcorePackage.Literals.EENUM_LITERAL__VALUE);
    return details;
  }

  protected void save(String content) throws IOException
  {
    OutputStream outputStream = URIConverter.INSTANCE.createOutputStream(currentArtifactURI, null);
    outputStream.write(content.getBytes("UTF-8"));
    outputStream.close();
  }
  
  /**
   * Returns a text that is suitable for displaying in HTML. This method analyzes the specified
   * <code>string</code>, checking whether it is already formated for HTML. If it is not, it replaces
   * line breaks by &lt;br /&gt;.
   * @param string the string to be analyzed
   * @return a text that is suitable for displaying in HTML
   */
  public String getLongText(String string)
  {
    if (string == null || string.length() == 0)
    {
      return "";
    }
    
    // This is certainly not the most optimized way to do this...
    //
    if (string.contains("<p>") || 
        string.contains("<br/>") || string.contains("<br />") ||
        string.contains("<i>") || string.contains("<b>") ||
        string.contains("<ul>") || string.contains("<ol>"))
    {
      return string;
    }
    
    
    // ...or this...
    //
    string = string.replace("\r\n", "\n").replace('\r', '\n');
    StringBuilder sb = new StringBuilder("<p>");
    for (StringTokenizer tokenizer = new StringTokenizer(string, "\n"); tokenizer.hasMoreElements();)
    {
      String line = tokenizer.nextElement().toString();
      if (line != null)
      {
        sb.append(line).append("<br />\n");
      }
    }
    return sb.append("</p>").toString();
  }
  
  public String computeConstraints(EModelElement modelElement)
  {
    List<String> constraints = EcoreUtil.getConstraints(modelElement);
    if (!constraints.isEmpty())
    {
      StringBuilder label = new StringBuilder();
      for (String constraint : constraints)
      {
        label.append(", <tt>").append(constraint).append("</tt>");
      }
      return label.substring(", ".length());
    }
    else
    {
      return null; 
    }   
   }
}