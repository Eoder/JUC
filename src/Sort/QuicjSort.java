package Sort;

/*
 * 宏观递归，微观细推
 * 该算法参照《算法导论》第七章快速排序。
 */


public class QuicjSort {
	
	

	//微观细推

	public static int partition(int []array,int p,int r){
		
		
		//选取最后一个节点作为 pivot
	    int pivot = array[r];
	    int i = p-1;
	    for(int j = p; j<= r-1; j++){
	    	if (array[r] <= pivot){
	    		i = i+1;
	    		array[i] = array[j];
	    	}
	    }
	    array[i+1]=array[r];
	    return i+1;
	    
			
	}
	    


	//宏观递归

	public static void QuickSort(int[] array,int lo ,int hi){
		
	    if(lo>=hi){
	        return ;
	    }
	    int index=partition(array,lo,hi);
	    QuickSort(array,lo,index-1);
	    QuickSort(array,index+1,hi); 
	}
	
	 public static void main(String[] args) {

	        int[] a = new int[]{4, 3, 6, 1, 2, 5,8,55,7,32,1};
	        QuickSort(a, 0, a.length-1);
	        for (int i = 0; i < a.length; ++i) {
	            System.out.print(a[i] + "　");
	        }
	   }
	
}
