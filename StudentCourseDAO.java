package hw3DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hw3DAO.MySQL;

import hw3DAO.Student;

public class StudentCourseDAO implements IDAO<StudentCourse>{

	@Override
	public boolean insert(StudentCourse t) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
		MySQL.getInstance();
		conn = MySQL.getConnection();
		String sql = "INSERT INTO StudentCourseData(stuid,no) VALUES (?,?)";//寫到資料庫要對應資料庫名字
		//String sql = "INSERT INTO total(id,courseid) VALUES (?,?)";//寫到資料庫要對應資料庫名字
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		String[] lines = file.readFile("D:\\DAOhw3\\StudentCourseData.txt");
		for(String line:lines) {
			String[] data = line.split(",");
			pstmt.setString(1,data[0]);
			pstmt.setString(2,data[1]);
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
		System.out.println("你已經把檔案新增到StudentCourseData資料庫");
		return true;
	}

	@Override
	public boolean update(StudentCourse t) {
		try(
				Connection connect = MySQL.getConnection();
			){
//				Scanner sc=new Scanner(System.in);  
//		        System.out.print("請輸入數字：");  
//		        int no=sc.nextInt();
			
				//選擇要更新什麼資料
				Scanner sc =new Scanner(System.in);
				System.out.println("範例:UPDATE StudentCourseData SET no='C999' WHERE id=1");
		        System.out.println("請輸入sql更新資料的語法：");  
		        String sql =sc.nextLine();
			        
				//String sql = "UPDATE StudentCourse SET no='C999' WHERE id=1";
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
		return false;
	}

	@Override
	public boolean delete(StudentCourse t) {
		try(
				Connection connect = MySQL.getConnection();
			){
//				Scanner sc=new Scanner(System.in);  
//		        System.out.print("請輸入數字：");  
//		        int no=sc.nextInt();  
			
				//輸入要刪除哪一筆
				Scanner sc =new Scanner(System.in); 
				System.out.println("範例:DELETE FROM StudentCourseData WHERE id = 1");
		        System.out.println("請輸入sql刪除資料的語法：");  
		        String sql =sc.nextLine();
			        
				//String sql = "DELETE FROM Total WHERE id = ?";
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
		return false;
	}

	@Override
	public StudentCourse get(String id) {
		// TODO 自動產生的方法 Stub
		return null;
	}

	@Override
	public StudentCourse get(int id) {
		StudentCourse stu=null;
		try(
			Connection connect = MySQL.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement("select * from StudentCourseData where id=?");
		){
			Scanner sc =new Scanner(System.in);  
	        System.out.println("請輸入要查詢的ID：");  
	        int iid =sc.nextInt();
            preparedStatement.setInt(1, iid);
            ResultSet resultSet = preparedStatement.executeQuery();
           
            while (resultSet.next()) {
            	//用迴圈選要拿取的值
                int sid = resultSet.getInt("id");
                String stuid = resultSet.getString("stuid");
                String no = resultSet.getString("no");
          
                
                System.out.println(stuid +','+ no);
                
            }
            resultSet.close();
            preparedStatement.close();
            
            if (resultSet!=null)
            	resultSet.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
		
		return stu;
	
	}

	@Override
	public List<StudentCourse> getAll() {
		ArrayList<StudentCourse> list = new ArrayList<StudentCourse>();
		Connection connect = MySQL.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM StudentCourseData;";
			pstmt = connect.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//用迴圈選要拿的資料
			while (rs.next()) {
				String sid = rs.getString(1);
				String stuid = rs.getString(2);
				String deptid = rs.getString(3);
			
				String total = sid +',' + stuid +','+ deptid;
				System.out.println(total);
//				Student student = new Student(sid, stuid, deptid, courseid);
//				list.add(student);	
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