#!/bin/bash

for file in ./*
do
    if [ -f $file ]&&[ ${file##*.} = "proto" ]
    then
        protoc -I=./ --java_out=../main/java/ $file
    fi
done