#!/bin/bash

# Feed SKU objects into the example space

. `dirname $0`/gs-setup.sh

java -jar `dirname $0`/../lib/feeder.jar \
     "jini://*/*/${EXAMPLE_SPACE_NAME}?groups=${LOOKUPGROUPS}"
