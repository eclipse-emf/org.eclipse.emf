/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.provider;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IPropertyEditorFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;


/**
 * This is used to encapsulate an {@link IItemPropertyDescriptor} along with the object for which it is an item property source
 * and make it behave like an {@link org.eclipse.ui.views.properties.IPropertyDescriptor}.
 */
public class PropertyDescriptor implements IPropertyDescriptor
{
  /**
   * This is the object for which this class is a property source.
   */
  protected Object object;

  /**
   * This is the descriptor to which we will delegate all the {@link org.eclipse.ui.views.properties.IPropertyDescriptor} methods.
   */
  protected IItemPropertyDescriptor itemPropertyDescriptor;

  /**
   * An instance is constructed from an object and its item property source.
   */
  public PropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor)
  {
    this.object = object;
    this.itemPropertyDescriptor = itemPropertyDescriptor;
  }

  /**
   * @since 2.14
   */
  public Object getObject()
  {
    return object;
  }

  /**
   * @since 2.14
   */
  public IItemPropertyDescriptor getItemPropertyDescriptor()
  {
    return itemPropertyDescriptor;
  }

  public String getCategory() 
  {
    return itemPropertyDescriptor.getCategory(object);
  }

  public String getDescription() 
  {
    return itemPropertyDescriptor.getDescription(object);
  }

  public String getDisplayName() 
  {
    return itemPropertyDescriptor.getDisplayName(object);
  }

  public String[] getFilterFlags() 
  {
    return itemPropertyDescriptor.getFilterFlags(object);
  }

  public Object getHelpContextIds() 
  {
    return itemPropertyDescriptor.getHelpContextIds(object);
  }

  /**
   * Provides direct access to the underlying feature object.
   * 
   * @since 2.9
   */
  public Object getFeature()
  {
    return itemPropertyDescriptor.getFeature(object);
  }

  public Object getId() 
  {
    return itemPropertyDescriptor.getId(object);
  }

  public ILabelProvider getLabelProvider() 
  {
    final IItemLabelProvider itemLabelProvider = itemPropertyDescriptor.getLabelProvider(object);
    return 
      new LabelProvider()
      {
        @Override
        public String getText(Object object)
        {
          return itemLabelProvider.getText(object);
        }
        @Override
        public Image getImage(Object object)
        {
          return ExtendedImageRegistry.getInstance().getImage(itemLabelProvider.getImage(object));
        }
      };
  }

  protected ILabelProvider getEditLabelProvider()
  {
    return getLabelProvider();
  }

  public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) 
  {
    return false;
  }

  /**
   * A delegate for handling validation and conversion for data type values.
   */
  protected static class EDataTypeValueHandler implements ICellEditorValidator, IInputValidator, ExtendedComboBoxCellEditor.ValueHandler
  {
    protected EDataType eDataType;

    /**
     * @since 2.14
     */
    protected IItemPropertyDescriptor.ValueHandler valueHandler;

    public EDataTypeValueHandler(EDataType eDataType)
    {
      this(eDataType, new ItemPropertyDescriptor.DataTypeValueHandler(eDataType));
    }

    /**
     * @since 2.14
     */
    public EDataTypeValueHandler(EDataType eDataType, IItemPropertyDescriptor.ValueHandler valueHandler)
    {
      this.eDataType = eDataType;
      this.valueHandler = valueHandler;
    }

    public String isValid(Object object)
    {
      return escape(valueHandler.isValid((String)object));
    }

    /**
     * JFace {@link MessageFormat#format(String, Object...) formats} messages, so special pattern characters need to be escaped.
     * 
     * @since 2.14
     */
    protected String escape(String message)
    {
      return message == null ? null : message.replace("'","''").replace("{", "'{'"); // }}
    }

    public String isValid(String text)
    {
      return isValid((Object)text);
    }

    public Object toValue(String string)
    {
      return valueHandler.toValue(string);
    }

    public String toString(Object value)
    {
      String result = valueHandler.toString(value);
      return result == null ? "" : result;
    }
  }

  public static class EDataTypeCellEditor extends TextCellEditor
  {
    protected EDataType eDataType;
    protected EDataTypeValueHandler valueHandler;  

    public EDataTypeCellEditor(EDataType eDataType, Composite parent)
    {
      this(eDataType, new EDataTypeValueHandler(eDataType), parent);
    }

    /**
     * @since 2.14
     */
    public EDataTypeCellEditor(EDataType eDataType, EDataTypeValueHandler valueHandler, Composite parent)
    {
      super(parent);
      this.eDataType = eDataType;
      this.valueHandler = valueHandler;
      setValidator(valueHandler);
    }

    @Override
    public Object doGetValue()
    {
      return valueHandler.toValue((String)super.doGetValue());
    }

    @Override
    public void doSetValue(Object value)
    {
      value = valueHandler.toString(value);
      super.doSetValue(value);
    }

    // CellEditor.setValue() calls isCorrect() to validate the value that is about to be set. We are doing conversion
    // between the value and a corresponding string, and we would usually like to validate the string. Because
    // setValue() is final, we cannot do that conversion there, so we need to record what we're trying to validate and
    // work around the problem in isCorrect().
    // 
    protected boolean validateAsValue = true;

    @Override
    protected void editOccured(ModifyEvent e)
    {
      validateAsValue = false;
      super.editOccured(e);
      validateAsValue = true;
    }

    @Override
    protected boolean isCorrect(Object value)
    {
      if (validateAsValue)
      {
        value = valueHandler.toString(value);
      }
      return super.isCorrect(value);
    }
  }

  /**
   * @since 2.14
   */
  public static class MultiLineEDataTypeCellEditor extends EDataTypeCellEditor
  {
    private static final EDataTypeValueHandler NO_OP_VALUE_HANDLER = new EDataTypeValueHandler(EcorePackage.Literals.ESTRING);

    protected Text text;

    protected Button button;

    protected boolean doEscape;

    private String value;

    private String dialogTitle;

    private MouseListener mouseListener = new MouseAdapter()
      {
        @Override
        public void mouseUp(MouseEvent e)
        {
          showDialog();
        }

        @Override
        public void mouseDoubleClick(MouseEvent e)
        {
          showDialog();
        }
      };

    private VerifyListener verifyListener = new VerifyListener()
      {
        public void verifyText(VerifyEvent e)
        {
          e.doit = false;
          showDialog();
        }
      };

    public MultiLineEDataTypeCellEditor(EDataType eDataType, EDataTypeValueHandler valueHandler, String dialogTitle, Composite parent)
    {
      super(eDataType, valueHandler, parent);
      this.dialogTitle = dialogTitle;
    }

    private void showDialog()
    {
      button.setFocus();
      button.getDisplay().asyncExec(new Runnable()
        {
          public void run()
          {
            button.notifyListeners(SWT.Selection, null);
          }
        });
    }

    @Override
    public Object doGetValue()
    {
      String str = value != null ? value : (String)super.doGetValue();

      if (doEscape)
      {
        str = unescape(str);
      }

      return str;
    }

    @Override
    public void doSetValue(Object value)
    {
      text.removeMouseListener(mouseListener);
      text.removeVerifyListener(verifyListener);

      String literal = valueHandler.toString(value);
      if (literal != null)
      {
        for (char c : literal.toCharArray())
        {
          if (Character.isISOControl(c))
          {
            doEscape = true;
            button.setImage(ExtendedImageRegistry.INSTANCE.getImage(EMFEditUIPlugin.INSTANCE.getImage("full/ctool16/EncodedEllipses")));
            literal = escape(literal);
            break;
          }
        }
      }

      EDataTypeValueHandler oldValueHandler = valueHandler;
      try
      {
        boolean isVeryLong = literal.length() > 2000;
        if (isVeryLong)
        {
          this.value = literal;
          literal = literal.substring(0, 2000);
        }
        else
        {
          this.value = null;
        }

        valueHandler = NO_OP_VALUE_HANDLER;
        super.doSetValue(literal);

        if (isVeryLong)
        {
          text.addMouseListener(mouseListener);
          text.addVerifyListener(verifyListener);
        }
      }
      finally
      {
        valueHandler = oldValueHandler;
      }
    }

    @Override
    protected Control createControl(Composite parent)
    {
      GridLayout layout = new GridLayout(2, false);
      layout.marginWidth = 0;
      layout.marginHeight = 0;
      layout.horizontalSpacing = 0;

      final Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(layout);
      composite.setFont(parent.getFont());
      composite.setBackground(parent.getBackground());

      text = (Text)super.createControl(composite);
      text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

      button = new Button(composite, SWT.PUSH);
      GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, true);
      if (parent instanceof Tree)
      {
        layoutData.widthHint = ((Tree)parent).getItemHeight();
      }
      else if (parent instanceof Table)
      {
       layoutData.widthHint = ((Table)parent).getItemHeight();
      }
      button.setLayoutData(layoutData);
      button.setImage(ExtendedImageRegistry.INSTANCE.getImage(EMFEditUIPlugin.INSTANCE.getImage("full/ctool16/Ellipses")));
      button.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            String stringValue = valueHandler.toString(getValue());
            boolean containsNull = stringValue.indexOf('\u0000') != -1;
            if (containsNull)
            {
              stringValue = stringValue.replace('\u0000', '\n');
            }

            MultiLineInputDialog dialog = new MultiLineInputDialog(
              composite.getShell(),
              dialogTitle,
              EMFEditUIPlugin.INSTANCE.getString("_UI_MultiLineInputDialog_message"),
              stringValue,
              valueHandler);

            if (dialog.open() == Window.OK)
            {
              String value = dialog.getValue();

              value = value.replace("\r\n", "\n");
              if (containsNull)
              {
                value = value.replace('\n', '\u0000');
              }

              Object newValue = valueHandler.toValue(value);
              if (newValue != null)
              {
                boolean newValidState = isCorrect(newValue);
                if (newValidState)
                {
                  markDirty();
                  doSetValue(newValue);
                  fireApplyEditorValue();
                }
              }
            }
            else
            {
              fireCancelEditor();
            }
          }
        });

      button.addKeyListener(new KeyAdapter()
        {
          @Override
          public void keyReleased(KeyEvent e)
          {
            if (e.character == '\u001b')
            {
              fireCancelEditor();
            }
          }
        });

      return composite;
    }

    @Override
    protected void focusLost()
    {
      if (isActivated())
      {
        button.getDisplay().asyncExec(new Runnable()
          {
            public void run()
            {
              try
              {
                if (button.isFocusControl())
                {
                  return;
                }
              }
              catch (Exception ex)
              {
                // Ignore.
              }

              try
              {
                fireApplyEditorValue();
                deactivate();
              }
              catch (Exception ex)
              {
                // Ignore.
              }
            }
          });
      }
    }

    protected String escape(String literal)
    {
      if (literal == null)
      {
        return null;
      }

      int len = literal.length();
      StringBuilder builder = new StringBuilder(len);

      for (int i = 0; i < len; i++)
      {
        char c = literal.charAt(i);
        if (c < 32)
        {
          switch (c)
          {
            case '\r':
              builder.append('\\');
              builder.append('r');
              break;

            case '\n':
              builder.append('\\');
              builder.append('n');
              break;

            case '\t':
              builder.append('\\');
              builder.append('t');
              break;

            case '\f':
              builder.append('\\');
              builder.append('f');
              break;

            case '\b':
              builder.append('\\');
              builder.append('b');
              break;

            default:
              if (c > 0xf)
              {
                builder.append("\\u00" + charToHex(c));
              }
              else
              {
                builder.append("\\u000" + charToHex(c));
              }
          }
        }
        else if (c == '\\')
        {
          builder.append('\\');
          builder.append('\\');
        }
        else
        {
          builder.append(c);
        }
      }

      return builder.toString();
    }

    protected String unescape(String literal)
    {
      if (literal == null)
      {
        return null;
      }

      int len = literal.length();
      StringBuilder builder = new StringBuilder(len);

      StringBuilder unicodeBuilder = new StringBuilder(4);
      boolean unicode = false;
      boolean slash = false;

      for (int i = 0; i < len; i++)
      {
        char c = literal.charAt(i);
        if (unicode)
        {
          unicodeBuilder.append(c);
          if (unicodeBuilder.length() == 4)
          {
            try
            {
              char value = hexToChar(unicodeBuilder.toString());
              builder.append(value);
              unicodeBuilder.setLength(0);
              unicode = false;
              slash = false;
            }
            catch (NumberFormatException ex)
            {
              builder.append('\\');
              builder.append('u');
              builder.append(unicodeBuilder);
            }
          }

          continue;
        }

        if (slash)
        {
          slash = false;

          switch (c)
          {
            case '\\':
              builder.append('\\');
              break;

            case 'r':
              builder.append('\r');
              break;

            case 'n':
              builder.append('\n');
              break;

            case 't':
              builder.append('\t');
              break;

            case 'f':
              builder.append('\f');
              break;

            case 'b':
              builder.append('\b');
              break;

            case 'u':
              unicode = true;
              break;

            case '0':
            case '1':
            case '2':
            case '3':
              if (i + 2 < len && literal.charAt(i + 1) >= '0' && literal.charAt(i + 1) <= '7' && literal.charAt(i + 2) >= '0' && literal.charAt(i + 2) <= '7')
              {
                builder.append((char)Integer.parseInt(literal.substring(i, i + 3), 8));
                i += 2;
                continue;
              }

              //$FALL-THROUGH$
            default:
            {
              builder.append(c);
            }
          }

          continue;
        }
        else if (c == '\\')
        {
          slash = true;
          continue;
        }

        builder.append(c);
      }

      if (slash)
      {
        builder.append('\\');
      }

      return builder.toString();
    }

    protected String charToHex(char ch)
    {
      return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
    }

    protected char hexToChar(String s)
    {
      return (char)Integer.parseInt(s, 16);
    }
  }

  private static class MultiLineInputDialog extends InputDialog
  {
    public MultiLineInputDialog(Shell parentShell, String title, String message, String initialValue, IInputValidator validator)
    {
      super(parentShell, title, message, initialValue, validator);
      setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
    }

    @Override
    protected Text createText(Composite composite)
    {
      Text text = new Text(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
      GridData data = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
      data.heightHint = 5 * text.getLineHeight();
      data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.ENTRY_FIELD_WIDTH);
      text.setLayoutData(data);
      return text;
    }
  }

  /**
   * Unlike {@link CheckboxCellEditor} this really creates a check box.
   *
   * @since 2.14
   */
  public static class CheckBoxCellEditor extends CellEditor
  {
    private Button checkButton;

    private boolean isTriState;

    private Label label;

    public CheckBoxCellEditor(Composite parent, int style, boolean isTriState)
    {
      super(parent, style);
      this.isTriState = isTriState;
    }

    @Override
    protected Control createControl(Composite parent)
    {
      Composite composite = new Composite(parent, SWT.NONE);
      RowLayout layout = new RowLayout();
      layout.marginTop = 1;
      layout.marginBottom = 0;
      layout.marginLeft = 5;
      layout.center = true;
      composite.setLayout(layout);
      checkButton = new Button(composite, SWT.CHECK | getStyle());
      checkButton.addFocusListener(new FocusAdapter()
        {
          @Override
          public void focusLost(FocusEvent e)
          {
            CheckBoxCellEditor.this.focusLost();
          }
        });
      checkButton.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            changeState(false);
          }
        });
      checkButton.addTraverseListener(new TraverseListener()
        {
          public void keyTraversed(TraverseEvent e)
          {
            if (e.detail == SWT.TRAVERSE_ESCAPE)
            {
              fireCancelEditor();
            }
            else if (e.detail == SWT.TRAVERSE_RETURN)
            {
              focusLost();
            }
          }
        });

      label = new Label(composite, SWT.NONE);

      MouseAdapter mouseListener = new MouseAdapter()
        {
          @Override
          public void mouseUp(MouseEvent e)
          {
            changeState(true);
          }
        };

      label.addMouseListener(mouseListener);
      composite.addMouseListener(mouseListener);
      Color background = parent.getBackground();
      label.setBackground(background);
      composite.setBackground(background);
      return composite;
    }

    private void changeState(boolean toggle)
    {
      markDirty();
      if (isTriState)
      {
        if (checkButton.getGrayed())
        {
          checkButton.setGrayed(false);
          checkButton.setSelection(false);
        }
        else if (checkButton.getSelection() == toggle)
        {
          checkButton.setSelection(false);
          checkButton.setGrayed(true);
        }
        else
        {
          checkButton.setSelection(true);
        }
      }
      else if (toggle)
      {
        checkButton.setSelection(!checkButton.getSelection());
      }
      doSetValue(doGetValue());
    }

    @Override
    protected Object doGetValue()
    {
      return checkButton.getGrayed() ? null : checkButton.getSelection();
    }

    @Override
    protected void doSetValue(Object value)
    {
      checkButton.setSelection(Boolean.TRUE.equals(value));
      checkButton.setGrayed(value == null);
      label.setText(value == null ? "null" : Boolean.TRUE.equals(value) ? "true" : "false");
      label.setEnabled(value != null);
      label.getParent().layout();
    }

    @Override
    protected void doSetFocus()
    {
      if (checkButton != null)
      {
        checkButton.setFocus();
      }
    }
  }

  /**
   * @since 2.14
   */
  protected CellEditor createEDataTypeCellEditor(final EDataType eDataType, final IItemPropertyDescriptor.ValueHandler specializedValueHandler, Composite composite)
  {
    if (itemPropertyDescriptor.isMultiLine(object))
    {
      EDataTypeValueHandler valueHandler = new EDataTypeValueHandler(eDataType, specializedValueHandler);
      return new MultiLineEDataTypeCellEditor(
        eDataType,
        valueHandler,
        EMFEditUIPlugin.INSTANCE.getString("_UI_FeatureEditorDialog_title", new Object []{ getDisplayName(), getEditLabelProvider().getText(object) }),
        composite);
    }
    return specializedValueHandler == null
      ? new EDataTypeCellEditor(eDataType, composite) : new EDataTypeCellEditor(eDataType, new EDataTypeValueHandler(eDataType, specializedValueHandler), composite);
  }

  protected CellEditor createEDataTypeCellEditor(final EDataType eDataType, Composite composite)
  {
    return createEDataTypeCellEditor(eDataType, null, composite);
  }

  /**
   * This cell editor ensures that only Integer values are supported
   * @deprecated
   */
  @Deprecated
  public static class IntegerCellEditor extends TextCellEditor
  {
    public IntegerCellEditor(Composite composite)
    {
      super(composite);
      setValidator
        (new ICellEditorValidator()
         {
           public String isValid(Object object)
           {
             if (object instanceof Integer)
             {
               return null;
             }
             else
             {
               String string = (String)object;
               try
               {
                 Integer.parseInt(string);
                 return null;
               }
               catch (NumberFormatException exception)
               {
                 return exception.getMessage();
               }
             }
           }
         });
    }

    @Override
    public Object doGetValue()
    {
      return Integer.parseInt((String)super.doGetValue());
    }

    @Override
    public void doSetValue(Object value)
    {
      super.doSetValue(value.toString());
    }
  }

  /**
   * This cell editor ensures that only Float values are supported
   * @deprecated
   */
  @Deprecated
  public static class FloatCellEditor extends TextCellEditor
  {
    public FloatCellEditor(Composite composite)
    {
      super(composite);
      setValidator
        (new ICellEditorValidator()
         {
           public String isValid(Object object)
           {
             if (object instanceof Float)
             {
               return null;
             }
             else
             {
               String string = (String)object;
               try
               {
                 Float.parseFloat(string);
                 return null;
               }
               catch (NumberFormatException exception)
               {
                 return exception.getMessage();
               }
             }
           }
         });
    }

    @Override
    public Object doGetValue()
    {
      return new Float(Float.parseFloat((String)super.doGetValue()));
    }

    @Override
    public void doSetValue(Object value)
    {
      super.doSetValue(value.toString());
    }
  }

  protected static final EcorePackage ecorePackage = EcorePackage.eINSTANCE;

  /**
   * This returns the cell editor that will be used to edit the value of this property.
   * This default implementation first calls {@link #createPropertyEditorFromFactory(Composite)} returning that result if not {@code null}.
   * Otherwise it determines the type of cell editor from the nature of the structural feature.
   */
  public CellEditor createPropertyEditor(Composite composite) 
  {
    CellEditor result = createPropertyEditorFromFactory(composite);
    if (result != null)
    {
      return result;
    }

    if (!itemPropertyDescriptor.canSetProperty(object))
    {
      return null;
    }

    Object genericFeature = itemPropertyDescriptor.getFeature(object);
    if (genericFeature instanceof EReference[])
    {
      result = new ExtendedComboBoxCellEditor(
        composite,
        new ArrayList<Object>(itemPropertyDescriptor.getChoiceOfValues(object)),
        getEditLabelProvider(),
        itemPropertyDescriptor.isSortChoices(object),
        SWT.READ_ONLY,
        null,
        true);
    }
    else if (genericFeature instanceof EStructuralFeature)
    {
      final EStructuralFeature feature = (EStructuralFeature)genericFeature;
      final EClassifier eType = feature.getEType();
      final Collection<?> choiceOfValues = itemPropertyDescriptor.getChoiceOfValues(object);
      if (choiceOfValues != null)
      {
        if (itemPropertyDescriptor.isMany(object))
        {
          boolean valid = true;
          for (Object choice : choiceOfValues)
          {
            if (!eType.isInstance(choice))
            {
              valid = false;
              break;
            }
          }

          if (valid)
          {
            final ILabelProvider editLabelProvider = getEditLabelProvider();
            result =
              new ExtendedDialogCellEditor(composite, editLabelProvider)
              {
                @Override
                protected Object openDialogBox(Control cellEditorWindow)
                {
                  FeatureEditorDialog dialog = new FeatureEditorDialog(
                    cellEditorWindow.getShell(),
                    editLabelProvider,
                    object,
                    feature.getEType(),
                    (List<?>)doGetValue(),
                    getDisplayName(),
                    new ArrayList<Object>(choiceOfValues),
                    false,
                    itemPropertyDescriptor.isSortChoices(object),
                    feature.isUnique() || feature instanceof EReference,
                    itemPropertyDescriptor instanceof IItemPropertyDescriptor.ValueHandlerProvider &&
                      ((IItemPropertyDescriptor.ValueHandlerProvider)itemPropertyDescriptor).isChoiceArbitrary(object) ?
                        ((IItemPropertyDescriptor.ValueHandlerProvider)itemPropertyDescriptor).getValueHandler(object) :
                        null);
                  dialog.open();
                  return dialog.getResult();
                }
              };
          }
        }

        if (result == null)
        {
          ArrayList<Object> values = new ArrayList<Object>(choiceOfValues);
          if (itemPropertyDescriptor instanceof IItemPropertyDescriptor.ValueHandlerProvider &&
                ((IItemPropertyDescriptor.ValueHandlerProvider)itemPropertyDescriptor).isChoiceArbitrary(object))
          {
            result =
              new ExtendedComboBoxCellEditor
                (composite,
                 values,
                 getEditLabelProvider(),
                 itemPropertyDescriptor.isSortChoices(object),
                 SWT.NONE,
                 new EDataTypeValueHandler((EDataType)eType, ((IItemPropertyDescriptor.ValueHandlerProvider)itemPropertyDescriptor).getValueHandler(object)),
                 false);
          }
          else
          {
            result =
              new ExtendedComboBoxCellEditor
                (composite, values, getEditLabelProvider(), itemPropertyDescriptor.isSortChoices(object), SWT.READ_ONLY, null, true);
          }
        }
      }
      else if (eType instanceof EDataType)
      {
        final EDataType eDataType = (EDataType)eType;
        if (eDataType.isSerializable())
        {
          if (itemPropertyDescriptor.isMany(object))
          {
            final ILabelProvider editLabelProvider = getEditLabelProvider();
            result =
              new ExtendedDialogCellEditor(composite, editLabelProvider)
              {
                @Override
                protected Object openDialogBox(Control cellEditorWindow)
                {
                  FeatureEditorDialog dialog = new FeatureEditorDialog(
                    cellEditorWindow.getShell(),
                    editLabelProvider,
                    object,
                    feature.getEType(),
                    (List<?>)doGetValue(),
                    getDisplayName(),
                    null,
                    itemPropertyDescriptor.isMultiLine(object),
                    false,
                    feature.isUnique(),
                    itemPropertyDescriptor instanceof IItemPropertyDescriptor.ValueHandlerProvider ?
                      ((IItemPropertyDescriptor.ValueHandlerProvider)itemPropertyDescriptor).getValueHandler(object) :
                      null);
                  dialog.open();
                  return dialog.getResult();
                }
              };
          }
          else if (eDataType.getInstanceClass() == Boolean.class) 
          {
            return new CheckBoxCellEditor(composite, SWT.NONE, true);
          }
          else if (eDataType.getInstanceClass() == Boolean.TYPE)
          {
            return new CheckBoxCellEditor(composite, SWT.NONE, false);
          }
          else if (itemPropertyDescriptor instanceof IItemPropertyDescriptor.ValueHandlerProvider)
          {
            result = createEDataTypeCellEditor(eDataType, ((IItemPropertyDescriptor.ValueHandlerProvider)itemPropertyDescriptor).getValueHandler(object), composite);
          }
          else
          {
            result = createEDataTypeCellEditor(eDataType, composite);
          }
        }
      }
    }

    return result;
  }

  /**
   * If the {@link #itemPropertyDescriptor} is an {@link org.eclipse.emf.edit.provider.IPropertyEditorFactory.Provider property editor factory provider},
   * this uses the factory, if available, to create the cell editor.
   * @return a new cell editor, or {@code null}.
   * @since 2.14
   */
  protected CellEditor createPropertyEditorFromFactory(Composite composite) 
  {
    if (itemPropertyDescriptor instanceof IPropertyEditorFactory.Provider)
    {
      IPropertyEditorFactory.Provider editorFactoryProvider = (IPropertyEditorFactory.Provider)itemPropertyDescriptor;
      IPropertyEditorFactory editorFactory = IPropertyEditorFactory.Registry.INSTANCE.getPropertyEditorFactory(editorFactoryProvider.getEditorFactory(object));
      if (editorFactory != null)
      {
        Object editor = editorFactory.createEditor(object, itemPropertyDescriptor, composite);
        if (editor instanceof CellEditor)
        {
          return (CellEditor)editor;
        }
      }
    }
    return null;
  }
}
