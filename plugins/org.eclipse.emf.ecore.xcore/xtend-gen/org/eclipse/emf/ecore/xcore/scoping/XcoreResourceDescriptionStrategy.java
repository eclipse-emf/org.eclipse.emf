package org.eclipse.emf.ecore.xcore.scoping;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.function.Consumer;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.ecore.EcoreResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescriptionStrategy;

@Singleton
@SuppressWarnings("all")
public class XcoreResourceDescriptionStrategy extends XbaseResourceDescriptionStrategy {
  @Inject
  private EcoreResourceDescriptionStrategy ecoreResourceDescriptionStrategy;
  
  @Override
  public boolean createEObjectDescriptions(final EObject it, final IAcceptor<IEObjectDescription> acceptor) {
    boolean _xblockexpression = false;
    {
      EClass _eClass = it.eClass();
      final EPackage ePackage = _eClass.getEPackage();
      boolean _matched = false;
      if (Objects.equal(ePackage, EcorePackage.eINSTANCE)) {
        _matched=true;
        this.ecoreResourceDescriptionStrategy.createEObjectDescriptions(it, acceptor);
        boolean _matched_1 = false;
        if (it instanceof EPackage) {
          _matched_1=true;
          EList<EClassifier> _eClassifiers = ((EPackage)it).getEClassifiers();
          final Consumer<EClassifier> _function = new Consumer<EClassifier>() {
            @Override
            public void accept(final EClassifier it) {
              XcoreResourceDescriptionStrategy.this.createEObjectDescriptions(it, acceptor);
            }
          };
          _eClassifiers.forEach(_function);
        }
        if (!_matched_1) {
          if (it instanceof EClass) {
            _matched_1=true;
            EList<EOperation> _eOperations = ((EClass)it).getEOperations();
            EList<EStructuralFeature> _eStructuralFeatures = ((EClass)it).getEStructuralFeatures();
            Iterable<ETypedElement> _plus = Iterables.<ETypedElement>concat(_eOperations, _eStructuralFeatures);
            final Consumer<ETypedElement> _function = new Consumer<ETypedElement>() {
              @Override
              public void accept(final ETypedElement it) {
                XcoreResourceDescriptionStrategy.this.createEObjectDescriptions(it, acceptor);
              }
            };
            _plus.forEach(_function);
          }
        }
      }
      if (!_matched) {
        if (Objects.equal(ePackage, GenModelPackage.eINSTANCE)) {
          _matched=true;
          boolean _matched_2 = false;
          if (it instanceof GenModel) {
            _matched_2=true;
            EList<GenPackage> _genPackages = ((GenModel)it).getGenPackages();
            final Consumer<GenPackage> _function = new Consumer<GenPackage>() {
              @Override
              public void accept(final GenPackage it) {
                XcoreResourceDescriptionStrategy.this.createEObjectDescriptions(it, acceptor);
              }
            };
            _genPackages.forEach(_function);
          }
          if (!_matched_2) {
            if (it instanceof GenPackage) {
              _matched_2=true;
              EList<GenClassifier> _genClassifiers = ((GenPackage)it).getGenClassifiers();
              final Consumer<GenClassifier> _function = new Consumer<GenClassifier>() {
                @Override
                public void accept(final GenClassifier it) {
                  XcoreResourceDescriptionStrategy.this.createEObjectDescriptions(it, acceptor);
                }
              };
              _genClassifiers.forEach(_function);
            }
          }
          if (!_matched_2) {
            if (it instanceof GenClassifier) {
              _matched_2=true;
              super.createEObjectDescriptions(it, acceptor);
            }
          }
        }
      }
      if (!_matched) {
        if (Objects.equal(ePackage, XcorePackage.eINSTANCE)) {
          _matched=true;
          boolean _matched_3 = false;
          if (it instanceof XPackage) {
            _matched_3=true;
            EList<XAnnotationDirective> _annotationDirectives = ((XPackage)it).getAnnotationDirectives();
            final Consumer<XAnnotationDirective> _function = new Consumer<XAnnotationDirective>() {
              @Override
              public void accept(final XAnnotationDirective it) {
                XcoreResourceDescriptionStrategy.this.createEObjectDescriptions(it, acceptor);
              }
            };
            _annotationDirectives.forEach(_function);
          }
          if (!_matched_3) {
            if (it instanceof XAnnotationDirective) {
              _matched_3=true;
              super.createEObjectDescriptions(it, acceptor);
            }
          }
        }
      }
      if (!_matched) {
        if (Objects.equal(ePackage, TypesPackage.eINSTANCE)) {
          _matched=true;
          if ((it instanceof JvmDeclaredType)) {
            super.createEObjectDescriptions(it, acceptor);
            EList<JvmMember> _members = ((JvmDeclaredType)it).getMembers();
            Iterable<JvmDeclaredType> _filter = Iterables.<JvmDeclaredType>filter(_members, JvmDeclaredType.class);
            final Consumer<JvmDeclaredType> _function = new Consumer<JvmDeclaredType>() {
              @Override
              public void accept(final JvmDeclaredType it) {
                XcoreResourceDescriptionStrategy.this.createEObjectDescriptions(it, acceptor);
              }
            };
            _filter.forEach(_function);
          }
        }
      }
      _xblockexpression = false;
    }
    return _xblockexpression;
  }
}
