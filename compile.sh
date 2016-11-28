#!/usr/bin/env bash


mvn    clean  compile  package  -Dmaven.test.skip=true

#mvn assembly:assembly  -DskipTests=true

# mvn  clean   install -U  -Dmaven.test.skip=true
