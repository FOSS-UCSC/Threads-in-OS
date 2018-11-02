import java.util.*;
import java.util.LinkedList;

class SW
{
    private LinkedList<Integer> list=new LinkedList<Integer>();
    private final int LIMIT =10;
    private Object lock=new Object();
    public void Produce() throws InterruptedException
    {
        int value=0;
        while(true){
            synchronized(lock)
            {
                while(list.size()==LIMIT){
                    lock.wait();

                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void Consume() throws InterruptedException
    {
        Random random=new Random();

        while(true){
            synchronized(lock)
            {
                while(list.size()==0){
                    lock.wait();

                }
                System.out.println("List size : "+list.size());
                int item=list.removeFirst();
                
                System.out.println("Value is : "+item);
                System.out.println("List size : "+list.size());
                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }
}


class SleepWakeup
{
    public static void main(String args[]){
        final SW sw=new SW();
        Thread t1=new Thread(new Runnable(){
            public void run(){
                try{
                    sw.Consume();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(new Runnable(){
            public void run(){
                try{
                    sw.Produce();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}