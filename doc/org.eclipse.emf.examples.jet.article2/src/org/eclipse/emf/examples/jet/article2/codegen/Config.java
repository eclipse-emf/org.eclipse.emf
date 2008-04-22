package org.eclipse.emf.examples.jet.article2.codegen;


import org.eclipse.core.runtime.Platform;


/**
 * Meta-data for code generation.
 * 
 * @author Remko Popma
 * @version $Revision: 1.3 $ ($Date: 2008/04/22 13:35:56 $)
 */
public class Config
{

  private Object mModel;

  private String mPluginId;

  private String mClasspathVariable;

  private String mTemplateRelativeUri;

  private String mMergeXmlRelativeUri;

  private String mTargetFolder;

  private String mTargetFile;

  private String mPackageName;

  private boolean mForceOverwrite = true;

  /**
   * Constructs an uninitialized instance.
   */
  public Config()
  {
    super();
  }

  /**
   * Returns the model object to pass to the JET template.
   * 
   * @return the model object to pass to the JET template
   */
  public Object getModel()
  {
    return mModel;
  }

  /**
   * Sets the model object to pass to the JET template.
   * 
   * @param object the model object to pass to the JET template
   */
  public void setModel(Object object)
  {
    mModel = object;
  }

  /**
   * Returns the plugin id of the plugin containing the JET template file, the
   * JMerge control model XML file, and the runtime library JAR file containing
   * any classes necessary to compile the translated JET template implementation
   * class.
   * 
   * @return the plugin id
   */
  public String getPluginId()
  {
    return mPluginId;
  }

  /**
   * Sets the plugin id of the plugin containing the JET template file, the
   * JMerge control model XML file, and the runtime library JAR file containing
   * any classes necessary to compile the translated JET template implementation
   * class.
   * 
   * @param id
   *          the plugin id
   */
  public void setPluginId(String id)
  {
    mPluginId = id;
  }

  /**
   * Returns the relative URI of the XML file containing the settings for the
   * JMerge control model.
   * 
   * @return the relative URI of the jmerge settings XML file
   */
  public String getMergeXmlRelativeUri()
  {
    return mMergeXmlRelativeUri;
  }

  /**
   * Sets the relative URI of the XML file containing the settings for the
   * JMerge control model.
   * 
   * @param uri
   *          the relative URI of the jmerge settings XML file
   */
  public void setMergeXmlRelativeUri(String uri)
  {
    mMergeXmlRelativeUri = uri;
  }

  /**
   * Returns the relative uri of the JET template file.
   * 
   * @return the relative uri of the JET template file
   */
  public String getTemplateRelativeUri()
  {
    return mTemplateRelativeUri;
  }

  /**
   * Returns the relative uri of the JET template file.
   * 
   * @param uri
   *          the relative uri of the JET template file
   */
  public void setTemplateRelativeUri(String uri)
  {
    mTemplateRelativeUri = uri;
  }

  /**
   * Returns the target folder (relative to the workspace root) where the
   * generated code should be saved.
   * 
   * @return the target folder (relative to the workspace root) where the
   *         generated code should be saved
   */
  public String getTargetFolder()
  {
    return mTargetFolder;
  }

  /**
   * Sets the target folder (relative to the workspace root) where the generated
   * code should be saved.
   * 
   * @param folder
   *          the target folder (relative to the workspace root) where the
   *          generated code should be saved
   */
  public void setTargetFolder(String folder)
  {
    mTargetFolder = folder;
  }

  /**
   * Returns the package name of the resource to generate.
   * 
   * @return the package name of the resource to generate
   */
  public String getPackageName()
  {
    return mPackageName;
  }

  /**
   * Sets the package name of the resource to generate.
   * 
   * @param name
   *          the package name of the resource to generate
   */
  public void setPackageName(String name)
  {
    mPackageName = name;
  }

  /**
   * Returns whether existing read-only files should be overwritten. This method
   * returns <code>true</code> by default.
   * 
   * @return whether existing read-only files should be overwritten
   */
  public boolean isForceOverwrite()
  {
    return mForceOverwrite;
  }

  /**
   * Sets whether existing read-only files should be overwritten. 
   */
  public void setForceOverwrite(boolean force)
  {
    mForceOverwrite = force;
  }

  /**
   * Returns the full URI of the JET template. This URI is found by appending
   * the relative template URI to the installation URI of the plugin specified
   * by the {@link #getPluginId() plugin id}.
   * 
   * @return the full URI of the JET template
   */
  public String getTemplateFullUri()
  {
    return getUri(getPluginId(), getTemplateRelativeUri());
  }

  /**
   * Returns the full URI of the the XML file containing the settings for the
   * JMerge control model. This URI is found by appending the relative merge XML
   * URI to the installation URI of the plugin specified by the
   * {@link #getPluginId() plugin id}.
   * 
   * @return the full URI of the the XML file containing the settings for the
   *         JMerge control model
   */
  public String getMergeXmlFullUri()
  {
    return getUri(getPluginId(), getMergeXmlRelativeUri());
  }

  private String getUri(String pluginId, String relativeUri)
  {
    String base = Platform.getBundle(pluginId).getEntry("/").toString();
    String result = base + relativeUri;
    return result;
  }

  /**
   * Returns the file name of the file where the generated code should be saved.
   * 
   * @return the file name of the file where the generated code should be saved
   */
  public String getTargetFile()
  {
    return mTargetFile;
  }

  /**
   * Sets the file name of the file where the generated code should be saved.
   * 
   * @param name
   *          the file name of the file where the generated code should be saved
   */
  public void setTargetFile(String name)
  {
    mTargetFile = name;
  }

  /**
   * Returns the classpath variable name to bind to the first jar in the plugin
   * identified by {@link #getPluginId()}.
   * 
   * @return classpath variable name
   */
  public String getClasspathVariable()
  {
    return mClasspathVariable;
  }

  /**
   * Sets the classpath variable name to bind to the first jar in the plugin
   * identified by {@link #getPluginId()}.
   * 
   * @param name
   *          classpath variable name
   */
  public void setClasspathVariable(String name)
  {
    mClasspathVariable = name;
  }

}