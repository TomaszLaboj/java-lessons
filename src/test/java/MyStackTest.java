import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.MyStack;
import org.junit.jupiter.api.Test;

public class MyStackTest {
    private MyStack myStack = new MyStack();

    @Test
    void pushing() {
        myStack.push(5);
        assertEquals(5, myStack.top());
    }

    @Test
    void popping() {
        myStack.push(5);
        int value = myStack.pop();
        assertEquals(5, value);
    }
}
