package org.eclipse.emf.ant;

import org.eclipse.core.runtime.Plugin;


/**
 * @since 2.1.0
 */
public class EMFAntPlugin extends Plugin
{
  //The shared instance.
  private static EMFAntPlugin plugin;

  public EMFAntPlugin()
  {
    super();
    plugin = this;
  }

  /**
   * Returns the shared instance.
   */
  public static EMFAntPlugin getDefault()
  {
    return plugin;
  }
}