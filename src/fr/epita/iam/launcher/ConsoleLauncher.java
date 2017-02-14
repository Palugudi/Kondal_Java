/**
 * 
 */
package fr.epita.iam.launcher;

import java.sql.SQLException;
import java.util.Scanner;

import fr.epita.iam.business.CreateActivity;
import fr.epita.iam.business.Delete;
import fr.epita.iam.business.Modify;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author Kondal
 *
 */
public class ConsoleLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the IAM software");
		Scanner scanner = new Scanner(System.in);
		
		//authentication
		if (!authenticate(scanner)){
			end(scanner);
			return;
		}
		
		//menu
		System.out.println("Please select an action :");
		System.out.println("a. create an Identity");
		System.out.println("b. modify an Identity");
		System.out.println("c. delete an Identity");
		System.out.println("d. show identities");
		System.out.println("e. quit");
		String choice = scanner.nextLine();
		IdentityJDBCDAO dao = new IdentityJDBCDAO();
		
		switch (choice) {
		
		case "a":
			//Create
			CreateActivity.execute(scanner);
			break;
		
		case "b":
			//Modify
			Modify.execute(scanner);
			
			break;
			
		case "c":
			//Delete
			Delete.execute(scanner);
			break;
		
		case "d":
			//Show identities
			try {
				System.out.println(dao.readAllIdentities());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "e":
			//Quit
			break;
			
		default:
			System.out.println("Your choice is not recognized");
			break;
		}

		
		end(scanner);
	}

	/**
	 * @param scanner
	 */
	private static void end(Scanner scanner) {
		System.out.println("Thanks for using this application, good bye!");
		scanner.close();
	}

	/**
	 * @param scanner
	 */
	private static boolean authenticate(Scanner scanner) {
		System.out.println("Please type your login : ");
		String login = scanner.nextLine();
		
		System.out.println("Please type your password : ");
		String password = scanner.nextLine();
		
		if (login.equals("Kondal") && password.equals("1234")){
			System.out.println("Athentication was successful");
			return true;
		}else{
			System.out.println("Athentication failed");
			return false;
		}
	}

}
