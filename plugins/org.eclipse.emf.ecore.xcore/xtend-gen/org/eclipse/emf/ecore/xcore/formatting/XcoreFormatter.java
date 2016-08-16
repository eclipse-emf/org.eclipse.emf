/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.formatting;

import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAttribute;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnum;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegionFinder;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegionsFinder;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XBasicForLoopExpression;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XCollectionLiteral;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XDoWhileExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XForLoopExpression;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XPostfixOperation;
import org.eclipse.xtext.xbase.XReturnExpression;
import org.eclipse.xtext.xbase.XSwitchExpression;
import org.eclipse.xtext.xbase.XSynchronizedExpression;
import org.eclipse.xtext.xbase.XThrowExpression;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XWhileExpression;
import org.eclipse.xtext.xbase.formatting2.XbaseFormatter;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xtype.XFunctionTypeRef;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XImportSection;

@SuppressWarnings("all")
public class XcoreFormatter extends XbaseFormatter {
  protected void _format(final XAnnotation xAnnotation, @Extension final IFormattableDocument format) {
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xAnnotation);
    ISemanticRegion _keyword = _regionFor.keyword("@");
    final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.noSpace();
      }
    };
    format.append(_keyword, _function);
    ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(xAnnotation);
    List<Pair<ISemanticRegion, ISemanticRegion>> _keywordPairs = _regionFor_1.keywordPairs("(", ")");
    Pair<ISemanticRegion, ISemanticRegion> _head = IterableExtensions.<Pair<ISemanticRegion, ISemanticRegion>>head(_keywordPairs);
    final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.indent();
      }
    };
    format.<ISemanticRegion, ISemanticRegion>interior(_head, _function_1);
    ISemanticRegionsFinder _regionFor_2 = this.textRegionExtensions.regionFor(xAnnotation);
    ISemanticRegion _keyword_1 = _regionFor_2.keyword("(");
    final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.noSpace();
      }
    };
    ISemanticRegion _prepend = format.prepend(_keyword_1, _function_2);
    final Procedure1<IHiddenRegionFormatter> _function_3 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.noSpace();
      }
    };
    format.append(_prepend, _function_3);
    ISemanticRegionsFinder _regionFor_3 = this.textRegionExtensions.regionFor(xAnnotation);
    ISemanticRegion _keyword_2 = _regionFor_3.keyword(")");
    final Procedure1<IHiddenRegionFormatter> _function_4 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.noSpace();
      }
    };
    ISemanticRegion _prepend_1 = format.prepend(_keyword_2, _function_4);
    final Procedure1<IHiddenRegionFormatter> _function_5 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.newLine();
      }
    };
    format.append(_prepend_1, _function_5);
    EMap<String, String> _details = xAnnotation.getDetails();
    for (final Map.Entry<String, String> entry : _details) {
      {
        final EObject detail = ((EObject) entry);
        ISemanticRegionsFinder _regionFor_4 = this.textRegionExtensions.regionFor(detail);
        ISemanticRegion _keyword_3 = _regionFor_4.keyword("=");
        final Procedure1<IHiddenRegionFormatter> _function_6 = new Procedure1<IHiddenRegionFormatter>() {
          @Override
          public void apply(final IHiddenRegionFormatter it) {
            it.noSpace();
          }
        };
        ISemanticRegion _prepend_2 = format.prepend(_keyword_3, _function_6);
        final Procedure1<IHiddenRegionFormatter> _function_7 = new Procedure1<IHiddenRegionFormatter>() {
          @Override
          public void apply(final IHiddenRegionFormatter it) {
            it.noSpace();
          }
        };
        format.append(_prepend_2, _function_7);
        ISemanticRegionFinder _immediatelyFollowing = this.textRegionExtensions.immediatelyFollowing(detail);
        final ISemanticRegion comma = _immediatelyFollowing.keyword(",");
        boolean _notEquals = (!Objects.equal(comma, null));
        if (_notEquals) {
          final Procedure1<IHiddenRegionFormatter> _function_8 = new Procedure1<IHiddenRegionFormatter>() {
            @Override
            public void apply(final IHiddenRegionFormatter it) {
              it.noSpace();
            }
          };
          ISemanticRegion _prepend_3 = format.prepend(comma, _function_8);
          final Procedure1<IHiddenRegionFormatter> _function_9 = new Procedure1<IHiddenRegionFormatter>() {
            @Override
            public void apply(final IHiddenRegionFormatter it) {
              it.oneSpace();
            }
          };
          ISemanticRegion _append = format.append(_prepend_3, _function_9);
          final Procedure1<IHiddenRegionFormatter> _function_10 = new Procedure1<IHiddenRegionFormatter>() {
            @Override
            public void apply(final IHiddenRegionFormatter it) {
              it.autowrap();
            }
          };
          format.append(_append, _function_10);
        }
      }
    }
  }
  
  protected void _format(final XPackage xPackage, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xPackage.getAnnotations();
    this.formatAnnotations(_annotations, format);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xPackage);
    ISemanticRegion _keyword = _regionFor.keyword("package");
    final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.noSpace();
      }
    };
    format.prepend(_keyword, _function);
    ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(xPackage);
    ISemanticRegion _feature = _regionFor_1.feature(XcorePackage.Literals.XNAMED_ELEMENT__NAME);
    final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.oneSpace();
      }
    };
    format.prepend(_feature, _function_1);
    final EList<XImportDirective> xImportDirectives = xPackage.getImportDirectives();
    boolean _isEmpty = xImportDirectives.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      final XImportDirective first = IterableExtensions.<XImportDirective>head(xImportDirectives);
      final XImportDirective last = IterableExtensions.<XImportDirective>last(xImportDirectives);
      for (final XImportDirective xImportDirective : xImportDirectives) {
        {
          ISemanticRegionsFinder _regionFor_2 = this.textRegionExtensions.regionFor(xImportDirective);
          ISemanticRegion _keyword_1 = _regionFor_2.keyword("import");
          final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
            @Override
            public void apply(final IHiddenRegionFormatter it) {
              int _xifexpression = (int) 0;
              boolean _equals = Objects.equal(xImportDirective, first);
              if (_equals) {
                _xifexpression = 2;
              } else {
                _xifexpression = 1;
              }
              it.setNewLines(_xifexpression);
            }
          };
          format.prepend(_keyword_1, _function_2);
          this.format(xImportDirective, format);
          boolean _equals = Objects.equal(xImportDirective, last);
          if (_equals) {
            IEObjectRegion _regionForEObject = this.textRegionExtensions.regionForEObject(xImportDirective);
            Iterable<ISemanticRegion> _allSemanticRegions = _regionForEObject.getAllSemanticRegions();
            ISemanticRegion _last = IterableExtensions.<ISemanticRegion>last(_allSemanticRegions);
            final Procedure1<IHiddenRegionFormatter> _function_3 = new Procedure1<IHiddenRegionFormatter>() {
              @Override
              public void apply(final IHiddenRegionFormatter it) {
                it.setNewLines(2);
              }
            };
            format.append(_last, _function_3);
          }
        }
      }
    } else {
      ISemanticRegionsFinder _regionFor_2 = this.textRegionExtensions.regionFor(xPackage);
      ISemanticRegion _feature_1 = _regionFor_2.feature(XcorePackage.Literals.XNAMED_ELEMENT__NAME);
      final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.setNewLines(2);
        }
      };
      format.append(_feature_1, _function_2);
    }
    final EList<XClassifier> xClassifiers = xPackage.getClassifiers();
    for (final XClassifier xClassifier : xClassifiers) {
      {
        this.format(xClassifier, format);
        IEObjectRegion _regionForEObject = this.textRegionExtensions.regionForEObject(xClassifier);
        Iterable<ISemanticRegion> _allSemanticRegions = _regionForEObject.getAllSemanticRegions();
        ISemanticRegion _head = IterableExtensions.<ISemanticRegion>head(_allSemanticRegions);
        final Procedure1<IHiddenRegionFormatter> _function_3 = new Procedure1<IHiddenRegionFormatter>() {
          @Override
          public void apply(final IHiddenRegionFormatter it) {
            it.setNewLines(2);
          }
        };
        format.prepend(_head, _function_3);
      }
    }
  }
  
  protected void _format(final XEnum xEnum, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xEnum.getAnnotations();
    this.formatAnnotations(_annotations, format);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xEnum);
    List<Pair<ISemanticRegion, ISemanticRegion>> _keywordPairs = _regionFor.keywordPairs("{", "}");
    Pair<ISemanticRegion, ISemanticRegion> _head = IterableExtensions.<Pair<ISemanticRegion, ISemanticRegion>>head(_keywordPairs);
    final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.indent();
      }
    };
    format.<ISemanticRegion, ISemanticRegion>interior(_head, _function);
    ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(xEnum);
    ISemanticRegion _keyword = _regionFor_1.keyword("{");
    final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.newLine();
      }
    };
    format.append(_keyword, _function_1);
    EList<XEnumLiteral> _literals = xEnum.getLiterals();
    for (final XEnumLiteral xEnumLiteral : _literals) {
      {
        this.format(xEnumLiteral, format);
        IEObjectRegion _regionForEObject = this.textRegionExtensions.regionForEObject(xEnumLiteral);
        Iterable<ISemanticRegion> _allSemanticRegions = _regionForEObject.getAllSemanticRegions();
        ISemanticRegion _last = IterableExtensions.<ISemanticRegion>last(_allSemanticRegions);
        final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
          @Override
          public void apply(final IHiddenRegionFormatter it) {
            it.newLine();
          }
        };
        format.append(_last, _function_2);
      }
    }
  }
  
  protected void _format(final XEnumLiteral xEnumLiteral, final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xEnumLiteral.getAnnotations();
    this.formatAnnotations(_annotations, format);
  }
  
  protected void _format(final XDataType xDataType, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xDataType.getAnnotations();
    this.formatAnnotations(_annotations, format);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xDataType);
    final ISemanticRegion leftAngleBracket = _regionFor.keyword("<");
    boolean _notEquals = (!Objects.equal(leftAngleBracket, null));
    if (_notEquals) {
      final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      ISemanticRegion _prepend = format.prepend(leftAngleBracket, _function);
      final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.append(_prepend, _function_1);
      EList<XTypeParameter> _typeParameters = xDataType.getTypeParameters();
      this.formatTypeParameters(_typeParameters, format);
      ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(xDataType);
      ISemanticRegion _keyword = _regionFor_1.keyword(">");
      final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      ISemanticRegion _prepend_1 = format.prepend(_keyword, _function_2);
      final Procedure1<IHiddenRegionFormatter> _function_3 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      format.append(_prepend_1, _function_3);
    }
  }
  
  protected void _format(final XClass xClass, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xClass.getAnnotations();
    this.formatAnnotations(_annotations, format);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xClass);
    final ISemanticRegion leftAngleBracket = _regionFor.keyword("<");
    boolean _notEquals = (!Objects.equal(leftAngleBracket, null));
    if (_notEquals) {
      final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      ISemanticRegion _prepend = format.prepend(leftAngleBracket, _function);
      final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.append(_prepend, _function_1);
      EList<XTypeParameter> _typeParameters = xClass.getTypeParameters();
      this.formatTypeParameters(_typeParameters, format);
      ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(xClass);
      ISemanticRegion _keyword = _regionFor_1.keyword(">");
      final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      ISemanticRegion _prepend_1 = format.prepend(_keyword, _function_2);
      final Procedure1<IHiddenRegionFormatter> _function_3 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      format.append(_prepend_1, _function_3);
    }
    ISemanticRegionsFinder _regionFor_2 = this.textRegionExtensions.regionFor(xClass);
    List<Pair<ISemanticRegion, ISemanticRegion>> _keywordPairs = _regionFor_2.keywordPairs("{", "}");
    Pair<ISemanticRegion, ISemanticRegion> _head = IterableExtensions.<Pair<ISemanticRegion, ISemanticRegion>>head(_keywordPairs);
    final Procedure1<IHiddenRegionFormatter> _function_4 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.indent();
      }
    };
    format.<ISemanticRegion, ISemanticRegion>interior(_head, _function_4);
    ISemanticRegionsFinder _regionFor_3 = this.textRegionExtensions.regionFor(xClass);
    ISemanticRegion _keyword_1 = _regionFor_3.keyword("{");
    final Procedure1<IHiddenRegionFormatter> _function_5 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.newLine();
      }
    };
    format.append(_keyword_1, _function_5);
    EList<XMember> _members = xClass.getMembers();
    for (final XMember xMember : _members) {
      {
        this.format(xMember, format);
        IEObjectRegion _regionForEObject = this.textRegionExtensions.regionForEObject(xMember);
        Iterable<ISemanticRegion> _allSemanticRegions = _regionForEObject.getAllSemanticRegions();
        ISemanticRegion _last = IterableExtensions.<ISemanticRegion>last(_allSemanticRegions);
        final Procedure1<IHiddenRegionFormatter> _function_6 = new Procedure1<IHiddenRegionFormatter>() {
          @Override
          public void apply(final IHiddenRegionFormatter it) {
            it.newLine();
          }
        };
        format.append(_last, _function_6);
      }
    }
  }
  
  protected void _format(final XReference xReference, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xReference.getAnnotations();
    this.formatAnnotations(_annotations, format);
    XGenericType _type = xReference.getType();
    format.<XGenericType>format(_type);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xReference);
    final ISemanticRegion multiplicity = _regionFor.feature(XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    boolean _notEquals = (!Objects.equal(multiplicity, null));
    if (_notEquals) {
      final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.prepend(multiplicity, _function);
    }
    final XBlockExpression get = xReference.getGetBody();
    boolean _notEquals_1 = (!Objects.equal(get, null));
    if (_notEquals_1) {
      ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(get);
      ISemanticRegion _keyword = _regionFor_1.keyword("{");
      final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      format.prepend(_keyword, _function_1);
      this.format(get, format);
    }
  }
  
  protected void _format(final XAttribute xAttribute, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xAttribute.getAnnotations();
    this.formatAnnotations(_annotations, format);
    XGenericType _type = xAttribute.getType();
    format.<XGenericType>format(_type);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xAttribute);
    final ISemanticRegion multiplicity = _regionFor.feature(XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    boolean _notEquals = (!Objects.equal(multiplicity, null));
    if (_notEquals) {
      final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.prepend(multiplicity, _function);
    }
    final XBlockExpression get = xAttribute.getGetBody();
    boolean _notEquals_1 = (!Objects.equal(get, null));
    if (_notEquals_1) {
      ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(get);
      ISemanticRegion _keyword = _regionFor_1.keyword("{");
      final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      format.prepend(_keyword, _function_1);
      this.format(get, format);
    }
  }
  
  protected void _format(final XOperation xOperation, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xOperation.getAnnotations();
    this.formatAnnotations(_annotations, format);
    XGenericType _type = xOperation.getType();
    format.<XGenericType>format(_type);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xOperation);
    final ISemanticRegion multiplicity = _regionFor.feature(XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    boolean _notEquals = (!Objects.equal(multiplicity, null));
    if (_notEquals) {
      final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.prepend(multiplicity, _function);
    }
    ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(xOperation);
    final ISemanticRegion leftAngleBracket = _regionFor_1.keyword("<");
    boolean _notEquals_1 = (!Objects.equal(leftAngleBracket, null));
    if (_notEquals_1) {
      final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      ISemanticRegion _prepend = format.prepend(leftAngleBracket, _function_1);
      final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.append(_prepend, _function_2);
      EList<XTypeParameter> _typeParameters = xOperation.getTypeParameters();
      this.formatTypeParameters(_typeParameters, format);
      ISemanticRegionsFinder _regionFor_2 = this.textRegionExtensions.regionFor(xOperation);
      ISemanticRegion _keyword = _regionFor_2.keyword(">");
      final Procedure1<IHiddenRegionFormatter> _function_3 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      ISemanticRegion _prepend_1 = format.prepend(_keyword, _function_3);
      final Procedure1<IHiddenRegionFormatter> _function_4 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      format.append(_prepend_1, _function_4);
    }
    ISemanticRegionsFinder _regionFor_3 = this.textRegionExtensions.regionFor(xOperation);
    ISemanticRegion _keyword_1 = _regionFor_3.keyword("(");
    final Procedure1<IHiddenRegionFormatter> _function_5 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.noSpace();
      }
    };
    ISemanticRegion _prepend_2 = format.prepend(_keyword_1, _function_5);
    final Procedure1<IHiddenRegionFormatter> _function_6 = new Procedure1<IHiddenRegionFormatter>() {
      @Override
      public void apply(final IHiddenRegionFormatter it) {
        it.noSpace();
      }
    };
    format.append(_prepend_2, _function_6);
    final EList<XParameter> xParameters = xOperation.getParameters();
    boolean _isEmpty = xParameters.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      ISemanticRegionsFinder _regionFor_4 = this.textRegionExtensions.regionFor(xOperation);
      ISemanticRegion _keyword_2 = _regionFor_4.keyword(")");
      final Procedure1<IHiddenRegionFormatter> _function_7 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.prepend(_keyword_2, _function_7);
      for (final XParameter xParameter : xParameters) {
        this.format(xParameter, format);
      }
    }
    final XBlockExpression body = xOperation.getBody();
    boolean _notEquals_2 = (!Objects.equal(body, null));
    if (_notEquals_2) {
      ISemanticRegionsFinder _regionFor_5 = this.textRegionExtensions.regionFor(body);
      ISemanticRegion _keyword_3 = _regionFor_5.keyword("{");
      final Procedure1<IHiddenRegionFormatter> _function_8 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      format.prepend(_keyword_3, _function_8);
      this.format(body, format);
    }
  }
  
  protected void _format(final XParameter xParameter, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xParameter.getAnnotations();
    this.formatAnnotations(_annotations, format);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xParameter);
    final ISemanticRegion multiplicity = _regionFor.feature(XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    boolean _notEquals = (!Objects.equal(multiplicity, null));
    if (_notEquals) {
      final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.prepend(multiplicity, _function);
    }
    XGenericType _type = xParameter.getType();
    format.<XGenericType>format(_type);
  }
  
  protected void _format(final XGenericType xGenericType, @Extension final IFormattableDocument format) {
    GenBase _type = xGenericType.getType();
    format.<GenBase>format(_type);
    ISemanticRegionsFinder _regionFor = this.textRegionExtensions.regionFor(xGenericType);
    final ISemanticRegion leftAngleBracket = _regionFor.keyword("<");
    boolean _notEquals = (!Objects.equal(leftAngleBracket, null));
    if (_notEquals) {
      final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      ISemanticRegion _prepend = format.prepend(leftAngleBracket, _function);
      final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.append(_prepend, _function_1);
      EList<XGenericType> _typeArguments = xGenericType.getTypeArguments();
      for (final XGenericType typeArgument : _typeArguments) {
        {
          format.<XGenericType>format(typeArgument);
          ISemanticRegionFinder _immediatelyFollowing = this.textRegionExtensions.immediatelyFollowing(typeArgument);
          final ISemanticRegion comma = _immediatelyFollowing.keyword(",");
          boolean _notEquals_1 = (!Objects.equal(comma, null));
          if (_notEquals_1) {
            final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
              @Override
              public void apply(final IHiddenRegionFormatter it) {
                it.noSpace();
              }
            };
            ISemanticRegion _prepend_1 = format.prepend(comma, _function_2);
            final Procedure1<IHiddenRegionFormatter> _function_3 = new Procedure1<IHiddenRegionFormatter>() {
              @Override
              public void apply(final IHiddenRegionFormatter it) {
                it.oneSpace();
              }
            };
            format.append(_prepend_1, _function_3);
          }
        }
      }
      ISemanticRegionsFinder _regionFor_1 = this.textRegionExtensions.regionFor(xGenericType);
      ISemanticRegion _keyword = _regionFor_1.keyword(">");
      final Procedure1<IHiddenRegionFormatter> _function_2 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      ISemanticRegion _prepend_1 = format.prepend(_keyword, _function_2);
      final Procedure1<IHiddenRegionFormatter> _function_3 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.noSpace();
        }
      };
      format.append(_prepend_1, _function_3);
    }
    final XGenericType upperBound = xGenericType.getUpperBound();
    boolean _notEquals_1 = (!Objects.equal(upperBound, null));
    if (_notEquals_1) {
      format.<XGenericType>format(upperBound);
      ISemanticRegionsFinder _regionFor_2 = this.textRegionExtensions.regionFor(xGenericType);
      ISemanticRegion _keyword_1 = _regionFor_2.keyword("extends");
      final Procedure1<IHiddenRegionFormatter> _function_4 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      ISemanticRegion _prepend_2 = format.prepend(_keyword_1, _function_4);
      final Procedure1<IHiddenRegionFormatter> _function_5 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      format.append(_prepend_2, _function_5);
    }
    final XGenericType lowerBound = xGenericType.getLowerBound();
    boolean _notEquals_2 = (!Objects.equal(lowerBound, null));
    if (_notEquals_2) {
      format.<XGenericType>format(lowerBound);
      ISemanticRegionsFinder _regionFor_3 = this.textRegionExtensions.regionFor(xGenericType);
      ISemanticRegion _keyword_2 = _regionFor_3.keyword("super");
      final Procedure1<IHiddenRegionFormatter> _function_6 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      ISemanticRegion _prepend_3 = format.prepend(_keyword_2, _function_6);
      final Procedure1<IHiddenRegionFormatter> _function_7 = new Procedure1<IHiddenRegionFormatter>() {
        @Override
        public void apply(final IHiddenRegionFormatter it) {
          it.oneSpace();
        }
      };
      format.append(_prepend_3, _function_7);
    }
  }
  
  protected void _format(final XTypeParameter xTypeParameter, @Extension final IFormattableDocument format) {
    EList<XAnnotation> _annotations = xTypeParameter.getAnnotations();
    this.formatAnnotations(_annotations, format);
    EList<XGenericType> _bounds = xTypeParameter.getBounds();
    for (final XGenericType bound : _bounds) {
      {
        format.<XGenericType>format(bound);
        ISemanticRegionFinder _immediatelyFollowing = this.textRegionExtensions.immediatelyFollowing(bound);
        final ISemanticRegion ampersand = _immediatelyFollowing.keyword("&");
        boolean _notEquals = (!Objects.equal(ampersand, null));
        if (_notEquals) {
          final Procedure1<IHiddenRegionFormatter> _function = new Procedure1<IHiddenRegionFormatter>() {
            @Override
            public void apply(final IHiddenRegionFormatter it) {
              it.oneSpace();
            }
          };
          ISemanticRegion _prepend = format.prepend(ampersand, _function);
          final Procedure1<IHiddenRegionFormatter> _function_1 = new Procedure1<IHiddenRegionFormatter>() {
            @Override
            public void apply(final IHiddenRegionFormatter it) {
              it.oneSpace();
            }
          };
          format.append(_prepend, _function_1);
        }
      }
    }
  }
  
  protected void formatAnnotations(final List<XAnnotation> xAnnotations, final IFormattableDocument format) {
    for (final XAnnotation xAnnotation : xAnnotations) {
      this.format(xAnnotation, format);
    }
  }
  
  protected void formatTypeParameters(final List<XTypeParameter> xTypeParameters, final IFormattableDocument format) {
    for (final XTypeParameter xTypeParameter : xTypeParameters) {
      this.format(xTypeParameter, format);
    }
  }
  
  public void format(final Object xAttribute, final IFormattableDocument format) {
    if (xAttribute instanceof XAttribute) {
      _format((XAttribute)xAttribute, format);
      return;
    } else if (xAttribute instanceof XReference) {
      _format((XReference)xAttribute, format);
      return;
    } else if (xAttribute instanceof XEnum) {
      _format((XEnum)xAttribute, format);
      return;
    } else if (xAttribute instanceof XOperation) {
      _format((XOperation)xAttribute, format);
      return;
    } else if (xAttribute instanceof XClass) {
      _format((XClass)xAttribute, format);
      return;
    } else if (xAttribute instanceof XDataType) {
      _format((XDataType)xAttribute, format);
      return;
    } else if (xAttribute instanceof XParameter) {
      _format((XParameter)xAttribute, format);
      return;
    } else if (xAttribute instanceof JvmTypeParameter) {
      _format((JvmTypeParameter)xAttribute, format);
      return;
    } else if (xAttribute instanceof XEnumLiteral) {
      _format((XEnumLiteral)xAttribute, format);
      return;
    } else if (xAttribute instanceof XPackage) {
      _format((XPackage)xAttribute, format);
      return;
    } else if (xAttribute instanceof XTypeParameter) {
      _format((XTypeParameter)xAttribute, format);
      return;
    } else if (xAttribute instanceof JvmFormalParameter) {
      _format((JvmFormalParameter)xAttribute, format);
      return;
    } else if (xAttribute instanceof XtextResource) {
      _format((XtextResource)xAttribute, format);
      return;
    } else if (xAttribute instanceof XAssignment) {
      _format((XAssignment)xAttribute, format);
      return;
    } else if (xAttribute instanceof XBinaryOperation) {
      _format((XBinaryOperation)xAttribute, format);
      return;
    } else if (xAttribute instanceof XDoWhileExpression) {
      _format((XDoWhileExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XFeatureCall) {
      _format((XFeatureCall)xAttribute, format);
      return;
    } else if (xAttribute instanceof XMemberFeatureCall) {
      _format((XMemberFeatureCall)xAttribute, format);
      return;
    } else if (xAttribute instanceof XPostfixOperation) {
      _format((XPostfixOperation)xAttribute, format);
      return;
    } else if (xAttribute instanceof XWhileExpression) {
      _format((XWhileExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XFunctionTypeRef) {
      _format((XFunctionTypeRef)xAttribute, format);
      return;
    } else if (xAttribute instanceof XAnnotation) {
      _format((XAnnotation)xAttribute, format);
      return;
    } else if (xAttribute instanceof JvmGenericArrayTypeReference) {
      _format((JvmGenericArrayTypeReference)xAttribute, format);
      return;
    } else if (xAttribute instanceof JvmParameterizedTypeReference) {
      _format((JvmParameterizedTypeReference)xAttribute, format);
      return;
    } else if (xAttribute instanceof JvmWildcardTypeReference) {
      _format((JvmWildcardTypeReference)xAttribute, format);
      return;
    } else if (xAttribute instanceof XBasicForLoopExpression) {
      _format((XBasicForLoopExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XBlockExpression) {
      _format((XBlockExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XCastedExpression) {
      _format((XCastedExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XClosure) {
      _format((XClosure)xAttribute, format);
      return;
    } else if (xAttribute instanceof XCollectionLiteral) {
      _format((XCollectionLiteral)xAttribute, format);
      return;
    } else if (xAttribute instanceof XConstructorCall) {
      _format((XConstructorCall)xAttribute, format);
      return;
    } else if (xAttribute instanceof XForLoopExpression) {
      _format((XForLoopExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XIfExpression) {
      _format((XIfExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XInstanceOfExpression) {
      _format((XInstanceOfExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XReturnExpression) {
      _format((XReturnExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XSwitchExpression) {
      _format((XSwitchExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XSynchronizedExpression) {
      _format((XSynchronizedExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XThrowExpression) {
      _format((XThrowExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XTryCatchFinallyExpression) {
      _format((XTryCatchFinallyExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XTypeLiteral) {
      _format((XTypeLiteral)xAttribute, format);
      return;
    } else if (xAttribute instanceof XVariableDeclaration) {
      _format((XVariableDeclaration)xAttribute, format);
      return;
    } else if (xAttribute instanceof XGenericType) {
      _format((XGenericType)xAttribute, format);
      return;
    } else if (xAttribute instanceof JvmTypeConstraint) {
      _format((JvmTypeConstraint)xAttribute, format);
      return;
    } else if (xAttribute instanceof XExpression) {
      _format((XExpression)xAttribute, format);
      return;
    } else if (xAttribute instanceof XImportDeclaration) {
      _format((XImportDeclaration)xAttribute, format);
      return;
    } else if (xAttribute instanceof XImportSection) {
      _format((XImportSection)xAttribute, format);
      return;
    } else if (xAttribute instanceof EObject) {
      _format((EObject)xAttribute, format);
      return;
    } else if (xAttribute == null) {
      _format((Void)null, format);
      return;
    } else if (xAttribute != null) {
      _format(xAttribute, format);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(xAttribute, format).toString());
    }
  }
}
