package BT;

import java.util.List;



public class BTree 
{
	private BTNode root;			//Btree�ĸ��ڵ�
	private int sizeOfKeys;			//Btree�йؼ��ָ���
	
	/**
	 * Ĭ�������ֻ��һ���ڵ���ΪҶ�ӽ��
	 */
	private BTree()
	{
		root = new BTNode(null);//���ڵ�û�и��ڵ�
	}
	
	private static BTree instance = new BTree();
	
	/**
	 * ����BTree
	 * @return BTree
	 */
	public static BTree newInstance()
	{
		return instance;
	}	
	
	/**
	 * ��BTree�в���ؼ���<p>
	 * �Ӹ��ڵ㿪ʼѰ����ѵĲ�����,��ΪҶ�ӽ��,���Ȳ���keyȻ���ж��Ƿ���Ҫ����;
	 * ��Ϊ��Ҷ�ӽ��,����ϵ���Ѱ����ѵ�Ҷ�ӽ��,Ȼ���ظ�����Ҷ�ӽ������
	 * @param key Integer
	 * @return boolean �Ƿ����ɹ�
	 */
	public boolean insertKey(Integer key)
	{
		BTNode node = root;
		
        while (node != null) //�Ӹ��ڵ㿪ʼ���²���
        {
            if (node.sizeOfChildren() == 0) //Ҷ�ӽڵ�
            {
            	node.addKey(key);
                if (node.sizeOfKeys() <= Constants.MAX_KEY_SIZE) 
                {
                    break;
                } else {//����ؼ���֮��������������size,��Ҫ���з���
                    splitNode(node);
                    break;
                }
            } else {//��Ҷ�ӽ��
                Integer lesser = node.getKey(0);
                if (key.compareTo(lesser) < 0) {//����С�Ĺؼ��ֻ�С
                    node = node.getChild(0);//��ؼ��ֱض����뵽������ߵ�������
                    continue;
                }

                int size = node.sizeOfKeys();
                int last = size - 1;
                Integer greater = node.getKey(last);
                if (key.compareTo(greater) > 0) {//�����Ĺؼ��ֻ���
                    node = node.getChild(size);//��ؼ��ֱض����뵽�����ұߵ�������
                    continue;
                }

                //�����������������������Ҫһ���������м���
                for (int i = 1; i < node.sizeOfKeys(); i++) 
                {
                    Integer prev = node.getKey(i - 1);
                    Integer next = node.getKey(i);
                    if (key.compareTo(prev) > 0 && key.compareTo(next) < 0) 
                    {
                        node = node.getChild(i);
                        break;
                    }
                }
            }
        }
        
        sizeOfKeys ++;
        return true;
	}
	
	
	/**
	 * ɾ��BTree�йؼ��֡����ҵ��ؼ������ĸ������,���û���ҵ��˽����ֱ�ӷ���false��
	 * ����˽������ٶԸý���Ҷ�ӽ��ͷ�Ҷ�ӽ�����ۣ�<br>
	 * <br>
	 * ����Ҷ�ӽ�㣺<br>
	 * 1)����Ҷ�ӽ�����Ǹ��ڵ��� node.sizeOfKeys() < Constants.MIN_KEY_SIZE<br>
	 * 2)Ҷ�ӽ�㵫�Ǹ������node.sizeOfKeys() < Constants.MIN_KEY_SIZE	----->ֻ����һ����Ҫ�ϲ�<br>
	 * 3)����Ҷ�ӽ�����Ǹ��ڵ���node.sizeOfKeys() > Constants.MIN_KEY_SIZE<br>
	 * 4)Ҷ�ӽ�㵫�Ǹ������node.sizeOfKeys() > Constants.MIN_KEY_SIZE<br>
	 * 5)ʵ��ֻ�������������<br>
	 * <br>
	 * ���ڷ�Ҷ�ӽ��:<br>
	 * 1)�Ȼ�ȡ�ؼ���key�����ڵĽ���еĹؼ����б�keys�е�����,ɾ���ùؼ���key<br>
	 * 2)��ȡ��ɾ�����������greatest�е�����Ԫ��,��Ԫ����Ҷ�ڵ���,�������������Ĺؼ������ɾ���Ĺؼ���key<br>
	 * 3)���greatest����еĹؼ��ָ�����������Ҫ�ϲ����<br>
	 * <p>
	 * ���Ȳ���B������ɾ����Ԫ��,�����Ԫ����B���д��ڣ��򽫸�Ԫ���������н���ɾ����
	 * ���ɾ����Ԫ�غ������жϸ�Ԫ���Ƿ������Һ��ӽ�㣬����У������ƺ��ӽ���е�
	 * ĳ���Ԫ��(���������ұߵĽڵ㡱���Һ�������ߵĽڵ㡱)�����ڵ��У�Ȼ�����ƶ�֮
	 * �����������û�У�ֱ��ɾ�����ƶ�֮��������ɾ��Ԫ�أ��ƶ���ӦԪ��֮��
	 * ���ĳ�����Ԫ����Ŀ�����ؼ�������С��ceil(m/2)-1������Ҫ����ĳ�����ֵܽ����
	 * ������������Ԫ�ظ�������ceil(m/2)-1�������ǵõ�һ���й���B���ĵ�5�������е�
	 * c��ô?�� c)�������֮��Ľ�㣨����Ҷ�ӽ�㣩�Ĺؼ��ֵĸ���n�������㣺
	 * (ceil(m / 2)-1)<= n <= m-1��m��ʾ��ຬ��m�����ӣ�n��ʾ�ؼ�������
	 *���� ��һ��5��B����ʾ���У��ؼ�����n���㣺2<=n<=4����������������򸸽ڵ�
	 * ��һ��Ԫ������������������������ֵܶ�����ƶ��������֮��������ĿС��ceil(m/2)-1��
	 * ��ý���������ڵ�ĳһ�ֵܽ����С��ϲ�����һ����㣬�Դ�������������
	 * @param key Integer
	 * @return True if value was removed from the tree.
	 */
	public boolean removeKey(Integer key)
	{
		//���ҵ��ؼ������ĸ������,���û���ҵ��˽����ֱ�ӷ���false
		BTNode node = this.findNode(key);
		if(node == null) 
			return false;
		
		int index = node.getKeys().indexOf(key);//��ȡ��key������
		node.getKeys().remove(key);//ɾ����key
		
		if(node.sizeOfChildren() != 0)//��Ҷ�ӽ��
		{
			BTNode left = node.getChild(index);//����
			BTNode greatest = this.getGreatestNode(left);
			
			//��ȡ��ɾ������������е�����Ԫ��
			Integer replaceValue = greatest.getKeys().remove(greatest.sizeOfKeys() - 1);
			//����Ԫ����ӵ�ɾ���ؼ���key�Ľ����
			node.addKey(replaceValue);
			
			//���ɾ���ؼ��ֺ�������,����Ҫ�ϲ����
			if(greatest.sizeOfKeys() < Constants.MIN_KEY_SIZE)
			{
				this.combined(greatest);	//greatestΪҶ�ӽ��
			}
			
		}else{//Ҷ�ӽ��
			//��Ϊ�������:
			//1)����Ҷ�ӽ�����Ǹ��ڵ��� node.sizeOfKeys() < Constants.MIN_KEY_SIZE
			//2)Ҷ�ӽ�㵫�Ǹ������node.sizeOfKeys() < Constants.MIN_KEY_SIZE	----->ֻ����һ����Ҫ�ϲ�
			//3)����Ҷ�ӽ�����Ǹ��ڵ���node.sizeOfKeys() > Constants.MIN_KEY_SIZE
			//4)Ҷ�ӽ�㵫�Ǹ������node.sizeOfKeys() > Constants.MIN_KEY_SIZE
			if(node.getParentNode() != null && node.sizeOfKeys() < Constants.MIN_KEY_SIZE)
			{
				this.combined(node);
			}else if(node.getParentNode() == null && node.sizeOfKeys() == 0)
			{
				//ɾ���������һ��Ԫ��
				root = null;
			}

		}
		sizeOfKeys --;
		return true;
	}
	
	
	/**
	 * ���ϵ���ֱ��Ҷ�ڵ��ҵ�����Ҷ���
	 * @param node BTNode
	 * @return BTNode
	 */
	private BTNode getGreatestNode(BTNode node) 
	{
		while(node.sizeOfChildren() != 0)
		{
			node = node.getChild(node.sizeOfChildren() - 1);
		}
		return node;
	}

