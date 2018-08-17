package main.Factories;

import main.Application.models.Lesson;

/**
 * 
 * @author Robbie McArthur - 2098323m
 * 
 * 
 * Factory class to produce Lessons
 */
public class LessonFactory {
	
	public LessonFactory() {
		
	}
	
	public Lesson getLesson () {
		return new Lesson();
	}
	
	public Lesson getLesson (String t, String c, String con, int grade, int id, int s) {
		return new Lesson(t,c,con,grade,id, s);
	}
}
