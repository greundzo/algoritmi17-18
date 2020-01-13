package sortarrayusage;
import java.util.Comparator;
/**
 *@author: Greundzo
 */
public class IncreaseComparator implements Comparator<Record>
{
  @Override
   /**
    *@param elem1 : first element to compare
    *@param elem2 : second element to compare
    *@return result : result = 1 if elem1<elem2
    */
  public int compare(Record elem1, Record elem2)
  {
    int result = (new Long(elem1.getValue())).compareTo(elem2.getValue());
    return result;
  }

}
