package main.Factories;

import java.util.ArrayList;

import main.StudentGraph.Lesson;

/**
 * 
 * @author Robbie McArthur - 2098323m
 * 
 * 
 * Factory class to produce Lessons
 */
public class LessonFactory {
	private ArrayList<Lesson> lessons;
	private int id;
	
	public LessonFactory() {
		lessons = new ArrayList(); 
		id = 0;
	}
	
	
	public Lesson getLesson () {
		return new Lesson();
	}
	
	public Lesson getLesson (String t, String c, String con, int grade, int id) {
		return new Lesson(t,c,con,grade,id);
	}
	
	public ArrayList<Lesson> createEmptyLessons(int n) {
		for(int i = 0; i<=n; i++) {
			lessons.add(new Lesson());
		}
		return this.lessons;
	}
	
	public Lesson getDummyLesson() {
		return new Lesson("Mr. X", "Math", "Algebra", 0, id++);	
	}
	
	
	public ArrayList<Lesson> getLessons() {
		return this.lessons;
	}
}
