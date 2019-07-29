<%@ page import="java.sql.*, org.json.*" %>
<%
    String name,username,password,email;
    try{
            name=request.getParameter("name");
            username=request.getParameter("username");
			password=request.getParameter("password");
			email = request.getParameter("email");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musica","root","root");
            PreparedStatement stmt = con.prepareStatement("insert into user(Name,User_Name,Password,Email_ID) values(?,?,?,?)");
			stmt.setString(1,name);
			stmt.setString(2,username);
			stmt.setString(3,password);
			stmt.setString(4,email);
            int result = stmt.executeUpdate();
			JSONObject res = new JSONObject();
			res.put("result",result);
			out.println(res.get("result"));
    }
	catch(Exception e){
		out.println(e.toString());
	}
%>

