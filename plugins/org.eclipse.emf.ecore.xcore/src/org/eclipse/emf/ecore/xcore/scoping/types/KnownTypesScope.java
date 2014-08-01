/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.ecore.xcore.scoping.types;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.Strings;

public class KnownTypesScope extends AbstractKnownTypesScope
{
  private final List<? extends JvmType> types;

  public KnownTypesScope(List<? extends JvmType> types, AbstractXcoreScope parent)
  {
    super(parent);
    this.types = types;
  }

  @Override
  protected void doGetElements(JvmType type, List<IEObjectDescription> result)
  {
    for (int i = 0, size = types.size(); i < size; i++)
    {
      JvmType knownType = types.get(i);
      if (EcoreUtil.isAncestor(knownType, type))
      {
        doGetDescriptions(type, knownType, i, result);
      }
    }
    super.doGetElements(type, result);
  }

  @Override
  protected IEObjectDescription doGetSingleElement(QualifiedName name, String firstSegment, int dollarIndex)
  {
    int index = -1;
    JvmType result = null;
    for (int i = 0, size = types.size(); i < size; i++)
    {
      JvmType type = types.get(i);
      JvmType exactMatch = getExactMatch(type, index, name);
      if (exactMatch != null)
      {
        return EObjectDescription.create(name, exactMatch);
      }
      if (isMatch(i, type, firstSegment, name))
      {
        JvmType resolved = getUnambiguousResult(result, index, type, i, name);
        if (resolved == null)
        {
          return null;
        }
        if (resolved != result)
        {
          result = resolved;
          index = i;
        }
      }
    }
    return toDescription(name, result, dollarIndex, index);
  }

  @Override
  protected IEObjectDescription toDescription(QualifiedName name, JvmType result, int dollarIndex, int index)
  {
    if (result != null)
    {
      JvmType actualResult = dollarIndex > 0 || name.getSegmentCount() > 0 ? findNestedType(result, index, name) : result;
      if (actualResult != null)
      {
        return EObjectDescription.create(name, actualResult);
      }
    }
    return null;
  }

  /*
   * If we know java.util.Map$Entry exists and we query for the FQN, we assume things are valid.
   */
  protected JvmType getExactMatch(JvmType type, int index, QualifiedName qualifiedName)
  {
    String typeIdentifier = type.getIdentifier();
    String name = qualifiedName.toString();
    if (name.startsWith(typeIdentifier))
    {
      int typeIdentifierLength = typeIdentifier.length();
      if (typeIdentifierLength == name.length())
      {
        return type;
      }

      char delimiter = name.charAt(typeIdentifierLength);
      if (delimiter != '$' && delimiter != '.')
      {
        return null;
      }

      String nestedType = name.substring(typeIdentifierLength - type.getSimpleName().length() + 1);
      JvmType result = findNestedType(type, index, QualifiedName.create(Strings.split(nestedType, '.')));
      return result;
    }
    return null;
  }

  protected boolean isMatch(int index, JvmType type, String simpleName, QualifiedName relativeName)
  {
    return simpleName.equals(type.getSimpleName());
  }

  @Override
  protected void doGetDescriptions(JvmType type, JvmType knownType, int index, List<IEObjectDescription> result)
  {
    if (type == knownType)
    {
      result.add(EObjectDescription.create(QualifiedName.create(type.getSimpleName()), type));
    }
    else if (type.eContainer() == knownType)
    {
      String knownTypeSimpleName = knownType.getSimpleName();
      String typeSimpleName = type.getSimpleName();
      result.add(EObjectDescription.create(QualifiedName.create(knownTypeSimpleName, typeSimpleName), type));
      result.add(EObjectDescription.create(QualifiedName.create(knownTypeSimpleName + '$' + typeSimpleName), type));
    }
    else
    {
      String knownTypeName = knownType.getQualifiedName();
      String withDollar = type.getQualifiedName('$');
      String withDot = type.getQualifiedName('.');
      int knowTypeNameLength = knownTypeName.length();
      result.add(EObjectDescription.create(QualifiedName.create(Strings.split(withDot.substring(knowTypeNameLength), '.')), type));
      result.add(EObjectDescription.create(QualifiedName.create(withDollar.substring(knowTypeNameLength)), type));
    }
  }
}