#!/usr/bin/env bash

# example:./newVersion.sh 1.0.0-SNAPSHOT

mvn versions:set -DnewVersion=$1   -DprocessAllModules=true -DallowSnapshots=true versions:commit