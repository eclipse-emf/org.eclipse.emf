package org.jetTest;

public class MediumGen
{
  protected static String nl;
  public static synchronized MediumGen create(String lineSeparator)
  {
    nl = lineSeparator;
    MediumGen result = new MediumGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public class Medium" + NL + "{" + NL + "\tpublic void main(String[] args)" + NL + "\t{" + NL + "\t\tSystem.out.println(\"The class of the argument is '";
  protected final String TEXT_2 = "'.\");" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_3 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(argument.getClass().getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
