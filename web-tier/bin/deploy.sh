#!/bin/bash

# Deploy the REST API example

. `dirname $0`/gs-setup.sh

${JSHOMEDIR}/bin/gs.sh deploy `dirname $0`/../lib/rest-api.war
