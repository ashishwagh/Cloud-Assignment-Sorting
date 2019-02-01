#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=SparkSort20GB.log
START_TIME=$SECONDS
LC_ALL=C spark-submit --class SparkSort --master yarn --deploy-mode client --driver-memory 1g --executor-memory 1g --executor-cores 1 --num-executors 1 SparkSort.jar /input/data-20GB /user/awagh/output-spark-20gb
ELAPSED_TIME=$(($SECONDS - $START_TIME))
echo "Total Time (seconds):"
echo "$ELAPSED_TIME"
hadoop jar /opt/hadoop-2.9.0/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.9.0.jar teravalidate /user/awagh/output-spark-20gb /user/awagh/report-spark-20gb
hadoop fs -get /user/awagh/report-spark-20gb/part-r-00000