	/**
	 * ��node����йؼ��ָ������㣬��Ҫ�ϲ���㣬��splitNode()�������ƣ�Ҳ�Ǵ�Ҷ�ڵ㿪ʼ���ϵݹ�ġ�<p>
	 * ��Ҫ��������ۣ�	<br>
	 * 1) �˽������ھӴ��������ھӽ���йؼ��ָ��� >= minSize + 1  &nbsp;&nbsp;  �൱������	&clubs;	<br>
	 * 2) �˽������ھӴ��������ھӽ���йؼ��ָ��� >= minSize + 1  &emsp;�൱������ 		&hearts;<br>
	 * 3) �˽������ھӴ����Ҹ��ڵ�ؼ��ָ�������0	&emsp;���ϲ�	&spades;<br>
	 * 4) �˽������ھӴ����Ҹ��ڵ�ؼ��ָ�������0	&emsp;���ϲ�	&diams;<br>
	 * @param node BTNode
	 */
	private void combined(BTNode node) 
	{
		//�Ȼ�ȡ�˽��ĸ��ڵ�
		BTNode parentNode = node.getParentNode();
		//��ȡ�˽�����丸�ڵ��е����������ڼ�������
		int index = parentNode.getChildNodes().indexOf(node);
		int indexOfLeftNeighbor = index - 1;
		int indexOfRightNeighbor = index + 1;
		
		BTNode rightNeighbor = null;
		int rightNeighborSize = 0;//???
		if(indexOfRightNeighbor < parentNode.sizeOfChildren())//���ھӴ���
		{
			rightNeighbor = parentNode.getChild(indexOfRightNeighbor);
			rightNeighborSize = rightNeighbor.sizeOfKeys();
		}
		
		//���ھӴ�������ؼ��ָ���������Сֵ
		if(rightNeighbor != null && rightNeighborSize > Constants.MIN_KEY_SIZE)
		{
			//�൱������
			Integer removeValue = rightNeighbor.getKeys().remove(0);
			int prev = getIndexOfPreviousValue(parentNode, removeValue);
			Integer parentValue = parentNode.getKeys().remove(prev);
			node.addKey(parentValue);
			parentNode.addKey(removeValue);
			
			if(rightNeighbor.sizeOfChildren() > 0)//������ھӵĺ��ӽ����ڣ�����Ҫ�����ھӵĵ�һ�����ӽ��ɾ������ӵ�node�����
			{
				node.addChild(rightNeighbor.getChildNodes().remove(0));
			}
		}else{
			BTNode leftNeighbor = null;
			int leftNeighborSize = 0;//???
			if(indexOfLeftNeighbor >= 0)//���ھӴ���
			{
				leftNeighbor = parentNode.getChild(indexOfLeftNeighbor);
				leftNeighborSize = leftNeighbor.sizeOfKeys();
			}
			
			//���ھӴ�������ؼ��ָ���������Сֵ
			if(leftNeighbor != null && leftNeighborSize > Constants.MIN_KEY_SIZE)//���ھӴ�������ؼ��ָ���������Сֵ
			{
				//�൱������
				Integer removeValue = leftNeighbor.getKeys().remove(leftNeighbor.sizeOfKeys() - 1);
				int next = getIndexOfNextValue(parentNode, removeValue);
				Integer parentValue = parentNode.getKeys().remove(next);
				node.addKey(parentValue);
				parentNode.addKey(removeValue);
				
				if(leftNeighbor.sizeOfChildren() > 0)//������ھӵĺ��ӽ����ڣ�����Ҫ�����ھӵ����һ�����ӽ��ɾ������ӵ�node�����
				{
					node.addChild(leftNeighbor.getChildNodes().remove(leftNeighbor.sizeOfChildren() - 1));
				}
				
			}else if(rightNeighbor != null && parentNode.sizeOfKeys() > 0)//���ھӴ����Ҹ��ڵ�ؼ��ָ�������0
			{
				Integer rightValue = rightNeighbor.getKey(0);//��ȡ���ھӽ��������ߵĹؼ���
				int prev = getIndexOfPreviousValue(parentNode, rightValue);//��ȡrightValue�ؼ��ֵĸ��ڵ��в����ڵ���ӽ��˹ؼ��ֵ�����
				Integer parentKey = parentNode.getKeys().remove(prev);//�ڸ��ڵ���ɾ����������Ӧ�Ĺؼ���
				parentNode.removeChild(rightNeighbor);////�ڸ��ڵ���ɾ����������Ӧ�ĺ��ӽ��
				node.addKey(parentKey);//��ɾ���Ĺؼ�����ӵ��ؼ�������Ľ����
				
				//�����ھ��еĹؼ�����ӽ�ȥ
				for(int i = 0 ; i < rightNeighbor.sizeOfKeys(); i ++)
				{
					node.addKey(rightNeighbor.getKey(i));
				}
				
				//�����ھ��еĺ��ӽ��Ҳ��ӽ�ȥ
				for(int i = 0 ; i < rightNeighbor.sizeOfChildren(); i ++)
				{
					node.addChild(rightNeighbor.getChild(i));
				}
				
				if(parentNode.getParentNode() != null && parentNode.sizeOfKeys() < Constants.MIN_KEY_SIZE)//��û������ڵ�
				{
					this.combined(parentNode);
					
				}else if(parentNode.sizeOfKeys() == 0){
					//���ڵ���û�йؼ����ˣ��򽵵����ĸ߶�
					node.setParentNode(null);//ע�⣺���ĸ߶Ƚ���һ�㣬�˽��ͱ�Ϊ���ڵ㣬һ��Ҫ�����丸�ڵ�Ϊnull
					parentNode = null;
					root = node;
				}
				
			}else if(leftNeighbor != null && parentNode.sizeOfKeys() > 0)	//���ھӴ����Ҹ��ڵ�ؼ��ָ�������0
			{
				Integer leftValue = leftNeighbor.getKey(leftNeighbor.sizeOfKeys() - 1);//��ȡ���ھӽ�������ұߵĹؼ���
				int next = getIndexOfNextValue(parentNode, leftValue);//��ȡleftValue�ؼ��ֵĸ��ڵ��в�С�ڵ���ӽ��˹ؼ��ֵ�����
				Integer parentKey = parentNode.getKeys().remove(next);//�ڸ��ڵ���ɾ����������Ӧ�Ĺؼ���
				parentNode.removeChild(leftNeighbor);////�ڸ��ڵ���ɾ����������Ӧ�ĺ��ӽ��
				node.addKey(parentKey);//��ɾ���Ĺؼ�����ӵ��ؼ�������Ľ����
				
				//�����ھ��еĹؼ�����ӽ�ȥ
				for(int i = 0 ; i < leftNeighbor.sizeOfKeys(); i ++)
				{
					node.addKey(leftNeighbor.getKey(i));
				}
				
				//�����ھ��еĺ��ӽ��Ҳ��ӽ�ȥ
				for(int i = 0 ; i < leftNeighbor.sizeOfChildren(); i ++)
				{
					node.addChild(leftNeighbor.getChild(i));
				}
				
				if(parentNode.getParentNode() != null && parentNode.sizeOfKeys() < Constants.MIN_KEY_SIZE)//��û������ڵ�
				{
					this.combined(parentNode);
					
				}else if(parentNode.sizeOfKeys() == 0){
					//���ڵ���û�йؼ����ˣ��򽵵����ĸ߶�
					node.setParentNode(null);//ע�⣺���ĸ߶Ƚ���һ�㣬�˽��ͱ�Ϊ���ڵ㣬һ��Ҫ�����丸�ڵ�Ϊnull
					parentNode = null;
					root = node;
				}
			}
		}//end else
	}

