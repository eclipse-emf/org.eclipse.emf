if [ "x"$ANT_HOME = "x" ]; then export ANT_HOME=/opt/apache-ant-1.6; fi
if [ "x"$JAVA_HOME = "x" ]; then export JAVA_HOME=/opt/ibm-java2-1.4; fi
export PATH=${PATH}:${ANT_HOME}/bin

# current directory
currentPath=$PWD

# The eclipse directory
eclipseDir=$1

# The destination directory
destDir=$eclipseDir/plugins/org.eclipse.emf.doc/references/javadoc

# Don't execute if the destination directory has files
if [ -d "$destDir" ]; then
	exit
fi

function groupPackage
{
	plugin=$1
	hasToken=`grep "@$plugin@" $currentPath/javadoc.xml.template`
	if [ "x$hasToken" != "x"  ]; then
		srcDir=$eclipseDir/plugins/$plugin/src
		if [ -d "$srcDir" ]; then
			packages=`find $srcDir -type f -name '*.java' -exec grep -e '^package .*;' {} \; | sed -e 's/^package *\(.*\);.*/\1/' | sort | uniq | xargs | sed -e 's/ /:/g'`
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
groupPackage org.eclipse.emf.mapping.ecore2xml
groupPackage org.eclipse.emf.mapping.ecore2xml.ui
groupPackage org.eclipse.emf.converter
groupPackage org.eclipse.emf.exporter
groupPackage org.eclipse.emf.importer
groupPackage org.eclipse.emf.importer.ecore
groupPackage org.eclipse.emf.importer.java
groupPackage org.eclipse.emf.importer.rose
groupPackage org.eclipse.emf.ant

# The directory of the emf plugins in the order they were built 
pluginDirs=`find $eclipseDir/plugins -name @dot -printf '%T@ %p\n' | sort -n | grep org.eclipse.emf | grep -v resources.jar | grep -v xsd | grep -v test  | grep -v org.eclipse.emf.java | grep -v sdo | cut -f2 -d' ' | sed -e 's/\(\/.*\)\/.*/\1/'`

### TODO: missing emf/sdo/xsd plugins (?) in $eclipseDir - need to copy them over or reference source (?)
### so that all classes/packages (and thus @links) can be resolved

# All the jars in the plugins directory
classpath=`find $eclipseDir/plugins -name "*.jar" | tr '\n' ':'`; echo "Got classpath: "; echo $classpath;

# Calculates the packagesets and the calls to copyDocFiles (used in javadoc.xml.template)
# also calculates pluginIDs used in the PDE Javadoc extension point in the plugin.xml 
packagesets=""
copydocfiles=""
pluginIDs=""

for pluginDir in $pluginDirs; do
	pluginDir=`echo $pluginDir | sed -e 's/\/runtime$//g'`
	srcDir=$pluginDir/src
	if [ -d "$srcDir" ]; then
		packagesets=$packagesets"<packageset dir=\"$srcDir\"><exclude name=\"$srcDir/**/doc-files/**\"/></packageset>"
		copydocfiles=$copydocfiles"<copyDocFiles pluginDir=\"$pluginDir\"/>"
		pluginID=`echo "$pluginDir" | sed -e 's|.*plugins/org|org|'`
		pluginIDs=$pluginIDs"<plugin id=\"$pluginID\"/>"
	fi
	srcDir=$pluginDir/tasks
	if [ -d "$srcDir" ]; then
		packagesets=$packagesets"<packageset dir=\"$srcDir\"><exclude name=\"$srcDir/**/doc-files/**\"/></packageset>"
		copydocfiles=$copydocfiles"<copyDocFiles pluginDir=\"$pluginDir\"/>"
	fi
done

# Replaces the token <!-- @pluginIDs@ --> in the plugin.xml by the value of pluginIDs
pluginIDs=`echo $pluginIDs | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
sed -e "s/<\!-- \@pluginIDs\@ --><plugin id=.*\/>/${pluginIDs}/g" $currentPath/../plugin.xml > plugin2.xml
mv plugin2.xml ../plugin.xml

# Replaces the token @packagesets@ in the template by the actual value
packagesets=`echo $packagesets | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
sed -e "s/\@packagesets\@/${packagesets}/g" $currentPath/javadoc.xml.template > javadoc.xml.template2
# Replaces the token @copydocfiles@ in the template by the actual value
copydocfiles=`echo $copydocfiles | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
sed -e "s/\@copydocfiles\@/${copydocfiles}/g" $currentPath/javadoc.xml.template2 > javadoc.xml
#cp javadoc.xml /tmp/emf-javadoc.xml

# Executes the ant script
$ANT_HOME/bin/ant	-f javadoc.xml \
	-DdestDir="$destDir" \
	-Dclasspath="$classpath" \
	-DeclipseDir="$eclipseDir" \
	-Doverview="$eclipseDir/plugins/org.eclipse.emf.doc/build/overview.html"
# Generate topics_Reference.xml (replacement for doclet). 
trXML=$currentPath"/../topics_Reference.xml";
echo '<?xml version="1.0" encoding="UTF-8"?>' > $trXML;
echo '<?NLS TYPE="org.eclipse.help.toc"?>' >> $trXML;
echo '<toc label="Reference">' >> $trXML;
echo '  <topic label="API Reference" href="references/javadoc/overview-summary.html">' >> $trXML;
for packSum in `find $destDir -name "package-summary.html" | sort`; do
	path=${packSum%/package-summary.html}; path=${path#$destDir/}; # org/eclipse/xsd/ecore/importer/taskdefs
	label=${path//\//.}; # org.eclipse.xsd.ecore.importer.taskdefs
	echo '    <topic label="'$label'" href="references/javadoc/'$path'/package-summary.html" />' >> $trXML;
done
echo '  </topic>' >> $trXML;
echo '</toc>' >> $trXML;
