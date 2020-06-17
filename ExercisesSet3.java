import java.io.*;
import java.util.*;

class ExercisesSet3
{

	/*
	* RUNNING FROM COMMAND LINE
	* Assuming that you have .java and input files in the same folder, do the following:
	*	- javac ExercisesSet3.java
	*	- java ExercisesSet3 philosophy_edgelist-1.txt Name1 Name2 lesmiserables.txt
	*
	*
	* RUNNING FROM IDE
	* It would be helpful for us, if you kept all the files in the same folder.
	*
	*/

	public static void main(String[] args)
	{

		exercise1();
		exercise2();
		exercise3();
		exercise4();
		exercise5();


	}

	public static void exercise1()
	{
        System.out.println( "Running exercise 1..." );
		//put array here for testing no need to write it in file.
		int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
		Exercise1 exercise1 = new Exercise1(intArray);
		exercise1.solveExercise1();
		exercise1.printSolution();		
	}

	public static void exercise2()
	{
		//the txt should be in the same file with the code
        System.out.println( "\nRunning exercise 2..." );
		Exercise2 exercise2 = new Exercise2("Huffman.txt");                     //the object must store the input , so that the encoded version of the input can be calculated
		// exercise2.printInputData();
		exercise2.solveExercise2();
		exercise2.printSolution();
	}

	public static void exercise3()
	{
        System.out.println( "\nRunning exercise 3..." );
		//put data here for testing no need to write it in file.
		int[] weights = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
		int capacity = 20;
		Exercise3 exercise3 = new Exercise3(weights,capacity);
		exercise3.solveExercise3();
		exercise3.printSolution();		
	}

	public static void exercise4()
	{
        System.out.println( "\nRunning exercise 4..." );
		//the txt should be in the same file with the code
		Map <String, List<String>> file4Data = Exercise4.readFile( "philosophy_edgelist-1.txt" );
		Exercise4 exercise4 = new Exercise4( file4Data );
		exercise4.printInputData();
		exercise4.solveExercise4();
		exercise4.printSolution();		
	}

	public static void exercise5()
	{
        System.out.println( "\nRunning exercise 5..." );
		//the txt should be in the same file with the code
		Map <String, List<String>> file5Data = Exercise5.readFile("lesmiserables.txt");
		Exercise5 exercise5 = new Exercise5( file5Data );
		// exercise5.printInputData();
		exercise5.solveExercise5();
		exercise5.printSolution();		
	}

	public static  Map<Character,Integer> parseHuff(String filename){
		File file = new File(filename);
		Map<Character,Integer> map = new HashMap<>();
		try {
			Scanner sc = new Scanner(file);
			String line;
			while(sc.hasNextLine()){
				line=sc.nextLine();
				char[] token= line.toCharArray();
				for(Character c:token){
					if(map.containsKey(c)){
						int x = map.get(c);
						x=x+1;
						map.put(c,x);
					}
					else{
						map.put(c,1);
					}

				}
			}
			sc.close();
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return  null;
	}
	public static Map <String, List<String>> parseData( String fileName )
	{
		try
		{
			File file = new File(fileName);
			List<List<String>> parsedData = UtilitiesForSet3.convertFileMatrixToListOfLists(file);
			
			if(validateListOfElements(parsedData))
				return createAdjacencyMatrix(parsedData);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}		
		return null;
	}

	public static boolean validateListOfElements( List<List<String>> parsedData )
	{ 
		for( int i=0; i<parsedData.size(); i++ )
		{
			List<String> elements = parsedData.get(i);
			if( elements.size()!=2 )
			{
				System.out.println( "- Problem with line "+i );
				return false;
			}
		}
		return true;
	}

	public static Map <String, List<String>> createAdjacencyMatrix( List<List<String>> parsedData )
	{
		Map <String, List<String>> AdjacencyList = new HashMap <String, List<String>>();
		
		for( int i=0; i<parsedData.size(); i++ )
		{
			List<String> elements = parsedData.get(i);
			
			if( AdjacencyList.containsKey(elements.get(0)) )
			{
				List<String> listElements = AdjacencyList.get(elements.get(0));
				if( !listElements.contains(elements.get(1)) )
					listElements.add(elements.get(1));	
			}
			else
			{
				List<String> list = new ArrayList<>();
				list.add(elements.get(1));		
				AdjacencyList.put(elements.get(0), list);	
			}			
			
			if( AdjacencyList.containsKey(elements.get(1)) )
			{
				List<String> listElements = AdjacencyList.get(elements.get(1));
				if( !listElements.contains(elements.get(0)) )
					listElements.add(elements.get(0));	
			}
			else
			{
				List<String> list = new ArrayList<>();
				list.add(elements.get(0));		
				AdjacencyList.put(elements.get(1), list);	
			}
			
		}
		return AdjacencyList;
	}

}
