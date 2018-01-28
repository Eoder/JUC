package BT;

  

public class Constants   
{  
    /** 
     * BTree�Ľ�<br> 
     * BTree�йؼ��ָ���Ϊ[ceil(m/2)-1,m-1]    <br> 
     * BTree����������Ϊ[ceil(m/2),m] 
     */  
    public static final int BTREE_ORDER = 3;      
      
    /** 
     * �Ǹ��ڵ�����С�Ĺؼ��ָ��� 
     */  
    public static final int MIN_KEY_SIZE = (int) (Math.ceil(Constants.BTREE_ORDER / 2.0) - 1);  
      
    /** 
     * �Ǹ��ڵ������Ĺؼ��ָ��� 
     */  
    public static final int MAX_KEY_SIZE = Constants.BTREE_ORDER - 1;  
      
    /** 
     * ÿ���������С�ĺ��Ӹ��� 
     */  
    public static final int MIN_CHILDREN_SIZE = (int) (Math.ceil(Constants.BTREE_ORDER / 2.0));  
      
    /** 
     * ÿ����������ĺ��Ӹ��� 
     */  
    public static final int MAX_CHILDREN_SIZE = Constants.BTREE_ORDER ;  
      
  
}  
