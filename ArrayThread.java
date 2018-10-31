import java.util.Scanner;
 
class ArrayInput implements Runnable
{
    int[] a=new int[100];
    Scanner sc=new Scanner(System.in);
    int num;
     
    public int getLength()
    {
        System.out.println("Enter the number of elements you want in the array = ");
        int num=sc.nextInt();
 
        return num;
    }
 
    public void run()
    {   
        ArrayInput ai=new ArrayInput();
        int len=ai.getLength();
 
        System.out.println("Enter the elements in the array = ");
         
        for(int i=0;i<len;i++)
        {
            a[i]=sc.nextInt();
        }
         
        try
        {
            for(int i=0;i<len;i++)
            {
                System.out.print (" "+a[i]);
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread Interrupted");
        }
    }
}
 
class ArraySum implements Runnable
{
    ArrayInput ai=new ArrayInput();
    int len=ai.getLength();
 
    public void run()
    {
        try
        {
            int sum=0;
            for(int i=0;i<len;i++)
            {
                sum=sum+ai.a[i];
                Thread.sleep(2000);
            }
            System.out.println("Sum : "+sum);
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread Interrupted");
        }
    }
}
     
 
public class ArrayThread
{
    public static void main(String[] args)
    {
        ArrayInput ai=new ArrayInput();
        Thread t=new Thread(ai);
        t.start();
         
        ArraySum as=new ArraySum();
        Thread t1=new Thread(as);
        t1.start();
    }
}