import org.example.MyHashTable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyHashTableTest {

    @Test
    void GivenEmptyHashTable_WhenAdding_ThenReturnsListWithObjects() {
        MyHashTable<Integer> hashTable = new MyHashTable<>();
        hashTable.insert("Tomasz", 45);
    }
}
