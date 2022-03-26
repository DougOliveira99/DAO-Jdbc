package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		while (true) {
			try {				
				System.out.println();
				System.out.println("1 - Find Department by id");
				System.out.println("2 - Find all Departments");
				System.out.println("3 - Insert new Department");
				System.out.println("4 - Update Department");
				System.out.println("5 - Delete Department by id");
				System.out.println("6 - Exit");
				System.out.print("Enter your option: ");
				
				int user = input.nextInt();
				
				if(user == 1) {
					System.out.print("Enter a department id: ");
					Department department = departmentDao.findById(input.nextInt());
					if (department != null) {
						System.out.println(department);
					}
					else {
						System.out.println("Error! Id not found!");
					}
				}
				else if(user == 2) {
					List<Department> list = departmentDao.findAll();
					
					for(Department dep : list) {
						System.out.println(dep);
					}
				}
				
				
				
				
				
				
				else if(user == 6) {
					break;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Error! Invalid input format! Try again.");
				input.nextLine();
			}
		}
		
		
		
		
		
		
		
		input.close();
	}

}
