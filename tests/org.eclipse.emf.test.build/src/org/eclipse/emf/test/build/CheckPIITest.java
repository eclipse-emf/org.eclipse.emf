/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: CheckPIITest.java,v 1.4 2008/12/01 00:47:38 nickb Exp $
 */
package org.eclipse.emf.test.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.environment.Constants;


public class CheckPIITest extends TestCase
{
  public static final boolean DEBUG = false;

  private static final int HTML = 0;
  private static final int PROPERTIES = 1;
  private static final int XML = 2;

  private String logFileName;
  
  private static FileTool.IZipFilter getTrueFilter()
  {
    return new FileTool.IZipFilter()
      {
        public boolean shouldExtract(String fullEntryName, String entryName, int depth)
        {
          return true;
        }

        public boolean shouldUnzip(String fullEntryName, String entryName, int depth)
        {
          return true;
        }
      };
  }

  /**
   * Method hasErrors.
   *
   * @param string
   * @return boolean
   */
  private boolean hasErrors(String string)
  {
    boolean result = false;
    BufferedReader aReader = null;
    try
    {
      aReader = new BufferedReader(new InputStreamReader(new FileInputStream(string)));
      String aLine = aReader.readLine();
      while (aLine != null)
      {
        int aNumber = parseLine(aLine);
        if (aNumber > 0)
        {
          result = true;
        }
        aLine = aReader.readLine();
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("** WARNING ** :: Could not open log file: " + string);
      result = false; // if no file, no errors!
    }
    catch (IOException e)
    {
      System.out.println("** ERROR ** :: Error reading log file: " + string);
      result = true;
    }
    finally
    {
      if (aReader != null)
      {
        try
        {
          aReader.close();
        }
        catch (IOException e)
        {
          result = true;
        }
      }
    }
    return result;
  }

  public void testChkpii()
  {
    if (DEBUG) { System.out.println("locateBuildGeneratedZipFiles() ... "); }

    String[] zipFiles = locateBuildGeneratedZipFiles();
    String sniffFolder = Platform.getInstanceLocation().getURL().getFile();

    if (DEBUG) { System.out.println("sniffFolder = "+sniffFolder); }

    FileTool.IZipFilter zipFilter = getTrueFilter();

    for (int i = 0; i < zipFiles.length; i++)
    {
      try
      {
            if (DEBUG) { System.out.println("Unzipping: "+zipFiles[i]); }
            FileTool.unzip(zipFilter, new ZipFile(zipFiles[i]), new File(sniffFolder));
      }
      catch (IOException e)
      {
        fail(zipFiles[i] + ": " + sniffFolder + ": " + "IOException unzipping Eclipse for chkpii");
      }
    }

    boolean htmlResult = testChkpii(HTML);
    boolean xmlResult = testChkpii(XML);
    boolean propertiesResult = testChkpii(PROPERTIES);

    String message = "../chkpii/org.eclipse.nls.html.txt:" + (htmlResult?"passed":"failed")
    + ", ../chkpii/org.eclipse.nls.xml.txt:"  + (xmlResult ?"passed":"failed")
    + ", ../chkpii/org.eclipse.nls.properties.txt:" + (propertiesResult?"passed":"failed");

	assertTrue("Translation errors in files.  See the chkpii logs in ../chkpii for details. (" + message + ")",
        (htmlResult && xmlResult && propertiesResult));
  }

  private boolean testChkpii(int type)
  {
    Runtime aRuntime = Runtime.getRuntime();
    String chkpiiString = getChkpiiString(type);
    System.out.println(chkpiiString);
    try
    {
      Process aProcess = aRuntime.exec(chkpiiString);
      BufferedReader aBufferedReader = new BufferedReader(new InputStreamReader(aProcess.getInputStream()));
      while (aBufferedReader.readLine() != null)
      {
        // ReadLine
      }
      aProcess.waitFor();
      Thread.sleep(1000);
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return false;
    }
    catch (InterruptedException e)
    {
      return false;
    }
    return !hasErrors(getOutputFile(type));
  }

  private String adjustPath(String path)
  {
    if (path == null)
      return null;

    if (File.separatorChar == '\\')
      path = path.replace('/', File.separatorChar);
    else if (File.separatorChar == '/')
      path = path.replace('\\', File.separatorChar);
    return path;
  }

  /**
   * Method getChkpiiString.
   *
   * @param type
   * @return String
   */
  private String getChkpiiString(int type)
  {
    return adjustPath(getExec() + " " + getFilesToTest(type) + " -E -O " + getOutputFile(type) + getExcludeErrors() + getExcludeFile(type)
        + " -S");
  }

  /**
   * Method locateEclipseZip.
   *
   * @return String
   */
  private String[] locateBuildGeneratedZipFiles()
  {
    // String to use when running as an automated test.
    String installDir = Platform.getInstallLocation().getURL().getPath() + File.separator + ".." + File.separator + "..";

    // String to use when running in Eclipse
    // String installDir = BootLoader.getInstallURL().getPath() + "..";

    if (DEBUG) { System.out.println("installDir = "+installDir); }

    try
    {
      installDir = adjustPath(new File(installDir).getCanonicalPath().toString());
      if (DEBUG) { System.out.println("installDir (adjusted) = "+installDir); }
    }
    catch (IOException e)
    {
      // Ignore
    }
    File aFile = new File(installDir);

    List<String> zipFiles = new ArrayList<String>();
    File[] files = aFile.listFiles();
    for (int i = 0; i < files.length; i++)
    {
      File file = files[i];
      String fileName = file.getName();
      if (DEBUG) { System.out.println("filename["+i+"] = "+fileName); }

      if (fileName.endsWith(".zip"))
      {
        if (DEBUG) { System.out.println("zip filename = "+fileName); }
        for (int j = 0; j < BUILD_GENERATED_ZIP_FILES_PREFIX.length; j++)
        {
          if (DEBUG) { System.out.println("Prefix: BUILD_GENERATED_ZIP_FILES_PREFIX["+j+"] = "+BUILD_GENERATED_ZIP_FILES_PREFIX[j]); }
          if (fileName.startsWith(BUILD_GENERATED_ZIP_FILES_PREFIX[j]))
          {
            if (DEBUG) { System.out.println("adding zip: "+file.getAbsolutePath()); }
            zipFiles.add(file.getAbsolutePath());
          }
        }
      }
    }
    if (DEBUG) { System.out.println("zipFiles.size() = "+zipFiles.size()); }
    return zipFiles.toArray(new String [zipFiles.size()]);
  }

  /**
   * Method getExcludeFiles.
   *
   * @return String
   */
  private String getExcludeFile(int type)
  {
    String file = File.separator + "chkpiiIgnoreFiles";
    switch (type)
    {
      case HTML:
        file += "_html.txt";
        break;
      case PROPERTIES:
        file += "_properties.txt";
        break;
      case XML:
        file += "_xml.txt";
        break;
      default:
        file += "_other.txt";
    }

    file = TestUtil.getPluginDirectory() + file;
    if (new File(file).isFile())
    {
      return " -X " + file;
    }

    return "";
  }

  /**
   * Method getOutputFile.
   *
   * @param type
   * @return String
   */
  private String getOutputFile(int type)
  {
    new File(logFileName).mkdirs();
    String aString = logFileName + File.separator + "org.eclipse.nls.";
    aString = new File(aString).getPath();
    switch (type)
    {
      case HTML:
        return aString + "html.txt";
      case PROPERTIES:
        return aString + "properties.txt";
      case XML:
        return aString + "xml.txt";
      default:
        return aString + "other.txt";
    }
  }

  /**
   * Method getFilesToTest.
   *
   * @param type
   * @return String
   */
  private String getFilesToTest(int type)
  {
    String sniffFolder = Platform.getInstanceLocation().getURL().getFile();
    String aString = new File(sniffFolder).getPath() + File.separator;
    switch (type)
    {
      case HTML:
        return aString + "*.htm*";
      case PROPERTIES:
        return aString + "*.properties";
      case XML:
        return aString + "*.xml";
      default:
        return aString + "*.*";
    }
  }

  /**
   * Method getExec.
   *
   * @return String
   */
  private String getExec()
  {
    return new File("chkpii.exe").getPath();
  }

  /**
   * Method getExcludeErrors.
   */
  private String getExcludeErrors()
  {
    String os = Platform.getOS();
    String fileName;
    if (os.equals(Constants.OS_WIN32))
    {
      fileName = "ignoreErrorsWindows.txt";
    }
    else
    {
      fileName = "ignoreErrorsUnix.txt";
    }
    String aString = System.getProperty("PLUGIN_PATH");
    aString = adjustPath(aString) + File.separator + fileName;
    if (new File(aString).isFile())
      return " -XM @" + aString;

    return "";
  }

  /**
   * Method parseLine.
   *
   * @param aLine
   * @return -1 if not an error or warning line or the number of errors or
   *         warnings.
   */
  private int parseLine(String aLine)
  {
    int index = aLine.indexOf("Files Could Not Be Processed: ");
    if (index == -1)
    {
      index = aLine.indexOf("Files Contain Error");
    }
    if (index == -1)
    {
      return -1;
    }
    else
    {
      String aString = aLine.substring(0, index).trim();
      return Integer.parseInt(aString);
    }
  }

  public class FileSuffixFilter implements FilenameFilter
  {
    private String suffix;

    public FileSuffixFilter(String suffix)
    {
      this.suffix = suffix;
    }

    /**
     * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
     */
    public boolean accept(File dir, String name)
    {
      int lastDot = name.lastIndexOf('.');
      if (lastDot == -1)
      {
        return false;
      }
      return name.substring(lastDot).equals(suffix);
    }
  }

  public static final String[] BUILD_GENERATED_ZIP_FILES_PREFIX = { "emf-runtime", "emf-sourcedoc", "xsd-runtime", "xsd-sourcedoc",
      "emf-xsd-SDK" }; // last one changed 041104 to fix absentee chkpii testing

  // must include non-shipping
  // test plugins
  public static final int FEATURE_COUNT = 9; // - 1; // Note this number must

  // include non-shipping test
  // feature

  public CheckPIITest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("CheckPIITests");
    ts.addTest(new CheckPIITest("testChkpii"));
    return ts;
  }

  /**
   * @see TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    assertNotNull(getExec());

    // Autoamted Test
    logFileName = Platform.getInstallLocation().getURL().getPath() + ".." + File.separator + ".." + File.separator + "results" + File.separator
        + "chkpii"; // A

    try
    {
      logFileName = adjustPath(new File(logFileName).getCanonicalPath().toString());
    }
    catch (IOException e)
    {
      // Ignore
    }

    System.setProperty("PLUGIN_PATH", adjustPath(Platform.getInstallLocation().getURL().getPath()) + "plugins");
  }
}