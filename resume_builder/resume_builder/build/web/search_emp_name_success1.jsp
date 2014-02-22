<%@ page import="java.sql.*"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>resumebuilder.com</title>
</head>

<body bgcolor="#FFFFFF" text="#990000" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
 <%
        Connection con;
PreparedStatement ps,ps1,ps2,ps3,ps4;
String s=null ;
s=request.getParameter("textfield");
  String s1=null ;
  s1=request.getParameter("textfield2");
  String s2=null ;
  s2=request.getParameter("textfield3");
  ResultSet rs=null;
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");
         ps=con.prepareStatement("select emp_id,first_name,last_name,middle_name from emp_details where lower(first_name) like lower(?) and lower(last_name) like lower(?) and lower(middle_name) like lower(?)");
        if(s!=null) {s="%"+s+"%";}
        if(s1!=null) {s1="%"+s1+"%";}
        if(s2!=null) {s2="%"+s2+"%";}
         ps.setString(1,s);
        ps.setString(2,s1);
        ps.setString(3,s2);
         rs=ps.executeQuery() ;
   }
catch(Exception e)
        {
    out.println(e);
    }
        %>
<table width="100%" height="172" border="0" cellspacing="0">
  <tr>
    <td width="25%" height="168" align="left" valign="top" bgcolor="#1F41B8"><img src="cap.png" width="248" height="161"></td>
    <td width="75%" valign="middle" bgcolor="#1F41B8"><font size="7">RESUME BUILDER </font></td>
  </tr>
</table>
<table border="0" width="100%" bgcolor="#000000" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%"><font color="#FFFFFF" face="Arial" size="2"><b>WWW.RESUMEBUILDER.COM</b></font></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="./JobSearchNewspaper.jpg">
  <tr>
    <td valign="top">&nbsp;</td>
    <td width="65%" align="center" valign="middle"><strong><font size="6">Search Results </font></strong>
    <td colspan="2">&nbsp;</td>
  </tr>

  <tr>
    <td width="15%" height="380" rowspan="2" valign="top">&nbsp;
      <p style="margin-left: 20"><b><font face="Arial" size="2" color="#000000">LINK
      1<br>
      LINK 2<br>
      LINK 3<br>
      LINK 4<br>
      LINK 5<br>
      LINK 6</font></b></p>
      <p>&nbsp;</p>
      <p>&nbsp; </p>
      <p>&nbsp;</p>
      <p>&nbsp; </p>
    <p></td>
    <td height="134" align="center" valign="top"><table width="582" border="0">
      <tr>
        <td width="131">Employee ID </td>
        <td width="132">Name</td>
      </tr>
	  <%
	  while(rs.next())
	  {
          String id=null,name=null ;
	  id=rs.getString(1) ;
	   name=rs.getString(2)+" "+rs.getString(4)+" "+rs.getString(3)  ;
	  %>
	  <tr>
        <td><%out.println(id);%></td>
        <td><%out.println(name);%></td>
      </tr>
	  <%}
	  %>
    </table>

    <td width="4%" rowspan="2">
        <p>&nbsp; </p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
      <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
    </blockquote>      </td>
    <td width="16%" rowspan="2" valign="top">
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td width="100%" bgcolor="#FFFFFF"><b><font face="Arial" size="2" color="#990000">SIDE
            HEADING 1</font></b></td>
        </tr>
      </table>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td width="100%"><br>
            <font size="2" face="Arial">This is the place for your news or other
            site information. Another good use for this space is to display
            advertisements.</font>
            <p><font size="2" face="Arial">This is the place for your news or
            other site information. Another good use for this space is to
            display advertisements.<br>
          &nbsp;</font></td>
        </tr>
      </table>
      <table border="0" width="100%" bgcolor="#008080" cellspacing="0" cellpadding="0">
        <tr>
          <td width="100%" bgcolor="#FFFFFF"><b><font face="Arial" size="2" color="#990000">SIDE
            HEADING 2</font></b></td>
        </tr>
      </table>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td width="100%"><br>
            <font size="2" face="Arial">This is the place for your news or other
            site information. Another good use for this space is to display
            advertisements.</font>
            <p><font size="2" face="Arial">This is the place for your news or
            other site information. Another good use for this space is to
            display advertisements<br>
          &nbsp;</font></td>
        </tr>
    </table>    </td>
  </tr>
  <tr>
    <td align="center" valign="top"><form name="form1" method="post" action="search_empid_success.jsp">
       <table width="245" border="0">
        <tr>
          <td width="92" height="56" align="left">Employee ID </td>
          <td width="153"><label>
            <input name="textfield" type="text" id="textfield">
          </label></td>
        </tr>
      </table>
      <p>
        <label>
        <input type="submit" name="Submit" value="Show Details">
        </label>
      </p>
    </form>
  </tr>
</table>
<table border="0" width="100%" bgcolor="#000000" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%"><font size="1">&nbsp;</font></td>
  </tr>
</table>

</body>

</html>