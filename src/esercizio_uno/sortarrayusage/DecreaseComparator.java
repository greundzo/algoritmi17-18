package sortarrayusage;
import java.util.Comparator;
/**
 *@author: Greundzo
 *@author: ShyGuy
 */

public class DecreaseComparator implements Comparator<Record>
{
  @Override
   /**
    *@param elem1 : first element to compare
    *@param elem2 : second element to compare
    *@return result : result=1 if elem2 < elem1
    */
  public int compare(Record elem1, Record elem2)
  {
    int result = (new Long(elem2.getValue())).compareTo(elem1.getValue());
    return result;
  }

}
