package com.java.dict;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilePreprocessor
{
	
	private static FilePreprocessor _instance;
	public FilePreprocessor getInstance()
	{
		if(null==_instance)
		{
			_instance = new FilePreprocessor();
		}
		return _instance;
	}
	
	
	
	public static void main(String[] args) 
	{
		String line;
		int index = 1;
		BufferedReader br = null;
		BufferedWriter bw = null;
		StringBuffer buffer = null;
		try
		{
			File rfile = new File("F://word-source.txt");
			if (!rfile.exists()) {
				rfile.createNewFile();
			}
			File wFile = new File("F://new-word-source.txt");
			if(!wFile.exists())
			{
				wFile.createNewFile();
			}
			FileReader fr = new FileReader(rfile);
			br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(wFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			while ((line = br.readLine()) != null) 
			{
				buffer = new StringBuffer();
				System.out.println(line);
				buffer.append(line).append("-").append("synonym"+index).append(",").append("antonym"+index).append(",").append("source"+index).append("\n");
				bw.write(buffer.toString());
				index ++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(null!=br)
					br.close();
				if(null!=bw)
					bw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
	}

}
