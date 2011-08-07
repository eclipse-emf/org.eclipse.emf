package org.eclipse.emf.ecore.xcore.linking;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;
import org.eclipse.xtext.xbase.jvmmodel.JvmModelXbaseLazyLinker;

public class XcoreLazyLinker extends JvmModelXbaseLazyLinker
{ 
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
