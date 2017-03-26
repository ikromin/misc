#!/bin/bash

#
# This script generates <package-name> elements for all packages in all jar
# files in a directory. See the <container-descriptor> documentation for
# WebLogic here:
# https://docs.oracle.com/cd/E24329_01/web.1211/e21049/weblogic_xml.htm#WBAPP592
#
# See the following blog post for an example use case: http://kr0m.in/6sl2Boh
#
# Written by Igor Kromin on 26 March 2017
#

if [ -d "$1" ]; then
	dir="$1"
	tmpfile=$(mktemp)

	for jar in "$dir"/*.jar; do
		echo Processing $jar
		zipinfo -1 "$jar" | grep .class | xargs -I{} dirname {} >> "$tmpfile"
	done

	sort -u "$tmpfile" | sed s:/:.:g > "$tmpfile".tmp
	mv "$tmpfile".tmp "$tmpfile"

	lnum=1
	while : ; do
		line=$(sed "${lnum}q;d" "$tmpfile")
        	if [ -z "$line" ]; then
                	break
        	fi

        	echo Adding package $line

        	ctr=$(grep -c "$line" "$tmpfile")
        	if [ $ctr -gt 1 ]; then
                	echo Filtering out redundant sub-packages
                	grep -v "$line\." "$tmpfile" > "$tmpfile".tmp
                	mv "$tmpfile".tmp "$tmpfile"
        	fi

        	((lnum++))
	done

	sed -E -i.bak "s:^(.+)$:<package-name>\1.*</package-name>:" "$tmpfile"
	rm "$tmpfile".bak
	echo Output saved to $tmpfile
else
	echo Provide a directory name
fi
