/*
  	ISYS 320
  	Name(s): Clayton Bruce
  	Date: 5/1/2018
*/
package edu.maryville.isys320.TriviaNight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestionsManager {
	public ArrayList<String> categories = new ArrayList<String>();
	public ArrayList<String> questions = new ArrayList<String>();;
	public ArrayList<String> answers = new ArrayList<String>();;
	
	public QuestionsManager(String categoryFolder) {
		File fldr = new File(categoryFolder);
		String[] files = fldr.list();
		
		Collections.addAll(categories,files);
		Collections.sort(categories);
		for (String fileName : categories) {
			File f = new File(categoryFolder + "/" + fileName);
			try {
				Scanner fs = new Scanner(f);
				while(fs.hasNextLine()) { // Assume Q, A, Blank Line triplets 
					questions.add(fs.nextLine());
					answers.add(fs.nextLine());
					fs.nextLine();
				}
				fs.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public String[] getCategories() {
		//need to allow us to choose the category order 
		//Maybe something like showing what is in the array?
		return categories.toArray(new String[categories.size()]);
	}
	
	public String[] getQuestions() {
		return questions.toArray(new String[questions.size()]);
	}
	
	public String[] getAnswers() {
		return answers.toArray(new String[answers.size()]);
	}

}
