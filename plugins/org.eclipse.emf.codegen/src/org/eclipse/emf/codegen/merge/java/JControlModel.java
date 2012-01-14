/**
 * Copyright (c) 2006-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.NodeConverter;
import org.eclipse.emf.common.EMFPlugin;

class PrefixHandler
{
  protected String classPrefix;
  
  protected PrefixHandler()
  {
    super();
  }

  protected PrefixHandler(String classPrefix)
  {
    this.classPrefix = classPrefix;
  }

  protected void setClassPrefix(String classPrefix)
  {
    this.classPrefix = classPrefix;
  }

  public String getClassPrefix()
  {
    return classPrefix;
  }
}

/**
 *  A control model that  provides dictionaries and rules to drive a merge process.
 */
public class JControlModel extends PrefixHandler
{  
  protected final static Class<?>[] NO_PARAMETER_TYPES = new Class<?>[0];
  
  public static class Feature extends PrefixHandler
  {
    protected Class<?> featureClass;
    protected Method featureMethod;

    public Feature(String classPrefix)
    {
      super(classPrefix);
    }

    public Feature(String classPrefix, String path, Class<?>[] parameterTypes)
    {
      super(classPrefix);
      initialize(path, parameterTypes);
    }
    
    public Class<?> getFeatureClass()
    {
      return featureClass;
    }

    public Method getFeatureMethod()
    {
      return featureMethod;
    }
    
    public void initialize(String path, Class<?>[] parameterTypes)
    {
      int index = path.indexOf('/');
      String className = path.substring(0, index);
      String methodName = path.substring(index + 1);
      try
      {
        featureClass = classForClassName(classPrefix, className);
        if (featureClass != null)
        {
          featureMethod = featureClass.getMethod(methodName, parameterTypes);
        }
      }
      catch (NoSuchMethodException exception)
      {
        // Ignore the exception.
      }
    }
  }

  public static class DictionaryPattern extends PrefixHandler
  {
    protected static Class<?>[] stringParameterType = new Class<?>[] { String.class };
    protected String name;
    protected Feature selectorFeature;
    protected Pattern pattern;

    public DictionaryPattern(String classPrefix)
    {
      super(classPrefix);
    }

    public DictionaryPattern(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      name = element.getAttribute("name");
      selectorFeature = createFeature(getClassPrefix(), element.getAttribute("select"), NO_PARAMETER_TYPES);
      pattern = Pattern.compile(element.getAttribute("match"), Pattern.MULTILINE | Pattern.DOTALL);
    }
    
    protected Feature createFeature(String classPrefix, String path, Class<?>[] parameterTypes)
    {
      return new Feature(getClassPrefix(), path, parameterTypes);
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Feature getSelectorFeature()
    {
      return selectorFeature;
    }

    public void setSelectorFeature(Feature selectorFeature)
    {
      this.selectorFeature = selectorFeature;
    }

    public Pattern getPattern()
    {
      return pattern;
    }

    public void setPattern(Pattern pattern)
    {
      this.pattern = pattern;
    }
  }

  public static class PullRule extends PrefixHandler
  {
    protected static Class<?>[] noParameterTypes = new Class<?>[0];
    protected String name;

    protected Pattern sourceMarkup;
    protected Pattern sourceParentMarkup;
    protected Feature sourceGetFeature;
    protected Pattern sourceTransfer;

    protected Pattern targetMarkup;
    protected Pattern targetParentMarkup;
    protected Feature targetPutFeature;
    
    protected Feature equalityFeature;

    public PullRule(String classPrefix)
    {
      super(classPrefix);
    }

