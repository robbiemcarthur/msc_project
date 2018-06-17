import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class WordProcessor {

	private static <E> String displaySet(Set<E> inputSet){
		//implement this static method to create a
		// String representation of set - 5 comma separated elements per line
		// assume that type E has a toString method

		String word = "";
		int n = 0;

		for(E element:inputSet) {
			if(n==5)
			{
				n=0;
				word+="\n";
			}
			word+=element.toString();
			n++;
		}
		return word;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String [] files = new String[] {"file0.txt", "file1.txt", "file2.txt"};
		Set<String> wordSet = new TreeSet<String>();
		Set <CountedElement<String>> countedWordSet = new TreeSet<CountedElement<String>>();
		int instance = 1;
		for(String fin:files)
		{
			File fileIn = new File(fin);
			try {
				Scanner s = new Scanner(fileIn);
				while(s.hasNextLine())
				{
					String[] words = s.nextLine().split(" ");
					for(String w:words)
					{
						if(!wordSet.contains(w)||wordSet.isEmpty())
						{
//							System.out.println("No instance of " + w + "!");        // TESTING
							wordSet.add(w);
							countedWordSet.add(new CountedElement<String>(w, instance));
						}
						else
						{
							for(CountedElement<String>ce:countedWordSet)
							{
								if(ce.getElement().equals(w))
								{
									ce.incrementCount();
								}
							}
						}
					}
				}
				s.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(CountedElement<String> element: countedWordSet)
			{
				element.compareTo(element);
			}
		}
		System.out.println(displaySet(countedWordSet));
	}
}
