@echo off

setlocal
if "%VABASE%"=="" set VABASE=..\..\..

%VABASE%\plugins\etools.build\build\tools\sh -c ". ${VABASE//\\\\//}/plugins/org.eclipse.emf.codegen/test/code-gen %*"
