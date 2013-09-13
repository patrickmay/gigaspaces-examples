#!/bin/bash

# Deploy the template example

. `dirname $0`/gs-setup.sh

${JSHOMEDIR}/bin/gs.sh deploy `dirname $0`/../lib/executor-space.jar
