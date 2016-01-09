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
import java.util.concurrent.locks.ReentrantLock;

// sort large array of 1's and 0's (too big for memory)
public class GoogleCoaching2Distributed {
	final static int NUMBER_OF_COMPUTERS = 4; // number of processors/threads
	final static int ARTIFICIAL_MEMORY_LIMIT = 100; // Pretend we can only fit 100 objects in memory per computer (processor)
	public static void main(String [] args) throws IOException {
		String inputName = "unsortedHugeFile0s1s.txt";
		String outputName = "sortedHugeFiles0s1s.txt";
		//generateRandomBinaryFile(inputName);
		System.out.print("Sorting huge file...");
		new SolutionDistributed().distribute(inputName, outputName);
	}
	
	public static class SolutionDistributed implements SolutionObserver {
		ReentrantLock lock = new ReentrantLock();
		public long onesCount = 0;
		public long responsesToGo = NUMBER_OF_COMPUTERS;

		public void distribute(String inputName, String outputName) throws IOException {
			
			/*******************OPEN FILE TO SORT**********************/
			// Open file.
			File inFile = new File(inputName);
			if(!inFile.exists()) inFile.createNewFile();
			
			/*******************FIND SIZE OF SECTIONS**********************/
			long totalSize = inFile.length();
			long sizeOfEachSection = (long) Math.ceil(totalSize/(double) NUMBER_OF_COMPUTERS);
			
			/*******************START SOLUTION THREADS**********************/
			for(int i = 0; i < NUMBER_OF_COMPUTERS; i++) {
				new Thread(new SolutionThread(this, i * sizeOfEachSection, sizeOfEachSection, inputName)).start();
			}
			
			/*******************WATI FOR THREADS**********************/
			while(true) {
				lock.lock();
				try {
					if(responsesToGo <= 0) break;
				} finally {
					lock.unlock();
				}
			}
			
			/*******************SORT AND WRITE TO FILE**********************/
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
				StringBuilder out = new StringBuilder();
				int write = 0;
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
		
		@Override
		public void notify(int onesCount) {
			if(onesCount == -1) {
				System.err.println("One of the threads failed. Exiting.");
				System.exit(-1);
			} else {
				lock.lock();
				try {
					// update onesCount
					this.onesCount += onesCount;
					// update number of responses
					responsesToGo--;
				} finally {
					lock.unlock();
				}
			}
		}
	}
	
	public static interface SolutionObserver {
		public abstract void notify(int onesCount);
	}
	
	public static class SolutionThread implements Runnable {
		public final SolutionObserver observer;
		public final long start;
		public long size;
		public final String fileName;
		
		public SolutionThread(SolutionObserver observer, long start, long size, String fileName) {
			this.observer = observer;
			this.start = start;
			this.size = size;
			this.fileName = fileName;
		}
		
		@Override
		public void run() {
			int onesCount = 0;
			// Open file.
			File inFile = new File(fileName);
			try {
				if(!inFile.exists()) inFile.createNewFile();
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
				br.skip(start);
				try {
					// Read in 100 lines at a time and count number of 1s
					char[] cbuf = new char [ARTIFICIAL_MEMORY_LIMIT];
					int read = 0;
					while(size > 0 && (read = br.read(cbuf, 0, ARTIFICIAL_MEMORY_LIMIT)) != -1) {
						for(int i = 0; i < read && i < size; i++) {
							onesCount += (cbuf[i] - '0');
						}
						size -= read;
					}
				} finally {
					br.close();
				}
			} catch(Exception e){
				observer.notify(-1);
				return;
			}
			observer.notify(onesCount);
		}
	}
	
	public static void generateRandomBinaryFile(String fileName) throws IOException {
		final int DATA_SIZE = 75 * (ARTIFICIAL_MEMORY_LIMIT+1);
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
