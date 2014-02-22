
<%@ page import="java.sql.*"%>
<html>

<head>
    <script language="JavaScript" src="gen_validator.js" type="text/javascript">
	</script>
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
      <p>a </p>
    <p></td>
    <td width="60%" valign="top"><p><strong><font size="4">Welcome Employee </font></strong></p>

      <label></label>
      <form name="form1" method="post" action="make_resume.jsp">
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
PreparedStatement ps,ps1,ps2,ps3,ps4;
  ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null;
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");

  String s=(String)session.getAttribute("username") ;

ps=con.prepareStatement("select emp_id,first_name,last_name,middle_name,to_char(dob,'dd/mon/yyyy'),to_char(doj,'dd/mon/yyyy'),salary,address,city,state,country from emp_details where username=?");
ps1=con.prepareStatement("select contact from emp_contact where emp_id=(select emp_id from emp_details where username=?)");
ps.setString(1,s);
ps2=con.prepareStatement("select email from emp_email where emp_id=(select emp_id from emp_details where username=?)");
ps2.setString(1,s);
ps1.setString(1,s);
rs=ps.executeQuery() ;
rs.next();
rs1=ps1.executeQuery() ;
rs1.next();
rs2=ps2.executeQuery() ;
rs2.next();
ps3=con.prepareStatement("select qualifications from emp_qual where emp_id=(select emp_id from emp_details where username=?)");
ps3.setString(1,s);
rs3=ps3.executeQuery() ;
rs3.next();
ps4=con.prepareStatement("select specializations from emp_spec where emp_id=(select emp_id from emp_details where username=?)");
ps4.setString(1,s);
rs4=ps4.executeQuery() ;
rs4.next();
//out.println(rs4.getString(1)) ;
//out.println(rs4.getString(1)) ;


}
catch(Exception e)
        {
    out.println(e);
    }
          %>
          <tr>
            <td nowrap><font color="#990000">Employee ID </font></td>
            <td colspan="4">
              <label>
              <input name="textfield3" type="text" id="textfield3" value="<%out.println(rs.getString(1));%>" readonly>
              </label>            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>First Name</td>
            <td colspan="4"><input type="text" name="textfield4" value="<%out.println(rs.getString(2));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Last Name </td>
            <td colspan="4"><input type="text" name="textfield5"value="<%out.println(rs.getString(3));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Middle Name </td>
            <td colspan="4"><input type="text" name="textfield6"value="<%out.println(rs.getString(4));%>"></td>
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
            <td nowrap>Date Of Joining </td>
            <td colspan="2"><label>
              <input name="textfield10" type="text" id="textfield10" value="<%out.println(rs.getString(6));%>"readonly>
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Qualifications</td>
            <td colspan="2"><label>
              <select name="select4">
            <%String ss= rs3.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>
                  <%rs3.next();%>
                <option value="B.E">B.E</option>
                <option value="M.E">M.E</option>
                <option value="M.C.A">M.C.A</option>
                <option value="DIPLOMA">DIPLOMA</option>
                <option value="PHD">PHD</option>
              </select>
            </label></td>
            <td><select name="select5">
              <%ss= rs3.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>
                  <%rs3.next();%>

                <option value="B.E">B.E</option>
                <option value="M.E">M.E</option>
                <option value="M.C.A">M.C.A</option>
                <option value="DIPLOMA">DIPLOMA</option>
                <option value="PHD">PHD</option>
            </select></td>
            <td>&nbsp;</td>
            <td><p><select name="select6">
               <% ss= rs3.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>



                <option value="B.E">B.E</option>
                <option value="M.E">M.E</option>
                <option value="M.C.A">M.C.A</option>
                <option value="DIPLOMA">DIPLOMA</option>
                <option value="PHD">PHD</option>
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

               <option value="C">C</option>
                <option value="C++">C++</option>
                <option value="JAVA">JAVA</option>
                <option value="VB">VB</option>
                <option value=".NET">.NET</option>
                <option value="ASP">ASP</option>
              </select>
            </label></td>
            <td>&nbsp;</td>
            <td><select name="select8">
               <% ss= rs4.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>
                  <%rs4.next();%>

         <option value="C">C</option>
                <option value="C++">C++</option>
                <option value="JAVA">JAVA</option>
                <option value="VB">VB</option>
                <option value=".NET">.NET</option>
                <option value="ASP">ASP</option>
            </select></td>
            <td>&nbsp;</td>
            <td><select name="select9">
 <% ss= rs4.getString(1);%> <option value="<%out.println(ss);%>" selected><%out.println(ss);%></option>

                <option value="C">C</option>
                <option value="C++">C++</option>
                <option value="JAVA">JAVA</option>
                <option value="VB">VB</option>
                <option value=".NET">.NET</option>
                <option value="ASP">ASP</option>
            </select></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Salary</td>
            <td colspan="4"><input type="text" name="textfield76"value="<%out.println(rs.getString(7));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Address</td>
            <td colspan="4"><input type="text" name="textfield73"value="<%out.println(rs.getString(8));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>City</td>
            <td colspan="4"><input type="text" name="textfield72"value="<%out.println(rs.getString(9));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>State</td>
            <td colspan="4"><input type="text" name="textfield74"value="<%out.println(rs.getString(10));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Country</td>
            <td colspan="4"><input type="text" name="textfield75"value="<%out.println(rs.getString(11));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Contact Number </td>
            <td colspan="4"><label>
              <input type="text" name="textfield7"value="<%out.println(rs1.getString(1));%>">
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Email id </td>
            <td colspan="4"><label>
              <input type="text" name="textfield8"value="<%out.println(rs2.getString(1));%>">
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
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
            <td colspan="4" align="center"><input type="submit" name="Submit" value="Make Resume"></td>
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
 <script language="JavaScript" type="text/javascript">
                    function DoCustomValidation()
{
  var frm = document.forms["form1"];

  if(false == DoMyValidationOne())
  {

    return false;
  }
  else
  return true ;
}
  function DoMyValidationOne()
  {
       var frm = document.forms["form1"];
       if(frm.select4.value=="NULL"&&frm.select5.value=="NULL"&&frm.select6.value=="NULL")
           {
            sfm_show_error_msg('Please select at least one qualification!');
               return false ;
           }
           else if(frm.select7.value=="NULL"&&frm.select8.value=="NULL"&&frm.select9.value=="NULL")
               {

               sfm_show_error_msg('Please select at least one specialization!');
               return false ;
               }

                else if(!(frm.select5.value=="NULL" &&frm.select6.value=="NULL"))
                {
                    if((frm.select4.value==frm.select5.value&frm.select4!="NULL")||(frm.select4.value==frm.select6.value&frm.select4!="NULL")||(frm.select5.value==frm.select6.value&frm.select5!="NULL"))

               {
                   sfm_show_error_msg('Two qualification fields cannot have same value!');
                   return false ;
               }
                }
               else if(!(frm.select8.value=="NULL" &&frm.select9.value=="NULL"))
                   {
               if((frm.select7.value==frm.select8.value&frm.select7!="NULL")||(frm.select7.value==frm.select9.value&frm.select7!="NULL")||(frm.select8.value==frm.select9.value&frm.select7!="NULL"))
               {
                   sfm_show_error_msg('Two specialization fields cannot have same value!');
                   return false ;
               }
                   }
               else
                   {
                       return true ;
                   }
  }
 var frmvalidator = new Validator("form1");
 frmvalidator.setAddnlValidationFunction("DoCustomValidation");
  frmvalidator.addValidation("textfield4","req","Enter All The Fields");
 frmvalidator.addValidation("textfield4","maxlen=20",
"Max length for textfield4 is 20");
 frmvalidator.addValidation("textfield4","alpha","invalid first name");
 frmvalidator.addValidation("textfield6","alpha","invalid last name");
 frmvalidator.addValidation("textfield5","req","Enter All The Fields");
 frmvalidator.addValidation("textfield5","maxlen=20");
 frmvalidator.addValidation("textfield4","maxlen=20");
 frmvalidator.addValidation("textfield6","maxlen=20");
 frmvalidator.addValidation("textfield5","alpha","invalid middle name");
 frmvalidator.addValidation("textfield76","req","Enter All The Fields");
  frmvalidator.addValidation("textfield76","numeric","invalid salary field");
 frmvalidator.addValidation("textfield73","req","Enter All The Fields");
 frmvalidator.addValidation("textfield73","alpha","invalid address");
 frmvalidator.addValidation("textfield72","req","Enter All The Fields");
 frmvalidator.addValidation("textfield72","alpha","invalid city field");
 frmvalidator.addValidation("textfield74","req","Enter All The Fields");
 frmvalidator.addValidation("textfield74","alpha","invalid state");
 frmvalidator.addValidation("textfield75","req","Enter All The Fields");
 frmvalidator.addValidation("textfield75","alpha","invalid country field");
 frmvalidator.addValidation("textfield7","req","Enter All The Fields");
 frmvalidator.addValidation("textfield7","numeric","invalid contact number");
 frmvalidator.addValidation("textfield8","req","Enter All The Fields");
 frmvalidator.addValidation("textfield8","email","invalid email id");
 </script>

</body>

</html>
