import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Arrays;

class Exercise4
{

	private Map <String, List<String>> nodes;
	private Map <String, String> parent;
	private List<String> solution = new LinkedList<String>();
	private String name1;
	private String name2;
	 
	
	public Exercise4( Map <String, List<String>> nodes )
	{
		this.nodes = nodes;
		this.solution = new ArrayList<String>();
	}

	public void solveExercise4()
	{

		if (nodes.isEmpty()) {
			return;
		}
		
		for( String name : nodes.keySet() ) {							//the first node is the root of the first BFS
			name1 = BFS(name);
			break;
		}
		
		name2 = BFS(name1);												//the last node we found is the root of the second BFS
		String current = name2;
		
		do {															// the solution is the path from name1 to name2
			solution.add(0, current);
			current = parent.get( current);			
		}while( current !=null);
		
		
	}
	
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
	
	

	public void printSolution(){
		if(solution.size()!=0)
		{
			System.out.print( "{ " );
			for( int i=0; i<solution.size(); i++ )
			{
				if (i>0)
					System.out.print(", " );
				
				System.out.print( solution.get(i) );
			}	
			System.out.print( " }\n" );
		}
	}

	
	public String BFS(String root) {
		Map <String, Boolean> visited =  new HashMap<String, Boolean>();
		Queue<String> queue =  new LinkedList<String>();
		parent =  new HashMap<String, String>();
		String current = root;
		String next;
		
		for ( String name : nodes.keySet() ) {
			visited.put(name,false);
		}
		
		queue.add(root);
		visited.put(root, true);
		parent.put(root, null);
		
        while (!queue.isEmpty() ){ 
        	current = queue.poll();   
        	Iterator<String> adjacent = nodes.get(current).listIterator();        	
        	 
        	
        	while (adjacent.hasNext()) {
        		
        		next = adjacent.next();
	            if ( ! visited.get(next) ) {
	            	parent.put(next, current);
	            	current = next;
	        		visited.put(current, true);
	                queue.add(current); 
	            }          
            
	            
        	}    
        } 
		
        
        return current;
	}
	
	
	public void printMap()
	{
		 Iterator it = nodes.entrySet().iterator();
			
		 while (it.hasNext()) {
			 Map.Entry pair = (Map.Entry)it.next();
			 System.out.println( pair.getKey()  ) ;
			 System.out.println( pair.getValue()  ) ;
			 
		 }	
	}
	
	public void printInputData()
	{
		if( nodes !=null )
		{
			System.out.println( "Size of Adjacency-List: "+nodes.size() );
			for (String key   : nodes.keySet()) 
			{
			    List<String> values = nodes.get(key);
			    System.out.println( "-Node "+key+" has neighbors: "+Arrays.toString(values.toArray()) ); 
			}
			System.out.println();
		} 
		else
			System.out.println("Input table is null.");

		System.out.println( "Find the diameter of the graph." );
	}
	
	

}
