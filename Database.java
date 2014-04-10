package toturialJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	private Connection con = null;
	private PreparedStatement ps = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(
					"jdbc:sqlite:projectrpl.sqlite");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertData(String nama, String waktu,String deskripsi) {
		try {
			ps = con.prepareStatement("INSERT INTO task (ID,nama_task,deadline,deskripsi) VALUES (null,?,?,?)");
			
			ps.setString(1, nama);
			ps.setString(2, waktu);
			ps.setString(3, deskripsi);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteData(String data) {
		try {
			ps = con.prepareStatement("delete from task where id = ?");
			ps.setString(1, data);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editData(String dataBaru,String id) {
		try {
			ps = con.prepareStatement("update task set nama_task = ? where ID = ?");
			ps.setString(0, dataBaru);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 


	public ArrayList<Task> getData() {
		ArrayList<Task> data = new ArrayList<Task>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from task");
			while (rs.next()) {
				Task t = new Task();
				t.setId(rs.getInt("ID"));
				t.setTask_name(rs.getString("nama_task"));
				t.setWaktu(rs.getString("deadline"));
				t.setDeskripsi(rs.getString("deskripsi"));
				data.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
	
	

	/*public ArrayList<Task> sortData() {
		// TODO Auto-generated method stub
		ArrayList<Task> data = new ArrayList<Task>();

		try {
			stmt = con.createStatement();
			rs = stmt
					.executeQuery("select * from task order by task_name desc");
			while (rs.next()) {
				Task t = new Task();
				t.setId(rs.getInt("id"));
				t.setTask_name(rs.getString("task_name"));
				data.add(t);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}*/
	

}
