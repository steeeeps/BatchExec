package net.steeeeps.batchExec;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class WinExecuter extends BatchExecuter {

	public WinExecuter(ExecInfo info) {
		super(info);
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		// ExecOnWin win = new ExecOnWin();
		// win.exec();

	}

	@Override
	protected int exec() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String command = "\\\\" + execInfo.getIp() + " -u " + execInfo.getUsername()
				+ " -p " + execInfo.getPassword() + " -c " + execInfo.getBatchpath()
				+ " " + execInfo.getArgs();
		Process p = null;
		try {
			PrintWriter writer = new PrintWriter(tempbatch, "UTF-8");
			writer.println(psexecpath + " " + command);// + " > "+logpath);
			writer.flush();
			writer.close();
			//
			String[] commands = { "cmd", "/c", tempbatch };
			ProcessBuilder pb = new ProcessBuilder(commands);
			p = pb.start();
			FileOutputStream outputStream = new FileOutputStream(logpath);
			FileOutputStream errorStream = new FileOutputStream(errorpath);
			new StreamGobbler(p.getInputStream(), "INFO", outputStream).start();
			new StreamGobbler(p.getErrorStream(), "ERROR", errorStream).start();

			int exitVal = p.waitFor();

			return exitVal;
		} catch (Exception e) {
			try {
				saveToFile(errorpath,new ByteArrayInputStream(e.getMessage().getBytes())) ;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return -1;
		} finally {
			if (p != null) {
				p.destroy();
			}
			p = null;
		}
	}
}
