
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Adv {
    public int a_id;
    public String a_name;
    public String specilization;

    public Adv(int a_id, String a_name, String specilization) {
        this.a_id = a_id;
        this.a_name = a_name;
        this.specilization = specilization;
    }
}


class Client {
    public int client_id;
    public String client_name;
    public String client_details;

    public Client(int client_id, String client_name, String client_details) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.client_details = client_details;
    }
}


class Appointment {

	public int ap_id;
	public int advocate_id;
	public int client_id;
	public LocalDate date;
	public Appointment(int ap_id, int advocate_id, int client_id, LocalDate date) {
		
		this.ap_id = ap_id;
		this.advocate_id = advocate_id;
		this.client_id = client_id;
		this.date = date;
	}
	
}
public class Advocate {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaproject", "root", "Root@123");

            while (true) { // Main Menu
            	System.out.println("                                     Online Advocate System         ");
            	System.out.println("                                           Main Menu        ");
                System.out.println("                                        Enter  1. Advocate ");
                System.out.println("                                        Enter  2. Customer ");
                System.out.println("                                        Enter  3. Appointment ");
                System.out.println("                                        Enter  4. Exit");
                System.out.println("                                        Enter your choice");
                System.out.println("_________________________________________________________________________________________________");
                int mainChoice = sc.nextInt();
                sc.nextLine();

                
    

                switch (mainChoice) {
                    case 1:
                        // Advocate options
                    		System.out.println("                                           Advcocate Menu          ");
                    		System.out.println("                                          Select an option ");
	                    	System.out.println("                               		1. Register Advocate ");
	                        System.out.println("                                        2. View Advocate records ");
	                        System.out.println("                                        3. Modify Advocate details  ");
	                        System.out.println("                                        4. Delete Advocate record");
	                        System.out.println("                                        4. Exit");
	                        
	                        System.out.println("_________________________________________________________________________________________________");
                    		
                            
                            int Advocatechoice = sc.nextInt();
                            sc.nextLine();

                            switch (Advocatechoice) {
                                case 1:
                                    System.out.println("                                        Enter Advocate ID");
                                    int a_id = Integer.parseInt(sc.nextLine());
                                    
                                    System.out.println("                                        Enter Advocate Name");
                                    String a_name = sc.nextLine();
                                    
                                    System.out.println("                                        Enter Specilization");
                                    String specilization = sc.nextLine();

                                    Adv obj = new Adv(a_id, a_name, specilization);

                                    String insertQuery = "insert into advocate (a_id, a_name, specilization) values (?, ?, ?)";
                                    try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                                        preparedStatement.setInt(1, obj.a_id);
                                        preparedStatement.setString(2, obj.a_name);
                                        preparedStatement.setString(3, obj.specilization);

                                        int res = preparedStatement.executeUpdate();
                                        if (res == 0) {
                                            System.out.println("                                        Record not inserted");
                                        } else {
                                            System.out.println("                                        Record inserted successfully");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                                case 2:
                                    // View Advocate records
                                    try {
                                        String selectQuery = "SELECT * FROM advocate";
                                        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                                            try (ResultSet rs = preparedStatement.executeQuery()) {
                                                System.out.println("                                  Advocate Records:");
                                                System.out.println("----------------------------------------------------------------------------");
                                                while (rs.next()) {
                                                	System.out.println("                                        Advocate_id" + "|" + "Advocate_name" + "|" + "Specilization");
                                                    System.out.println("                                        "+rs.getInt("a_id") + "|" + rs.getString("a_name") + "|" + rs.getString("specilization"));
                                                }
                                            }
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                	// Modify advocate Details
                                	System.out.println("                                  Enter advocate ID to modify");
                                     a_id = Integer.parseInt(sc.nextLine());
                                    
                                    System.out.println("                                  Enter advocate name to modify");
                                     a_name = sc.nextLine();
                                    
                                   
                                    System.out.println("                                  Enter advocate specilization to modify");
                                     specilization = sc.nextLine();
                                
                                    String UpdateQuery = "UPDATE clients SET a_name = ?, specilization = ? where a_id =?";
                                    try (PreparedStatement preparedstatement = con.prepareStatement(UpdateQuery)){
                                    	preparedstatement.setString(2,specilization);
                                    	preparedstatement.setString(1,a_name);
                                    	preparedstatement.setInt(3,a_id);
                                    	
                                    	int res = preparedstatement.executeUpdate();
                                    	if(res==0)
                                    	{
                                    		 System.out.println("                                  Record not Found/Modified");
                                        } else {
                                            System.out.println("                                  Record modified successfully");
                                    	}
                                    	
                                    }catch(SQLException e)
                                    {
                                    	e.printStackTrace();

                                    }
                                    break;
                                case 4:
                                	// Delete Advocate Details
                                	System.out.println(                                        "Enter advocate ID to modify");
                                     a_id = Integer.parseInt(sc.nextLine());
                                   
                                	
                                    String deleteQuery = "DELETE advocate SET where a_id =?";
                                    try (PreparedStatement preparedstatement = con.prepareStatement(deleteQuery)){
                                    	
                                    	preparedstatement.setInt(1,a_id);
                                    	
                                    	int res = preparedstatement.executeUpdate();
                                    	if(res==0)
                                    	{
                                    		 System.out.println("                                  Record not Deleted");
                                        } else {
                                            System.out.println("                                  Record Deleted successfully");
                                    	}
                                    	
                                    }catch(SQLException e)
                                    {
                                    	e.printStackTrace();

                                    }
                                    break;
                                case 5:
                                    System.out.println("                                        Exiting Advocate menu.");
                                    break;

                                default:
                                    System.out.println("                                        Invalid choice.");
                                    break;
                            
                            }break;
                        
                           
                        
				case 2:
                        // Customer options
					System.out.println("                                           Customer Menu          ");
					System.out.println("                                          Select an option");
                	System.out.println("                               		1. Register Customer ");
                    System.out.println("                                        2. View Customer records ");
                    System.out.println("                                        3. Modify Customer details  ");
                    System.out.println("                                        4. Delete Customer record");
                    System.out.println("                                        5. Exit");
                    
                    System.out.println("_________________________________________________________________________________________________");
                            int Clientchoice = sc.nextInt();
                            sc.nextLine();

                            switch (Clientchoice) {
                                case 1:
                                    System.out.println("                                        Enter client ID");
                                    int client_id = Integer.parseInt(sc.nextLine());
                                    
                                    System.out.println("                                        Enter client Name");
                                    String client_name = sc.nextLine();
                                    
                                    System.out.println("                                        Enter client_details");
                                    String client_details = sc.nextLine();

                                    Client cli = new Client(client_id, client_name, client_details);

                                    String insertQuery = "insert into clients (client_id, client_name, client_details) values (?, ?, ?)";
                                    try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                                        preparedStatement.setInt(1, cli.client_id);
                                        preparedStatement.setString(2, cli.client_name);
                                        preparedStatement.setString(3, cli.client_details);

                                        int res = preparedStatement.executeUpdate();
                                        if (res == 0) {
                                            System.out.println("                                        Record not inserted");
                                        } else {
                                            System.out.println("                                        Record inserted successfully");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;

                                case 2:
                                    // View Client records
                                    try {
                                        String selectQuery = "SELECT * FROM clients";
                                        try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                                            try (ResultSet rs = preparedStatement.executeQuery()) {
                                                System.out.println("Customer Records:");
                                                System.out.println("------------------");
                                                while (rs.next()) {
                                                	System.out.println("                                        Client_id" + "|" + "Client_name" + "|" + "Client_details");
                                                    System.out.println("                                        "+rs.getInt("client_id") + "|" + rs.getString("client_name") + "|" + rs.getString("client_details"));
                                                }
                                            }
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                	// Modify Client Details
                                	System.out.println("                                        Enter client ID to modify");
                                    int modifyclient_id = Integer.parseInt(sc.nextLine());
                                    
                                    System.out.println("                                        Enter client name to modify");
                                    String modifyclient_name = sc.nextLine();
                                    
                                   
                                    System.out.println("                                        Enter client details to modify");
                                    String modifyclient_details = sc.nextLine();
                                	
                              
                                    String UpdateQuery = "UPDATE clients SET client_name = ?, client_details = ? where client_id =?";
                                    try (PreparedStatement preparedstatement = con.prepareStatement(UpdateQuery)){
                                    	preparedstatement.setString(2,modifyclient_details);
                                    	preparedstatement.setString(1,modifyclient_name);
                                    	preparedstatement.setInt(3,modifyclient_id);
                                    	
                                    	int res = preparedstatement.executeUpdate();
                                    	if(res==0)
                                    	{
                                    		 System.out.println("                                        Record not Found/Modified");
                                        } else {
                                            System.out.println("                                         Record modified successfully");
                                    	}
                                    	
                                    }catch(SQLException e)
                                    {
                                    	e.printStackTrace();

                                    }
                                    break;
                                case 4:
                                	// Delete Client Details
                                	System.out.println("Enter client ID to delete");
                                	int deleteClient_id = sc.nextInt();
                                	sc.nextLine();

                                	String deleteQuery = "DELETE FROM clients WHERE client_id = ?";
                                	try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                                	    preparedStatement.setInt(1, deleteClient_id);

                                	    int res = preparedStatement.executeUpdate();
                                	    if (res == 0) {
                                	        System.out.println("Record not Deleted");
                                	    } else {
                                	        System.out.println("Record Deleted successfully");
                                	    }
                                	} catch (SQLException e) {
                                	    e.printStackTrace();
                                	}

                                    break;
                                case 5:
                                    System.out.println("                                        Exiting Customer menu.");
                                    break;

                                default:
                                    System.out.println("                                        Invalid choice.");
                                    break;
                            }
                            break;
                        
                       
                       
				case 3:   
					
					System.out.println("                                           Appointment Menu          ");
					System.out.println("                                          Select an option");
                	System.out.println("                               		      1. Book an  Appointment ");
                    System.out.println("                                        2. View Appointment ");
                    System.out.println("                                        3. Modify Appointment  ");
                    System.out.println("                                        4. Delete Appointment ");
                    System.out.println("                                        4. Exit");
                    
                    System.out.println("_________________________________________________________________________________________________");
					
                    
					    int Appointmentchoice = sc.nextInt();
					    sc.nextLine();

					switch (Appointmentchoice) {
					        case 1:
					            int lastAppointmentId = 0;

					            // Retrieve the maximum app_id from the database
					            String selectQuery = "SELECT MAX(ap_id) AS max_id FROM appointments";
					            try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
					                ResultSet resultSet = preparedStatement.executeQuery();
					                if (resultSet.next()) {
					                    lastAppointmentId = resultSet.getInt("max_id");
					                }
					            } catch (SQLException e) {
					                e.printStackTrace();
					            }

					            // Increment the maximum app_id to generate the new appointment_id
					            int newAppointmentId = lastAppointmentId + 1;
					            int adv_id =0;
					            
					            
					            System.out.println("                                        Enter Advocate Name to fetch a_id:");
					            String advocateNameToFetch = sc.nextLine();

					            String fetchQuery = "SELECT a_id FROM advocate WHERE a_name = ?";
					            try (PreparedStatement preparedStatement = con.prepareStatement(fetchQuery)) {
					                preparedStatement.setString(1, advocateNameToFetch);
					                ResultSet resultSet = preparedStatement.executeQuery();
					                if (resultSet.next()) {
					                    adv_id = resultSet.getInt("a_id");
					                    System.out.println("                                        Fetched a_id: " + adv_id);
					                } else {
					                    System.out.println("                                        Advocate with the given name not found.:: TRY AGAIN");
					                    break;
					                }
					            } catch (SQLException e) {
					                e.printStackTrace();
					            }

					            
					            
					            
					            int  advocate_id = adv_id;
					            
					            System.out.println("                                        Enter client_id");
					            int client_id = sc.nextInt();
					            
					            sc.nextLine();
					            
					            
                                System.out.println("                                        Enter a date (yyyy-MM-dd):");
                                String userInput = sc.nextLine();
                                
                                LocalDate selectedDate = null;
                                try {
                                    // Parse the user input into a LocalDate object
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                    selectedDate = LocalDate.parse(userInput, formatter);
                                    System.out.println("                                        Selected date: " + selectedDate);
                                } catch (Exception e) {
                                    System.out.println("                                        Invalid date format. Please use yyyy-MM-dd format.");
                                }

                                if (selectedDate != null) {
                                    Date sqlDate = Date.valueOf(selectedDate);

                                    Appointment appointment = new Appointment(newAppointmentId, advocate_id, client_id, selectedDate);

                                    String insertQuery = "insert into appointments (ap_id, advocate_id, client_id, date) values (?, ?, ?, ?)";
                                    try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                                        preparedStatement.setInt(1, appointment.ap_id);
                                        preparedStatement.setInt(2, appointment.advocate_id);
                                        preparedStatement.setInt(3, appointment.client_id);
                                        preparedStatement.setDate(4, sqlDate);

                                        int res = preparedStatement.executeUpdate();
                                        if (res == 0) {
                                            System.out.println("                                        Record not inserted");
                                        } else {
                                            System.out.println("                                        Record inserted successfully");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;

					       case 2:
                                // View Appointment records
                                try {
                                     selectQuery = "SELECT * FROM appointments";
                                    try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                                        try (ResultSet rs = preparedStatement.executeQuery()) {
                                            System.out.println("Appointment Records:");
                                            System.out.println("------------------");
                                            while (rs.next()) {
                                            	System.out.println("                                        appointment_id" + "|" + "advocate_id" + "|" + "client_id"+ "|" + "appointment_date");
                                                System.out.println("                                        "+rs.getInt("ap_id") + "|" + rs.getString("advocate_id") + "|" + rs.getString("client_id")+ "|" + rs.getDate("date"));
                                            }
                                        }
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
					       case 3:
                           	// Modify Appointment Details
                           		System.out.println("                                        Enter Advocate ID to modify");
                                 advocate_id = Integer.parseInt(sc.nextLine());
                               System.out.println("                                        Enter client ID to modify");
                                client_id = Integer.parseInt(sc.nextLine());
                               
                               System.out.println("                                        Enter a date (yyyy-MM-dd): to Modify");
                                userInput = sc.nextLine();
                               
                                selectedDate = null;
                               try {
                                   // Parse the user input into a LocalDate object
                                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                   selectedDate = LocalDate.parse(userInput, formatter);
                                   System.out.println("                                        Selected date: " + selectedDate);
                               } catch (Exception e) {
                                   System.out.println("                                        Invalid date format. Please use yyyy-MM-dd format.");
                               }

                               if (selectedDate != null) {
                                   Date sqlDate = Date.valueOf(selectedDate);

                           	
                               String UpdateQuery = "UPDATE appointments SET advocate_id = ?, date = ? where client_id =?";
                               try (PreparedStatement preparedstatement = con.prepareStatement(UpdateQuery)){
                               	preparedstatement.setDate(2,sqlDate);
                               	preparedstatement.setInt(1,advocate_id);
                               	preparedstatement.setInt(3,client_id);
                               	
                               	int res = preparedstatement.executeUpdate();
                               	if(res==0)
                               	{
                               		 System.out.println("                                        Record not Found/Modified");
                                   } else {
                                       System.out.println("                                        Record modified successfully");
                               	}
                               	
                               }catch(SQLException e)
                               {
                               	e.printStackTrace();

                               }
                               }
                               break;
                           case 4:
                           	// Delete Appointment  Details
                        	   System.out.println("                                        Enter Client ID to Delete");
                               int deleteAppointment_id = Integer.parseInt(sc.nextLine());
                        	

                        	   String deleteQuery = "DELETE FROM appointments WHERE client_id = ?";
                        	   try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                        	       preparedStatement.setInt(1, deleteAppointment_id);

                        	       int res = preparedStatement.executeUpdate();
                        	       if (res == 0) {
                        	           System.out.println("Record Deleted successfully");
                        	       } else {
                        	           System.out.println("Record not Deleted");
                        	       }
                        	   } catch (SQLException e) {
                        	       e.printStackTrace();
                        	   }

                           	
                              
                               
                           	
                               
                               break;
                           case 5:
                               System.out.println("                                        Exiting Appointments menu.");
                               break;

                           default:
                               System.out.println("                                        Invalid choice.");
                               break;					    }
					    break;
					
					  
				case 4:
                        System.out.println("                                        Exiting program.");
                        con.close();
                        sc.close();
                        System.exit(0);
                        break;

                default:
                        System.out.println("                                        Invalid choice.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    
    }
}
