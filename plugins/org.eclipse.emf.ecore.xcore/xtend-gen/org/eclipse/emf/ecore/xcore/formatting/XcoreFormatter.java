/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.formatting;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XBasicForLoopExpression;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XCatchClause;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XCollectionLiteral;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XDoWhileExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XForLoopExpression;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XReturnExpression;
import org.eclipse.xtext.xbase.XSwitchExpression;
import org.eclipse.xtext.xbase.XSynchronizedExpression;
import org.eclipse.xtext.xbase.XThrowExpression;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XWhileExpression;
import org.eclipse.xtext.xbase.formatting.BlankLineKey;
import org.eclipse.xtext.xbase.formatting.FormattableDocument;
import org.eclipse.xtext.xbase.formatting.FormattingData;
import org.eclipse.xtext.xbase.formatting.FormattingDataFactory;
import org.eclipse.xtext.xbase.formatting.FormattingDataInit;
import org.eclipse.xtext.xbase.formatting.NodeModelAccess;
import org.eclipse.xtext.xbase.formatting.XbaseFormatter2;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xtype.XFunctionTypeRef;

@SuppressWarnings("all")
public class XcoreFormatter extends XbaseFormatter2 {
  @Inject
  @Extension
  private NodeModelAccess _nodeModelAccess;
  
  @Inject
  @Extension
  private FormattingDataFactory _formattingDataFactory;
  
  private final BlankLineKey blankLines = new BlankLineKey("foo", Integer.valueOf(1));
  
  protected void _format(final XAnnotation xAnnotation, final FormattableDocument format) {
    ILeafNode _nodeForKeyword = this._nodeModelAccess.nodeForKeyword(xAnnotation, "@");
    final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.noSpace();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append = this._formattingDataFactory.append(_nodeForKeyword, _function);
    format.operator_add(_append);
    final ILeafNode leftParenthesis = this._nodeModelAccess.nodeForKeyword(xAnnotation, "(");
    final Procedure1<FormattingDataInit> _function_1 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.noSpace();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(leftParenthesis, _function_1);
    format.operator_add(_prepend);
    final Procedure1<FormattingDataInit> _function_2 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.noSpace();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_1 = this._formattingDataFactory.append(leftParenthesis, _function_2);
    format.operator_add(_append_1);
    final ILeafNode rightParenthesis = this._nodeModelAccess.nodeForKeyword(xAnnotation, ")");
    final Procedure1<FormattingDataInit> _function_3 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.noSpace();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_1 = this._formattingDataFactory.prepend(rightParenthesis, _function_3);
    format.operator_add(_prepend_1);
    final Procedure1<FormattingDataInit> _function_4 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.newLine();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_2 = this._formattingDataFactory.append(rightParenthesis, _function_4);
    format.operator_add(_append_2);
    EMap<String,String> _details = xAnnotation.getDetails();
    for (final Map.Entry<String,String> entry : _details) {
      {
        final EObject detail = ((EObject) entry);
        final ILeafNode equals = this._nodeModelAccess.nodeForKeyword(detail, "=");
        final Procedure1<FormattingDataInit> _function_5 = new Procedure1<FormattingDataInit>() {
          public void apply(final FormattingDataInit it) {
            it.noSpace();
          }
        };
        Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_2 = this._formattingDataFactory.prepend(equals, _function_5);
        format.operator_add(_prepend_2);
        final Procedure1<FormattingDataInit> _function_6 = new Procedure1<FormattingDataInit>() {
          public void apply(final FormattingDataInit it) {
            it.noSpace();
          }
        };
        Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_3 = this._formattingDataFactory.append(equals, _function_6);
        format.operator_add(_append_3);
        INode _nodeForEObject = this._nodeModelAccess.nodeForEObject(detail);
        final ILeafNode comma = this._nodeModelAccess.immediatelyFollowingKeyword(_nodeForEObject, ",");
        boolean _notEquals = (!Objects.equal(comma, null));
        if (_notEquals) {
          final Procedure1<FormattingDataInit> _function_7 = new Procedure1<FormattingDataInit>() {
            public void apply(final FormattingDataInit it) {
              it.noSpace();
            }
          };
          Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_3 = this._formattingDataFactory.prepend(comma, _function_7);
          format.operator_add(_prepend_3);
        }
      }
    }
  }
  
