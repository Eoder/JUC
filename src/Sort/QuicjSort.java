package Sort;

/*
 * ��۵ݹ飬΢��ϸ��
 * ���㷨���ա��㷨���ۡ������¿�������
 */


public class QuicjSort {
	
	

	//΢��ϸ��

	public static int partition(int []array,int p,int r){
		
		
		//ѡȡ���һ���ڵ���Ϊ pivot
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
	    


	//��۵ݹ�

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
	            System.out.print(a[i] + "��");
	        }
	   }
	
}
