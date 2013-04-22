package com.java.dict.model;

import java.util.ArrayList;
import java.util.List;

public class Node 
{
	private boolean isRootNode;
	private char value;
	private boolean isEndOfWord;
	private List<Node> children;
	private List<String> details;
	
	public Node(char value) {
		this.value = value;
		this.children = new ArrayList<Node>();
		this.isEndOfWord = false;
	}
	
	public Node checkIfNodeExists(char c)
	{
		if(null!=children)
		{
			for(Node n:children)
			{
				if(n.getValue() == c)
				{
					return n;
				}
			}
		}
		return null;
	}
	
	
	public boolean isEndOfWord() {
		return isEndOfWord;
	}
	public void setEndOfWord(boolean isEndOfWord) {
		this.isEndOfWord = isEndOfWord;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public boolean isRootNode() {
		return isRootNode;
	}
	public void setRootNode(boolean isRootNode) {
		this.isRootNode = isRootNode;
	}
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
}