	/**
	 * ����node�����ֵ�����ڵ���ӽ���value�Ĺؼ��ֵ�����������value���򷵻�0������valueС�򷵻�size-1
	 * @param node BTNode
	 * @param value Integer
	 * @return int
	 */
	private int getIndexOfPreviousValue(BTNode node, Integer value) 
	{
		for (int i = 1; i < node.sizeOfKeys(); i++) 
		{
			Integer t = node.getKey(i);
            if (t.compareTo(value) >= 0) 
            	return i - 1;
        }
        return node.sizeOfKeys() - 1;
	}

	/**
	 * ����node�����ֵ��С�ڵ���ӽ���value�Ĺؼ��ֵ�����������value���򷵻�0������valueС�򷵻�size-1
	 * @param node BTNode
	 * @param value Integer
	 * @return
	 */
	private int getIndexOfNextValue(BTNode node, Integer value)
	{
		for(int i = 0 ; i < node.sizeOfKeys(); i ++)
		{
			Integer t = node.getKey(i);
			
			if(t.compareTo(value) >= 0)
			{
				return i;
			}
		}
		return node.sizeOfKeys() - 1;
	}
	
	/**
	 * ��BTree�в���key���������򷵻ش�BTNode���������򷵻�null
	 * @param key Integer
	 * @return BTNode
	 */
	public BTNode findNode(Integer key) 
	{
		BTNode node = root;
		
		while(node != null)
		{
			if(node.sizeOfChildren() == 0)//Ҷ�ӽ��
			{
				if(node.getKeys().contains(key))
				{
					return node;
				}
				return null;
				
			}else{//��Ҷ�ӽ��
				if(key.compareTo(node.getKey(0)) < 0)//����С��С
				{
					node = node.getChild(0);
					continue;
				}
				
				if(key.compareTo(node.getKey(node.sizeOfKeys() - 1)) > 0)//�����Ļ���
				{
					node = node.getChild(node.sizeOfKeys());
					continue;
				}
				
				//�м����
				for(int i = 1 ;i < node.sizeOfKeys(); i ++)
				{
					if(key.compareTo(node.getKey(i)) == 0)
					{
						return node;
					}else if(key.compareTo(node.getKey(i - 1)) > 0 && key.compareTo(node.getKey(i)) < 0)
					{
						node = node.getChild(i);
						break;
					}
				}
				
			}
		}
		return null;
	}

