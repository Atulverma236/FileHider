package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDao;
import model.User;
import service.GenerateOTP;
import service.SendOTPS;
import service.UserService;

public class Welcome {

	public void welcomeScreen() {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the App...");
		System.out.println("Press 1 to Login");
		System.out.println("Press 2 to SignUp");
		System.out.println("Press 0 to exit");
		int choice=0;
		
		try {
			choice=Integer.parseInt(br.readLine());
		} catch (IOException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			
		}
		switch(choice) {
		case 1 : login();
		break;
		case 2 : signUp();
		break;
		case 0 : System.exit(0);
		
		
		}
	}
	 
	   private void login() {
		   Scanner sc=new Scanner(System.in);
		   System.out.println("Enter Email:");
		   String email=sc.nextLine();
		   try {
			if(UserDao.isExists(email)) {
				String genOTP=GenerateOTP.getOTP();
				SendOTPS.sendOTP(email, genOTP);
				System.out.println("Enter the OTP");
				String otp=sc.nextLine();
				if(otp.equals(genOTP)) {
					new UserView(email).home();
					
				}else {
					System.out.println("Wrong OTP");
				}
			}else {
				System.out.println("User not found");
			}
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		   
	   }

		private void signUp() {
         Scanner sc=new Scanner(System.in);
         System.out.println("Enter Name:");
         String name=sc.nextLine();
         System.out.println("Enter Email:");
         String email=sc.nextLine();
         String genOTP=GenerateOTP.getOTP();
			SendOTPS.sendOTP(email, genOTP);
			System.out.println("Enter the OTP");
			String otp=sc.nextLine();
			if(otp.equals(genOTP)) {
			User user=new User(name,email);
			int response =UserService.saveUser(user);
			switch (response) {
			case 0: 
				System.out.println("User registered Successfully");
				
				break;

			case 1: 
				System.out.println("User already exists");
				break;
			}
			
			}else {
				System.out.println("Wrong OTP");
			}
         
         
		}
}
