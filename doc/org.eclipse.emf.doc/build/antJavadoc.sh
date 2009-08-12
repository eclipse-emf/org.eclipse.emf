#!/bin/sh

# BEGIN CUSTOMIZATIONS

# The plugin name
pluginName="org.eclipse.emf";


# END CUSTOMIZATIONS

##########################################################################

debug=0; if [[ $debug -gt 0 ]]; then echo "[antJd] debug: "$debug; fi

if [ "x"$ANT_HOME = "x" ]; then export ANT_HOME=/opt/apache-ant-1.6; fi
if [ "x"$JAVA_HOME = "x" ]; then export JAVA_HOME=/opt/ibm-java2-1.4; fi
export PATH=${PATH}:${ANT_HOME}/bin

# current directory - all but the name of this script, no trailing slash
currentPath=$PWD"/"$0; currentPath=${currentPath%/*}; if [[ $debug -gt 0 ]]; then echo "[antJd] currentPath: "$currentPath; fi

# path to $buildID/eclipse/plugins, no trailing slash
pluginPath=${currentPath%/$pluginName*}; if [[ $debug -gt 0 ]]; then echo "[antJd] pluginName: "$pluginName; echo "[antJd] pluginPath: "$pluginPath; fi

# ant script to create and then execute
antScript=$currentPath"/javadoc.xml"; if [[ $debug -gt 0 ]]; then echo "[antJd] antScript: "$antScript; fi
# The eclipse directory
eclipseDir=`cd $1; echo $PWD`; if [[ $debug -gt 0 ]]; then echo "[antJd] eclipseDir: "$eclipseDir; fi

# The destination directory
destDir=$currentPath/../references/javadoc; mkdir -p $destDir; destDir=`cd $destDir; echo $PWD`; # resolve relative path
if [[ $debug -gt 0 ]]; then echo "[antJd] destDir: "$destDir; fi

function groupPackage
{
	plugin=$1
	hasToken=`grep -c "@plugin@" $antScript".template"`;
	if [[ $hasToken -gt 0  ]]; then
		srcDir=$eclipseDir/plugins/$plugin/src
		if [ -d "$srcDir" ]; then
			if [ `find $srcDir -name "*.java" | grep -c .` -eq 0 ]; then # must unpack zips first
				if [[ $debug -gt 0 ]]; then echo "[antJd] Unpacking *src.zip"; fi
				for f in `find $srcDir -name "*src.zip"`; do 
					if [[ $debug -gt 1 ]]; then echo "[antJd] Unpack $f"; fi
					unzip -q -d $srcDir $f; 
				done
			fi
			if [[ $debug -gt 1 ]]; then echo "[antJd] *.java in srcDir: "; echo "-----------------"; find $srcDir -type f -name '*.java'; echo "-----------------"; fi
			packages=`find $srcDir -type f -name '*.java' -exec grep -e '^package .*;' {} \; | sed -e 's/^package *\(.*\);/\1/' | sed -e 's/[ ]*//g' | dos2unix | sort | uniq | xargs | sed -e 's/ /:/g'`;
			# packages=`find $srcDir -type f -name '*.java' -exec grep -e '^package .*;' {} \; | sed -e 's/^package *\(.*\);.*/\1/' | sort | uniq | xargs | sed -e 's/ /:/g'` # old way
			if [[ $debug -gt 1 ]]; then echo "[antJd] packages1: "$packages; fi
			packages=`echo $packages | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`; # slash escape
			if [[ $debug -gt 1 ]]; then echo "[antJd] packages2: "$packages; fi
			sed -e "s/\@${plugin}\@/${packages}/g" ${antScript}.template > ${antScript}.template.tmp;
			#sed -e "s/\@${plugin}\@/${packages}/g" $currentPath/javadoc.xml.template > javadoc.xml.template.tmp; # old way
			mv ${antScript}.template.tmp ${antScript}.template
		else 
			echo "[antJd] ERROR! "$srcDir" does not exist!";
			exit 1;
		fi
	else
		echo "Error: $antScript.template contains no @plugin@ tokens!"; 
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

# The directory of the plugins in the order they were built
excludes="resources.jar";
includes="${pluginName}";
pluginDirs=`find -L $eclipseDir/plugins -name @dot -printf '%T@ %p\n' | sort -n | egrep "$includes" | egrep -v "$excludes" | cut -f2 -d' ' | sed -e 's/\(\/.*\)\/.*/\1/'`

### TODO: missing emf/xsd plugins (?) in $eclipseDir - need to copy them over or reference source (?)
### so that all classes/packages (and thus @links) can be resolved

# All the jars in the plugins directory
classpath=`find -L $eclipseDir/plugins \( -name "*.jar" -type f \) -o \( -name '@dot' -type d \) | tr '\n' ':'`; 
if [[ $debug -gt 1 ]]; then echo "Got classpath: "; echo $classpath | perl -pe "s#:#\n\t:#g"; fi

# Calculates the packagesets and the calls to copyDocFiles (used in ${antScript}.template)
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
sed -e "s/\@packagesets\@/${packagesets}/g" ${antScript}.template > ${antScript}.template2
# Replaces the token @copydocfiles@ in the template by the actual value
copydocfiles=`echo $copydocfiles | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
sed -e "s/\@copydocfiles\@/${copydocfiles}/g" ${antScript}.template2 > ${antScript}
#cp ${antScript} /tmp/${pluginName}-javadoc.xml

# Executes the ant script
ant	-f ${antScript} \
	-DdestDir="$destDir" \
	-Dclasspath="$classpath" \
	-DeclipseDir="$eclipseDir" \
	-Doverview="$eclipseDir/plugins/${pluginName}.doc/build/overview.html"

# Don't clean up templates because this script is called more than once (though it shouldn't have to be!)
#rm -f $antScript $antScript.template*;

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
	