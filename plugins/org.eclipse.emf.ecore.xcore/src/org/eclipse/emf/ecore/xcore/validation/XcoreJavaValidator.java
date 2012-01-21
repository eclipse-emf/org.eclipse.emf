/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.validation;


import static com.google.common.collect.Maps.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xtype.XtypePackage;


public class XcoreJavaValidator extends AbstractXcoreJavaValidator
{

  @Override
  protected List<EPackage> getEPackages()
  {
    List<EPackage> result = new ArrayList<EPackage>();
    result.add(XcorePackage.eINSTANCE);
    result.add(XbasePackage.eINSTANCE);
    result.add(TypesPackage.eINSTANCE);
    result.add(XtypePackage.eINSTANCE);
    return result;
  }

  @Check
  public void checkImports(XPackage xPackage)
  {
    final Map<EObject, XImportDirective> imports = newHashMap();
    final Map<String, EObject> importedNames = newHashMap();

    // Process all the import directives...
    //
    for (XImportDirective xImportDirective : xPackage.getImportDirectives())
    {
      // If there is a resolved imported object...
      //
      EObject importedObject = xImportDirective.getImportedObject();
      if (importedObject != null && !importedObject.eIsProxy())
      {
        // Check that we've not imported this object already....
        //
        if (imports.containsKey(importedObject))
        {
          warning("Duplicate import of '" + xImportDirective.getImportedNamespace(), xImportDirective, null, XcoreIssueCodes.DUPLICATE_IMPORT);
        }
        else
        {
          // Keep track of the fact that this object was made visible by this import.
          //
          imports.put(importedObject, xImportDirective);

          // Determine whether there is a simple name of the imported object.
          //
          String simpleName =
            importedObject instanceof JvmType ?
              ((JvmType)importedObject).getSimpleName() :
              importedObject instanceof GenClassifier ?
                ((GenClassifier)importedObject).getName() :
                importedObject instanceof XAnnotationDirective ?
                  ((XAnnotationDirective)importedObject).getName() :
                  null;
          if (simpleName != null)
          {
            // Check whether this simple name hasn't already been used to import something else...
            //
            EObject previouslyImportedObject = importedNames.put(simpleName, importedObject);
            if (previouslyImportedObject != null)
            {
              error("The import " + xImportDirective.getImportedNamespace() + " collides with another import", xImportDirective, null, XcoreIssueCodes.COLLIDING_IMPORT);

              // Behave as if this import doesn't exist so we don't get any other warnings.
              //
              imports.remove(importedObject);
              importedNames.put(simpleName, previouslyImportedObject);
            }

            // Track all the type qualified names that might be used to refer to the type being imported...
            //
            for (EObject eContainer = importedObject.eContainer(); eContainer instanceof JvmType; eContainer = eContainer.eContainer())
            {
              simpleName = ((JvmType)eContainer).getSimpleName() + "$" + simpleName;
              importedNames.put(simpleName, importedObject);
            }
          }
        }
      }
    }

    // Check for collisions with locally declared classifiers.
    //
    for (XClassifier xClassifier : xPackage.getClassifiers())
    {
      // Ignore collisions with imports that resolve to JvmTypes...
      //
      String name = xClassifier.getName();
      EObject importedObject = importedNames.get(name);
      if (importedObject != null && !(importedObject instanceof JvmType))
      {
        importedNames.remove(name);
        XImportDirective xImportDirective = imports.remove(importedObject);
        error("The import " + xImportDirective.getImportedNamespace() + " collides with a local classifier", xImportDirective, null, XcoreIssueCodes.COLLIDING_IMPORT);
      }
    }

    // Check for collisions with locally declared annotation directives.
    //
    for (XAnnotationDirective xAnnotationDirective : xPackage.getAnnotationDirectives())
    {
      // Ignore collisions with imports that resolve to JvmTypes...
      //
      String name = xAnnotationDirective.getName();
      EObject importedObject = importedNames.get(name);
      if (importedObject != null && !(importedObject instanceof JvmType))
      {
        importedNames.remove(name);
        XImportDirective xImportDirective = imports.remove(importedObject);
        error("The import " + xImportDirective.getImportedNamespace() + " collides with a local annotation directive", xImportDirective, null, XcoreIssueCodes.COLLIDING_IMPORT);
      }
    }

    // Scan all the contents to consider all the names actually being used in this resource...
    //
    for (INode node : NodeModelUtils.findActualNodeFor(xPackage).getAsTreeIterable())
    {
      if (node.getGrammarElement() instanceof CrossReference)
      {
        EClassifier classifier = ((CrossReference)node.getGrammarElement()).getType().getClassifier();
        if (classifier instanceof EClass)
        {
          EClass eClass = (EClass)classifier;

          // We're interested in references to Jvm types or constructors, Xcore annotation directives, or GenModel base references that aren't references to features, i.e,. opposites and keys.
          //
          boolean isJvmTypeReference = TypesPackage.Literals.JVM_TYPE.isSuperTypeOf(eClass) || TypesPackage.Literals.JVM_CONSTRUCTOR.isSuperTypeOf(eClass);
          if (isJvmTypeReference ||
                (XcorePackage.Literals.XANNOTATION_DIRECTIVE.isSuperTypeOf(eClass)) ||
                GenModelPackage.Literals.GEN_BASE.isSuperTypeOf(eClass) && !GenModelPackage.Literals.GEN_FEATURE.isSuperTypeOf(eClass))
          {
            String simpleName = node.getText().trim();
            if (simpleName.endsWith("::"))
            {
              simpleName = simpleName.substring(0, simpleName.length() - 2);
            }

            // If this simple name is imported, remove the corresponding object it imported as being used.
            // Note that the same import may be used by Xcore to import several different types of things,
            // i.e., a Jvm type, an GenModel classifier, and an Xcore annotation directive.
            // As long as one of those things is used, the import is used.
            //
            if (importedNames.containsKey(simpleName))
            {
              EObject importedObject = importedNames.remove(simpleName);
              imports.remove(importedObject);
            }
            else if (isJvmTypeReference)
            {
              // Consider all the qualified names as well...
              //
              while (simpleName.contains("$"))
              {
                simpleName = simpleName.substring(0, simpleName.lastIndexOf('$'));
                if (importedNames.containsKey(simpleName))
                {
                  imports.remove(importedNames.remove(simpleName));
                  break;
                }
              }
            }
          }
        }
      }
    }

    // Any imports left at this point must be unused ones.
    //
    for (XImportDirective xImportDirective : imports.values())
    {
      warning("The import '" + xImportDirective.getImportedNamespace() + "' is never used", xImportDirective, null, XcoreIssueCodes.UNUSED_IMPORT);
    }
  }

}
