package org.eclipse.emf.examples.jet.article2.ui;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.emf.examples.jet.article2.model.Attribute;
import org.eclipse.emf.examples.jet.article2.model.Instance;



/**
 * Dialog for editing an <code>Instance</code>.
 * 
 * @version $Revision: 1.3 $ ($Date: 2008/05/29 14:56:38 $)
 * @author Remko Popma
 */
public class EditInstanceDialog extends Dialog
{

  private Button mCheckboxDefault = null;

  private Text mTextName = null;

  private Text[] mTextAttributeValues = null;

  private Instance mInstance = null;

  private Attribute[] mAttributes = new Attribute [0];

  private String mTitle = "Edit Instance";

  /**
   * Constructor for EditInstanceDialog.
   * 
   * @param parentShell
   */
  public EditInstanceDialog(Shell parentShell)
  {
    super(parentShell);
    setShellStyle(getShellStyle() | SWT.RESIZE);
  }

  /*
   * @see org.eclipse.jface.dialogs.Dialog#okPressed()
   */
  @Override
  protected void okPressed()
  {
    if (mCheckboxDefault.getSelection())
    {
      getInstance().setDefault();
    }
    else
    {
      if (getInstance().getType().getDefaultInstance() == getInstance())
      {
        getInstance().getType().setDefaultInstance(null);
      }
    }
    getInstance().setName(mTextName.getText());
    for (int i = 0; i < mAttributes.length; i++)
    {
      String name = mAttributes[i].getName();
      String value = mTextAttributeValues[i].getText();
      getInstance().getValues().put(name, value);
    }
    super.okPressed();
  }

  /*
   * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
   */
  @Override
  protected void cancelPressed()
  {
    super.cancelPressed();
  }

  /**
   * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent)
  {
    Composite result = (Composite)super.createDialogArea(parent);

    Composite panel = new Composite(result, SWT.NONE);
    panel.setLayout(new GridLayout(2, false));
    panel.setLayoutData(new GridData(GridData.FILL_BOTH));

    Label name = new Label(panel, SWT.NONE);
    name.setText(WizardMessages.getString("NewEnumWizPageInst.col.Name"));
    mTextName = new Text(panel, SWT.SINGLE | SWT.BORDER);
    mTextName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    mTextAttributeValues = new Text [mAttributes.length];
    for (int i = 0; i < mAttributes.length; i++)
    {
      Label label = new Label(panel, SWT.NONE);
      String[] attr = new String []{ mAttributes[i].getType(), mAttributes[i].getName(), };
      label.setText(WizardMessages.getFormattedString("EditInstanceDialog.attribute.Value", attr));
      mTextAttributeValues[i] = new Text(panel, SWT.SINGLE | SWT.BORDER);
      mTextAttributeValues[i].setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    Label defaultInstance = new Label(panel, SWT.NONE);
    defaultInstance.setText(WizardMessages.getString("NewEnumWizPageInst.col.Default"));
    mCheckboxDefault = new Button(panel, SWT.CHECK);

    initWidgetValues();
    hookListeners();

    mTextName.forceFocus();

    return result;
  }

  @Override
  protected Control createContents(Composite parent)
  {
    Control result = super.createContents(parent);
    validateInput();
    return result;
  }

  private void initWidgetValues()
  {
    if (getInstance() == null)
    {
      throw new IllegalStateException("Instance not set; cannot initialize");
    }
    mCheckboxDefault.setSelection(getInstance().isDefault());
    mTextName.setText(getInstance().getName());

    for (int i = 0; i < mTextAttributeValues.length; i++)
    {
      String value = getInstance().getAttributeValue(getAttributes()[i]);
      value = (value == null) ? "" : value;
      mTextAttributeValues[i].setText(value);
    }
  }

  private void hookListeners()
  {

    mTextName.addModifyListener(new ModifyListener()
      {
        public void modifyText(ModifyEvent e)
        {
          validateInput();
        }
      });

    for (int i = 0; i < mTextAttributeValues.length; i++)
    {
      mTextAttributeValues[i].addModifyListener(new ModifyListener()
        {
          public void modifyText(ModifyEvent e)
          {
            validateInput();
          }
        });
    }
  }

  private void validateInput()
  {
    boolean hasName = mTextName.getText().trim().length() > 0;
    boolean hasAllValues = true;
    for (int i = 0; i < mTextAttributeValues.length; i++)
    {
      boolean hasValue = mTextAttributeValues[i].getText().trim().length() > 0;
      hasAllValues &= hasValue;
    }

    boolean enabled = hasName && hasAllValues;
    getButton(IDialogConstants.OK_ID).setEnabled(enabled);
  }

  @Override
  protected void configureShell(Shell newShell)
  {
    super.configureShell(newShell);
    newShell.setText(getTitle());
  }

  /**
   * Returns the dialog title.
   * 
   * @return the dialog title
   */
  public String getTitle()
  {
    return mTitle;
  }

  /**
   * Sets the dialog title.
   * 
   * @param title
   *          the dialog title
   */
  public void setTitle(String title)
  {
    mTitle = title;
  }

  /**
   * Returns the <code>Instance</code> edited in this dialog.
   * 
   * @return the <code>Instance</code>
   */
  public Instance getInstance()
  {
    return mInstance;
  }

  /**
   * Sets the <code>Instance</code> to edit in this dialog.
   * 
   * @param instance
   *          the <code>Instance</code> to edit
   */
  public void setInstance(Instance instance)
  {
    mInstance = instance;
  }

  /**
   * @return the attribute array
   */
  public Attribute[] getAttributes()
  {
    return mAttributes;
  }

  /**
   * @param attributes
   */
  public void setAttributes(Attribute[] attributes)
  {
    if (attributes == null)
    {
      attributes = new Attribute [0];
    }
    mAttributes = attributes;
  }

}