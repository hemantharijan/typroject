<%@ page import="java.sql.*, org.json.*" %>
<%
    String username,password;
    try{
            username=request.getParameter("username");
            password=request.getParameter("password");
            Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musica","root","root");
            PreparedStatement stmt = con.prepareStatement("select User_ID from user where User_Name=? and Password=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
			JSONObject res = new JSONObject();
			if (result.next()) {
				out.println("1");
			}
			else {
				out.println("0");
			}
    }
	catch(Exception e){
		out.println(e.toString());
	}
%>

