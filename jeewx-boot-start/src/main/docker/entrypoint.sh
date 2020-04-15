#!/bin/sh

echo "The application will start in ${KONGQI_SLEEP}s..." && sleep ${KONGQI_SLEEP}
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar "${HOME}/app.jar" "$@"
