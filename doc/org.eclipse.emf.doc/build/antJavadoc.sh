# The current direcotry
currentPath=$PWD

# The eclipse directory
eclipseDir=$1

# The destination directory
destDir=$eclipseDir/plugins/org.eclipse.emf.doc/references/javadoc

# Don't execute if the destination directory has files
if [ -d $destDir ]; then
	exit
fi

function groupPackage
{
	plugin=$1
	hasToken=`grep "@$plugin@" $currentPath/javadoc.xml.template`
	if [ "x$hasToken" != "x"  ]; then
		srcDir=$eclipseDir/plugins/$plugin/src
		if [ -d $srcDir ]; then
			packages=`find $srcDir -type f -name '*.java' -exec grep -e '^package .*;' {} \; | sed -e 's/^package *\(.*\);/\1/' | sort | uniq | xargs | sed -e 's/ /:/g'`
			packages=`echo $packages | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
		
			sed -e "s/\@${plugin}\@/${packages}/g" $currentPath/javadoc.xml.template > javadoc.xml.template.tmp
	
			mv javadoc.xml.template.tmp javadoc.xml.template
		fi
	fi
}
groupPackage org.eclipse.emf.codegen
groupPackage org.eclipse.emf.codegen.ui
groupPackage org.eclipse.emf.codegen.ecore
groupPackage org.eclipse.emf.codegen.ecore.ui
groupPackage org.eclipse.emf.common
groupPackage org.eclipse.emf.common.ui
groupPackage org.eclipse.emf.ecore
groupPackage org.eclipse.emf.ecore.edit
groupPackage org.eclipse.emf.ecore.editor
groupPackage org.eclipse.emf.ecore.change
groupPackage org.eclipse.emf.ecore.change.edit
groupPackage org.eclipse.emf.ecore.xmi
groupPackage org.eclipse.emf.edit
groupPackage org.eclipse.emf.edit.ui
groupPackage org.eclipse.emf.mapping
groupPackage org.eclipse.emf.mapping.ui
groupPackage org.eclipse.emf.mapping.ecore2ecore
groupPackage org.eclipse.emf.mapping.ecore2ecore.editor

# The directory of the emf plugins in the order they were built 
pluginDirs=`find $eclipseDir/plugins -name @dot -printf '%T@ %p\n' | sort -n | grep org.eclipse.emf | grep -v resources.jar | grep -v xsd | grep -v test  | grep -v org.eclipse.emf.java | grep -v sdo | cut -f2 -d' ' | sed -e 's/\(\/.*\)\/.*/\1/'`

# All the jars in the pluigins directory
classpath=`find $eclipseDir/plugins -name *.jar -print | grep -v org.eclipse.emf | grep -v org.eclipse.xsd | tr '\n' ':'`

echo "Got classpath entries: "; for cp in classpath; do echo $cp; done

# Calculates the packagesets and the calls to copyDocFiles
packagesets=""
copydocfiles=""
for pluginDir in $pluginDirs; do
	pluginDir=`echo $pluginDir | sed -e 's/\/runtime$//g'`
	srcDir=$pluginDir/src
	if [ -d $srcDir ]; then
		packagesets=$packagesets"<packageset dir=\"$srcDir\"><exclude name=\"$srcDir/**/doc-files/**\"/></packageset>"
		copydocfiles=$copydocfiles"<copyDocFiles pluginDir=\"$pluginDir\"/>"
	fi
done

# Replaces the token @packagesets@ in the template by the actual value
packagesets=`echo $packagesets | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
sed -e "s/\@packagesets\@/${packagesets}/g" $currentPath/javadoc.xml.template > javadoc.xml.template2
# Replaces the token @copydocfiles@ in the template by the actual value
copydocfiles=`echo $copydocfiles | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
sed -e "s/\@copydocfiles\@/${copydocfiles}/g" $currentPath/javadoc.xml.template2 > javadoc.xml

cp javadoc.xml /tmp/emf-javadoc.xml

# Executes the ant script
ant	-f javadoc.xml \
	-DdestDir="$destDir" \
	-Dclasspath="$classpath" \
	-DeclipseDir="$eclipseDir" \
	-Doverview="$eclipseDir/plugins/org.eclipse.emf.doc/build/overview.html"
