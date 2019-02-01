import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text val, Context context) throws IOException, InterruptedException {

		Text opKey = new Text(val.toString().substring(0, 10));
		Text opVal = new Text(val.toString().substring(opKey.getLength()));
		context.write(opKey, opVal);
	}
}