  protected void _format(final XPackage xPackage, final FormattableDocument format) {
    EList<XAnnotation> _annotations = xPackage.getAnnotations();
    this.formatAnnotations(_annotations, format);
    INode _nodeForEObject = this._nodeModelAccess.nodeForEObject(xPackage);
    final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.noSpace();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(_nodeForEObject, _function);
    format.operator_add(_prepend);
    INode _nodeForFeature = this._nodeModelAccess.nodeForFeature(xPackage, XcorePackage.Literals.XNAMED_ELEMENT__NAME);
    final Procedure1<FormattingDataInit> _function_1 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.oneSpace();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_1 = this._formattingDataFactory.prepend(_nodeForFeature, _function_1);
    format.operator_add(_prepend_1);
    final EList<XImportDirective> xImportDirectives = xPackage.getImportDirectives();
    boolean _isEmpty = xImportDirectives.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      final XImportDirective first = IterableExtensions.<XImportDirective>head(xImportDirectives);
      final XImportDirective last = IterableExtensions.<XImportDirective>last(xImportDirectives);
      for (final XImportDirective xImportDirective : xImportDirectives) {
        {
          final INode node = this._nodeModelAccess.nodeForEObject(xImportDirective);
          boolean _equals = Objects.equal(xImportDirective, first);
          if (_equals) {
            final Procedure1<FormattingDataInit> _function_2 = new Procedure1<FormattingDataInit>() {
              public void apply(final FormattingDataInit it) {
                it.cfg(XcoreFormatter.this.blankLines);
              }
            };
            Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_2 = this._formattingDataFactory.prepend(node, _function_2);
            format.operator_add(_prepend_2);
          }
          this.format(xImportDirective, format);
          boolean _equals_1 = Objects.equal(xImportDirective, last);
          if (_equals_1) {
            final Procedure1<FormattingDataInit> _function_3 = new Procedure1<FormattingDataInit>() {
              public void apply(final FormattingDataInit it) {
                it.cfg(XcoreFormatter.this.blankLines);
              }
            };
            Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append = this._formattingDataFactory.append(node, _function_3);
            format.operator_add(_append);
          } else {
            final Procedure1<FormattingDataInit> _function_4 = new Procedure1<FormattingDataInit>() {
              public void apply(final FormattingDataInit it) {
                it.newLine();
              }
            };
            Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_1 = this._formattingDataFactory.append(node, _function_4);
            format.operator_add(_append_1);
          }
        }
      }
    } else {
      INode _nodeForFeature_1 = this._nodeModelAccess.nodeForFeature(xPackage, XcorePackage.Literals.XNAMED_ELEMENT__NAME);
      final Procedure1<FormattingDataInit> _function_2 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.cfg(XcoreFormatter.this.blankLines);
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append = this._formattingDataFactory.append(_nodeForFeature_1, _function_2);
      format.operator_add(_append);
    }
    final EList<XClassifier> xClassifiers = xPackage.getClassifiers();
    final XClassifier last_1 = IterableExtensions.<XClassifier>last(xClassifiers);
    for (final XClassifier xClassifier : xClassifiers) {
      {
        this.format(xClassifier, format);
        boolean _notEquals = (!Objects.equal(last_1, xClassifier));
        if (_notEquals) {
          INode _nodeForEObject_1 = this._nodeModelAccess.nodeForEObject(xClassifier);
          final Procedure1<FormattingDataInit> _function_3 = new Procedure1<FormattingDataInit>() {
            public void apply(final FormattingDataInit it) {
              it.cfg(XcoreFormatter.this.blankLines);
            }
          };
          Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_1 = this._formattingDataFactory.append(_nodeForEObject_1, _function_3);
          format.operator_add(_append_1);
        }
      }
    }
  }
  
  protected void _format(final XEnum xEnum, final FormattableDocument format) {
    EList<XAnnotation> _annotations = xEnum.getAnnotations();
    this.formatAnnotations(_annotations, format);
    final ILeafNode leftBrace = this._nodeModelAccess.nodeForKeyword(xEnum, "{");
    final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.increaseIndentation();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append = this._formattingDataFactory.append(leftBrace, _function);
    format.operator_add(_append);
    final Procedure1<FormattingDataInit> _function_1 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.newLine();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_1 = this._formattingDataFactory.append(leftBrace, _function_1);
    format.operator_add(_append_1);
    EList<XEnumLiteral> _literals = xEnum.getLiterals();
    for (final XEnumLiteral xEnumLiteral : _literals) {
      {
        this.format(xEnumLiteral, format);
        INode _nodeForEObject = this._nodeModelAccess.nodeForEObject(xEnumLiteral);
        final Procedure1<FormattingDataInit> _function_2 = new Procedure1<FormattingDataInit>() {
          public void apply(final FormattingDataInit it) {
            it.newLine();
          }
        };
        Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_2 = this._formattingDataFactory.append(_nodeForEObject, _function_2);
        format.operator_add(_append_2);
      }
    }
    final ILeafNode rightBrace = this._nodeModelAccess.nodeForKeyword(xEnum, "}");
    final Procedure1<FormattingDataInit> _function_2 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.decreaseIndentation();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(rightBrace, _function_2);
    format.operator_add(_prepend);
  }
  
  protected void _format(final XEnumLiteral xEnumLiteral, final FormattableDocument format) {
    EList<XAnnotation> _annotations = xEnumLiteral.getAnnotations();
    this.formatAnnotations(_annotations, format);
  }
  
  protected void _format(final XDataType xDataType, final FormattableDocument format) {
    final ILeafNode leftAngleBracket = this._nodeModelAccess.nodeForKeyword(xDataType, "<");
    boolean _notEquals = (!Objects.equal(leftAngleBracket, null));
    if (_notEquals) {
      final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(leftAngleBracket, _function);
      format.operator_add(_prepend);
      final Procedure1<FormattingDataInit> _function_1 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append = this._formattingDataFactory.append(leftAngleBracket, _function_1);
      format.operator_add(_append);
      EList<XTypeParameter> _typeParameters = xDataType.getTypeParameters();
      this.formatTypeParameters(_typeParameters, format);
      ILeafNode _nodeForKeyword = this._nodeModelAccess.nodeForKeyword(xDataType, ">");
      final Procedure1<FormattingDataInit> _function_2 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_1 = this._formattingDataFactory.prepend(_nodeForKeyword, _function_2);
      format.operator_add(_prepend_1);
    }
  }
  
  protected void _format(final XClass xClass, final FormattableDocument format) {
    EList<XAnnotation> _annotations = xClass.getAnnotations();
    this.formatAnnotations(_annotations, format);
    final ILeafNode leftAngleBracket = this._nodeModelAccess.nodeForKeyword(xClass, "<");
    boolean _notEquals = (!Objects.equal(leftAngleBracket, null));
    if (_notEquals) {
      final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(leftAngleBracket, _function);
      format.operator_add(_prepend);
      final Procedure1<FormattingDataInit> _function_1 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append = this._formattingDataFactory.append(leftAngleBracket, _function_1);
      format.operator_add(_append);
      EList<XTypeParameter> _typeParameters = xClass.getTypeParameters();
      this.formatTypeParameters(_typeParameters, format);
      ILeafNode _nodeForKeyword = this._nodeModelAccess.nodeForKeyword(xClass, ">");
      final Procedure1<FormattingDataInit> _function_2 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_1 = this._formattingDataFactory.prepend(_nodeForKeyword, _function_2);
      format.operator_add(_prepend_1);
    }
    final ILeafNode leftBrace = this._nodeModelAccess.nodeForKeyword(xClass, "{");
    final Procedure1<FormattingDataInit> _function_3 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.increaseIndentation();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_1 = this._formattingDataFactory.append(leftBrace, _function_3);
    format.operator_add(_append_1);
    final Procedure1<FormattingDataInit> _function_4 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.newLine();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_2 = this._formattingDataFactory.append(leftBrace, _function_4);
    format.operator_add(_append_2);
    EList<XMember> _members = xClass.getMembers();
    for (final XMember xMember : _members) {
      {
        this.format(xMember, format);
        INode _nodeForEObject = this._nodeModelAccess.nodeForEObject(xMember);
        final Procedure1<FormattingDataInit> _function_5 = new Procedure1<FormattingDataInit>() {
          public void apply(final FormattingDataInit it) {
            it.newLine();
          }
        };
        Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_3 = this._formattingDataFactory.append(_nodeForEObject, _function_5);
        format.operator_add(_append_3);
      }
    }
    final ILeafNode rightBrace = this._nodeModelAccess.nodeForKeyword(xClass, "}");
    final Procedure1<FormattingDataInit> _function_5 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.decreaseIndentation();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_2 = this._formattingDataFactory.prepend(rightBrace, _function_5);
    format.operator_add(_prepend_2);
  }
  
  protected void _format(final XReference xReference, final FormattableDocument format) {
    EList<XAnnotation> _annotations = xReference.getAnnotations();
    this.formatAnnotations(_annotations, format);
    final INode multiplicity = this._nodeModelAccess.nodeForFeature(xReference, XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    boolean _notEquals = (!Objects.equal(multiplicity, null));
    if (_notEquals) {
      final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(multiplicity, _function);
      format.operator_add(_prepend);
    }
    final XBlockExpression get = xReference.getGetBody();
    boolean _notEquals_1 = (!Objects.equal(get, null));
    if (_notEquals_1) {
      final ILeafNode leftBrace = this._nodeModelAccess.nodeForKeyword(get, "{");
      final Procedure1<FormattingDataInit> _function_1 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.oneSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_1 = this._formattingDataFactory.prepend(leftBrace, _function_1);
      format.operator_add(_prepend_1);
      this.format(get, format);
    }
  }
  
  protected void _format(final XAttribute xAttribute, final FormattableDocument format) {
    EList<XAnnotation> _annotations = xAttribute.getAnnotations();
    this.formatAnnotations(_annotations, format);
    final INode multiplicity = this._nodeModelAccess.nodeForFeature(xAttribute, XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    boolean _notEquals = (!Objects.equal(multiplicity, null));
    if (_notEquals) {
      final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(multiplicity, _function);
      format.operator_add(_prepend);
    }
    final XBlockExpression get = xAttribute.getGetBody();
    boolean _notEquals_1 = (!Objects.equal(get, null));
    if (_notEquals_1) {
      final ILeafNode leftBrace = this._nodeModelAccess.nodeForKeyword(get, "{");
      final Procedure1<FormattingDataInit> _function_1 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.oneSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_1 = this._formattingDataFactory.prepend(leftBrace, _function_1);
      format.operator_add(_prepend_1);
      this.format(get, format);
    }
  }
  
  protected void _format(final XOperation xOperation, final FormattableDocument format) {
    EList<XAnnotation> _annotations = xOperation.getAnnotations();
    this.formatAnnotations(_annotations, format);
    final INode multiplicity = this._nodeModelAccess.nodeForFeature(xOperation, XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    boolean _notEquals = (!Objects.equal(multiplicity, null));
    if (_notEquals) {
      final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(multiplicity, _function);
      format.operator_add(_prepend);
    }
    final ILeafNode leftAngleBracket = this._nodeModelAccess.nodeForKeyword(xOperation, "<");
    boolean _notEquals_1 = (!Objects.equal(leftAngleBracket, null));
    if (_notEquals_1) {
      final Procedure1<FormattingDataInit> _function_1 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_1 = this._formattingDataFactory.prepend(leftAngleBracket, _function_1);
      format.operator_add(_prepend_1);
      final Procedure1<FormattingDataInit> _function_2 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append = this._formattingDataFactory.append(leftAngleBracket, _function_2);
      format.operator_add(_append);
      EList<XTypeParameter> _typeParameters = xOperation.getTypeParameters();
      this.formatTypeParameters(_typeParameters, format);
      ILeafNode _nodeForKeyword = this._nodeModelAccess.nodeForKeyword(xOperation, ">");
      final Procedure1<FormattingDataInit> _function_3 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_2 = this._formattingDataFactory.prepend(_nodeForKeyword, _function_3);
      format.operator_add(_prepend_2);
    }
    final ILeafNode leftParenthesis = this._nodeModelAccess.nodeForKeyword(xOperation, "(");
    final Procedure1<FormattingDataInit> _function_4 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.noSpace();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_3 = this._formattingDataFactory.prepend(leftParenthesis, _function_4);
    format.operator_add(_prepend_3);
    final Procedure1<FormattingDataInit> _function_5 = new Procedure1<FormattingDataInit>() {
      public void apply(final FormattingDataInit it) {
        it.noSpace();
      }
    };
    Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _append_1 = this._formattingDataFactory.append(leftParenthesis, _function_5);
    format.operator_add(_append_1);
    final EList<XParameter> xParameters = xOperation.getParameters();
    boolean _isEmpty = xParameters.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      final ILeafNode rightParenthesis = this._nodeModelAccess.nodeForKeyword(xOperation, ")");
      final Procedure1<FormattingDataInit> _function_6 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_4 = this._formattingDataFactory.prepend(rightParenthesis, _function_6);
      format.operator_add(_prepend_4);
      for (final XParameter xParameter : xParameters) {
        this.format(xParameter, format);
      }
    }
    final XBlockExpression body = xOperation.getBody();
    boolean _notEquals_2 = (!Objects.equal(body, null));
    if (_notEquals_2) {
      final ILeafNode leftBrace = this._nodeModelAccess.nodeForKeyword(body, "{");
      final Procedure1<FormattingDataInit> _function_7 = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.oneSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend_5 = this._formattingDataFactory.prepend(leftBrace, _function_7);
      format.operator_add(_prepend_5);
      this.format(body, format);
    }
  }
  
  protected void _format(final XParameter xParameter, final FormattableDocument format) {
    EList<XAnnotation> _annotations = xParameter.getAnnotations();
    this.formatAnnotations(_annotations, format);
    final INode multiplicity = this._nodeModelAccess.nodeForFeature(xParameter, XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    boolean _notEquals = (!Objects.equal(multiplicity, null));
    if (_notEquals) {
      final Procedure1<FormattingDataInit> _function = new Procedure1<FormattingDataInit>() {
        public void apply(final FormattingDataInit it) {
          it.noSpace();
        }
      };
      Function1<? super FormattableDocument,? extends Iterable<FormattingData>> _prepend = this._formattingDataFactory.prepend(multiplicity, _function);
      format.operator_add(_prepend);
    }
  }
  
  protected void formatAnnotations(final List<XAnnotation> xAnnotations, final FormattableDocument format) {
    for (final XAnnotation xAnnotation : xAnnotations) {
      this.format(xAnnotation, format);
    }
  }
  
  protected void formatTypeParameters(final List<XTypeParameter> xTypeParameters, final FormattableDocument format) {
    for (final XTypeParameter xTypeParameter : xTypeParameters) {
      this.format(xTypeParameter, format);
    }
  }
  
  protected void format(final EObject xAttribute, final FormattableDocument format) {
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
    } else if (xAttribute instanceof JvmFormalParameter) {
      _format((JvmFormalParameter)xAttribute, format);
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
    } else if (xAttribute instanceof org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation) {
      _format((org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation)xAttribute, format);
      return;
    } else if (xAttribute instanceof JvmTypeConstraint) {
      _format((JvmTypeConstraint)xAttribute, format);
      return;
    } else if (xAttribute instanceof XCatchClause) {
      _format((XCatchClause)xAttribute, format);
      return;
    } else if (xAttribute instanceof XExpression) {
      _format((XExpression)xAttribute, format);
      return;
    } else if (xAttribute != null) {
      _format(xAttribute, format);
      return;
    } else if (xAttribute == null) {
      _format((Void)null, format);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(xAttribute, format).toString());
    }
  }
}
