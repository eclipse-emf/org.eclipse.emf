package org.jetTest;

import java.util.List;

public class ComplexGen
{
  protected static String nl;
  public static synchronized ComplexGen create(String lineSeparator)
  {
    nl = lineSeparator;
    ComplexGen result = new ComplexGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public class Complex" + NL + "{" + NL + "\tpublic void main(String[] args)" + NL + "\t{";
  protected final String TEXT_2 = NL + "\t\tSystem.out.println(\"";
  protected final String TEXT_3 = "\");";
  protected final String TEXT_4 = NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_5 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    List<?> objectsToPrint = (List<?>)argument;
    stringBuffer.append(TEXT_1);
    for (Object objectToPrint : objectsToPrint) {
    stringBuffer.append(TEXT_2);
    stringBuffer.append(objectToPrint.toString());
    stringBuffer.append(TEXT_3);
    }
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
