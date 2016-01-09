package examples.google;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

import utilities.MyTimer;

// sort large array of 1's and 0's (too big for memory)
public class GoogleCoaching2Local {
	final static int ARTIFICIAL_MEMORY_LIMIT = 10000; // Pretend we can only fit 10000 objects in memory
	public static void main(String [] args) throws IOException {
		String inputName = "unsortedHugeFile0s1s.txt";
		String outputName = "sortedHugeFiles0s1s.txt";
		//generateRandomBinaryFile(inputName);
		System.out.print("Sorting huge file...");
		MyTimer timer = new MyTimer();
		timer.start();
		solutionLocal(inputName, outputName);
		System.out.println("Runtime: " + timer.time() + " miliseconds");
	}
	
	public static void solutionLocal(String inputName, String outputName) throws IOException {
		long totalSize = 0;
		long onesCount = 0;
		
		// Open file.
		File inFile = new File(inputName);
		if(!inFile.exists()) inFile.createNewFile();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
		try {
			// Read in 100 lines at a time and count number of 1s
			char[] cbuf = new char [ARTIFICIAL_MEMORY_LIMIT];
			int read = 0;
			while((read = br.read(cbuf, 0, ARTIFICIAL_MEMORY_LIMIT)) != -1) {
				for(int i = 0; i < read; i++) {
					onesCount += (cbuf[i] - '0');
					totalSize++;
				}
			}
		} finally {
			br.close();
		}
		
		// Build output string
		StringBuilder out = new StringBuilder();
		
		// open file for output
		File outFile = new File(outputName);
		if(!outFile.exists()) outFile.createNewFile();
		else {
			outFile.delete();
			outFile.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		
		try {
			long zerosCount = totalSize - onesCount;
			System.out.print("total: " + totalSize + " ones: " + onesCount + " zeros: " + zerosCount);
			long write = 0;
			while(write < totalSize) {
				if(out.length() == ARTIFICIAL_MEMORY_LIMIT) {
					bw.append(out.toString());
					bw.flush();
					out.delete(0, out.length());
				} else {
					out.append(write < zerosCount ? "0" : "1");
					write++;
				}
			}
			if(out.length() > 0) {
				bw.append(out.toString());
				bw.flush();
				out.delete(0, out.length());
			}
		} finally {
			bw.close();
		}
		
		System.out.println("...done. Check output in: " + outFile);
	}
	
	public static void generateRandomBinaryFile(String fileName) throws IOException {
		final int DATA_SIZE = 7500 * (ARTIFICIAL_MEMORY_LIMIT+1);
		File file = new File(fileName);
		if(!file.exists()) file.createNewFile();
		
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for(int i = 0; i < DATA_SIZE; i++) sb.append(r.nextInt(Integer.MAX_VALUE) % 2);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		try {
			bw.write(sb.toString());
		} finally {
			bw.close();
		}
	}
}
