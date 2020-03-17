package roundrobinscheduling;

import java.util.*;

public class RoundRobinScheduling {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter no of Process:");
        int n=in.nextInt();
        Process p[]=new Process[n];
        System.out.print("Enter time quantum:");
        int q=in.nextInt();
        for(int i=0;i<n;i++){
            System.out.print("For Process "+(i+1)+":\nEnter arrival time:");
            int aTime=in.nextInt();
            System.out.print("Enter cpu time:");
            int cTime=in.nextInt();
            p[i]=new Process(i+1,aTime,cTime);
            
        }
        Comparator<Process> com=new Comparator<Process>(){
            @Override
            public int compare(Process p1, Process p2) {
                if(p1.at>p2.at)
                    return 1;
                else
                    return -1;
            }
        };
        Arrays.sort(p,com);
        gantChart(p,q);
        
    }
    public static void gantChart(Process p[],int q){
        Queue<Process> qu = new LinkedList<>();
        int i=0;
        qu.add(p[i]);
        int time=p[i].at;
        String s1=time+"\t",s2="|";
        while(!qu.isEmpty()){
            Process p1=qu.remove();
            if(p1.st==-1){
                p1.st=time-p1.at;
            }
            if(p1.rt>q){
                p1.rt-=q;
                time+=q;
            }
            else{
                time+=p1.rt;
                p1.rt=0;
                p1.ft=time;
            }
            while(i+1<p.length){
                if(p[i+1].at<=time){
                    qu.add(p[i+1]);
                    i++;
                }
                else{
                    break;
                }
            }
            if(p1.rt!=0){
                qu.add(p1);
            }
            s1+=time+"\t";
            s2+="--"+p1.pName+"---|";
        }
        System.out.println("\nGant chart:\n\n"+s1+"\n"+s2+"\n");
        double totTat=0,totWt=0;
        for(int j=0;j<p.length;j++){
            p[j].tat=p[j].ft-p[j].at;
            p[j].wt=p[j].tat-p[j].ct;
            totTat+=p[j].tat;
            totWt+=p[j].wt;
            System.out.println(p[j].pName+": turnaround time:"+p[j].tat+" waiting time:"+p[j].wt+" response time:"+p[j].st);
        }
        System.out.println("\nAvg turnaround time:"+(totTat/p.length)+" Avg waiting time:"+(totWt/p.length));
    }
}
class Process{
    String pName;
    int at;
    int ct;
    int st=-1,ft,rt,tat,wt;
    Process(int i,int aTime,int cTime){
        pName="P"+i;
        at=aTime;
        ct=cTime;
        rt=ct;
    }
}
