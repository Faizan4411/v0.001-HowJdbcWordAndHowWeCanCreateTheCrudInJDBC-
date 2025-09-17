

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

class DatabaseManager{
	
	private static Connection con; 
		static{
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Driver Connected...");
				
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/techfira1","root","");
				System.out.println("Database Connected Successfully...");
				
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
		
		static void getCustomerView()throws Exception{// getCustomerView Start
				
			String query="select * from customer";
			System.out.println(query);
			
			Statement st=null;
			ResultSet result=null;
			
			try{
				st=con.createStatement();
				result=st.executeQuery(query);
				
				while(result.next()){
					int customerId=result.getInt("customer_id");
					String customerName=result.getString("customer_name");
					String gmail=result.getString("gmail");
					String contact=result.getString("contact");
					
					System.out.println("\ncustomer_id   : "+customerId);
					System.out.println("customer_name : "+customerName);
					System.out.println("gmail         : "+gmail);
					System.out.println("contact       : "+contact);
				}
			}finally{
   
			   if(st!=null)st.close();
			   if(result!=null)result.close();
			}
		}// getCustomerView End
		
		public static void insertCustomer(String customerName,String gmail,String contact)throws Exception{// InsertCustomer start
			
			
			String query="insert into customer(customer_name,gmail,contact)values('"+customerName+"', '"+gmail+"','"+contact+"')";
			System.out.println(query);
		   
			Statement st=null;
		   
			try{
			   st=con.createStatement();
			   int rows=st.executeUpdate(query);
			   
				if(rows==1){
					javax.swing.JOptionPane.showMessageDialog(null,"Customer Add "+rows);
				}else{
					javax.swing.JOptionPane.showMessageDialog(null,"Record Not add "+rows);
				}
			}
			finally{
		   
			   if(st!=null)st.close();
			}
		}// InsertCustomer End
		
		
		public static void updateCustomer(int customerId,String customerName,String gmail,String contact)throws Exception{
   
			String query="update customer set customer_name= '"+customerName+"', gmail= '"+gmail+"', contact= '"+contact+"'  where customer_id= "+customerId;
			System.out.println(query);
		   
			Statement st=null;
		   
			try{
		   
			   st=con.createStatement();
			   int rows=st.executeUpdate(query);
			   if(rows==1){
					javax.swing.JOptionPane.showMessageDialog(null,"Customer Update "+rows);
				}else{
					javax.swing.JOptionPane.showMessageDialog(null,"Record Not Update "+rows);
				}
			}
			finally{
		   
				if(st!=null){
			   
				   st.close();
				}
			}
		}
		
		
		public static void deleteCustomer(int customerId)throws Exception{// deleteCustomer start
    
			String query="delete from customer where customer_id="+customerId;
			System.out.println(query);
			
			Statement st=null;
			
			try{
			
				st=con.createStatement();
				int rows=st.executeUpdate(query);
				
				if(rows==1){
					javax.swing.JOptionPane.showMessageDialog(null,"Customer Delete "+rows);
				}else{
					javax.swing.JOptionPane.showMessageDialog(null,"Record Not Delete "+rows);
				}
			}
			finally{// finally start
			
				if(st!=null){
				
					st.close();
				}
			}
		}// deleteCustomer End
	
	
	public static void main(String arg[]){
		
		
		java.util.Scanner as=new java.util.Scanner(System.in);
		try{
			DatabaseManager dbm=new DatabaseManager();
			do{
				System.out.println("\n-------------------------------");
				System.out.print("\n1 Customer Review\n2 Customer Add\n3 Customer Update\n4 Customer Delete\n5 Exit\n\nEnter Selection:  ");
				int number=as.nextInt();
				System.out.println("\n-------------------------------");
				
				//------------------get Customer Review start--------------------------------------------
				if(number==1){
					dbm.getCustomerView();	
				}
				//------------------get Customer Review End--------------------------------------------
				
				//------------------get Customer Insert start--------------------------------------------
				if(number==2){
					
					System.out.print("Enter Name: ");
					String insertName=as.next();
					System.out.print("Enter gmail: ");
					String insertGmail=as.next();
					System.out.print("Enter contact Number: ");
					String insertContact=as.next();
					
					dbm.insertCustomer(insertName,insertGmail,insertContact);
				}
				//------------------get Customer Insert End--------------------------------------------
				
				//------------------get Customer Update start--------------------------------------------
				if(number==3){
					
					System.out.print("Enter Update Id: ");
					int	updateCustomerId=as.nextInt();
					
					// System.out.print("Enter 1 All rows Update\nEnter 2 One Row Update\n\n Enter Selection: ");
					// int updateSelection=as.nextInt();
					
					String updateName=null;
					String updateGmail=null;
					String updateContact=null;
					
					// if(updateSelection==1){
						System.out.print("Enter Name: ");
						updateName=as.next();
						System.out.print("Enter gmail: ");
						updateGmail=as.next();
						System.out.print("Enter contact Number: ");
						updateContact=as.next();
						
					// }
					
					// if(updateSelection==2){
						
						// System.out.println("1 Customer Name\n2 Customer gmail\n3 Customer contact ");
						// System.out.print("Select the rows Name you want to change: ");
						// int updateChange=as.nextInt();
						
						// if(updateChange==1){
							// System.out.print("Enter Name: ");
							// updateName=as.next();
						// }
						// if(updateChange==2){
							// System.out.print("Enter gmail: ");
							// updateGmail=as.next();
						// }
						// if(updateChange==3){
							// System.out.print("Enter contact Number: ");
							// updateContact=as.next();
						// }
						
					// }
					dbm.updateCustomer(updateCustomerId,updateName,updateGmail,updateContact);
					
				}
				//------------------get Customer Update End--------------------------------------------
				//------------------get Customer Delete start--------------------------------------------
				if(number==4){
					System.out.print("Enter Update Id: ");
					int deleteCustomerId=as.nextInt();
					
					dbm.deleteCustomer(deleteCustomerId);
				}
				//------------------get Customer Delete End--------------------------------------------
				
				
				if(number==5){break;}
				
			}while(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}