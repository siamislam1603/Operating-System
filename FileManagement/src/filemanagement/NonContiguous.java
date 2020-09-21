package filemanagement;

import java.util.*;
public class NonContiguous {
    public void nonContiguousAllocation(int n){
        Scanner in=new Scanner(System.in);
        Map<Integer,String> disk=new HashMap<Integer,String>();
        ArrayList<String> list=new ArrayList<String>();
        Random rand = new Random();
        String name;
        int size=0,remSize=n,randomIndex;
        for(;;){
            System.out.print("Enter \"A\" for Allocation Or \"S\" for Search Or \"E\" for Exit NonContiguous:");
            String c=in.nextLine();
            if(c.equalsIgnoreCase("A")){
                System.out.print("Enter Filename:");
                name=in.nextLine();
                System.out.print("Enter File-size:");
                size=in.nextInt();
                if(size<=remSize){
                    list.add(name);
                    for(int i=0;i<size;i++){
                        while(true){
                            randomIndex = rand.nextInt(n);
                            if(!disk.containsKey(randomIndex))
                                break;
                        }
                        disk.put(randomIndex, name);
                    }
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
                if(list.size()==0 || !disk.containsValue(name))
                    System.out.println("File not Found.");
                else{
                    System.out.print("File Found in the blocks :");
                    for(int i=0;i<n;i++){
                        if(disk.containsKey(i)){
                            if(disk.get(i).equals(name)){
                                System.out.print((i+1)+",");
                            }
                        }
                    } 
                    System.out.println("");
                }
            }
            else if(c.equalsIgnoreCase("E"))
                break;
        }
        
    }
}
