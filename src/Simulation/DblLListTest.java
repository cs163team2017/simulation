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
    
    @Test
    public void DblLListBasicFuncsWorkGivenValidCalls() {
        DblLList<Integer> list = new DblLList<Integer>();
        list.add(1); //[1]
        assert(1 == list.first());
        assert(1 == list.last());
        list.addFirst(2); // [2,1]
        assert(2 == list.first());
        assert(1 == list.last());
        list.add(3); // [2,1,3]
        assert(3 == list.last());
        list.insertAtIndex(4, 1); // [2,4,1,3]
        assert(4 == list.getAtIndex(1));
        assert(1 == list.getAtIndex(2));
        list.setAtIndex(5, 2); // [2,4,5,3]
        assert(5 == list.getAtIndex(2));
        assert(4 == list.size());
        int v = list.removeAtIndex(1); // [2,5,3]
        assert(4 == v);
        assert(5 == list.getAtIndex(1));
        assert(3 == list.size());
        v = list.removeFirst(); // [5,3]
        assert(2 == v);
        assert(5 == list.first());
        assert(2 == list.size());
        v = list.removeLast(); // [5]
        assert(v == 3);
        assert(1 == list.size());
        assert(5 == list.last());
        assert(5 == list.first());
        v = list.removeAtIndex(0);
        assert(5 == v);
        assert(0 == list.size());
    }
    
    @Test
    public void DblLListThowsExceptionsGivenInvalidInput() {
        DblLList<String> list = new DblLList<String>();
        // TODO 2: Finish this test
        // all throw index out of bounds:
        // getNodeAtIndex(), 
        // setAtIndex(), 
        // insertAtIndex(), 
        // removeNodeAtIndex(), 
        // getNodeAtIndex(), 
    }

}
