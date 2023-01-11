/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    format.append(this.regionFor2(xAnnotation).keyword("@"), _function);
    final Pair<ISemanticRegion, ISemanticRegion> parentheses = IterableExtensions.<Pair<ISemanticRegion, ISemanticRegion>>head(this.regionFor2(xAnnotation).keywordPairs("(", ")"));
    if ((parentheses == null)) {
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
        it.newLine();
      };
      format.append(this.regionFor2(xAnnotation).feature(XcorePackage.Literals.XANNOTATION__SOURCE), _function_1);
    } else {
      final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
        it.indent();
      };
      format.<ISemanticRegion, ISemanticRegion>interior(parentheses, _function_2);
      final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.append(format.prepend(this.regionFor2(xAnnotation).keyword("("), _function_3), _function_4);
      final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_6 = (IHiddenRegionFormatter it) -> {
        it.newLine();
      };
      format.append(format.prepend(this.regionFor2(xAnnotation).keyword(")"), _function_5), _function_6);
      EMap<String, String> _details = xAnnotation.getDetails();
      for (final Map.Entry<String, String> entry : _details) {
        {
          final EObject detail = ((EObject) entry);
          final Procedure1<IHiddenRegionFormatter> _function_7 = (IHiddenRegionFormatter it) -> {
            it.noSpace();
          };
          final Procedure1<IHiddenRegionFormatter> _function_8 = (IHiddenRegionFormatter it) -> {
            it.noSpace();
          };
          format.append(format.prepend(this.regionFor2(detail).keyword("="), _function_7), _function_8);
          final ISemanticRegion comma = this.immediatelyFollowing2(detail).keyword(",");
          if ((comma != null)) {
            final Procedure1<IHiddenRegionFormatter> _function_9 = (IHiddenRegionFormatter it) -> {
              it.noSpace();
            };
            final Procedure1<IHiddenRegionFormatter> _function_10 = (IHiddenRegionFormatter it) -> {
              it.oneSpace();
            };
            final Procedure1<IHiddenRegionFormatter> _function_11 = (IHiddenRegionFormatter it) -> {
              it.autowrap();
            };
            format.append(format.append(format.prepend(comma, _function_9), _function_10), _function_11);
          }
        }
      }
    }
  }

  protected void _format(final XPackage xPackage, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xPackage.getAnnotations(), format);
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    format.prepend(this.regionFor2(xPackage).keyword("package"), _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.oneSpace();
    };
    format.prepend(this.regionFor2(xPackage).feature(XcorePackage.Literals.XNAMED_ELEMENT__NAME), _function_1);
    final EList<XImportDirective> xImportDirectives = xPackage.getImportDirectives();
    boolean _isEmpty = xImportDirectives.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      final XImportDirective first = IterableExtensions.<XImportDirective>head(xImportDirectives);
      final XImportDirective last = IterableExtensions.<XImportDirective>last(xImportDirectives);
      for (final XImportDirective xImportDirective : xImportDirectives) {
        {
          final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
            int _xifexpression = (int) 0;
            boolean _equals = Objects.equal(xImportDirective, first);
            if (_equals) {
              _xifexpression = 2;
            } else {
              _xifexpression = 1;
            }
            it.setNewLines(_xifexpression);
          };
          format.prepend(this.regionFor2(xImportDirective).keyword("import"), _function_2);
          this.format(xImportDirective, format);
          boolean _equals = Objects.equal(xImportDirective, last);
          if (_equals) {
            final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
              it.setNewLines(2);
            };
            format.append(IterableExtensions.<ISemanticRegion>last(this.regionForEObject2(xImportDirective).getAllSemanticRegions()), _function_3);
          }
        }
      }
    } else {
      final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
        it.setNewLines(2);
      };
      format.append(this.regionFor2(xPackage).feature(XcorePackage.Literals.XNAMED_ELEMENT__NAME), _function_2);
    }
    final EList<XClassifier> xClassifiers = xPackage.getClassifiers();
    for (final XClassifier xClassifier : xClassifiers) {
      {
        this.format(xClassifier, format);
        final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
          it.setNewLines(2);
        };
        format.prepend(IterableExtensions.<ISemanticRegion>head(this.regionForEObject2(xClassifier).getAllSemanticRegions()), _function_3);
      }
    }
  }

  protected void _format(final XEnum xEnum, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xEnum.getAnnotations(), format);
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    format.<ISemanticRegion, ISemanticRegion>interior(IterableExtensions.<Pair<ISemanticRegion, ISemanticRegion>>head(this.regionFor2(xEnum).keywordPairs("{", "}")), _function);
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    format.append(this.regionFor2(xEnum).keyword("{"), _function_1);
    EList<XEnumLiteral> _literals = xEnum.getLiterals();
    for (final XEnumLiteral xEnumLiteral : _literals) {
      {
        this.format(xEnumLiteral, format);
        final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
          it.newLine();
        };
        format.append(IterableExtensions.<ISemanticRegion>last(this.regionForEObject2(xEnumLiteral).getAllSemanticRegions()), _function_2);
      }
    }
  }

  protected void _format(final XEnumLiteral xEnumLiteral, final IFormattableDocument format) {
    this.formatAnnotations(xEnumLiteral.getAnnotations(), format);
  }

  protected void _format(final XDataType xDataType, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xDataType.getAnnotations(), format);
    final ISemanticRegion leftAngleBracket = this.regionFor2(xDataType).keyword("<");
    if ((leftAngleBracket != null)) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.append(format.prepend(leftAngleBracket, _function), _function_1);
      this.formatTypeParameters(xDataType.getTypeParameters(), format);
      final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      format.append(format.prepend(this.regionFor2(xDataType).keyword(">"), _function_2), _function_3);
    }
  }

  protected void _format(final XClass xClass, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xClass.getAnnotations(), format);
    final ISemanticRegion leftAngleBracket = this.regionFor2(xClass).keyword("<");
    if ((leftAngleBracket != null)) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.append(format.prepend(leftAngleBracket, _function), _function_1);
      this.formatTypeParameters(xClass.getTypeParameters(), format);
      final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      format.append(format.prepend(this.regionFor2(xClass).keyword(">"), _function_2), _function_3);
    }
    final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
      it.indent();
    };
    format.<ISemanticRegion, ISemanticRegion>interior(IterableExtensions.<Pair<ISemanticRegion, ISemanticRegion>>head(this.regionFor2(xClass).keywordPairs("{", "}")), _function_4);
    final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    format.append(this.regionFor2(xClass).keyword("{"), _function_5);
    EList<XMember> _members = xClass.getMembers();
    for (final XMember xMember : _members) {
      {
        this.format(xMember, format);
        final Procedure1<IHiddenRegionFormatter> _function_6 = (IHiddenRegionFormatter it) -> {
          it.newLine();
        };
        format.append(IterableExtensions.<ISemanticRegion>last(this.regionForEObject2(xMember).getAllSemanticRegions()), _function_6);
      }
    }
  }

  protected void _format(final XReference xReference, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xReference.getAnnotations(), format);
    format.<XGenericType>format(xReference.getType());
    final ISemanticRegion multiplicity = this.regionFor2(xReference).feature(XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    if ((multiplicity != null)) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.prepend(multiplicity, _function);
    }
    final XBlockExpression get = xReference.getGetBody();
    if ((get != null)) {
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      format.prepend(this.regionFor2(get).keyword("{"), _function_1);
      this.format(get, format);
    }
  }

  protected void _format(final XAttribute xAttribute, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xAttribute.getAnnotations(), format);
    format.<XGenericType>format(xAttribute.getType());
    final ISemanticRegion multiplicity = this.regionFor2(xAttribute).feature(XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    if ((multiplicity != null)) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.prepend(multiplicity, _function);
    }
    final XBlockExpression get = xAttribute.getGetBody();
    if ((get != null)) {
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      format.prepend(this.regionFor2(get).keyword("{"), _function_1);
      this.format(get, format);
    }
  }

  protected void _format(final XOperation xOperation, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xOperation.getAnnotations(), format);
    format.<XGenericType>format(xOperation.getType());
    final ISemanticRegion multiplicity = this.regionFor2(xOperation).feature(XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    if ((multiplicity != null)) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.prepend(multiplicity, _function);
    }
    final ISemanticRegion leftAngleBracket = this.regionFor2(xOperation).keyword("<");
    if ((leftAngleBracket != null)) {
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.append(format.prepend(leftAngleBracket, _function_1), _function_2);
      this.formatTypeParameters(xOperation.getTypeParameters(), format);
      final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      format.append(format.prepend(this.regionFor2(xOperation).keyword(">"), _function_3), _function_4);
    }
    final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    final Procedure1<IHiddenRegionFormatter> _function_6 = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    format.append(format.prepend(this.regionFor2(xOperation).keyword("("), _function_5), _function_6);
    final EList<XParameter> xParameters = xOperation.getParameters();
    boolean _isEmpty = xParameters.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      final Procedure1<IHiddenRegionFormatter> _function_7 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.prepend(this.regionFor2(xOperation).keyword(")"), _function_7);
      for (final XParameter xParameter : xParameters) {
        this.format(xParameter, format);
      }
    }
    final XBlockExpression body = xOperation.getBody();
    if ((body != null)) {
      final Procedure1<IHiddenRegionFormatter> _function_8 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      format.prepend(this.regionFor2(body).keyword("{"), _function_8);
      this.format(body, format);
    }
  }

  protected void _format(final XParameter xParameter, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xParameter.getAnnotations(), format);
    final ISemanticRegion multiplicity = this.regionFor2(xParameter).feature(XcorePackage.Literals.XTYPED_ELEMENT__MULTIPLICITY);
    if ((multiplicity != null)) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.prepend(multiplicity, _function);
    }
    format.<XGenericType>format(xParameter.getType());
  }

  protected void _format(final XGenericType xGenericType, @Extension final IFormattableDocument format) {
    format.<GenBase>format(xGenericType.getType());
    final ISemanticRegion leftAngleBracket = this.regionFor2(xGenericType).keyword("<");
    if ((leftAngleBracket != null)) {
      final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.append(format.prepend(leftAngleBracket, _function), _function_1);
      EList<XGenericType> _typeArguments = xGenericType.getTypeArguments();
      for (final XGenericType typeArgument : _typeArguments) {
        {
          format.<XGenericType>format(typeArgument);
          final ISemanticRegion comma = this.immediatelyFollowing2(typeArgument).keyword(",");
          if ((comma != null)) {
            final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
              it.noSpace();
            };
            final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
              it.oneSpace();
            };
            format.append(format.prepend(comma, _function_2), _function_3);
          }
        }
      }
      final Procedure1<IHiddenRegionFormatter> _function_2 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_3 = (IHiddenRegionFormatter it) -> {
        it.noSpace();
      };
      format.append(format.prepend(this.regionFor2(xGenericType).keyword(">"), _function_2), _function_3);
    }
    final XGenericType upperBound = xGenericType.getUpperBound();
    if ((upperBound != null)) {
      format.<XGenericType>format(upperBound);
      final Procedure1<IHiddenRegionFormatter> _function_4 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_5 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      format.append(format.prepend(this.regionFor2(xGenericType).keyword("extends"), _function_4), _function_5);
    }
    final XGenericType lowerBound = xGenericType.getLowerBound();
    if ((lowerBound != null)) {
      format.<XGenericType>format(lowerBound);
      final Procedure1<IHiddenRegionFormatter> _function_6 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      final Procedure1<IHiddenRegionFormatter> _function_7 = (IHiddenRegionFormatter it) -> {
        it.oneSpace();
      };
      format.append(format.prepend(this.regionFor2(xGenericType).keyword("super"), _function_6), _function_7);
    }
  }

  protected void _format(final XTypeParameter xTypeParameter, @Extension final IFormattableDocument format) {
    this.formatAnnotations(xTypeParameter.getAnnotations(), format);
    EList<XGenericType> _bounds = xTypeParameter.getBounds();
    for (final XGenericType bound : _bounds) {
      {
        format.<XGenericType>format(bound);
        final ISemanticRegion ampersand = this.immediatelyFollowing2(bound).keyword("&");
        if ((ampersand != null)) {
          final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
            it.oneSpace();
          };
          final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
            it.oneSpace();
          };
          format.append(format.prepend(ampersand, _function), _function_1);
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

  private ISemanticRegionFinder immediatelyFollowing2(final EObject semanticElement) {
    return this.textRegionExtensions.immediatelyFollowing(semanticElement);
  }

  private ISemanticRegionsFinder regionFor2(final EObject semanticElement) {
    return this.textRegionExtensions.regionFor(semanticElement);
  }

  private IEObjectRegion regionForEObject2(final EObject semanticElement) {
    return this.textRegionExtensions.regionForEObject(semanticElement);
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
