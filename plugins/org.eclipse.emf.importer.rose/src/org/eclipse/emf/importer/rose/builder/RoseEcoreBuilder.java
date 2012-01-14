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
package org.eclipse.emf.importer.rose.builder;

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

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
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
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.importer.rose.RoseImporterPlugin;
import org.eclipse.emf.importer.rose.parser.RoseNode;
import org.eclipse.emf.importer.rose.parser.Util;


/**
 * Traverses the Rose file and create eCore object in memory.
 */
public class RoseEcoreBuilder implements RoseVisitor
{
  public boolean noQualify = false;
  public boolean unsettablePrimitive = "true".equals(System.getProperty("EMF_UNSETTABLE_PRIMITIVE"));

  protected RoseUtil roseUtil;
  protected Set<EModelElement> bounded = new HashSet<EModelElement>();

  protected Map<EStructuralFeature, String> eStructuralFeatureToXMLNamespaceMap = new HashMap<EStructuralFeature, String>();

  protected List<EStructuralFeature> eStructuralFeatures =  new BasicEList.FastCompare<EStructuralFeature>();
  protected Map<EEnum, String> eEnums = new HashMap<EEnum, String>();
  protected Map<String, Object> idToParentMap = new HashMap<String, Object>();

  protected EReference ref1 = null;
  protected EReference ref2 = null;
  protected RoseNode role1 = null;
  protected RoseNode role2 = null;
  protected EGenericType eGenericType1;
  protected EGenericType eGenericType2;
  
  protected Set<EAttribute> attributesToConvert = new HashSet<EAttribute>();

  // This could potentially map every created model element to its corresponding Rose node.
  // However, for now we're only using it as needed.
  //
  protected Map<EModelElement, RoseNode> eModelElementToRoseNodeMap = new HashMap<EModelElement, RoseNode>();

  public RoseEcoreBuilder(RoseUtil roseUtil)
  {
    super();
    this.roseUtil = roseUtil;
  }

  public void visitList(RoseNode roseNode)
  {
    // Nothing to do
  }

  public void visitObject(RoseNode roseNode)
  {
    String roseNodeValue = roseNode.getValue();
    String objectKey = roseNode.getKey();
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

    visitObject(roseNode, roseNodeValue, objectKey, objectType, objectName, parent);
  }

  protected void visitObject(RoseNode roseNode, String roseNodeValue, String objectKey, String objectType, String objectName, Object parent)
  {
    if (objectKey.equals("") && objectType.equals(RoseStrings.CLASS_CATEGORY))
    {
      visitClassCategory(roseNode, roseNodeValue, objectKey, objectName, parent);
    }
    else if (objectType.equals(RoseStrings.CLASS))
    {
      visitClass(roseNode, roseNodeValue, objectKey, objectName, parent);
    }
    else if (objectType.equals(RoseStrings.OPERATION))
    {
      visitOperation(roseNode, roseNodeValue, objectKey, objectName, parent);
    }
    else if (objectType.equals(RoseStrings.PARAMETER))
    {
      visitParameter(roseNode, roseNodeValue, objectKey, objectName, parent);
    }
    else if (objectType.equals(RoseStrings.INHERITANCE_RELATIONSHIP))
    {
      visitInheritanceRelationship(roseNode, roseNodeValue, objectKey, objectName, parent);
    }
    else if (objectType.equals(RoseStrings.CLASSATTRIBUTE) && (!roseNode.isDerived() || !"reference".equals(roseNode.getStereotype())))
    {
      visitClassattribute(roseNode, roseNodeValue, objectKey, objectName, parent);
    }
    else if (objectType.equals(RoseStrings.ASSOCIATION))
    {
      visitAssociation(roseNode, roseNodeValue, objectKey, objectName, parent);
    }
    else if (objectType.equals(RoseStrings.ROLE) && !objectName.startsWith("/"))
    {
      visitRole(roseNode, roseNodeValue, objectKey, objectName, parent);
    }
  }

