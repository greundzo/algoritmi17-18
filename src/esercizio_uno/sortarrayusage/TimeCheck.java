package sortarrayusage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
/**
 *@author: Greundzo
 *@author: OrangeThrower
 *@author: ShyGuy
 */
public class TimeCheck
{
	private Timer timer;
	private String elapsed = null;
  /**
	 *@param seconds : seconds that have to pass to start the execution
	 */
	public TimeCheck(int seconds)
	{
		this.timer = new Timer();
		this.timer.schedule(new RemindTask(), seconds * 1000);
	}
  /**
	 *@method : print the schedule in which the esecution starts
	 */
	public void printStartTime()
	{
		elapsed = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\nSorting started at: " + elapsed);
	}
  /**
	 *@method : print the schedule in which the esecution ends
 	 */
	public void printEndTime()
	{
		elapsed = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\nSorting ended successfully at: " + elapsed);
	}

	class RemindTask extends TimerTask
	{
		public void run()
		{
			String elapsed = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
			System.out.format("Sorting terminated. Time expired at: " + elapsed);
			System.exit(0);
			timer.cancel();//Terminate timer
		}
	}
}
