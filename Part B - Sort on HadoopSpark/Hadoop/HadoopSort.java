import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class HadoopSort extends Configured implements Tool{
	
	public static void main(String[] args) throws Exception {
        int returnVal = ToolRunner.run(new Configuration(), new HadoopSort(), args);
        System.exit(returnVal);
    }

	 @Override
	 public int run(String[] args) throws Exception {
		 int returnVal = 0;
		if(args ==null || args.length < 2 || args.length >2)
		{	
			System.out.println("Please provide the two arguments 1.Input file name 2.Output file name.");
			return 0;
		}else {
			Configuration conf = getConf();
			conf.set("mapreduce.output.textoutputformat.separator", " ");
			conf.set("mapreduce.output.fileoutputformat.compress", "true");
			
			FileSystem fs = FileSystem.get(conf);
			if(fs.exists(new Path(args[1]))){
				fs.delete(new Path(args[1]), true);
			}

			Job hjob = Job.getInstance(conf);
			hjob.setJobName("ExternalHadoopSort");
			hjob.setJarByClass(HadoopSort.class);
			hjob.setPartitionerClass(CustSortPart.class);

			hjob.setNumReduceTasks(50);
			hjob.setMapOutputKeyClass(Text.class);
			hjob.setMapOutputValueClass(Text.class);

			hjob.setMapperClass(SortMapper.class);
			
			hjob.setReducerClass(SortReducer.class);

			FileInputFormat.addInputPath(hjob, new Path(args[0]));
			FileOutputFormat.setOutputPath(hjob, new Path(args[1]));

			returnVal = hjob.waitForCompletion(true)?0:1;			
			return returnVal;
		}
	}
	 
}
