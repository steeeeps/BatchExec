package net.steeeeps.batchExec;

/**
 * 批处理常用信息
 * @author steeeeps
 *
 */
public class ExecInfo {

	private String ip;					//远程服务器ip
	private int port=22;				//远程服务器端口
	private String username;			//远程服务器用户名
	private String password;			//远程服务器密码
	private String args;				//批处理执行参数
	private String batchpath;			//批处理脚本路径
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public String getBatchpath() {
		return batchpath;
	}
	public void setBatchpath(String batchpath) {
		this.batchpath = batchpath;
	}
	public ExecInfo(String username,String password,String ip,int port,String args,String batchpath){
		this.username=username;
		this.password=password;
		this.ip=ip;
		this.port=port;
		this.args=args;
		this.batchpath=batchpath;
	}
	public ExecInfo(String username,String password,String ip,String args,String batchpath){
		this.username=username;
		this.password=password;
		this.ip=ip;
		this.args=args;
		this.batchpath=batchpath;
	}
	public ExecInfo(){
		
	}
	
}
