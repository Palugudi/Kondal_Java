/**
 * 
 */
package fr.epita.iam.business;

import java.sql.SQLException;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author Kondal
 *
 */
public class CreateActivity {
	
	
	public static void execute(Scanner scanner){
		System.out.println("Identity Creation");
		System.out.println("please input the displayName");
		String displayName = scanner.nextLine();
		System.out.println("please input the email address");
		String email = scanner.nextLine();
		System.out.println("please input the birthdate");
		String birthdate = scanner.nextLine();
		Identity identity = new Identity("",displayName, email, birthdate);
		
	
		//persist the identity somewhere
		System.out.println("this is the identity you created");
		IdentityJDBCDAO identityWriter = new IdentityJDBCDAO();
		try {
			identityWriter.write(identity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		
		System.out.println("creation Done");
		
	}
	

}
