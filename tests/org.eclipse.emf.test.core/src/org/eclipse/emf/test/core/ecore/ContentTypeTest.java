/**
 * Copyright (c) 2012 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ed Merks - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;
import org.eclipse.emf.ecore.resource.impl.PlatformContentHandlerImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLContentHandlerImpl;
import org.eclipse.emf.test.models.tree.Data;
import org.eclipse.emf.test.models.tree.Node;
import org.eclipse.emf.test.models.tree.TreeFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Test that {@link URIConverter#contentDescription(URI, Map) content descriptions} function properly
 * and that {@link Resource#OPTION_LINE_DELIMITER} is properly supported based on that.
 */
public class ContentTypeTest extends TestCase
{
  protected IProject project;
  protected URI projectURI = URI.createURI("platform:/resource/testProject");
  protected ResourceSet resourceSet;
  protected IWorkspaceRoot root;

  public ContentTypeTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ContentTypeTest");
    ts.addTest(new ContentTypeTest("testAllCombinations"));
    return ts;
  }

  /**
   * Sets up a resource set and an empty project.
   */
  @Override
  protected void setUp() throws Exception
  {
    root = ResourcesPlugin.getWorkspace().getRoot();
    project = root.getProject(projectURI.segment(1));
    project.create(null);
    project.open(null);
    resourceSet = new ResourceSetImpl();
  }

  /**
   * Deleted the test project.
   */
  @Override
  protected void tearDown() throws Exception
  {
    project.delete(IProject.FORCE, null);
  }

  /**
   * XML encodings to test.
   */
  protected static final String [] ENCODINGS = new String[] { "ASCII", "UTF-8", "UTF-16" };
  
  /**
   * Line separator preferences to test.
   */
  protected static final String LINUX = "\n";
  protected static final String WINDOWS = "\r\n";
  protected static final String MAC_OS = "\r";
  protected static final String [] LINE_SEPARATOR_PREFERENCES = new String[] { LINUX, WINDOWS, MAC_OS };

  /**
   * Simulates setting a project level workspace preference for the desired line endings for newly created files.
   */
  protected void setLineSeparatorPreference(String lineSeparator) throws Exception
  {
    IEclipsePreferences node = new ProjectScope(project).getNode(Platform.PI_RUNTIME);
    node.put(Platform.PREF_LINE_SEPARATOR, lineSeparator);
    node.flush();
  }

  /**
   * Test all the combinations of encodings and line separators preferences across EMF's platform integrated content handler as well as EMF's standalone content handler.
   */
  public void testAllCombinations() throws Exception
  {
    // Create a new resource in the resource set.
    //
    URI uri = projectURI.appendSegment("tree.xmi");
    Resource resource = resourceSet.createResource(uri, "org.eclipse.emf.ecore.xmi");
    EList<ContentHandler> contentHandlers = resourceSet.getURIConverter().getContentHandlers();

    // Create a simple model that includes data with line feeds.
    // We'd expect these to be escaped and have no impact on the determination of the resource's line delimiter.
    //
    Node node = TreeFactory.eINSTANCE.createNode();
    Data data = TreeFactory.eINSTANCE.createData();
    data.setName("\n\r\r\n\n\n");
    node.setData(data);
    resource.getContents().add(node);
    resource.getContents().add(data);

    // Iterate over the various line separator preferences.
    //
    int counter = 0;
    for (final String lineSeparator : LINE_SEPARATOR_PREFERENCES)
    {
      // Simulate the workspace preference for the line separator.
      //
      setLineSeparatorPreference(lineSeparator);

      // Simulate a stand alone version of the content handler that returns the preferred line delimiter for files new files.
      //
      XMLContentHandlerImpl.XMI xmiContentHandler =
        new XMLContentHandlerImpl.XMI()
        {
          @Override
          protected String getLineDelimiter(URI uri, InputStream inputStream, Map<?, ?> options, Map<Object, Object> context) throws IOException
          {
            String result = super.getLineDelimiter(uri, inputStream, options, context);
            return result == null ? lineSeparator : result;
          }
        };

      // Try everything with both the standard registered content handler and the specialized XMI content handler.
      //
      for (ContentHandler contentHandler : new ContentHandler[] { new PlatformContentHandlerImpl(), xmiContentHandler})
      {
        contentHandlers.clear();
        contentHandlers.add(contentHandler);
        
        // Try everything for the various character encodings.
        //
        for (String encoding : ENCODINGS)
        {
          // Use a unique new URI in an attempt to avoid Linux problems with the platform caching the old information.
          //
          resource.setURI(resource.getURI().trimSegments(1).appendSegment("tree_" + ++counter + ".xmi"));

          String message = "Combination: " +  lineSeparator.replace("\n", "\\n").replace("\r", "\\r") + " " + encoding + (contentHandler == xmiContentHandler ? " stand-alone" : " platform-integrated");

          // Specify the encoding to be used for saving as well as the option to determine the desired line delimiter during save.
          //
          Map<String, String> options = new HashMap<String, String>();
          options.put(XMLResource.OPTION_ENCODING, encoding);
          options.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);
          resource.save(options);

          // Check that this really produces a workspace file.
          //
          IFile file = root.getFile(new Path(resource.getURI().toPlatformString(true)));
          assertTrue(message, file.exists());

          // Check that the content description is as expected.
          //
          validateContentDescription(message, file, encoding, lineSeparator);

          // Test that changing the file contents to use the updated separator and then saving the resource, preserves the separator currently in the contents.
          //
          for (String updatedLineSeparator : LINE_SEPARATOR_PREFERENCES)
          {
            // Update the contents of the file to use this line separator, and check that it's been properly updated.
            //
            replace(file, updatedLineSeparator);
            validateContentDescription(message, file, encoding, updatedLineSeparator);
            
            // Save the resource and check that it's update the separator in the existing contents.
            //
            resource.save(options);
            validateContentDescription(message, file, encoding, updatedLineSeparator);
          }

          // Test that specifying the encoding and the specific desired line separator in the options for save produce results with exactly that encoding and line separator.
          //
          for (String updatedLineSeparator : LINE_SEPARATOR_PREFERENCES)
          {
            for (String updatedEncoding : ENCODINGS)
            {
              options.put(XMLResource.OPTION_ENCODING, updatedEncoding);
              options.put(Resource.OPTION_LINE_DELIMITER, updatedLineSeparator);
              resource.save(options);
              validateContentDescription(message, file, updatedEncoding, updatedLineSeparator);
            }
          }

          // Delete the file before the next iteration of the loop.
          //
          file.delete(true, null);
          assertTrue(message, !file.exists());
        }
      }
    }
  }

  private static final Pattern LINE_DELIMITER_PATTERN = Pattern.compile("(\n\r?)|(\r\n?)");

  /**
   * Replaces the contents of the file to use the given line separator.
   */
  protected void replace(IFile file, String lineSeparator) throws IOException, CoreException
  {
    InputStream contents = file.getContents();
    String charset = file.getCharset();
    BufferedReader reader = new BufferedReader(new InputStreamReader(contents, charset));
    CharArrayWriter writer = new CharArrayWriter();
    int c;
    while ((c = reader.read()) != -1)
    {
      writer.write(c);
    }
    contents.close();
    String string = writer.toString();
    String newContents = LINE_DELIMITER_PATTERN.matcher(string).replaceAll(lineSeparator);
    byte[] bytes = newContents.getBytes(charset);
    file.setContents(new ByteArrayInputStream(bytes), IResource.FORCE, null);
  }

  protected void validateContentDescription(String message, IFile file, String encoding, String lineSeparator) throws IOException, CoreException
  {
    // Check EMF's content description support for both platform resource access and for direct access to the underlying file system.
    //
    for (URI accessURI : new URI [] { URI.createPlatformResourceURI(file.getFullPath().toString(), true), URI.createFileURI(file.getLocation().toString())})
    {
      // Check that the content type can be determine and that all the properties have the expected values.
      //
      Map<String, ?> emfContentDescription = resourceSet.getURIConverter().contentDescription(accessURI, null);
      assertEquals(message, ContentHandler.Validity.VALID, emfContentDescription.get(ContentHandler.VALIDITY_PROPERTY));
      assertEquals(message, "org.eclipse.emf.ecore.xmi", emfContentDescription.get(ContentHandler.CONTENT_TYPE_PROPERTY));
      assertEquals(message, encoding, emfContentDescription.get(ContentHandler.CHARSET_PROPERTY));
      assertEquals(message, lineSeparator, emfContentDescription.get(ContentHandler.LINE_DELIMITER_PROPERTY));
      Object byteOrderMark = emfContentDescription.get(ContentHandler.BYTE_ORDER_MARK_PROPERTY);
      if ("UTF-16".equals(encoding))
      {
        // We only expect byte order markers for UTF-16 encoding.
        // The endian is hardware dependent, so we tolerate either one so the test passed for all hardware.
        //
        assertTrue(message, ContentHandler.ByteOrderMark.UTF_16BE == byteOrderMark || ContentHandler.ByteOrderMark.UTF_16LE == byteOrderMark);
      }
      else
      {
        assertEquals(message, null, byteOrderMark);
      }
    }

    // Check that the integration with the platform's content description mechanism produces the same expected results.
    //
    IContentDescription contentDescription = file.getContentDescription();
    assertEquals(message, "org.eclipse.emf.ecore.xmi", contentDescription.getContentType().getId());
    assertEquals(message, encoding, contentDescription.getProperty(IContentDescription.CHARSET));
    assertEquals(message, lineSeparator, contentDescription.getProperty(ContentHandlerImpl.Describer.LINE_DELIMITER));
    Object byteOrderMark = contentDescription.getProperty(IContentDescription.BYTE_ORDER_MARK);
    if ("UTF-16".equals(encoding))
    {
      // We only expect byte order markers for UTF-16 encoding.
      // The endian is hardware dependent, so we tolerate either one so the test passed for all hardware.
      //
      assertTrue(message, IContentDescription.BOM_UTF_16BE == byteOrderMark || IContentDescription.BOM_UTF_16LE == byteOrderMark);
    }
    else
    {
      assertEquals(message, null, byteOrderMark);
    }
  }
}
