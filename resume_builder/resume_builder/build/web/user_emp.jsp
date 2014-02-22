<%@page import="java.sql.*"%>
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

<%
          Connection con;
PreparedStatement ps,ps1,ps2,ps3,ps4,ps5;
boolean test=false;
  ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs4=null,rs5=null;
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");

  String s=null ;
  s=request.getParameter("textfield");
  ps=con.prepareStatement("select emp_id from emp_details");
 ps1=con.prepareStatement("select emp_id,first_name,last_name,middle_name,to_char(dob,'dd'),to_char(dob,'mon'),to_char(dob,'yyyy')from emp_details where emp_id=?");
ps2=con.prepareStatement("select contact from emp_contact where emp_id=?");
ps1.setString(1,s);
ps3=con.prepareStatement("select email from emp_email where emp_id=?");
ps4=con.prepareStatement("select qualifications from emp_qual where emp_id=?");
ps5=con.prepareStatement("select specializations from emp_spec where emp_id=?");


ps1.setString(1,s);
ps2.setString(1,s);
ps3.setString(1,s);
ps4.setString(1,s);
ps5.setString(1,s);
   rs=ps.executeQuery();
   rs1=ps1.executeQuery();
   rs2=ps2.executeQuery();
   rs3=ps3.executeQuery();
   rs4=ps4.executeQuery();
   rs5=ps5.executeQuery();
   rs1.next();
   rs2.next();
   rs3.next();
   rs4.next();
   rs5.next();
   while(rs.next())
            {
           String val= rs.getString(1);
            if(val!=null && val.equals(s))
                    test=true ;
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
      <p>&nbsp; </p>
    <p></td>
    <td width="60%" valign="top">
      <blockquote>
      <p><br>
        <strong>Employee Registration Form        </strong></p>
		<form name="form1" method="post" action="user_reg_success.jsp">
		  <table width="281" border="0" align="center" bordercolor="#F0F0F0">
         <% if(test)
       {%>
          <tr>
            <td width="117" nowrap><font color="#990000">Username</font></td>
            <td width="52" colspan="6"><label>
              <input name="textfield" type="text" value="">
            </label></td>
            <td width="24">&nbsp;</td>
            <td width="12">&nbsp;</td>
            <td width="23">&nbsp;</td>
          </tr>
          <tr>
            <td nowrap><font color="#990000">Password</font></td>
            <td colspan="6"><input type="password" name="textfield2"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap><font color="#990000">Confirm Password </font></td>
            <td colspan="6"><input type="password" name="textfield3"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>First Name</td>
            <td colspan="6"><input type="text" name="textfield4" value="<%out.println(rs1.getString(2));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Last Name </td>
            <td colspan="6"><input type="text" name="textfield5"value="<%out.println(rs1.getString(3));%>"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Middle Name </td>
            <td colspan="6"><input type="text" name="textfield6"value="<%out.println(rs1.getString(4));%>"></td>
            <td>&nbsp;</td>
            <td colspan="6">
            
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Date Of Birth </td>
            <td><label>
              <select name="select" size="1">
                <option value="<%out.println(rs1.getString(4));%>"><%out.println(rs1.getString(4));%></option>
               
              </select>
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td><select name="select2" size="1">
              <option value="<%out.println(rs1.getString(5));%>"><%out.println(rs1.getString(5));%></option>
            
            </select></td>
            <td><select name="select3" size="1">
              
                  <option value="<%out.println(rs1.getString(6));%>"><%out.println(rs1.getString(6));%></option>
                  
            </select></td>
            <td>&nbsp;</td>
            <td><label></label></td>
            <td>
              <label></label>            </td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Qualifications</td>
            <td colspan="4"><label>
              <select name="select4">
                <option value="<%out.println(rs4.getString(1));%>"><%out.println(rs4.getString(1));%></option>
                <%rs4.next();%>
              </select>
            </label></td>
            <td><select name="select5">
              <option value="<%out.println(rs4.getString(1));%>"><%out.println(rs4.getString(1));%></option>
              <%rs4.next();%>
            </select></td>
            <td>&nbsp;</td>
            <td><select name="select6">
              <option value="<%out.println(rs4.getString(1));%>"><%out.println(rs4.getString(1));%></option>

              </select></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Specializations</td>
            <td colspan="3"><label>
            <select name="select7">
              <option value="<%out.println(rs5.getString(1));%>"><%out.println(rs5.getString(1));%></option>
              <%rs4.next();%>
              </select>
            </label></td>
            <td>&nbsp;</td>
            <td><select name="select8">
              <option value="<%out.println(rs5.getString(1));%>"><%out.println(rs5.getString(1));%></option>
              <%rs4.next();%>

            </select></td>
            <td>&nbsp;</td>
            <td><select name="select9">
              <option value="<%out.println(rs5.getString(1));%>"><%out.println(rs5.getString(1));%></option>
         
            </select></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Contact Number </td>
            <td colspan="6">
              <input type="text" name="textfield7" value="<%out.println(rs2.getString(1));%>">
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Email id </td>
            <td colspan="6">
              <input type="text" name="textfield8" value="<%out.println(rs3.getString(1));%>">
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>&nbsp;</td>
            <td colspan="6" align="center"><label></label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>&nbsp;</td>
            <td colspan="6">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>&nbsp;</td>
            <td colspan="6" align="center"><input type="submit" name="Submit" value="Submit"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>&nbsp;</td>
            <td colspan="6">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table><%}}
