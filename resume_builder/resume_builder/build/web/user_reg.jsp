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
      <p>&nbsp; </p>
    <p></td>
    <td width="60%" valign="top">
      <blockquote>
      <p><br>
        <strong>User Registration Form        </strong></p>
		<form name="form1" method="post" action="user_reg_success.jsp">
        <table width="281" border="0" align="center" bordercolor="#F0F0F0">
          <tr>
            <td width="117" nowrap><font color="#990000">Username</font></td>
            <td width="52" colspan="6"><label>
              <input type="text" name="textfield">
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
            <td colspan="6"><input type="text" name="textfield4"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Last Name </td>
            <td colspan="6"><input type="text" name="textfield5"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Middle Name </td>
            <td colspan="6"><input type="text" name="textfield6"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Date Of Birth </td>
            <td><label>
              <select name="select" size="1">
                <option value="1" selected>1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
              </select>
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td><select name="select2" size="1">
              <option value="JAN">JAN</option>
              <option value="FEB">FEB</option>
              <option value="MAR">MAR</option>
              <option value="APR">APR</option>
              <option value="MAY">MAY</option>
              <option value="JUN">JUN</option>
              <option value="JUL">JUL</option>
              <option value="AUG">AUG</option>
              <option value="SEP">SEP</option>
              <option value="OCT">OCT</option>
              <option value="NOV">NOV</option>
              <option value="DEC">DEC</option>
            </select></td>
            <td><select name="select3" size="1">
              <%for(int i=1900;i<=2010;i++)
                  {%>
                  <option value="<%out.println(i);%>"><%out.println(i);%></option>
                  <%
                  }%>
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
                <option value="NULL">(SELECT)</option>
                <option value="B.E">B.E</option>
                <option value="M.E">M.E</option>
                <option value="M.C.A">M.C.A</option>
                <option value="DIPLOMA">DIPLOMA</option>
                <option value="PHD">PHD</option>
              </select>
            </label></td>
            <td><select name="select5">
              <option value="NULL">(SELECT)</option>
              <option value="B.E">B.E</option>
              <option value="M.E">M.E</option>
              <option value="M.C.A">M.C.A</option>
              <option value="DIPLOMA">DIPLOMA</option>
              <option value="PHD">PHD</option>
            </select></td>
            <td>&nbsp;</td>
            <td><select name="select6">
              <option value="NULL">(SELECT)</option>
              <option value="B.E">B.E</option>
              <option value="M.E">M.E</option>
              <option value="M.C.A">M.C.A</option>
              <option value="DIPLOMA">DIPLOMA</option>
              <option value="PHD">PHD</option>
            </select></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Specializations</td>
            <td colspan="3"><label>
            <select name="select7">
              <option value="NULL">(SELECT)</option>
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
              <option value="NULL">(SELECT)</option>
              <option value="C">C</option>
              <option value="C++">C++</option>
              <option value="JAVA">JAVA</option>
              <option value="VB">VB</option>
              <option value=".NET">.NET</option>
              <option value="ASP">ASP</option>
            </select></td>
            <td>&nbsp;</td>
            <td><select name="select9">
              <option value="NULL">(SELECT)</option>
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
            <td nowrap>Contact Number </td>
            <td colspan="6"><label>
              <input type="text" name="textfield7">
            </label></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td nowrap>Email id </td>
            <td colspan="6"><label>
              <input type="text" name="textfield8">
            </label></td>
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
        </table>
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
 frmvalidator.addValidation("textfield","alnum","invalid username");
 frmvalidator.addValidation("textfield2","alnum","invalid password(enter alphanumeric)");
frmvalidator.addValidation("textfield3","alnum","invalid confirm password field");
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

</body>

</html>
