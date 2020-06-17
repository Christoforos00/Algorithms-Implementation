class Exercise3
{

	private int[] weights; // 1D array, holding the weight of each item.
	private int capacity; // Box capacity.
	 int[] solution; // The selected items.
	private int count = 0;
	boolean [][] choices , isUsed;

	public Exercise3( int[] weights, int capacity )
	{
		this.weights = weights;
		this.capacity = capacity;	
		choices = new boolean[weights.length+1][capacity+1];
		isUsed = new boolean[weights.length+1][capacity+1];
	}

	public void solveExercise3()
	{
        // solution array contains the items that are used ,  e.g. [ 1 , 2 , 7] if the 1st , 2nd , 7th items are used 
        solution = new int[weights.length];
        
        for (int i=0 ; i<capacity+1 ; i++)							//initializing choices array
        	choices[0][i] = false;									//choices[i ][C] = true if we can fill the capacity C
        															//with items x1,x2...xi      else false
        for (int i=0 ; i<weights.length+1 ; i++)
        	choices[i][0] = true;

        

        
        for (int i=1 ; i<weights.length+1 ; i++) {
        
        	for (int j=1 ; j<capacity+1 ; j++) {

        		isUsed[i][j] = false;        		
        		
        		if (j>=weights[i-1] && choices[i-1][j-weights[i-1]]) {  
        			
        			choices[i][j] = true;	
        			isUsed[i][j] = true;
        		}else if (choices[i-1][j]) {
        			choices[i][j] = true;
        		}else {
        			choices[i][j] = false;
        		}

        	}
        		
        }

        int i = weights.length;
        int j = capacity;
        while ((i > 0) && (j > 0)) {
        	if (choices[i][j] ) {
        		
        		if (isUsed[i][j]) {
	            	solution[count++] = i-1;	
	            	j -= weights[i-1];
        		}
        		
        	}else {
        		break;
        	}
        	i -= 1;

        }

	}

	
	public void printSolution()
	{
		
		if( choices[weights.length][capacity] && solution!=null) {
			System.out.print("The indexes of the chosen items are: " );
			for( int i=0; i<count; i++ )
			{
				System.out.print( solution[i]+" " );
			}
		}
		else
			System.out.print( "Solution for Exercise3 is empty." );
				
		System.out.println();
	}
	
	public void printChoices()
	{

        for (int i=0 ; i<weights.length+1 ; i++) {
        	System.out.println();
        	for (int j=0 ; j<capacity+1 ; j++) {
        		System.out.print(choices[i][j] + " ");
        	}
        		
        }
	}

	
	public void printInputData()
	{
		if( weights !=null )
		{
			int items = weights.length;
			
			for( int i=0; i<items; i++ )
			{
				System.out.print( weights[i]+" " );
			}
			System.out.println();
		} 
		else
			System.out.println("Input table is null.");
		System.out.println( "Capacity: "+capacity );
	}
	
	

}
