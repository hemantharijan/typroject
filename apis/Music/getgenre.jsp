<%@ page import="java.sql.*, org.json.*" %>
<%
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musica","root","root");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from genre");
		JSONArray emp_list = new JSONArray();
		while(rs.next())
		{
			JSONObject emp = new JSONObject();
			emp.put("id",rs.getInt("id"));
			emp.put("genre_name",rs.getString("genre_name"));
			emp.put("Img_url",rs.getString("Img_url"));
			emp_list.put(emp);
		}
		out.println(emp_list);
	}
	catch(Exception e)
	{
		out.println(e.toString());
	}
%>
