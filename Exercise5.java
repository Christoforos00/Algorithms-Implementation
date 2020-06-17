import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Iterator;


class Exercise5
{

	private Map <String, List<String>> nodes;
	private List<String> solution1;
	private double t1;
	private List<String> solution2;
	private double t2;

	public Exercise5( Map <String, List<String>> nodes )
	{
		this.nodes = nodes;
		this.solution1 = new ArrayList<>();
		this.t1 = 0; // time in milliseconds
		this.solution2 = new ArrayList<>();
		this.t2 = 0;  // time in milliseconds
	}

	
	public void solveExercise5(){
		/*
		* Implement your solution.
		* Use the following code to get the time (in milliseconds) for each solution.
		* - long start = System.currentTimeMillis();
		* - // your solution
		* - long end = System.currentTimeMillis(); 
		* - t1 = (end-start); // in the same way t2 = (end-start);
		*/
		solution1 = findSolution1();
		solution2 = findSolution2();
	}
	
	
    /**
	*	Implements the exhaustive algorithm and returns the max Clique that was found.
    */
	public List<String> findSolution1() {
		List<String> maxClique=null;
		List<String> currentClique = new ArrayList<String>();
		String[] nodesArray = new String[nodes.size()];
		long start = System.nanoTime(); 
		
		for (int k=1 ; k< nodes.size(); k++) {						                                                    	//beginning with k =1
			
			currentClique = getMaxClique( nodes.keySet().toArray(nodesArray) ,  k ) ;       //finding a clique with size k
			if (currentClique!=null)																		                    	//if it exists it is the new max clique
				maxClique = currentClique;	
			else																							                            //if not we stop the search
				break;
			
		}
		long end = System.nanoTime(); 											                                                            	//saving the time
		t1 = BigDecimal.valueOf((end-start)).multiply(BigDecimal.valueOf(0.000001)).doubleValue();				//using Big Decimal class to prevent overflow
		return maxClique;
		
	}
	
    /**
	*	Implements the greedy algorithm and returns the max Clique that was found.
    */
	
	public List<String> findSolution2() {
		List<String> maxClique = new ArrayList<String>();
		long start = System.nanoTime(); 
		
		for (String name : nodes.keySet()) {							//iterating through every name
						
		
			if ( isStillClique(maxClique,name) )								// we add the current name to the current max clique
				maxClique.add(name);											//if the maxCLique will remain a clique 
			
		}
		long end = System.nanoTime(); 
		t2 = BigDecimal.valueOf((end-start)).multiply(BigDecimal.valueOf(0.000001)).doubleValue();			//using Big Decimal class to prevent overflow
		return maxClique;
	}
	
	
    /**
	*	Recursively returns a combination of k elements from the given array.
	*/
	List<String> maxCliqueRecursive(String allNodesArray[], String currentComb[], int start,int end, int combIndex, int k) { 
		
		
		List<String> list = new ArrayList<String>();				//start & end are the the current indexes in allNodesArray[] ,	
																	//combIndex ---> Current index in currentComb[] 	

		if (combIndex == k) { 											// If current list is ready , return it		
			for (int j=0; j<k ; j++) 
				list.add(currentComb[j]);
			
		return list; 
		} 
		
		
		
		List<String> subList ;
		for (int i=start; i<=end && end-i+1 >= k-combIndex; i++){ 					// replace index with all possible elements. The condition 
			currentComb[combIndex] = allNodesArray[i]; 								// "end-i+1 >= k-combIndex" makes sure that including one element 
																				// at index will make a combination with remaining elements 
			subList = new ArrayList<String>();									// at remaining positions 
			for (int j=0; j< combIndex+1 ; j++) {												//creating the helper subList
				if (currentComb[j]!=null) 
					subList.add(currentComb[j]);			
			}
			if ( !isClique(subList) ) 															//if the subList is not a clique we
				continue;																		//don't need to look further in this path
			
			
			list = maxCliqueRecursive(allNodesArray, currentComb, i+1, end, combIndex+1, k);		//else we look at all the possible combinations		
			if ( list!= null && isClique(list) ) 												//if we have found at least one clique 
				return list;																	//of size k we return it
			
		} 
		return null;
	} 
	
    
	
