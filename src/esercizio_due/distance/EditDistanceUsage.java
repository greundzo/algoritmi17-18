package distance;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.lang.*;

/**
 *@author: Greundzo
 *@author: OrangeThrower
 *@author: ShyGuy
 */
public class EditDistanceUsage
{

  /**
   *@param filepath : filepath of the file
   *@param array : array in which the words have to be stored
   */
  private static void loadFile(String filepath, ArrayList<String> array) throws IOException, FileNotFoundException
  {
    System.out.println("Loading file to correct..");
    System.out.println();
    System.out.println();
    try(Scanner fileInputReader = new Scanner(new File(filepath)))
    {
      String line = null;
	    while (fileInputReader.hasNext())
      {
        line = fileInputReader.next();
        line = line.replaceAll("\\p{Punct}", "");
        array.add(line.toLowerCase());
      }//while
    }//try
    catch(IOException e)
    {
      System.out.println("Error while loading correctme.");
    }
  }//loadFile

  /**
   *@param filepath : filepath of the file
   *@param array : array in which the words have to be stored
   */
  private static void loadDictionary(String filepath, ArrayList<String> array) throws IOException, FileNotFoundException
  {
    System.out.println("Loading dictionary file..");
    try(BufferedReader fileInputReader = new BufferedReader(new FileReader(filepath)))
    {
      String line = null;
	    while((line = fileInputReader.readLine()) != null)
        array.add(line);
    }
    catch(IOException e)
    {
      System.out.println("Error while loading dictionary.");
    }
  }//loadDictionary

  /**
   *@param array : array to be printed
   */
  private static void printText(ArrayList<String> array) throws IOException
  {
    System.out.println("Printing text..");
    for(int i=0; i <array.size(); i++)
      System.out.println(array.get(i));
    System.out.println("\n-----------------------------");
    System.out.println();
    System.out.println();
  }//printText

 /**
  *@method corrector : define the minimum edit_distance of each word from correctme compared to each word from dictionary
  *@param dictionary : fist array of string to compare
  *@param correctme : second array of string to be compare
  */
  public static void corrector(ArrayList<String> dictionary, ArrayList<String> correctme)
  {
    for(String w: correctme)
    {
      ArrayList<String> minWords = new ArrayList<String>();
      int min = Integer.MAX_VALUE;

      for(String d: dictionary)
      {
        int dist = EditDistance.edit_distance_dyn(d, w);
        if(dist < min)
        {
          min = dist;
          minWords.clear();
          minWords.add(d);
        }//if
        else
          if(dist == min)
            minWords.add(d);
      }//for

      if(min !=0)
      {
        System.out.println("Error in word: " + w);
        System.out.println("Possible corrections: ");
        for(String corr: minWords)
          System.out.println(corr);
        System.out.println();
        System.out.println("\n-----------------------------");
        System.out.println();
      }//if
    }//for
  }//corrector

  public static void main (String[]args) throws IOException, FileNotFoundException, EditDistanceException
  {
    if(args.length < 1)
      throw new EditDistanceException("Usage: EditDistanceUsage <dictionary_path> <correctme_path>");

	   ArrayList<String> dictionary = new ArrayList<String>();
	   ArrayList<String> correctme = new ArrayList<String>();
	   loadDictionary(args[0], dictionary);
	   loadFile(args[1], correctme);
	   printText(correctme);
     corrector(dictionary,correctme);
	}//main

}//class
