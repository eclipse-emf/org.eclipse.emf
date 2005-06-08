/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EcoreBuilder.java,v 1.8.2.1 2005/06/08 18:27:44 nickb Exp $
 */
package org.eclipse.emf.codegen.ecore.rose2ecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.rose2ecore.parser.RoseNode;
import org.eclipse.emf.codegen.ecore.rose2ecore.parser.Util;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;


/**
 * Traverses the Rose file and create eCore object in memory.
 */
public class EcoreBuilder implements RoseVisitor
{
  protected RoseUtil roseUtil;
  protected EcorePackage ecorePackage;
  protected EcoreFactory ecoreFactory;
  protected Set bounded = new HashSet();

  protected Map eStructuralFeatureToXMLNamespaceMap = new HashMap();

  protected List eStructuralFeatures = 
    new BasicEList() 
    { 
      protected boolean useEquals()
      {
        return false;
      }
    };
  protected Map eEnums = new HashMap();
  protected Map idToParentMap = new HashMap();

  protected EReference ref1 = null;
  protected EReference ref2 = null;
  protected RoseNode role1 = null;
  protected RoseNode role2 = null;

  public EcoreBuilder(RoseUtil roseUtil)
  {
    super();
    this.roseUtil = roseUtil;
    ecorePackage = EcorePackage.eINSTANCE;
    ecoreFactory = EcoreFactory.eINSTANCE;
  }

  public void visitList(RoseNode roseNode)
  {
  }

