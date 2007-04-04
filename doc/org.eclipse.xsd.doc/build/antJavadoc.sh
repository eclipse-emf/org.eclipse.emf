# The current direcotry
currentPath=$PWD

# The eclipse directory
eclipseDir=$1

# The destination directory
destDir=$eclipseDir/plugins/org.eclipse.xsd.doc/references/javadoc

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
groupPackage org.eclipse.xsd
groupPackage org.eclipse.xsd.edit
groupPackage org.eclipse.xsd.editor
groupPackage org.eclipse.emf.mapping.xsd2ecore
groupPackage org.eclipse.emf.mapping.xsd2ecore.editor

# The directory of the xsd plugins in the order they were built 
pluginDirs=`find $eclipseDir/plugins -name @dot -printf '%T@ %p\n' | sort -n | grep -v resources.jar | egrep -e 'org.eclipse.xsd|org.eclipse.emf.mapping.xsd2ecore' | cut -f2 -d' ' | sed -e 's/\(\/.*\)\/.*/\1/'`

# All the jars in the plugins directory
classpath=`find $eclipseDir/plugins \( -name "*.jar" -type f \) -o \( -name '@dot' -type d \) | tr '\n' ':'`; echo "Got classpath: "; echo $classpath;

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
#cp javadoc.xml /tmp/xsd-javadoc.xml

# Executes the ant script
ant	-f javadoc.xml \
	-DdestDir="$destDir" \
	-Dclasspath="$classpath" \
	-DeclipseDir="$eclipseDir" \
	-Doverview="$eclipseDir/plugins/org.eclipse.xsd.doc/build/overview.html"

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
	
