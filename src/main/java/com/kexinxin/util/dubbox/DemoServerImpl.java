/**
 * 
 */
package com.kexinxin.util.dubbox;

import java.util.Date;

/**
 * @author wanggengqi
 * @email wanggengqi@chinasofti.com
 * @date 2014年10月23日 下午1:31:47
 */
public class DemoServerImpl implements DemoServer {

	/**
	 * 返回添加后的语句
	 */
	public String sayHello(String str) {
		str = "Hello " + str + "  2:" + new Date();
		System.err.println("server:" + str);
		return str;
	}

}
