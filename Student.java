package hw3DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import hw3DAO.MySQL;

import hw3DAO.file;

public class Student {
	int id;
	String name,sid,stuid,deptid,courseid;



	@Override
	public String toString() {
		return "Student 學號=" + sid + ", 姓名=" + stuid + ", 科系=" + deptid + ", 課程=" +"{" +  courseid + "}";
	}

	public Student(int sid, String stuid,String deptname, String coursename) {
		
		
	}
	
	
	public Student(String sid, String stuid, String deptid, String courseid) {
		super();
		this.sid = sid;
		this.stuid = stuid;
		this.deptid = deptid;
		this.courseid = courseid;
	}

	public Student(int sid, String stuid, String name2, String deptname, String coursename) {
	}


	public Student() {
		// TODO 自動產生的建構子 Stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
