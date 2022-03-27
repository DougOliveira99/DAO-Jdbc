package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		Scanner input = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		while (true) {
			try {
				
				System.out.println();
				System.out.println("1 - Find seller by id");
				System.out.println("2 - Find seller by department");
				System.out.println("3 - Find all sellers");
				System.out.println("4 - Insert new seller");
				System.out.println("5 - Update seller by id");
				System.out.println("6 - Delete seller by id");
				System.out.println("7 - Exit");
				System.out.print("Enter your option: ");
				
				int user = input.nextInt();
				
				if(user == 1) {
					System.out.print("Enter a seller id: ");
					Seller seller = sellerDao.findById(input.nextInt());
					if (seller != null) {
						System.out.println(seller);
					}
					else {
						System.out.println("Error! Id not found!");
					}
				}
				
				else if(user == 2) {
					System.out.print("Enter a department id: ");
					Department department = new Department(input.nextInt(), null);
					List<Seller> list = sellerDao.findByDepartment(department);
					for (Seller obj : list) {
						System.out.println(obj);
					}
				}
				
				else if(user == 3) {
					List<Seller>list = sellerDao.findAll();
					
					for (Seller obj : list) {
						System.out.println(obj);
					}
					
				}
				
				else if(user == 4) {
					System.out.print("Name: ");
					input.nextLine();
					String name = input.nextLine();
					System.out.print("Email: ");
					String email = input.nextLine();
					System.out.print("Birthdate (DD/MM/YYYY): ");
					Date birthdate = sdf.parse(input.nextLine());
					System.out.print("Base salary: $");
					double baseSalary = input.nextDouble();
					System.out.print("Department Id: ");
					Department department = new Department(input.nextInt(), null);
					
					Seller seller = new Seller(null, name, email, birthdate, baseSalary, department);
					sellerDao.insert(seller);
					System.out.println("Seller inserted! new id = " + seller.getId());
				}
				
				else if (user == 5) {
					System.out.print("Enter a seller id: ");
					Seller seller = sellerDao.findById(input.nextInt());
					if (seller != null) {
						System.out.println("1 - Name");
						System.out.println("2 - Email");
						System.out.println("3 - Birthdate");
						System.out.println("4 - Base salary");
						System.out.println("5 - Department");
						System.out.print("Select a field to update: ");
						
						user = input.nextInt();
						
						if(user == 1) {
							input.nextLine();
							System.out.print("New name: ");
							seller.setName(input.nextLine());
						}
						else if(user == 2) {
							input.nextLine();
							System.out.print("New email: ");
							seller.setEmail(input.nextLine());
						}
						else if(user == 3) {
							System.out.print("New birthdate (DD/MM/YYYY): ");
							seller.setBirthdate(sdf.parse(input.nextLine()));
						}
						else if(user == 4) {
							System.out.print("New base salary: $");
							seller.setBaseSalary(input.nextDouble());
						}
						else if(user == 5) {
							System.out.print("New department id: ");
							seller.setDepartment(new Department(input.nextInt(), null));
						}
						else {
							System.out.println("Error! Invalid option!");
						}
						sellerDao.update(seller);
						System.out.println("Seller Updated!");
					}
					else {
						System.out.println("Error! Id not found!");
					}
				}
				
				else if(user == 6) {
					System.out.print("Enter a seller id: ");
					sellerDao.deleteById(input.nextInt());
					System.out.println("Delete Complete!");
				}
				else if(user == 7) {
					break;
				}
				else {
					System.out.println("Error! Invalid option!");
				}
			}
			
			catch (InputMismatchException e) {
				System.out.println("Invalid input type. Try again.");
				input.nextLine();
			}
			
			catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}
			
		input.close();
	}
}
