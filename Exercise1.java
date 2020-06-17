import java.util.ArrayList;
import java.util.Random;

class Exercise1
{
	private int[] inputTable;
    private int[] solution = new int[1];
	public Exercise1( int[] table)
	{
		inputTable= table;
        
	}

    private static int InversionMerge(int[] A, int l, int m, int r) 
    { 
        
        
        int[] tempL =  java.util.Arrays.copyOfRange(A, l, m + 1); 
  
        int[] tempR =  java.util.Arrays.copyOfRange(A, m + 1, r + 1); 
  
        int i = 0, j = 0, indexA = l, inversions = 0; 
  
        while (i < tempL.length && j < tempR.length) { 
            if (tempL[i] <= tempR[j]) 
                A[indexA++] = tempL[i++]; 
            else { 
                A[indexA++] = tempR[j++]; 
                inversions += m + 1 - l - i; 
            } 
        } 
  
        while (i < tempL.length) 
            A[indexA++] = tempL[i++]; 
  
        while (j < tempR.length) 
            A[indexA++] = tempR[j++]; 
  
        return inversions; 
    } 
  
    private static int InversionMergeSort(int[] A, int l, int r) 
    { 
  

        int inversions = 0; 
  
        if (l < r) { 
            int m = (l + r) / 2; 

            inversions += InversionMergeSort(A, l, m); 

            inversions += InversionMergeSort(A, m + 1, r); 

            inversions += InversionMerge(A, l, m, r); 
        } 
  
        return inversions; 
    }     

	public void solveExercise1()
	{
        solution[0] = InversionMergeSort(inputTable, 0, inputTable.length - 1);
	}

	public void printSolution()
	{
		System.out.println(inputTable[0]);
        System.out.print("Number of inversions:");
		if(solution!=null)
			for( int i=0; i<solution.length; i++ )
			{
				System.out.print( solution[i]+" " );
			}
		else
			System.out.print( " Solution for Exercise1 is empty." );
				
		System.out.println();
		 

	}



}
