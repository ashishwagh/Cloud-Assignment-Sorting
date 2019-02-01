#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=HadoopSort8GB.log
START_TIME=$SECONDS
LC_ALL=C hadoop jar HadoopSort.jar HadoopSort -D mapred.reduce.tasks=1 /input/data-8GB /user/awagh/output-hadoop-8gb
ELAPSED_TIME=$(($SECONDS - $START_TIME))
echo "Total Time (seconds):"
echo "$ELAPSED_TIME"
hadoop jar /opt/hadoop-2.9.0/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.9.0.jar teravalidate /user/awagh/output-hadoop-8gb /user/awagh/report-hadoop-8gb
hadoop fs -get /user/awagh/report-hadoop-8gb/part-r-00000
