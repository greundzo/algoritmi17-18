package distance;
import java.util.*;
import java.lang.*;
/**
 *@author: Greundzo
 *@author: ShyGuy
 *@author: OrangeThrower
 */
public class EditDistance
{
	/**
	 *@param x : first element to compare
	 *@param y : second element to compare
	 *@param z : third element to compare
	 *@return : method returns the minimum number between x,y and z
	 */
	private static int min(int x,int y,int z)
	{
		if (x<=y && x<=z) return x;
		if (y<=x && y<=z) return y;
		else return z;
	}
	/**
	 *@param s: string to be "cutted"
	 *@return : string without the first letter
	 */
  private static String rest(String s)
	{
		return s.substring(1);
	}
  /**
	 *@param str1,str2 :  the two strings that we have to compare
	 *@return : the minimum number of operations to make the second string str2 like the first string str1
	 */
	public static int editDist(String str1 , String str2)
	{
		int dist = 0;
		int dnoop = 0;
		int dcanc = 0;
		int dins = 0;
		int inf = Integer.MAX_VALUE;

    if(str1.length() == 0)
	  	return str2.length();
		if (str2.length() == 0)
	  	return str1.length();

		if(str1.charAt(0) == str2.charAt(0))
	    dnoop = editDist(rest(str1), rest(str2));
	  else
			dnoop = inf;

		dcanc = 1 + editDist(str1,rest(str2));
    dins = 1 + editDist(rest(str1), str2);

		return min(dnoop, dcanc, dins);
	}
  /**
	 *@method: the dynamic version of editDist
	 *@param str1,str2:  the two strings that we have to compare
	 *@return : the minimum number of operations to make the second string str2 like the first string str1
	 */
	public static int edit_distance_dyn(String str1, String str2) throws NullPointerException
	{
		if(str1 == null || str2 == null)
			throw new NullPointerException("Strings cannot be null");
		int[][] mat = new int[str1.length()+1][str2.length()+1];

		int m = str1.length() + 1;
    int n = str2.length() + 1;

		for(int i = 0; i<m; i++)
      mat[i][0] = i;
    for(int i = 0; i < n; i++)
        mat[0][i] = i;

		for(int i = 1; i < m; i++)
		{
      for(int j = 1; j < n; j++)
			{
        if (str1.charAt(i-1) == str2.charAt(j-1))
          mat[i][j] = mat[i-1][j-1];
        else
          mat[i][j] = Math.min(mat[i-1][j], mat[i][j-1]) + 1;
      }
    }
    return mat[m-1][n-1];
	}
}
