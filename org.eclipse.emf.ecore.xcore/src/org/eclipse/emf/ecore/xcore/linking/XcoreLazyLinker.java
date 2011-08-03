package org.eclipse.emf.ecore.xcore.linking;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.xbase.jvmmodel.JvmModelXbaseLazyLinker;

import com.google.inject.Inject;

public class XcoreLazyLinker extends JvmModelXbaseLazyLinker
{ 
  private static final String GEN_MODEL_PACKAGE_NS_URI = "http://www.eclipse.org/emf/2002/GenModel";

  @Inject
  private IReferableElementsUnloader.GenericUnloader unloader;

  @Override
  protected void beforeModelLinked(EObject model, IDiagnosticConsumer diagnosticsConsumer)
  {
    for (Iterator<EObject> i = model.eResource().getContents().iterator(); i.hasNext(); )
    {
      EObject eObject = i.next();
      if (eObject instanceof EPackage || eObject instanceof GenModel)
      {
        unloader.unloadRoot(eObject);
        i.remove();
      }
    }

    super.beforeModelLinked(model, diagnosticsConsumer);
  }
  
  @Override
  protected void afterModelLinked(EObject model, IDiagnosticConsumer diagnosticsConsumer)
  {
    if (model instanceof XPackage)
    {
      XcoreEcoreBuilder xcoreEcoreBuilder = new XcoreEcoreBuilder();
      EPackage ePackage = xcoreEcoreBuilder.getEPackage((XPackage)model);
      model.eResource().getContents().add(ePackage);
      GenModel genModel =  GenModelFactory.eINSTANCE.createGenModel();
      genModel.initialize(Collections.singleton(ePackage));
      model.eResource().getContents().add(genModel);
      genModel.initialize();
      for (Iterator<EObject> i = genModel.eAllContents(); i.hasNext(); )
      {
        EObject eObject = i.next();
        if (eObject instanceof GenBase)
        {
          GenBase genBase = (GenBase)eObject;
          EModelElement eModelElement = genBase.getEcoreModelElement();
          if (eModelElement != null)
          {
            XcoreEcoreBuilder.map(eModelElement, (GenBase)eObject);
          }
        }
      }
      
      xcoreEcoreBuilder.link(); 
    }
    super.afterModelLinked(model, diagnosticsConsumer);
  }
}
