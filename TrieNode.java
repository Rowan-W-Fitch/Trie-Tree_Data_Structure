import java.util.ArrayList;

public class TrieNode extends Object {
	char data;
	boolean isEnd;
	ArrayList<TrieNode> childList;
	
	public TrieNode(char c) {
		this.data = c;
		this.isEnd = false;
		this.childList = new ArrayList<TrieNode>();
	}
	
	public TrieNode getChild(char c) {
		TrieNode target = null;
		if(childList.isEmpty()) {
			isEnd = true;
			return null;
		}
		else {
			isEnd = false;
			for(TrieNode t: childList) {
				if(t.data == c) {
					target = t;
					break;
				}
				else {
					continue;
				}
			}
			return target;
		}
		
	}

}
