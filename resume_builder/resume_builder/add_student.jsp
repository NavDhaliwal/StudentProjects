<%@ include file="checklogin.jsp" %>
<%@ page import="java.sql.*"%>
<%-- 
    Document   : index
    Created on : 9 Nov, 2009, 2:10:09 PM
    Author     : Shobit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>eSHIKSHA</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="wrapper">
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="index.jsp">Home</a></li>
			<li><a href="login.jsp">Login</a></li>
			<li><a href="photos.jsp">Photos</a></li>
			<li><a href="about.jsp">About</a></li>
			<li><a href="contact.jsp">Contact</a></li>
			<li><a href="help.jsp">Help</a></li>
		</ul>
	</div>
	<!-- end #menu -->
  <div id="header">
		<div id="logo">
			<h1>ESHIKSHA</h1>
			<p></p>
		</div>
	    <p>&nbsp;</p>
	</div>
    <!-- end #header -->
<div id="page">
	<div id="page-bgtop">
	<div id="page-bgbtm">
	  <%
Connection con;
    PreparedStatement ps,ps1;
    ResultSet rs;
    int p=0;
    String a;

	String s1=request.getParameter("textbox1");
	String s2=request.getParameter("textbox2");
    String s3=request.getParameter("textbox3");
    String s4=request.getParameter("dropdown1");
    String s5=request.getParameter("textbox4");
    String s6=request.getParameter("dropdown2");
    String s7=request.getParameter("textbox5");
    String s8=request.getParameter("textbox6");
    String s9=request.getParameter("textbox7");
    String s10=request.getParameter("textbox8");
    String s11=request.getParameter("textbox9");
    String s12=request.getParameter("textbox10");
    String s13=request.getParameter("textbox11");
    String s14=request.getParameter("textbox12");

	try{
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pdsn","system","test123");
        ps1=con.prepareStatement("select sid from student");
        rs=ps1.executeQuery();
        while(rs.next())
            {
        p=Integer.parseInt(rs.getString(1));
        }
        p=p+1;
        a=p+"";
        ps=con.prepareStatement("insert into student values(?,?,?,?,?,to_date(?,'dd/mm/yyyy'),?,?,?,?,?,?,?,?,?)");
        ps.setString(1,a);
        ps.setString(2,s1);
		ps.setString(3,s2);
        ps.setString(4,s3);
        ps.setString(5,s4);
        ps.setString(6,s5);
        ps.setString(7,s6);
        ps.setString(8,s7);
        ps.setString(9,s8);
        ps.setString(10,s9);
        ps.setString(11,s10);
        ps.setString(12,s11);
        ps.setString(13,s12);
        ps.setString(14,s13);
        ps.setString(15,s14);

		int result=ps.executeUpdate();
		if(result==1)
            {
            out.println("STUDENT INSERTED");
 %>
             <p>&nbsp;</p>
 <%
            out.println("Student ID:"+p);
        //response.sendRedirect("admin.jsp");
        }
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
	  </div>
	</div>
  </div>
</div>
	<div id="footer"></div>
	<!-- end #footer -->
</body>
</html>