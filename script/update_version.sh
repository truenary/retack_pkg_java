#!/bin/bash
# Usage: ./update_version.sh pom.xml 1.0.1

xml_file=$1
new_version=$2

if [ -z "$xml_file" ] || [ -z "$new_version" ]; then
    echo "Usage: $0 <xml-file> <new-version>"
    exit 1
fi

# Update the version in the pom.xml file
# Use sed to update only the first occurrence of the <version> tag under <project>
sed -i '0,/<version>.*<\/version>/s//<version>'"$new_version"'<\/version>/' $xml_file

echo "Updated version to $new_version in $xml_file"
