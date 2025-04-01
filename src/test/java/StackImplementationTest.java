import org.example.MyStack;
import org.example.StackImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackImplementationTest {

    @Test
    void GivenAnEmptyArray_WhenTopping_ThenExceptionIsThrown() {
        MyStack<Integer> stack = new StackImplementation<>();

        Exception thrown = assertThrows(Exception.class, () -> stack.top());
        assertEquals("Stack is empty", thrown.getMessage());
    }

    @Test
    void GivenEmptyArray_WhenPushed_ThenSizeIsIncreased() {
        MyStack<Integer> stack = new StackImplementation<>();
        stack.push(5);
        assertEquals(1, stack.size());
    }

    @Test
    void GivenEmptyArray_WhenPushed_ThenElementIsReturnedWhenTopped() throws Exception {
        MyStack<Integer> stack = new StackImplementation<>();
        stack.push(5);
        assertEquals(5, stack.top());
    }

    @Test
    void GivenEmptyArray_WhenPushedAndPopped_ThenStackIsEmpty() throws Exception {
        MyStack<Integer> stack = new StackImplementation<>();
        stack.push(5);
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void GivenEmptyArray_WhenPushedAndTopped_ThenElementIsReturnedAndStackSizeRemainsOne() throws Exception {
        MyStack<Integer> stack = new StackImplementation<>();
        stack.push(5);
        int value = stack.top();
        assertEquals(5, value);
        assertEquals(1, stack.size());
    }

    @Test
    void GivenStackWithOneElement_WhenPopped_ThenElementIsReturned() throws Exception {
        MyStack<Integer> stack = new StackImplementation<>();
        stack.push(1);
        int value = stack.pop();
        assertEquals(1, value);
    }


}
