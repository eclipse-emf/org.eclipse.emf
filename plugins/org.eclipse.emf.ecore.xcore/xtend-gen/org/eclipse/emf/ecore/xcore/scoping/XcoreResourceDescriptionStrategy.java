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
      final EPackage ePackage = it.eClass().getEPackage();
      boolean _matched = false;
      if (Objects.equal(ePackage, EcorePackage.eINSTANCE)) {
        _matched=true;
        this.ecoreResourceDescriptionStrategy.createEObjectDescriptions(it, acceptor);
        boolean _matched_1 = false;
        if (it instanceof EPackage) {
          _matched_1=true;
          final Consumer<EClassifier> _function = (EClassifier it_1) -> {
            this.createEObjectDescriptions(it_1, acceptor);
          };
          ((EPackage)it).getEClassifiers().forEach(_function);
        }
        if (!_matched_1) {
          if (it instanceof EClass) {
            _matched_1=true;
            EList<EOperation> _eOperations = ((EClass)it).getEOperations();
            EList<EStructuralFeature> _eStructuralFeatures = ((EClass)it).getEStructuralFeatures();
            final Consumer<ETypedElement> _function = (ETypedElement it_1) -> {
              this.createEObjectDescriptions(it_1, acceptor);
            };
            Iterables.<ETypedElement>concat(_eOperations, _eStructuralFeatures).forEach(_function);
          }
        }
      }
      if (!_matched) {
        if (Objects.equal(ePackage, GenModelPackage.eINSTANCE)) {
          _matched=true;
          boolean _matched_2 = false;
          if (it instanceof GenModel) {
            _matched_2=true;
            final Consumer<GenPackage> _function = (GenPackage it_1) -> {
              this.createEObjectDescriptions(it_1, acceptor);
            };
            ((GenModel)it).getGenPackages().forEach(_function);
          }
          if (!_matched_2) {
            if (it instanceof GenPackage) {
              _matched_2=true;
              final Consumer<GenClassifier> _function = (GenClassifier it_1) -> {
                this.createEObjectDescriptions(it_1, acceptor);
              };
              ((GenPackage)it).getGenClassifiers().forEach(_function);
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
            final Consumer<XAnnotationDirective> _function = (XAnnotationDirective it_1) -> {
              this.createEObjectDescriptions(it_1, acceptor);
            };
            ((XPackage)it).getAnnotationDirectives().forEach(_function);
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
            final Consumer<JvmDeclaredType> _function = (JvmDeclaredType it_1) -> {
              this.createEObjectDescriptions(it_1, acceptor);
            };
            Iterables.<JvmDeclaredType>filter(((JvmDeclaredType)it).getMembers(), JvmDeclaredType.class).forEach(_function);
          }
        }
      }
      _xblockexpression = false;
    }
    return _xblockexpression;
  }
}
