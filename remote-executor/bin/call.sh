#!/bin/bash

# Invoke the remote executor

. `dirname $0`/gs-setup.sh

java -cp ${REQUIRED_DIR}/*:`dirname $0`/../lib/caller.jar \
     com.gigaspaces.examples.executor.client.Student \
     "jini://*/*/${EXAMPLE_SPACE_NAME}?groups=${LOOKUPGROUPS}"