catch(Exception e)
        {
    out.println(e) ;
    }%>
        <%if(!test){%>

		  <table width="200" border="0" align="center">
            <tr>
              <td align="center">Employee ID Not Found!! </td>
            </tr>
            <tr>
              <td align="center"><a href="enter_empid.jsp"><font color="#990000">Please Try Different ID</font></a> </td>
            </tr>
          </table>
          <%}%>
		</form>
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
       
      </table>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
        
      </table>
      <table border="0" width="100%" bgcolor="#008080" cellspacing="0" cellpadding="0">
        
      </table>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
       
      </table>    </td>
  </tr>
</table>
<table border="0" width="100%" bgcolor="#000000" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%"><font size="1">&nbsp;</font></td>
  </tr>
</table>

<%if(test){%>
          <script language="JavaScript" type="text/javascript">
              function DoCustomValidation()
{
  var frm = document.forms["form1"];
  if(frm.textfield2.value != frm.textfield3.value)
  {
    sfm_show_error_msg('The Password and Confirm password don not match!',frm.textfield2);
    return false;
  }
  else if(false == DoMyValidationOne())
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
 frmvalidator.addValidation("textfield","alpha","invalid username");
 frmvalidator.addValidation("textfield2","alpha","invalid password(enter alphanumeric)");
frmvalidator.addValidation("textfield3","alpha","invalid confirm password field");
  frmvalidator.addValidation("textfield2","maxlen=20");
   frmvalidator.addValidation("textfield","maxlen=20");
   frmvalidator.addValidation("textfield3","maxlen=20");
  frmvalidator.addValidation("textfield4","req","Enter All The Fields");
 frmvalidator.addValidation("textfield4","maxlen=20",
"Max length for textfield4 is 20");
 frmvalidator.addValidation("textfield4","alpha","invalid first name");
 frmvalidator.addValidation("textfield5","alpha","invalid last name");
 frmvalidator.addValidation("textfield5","req","Enter All The Fields");
 frmvalidator.addValidation("textfield5","maxlen=20");
 frmvalidator.addValidation("textfield4","maxlen=20");
 frmvalidator.addValidation("textfield6","maxlen=20");
 frmvalidator.addValidation("textfield6","alpha","invalid middle name");
 frmvalidator.addValidation("textfield7","req","Enter All The Fields");
 frmvalidator.addValidation("textfield7","numeric","invalid contact number");
 frmvalidator.addValidation("textfield8","req","Enter All The Fields");
 frmvalidator.addValidation("textfield8","email","invalid email id");
 </script>
 <%}%>

</body>

</html>
