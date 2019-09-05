import java.util.ArrayList;

public class TrieTree extends Object{
	TrieNode root;
	
	public TrieTree() {
		root = new TrieNode((char) 0);
	}
	
	public void insert(String str) {
		//inserts string into trie
		//go thru string char at time
		//if trie doesn't contain char -> add char to trie
		str = str.toLowerCase();
		int height;
		int wordLen = str.length();
		char word;
		TrieNode temp = root;
		
		for(height =0; height < wordLen; height++) {
			word = str.charAt(height);
			TrieNode node = temp.getChild(word);
			if(node == null) {
				TrieNode newNode = new TrieNode(word);
				temp.childList.add(newNode);
			}
			
			temp = temp.getChild(word);
		}
		temp.isEnd = true;
	}
	
	public boolean search(String str) {
		//go thru string char at time
		str = str.toLowerCase();
		int height;
		int wordLen = str.length();
		char word;
		TrieNode temp = root;
		
		for(height=0; height<wordLen; height++) {
			word = str.charAt(height);
			if(temp.getChild(word)==null) {
				return false;
			}
			
			temp = temp.getChild(word);
			
		}
		
		return temp.isEnd;
	}
	
	public void remove(String str) {
		//go thru string until reach end
		//1st check if word is in trie
		str = str.toLowerCase();
		if(search(str) == false) {
			return;
		}
		TrieNode temp = root;
		for(int i=0; i< str.length(); i++) {
			temp = temp.getChild(str.charAt(i));
		}
			//temp is now the node that contains the last char of the word in its arraylist of nodes
			//delete last char from arraylist
			//temp.childList.remove(temp.getChild(str.charAt(str.length() -1)));

		temp.isEnd = false;
	}
	//helper method returns array list
	//array list as parameter
	private ArrayList<String> listPrefix(TrieNode node, String prefix, ArrayList<String> list){
		if(node.childList.isEmpty()) {
			//System.out.println(true);
			list.add(prefix);
			return list;
		}
		else {
			for(TrieNode n: node.childList) {
				String prefix2 = prefix + n.data;
				listPrefix(n, prefix2, list);
			}
			return (ArrayList<String>) null;
		}
	}
	
	public ArrayList<String> wordListForPrefix(String prefix){
		ArrayList<String> list = new ArrayList<String>();
		prefix = prefix.toLowerCase();
		/*if(!search(prefix)) {
			ArrayList<String> empty = new ArrayList<String>();
			return empty;
		}
		*/
		
		
		TrieNode temp = root;
		//traversing trie
		for(int i=0; i<prefix.length(); i++) {
			if(temp.getChild(prefix.charAt(i))== null){
				return list;
			}
			temp = temp.getChild(prefix.charAt(i));
		}
		//return listPrefix(temp, prefix, list);
		//System.out.println(temp.data);
		//list.add(listPrefix(temp, prefix));
		
		for(TrieNode node: temp.childList) {
			//System.out.print(node.data + " ");
			String prefix2 = prefix + node.data;
			listPrefix(node, prefix2, list);
		}
		
		
		
		return list;
	}
}
