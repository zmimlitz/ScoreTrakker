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
	
	private void loadDataFromFile(String filename){
		students = new ArrayList<Student>();
		try {
			Scanner fin = new Scanner(new File(filename));
			while (fin.hasNext()){
				String name = fin.nextLine();
				int score = Integer.parseInt(fin.nextLine());
				students.add(new Student(name, score));
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
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
		loadDataFromFile("scores.txt");
		printInOrder();
	}
	
}
