public class Enqueue extends Thread{
    private final CustomQueue customQueue;
    public Enqueue(CustomQueue customQueue){
        this.customQueue = customQueue;
    }
    @Override
    public void run(){
        for (int i = 0; i< 5; i++){
            int item = (int) (Math.random() * 100);
            customQueue.enqueue(item);

            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }


}
