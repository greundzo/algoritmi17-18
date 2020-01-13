package sortarray;
import java.util.ArrayList;
import java.util.Comparator;
/**
 *@author Alleyson
 *@author Castiglioni
 *@author Fessia
 *@param <T>: typeOf(SortArray.getElements())
 */
public class SortArray<T>
{
	private ArrayList<T> array;
	private Comparator<? super T> comparator;

	/**
	 *@param comparator: object comparator that implements the relationship between array's elements
	 */
	public SortArray(Comparator <? super T> comparator)
	{
		this.array = new ArrayList<>();
		this.comparator = comparator;
	}//constructor

	/**
   * @return: true if array's empty
   */
  public boolean isEmpty()
	{
		return(this.array).isEmpty();
	}// isEmpty

	/**
   *@return: array's length
   */
	public int size()
	{
		return (this.array).size();
	}//size

	/**
   *@param : element to be added into the array
   *@throw : element can't be null
   */
  public void addElem (T elem) throws SortArrayException
	{
    if (elem == null)
      throw new SortArrayException("addElem: element can't be NULL");
    (this.array).add(elem);
  }// addElem

	/**
   *@param position : searched element's position
   *@throw : position can't be higher or equal to the array's length
   *@return : element in the specified position
   */
  public T getElem(int position) throws SortArrayException
	{
    if(position < 0 || position >= (this.array.size()))
    	throw new SortArrayException("getElem: element's position doesn't exist");
    return (this.array).get(position);
  }//getElement

	/**
   *@param position : position where insert the element
   *@param element : element to insert in the specified position
   *@throw : element and position have to respect the rules
   */
  public void setElem(int position, T element) throws SortArrayException
	{
  	if(position < 0 || position >= (this.array.size()) || element == null)
    	throw new SortArrayException("setElem: position higher than array's lenght or null element");
  	(this.array).set(position,element);
	}//setElem


 /**
  *@param vector: array to sort
  *@throw: array can't be null
  */
	public void insertionSort(SortArray<T> vector) throws SortArrayException
	{
		if (vector.isEmpty())
			throw new SortArrayException("insertionSort: array's empty");
		if (vector.size() > 1)
		{
			T value;
			for (int i = 1; i < vector.size(); i++)
			{
				value = vector.getElem(i);
				int j = i;
				while( j > 0 && (this.comparator).compare(value, vector.getElem(j-1)) < 0)
				{
					vector.setElem(j, vector.getElem(j-1));
					j--;
				}//while
				vector.setElem(j, value);
			}//for
		}//if
	}//insertionSort

 /**
  *@param vector: ordered or disordered arraylist
  */
	public void mergeSort(SortArray<T> vector) throws SortArrayException
	{
		if(vector.isEmpty())
			throw new SortArrayException("ArrayList is empty.");
		if(vector.size() > 1)
			vector.mergeSort(0, vector.size()-1);
	}

/**
 *@param left: first element's index
 *@param right: last element's index
 *@throw array can't be null
 */
	public void mergeSort(int left, int right) throws SortArrayException
	{
		if(this.isEmpty())
			throw new SortArrayException("ArrayList is empty.");

		if(left < right && (right-left) >= 1)
		{
			int middle = (left+right)/2;
			mergeSort(left, middle);
			mergeSort(middle+1, right);
			merge(left, middle, right);
		}//if
	}//mergeSort

/**
 *@param left: first element's index
 *@param middle : middle element's index
 *@param right: last element's index
 */
	private void merge(int left, int middle, int right)
	{
		ArrayList<T> merge_vector = new ArrayList<>();
		int i = 0;
		int j = left;
		int index = middle+1;

		while(left <= middle && index <= right)
		{
			if( comparator.compare( (this.array).get(left), (this.array).get(index) ) <= 0)//
			{
				merge_vector.add((this.array).get(left));
				left++;
			}//if
			else
			{
				merge_vector.add((this.array).get(index));
				index++;
			}//else
		}//while

		while(left <= middle)
		{
			merge_vector.add((this.array).get(left));
			left++;
		}//while

		while(index <= right)
		{
			merge_vector.add((this.array).get(index));
			index++;
		}//while
		// Copy sort array(merge_vector) in the original array
		while(i < merge_vector.size())
		{
			(this.array).set(j++, merge_vector.get(i++));
		}//while
	}//merge
}//class
