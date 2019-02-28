import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Thomas Prenderville
 * tmp2133
 * Fail2ban.java -- This program reads in a txt file of the user's choice
 * and checks whether an IP address has made 3 failed logon attempts.
 * The program will then write those IP addresses (if any) into a
 * txt file named "Ban.txt"
 */
public class Fail2Ban 
{
	public static void main(String[] args) 
	{
		// Ask user which file to read IP addresses from
		System.out.println("Please enter a file name:");
		Scanner in = new Scanner(System.in);
		File file = new File(in.nextLine());
		
		//Build Arrays needed
		ArrayList<String> failedIPs = new ArrayList();
		ArrayList<String> banList = new ArrayList();
		banList.add("Banned IPs inclue: ");

		// Reads in IP addresses of Invalid Attempts from file, saves to ArrayList
		Scanner fileIn;
		try 
		{
			Scanner fileIn = new Scanner(file);
			while (fileIn.hasNextLine()) 
			{
				String didFail = fileIn.nextLine();
				if (didFail.contains("Invalid")) 
				{
					failedIPs.add(didFail.substring(didFail.lastIndexOf(" ")));

				}						
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Sorry, that file was not found.");
		}
		
		// Compare IP addresses to each other
		for (int i = 0; i < failedIPs.size(); i++)
		{	
			int threeStrikes = 1;
			int j = i+1;
			while (j<failedIPs.size())
			{
				if (banList.get(banList.size()-1).equals(failedIPs.get(i)))
				{
					j++;
					continue;
				}
				else if (failedIPs.get(i).equals(failedIPs.get(j)) && threeStrikes < 3)
				{
					threeStrikes++;
					j++;
					if (threeStrikes == 3)
						banList.add(failedIPs.get(i));
					continue;
				}
				else
					j++;
					continue;
			}
			continue;
			
		}
			
		// Writes to a file named Ban.txt
		try
		{
			PrintWriter writer = new PrintWriter("Ban.txt");
			for (int j=1; j<banList.size();j++)
			{
				writer.println(banList.get(j));
				
			}
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Sorry, that file was not found");
		}
		System.out.println("Done");

	}
}
