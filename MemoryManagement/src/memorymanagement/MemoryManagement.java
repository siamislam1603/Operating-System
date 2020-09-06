package memorymanagement;

import java.util.*;

public class MemoryManagement {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.print("No. of memory holes:");
        int n=in.nextInt();
        List<Integer> b=new ArrayList<Integer>();
        System.out.print("Memory holes:");
        for(int i=0;i<n;i++)
            b.add(in.nextInt());
        System.out.print("No. of requests:");
        int r=in.nextInt();
        List<Integer> mr=new ArrayList<Integer>();
        System.out.print("Memory requests:");
        for(int i=0;i<r;i++){
            mr.add(in.nextInt());
        }
        FirstFit firstFit=new FirstFit(b,mr);
        firstFit.memoryAllocation();
        WorstFit worstFit=new WorstFit(b,mr);
        worstFit.memoryAllocation();
        BestFit bestFit=new BestFit(b,mr);
        bestFit.memoryAllocation();
    }
    
}
