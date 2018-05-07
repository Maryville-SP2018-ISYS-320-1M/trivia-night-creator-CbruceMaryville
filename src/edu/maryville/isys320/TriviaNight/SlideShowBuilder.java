/*
  	ISYS 320
  	Name(s): Clayton Bruce
  	Date: 5/1/2018
*/
package edu.maryville.isys320.TriviaNight;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class SlideShowBuilder {

	public void printSlideShowInfo(String fileName) throws FileNotFoundException, IOException {
		XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(fileName));
		// get slides
		for (XSLFSlide slide : ppt.getSlides()) {
			for (XSLFShape sh : slide.getShapes()) {
				// name of the shape
				String name = sh.getShapeName();
				System.out.println("Shape: " + name);

				// shapes's anchor which defines the position of this shape in the slide
				if (sh instanceof PlaceableShape) {
					java.awt.geom.Rectangle2D anchor = ((PlaceableShape) sh).getAnchor();
					System.out.printf("(%f, %f, %f, %f)\n", anchor.getX(), anchor.getY(), anchor.getWidth(),
							anchor.getHeight());
				}

				if (sh instanceof XSLFTextShape) {
					XSLFTextShape shape = (XSLFTextShape) sh;
					System.out.printf("%s\n", shape);

				}
			}
		}
	}

	public void buildSlideShow(String[] categories, String[] questions, String[] answers, String fileName) {
		XMLSlideShow ppt = new XMLSlideShow();
		XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);

		for (int roundNumber = 0; roundNumber < 10; roundNumber++) {
			makeRoundSlide(ppt, defaultMaster, "Round " + (roundNumber + 1));
			//Below changed <10 to questions.length from questions manager to change the max number of questions to be lower or higher than 10
			for (int questionNumber = 0; questionNumber < questions.length; questionNumber++) {
				// FOR EVERY QUESTION HERE IS WHERE WE MAKE A SLIDE
				makeSlideQ(ppt, defaultMaster, categories[roundNumber], questions[calculateQuestionOrAnswerOfset(roundNumber, questionNumber)]);
				// FOR EVERY ANSWER HIS IS WHERE WE MAKE A SLIDE
				makeSlideA(ppt, defaultMaster, categories[roundNumber], questions[calculateQuestionOrAnswerOfset(roundNumber, questionNumber)] ,answers[calculateQuestionOrAnswerOfset(roundNumber, questionNumber)]);
			}
		} // SOMETHING HERE IN CHANGING THE SLIDES TO ANSWERS AND Q SEPERATE-DONE

		savePPTX(ppt, fileName);
	}

	private int calculateQuestionOrAnswerOfset(int roundNumber, int questionNumber) {
		return roundNumber * 10 + questionNumber;
	}

	private static void makeRoundSlide(XMLSlideShow ppt, XSLFSlideMaster master, String round) {
		// THE ROUND SLIDE
		XSLFSlideLayout titleSlide = master.getLayout(SlideLayout.SECTION_HEADER);
		XSLFSlide slide = ppt.createSlide(titleSlide);

		XSLFTextShape sh1 = (XSLFTextShape) slide.getShapes().get(0);
		sh1.setText(round).setFontSize(36.0d);// TEXT SIZE
		sh1.setHorizontalCentered(true);//

	}

	private static void makeSlideQ(XMLSlideShow ppt, XSLFSlideMaster master, String title, String question) {//used to create just the question slide (no answer)
		XSLFSlideLayout blankSlide = master.getLayout(SlideLayout.BLANK);
		XSLFSlide slide = ppt.createSlide(blankSlide);

		// HEADER FOR THE SLIDE HOLDS THE TITLE OR (CATAGORY)
		XSLFTextShape header = slide.createTextBox();
		header.setAnchor(new Rectangle2D.Double(16.988740, 17.797717, 685.011260, 50.892205));
		header.setText(title).setFontSize(36.0d);
		header.setHorizontalCentered(true);

		// TEXT BOX (BULK OF SCREEN) (QUESTION) just question no answer
		XSLFTextShape question1 = slide.createTextBox();
		question1.setAnchor(new Rectangle2D.Double(16.988740, 68.689921, 685.011260, 298.120866));
		question1.setText(question).setFontSize(44.0);
		question1.setHorizontalCentered(true);

	}

	private static void makeSlideA(XMLSlideShow ppt, XSLFSlideMaster master, String title,String question, String answer) {// This is the separate question then answer slide
		XSLFSlideLayout blankSlide = master.getLayout(SlideLayout.BLANK);
		XSLFSlide slide = ppt.createSlide(blankSlide);

		// HEADER FOR THE SLIDE HOLDS THE TITLE OR (CATAGORY)
		XSLFTextShape header = slide.createTextBox();
		header.setAnchor(new Rectangle2D.Double(16.988740, 17.797717, 685.011260, 50.892205));
		header.setText(title).setFontSize(36.0d);
		header.setHorizontalCentered(true);

		// TEXT BOX (BULK OF SCREEN) (Question) reminds people playing of question before the answer
		XSLFTextShape question1 = slide.createTextBox();
		question1.setAnchor(new Rectangle2D.Double(16.988740, 68.689921, 685.011260, 298.120866));
		question1.setText(question).setFontSize(44.0);
		question1.setHorizontalCentered(true);

		// TEXT BOX (ANSWER) NEEDS TO BE ON A SEPERATE SLIDE THAN QUESTION
		XSLFTextShape answer1 = slide.createTextBox();
		answer1.setAnchor(new Rectangle2D.Double(16.988740, 366.810787, 685.011260, 74.918976));
		answer1.setText(answer).setFontSize(36.0d);
		answer1.setHorizontalCentered(true);
	}

	private void savePPTX(XMLSlideShow ppt, String filePath) {
		File file = new File(filePath);
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			ppt.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
