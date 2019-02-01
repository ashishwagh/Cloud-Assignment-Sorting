#!/bin/sh
#SBATCH --nodes=1
#SBATCH --output=mysort2gb.log
java  -Xms1024m -Xmx4g MyExternalSort /input/data-2GB.in /tmp/MySort2gbOutput 1

valsort /tmp/MySort2gbOutput
