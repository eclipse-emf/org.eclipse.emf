package org.eclipse.emf.ecore.xcore.generator;

import org.eclipse.xtext.xbase.compiler.IAppendable;
import org.eclipse.xtext.xbase.compiler.ImportManager;
import org.eclipse.xtext.xbase.compiler.StringBuilderBasedAppendable;

public class XcoreAppendable extends StringBuilderBasedAppendable
{
	public XcoreAppendable()
	{
		super(new XcoreImportManager(), "\t");
	}
	
	@Override
	public IAppendable append(Object object)
	{
		if (object instanceof String)
		{
			String string = (String)object;
			if (string.startsWith(" else") || string.startsWith(" catch") || string.startsWith(" finally") || string.startsWith("\n"))
			{
				return super.append("\n").append(string.substring(1));
			}
			else if (string.endsWith(" {"))
			{
				return super.append(string.substring(0, string.length() - 2)).append("\n{");
			}
			else if (string.endsWith(" { "))
			{
				return super.append(string.substring(0, string.length() - 3)).append("\n{");
			}
			else if (string.startsWith("} "))
			{
				return super.append("}\n").append(string.substring(2));
			}
			else if (string.endsWith("finally "))
			{
				return super.append(string.substring(0, string.length() - 1)).append("\n");
			}
		}
    return super.append(object);
	}

}
