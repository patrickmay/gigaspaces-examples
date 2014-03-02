#!/bin/bash

# Deploy the REST API example

. `dirname $0`/gs-setup.sh

${JSHOMEDIR}/bin/gs.sh deploy-space \
    -cluster schema=partitioned-sync2backup total_members=2,1 \
    -max-instances-per-vm 1 \
    -zones ${SPACE_ZONE} \
    ${EXAMPLE_SPACE_NAME}

${JSHOMEDIR}/bin/gs.sh deploy \
    -cluster total_members=2 \
    -max-instances-per-vm 1 \
    -zones ${WEB_ZONE} \
    `dirname $0`/../lib/rest-api.war
