package demos.a9_24;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileTest {
	static String file="C:/Users/ke/Desktop/temp.txt";
	static void display() throws IOException{
		RandomAccessFile raf=new RandomAccessFile("C:/Users/ke/Desktop/test.txt", "r");
		for (int i = 0; i < 7; i++) 
			System.out.println("value"+i+":"+raf.readDouble());
		System.out.println(raf.readUTF());
		raf.close();
	}
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf=new RandomAccessFile(file, "rw");
		for (int i = 0; i < 7; i++) 
			raf.writeDouble(i*1.414);
		raf.writeUTF("it's end");
		raf.close();
		display();
		raf=new RandomAccessFile(file, "rw");
		raf.seek(5*8);
		raf.writeDouble(88.08);
		display();
		raf.close();
	}
}
