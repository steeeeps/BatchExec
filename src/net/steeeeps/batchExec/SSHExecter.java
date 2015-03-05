//http://blog.csdn.net/fyqcdbdx/article/details/23863717
package net.steeeeps.batchExec;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class SSHExecter extends BatchExecuter {

	public SSHExecter(ExecInfo info) {
		// TODO Auto-generated constructor stub
		super(info);
	}

	public static void main(String[] arg) throws Exception {

		ExecInfo info = new ExecInfo("taopy", "admin", "192.168.159.129",
				"", "D:\\PSTools\\shtest.sh");
		SSHExecter executer = new SSHExecter(info);
		executer.exec();
		
	}



	@Override
	protected int exec() {
		// TODO Auto-generated method stub
		int exitvalue = -1;
		try {
			JSch jsch = new JSch();

			Session session = jsch.getSession(execInfo.getUsername(),
					execInfo.getIp(), execInfo.getPort());

			UserInfo ui = new SSHUserInfo(execInfo);
			session.setUserInfo(ui);
			session.connect();
			String command = getContent(execInfo.getBatchpath());
			if (execInfo.getArgs() != null && !execInfo.getArgs().equals("")) {
				command += " " + execInfo.getArgs();
			}
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command.getBytes());
			FileOutputStream errorStream = new FileOutputStream(errorpath);

			((ChannelExec) channel).setErrStream(errorStream);

			InputStream in = channel.getInputStream();
			((ChannelExec) channel).setOutputStream(new FileOutputStream(
					logpath));
			channel.connect();

			while (true) {

				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					exitvalue = channel.getExitStatus();
					break;
				}
				try {
					Thread.sleep(500);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			exitvalue = -1;
			try {
				saveToFile(errorpath, new ByteArrayInputStream(e.getMessage().getBytes()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block

			}
		}
		return exitvalue;
	}

}