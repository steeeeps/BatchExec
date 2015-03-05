package net.steeeeps.batchExec;

import com.jcraft.jsch.UserInfo; 

public class SSHUserInfo implements UserInfo {
	private ExecInfo info;

	public SSHUserInfo(ExecInfo info) {
		super();
		this.info = info;
	}

	public String getPassword() {
		return this.info.getPassword();
	}

	public boolean promptYesNo(String str) {
		return true;
	}

	public String getPassphrase() {
		return null;
	}

	public boolean promptPassphrase(String message) {
		return true;
	}

	public boolean promptPassword(String message) {
		return true;
	}

	public void showMessage(String message) {

	}

	public String[] promptKeyboardInteractive(String destination, String name,
			String instruction, String[] prompt, boolean[] echo) {
		return null;
	}
}
