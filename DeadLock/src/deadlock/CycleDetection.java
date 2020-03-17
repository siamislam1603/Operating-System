package deadlock;
import java.util.*;
public class CycleDetection {
    static int u,flag[],parent[];
    static LinkedList<Character> adj[];
    static HashMap<Character, Integer> v = new HashMap<>(); 
    static HashMap<Integer, Character> v1 = new HashMap<>();
    static Stack<Character> st=new Stack();
    public CycleDetection(int u) {
       this.u = u;
       adj = new LinkedList[u];
       flag=new int[u];
       parent=new int[u];
       for(int i=0;i<u;i++){
           adj[i] = new LinkedList<>();
           flag[i]=-1;
           parent[i]=-1;
       }
    }
    void addEdge(int u,char v){
        adj[u].add(v);
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        //System.out.print("Number of nodes:");
        int n=in.nextInt();
        for(int i=0;i<n;i++){
            char c=in.next().charAt(0);
            v.put(c,i);
            v1.put(i, c);
        }
        int e=in.nextInt();
        CycleDetection cycleDetection = new CycleDetection(n);
        for(int i=0;i<e;i++){
            char c1=in.next().charAt(0);
            char c2=in.next().charAt(0);
            Integer a=v.get(c1);
            cycleDetection.addEdge(a, c2);
        }
        for(int i=0;i<n;i++){
            cycleDetect(v1.get(i));
        }
    }
    public static void cycleDetect(char c){
        flag[v.get(c)]=0;
        st.push(c);
        if(adj[v.get(c)].size()==0){
            st.pop();
            flag[v.get(c)]=1;
        }
        for(int i=0;i<adj[v.get(c)].size();i++){
            char ch=adj[v.get(c)].get(i);
            if(flag[v.get(ch)]==-1){
                cycleDetect(ch);
                parent[v.get(ch)]=v.get(c);
            }
            else if(flag[v.get(ch)]==0 && flag[v.get(ch)]!=1){
                flag[v.get(c)]=1;
                if(st.contains(ch)==true)
                    cyclePrint(v.get(ch),c);
            }
        }
    }
    public static void cyclePrint(int child,char par){
        Stack<Character> s = new Stack<>();
        int i=v.get(par);
        s.push(par);
        while(i!=child){
            st.pop();
            if(v1.get(parent[i])==null){
                s=new Stack();
                return;
            }
            s.push(v1.get(parent[i]));
            i=parent[i];
        } 
        System.out.println("Cycle:");
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
        System.out.println();
    }
}