package sortarray;

import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *@author Greundzo
 */

public class SortArrayTests
{
	class IntegerComparator implements Comparator<Integer>
	{
    @Override
    public int compare(Integer i1, Integer i2)
		{
      return i1.compareTo(i2);
    } // compare
  } // inner class

  private Integer i1, i2, i3, i4, i5;
  private SortArray<Integer> SortArray;

  @Before
  public void createSortArray()
	{
    i1 = 1;
  	i2 = 2;
  	i3 = 3;
		i4 = 4;
		i5 = 5;
    SortArray = new SortArray<>(new IntegerComparator());
  }

	@Test
  public void testIsEmpty_zeroEl()
	{
  	assertTrue(SortArray.isEmpty());
  }

  @Test
	public void testIsEmpty_oneEl() throws Exception
	{
    SortArray.addElem(i1);
  	assertFalse(SortArray.isEmpty());
	}

	@Test
	public void testSize_zeroEl()
	{
    assertEquals(0,SortArray.size());
	}

	@Test
	public void testSize_oneEl() throws Exception
  {
    SortArray.addElem(i1);
    assertEquals(1,SortArray.size());
  }

  @Test
  public void testSize_twoEl() throws Exception
  {
		SortArray.addElem(i1);
    SortArray.addElem(i2);
  	assertEquals(2,SortArray.size());
	}

	@Test
	public void testInsertion_oneEl() throws Exception
	{
		SortArray.addElem(i1);
		SortArray.insertionSort(SortArray);
		Integer[] arrExpected = {i1};
		Integer[] arrActual = new Integer[1];
		arrActual[0]=SortArray.getElem(0);
		assertArrayEquals(arrActual,arrExpected);
	}

	@Test
	public void testInsertion_moreEl() throws Exception
	{
		SortArray.addElem(i5);
		SortArray.addElem(i3);
		SortArray.addElem(i1);
		SortArray.addElem(i4);
		SortArray.addElem(i2);
		SortArray.insertionSort(SortArray);
		Integer[] arrExpected = {i1, i2, i3, i4, i5};
		Integer[] arrActual = new Integer[5];
    for (int i=0; i<5; i++)
  		arrActual[i] = SortArray.getElem(i);
		assertArrayEquals(arrActual,arrExpected);
	}

	@Test
	public void testMerge_oneEL() throws Exception
	{
		SortArray.addElem(i1);
		SortArray.mergeSort(0,SortArray.size()-1);
		Integer[] arrExpected = {i1};
		Integer[] arrActual = new Integer[1];
		arrActual[0]=SortArray.getElem(0);
		assertArrayEquals(arrActual,arrExpected);
	}

	@Test
	public void testMerge_moreEl() throws Exception
	{
		SortArray.addElem(i5);
		SortArray.addElem(i3);
		SortArray.addElem(i1);
		SortArray.addElem(i4);
		SortArray.addElem(i2);
		SortArray.mergeSort(0,SortArray.size()-1);
		Integer[] arrExpected = {i1, i2, i3, i4, i5};
		Integer[] arrActual = new Integer[5];
    for (int i=0; i<5; i++)
  		arrActual[i] = SortArray.getElem(i);
		assertArrayEquals(arrActual,arrExpected);
	}
}
