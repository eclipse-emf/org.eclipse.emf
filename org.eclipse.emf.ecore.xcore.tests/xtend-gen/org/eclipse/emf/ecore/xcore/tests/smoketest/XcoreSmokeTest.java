package org.eclipse.emf.ecore.xcore.tests.smoketest;

import com.google.inject.Inject;
import java.util.ArrayList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit.util.ParseHelper;
import org.eclipse.xtext.junit.validation.ValidationTestHelper;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.smoketest.AbstractSmokeTest;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class XcoreSmokeTest extends AbstractSmokeTest {
  @Inject
  private ParseHelper<EObject> parser;
  
  @Inject
  private ValidationTestHelper validationTestHelper;
  
  /**
   * The models don't neccessarily need to be proper Xcore models.
   */
  public Iterable<String> getSmokeTestModels() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo ");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.EObject");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.EEList");
    _builder.newLine();
    _builder.append("class Stuff {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String x");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("contains OtherStuff otherStuff opposite parent keys x");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class OtherStuff {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("container Stuff parent opposite otherStuff");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int x");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("transient EEList<Integer> ints");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("refers Stuff stuff ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("refers EObject data");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("op int bar() { x + 1 }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("type ListOfStringArray wraps java.util.List<String[]>");
    _builder.newLine();
    String _string = _builder.toString();
    ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList(_string);
    return _newArrayList;
  }
  
  public void processModel(final String model) throws Exception {
    EObject _parse = this.parser.parse(model);
    this.validationTestHelper.validate(_parse);
  }
  
  public void processModelWithoutResourceSet(final String model) {
  }
  
  public LazyLinkingResource createResource(final String string) {
    return null;
  }
}