	/**
	 * @return BTree�йؼ��ָ���
	 */
	public int sizeOfKeys()
	{
		return sizeOfKeys;
	}
	

	/**
	 * ��Ҷ�ӽڵ㿪ʼ���µ��ϵݹ����
	 * @param node BTNode
	 * @param index int
	 */
	private void splitNode(BTNode node) 
	{
		//����λ�÷�����size/2,���ѳ��������
		int splitIndex = node.sizeOfKeys() / 2;
		//��Ҫ���ƵĹؼ���
		Integer splitKey = node.getKeys().get(splitIndex);
		
//		List<Integer> copy1 = new ArrayList<>(node.getKeys().subList(0, splitIndex));//---2
//		List<Integer> copy2 = new ArrayList<>(node.getKeys().subList(splitIndex + 1, node.getKeys().size()));//----2
		
		//�����µĽ��
		BTNode left = new BTNode(null);
//		left.setKeys(node.getKeys().subList(0, splitIndex));	
//		left.setKeys(copy1);	//-----2
		for(int i = 0 ; i < splitIndex; i ++)
		{
			left.addKey(node.getKey(i));
		}
		if(node.sizeOfChildren() > 0)
		{
			left.addChildren(node.getChildNodes().subList(0, splitIndex + 1));//����������Ҫע��
		}
		
		BTNode right = new BTNode(null);
//		right.setKeys(node.getKeys().subList(splitIndex + 1, node.getKeys().size()));
//		right.setKeys(copy2);	//----2
		for(int i = splitIndex + 1; i < node.sizeOfKeys(); i ++)
		{
			right.addKey(node.getKey(i));
		}
		if(node.sizeOfChildren() > 0)
		{
			right.addChildren(node.getChildNodes().subList(splitIndex + 1, node.sizeOfChildren()));
		}
		
		if(node.getParentNode() != null)//�и��ڵ�
		{
			BTNode parent = node.getParentNode();//ȡ���丸�ڵ�
			parent.addKey(splitKey);
			parent.removeChild(node);
			parent.addChild(left);
			parent.addChild(right);
			
			if(parent.sizeOfKeys() > Constants.MAX_KEY_SIZE)
			{
				splitNode(parent);
			}
			
		}else{//û�и��ڵ�,��������ڵ�
			BTNode newRoot = new BTNode(null);
			newRoot.getKeys().add(splitKey);
			root = newRoot;
			newRoot.addChild(left);
			newRoot.addChild(right);
		}
	}


