package com.yango.robot.util.Nlpir;

/**
 * 获取组件路径
 * 
 * @author move
 * 
 */
public class OSInfo {
	private static String OS_NAME = System.getProperty("os.name").toLowerCase();
	private static String OS_ARCH = System.getProperty("os.arch").toLowerCase();

	/**
	 * 根据操作系统获得对应的组件路径
	 * 
	 * @param module
	 *            组件名(不带扩展名)
	 * @return 形式如下：linux64/组件名.扩展名、win32/组件名.扩展名
	 */
	public static String getModulePath(String module) {
		String folder = ""; // 文件夹名
		String extension = ""; // 扩展名

		if (OS_NAME.contains("win")) {
			extension = ".dll";

			if (OS_ARCH.contains("86")) {
				folder = "src/main/resources/file/win32/";
			} else {
				folder = "src/main/resources/file/win64/";
			}
		} else {
			extension = ".so";

			if (OS_ARCH.contains("86")) {
				folder = "src/main/resources/file/linux32/";
			} else {
				folder = "src/main/resources/file/linux64/";
			}
		}

		return folder + module + extension;
	}
}