    public PullRule(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      String classPrefix = getClassPrefix();
      sourceGetFeature = createFeature(classPrefix, element.getAttribute("sourceGet"), noParameterTypes);
      if (sourceGetFeature != null)
      {
        Method featureMethod = sourceGetFeature.getFeatureMethod();
        if (featureMethod != null)
        {
          Class<?> sourceReturnType = featureMethod.getReturnType();
          targetPutFeature = createFeature(classPrefix, element.getAttribute("targetPut"), new Class<?>[] { sourceReturnType });
          if (targetPutFeature.getFeatureMethod() == null && sourceReturnType.isArray())
          {
            targetPutFeature = createFeature(classPrefix, element.getAttribute("targetPut"), new Class<?>[] { sourceReturnType.getComponentType() });
          }
        }
      }
      if (element.hasAttribute("sourceMarkup"))
      {
        sourceMarkup= Pattern.compile(element.getAttribute("sourceMarkup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("sourceParentMarkup"))
      {
        sourceParentMarkup= Pattern.compile(element.getAttribute("sourceParentMarkup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("targetMarkup"))
      {
        targetMarkup= Pattern.compile(element.getAttribute("targetMarkup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("targetParentMarkup"))
      {
        targetParentMarkup= Pattern.compile(element.getAttribute("targetParentMarkup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("sourceTransfer"))
      {
        sourceTransfer= Pattern.compile(element.getAttribute("sourceTransfer"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("equals"))
      {
        equalityFeature = createFeature(classPrefix, element.getAttribute("equals"), noParameterTypes);
      }
    }
    
    protected Feature createFeature(String classPrefix, String path, Class<?>[] parameterTypes)
    {
      return new Feature(getClassPrefix(), path, parameterTypes);
    }    

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Feature getSourceGetFeature()
    {
      return sourceGetFeature;
    }

    public void setSourceGetFeature(Feature sourceGetFeature)
    {
      this.sourceGetFeature = sourceGetFeature;
    }

    public Feature getEqualityFeature()
    {
      return equalityFeature;
    }

    public void setEqualityFeature(Feature equalityFeature)
    {
      this.equalityFeature = equalityFeature;
    }

    public Feature getTargetPutFeature()
    {
      return targetPutFeature;
    }

    public void setTargetPutFeature(Feature targetPutFeature)
    {
      this.targetPutFeature = targetPutFeature;
    }

    public Pattern getSourceTransfer()
    {
      return sourceTransfer;
    }

    public void setSourceTransfer(Pattern sourceTransfer)
    {
      this.sourceTransfer = sourceTransfer;
    }

    public Pattern getSourceMarkup()
    {
      return sourceMarkup;
    }

    public void setSourceMarkup(Pattern sourceMarkup)
    {
      this.sourceMarkup = sourceMarkup;
    }

    public Pattern getTargetMarkup()
    {
      return targetMarkup;
    }

    public void setTargetMarkup(Pattern targetMarkup)
    {
      this.targetMarkup = targetMarkup;
    }
    
    public Pattern getSourceParentMarkup()
    {
      return sourceParentMarkup;
    }

    public void setSourceParentMarkup(Pattern sourceParentMarkup)
    {
      this.sourceParentMarkup = sourceParentMarkup;
    }

    public Pattern getTargetParentMarkup()
    {
      return targetParentMarkup;
    }

    public void setTargetParentMarkup(Pattern targetParentMarkup)
    {
      this.targetParentMarkup = targetParentMarkup;
    }
  }

  /**
   * <p>A push rule restricts what elements are pushed from the source to the target.
   * By default, if there are no push rules for the specific element type (element is 
   * not an instance of selector class of any rules), then the element is pushed.</p>
   * 
   * <p>If element is an instance of selector class of at least one push rule, then the 
   * element is pushed only if the element is marked up by at least one push rule with 
   * matching selector class.</p>
   * 
   * <p>If none of mark-up and targetParentMarkup is set in the push rule with matching 
   * selector class, then node is marked up. If both mark-up and targetParentMarkup is set, 
   * then the node is marked up only if node and its parent in the target are marked up 
   * respectively. If mark-up or targetParentMarkup is set, the node is marked up if node or 
   * its parent in the target are marked up respectively.</p>
   */
  public static class PushRule extends PrefixHandler
  {
    protected String name;
    protected Class<?> selector;
    protected Pattern markup;
    protected Pattern targetParentMarkup;    

    public PushRule(String classPrefix)
    {
      super(classPrefix);
    }

    public PushRule(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      if (element.hasAttribute("select"))
      {
        selector = classForClassName(getClassPrefix(), element.getAttribute("select"));
      }
      if (element.hasAttribute("markup"))
      {
        markup= Pattern.compile(element.getAttribute("markup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      
      if (element.hasAttribute("targetParentMarkup"))
      {
        targetParentMarkup= Pattern.compile(element.getAttribute("targetParentMarkup"), Pattern.MULTILINE | Pattern.DOTALL);
      }      
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Class<?> getSelector()
    {
      return selector;
    }

    public void setSelector(Class<?> selector)
    {
      this.selector = selector;
    }

    public Pattern getMarkup()
    {
      return markup;
    }

    public void setMarkup(Pattern markup)
    {
      this.markup = markup;
    }
    
    public Pattern getTargetParentMarkup()
    {
      return targetParentMarkup;
    }

    public void setTargetParentMarkup(Pattern targetParentMarkup)
    {
      this.targetParentMarkup = targetParentMarkup;
    }    
  }  
  
  /**
   * <p>A sweep rule removes elements from the target if they are <b>NOT</b> available
   * in the source.  It can work on available Dictionary Patterns or be used to
   * filter out import statements as follows:</p>
   * <pre>
   *   &lt;merge:sweep markup=&quot;^gen$&quot; select=&quot;Member&quot;/&gt;
   *   &lt;merge:sweep markup=&quot;^org.eclipse.emf.ecore.EMetaObject$&quot; select=&quot;Import&quot;/&gt;
   * </pre>
   * <p>The first line removes all &quot;members&quot; (attribute, method, ...) that matches
   * the expression defined by the &quot;^gen$&quot; Dictionary Pattern.  The second
   * removes the &quot;org.eclipse.emf.ecore.EMetaObject&quot; import.</p>
   * <p>The <code>action</code> attribute defines what the sweep rule does with the 
   * node to be sweep.  Besides removing the node (which is the default action), you can use this
   * attribute to also rename the node or comment it out.  The &quot;rename&quot; action
   * requires the <code>newName</code> attribute to be set. This attribute is the name that
   * the node is renamed to and can be expressed as <code>&quot;deleted_{0}&quot;</code>, where 
   * <code>{0}</code> is presents the current name.</p> 
   */
  public static class SweepRule extends PrefixHandler
  {
    public static enum Action
    {
      REMOVE,
      RENAME,
      COMMENT;
    }
    
    protected String name;
    protected Class<?> selector;
    protected Pattern markup;
    protected Pattern parentMarkup;
    
    protected SweepRule.Action action = Action.REMOVE;
    protected String newName;    

    public SweepRule(String classPrefix)
    {
      super(classPrefix);
    }

    public SweepRule(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      if (element.hasAttribute("select"))
      {
        selector = classForClassName(getClassPrefix(), element.getAttribute("select"));
      }
      if (element.hasAttribute("markup"))
      {
        markup= Pattern.compile(element.getAttribute("markup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("parentMarkup"))
      {
        parentMarkup= Pattern.compile(element.getAttribute("parentMarkup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("action"))
      {
        action = Action.valueOf(element.getAttribute("action").toUpperCase());
        if (action == Action.RENAME && element.hasAttribute("newName"))
        {
          newName = element.getAttribute("newName");
        }
      }      
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Class<?> getSelector()
    {
      return selector;
    }

    public void setSelector(Class<?> selector)
    {
      this.selector = selector;
    }

    public Pattern getMarkup()
    {
      return markup;
    }

    public void setMarkup(Pattern markup)
    {
      this.markup = markup;
    }
    
    public Pattern getParentMarkup()
    {
      return parentMarkup;
    }

    public void setParentMarkup(Pattern parentMarkup)
    {
      this.parentMarkup = parentMarkup;
    }
    
    public SweepRule.Action getAction()
    {
      return action;
    }
    
    public void setAction(SweepRule.Action action)
    {
      this.action = action;
    }
    
    public String getNewName()
    {
      return newName;
    }
    
    public void setNewName(String newName)
    {
      this.newName = newName;
    }    
  }

  /**
   * <p>The sort rule is used to ensure that the order of the attributes as declared
   * on the source is respected  As usual you need to specify a Dictionary Pattern 
   * to identify the attributes that should be treated.  Here's an example:</p>
   * <pre>
   *   &lt;merge:sort markup=&quot;^ordered$&quot; select=&quot;Member&quot;/&gt;
   * </pre>
   */
  public static class SortRule extends PrefixHandler
  {
    protected String name;
    protected Class<?> selector;
    protected Pattern markup;

    public SortRule(String classPrefix)
    {
      super(classPrefix);
    }

    public SortRule(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      if (element.hasAttribute("select"))
      {
        selector = classForClassName(getClassPrefix(), element.getAttribute("select"));
      }
      if (element.hasAttribute("markup"))
      {
        markup= Pattern.compile(element.getAttribute("markup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Class<?> getSelector()
    {
      return selector;
    }

    public void setSelector(Class<?> selector)
    {
      this.selector = selector;
    }

    public Pattern getMarkup()
    {
      return markup;
    }

    public void setMarkup(Pattern markup)
    {
      this.markup = markup;
    }
  }
  
  static Map<String, Class<?>> classNameToClassMap = new HashMap<String, Class<?>>();

  public static Class<?> classForClassName(String classPrefix, String className)
  {
    if (classPrefix != null)
    {
      className = classPrefix + className;
    }
    
    Class<?> result = classNameToClassMap.get(className);
    if (result == null)
    {
      try
      {
        result = Class.forName(className);
        classNameToClassMap.put(className, result);
      }
      catch (ClassNotFoundException exception)
      {
        //  We expect this failure when running stand-alone
        //
        if (EMFPlugin.IS_ECLIPSE_RUNNING)
        {
          CodeGenPlugin.INSTANCE.log(exception);
        }
        
        // JControlModel.class is used with classNames that were not found
        //
        classNameToClassMap.put(className, JControlModel.class);
      }
    }
    return result == JControlModel.class ? 
      null : 
      result;
  }
  
  /**
   * <p>During the merge, the current state of the nodes of a tree is applied to the nodes of 
   * another tree.  Pairing the nodes of each tree is one of the main activities of the process.</p>
   * 
   * <p>A Match Rule allows a developer to replace the default matching algorithm, based on 
   * qualified names, to better suite the needs of an specific application.  Some examples:</p>    
   * 
   * <pre>
   *   &lt;merge:match markup=&quot;^gen$&quot; get=&quot;Member/getName&quot;/&gt;
   *   &lt;merge:match get=&quot;Method/getComment&quot; signature=&quot;\s*@\s*uuid\s*(\S*)\s*\n&quot;/&gt;</pre>
   * 
   * <p>The first match rule is applicable to any Member marked with the expression defined 
   * by the &quot;^gen$&quot; Dictionary Pattern.  It defines that these members are matched
   * by their names.  The second rule is applicable to any method that has 
   * <code>@uuid xyz</code> on its comment.  In this case, the string <code>xyz</code> 
   * is be used to match the nodes.</p>
   * 
   * <p>An important remark is that if there is a type conversion during the merge process,
   * the match rules should <b>not</b> be used to pair different &quot;kinds&quot; of elements
   * like fields and enumerator constants for example. See 
   * {@link JMerger#convertTarget(org.eclipse.emf.codegen.merge.java.facade.JAbstractType, Class) Jmerger.convert}
   * and {@link NodeConverter} for further details on conversions.</p>
   */
  public static class MatchRule extends PrefixHandler
  {
    protected String name;    
    protected Pattern markup;    
    protected Feature getFeature;
    protected Pattern signature;
    protected boolean stopMatching = false;
    
    public MatchRule(String classPrefix)
    {
      super(classPrefix);
    }

    public MatchRule(String classPrefix, Element element)
    {
      this(classPrefix);
      initialize(element);
    }

    public void initialize(Element element)
    {
      if (element.hasAttribute("markup"))
      {
        markup= Pattern.compile(element.getAttribute("markup"), Pattern.MULTILINE | Pattern.DOTALL);
      }
      if (element.hasAttribute("get"))
      {
        getFeature = createFeature(getClassPrefix(), element.getAttribute("get"), NO_PARAMETER_TYPES);
        if (element.hasAttribute("signature"))
        {
          signature = Pattern.compile(element.getAttribute("signature"), Pattern.MULTILINE | Pattern.DOTALL);
        }
      }
      if (element.hasAttribute("stopMatching"))
      {
        stopMatching = Boolean.parseBoolean(element.getAttribute("stopMatching"));
      }
    }
    
    protected Feature createFeature(String classPrefix, String path, Class<?>[] parameterTypes)
    {
      return new Feature(getClassPrefix(), path, parameterTypes);
    }    

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public Feature getGetFeature()
    {
      return getFeature;
    }

    public void setGetFeature(Feature getFeature)
    {
      this.getFeature = getFeature;
    }

    public Pattern getMarkup()
    {
      return markup;
    }

    public void setMarkup(Pattern markup)
    {
      this.markup = markup;
    }
    
    public Pattern getSignature()
    {
      return signature;
    }
    
    public void setSignature(Pattern signature)
    {
      this.signature = signature;
    }
    
    public boolean isStopMatching()
    {
      return this.stopMatching;
    }
    
    public void setStopMatching(boolean stopMatching)
    {
      this.stopMatching = stopMatching;
    }
  }  
  
  protected FacadeHelper facadeHelper;
  
  protected List<DictionaryPattern> dictionaryPatterns;
  protected List<PullRule> pullRules;
  protected List<PushRule> pushRules;
  protected List<SweepRule> sweepRules;
  protected List<SortRule> sortRules;
  protected List<MatchRule> matchRules;
  protected Pattern blockPattern;
  protected Pattern noImportPattern;
  protected String redirect;

  protected boolean indentIsSet = false;
  protected String indent;
  protected boolean standardBraceStyleIsSet = false;
  protected boolean standardBraceStyle;

  public JControlModel()
  {
    super();
  }
  
  protected void setFacadeHelper(FacadeHelper facadeHelper)
  {
    if (this.facadeHelper != null)
    {
      this.facadeHelper.setControlModel(null);
      if (dictionaryPatterns != null)
      {
        dictionaryPatterns.clear();
      }
      if (pullRules != null)
      {
        pullRules.clear();
      }
      if (pushRules != null)
      {
        pushRules.clear();
      }
      if (sweepRules != null)
      {
        sweepRules.clear();
      }
      if (sortRules != null)
      {
        sortRules.clear();
      }
      if (matchRules != null)
      {
        matchRules.clear();
      }
      blockPattern = null;
      noImportPattern = null;
      redirect = null;      
    }
    
    this.facadeHelper = facadeHelper;
    
    if (facadeHelper != null)
    {
      setClassPrefix(facadeHelper.getClassPrefix());
      facadeHelper.setControlModel(this);
    }
  }

  public FacadeHelper getFacadeHelper()
  {
    return facadeHelper;
  }
  
  public boolean convertToStandardBraceStyle()
  {
    return standardBraceStyle;
  }

  public void setConvertToStandardBraceStyle(boolean standardBraceStyle)
  {
    standardBraceStyleIsSet = true;
    this.standardBraceStyle = standardBraceStyle;
  }

  public String getLeadingTabReplacement()
  {
    return indent;
  }

  public void setLeadingTabReplacement(String indent)
  {
    indentIsSet = true;
    this.indent = indent;
  }

  public String getRedirect()
  {
    return redirect;
  }

  public Pattern getBlockPattern()
  {
    return blockPattern;
  }

  public Pattern getNoImportPattern()
  {
    return noImportPattern;
  }

  public List<DictionaryPattern> getDictionaryPatterns()
  {
    if (dictionaryPatterns == null)
    {
      dictionaryPatterns = new ArrayList<DictionaryPattern>();
    }
    return dictionaryPatterns;
  }

  public List<PullRule> getPullRules()
  {
    if (pullRules == null)
    {
      pullRules = new ArrayList<PullRule>();
    }
    return pullRules;
  }

  public List<PushRule> getPushRules()
  {
    if (pushRules == null)
    {
      pushRules = new ArrayList<PushRule>();
    }
    return pushRules;
  }  
  
  public List<SweepRule> getSweepRules()
  {
    if (sweepRules == null)
    {
      sweepRules = new ArrayList<SweepRule>();
    }
    return sweepRules;
  }

  public List<SortRule> getSortRules()
  {
    if (sortRules == null)
    {
      sortRules = new ArrayList<SortRule>();
    }
    return sortRules;
  }
  
  public List<MatchRule> getMatchRules()
  {
    if (matchRules == null)
    {
      matchRules = new ArrayList<MatchRule>();
    }
    return matchRules;
  }

  public boolean canMerge()
  {
    FacadeHelper facadeHelper = getFacadeHelper();
    return facadeHelper != null && facadeHelper.canMerge();
  }
  
  public void initialize(FacadeHelper facadeHelper, String uri)
  {
    setFacadeHelper(facadeHelper);
    initialize(uri);
  }

  protected void initialize(String uri)
  {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    documentBuilderFactory.setValidating(false);
    try
    {
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(new InputSource(uri));
      initialize(document.getDocumentElement());
    }
    catch (Exception exception)
    {
      CodeGenPlugin.INSTANCE.log(exception);
    }
  }

  protected void initialize(Element element)
  {
    if (element.getLocalName().equals("options"))
    {
      if (!standardBraceStyleIsSet && "standard".equals(element.getAttributeNS(null, "braceStyle")))
      {
        standardBraceStyle = true;
      }

      if (!indentIsSet && element.hasAttributeNS(null, "indent"))
      {
        indent = element.getAttributeNS(null, "indent");
      }

      if (element.hasAttributeNS(null, "redirect"))
      {
        redirect = element.getAttributeNS(null, "redirect");
      }

      if (element.hasAttributeNS(null, "block"))
      {
        blockPattern = Pattern.compile(element.getAttributeNS(null, "block"), Pattern.MULTILINE | Pattern.DOTALL); 
      }

      if (element.hasAttributeNS(null, "noImport"))
      {
        noImportPattern = Pattern.compile(element.getAttributeNS(null, "noImport"), Pattern.MULTILINE | Pattern.DOTALL); 
      }

      String classPrefix = getClassPrefix();
      for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
      {
        if (child.getNodeType() == Node.ELEMENT_NODE)
        {
          Element elementChild = (Element)child;
          if (elementChild.getLocalName().equals("dictionaryPattern"))
          {
            getDictionaryPatterns().add(createDictionaryPattern(classPrefix, elementChild));
          }
          else if (elementChild.getLocalName().equals("pull"))
          {
            getPullRules().add(createPullRule(classPrefix, elementChild));
          }
          else if (elementChild.getLocalName().equals("push"))
          {
            getPushRules().add(createPushRule(classPrefix, elementChild));
          }
          else if (elementChild.getLocalName().equals("sweep"))
          {
            getSweepRules().add(createSweepRule(classPrefix, elementChild));
          }
          else if (elementChild.getLocalName().equals("sort"))
          {
            getSortRules().add(createSortRule(classPrefix, elementChild));
          }
          else if (elementChild.getLocalName().equals("match"))
          {
            getMatchRules().add(createMatchRule(classPrefix, elementChild));
          }
        }
      }
    }
  }
  
  protected DictionaryPattern createDictionaryPattern(String classPrefix, Element elementChild)
  {
    return new DictionaryPattern(classPrefix, elementChild);
  }
  
  protected PullRule createPullRule(String classPrefix, Element elementChild)
  {
    return new PullRule(classPrefix, elementChild);
  }
  
  protected PushRule createPushRule(String classPrefix, Element elementChild)
  {
    return new PushRule(classPrefix, elementChild);
  }
 
  protected SweepRule createSweepRule(String classPrefix, Element elementChild)
  {
    return new SweepRule(classPrefix, elementChild);
  }
  
  protected SortRule createSortRule(String classPrefix, Element elementChild)
  {
    return new SortRule(classPrefix, elementChild);
  }  

  protected MatchRule createMatchRule(String classPrefix, Element elementChild)
  {
    return new MatchRule(classPrefix, elementChild);
  }  
}
