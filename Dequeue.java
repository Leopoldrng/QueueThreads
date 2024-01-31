public class Dequeue extends Thread{
    private final CustomQueue customQueue;

    public Dequeue(CustomQueue customQueue) {
        this.customQueue = customQueue;

    }

    @Override
    public void run(){
        for (int i = 0; i< 5; i++){
            customQueue.dequeue();
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
