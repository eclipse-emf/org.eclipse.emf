#!/bin/bash

# run java to launch JUnit standalone with minimum jars:
#	JUnit
#	EMF version of eclipse.test.performance
#	EMF performance tests
#	EMF/SDO/XSD standalone

# for testing...

# replace eclipse.test.performance or emf.test.performance jars:
# cp /home/ibm/workspace1/org.eclipse.emf.test.performance/test.performance.jar 2.1.0/test.performance.jar; \
# cp /home/ibm/workspace1/org.eclipse.test.performance/testperformance_emf.jar lib/testperformance_emf.jar; \

# then run tests:
# /opt/ibm-java2-1.4/bin/java -showversion -Xj9 -Xms256M -Xmx400M -classpath /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/lib/junit.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/lib/testperformance_emf.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/test.performance.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.common.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.ecore.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.ecore.change.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.ecore.sdo.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.commonj.sdo.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.ecore.xmi.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/xsd.jar \
# junit.textui.TestRunner \
# org.eclipse.emf.test.performance.AllSuites;

# or run as zombie process logging to file: 
# bash -c "exec nohup setsid /opt/ibm-java2-1.4/bin/java -showversion -Xj9 -Xms256M -Xmx400M -classpath /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/lib/junit.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/lib/testperformance_emf.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/test.performance.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.common.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.ecore.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.ecore.change.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.ecore.sdo.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.commonj.sdo.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/emf.ecore.xmi.jar:\
# /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/2.1.0/xsd.jar \
# junit.textui.TestRunner \
# org.eclipse.emf.test.performance.AllSuites \
# 2>&1 > /home/www-data/perftests-standalone/2.1.0/R200507070200/200507171759/testlog.txt" &

# default eclipse.org username
user=$USER;

#default build branch/buildBranchAndID
buildBranchAndID="";

#default tests branch/buildBranchAndID
testBranchAndID="";

#noclean=0 (purge temp files) or 1 (keep them)
noclean=0;

#default CVS quietness
quietCVS=-Q;

#java vm and args
vm="/opt/ibm-java2-1.4/bin/java";
vmargs="-Xj9 -Xms512M -Xmx512M";

function doInstructions() 
{
	echo "usage: promoteToEclipse.sh"
	echo "-user     <cvs user for emf.torolab.ibm.com and dev.eclipse.org (default: $USER)>"
	echo "-build    <build to be tested, eg., 2.0.1/R200409171617> (required)"
	echo "-test     <build containing tests (if different), eg., 2.0.3/R200506091052> (optional)"
	echo "-noclean  <don't clean up when done (leave /tmp folder)>"
	echo "-email    <email to notify when promotion is complete>"
	echo " "
	echo "Example:"
	echo "bash -c \"exec nohup setsid ./perf-standalone.sh -build 2.1.0/R200507070200 \\"
	echo "  -user nickb -email codeslave@ca.ibm.com -noclean \\"
	echo "  2>&1 > ~/log_\`date +%Y%m%d_%H%M%S\`.txt\" &"

}

if [ $# -lt 1 ]; then
	doInstructions;
	exit 1
fi

# Create local variable based on the input
echo " "
echo "[perf] Started `date`. Executing with the following options:"
while [ "$#" -gt 0 ]; do
	case $1 in
		'-user')
			user=$2;
			echo "   -user $user";
			shift 1
			;;
		'-build')
			buildBranchAndID=$2;
			echo "   -build $buildBranchAndID";
			shift 1
			;;
		'-test')
			testBranchAndID=$2;
			echo "   -test $testBranchAndID";
			shift 1
			;;
		'-email')
			email=$2;
			echo "   -email $email";
			shift 1
			;;
		'-noclean')
			noclean=1;
			echo "   -noclean";
			shift 0
			;;
	esac
	shift 1
done

if [ "x$buildBranchAndID" = "x" ]; then
	doInstructions;
	exit 1
fi

#cvs rep for org.eclipse.test.performance (emf version)
CVSRep=:ext:$user@emf.torolab.ibm.com:/home/cvs
ProdCVSRep=:ext:$user@dev.eclipse.org:/home/tools

#test timstamp
testTimestamp=`date +%Y%m%d%H%M`

#default working dir
workingDir=/home/www-data/perftests-standalone/$buildBranchAndID/$testTimestamp;
mkdir -p $workingDir;
cd $workingDir;

if [ "x$testBranchAndID" = "x" ]; then
	testBranchAndID=$buildBranchAndID;
fi

