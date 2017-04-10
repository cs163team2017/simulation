package Simulation;

import java.util.ArrayList;

import org.junit.Test;

public class DblLListTest {

    @Test
    public void QueueInterfaceSetsFirstWhenenQCalled() {
        CisQueue<String> q = new DblLList<String>();
        q.enQ("a");
        assert(q.peek().equals("a"));
        DblLList<String> l = (DblLList<String>)q;
        assert(1 == l.size());
        assert(l.first() == l.last());
    }
    
    @Test 
    public void QueueReturnsFirstValueWhenDeQueued() {
        CisQueue<String> q = new DblLList<String>();
        q.enQ("abc");
        String a = q.deQ();
        assert(a.equals("abc"));
        DblLList<String> l = (DblLList<String>)q;
        assert(0 == l.size());
        assert(l.first() == null);
        assert(l.first() == l.last());
        String v = q.deQ();
        assert(v == null);      
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
        assert(null == list.first());
        assert(null == list.last());
        assert(0 == list.size());
        list.push(6);
        assert(1 == list.size());
        System.out.println(list.first());
        assert(6 == list.first());
        assert(1 == list.size());
        list.push(7);
        assert(6 == list.first());
        assert(7 == list.last());
        assert(2 == list.size());
        v = list.pop();
        assert(v == 7);
        assert(1 == list.size());
        v = list.pop();
        assert(v == 6);
        assert(0 == list.size());
        assert(null == list.first());
        assert(null == list.last());
    }
    
    @Test
    public void DblLListAddFirstWorksGivenEmptyList() {
        DblLList<String> list = new DblLList<String>();
        list.addFirst("a");
        assert("a" == list.last());
    }
    
    @Test
    public void DblLListRemoveLastWorksGivenShortList() {
        DblLList<String> list = new DblLList<String>();
        list.addFirst("a");
        assert("a" == list.removeLast());
    }
    
    @Test
    public void DblLListGetAtIndexAfterHalfWorksGivenLongList() {
        DblLList<String> list = new DblLList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        assert("e" == list.getAtIndex(4));
    }

    @Test
    public void DblLListSetAtIndexDoesNotThrowGivenValidInput() {
        DblLList<String> list = new DblLList<String>();
        list.setAtIndex("a", 0);
        assert("a" == list.last());
    }
    
    @Test
    public void DblLListInsertAtIndexDoesNotThrowGivenValidInput() {
        DblLList<String> list = new DblLList<String>();
        list.insertAtIndex("a", 0);
        assert("a" == list.first());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListGetNodeAtIndexThowsGivenInvalidInput1() {
        DblLList<String> list = new DblLList<String>();
        list.getAtIndex(0);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListGetNodeAtIndexThowsGivenInvalidInput2() {
        DblLList<String> list = new DblLList<String>();
        list.add("a");
        list.getAtIndex(1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListGetNodeAtIndexThowsGivenInvalidInput3() {
        DblLList<String> list = new DblLList<String>();
        list.add("a");
        list.getAtIndex(-1);
    }
    
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListSetAtIndexThrowsGivenInvalidInput1() {
        DblLList<String> list = new DblLList<String>();
        list.setAtIndex("a", -1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListSetAtIndexThrowsGivenInvalidInput2() {
        DblLList<String> list = new DblLList<String>();
        list.add("a");
        list.setAtIndex("a", 1);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListInsertAtIndexThrowsGivenInvalidInput1() {
        DblLList<String> list = new DblLList<String>();
        list.insertAtIndex("a", -1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListInsertAtIndexThrowsGivenInvalidInput2() {
        DblLList<String> list = new DblLList<String>();
        list.add("a");
        list.insertAtIndex("a", 1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListRemoveAtIndexThrowsGivenInvalidInput1() {
        DblLList<String> list = new DblLList<String>();
        list.add("a");
        list.removeAtIndex(1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListRemoveAtIndexThrowsGivenInvalidInput2() {
        DblLList<String> list = new DblLList<String>();
        list.add("a");
        list.removeAtIndex(-1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void DblLListRemoveAtIndexThrowsGivenInvalidInput3() {
        DblLList<String> list = new DblLList<String>();
        list.removeAtIndex(0);
    }
    
    @Test
    public void ForEachLoopingWorksGivenValidInput() {
        DblLList<Integer> list = new DblLList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (ListNode<Integer> n : list) {
            output.add(n.getValue());
        }
        assert(4 == output.size());
        assert(2 == output.get(1));
        assert(4 == output.get(3));
    }
}
