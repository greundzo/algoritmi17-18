package priorityqueue;
import java.util.*;

/**
 *@author: Greundzo
 *@author: ShyGuy
 */
public class PriorityQueue<T extends Comparable<? super T>,E>
{
  private ArrayList<Entry<T,E>> heap;
  private String mod;

/**
 *@param mod: String to determine order (MaxHeap or MinHeap)
 */
  public PriorityQueue(String mod) throws IllegalAccessException
  {
    this.heap = new ArrayList<Entry<T,E>>();
    if(mod!="max" && mod!="min")
      throw new IllegalAccessException("Constructor: param must be 'max' or 'min'");
    this.mod = mod;
  }//constructor

/**
 *@return: key at position i
 */
  public T getKey(int i)
  {
    return this.heap.get(i).getKey();
  }//get

/**
 *@return: value at position i
 */
  public E getElem(int i)
  {
    return this.heap.get(i).getElem();
  }//getElem

/**
 *@param i: heap position to set
 *@param key: node key
 *@param elem: node element
 */
  public void set(int i, T key,E elem)
  {
    Entry<T,E> aux = new Entry<>(key,elem);
    this.heap.set(i, aux);
  }

/**
 *@return: PriorityQueue size
 */
  public int size()
  {
    return this.heap.size();
  }//size

/**
 *@return: true if queue is empty, false if not
 */
  public boolean empty()
  {
    return this.heap.isEmpty();
  }//empty

/**
 *@param key: key to search
 *@return: index of node that contains the elem; -1 if not contains
 */
  private int searchKey(T key)
  {
    for(int i = 0; i < heap.size(); i++)
    {
      if(heap.get(i).getKey() == key)
        return i;
    }
    return -1;
  }

/**
 *@param elem: element to search
 *@return: index of node that contains the element; -1 if not contains
 */
  public int searchElem(E elem)
  {
    for(int i = 0; i < heap.size(); i++)
    {
      if(this.getElem(i) == elem)
        return i;
    }//for
    return -1;
  }//searchElem

/**
 *@return: true if queue contains the elem, false if not
 */
  public boolean contains(E elem)
  {
    if(searchElem(elem)!=-1)
      return true;
    else
      return false;
  }//contains

/**
 *@return: father position
 */
  private int parent(int i)
  {
    return ((i-1)/2);
  }//parent

/**
 *@return: left child position
 */
  public int left(int i)
  {
    return 2*i + 1;
  }//left

/**
 *@return: right child position
 */
  public int right(int i)
  {
    return 2*i + 2;
  }//right

/**
 *@param i: first node position
 *@param j: second node position
 */
  private void swap(int i, int j)
  {
    Entry<T,E> aux = new Entry<>(heap.get(i).getKey(), heap.get(i).getElem());
    this.heap.set(i, heap.get(j));
    this.heap.set(j, aux);
  }//swap

/**
 *@return: heap root key
 */
  public T peek()
  {
    if(this.empty())
      return null;
    return heap.get(0).getKey();
  }//peek

/**
 *@return: heap root and removes it
 */
  public E extractFirst()
  {
    Entry<T,E> root;
		if (this.empty())
			return null;
    if(this.size()==1)
    {
      root = heap.get(0);
      this.heap.remove(0);
    }
    else
    {
      root = heap.get(0);
		  heap.set(0, heap.get(heap.size()-1));  // Move last to position 0
      heap.remove(heap.size()-1);
      heapify(heap,0);
    }
    return root.getElem();
	}//extractFirst

/**
 *@param key: key to insert
 *@param elem: elem to insert
 */
  public void insert(T key, E elem)
  {
    if(!this.contains(elem))
    {
      Entry<T,E> aux = new Entry<>(key,elem);
      heap.add(aux);
      int i = heap.size()-1;

      if(mod=="max")
      {
        while(i >= 0 && heap.get(parent(i)).getKey().compareTo(heap.get(i).getKey()) < 0)
        {
          swap(i,parent(i));
          i = parent(i);
        }//while
      }//if
      else
        if(mod=="min")
        {
          while(i >= 0 && heap.get(parent(i)).getKey().compareTo(heap.get(i).getKey()) > 0)
          {
            swap(i,parent(i));
            i = parent(i);
          }//while
        }//else
    }//if
  }//insert

/**
 *@param key: key of node to remove
 */
  public void remove(T key)
  {
    if(searchKey(key)!=-1)
    {
      heap.remove(searchKey(key));
      if(this.size()>1)
      {
        int i = heap.size()-1;
        heapify(heap,i);
      }//if
    }//if
  }//remove

/**
 *@param newKey: key to modify
 *@param elem: element to search the correspondant containing node
 */
  public void modifyKey(T newKey, E elem)
  {
    if(this.contains(elem))
    {
      heap.remove(searchElem(elem));
      this.insert(newKey, elem);
      int i = heap.size()-1;

      if(mod=="max")
      {
        while(i >= 0 && heap.get(parent(i)).getKey().compareTo(heap.get(i).getKey()) < 0)
        {
          swap(i,parent(i));
          i = parent(i);
        }//while
      }//if
      else
        if(mod=="min")
        {
          while(i >= 0 && heap.get(parent(i)).getKey().compareTo(heap.get(i).getKey()) > 0)
          {
            swap(i,parent(i));
            i = parent(i);
          }//while
        }//if else
    }//if
  }//modifyKey

/**
 *@param heap: binary heap to order
 *@param i: start position to order the heap
 */
  private void heapify(ArrayList<Entry<T,E>> heap, int i)
  {
    int l = left(i);
    int r = right(i);
    int max = 0;
    int min = 0;

    if(mod=="max")
    {
      if(l <= heap.size()-1 && heap.get(l).getKey().compareTo(heap.get(i).getKey()) > 0)
        max = l;
      else
        max = i;

      //if r > i && r > l, r is maximum. Else is the previous max value
      if(r <= heap.size()-1 && heap.get(r).getKey().compareTo(heap.get(max).getKey()) > 0)
        max = r;

      if(max!=i)
      {
        swap(i,max);
        heapify(heap,max);
      }//if
    }//if
    else
      if(mod=="min")
      {
        if(l <= heap.size()-1 && heap.get(l).getKey().compareTo(heap.get(i).getKey()) < 0)
          min = l;
        else
          min = i;

        if(r <= heap.size()-1 && heap.get(r).getKey().compareTo(heap.get(min).getKey()) < 0)
          min = r;

        if(min!=i)
        {
          swap(i, min);
          heapify(heap,min);
        }//if
    }//else
  }//heapify

/**
 *Sorts heap elements and place them in order
 */
  public void heapSort()
  {
    for(int i = (heap.size()/2)-1;i >= 0;i--)
      heapify(heap,i);
    for(int j = heap.size()-1; j >= 0; j--)
    {
      swap(0, j);
      heapify(heap,0);
    }//for
  }//heapSort
}//PriorityQueue
