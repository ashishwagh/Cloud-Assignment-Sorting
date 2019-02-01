import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustSortPart extends Partitioner<Text, Text>{

	@Override
	public int getPartition(Text key, Text val, int numPart) {
		if(numPart == 0 || numPart == 1 || key.charAt(0) < 32)
			return 0;
		else if (key.charAt(0) >126){
			return numPart -1;
		}
/*
		else if(key.charAt(0)>=33 && key.charAt(0)<=47) {
			int cntPart1=15;
			int part = ((int)Math.ceil(cntPart1/numPart))+1;
			int partition  = (int)Math.floor((key.charAt(0)-32)/part);
			return partition;
	}else if(key.charAt(0)>=48 && key.charAt(0)<=64){
			int cntPart2=17;
			int part = ((int)Math.ceil(cntPart2/numPart))+1;
			int partition  = (int)Math.floor((key.charAt(0)-32)/part);
			return partition;
	}*/
else{
			int cntPart3 = 95;
			int part = ((int)Math.ceil(cntPart3/numPart))+1;
			int partition  = (int)Math.floor((key.charAt(0)-32)/part);
			return partition;
		}
	}

}
