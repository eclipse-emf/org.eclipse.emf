rem You need to set the ECLIPSE_DIR variable below.  It should point to your eclipse directory.
set ECLIPSE_DIR=z

rem Model project
set MAIN_DIR=..

rem The directory indicated by the -modelProject argument should be neither a parent nor a child directory of the workspace.
set WORKSPACE=%MAIN_DIR%\..\codegenWorkspace

rem XSD2GenModel application
%ECLIPSE_DIR%\eclipsec -noSplash -clean -data %WORKSPACE% -application org.eclipse.xsd.ecore.importer.XSD2GenModel %MAIN_DIR%\model\library.xsd %MAIN_DIR%\emf\library.genmodel -modelProject %MAIN_DIR% src -copyright "This is my code." -jdkLevel "5.0" -packages http://www.example.eclipse.org/Library -packageMap http://www.example.eclipse.org/Library org.examples.library

rem Generator application
%ECLIPSE_DIR%\eclipsec -noSplash -clean -data %WORKSPACE% -application org.eclipse.emf.codegen.ecore.Generator -model %MAIN_DIR%\emf\library.genmodel