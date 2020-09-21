
package filemanagement;

import java.util.*;

public class Contiguous {
    public void contiguousAllocation(int n){
        Scanner in=new Scanner(System.in);
        ArrayList<String> disk=new ArrayList<String>();
        Map<String,Integer> fat=new HashMap<String,Integer>();
        Map<String,Integer> sz=new HashMap<String,Integer>();
        String name;
        int size=0,remSize=n,currPos=0;
        for(;;){
            System.out.print("Enter \"A\" for Allocation Or \"S\" for Search Or \"E\" for Exit Contiguous:");
            String c=in.nextLine();
            if(c.equalsIgnoreCase("A")){
                System.out.print("Enter Filename:");
                name=in.nextLine();
                System.out.print("Enter File-size:");
                size=in.nextInt();
                if(size<=remSize){
                    fat.put(name,currPos);
                    sz.put(name,size);
                    for(int i=currPos;i<currPos+size;i++){
                        disk.add(name);
                    }
                    currPos=size;
                    remSize-=size;
                    System.out.println("File "+name+" created");
                }
                else{
                    System.out.println("File "+name+" can not be created(not enough free blocks");
                }
            }
            else if(c.equalsIgnoreCase("S")){
                System.out.print("Search Filename:");
                name=in.nextLine();
                if(currPos==0 || !disk.contains(name))
                    System.out.println("File not Found.");
                else{
                    System.out.print("File Found in the blocks :");
                    for(int i=fat.get(name);i<fat.get(name)+sz.get(name);i++)
                        System.out.print((i+1)+",");
                    System.out.println("");
                }
            }
            else if(c.equalsIgnoreCase("E"))
                break;
        }
        
    }
}