  public void visitObject(RoseNode roseNode)
  {
    String  roseNodeValue = roseNode.getValue();
    String objectKey  = roseNode.getKey();
    String objectType = Util.getType(roseNodeValue);
    String objectName = Util.getName(roseNodeValue);

// System.out.println("---> visitObject,,, objectKey - objectType - objectName: " + objKey + ", " + objType + ", " + objectName);

    // Get the parent object.
    //
    RoseNode currentNode = roseNode;
    Object parent = currentNode.getNode();
    while (parent == null)
    {
      currentNode = currentNode.getParent();
      parent = currentNode.getNode();
    }

    if (objectKey.equals("") && objectType.equals(RoseStrings.CLASS_CATEGORY))
    {
      // Map to an EPackage.
      //
      if (roseNode.isLoaded())
      {
        String id = roseNode.getRoseId();
        if (idToParentMap.containsKey(id))
        {
          parent = idToParentMap.get(id);
        }
        EPackage ePackage = ecoreFactory.createEPackage();
        if (parent instanceof EPackage)
        {
          // Add to package.
          //
          ((EPackage)parent).getESubpackages().add(ePackage);
        }
        else if (parent instanceof EList)
        {
          ((EList)parent).add(ePackage);
        }
        setEPackageProperties(roseNode, ePackage, objectName.toLowerCase());
      }
      else
      {
        idToParentMap.put(roseNode.getRoseId(), parent);
      }
    }
    else if (objectType.equals(RoseStrings.CLASS))
    {
      if (objectName == null || objectName.length() == 0)
      {
        String quid = roseNode.getRoseId();
        if (quid != null)
        {
          quid = quid.substring(1, quid.length() - 1);
        }

        objectName = "Unnamed" + quid;
        error(CodeGenEcorePlugin.INSTANCE.getString("_UI_UnnamedClass_message", new Object [] { objectName }));
      }

      // Map to EClass, EEnum or EInerface.
      // Note that we do not map structure and primitive type class.
      //
      RoseNode stereoTypeNode = roseNode.findNodeWithKey(RoseStrings.STEREOTYPE);
      if (stereoTypeNode != null)
      {
        String stereoTypeValue = stereoTypeNode.getValue();
        stereoTypeValue = stereoTypeValue.substring(1, stereoTypeValue.length() - 1);
        if (stereoTypeValue.equals(RoseStrings.INTERFACE))
        {
          // Map to an EClass.
          //
          EClass eClass = ecoreFactory.createEClass();
          String classifierName = roseNode.getClassifierName();
          if (classifierName == null || classifierName.length() == 0)
          {
            classifierName = validName(upperCaseName(objectName));
          }
          eClass.setName(classifierName);
          roseNode.setNode(eClass);
          setEClassProperties(roseNode, eClass);
          eClass.setInterface(true);
          eClass.setAbstract(true);
          build(roseNode, parent, eClass);
        }
        else if (stereoTypeValue.equalsIgnoreCase(RoseStrings.ENUMERATION))
        {
          // Map to an EEnum.
          EEnum eEnum = ecoreFactory.createEEnum();
          String classifierName = roseNode.getClassifierName();
          if (classifierName == null || classifierName.length() == 0)
          {
            classifierName = validName(upperCaseName(objectName));
          }
          eEnum.setName(classifierName);
          roseNode.setNode(eEnum);
          setEEnumProperties(roseNode, eEnum);
          build(roseNode, parent, eEnum);
        }
        else if (stereoTypeValue.equalsIgnoreCase("datatype") || stereoTypeValue.equalsIgnoreCase("primitive"))
        {
          // Map to an EDataType.
          //
          EDataType eDataType = ecoreFactory.createEDataType();
          String classifierName = roseNode.getClassifierName();
          if (classifierName == null || classifierName.length() == 0)
          {
            classifierName = validName(upperCaseName(objectName));
          }
          eDataType.setName(classifierName);
          roseNode.setNode(eDataType);
          setEDataTypeProperties(roseNode, eDataType);
          build(roseNode, parent, eDataType);

          String uml2MOFCorbaType = roseNode.getUML2MOFCorbaType();
          if (uml2MOFCorbaType != null)
          {
            uml2MOFCorbaType = uml2MOFCorbaType.trim();
            int start = uml2MOFCorbaType.indexOf("typedef ");
            if (start != -1)
            {
              uml2MOFCorbaType = uml2MOFCorbaType.substring(8);
              int end = uml2MOFCorbaType.lastIndexOf(" ");
              if (end != -1)
              {
                uml2MOFCorbaType = uml2MOFCorbaType.substring(0, end);
              }
            }

            if (uml2MOFCorbaType != null && uml2MOFCorbaType.length() != 0)
            {
              roseUtil.typeTable.put(eDataType, uml2MOFCorbaType);
            }
          }

        }
        else if (stereoTypeValue.equalsIgnoreCase("javatype"))
        {
          // Map to an EDataType.
          //
          EDataType eDataType = ecoreFactory.createEDataType();
          String classifierName = roseNode.getClassifierName();
          if (classifierName == null || classifierName.length() == 0)
          {
            int index = objectName.lastIndexOf(".");
            classifierName = validName(upperCaseName(index == -1 ? objectName : objectName.substring(index + 1)));
          }
          int index = objectName.lastIndexOf(".");
          eDataType.setName(validName(upperCaseName(index == -1 ? objectName : objectName.substring(index + 1))));
          eDataType.setInstanceClassName(objectName);
          roseNode.setNode(eDataType);
          setEDataTypeProperties(roseNode, eDataType);
          build(roseNode, parent, eDataType);
        }
        else if (stereoTypeValue.equalsIgnoreCase("abstract"))
        {
          // Map to an EClass.
          //
          EClass eClass = ecoreFactory.createEClass();
          String classifierName = roseNode.getClassifierName();
          if (classifierName == null || classifierName.length() == 0)
          {
            classifierName = validName(upperCaseName(objectName));
          }
          eClass.setName(classifierName);
          roseNode.setNode(eClass);
          setEClassProperties(roseNode, eClass);
          build(roseNode, parent, eClass);
        }
        else if (stereoTypeValue.equalsIgnoreCase("MapEntry"))
        {
          // Map to an EClass.
          //
          EClass eClass = ecoreFactory.createEClass();
          String classifierName = roseNode.getClassifierName();
          if (classifierName == null || classifierName.length() == 0)
          {
            classifierName = validName(upperCaseName(objectName));
          }
          eClass.setName(classifierName);
          roseNode.setNode(eClass);
          setEClassProperties(roseNode, eClass);
          eClass.setInstanceClassName("java.util.Map$Entry");
          build(roseNode, parent, eClass);
        }
        else
        {
          warning
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_UnrecognizedStereotype_message", new Object [] { stereoTypeValue, objectName }));

          // Map to an eClass.
          //
          EClass eClass = ecoreFactory.createEClass();
          String classifierName = roseNode.getClassifierName();
          if (classifierName == null || classifierName.length() == 0)
          {
            classifierName = validName(upperCaseName(objectName));
          }
          eClass.setName(classifierName);
          roseNode.setNode(eClass);
          setEClassProperties(roseNode, eClass);
          build(roseNode, parent, eClass);
        }
      }
      else
      {
        // Map to an eClass.
        //
        EClass eClass = ecoreFactory.createEClass();
        String classifierName = roseNode.getClassifierName();
        if (classifierName == null || classifierName.length() == 0)
        {
          classifierName = validName(upperCaseName(objectName));
        }
        eClass.setName(classifierName);
        roseNode.setNode(eClass);
        setEClassProperties(roseNode, eClass);
        build(roseNode, parent, eClass);
      }
    }
    else if (objectType.equals(RoseStrings.OPERATION))
    {
      // Map to an EOperation.
      EOperation eOperation = ecoreFactory.createEOperation();
      String operationName = roseNode.getOperationName();
      if (operationName == null || operationName.length() == 0)
      {
        operationName = validName(objectName);
      }
      eOperation.setName(operationName);
      roseNode.setNode(eOperation);
      setResultType(roseNode, eOperation);
      setEOperationProperties(roseNode, eOperation);
      if (parent instanceof EClass)
      {
        // Add to an EClass
        //
        ((EClass)parent).getEOperations().add(eOperation);
      }
    }
    else if (objectType.equals(RoseStrings.PARAMETER))
    {
      // Map to an EParameter as input parameter for operation.
      //
      EParameter eParameter = ecoreFactory.createEParameter();
      eParameter.setName(validName(objectName));
      roseNode.setNode(eParameter);

      // Do this first for better error message during setEParameterProperties.
      //
      if (parent instanceof EOperation)
      {
        ((EOperation)parent).getEParameters().add(eParameter);
      }
      setEParameterProperties(roseNode, eParameter);
    }
    else if (objectType.equals(RoseStrings.INHERITANCE_RELATIONSHIP))
    {
      String quidu = roseNode.getRoseRefId();
      if (quidu != null && !quidu.equals(""))
      {
        quidu = quidu.substring(1, quidu.length()-1);
      }
      List superList = (List)roseUtil.superTable.get(parent);
      if (superList == null)
      {
        superList = new ArrayList();
        roseUtil.superTable.put(parent, superList);
      }
      superList.add(quidu);
      superList.add(roseNode.getStereotype());
    }
    else if (objectType.equals(RoseStrings.CLASSATTRIBUTE) && (!roseNode.isDerived() || !"reference".equals(roseNode.getStereotype())))
    {
      // Map to EAttribute, or EEnumLiteral.
      //
      if (parent instanceof EEnum)
      {
        EEnumLiteral eEnumLiteral = ((EEnum)parent).getEEnumLiteral(objectName);
        if (eEnumLiteral == null)
        {
          eEnumLiteral = ecoreFactory.createEEnumLiteral();
          String literalName = roseNode.getAttributeName();
          if (literalName == null || literalName.length() == 0)
          {
            literalName = validName(objectName);
          }
          eEnumLiteral.setName(literalName);
          roseNode.setNode(eEnumLiteral);
          if (!setEEnumLiteralProperties(roseNode, eEnumLiteral))
          {
            if (((EEnum)parent).getELiterals() == null)
            {
              eEnumLiteral.setValue(0);
            }
            else
            {
              eEnumLiteral.setValue(((EEnum)parent).getELiterals().size());
            }
          }
          ((EEnum)parent).getELiterals().add(eEnumLiteral);
        } 
      }
      else if (parent instanceof EClassifier)
      {
        String stereoTypeValue = null;
        RoseNode stereoTypeNode = roseNode.findNodeWithKey(RoseStrings.STEREOTYPE);
        if (stereoTypeNode != null)
        {
          stereoTypeValue = stereoTypeNode.getValue();
          stereoTypeValue = stereoTypeValue.substring(1, stereoTypeValue.length() - 1);
        }

        if ((parent instanceof EDataType || (parent instanceof EClass && ((EClass)parent).isInterface())) &&
            "javaclass".equalsIgnoreCase(stereoTypeValue))
        {
          roseUtil.typeTable.remove(parent);
          ((EClassifier)parent).setInstanceClassName(objectName);
        }
        else if (parent instanceof EClass)
        {
          EAttribute eAttribute = ecoreFactory.createEAttribute();
          String attributeName = roseNode.getAttributeName();
          if (attributeName == null || attributeName.length() == 0)
          {
            attributeName = validName(objectName);
          }

          eAttribute.setName(attributeName);
          roseNode.setNode(eAttribute);
          setEAttributeProperties(roseNode, eAttribute);
          ((EClass)parent).getEStructuralFeatures().add(eAttribute);
          if (eAttribute.getUpperBound() == 0)
          {
            eAttribute.setUpperBound(1);
          }
        }
      }
    }
    else if (objectType.equals(RoseStrings.ASSOCIATION))
    {
      ref1 = null;
      ref2 = null;
      role1 = null;
      role2 = null;
    }
    else if (objectType.equals(RoseStrings.ROLE) && !objectName.startsWith("/"))
    {
      // map to EReference when is navigable
      //
      EReference ref = ecoreFactory.createEReference();
      ref.setUpperBound(0);
      String referenceName = roseNode.getReferenceName();
      if (referenceName == null || referenceName.length() == 0)
      {
        referenceName = validName(objectName);
      }
      ref.setName(referenceName);
      roseNode.setNode(ref);
      setEReferenceProperties(roseNode, ref);
      if (ref1 == null)
      {
        ref1 = ref;
      }
      else if (ref2 == null)
      {
        ref2 = ref;
      }
      if (role1 == null)
      {
        role1 = roseNode;
      }
      else if (role2 == null)
      {
        role2 = roseNode;
      }
      if (ref1 != null && ref2 != null && role1 != null && role2 != null)
      {
        String ref1Quidu = role1.getRoseRefId();
        if (ref1Quidu != null && !ref1Quidu.equals(""))
        {
          ref1Quidu = ref1Quidu.substring(1, ref1Quidu.length()-1);
        }
        String ref2Quidu = role2.getRoseRefId();
        if (ref2Quidu != null && !ref2Quidu.equals(""))
        {
          ref2Quidu = ref2Quidu.substring(1, ref2Quidu.length()-1);
        }
        boolean ref1Navigable = role1.isNavigable();
        boolean ref2Navigable = role2.isNavigable();
        if (ref1Navigable)
        {
          ref2.setEOpposite(ref1);
          setEReferenceIsContainment(ref1, role1, role2);
          roseUtil.refTable.put(ref1, ref2Quidu);
          TableObject obj = (TableObject)roseUtil.quidTable.get(ref1Quidu);
          if (obj != null)
          {
            roseUtil.typeTable.put(ref1, obj.getName());
          }
          else
          {
            warning
              (CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_UnresolvedTypeNameFor_message", new Object [] { role1.getRoseSupplier(), ref1.getName() }));
            roseUtil.typeTable.put(ref1, "EObject");
          }
        }
        if (ref2Navigable)
        {
          ref1.setEOpposite(ref2);
          setEReferenceIsContainment(ref2, role2, role1);
          roseUtil.refTable.put(ref2, ref1Quidu);
          TableObject obj = (TableObject)roseUtil.quidTable.get(ref2Quidu);
          if (obj != null)
          {
            roseUtil.typeTable.put(ref2, obj.getName());
          }
          else
          {
            warning
              (CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_UnresolvedTypeNameFor_message", new Object [] { role2.getRoseSupplier(), ref2.getName() }));
            roseUtil.typeTable.put(ref2, "EObject");
          }
        }
      }
 
      if (ref.getUpperBound() == 0)
      {
        setEReferenceDefaultMultiplicity(ref);
      }
    }
  }

  protected EList getExtentFromTableObject(RoseNode roseNode)
  {
    String quid = roseNode.getRoseId();
    if (quid != null)
    {
      quid = quid.substring(1, quid.length()-1);
    }
    TableObject obj = (TableObject)roseUtil.quidTable.get(quid);
    return obj == null ? null : obj.getContainer().getExtent();
  }

  protected void setEReferenceIsContainment(EReference ref, RoseNode role1, RoseNode role2)
  {
    boolean isAggregate = role2.isAggregate();
    String containmentV = role1.getContainment();
    if (isAggregate && (containmentV != null && containmentV.equalsIgnoreCase("by value")))
    {
      EReference opposite = ref.getEOpposite();
      if (opposite != null)
      {
        if (opposite.getUpperBound() != 1)
        {
          if (bounded.contains(opposite))
          {
            error
              (CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_ContainerRelationUpperBound_message", new Object [] { ref.getName(), ref.getEOpposite().getName() }));
          }
          opposite.setUpperBound(1);
        }
      }
      ref.setContainment(true);
    }
  }

  protected void setResultType(RoseNode roseNode, EOperation eOperation)
  {
    String quid = roseNode.getRoseRefId();
    if (quid != null && !quid.equals(""))
    {
      quid = quid.substring(1, quid.length() - 1);
      TableObject tableObj = (TableObject) roseUtil.quidTable.get(quid);
      if (tableObj != null)
      {
        roseUtil.typeTable.put(eOperation, tableObj.getName());
      }
      else
      {
        warning(CodeGenEcorePlugin.INSTANCE.getString("_UI_UnresolvedTypeIDFor_message", new Object [] { quid, eOperation.getName() }));
        roseUtil.typeTable.put(eOperation, "EString");
      }
    }
    else
    {
      String resultValue = getQualifiedTypeName(eOperation, roseNode.getResult());
      if (resultValue != null && !resultValue.equalsIgnoreCase("void"))
      {
        if (!resultValue.equals(""))
        {
          roseUtil.typeTable.put(eOperation, resultValue);
        }
        else
        {
          warning
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_UnresolvedTypeNameFor_message", new Object [] { roseNode.getRoseSupplier(), eOperation.getName() }));
          roseUtil.typeTable.put(eOperation, "EString");
        }
      }
    }
  }


  protected static final Pattern ANNOTATION_PATTERN = Pattern.compile("\\s*([^ ='\"]+)((\\s+[^ ='\"]+=(['\"])[^'\"]*\\4)*)");
  protected static final Pattern ANNOTATION_DETAIL_PATTERN = Pattern.compile("\\s*([^ ='\"]+)=(['\"])([^'\"]*)\\2");

  protected void setEModelElementProperties(RoseNode roseNode, EModelElement eModelElement)
  {
    String annotation = roseNode.getAnnotation();
    if (annotation != null)
    {
      for (Matcher matcher = ANNOTATION_PATTERN.matcher(annotation); matcher.find(); )
      {
        EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        eAnnotation.setSource(parseString(matcher.group(1)));
        for (Matcher detailMatcher = ANNOTATION_DETAIL_PATTERN.matcher(matcher.group(2)); detailMatcher.find(); )
        {
          eAnnotation.getDetails().put(parseString(detailMatcher.group(1)), parseString(detailMatcher.group(3)));
        }
        eModelElement.getEAnnotations().add(eAnnotation);
      }
    }
    String documentation = roseNode.getDocumentation();
    if (documentation != null)
    {
      EcoreUtil.setDocumentation(eModelElement, documentation);
    }

    String constraints = roseNode.getEcoreConstraints();
    if (constraints != null)
    {
      List constraintList = new ArrayList();
      for (StringTokenizer stringTokenizer = new StringTokenizer(constraints);
           stringTokenizer.hasMoreTokens(); )
      {
        String constraint = stringTokenizer.nextToken();
        constraintList.add(constraint);
      }
      EcoreUtil.setConstraints(eModelElement, constraintList);
    }
  }

  protected void setEPackageProperties(RoseNode roseNode, EPackage ePackage, String tentativeName)
  {
    roseNode.setNode(ePackage);

    setEModelElementProperties(roseNode, ePackage);

    String packageName = roseNode.getPackageName();
    if (packageName == null || packageName.length() == 0)
    {
      packageName = validName(tentativeName);
    }
    ePackage.setName(packageName);

    String basePackage = roseNode.getBasePackage();
    String prefix = validName(upperCaseName(roseNode.getPrefix()));
    String nsPrefix =
      roseNode.getNsPrefix() == null || roseNode.getNsPrefix().length() == 0 ?
        (String)roseUtil.packageNameToNSNameMap.get(packageName) : roseNode.getNsPrefix();
    if (nsPrefix == null || nsPrefix.length() == 0)
    {
      nsPrefix = packageName;
      EPackage eSuperPackage = ePackage.getESuperPackage();
      if (eSuperPackage != null)
      {
        nsPrefix = eSuperPackage.getNsPrefix() + "." + nsPrefix;
      }
      else if (basePackage != null && basePackage.length() != 0)
      {
        nsPrefix = basePackage + "." + nsPrefix;
      }
    }
    ePackage.setNsPrefix(nsPrefix);

    String nsURI =
      roseNode.getNsURI() == null || roseNode.getNsURI().length() == 0 ?
        (String)roseUtil.packageNameToNSURIMap.get(packageName) : roseNode.getNsURI();
    if (nsURI == null || nsURI.length() == 0)
    {
      if (org.eclipse.emf.codegen.ecore.Rose2GenModel.noQualify)
      {
        nsURI = nsPrefix + ".ecore";
      }
      else
      {
        nsURI = "http:///" + nsPrefix.replace('.', '/') + ".ecore";
      }
    }
    ePackage.setNsURI(nsURI);

    if (prefix != null && prefix.length() == 0) prefix = null;
    if (basePackage != null && basePackage.length() == 0) basePackage = null;

    if (prefix != null || basePackage != null)
    {
      List information = new ArrayList();
      information.add(basePackage);
      information.add(prefix);
      roseUtil.getEPackageToInformationMap().put(ePackage, information);
    }
  }

  protected void setEClassProperties(RoseNode roseNode, EClass eClass)
  {
    setEModelElementProperties(roseNode, eClass);
    String xmlName = roseNode.getXMLName();
    if (xmlName != null && xmlName.length() != 0)
    {
      ExtendedMetaData.INSTANCE.setName(eClass, xmlName);
    }
    int xmlContentKind = roseNode.getXMLContentKind();
    if (xmlContentKind != 0)
    {
      ExtendedMetaData.INSTANCE.setContentKind(eClass, xmlContentKind);
    }
    eClass.setAbstract(roseNode.isAbstract());
  }

  protected void setEDataTypeProperties(RoseNode roseNode, EDataType eDataType)
  {
    setEModelElementProperties(roseNode, eDataType);
    eDataType.setSerializable(!roseNode.isAbstract());
  }

  protected void setEEnumProperties(RoseNode roseNode, EEnum eEnum)
  {
    setEModelElementProperties(roseNode, eEnum);
    String value = roseNode.getDocumentation();
    if (value != null && !value.equals(""))
    {
      eEnums.put(eEnum, value);
    }
  }

  protected void populateEEnumFromDocumentation(EEnum enum, String documentation)
  {
    // process documentation info and create eEnumLiteral for each line
    //
    List eLiterals = enum.getELiterals();
    for (StringTokenizer stringTokenizer = new StringTokenizer(documentation, ", \n\r\t"); stringTokenizer.hasMoreTokens();)
    {
      String literalV = stringTokenizer.nextToken();
      String name = literalV;
      String number = "";
      int ind = literalV.indexOf("=");
      if (ind != -1)
      {
        name = literalV.substring(0, ind);
        number = literalV.substring(ind + 1, literalV.length());
      }
      int numberValue = 0;
      if (!number.equals(""))
      {
        numberValue = new Integer(number).intValue();
      }
      else if (!eLiterals.isEmpty())
      {
        numberValue = ((EEnumLiteral)eLiterals.get(eLiterals.size() - 1)).getValue() + 1;
      }

      if (!name.equals(""))
      {
        EEnumLiteral lit = enum.getEEnumLiteral(name);
        if (lit == null)
        {
          lit = ecoreFactory.createEEnumLiteral();
          lit.setName(validName(name));
          lit.setValue(numberValue);
          eLiterals.add(lit);
        }
        else
        {
          lit.setValue(numberValue);
        }
      }
    }
  }

  protected void setEOperationProperties(RoseNode roseNode, EOperation eOperation)
  {
    setEModelElementProperties(roseNode, eOperation);
    String semantics = roseNode.getSemantics();
    if (semantics != null)
    {
      EAnnotation eAnnotation = eOperation.getEAnnotation(GenModelPackage.eNS_URI);
      if (eAnnotation == null)
      {
        eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        eAnnotation.setSource(GenModelPackage.eNS_URI);
        eOperation.getEAnnotations().add(eAnnotation);
      }
      eAnnotation.getDetails().put("body", semantics);
    }
    String exceptions = roseNode.getExceptions();
    if (exceptions != null)
    {
      int count = 0;
      for (StringTokenizer stringTokenizer = new StringTokenizer(exceptions.trim(), ","); stringTokenizer.hasMoreTokens(); )
      {
        // This handles Rose 2003 format, e.g.,
        // Logical View::JavaException[40722F9D0294]
        //
        String exception = stringTokenizer.nextToken().trim();
        if (exception.indexOf("[") != -1)
        {
          exception = exception.substring(0, exception.indexOf("["));
        }
        String exceptionValue = getQualifiedTypeName(eOperation, exception);
        if (exceptionValue != null && !exceptionValue.equals(""))
        {
          EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
          eAnnotation.getReferences().add(eOperation);
          eAnnotation.getDetails().put("position", Integer.toString(count++));
          roseUtil.typeTable.put(eAnnotation, exceptionValue);
        }
        else
        {
          warning
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_UnresolvedTypeNameFor_message", new Object [] { roseNode.getRoseSupplier(), eOperation.getName() }));
        }
      }
    }
    String stereotype = roseNode.getStereotype();
    if (stereotype != null)
    {
      if ("inv".equals(stereotype))
      {
        eOperation.setEType(EcorePackage.eINSTANCE.getEBoolean());

        eOperation.getEParameters().clear();

        EParameter eParameter = ecoreFactory.createEParameter();
        eParameter.setName("diagnostics");
        eParameter.setEType(EcorePackage.eINSTANCE.getEDiagnosticChain());
        eOperation.getEParameters().add(eParameter);

        eParameter = ecoreFactory.createEParameter();
        eParameter.setName("context");
        eParameter.setEType(EcorePackage.eINSTANCE.getEMap());
        eOperation.getEParameters().add(eParameter);
      }
    }
  }

  protected void setEAttributeProperties(RoseNode roseNode, EAttribute eAttribute)
  {
    eStructuralFeatures.add(eAttribute);

    String quid = roseNode.getRoseRefId();
    if (quid != null && !quid.equals(""))
    {
      quid = quid.substring(1, quid.length() - 1);
      TableObject tableObj = (TableObject) roseUtil.quidTable.get(quid);
      if (tableObj != null)
      {
        roseUtil.typeTable.put(eAttribute, tableObj.getName());
      }
      else
      {
        warning(CodeGenEcorePlugin.INSTANCE.getString("_UI_UnresolvedTypeIDFor_message", new Object [] { quid, eAttribute.getName() }));
        roseUtil.typeTable.put(eAttribute, "String");
      }
    }
    else
    {
      String type = getQualifiedTypeName(eAttribute, roseNode.getType());
      if (type != null && !type.equals(""))
      {
        roseUtil.typeTable.put(eAttribute, type);
      }
      else
      {
        roseUtil.typeTable.put(eAttribute, "String");
        warning
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_AttributeDoesNotDefineItsType_message", new Object [] { eAttribute.getName() }));
      }
    }

    // default value
    //
    String initv = roseNode.getInitV();
    if (initv != null && initv.length() >= 2)
    {
      if (initv.charAt(0) == '\'' && initv.charAt(initv.length() - 1) == '\'')
      {
        try
        {
          int i = parseChar(initv.substring(1, initv.length() - 1));
          initv = Integer.toString(i);
        }
        catch (IllegalArgumentException e)
        {
          error
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_InvalidDefaultValueForAttribute_message", new Object [] { eAttribute.getName() }));
          initv = null;
        }
      }
      else if (initv.charAt(0)== '\"' && initv.charAt(initv.length() - 1) == '\"')
      {
        try
        {
          initv = parseString(initv.substring(1, initv.length() - 1));
        }
        catch (IllegalArgumentException e)
        {
          error
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_InvalidDefaultValueForAttribute_message", new Object [] { eAttribute.getName() }));
          initv = null;
        }
      }
    }
    if (initv != null)
    {
      eAttribute.setDefaultValueLiteral(initv);
    }

    setEStructuralFeatureProperties(roseNode, eAttribute);
    eAttribute.setDerived(roseNode.isDerived());

    eAttribute.setTransient(roseNode.isTransient());
    eAttribute.setVolatile(roseNode.isVolatile());
    eAttribute.setChangeable(roseNode.isChangeable());
    eAttribute.setUnique(roseNode.isUnique());
    eAttribute.setUnsettable(roseNode.isUnsettable());
    eAttribute.setID(roseNode.isID());
  }

  protected void setEReferenceProperties(RoseNode roseNode, EReference eReference)
  {
    eStructuralFeatures.add(eReference);

    eReference.setDerived(roseNode.getParent().getParent().isDerived());
    setEStructuralFeatureProperties(roseNode, eReference);

    // eReference.setNavigable(roseNode.isNavigable());
    eReference.setTransient(roseNode.isTransient());
    eReference.setVolatile(roseNode.isVolatile());
    eReference.setChangeable(roseNode.isChangeable());
    eReference.setResolveProxies(roseNode.isResolveProxies());
    eReference.setUnsettable(roseNode.isUnsettable());
  }

  protected void setEStructuralFeatureProperties(RoseNode roseNode, EStructuralFeature eStructuralFeature)
  {
    setEModelElementProperties(roseNode, eStructuralFeature);

    String xmlName = roseNode.getXMLName();
    if (xmlName != null && xmlName.length() != 0)
    {
      ExtendedMetaData.INSTANCE.setName(eStructuralFeature, xmlName);
    }
    int xmlFeatureKind = roseNode.getXMLFeatureKind();
    if (xmlFeatureKind != 0)
    {
      ExtendedMetaData.INSTANCE.setFeatureKind(eStructuralFeature, xmlFeatureKind);
    }
    String xmlNamespace = roseNode.getXMLNamespace();
    if (xmlNamespace != null && xmlNamespace.length() != 0)
    {
      eStructuralFeatureToXMLNamespaceMap.put(eStructuralFeature, xmlNamespace);
    }

    // multiplicity
    //
    String multiplicity = eStructuralFeature instanceof EAttribute ? roseNode.getStereotype() : roseNode.getRoleMultiplicity();
    if (multiplicity != null)
    {
      bounded.add(eStructuralFeature);

      if (multiplicity.length() > 0 &&
            Character.isLetter(multiplicity.charAt(0)) &&
            !"n".equalsIgnoreCase(multiplicity))
      {
        return;
      }

      StringTokenizer stringTokenizer = new StringTokenizer(multiplicity, ". \n\r\t");
      switch (stringTokenizer.countTokens())
      {
        case 1:
        {
          String bound = stringTokenizer.nextToken();
          if (bound.equals("*") || bound.equalsIgnoreCase("n"))
          {
            eStructuralFeature.setUpperBound(-1);
          }
          else
          {
            try
            {
              int boundValue = Integer.parseInt(bound);
              if (boundValue > 0)
              {
                eStructuralFeature.setLowerBound(boundValue);
                eStructuralFeature.setUpperBound(boundValue);
              }
              else
              {
                warning
                  (CodeGenEcorePlugin.INSTANCE.getString
                     ("_UI_BadMultiplicityFor_message", new Object [] { multiplicity,  eStructuralFeature.getName() }));
              }
            }
            catch (NumberFormatException exception)
            {
              warning
                (CodeGenEcorePlugin.INSTANCE.getString
                   ("_UI_BadMultiplicityFor_message", new Object [] { multiplicity,  eStructuralFeature.getName() }));
            }
          }
          break;
        }
        case 2:
        {
          String lowerBound = stringTokenizer.nextToken();
          try
          {
            int lowerBoundValue = Integer.parseInt(lowerBound);
            if (lowerBoundValue >= 0)
            {
              String upperBound = stringTokenizer.nextToken();
              if (upperBound.equals("*") || upperBound.equalsIgnoreCase("n"))
              {
                eStructuralFeature.setLowerBound(lowerBoundValue);
                eStructuralFeature.setUpperBound(-1);
              }
              else
              {
                int upperBoundValue = Integer.parseInt(upperBound);
                if (upperBoundValue <= 0 || lowerBoundValue > upperBoundValue)
                {
                  warning
                    (CodeGenEcorePlugin.INSTANCE.getString
                       ("_UI_BadMultiplicityFor_message", new Object [] { multiplicity,  eStructuralFeature.getName() }));
                }
                else
                {
                  eStructuralFeature.setLowerBound(lowerBoundValue);
                  eStructuralFeature.setUpperBound(upperBoundValue);
                }
              }
            }
            else
            {
              warning
                (CodeGenEcorePlugin.INSTANCE.getString
                   ("_UI_BadMultiplicityFor_message", new Object [] { multiplicity,  eStructuralFeature.getName() }));
            }
          }
          catch (NumberFormatException exception)
          {
            warning
              (CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_BadMultiplicityFor_message", new Object [] { multiplicity,  eStructuralFeature.getName() }));
          }
          break;
        }
        default:
        {
          warning
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_BadMultiplicityFor_message", new Object [] { multiplicity,  eStructuralFeature.getName() }));
        }
      }
    }
  }

  protected boolean setEEnumLiteralProperties(RoseNode roseNode, EEnumLiteral eEnumLiteral)
  {
    setEModelElementProperties(roseNode, eEnumLiteral);
    String value = roseNode.getInitV();
    if (value != null && !value.equals(""))
    {
      eEnumLiteral.setValue(Integer.parseInt(value));
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void setEParameterProperties(RoseNode roseNode, EParameter eParameter)
  {
    setEModelElementProperties(roseNode, eParameter);
    String quid = roseNode.getRoseRefId();
    if (quid != null && !quid.equals(""))
    {
      quid = quid.substring(1, quid.length() - 1);
      TableObject tableObj = (TableObject) roseUtil.quidTable.get(quid);

      if (tableObj != null)
      {
        roseUtil.typeTable.put(eParameter, tableObj.getName());
      }
      else
      {
        warning
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_UnresolvedTypeIDFor_message", new Object [] { quid, eParameter.getEOperation().getName() }));
        roseUtil.typeTable.put(eParameter, "EObject");
      }
    }
    else
    {
      String type = getQualifiedTypeName(eParameter, roseNode.getType());
      if (type != null && !type.equals(""))
      {
        roseUtil.typeTable.put(eParameter, type);
      }
      else
      {
        warning
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_UnresolvedTypeNameFor_message", new Object [] { roseNode.getRoseSupplier(), eParameter.getEOperation().getName() }));
        roseUtil.typeTable.put(eParameter, "EObject");
      }
    }
  }

  protected void setEReferenceDefaultMultiplicity(EReference eReference)
  {
    if (eReference.getEOpposite() == null || !eReference.getEOpposite().isContainment())
    {
      eReference.setLowerBound(0);
      eReference.setUpperBound(-1);
    }
  }

  public void setEEnums()
  {
    for (Iterator i = eEnums.entrySet().iterator(); i.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)i.next();
      EEnum eEnum = (EEnum)entry.getKey();
      if (eEnum.getELiterals().isEmpty())
      {
        populateEEnumFromDocumentation(eEnum, (String)entry.getValue());
      }
    }
  }

  protected static Comparator eClassComparator =
    new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        // Order first by number of features (descending) and then alphabetically (ascending)
        //
        EClass c1 = (EClass) o1;
        EClass c2 = (EClass) o2;
        int count1 = c1.getEAllAttributes().size() + c1.getEAllReferences().size();
        int count2 = c2.getEAllAttributes().size() + c2.getEAllReferences().size();
        if (count1 < count2) return 1;
        if (count1 > count2) return -1;
        return c1.getName().compareTo(c2.getName());
      }
    };

  public void setSuper()
  {
    Map superMap = new HashMap();

    for (Iterator i = roseUtil.superTable.keySet().iterator(); i.hasNext(); )
    {
      Object subObject = i.next();
      if (subObject instanceof EClass)
      {
        EClass eClass = (EClass)subObject;
        List extend = new ArrayList();
        List unspecified = new ArrayList();
        List mixin = new ArrayList();
        List nonClass = new ArrayList();
        for (Iterator j = ((List)roseUtil.superTable.get(eClass)).iterator(); j.hasNext(); )
        {
          String quid = (String)j.next();
          String stereotype = (String)j.next();
          TableObject tableObject = (TableObject)roseUtil.quidTable.get(quid);
          if (tableObject != null)
          {
            Object superObject = tableObject.getObject();
            if (superObject instanceof EClass)
            {
              EClass superClass = (EClass)superObject;
              if (!superClass.isInterface())
              {
                if ("extend".equals(stereotype))
                {
                  extend.add(superObject);
                }
                else if ("mixin".equals(stereotype))
                {
                  mixin.add(superObject);
                }
                else
                {
                  unspecified.add(superObject);
                }
              }
              else
              {
                nonClass.add(superObject);
              }
            }
            else
            {
              warning
                (CodeGenEcorePlugin.INSTANCE.getString
                   ("_UI_CannotAddSuperLinkBetween_message", 
                    new Object [] { eClass.getName(), ((ENamedElement)superObject).getName() }));
            }
          }
        }

        if (extend.size() > 1)
        {
          warning
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_CannotSpecifyMoreThanOneExtendFor_message", new Object [] { eClass.getName() }));
        }

        superMap.put(eClass, new List [] { extend, unspecified, mixin });

        eClass.getESuperTypes().addAll(extend);
        eClass.getESuperTypes().addAll(unspecified);
        eClass.getESuperTypes().addAll(mixin);
        eClass.getESuperTypes().addAll(nonClass);
      }
      else
      {
        warning
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_CannotAddSuperLinkInvolving_message", new Object [] { ((ENamedElement)subObject).getName() }));
      }
    }

    for (Iterator entries = superMap.entrySet().iterator(); entries.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)entries.next();
      EClass eClass = (EClass)entry.getKey();
      List [] collections = (List [])entry.getValue();
      Collections.sort(collections[0], eClassComparator);
      Collections.sort(collections[1], eClassComparator);
      Collections.sort(collections[2], eClassComparator);
      List combined = new ArrayList(collections[0]);
      combined.addAll(collections[1]);
      combined.addAll(collections[2]);
      EList eSuper = eClass.getESuperTypes();
      for (ListIterator ordered = combined.listIterator(); ordered.hasNext(); )
      {
        Object eSuperItem = ordered.next();
        eSuper.move(ordered.previousIndex(), eSuperItem);
      }
    }
  }

  public void setIDs(final EObject parent, EObject child)
  {
    new EcoreSwitch()
    {
      public Object caseEPackage(EPackage ePackage)
      {
        return null;
      }

      public Object caseEClassifier(EClassifier eClassifier)
      {
        return null;
      }

      public Object caseEOperation(EOperation eOperation)
      {
        return null;
      }

      public Object caseEParameter(EParameter eParameter)
      {
        return null;
      }

      public Object caseEStructuralFeature(EStructuralFeature eStructuralFeature)
      {
        return null;
      }

      public Object caseEEnumLiteral(EEnumLiteral eEnumLiteral)
      {
        return null;
      }

      public Object defaultCase(EObject eObject)
      {
        for (Iterator i = eObject.eContents().iterator(); i.hasNext(); )
        {
          setIDs(eObject, (EObject)i.next());
        }
        return this;
      }
    }.doSwitch(child);
  }

  public void validate(EObject object)
  {
    new EcoreSwitch()
    {
      public Object caseEDataType(EDataType eDataType)
      {
        if (!(eDataType instanceof EEnum) && eDataType.getInstanceClassName() == null)
        {
          error
            (CodeGenEcorePlugin.INSTANCE.getString("_UI_DatatypeNotSetFor_message", new Object [] { eDataType.getName() }));
          eDataType.setInstanceClassName("java.lang.String");
        }
        return null;
      }

      public Object caseEEnum(EEnum eEnum)
      {
        for (Iterator literals = eEnum.getELiterals().iterator(); literals.hasNext(); )
        {
          EEnumLiteral eEnumLiteral = (EEnumLiteral)literals.next();
          for (Iterator allLiterals = eEnum.getELiterals().iterator(); allLiterals.hasNext(); )
          {
            EEnumLiteral otherLiteral = (EEnumLiteral)allLiterals.next();
            if (eEnumLiteral == otherLiteral)
            {
              break;
            }
            else if (eEnumLiteral.getName().equalsIgnoreCase(otherLiteral.getName()))
            {
              error
                (CodeGenEcorePlugin.INSTANCE.getString
                   ("_UI_DuplicateLiteral_message", new Object [] { eEnumLiteral.getName(), eEnum.getName() }));
              literals.remove();
              break;
            }
          }
        }
        return this;
      }

      public Object caseEClass(EClass eClass)
      {
        List oppositesToRemove = new ArrayList();
        for (Iterator features = eClass.getEStructuralFeatures().iterator(); features.hasNext(); )
        {
          EStructuralFeature eStructuralFeature = (EStructuralFeature)features.next();
          if (eStructuralFeature instanceof EAttribute)
          {
            EAttribute eAttribute = (EAttribute)eStructuralFeature;

            // Temporary WAS/WSAD migration option.
            //
            if (org.eclipse.emf.codegen.ecore.Rose2GenModel.unsettablePrimitive)
            {
              try
              {
                EDataType eDataType = eAttribute.getEAttributeType();
                if (eDataType instanceof EEnum || eDataType.getInstanceClass().isPrimitive())
                {
                  eAttribute.setUnsettable(true);
                }
              }
              catch (Exception e)
              {
              }
            }

            for (Iterator allFeatures = eClass.getEAllStructuralFeatures().iterator(); allFeatures.hasNext(); )
            {
              EStructuralFeature otherFeature = (EStructuralFeature)allFeatures.next();
              if (eAttribute == otherFeature)
              {
                break;
              }
              else if (eAttribute.getName().equalsIgnoreCase(otherFeature.getName()))
              {
                error
                  (CodeGenEcorePlugin.INSTANCE.getString
                     ("_UI_DuplicateAttribute_message", new Object [] { eAttribute.getName(), eClass.getName() }));
                features.remove();
                break;
              }
              else if (!eAttribute.getEAttributeType().isSerializable() && !eAttribute.isTransient())
              {
                error
                  (CodeGenEcorePlugin.INSTANCE.getString
                     ("_UI_TheAttributeShouldBeTransient_message", new Object [] { eAttribute.getName(), eAttribute.getEType().getName() }));
                break;
              }

            }
          }
          else
          {
            EReference eReference = (EReference)eStructuralFeature;
            EReference opposite = eReference.getEOpposite();
            if (opposite != null)
            {
              if (opposite.eContainer() == null)
              {
                error
                  (CodeGenEcorePlugin.INSTANCE.getString
                     ("_UI_AnAssociationHasADanglingEnd_message", new Object [] { opposite.getName(), eReference.getName() }));
                opposite = null;
                eReference.setEOpposite(null);
              }
              else if (opposite.isContainment())
              {
                // A container must be transient.
                //
                eReference.setTransient(true);

                if (eReference.getUpperBound() != 1)
                {
                  if (bounded.contains(eReference))
                  {
                    error
                      (CodeGenEcorePlugin.INSTANCE.getString
                         ("_UI_ContainerRelationUpperBound_message", new Object [] { opposite.getName(), eReference.getName() }));
                  }
                  eReference.setUpperBound(1);
                }
              }
            }

            if (eReference.isTransient() &&
                  !eReference.isVolatile() &&
                  opposite != null &&
                  !opposite.isTransient() &&
                  opposite.isResolveProxies() &&
                  !opposite.isContainment())
            {
              error
                (CodeGenEcorePlugin.INSTANCE.getString
                   ("_UI_CrossDocumentBidirectionalTransient_message", 
                    new Object [] { opposite.getName(), eReference.getName() }));
            }

            for (Iterator allFeatures = eClass.getEAllStructuralFeatures().iterator(); allFeatures.hasNext(); )
            {
              EStructuralFeature otherFeature = (EStructuralFeature)allFeatures.next();
              if (eReference == otherFeature)
              {
                break;
              }
              else if (eReference.getName().equalsIgnoreCase(otherFeature.getName()))
              {
                error
                  (CodeGenEcorePlugin.INSTANCE.getString
                     ("_UI_DuplicateReference_message", new Object [] { eReference.getName(), eClass.getName() }));
                if (opposite != null)
                {
                  oppositesToRemove.add(opposite);
                }
                features.remove();
                break;
              }
            }

            if (!eReference.isContainer() && "java.util.Map$Entry".equals(eReference.getEType().getInstanceClassName()))
            {
              if (!eReference.isContainment() || !eReference.isMany())
              {
                error
                  (CodeGenEcorePlugin.INSTANCE.getString
                     ("_UI_MultiplicityManyContainmentIsAssumedFor_message", new Object [] { eReference.getName(), eClass.getName() }));
                eReference.setContainment(true);
                eReference.setUpperBound(-1);
              }
            }
          }
        }

        for (Iterator opposites = oppositesToRemove.iterator(); opposites.hasNext(); )
        {
          EReference opposite = (EReference)opposites.next();
          EClass oppositeEClass = opposite.getEContainingClass();
          if (oppositeEClass != null)
          {
            oppositeEClass.getEStructuralFeatures().remove(opposite);
          }
        }

        if (eClass.getESuperTypes().size() > 1)
        {
          Iterator superTypes = eClass.getESuperTypes().iterator();
          superTypes.next();
          while (superTypes.hasNext())
          {
            EClass superType = (EClass)superTypes.next();
            superFeatureLoop: for (Iterator superFeatures = superType.getEAllStructuralFeatures().iterator(); superFeatures.hasNext(); )
            {
              EStructuralFeature superFeature = (EStructuralFeature)superFeatures.next();
              for (Iterator allFeatures = eClass.getEAllStructuralFeatures().iterator(); allFeatures.hasNext(); )
              {
                EStructuralFeature otherFeature = (EStructuralFeature)allFeatures.next();
                if (superFeature == otherFeature)
                {
                  break;
                }
                else if (superFeature.getName().equalsIgnoreCase(otherFeature.getName()))
                {
                  error
                    (CodeGenEcorePlugin.INSTANCE.getString
                       ("_UI_DuplicateFeatureInheritance_message", 
                        new Object [] { superFeature.getName(), eClass.getName(), superType.getName() }));
                  superTypes.remove();
                  break superFeatureLoop;
                }
              }
            }
          }
        }

        if ("java.util.Map$Entry".equals(eClass.getInstanceClassName()))
        {
          EStructuralFeature keyFeature = eClass.getEStructuralFeature("key");
          EStructuralFeature valueFeature = eClass.getEStructuralFeature("value");
          if (keyFeature == null)
          {
            error(CodeGenEcorePlugin.INSTANCE.getString("_UI_ExpectingFeatureNamedKey_message", new Object [] { eClass.getName() }));
            eClass.setInstanceClassName(null);
          }

          if (valueFeature == null)
          {
            error(CodeGenEcorePlugin.INSTANCE.getString("_UI_ExpectingFeatureNamedValue_message", new Object [] { eClass.getName() }));
            eClass.setInstanceClassName(null);
          }
        }

        return this;
      }

      public Object defaultCase(EObject eObject)
      {
        for (Iterator i = eObject.eContents().iterator(); i.hasNext(); )
        {
          validate((EObject)i.next());
        }
        return this;
      }
    }.doSwitch(object);
  }

  protected Comparator eStructuralFeatureComparator =
    new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        return eStructuralFeatures.indexOf(o1) - eStructuralFeatures.indexOf(o2);
      }
    };

