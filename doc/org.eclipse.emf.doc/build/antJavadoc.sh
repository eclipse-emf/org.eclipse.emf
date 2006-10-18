#!/bin/sh

# BEGIN CUSTOMIZATIONS

# The plugin name
pluginName="org.eclipse.xsd"; 

# string labels for javadoc content
windowTitle="XSD Javadoc";
groupTitle="XSD";

# files to exclude from javadoc process - use Ant syntax
javadocExclusions="<exclude name=\"**/internal/**\"/> <exclude name=\"**/examples/**\"/> <exclude name=\"**/tests/**\"/>";

# use 1.5
export JAVA_HOME=/opt/sun-java2-5.0

# END CUSTOMIZATIONS

##########################################################################

debug=0; if [ $debug -gt 0 ]; then echo "[antJd] debug: "$debug; fi

if [ "x"$ANT_HOME = "x" ]; then export ANT_HOME=/opt/apache-ant-1.6; fi
if [ "x"$JAVA_HOME = "x" ]; then export JAVA_HOME=/opt/sun-java2-5.0; fi
export PATH=${PATH}:${ANT_HOME}/bin

# current directory - all but the name of this script, no trailing slash
currentPath=$PWD"/"$0; currentPath=${currentPath%/*}; if [ $debug -gt 0 ]; then echo "[antJd] currentPath: "$currentPath; fi

# path to $buildID/eclipse/plugins, no trailing slash
pluginPath=${currentPath%/$pluginName*}; if [ $debug -gt 0 ]; then echo "[antJd] pluginName: "$pluginName; echo "[antJd] pluginPath: "$pluginPath; fi

# ant script to create and then execute
antScript=$currentPath"/javadoc.xml.template"; if [ $debug -gt 0 ]; then echo "[antJd] antScript: "$antScript; fi

# The eclipse directory
eclipseDir=`cd $1; echo $PWD`; if [ $debug -gt 0 ]; then echo "[antJd] eclipseDir: "$eclipseDir; fi

# The destination directory
destDir=$currentPath/../references/javadoc; mkdir -p $destDir; destDir=`cd $destDir; echo $PWD`; # resolve relative path
if [ $debug -gt 0 ]; then echo "[antJd] destDir: "$destDir; fi

hasToken=`grep -c "@plugin@" $antScript`;
if [ $hasToken -gt 0  ]; then
	srcDir=$pluginPath/$pluginName.source/src; if [ $debug -gt 0 ]; then echo "[antJd] srcDir: "$srcDir; fi
	if [ -d "$srcDir" ]; then
		#if [ `find $srcDir -name "*.java" | grep -c .` -eq 0 ]; then # must unpack zips first
		#	if [ $debug -gt 0 ]; then echo "[antJd] Unpacking *src.zip"; fi
		#	for f in `find $srcDir -name "*src.zip"`; do 
		#		if [ $debug -gt 1 ]; then echo "[antJd] Unpack $f"; fi
		#		unzip -q -d $srcDir $f; 
		#	done
		#fi
		if [ $debug -gt 0 ]; then echo "[antJd] *.java in srcDir: "; echo "-----------------"; echo `find $srcDir -type f -name '*.java'`; echo "-----------------"; fi
		packages=`find $srcDir -type f -name '*.java' -exec grep -e '^package .*;' {} \; | sed -e 's/^package *\(.*\);/\1/' | sed -e 's/[ ]*//g' | dos2unix | sort | uniq | xargs | sed -e 's/ /:/g'`;
		if [ $debug -gt 1 ]; then echo "[antJd] packages1: "$packages; fi
		packages=`echo $packages | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`; # slash escape
		if [ $debug -gt 1 ]; then echo "[antJd] packages2: "$packages; fi
		sed -e "s/\@plugin\@/${packages}/g" $antScript > $antScript.tmp;
	else 
		echo "[antJd] ERROR! "$srcDir" does not exist!";
		exit 1;
  	fi
else 
	echo "[antJd] ERROR! "$antScript" does not contain token @plugin@!";
	exit 1;
fi

# Finds plugins in the Workspace:
pluginDirs=`find $pluginPath -name "${pluginName}*" -maxdepth 1 -type d -printf '%T@ %p\n' | sort -n | cut -f2 -d' '`; 
if [ $debug -gt 0 ]; then 
	echo "[antJd] pluginDirs:"; 
	for pluginDir in $pluginDirs; do echo "[antJd]   "$pluginDir; done
fi

### TODO?: missing emf/sdo/xsd plugins in $eclipseDir - need to copy them over or reference source so that all classes/packages (and thus @links) can be resolved

# All the jars in the plugins directory
classpath="."`find $eclipseDir/plugins -name "*.jar" -printf ":%p"`; if [ $debug -gt 0 ]; then echo "[antJd] classpath: "$classpath; fi

# Calculates the packagesets and the calls to copyDocFiles
packagesets="";
copydocfiles="";
for pluginDir in $pluginDirs; do
	pluginDir=`echo $pluginDir | sed -e 's/\/runtime$//g'`;
	for srcDir in $pluginDir/src $pluginDir/tasks; do
		if [ $debug -gt 0 ]; then echo "[antJd] srcDir: "$srcDir; fi
		if [ -d "$srcDir" ]; then
			# define what to include when javadoc'ing here:
			packagesets=$packagesets"<packageset dir=\"$srcDir\"> ";
			packagesets=$packagesets"<exclude name=\"$srcDir/**/doc-files/**\"/> ";
			packagesets=$packagesets""$javadocExclusions;
			packagesets=$packagesets"</packageset>";
			copydocfiles=$copydocfiles"<copyDocFiles pluginDir=\"$pluginDir\"/>";
		fi
	done
done
if [ $debug -gt 0 ]; then 
	echo "[antJd] packagesets:";	echo $packagesets;
	echo "[antJd] copydocfiles:";	echo $copydocfiles;
fi
	
# Finds the proper org.eclipse.platform.doc.isv jar (with full path)
docjar=`find $eclipseDir/plugins/ -name "org.eclipse.platform.doc.isv*.jar"`; if [ $debug -gt 1 ]; then echo "[antJd] docjar: "$docjar; fi

if [ -f $antScript ]; then
	true;
else
	cp $antScript $antScript.tmp;
fi

# do replacements
if [ $debug -gt 1 ]; then echo "[antJd] Replace @packagesets@ ..."; fi
packagesets=`echo $packagesets | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`;
sed -e "s/<\!-- @packagesets\@ -->/${packagesets}/g" $antScript.tmp > $antScript.tmp2;

if [ $debug -gt 1 ]; then echo "[antJd] Replace @copydocfiles@ ..."; fi
copydocfiles=`echo $copydocfiles | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`;
sed -e "s/<\!-- \@copydocfiles\@ -->/${copydocfiles}/g" $antScript.tmp2 > $antScript;

#run ant to do javadoc build
cmd="ant -f $antScript \
	-DdestDir=\"$destDir\" \
	-Dclasspath=\"$classpath\" \
	-DeclipseDir=\"$eclipseDir\" \
	-Ddocjar=\"$docjar\" \
	-DwindowTitle=\"$windowTitle\" \
	-DgroupTitle=\"$groupTitle\" \
	-Doverview=\"$currentPath/overview.html\"";

echo $cmd | sed -e 's/\-D/@/g' | tr "@" "\n";
$cmd;

# Clean up tempfiles
rm -f $antScript $antScript.tmp $antScript.tmp2;

# Generate topics_Reference.xml (replacement for doclet). 
trXML=$currentPath"/../topics_Reference.xml";
echo '<?xml version="1.0" encoding="UTF-8"?>' >> $trXML;
echo '<?NLS TYPE="org.eclipse.help.toc"?>' >> $trXML;
echo '<toc label="Reference">' >> $trXML;
echo '  <topic label="API Reference" href="references/javadoc/overview-summary.html">' >> $trXML;
for packSum in `find $destDir -name "package-summary.html"`; do
	path=${packSum%/package-summary.html}; path=${path#$destDir/}; # org/eclipse/xsd/ecore/importer/taskdefs
	label=${path//\//.}; # org.eclipse.xsd.ecore.importer.taskdefs
	echo '    <topic label="'$label'" href="references/javadoc/'$path'/package-summary.html" />' >> $trXML;
done
echo '  </topic>' >> $trXML;
echo '</toc>' >> $trXML;

