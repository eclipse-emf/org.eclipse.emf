@echo off

setlocal
set VABASE=..\..\..

%VABASE%\jre\bin\java -Djava.compiler=NONE -verify -cp %VABASE%\startup.jar org.eclipse.core.launcher.Main -application org.eclipse.xsd.example.XSDMainExample %*