  public void setEReferences()
  {
    // process eStructuralFeatures for association end(Role)
    //
    for (Iterator i = roseUtil.refTable.keySet().iterator(); i.hasNext(); )
    {
      EReference eReference = (EReference)i.next();
      String quid = (String)roseUtil.refTable.get(eReference);
      TableObject tableObject = (TableObject)roseUtil.quidTable.get(quid);
      if (tableObject != null)
      {
        Object struct = tableObject.getObject();
        if (struct instanceof EClass)
        {
          ((EClass)struct).getEStructuralFeatures().add
            (-1 - Collections.binarySearch(((EClass)struct).getEStructuralFeatures(), eReference, eStructuralFeatureComparator), eReference);
        }
        else
        {
          warning
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_CannotAddReference_message", new Object [] { eReference.getName(), tableObject.getName() }));
        }
      }
    }

    for (Iterator i = eStructuralFeatureToXMLNamespaceMap.entrySet().iterator(); i.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)i.next();
      ExtendedMetaData.INSTANCE.setNamespace((EStructuralFeature)entry.getKey(), (String)entry.getValue());
    }
  }

  public void setETypeClassifier()
  {
    // setup attribute and parameter type
    //
    for (Iterator it = roseUtil.typeTable.keySet().iterator(); it.hasNext(); )
    {
      EModelElement element = (EModelElement)it.next();
      String type = (String)roseUtil.typeTable.get(element);
      int position = -1;
      if (element instanceof EAnnotation)
      {
        position = Integer.parseInt((String)((EAnnotation)element).getDetails().get("position"));
        element = (EModelElement)((EAnnotation)element).getReferences().get(0);
      }
      TableObject tableObj = null;
      if (type.indexOf(".") == -1)
      {
        String qualifier = "";
        for (EObject parent = element.eContainer(); parent != null; parent = parent.eContainer())
        {
          if (parent instanceof EPackage)
          {
            qualifier = ((EPackage)parent).getName() + "." + qualifier;
          }
        }
        tableObj = (TableObject)roseUtil.nameTable.get(qualifier + type);
      }

      if (tableObj == null)
      {
        tableObj = (TableObject)roseUtil.nameTable.get(type);
      }

      EClassifier eType;

      if (tableObj != null && tableObj.getObject() != null && element instanceof ETypedElement)
      {
        // Convert attributes of with EClass type to references.
        //
        eType = (EClassifier)tableObj.getObject();
      }
      else
      {
        // It was not found in the model class so check if primitive type.
        //
        if (type.equals("boolean") || type.equalsIgnoreCase("eboolean"))
        {
          eType = getBasicType("EBoolean");
        }
        else if (type.equalsIgnoreCase("boolean") || type.equalsIgnoreCase("ebooleanobject"))
        {
          eType = getBasicType("EBooleanObject");
        }
        else if (type.equalsIgnoreCase("string") || type.equalsIgnoreCase("estring"))
        {
          eType = ecorePackage.getEString();
        }
        else if (type.equalsIgnoreCase("char") || type.equalsIgnoreCase("echar"))
        {
          eType = getBasicType("EChar");
        }
        else if (type.equalsIgnoreCase("character") || type.equalsIgnoreCase("echaracterobject"))
        {
          eType = getBasicType("ECharacterObject");
        }
        else if (type.equals("double") || type.equalsIgnoreCase("edouble") || type.equalsIgnoreCase("currency"))
        {
          eType = getBasicType("EDouble");
        }
        else if (type.equalsIgnoreCase("double") || type.equalsIgnoreCase("edoubleobject"))
        {
          eType = getBasicType("EDoubleObject");
        }
        else if (type.equalsIgnoreCase("int") || type.equalsIgnoreCase("eint"))
        {
          eType = getBasicType("EInt");
        }
        else if (type.equalsIgnoreCase("integer") || type.equalsIgnoreCase("eintegerobject"))
        {
          eType = getBasicType("EIntegerObject");
        }
        else if (type.equals("long long") || type.equals("long") || type.equalsIgnoreCase("elong"))
        {
          eType = getBasicType("ELong");
        }
        else if (type.equalsIgnoreCase("long") || type.equalsIgnoreCase("elongobject"))
        {
          eType = getBasicType("ELongObject");
        }
        else if (type.equals("float") || type.equalsIgnoreCase("efloat") || type.equalsIgnoreCase("single"))
        {
          eType = getBasicType("EFloat");
        }
        else if (type.equalsIgnoreCase("float") || type.equalsIgnoreCase("efloatobject"))
        {
          eType = getBasicType("EFloatObject");
        }
        else if (type.equals("short") || type.equalsIgnoreCase("eshort"))
        {
          eType = getBasicType("EShort");
        }
        else if (type.equalsIgnoreCase("short") || type.equalsIgnoreCase("eshortobject"))
        {
          eType = getBasicType("EShortObject");
        }
        else if (type.equals("byte") || type.equalsIgnoreCase("ebyte"))
        {
          eType = getBasicType("EByte");
        }
        else if (type.equals("byte[]") || type.equalsIgnoreCase("ebytearray") || type.equalsIgnoreCase("ebyte[]"))
        {
          eType = getBasicType("EByteArray");
        }
        else if (type.equalsIgnoreCase("byte") || type.equalsIgnoreCase("ebyteObject"))
        {
          eType = getBasicType("EByteObject");
        }
        else if (type.equalsIgnoreCase("ebigdecimal"))
        {
          eType = getBasicType("EBigDecimal");
        }
        else if (type.equalsIgnoreCase("ebiginteger"))
        {
          eType = getBasicType("EBigInteger");
        }
        else if (type.equalsIgnoreCase("edate"))
        {
          eType = getBasicType("EDate");
        }
        else if (type.equalsIgnoreCase("eobject"))
        {
          eType = ecorePackage.getEObject();
        }
        else
        {
          warning
            (CodeGenEcorePlugin.INSTANCE.getString
               ("_UI_UnresolvedTypeNameFor_message", new Object [] { type, ((ENamedElement)element).getName() }));
          eType = getBasicType("EString");
        }
      }

      if (eType instanceof EClass && element instanceof EAttribute)
      {
        EAttribute eAttribute = (EAttribute)element;
        EReference eReference = ecoreFactory.createEReference();

        eReference.setName(eAttribute.getName());
        eReference.setTransient(eAttribute.isTransient());
        eReference.setVolatile(eAttribute.isVolatile());
        eReference.setDerived(eAttribute.isDerived());
        eReference.setChangeable(eAttribute.isChangeable());
        eReference.setLowerBound(eAttribute.getLowerBound());
        eReference.setUpperBound(eAttribute.getUpperBound());
        eReference.setContainment(true);
        eReference.getEAnnotations().addAll(eAttribute.getEAnnotations());
        eReference.setUnsettable(eAttribute.isUnsettable());

        eStructuralFeatures.set(eStructuralFeatures.indexOf(eAttribute), eReference);

        EClass containingClass = eAttribute.getEContainingClass();
        containingClass.getEStructuralFeatures().add
          (containingClass.getEStructuralFeatures().indexOf(eAttribute), eReference);
        containingClass.getEStructuralFeatures().remove(eAttribute);

        element = eReference;
      }
      else if (eType instanceof EDataType && element instanceof EReference)
      {
        EReference eReference = (EReference)element;
        EAttribute eAttribute = ecoreFactory.createEAttribute();

        eAttribute.setName(eReference.getName());
        eAttribute.setTransient(eReference.isTransient());
        eAttribute.setVolatile(eReference.isVolatile());
        eAttribute.setDerived(eReference.isDerived());
        eAttribute.setChangeable(eReference.isChangeable());
        eAttribute.setLowerBound(eReference.getLowerBound());
        eAttribute.setUpperBound(eReference.getUpperBound());
        eAttribute.getEAnnotations().addAll(eReference.getEAnnotations());
        eAttribute.setUnsettable(eReference.isUnsettable());

        eStructuralFeatures.set(eStructuralFeatures.indexOf(eReference), eAttribute);

        EClass containingClass = eReference.getEContainingClass();
        if (containingClass != null)
        {
          containingClass.getEStructuralFeatures().add
            (containingClass.getEStructuralFeatures().indexOf(eReference), eAttribute);
          containingClass.getEStructuralFeatures().remove(eReference);
        }

        element = eAttribute;
      }

      if (element instanceof EDataType)
      {
        ((EDataType)element).setInstanceClassName(eType.getInstanceClassName());
      }
      else if (position != -1)
      {
        List exceptions = ((EOperation)element).getEExceptions();
        if (!exceptions.contains(eType))
        {
          if (position < exceptions.size())
          {
            exceptions.add(position, eType);
          }
          else
          {
            exceptions.add(eType);
          }
        }
      }
      else
      {
        ((ETypedElement)element).setEType(eType);

        if (element instanceof EStructuralFeature)
        {
          EStructuralFeature eStructuralFeature = (EStructuralFeature)element;
          if ("".equals(eStructuralFeature.getName()))
          {
            eStructuralFeature.setName(eType.getName());
          }
        }
      }
    }
  }

  protected EDataType getBasicType(String value)
  {
    return (EDataType)ecorePackage.getEClassifier(value);
  }

  public void createEPackageForRootClasses(EList extent, RoseNode roseNode, String packageName)
  {
    boolean hasSubpackages = false;
    ArrayList list = new ArrayList();
    for (Iterator i = extent.iterator(); i.hasNext(); )
    {
      Object object = i.next();
      if (!(object instanceof EPackage))
      {
        list.add(object);
      }
    }

    if (!list.isEmpty())
    {
      EPackage ePackage = ecoreFactory.createEPackage();
      setEPackageProperties(roseNode, ePackage, packageName.toLowerCase());

      extent.add(ePackage);
      for (Iterator i = list.iterator(); i.hasNext(); )
      {
        EClassifier eClassifier = (EClassifier)i.next();
        ePackage.getEClassifiers().add(eClassifier);
        extent.remove(eClassifier);
      }
    }
  }

  protected void build(RoseNode roseNode, Object parent, ENamedElement eNamedElement)
  {
    String quid = roseNode.getRoseId();
    if (quid != null && !quid.equals(""))
    {
      quid = quid.substring(1, quid.length()-1);
    }
    TableObject tableObj = (TableObject)roseUtil.quidTable.get(quid);
    if (tableObj!= null)
    {
      tableObj.setObject(eNamedElement);
    }

    if (parent instanceof EPackage)
    {
      ((EPackage)parent).getEClassifiers().add(eNamedElement);
    }
    else if (parent instanceof EList)
    {
      ((EList)parent).add(eNamedElement);
    }
  }

  protected String upperCaseName(String name)
  {
    return
      name != null && name.length() > 0 && Character.isLowerCase(name.charAt(0)) ?
        Character.toUpperCase(name.charAt(0)) + name.substring(1) :
        name;
  }

  protected String validName(String name)
  {
    return Generator.validName(name);
  }

  protected void warning(String message)
  {
    System.err.println("-->Warning: " + message);
    roseUtil.getStatus().add
     (new Status
       (IStatus.WARNING,
        CodeGenEcorePlugin.getPlugin().getBundle().getSymbolicName(),
        0,
        message,
        null));
  }

  protected void error(String message)
  {
    System.err.println("-->Error: " + message);
    roseUtil.getStatus().add
     (new Status
       (IStatus.ERROR,
        CodeGenEcorePlugin.getPlugin().getBundle().getSymbolicName(),
        0,
        message,
        null));
  }

  // Interprets escaped characters according to Java literal rules, with one
  // exception: a single \ is taken literally, not as an error.
  protected static char parseChar(String c)
  {
    if (c == null) throw new IllegalArgumentException("null");

    if ("\\b".equals(c)) return '\b';
    if ("\\t".equals(c)) return '\t';
    if ("\\n".equals(c)) return '\n';
    if ("\\f".equals(c)) return '\f';
    if ("\\r".equals(c)) return '\r';
    if ("\\\"".equals(c)) return '\"';
    if ("\\\'".equals(c)) return '\'';
    if ("\\\\".equals(c)) return '\\';

    if (c.startsWith("\\u") && c.length() == 6)
    {
      int i = Integer.parseInt(c.substring(2), 16);
      if (i >= Character.MIN_VALUE && i <= Character.MAX_VALUE)
      {
        return (char)i;
      }
    }
    else if (c.length() >= 2 && c.length() <= 4 && c.charAt(0) == '\\')
    {
      int i = Integer.parseInt(c.substring(1), 8);
      if (i >= Character.MIN_VALUE && i <= Character.MAX_VALUE)
      {
        return (char)i;
      }
    }

    if (c.length() != 1) throw new IllegalArgumentException(c);
    return c.charAt(0);
  }

  // Interprets escaped characters within the string according to Java
  // literal rules, with two exceptions: an unescaped " does not terminate
  // the string, and a \ not followed by b, t, n, f, r, ", ', u, or an octal
  // digit is taken literally, not as an error
  protected static String parseString(String s)
  {
    if (s == null) return null;

    int len = s.length();
    StringBuffer result = new StringBuffer(len);
    for (int i = 0; i < len; i++)
    {
      char c = s.charAt(i);
      if (c == '\\' && len > i + 1)
      {
        if ("btnfr\"\'\\".indexOf(s.charAt(i + 1)) != -1)
        {
          c = parseChar(s.substring(i, i + 2));
          i++;
        }
        else if (s.charAt(i + 1) == 'u' && len > i + 5)
        {
          c = parseChar(s.substring(i, i + 6));
          i += 5;
        }
        else
        {
          int j;  // will point at the character after 0 to 3 octal digits
          for (j = i + 1; j < len && j - i < 4; j++)
          {
            char digit = s.charAt(j);
            if  (digit < '0' || digit > '7') break;
          }
          c = parseChar(s.substring(i, j));
          i = j - 1;
        }
      }
      result.append(c);
    }
    return result.toString();
  }

  protected String getQualifiedTypeName(ETypedElement typedElement, String type)
  {
    // try to retrieve the fully qualified name of the specified type...
    if (null == type || type.length() == 0 || "void".equals(type))
    {
      return type;
    }

    String qualifiedType = type;
    // convert to dot-separated format if necessary...
    if (qualifiedType.indexOf("::") != -1)
    {
      StringTokenizer st = new StringTokenizer(qualifiedType, "::");
      if (st.hasMoreTokens())
      {
        st.nextToken();
      }
      StringBuffer stringBuffer = new StringBuffer();
      while (st.hasMoreTokens())
      {
        // remove garbage characters...
        stringBuffer.append(st.nextToken().replace('"', ' ').replace('[', ' ').replace(']', ' ').trim());
        if (st.hasMoreTokens())
        {
          stringBuffer.append(".");
        }
      }

      qualifiedType = stringBuffer.toString();
    }

    // qualify type name if not already qualified...
    if (qualifiedType.indexOf('.') == -1)
    {
      String qualifier = "";
      for (EObject parent = typedElement.eContainer(); null != parent; parent = parent.eContainer())
      {
        if (parent instanceof EPackage)
        {
          qualifier = ((EPackage) parent).getName() + "." + qualifier;
        }
      }

      qualifiedType = qualifier + qualifiedType;
    }

    if (!qualifiedType.equals(type) && !roseUtil.nameTable.containsKey(qualifiedType))
    {
      qualifiedType = type;
    }

    return qualifiedType;
  }
}
