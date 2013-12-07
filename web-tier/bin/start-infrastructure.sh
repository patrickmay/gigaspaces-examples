#!/bin/bash

# Start the GigaSpaces XAP infrastructure

. `dirname $0`/gs-setup.sh

${JSHOMEDIR}/bin/gs-agent.sh gsa.gsc 4 gsa.lus 1 gsa.gsm 1 &