  protected void visitClassCategory(RoseNode roseNode, String roseNodeValue, String objectKey, String objectName, Object parent)
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
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      if (parent instanceof EPackage)
      {
        // Add to package.
        //
        ((EPackage)parent).getESubpackages().add(ePackage);
      }
      else if (parent instanceof EList<?>)
      {
        @SuppressWarnings("unchecked")
        EList<Object> list = (EList<Object>)parent;
        list.add(ePackage);
      }
      setEPackageProperties(roseNode, ePackage, objectName.toLowerCase());
    }
    else
    {
      idToParentMap.put(roseNode.getRoseId(), parent);
    }
  }

  protected void visitClass(RoseNode roseNode, String roseNodeValue, String objectKey, String objectName, Object parent)
  {
    if (objectName == null || objectName.length() == 0)
    {
      String quid = roseNode.getRoseId();
      if (quid != null)
      {
        quid = quid.substring(1, quid.length() - 1);
      }

      objectName = "Unnamed" + quid;
      error(RoseImporterPlugin.INSTANCE.getString("_UI_UnnamedClass_message", new Object []{ objectName }));
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
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
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
        EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
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
        EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
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
        EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
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
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
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
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
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
        warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnrecognizedStereotype_message", new Object []{ stereoTypeValue, objectName }));

        // Map to an eClass.
        //
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
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
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
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

  protected void visitOperation(RoseNode roseNode, String roseNodeValue, String objectKey, String objectName, Object parent)
  {
    // Map to an EOperation.
    EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
    String operationName = roseNode.getOperationName();
    String rawName = operationName;
    if (operationName == null || operationName.length() == 0)
    {
      rawName = objectName;
      operationName = validName(objectName);
    }

    int index = rawName.lastIndexOf(">");
    if (index != -1)
    {
      if (rawName.startsWith("<"))
      {
        String templateParameters = rawName.substring(1, index);
        eOperation.getETypeParameters().addAll(parseTemplateParameters(templateParameters));
      }
      operationName = rawName.substring(index + 1).trim();
      if (roseNode.getOperationName() == null || roseNode.getOperationName().length() == 0)
      {
        operationName = validName(operationName);
      }
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

  protected void visitParameter(RoseNode roseNode, String roseNodeValue, String objectKey, String objectName, Object parent)
  {
    // Map to an EParameter as input parameter for operation.
    //
    EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
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

  protected void visitInheritanceRelationship(RoseNode roseNode, String roseNodeValue, String objectKey, String objectName, Object parent)
  {
    String quidu = roseNode.getRoseRefId();
    if (quidu != null && !quidu.equals(""))
    {
      quidu = quidu.substring(1, quidu.length() - 1);
    }
    List<String> superList = roseUtil.superTable.get(parent);
    if (superList == null)
    {
      superList = new ArrayList<String>();
      roseUtil.superTable.put(parent, superList);
    }
    superList.add(quidu);
    superList.add(roseNode.getStereotype());
  }

  protected void visitClassattribute(RoseNode roseNode, String roseNodeValue, String objectKey, String objectName, Object parent)
  {
    // Map to EAttribute, or EEnumLiteral.
    //
    if (parent instanceof EEnum)
    {
      EEnumLiteral eEnumLiteral = ((EEnum)parent).getEEnumLiteral(objectName);
      if (eEnumLiteral == null)
      {
        eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
        String literalName = roseNode.getAttributeName();
        if (literalName == null || literalName.length() == 0)
        {
          literalName = validName(objectName);
        }
        else
        {
          eEnumLiteral.setLiteral(objectName);
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

      if ((parent instanceof EDataType || parent instanceof EClass) && "javaclass".equalsIgnoreCase(stereoTypeValue))
      {
        roseUtil.typeTable.remove(parent);
        ((EClassifier)parent).setInstanceTypeName(objectName);
      }
      else if ((parent instanceof EDataType || parent instanceof EClass) && "parameter".equalsIgnoreCase(stereoTypeValue))
      {
        String attributeName = roseNode.getAttributeName();
        if (attributeName == null || attributeName.length() == 0)
        {
          attributeName = objectName;
        }
        ETypeParameter eTypeParameter = parseTemplateParameter(attributeName);
        roseNode.setNode(eTypeParameter);
        ((EClassifier)parent).getETypeParameters().add(eTypeParameter);
      }
      else if (parent instanceof EClass)
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        String attributeName = roseNode.getAttributeName();
        if (attributeName == null || attributeName.length() == 0)
        {
          attributeName = validName(objectName);
        }
  
        eAttribute.setName(attributeName);
        roseNode.setNode(eAttribute);
        ((EClass)parent).getEStructuralFeatures().add(eAttribute);
        setEAttributeProperties(roseNode, eAttribute);
        if (eAttribute.getUpperBound() == 0)
        {
          eAttribute.setUpperBound(1);
        }

        // We will need to check the containment if we have to convert the EAttribute to an EReference later. 
        //
        if (roseNode.getContainment() != null)
        {
          eModelElementToRoseNodeMap.put(eAttribute, roseNode);
        }

        // Convert to an EReference.
        //
        if ("reference".equals(stereoTypeValue))
        {
          attributesToConvert.add(eAttribute);
        }
      }
    }
  }

  protected void visitAssociation(RoseNode roseNode, String roseNodeValue, String objectKey, String objectName, Object parent)
  {
    ref1 = null;
    ref2 = null;
    role1 = null;
    role2 = null;
  }

  protected void visitRole(RoseNode roseNode, String roseNodeValue, String objectKey, String objectName, Object parent)
  {
    // map to EReference when is navigable
    //
    EReference ref = EcoreFactory.eINSTANCE.createEReference();
    ref.setUpperBound(0);
    String referenceName = roseNode.getReferenceName();
    String rawName = referenceName;
    if (referenceName == null || referenceName.length() == 0)
    {
      rawName = objectName;
      referenceName = validName(objectName);
    }

    EGenericType eGenericType = null;
    int index = rawName.indexOf("<");
    if (index != -1)
    {
      eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
      if (rawName.endsWith(">"))
      {
        String templateArguments = rawName.substring(index + 1, rawName.length() - 1);
        eGenericType.getETypeArguments().addAll(parseTemplateArguments(templateArguments));
      }
      ref.setEGenericType(eGenericType);
      referenceName = rawName.substring(0, index);
      if (roseNode.getReferenceName() == null || roseNode.getReferenceName().length() == 0)
      {
        referenceName = validName(referenceName);
      }
    }

    ref.setName(referenceName);
    roseNode.setNode(ref);
    setEReferenceProperties(roseNode, ref);
    if (ref1 == null)
    {
      ref1 = ref;
      eGenericType1 = eGenericType;
    }
    else if (ref2 == null)
    {
      ref2 = ref;
      eGenericType2 = eGenericType;
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
        ref1Quidu = ref1Quidu.substring(1, ref1Quidu.length() - 1);
      }
      String ref2Quidu = role2.getRoseRefId();
      if (ref2Quidu != null && !ref2Quidu.equals(""))
      {
        ref2Quidu = ref2Quidu.substring(1, ref2Quidu.length() - 1);
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
          roseUtil.typeTable.put(eGenericType1 == null ? ref1 : eGenericType1, obj.getName());
        }
        else
        {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeNameFor_message", new Object []{
            role1.getRoseSupplier(),
            ref1.getName() }));
          roseUtil.typeTable.put(eGenericType1 == null ? ref1 : eGenericType1, "EObject");
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
          roseUtil.typeTable.put(eGenericType2 == null ? ref2 : eGenericType2, obj.getName());
        }
        else
        {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeNameFor_message", new Object []{
            role2.getRoseSupplier(),
            ref2.getName() }));
          roseUtil.typeTable.put(eGenericType2 == null ? ref2 : eGenericType2, "EObject");
        }
      }
    }

    if (ref.getUpperBound() == 0)
    {
      setEReferenceDefaultMultiplicity(ref);
    }
  }

  protected EList<EObject> getExtentFromTableObject(RoseNode roseNode)
  {
    String quid = roseNode.getRoseId();
    if (quid != null)
    {
      quid = quid.substring(1, quid.length() - 1);
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
            error(RoseImporterPlugin.INSTANCE.getString("_UI_ContainerRelationUpperBound_message", new Object []{
              ref.getName(),
              ref.getEOpposite().getName() }));
          }
          opposite.setUpperBound(1);
        }
      }
      ref.setContainment(true);
    }
  }

  protected void setResultType(RoseNode roseNode, EOperation eOperation)
  {
    /*
    String name = roseNode.getResult();
    for (ETypeParameter eTypeParameter : eOperation.getETypeParameters())
    {
      if (eTypeParameter.getName().equals(name))
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setETypeParameter(eTypeParameter);
        eOperation.setEGenericType(eGenericType);
        return;
      }
    }
    */
    String quid = roseNode.getRoseRefId();
    if (quid != null && !quid.equals(""))
    {
      quid = quid.substring(1, quid.length() - 1);
      TableObject tableObj = (TableObject)roseUtil.quidTable.get(quid);
      if (tableObj != null)
      {
        roseUtil.typeTable.put(eOperation, tableObj.getName());
      }
      else
      {
        warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeIDFor_message", new Object []{ quid, eOperation.getName() }));
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
          eOperation.setEGenericType(parseTemplateArgument(resultValue));
        }
        else
        {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeNameFor_message", new Object []{
            roseNode.getRoseSupplier(),
            eOperation.getName() }));
          roseUtil.typeTable.put(eOperation, "EString");
        }
      }
    }
  }

  protected static final Pattern ANNOTATION_PATTERN = Pattern.compile("\\G\\s*((?>\\\\.|\\S)+)((?:\\s+(?>\\\\.|\\S)+\\s*+=\\s*(['\"])((?>\\\\.|.)*?)\\3)*)");

  protected static final Pattern ANNOTATION_DETAIL_PATTERN = Pattern.compile("\\s+((?>\\\\.|\\S)+)\\s*+=\\s*((['\"])((?>\\\\.|.)*?)\\3)");

  protected void setEModelElementProperties(RoseNode roseNode, EModelElement eModelElement)
  {
    String annotation = roseNode.getAnnotation();
    if (annotation != null)
    {
      for (Matcher matcher = ANNOTATION_PATTERN.matcher(annotation); matcher.find();)
      {
        EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        eAnnotation.setSource(CodeGenUtil.parseString(matcher.group(1)));
        for (Matcher detailMatcher = ANNOTATION_DETAIL_PATTERN.matcher(matcher.group(2)); detailMatcher.find();)
        {
          eAnnotation.getDetails().put(CodeGenUtil.parseString(detailMatcher.group(1)), CodeGenUtil.parseString(detailMatcher.group(4)));
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
      List<String> constraintList = new ArrayList<String>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(constraints); stringTokenizer.hasMoreTokens();)
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
    String nsPrefix = roseNode.getNsPrefix() == null || roseNode.getNsPrefix().length() == 0
      ? (String)roseUtil.packageNameToNSNameMap.get(packageName) : roseNode.getNsPrefix();
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

    String nsURI = roseNode.getNsURI() == null || roseNode.getNsURI().length() == 0
      ? (String)roseUtil.packageNameToNSURIMap.get(packageName) : roseNode.getNsURI();
    if (nsURI == null || nsURI.length() == 0)
    {
      if (noQualify)
      {
        nsURI = nsPrefix + ".ecore";
      }
      else
      {
        nsURI = "http:///" + nsPrefix.replace('.', '/') + ".ecore";
      }
    }
    ePackage.setNsURI(nsURI);

    if (prefix != null && prefix.length() == 0)
      prefix = null;
    if (basePackage != null && basePackage.length() == 0)
      basePackage = null;

    if (prefix != null || basePackage != null)
    {
      List<String> information = new ArrayList<String>();
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
    String xmlName = roseNode.getXMLName();
    if (xmlName != null && xmlName.length() != 0)
    {
      ExtendedMetaData.INSTANCE.setName(eDataType, xmlName);
    }
    eDataType.setSerializable(!roseNode.isAbstract());
  }

  protected void setEEnumProperties(RoseNode roseNode, EEnum eEnum)
  {
    setEModelElementProperties(roseNode, eEnum);
    String xmlName = roseNode.getXMLName();
    if (xmlName != null && xmlName.length() != 0)
    {
      ExtendedMetaData.INSTANCE.setName(eEnum, xmlName);
    }
    String value = roseNode.getDocumentation();
    if (value != null && !value.equals(""))
    {
      eEnums.put(eEnum, value);
    }
  }

  protected void populateEEnumFromDocumentation(EEnum eEnum, String documentation)
  {
    // process documentation info and create eEnumLiteral for each line
    //
    List<EEnumLiteral> eLiterals = eEnum.getELiterals();
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
        numberValue = Integer.parseInt(number);
      }
      else if (!eLiterals.isEmpty())
      {
        numberValue = eLiterals.get(eLiterals.size() - 1).getValue() + 1;
      }

      if (!name.equals(""))
      {
        EEnumLiteral lit = eEnum.getEEnumLiteral(name);
        if (lit == null)
        {
          lit = EcoreFactory.eINSTANCE.createEEnumLiteral();
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
    setETypedElementProperties(roseNode, eOperation);
    eOperation.setOrdered(roseNode.isOrdered());
    eOperation.setUnique(roseNode.isUnique());
    
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
      for (StringTokenizer stringTokenizer = new StringTokenizer(exceptions.trim(), ","); stringTokenizer.hasMoreTokens();)
      {
        // This handles Rose 2003 format, e.g.,
        // Logical View::JavaException[40722F9D0294]
        //
        String exception = stringTokenizer.nextToken().trim();
        if (exception.indexOf("[") != -1)
        {
          exception = exception.substring(0, exception.indexOf("["));
        }
          
        if (exception != null && !exception.equals(""))
        {
          EGenericType eGenericType = parseTemplateArgument(exception);
          eOperation.getEGenericExceptions().add(eGenericType);
        }
        /*
        String exceptionValue = getQualifiedTypeName(eOperation, exception);
        if (exceptionValue != null && !exceptionValue.equals(""))
        {
          EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
          eAnnotation.getReferences().add(eOperation);
          eAnnotation.getDetails().put("position", Integer.toString(count++));
          roseUtil.typeTable.put(eAnnotation, exceptionValue);
        }
        */
        else
        {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeNameFor_message", new Object []{
            roseNode.getRoseSupplier(),
            eOperation.getName() }));
        }
      }
    }

    String stereotype = roseNode.getStereotype();
    if (stereotype != null)
    {
      if ("inv".equals(stereotype))
      {
        eOperation.setEType(EcorePackage.Literals.EBOOLEAN);

        eOperation.getEParameters().clear();

        EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
        eParameter.setName("diagnostics");
        eParameter.setEType(EcorePackage.Literals.EDIAGNOSTIC_CHAIN);
        eOperation.getEParameters().add(eParameter);

        eParameter = EcoreFactory.eINSTANCE.createEParameter();
        eParameter.setName("context");

        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setEClassifier(EcorePackage.Literals.EMAP);
        EGenericType eGenericKeyType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericKeyType.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
        eGenericType.getETypeArguments().add(eGenericKeyType);
        EGenericType eGenericValueType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericValueType.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
        eGenericType.getETypeArguments().add(eGenericValueType);
        eParameter.setEGenericType(eGenericType);

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
      TableObject tableObj = (TableObject)roseUtil.quidTable.get(quid);
      if (tableObj != null)
      {
        roseUtil.typeTable.put(eAttribute, tableObj.getName());
      }
      else
      {
        warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeIDFor_message", new Object []{ quid, eAttribute.getName() }));
        roseUtil.typeTable.put(eAttribute, "String");
      }
    }
    else
    {
      String roseType = roseNode.getType();
      if (roseType == null || roseType.equals(""))
      {
        roseUtil.typeTable.put(eAttribute, "String");
        warning(RoseImporterPlugin.INSTANCE.getString("_UI_AttributeDoesNotDefineItsType_message", new Object []{ eAttribute.getName() }));
      }
      else
      {
        eAttribute.setEGenericType(parseTemplateArgument(roseType));
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
          int i = CodeGenUtil.parseChar(initv.substring(1, initv.length() - 1));
          initv = Integer.toString(i);
        }
        catch (IllegalArgumentException e)
        {
          error(RoseImporterPlugin.INSTANCE.getString("_UI_InvalidDefaultValueForAttribute_message", new Object []{ eAttribute.getName() }));
          initv = null;
        }
      }
      else if (initv.charAt(0) == '\"' && initv.charAt(initv.length() - 1) == '\"')
      {
        try
        {
          initv = CodeGenUtil.parseString(initv.substring(1, initv.length() - 1));
        }
        catch (IllegalArgumentException e)
        {
          error(RoseImporterPlugin.INSTANCE.getString("_UI_InvalidDefaultValueForAttribute_message", new Object []{ eAttribute.getName() }));
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
    eAttribute.setOrdered(roseNode.isOrdered());
    eAttribute.setUnique(roseNode.isUnique());
    eAttribute.setUnsettable(roseNode.isUnsettable());
    eAttribute.setID(roseNode.isID());

    setEStructuralFeatureVisibility(roseNode, eAttribute);
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
    eReference.setOrdered(roseNode.isOrdered());
    eReference.setUnique(roseNode.isUnique());

    setEStructuralFeatureVisibility(roseNode, eReference);
  }

  protected void setEStructuralFeatureProperties(RoseNode roseNode, EStructuralFeature eStructuralFeature)
  {
    setETypedElementProperties(roseNode, eStructuralFeature);

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
  }

  protected void setETypedElementProperties(RoseNode roseNode, ETypedElement eTypedElement)
  {
    setEModelElementProperties(roseNode, eTypedElement);

    String multiplicity = eTypedElement instanceof EReference ?  roseNode.getRoleMultiplicity() : roseNode.getStereotype();
    if (multiplicity != null)
    {
      bounded.add(eTypedElement);

      if (multiplicity.length() > 0 && Character.isLetter(multiplicity.charAt(0)) && !"n".equalsIgnoreCase(multiplicity))
      {
        return;
      }

      StringTokenizer stringTokenizer = new StringTokenizer(multiplicity, ". \n\r\t");
      switch (stringTokenizer.countTokens())
      {
        case 1: {
          String bound = stringTokenizer.nextToken();
          if (bound.equals("*") || bound.equalsIgnoreCase("n"))
          {
            eTypedElement.setUpperBound(-1);
          }
          else
          {
            try
            {
              int boundValue = Integer.parseInt(bound);
              if (boundValue > 0)
              {
                eTypedElement.setLowerBound(boundValue);
                eTypedElement.setUpperBound(boundValue);
              }
              else
              {
                warning(RoseImporterPlugin.INSTANCE.getString("_UI_BadMultiplicityFor_message", new Object []{
                  multiplicity,
                  eTypedElement.getName() }));
              }
            }
            catch (NumberFormatException exception)
            {
              warning(RoseImporterPlugin.INSTANCE.getString("_UI_BadMultiplicityFor_message", new Object []{
                multiplicity,
                eTypedElement.getName() }));
            }
          }
          break;
        }
        case 2: {
          String lowerBound = stringTokenizer.nextToken();
          try
          {
            int lowerBoundValue = Integer.parseInt(lowerBound);
            if (lowerBoundValue >= 0)
            {
              String upperBound = stringTokenizer.nextToken();
              if (upperBound.equals("*") || upperBound.equalsIgnoreCase("n"))
              {
                eTypedElement.setLowerBound(lowerBoundValue);
                eTypedElement.setUpperBound(-1);
              }
              else
              {
                int upperBoundValue = Integer.parseInt(upperBound);
                if (upperBoundValue <= 0 || lowerBoundValue > upperBoundValue)
                {
                  warning(RoseImporterPlugin.INSTANCE.getString("_UI_BadMultiplicityFor_message", new Object []{
                    multiplicity,
                    eTypedElement.getName() }));
                }
                else
                {
                  eTypedElement.setLowerBound(lowerBoundValue);
                  eTypedElement.setUpperBound(upperBoundValue);
                }
              }
            }
            else
            {
              warning(RoseImporterPlugin.INSTANCE.getString("_UI_BadMultiplicityFor_message", new Object []{
                multiplicity,
                eTypedElement.getName() }));
            }
          }
          catch (NumberFormatException exception)
          {
            warning(RoseImporterPlugin.INSTANCE.getString("_UI_BadMultiplicityFor_message", new Object []{
              multiplicity,
              eTypedElement.getName() }));
          }
          break;
        }
        default: {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_BadMultiplicityFor_message", new Object []{
            multiplicity,
            eTypedElement.getName() }));
        }
      }
    }
  }

  /**
   * Maps the single, user-friendly Rose setting into accessor method suppression, via EcoreUtil.
   */
  protected void setEStructuralFeatureVisibility(RoseNode roseNode, EStructuralFeature eStructuralFeature)
  {
    switch (roseNode.getVisibility())
    {
      case RoseNode.VISIBILITY_NONE:
        EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.GET, true);

        if (eStructuralFeature.isChangeable() && !eStructuralFeature.isMany())
        {
          EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.SET, true);
        }

        if (eStructuralFeature.isUnsettable())
        {
          EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.IS_SET, true);
          if (eStructuralFeature.isChangeable())
          {
            EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.UNSET, true);
          }
        }
        break;
      case RoseNode.VISIBILITY_READ_ONLY:
        if (eStructuralFeature.isMany())
        {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_InvalidReadOnlyVisibility_message", new Object []{ eStructuralFeature.getName() }));
        }
        else if (eStructuralFeature.isChangeable())
        {
          EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.SET, true);
        }

        if (eStructuralFeature.isUnsettable())
        {
          EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.IS_SET, true);
          if (eStructuralFeature.isChangeable())
          {
            EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.UNSET, true);
          }
        }
        break;
      case RoseNode.VISIBILITY_READ_WRITE:
        if (!eStructuralFeature.isChangeable() && !eStructuralFeature.isMany())
        {
          warning(RoseImporterPlugin.INSTANCE.getString(
            "_UI_InvalidReadWriteVisibility_message",
            new Object []{ eStructuralFeature.getName() }));
        }

        if (eStructuralFeature.isUnsettable())
        {
          EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.IS_SET, true);
          if (eStructuralFeature.isChangeable())
          {
            EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.UNSET, true);
          }
        }
        break;
      case RoseNode.VISIBILITY_READ_ONLY_UNSETTABLE:
        if (eStructuralFeature.isMany())
        {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_InvalidReadOnlyVisibility_message", new Object []{ eStructuralFeature.getName() }));
        }
        else if (eStructuralFeature.isChangeable())
        {
          EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.SET, true);
        }

        if (!eStructuralFeature.isUnsettable())
        {
          warning(RoseImporterPlugin.INSTANCE.getString(
            "_UI_InvalidUnsettableVisibility_message",
            new Object []{ eStructuralFeature.getName() }));
        }
        else if (eStructuralFeature.isChangeable())
        {
          EcoreUtil.setSuppressedVisibility(eStructuralFeature, EcoreUtil.UNSET, true);
        }
        break;
      case RoseNode.VISIBILITY_READ_WRITE_UNSETTABLE:
        if (!eStructuralFeature.isChangeable())
        {
          warning(RoseImporterPlugin.INSTANCE.getString(
            "_UI_InvalidReadWriteVisibility_message",
            new Object []{ eStructuralFeature.getName() }));
        }
        if (!eStructuralFeature.isUnsettable())
        {
          warning(RoseImporterPlugin.INSTANCE.getString(
            "_UI_InvalidUnsettableVisibility_message",
            new Object []{ eStructuralFeature.getName() }));
        }
        break;
      default:
        break;
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
    /*
    String name = roseNode.getType();
    for (ETypeParameter eTypeParameter : eParameter.getEOperation().getETypeParameters())
    {
      if (eTypeParameter.getName().equals(name))
      {
        EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
        eGenericType.setETypeParameter(eTypeParameter);
        eParameter.setEGenericType(eGenericType);
        return;
      }
    }
    */
    setEModelElementProperties(roseNode, eParameter);
    String quid = roseNode.getRoseRefId();
    if (quid != null && !quid.equals(""))
    {
      quid = quid.substring(1, quid.length() - 1);
      TableObject tableObj = (TableObject)roseUtil.quidTable.get(quid);

      if (tableObj != null)
      {
        roseUtil.typeTable.put(eParameter, tableObj.getName());
      }
      else
      {
        warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeIDFor_message", new Object []{
          quid,
          eParameter.getEOperation().getName() }));
        roseUtil.typeTable.put(eParameter, "EObject");
      }
    }
    else
    {
      String type = getQualifiedTypeName(eParameter, roseNode.getType());
      if (type != null && !type.equals(""))
      {
        eParameter.setEGenericType(parseTemplateArgument(type));
      }
      else
      {
        warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeNameFor_message", new Object []{
          roseNode.getRoseSupplier(),
          eParameter.getEOperation().getName() }));
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
    for (Map.Entry<EEnum, String> entry : eEnums.entrySet())
    {
      EEnum eEnum = entry.getKey();
      if (eEnum.getELiterals().isEmpty())
      {
        populateEEnumFromDocumentation(eEnum, entry.getValue());
      }
    }
  }

  protected static Comparator<Object> eClassComparator = new Comparator<Object>()
    {
      public int compare(Object o1, Object o2)
      {
        // Order first by number of features (descending) and then alphabetically (ascending)
        //
        EClass c1 = (EClass)(o1 instanceof EGenericType ? ((EGenericType)o1).getEClassifier() : o1);
        EClass c2 = (EClass)(o2 instanceof EGenericType ? ((EGenericType)o2).getEClassifier() : o2);
        int count1 = c1.getEAllAttributes().size() + c1.getEAllReferences().size();
        int count2 = c2.getEAllAttributes().size() + c2.getEAllReferences().size();
        if (count1 < count2)
          return 1;
        if (count1 > count2)
          return -1;
        return c1.getName().compareTo(c2.getName());
      }
    };

  public void setSuper()
  {
    Map<EClass, List<EGenericType>[]> superMap = new HashMap<EClass, List<EGenericType>[]>();
    for (Iterator<Object> i = roseUtil.superTable.keySet().iterator(); i.hasNext();)
    {
      Object subObject = i.next();
      if (subObject instanceof EClass)
      {
        EClass eClass = (EClass)subObject;
        List<EGenericType> extend = new ArrayList<EGenericType>();
        List<EGenericType> unspecified = new ArrayList<EGenericType>();
        List<EGenericType> mixin = new ArrayList<EGenericType>();
        List<EGenericType> nonClass = new ArrayList<EGenericType>();
        for (Iterator<String> j = roseUtil.superTable.get(eClass).iterator(); j.hasNext();)
        {
          String quid = j.next();
          String stereotype = j.next();
          TableObject tableObject = (TableObject)roseUtil.quidTable.get(quid);
          if (tableObject != null)
          {
            Object superObject = tableObject.getObject();
            if (superObject instanceof EClass)
            {
              EClass superClass = (EClass)superObject;
              EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
              eGenericType.setEClassifier(superClass);
              if (stereotype != null)
              {
                int start = stereotype.indexOf("<");
                if (start != -1)
                {
                  if (stereotype.endsWith(">"))
                  {
                    String templateArguments = stereotype.substring(start + 1, stereotype.length() - 1);
                    eGenericType.getETypeArguments().addAll(parseTemplateArguments(templateArguments));
                  }
                  stereotype = stereotype.substring(0, start);
                }
              }
              if (!superClass.isInterface())
              {
                if ("extend".equals(stereotype))
                {
                  extend.add(eGenericType);
                }
                else if ("mixin".equals(stereotype))
                {
                  mixin.add(eGenericType);
                }
                else
                {
                  unspecified.add(eGenericType);
                }
              }
              else
              {
                nonClass.add(eGenericType);
              }
            }
            else
            {
              warning(RoseImporterPlugin.INSTANCE.getString("_UI_CannotAddSuperLinkBetween_message", new Object []{
                eClass.getName(),
                ((ENamedElement)superObject).getName() }));
            }
          }
        }

        if (extend.size() > 1)
        {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_CannotSpecifyMoreThanOneExtendFor_message", new Object []{ eClass.getName() }));
        }

        @SuppressWarnings("unchecked")
        List<EGenericType>[] lists = new List[]{extend, unspecified, mixin};
        superMap.put(eClass, lists);

        eClass.getEGenericSuperTypes().addAll(extend);
        eClass.getEGenericSuperTypes().addAll(unspecified);
        eClass.getEGenericSuperTypes().addAll(mixin);
        eClass.getEGenericSuperTypes().addAll(nonClass);

        /*
        eClass.getESuperTypes().addAll(extend);
        eClass.getESuperTypes().addAll(unspecified);
        eClass.getESuperTypes().addAll(mixin);
        eClass.getESuperTypes().addAll(nonClass);
        */
      }
      else
      {
        warning(RoseImporterPlugin.INSTANCE.getString(
          "_UI_CannotAddSuperLinkInvolving_message",
          new Object []{ ((ENamedElement)subObject).getName() }));
      }
    }

    sortSuper(superMap);
  }

  protected void sortSuper(Map<EClass, List<EGenericType>[]> superMap)
  {
    for (Map.Entry<EClass, List<EGenericType>[]> entry : superMap.entrySet())
    {
      EClass eClass = entry.getKey();
      List<EGenericType>[] collections = entry.getValue();
      Collections.sort(collections[0], eClassComparator);
      Collections.sort(collections[1], eClassComparator);
      Collections.sort(collections[2], eClassComparator);
      List<EGenericType> combined = new UniqueEList<EGenericType>(collections[0]);
      combined.addAll(collections[1]);
      combined.addAll(collections[2]);
      EList<EGenericType> eSuper = eClass.getEGenericSuperTypes();
      for (ListIterator<EGenericType> ordered = combined.listIterator(); ordered.hasNext();)
      {
        EGenericType eSuperItem = ordered.next();
        eSuper.move(ordered.previousIndex(), eSuperItem);
      }
    }
  }
  
  protected List<ETypeParameter> parseTemplateParameters(String templateParameters)
  {
    List<ETypeParameter> result = new ArrayList<ETypeParameter>();
    int start = 0;
    int depth = 0;
    for (int i = 0, length = templateParameters.length(); i < length; ++i)
    {
      char character = templateParameters.charAt(i);
      switch (character)
      {
        case ' ':
        {
          if (start == i)
          {
            ++start;
          }
          break;
        }
        case '<':
        {
          ++depth;
          break;
        }
        case '>':
        {
          --depth;
          break;
        }
        case ',':
        {
          if (depth == 0)
          {
            result.add(parseTemplateParameter(templateParameters.substring(start, i).trim()));
            start = i + 1;
          }
          break;
        }
      }
    }
    result.add(parseTemplateParameter(templateParameters.substring(start).trim()));
    return result;
  }

  ETypeParameter parseTemplateParameter(String templateParameter)
  {
    ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
    int index = templateParameter.indexOf(" extends ");
    if (index == -1)
    {
      eTypeParameter.setName(templateParameter);
    }
    else
    {
      eTypeParameter.setName(templateParameter.substring(0, index).trim());
      String bounds = templateParameter.substring(index + 9).trim();
      for (StringTokenizer stringTokenizer = new StringTokenizer(bounds, "&"); stringTokenizer.hasMoreTokens(); )
      {
        String templateArgument = stringTokenizer.nextToken().trim();
        eTypeParameter.getEBounds().add(parseTemplateArgument(templateArgument));
      }
    }
    return eTypeParameter;
  }

  protected List<EGenericType> parseTemplateArguments(String templateArguments)
  {
    List<EGenericType> result = new ArrayList<EGenericType>();
    int start = 0;
    int depth = 0;
    for (int i = 0, length = templateArguments.length(); i < length; ++i)
    {
      char character = templateArguments.charAt(i);
      switch (character)
      {
        case ' ':
        {
          if (start == i)
          {
            ++ start;
          }
          break;
        }
        case '<':
        {
          ++depth;
          break;
        }
        case '>':
        {
          --depth;
          break;
        }
        case ',':
        {
          if (depth == 0)
          {
            result.add(parseTemplateArgument(templateArguments.substring(start, i).trim()));
            start = i + 1;
          }
          break;
        }
      }
    }
    result.add(parseTemplateArgument(templateArguments.substring(start).trim()));
    return result;
  }
  
  EGenericType parseTemplateArgument(String templateArgument)
  {
    EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
    int index = 0;
    int length = templateArgument.length();
    if (index < length)
    {
      if (templateArgument.charAt(index) == '?')
      {
        ++index;
        while (index < length && Character.isWhitespace(templateArgument.charAt(index)))
        {
          ++index;
        }
        if (templateArgument.indexOf("extends ") == index)
        {
          eGenericType.setEUpperBound(parseTemplateArgument(templateArgument.substring(index + 8).trim()));
          
        }
        else if (templateArgument.indexOf("super ") == index)
        {
          eGenericType.setELowerBound(parseTemplateArgument(templateArgument.substring(index + 6).trim()));
        }
      }
      else
      {
        index = templateArgument.indexOf('<', index);
        String name;
        if (index == -1)
        {
          name = templateArgument;
        }
        else
        {
          name = templateArgument.substring(0, index);
          eGenericType.getETypeArguments().addAll(parseTemplateArguments(templateArgument.substring(index + 1, length - 1)));
        }
        roseUtil.typeTable.put(eGenericType, name);
      }
    }
    return eGenericType;
  }

  public void setIDs(final EObject parent, EObject child)
  {
    new EcoreSwitch<Object>()
      {
        @Override
        public Object caseEPackage(EPackage ePackage)
        {
          return null;
        }

        @Override
        public Object caseEClassifier(EClassifier eClassifier)
        {
          return null;
        }

        @Override
        public Object caseEOperation(EOperation eOperation)
        {
          return null;
        }

        @Override
        public Object caseEParameter(EParameter eParameter)
        {
          return null;
        }

        @Override
        public Object caseEStructuralFeature(EStructuralFeature eStructuralFeature)
        {
          return null;
        }

        @Override
        public Object caseEEnumLiteral(EEnumLiteral eEnumLiteral)
        {
          return null;
        }

        @Override
        public Object defaultCase(EObject eObject)
        {
          for (EObject child : eObject.eContents())
          {
            setIDs(eObject, child);
          }
          return this;
        }
      }.doSwitch(child);
  }

  public void validate(EObject object)
  {
    new EcoreSwitch<Object>()
      {
        @Override
        public Object caseEDataType(EDataType eDataType)
        {
          return validateEDataType(eDataType);
        }

        @Override
        public Object caseEEnum(EEnum eEnum)
        {
          return validateEEnum(eEnum);
        }

        @Override
        public Object caseEClass(EClass eClass)
        {
          return validateEClass(eClass);
        }

        @Override
        public Object defaultCase(EObject eObject)
        {
          for (EObject child : eObject.eContents())
          {
            validate(child);
          }
          return this;
        }
      }.doSwitch(object);
  }

  protected Object validateEDataType(EDataType eDataType)
  {
    if (!(eDataType instanceof EEnum) && eDataType.getInstanceClassName() == null)
    {
      error(RoseImporterPlugin.INSTANCE.getString("_UI_DatatypeNotSetFor_message", new Object []{ eDataType.getName() }));
      eDataType.setInstanceClassName("java.lang.String");
    }
    return null;
  }

  protected Object validateEEnum(EEnum eEnum)
  {
    for (Iterator<EEnumLiteral> literals = eEnum.getELiterals().iterator(); literals.hasNext();)
    {
      EEnumLiteral eEnumLiteral = literals.next();
      for (EEnumLiteral otherLiteral : eEnum.getELiterals())
      {
        if (eEnumLiteral == otherLiteral)
        {
          break;
        }
        else if (eEnumLiteral.getName().equalsIgnoreCase(otherLiteral.getName()))
        {
          error(RoseImporterPlugin.INSTANCE.getString("_UI_DuplicateLiteral_message", new Object []{ eEnumLiteral.getName(), eEnum.getName() }));
          literals.remove();
          break;
        }
      }
    }
    return this;
  }

  protected Object validateEClass(EClass eClass)
  {
    List<EReference> oppositesToRemove = new ArrayList<EReference>();
    for (Iterator<EStructuralFeature> features = eClass.getEStructuralFeatures().iterator(); features.hasNext();)
    {
      EStructuralFeature eStructuralFeature = features.next();
      if (eStructuralFeature instanceof EAttribute)
      {
        EAttribute eAttribute = (EAttribute)eStructuralFeature;

        // Temporary WAS/WSAD migration option.
        //
        if (unsettablePrimitive)
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
            // Ignore
          }
        }

        for (EStructuralFeature otherFeature : eClass.getEAllStructuralFeatures())
        {
          if (eAttribute == otherFeature)
          {
            break;
          }
          else if (eAttribute.getName().equalsIgnoreCase(otherFeature.getName()))
          {
            error(RoseImporterPlugin.INSTANCE.getString("_UI_DuplicateAttribute_message", new Object []{
              eAttribute.getName(),
              eClass.getName() }));
            features.remove();
            break;
          }
          else if (!eAttribute.getEAttributeType().isSerializable() &&
                     !eAttribute.isTransient() && 
                     !"org.eclipse.emf.ecore.util.FeatureMap$Entry".equals(eAttribute.getEAttributeType().getInstanceClassName()))
          {
            error(RoseImporterPlugin.INSTANCE.getString("_UI_TheAttributeShouldBeTransient_message", new Object []{
              eAttribute.getName(),
              eAttribute.getEType().getName() }));
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
            error(RoseImporterPlugin.INSTANCE.getString("_UI_AnAssociationHasADanglingEnd_message", new Object []{
              opposite.getName(),
              eReference.getName() }));
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
                error(RoseImporterPlugin.INSTANCE.getString("_UI_ContainerRelationUpperBound_message", new Object []{
                  opposite.getName(),
                  eReference.getName() }));
              }
              eReference.setUpperBound(1);
            }
          }
        }

        if (eReference.isTransient() && !eReference.isVolatile() && opposite != null && !opposite.isTransient()
          && opposite.isResolveProxies() && !opposite.isContainment())
        {
          error(RoseImporterPlugin.INSTANCE.getString("_UI_CrossDocumentBidirectionalTransient_message", new Object []{
            opposite.getName(),
            eReference.getName() }));
        }

        for (EStructuralFeature otherFeature : eClass.getEAllStructuralFeatures())
        {
          if (eReference == otherFeature)
          {
            break;
          }
          else if (eReference.getName().equalsIgnoreCase(otherFeature.getName()))
          {
            error(RoseImporterPlugin.INSTANCE.getString("_UI_DuplicateReference_message", new Object []{
              eReference.getName(),
              eClass.getName() }));
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
            error(RoseImporterPlugin.INSTANCE.getString("_UI_MultiplicityManyContainmentIsAssumedFor_message", new Object []{
              eReference.getName(),
              eClass.getName() }));
            eReference.setContainment(true);
            eReference.setUpperBound(-1);
          }
        }
      }
    }

    for (EReference opposite : oppositesToRemove)
    {
      EClass oppositeEClass = opposite.getEContainingClass();
      if (oppositeEClass != null)
      {
        oppositeEClass.getEStructuralFeatures().remove(opposite);
      }
    }

    for (EOperation eOperation : eClass.getEOperations())
    {
      EClassifier opType = eOperation.getEType();
      if (opType instanceof EClass && "java.util.Map$Entry".equals(opType.getInstanceClassName()))
      {
        if (!eOperation.isMany())
        {
          warning
            (RoseImporterPlugin.INSTANCE.getString
              ("_UI_MultiplicityManyIsAssumedForOperation_message", new Object [] { eOperation.getName(), eClass.getName() }));
          eOperation.setUpperBound(-1);
        }
      }

      for (EParameter eParameter : eOperation.getEParameters())
      {
        EClassifier paramType = eParameter.getEType();
        if (paramType instanceof EClass && "java.util.Map$Entry".equals(paramType.getInstanceClassName()))
        {
          if (!eParameter.isMany())
          {
            warning
              (RoseImporterPlugin.INSTANCE.getString
                ("_UI_MultiplicityManyIsAssumedForParameter_message", new Object [] { eParameter.getName(), eOperation.getName(), eClass.getName() }));
            eParameter.setUpperBound(-1);
          }
        }
      }
    }

    if (eClass.getESuperTypes().size() > 1)
    {
      Iterator<EClass> superTypes = eClass.getESuperTypes().iterator();
      superTypes.next();
      while (superTypes.hasNext())
      {
        EClass superType = superTypes.next();
        superFeatureLoop: for (Iterator<EStructuralFeature> superFeatures = superType.getEAllStructuralFeatures().iterator(); superFeatures.hasNext();)
        {
          EStructuralFeature superFeature = superFeatures.next();
          for (EStructuralFeature otherFeature : eClass.getEAllStructuralFeatures())
          {
            if (superFeature == otherFeature)
            {
              break;
            }
            else if (superFeature.getName().equalsIgnoreCase(otherFeature.getName()))
            {
              error(RoseImporterPlugin.INSTANCE.getString("_UI_DuplicateFeatureInheritance_message", new Object []{
                superFeature.getName(),
                eClass.getName(),
                superType.getName() }));
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
        error(RoseImporterPlugin.INSTANCE.getString("_UI_ExpectingFeatureNamedKey_message", new Object []{ eClass.getName() }));
        eClass.setInstanceClassName(null);
      }

      if (valueFeature == null)
      {
        error(RoseImporterPlugin.INSTANCE.getString("_UI_ExpectingFeatureNamedValue_message", new Object []{ eClass.getName() }));
        eClass.setInstanceClassName(null);
      }
    }
    return this;
  }

  protected Comparator<EStructuralFeature> eStructuralFeatureComparator = new Comparator<EStructuralFeature>()
    {
      public int compare(EStructuralFeature o1, EStructuralFeature o2)
      {
        return eStructuralFeatures.indexOf(o1) - eStructuralFeatures.indexOf(o2);
      }
    };

  public void setEReferences()
  {
    // process eStructuralFeatures for association end(Role)
    //
    for (EReference eReference : roseUtil.refTable.keySet())
    {
      String quid = roseUtil.refTable.get(eReference);
      TableObject tableObject = (TableObject)roseUtil.quidTable.get(quid);
      if (tableObject != null)
      {
        Object struct = tableObject.getObject();
        if (struct instanceof EClass)
        {
          ((EClass)struct).getEStructuralFeatures().add(
            -1 - Collections.binarySearch(((EClass)struct).getEStructuralFeatures(), eReference, eStructuralFeatureComparator),
            eReference);
        }
        else
        {
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_CannotAddReference_message", new Object []{
            eReference.getName(),
            tableObject.getName() }));
        }
      }
    }

    for (Map.Entry<EStructuralFeature, String> entry : eStructuralFeatureToXMLNamespaceMap.entrySet())
    {
      EStructuralFeature eStructuralFeature = entry.getKey();
      if (eStructuralFeature.eContainer() != null)
      {
        ExtendedMetaData.INSTANCE.setNamespace(entry.getKey(), entry.getValue());
      }
    }
  }

  public void setETypeClassifier()
  {
    // setup attribute and parameter type
    //
    for (EObject element : roseUtil.typeTable.keySet())
    {
      String type = roseUtil.typeTable.get(element);
      int position = -1;
      if (element instanceof EAnnotation)
      {
        position = Integer.parseInt(((EAnnotation)element).getDetails().get("position"));
        element = ((EAnnotation)element).getReferences().get(0);
      }
      TableObject tableObj = null;
      ETypeParameter resolvedETypeParameter = null;
      if (type.indexOf(".") == -1)
      {
        String qualifier = "";
        LOOP:
        for (EObject parent = element.eContainer(); parent != null; parent = parent.eContainer())
        {
          if (parent instanceof EClass || parent instanceof EOperation)
          {
            for (ETypeParameter eTypeParameter : 
                   parent instanceof EClass ? ((EClass)parent).getETypeParameters() : ((EOperation)parent).getETypeParameters())
            {
              if (type.equals(eTypeParameter.getName()))
              {
                resolvedETypeParameter = eTypeParameter;
                break LOOP;
              }
            }
          }
          if (parent instanceof EPackage)
          {
            qualifier = ((EPackage)parent).getName() + "." + qualifier;
          }
        }
        if (resolvedETypeParameter == null)
        {
          tableObj = (TableObject)roseUtil.nameTable.get(qualifier + type);
        }
      }

      if (resolvedETypeParameter == null && tableObj == null)
      {
        tableObj = (TableObject)roseUtil.nameTable.get(type);
      }

      EClassifier eType;

      if (tableObj != null && tableObj.getObject() != null && (element instanceof ETypedElement || element instanceof EGenericType))
      {
        // Convert attributes of with EClass type to references.
        //
        eType = (EClassifier)tableObj.getObject();
      }
      else if (resolvedETypeParameter == null)
      {
        // It was not found in the model class so check if primitive type.
        //
        eType = getBasicType(type);

        if (eType == null)
        {
          EObject eNamedElement = element;
          while (!(eNamedElement instanceof ENamedElement))
          {
            eNamedElement = eNamedElement.eContainer();
          }
          warning(RoseImporterPlugin.INSTANCE.getString("_UI_UnresolvedTypeNameFor_message", new Object []{
            type,
            ((ENamedElement)eNamedElement).getName() }));
          eType = getBasicType("EString");
        }
      }
      else
      {
        // TODO So what should be the eType be now?
        //
        eType = getBasicType("EString");
      }

      EGenericType eGenericType = null;
      if (element instanceof EGenericType)
      {
        eGenericType = (EGenericType)element;
        if (resolvedETypeParameter == null)
        {
          eGenericType.setEClassifier(eType);
        }
        else
        {
          eGenericType.setETypeParameter(resolvedETypeParameter);
        }
        element = element.eContainer();
      }

      if (element instanceof EAttribute && eType instanceof EClass || attributesToConvert.contains(element))
      {
        EAttribute eAttribute = (EAttribute)element;
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        RoseNode roseNode = eModelElementToRoseNodeMap.remove(eAttribute);

        eReference.setName(eAttribute.getName());
        eReference.setTransient(eAttribute.isTransient());
        eReference.setVolatile(eAttribute.isVolatile());
        eReference.setDerived(eAttribute.isDerived());
        eReference.setChangeable(eAttribute.isChangeable());
        eReference.setLowerBound(eAttribute.getLowerBound());
        eReference.setUpperBound(eAttribute.getUpperBound());
        eReference.setContainment(roseNode == null || !"by reference".equalsIgnoreCase(roseNode.getContainment()));
        eReference.setResolveProxies(false);
        eReference.getEAnnotations().addAll(eAttribute.getEAnnotations());
        eReference.setUnsettable(eAttribute.isUnsettable());
        eReference.setOrdered(eAttribute.isOrdered());
        eReference.setUnique(eAttribute.isUnique());

        eStructuralFeatures.set(eStructuralFeatures.indexOf(eAttribute), eReference);

        EClass containingClass = eAttribute.getEContainingClass();
        containingClass.getEStructuralFeatures().add(containingClass.getEStructuralFeatures().indexOf(eAttribute), eReference);
        containingClass.getEStructuralFeatures().remove(eAttribute);

        element = eReference;
      }
      else if (element instanceof EReference)
      {
        // Convert reference to attribute if its type is an EDataType... 
        //
        EReference eReference = (EReference)element;
        boolean convert = eType instanceof EDataType;

        // ...or if it's a wildcard or group feature.  Also, make it FeatureMap-typed.
        //
        int kind = ExtendedMetaData.INSTANCE.getFeatureKind(eReference);
        if (kind == ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE || kind == ExtendedMetaData.ELEMENT_WILDCARD_FEATURE
          || kind == ExtendedMetaData.GROUP_FEATURE)
        {
          convert = true;
          eType = EcorePackage.Literals.EFEATURE_MAP_ENTRY;
        }

        if (convert)
        {
          EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();

          eAttribute.setName(eReference.getName());
          eAttribute.setTransient(eReference.isTransient());
          eAttribute.setVolatile(eReference.isVolatile());
          eAttribute.setDerived(eReference.isDerived());
          eAttribute.setChangeable(eReference.isChangeable());
          eAttribute.setLowerBound(eReference.getLowerBound());
          eAttribute.setUpperBound(eReference.getUpperBound());
          eAttribute.getEAnnotations().addAll(eReference.getEAnnotations());
          eAttribute.setUnsettable(eReference.isUnsettable());
          eAttribute.setOrdered(eReference.isOrdered());
          eAttribute.setUnique(eReference.isUnique());

          eStructuralFeatures.set(eStructuralFeatures.indexOf(eReference), eAttribute);

          EClass containingClass = eReference.getEContainingClass();
          if (containingClass != null)
          {
            containingClass.getEStructuralFeatures().add(containingClass.getEStructuralFeatures().indexOf(eReference), eAttribute);
            containingClass.getEStructuralFeatures().remove(eReference);
          }

          element = eAttribute;
        }
      }

      if (element instanceof EDataType)
      {
        if (eGenericType == null)
        {
          ((EDataType)element).setInstanceClassName(eType.getInstanceClassName());
        }
      }
      else if (position != -1)
      {
        List<EClassifier> exceptions = ((EOperation)element).getEExceptions();
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
      else if (element instanceof ETypedElement)
      {
        if (eGenericType != null)
        {
          if (!(element instanceof EOperation) || !((EOperation)element).getEGenericExceptions().contains(eGenericType))
          {
            ((ETypedElement)element).setEGenericType(eGenericType);
          }
        }
        else
        {
          ((ETypedElement)element).setEType(eType);
        }

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

  protected EClassifier getBasicType(String value)
  {
    if (value.equals("boolean") || value.equalsIgnoreCase("eboolean"))
    {
      return EcorePackage.Literals.EBOOLEAN;
    }
    else if (value.equalsIgnoreCase("boolean") || value.equalsIgnoreCase("ebooleanobject"))
    {
      return EcorePackage.Literals.EBOOLEAN_OBJECT;
    }
    else if (value.equalsIgnoreCase("string") || value.equalsIgnoreCase("estring"))
    {
      return EcorePackage.Literals.ESTRING;
    }
    else if (value.equalsIgnoreCase("char") || value.equalsIgnoreCase("echar"))
    {
      return EcorePackage.Literals.ECHAR;
    }
    else if (value.equalsIgnoreCase("character") || value.equalsIgnoreCase("echaracterobject"))
    {
      return EcorePackage.Literals.ECHARACTER_OBJECT;
    }
    else if (value.equals("double") || value.equalsIgnoreCase("edouble") || value.equalsIgnoreCase("currency"))
    {
      return EcorePackage.Literals.EDOUBLE;
    }
    else if (value.equalsIgnoreCase("double") || value.equalsIgnoreCase("edoubleobject"))
    {
      return EcorePackage.Literals.EDOUBLE_OBJECT;
    }
    else if (value.equalsIgnoreCase("int") || value.equalsIgnoreCase("eint"))
    {
      return EcorePackage.Literals.EINT;
    }
    else if (value.equalsIgnoreCase("integer") || value.equalsIgnoreCase("eintegerobject"))
    {
      return EcorePackage.Literals.EINTEGER_OBJECT;
    }
    else if (value.equals("long long") || value.equals("long") || value.equalsIgnoreCase("elong"))
    {
      return EcorePackage.Literals.ELONG;
    }
    else if (value.equalsIgnoreCase("long") || value.equalsIgnoreCase("elongobject"))
    {
      return EcorePackage.Literals.ELONG_OBJECT;
    }
    else if (value.equals("float") || value.equalsIgnoreCase("efloat") || value.equalsIgnoreCase("single"))
    {
      return EcorePackage.Literals.EFLOAT;
    }
    else if (value.equalsIgnoreCase("float") || value.equalsIgnoreCase("efloatobject"))
    {
      return EcorePackage.Literals.EFLOAT_OBJECT;
    }
    else if (value.equals("short") || value.equalsIgnoreCase("eshort"))
    {
      return EcorePackage.Literals.ESHORT;
    }
    else if (value.equalsIgnoreCase("short") || value.equalsIgnoreCase("eshortobject"))
    {
      return EcorePackage.Literals.ESHORT_OBJECT;
    }
    else if (value.equals("byte") || value.equalsIgnoreCase("ebyte"))
    {
      return EcorePackage.Literals.EBYTE;
    }
    else if (value.equals("byte[]") || value.equalsIgnoreCase("ebytearray") || value.equalsIgnoreCase("ebyte[]"))
    {
      return EcorePackage.Literals.EBYTE_ARRAY;
    }
    else if (value.equalsIgnoreCase("byte") || value.equalsIgnoreCase("ebyteObject"))
    {
      return EcorePackage.Literals.EBYTE_OBJECT;
    }
    else if (value.equalsIgnoreCase("ebigdecimal"))
    {
      return EcorePackage.Literals.EBIG_DECIMAL;
    }
    else if (value.equalsIgnoreCase("ebiginteger"))
    {
      return EcorePackage.Literals.EBIG_INTEGER;
    }
    else if (value.equalsIgnoreCase("edate"))
    {
      return EcorePackage.Literals.EDATE;
    }
    else if (value.equalsIgnoreCase("eobject"))
    {
      return EcorePackage.Literals.EOBJECT;
    }
    else if (value.equalsIgnoreCase("efeaturemapentry"))
    {
      return EcorePackage.Literals.EFEATURE_MAP_ENTRY;
    }
    else
    {
      return null;
    }
  }

  public void createEPackageForRootClasses(EList<EObject> extent, RoseNode roseNode, String packageName)
  {
    ArrayList<EObject> list = new ArrayList<EObject>();
    for (EObject eObject : extent)
    {
      if (!(eObject instanceof EPackage))
      {
        list.add(eObject);
      }
    }

    if (!list.isEmpty())
    {
      EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
      setEPackageProperties(roseNode, ePackage, packageName.toLowerCase());

      extent.add(ePackage);
      for (Iterator<EObject> i = list.iterator(); i.hasNext();)
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
      quid = quid.substring(1, quid.length() - 1);
    }
    TableObject tableObj = (TableObject)roseUtil.quidTable.get(quid);
    if (tableObj != null)
    {
      tableObj.setObject(eNamedElement);
    }

    if (parent instanceof EPackage)
    {
      ((EPackage)parent).getEClassifiers().add((EClassifier)eNamedElement);
    }
    else if (parent instanceof EList<?>)
    {
      @SuppressWarnings("unchecked")
      EList<ENamedElement> namedElements = (EList<ENamedElement>)parent;
      namedElements.add(eNamedElement);
    }
  }

  protected String upperCaseName(String name)
  {
    return name != null && name.length() > 0 && Character.isLowerCase(name.charAt(0)) ? Character.toUpperCase(name.charAt(0))
      + name.substring(1) : name;
  }

  protected String validName(String name)
  {
    return CodeGenUtil.validJavaIdentifier(name);
  }

  protected void warning(String message)
  {
    System.err.println("-->Warning: " + message);
    roseUtil.addDiagnostic(new BasicDiagnostic(Diagnostic.WARNING, RoseImporterPlugin.getPlugin().getBundle().getSymbolicName(), 0, message, null));
  }

  protected void error(String message)
  {
    System.err.println("-->Error: " + message);
    roseUtil.addDiagnostic(new BasicDiagnostic(Diagnostic.ERROR, RoseImporterPlugin.getPlugin().getBundle().getSymbolicName(), 0, message, null));
  }

  protected String getQualifiedTypeName(ETypedElement typedElement, String type)
  {
    return getQualifiedTypeName((EModelElement)typedElement, type);
  }

  protected String getQualifiedTypeName(EModelElement eModelElement, String type)
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
    if (qualifiedType.indexOf('.') == -1 && eModelElement != null)
    {
      String qualifier = "";
      for (EObject parent = eModelElement.eContainer(); null != parent; parent = parent.eContainer())
      {
        if (parent instanceof EPackage)
        {
          qualifier = ((EPackage)parent).getName() + "." + qualifier;
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
