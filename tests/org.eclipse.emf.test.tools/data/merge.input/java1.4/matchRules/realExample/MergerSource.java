/*
 * @(#)com.ibm.vista.re.asl.provider.validator.ReAdministerReminderRulesASPValidator.java
 * ===========================================================================
 * Licensed Materials - Property of IBM 
 * "Restricted Materials of IBM" 
 * (C) Copyright IBM Corp. 2005 All rights reserved.
 * ===========================================================================
 * 
 * @(#)ReAdministerReminderRulesASPValidator.java  $Revision: 1.1 $  $Date: 2007/04/05 18:32:08 $
 */
/*
 * Change Activity:
 *
 * Reason    Date     Author      Version    Description
 * ------     ----     ------      -------    -------------------------------------- 
 * 
 */
package com.ibm.vista.re.asl.provider.validator;

import com.ibm.vista.common.constants.IVistaConstants;
import com.ibm.vista.common.asl.provider.impl.AbstractBaseASPValidator;
import com.ibm.vista.common.exceptions.VistaFunctionalException;  
import com.ibm.vista.re.am.ifc.IReReminderTemplateAEList;    
import com.ibm.vista.re.asl.administerreminderrules.response.ifc.ISaveReminderRulesResponse;
import com.ibm.vista.re.dsl.provider.ifc.IReReminderTemplateDSP;
import com.ibm.vista.re.dm.ifc.IReDomainEntityFactory;
import com.ibm.vista.re.asl.provider.helper.ReAdministerReminderRulesMappingASPHelper;
import com.ibm.vista.re.am.ifc.IReApplicationEntityFactory;
/**
 * 
 *
 * <!--begin-user-doc-->
 *
 * TODO_GEN Add class or interface description
 * @author FC092022 (IBM Corp.)
 * @invariant $none
 *
 * <!--end-user-doc--> 
 * @version $Revision: 1.1 $ 
 * ReAdministerReminderRulesASPValidator.java
 * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
 * @model ARC108_ComponentModel_RE_Configuration_AC_e
 */

public class ReAdministerReminderRulesASPValidator extends AbstractBaseASPValidator {

  /**
   * MODEL_NAME stores the name of model from which the component has been generated.
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"   
   */   
  private static final String MODEL_NAME = "ARC108_ComponentModel_RE_Configuration_AC_e";
  
  /**
   * CONTEXT_ID stores the context in which the component can be invoked.
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"   
   */
  private static final String CONTEXT_ID = "RE";
  
