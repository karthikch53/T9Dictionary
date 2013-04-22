package com.java.dict;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.java.dict.model.Node;

public class CustomDictionary
{
	Node rootNode = null;
	
	public CustomDictionary()
	{
		rootNode = new Node(' ');
	}
	
	public void add(String string, List<String> details)
	{
		Node currentNode = rootNode;
		Node childNode = null;
		int i=0;
		//String str = pramati
		//String length - 7
		if(string == null)
		{
			return;
		}
		while(i<string.length())
		{
			char toBeAdded = string.charAt(i);
			System.out.println("char to be added " + toBeAdded);
			childNode = currentNode.checkIfNodeExists(toBeAdded);
			if(null!=childNode)
			{
				System.out.println("current node already has a child with value " + childNode.getValue());
				currentNode = childNode;
			}
			else
			{
				System.out.println("adding a new child " + toBeAdded);
				currentNode.getChildren().add(new Node(toBeAdded));
				System.out.println("pointing to the last added location " + toBeAdded);
				currentNode = currentNode.checkIfNodeExists(toBeAdded);
			}
			if(i == string.length()-1)
			{
				currentNode.setEndOfWord(true);
				currentNode.setDetails(details);
			}
			i++;
		}
		
	}
	
	private Node search(String string)
	{
		Node currentNode = rootNode;
		Node childNode;
		int i=0;
		if(null==string)
		{
			return null;
		}
		while(i<string.length())
		{
			char c = string.charAt(i);
			childNode = currentNode.checkIfNodeExists(c);
			if(null!=childNode)
			{
				currentNode = currentNode.checkIfNodeExists(c);
			}
			else
			{
				return null;
			}
			i++;
		}
		if(currentNode.isEndOfWord())
		{
			return currentNode;
		}
		else
		{
			return null;
		}
		
	}
	
	public List<String> getWordDetails(String word) throws Exception
	{
		List<String> details = null;
		Node currentNode = search(word);
		if(null!=currentNode)
		{
			details = currentNode.getDetails();
		}
		else
		{
			throw new Exception("Word doesn't exist.");
		}
		return details;
	}
	
	
	
	public void constructDictionaryFromFile(String fileName) {
		CustomDictionary c = new CustomDictionary();
		FileReader fr = null;
		String line = "";
		BufferedReader br = null;
		List<String> details=null;
		try
		{
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) 
			{
				String[] arr1 = line.split("-");
				String[] arr2 = arr1[1].split(",");
				if(null!=arr2 && arr2.length>0)
				{
					details = new ArrayList<String>();
					for(int i=0;i< arr2.length;i++)
					{
						details.add(arr2[i]);
					}
				}
				c.add(arr1[0],details);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		finally
		{
			try 
			{
				br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		CustomDictionary c = new CustomDictionary();
		String line;
		BufferedReader br = null;
		List<String> details=null;
		try
		{
			InputStream is = CustomDictionary.class.getClassLoader().getResourceAsStream("new-word-source.txt");
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) 
			{
				String[] arr1 = line.split("-");
				String[] arr2 = arr1[1].split(",");
				if(null!=arr2 && arr2.length>0)
				{
					details = new ArrayList<String>();
					for(int i=0;i< arr2.length;i++)
					{
						details.add(arr2[i]);
					}
				}
				c.add(arr1[0],details);
			}
			List<String> l = c.getWordDetails("zenner");
			if(null!=l && l.size()>0)
			{
				for(String s:l)
				{
					System.out.println(s);
				}
			}
			List<String> l1 = c.getWordDetails("adsorption");
			if(null!=l1 && l1.size()>0)
			{
				for(String s:l1)
				{
					System.out.println(s);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try 
			{
				if(null!=br)
				br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
	}
}
