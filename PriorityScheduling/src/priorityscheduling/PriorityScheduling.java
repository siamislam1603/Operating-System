package priorityscheduling;
import java.util.*;
public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter no of process:");
        int n=in.nextInt();
        Process p[]=new Process[n];
        for(int i=0;i<p.length;i++){
            System.out.println("For P"+(i+1)+":");
            System.out.print("Arrival time:");
            int at=in.nextInt();
            System.out.print("CPU time:");
            int ct=in.nextInt();
            System.out.print("Priority:");
            int pr=in.nextInt();
            p[i]=new Process(i+1,ct,at,pr);
        }
        Comparator<Process> com=new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                if(p1.at==p2.at && p1.pr<p2.pr){
                   return -1;
               }
               else if(p1.at<p2.at)
                   return -1;
               else
                   return 1;
            }
        };
        Arrays.sort(p,com);
        ganttChart(p);
    }
    public static void ganttChart(Process p[]){
        int q=2;
        Queue<Process> qu=new LinkedList<>();
        qu.add(p[0]);
        int time=p[0].at;
        int i=0,lP=99999,sP=-1;
        String s1=time+"\t",s2="|";
        while(!qu.isEmpty()){
            Process p1=qu.remove();
            lP=99999;
            sP=-1;
            if(p1.rt>q){
                p1.rt-=q;
                time+=q;
            }
            else{
                time+=p1.rt;
                p1.rt=0;
                p1.ft=time;
                i=i+1;
            }
            for(int j=0;j<p.length;j++){
                if(p[j].at<=time && lP>p[j].pr && p[j].rt!=0){
                    lP=p[j].pr;
                    sP=j;
                }
                else{
                    break;
                }
            }
            if(sP!=-1){
                qu.add(p[sP]);
                if(time>=p[p.length-1].at){
                    q=p[sP].rt;
                }   
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
            System.out.println(p[j].pName+": turnaround time:"+p[j].tat+" waiting time:"+p[j].wt);
        }
        System.out.println("\nAvg turnaround time:"+(totTat/p.length)+" Avg waiting time:"+(totWt/p.length));
    }
    
}
class Process{
    int ct,ft,at,pr,rt,tat,wt;
    String pName;
    Process(int i,int ct,int at,int pr){
        pName="P"+i;
        this.ct=ct;
        this.at=at;
        this.pr=pr;
        this.rt=this.ct;
    }
}
