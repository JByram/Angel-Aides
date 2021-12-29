package DoctorsDAO;

import java.util.*;
import java.sql.*;
import java.io.*;

import Doctor


public class DoctorDAO {
	private Connection myConn;

	public DoctorDAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("Angel_Aides.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	public List<Doctor> getAllDoctors() throws Exception {
		List<Doctor> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Doctors");
			
			while (myRs.next()) {
				Doctor tempDoctor = convertRowToDoctor(myRs);
				list.add(tempDoctor);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Doctor> searchDoctors(String specialization) throws Exception {
		List<Doctor> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			specialization += "%";
			myStmt = myConn.prepareStatement("select * from Doctors where specialization like ?");
			
			myStmt.setString(1, specialization);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Doctor tempDoctor = convertRowToDoctor(myRs);
				list.add(tempDoctor);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Doctor convertRowToDoctor(ResultSet myRs) throws SQLException {
		
		int DocID = myRs.getInt("DocID");
		String last = myRs.getString("last");
		String first = myRs.getString("first");
		String email = myRs.getString("email");
		String specialization = myRs.getString("specialization");
		
		Doctors tempDoctor = new Doctor(id, last, first, email, specialization);
		
		return tempDoctor;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		DoctorDAO dao = new DoctorDAO();
		System.out.println(dao.searchDoctors("General"));

		System.out.println(dao.getAllDoctors());
	}
}

}
