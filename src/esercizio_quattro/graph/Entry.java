package graph;
import java.util.*;

/**
 *@author: Greundzo
 *@author: ShyGuy
 */
public class Entry<T,E> extends Object
{
  private T key;
  private E elem;
/**
 *@param key: Object key
 *@param elem: Object element
 */
  public Entry(T key, E elem)
  {
    this.key = key;
    this.elem = elem;
  }//constructor

/**
 *@return: Object key
 */
  public T getKey()
  {
    return this.key;
  }//getKey

/**
 *@return: Object elem
 */
  public E getElem()
  {
    return this.elem;
  }//getElem

/**
 *@param k: key to set
 */
  public void setKey(T k)
  {
    this.key = k;
  }//setKey

/**
 *@param e: elem to set
 */
  public void setElem(E e)
  {
    this.elem = e;
  }//setValue

/**
 *@param k: Object key
 *@param e: Object elem
 */
  public void setEntry(T k, E e)
  {
    this.key = k;
    this.elem = e;
  }//setEntry
}//Entry
