package sortarrayusage;
import sortarray.*;
import java.lang.Object;
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;

/**
 *@author: Greundzo
 *@author: OrangeThrower
 *@author: ShyGuy
 */

public class SumMain
{
  private static final Charset ENCODING = StandardCharsets.UTF_8;
  /**
   *@param : boolean's array to print
   */
  private static void printArray(ArrayList<Boolean> array)throws SortArrayException, IOException
  {
    System.out.println("Printing array..");
    int size = array.size();
    for(int i = 0; i < size; i++)
      System.out.println(array.get(i) + "\n");
  }//printArray

  private static void loadFile(String filePath, SortArray<Record> array) throws SortArrayException, IOException
  {
   System.out.println("Loading file..");
   Path inputFile = Paths.get(filePath);
   try
   {
     BufferedReader reader = Files.newBufferedReader(inputFile, ENCODING);
     String line = null;
     while( (line = reader.readLine()) != null)
     {
       Record fileElement = new Record(Long.parseLong(line));
       array.addElem(fileElement);
     }//while
    }//try
    catch(IOException e)
    {
      throw new IOException("File doesn't exists or wrong file path.");
    }//catch IO
    catch(SortArrayException e)
    {
      throw new SortArrayException("Null or empty array.");
    }//catch Sort
   }//loadFile

   /**
    *@param sum : array that contains the sums to search in searchArray
    *@param searchArray : array that contains the elements to sum
    */
  public static void searchInt(SortArray<Record> sum, SortArray<Record> searchArray) throws SortArrayException, IOException
  {
    int i = 0;
    boolean found = false;
    int left = 0;
    int right = searchArray.size()-1;
    ArrayList<Boolean> foundNumbers = new ArrayList<>();
    if(searchArray.size() > 2)
      searchArray.mergeSort(left, right);
    while(i < sum.size())
      {
        long sumElem = sum.getElem(i).getValue();
        found = false;
        while(left < right && found == false)
        {
          long leftElem = searchArray.getElem(left).getValue();
          long rightElem = searchArray.getElem(right).getValue();
          if(leftElem + rightElem == sumElem)
          {
            foundNumbers.add(true);
            found = true;
      		}
          else
          {
            if(leftElem + rightElem < sumElem)
              left++;
            else
              right--;
          }
        }//while
        if(left >= right)
          foundNumbers.add(false);
        left = 0;
        right = searchArray.size()-1;
        i++;
      }//while
    printArray(foundNumbers);
  }//searchInt

  public static void main(String[] args) throws SortArrayException, IOException
  {
    if(args.length < 1)
		throw new SortArrayException("Usage: SumMain <fileName> <fileName>");
    SortArray<Record> searchArray = new SortArray<>(new IncreaseComparator());
    loadFile(args[0], searchArray);
    SortArray<Record> sumArray = new SortArray<>(new IncreaseComparator());
    loadFile(args[1], sumArray);
    searchInt(sumArray, searchArray);
  }//main
}//class