  /**
   * SERVICE_PROVIDER stores the service provider name.
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"   
   */
  private static final String SERVICE_PROVIDER = "ReAdministerReminderRulesASP";
  
  
  /**
   * Reference Property for ReReminderTemplateDSP
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  private IReReminderTemplateDSP reReminderTemplateDSP = null;

  /**
   * Reference Property for ReDomainEntityFactory
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  private IReDomainEntityFactory reDomainEntityFactory = null;

  /**
   * Reference Property for ReAdministerReminderRulesMappingASPHelper
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  private ReAdministerReminderRulesMappingASPHelper reAdministerReminderRulesMappingASPHelper = null;

  /**
   * Reference Property for ReApplicationEntityFactory
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  private IReApplicationEntityFactory reApplicationEntityFactory = null;




  /**
   * Default Constructor for ReAdministerReminderRulesASPValidator
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public ReAdministerReminderRulesASPValidator() {
    super();
  } // End of Default Constructor - ReAdministerReminderRulesASPValidator

  /**
   *
   * <!--begin-user-doc-->
   *
   * TODO_GEN add method comment here
   * @param pReminderRulesInList TODO_GEN add parameter description here
   * @return TODO_GEN add description here
   * @pre $none
   * @post $none 
   *
   * <!--end-user-doc-->
   *
   * @service saveReminderRules
   * @serviceType save 
   * @uuid (__rbGsFHqEdugi_DC_0ZCCg)
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public boolean preValidateSaveReminderRules(IReReminderTemplateAEList pReminderRulesInList, ISaveReminderRulesResponse pResponse) throws VistaFunctionalException {
    //source
     if (isDebugEnabled()){
      logDebug(IVistaConstants.MSG_METHOD_ENTRY, new Object[]{"preValidateSaveReminderRules", this.getClass().getName()});
    } // End If
    boolean validationSuccessful = true;
    // begin-user-code
      
    validationSuccessful = super.validateParametersAreNotNull(new String[] {"pReminderRulesInList"}, new Object[] {pReminderRulesInList}, pResponse); 
    // end-user-code  
    if (isDebugEnabled()){
      logDebug(IVistaConstants.MSG_METHOD_EXIT, new Object[]{"preValidateSaveReminderRules", this.getClass().getName()});
    } // End If
    return validationSuccessful;
  } // End of Method - preValidateSaveReminderRules

  /**
   *
   * <!--begin-user-doc-->
   *
   * TODO_GEN add method comment here
   * @param pReminderRulesInList TODO_GEN add parameter description here
   * @return TODO_GEN add description here
   * @pre $none
   * @post $none 
   *
   * <!--end-user-doc-->
   *
   * @service saveReminderRules
   * @serviceType save 
   * @uuid (__rbGsFHqEdugi_DC_0ZCCg)
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public boolean postValidateSaveReminderRules(IReReminderTemplateAEList pReminderRulesInList, ISaveReminderRulesResponse pResponse) throws VistaFunctionalException {
    if (isDebugEnabled()){  
      logDebug(IVistaConstants.MSG_METHOD_ENTRY, new Object[]{"postValidateSaveReminderRules", this.getClass().getName()});
    } // End If
    boolean postValidationSuccessful = true;
    // begin-user-code
    // end-user-code  
    if (isDebugEnabled()){
      logDebug(IVistaConstants.MSG_METHOD_EXIT, new Object[]{"postValidateSaveReminderRules", this.getClass().getName()});
    } // End If
    return postValidationSuccessful;
  } // End of Method - postValidateSaveReminderRules
  

  
  
  /**
   * Setter-method for reReminderTemplateDSP.
   * @param reReminderTemplateDSP Reference to set
   * @pre $none
   * @post $none
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public void setReReminderTemplateDSP(IReReminderTemplateDSP pReReminderTemplateDSP) {
    this.reReminderTemplateDSP = pReReminderTemplateDSP;
  } // End of Method - setReReminderTemplateDSP
  
  /**
   * Getter-method for reReminderTemplateDSP
   * @return reference of reReminderTemplateDSP.
   * @pre $none
   * @post $none
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */  
  public IReReminderTemplateDSP getReReminderTemplateDSP() {
    return reReminderTemplateDSP;
  } // End of Method - getReReminderTemplateDSP
  
  
  /**
   * Setter-method for reDomainEntityFactory.
   * @param reDomainEntityFactory Reference to set
   * @pre $none
   * @post $none
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public void setReDomainEntityFactory(IReDomainEntityFactory pReDomainEntityFactory) {
    this.reDomainEntityFactory = pReDomainEntityFactory;
  } // End of Method - setReDomainEntityFactory
  
  /**
   * Getter-method for reDomainEntityFactory
   * @return reference of reDomainEntityFactory.
   * @pre $none
   * @post $none
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */  
  public IReDomainEntityFactory getReDomainEntityFactory() {
    return reDomainEntityFactory;
  } // End of Method - getReDomainEntityFactory
  
  
  /**
   * Setter-method for reAdministerReminderRulesMappingASPHelper.
   * @param reAdministerReminderRulesMappingASPHelper Reference to set
   * @pre $none
   * @post $none
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public void setReAdministerReminderRulesMappingASPHelper(ReAdministerReminderRulesMappingASPHelper pReAdministerReminderRulesMappingASPHelper) {
    this.reAdministerReminderRulesMappingASPHelper = pReAdministerReminderRulesMappingASPHelper;
  } // End of Method - setReAdministerReminderRulesMappingASPHelper
  
  /**
   * Getter-method for reAdministerReminderRulesMappingASPHelper
   * @return reference of reAdministerReminderRulesMappingASPHelper.
   * @pre $none
   * @post $none
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */  
  public ReAdministerReminderRulesMappingASPHelper getReAdministerReminderRulesMappingASPHelper() {
    return reAdministerReminderRulesMappingASPHelper;
  } // End of Method - getReAdministerReminderRulesMappingASPHelper
  
  
  /**
   * Setter-method for reApplicationEntityFactory.
   * @param reApplicationEntityFactory Reference to set
   * @pre $none
   * @post $none
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public void setReApplicationEntityFactory(IReApplicationEntityFactory pReApplicationEntityFactory) {
    this.reApplicationEntityFactory = pReApplicationEntityFactory;
  } // End of Method - setReApplicationEntityFactory
  
  /**
   * Getter-method for reApplicationEntityFactory
   * @return reference of reApplicationEntityFactory.
   * @pre $none
   * @post $none
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */  
  public IReApplicationEntityFactory getReApplicationEntityFactory() {
    return reApplicationEntityFactory;
  } // End of Method - getReApplicationEntityFactory

  /**
   * @see com.ibm.vista.common.provider.ifc.IBaseServiceProvider#getName()
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public String getName() {
    return ReAdministerReminderRulesASPValidator.SERVICE_PROVIDER;
  }

  /**
   * @see com.ibm.vista.common.provider.ifc.IBaseServiceProvider#getContextId()
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public String getContextId() {
    return ReAdministerReminderRulesASPValidator.CONTEXT_ID;
  }
  
  /**
   * @see com.ibm.vista.common.provider.ifc.IBaseServiceProvider#getModelName()
   * @generated "1.3.14/Nov 27, 2006 9:58:07 AM"
   */
  public String getModelName() {
    return ReAdministerReminderRulesASPValidator.MODEL_NAME;
  }  

} // End of Class - ReAdministerReminderRulesASPValidator
