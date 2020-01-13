package distance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *@author: ShyGuy
 *@author: Greundzo
 *@author: OrangeThrower
 */
public class EditDistanceUnitTests
{
	private String w1, w2, w3, w4;

	@Before
	public void createString()
	{
    w1 = "";
		w2 = "pass";
		w3 = "passa";
		w4 = "passa";
  }

	@Test
  public void testEditDistEmptyString() throws Exception
	{
    assertEquals(5, EditDistance.edit_distance_dyn(w1, w3));
  }

	@Test
  public void testEditDistEmptyOtherString() throws Exception
	{
  	assertEquals(5, EditDistance.edit_distance_dyn(w3, w1));
  }

	@Test
	public void testEditDistEqualStrings() throws Exception
	{
  	assertEquals(0, EditDistance.edit_distance_dyn(w3,w4));
	}

	@Test
	public void testEditDistDifferentStrings() throws Exception
	{
  	assertEquals(1, EditDistance.edit_distance_dyn(w3, w2));
	}

	@Test
	public void testEDIST()
	{
		String str1 = "sunday";
		String str2 = "saturday";

		assertEquals( EditDistance.editDist(str1 , str2),	EditDistance.edit_distance_dyn(str1, str2) );
	}
}
