#!/bin/bash

# GigaSpaces XAP setup

DEFAULT_JSHOMEDIR=/usr/local/gigaspaces-xap-premium-9.7.0-ga
if [ -z "$JSHOMEDIR" ]; then
  export JSHOMEDIR=$DEFAULT_JSHOMEDIR
fi  
export GS_HOME=${JSHOMEDIR}

export PATH=${JSHOMEDIR}/bin:${PATH}

export NIC_ADDR=localhost
#export LOOKUPLOCATORS=${NIC_ADDR}
export LOOKUPGROUPS="zone-group"

export EXAMPLE_SPACE_NAME="zone-space"

export SPACE_ZONE="space-zone"
export WEB_ZONE="web-zone"

${JSHOMEDIR}/bin/setenv.sh

