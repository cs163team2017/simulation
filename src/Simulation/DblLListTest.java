package Simulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class DblLListTest {

    @Test
    public void QueueInterfaceSetsFirstWhenenQCalled() {
        CisQueue<String> q = new DblLList<String>();
        q.enQ("a");
        assert(q.peek().equals("a"));
    }
    
    @Test 
    public void QueueReturnsFirstValueWhenDeQueued() {
        CisQueue<String> q = new DblLList<String>();
        q.enQ("abc");
        String a = q.deQ();

        assert(a.equals("abc"));
    }
    
    @Test
    public void AQueueHandlesMultipleEnqueuesAndDequeues() {
        CisQueue<Integer> q = new DblLList<Integer>();
        q.enQ(1);
        q.enQ(2);
        q.enQ(3);
        assert(q.peek() == 1);
        assert(q.deQ() == 1);
        assert(q.peek() == 2);
        assert(q.deQ() == 2);
        assert(q.peek() == 3);
        q.enQ(5);
        assert(q.deQ() == 3);
        assert(q.peek() == 5);
    }

}
