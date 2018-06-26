/*
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.action;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.edit.ui.util.FindAndReplaceTarget;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.IFindReplaceTarget;
import org.eclipse.ui.IWorkbenchPart;


/**
 * An action for showing the find and replace dialog.
 * Use {@link #create()} to create an instance.
 * Because this implementation use platform internals, it is not extensible because the platform internals could arbitrarily change.
 * 
 * @since 2.14
 */
public final class FindAction extends Action
{
  /**
   * The constructor for reusing the platform's text editor's org.eclipse.ui.texteditor.FindReplaceAction.
   */
  private static final Constructor<? extends Action> FIND_AND_REPLACE_CONSTRUCTOR;

  /**
   * The resource bundle from the platform's org.eclipse.ui.texteditor.EditorMessages.
   */
  private static final ResourceBundle FIND_AND_REPLACE_RESOURCE_BUNDLE;

  static
  {
    Constructor<? extends Action> findAndReplaceConstructor = null;
    ResourceBundle resourceBundle = null;
    try
    {
      Class<?> editorMessagesClass = CommonPlugin.loadClass("org.eclipse.ui.workbench.texteditor", "org.eclipse.ui.texteditor.EditorMessages");
      Method getBundleForConstructedKeysMethod = editorMessagesClass.getMethod("getBundleForConstructedKeys");
      getBundleForConstructedKeysMethod.setAccessible(true);
      resourceBundle = (ResourceBundle)getBundleForConstructedKeysMethod.invoke(null);
      Class<?> findAndReplaceActionClass = CommonPlugin.loadClass("org.eclipse.ui.workbench.texteditor", "org.eclipse.ui.texteditor.FindReplaceAction");
      @SuppressWarnings("unchecked")
      Constructor<? extends Action> constructor = (Constructor<? extends Action>)findAndReplaceActionClass.getConstructor(ResourceBundle.class, String.class, IWorkbenchPart.class);
      findAndReplaceConstructor = constructor;
    }
    catch (Throwable throwable)
    {
      throwable.printStackTrace();
    }

    FIND_AND_REPLACE_CONSTRUCTOR = findAndReplaceConstructor;
    FIND_AND_REPLACE_RESOURCE_BUNDLE = resourceBundle;
  }

  /**
   * Returns a new find action or {@code null} if the bundle {@code org.eclipse.ui.workbench.texteditor} isn't installed.
   */
  public static FindAction create()
  {
    if (FIND_AND_REPLACE_CONSTRUCTOR != null)
    {
      return new FindAction();
    }
    else
    {
      return null;
    }
  }

  /**
   * The current associated workbench part.
   * @see #setActiveWorkbenchPart(IWorkbenchPart)
   */
  private IWorkbenchPart workbenchPart;

  /**
   * Creates an instance, but the {@link #create()} is the only public way to create an instance.
   */
  private FindAction()
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_FindReplace_menu_item"), ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/ctool16/Search")));
  }

  /**
   * Gets the {@link IFindReplaceTarget} adapter from the {@link #setActiveWorkbenchPart(IWorkbenchPart) active workbench part}
   * and if that's a {@link FindAndReplaceTarget}, [@link {@link FindAndReplaceTarget#initialize(IWorkbenchPart) initializes it}.
   * Then it creates the platform's {@code org.eclipse.ui.texteditor.FindReplaceDialog}.
   * 
   * @see FindAndReplaceTarget#getAdapter(Class, IWorkbenchPart, org.eclipse.ui.plugin.AbstractUIPlugin)
   */
  @Override
  public void run()
  {
    try
    {
      Action findAndReplaceAction = FIND_AND_REPLACE_CONSTRUCTOR.newInstance(FIND_AND_REPLACE_RESOURCE_BUNDLE, "Editor.FindReplace.", workbenchPart);
      findAndReplaceAction.run();
    }
    catch (Exception exception)
    {
      EMFEditUIPlugin.INSTANCE.log(exception);
    }
  }

  /**
   * Sets the current active workbench part.
   * @param workbenchPart the current active workbench part.
   * @see EditingDomainActionBarContributor#activate()
   * @see EditingDomainActionBarContributor#deactivate()
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    this.workbenchPart = workbenchPart;
    if (workbenchPart != null)
    {
      setEnabled(workbenchPart.getAdapter(IFindReplaceTarget.class) != null);
    }
  }

  /**
   * Updates the action.
   * 
   * @see EditingDomainActionBarContributor#update()
   */
  public void update()
  {
  }
}