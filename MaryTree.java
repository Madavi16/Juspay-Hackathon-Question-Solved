
import java.util.*;
public class MaryTree
{
    static class Node{
        Integer uid=null; //wrapper class to make it even as null
        String name;
        ArrayList<Node> children= new ArrayList<>(); //dpends on M value
        int lockeddescendants=0; //children count
        Node parent; //giving access to parent
        int ind=0; //position to access the stored or current data
        Node(String name, int ind) //to copy and update data
        {
            this.name=name;
            this.ind=ind;
        }
    }
    static class NaryTree{
        int N;
        int M;
        Node[] nodes;
        Map<String, Node> ref=new HashMap<>();
        
        NaryTree(int N, int M,String[] names)
        {
            this.N=N;
            this.M=M;
            nodes =new Node[N];
            for(int ind=0;ind<N;ind++) 
		    {
		        nodes[ind] = new Node(names[ind],ind);
                ref.put(names[ind],nodes[ind]);
		    }
            int child_index=1;
                for(int parent=0;parent<N && child_index<N;parent++)
                {
                     for(int child=0;child<M&& child_index<N;child++) // for not crossing the index length
                    {
                         nodes[parent].children.add(nodes[child_index]);
                        nodes[child_index].parent=nodes[parent];
                        child_index++;
                    }              
                }
        }

        boolean checkAncestores(Node node)
        {
            while(node!=null)
            {
                if(node.uid!=null)
                {
                    return true;
                }
                node=node.parent;
            }
            return false;
        }
        boolean hasLockedDescendants(Node node)
        {
            return node.lockeddescendants>0;
        }
        void updatelockeddescendants(Node node, int update)
        {
            while(node!=null)
            {
                node.lockeddescendants+=update;
                node = node.parent;
                
            }
        }
        boolean Lock(String name, int uid)
        {
            Node node =ref.get(name);
            if(node.uid !=null || checkAncestores(node) || node.lockeddescendants>0)
            {
                return false;
            }
            node.uid=uid;
            updatelockeddescendants (node.parent,1);
            
        return true;

        }
        boolean Unlock(String name, int uid)
        {
            Node node=ref.get(name);
            if(node.uid==null || !node.uid.equals(uid))
            {
                return false;
            }
            node.uid=uid;
            updatelockeddescendants (node.parent,-1);

            return true;
        }
        boolean collectlockeddescendants(Node node, int uid, List<Node> lockedNodes)
        {
            for(Node child:node.children)
            {
                if(child.uid!=null)
                {
                    if(!child.uid.equals(uid)) return false;
                    lockedNodes.add(child);
                }
                if(!collectlockeddescendants(child, uid, lockedNodes)) return false;
            }
            return true;
        }
        boolean Upgrade(String name, int uid){
            Node node=ref.get(name);
            if(node.uid!=null || node.lockeddescendants==0) return false;

            List<Node> lockedNodes=new ArrayList<>();
            if(!collectlockeddescendants(node, uid, lockedNodes)){
                return false;
            }

            for(Node n:lockedNodes)
            {
                n.uid=null;
                updatelockeddescendants (n.parent,-1);
            }

            node.uid=uid;
            updatelockeddescendants (node.parent,-1);

            return true;

        }
        
    }
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in); 
		int N=input.nextInt();
		int M=input.nextInt();
		input.nextLine();
        
		String[] names=new String[N];
        for(int i=0;i<N;i++)
        {
            names[i]=input.nextLine().trim();
        }

        
		NaryTree tree=new NaryTree(N,M,names);
        int Q=input.nextInt();
        input.nextLine();
        
        for(int q=0;q<Q;q++)
        {
            String[] que=input.nextLine().split("\\s+");
            int operation =Integer.parseInt(que[0]);
           String nodeName= que[1];
            int uid=Integer.parseInt(que[2]);
            boolean result;
            if(operation==1)
            {
                result=tree.Lock(nodeName, uid);

            }
            else if(operation==2)
            {
               result= tree.Unlock(nodeName,uid);
            }
            else{
                result= tree.Upgrade(nodeName,uid);
            }

            System.out.println(result);
        }
		
		input.close();
		
	}
}