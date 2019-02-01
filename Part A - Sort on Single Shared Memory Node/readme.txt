*********************************************************************************
CS553
Readme file _PA2
*********************************************************************************

Login to the Neutron account using $ ssh user name@216.47.142.38 and password.All code will be there in cs553-pa2 folder.

*********************************************************************************
Sort on Single Shared Memory Node 
*********************************************************************************
Step 1:Go to cs553-pa2a folder using $cd /cs553-pa2a.
Step 2:Compile the progrom using the Makefile i.e. using command $make.Contains of the make file are:
	JCC = javac

	default:
		$(JCC) MyExternalSort.java
	clean:
		$(RM) *.class

Step 3:There will be files named mysort2GB.slurm,mysort20GB.slurm,linsort2GB.slurm and linsort20GB.slurm.Below are the sample contains of mysort2GB.slurm file
	#!/bin/sh
	#SBATCH --nodes=1
	#SBATCH --output=mysort2gb.log
	java  -Xms1024m -Xmx4g MyExternalSort /input/data-2GB.in /tmp/MySort2gbOutput 1 -->command line argument (input file,output file and number of threads)

	valsort /tmp/MySort2gbOutput -->for running valsort command on output data

Step 5:Output file of sorted record will be generated in the tmp folder.
Step 6:Use $sbatch mysort2GB.slurm command for scheduling the job.
Step 7:Output files will generated in the same folder with file name like linsort2GB.log,linsort20GB.log,mysort2gb.log,mysort20gb.log.
Step 8:Below is the output file shared memory 2 gb data (filename=mysort2gb.log)
	Total Time Taken(seconds): 80.312
	Records: 20000000
	Checksum: 98923c23abc63a
	Duplicate keys: 0
	SUCCESS - all records are in order


