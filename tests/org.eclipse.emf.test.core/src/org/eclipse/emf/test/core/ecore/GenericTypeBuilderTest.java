/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: GenericTypeBuilderTest.java,v 1.4 2007/06/06 12:20:25 emerks Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;

/**
 * @since 2.3
 */
public class GenericTypeBuilderTest extends TestCase
{
  private EcoreValidator.EGenericTypeBuilder genericTypeBuilder;
  
  public GenericTypeBuilderTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("GenericTypeBuilderTest");
    testSuite.addTest(new GenericTypeBuilderTest("testErasedTypes"));
    testSuite.addTest(new GenericTypeBuilderTest("testGenericTypes"));
    testSuite.addTest(new GenericTypeBuilderTest("knownProblems"));
    return testSuite;
  }
  
  @Override
  protected void setUp() throws Exception
  {
    genericTypeBuilder = new EcoreValidator.EGenericTypeBuilder();
  }
  
  public void knownProblems()
  {
    {
      String instanceTypeName = "A<B";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    }    

    {
      String instanceTypeName = "AB>";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    }
  }
  
  public void testErasedTypes()
  {
    {
      String instanceTypeName = "A";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getData().size());
      assertEquals(instanceTypeName, diagnostic.getData().get(1));
      assertTrue(diagnostic.getData().get(0).toString(), diagnostic.getData().get(0) instanceof EGenericType);
      
      EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
      assertEquals("java.lang.Object", genericType.getEClassifier().getInstanceClassName());
      assertEquals("A", genericType.getEClassifier().getInstanceTypeName());
      assertEquals(0, genericType.getETypeArguments().size());
      
      assertEquals(0, diagnostic.getChildren().size());
    }
    
    {
      String instanceTypeName = "a.b.c.A";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getData().size());
      assertEquals(instanceTypeName, diagnostic.getData().get(1));
      assertTrue(diagnostic.getData().get(0).toString(), diagnostic.getData().get(0) instanceof EGenericType);
      
      EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
      assertEquals("a.b.c.A", genericType.getEClassifier().getInstanceClassName());
      assertEquals("a.b.c.A", genericType.getEClassifier().getInstanceTypeName());
      assertEquals(0, genericType.getETypeArguments().size());
      
      assertEquals(0, diagnostic.getChildren().size());
    }
    
    {
      String instanceTypeName = "Integer";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getData().size());
      assertEquals(instanceTypeName, diagnostic.getData().get(1));
      assertTrue(diagnostic.getData().get(0).toString(), diagnostic.getData().get(0) instanceof EGenericType);
      
      EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
      assertEquals("java.lang.Object", genericType.getEClassifier().getInstanceClassName());
      assertEquals("Integer", genericType.getEClassifier().getInstanceTypeName());
      assertEquals(0, genericType.getETypeArguments().size());
      
      assertEquals(0, diagnostic.getChildren().size());
    }    
    
    {
      String instanceTypeName = "A[]";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getData().size());
      assertEquals(instanceTypeName, diagnostic.getData().get(1));
      assertTrue(diagnostic.getData().get(0).toString(), diagnostic.getData().get(0) instanceof EGenericType);
      
      EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
      assertEquals("java.lang.Object[]", genericType.getEClassifier().getInstanceClassName());
      assertEquals("A[]", genericType.getEClassifier().getInstanceTypeName());
      assertEquals(0, genericType.getETypeArguments().size());
      
      assertEquals(0, diagnostic.getChildren().size());
    }    
  }
  
  public void testGenericTypes()
  {
    {
      String instanceTypeName = "A<E>";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getData().size());
      assertEquals(instanceTypeName, diagnostic.getData().get(1));
      assertTrue(diagnostic.getData().get(0).toString(), diagnostic.getData().get(0) instanceof EGenericType);
      
      EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
      assertEquals("java.lang.Object", genericType.getEClassifier().getInstanceClassName());
      assertEquals("A", genericType.getEClassifier().getInstanceTypeName());
      
      assertEquals(1, genericType.getETypeArguments().size());
      assertNull(genericType.getEUpperBound());
      assertNull(genericType.getELowerBound());        
      {
        EGenericType typeArgument = genericType.getETypeArguments().get(0);
        assertEquals("java.lang.Object", typeArgument.getEClassifier().getInstanceClassName());
        assertEquals("E", typeArgument.getEClassifier().getInstanceTypeName());
        assertEquals(0, typeArgument.getETypeArguments().size());
        assertNull(typeArgument.getEUpperBound());
        assertNull(typeArgument.getELowerBound());        
      }
      
      assertEquals(0, diagnostic.getChildren().size());
    }

    {
      String instanceTypeName = "A<Integer, List<?>, int[], java.util.List<Map<String, ? extends Number>>>";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getData().size());
      assertEquals(instanceTypeName, diagnostic.getData().get(1));
      assertTrue(diagnostic.getData().get(0).toString(), diagnostic.getData().get(0) instanceof EGenericType);
      
      EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
      assertEquals("java.lang.Object", genericType.getEClassifier().getInstanceClassName());
      assertEquals("A", genericType.getEClassifier().getInstanceTypeName());
      
      assertEquals(4, genericType.getETypeArguments().size());
      assertNull(genericType.getELowerBound());
      assertNull(genericType.getEUpperBound());
      {
        EGenericType typeArgument = genericType.getETypeArguments().get(0);
        assertEquals("java.lang.Object", typeArgument.getEClassifier().getInstanceClassName());
        assertEquals("Integer", typeArgument.getEClassifier().getInstanceTypeName());
        assertEquals(0, typeArgument.getETypeArguments().size());
        assertNull(typeArgument.getELowerBound());
        assertNull(typeArgument.getEUpperBound());
      }
      {
        EGenericType typeArgument = genericType.getETypeArguments().get(1);
        assertEquals("java.lang.Object", typeArgument.getEClassifier().getInstanceClassName());
        assertEquals("List", typeArgument.getEClassifier().getInstanceTypeName());
        assertEquals(1, typeArgument.getETypeArguments().size());
        {
          EGenericType childTypeArgument = typeArgument.getETypeArguments().get(0);
          assertNull(childTypeArgument.getEClassifier());
          assertEquals(0, childTypeArgument.getETypeArguments().size());
          assertNull(childTypeArgument.getELowerBound());
          assertNull(childTypeArgument.getEUpperBound());
        }
      }
      {
        EGenericType typeArgument = genericType.getETypeArguments().get(2);
        assertEquals("int[]", typeArgument.getEClassifier().getInstanceClassName());
        assertEquals("int[]", typeArgument.getEClassifier().getInstanceTypeName());
        assertEquals(0, typeArgument.getETypeArguments().size());
        assertNull(typeArgument.getELowerBound());
        assertNull(typeArgument.getEUpperBound());
      }
      {
        EGenericType typeArgument = genericType.getETypeArguments().get(3);
        assertEquals("java.util.List", typeArgument.getEClassifier().getInstanceClassName());
        assertEquals("java.util.List", typeArgument.getEClassifier().getInstanceTypeName());
        assertEquals(1, typeArgument.getETypeArguments().size());
        {
          EGenericType childTypeArgument = typeArgument.getETypeArguments().get(0);
          assertEquals("java.lang.Object", childTypeArgument.getEClassifier().getInstanceClassName());
          assertEquals("Map", childTypeArgument.getEClassifier().getInstanceTypeName());
          assertEquals(2, childTypeArgument.getETypeArguments().size());
          {
            EGenericType grandChildTypeArgument = childTypeArgument.getETypeArguments().get(0);
            assertEquals("java.lang.Object", grandChildTypeArgument.getEClassifier().getInstanceClassName());
            assertEquals("String", grandChildTypeArgument.getEClassifier().getInstanceTypeName());
            assertEquals(0, grandChildTypeArgument.getETypeArguments().size());
            assertNull(grandChildTypeArgument.getELowerBound());
            assertNull(grandChildTypeArgument.getEUpperBound());
          }
          {
            EGenericType grandChildTypeArgument = childTypeArgument.getETypeArguments().get(1);
            assertNull(grandChildTypeArgument.getEClassifier());
            assertEquals(0, grandChildTypeArgument.getETypeArguments().size());
            assertNull(grandChildTypeArgument.getELowerBound());
            assertNotNull(grandChildTypeArgument.getEUpperBound());
            
            EGenericType upperBound = grandChildTypeArgument.getEUpperBound();
            assertEquals("java.lang.Object", upperBound.getEClassifier().getInstanceClassName());
            assertEquals("Number", upperBound.getEClassifier().getInstanceTypeName());
            assertEquals(0, upperBound.getETypeArguments().size());
            assertNull(upperBound.getELowerBound());
            assertNull(upperBound.getEUpperBound());
          }
        }
      }
      
      assertEquals(0, diagnostic.getChildren().size());
    }
    
    {
      String instanceTypeName = "List<int[]>";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getData().size());
      assertEquals(instanceTypeName, diagnostic.getData().get(1));
      assertTrue(diagnostic.getData().get(0).toString(), diagnostic.getData().get(0) instanceof EGenericType);
      
      EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
      assertEquals("java.lang.Object", genericType.getEClassifier().getInstanceClassName());
      assertEquals("List", genericType.getEClassifier().getInstanceTypeName());
      
      assertEquals(1, genericType.getETypeArguments().size());
      {
        EGenericType typeArgument = genericType.getETypeArguments().get(0);
        assertEquals("int[]", typeArgument.getEClassifier().getInstanceClassName());
        assertEquals("int[]", typeArgument.getEClassifier().getInstanceTypeName());
        assertEquals(0, typeArgument.getETypeArguments().size());
        assertNull(typeArgument.getEUpperBound());
        assertNull(typeArgument.getELowerBound());
      }
      
      assertEquals(0, diagnostic.getChildren().size());
    }    

    {
      String instanceTypeName = "List<? super Integer>[]";
      Diagnostic diagnostic = genericTypeBuilder.parseInstanceTypeName(instanceTypeName);
      assertEquals(Diagnostic.OK, diagnostic.getSeverity());
      assertEquals(2, diagnostic.getData().size());
      assertEquals(instanceTypeName, diagnostic.getData().get(1));
      assertTrue(diagnostic.getData().get(0).toString(), diagnostic.getData().get(0) instanceof EGenericType);
      
      EGenericType genericType = (EGenericType)diagnostic.getData().get(0);
      assertEquals("java.lang.Object[]", genericType.getEClassifier().getInstanceClassName());
      assertEquals("List[]", genericType.getEClassifier().getInstanceTypeName());
      
      assertEquals(1, genericType.getETypeArguments().size());
      assertNull(genericType.getEUpperBound());
      assertNull(genericType.getELowerBound());        
      {
        EGenericType typeArgument = genericType.getETypeArguments().get(0);
        assertNull(typeArgument.getEClassifier());
        assertEquals(0, typeArgument.getETypeArguments().size());
        assertNull(typeArgument.getEUpperBound());
        assertNotNull(typeArgument.getELowerBound());
        
        EGenericType lowerBound = typeArgument.getELowerBound();
        assertEquals("java.lang.Object", lowerBound.getEClassifier().getInstanceClassName());
        assertEquals("Integer", lowerBound.getEClassifier().getInstanceTypeName());        
      }
      
      assertEquals(0, diagnostic.getChildren().size());
    }    
  }
  
  public static void main(String[] args)
  {    
    List<int[]> li = new ArrayList<int[]>();
    li.add(new int[] {1});
    System.out.println(li.get(0)[0]);
    
    @SuppressWarnings("unchecked") List<? super Integer>[] lis = new List[2];
    lis[0] = new ArrayList<Integer>();
    lis[1] = new ArrayList<Object>();
    
    String [] instanceTypeNames = 
      {
        "A<B<C[], d.D<e.E>>[][]>[]",
        "A[]<A>",
        "A<>",
        "A<,>",
        "A<X,>",
        "A<,X>",
        "A<?>",
        "A< ? >",
        "A< ? extends X>",
        "A< ? super X>",
        "A< ? super ?>",
        "A< ? x>",
        "a<b<c>>",
        "a<b>",
        "a.b[]",
        "a[]",
        "a []",
        " a [ ] ",
        "a [a]",
        "a.[]",
        " a [",
        " a [ ",
        " [ ",
        ".",
        " .",
        ". ",
        " . ",
        "a.",
        " a.",
        "a. ",
        " a. ",
        ".a",
        " .a",
        ".a ",
        " .a ",
        " a .bc ",
        " a bc ",
        "abc",
        " abc ",
        "a.bc",
        " a. bc ",
        " a . bc ",
      };
    for (int i = 0; i < instanceTypeNames.length; ++i)
    {
      Diagnostic diagnostic = new EcoreValidator.EGenericTypeBuilder().parseInstanceTypeName(instanceTypeNames[i]);
      EGenericType eGenericType = (EGenericType)diagnostic.getData().get(0);
      if (diagnostic.getSeverity() > Diagnostic.OK)
      {
        for (Diagnostic child : diagnostic.getChildren())
        {
          System.out.println(child.getMessage());
          for (int j = -1, count = (Integer)child.getData().get(0); j < count; ++j)
          {
            System.out.print(' ');
          }
          System.out.println('v');
          System.out.println("'" + instanceTypeNames[i] + "' -> '" + EcoreUtil.toJavaInstanceTypeName(eGenericType) + "'");
        }
        System.out.flush();
      }
      else
      {
        System.out.println("'" + instanceTypeNames[i] + "' -> '" + EcoreUtil.toJavaInstanceTypeName(eGenericType) + "'");
        System.out.flush();
      }
    }

    String [] typeParameters = 
      {
        "<T, U, V>",
        "<T, U extends T, V extends U>",
        "<TT, UU extends TT, VV extends UU>",
        "<TT, UU extends TT, VV extends UU & TT>",
        "<TT, UU extends TT, VV extends UU & TT & java.util.List<java.util.List<?>>>",
        "<TT, UU extends TT, VV extends UU & TT & java.util.List<java.util.Map<?,?>>>",
      };
    for (int i = 0; i < typeParameters.length; ++i)
    {
      Diagnostic diagnostic = new EcoreValidator.EGenericTypeBuilder().parseTypeParameterList(typeParameters[i]);
      @SuppressWarnings("unchecked")
      List<ETypeParameter> eTypeParameters = (List<ETypeParameter>)diagnostic.getData().get(0);
      StringBuilder result = new StringBuilder();
      for (Iterator<ETypeParameter> j = eTypeParameters.iterator(); j.hasNext(); )
      {
        ETypeParameter eTypeParameter = j.next();
        result.append(eTypeParameter.getName());
        if (!eTypeParameter.getEBounds().isEmpty())
        {
          result.append(" extends ");
          for (Iterator<EGenericType> k = eTypeParameter.getEBounds().iterator(); k.hasNext(); )
          {
            result.append(EcoreUtil.toJavaInstanceTypeName(k.next()));
            if (k.hasNext())
            {
              result.append(" & ");
            }
          }
        }
        if (j.hasNext())
        {
          result.append(", ");
        }
      }
      if (diagnostic.getSeverity() > Diagnostic.OK)
      {
        for (Diagnostic child : diagnostic.getChildren())
        {
          System.out.println(child.getMessage());
          for (int j = -1, count = (Integer)child.getData().get(0); j < count; ++j)
          {
            System.out.print(' ');
          }
          System.out.println('v');
          System.out.println("'" + typeParameters[i] + "' -> '" + result + "'");
        }
        System.out.flush();
      }
      else
      {
        System.out.println("'" + typeParameters[i] + "' -> '" + result + "'");
        System.out.flush();
      }
    }
  }  
}
