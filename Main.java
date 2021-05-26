import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MainClass {

	public static void main(String[] args) {
		
			BeforeSign br=new BeforeSign();
	}

}


class BeforeSign{
	Scanner sc=new Scanner(System.in);
	BeforeSign(){
		System.out.println("\tWelcome to RJ Group of Companies");
		
		while(true) {
			System.out.println("Please select one of given names as you required to go further");
			System.out.println("\tEnter word like \n\t\t1.Registation\n\t\t2.Login (for update and deletion onl )\n\t\t3.exit(to come out)");
			String section=sc.next();
			if(section.equalsIgnoreCase("Registation")) {
				
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata","root","123456789");
					Statement stmt = con.createStatement();
				    String query = "select count(*) from employ";
				    ResultSet rs = stmt.executeQuery(query);
				    //Retrieving the result
				    rs.next();
				    int count = rs.getInt(1);
				 
				 if(count>=18) 
					 throw new TableSizeException("Don't have any vacancies ... Sorry");
				 else {
					 regis();
						continue;
				 }
					 
						}
						catch(Exception e) {
							System.out.println(e.getMessage());
						}
			}
			else if(section.equalsIgnoreCase("Login")) {
				login();
				continue;
			}
			else if(section.equalsIgnoreCase("exit")) {
				System.out.println("Thankyou for visiting");
				break;
			}
				
		}
	}
	public void regis() {
		while(true) {
		System.out.println("Welcome to Registation form \n \t please enter First Name ");
		String firstName=sc.next();
		System.out.println(" \t please enter Last Name ");
		String lastName=sc.next();
		System.out.println(" please enter Gender like male or female or others");
		String gender=sc.next();
		System.out.println(" \t please enter Department like Marketing  or Sales or Production or Management");
		String depart=sc.next();
		System.out.println(" \t please enter Email Id ");
		String email=sc.next();
		System.out.println(" \t please enter password ");
		String password=sc.next();
		System.out.println(" \t please enter Conform Password ");
		String conform=sc.next();
		System.out.println("\t Please enter mobile number");
		String mobile=sc.next();
		
		
		try {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata","root","123456789");
		
		PreparedStatement pre=con.prepareStatement("insert into employ values(?,?,?,?,?,?,?)");
		pre.setString(1,firstName);
		pre.setString(2, lastName);
		pre.setString(3, gender);
		pre.setString(4, mobile);
		pre.setString(5,email);
		pre.setString(6, password);
		pre.setString(7,depart);
		if(password.equalsIgnoreCase(conform)) {
			if(firstName.length()>=4) {
				if(lastName.length()>=4) {
					if(mobile.length()==10) {
			pre.executeUpdate();
			System.out.println("Registation Successfull");
			break;
			}
					else
						throw new MobileLengthException("Please enter valid mobile number");
					
				}
				else
					throw new LastLengthException("The length of last name should be greater than 4 letters");
				
			}
			else
				throw new FirstLengthException("The length of first name should be greater than 4 letters");
		}
		else
			throw new PasswordException("Password and Conform Password should be same");
		
		
		}
		catch(Exception e) {
			System.out.print("error:"+e.getMessage());
		}
		}
	}
	public void login()   {
		while(true) {
		System.out.println("Welcome to login \n \t only management department can use this feature ");
		System.out.println(" \t please enter email id ");
		String email=sc.next();
		System.out.println("\t Please enter password");
		String password=sc.next();
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata","root","123456789");

			PreparedStatement pre=con.prepareStatement("select email,password,depart from employ where email=? and password=? and depart='management'");
		pre.setString(1,email);
			pre.setString(2, password);
			 //pre.executeUpdate();
			 ResultSet rs = pre.executeQuery();
             if (rs.next()) {
                
                 System.out.println("You have successfully logged in");
                 System.out.println("Please enter \n\t1.delete for deleting any person in the table\n\t2.update any person's personal detains");
                 String sele=sc.next();
                 if(sele.equalsIgnoreCase("delete")) {
                	 
                	 dele();
                	 break;
                 }
                 else if(sele.equalsIgnoreCase("update")) {
                	 update();
                	 break;
                 }
             }
             else
            	 throw new LoginFailedException("Please enter valid user name and password");
                
		}
		catch(LoginFailedException e) {
			System.out.println(e);
	}
		catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	}
	
	public void dele() {
		System.out.println("Please enter any person's \n\temail id");
		String email=sc.next();
		System.out.println("\tmobile number");
		String mobile=sc.next();
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata","root","123456789");

			PreparedStatement pre=con.prepareStatement("delete from employ where email=? and mobile=?");
		pre.setString(1,email);
			pre.setString(2, mobile);
			 pre.executeUpdate();
			System.out.println("Deletion successfull");
                
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}
	}
	public void update() {
		System.out.println("Welcome to update form \n \t please enter email id it should be same ");
		String email=sc.next();
		System.out.println(" \t please enter First Name");
		
		String firstName=sc.next();
		System.out.println(" \t please enter Last Name ");
		String lastName=sc.next();
		//System.out.println(" please enter Gender like male or female or others");
		//String gender=sc.next();
		//System.out.println(" \t please enter Department like Marketing  or Sales or Production");
		//String depart=sc.next();
		
		System.out.println(" \t please enter password ");
		String password=sc.next();
		System.out.println(" \t please enter Conform Password ");
		String conform=sc.next();
		System.out.println("\t Please enter mobile number");
		String mobile=sc.next();
		 try {
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata","root","123456789");
				
				PreparedStatement pre=con.prepareStatement("update employ set firstName=?,lastName=?,mobile=?,password=? where email=?");
				pre.setString(1,firstName);
				pre.setString(2, lastName);
				//pre.setString(3, gender);
				pre.setString(3, mobile);
				pre.setString(5,email);
				pre.setString(4, password);
				//pre.setString(6,depart);
				if(password.equalsIgnoreCase(conform))
             {
                 
                 pre.executeUpdate();
                 System.out.println("Update Succeesfull");
                 
             }
				else {
					throw new ErrorException("update is not possible");
					
				}
             
		 }
		 catch(Exception e) {
			 System.out.println("error:"+e.getMessage());
		 }
	}
}
