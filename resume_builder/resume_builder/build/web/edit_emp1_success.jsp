<%@ page import="java.sql.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>USER_REGISTERED</title>
</head>
<body bgcolor="#FFFFFF" text="#990000" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<%
Connection con;
    PreparedStatement ps=null,ps1=null,ps2=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null,ps8=null,ps9=null,ps10=null,ps11=null,ps12=null,ps13=null,ps14=null,ps15=null,ps16=null,ps17=null,ps18=null,ps19=null;
    //ResultSet rs8=null,rs9=null,rs10=null,rs11=null,rs12=null,rs13=null;
     String message=null ;
String s1=request.getParameter("textfield");
String s2=request.getParameter("textfield4");
String s3=request.getParameter("textfield5");
String s4=request.getParameter("textfield6");
String s5=request.getParameter("textfield2");
String s6=request.getParameter("textfield3");
String s7=request.getParameter("select4");
String s8=request.getParameter("select5");
String s9=request.getParameter("select6");
String s10=request.getParameter("select7");
String s11=request.getParameter("select8");
String s12=request.getParameter("select9");
String s13=request.getParameter("textfield76");
String s14=request.getParameter("textfield73");
String s15=request.getParameter("textfield72");
String s16=request.getParameter("textfield74");
String s17=request.getParameter("textfield75");
String s18=request.getParameter("textfield7");
String s19=request.getParameter("textfield8");
try{
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");
ps=con.prepareStatement("update emp_details set first_name=? where emp_id=?");
ps.setString(1,s2);
ps.setString(2,s1);
int rs=ps.executeUpdate();
ps1=con.prepareStatement("update emp_details set last_name=? where emp_id=?");
ps1.setString(1,s3);
ps1.setString(2,s1);
int rs1=ps1.executeUpdate();
ps2=con.prepareStatement("update emp_details set middle_name=? where emp_id=?");
ps2.setString(1,s4);
ps2.setString(2,s1);
int rs2=ps2.executeUpdate();
ps3=con.prepareStatement("update emp_details set salary=? where emp_id=?");
ps3.setString(1,s13);
ps3.setString(2,s1);
int rs3=ps3.executeUpdate();
ps4=con.prepareStatement("update emp_details set address=? where emp_id=?");
ps4.setString(1,s14);
ps4.setString(2,s1);
int rs4=ps4.executeUpdate();
ps5=con.prepareStatement("update emp_details set city=? where emp_id=?");
ps5.setString(1,s15);
ps5.setString(2,s1);
int rs5=ps5.executeUpdate();

ps6=con.prepareStatement("update emp_details set state=? where emp_id=?");
ps6.setString(1,s16);
ps6.setString(2,s1);
int rs6=ps6.executeUpdate();

ps7=con.prepareStatement("update emp_details set country=? where emp_id=?");
ps7.setString(1,s17);
ps7.setString(2,s1);
int rs7=ps7.executeUpdate();

ps8=con.prepareStatement("delete from emp_contact where emp_id=?");
ps8.setString(1,s1);
int rs8=ps8.executeUpdate();

ps9=con.prepareStatement("insert into emp_contact values(?,?)");
ps9.setString(1,s1);
ps9.setString(2,s18);
int result=ps9.executeUpdate();

ps10=con.prepareStatement("delete from emp_email where emp_id=?");
ps10.setString(1,s1);
int rs10=ps10.executeUpdate();

ps11=con.prepareStatement("insert into emp_email values(?,?)");
ps11.setString(1,s1);
ps11.setString(2,s19);
int result1=ps11.executeUpdate();

ps12=con.prepareStatement("delete from emp_qual where emp_id=?");
ps12.setString(1,s1);
int rs12=ps12.executeUpdate();

ps13=con.prepareStatement("delete from emp_spec where emp_id=?");
ps13.setString(1,s1);
int rs13=ps13.executeUpdate();

ps14=con.prepareStatement("insert into emp_qual values(?,?)");
ps14.setString(1,s1);
ps14.setString(2,s7);
int result3=ps14.executeUpdate();
ps14.setString(1,s1);
ps14.setString(2,s8);
int result4=ps14.executeUpdate();
ps14.setString(1,s1);
ps14.setString(2,s9);
int result5=ps14.executeUpdate();

ps17=con.prepareStatement("insert into emp_spec values(?,?)");
ps17.setString(1,s1);
ps17.setString(2,s10);
int result6=ps17.executeUpdate();
ps17.setString(1,s1);
ps17.setString(2,s11);
int result7=ps17.executeUpdate();
ps17.setString(1,s1);
ps17.setString(2,s12);
int result8=ps17.executeUpdate();




 
if(rs==1&&rs1==1&&rs2==1&&rs3==1&&rs4==1&&rs5==1&&rs6==1&&rs7==1&&rs8==1&&rs10==1&&rs12==3&&rs13==3&&result==1 && result1==1 && result3==1 && result4==1 && result5==1 && result6==1 && result7==1 && result8==1)
            {
            message="EMPLOYEE UPDATED";
 %>
<%
            //out.println("USERNAME:"+s3);
	//out.println("REGISTRATION NO:"+a);
        
        }
else
{
message="EMPLOYEE NOT UPDATED";
 %>
 <%
            

}
    }
	catch(SQLException se)
		{
		out.println("exc.in sql////////////////////////"+se);
		}
		catch(ClassNotFoundException se1)
		{
		out.println("exc.in class///////////////////////"+se1);
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
    <td align="left" valign="middle"><strong>User/Administrator Login </strong><blockquote>
    <td align="right" valign="middle"><a href="user_reg.htm"><font color="#990000">New User?Sign Up!</font></a>
    <td colspan="2">&nbsp;</td>
  </tr>

  <tr>
    <td width="15%" height="380" valign="top">&nbsp;
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
    <td width="58%" colspan="2" align="center" valign="top"><form name="form1" method="post" action="admin_home.htm">
    </form>
      <table width="229" height="112" border="0">
        <tr>
          <td align="center"><%out.println(message);%> </td>
        </tr>
      </table>
    <td width="11%">
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
    <td width="16%" valign="top">
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
</table>
<table border="0" width="100%" bgcolor="#000000" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%"><font size="1">&nbsp;</font></td>
  </tr>
</table>

</body>
</html>