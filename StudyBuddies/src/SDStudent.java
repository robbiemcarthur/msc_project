
public class SDStudent extends Student {
	protected final String course = "Software Development";
	protected final String[] modules = new String [] {"Internet Technology", "HCI", "Programming", 
			"Advanced Programming", "Algorithms and Data Structures", "Software Project Management",
			"Software Engineering", "Systems and Networks", "Enterprise Cyber Security", 
			"Database Theory and Application"};
	protected final String[] iTechConcepts = new String [] {"HTML", "Django", "System Architectures", };
	
	public SDStudent() {
		super.course = "Software Development";
		super.modules = this.modules;
	}
}
