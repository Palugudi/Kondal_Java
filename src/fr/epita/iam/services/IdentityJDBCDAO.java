/**
 * 
 */
package fr.epita.iam.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.DaoInitializationException;

/**
 * @author Kondal
 *
 */
public class IdentityJDBCDAO {

	private Connection currentConnection;

	/**
	 * 
	 */
	public IdentityJDBCDAO() throws DaoInitializationException {
			
		try {
			getConnection();
		} catch (SQLException e) {
			DaoInitializationException die = new DaoInitializationException();
			die.initCause(e);
			throw die;
		}
	}

	/**
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		try {
			this.currentConnection.getSchema();
		} catch (Exception e) {
			// TODO read those information from a file
			String user = "TOM";
			String password = "TOM";
			String connectionString = "jdbc:derby://localhost:1527//Epita;create=true";
			this.currentConnection = DriverManager.getConnection(connectionString, user, password);
		}
		return this.currentConnection;
	}

	
	private void releaseResources() {
		try {
			this.currentConnection.close();
		} catch (Exception e) {
			//TODO trace Exception
		}
	}

	/**
	 * Read all the identities from the database
	 * @return
	 * @throws SQLException
	 */
	public List<Identity> readAllIdentities() throws SQLException {
		List<Identity> identities = new ArrayList<Identity>();

		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int uid = rs.getInt("IDENTITY_ID");
			String displayName = rs.getString("IDENTITY_DISPLAYNAME");
			String email = rs.getString("IDENTITY_EMAIL");
			String birthdate = rs.getString("IDENTITY_BIRTHDATE");
			Identity identity = new Identity(String.valueOf(uid), displayName, email, birthdate);
			identities.add(identity);
		}

		return identities;
	}

	/**
	 * write an identity in the database
	 * @param identity
	 * @throws SQLException
	 */
	public void write(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE) VALUES(?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		// TODO implement date for identity
		pstmt.setString(3, identity.getBirthDate());

		pstmt.execute();
	}
	/**
	 * Update an identity in the database
	 */

	
	public void update(Identity identity, String uid) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "UPDATE IDENTITIES SET IDENTITY_DISPLAYNAME= ? , IDENTITY_EMAIL=?, IDENTITY_BIRTHDATE=? WHERE IDENTITY_ID=?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getDisplayName());
		pstmt.setString(2, identity.getEmail());
		pstmt.setString(3, identity.getBirthDate());
		pstmt.setString(4, uid);

		pstmt.execute();
	}
	
	/**
	 * Delete an identity in the database
	 */

	public void delete(Identity identity, String uid) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "DELETE FROM IDENTITIES WHERE IDENTITY_ID=?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1,uid);

		pstmt.execute();
	}

	
	
}
