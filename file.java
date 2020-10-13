package hw3DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class file {

	public static String[] readFile(String filename){	
		ArrayList<String> lines = new ArrayList<String>(); //File Content
		try(	//寫�?��?�種?��法就不用close  file
				FileReader fr = new FileReader(filename);
		   )
		{	
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()){
			String line = br.readLine();
			//如�?�line???#跳�??
			if (line.charAt(0)=='#')
				continue;
			//?��下�?��?�內容放?��line
			lines.add(line);	
		}
		}catch(Exception e){
			e.printStackTrace();
		}
			return lines.toArray(new String[lines.size()]);
	}
}
