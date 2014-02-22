<%@ page import="java.sql.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>LOGIN_SUCCESS</title>
</head>
<body>
<%
Connection con;
    PreparedStatement ps,ps1;
    ResultSet rs,rs1;
String s1=request.getParameter("textfield");
	String s2=request.getParameter("textfield2");
	if(s1.equals("admin")&s2.equals("test123") )
	{
		response.sendRedirect("admin_home.jsp");
	}
try{
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");
ps=con.prepareStatement("select username,password from emp_details");
//ps.setString(1,s1);
ps1=con.prepareStatement("select username,password from new_user");
//ps1.setString(1,s1);
        rs=ps.executeQuery();
      
  //      rs.next();
        rs1=ps1.executeQuery();
       
    //    rs1.next();
         while(rs1.next())
            {
            String cmp ;
             String cmpp ;
             cmpp=rs1.getString(1);
            cmp=rs1.getString(2);
            if(cmp!=null&cmpp!=null)
                {
       if(cmp.equals(s2)&cmpp.equals(s1))
                {
            session.setAttribute("username",s1);
            session.setAttribute("password",s2);
            response.sendRedirect("welcome.jsp");
            }
       }
       }

        while(rs.next())
            {
                                  String cmp;
                                  String cmpp;
                                  cmp=rs.getString(2) ;
                                  cmpp=rs.getString(1) ;
                                  //out.println(cmp);
if(cmp!=null&cmpp!=null)
{
if(cmp.equals(s2)&cmpp.equals(s1))
{
 session.setAttribute("username",s1);
 session.setAttribute("password",s2);
response.sendRedirect("user_home.jsp");
}
 }       }
       
response.sendRedirect("welcome_main.jsp");
 
}
catch(SQLException se)
		{
		out.println("exc.in sql"+se);
		}
		catch(ClassNotFoundException se1)
		{
		out.println("exc.in class"+se1);
		}
    catch(Exception e)
            {
        out.println(e) ;
        }


%>
</body>
</html>