package hw3DAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;




public class Client {

	public static void main(String[] args) throws SQLException {
		DeptDAO a1 = new DeptDAO();//a1.insert(null);//a1.update(null);//a1.delete(null);//a1.getAll();//a1.get(1);
		CourseDataDAO a2 = new CourseDataDAO();//a2.insert(null);//a2.update(null);//a2.delete(null);//a2.getAll();//a2.get(1);
		StudentCourseDAO a3 = new StudentCourseDAO();//a3.insert(null);//a3.update(null);//a3.delete(null);//a3.getAll();//a3.get(1);
		StudentDAO a4 = new StudentDAO();//a4.insert(null);//a4.update(null);//a4.delete(null);//a4.getAll();//a4.get(1);
		TotalDAO a5 = new TotalDAO();
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("------------------------------------------------------------------------------------------------------------");
			System.out.println("請輸入選項：(1)新增所有資料到table  (2)查詢全部資料 (3)更新資料 (4)刪除資料 (5)查詢單筆資料 (6)刪除table所有資料 (7)離開程式");
			int no =input.nextInt();
	        if (no == 1) {
	        	a1.insert(null);
	        	a2.insert(null);
	        	a3.insert(null);
	        	a4.insert(null);
	        	a5.insert(null);
	        	continue;
	        }else if(no == 2){
	        	a5.getAll();
	    		continue;
	        }else if(no == 3){
	        	a5.update(null);
	        	continue;
	        }else if(no == 4){
	        	a5.delete(null);
	        	continue;
	        }else if(no == 5){
	        	a5.get(1);
	        	continue;
	        }else if(no == 6){
	        	a5.deletetable();
	        	continue;
	        }else if(no == 7){
	        	System.out.println("你已關閉程式");
	        	break;
	        }else{
	        	System.out.println("請輸入正確選項:(只有數字1~7的選項)");
	        	continue;
	        }
			}
		
		//a5.insert(null);
		//a5.update(null);
		//a5.delete(null);
		//a5.getAll();
		//a5.get(1);
		//a5.deletetable();
		
		
		//IDAO<Student> sDAO = new StudentDAO();
		//Student stu = new Student(0, null, null, null, null);

		//引入txt放到不同table
		//sDAO.insert(null);
		
		//新增全部檔案到table
		//stu.Total();
		
		//查詢單筆資料
		//sDAO.get(1);

		//查詢全部資料
		//List<Student> all = sDAO.getAll();
		//all.forEach(System.out::println);
		//或用這個
		//sDAO.getAll();
		
		//修改資料  語法(UPDATE Total SET deptname='數學系' WHERE id=6)
		//sDAO.update(null);
		
		//刪除資料 語法(DELETE FROM Total WHERE id = 1)
		//sDAO.delete(null);
		
		//list a = sDAO.getAll();
		
		//sDAO.update(stu);

	}


}
