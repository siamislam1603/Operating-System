package ProducerConsumer;
import java.util.Scanner;
public class MainClass {
    public static void main(String[] args) throws InterruptedException 
    { 
        Scanner in=new Scanner(System.in);
        System.out.print("Enter the size of the buffer:");
        int n=in.nextInt();
        ProducerConsumerClass pc = new ProducerConsumerClass(n); 
        Thread t1 = new Thread(new Runnable() { 
            @Override
            public void run() 
            { 
                try { 
                    pc.produceData(); 
                } 
                catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
        Thread t2 = new Thread(new Runnable() { 
            @Override
            public void run() 
            { 
                try { 
                    pc.consumeData(); 
                } 
                catch (InterruptedException e) { 
                    e.printStackTrace(); 
                } 
            } 
        });  
        t1.start(); 
        t2.start(); 
        t1.join();
        t2.join();
    } 
} 