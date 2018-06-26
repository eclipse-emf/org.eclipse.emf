/**
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.provider.annotation.EAnnotationItemProviderAdapterFactory;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;


/**
 * An extension for annotations with source <code>http://www.eclipse.org/emf/2002/GenModel</code>.
 * 
 * @since 2.14
 * @see GenModelPackage#eNS_URI
 */
public final class GenModelEAnnotationItemProviderAdapterFactory extends EAnnotationItemProviderAdapterFactory
{
  protected static final BasicEAnnotationValidator.Assistant ASSISTANT = ((BasicEAnnotationValidator)EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator(
    GenModelPackage.eNS_URI)).getAssistant();

  /**
   * Creates an instance.
   */
  public GenModelEAnnotationItemProviderAdapterFactory()
  {
    super(GenModelEditPlugin.INSTANCE, ASSISTANT);
  }

  @Override
  public boolean isShowInstances(EAnnotation eAnnotation)
  {
    return true;
  }

  @Override
  public IItemPropertyDescriptor createPropertyDescriptorDecorator(
    IItemPropertyDescriptor propertyDescriptor,
    EObject eObject,
    final String key,
    final EStructuralFeature eStructuralFeature,
    final EAnnotation eAnnotation,
    ResourceLocator resourceLocator,
    final EditingDomain domain)
  {
    return new EAnnotationItemProviderAdapterFactory.ModeledItemPropertyDescriptorDecorator(
      propertyDescriptor,
      eObject,
      key,
      eStructuralFeature,
      eAnnotation,
      resourceLocator,
      domain,
      ASSISTANT)
      {
        @Override
        public Collection<?> getChoiceOfValues(Object object)
        {
          if (eStructuralFeature == GenModelPackage.Literals.GEN_CLASS__LABEL_FEATURE)
          {
            GenClass genClass = (GenClass)this.object;
            GenFeature labelFeature = genClass.getLabelFeature();
            List<GenFeature> result = new ArrayList<GenFeature>();
            EClass modelEClass = (EClass)eAnnotation.getEModelElement();
            for (EAttribute eAttribute : modelEClass.getEAllAttributes())
            {
              if (labelFeature != null && labelFeature.getEcoreFeature() == eAttribute)
              {
                result.add(labelFeature);
              }
              else
              {
                GenFeature genFeature = GenModelFactory.eINSTANCE.createGenFeature();
                genFeature.setEcoreFeature(eAttribute);
                result.add(genFeature);
              }
            }
            return result;
          }
          else
          {
            return super.getChoiceOfValues(object);
          }
        }
      };
  }
}
