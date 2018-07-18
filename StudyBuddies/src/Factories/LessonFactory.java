package Factories;

import java.util.ArrayList;

import StudentGraph.Lesson;

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
	
	public Lesson createEmptyLesson () {
		return new Lesson();
	}
	
	public ArrayList<Lesson> createEmptyLessons(int n) {
		for(int i = 0; i<=n; i++) {
			lessons.add(new Lesson());
		}
		return this.lessons;
	}
	
	public Lesson createDummyLesson() {
		return new Lesson("Mr. X", "math", "algebra", 0, id++, "Robbie McArthur");	
	}
	
	public ArrayList<Lesson> getLessons() {
		return this.lessons;
	}
}