	/**
	 * ����ؼ�������
	 * @param keys Integer[]
	 * @return boolean �Ƿ����ɹ�
	 */
	public boolean insertKeys(Integer[] keys)
	{
		boolean isInsert ;
		for(int i = 0 ; i < keys.length; i ++)
		{
			isInsert = insertKey(keys[i]);
			if(! isInsert)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * ����ؼ�������
	 * @param keys List
	 * @return boolean �Ƿ����ɹ�
	 */
	public boolean insertKeys(List<Integer> keys)
	{
		Integer[] keysArray  = (Integer[]) keys.toArray();
		return insertKeys(keysArray);
	}
	

	
	private static class TreePrinter 
    {

        public static  String getString(BTree tree) 
        {
            if (tree.root == null) return "Tree has no nodes.";
            return getString(tree.root, "", true);
        }

        private static String getString(BTNode node, String prefix, boolean isTail) 
        {
            StringBuilder builder = new StringBuilder();

            builder.append(prefix).append((isTail ? "������ " : "������ "));
            for (int i = 0; i < node.sizeOfKeys(); i++) {
                Integer value = node.getKey(i);
                builder.append(value);
                if (i < node.sizeOfKeys() - 1) builder.append(", ");
            }
            builder.append("\n");

            if (node.getChildNodes() != null) {
                for (int i = 0; i < node.sizeOfChildren() - 1; i++) {
                	BTNode obj = node.getChild(i);
                    builder.append(getString(obj, prefix + (isTail ? "    " : "��   "), false));
                }
                if (node.sizeOfChildren() >= 1) {
                	BTNode obj = node.getChild(node.sizeOfChildren() - 1);
                    builder.append(getString(obj, prefix + (isTail ? "    " : "��   "), true));
                }
            }

            return builder.toString();
        }
    }
    
    public static void main(String[] args) 
    {
		BTree bTree = BTree.newInstance();
		
		for(int i = 1 ; i <= 10; i ++)
		{
			bTree.insertKey(i);
			System.out.println(TreePrinter.getString(bTree));
		}
		
		for(int i = 10 ; i > 0; i --)
		{
			bTree.removeKey(i);
			System.out.println(TreePrinter.getString(bTree));
		}

	}
	
}
