package BT;

/** 
 * ��B���ڵ�������������ֵ�ķ��ؽ����<p> 
 * �ý������������ɡ���һ���ֱ�ʾ�˴β����Ƿ�ɹ�,<br> 
 * ������ҳɹ�,�ڶ����ֱ�ʾ������ֵ��B���ڵ��е�λ��,<br> 
 * �������ʧ��,�ڶ����ֱ�ʾ������ֵӦ�ò����λ�á� 
 * @ClassName: Result  
 * @Description: 
 */  
public class SearchResult   
{  
    private boolean result;  
    private int index;  
      
    public SearchResult(boolean result, int index) {  
        super();  
        this.result = result;  
        this.index = index;  
    }  
  
    /** 
     * @return ������ֵ��B���ڵ��е�λ�û��߸�����ֵӦ�ò����λ�� 
     */  
    public int getIndex() {  
        return index;  
    }  
      
    /** 
     * @return �����Ƿ�ɹ� 
     */  
    public boolean getResult(){  
        return result;  
    }  
}  