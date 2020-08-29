package pagereplacement;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
        System.out.print("Number of pages:");
        int np=in.nextInt();
        System.out.print("Number of page references:");
        int nr=in.nextInt();
        BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Reference String:");
        String str=ob.readLine();
        String ar[]=str.split(",");
        System.out.print("Number of memory page frame:");
        int nf=in.nextInt();
        fifo(np,ar,nf,nr);
        optimal(np,ar,nf,nr);
        lru(np,ar,nf,nr);
    }
    public static void fifo(int np,String rs[],int nf,int nr){
        Queue<String> q = new LinkedList<>();
        List<String> frame = new ArrayList<>();
        int pageFault = 0;
        double pfr;
        for(int i=0;i<rs.length;i++){
            String s=rs[i];
            if(!frame.contains(s)){
                if(frame.size()==nf){
                   String head= q.poll();
                   int index = frame.indexOf(head);
                   frame.remove(index);
                   frame.add(index,s);
                   q.add(s);
                }
                else {
                    frame.add(s);
                    q.add(s);
                }
                pageFault++;
            }
        }
        System.out.println("Number of page fault using FIFO Page replacement Algorithm:"+pageFault);
        pfr=((double)pageFault/nr)*100;
        System.out.println("Page fault rate:"+(int)Math.round(pfr)+"%");
    }
    public static void optimal(int np,String rs[],int nf,int nr){
        List<String> frame = new ArrayList<>();
        int pageFault = 0;
        double pfr;
        for(int i=0;i<rs.length;i++){
            if(!frame.contains(rs[i])){
                if(frame.size()==nf){
                    Distance dis[]=new Distance[nf];
                    for(int j=0;j<nf;j++){
                        dis[j]=new Distance(frame.get(j),-1);
                        for(int k=i+1;k<rs.length;k++){
                            if(frame.get(j).trim().equals(rs[k].trim())){
                                dis[j].distance=k-i;
                                break;
                            }
                        }
                    }
                    Comparator<Distance> com=new Comparator<Distance>() {
                        @Override
                        public int compare(Distance d1, Distance d2) {
                            if(d1.distance>d2.distance)
                                return 1;
                            return -1;
                        }
                    };
                    Arrays.sort(dis,com);
                    if(dis[0].distance==-1){
                        frame.set(frame.indexOf(dis[0].pageName), rs[i]);
                    }
                    else{
                        frame.set(frame.indexOf(dis[nf-1].pageName),rs[i]);
                    }
                }
                else{
                    frame.add(rs[i]);
                }
                pageFault++;
            }
        }
        System.out.println("Number of page fault using Optimal Page replacement Algorithm:"+pageFault);
        pfr=((double)pageFault/nr)*100;
        System.out.println("Page fault rate:"+(int)Math.round(pfr)+"%");
    }
    public static void lru(int np,String rs[],int nf,int nr){
        List<String> frame = new ArrayList<>();
        int pageFault = 0;
        double pfr;
        for(int i=0;i<rs.length;i++){
            if(!frame.contains(rs[i])){
                if(frame.size()==nf){
                    Distance dis[]=new Distance[nf];
                    for(int j=0;j<nf;j++){
                        dis[j]=new Distance(frame.get(j),-1);
                        for(int k=i-1;k>=0;k--){
                            if(frame.get(j).trim().equals(rs[k].trim())){
                                dis[j].distance=i-k;
                                break;
                            }
                        }
                    }
                    Comparator<Distance> com=new Comparator<Distance>() {
                        @Override
                        public int compare(Distance d1, Distance d2) {
                            if(d1.distance>d2.distance)
                                return 1;
                            return -1;
                        }
                    };
                    Arrays.sort(dis,com);
                    if(dis[0].distance==-1){
                        frame.set(frame.indexOf(dis[0].pageName), rs[i]);
                    }
                    else{
                        frame.set(frame.indexOf(dis[nf-1].pageName),rs[i]);
                    }
                }
                else{
                    frame.add(rs[i]);
                }
                pageFault++;
            }
        }
        System.out.println("Number of page fault using Least Recently Used Page replacement Algorithm:"+pageFault);
        pfr=((double)pageFault/nr)*100;
        System.out.println("Page fault rate:"+(int)Math.round(pfr)+"%");
    }
    
}
class Distance{
    String pageName;
    int distance;
    Distance(String pageName,int distance){
        this.pageName=pageName;
        this.distance=distance;
    }
}
