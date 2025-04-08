import org.example.MyQueue;

public class MyQueueTest {
    public static void main(String[] args) throws Exception {
        MyQueue<Integer> queue = new MyQueue<>();

//        System.out.println(queue.dequeue()); // should throw an exception;
        queue.enqueue(5);
        System.out.println("testing size after adding 1 element - should be 1: " + queue.size());
        System.out.println("testing peek should return 5: " + queue.peek());
        queue.dequeue();
        System.out.println("testing size after deque - should be 0: " + queue.size());
        queue.enqueue(5);
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(7);
        queue.enqueue(7);
        System.out.println("testing size after exceeding 10 elements: " + queue.size());
    }
}
