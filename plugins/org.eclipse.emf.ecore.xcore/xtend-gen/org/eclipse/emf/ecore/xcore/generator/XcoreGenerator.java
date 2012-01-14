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
import org.eclipse.emf.ecore.xcore.generator.XcoreAppendable;
import org.eclipse.emf.ecore.xcore.generator.XcoreGeneratorImpl;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xtend2.lib.EObjectExtensions;

@SuppressWarnings("all")
public class XcoreGenerator implements IGenerator {
  @Inject
  private XcoreMapper mappings;
  
  @Inject
  private XbaseCompiler compiler;
  
  @Inject
  private Provider<XcoreGeneratorImpl> xcoreGeneratorImplProvider;
  
  @Inject
  private TypeReferences typeReferences;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
      EList<EObject> _contents = resource.getContents();
      EObject _head = IterableExtensions.<EObject>head(_contents);
      final XPackage pack = ((XPackage) _head);
      Iterable<EObject> _allContentsIterable = EObjectExtensions.allContentsIterable(pack);
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
            {
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
      }
      Iterable<EObject> _allContentsIterable_1 = EObjectExtensions.allContentsIterable(pack);
      Iterable<XStructuralFeature> _filter_1 = IterableExtensions.<XStructuralFeature>filter(_allContentsIterable_1, org.eclipse.emf.ecore.xcore.XStructuralFeature.class);
      for (final XStructuralFeature feature : _filter_1) {
        {
          XFeatureMapping _mapping_2 = this.mappings.getMapping(feature);
          EStructuralFeature _eStructuralFeature = _mapping_2.getEStructuralFeature();
          final EStructuralFeature eStructuralFeature = _eStructuralFeature;
          XBlockExpression _getBody = feature.getGetBody();
          final XBlockExpression getBody = _getBody;
          boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(getBody, null);
          if (_operator_notEquals_1) {
            {
              XFeatureMapping _mapping_3 = this.mappings.getMapping(feature);
              JvmOperation _getter = _mapping_3.getGetter();
              final JvmOperation getter = _getter;
              XcoreAppendable _createAppendable_1 = this.createAppendable();
              final XcoreAppendable appendable_1 = _createAppendable_1;
              JvmDeclaredType _declaringType_1 = getter.getDeclaringType();
              appendable_1.declareVariable(_declaringType_1, "this");
              this.compiler.compile(getBody, appendable_1, null);
              String _string_1 = appendable_1.toString();
              String _extractBody_1 = this.extractBody(_string_1);
              EcoreUtil.setAnnotation(eStructuralFeature, GenModelPackage.eNS_URI, "get", _extractBody_1);
            }
          }
        }
      }
      Iterable<EObject> _allContentsIterable_2 = EObjectExtensions.allContentsIterable(pack);
      Iterable<XDataType> _filter_2 = IterableExtensions.<XDataType>filter(_allContentsIterable_2, org.eclipse.emf.ecore.xcore.XDataType.class);
      for (final XDataType dataType : _filter_2) {
        {
          XDataTypeMapping _mapping_4 = this.mappings.getMapping(dataType);
          EDataType _eDataType = _mapping_4.getEDataType();
          final EDataType eDataType = _eDataType;
          XBlockExpression _createBody = dataType.getCreateBody();
          final XBlockExpression createBody = _createBody;
          XDataTypeMapping _mapping_5 = this.mappings.getMapping(dataType);
          JvmOperation _creator = _mapping_5.getCreator();
          final JvmOperation creator = _creator;
          boolean _operator_and = false;
          boolean _operator_notEquals_2 = ObjectExtensions.operator_notEquals(createBody, null);
          if (!_operator_notEquals_2) {
            _operator_and = false;
          } else {
            boolean _operator_notEquals_3 = ObjectExtensions.operator_notEquals(creator, null);
            _operator_and = BooleanExtensions.operator_and(_operator_notEquals_2, _operator_notEquals_3);
          }
          if (_operator_and) {
            {
              XcoreAppendable _createAppendable_2 = this.createAppendable();
              final XcoreAppendable appendable_2 = _createAppendable_2;
              EList<JvmFormalParameter> _parameters = creator.getParameters();
              JvmFormalParameter _get = _parameters.get(0);
              appendable_2.declareVariable(_get, "it");
              this.compiler.compile(createBody, appendable_2, null);
              String _string_2 = appendable_2.toString();
              String _extractBody_2 = this.extractBody(_string_2);
              EcoreUtil.setAnnotation(eDataType, GenModelPackage.eNS_URI, "create", _extractBody_2);
            }
          }
          XBlockExpression _convertBody = dataType.getConvertBody();
          final XBlockExpression convertBody = _convertBody;
          XDataTypeMapping _mapping_6 = this.mappings.getMapping(dataType);
          JvmOperation _converter = _mapping_6.getConverter();
          final JvmOperation converter = _converter;
          boolean _operator_and_1 = false;
          boolean _operator_notEquals_4 = ObjectExtensions.operator_notEquals(convertBody, null);
          if (!_operator_notEquals_4) {
            _operator_and_1 = false;
          } else {
            boolean _operator_notEquals_5 = ObjectExtensions.operator_notEquals(converter, null);
            _operator_and_1 = BooleanExtensions.operator_and(_operator_notEquals_4, _operator_notEquals_5);
          }
          if (_operator_and_1) {
            {
              XcoreAppendable _createAppendable_3 = this.createAppendable();
              final XcoreAppendable appendable_3 = _createAppendable_3;
              EList<JvmFormalParameter> _parameters_1 = converter.getParameters();
              JvmFormalParameter _get_1 = _parameters_1.get(0);
              appendable_3.declareVariable(_get_1, "it");
              this.compiler.compile(convertBody, appendable_3, null);
              String _string_3 = appendable_3.toString();
              String _extractBody_3 = this.extractBody(_string_3);
              EcoreUtil.setAnnotation(eDataType, GenModelPackage.eNS_URI, "convert", _extractBody_3);
            }
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
      BasicMonitor _basicMonitor = new BasicMonitor();
      Diagnostic _generate = generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, _basicMonitor);
      _xblockexpression = (_generate);
    }
    return _xblockexpression;
  }
}
