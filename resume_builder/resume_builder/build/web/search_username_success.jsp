<%@ page import="java.sql.*"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>resumebuilder.com</title>
</head>

<body bgcolor="#FFFFFF" text="#990000" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
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
    <td width="18%" height="380" valign="top">&nbsp;
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

    <p></td>
    <td width="60%" valign="top"><p><font size="4"><strong>User Details</strong></font></p>

      <label></label>
      <form name="form1" method="post" action="edit_emp1_success.jsp">
        <table width="281" border="0" align="center" bordercolor="#F0F0F0">
          <tr>
            <td width="117" nowrap>&nbsp;</td>
            <td width="52" colspan="4"><label></label></td>
            <td width="24">&nbsp;</td>
            <td width="12">&nbsp;</td>
            <td width="23">&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>&nbsp;</td>
            <td colspan="4">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <%
          Connection con;
PreparedStatement ps,ps1,ps2,ps3,ps4,psn;
boolean test=false;
  ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rsn=null;
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");

  String s=null ;
  s=request.getParameter("textfield");
   psn=con.prepareStatement("select username from new_user");
   rsn=psn.executeQuery();
   while(rsn.next())
            {
           String val= rsn.getString(1);
            if(val!=null && val.equals(s))
                    test=true ;
            }
   if(test)
       {
ps=con.prepareStatement("select username,first_name,last_name,middle_name,to_char(dob,'dd/mon/yyyy') from new_user where username=?");
ps1=con.prepareStatement("select contact from user_contact where username=?");
ps.setString(1,s);
ps2=con.prepareStatement("select email from user_email where username=?");
ps2.setString(1,s);
ps1.setString(1,s);
rs=ps.executeQuery() ;
rs.next();
rs1=ps1.executeQuery() ;
rs1.next();
rs2=ps2.executeQuery() ;
rs2.next();
ps3=con.prepareStatement("select qualifications from user_qual where username=?");
ps3.setString(1,s);
rs3=ps3.executeQuery() ;
rs3.next();
ps4=con.prepareStatement("select specializations from user_spec where username=?");
ps4.setString(1,s);
rs4=ps4.executeQuery() ;
rs4.next();
//out.println(rs4.getString(1)) ;
//out.println(rs4.getString(1)) ;


}
   }
catch(Exception e)
        {
    out.println(e);
    }
          %>

<% if(test)
    {%>
          <tr>
            <td nowrap><font color="#990000">Username </font></td>
            <td colspan="4">
              <label>
              <input name="textfield" type="text" value="<%out.println(rs.getString(1));%>" readonly>
              </label>
              </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>First Name</td>
            <td colspan="4"><input type="text" name="textfield4" value="<%out.println(rs.getString(2));%>" readonly></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Last Name </td>
            <td colspan="4"><input type="text" name="textfield5"value="<%out.println(rs.getString(3));%>"readonly></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Middle Name </td>
            <td colspan="4"><input type="text" name="textfield6"value="<%out.println(rs.getString(4));%>"readonly></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Date Of Birth </td>
            <td colspan="2"><label>
              <input type="text" name="textfield2"value="<%out.println(rs.getString(5));%>" readonly>
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td><label></label></td>
            <td><label></label>            </td>
            <td>&nbsp;</td>
          </tr>
          
          <tr>
            <td nowrap>Qualifications</td>
            <td colspan="2"><label>
              <select name="select4">
            <%String ss= rs3.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>
                  <%rs3.next();%>

              </select>
            </label></td>
            <td><select name="select5">
              <%ss= rs3.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>
                  <%rs3.next();%>


            </select></td>
            <td>&nbsp;</td>
            <td><p><select name="select6">
               <% ss= rs3.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>




              </select>
                </p>            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Specializations</td>
            <td><label>
              <select name="select7">
             <% ss= rs4.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>
                  <%rs4.next();%>


              </select>
            </label></td>
            <td>&nbsp;</td>
            <td><select name="select8">
               <% ss= rs4.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>
                  <%rs4.next();%>


            </select></td>
            <td>&nbsp;</td>
            <td><select name="select9">
 <% ss= rs4.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>


            </select></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          
          <tr>
            <td nowrap>Contact Number </td>
            <td colspan="4"><label>
              <input type="text" name="textfield7"value="<%out.println(rs1.getString(1));%>"readonly>
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Email id </td>
            <td colspan="4"><label>
              <input type="text" name="textfield8"value="<%out.println(rs2.getString(1));%>"readonly>
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>

              <td>&nbsp;</td>
            <td>&nbsp;</td>

              <td>&nbsp;</td>
            <td>&nbsp;</td>
          <tr>
          <td align="center"><a href="search_user.jsp"><font color="#990000">Search Again </font></a></td>
        </tr>

          <%
          }
  %>

<td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          <tr>
          <td width="285" align="center"><%if(!test){%>USERNAME NOT FOUND!!<%}%></td>
        </tr>
        <tr><%if(!test){%>
          <td align="center"><a href="search_username.jsp"><font color="#990000">PLEASE TRY DIFFERENT USERNAME</font></a> </td>
		   <%}%>
        </tr>


          <tr>
            <td nowrap>&nbsp;</td>
            <td colspan="4" align="center"><label></label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>&nbsp;</td>
            <td colspan="4">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>&nbsp;</td>

            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>&nbsp;</td>
            <td colspan="4">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
      </form>
    <p>&nbsp;</p>
    <td>
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
    <td width="22%" valign="top">
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
