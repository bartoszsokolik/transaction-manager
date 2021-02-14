#!/bin/bash
java -agentlib:jdwp=transport=dt_socket,server=y,address=5005,suspend=n -XX:+PrintFlagsFinal -Xlog:gc* -Xms256m -Xmx1024m -jar service.jar
