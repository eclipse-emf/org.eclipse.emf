package org.eclipse.emf.examples.jet.article2.ui;



import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.emf.examples.jet.article2.model.TypesafeEnum;


/**
 * Wizard page where the user can specify the class name, package, source
 * folder, and other type-related information on the typesafe enumeration class
 * to create.
 * 
 * @author Remko Popma
 * @version $Revision: 1.3 $ ($Date: 2008/04/22 13:35:56 $)
 */
public class NewTypesafeEnumCreationWizardPage extends NewTypeWizardPage
{

  protected final static String PAGE_NAME = "NewTypesafeEnumCreationWizardPage"; //$NON-NLS-1$

  protected TypesafeEnum mTypesafeEnumModel = new TypesafeEnum();

  private Text mTextAuthor = null;

  private Text mTextVersion = null;

  /**
   */
  public NewTypesafeEnumCreationWizardPage()
  {
    super(true, PAGE_NAME);
    setTitle(WizardMessages.getString("NewEnumWizPage.title")); //$NON-NLS-1$
    setDescription(WizardMessages.getString("NewEnumWizPage.description")); //$NON-NLS-1$
  }

  /**
   * The wizard owning this page is responsible for calling this method with the
   * current selection. The selection is used to initialize the fields of the
   * wizard page.
   * 
   * @param selection
   *          used to initialize the fields
   */
  public void init(IStructuredSelection selection)
  {
    IJavaElement jelem = getInitialJavaElement(selection);
    initContainerPage(jelem);
    initTypePage(jelem);
    doStatusUpdate();

    //		boolean createMain= false;
    //		boolean createConstructors= false;
    //		boolean createUnimplemented= true;
    //		IDialogSettings section= getDialogSettings().getSection(PAGE_NAME);
    //		if (section != null) {
    //			createMain= section.getBoolean(SETTINGS_CREATEMAIN);
    //			createConstructors= section.getBoolean(SETTINGS_CREATECONSTR);
    //			createUnimplemented= section.getBoolean(SETTINGS_CREATEUNIMPLEMENTED);
    //		}
    //		
    //		setMethodStubSelection(createMain, createConstructors,
    // createUnimplemented, true);
  }

  // ------ validation --------
  private void doStatusUpdate()
  {
    // status of all used components
    IStatus[] status = new IStatus []{
      fContainerStatus,
      isEnclosingTypeSelected() ? fEnclosingTypeStatus : fPackageStatus,
      fTypeNameStatus,
      fModifierStatus,
      fSuperClassStatus,
      fSuperInterfacesStatus };

    updateEnumType();

    // the mode severe status will be displayed and the ok button
    // enabled/disabled.
    updateStatus(status);
  }

  /**
   * Updates the <code>TypesafeEnum</code> model from the user input.
   */
  private void updateEnumType()
  {
    getTypesafeEnumModel().setClassName(getTypeName());
    getTypesafeEnumModel().setPackageName(getPackageText());
  }

  /**
   * @see IWizardPage#canFlipToNextPage
   */
  @Override
  public boolean canFlipToNextPage()
  {
    boolean complete = isPageComplete();
    boolean hasNext = (getNextPage() != null);
    return complete && hasNext;
  }

  /*
   * @see NewContainerWizardPage#handleFieldChanged
   */
  @Override
  protected void handleFieldChanged(String fieldName)
  {
    super.handleFieldChanged(fieldName);

    doStatusUpdate();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
   */
  public void createControl(Composite parent)
  {
    initializeDialogUnits(parent);

    Composite composite = new Composite(parent, SWT.NONE);

    int nColumns = 4;

    GridLayout layout = new GridLayout();
    layout.numColumns = nColumns;
    composite.setLayout(layout);

    // pick & choose the wanted UI components

    createContainerControls(composite, nColumns);
    createPackageControls(composite, nColumns);

    createSeparator(composite, nColumns);

    createTypeNameControls(composite, nColumns);

    createAuthorVersionControls(composite, nColumns);

    //		createModifierControls(composite, nColumns);
    //		createSuperClassControls(composite, nColumns);
    //		createSuperInterfacesControls(composite, nColumns);

    setSuperClass("java.lang.Object", true);

    setControl(composite);

    //set default and focus
    //		restoreWidgetValues();
    Dialog.applyDialogFont(composite);
    //		WorkbenchHelp.setHelp(composite,
    // IJUnitHelpContextIds.NEW_TESTCASE_WIZARD_PAGE);
  }

  /**
   * @param parent
   * @param nColumns
   */
  private void createAuthorVersionControls(Composite parent, int nColumns)
  {
    Label author = new Label(parent, SWT.NONE);
    author.setText(WizardMessages.getString("NewEnumWizPage.author"));
    mTextAuthor = new Text(parent, SWT.SINGLE | SWT.BORDER);
    mTextAuthor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    mTextAuthor.addModifyListener(new ModifyListener()
      {

        public void modifyText(ModifyEvent e)
        {
          getTypesafeEnumModel().setAuthor(mTextAuthor.getText());
        }
      });
    mTextAuthor.setText(System.getProperty("user.name"));

    new Label(parent, SWT.NONE);
    new Label(parent, SWT.NONE);

    Label version = new Label(parent, SWT.NONE);
    version.setText(WizardMessages.getString("NewEnumWizPage.version"));
    mTextVersion = new Text(parent, SWT.SINGLE | SWT.BORDER);
    mTextVersion.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    mTextVersion.addModifyListener(new ModifyListener()
      {

        public void modifyText(ModifyEvent e)
        {
          getTypesafeEnumModel().setVersion(mTextVersion.getText());
        }
      });
    mTextVersion.setText("1.0");

    new Label(parent, SWT.NONE);
    new Label(parent, SWT.NONE);
  }

  /**
   * Returns the <code>TypesafeEnum</code> instance manipulated by the wizard
   * pages.
   * 
   * @return the <code>TypesafeEnum</code> instance
   */
  public TypesafeEnum getTypesafeEnumModel()
  {
    return mTypesafeEnumModel;
  }
}