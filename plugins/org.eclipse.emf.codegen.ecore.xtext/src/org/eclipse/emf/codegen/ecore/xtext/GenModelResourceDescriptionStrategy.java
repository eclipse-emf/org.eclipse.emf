/**
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.codegen.ecore.xtext;


import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

import com.google.inject.Singleton;


/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * 
 * TODO: We should probably add some hashed user data over all information that
 * impacts the code generation in order to be properly notify clients about these changes
 */
@Singleton
public class GenModelResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy
{
  @Override
  public boolean createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor)
  {
    if (eObject instanceof GenModel)
    {
      ((GenModel)eObject).reconcile();
    }
    super.createEObjectDescriptions(eObject, acceptor);
    return !(eObject instanceof GenClassifier);
  }
}
