

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FindSuspect {
	public static void main(String[] args) {
		TrieTree tree = new TrieTree();
		TrieTree colorTree = new TrieTree();
		String[] evidence = {"Toyota", "Prius", "90089", "TRUE", "8IR", "BIR", "81R", "B1R"};
		//String[] darkColors = {"black", "blue", "green", "brown", "navy"};

	
		ArrayList<String[]> suspectList = new ArrayList<>();
	
		try(Scanner scan = new Scanner(new File("records.csv"))){
			
			while(scan.hasNextLine()) {
				String[] suspect = scan.nextLine().split(",");
				suspectList.add(suspect);
			}
			
			for(String[] suspect: suspectList) {
				colorTree.insert(suspect[5].toLowerCase());
				for(int i=0; i< suspect.length; i++) {
					tree.insert(suspect[i].toLowerCase());
				}
			}
			
			//System.out.println(colorTree.wordListForPrefix(""));
			
			
			ArrayList<ArrayList<String>> matches = new ArrayList<ArrayList<String>>();
			
			for(int i = 4; i < evidence.length; i++){
				matches.add(tree.wordListForPrefix(evidence[i]));
			}
			
			try(Scanner scan1 = new Scanner(new File("records.csv"));){
			
				ArrayList<String[]> suspects = new ArrayList<String[]>();

				while(scan1.hasNextLine()){
				String temp = scan1.nextLine();
				for(ArrayList<String> lists: matches){
					for(String element: lists){
						if(temp.toLowerCase().contains(element) && temp.toLowerCase().contains("toyota")
								&& !temp.toLowerCase().contains("black") && !temp.toLowerCase().contains("blue") && !temp.toLowerCase().contains("green")
								&& !temp.toLowerCase().contains("brown") && !temp.toLowerCase().contains("navy")){
							String[] tempArr = temp.split(",");
							suspects.add(tempArr);
						}
					}
				}
			}
				for(String[] array: suspects){
					String name = array[0] + " " + array[1];
					System.out.println(name);
					//String str = Arrays.deepToString(array);
					//System.out.println(str);
				}
			}
			
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}

}
