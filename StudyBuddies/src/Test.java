public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Name Generator Test
		//		NameGenerator ng = new NameGenerator();
		//		ng.loadNames(50);
		//		for (int i = 0; i <25; i++)
		//		{
		//		String name = ng.GenerateName();
		//		System.out.println(name);

		// Student Generator Test
		StudentGenerator sg = new StudentGenerator();
		for(int i = 0; i < 10; i++) {
			SDStudent s = (SDStudent) sg.makeStudent();
			System.out.println("Student: " + s.getName());
			System.out.println("Course: " + s.getCourse());
			System.out.println("Module: Programming");
			System.out.println("Concept scores: " + s.getScores());
		}
	}
}

//}
