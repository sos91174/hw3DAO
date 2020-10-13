package hw3DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hw3DAO.MySQL;

import hw3DAO.file;

public class StudentDAO implements IDAO<Student> {
	Student stu = new Student(0, null, null, null, null);
	
	
	@Override   //把外部txt資料寫入
	public boolean insert(Student t) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
		MySQL.getInstance();
		conn = MySQL.getConnection();
		String sql = "INSERT INTO StudentData(stuid,name,bd) VALUES (?,?,?)";//寫到資料庫要對應資料庫名字
		//String sql = "INSERT INTO total(id,name,birth) VALUES (?,?,?)";//寫到資料庫要對應資料庫名字
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		String[] lines = file.readFile("D:\\DAOhw3\\StudentData.txt");
		for(String line:lines) {
			String[] data = line.split(",");
			pstmt.setString(1,data[0]);
			pstmt.setString(2,data[1]);
			pstmt.setString(3,data[2]);
			pstmt.execute();
		}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
	            }
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
		System.out.println("你已經把檔案新增到StudentData資料庫");
		return true;
	}
	
	@Override
	public boolean update(Student t) {
		try(
				Connection connect = MySQL.getConnection();
			){

				//選擇要更新什麼資料
				Scanner sc =new Scanner(System.in);  
		        System.out.println("請輸入sql更新資料的語法：");
		        System.out.println("範例:UPDATE StudentData SET name='賈伯斯' WHERE id=1");
		        String sql =sc.nextLine();
			        
				//String sql = "UPDATE StudentData SET name='數學系' WHERE id=1";
				PreparedStatement pstmt = connect.prepareStatement(sql);
//				pstmt.setInt(1, no);
				pstmt.executeUpdate(sql);
//	            ResultSet rs = pstmt.executeQuery(sql);
//	            pstmt.execute();
//	            
//	            rs.close();
	            pstmt.close();
	            System.out.println("你已更新一筆資料");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		return true;
	}
	
	
	
	
//	@Override
	public boolean delete(Student id) {
		try(
				Connection connect = MySQL.getConnection();
			){
//				Scanner sc=new Scanner(System.in);  
//		        System.out.print("請輸入數字：");  
//		        int no=sc.nextInt();  
			
				//輸入要刪除哪一筆
				Scanner sc =new Scanner(System.in);  
		        System.out.println("請輸入sql刪除資料的語法：");
		        System.out.println("範例:DELETE FROM StudentData WHERE id = 1");
		        String sql =sc.nextLine();
			        
				//String sql = "DELETE FROM StudentData WHERE id = ?";
				PreparedStatement pstmt = connect.prepareStatement(sql);
//				pstmt.setInt(1, no);
				pstmt.executeUpdate(sql);
//	            ResultSet rs = pstmt.executeQuery(sql);
//	            pstmt.execute();
//	            
//	            rs.close();
	            pstmt.close();	
	            System.out.println("你已刪除一筆資料");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		
		return true;
	}
	
	
	
	@Override
	public Student get(String id) {
		return get(Integer.parseInt(id));
	}
	
	//拿資料庫的某一筆資料
	@Override
	public Student get(int id) {
		Student aStud=null;
		try(
			Connection connect = MySQL.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement("select * from StudentData where id=?");
		){
			Scanner sc =new Scanner(System.in);  
	        System.out.println("請輸入要查詢的ID：");  
	        int iid =sc.nextInt();
            preparedStatement.setInt(1, iid);
            ResultSet resultSet = preparedStatement.executeQuery();
           
            while (resultSet.next()) {
            	//用迴圈選要拿取的值
            
                String stuid = resultSet.getString("stuid");
                String name = resultSet.getString("name");
                String deptname = resultSet.getString("bd");
             
                System.out.println(stuid +','+ name +','+ deptname );
            
            }
            resultSet.close();
            preparedStatement.close();
            
            if (resultSet!=null)
            	resultSet.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
		
		return aStud;
	
	}
	
	//拿取資料庫全部資料
	@Override
	public List<Student> getAll(){
		ArrayList<Student> list = new ArrayList<Student>();
		Connection connect = MySQL.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM StudentData;";
			pstmt = connect.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//用迴圈選要拿的資料
			while (rs.next()) {
				String sid = rs.getString(2);
				String stuid = rs.getString(3);
				String deptid = rs.getString(4);
			
				String total = sid +',' + stuid +','+ deptid ;
				System.out.println(total);
			
		} 
		
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {//就算程式出錯也要把流關閉 所以要放到這邊執行
			try {
				rs.close();
				
			} catch (SQLException e) {
				// TODO 自動產生的 catch 區塊
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO 自動產生的 catch 區塊
				e.printStackTrace();
			}
		}
		return null;
}
	

}
		
	

