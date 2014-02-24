/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.generator;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenEnumGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccessExtension2;
import org.eclipse.xtext.generator.IFileSystemAccessExtension3;


public class XcoreGenModelGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
{
  private static final URI ROOT = URI.createURI("/");

  /**
   * An interface implemented by a derived {@link IFileSystemAccess} implementation
   * so that the callback can be informed about resources that aren't regenerated
   * because the result would be identical to the current contents.
   */
  public interface FileSystemReadAccess
  {
    void readFile(String targetFile);
  }

  private IFileSystemAccess fsa;
  private URI modelDirectory;
  private Boolean isDefaultOutput;

  public void setFileSystemAccess(IFileSystemAccess fsa)
  {
    this.fsa = fsa;
  }

  public void setModelDirectory(URI modelDirectory)
  {
    this.modelDirectory =  !modelDirectory.hasTrailingPathSeparator() ? modelDirectory.appendSegment("") : modelDirectory;
  }

  private boolean isDefaultOutput()
  {
    if (isDefaultOutput == null)
    {
      if (fsa instanceof IFileSystemAccessExtension2)
      {
        URI uri = ((IFileSystemAccessExtension2)fsa).getURI(".");
        if (uri.isPlatformResource())
        {
          String path = uri.toPlatformString(true) + "/";
          if (path.equals(modelDirectory.toString()))
          {
            return isDefaultOutput = Boolean.TRUE;
          }
          else
          {
            return isDefaultOutput = Boolean.FALSE;
          }
        }
      }
      return isDefaultOutput = Boolean.TRUE;
    }
    return isDefaultOutput;
  }

  private boolean shouldMerge(URI workspacePath)
  {
    if (isDefaultOutput())
    {
      int segmentCount = modelDirectory.segmentCount() - 1;
      if (workspacePath.segmentCount() > segmentCount)
      {
        for (int i = 0; i < segmentCount; ++i)
        {
          if (!workspacePath.segment(i).equals(modelDirectory.segment(i)))
          {
            return true;
          }
        }
        return false;
      }
    }
    return true;
  }

  protected OutputStream createOutputStream(URI workspacePath)
  {
    if (!isDefaultOutput())
    {
      return null;
    }
    else
    {
      final URI targetFile = workspacePath.replacePrefix(modelDirectory, ROOT);
      if (targetFile == null)
      {
        return null;
      }
      else
      {
        getGenerator().generatedOutput(workspacePath);
        return
          new ByteArrayOutputStream()
          {
            @Override
            public void close() throws IOException
            {
              if (fsa instanceof IFileSystemAccessExtension3)
              {
                ((IFileSystemAccessExtension3)fsa).generateFile(targetFile.toString(), new ByteArrayInputStream(buf, 0, count));
              }
              else
              {
                fsa.generateFile(targetFile.toString(), new String(this.toByteArray()));
              }
              super.close();
            }
          };
      }
    }
  }

  protected void inputStreamRequest(URI workspacePath)
  {
    if (isDefaultOutput() && fsa instanceof FileSystemReadAccess)
    {
      final URI targetFile = workspacePath.replacePrefix(modelDirectory, ROOT);
      if (targetFile != null)
      {
        ((FileSystemReadAccess)fsa).readFile(workspacePath.toString());
      }
    }
  }

  /**
   * Returns a singleton {@link GenModelGeneratorAdapter}.
   */
  @Override
  public Adapter createGenModelAdapter()
  {
    if (genModelGeneratorAdapter == null)
    {
      genModelGeneratorAdapter =
        new GenModelGeneratorAdapter(this)
        {
          @Override
          protected OutputStream createOutputStream(URI workspacePath) throws Exception
          {
            OutputStream result = XcoreGenModelGeneratorAdapterFactory.this.createOutputStream(workspacePath);
            return result == null ? super.createOutputStream(workspacePath) : result;
          }

          @Override
          protected InputStream createInputStream(URI workspacePath) throws Exception
          {
            inputStreamRequest(workspacePath);
            return super.createInputStream(workspacePath);
          }

          @Override
          protected boolean shouldMerge(URI workspacePath)
          {
            return XcoreGenModelGeneratorAdapterFactory.this.shouldMerge(workspacePath);
          }
        };
    }
    return genModelGeneratorAdapter;
  }

  /**
   * Returns a singleton {@link GenPackageGeneratorAdapter}.
   */
  @Override
  public Adapter createGenPackageAdapter()
  {
    if (genPackageGeneratorAdapter == null)
    {
      genPackageGeneratorAdapter =
        new GenPackageGeneratorAdapter(this)
        {
          @Override
          protected OutputStream createOutputStream(URI workspacePath) throws Exception
          {
            OutputStream result = XcoreGenModelGeneratorAdapterFactory.this.createOutputStream(workspacePath);
            return result == null ? super.createOutputStream(workspacePath) : result;
          }

          @Override
          protected InputStream createInputStream(URI workspacePath) throws Exception
          {
            inputStreamRequest(workspacePath);
            return super.createInputStream(workspacePath);
          }

          @Override
          protected boolean shouldMerge(URI workspacePath)
          {
            return XcoreGenModelGeneratorAdapterFactory.this.shouldMerge(workspacePath);
          }
        };
    }
    return genPackageGeneratorAdapter;
  }

  /**
   * Returns a singleton {@link GenClassGeneratorAdapter}.
   */
  @Override
  public Adapter createGenClassAdapter()
  {
    if (genClassGeneratorAdapter == null)
    {
      genClassGeneratorAdapter =
        new GenClassGeneratorAdapter(this)
        {
          @Override
          protected OutputStream createOutputStream(URI workspacePath) throws Exception
          {
            OutputStream result = XcoreGenModelGeneratorAdapterFactory.this.createOutputStream(workspacePath);
            return result == null ? super.createOutputStream(workspacePath) : result;
          }

          @Override
          protected InputStream createInputStream(URI workspacePath) throws Exception
          {
            inputStreamRequest(workspacePath);
            return super.createInputStream(workspacePath);
          }

          @Override
          protected boolean shouldMerge(URI workspacePath)
          {
            return XcoreGenModelGeneratorAdapterFactory.this.shouldMerge(workspacePath);
          }
        };
    }
    return genClassGeneratorAdapter;
  }

  /**
   * Returns a singleton {@link GenEnumGeneratorAdapter}.
   */
  @Override
  public Adapter createGenEnumAdapter()
  {
    if (genEnumGeneratorAdapter == null)
    {
      genEnumGeneratorAdapter =
        new GenEnumGeneratorAdapter(this)
        {
          @Override
          protected OutputStream createOutputStream(URI workspacePath) throws Exception
          {
            OutputStream result = XcoreGenModelGeneratorAdapterFactory.this.createOutputStream(workspacePath);
            return result == null ? super.createOutputStream(workspacePath) : result;
          }

          @Override
          protected InputStream createInputStream(URI workspacePath) throws Exception
          {
            inputStreamRequest(workspacePath);
            return super.createInputStream(workspacePath);
          }

          @Override
          protected boolean shouldMerge(URI workspacePath)
          {
            return XcoreGenModelGeneratorAdapterFactory.this.shouldMerge(workspacePath);
          }
        };
    }
    return genEnumGeneratorAdapter;
  }
}
