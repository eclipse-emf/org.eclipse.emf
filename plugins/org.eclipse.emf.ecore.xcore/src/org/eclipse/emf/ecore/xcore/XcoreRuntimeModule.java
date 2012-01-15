/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xcore.conversion.FixedQualifiedNameValueConverter;
import org.eclipse.emf.ecore.xcore.conversion.ValueConverterService;
import org.eclipse.emf.ecore.xcore.resource.XcoreResource;
import org.eclipse.emf.ecore.xcore.scoping.XcoreIdentifableSimpleNameProvider;
import org.eclipse.emf.ecore.xcore.scoping.XcoreImportedNamespaceAwareScopeProvider;
import org.eclipse.emf.ecore.xcore.scoping.XcoreQualifiedNameProvider;
import org.eclipse.emf.ecore.xcore.scoping.XcoreResourceDescriptionManager;
import org.eclipse.emf.ecore.xcore.scoping.XcoreResourceDescriptionStrategy;
import org.eclipse.emf.ecore.xcore.validation.XcoreDiagnosticConverter;
import org.eclipse.emf.ecore.xcore.validation.XcoreDiagnostician;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.resource.IResourceDescription.Manager;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.serializer.impl.Serializer;
import org.eclipse.xtext.service.SingletonBinding;
import org.eclipse.xtext.validation.IDiagnosticConverter;
import org.eclipse.xtext.xbase.featurecalls.IdentifiableSimpleNameProvider;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
 @SuppressWarnings("restriction")
public class XcoreRuntimeModule extends org.eclipse.emf.ecore.xcore.AbstractXcoreRuntimeModule 
{
  
  @Override
  public Class<? extends ISerializer> bindISerializer()
  {
    return Serializer.class;
  }
  
  public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy()
  {
	return XcoreResourceDescriptionStrategy.class;
  }
  
  @Override
  public Class<? extends XtextResource> bindXtextResource() 
  {
	return XcoreResource.class;
  }
  
  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider()
  {
    return XcoreQualifiedNameProvider.class;
  }
  
  @Override
  public void configureIScopeProviderDelegate(Binder binder)
  {
    binder.bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(XcoreImportedNamespaceAwareScopeProvider.class);
  }
  
  @Override
  public Class<? extends IScopeProvider> bindIScopeProvider()
  {
    return org.eclipse.emf.ecore.xcore.scoping.XcoreScopeProvider.class;
  }
  
  @Override
  public Class<? extends Manager> bindIResourceDescription$Manager()
  {
  	return XcoreResourceDescriptionManager.class;
  }
  
  public Class<? extends IReferableElementsUnloader> bindIReferableElementsUnloader() 
  {
  	return IReferableElementsUnloader.GenericUnloader.class;
  }
  
  @Override
  public Class<? extends IdentifiableSimpleNameProvider> bindIdentifiableSimpleNameProvider()
  {
    return XcoreIdentifableSimpleNameProvider.class;
  }
  
	public Class<? extends IDiagnosticConverter> bindIDiagnosticConverter() 
	{
		return XcoreDiagnosticConverter.class;
	}
	
	@Override
    @SingletonBinding
	public Class<? extends Diagnostician> bindDiagnostician() 
	{
	  return XcoreDiagnostician.class;
  }
	
	@Override
	public Class<? extends IValueConverterService> bindIValueConverterService()
	{
	  return ValueConverterService.class;
	}
	
	public Class<? extends QualifiedNameValueConverter> bindFixedQualifiedNameValueConverter() {
		return FixedQualifiedNameValueConverter.class;
	}
	
	@Override
	public Class<? extends IDerivedStateComputer> bindIDerivedStateComputer()
	{
	  return org.eclipse.emf.ecore.xcore.resource.LateInferrer.class;
	}
	
	@Override
	public Class<? extends IGenerator> bindIGenerator()
	{
	  return org.eclipse.emf.ecore.xcore.generator.XcoreGenerator.class;
	}
}
