
/**
 * author:www.steeeeps.net
 * email:steeeeps@gmail.com
 * 
 */
package net.steeeeps.batchExec;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BatchExecuter executer = null;
		String bactchPath = System.getProperty("user.dir")
				+ "\\libs\\service_start_oracle11g.bat";
		String extension = bactchPath.substring(
				bactchPath.lastIndexOf(".") + 1, bactchPath.length());

		ExecInfo info = new ExecInfo("taopy", "Admin123", "127.0.0.1", "",
				bactchPath);
		if (extension.equals("bat")) {
			executer = new WinExecuter(info);
		} else if (extension.equals("sh")) {
			executer = new SSHExecter(info);
		} else {
			System.out.println("系统暂时不支持该文件类型，请使用.bat、.sh扩展名的批处理文件!");
		}
		int exitvalue = executer.exec();
		if (exitvalue == 0 || exitvalue == 1) {
			System.out.println("执行批处理脚本成功!");
			System.out.println(executer.getLog());
		} else {
			System.err.println("执行批处理脚本出错,请重试!");
			System.err.println(executer.getError());
		}
	}

}
