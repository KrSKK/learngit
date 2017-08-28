#!/bin/bash

rm -rf ../main/java/com/zzx/protoClasses/*

for file in ./*
do
    if [ -f $file ]&&[ ${file##*.} = "proto" ]
    then
        protoc -I=./ --java_out=../main/java/ $file
    fi
done