package priorityqueue;

import java.util.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import java.util.ArrayList;
import org.junit.Test;

/**
*@author: ShyGuy
*@author: Greundzo
*/

public class PriorityQueueUnitTests
{
  private PriorityQueue<Integer,String> queue;
  private PriorityQueue<Integer,String> secondQueue;
  private Entry<Integer, String> e1, e2, e3, e4, e5, e6;

  @Before
  public void createArray() throws IllegalAccessException
  {
    e1 = new Entry<>(1, "a");
    e2 = new Entry<>(2, "b");
    e3 = new Entry<>(3, "c");
    e4 = new Entry<>(4, "d");
    e5 = new Entry<>(5, "e");
    e6 = new Entry<>(6,"f");
    queue = new PriorityQueue<>("max");
    secondQueue = new PriorityQueue<>("min");
  }

  @Test
  public void insertOneEl()
  {
    queue.insert(1, "a");
    assertEquals(e1.getKey(),queue.getKey(0));
  }

  @Test
  public void insertMoreEl()
  {
    queue.insert(4, "d");
    queue.insert(2, "b");
    queue.insert(6, "f");
    queue.insert(3, "c");
    queue.insert(5, "e");
    queue.insert(1, "a");

    int f = queue.getKey(queue.size()-1);
    assertEquals(1, f);
    assertEquals(e4.getKey(), queue.getKey(2));
    assertEquals("c", queue.getElem(4));
  }

  @Test
  public void peekOneEl()
  {
    queue.insert(4, "d");
    assertEquals(e4.getKey(), queue.peek());
  }

  @Test
  public void peekMoreEl()
  {
    queue.insert(e6.getKey(),e6.getElem());
    queue.insert(e5.getKey(),e5.getElem());
    queue.insert(2,"b");
    queue.insert(3,"c");

    int i = queue.peek();
    assertEquals(6, i);
  }

  @Test
  public void extractFirstOneEl()
  {
    queue.insert(1,"a");
    assertEquals(e1.getElem(),queue.extractFirst());
	}

  @Test
	public void extractFirstMoreEl()
  {
    queue.insert(e6.getKey(), e6.getElem());
    queue.insert(e3.getKey(), e3.getElem());
    queue.insert(e5.getKey(), e5.getElem());
    queue.insert(e4.getKey(), e4.getElem());
    queue.insert(e1.getKey(), e1.getElem());

    secondQueue.insert(e6.getKey(), e6.getElem());
    secondQueue.insert(e3.getKey(), e3.getElem());
    secondQueue.insert(e5.getKey(), e5.getElem());
    secondQueue.insert(e4.getKey(), e4.getElem());
    secondQueue.insert(e1.getKey(), e1.getElem());

		assertEquals(e6.getElem(), queue.extractFirst());
    assertEquals(e1.getElem(), secondQueue.extractFirst());
	}

  @Test
  public void containsOneEl()
  {
    queue.insert(3,"c");
    assertTrue(queue.contains(e3.getElem()));
  }

  @Test
  public void containsMoreEl()
  {
    queue.insert(e2.getKey(), e2.getElem());
    queue.insert(e3.getKey(), e3.getElem());
    queue.insert(e5.getKey(), e5.getElem());
    queue.insert(e4.getKey(), e4.getElem());
    queue.insert(e1.getKey(), e1.getElem());

    assertTrue(queue.contains("b"));
  }

  @Test
  public void removeOneEl()
  {
    queue.insert(1,"a");
    queue.remove(1);
    assertTrue(queue.empty());
  }

  @Test
  public void remove_moreEl()
  {
    queue.insert(e6.getKey(), e6.getElem());
    queue.insert(e3.getKey(), e3.getElem());
    queue.insert(e5.getKey(), e5.getElem());
    queue.insert(e4.getKey(), e4.getElem());
    queue.insert(e1.getKey(), e1.getElem());

    secondQueue.insert(e6.getKey(), e6.getElem());
    secondQueue.insert(e3.getKey(), e3.getElem());
    secondQueue.insert(e5.getKey(), e5.getElem());
    secondQueue.insert(e4.getKey(), e4.getElem());
    secondQueue.insert(e1.getKey(), e1.getElem());

    queue.remove(1);
    secondQueue.remove(5);

    assertFalse(queue.contains("a"));
    assertFalse(secondQueue.contains("e"));
  }

  @Test
  public void testModify()
  {
    queue.insert(1, "a");
    queue.modifyKey(4, "a");

    secondQueue.insert(e3.getKey(),e3.getElem());
    secondQueue.modifyKey(7, e3.getElem());

    assertTrue(queue.getKey(0)==4);
    assertTrue(secondQueue.getKey(0)==7);

  }
}