branch=${buildBranchAndID%%/*}; #  get 2.1.0 from 2.1.0/R200507070200
buildID=${buildBranchAndID##*/}; #  get R200507070200 from 2.1.0/R200507070200
testID=${testBranchAndID##*/}; #  get R200507070200 from 2.1.0/R200507070200
#echo "branch = $branch";

# 1. check out lib jars
echo "[perf] [`date +%H:%M:%S`] check out lib jars ..."
cd $workingDir;
cvs -d $CVSRep $quietCVS co -P -d tmp org.eclipse.test.performance;
mkdir -p $workingDir/lib;
mv tmp/testperformance_emf.jar tmp/junit.jar lib/;
rm -fr tmp;

#get data/ folder from org.eclipse.emf.test.performance on dev
echo "[perf] [`date +%H:%M:%S`] check out org.eclipse.emf.test.performance/data"
cd $workingDir;
cvs -d $ProdCVSRep $quietCVS co -P -d data org.eclipse.emf/tests/org.eclipse.emf.test.performance/data;

echo -n "[perf] [`date +%H:%M:%S`] Running tests on build $buildBranchAndID";
if [ "$buildBranchAndID" != "$testBranchAndID" ]; then
	echo -n ", with tests from $testBranchAndID";
fi
echo " ...";

echo "";

function getZip()
{
	pre=$1;
	branch=$2;
	ID=$3;
	webdir=tools/emf/downloads/drops/$branch/$ID
	basedir=/home/www-data/emf-build/$webdir
	if [ ! -e $basedir ]; then
		mkdir -p $basedir;
	fi
	cd $basedir;
	zip=`find $basedir -name "$pre*.zip" -type f | tail -1`
	if  [ "x$zip" = "x" ] || [ ! -e $zip ]; then
		echo -n "[perf] [`date +%H:%M:%S`] wget ";
		wget -nv http://fullmoon.torolab.ibm.com/$webdir/$pre$branch.zip; # R build
		zip=`find $basedir -name "$pre*.zip" -type f | tail -1`
		if [ "x$zip" = "x" ] || [ ! -e $zip ]; then
			echo -n "[perf] [`date +%H:%M:%S`] wget ";
			wget -nv http://fullmoon.torolab.ibm.com/$webdir/$pre$ID.zip # I/M build
			zip=`find $basedir -name "$pre*.zip" -type f | tail -1`
			if [ "x$zip" = "x" ] || [ ! -e $zip ]; then
				echo -n "[perf] [`date +%H:%M:%S`] wget ";
				wget -nv http://emf.torolab.ibm.com/$webdir/$pre$ID.zip # N build
				zip=`find $basedir -name "$pre*.zip" -type f | tail -1`
				if [ "x$zip" = "x" ] || [ ! -e $zip ]; then
					echo "[perf] ERROR: couldn't download $pre*.zip!"
					echo "[perf] Exit: 1"
					exit 1;
				fi
			fi
		fi
	fi
}

if [ "$branch" = "2.0.1" ] || [ "$branch" = "2.0.2" ] || [ "$branch" = "2.0.3" ]; then

	mkdir -p $workingDir/$branch;
	cd $workingDir;

	# 2. unpack SDK zip, need only jars
	echo "[perf] [`date +%H:%M:%S`] unpack jars from SDK zip ..."
	getZip emf-sdo-xsd-SDK- $branch $buildID
	cd $workingDir/$branch;
	unzip -uoj -qq /home/www-data/emf-build/tools/emf/downloads/drops/$buildBranchAndID/emf-sdo-xsd-SDK-*.zip *.jar;

	cd $workingDir;

	# 3. unpack test.performance.jar (JUnit tests)
	echo "[perf] [`date +%H:%M:%S`] unpack test.performance.jar (JUnit tests) from Automated-Tests zip ..."
	getZip emf-sdo-xsd-Automated-Tests- $branch $testID
	cd $workingDir/$branch;
	unzip -uoj -qq /home/www-data/emf-build/tools/emf/downloads/drops/$testBranchAndID/emf-sdo-xsd-Automated-Tests-*.zip testing/emf-sdo-xsd-JUnit-Tests-*.zip;
	unzip -uoj -qq emf-sdo-xsd-JUnit-Tests-*.zip eclipse/plugins/org.eclipse.emf.test.performance_*/test.performance.jar;
	rm -fr emf-sdo-xsd-JUnit-Tests-*.zip

	cd $workingDir;
	
	# fix permissions 
	find $workingDir -name "*.jar" -exec chmod 644 {} \;

	cmd="$vm $vmargs -classpath $workingDir/lib/junit.jar:$workingDir/lib/testperformance_emf.jar:$workingDir/$branch/test.performance.jar:\
$workingDir/$branch/common.jar:$workingDir/$branch/common.resources.jar:$workingDir/$branch/ecore.sdo.jar:$workingDir/$branch/commonj.sdo.jar:\
$workingDir/$branch/ecore.jar:$workingDir/$branch/ecore.change.jar:$workingDir/$branch/ecore.resources.jar:\
$workingDir/$branch/ecore.xmi.jar:$workingDir/$branch/xsd.jar:$workingDir/$branch/xsd.resources.jar \
junit.textui.TestRunner org.eclipse.emf.test.performance.AllSuites";

	echo "[perf] [`date +%H:%M:%S`] Run junit.textui.TestRunner ...";
	echo "";
	vmver=`$vm -version 2>&1`;
	
	#echo to shell log
	echo $vmver;
	echo $cmd; echo "  2>&1 > $workingDir/testlog.txt"; echo ""; 
	
	#echo to test log
	echo $vmver > $workingDir/testlog.txt; 
	echo $cmd >> $workingDir/testlog.txt;
	
	#run java, echo to test log
	$cmd 2>&1 >> $workingDir/testlog.txt; 
elif  [ "$branch" = "2.1.0" ] || [ "$branch" = "2.1.1" ] || [ "$branch" = "2.2.0" ]; then

	mkdir -p $workingDir/$branch;
	cd $workingDir;

	# 2a. unpack Standalone zip
	echo "[perf] [`date +%H:%M:%S`] unpack jars from Standalone zip ..."
	getZip emf-sdo-xsd-Standalone- $branch $buildID
	cd $workingDir/$branch;
	unzip -uoj -qq /home/www-data/emf-build/tools/emf/downloads/drops/$buildBranchAndID/emf-sdo-xsd-Standalone-*.zip *.jar;

	#2b. rename foo_2.1.0.jar to foo.jar
	echo "[perf] [`date +%H:%M:%S`] remove version from jar names (eg., xsd_2.1.0.jar -> xsd.jar) ..."
	jars=`find $workingDir/$branch -name "*.jar"`
	for jar in $jars; do
		mv $jar ${jar%%\_*}.jar
	done

	cd $workingDir;

	# 3. unpack test.performance.jar (JUnit tests)
	echo "[perf] [`date +%H:%M:%S`] unpack test.performance.jar (JUnit tests) from Automated-Tests zip ..."
	getZip emf-sdo-xsd-Automated-Tests- $branch $testID
	cd $workingDir/$branch;
	unzip -uoj -qq /home/www-data/emf-build/tools/emf/downloads/drops/$testBranchAndID/emf-sdo-xsd-Automated-Tests-*.zip testing/emf-sdo-xsd-JUnit-Tests-*.zip;
	unzip -uoj -qq emf-sdo-xsd-JUnit-Tests-*.zip eclipse/plugins/org.eclipse.emf.test.performance_*/test.performance.jar;
	rm -fr emf-sdo-xsd-JUnit-Tests-*.zip;

	cd $workingDir;

	# fix permissions 
	find $workingDir -name "*.jar" -exec chmod 644 {} \;

	cmd="$vm $vmargs -classpath $workingDir/lib/junit.jar:$workingDir/lib/testperformance_emf.jar:$workingDir/$branch/test.performance.jar:\
$workingDir/$branch/emf.common.jar:$workingDir/$branch/emf.ecore.jar:$workingDir/$branch/emf.ecore.change.jar:\
$workingDir/$branch/emf.ecore.sdo.jar:$workingDir/$branch/emf.commonj.sdo.jar:\
$workingDir/$branch/emf.ecore.xmi.jar:$workingDir/$branch/xsd.jar \
junit.textui.TestRunner org.eclipse.emf.test.performance.AllSuites";

	echo "[perf] [`date +%H:%M:%S`] Run junit.textui.TestRunner ...";
	echo "";
	vmver=`$vm -version 2>&1`;
	
	#echo to shell log
	echo $vmver; echo "";
	echo $cmd; echo "  2>&1 > $workingDir/testlog.txt"; echo ""; 
	
	#echo to test log
	echo $vmver > $workingDir/testlog.txt; 
	echo "" >> $workingDir/testlog.txt; 
	echo $cmd >> $workingDir/testlog.txt;
	echo "" >> $workingDir/testlog.txt;
	
	#run java, echo to test log
	$cmd 2>&1 >> $workingDir/testlog.txt; 
else
	echo "Branch $branch is not supported yet.";
fi

echo "";

if [ $noclean -eq 0 ]; then
	# cleanup temp space
	rm -fr $workingDir/$branch;
	rm -fr $workingDir/data;
	rm -fr $workingDir/lib;
	echo "[perf] Temporary files purged from $workingDir/.";
else
	echo "[perf] Temporary files left in $workingDir/. Please scrub them manually when done.";
fi

PHP=php4
if [ -x /usr/bin/php4 ]; then
	PHP=/usr/bin/php4
elif [ -x /usr/bin/php ]; then
	PHP=/usr/bin/php
else
	PHP=php
fi

# send email notification?
if [ "x$email" != "x" ]; then
	$PHP -q $buildScriptsDir/sendEmail-promote.php -email $email -branch $branch -buildBranchAndID $buildBranchAndID;
fi

echo "[perf] Done `date`";
echo "";


