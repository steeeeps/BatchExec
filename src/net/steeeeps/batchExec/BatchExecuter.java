package net.steeeeps.batchExec;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public abstract class BatchExecuter {

	protected ExecInfo execInfo;
	protected static String workspace = "";
	protected static String psexecpath = "";
	protected static String logpath = "";
	protected static String errorpath = "";
	protected static String tempbatch = "";
	static {
		workspace = System.getProperty("user.dir") + "\\libs";
		psexecpath = workspace + "/PsExec.exe";
		tempbatch = workspace + "/temp.bat";
		logpath = workspace + "/logout.txt";
		errorpath = workspace + "/errorout.txt";
		File logfile = new File(logpath);
		File errorfile = new File(errorpath);
		if (!logfile.exists()) {
			try {
				logfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!errorfile.exists()) {
			try {
				errorfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected abstract int exec();

	public BatchExecuter(ExecInfo info) {
		this.execInfo = info;
	}

	protected String getLog() {
		return getContent(logpath);
	}

	protected String getError() {
		return getContent(errorpath);
	}

	protected synchronized String getContent(String filepath) {
		BufferedReader br = null;
		String content = "";
		try {
			br = new BufferedReader(new FileReader(filepath));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			content = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content;
	}

	public synchronized void saveToFile(String fileName, InputStream in)
			throws IOException {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		bis = new BufferedInputStream(in);
		fos = new FileOutputStream(fileName);
		while ((size = bis.read(buf)) != -1) {
			fos.write(buf, 0, size);

		}
		fos.close();
		bis.close();
	}
}
