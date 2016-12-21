package demos.ioDemo;

import java.io.*;

public class IODemo {
	public void simpleWrite(String content, String filePath) throws IOException {
		File file = new File(filePath);
		FileOutputStream out = null;
		out = new FileOutputStream(file);
		out.write(content.getBytes());
		out.close();
	}

	/*
	 * 不能读汉字,文件内容开始有三个莫名符号
	 */
	public String simpleByteRead(String filePath) throws IOException {
		FileInputStream in = new FileInputStream(filePath);
		StringBuilder result = new StringBuilder();
		int i;
		while ((int) (i = in.read()) != -1) {
			result.append((char) i);
		}
		return result.toString();
	}

	public String simpleRead(String filePath, String charset)
			throws IOException {
		FileInputStream in = new FileInputStream(filePath);
		InputStreamReader isr;
		if (null == charset) {
			isr = new InputStreamReader(in);
		} else {
			isr = new InputStreamReader(in, charset);
		}
		StringBuilder result = new StringBuilder();
		int i;
		while ((int) (i = isr.read()) != -1) {
			result.append((char) i);
		}
		return result.toString();
	}

	public String simpleBufferRead(String filePath, String charset)
			throws IOException {
		BufferedReader bReader;
		if ("utf8".equalsIgnoreCase(charset)) {
			// 默认以utf8读取
			bReader = new BufferedReader(new FileReader(filePath));
		} else {
			bReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), charset));
		}
		StringBuilder result = new StringBuilder();
		String temp;
		while ((temp = bReader.readLine()) != null) {
			result.append(temp + "\n");
		}
		return result.toString();
	}

	/*
	 * 文件内容开始有三个莫名符号
	 */
	public String readOffset(String filePath, int offset) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(filePath, "r");
		raf.seek(offset);
		StringBuilder result = new StringBuilder();
		String temp;
		while ((temp = raf.readLine()) != null) {
			result.append(new String((temp + "\n").getBytes(), "utf8"));
		}
		return result.toString();
	}

	/**
	 * 字节流转字符流
	 */
	public void byte2char() throws UnsupportedEncodingException {
		String str="你好";
		// 编码和解码要一致
		Reader reader = new InputStreamReader(new ByteArrayInputStream(str.getBytes("UTF-8")),"UTF-8");
		char[] chars = new char[2];
		while (true){
			try {
				int i = reader.read(chars);
				if (i==-1){
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(new String(chars));
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		IODemo demo = new IODemo();
		demo.byte2char();
	}
}
