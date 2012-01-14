/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelSwitch;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.xtext.EcoreUtil2;

public class GenModelFormatter extends EObjectFormatter
{

	private static class EcoreTitleSwitch extends EcoreSwitch<String>
	{
		@Override
		public String caseENamedElement(ENamedElement object)
		{
			EPackage pkg = EcoreUtil2.getContainerOfType(object, EPackage.class);
			StringBuilder result = new StringBuilder();
			if (pkg != object)
			{
				result.append(pkg.getName());
				result.append("::");
			}
			result.append(object.getName());
			return result.toString();
		}

		@Override
		public String caseEStructuralFeature(EStructuralFeature object)
		{
			StringBuilder result = new StringBuilder();
			result.append(doSwitch(object.getEContainingClass()));
			result.append("::");
			result.append(object.getName());
			return result.toString();
		}
	}

	private static class GenModelTitleSwitch extends GenModelSwitch<String>
	{
		@Override
		public String caseGenClass(GenClass object)
		{
			return object.getQualifiedInterfaceName();
		}

		@Override
		public String caseGenDataType(GenDataType object)
		{
			return object.getQualifiedInstanceClassName();
		}

		@Override
		public String caseGenFeature(GenFeature object)
		{
			StringBuilder result = new StringBuilder();
			result.append(doSwitch(object.getGenClass()));
			result.append(".");
			result.append(object.getName());
			return result.toString();
		}

		@Override
		public String caseGenModel(GenModel object)
		{
			return object.getModelName();
		}

		@Override
		public String caseGenPackage(GenPackage object)
		{
			return object.getQualifiedPackageInterfaceName();
		}

	}

	@Override
	protected String formatCrossRefValue(EObject object, EReference feature, EObject value)
	{
		if (value != null && !value.eIsProxy())
		{
			String title = null;
			if (value.eClass().getEPackage() == EcorePackage.eINSTANCE)
				title = new EcoreTitleSwitch().doSwitch(value);
			else if (value.eClass().getEPackage() == GenModelPackage.eINSTANCE)
				title = new GenModelTitleSwitch().doSwitch(value);
			if (title != null)
				return value.eClass().getName() + "  " + title;
		}
		return super.formatCrossRefValue(object, feature, value);
	}
}