    /**
	*	Returns a clique of size k if it exists , else returns null.
    */
	List<String> getMaxClique(String allNodesArray[], int k) 
		{   
            String currentComb[]=new String[k];                                                           //temporary array for the combinations			
			return maxCliqueRecursive(allNodesArray, currentComb, 0, allNodesArray.length-1, 0, k); 
		} 	
			
	

    /**
	*	Returns true if the list will still be a clique if we add the newNode to it.
    */
	public boolean isStillClique( List<String> list , String newNode) {
		List<String> currentNeighbours = nodes.get(newNode);	
		return currentNeighbours.containsAll(list);

	}

	
	
    /**
	*	Returns true if the given list of names is a clique in the graph.
    */
	public boolean isClique( List<String> list ) {
		List<String> currentNeighbours;
		for(String name : list ) {									//for every name in the given list
			currentNeighbours = nodes.get(name);					//we get its neighborhood from the graph
			currentNeighbours.add(0,name);							//we add the name in its own neighborhood temporarily
			if ( !currentNeighbours.containsAll(list) ) {			//if not every name in the given list is in its neighborhood
				currentNeighbours.remove(0);
				return false;										//then the given list is not a clique

			}
			currentNeighbours.remove(0);							//removing the name from its neighborhood

		}		
		return true;
	}

	
	
	public void printSolution()
	{
		System.out.print( "\nExhaustive solution: ");
		if(solution1.size()!=0)
		{
			System.out.print( "{");
			for( int i=0; i<solution1.size(); i++ )
			{
				if (i>0) {
					System.out.print(", ");
				}
				System.out.print( solution1.get(i) );
			}
			System.out.print( "},"+t1 );
		}
		
		System.out.print( "\nGreedy solution: ");
		if(solution2.size()!=0)
		{
			System.out.print( "{");
			for( int i=0; i<solution2.size(); i++ )
			{
				if (i>0) {
					System.out.print(", ");
				}
				System.out.print( solution2.get(i) );
			}
			System.out.println( "},"+t2 + "\n");
		}
	}

		
	
    /**
	*	Helper function that reads the file and returns the hashmap of the graph.
    */
	public static Map <String, List<String>> readFile(String path ) {
		Map <String, List<String>> nodes = new HashMap<String, List<String>>();
		String line , name1 , name2;
		StringTokenizer st;


		 
        try {
    		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
    		line = reader.readLine();                                   

    		
    		while( line!=null){
	                       
        		st = new StringTokenizer(line , "\t");
	            name1 = st.nextToken();
	            name2 = st.nextToken();
	            line = reader.readLine();                                   
	         
	            if(! nodes.containsKey(name1)) 
	            	nodes.put(name1, new ArrayList<String>());
	            
	            if(! nodes.containsKey(name2)) 
	            	nodes.put(name2, new ArrayList<String>());
	            
	            if( !nodes.get(name1).contains(name2) )  
		            nodes.get(name1).add(name2);
	            
	            if( !nodes.get(name2).contains(name1) )  
		            nodes.get(name2).add(name1);
    
    		}
	            
           	                
               reader.close();

        }catch (IOException e){
      System.err.println("Error Reading File...");

    }
    
	
		return nodes;
		
	}
	
	
	
	
	
	public void printInputData()
	{
		if( nodes !=null )
		{
			System.out.println( "Size of Adjacency-List: "+nodes.size() );
			for (String key   : nodes.keySet()) 
			{
			    List<String> values = nodes.get(key);
			    System.out.println( "-Node "+key+" has neighbors: "+ Arrays.toString(values.toArray()) ); 
			}
			System.out.println();
		} 
		else
			System.out.println("Input table is null.");
	}
	
	
}
