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

public class DeptDAO implements IDAO<Dept>{
	
	


	@Override
	public boolean insert(Dept t) {
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
		MySQL.getInstance();
		conn = MySQL.getConnection();
		String sql = "INSERT INTO DeptData(no,name) VALUES (?,?)";//寫到資料庫要對應資料庫名字
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		String[] lines = file.readFile("D:\\DAOhw3\\DeptData.txt");
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
		System.out.println("你已經把檔案新增到DeptData資料庫");
		return true;
	}

	@Override
	public boolean update(Dept t) {
		try(
				Connection connect = MySQL.getConnection();
			){

			
				//選擇要更新什麼資料
				Scanner sc =new Scanner(System.in);  
		        System.out.println("請輸入sql更新資料的語法：");
		        System.out.println("範例:UPDATE DeptData SET deptname='數學系' WHERE id=6");
		        String sql =sc.nextLine();
			        
				//String sql = "UPDATE DeptData SET deptname='數學系' WHERE id=6";
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


	public boolean delete(Dept t) {
		try(
				Connection connect = MySQL.getConnection();
			){
//				Scanner sc=new Scanner(System.in);  
//		        System.out.print("請輸入數字：");  
//		        int no=sc.nextInt();  
			
				//輸入要刪除哪一筆
				Scanner sc =new Scanner(System.in);  
		        System.out.println("請輸入sql刪除資料的語法：");
		        System.out.println("範例:DELETE FROM DeptData WHERE id = 1");
		        String sql =sc.nextLine();
			        
				//String sql = "DELETE FROM DeptData WHERE id = ?";
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
	public List<Dept> getAll() {
		ArrayList<Dept> list = new ArrayList<Dept>();
		Connection connect = MySQL.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM DeptData;";
			pstmt = connect.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//用迴圈選要拿的資料
			while (rs.next()) {
				String id = rs.getString(1);
				String no = rs.getString(2);
				String name = rs.getString(3);
				String total = id +',' + no +',' + '['+ name + ']';
				System.out.println(total);
				Dept dept = new Dept(id, no, name);
				list.add(dept);	
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

	@Override
	public Dept get(String id) {
		// TODO 自動產生的方法 Stub
		return null;
	}

	@Override
	public Dept get(int id) {
		Dept dept=null;
		try(
			Connection connect = MySQL.getConnection();
			PreparedStatement preparedStatement = connect.prepareStatement("select * from DeptData where id=?");
		){
			Scanner sc =new Scanner(System.in);  
	        System.out.println("請輸入要查詢的ID：");  
	        int iid =sc.nextInt();
            preparedStatement.setInt(1, iid);
            ResultSet resultSet = preparedStatement.executeQuery();
           
            while (resultSet.next()) {
            	//用迴圈選要拿取的值
              
                String stuid = resultSet.getString("no");
                String name = resultSet.getString("name");

                
                System.out.println(stuid +',' + '['+ name + ']');
             
                return dept;
            }
            resultSet.close();
            preparedStatement.close();
            
            if (resultSet!=null)
            	resultSet.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
		
		return dept;
	
	}
}


