#!/bin/bash

# GigaSpaces XAP setup

DEFAULT_JSHOMEDIR=/usr/local/gigaspaces-xap-premium-9.6.2-ga
if [ -z "$JSHOMEDIR" ]; then
  export JSHOMEDIR=$DEFAULT_JSHOMEDIR
fi  
export GS_HOME=${JSHOMEDIR}

export PATH=${JSHOMEDIR}/bin:${PATH}

export NIC_ADDR=localhost
#export LOOKUPLOCATORS=${NIC_ADDR}
export LOOKUPGROUPS="rest-api"

export EXAMPLE_SPACE_NAME="rest-api-space"

${JSHOMEDIR}/bin/setenv.sh

