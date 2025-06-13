package com.nisum;


class RunnableDemo implements Runnable{
    private Thread threadInstance;
    private String nameOfThread;

    RunnableDemo(String name){
        nameOfThread = name;
        System.out.println("Creating " + nameOfThread);
    }

    public void run(){
        System.out.println("Executing thread " + nameOfThread);
        try{
            for(int counter=4; counter>0; counter--){
                System.out.println("Thread: "+ nameOfThread + " , " + counter);
                Thread.sleep(5);
            }
        }catch(InterruptedException exception){
            System.out.println("Thread" + nameOfThread + " execution was interrupted.");
        }
        System.out.println("Thread " + nameOfThread + " terminating");

    }
    public void start(){
        System.out.println("Commencing thread " + nameOfThread);
        if(threadInstance == null){
            threadInstance = new Thread(this, nameOfThread);
            threadInstance.start();
        }
    }
}
public class TestThread {
    public static void main(String[] args) {
        RunnableDemo runnable = new RunnableDemo("Thread-1");
        runnable.start();
        RunnableDemo runnable2 = new RunnableDemo("Thread-2");
        runnable2.start();
    }
}

