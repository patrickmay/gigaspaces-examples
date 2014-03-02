#!/bin/bash

# Start the GigaSpaces XAP infrastructure

. `dirname $0`/gs-setup.sh

export GSC_JAVA_OPTIONS=-Dcom.gs.zones=${SPACE_ZONE}
${JSHOMEDIR}/bin/gs-agent.sh gsa.gsc 4 gsa.lus 1 gsa.gsm 1 &

export GSC_JAVA_OPTIONS=-Dcom.gs.zones=${WEB_ZONE}
${JSHOMEDIR}/bin/gs-agent.sh gsa.gsc 2 gsa.lus 0 gsa.gsm 0 &
