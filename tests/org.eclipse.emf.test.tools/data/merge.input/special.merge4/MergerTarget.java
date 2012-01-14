/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * This is the Source Header
 */
package org.eclipse.emf.test.tools.merger;

/**
 * This is the action bar contributor for the UML model editor.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AActionBarContributor
{
    /**
     * This action opens the Properties view.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IAction showPropertiesViewAction = new Action(
        UMLEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item")) //$NON-NLS-1$
    {

        public void run() {
            try {
                getPage().showView("org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
            } catch (PartInitException exception) {
                UMLEditorPlugin.INSTANCE.log(exception);
            }
        }
    };
    
    int a;
}
