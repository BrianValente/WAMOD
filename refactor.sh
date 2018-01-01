#!/usr/bin/env bash

super='.super Landroid/support/v4/app/Fragment;'
sed='s/A()V/onResume()V/g'

xd () {
    # $1: Class
    local xd_super=".super $1"
    grep -rl "$xd_super" ./smali/* | while read XD_FILE
    do
        local xd_classLine=$(cat $XD_FILE | grep -F ".class ")
        local xd_className=$(lastStringWord "$xd_classLine")
        echo "Modifying class: $xd_className"
        sed -i. $sed $XD_FILE
        xd $xd_className
    done
}

lastStringWord () {
    echo $1 | awk '{print $NF}'
}

grep -rl "$super" ./smali/* | while read FILE
do
    classLine=$(cat $FILE | grep -F ".class ")
    className=$(lastStringWord "$classLine")
    echo "Modifying class: $className"
    sed -i. $sed $FILE
    xd $className
done

