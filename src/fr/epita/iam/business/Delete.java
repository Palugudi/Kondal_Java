package fr.epita.iam.business;

import java.sql.SQLException;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author Kondal
 *
 */


public class Delete {
	public static void execute(Scanner scanner){
		System.out.println("Identity Deletion");
		System.out.println("please input the UID of the identity to Delete");
	 String uid = scanner.nextLine();
	 Identity identity = new Identity(null,null,null,uid);
		
		IdentityJDBCDAO dao = new IdentityJDBCDAO();
		try {
			dao.delete(identity,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Deletion done!!");
	}
}
