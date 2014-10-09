package amazom.hibernate.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

public class ReviewData {

	public void insertData() throws FileNotFoundException, IOException{
		InputStream fileStream = new FileInputStream("/home/abhinav/Downloads/Arts.txt.gz");
		InputStream gzipStream = new GZIPInputStream(fileStream);
		Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
		BufferedReader buffered = new BufferedReader(decoder);
		while(buffered.readLine()!=null){
			System.out.println(buffered.readLine());
		}
	}

}
