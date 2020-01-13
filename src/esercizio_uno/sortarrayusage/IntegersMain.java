package sortarrayusage;
import sortarray.*;
import java.lang.Object;
import java.util.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
*@author: Greundzo
*@author: OrangeThrower
*@author: ShyGuy
*/
public class IntegersMain
{
	private static final Charset ENCODING = StandardCharsets.UTF_8;
	private static final int seconds = 600;
	/**
	 *@param array: sorted array
	 */
	private static void printArray(SortArray<Record> array) throws SortArrayException
	{
		Record current_element = null;
    int method_array;
    System.out.println("Firsts 100 ordered array's elements");
    method_array = array.size();
    for(int i=0; i<100; i++)
		{
      current_element = array.getElem(i);
      System.out.println(current_element.getValue());
    }//for
	}//printArray

	/**
	 *@param filePath: file's path
	 *@param array: array.load(fileContent)
	 */
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

	/**Local call to package method
	 *@param filepath: file's path
	 *@param comparator: sorting choice comparator
	 */
	private static void insertionSort(String filepath, Comparator<Record> comparator) throws SortArrayException, IOException
	{
		SortArray<Record> insortArray = new SortArray<>(comparator);
		loadFile(filepath, insortArray);
		TimeCheck time = new TimeCheck(seconds);
		time.printStartTime();
		insortArray.insertionSort(insortArray);
		printArray(insortArray);
		time.printEndTime();
		System.exit(0);
  }//insertionSort

	/**Local call to package method
	 *@param filepath: file's path
	 *@param comparator: sorting choice comparator
	 */
  private static void mergeSort(String filepath, Comparator<Record> comparator) throws SortArrayException, IOException
	{
		SortArray<Record> mergeArray = new SortArray<>(comparator);
		loadFile(filepath, mergeArray);
		TimeCheck time = new TimeCheck(seconds);
		time.printStartTime();
		mergeArray.mergeSort(0, mergeArray.size()-1);
		printArray(mergeArray);
		time.printEndTime();
		System.exit(0);
   }//mergeSort

	/**
	 *@param args: file's name
	 *@throw : exception if file doesn't exists, file name's wrong or sorting choice's wrong
	 */
	public static void main(String[] args) throws SortArrayException, IOException
	{
		if(args.length < 1)
			throw new SortArrayException("Usage: IntegersMain <fileName>");

		Scanner scan = new Scanner(System.in);
		System.out.println("Select sorting method\n");
		System.out.println("1: insertionSort 		2: mergeSort");
		int num = scan.nextInt();
		int order;

		if (num != 1 && num !=2)
			throw new SortArrayException("Invalid choice");

		switch(num)
		{
			case 1://insertionSort
				System.out.println("Select sorting order.");
				System.out.print("1: Increase order		2: Decrease order\n");
				order = scan.nextInt();

				if(order != 1 && order != 2)
					throw new SortArrayException("Invalid choice.");
				if(order == 1)
					insertionSort(args[0], new IncreaseComparator());
				if(order == 2)
					insertionSort(args[0], new DecreaseComparator());
				break;
				//end case 1
			case 2://mergeSort
				System.out.println("Select sorting order.");
				System.out.print("1: Increase order		2: Decrease order\n");
				order = scan.nextInt();

				if(order != 1 && order != 2)
					throw new SortArrayException("Invalid choice.");
				if(order == 1)
					mergeSort(args[0], new IncreaseComparator());
				if(order == 2)
					mergeSort(args[0], new DecreaseComparator());
				break;
				//end case 2
			default:
		}//switch
	}//main
}//class
