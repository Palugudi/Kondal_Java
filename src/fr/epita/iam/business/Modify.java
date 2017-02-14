package fr.epita.iam.business;

import java.sql.SQLException;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.IdentityJDBCDAO;

/**
 * @author Kondal
 *
 */


public class Modify {

	public static void execute(Scanner scanner){
		System.out.println("Identity Modification");
		System.out.println("please input the UID of the identity to modify");
		String uid = scanner.nextLine();
		System.out.println("please input the new displayname");
		String displayname = scanner.nextLine();
		System.out.println("please input the new email address");
		String email = scanner.nextLine();
		System.out.println("please input the new dirth date");
		String bdate = scanner.nextLine();
		Identity identity = new Identity("",displayname, email, bdate);
		
		IdentityJDBCDAO dao = new IdentityJDBCDAO();
		try {
			dao.update(identity, uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Modification done!!");
	}
}
