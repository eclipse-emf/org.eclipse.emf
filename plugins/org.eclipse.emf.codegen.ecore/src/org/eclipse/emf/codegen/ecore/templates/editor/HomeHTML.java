package org.eclipse.emf.codegen.ecore.templates.editor;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class HomeHTML
{
  protected static String nl;
  public static synchronized HomeHTML create(String lineSeparator)
  {
    nl = lineSeparator;
    HomeHTML result = new HomeHTML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<!doctype html>" + NL + "<html style=\"height : 100%\"> " + NL + "  <head>" + NL + "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">" + NL + "    <!--" + NL + "    <link type=\"text/css\" rel=\"stylesheet\" href=\"Com_macromodeling.css\">" + NL + "    -->" + NL + "" + NL + "    <!--                                           -->" + NL + "    <!-- Any title is fine                         -->" + NL + "    <!--                                           -->" + NL + "    <title>Web Application Starter Project</title>" + NL + "    " + NL + "    <!--                                           -->" + NL + "    <!-- This script loads your compiled module.   -->" + NL + "    <!-- If you add any GWT meta tags, they must   -->" + NL + "    <!-- be added before this line.                -->" + NL + "    <script type=\"text/javascript\" language=\"javascript\" src=\"";
  protected final String TEXT_2 = "/";
  protected final String TEXT_3 = ".nocache.js\"></script>" + NL + "  </head>" + NL + "" + NL + "  <body style=\"height : 100%\">" + NL + "    <div id=\"main\" style=\"height : 100%\">" + NL + "    </div>" + NL + "  </body>" + NL + "</html>";
  protected final String TEXT_4 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2010 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
 *
 * </copyright>
 */

    GenModel genModel = (GenModel)argument; /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(genModel.getQualifiedEditorModuleName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(genModel.getQualifiedEditorModuleName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
