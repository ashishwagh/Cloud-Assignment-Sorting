#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=HadoopSort20GB.log
START_TIME=$SECONDS
LC_ALL=C hadoop jar HadoopSort.jar HadoopSort -D mapred.reduce.tasks=1 /input/data-20GB /user/awagh/output-hadoop-20gb
ELAPSED_TIME=$(($SECONDS - $START_TIME))
echo "Total Time (seconds):"
echo "$ELAPSED_TIME"
hadoop jar /opt/hadoop-2.9.0/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.9.0.jar teravalidate /user/awagh/output-hadoop-20gb /user/awagh/report-hadoop-20gb
hadoop fs -get /user/awagh/report-hadoop-20gb/part-r-00000

