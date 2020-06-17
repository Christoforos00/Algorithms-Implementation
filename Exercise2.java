
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Random;

class Exercise2
{
	HashMap<String ,Integer> frequency = new HashMap<String ,Integer>();
	HashMap<String ,String> symbolMap = new HashMap<String ,String>();
	PriorityQueue<Node> pQueue = new PriorityQueue<Node>(); 
	String input,output;
	
	//Node class representing the nodes of the huffman tree
	public class Node implements Comparable<Node>{
		String symbol;
		int frequency;
		Node child0 = null, child1= null, child2 = null;
		
		public Node(String s , int f) {
			symbol =s;
			frequency =f;
		}
		
		public boolean setChild( Node child) {
			if (child0 == null) 
				child0 = child;
			else if (child1 == null) 
				child1 = child;	
			else if (child2 == null) 
				child2 = child;			
			else 
				return false;
			
		
			return true;
		}
		
		public int compareTo(Node other) {
		    return Integer.compare(this.frequency, other.frequency);
		}
		
		public String toString() {
		    return symbol + "-" +frequency  ;
		}
		
        //print the children nodes
		public String showChildren() {
			return child0 + " " + child1 + " " + child2 ;
		}
	}


	public Exercise2( String file )
	{
		input = textInput( file  );
        
	}

	public void solveExercise2()
	{
		toPQ();
		toTree();
		Node head =pQueue.poll() ;
		toMap(head , "");
		output = "";
		
		StringTokenizer st = new StringTokenizer(input, input,true);
		String currentSymbol;
		
		while ( st.hasMoreTokens()) {
			currentSymbol = st.nextToken();
			output += symbolMap.get(currentSymbol);
			
		}
	}


	//Counting the frequency of every symbol in the original string
	public String textInput(String path) {
		
		String line,current,all ;
		StringTokenizer st;
		all="";
        try {
    		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
    		line = reader.readLine();                                   

    		
    		while( line!=null){
	                       
	    		st = new StringTokenizer(line, line,true);	
				while ( st.hasMoreTokens()) {
					current = st.nextToken();
					all+=current;
					if (frequency.putIfAbsent(current,1) != null ) {
						frequency.replace(current, frequency.get(current) +1);
					}
				}	
				line = reader.readLine(); 
			}
    		return all;
    	}catch (IOException e){
    	      System.err.println("Error Reading File...");
    	      return all;
    	}
	}
	
	//After counting the frequency we put every symbol in a PQ as a NOde object
	public void toPQ() {
		
		 Iterator it = frequency.entrySet().iterator();
		
		 while (it.hasNext()) {
			 Map.Entry pair = (Map.Entry)it.next();
			 pQueue.add( new Node ( (String)pair.getKey() , (int) pair.getValue()) );
			 
		 }
		 
	}
	
	
	//We use the PQ to extrect the minimum nodes to create the huffman tree
	public void toTree() {
		
		 while ( pQueue.size() >1) {

			 Node newNode = new Node( null,0 );
			 for ( int i=0 ; i<3 ; i++ ) {
				 Node min = pQueue.poll();

				 if (min !=null) {
					 newNode.setChild( min);
					 newNode.frequency += min.frequency;
				 }
				 
			 }
			 pQueue.add(newNode);
			 
		 }
	}
	
	
	//converting the huffman tree to a hashmap , so that we can get the encoded version of each symbol fast
	public void toMap(Node head , String s) {

		if ( head != null ) {
			
			if (head.symbol == null) {
				toMap( head.child0 , s+"0" );
				toMap( head.child1 , s+"1" );				
				toMap( head.child2 , s+"2" );
			}else {
				symbolMap.put( head.symbol, s);
			}			
			
		}
				
	}
	
	
	//Method to print the encoding of each symbol
	public void printCode() {
		
		if (symbolMap.isEmpty()) {
			System.out.println( "Not encoded yet.");
			return;
		}
			
		System.out.println( "Encoding of each symbol:");
		Iterator it = symbolMap.entrySet().iterator();
			
		 while (it.hasNext()) {
			 Map.Entry pair = (Map.Entry)it.next();
			 System.out.println( pair.getKey() + " " + pair.getValue()  );
			 
		 }
		
	}

	public void printSolution()
	{
		/*
		if(solution!=null)
			for( int i=0; i<solution.length; i++ )
			{
				System.out.print( solution[i]+" " );
			}
		else
			System.out.print( " Solution for Exercise2 is empty." );
				
	    /System.out.println();
        */

		 System.out.println("The encoded input is: " +output);
	}



}
