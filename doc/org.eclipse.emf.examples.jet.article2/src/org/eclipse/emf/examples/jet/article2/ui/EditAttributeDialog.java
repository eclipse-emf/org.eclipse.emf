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



/**
 * Dialog for editing an <code>Attribute</code>.
 * 
 * @version $Revision: 1.2 $ ($Date: 2006/12/29 18:36:19 $)
 * @author Remko Popma
 */
public class EditAttributeDialog extends Dialog
{

  private Button mCheckboxKey = null;

  private Text mTextName = null;

  private Text mTextType = null;

  private Attribute mAttribute = null;

  private String mTitle = "Edit Attribute";

  /**
   * Constructor for EditAttributeDialog.
   * 
   * @param parentShell
   */
  public EditAttributeDialog(Shell parentShell)
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
    getAttribute().setKey(mCheckboxKey.getSelection());
    getAttribute().setName(mTextName.getText());
    getAttribute().setType(mTextType.getText());
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
    name.setText(WizardMessages.getString("NewEnumWizPageAttr.col.Name"));
    mTextName = new Text(panel, SWT.SINGLE | SWT.BORDER);
    mTextName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    Label type = new Label(panel, SWT.NONE);
    type.setText(WizardMessages.getString("NewEnumWizPageAttr.col.Type"));
    mTextType = new Text(panel, SWT.SINGLE | SWT.BORDER);
    mTextType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    Label key = new Label(panel, SWT.NONE);
    key.setText(WizardMessages.getString("NewEnumWizPageAttr.col.Key"));
    mCheckboxKey = new Button(panel, SWT.CHECK);

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
    if (getAttribute() == null)
    {
      throw new IllegalStateException("Attribute not set; cannot initialize");
    }
    mCheckboxKey.setSelection(getAttribute().isKey());
    mTextName.setText(getAttribute().getName());
    mTextType.setText(getAttribute().getType());
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
    mTextType.addModifyListener(new ModifyListener()
      {
        public void modifyText(ModifyEvent e)
        {
          validateInput();
        }
      });
  }

  private void validateInput()
  {
    boolean hasName = mTextName.getText().trim().length() > 0;
    boolean hasType = mTextType.getText().trim().length() > 0;

    boolean enabled = hasName && hasType;
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
   * Returns the <code>Attribute</code> edited in this dialog.
   * 
   * @return the <code>Attribute</code>
   */
  public Attribute getAttribute()
  {
    return mAttribute;
  }

  /**
   * Sets the <code>Attribute</code> to edit in this dialog.
   * 
   * @param attribute
   *          the <code>Attribute</code> to edit
   */
  public void setAttribute(Attribute attribute)
  {
    mAttribute = attribute;
  }
}