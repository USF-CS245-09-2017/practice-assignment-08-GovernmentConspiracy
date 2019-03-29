import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;


public class Practice08Test {
	
	
	public Practice08Test () {
		System.out.println("Starting Practice08Test: binary search trees");
	}
	
	
	public boolean constructorTest() {
		try {
			@SuppressWarnings("unused")
			BST<String> bst = new BST<String>();
			System.out.println("[20%] = Passed constructor test");
			bst = null;
			return true;
		} catch (Exception e) {
			System.out.println("[   ] = Failed constructor test");
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean insertOneTest() {
		String testString = "test string 1";
		String anotherString = "test string 2";
		try {
			BST<String> bst = new BST<String>();
			bst.insert(testString);
			if (bst.find(testString) && ! bst.find(anotherString)) {
				System.out.println("[20%] = Passed insert one item test");
				return true;
			} else {
				throw new Exception("Find error.");
			}
		} catch (Exception e) {
			System.out.println("[   ] = Failed insert one item test");
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean insertTwoTest() {
		String allKeys = "The quick brown fox jumped over the lazy dog.";

		try {
			BST<String> bst = new BST<String>();
			
			Random random = new Random();
			ArrayList<String> list = new ArrayList<String>();
	
			String delims = " .";
			StringTokenizer st = new StringTokenizer(allKeys, delims);
			
			while(st.hasMoreElements()) {
				String token = st.nextToken();
				bst.insert(token);
				if (random.nextInt() > 4) {
					list.add(token);
				}
			}
			if (list.size() == 0) {
				// Pass the test... even though it's not really tested well.
				System.out.println("[20%] = Could not execute insert multiple items test; assumed correct");
				return true;
			}
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				String item = it.next();
				if (! bst.find(item)) {
					throw new Exception("Could not find " + item + " in BST.");
				}
			}
			System.out.println("[20%] = Passed insert multiple items test");
			return true;
		} catch (Exception e) {
			System.out.println("[   ] = Failed insert multiple items test");
			return false;
		}
	}
	
	
	public boolean deleteTest() {
		BST<String> bst = new BST<String>();
		String [] insertables = {"hello", "world"};
		for (String ins : insertables) {
			bst.insert(ins);
		}
		bst.delete(insertables[0]);
		if (bst.find(insertables[0]) || ! bst.find(insertables[1])) {
			System.out.println("[   ] = Failed delete item test");
			return false;
		} else {
			System.out.println("[20%] = Passed delete item test");
			return true;
		}
	}
	
	
	public boolean orderTest() {
		String fromWikipedia = "Grace Brewster Murray Hopper (née Murray December 9, 1906 – January 1, 1992) ";/*+

 				"was an American computer scientist and United States Navy rear admiral. One of the first " +
				"programmers of the Harvard Mark I computer, she was a pioneer of computer programming who " +
				"invented one of the first linkers. She popularized the idea of machine-independent programming " +
				"languages, which led to the development of COBOL, an early high-level programming language " +
				"still in use today. She always dreamed of a programming language written in English." +
				"Prior to joining the Navy, Hopper earned a Ph.D. in mathematics from Yale University and was a " +
				"professor of mathematics at Vassar College. Hopper attempted to enlist in the Navy during World " +
				"War II but was rejected because she was 34 years old. She instead joined the Navy Reserves. " +
				"Hopper began her computing career in 1944 when she worked on the Harvard Mark I team led by " +
				"Howard H. Aiken. In 1949, she joined the Eckert–Mauchly Computer Corporation and was part of the " +
				"team that developed the UNIVAC I computer. At Eckert–Mauchly she began developing the compiler. " +
				"She believed that a programming language based on English was possible. Her compiler converted " +
				"English terms into machine code understood by computers. By 1952, Hopper had finished her program " +
				"linker (originally called a compiler), which was written for the A-0 System. During her wartime " +
				"service, she co-authored three papers based on her work on the Harvard Mark 1.";//*/

		
		BST<String> bst = new BST<String>();String delims = " (,.)";
		
		StringTokenizer st = new StringTokenizer(fromWikipedia, delims);
		
		while(st.hasMoreElements()) {
			String token = st.nextToken();
			bst.insert(token);
		}
		System.out.println("[   ] = Basic insert/retrieve test to be checked by grader. (Everything below until \"Well done...\" should be in alphabetical order.)");
		bst.print();
		return true;  // Probably...
	}
	
	
	public void runTest() {
		if (constructorTest() &&
				insertOneTest() &&
				insertTwoTest() &&
				deleteTest() &&
				orderTest()) {
			// Not adding up the total since the orderTest does not *really* "test" exactly....
			System.out.println("Well done!");
		}
	}

	
	public static void main(String[] args) {
		Practice08Test test = new Practice08Test();
		test.runTest();
	}

}
