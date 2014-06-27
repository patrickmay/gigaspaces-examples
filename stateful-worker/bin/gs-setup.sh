#!/bin/bash

# GigaSpaces XAP setup

DEFAULT_JSHOMEDIR=/usr/local/gigaspaces-xap-premium-9.7.1-ga
if [ -z "$JSHOMEDIR" ]; then
  export JSHOMEDIR=$DEFAULT_JSHOMEDIR
fi  
export GS_HOME=${JSHOMEDIR}

export PATH=${JSHOMEDIR}/bin:${PATH}

export NIC_ADDR=localhost
export LOOKUPLOCATORS=${NIC_ADDR}
export LOOKUPGROUPS="stateful-worker-example"

export EXAMPLE_SPACE_NAME="stateful-worker-space"

${JSHOMEDIR}/bin/setenv.sh

REQUIRED_DIR="${JSHOMEDIR}/lib/required"
