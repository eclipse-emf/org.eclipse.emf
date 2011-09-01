package org.eclipse.emf.ecore.xcore.generator;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.generator.XcoreGeneratorImpl;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.compiler.StringBuilderBasedAppendable;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
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
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    {
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
          StringBuilderBasedAppendable _stringBuilderBasedAppendable = new StringBuilderBasedAppendable();
          final StringBuilderBasedAppendable appendable = _stringBuilderBasedAppendable;
          XOperationMapping _mapping_1 = this.mappings.getMapping(op);
          JvmOperation _jvmOperation = _mapping_1.getJvmOperation();
          JvmDeclaredType _declaringType = _jvmOperation.getDeclaringType();
          appendable.declareVariable(_declaringType, "this");
          XBlockExpression _body = op.getBody();
          this.compiler.compile(_body, appendable, null);
          EList<EAnnotation> _eAnnotations = eOperation.getEAnnotations();
          String _string = appendable.toString();
          EAnnotation _createGenModelAnnotation = this.createGenModelAnnotation("body", _string);
          _eAnnotations.add(_createGenModelAnnotation);
        }
      }
      Iterable<EObject> _allContentsIterable_1 = EObjectExtensions.allContentsIterable(pack);
      Iterable<XStructuralFeature> _filter_1 = IterableExtensions.<XStructuralFeature>filter(_allContentsIterable_1, org.eclipse.emf.ecore.xcore.XStructuralFeature.class);
      for (final XStructuralFeature feature : _filter_1) {
        {
          XFeatureMapping _mapping_2 = this.mappings.getMapping(feature);
          EStructuralFeature _eStructuralFeature = _mapping_2.getEStructuralFeature();
          final EStructuralFeature eStructuralFeature = _eStructuralFeature;
          XFeatureMapping _mapping_3 = this.mappings.getMapping(feature);
          JvmOperation _getter = _mapping_3.getGetter();
          final JvmOperation getter = _getter;
          boolean _operator_notEquals = ObjectExtensions.operator_notEquals(getter, null);
          if (_operator_notEquals) {
            {
              StringBuilderBasedAppendable _stringBuilderBasedAppendable_1 = new StringBuilderBasedAppendable();
              final StringBuilderBasedAppendable appendable_1 = _stringBuilderBasedAppendable_1;
              JvmDeclaredType _declaringType_1 = getter.getDeclaringType();
              appendable_1.declareVariable(_declaringType_1, "this");
              XBlockExpression _getBody = feature.getGetBody();
              this.compiler.compile(_getBody, appendable_1, null);
              EList<EAnnotation> _eAnnotations_1 = eStructuralFeature.getEAnnotations();
              String _string_1 = appendable_1.toString();
              EAnnotation _createGenModelAnnotation_1 = this.createGenModelAnnotation("get", _string_1);
              _eAnnotations_1.add(_createGenModelAnnotation_1);
            }
          }
        }
      }
      EList<EObject> _contents_1 = resource.getContents();
      Iterable<GenModel> _filter_2 = IterableExtensions.<GenModel>filter(_contents_1, org.eclipse.emf.codegen.ecore.genmodel.GenModel.class);
      GenModel _head_1 = IterableExtensions.<GenModel>head(_filter_2);
      this.generateGenModel(_head_1, fsa);
    }
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
  
  public EAnnotation createGenModelAnnotation(final String key, final String value) {
    {
      EAnnotation _createEAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      final EAnnotation result = _createEAnnotation;
      result.setSource(GenModelPackage.eNS_URI);
      EMap<String,String> _details = result.getDetails();
      _details.put(key, value);
      return result;
    }
  }
}