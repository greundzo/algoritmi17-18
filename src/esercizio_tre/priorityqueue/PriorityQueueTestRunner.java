package priorityqueue;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
*@author: Greundzo
*/
public class PriorityQueueTestRunner
{
  public static void main(String [] args)
  {
    Result result = JUnitCore.runClasses(PriorityQueueUnitTests.class);
    for (Failure failure : result.getFailures())
      System.out.println(failure.toString());
    System.out.println(result.wasSuccessful());
  }// main
}// class
