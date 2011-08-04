package org.eclipse.emf.ecore.xcore.linking;

import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.xbase.jvmmodel.JvmModelXbaseLazyLinker;

import com.google.inject.Inject;

public class XcoreLazyLinker extends JvmModelXbaseLazyLinker
{ 
  private static final String GEN_MODEL_PACKAGE_NS_URI = "http://www.eclipse.org/emf/2002/GenModel";


  @Override
  protected void beforeModelLinked(EObject model, IDiagnosticConsumer diagnosticsConsumer)
  {
    // Do nothing.
  }
  
  
  @Override
  protected void afterModelLinked(EObject model, IDiagnosticConsumer diagnosticsConsumer)
  {
    // do nothing
  }
}
