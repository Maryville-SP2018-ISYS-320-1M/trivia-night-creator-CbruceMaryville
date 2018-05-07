/*
  	ISYS 320
  	Name(s): Clayton Bruce
  	Date: 5/1/2018
*/
package edu.maryville.isys320.TriviaNight;

import java.io.*;

public class SampleFileCreator {

	public static void main(String[] args) {

		for (int round = 1; round <= 10; round++) {
			// Get the output file name and round number
			
			//Need to change the category name to an actual NAME somehow
			String fileName = "category_name_" + round + ".txt";
			String roundNumber = String.valueOf(round);
			File f = new File(fileName);

			System.out.println("Creating file: " + f.getAbsolutePath());

			// Create the output file, overwrite it if it exists
			PrintStream s;
			try {
				s = new PrintStream(f);
				for (int i = 1; i <= 10; i++) {
					s.println("Question " + i + " of 10 in round " + roundNumber);
					s.println("Answer " + i + " of 10 in round " + roundNumber);
					s.println();
				}

				// flush the output file, to ensure the last thing written is capture in the
				// file.
				s.flush();

				// close the output file.
				s.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
