@echo off

setlocal
if "%VABASE%"=="" set VABASE=..\..\..
%VABASE%\jre\bin\java -Xmx900M -Djava.compiler=NONE -verify -cp %VABASE%\startup.jar org.eclipse.core.launcher.Main -data workspace -application org.eclipse.emf.codegen.ecore.rose2ecore.Rose2Ecore %*
