package mytest;

public class thread {
    int temp = 0;
    Thread pre =Thread.currentThread();
    public static void main(String[] args){
        thread my = new thread();
        my.threadtest();
    }
    public void threadtest(){
        for(int i=0;i<3;i++){
            Thread t = new Thread(new deman(pre),"thread "+i+" ");
            t.start();
            pre = t;
        }
    }
    synchronized  void  prin(){
        System.out.print(Thread.currentThread().getName());
        System.out.println(temp);
        temp++;
    }
    class deman implements Runnable{
        Thread t=null;
        deman(Thread t){
            this.t= t;
        }
        @Override
        public void run() {
                prin();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


            }

    }
}
