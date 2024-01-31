import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue {
    private Queue<Integer> queue = new LinkedList<>();

    public synchronized void enqueue(int item) {
        queue.add(item);
        System.out.println("Enqueue: " + item);
        notifyAll();
    }

    public synchronized int dequeue() {
        while (queue.isEmpty()) {
            try {
                System.out.println("Waiting for elements...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int item = queue.poll();
        System.out.println("Dequeue: " + item);
        return item;
    }

    public static void main (String [] args){
        CustomQueue customQueue1 = new CustomQueue();

        for (int i = 0; i < 2; i++){
            new Dequeue(customQueue1).start();
        }
        for (int i = 0; i < 3; i++){
            new Enqueue(customQueue1).start();
        }
    }
}


