@echo off
setlocal
set ECLIPSE=..\..\..
set CLASSPATH=%ECLIPSE%/plugins/org.eclipse.emf.common/runtime/common.jar;%ECLIPSE%/plugins/org.eclipse.emf.ecore/runtime/ecore.jar;%ECLIPSE%/plugins/org.eclipse.xsd/runtime/xsd.resources.jar;%ECLIPSE%/plugins/org.eclipse.xsd/runtime/xsd.jar;%ECLIPSE%/plugins/org.eclipse.xsd.test/runtime/xsd.test.jar
%ECLIPSE%\jre\bin\java -DVABASE=%ECLIPSE% -verify org.eclipse.xsd.test.XSDMainTest  %*
