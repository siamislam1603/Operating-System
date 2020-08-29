package ProducerConsumer;
import java.util.*;
public class ProducerConsumerClass { 
    LinkedList<Integer> list = new LinkedList<>(); 
    int capacity,value=0,head=1,tail=1; 
    Scanner in=new Scanner(System.in);
    ProducerConsumerClass(int capacity){
        this.capacity=capacity;
    }
    public void produceData() throws InterruptedException 
    {
        while (true) { 
            synchronized (this) 
            {  
                while (list.size() == capacity){ 
                    wait();
                }
                System.out.print("Producer "+head+": Data to insert in the buffer\nData: ");
                int x=in.nextInt();
                list.add(x);
                System.out.println("Producer "+head+": Data "+ x+" is inserted in the buffer"
                    +"\nEmpty Space:"+(capacity-list.size())+"\nFull Space:"+(head++)); 
                value++;
                notify(); 
                Thread.sleep(100); 
            } 
        } 
    } 
    public void consumeData() throws InterruptedException
    {
        while (true) {
            synchronized (this)
            {
                while (list.size() == 0){
                    System.out.println("\nNew Daataset created:");
                    System.out.print("Enter the size of the buffer:");
                    capacity=in.nextInt();
                    tail=1;
                    head=1;
                    value=0;
                    wait();
                }
                int val = list.removeFirst();
                System.out.println("Consumer "+(tail++)+": consumed data "+ val+" from the buffer"
                        +"\nFull space:"+(--value));
                notify();
                Thread.sleep(100);
            }
        }
    } 
} 