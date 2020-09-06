package memorymanagement;

import java.util.*;

public class FirstFit {
    List<Integer> b;
    List<Integer> mr;
    FirstFit(List<Integer> block,List<Integer> memoryReq){
        b = new ArrayList<Integer>(block);
        mr=new ArrayList<Integer>(memoryReq);
    }
    void memoryAllocation(){
        System.out.println("\nMemory Allocation for \"First Fit\":");
        int i=0,flag=0;
        for(i=0;i<mr.size();i++){
            int j=0,block=0;
            flag=0;
            String output="";
            System.out.print("\n\nMemory request - "+mr.get(i)+":\t");
            for(j=0;j<b.size();j++){
                if(b.get(j)>=mr.get(i)){
                    b.set(j,b.get(j)-mr.get(i));
                    output+=b.get(j)+" ";
                    flag=1;
                    block=j+1;
                    break;
                }
                else{
                    output+=b.get(j)+" ";
                }   
            }
            if(j!=b.size()-1)
                for(j=j+1;j<b.size();j++)
                    output+=b.get(j)+" ";
            if(flag==0){
                System.out.println("Can't be allocated");
                break;
            }
            System.out.printf("%-20s \t%d->Block %d",output,mr.get(i),block);
        }
        if(flag==0){
            int fragment=0;
            for(int j=0;j<b.size();j++)
                fragment+=b.get(j);
            System.out.println("Ext. Fragmentation:"+fragment);
        }
        else{
            System.out.println("No External Fragmentation");
        }
    }
}
