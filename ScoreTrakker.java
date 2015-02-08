package ScoreTrakker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreTrakker {
	
	public static void main(String[] bunnies){
		ScoreTrakker trakker = new ScoreTrakker();
		trakker.processFiles();
	}

	private ArrayList<Student> students;
	
	private void loadDataFromFile(String filename) throws Exception {
		students = new ArrayList<Student>();
		Scanner fin = new Scanner(new File(filename));
		while (fin.hasNext()){
			String name = fin.nextLine();
			String prescore = fin.nextLine();
			if (name.indexOf(' ') == -1){
				throw new Exception("Could not load the file: \'" + name + "\' is not a valid name.");
			}
			try {
				int score = Integer.parseInt(prescore);
				students.add(new Student(name, score));
			}
			catch (NumberFormatException nfe){
				System.out.println("Incorrect format for " + name + ", not a valid score: " + prescore + "\n");
			}
		}
	}
	
	private void printInOrder(){
		ArrayList<Student> ordered = new ArrayList<Student>();
		while (ordered.size() < students.size()){
			Student low = null;
			for (int i = 0; i < students.size(); i++){
				if (!ordered.contains(students.get(i))){
					low = students.get(i);
					break;
				}
			}
			for (Student stud : students){
				if (stud.compareTo(low) <= 0){
					if (!ordered.contains(stud)){
						low = stud;
					}
				}
			}
			ordered.add(low);
		}
		System.out.println("Student Score List:");
		for (Student out : ordered){
			System.out.println(out);
		}
	}
	
	public void processFiles(){

		String[] files = {"scores.txt", "badscore.txt", "nofile.txt", "badname.txt"};
		for (String file : files){
			try {
				loadDataFromFile(file);
				printInOrder();
			} 
			catch (FileNotFoundException e) {
				System.out.println("Cannot find the file: " + file);
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
			System.out.println("\n");
		}
	}
	
}
