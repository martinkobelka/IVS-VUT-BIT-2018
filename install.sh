#!/bin/bash
mkdir /usr/lib/my_007_calculator
cp -r ./out/pack/* /usr/lib/my_007_calculator
cp ./my_007_calculator /usr/bin/my_007_calculator
chmod 777 /usr/bin/my_007_calculator
