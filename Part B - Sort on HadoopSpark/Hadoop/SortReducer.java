import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class SortReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> val, Context context) throws IOException, InterruptedException {
		for (Text txt : val) {
			context.write(key, new Text(txt.toString().substring(1)+"\r"));
		}
	}

}
