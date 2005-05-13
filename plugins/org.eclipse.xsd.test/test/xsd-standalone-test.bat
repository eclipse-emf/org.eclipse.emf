@echo off
setlocal
set ECLIPSE=..\..\..
set CLASSPATH=%ECLIPSE%/plugins/org.eclipse.emf.common/common.jar;%ECLIPSE%/plugins/org.eclipse.emf.ecore/ecore.jar;%ECLIPSE%/plugins/org.eclipse.xsd/xsd.resources.jar;%ECLIPSE%/plugins/org.eclipse.xsd/xsd.jar;%ECLIPSE%/plugins/org.eclipse.xsd.test/xsd.test.jar
%ECLIPSE%\jre\bin\java -DVABASE=%ECLIPSE% -verify org.eclipse.xsd.test.XSDMainTest  %*
