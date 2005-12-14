package org.eclipse.emf.exporter.util;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;

public class ExporterUtil
{
  /**
   * Returns the GenBase object's GenAnnotation, creating one if necessary.
   */
  public static GenAnnotation findOrCreateGenAnnotation(GenBase genBase, String source)
  {
    GenAnnotation genAnnotation = genBase.getGenAnnotation(source);
    if (genAnnotation == null)
    {
      genAnnotation = genBase.getGenModel().createGenAnnotation();
      genAnnotation.setSource(source);
      genBase.getGenAnnotations().add(genAnnotation);      
    }
    return genAnnotation;
  }
}
