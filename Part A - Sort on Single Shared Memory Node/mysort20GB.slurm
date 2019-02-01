#!/bin/sh
#SBATCH --nodes=1
#SBATCH --output=mysort20gb.log
java  -Xms2g -Xmx6g MyExternalSort /input/data-20GB.in /tmp/MySort20gbOutput 1

valsort /tmp/MySort20gbOutput
