rem You need to set the ECLIPSE_DIR variable below.  It should point to your eclipse directory.
set ECLIPSE_DIR=z

rem The GenModel IPath (it has to be a workspace absolute path, in which the project is a valid Java Project)
set GENMODEL_IPATH=/myjavaproject/model/library.genmodel

rem The workspace absolute path of the model project
set MODEL_PROJECT=/myjavaproject

set GENMODEL_LOCATION=%MODEL_PROJECT%\model\library.genmodel

rem The workspace directory: should be a workspace in which %MODEL_PROJECT% is registered as a Java project
set WORKSPACE=%MAIN_DIR%\..\codegenWorkspace

rem Java2GenModel application
%ECLIPSE_DIR%\eclipsec -noSplash -clean -data %WORKSPACE% -application org.eclipse.emf.importer.java.Java2GenModel %GENMODEL_IPATH% -modelProject %MODEL_PROJECT% -modelPluginID library.model -copyright "This is my code." -jdkLevel "5.0" 

rem Generator application
%ECLIPSE_DIR%\eclipsec -noSplash -clean -data %WORKSPACE% -application org.eclipse.emf.codegen.ecore.Generator -model %GENMODEL_LOCATION%