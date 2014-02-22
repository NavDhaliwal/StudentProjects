<%@ page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
<%
Connection con;
PreparedStatement ps;
  ResultSet rs;
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");
ps=con.prepareStatement("CREATE TABLE new_user(username varchar(20) primary key,password varchar(20) not null,first_name varchar(20) not null, last_name varchar(20) not null, middle_name varchar(20),dob date not null,doj date not null,salary number(10) not null,address varchar(20) not null,city varchar(20) not null, state varchar(20) not null,country varchar(20) not null,email_id varchar(30) )");
 rs=ps.executeQuery();
            
            out.println("table created");
 %>
             <p>&nbsp;</p>
 <%
            
        
    }
	catch(SQLException se)
		{
		out.println("exc.in sql"+se);
		}
		catch(ClassNotFoundException se1)
		{
		out.println("exc.in class"+se1);
		}
%>
</body>
</html>