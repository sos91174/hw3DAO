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

public class TotalDAO implements IDAO<Total>{

	@Override
	public boolean insert(Total t) {
		Connection conn;
		String total ;
		PreparedStatement pstmt;
		ResultSet rs;
		try {
			MySQL.getInstance();
		//建立連接口
		conn = MySQL.getConnection();
		//寫mysql語法
		String sql = "SELECT sd.stuid , sd.name , dd.name ,  group_concat(cd.coursename) coursename " + 
				"FROM CourseData cd , DeptData dd , StudentCourseData scd , StudentData sd " + 
				"WHERE substring(sd.stuid, 1, 2) = dd.no && sd.stuid=scd.stuid && scd.no = cd.course " + 
				"group by sd.stuid;";
		//建立PreparedStatement
		pstmt = conn.prepareStatement(sql);
		//建立ResultSet
		rs = pstmt.executeQuery(sql);
		while (rs.next()) {
			String sid = rs.getString(1);
			String stuid = rs.getString(2);
			String deptid = rs.getString(3);
			String courseid = rs.getString(4);
			total = sid +',' + stuid +','+ deptid +','+'['+ courseid + ']';
			//再迴圈內把整理好的ResultSet寫進去新的table
			String sql2 = "INSERT INTO Total(stuid,stuname,deptname,coursename) VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1,rs.getString(1));
			pstmt.setString(2,rs.getString(2));
			pstmt.setString(3,rs.getString(3));
			pstmt.setString(4,rs.getString(4));
			pstmt.execute();
		}
		
			rs.close();
			pstmt.close();
			System.out.println("所有table資料都已經全部新增到Total資料庫");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return true;
	}

	@Override
	public boolean update(Total t) {
		try(
				Connection connect = MySQL.getConnection();
			){
//				Scanner sc=new Scanner(System.in);  
//		        System.out.print("請輸入數字：");  
//		        int no=sc.nextInt();
			
				//選擇要更新什麼資料
				Scanner sc =new Scanner(System.in);  
				System.out.println("範例:UPDATE Total SET deptname='數學系' WHERE id=1");
		        System.out.println("請輸入sql更新資料的語法：");  
		        String sql =sc.nextLine();
			        
				//String sql = "UPDATE Total SET deptname='數學系' WHERE id=6";
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
	public boolean delete(Total t) {
		try(
				Connection connect = MySQL.getConnection();
			){
//				Scanner sc=new Scanner(System.in);  
//		        System.out.print("請輸入數字：");  
//		        int no=sc.nextInt();  
			
				//輸入要刪除哪一筆
				Scanner sc =new Scanner(System.in);  
				System.out.println("範例:DELETE FROM Total WHERE id = 2");
		        System.out.println("請輸入sql刪除資料的語法：");  
		        String sql =sc.nextLine();
			        
				//String sql = "DELETE FROM Total WHERE id = 3";
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

	
public void deletetable() {
		
		try(
				Connection connect = MySQL.getConnection();
			){

				//刪除table裡的所有資料
				Scanner sc =new Scanner(System.in);  
		        System.out.println("請輸入你要刪除table資料的語法：");
		        System.out.println("範例:truncate Total");
		        String sql =sc.nextLine();
//		        String sql ="truncate Total";
			        
				//String sql = "DELETE FROM Total WHERE id = ?";
				PreparedStatement pstmt = connect.prepareStatement(sql);
//				pstmt.setInt(1, no);
				pstmt.executeUpdate(sql);
//	            ResultSet rs = pstmt.executeQuery(sql);
//	            pstmt.execute();
//	            
//	            rs.close();
	            pstmt.close();	
	            System.out.println("你已刪除Table的所有資料");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
	           
	}


	@Override
	public Total get(String id) {
		// TODO 自動產生的方法 Stub
		return null;
	}

	@Override
	public Total get(int id) {
		Total aStud=null;
		try(
			Connection connect = MySQL.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement("select * from Total where id=?");
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
                String name = resultSet.getString("stuname");
                String deptname = resultSet.getString("deptname");
                String coursename = resultSet.getString("coursename");
                
                System.out.println(stuid +','+ name +','+ deptname +'['+ coursename + ']');
//                aStud = new Student(sid,stuid,name,deptname,coursename);
//                return aStud;
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

	@Override
	public List<Total> getAll() {
		ArrayList<Total> list = new ArrayList<Total>();
		Connection connect = MySQL.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM Total;";
			pstmt = connect.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//用迴圈選要拿的資料
			while (rs.next()) {
				String sid = rs.getString(2);
				String stuid = rs.getString(3);
				String deptid = rs.getString(4);
				String courseid = rs.getString(5);
				String total = sid +',' + stuid +','+ deptid +','+'['+ courseid + ']';
				System.out.println(total);
//				Total student = new Total(sid, stuid, deptid, courseid);
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
