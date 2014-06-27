#!/bin/bash

# Feed Request objects into the template example space

. `dirname $0`/gs-setup.sh

java -cp ${REQUIRED_DIR}/*:`dirname $0`/../lib/feeder.jar \
     com.gigaspaces.examples.stateful.feeder.Feeder \
     "jini://*/*/${EXAMPLE_SPACE_NAME}?groups=${LOOKUPGROUPS}" \
     100000 3 100
