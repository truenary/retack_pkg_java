#!/bin/bash
generate_config_file() {
  local PUBLISHING_SERVER_ID=$1
  local SONATYPE_USERNAME=$2
  local SONATYPE_PASSWORD=$3
  local GPG_PASSPHRASE=$4

  # Check if all required arguments are provided
  if [ -z "$PUBLISHING_SERVER_ID" ] || [ -z "$SONATYPE_USERNAME" ] || [ -z "$SONATYPE_PASSWORD" ] || [ -z "$GPG_PASSPHRASE" ]; then
      echo "Usage: generate_config_file <PUBLISHING_SERVER_ID> <SONATYPE_USERNAME> <SONATYPE_PASSWORD> <GPG_PASSPHRASE>"
      exit 1
  fi

  # Define the location to create the temporary file
  local temp_settings_file="settings.xml"

  # Read the template file and replace placeholders with the provided values
  if ! sed -e "s/\$PUBLISHING_SERVER_ID/$PUBLISHING_SERVER_ID/g" \
          -e "s/\$SONATYPE_USERNAME/$SONATYPE_USERNAME/g" \
          -e "s/\$SONATYPE_PASSWORD/$SONATYPE_PASSWORD/g" \
          -e "s/\$GPG_PASSPHRASE/$GPG_PASSPHRASE/g" \
          TEMPLATE-Setting.txt > $temp_settings_file; then
      echo "Error: Failed to generate configuration file"
      exit 1
  fi

  echo "Configuration file created with the provided values."

  # Create the ~/.m2 directory if it doesn't exist
  if ! mkdir -p ~/.m2; then
      echo "Error: Failed to create ~/.m2 directory"
      rm -f $temp_settings_file
      exit 1
  fi

  # Move the generated settings.xml to ~/.m2 directory
  if ! mv $temp_settings_file ~/.m2/settings.xml; then
      echo "Error: Failed to move configuration file to ~/.m2 directory"
      rm -f $temp_settings_file
      exit 1
  fi

  echo "Configuration file moved to ~/.m2 directory."
}

# Call the function with arguments passed to the script
generate_config_file "$@"