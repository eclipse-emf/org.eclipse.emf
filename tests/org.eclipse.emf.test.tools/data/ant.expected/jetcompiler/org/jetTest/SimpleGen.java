package org.jetTest;

public class SimpleGen
{
  protected static String nl;
  public static synchronized SimpleGen create(String lineSeparator)
  {
    nl = lineSeparator;
    SimpleGen result = new SimpleGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public class Simple" + NL + "{" + NL + "\tpublic void main(String[] args)" + NL + "\t{";
  protected final String TEXT_2 = NL + "\t\tSystem.out.println(\"The number \" + ";
  protected final String TEXT_3 = " + \" is ";
  protected final String TEXT_4 = "\");";
  protected final String TEXT_5 = NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_6 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    for (int i=1; i <= 20; i++) {
    stringBuffer.append(TEXT_2);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(i%2==0?"even":"odd");
    stringBuffer.append(TEXT_4);
    }
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
