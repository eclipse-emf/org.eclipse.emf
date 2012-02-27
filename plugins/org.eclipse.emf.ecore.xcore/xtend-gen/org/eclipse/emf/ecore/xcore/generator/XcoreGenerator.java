package org.eclipse.emf.ecore.xcore.generator;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcoreExtensions;
import org.eclipse.emf.ecore.xcore.generator.XcoreAppendable;
import org.eclipse.emf.ecore.xcore.generator.XcoreGeneratorImpl;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

@SuppressWarnings("all")
public class XcoreGenerator implements IGenerator {
  @Inject
  private XcoreMapper mappings;
  
  @Inject
  private XbaseCompiler compiler;
  
  @Inject
  private Provider<XcoreGeneratorImpl> xcoreGeneratorImplProvider;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    EList<EObject> _contents = resource.getContents();
    EObject _head = IterableExtensions.<EObject>head(_contents);
    final XPackage pack = ((XPackage) _head);
    Iterable<EObject> _allContentsIterable = XcoreExtensions.allContentsIterable(pack);
    Iterable<XOperation> _filter = IterableExtensions.<XOperation>filter(_allContentsIterable, org.eclipse.emf.ecore.xcore.XOperation.class);
    for (final XOperation op : _filter) {
      {
        XOperationMapping _mapping = this.mappings.getMapping(op);
        EOperation _eOperation = _mapping.getEOperation();
        final EOperation eOperation = _eOperation;
        XBlockExpression _body = op.getBody();
        final XBlockExpression body = _body;
        boolean _operator_notEquals = ObjectExtensions.operator_notEquals(body, null);
        if (_operator_notEquals) {
          XcoreAppendable _createAppendable = this.createAppendable();
          final XcoreAppendable appendable = _createAppendable;
          XOperationMapping _mapping_1 = this.mappings.getMapping(op);
          JvmOperation _jvmOperation = _mapping_1.getJvmOperation();
          JvmDeclaredType _declaringType = _jvmOperation.getDeclaringType();
          appendable.declareVariable(_declaringType, "this");
          this.compiler.compile(body, appendable, null);
          String _string = appendable.toString();
          String _extractBody = this.extractBody(_string);
          EcoreUtil.setAnnotation(eOperation, GenModelPackage.eNS_URI, "body", _extractBody);
        }
      }
    }
    Iterable<EObject> _allContentsIterable_1 = XcoreExtensions.allContentsIterable(pack);
    Iterable<XStructuralFeature> _filter_1 = IterableExtensions.<XStructuralFeature>filter(_allContentsIterable_1, org.eclipse.emf.ecore.xcore.XStructuralFeature.class);
    for (final XStructuralFeature feature : _filter_1) {
      {
        XFeatureMapping _mapping = this.mappings.getMapping(feature);
        EStructuralFeature _eStructuralFeature = _mapping.getEStructuralFeature();
        final EStructuralFeature eStructuralFeature = _eStructuralFeature;
        XBlockExpression _getBody = feature.getGetBody();
        final XBlockExpression getBody = _getBody;
        boolean _operator_notEquals = ObjectExtensions.operator_notEquals(getBody, null);
        if (_operator_notEquals) {
          XFeatureMapping _mapping_1 = this.mappings.getMapping(feature);
          JvmOperation _getter = _mapping_1.getGetter();
          final JvmOperation getter = _getter;
          XcoreAppendable _createAppendable = this.createAppendable();
          final XcoreAppendable appendable = _createAppendable;
          JvmDeclaredType _declaringType = getter.getDeclaringType();
          appendable.declareVariable(_declaringType, "this");
          this.compiler.compile(getBody, appendable, null);
          String _string = appendable.toString();
          String _extractBody = this.extractBody(_string);
          EcoreUtil.setAnnotation(eStructuralFeature, GenModelPackage.eNS_URI, "get", _extractBody);
        }
      }
    }
    Iterable<EObject> _allContentsIterable_2 = XcoreExtensions.allContentsIterable(pack);
    Iterable<XDataType> _filter_2 = IterableExtensions.<XDataType>filter(_allContentsIterable_2, org.eclipse.emf.ecore.xcore.XDataType.class);
    for (final XDataType dataType : _filter_2) {
      {
        XDataTypeMapping _mapping = this.mappings.getMapping(dataType);
        EDataType _eDataType = _mapping.getEDataType();
        final EDataType eDataType = _eDataType;
        XBlockExpression _createBody = dataType.getCreateBody();
        final XBlockExpression createBody = _createBody;
        XDataTypeMapping _mapping_1 = this.mappings.getMapping(dataType);
        JvmOperation _creator = _mapping_1.getCreator();
        final JvmOperation creator = _creator;
        boolean _operator_and = false;
        boolean _operator_notEquals = ObjectExtensions.operator_notEquals(createBody, null);
        if (!_operator_notEquals) {
          _operator_and = false;
        } else {
          boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(creator, null);
          _operator_and = BooleanExtensions.operator_and(_operator_notEquals, _operator_notEquals_1);
        }
        if (_operator_and) {
          XcoreAppendable _createAppendable = this.createAppendable();
          final XcoreAppendable appendable = _createAppendable;
          EList<JvmFormalParameter> _parameters = creator.getParameters();
          JvmFormalParameter _get = _parameters.get(0);
          appendable.declareVariable(_get, "it");
          this.compiler.compile(createBody, appendable, null);
          String _string = appendable.toString();
          String _extractBody = this.extractBody(_string);
          EcoreUtil.setAnnotation(eDataType, GenModelPackage.eNS_URI, "create", _extractBody);
        }
        XBlockExpression _convertBody = dataType.getConvertBody();
        final XBlockExpression convertBody = _convertBody;
        XDataTypeMapping _mapping_2 = this.mappings.getMapping(dataType);
        JvmOperation _converter = _mapping_2.getConverter();
        final JvmOperation converter = _converter;
        boolean _operator_and_1 = false;
        boolean _operator_notEquals_2 = ObjectExtensions.operator_notEquals(convertBody, null);
        if (!_operator_notEquals_2) {
          _operator_and_1 = false;
        } else {
          boolean _operator_notEquals_3 = ObjectExtensions.operator_notEquals(converter, null);
          _operator_and_1 = BooleanExtensions.operator_and(_operator_notEquals_2, _operator_notEquals_3);
        }
        if (_operator_and_1) {
          XcoreAppendable _createAppendable_1 = this.createAppendable();
          final XcoreAppendable appendable_1 = _createAppendable_1;
          EList<JvmFormalParameter> _parameters_1 = converter.getParameters();
          JvmFormalParameter _get_1 = _parameters_1.get(0);
          appendable_1.declareVariable(_get_1, "it");
          this.compiler.compile(convertBody, appendable_1, null);
          String _string_1 = appendable_1.toString();
          String _extractBody_1 = this.extractBody(_string_1);
          EcoreUtil.setAnnotation(eDataType, GenModelPackage.eNS_URI, "convert", _extractBody_1);
        }
      }
    }
    EList<EObject> _contents_1 = resource.getContents();
    Iterable<GenModel> _filter_3 = IterableExtensions.<GenModel>filter(_contents_1, org.eclipse.emf.codegen.ecore.genmodel.GenModel.class);
    GenModel _head_1 = IterableExtensions.<GenModel>head(_filter_3);
    this.generateGenModel(_head_1, fsa);
  }
  
  public XcoreAppendable createAppendable() {
    XcoreAppendable _xcoreAppendable = new XcoreAppendable();
    return _xcoreAppendable;
  }
  
  public String extractBody(final String body) {
    String _xblockexpression = null;
    {
      String _xifexpression = null;
      boolean _startsWith = body.startsWith("\n");
      if (_startsWith) {
        String _substring = body.substring(1);
        _xifexpression = _substring;
      } else {
        _xifexpression = body;
      }
      String result = _xifexpression;
      String _xifexpression_1 = null;
      boolean _startsWith_1 = result.startsWith("{\n");
      if (_startsWith_1) {
        String _xblockexpression_1 = null;
        {
          String _replace = result.replace("\n\t", "\n");
          result = _replace;
          int _length = result.length();
          int _operator_minus = IntegerExtensions.operator_minus(_length, 2);
          String _substring_1 = result.substring(1, _operator_minus);
          _xblockexpression_1 = (_substring_1);
        }
        _xifexpression_1 = _xblockexpression_1;
      } else {
        _xifexpression_1 = result;
      }
      _xblockexpression = (_xifexpression_1);
    }
    return _xblockexpression;
  }
  
  public Diagnostic generateGenModel(final GenModel genModel, final IFileSystemAccess fsa) {
    Diagnostic _xblockexpression = null;
    {
      genModel.setCanGenerate(true);
      XcoreGeneratorImpl _get = this.xcoreGeneratorImplProvider.get();
      final XcoreGeneratorImpl generator = _get;
      generator.setInput(genModel);
      generator.setFileSystemAccess(fsa);
      String _modelDirectory = genModel.getModelDirectory();
      generator.setModelDirectory(_modelDirectory);
      BasicMonitor _basicMonitor = new BasicMonitor();
      generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, _basicMonitor);
      BasicMonitor _basicMonitor_1 = new BasicMonitor();
      generator.generate(genModel, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, _basicMonitor_1);
      BasicMonitor _basicMonitor_2 = new BasicMonitor();
      generator.generate(genModel, GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, _basicMonitor_2);
      BasicMonitor _basicMonitor_3 = new BasicMonitor();
      Diagnostic _generate = generator.generate(genModel, GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE, _basicMonitor_3);
      _xblockexpression = (_generate);
    }
    return _xblockexpression;
  }
}
