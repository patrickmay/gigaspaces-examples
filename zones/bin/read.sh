#!/bin/bash

# Read SKU objects from the example space

. `dirname $0`/gs-setup.sh

java -jar `dirname $0`/../lib/reader.jar \
     "jini://*/*/${EXAMPLE_SPACE_NAME}?groups=${LOOKUPGROUPS}"
