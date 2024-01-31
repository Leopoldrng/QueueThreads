# QueueThreads

### Aufgabe:

Implementiere eine einfache Warteschlange (Queue), die von mehreren Threads verwendet werden kann. Die Warteschlange soll die folgenden Methoden unterstützen:

- `enqueue(item)`: Fügt ein Element am Ende der Warteschlange hinzu.
- `dequeue()`: Entfernt und gibt das Element am Anfang der Warteschlange zurück.
- `isEmpty()`: Gibt `true` zurück, wenn die Warteschlange leer ist, sonst `false`.

Erstelle dann zwei Arten von Threads:

1. **`Producer`-Thread:**
    - Führt wiederholt `enqueue`-Operationen durch, indem er zufällige Elemente in die Warteschlange einfügt.

2. **`Consumer`-Thread:**
    - Führt wiederholt `dequeue`-Operationen durch und gibt die entfernten Elemente aus.

Stelle sicher, dass die Threads sicher auf die Warteschlange zugreifen und dass die Warteschlange Wettlaufbedingungen vermeidet. Verwende dazu Synchronisationsmechanismen wie `synchronized`-Blöcke, `wait()` und `notifyAll()`.

### Hinweise:

- Überlege, welche Daten in der Warteschlange gemeinsam genutzt werden müssen.
- Denke darüber nach, wie du die Methoden `enqueue`, `dequeue` und `isEmpty` so synchronisierst, dass mehrere Threads sicher darauf zugreifen können.

Viel Erfolg beim Implementieren! 


### Lösungsvorschlag

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
        while (isEmpty()) {
            try {
                System.out.println("Dequeue: Waiting for elements.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int item = queue.poll();
        System.out.println("Dequeue: " + item);
        return item;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

public class Main {
public static void main(String[] args) {
CustomQueue customQueue = new CustomQueue();

        // Erstelle zwei Producer-Threads
        for (int i = 0; i < 2; i++) {
            new Producer(customQueue).start();
        }

        // Erstelle drei Consumer-Threads
        for (int i = 0; i < 3; i++) {
            new Consumer(customQueue).start();
        }
    }
}

class Producer extends Thread {
private final CustomQueue customQueue;

    public Producer(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int item = (int) (Math.random() * 100);
            customQueue.enqueue(item);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
private final CustomQueue customQueue;

    public Consumer(CustomQueue customQueue) {
        this.customQueue = customQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            customQueue.dequeue();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



