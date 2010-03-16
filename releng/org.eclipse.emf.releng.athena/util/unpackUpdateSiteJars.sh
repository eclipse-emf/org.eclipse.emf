#!/bin/bash
# unpack packed jars in an update site

if [[ $1 ]]; then
	updateDir="$1";
else
	echo "Must specify dir to search and unpack, eg."
	echo " $0 /home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.6"
	exit 1;
fi

# if you built with JDK6, you must use JDK6 to unpack; this assumes JDK5 was used
unpack=/shared/common/ibm-java2-ppc-50/jre/bin/unpack200

for j in $(find $updateDir -maxdepth 2 -type f -name "*.pack.gz"); do 
	k=${j/.pack.gz/}; #echo $k; 
	$unpack $j $k
done
