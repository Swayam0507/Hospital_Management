package Hospital_Management1;

import java.sql.*;
import java.util.Scanner;
import java.io.*;

import static Hospital_Management1.Patient_Manager.*;

public class Hospital {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "root";
		String password = "Swayam@9728#";
		Scanner sc=new Scanner(System.in);
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Successfully");
			Patient_Manager patientManager=new Patient_Manager(connection,sc);
			//load all database data into arraylist
			String query="select * from patient";
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()){
				int id= resultSet.getInt("patient_id");
				String name= resultSet.getString("full_name");
				String disease= resultSet.getString("disease");
				int age=resultSet.getInt("age");
				patientArrayList.add(new Patient(id,name,disease,age));
			}
			//Write All Patient Data Into Patient.txt

			boolean b=true;
			while (b){
				System.out.println("Press 1 For Add Patient");
				System.out.println("Press 2 For View Patient");
				System.out.println("Press 3 For Update Patient Details");
				System.out.println("Press 4 For Delete Patient");
				System.out.println("Press 5 For Search Patient by Name");
				System.out.println("Press 6 For Sort Patient By Age");
				System.out.println("Press 7 For Get Patient data on File");
				int choice= sc.nextInt();
				switch (choice){
					case 1:
						patientManager.addPatient();
						break;
					case 2:
						patientManager.viewPatient1();
						break;
					case 4:
						patientManager.deletePatient();
						break;
					case 5:
						sc.nextLine();
						System.out.println("Enter Name");
						String name=sc.nextLine();
						Patient result = getPatientByName(name);
						if (result != null) {
							System.out.println("Found: " + result.name + " - " + result.disease + " - " + result.age);
						} else {
							System.out.println("Patient not found.");
						}
						break;
					case 6:
						patientManager.sortPatient();
						break;
					case 7:
						try {
							File file=new File("Patient_File.txt");
							BufferedWriter writer = new BufferedWriter(new FileWriter(file));

							writer.write("============== PATIENT TABLE ==============\n");
							writer.write("| ID   | Name           | Disease        | Age  |\n");
							writer.write("---------------------------------------------\n");

							for (Patient p : patientArrayList) {
								writer.write(p.toTableRow() + "\n");
							}

							writer.write("---------------------------------------------\n");

							writer.close();
							System.out.println("Data successfully written to Patient_File.txt");
						} catch (IOException e) {
							System.out.println("An error occurred while writing to the file.");
							e.printStackTrace();
						}
						break;
					default:
						System.out.println("Please Enter Valid Input");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
}

