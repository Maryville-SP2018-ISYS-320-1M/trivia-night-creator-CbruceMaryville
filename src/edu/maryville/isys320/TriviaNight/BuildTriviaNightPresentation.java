/*
 * 
 * 10 rounds with 10 questions
 * 
 * so question 1 of 10 in round 1 
 * answer 1 of 10 in round 1 
 * 
 * there is also 10ish categories etc
 * 
 * creating 10 category files 
 * open them 
 * question answer blank blank
 * 
 * store into strings
 * 
 * then call a library with a list of these questions loaded
 * 
 * 
 * When printing to the slide show you want to print out all 10
 * Questions first for part 1
 * Then after all 10 Questions you want to print out all 10
 * Answers
 * 
 * Do this for each 10 categories
 * 
 * Most of the project is done but what he wants us to do is change part 
 * of the code so that it does the question and answers slides separate
 * just like above described.
 */


/*
  	ISYS 320
  	Name(s): Clayton Bruce
  	Date: 5/1/2018
*/
package edu.maryville.isys320.TriviaNight;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class BuildTriviaNightPresentation {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String fileName = "//Users//clayton//TriviaNight.pptx";
		SlideShowBuilder builder = new SlideShowBuilder();
		QuestionsManager qm = new QuestionsManager("data");
		
		builder.buildSlideShow(qm.getCategories(), qm.getQuestions(), qm.getAnswers(), fileName);
//		builder.printSlideShowInfo("Sample.pptx");

	}
	
}

