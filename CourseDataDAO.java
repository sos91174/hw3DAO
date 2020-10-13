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

public class CourseDataDAO implements IDAO<CourseData>{

	//讀txt檔案並寫入CourseData資料庫
	public boolean insert(CourseData t) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
		MySQL.getInstance();
		conn = MySQL.getConnection();
		String sql = "INSERT INTO CourseData(course,coursename) VALUES (?,?)";//寫到資料庫要對應資料庫名字
		//String sql = "INSERT INTO total(courseid,course) VALUES (?,?)";//寫到資料庫要對應資料庫名字
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		String[] lines = file.readFile("D:\\DAOhw3\\CourseData.txt");//讀取檔案
		for(String line:lines) {//用迴圈取每個位置的資料
			String[] data = line.split(",");
			pstmt.setString(1,data[0]);
			pstmt.setString(2,data[1]);
			pstmt.execute();//執行sql
		}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
	            }
				if (conn != null) {
					conn.close();//關閉接口
				}
			} catch (SQLException e) {
			}
		}
		System.out.println("你已經把檔案新增到CourseData資料庫");
		return true;
	}
	//更新檔案
	@Override
	public boolean update(CourseData t) {
		try(
				Connection connect = MySQL.getConnection();
			){

				//選擇要更新什麼資料
				Scanner sc =new Scanner(System.in);
				System.out.println("範例:UPDATE CourseData SET coursename='數學系' WHERE id=2");
		        System.out.println("請輸入sql更新資料的語法：");  
		        String sql =sc.nextLine();
			        
				//String sql = "UPDATE CourseData SET coursename='數學系' WHERE id=2";
				PreparedStatement pstmt = connect.prepareStatement(sql);
//				pstmt.setInt(1, no);
				pstmt.executeUpdate(sql);
//	            ResultSet rs = pstmt.executeQuery(sql);
//	            pstmt.execute();
//	            rs.close();
	            pstmt.close();
	            System.out.println("你已更新一筆資料");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		return false;
	}

	//刪除CourseData資料庫的檔案
	public boolean delete(CourseData t) {
		try(
				Connection connect = MySQL.getConnection();
			){
				//輸入要刪除哪一筆
				Scanner sc =new Scanner(System.in);
				System.out.println("DELETE FROM CourseData WHERE id = 1");
		        System.out.println("請輸入sql刪除資料的語法：");  
		        String sql =sc.nextLine();
			        
				//String sql = "DELETE FROM CourseData WHERE id = 1";
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
	public CourseData get(String id) {
		// TODO 自動產生的方法 Stub
		return null;
	}
	//讀取CourseData單筆資料
	@Override
	public CourseData get(int id) {
		CourseData co=null;
		try(
			Connection connect = MySQL.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement("select * from CourseData where id=?");
		){
			Scanner sc =new Scanner(System.in);  
	        System.out.println("請輸入要查詢的ID：");  
	        int iid =sc.nextInt();
            preparedStatement.setInt(1, iid);
            ResultSet resultSet = preparedStatement.executeQuery();
           
            while (resultSet.next()) {
            	//用迴圈選要拿取的值
               // int sid = resultSet.getInt("id");
                String cours = resultSet.getString("course");
                String coursename = resultSet.getString("coursename");
               
                
                System.out.println(cours +','+'['+ coursename + ']');
//                co = new CourseData(sid,cours,coursename);
//                return co;
            }
            resultSet.close();
            preparedStatement.close();
            
            if (resultSet!=null)
            	resultSet.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
		
		return co;
	
	}
	//讀取CourseData裡面的全部資料
	@Override
	public List<CourseData> getAll() {
		List<CourseData> list = new ArrayList<CourseData>();
		Connection connect = MySQL.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM CourseData;";
			pstmt = connect.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//用迴圈選要拿的資料
			while (rs.next()) {
				String id = rs.getString(1);
				String course = rs.getString(2);
				String coursename = rs.getString(3);
				String total = id +',' + course +','+'['+ coursename + ']';
				System.out.println(total);
//				CourseData co = new CourseData(id, course, coursename);
//				list.add(co);	
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
