#!/bin/bash

# Write, read, and take Swag objects into and from the template example space

. `dirname $0`/gs-setup.sh

java -cp ${REQUIRED_DIR}/*:`dirname $0`/../lib/cacher.jar \
     com.gigaspaces.examples.cache.Cacher \
     "jini://*/*/${EXAMPLE_SPACE_NAME}?groups=${LOOKUPGROUPS}" 10000
