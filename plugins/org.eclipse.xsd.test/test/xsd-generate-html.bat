@echo off

setlocal
set ECLIPSE=..\..\..

%ECLIPSE%\jre\bin\java -verify -cp %ECLIPSE%\startup.jar org.eclipse.core.launcher.Main -application org.eclipse.xsd.test.XSDGenerateHTML %*

