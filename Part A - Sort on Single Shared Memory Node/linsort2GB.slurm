#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=linsort2GB.log
START_TIME=$SECONDS
LC_ALL=C sort -o /tmp/linsort2gboutput /input/data-2GB.in
ELAPSED_TIME=$(($SECONDS - $START_TIME))
echo "Total Time (seconds):"
echo "$ELAPSED_TIME"
valsort /tmp/linsort2gboutput
