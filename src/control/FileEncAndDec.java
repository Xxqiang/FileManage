package control;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileEncAndDec {
	private static final int numOfEncAndDec = 0x99; // 加密解密秘钥
	private static int dataOfFile = 0; // 文件字节内容


	public static void EncFile(String  srcpath, String encPath) throws Exception {
		File srcFile = new File(srcpath);
		File encFile = new File(encPath); // 加密文件
		if (!srcFile.exists()) {
			System.out.println("source file not exixt");
			return;
		}

		if (!encFile.exists()) {
			System.out.println("encrypt file created");
			encFile.createNewFile();
		}
		InputStream fis = new FileInputStream(srcFile);
		OutputStream fos = new FileOutputStream(encFile);

		while ((dataOfFile = fis.read()) > -1) {
			fos.write(dataOfFile ^ numOfEncAndDec);
		}

		fis.close();
		fos.flush();
		fos.close();
	}

	public static void DecFile(String encPath, String decFilePath) throws Exception {
		File encFile = new File(encPath); // 加密文件
		File decFile = new File(decFilePath); // 解密文件
		if (!encFile.exists()) {
			System.out.println("encrypt file not exixt");
			return;
		}

		if (!decFile.exists()) {
			System.out.println("decrypt file created");
			decFile.createNewFile();
		}

		InputStream fis = new FileInputStream(encFile);
		OutputStream fos = new FileOutputStream(decFile);

		while ((dataOfFile = fis.read()) > -1) {
			fos.write(dataOfFile ^ numOfEncAndDec);
		}

		fis.close();
		fos.flush();
		fos.close();
	}
}