package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;

public class writeDateToSdCard {
	
	
	public static boolean writeDateTosdcard(String path,String filename,byte[] bytes)
	{
		boolean b = false;
		try {
			File file=new File(path,filename);
			FileOutputStream output=new FileOutputStream(file);
			output.write(bytes);
			output.close();
			b=true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return b;
		
	}

}
