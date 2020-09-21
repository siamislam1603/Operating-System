
package filemanagement;

import java.util.*;

public class FileManagement {
   public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter total number of blocks:");
        int n=in.nextInt();
        Contiguous contiguous=new Contiguous();
        System.out.println("\nContiguous Allocation:\n");
        contiguous.contiguousAllocation(n);
        NonContiguous nonContiguous=new NonContiguous();
        System.out.println("\nNon-Contiguous Allocation:\n");
        nonContiguous.nonContiguousAllocation(n);
    }
    
}
