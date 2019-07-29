<%@ page import="java.sql.*, org.json.*" %>
<%
    String genre_id;
	int genreid;
    
    try{
            genre_id=request.getParameter("genre_id");
            genreid=Integer.parseInt(genre_id);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musica","root","root");
            PreparedStatement stmt = con.prepareStatement("select m.id, m.name,m.artist_name,m.cover_img_url,m.music_url,u.name from music m,user u where u.user_id=m.user_id and genre_id=?;");
			stmt.setInt(1,genreid);
            ResultSet rs = stmt.executeQuery();
			JSONArray songList = new JSONArray();
			while(rs.next()){
				JSONObject song = new JSONObject();
				song.put("id",rs.getInt(1)); 
				song.put("name",rs.getString(2)); 
				song.put("artist_name",rs.getString(3)); 
				song.put("cover",rs.getString(4)); 
				song.put("url",rs.getString(5));
				song.put("uploaded",rs.getString(6));
				songList.put(song);
			}
			
			out.println(songList);
    }
	catch(Exception e){
		out.println(e.toString());
	}
%>
