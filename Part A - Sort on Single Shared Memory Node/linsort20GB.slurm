#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=linsort20GB.log
START_TIME=$SECONDS
LC_ALL=C sort -o /tmp/linsort20gboutput /input/data-20GB.in
ELAPSED_TIME=$(($SECONDS - $START_TIME))
echo "Total Time (seconds):"
echo "$ELAPSED_TIME"
valsort /tmp/linsort20gboutput
