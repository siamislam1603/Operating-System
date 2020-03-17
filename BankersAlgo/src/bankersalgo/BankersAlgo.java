package bankersalgo;
import java.util.*;
public class BankersAlgo {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter no of process:");
        int n=in.nextInt();
        System.out.print("Enter no of resources:");
        int m=in.nextInt();
        int r[]=new int[m];
        int av[]=new int[m];
        Process p[]=new Process[n];
        for(int i=0;i<n;i++){
            System.out.println("\nP"+(i+1)+":");
            p[i]=new Process(i+1,m);
            for(int j=0;j<m;j++){
                System.out.print("Maximum value for R"+(j+1)+":");
                p[i].max[j]=in.nextInt();
            }
            for(int j=0;j<m;j++){
                System.out.print("Allocated From R"+(j+1)+":");
                p[i].al[j]=in.nextInt();
                p[i].rem[j]=p[i].max[j]-p[i].al[j];
                av[j]+=p[i].al[j];
            }
        }
        for(int i=0;i<m;i++){
            System.out.print("\nTotal value of R"+(i+1)+":");
            r[i]=in.nextInt();
            av[i]=r[i]-av[i];
        }
        safeSequence(p,av,m);
    }  
    public static void safeSequence(Process p[],int av[],int m){
        int seq[]=new int[p.length],flag=0,count=1;
        for(int i=0;i<p.length;i++){
            int x=0;
            if(seq[i]!=(i+1))
                for(int j=0;j<m;j++){
                    if(p[i].rem[j]>av[j]){
                        break;
                    }
                    else{
                        x++;
                    }
                }
            if(x==m){
                System.out.print(p[i].pName+" ");
                for(int j=0;j<m;j++){
                    av[j]+=p[i].al[j];
                }
                seq[i]=i+1;
                i=-1;
            }
            else if(i==p.length-1 && count==p.length){
                flag=1;
                System.out.println("Unsafe State");
                break;
            }
            count++;
        }
        
        if(flag==0)
            System.out.println("\nis the safe sequence");
    }
}
class Process{
    String pName;
    int al[];
    int max[];
    int rem[];
    Process(int i,int sz){
        this.pName="P"+i;
        max=new int[sz];
        rem=new int[sz];
        al=new int[sz];
    }
}
