package diskarmscheduling;

import java.io.*;
import java.util.*;

public class DiskArmScheduling{
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
        System.out.print("Number of heads:");
        int nc=in.nextInt();
        System.out.print("Cylinder requests:");
        BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
        String s=ob.readLine();
        String ar[]=s.split(",");
        List<String> cyl = new ArrayList(Arrays.asList(ar));
        System.out.print("Current head position:");
        int h=in.nextInt();
        cscan(nc,cyl,h);
        sstf(nc,cyl,h);
    }
    public static void cscan(int nc,List<String> cyl,int head){
        List<Integer> right=new ArrayList<Integer>();
        List<Integer> left=new ArrayList<Integer>();
        left.add(0);
        right.add(nc-1);
        for(int i=0;i<cyl.size();i++){
            int x=Integer.parseInt((cyl.get(i)).trim());
            if(x<head){
                left.add(x);
            }
            else
                right.add(x);
        }
        Collections.sort(left);
        Collections.sort(right);
        System.out.print("Cylinder serving orders for CSCAN:"+head);
        int tm=0;
        for(int i=0;i<right.size();i++){
            tm+=Math.abs(head-right.get(i));
            System.out.print("->"+right.get(i));
            head=right.get(i);
        }
        for(int i=0;i<left.size();i++){
            tm+=Math.abs(head-left.get(i));
            System.out.print("->"+left.get(i));
            head=left.get(i);
        }
        System.out.println("\nTotal Cylinder Movements:"+tm);
    }
    public static void sstf(int nc,List<String> cyl,int head){
        System.out.print("Cylinder serving orders for SSTF:"+head);
        int tm=0;
        while(!cyl.isEmpty()){
            int min=Integer.MAX_VALUE,index=0;
            for(String c:cyl){
                int dif=Math.abs(Integer.parseInt(c.trim())-head);
                if(dif<min){
                    min=dif;
                    index=cyl.indexOf(c);
                }
            }
            tm+=min;
            head=Integer.parseInt((cyl.remove(index)).trim());
            System.out.print("->"+head);
        }
        System.out.println("\nTotal Cylinder Movements:"+tm);
    }
}
