# The current direcotry
currentPath=$PWD

# The eclipse directory
eclipseDir=$1

# The destination directory
destDir=$eclipseDir/plugins/org.eclipse.xsd.doc/references/javadoc

# Don't execute if the destination directory has files
if [ -d $destDir ]; then
	exit
fi

# The directory of the xsd plugins in the order they were built 
pluginDirs=`find $eclipseDir/plugins -name *.jar -printf '%T@ %p\n' | sort -n | grep -v resources.jar | egrep -e 'org.eclipse.xsd|org.eclipse.emf.mapping.xsd2ecore' | cut -f2 -d' ' | sed -e 's/\(\/.*\)\/.*/\1/'`

# All the jars in the pluigins directory
classpath=`find $eclipseDir/plugins -name *.jar -print | grep -v org.eclipse.xsd | grep -v org.eclipse.emf.mapping.xsd2ecore | tr '\n' ';'`

# Calculates the packagesets
packagesets=""
for pluginDir in $pluginDirs; do
	pluginDir=`echo $pluginDir | sed -e 's/\/runtime$//g'`
	srcDir=$pluginDir/src
	if [ -d $srcDir ]; then
		packagesets=$packagesets"<packageset dir=\"$srcDir\"/>"
	fi
done

# Replaces the token @packagesets@ in the template by the actual value
packagesets=`echo $packagesets | sed -e 's/\//\\\\\\//g' | sed -e 's/\./\\\\\./g'`
sed -e "s/\@packagesets\@/${packagesets}/g" $currentPath/javadoc.xml.template > javadoc.xml

# Executes the ant script
ant	-f javadoc.xml \
	-DdestDir="$destDir" \
	-Dclasspath="$classpath" \
	-Doverview="$eclipseDir/plugins/org.eclipse.xsd.doc/build/overview.html"
