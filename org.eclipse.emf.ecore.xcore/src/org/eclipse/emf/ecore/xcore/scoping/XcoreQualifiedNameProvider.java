package org.eclipse.emf.ecore.xcore.scoping;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.inject.Inject;

public class XcoreQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider
{
  @Inject
  private IQualifiedNameConverter nameConverter;
  
  @Override
  public QualifiedName getFullyQualifiedName(EObject eObject)
  {
    if (eObject instanceof GenClassifier)
    {
      GenClassifier genClassifier = (GenClassifier)eObject;
      String packageName = genClassifier.getGenPackage().getQualifiedPackageName();
      return nameConverter.toQualifiedName(packageName + "." + genClassifier.getName());
    }
    else if (eObject instanceof JvmType)
    {
    	JvmType type = (JvmType) eObject;
    	String typeName = type.getQualifiedName();
    	return typeName == null ? null : nameConverter.toQualifiedName(typeName);
    }
    else
    {
      return super.getFullyQualifiedName(eObject);
    }
  }

}
