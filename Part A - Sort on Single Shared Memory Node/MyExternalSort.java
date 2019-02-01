import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyExternalSort {

	static List<File> imdFilesList = new ArrayList<File>();
	static int cnt = 0;
	static String[] imdDataArr;
	static int totNumLine = 0;
	
	public static void imdMergeSort(String[] tmpPassed) {
		if (tmpPassed.length > 1) {
			String[] lower = lowerHalf(tmpPassed);
			String[] upper = upperHalf(tmpPassed);
			imdMergeSort(lower);
			imdMergeSort(upper);
			merge(tmpPassed, lower, upper);
		}
	}

	public static String[] lowerHalf(String[] tmpLeft) {
		int size1 = tmpLeft.length / 2;
		String[] lower = new String[size1];
		for (int i = 0; i < size1; i++) {
			lower[i] = tmpLeft[i];
		}
		return lower;
	}

	public static String[] upperHalf(String[] tmpRight) {
		int size1 = tmpRight.length / 2;
		int size2 = tmpRight.length - size1;
		String[] upper = new String[size2];
		for (int i = 0; i < size2; i++) {
			upper[i] = tmpRight[i + size1];
		}
		return upper;
	}

	public static void merge(String[] tmpFinal, String[] lower, String[] upper) {
		int i1 = 0;
		int i2 = 0;

		for (int i = 0; i < tmpFinal.length; i++) {
			if (i2 >= upper.length || (i1 < lower.length && lower[i1].compareTo(upper[i2]) < 0)) {
				tmpFinal[i] = lower[i1];
				i1++;
			} else {
				tmpFinal[i] = upper[i2];
				i2++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String ipfile = args[0];
		String opfile = args[1];
		int numThreads = Integer.valueOf(args[2]);

		double startTime = System.currentTimeMillis();
		imdSort(new File(ipfile), numThreads);
		finalMerge(imdFilesList, new File(opfile));
		double endtime = System.currentTimeMillis();
		System.out.println("Total Time Taken(seconds): " + (endtime - startTime)/1000);

	}

	public static void imdSort(File file, int thread) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(file));
		String readLine = "";
		List<String> imdChklst = new ArrayList<String>();
		long sizeofChunk;
		long imdFileLines = 0;
		
		if (file.getName().contains("20")) {
			sizeofChunk = file.length() / 100;
		} else {
			sizeofChunk = file.length() / 10;
		}
		//System.out.println(file.length());
		int numThreads = thread;
		Thread[] threadsArray = new Thread[numThreads];

		try {
				while (true) {
					while ((readLine = buffRead.readLine()) != null) {
						imdChklst.add(readLine);
						totNumLine = totNumLine + 1;
						imdFileLines += readLine.length() + 2;
						if (imdFileLines >= sizeofChunk)
							break;
					}
					//System.out.println("imdFileLines " + imdFileLines);
					if (readLine == null)
						break;
					File imdWrFP = null;
					if (imdChklst != null && imdChklst.size() != 0) {
						imdDataArr = (String[]) imdChklst.toArray(new String[0]);
						imdMergeSort(imdDataArr);
						imdWrFP = null;
						try {
							imdWrFP = new File("/tmp/externalSortfile_" + cnt);
							if (!imdWrFP.exists())
								imdWrFP.createNewFile();
							BufferedWriter imdFileWr = new BufferedWriter(new FileWriter(imdWrFP));
							for (int i = 0; i < imdDataArr.length; i++) {
								imdFileWr.write(imdDataArr[i]);
								imdFileWr.write("\r\n");
							}
							imdFileWr.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					cnt++;
					imdFileLines = 0;
					imdChklst.clear();
					imdFilesList.add(imdWrFP);
				}
				//System.out.println("totNumLine" + totNumLine);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				buffRead.close(); 
		}
	}

	public static void finalMerge(List<File> fileList, File opfile) throws IOException {

		BufferedReader[] buffRead = new BufferedReader[fileList.size()];
		BufferedWriter buffWr = new BufferedWriter(new FileWriter(opfile));
		int z = 0,j=0,k=0;
		String minRec = "";
		for (File file : fileList) {
			buffRead[z] = new BufferedReader(new FileReader(file));
			z++;
		}
		// System.out.println("size"+fileList.size());
		
		String[] RecordFromSingleFile = new String[fileList.size()];
		for (int i = 0; i < fileList.size(); i++) {
			RecordFromSingleFile[i] = buffRead[i].readLine();
		}
		try {
			for (j = 0; j < totNumLine; j++) {
				minRec = RecordFromSingleFile[0];
				int minFileIndex = 0;
				for (k = 0; k < fileList.size(); k++) {
					if (minRec != null && RecordFromSingleFile[k] != null
							&& minRec.compareTo(RecordFromSingleFile[k]) > 0) { 
						minRec = RecordFromSingleFile[k];
						minFileIndex = k;
					}
				}
				buffWr.write(minRec);
				buffWr.write("\r\n");
				RecordFromSingleFile[minFileIndex] = buffRead[minFileIndex].readLine();
			}
		} catch (Exception e) {
			//System.out.println("j=" + j);
			//System.out.println("minRec" + minRec);
		}
		buffWr.close(); 
		for (int i = 0; i < buffRead.length; i++)
			buffRead[i].close();

	}

}

