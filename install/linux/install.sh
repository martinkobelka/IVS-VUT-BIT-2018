#!/bin/bash
sudo mkdir /usr/lib/my_007_calculator
sudo cp -r ./assets* /usr/lib/my_007_calculator
sudo cp ./my_007_calculator /usr/bin/my_007_calculator
sudo cp ./icon.png /usr/lib/my_007_calculator/icon.png
sudo chmod 777 /usr/bin/my_007_calculator

while getopts "d" arg; do
  case $arg in
    d)

      sudo cp launcher_my_007_calculator.desktop ~/Desktop/launcher_my_007_calculator.desktop &> /dev/null
      sudo chmod 777 ~/Desktop/launcher_my_007_calculator.desktop &> /dev/null

      sudo cp launcher_my_007_calculator.desktop ~/Plocha/launcher_my_007_calculator.desktop &> /dev/null
      sudo chmod 777 ~/Plocha/launcher_my_007_calculator.desktop &> /dev/null
      ;;
  esac
done
