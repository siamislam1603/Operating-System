package memorymanagement;
import java.util.*;
public class BestFit {
    List<Integer> b;
    List<Integer> mr;
    BestFit(List<Integer> block,List<Integer> memoryReq){
        b = new ArrayList<Integer>(block);
        mr=new ArrayList<Integer>(memoryReq);
    }
    void memoryAllocation(){
        System.out.println("\nMemory Allocation for \"Best Fit\":");
        int i=0,flag=0;
        for(i=0;i<mr.size();i++){
            int j=0,minDiff=Integer.MAX_VALUE,index=-1;
            flag=0;
            String output="";
            System.out.print("\n\nMemory request - "+mr.get(i)+":\t");
            for(j=0;j<b.size();j++){
                if(b.get(j)>=mr.get(i)){
                    if((b.get(j)-mr.get(i))<minDiff){
                        minDiff=b.get(j)-mr.get(i);
                        index=j;
                    }
                }   
            }
            if(index!=-1){
                for(j=0;j<b.size();j++){
                    if(index==j){
                        b.set(j, b.get(j)-mr.get(i));
                        output+=b.get(j)+" ";
                        flag=1;
                        break;
                    }
                    else{
                        output+=b.get(j)+" ";
                    } 
                }
            }
            if(j!=b.size()-1)
                for(j=j+1;j<b.size();j++)
                    output+=b.get(j)+" ";
            if(flag==0){
                System.out.println("Can't be allocated");
                break;
            }
            System.out.printf("%-20s \t%d->Block %d",output,mr.get(i),(index+1));
        }
        if(flag==0){
            int fragment=0;
            for(int j=0;j<b.size();j++)
                fragment+=b.get(j);
            System.out.println("Ext. Fragmentation:"+fragment);
        }
        else{
            System.out.println("\nNo External Fragmentation");
        }
    }
}
