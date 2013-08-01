#!/bin/bash

# Start GigaSpaces XAP infrastructure

. `dirname $0`/gs-setup.sh

${JSHOMEDIR}/bin/gs-agent.sh gsa.gsc 2 gsa.lus 1 gsa.gsm 1 &
