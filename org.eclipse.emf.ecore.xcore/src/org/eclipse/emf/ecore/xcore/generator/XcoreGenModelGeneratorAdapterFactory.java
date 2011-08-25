package org.eclipse.emf.ecore.xcore.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenEnumGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.generator.IFileSystemAccess;

public class XcoreGenModelGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
{
	
	private IFileSystemAccess fsa;

	public void setFileSystemAccess(IFileSystemAccess fsa)
	{
		this.fsa = fsa;
	}
	
	protected OutputStream createOutputStream(URI workspacePath)
  {
		String string = workspacePath.toString();
		final String targetFile = string.substring(string.indexOf(XcoreGeneratorImpl.OUTPUT_DIR_MARKER) + XcoreGeneratorImpl.OUTPUT_DIR_MARKER.length()+1);
		return new ByteArrayOutputStream() {
			@Override
			public void close() throws IOException
			{
				fsa.generateFile(targetFile, new String(this.toByteArray()));
			  super.close();
			}
		};
  }
	
	/**
   * Returns a singleton {@link GenModelGeneratorAdapter}.
   */
  @Override
  public Adapter createGenModelAdapter()
  {
    if (genModelGeneratorAdapter == null)
    {
      genModelGeneratorAdapter = new GenModelGeneratorAdapter(this){
				@Override
				protected OutputStream createOutputStream(URI workspacePath) throws Exception
				{
				  return XcoreGenModelGeneratorAdapterFactory.this.createOutputStream(workspacePath);
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
			genPackageGeneratorAdapter = new GenPackageGeneratorAdapter(this) {
				@Override
				protected OutputStream createOutputStream(URI workspacePath) throws Exception
				{
				  return XcoreGenModelGeneratorAdapterFactory.this.createOutputStream(workspacePath);
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
      genClassGeneratorAdapter = new GenClassGeneratorAdapter(this){
				@Override
				protected OutputStream createOutputStream(URI workspacePath) throws Exception
				{
				  return XcoreGenModelGeneratorAdapterFactory.this.createOutputStream(workspacePath);
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
      genEnumGeneratorAdapter = new GenEnumGeneratorAdapter(this){
				@Override
				protected OutputStream createOutputStream(URI workspacePath) throws Exception
				{
				  return XcoreGenModelGeneratorAdapterFactory.this.createOutputStream(workspacePath);
				}
			};
    }
    return genEnumGeneratorAdapter;
  }
	
}
