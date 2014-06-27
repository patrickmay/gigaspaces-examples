#!/bin/bash

# GigaSpaces XAP setup

DEFAULT_JSHOMEDIR=/usr/local/gigaspaces-xap-premium-9.7.1-ga
if [ -z "$JSHOMEDIR" ]; then
  export JSHOMEDIR=$DEFAULT_JSHOMEDIR
fi  
export GS_HOME=${JSHOMEDIR}

export PATH=${JSHOMEDIR}/bin:${PATH}

#export NIC_ADDR=127.0.0.1
#export LOOKUPLOCATORS=${NIC_ADDR}
export LOOKUPGROUPS="simple-cache-example"

export EXAMPLE_SPACE_NAME="simple-cache-space"

${JSHOMEDIR}/bin/setenv.sh

REQUIRED_DIR="${JSHOMEDIR}/lib/required"
