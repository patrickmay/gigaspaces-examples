#!/bin/bash

# Start the GigaSpaces XAP GUI

. `dirname $0`/gs-setup.sh

set JAVA_OPTIONS=-Xmx512m

${JSHOMEDIR}/bin/gs-ui.sh &
