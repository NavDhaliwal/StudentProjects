package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class user_005freg_005fsuccess_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.apache.jasper.runtime.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n");
      out.write("   \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>USER_REGISTERED</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("    <body bgcolor=\"#FFFFFF\" text=\"#990000\" leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" height=\"172\" border=\"0\" cellspacing=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"25%\" height=\"168\" align=\"left\" valign=\"top\" bgcolor=\"#1F41B8\"><img src=\"cap.png\" width=\"248\" height=\"161\"></td>\r\n");
      out.write("    <td width=\"75%\" valign=\"middle\" bgcolor=\"#1F41B8\"><font size=\"7\">RESUME BUILDER </font></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("<table border=\"0\" width=\"100%\" bgcolor=\"#000000\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"100%\"><font color=\"#FFFFFF\" face=\"Arial\" size=\"2\"><b>WWW.RESUMEBUILDER.COM</b></font></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" background=\"./JobSearchNewspaper.jpg\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"top\">&nbsp;</td>\r\n");
      out.write("    <td align=\"left\" valign=\"middle\">\r\n");
      out.write("\r\n");
      out.write("    <td align=\"right\" valign=\"middle\"><a href=\"user_reg.htm\"></a>\r\n");
      out.write("    <td colspan=\"2\">&nbsp;</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"15%\" height=\"380\" valign=\"top\">&nbsp;\r\n");
      out.write("      <p style=\"margin-left: 20\"><b><font face=\"Arial\" size=\"2\" color=\"#000000\">LINK\r\n");
      out.write("      1<br>\r\n");
      out.write("      LINK 2<br>\r\n");
      out.write("      LINK 3<br>\r\n");
      out.write("      LINK 4<br>\r\n");
      out.write("      LINK 5<br>\r\n");
      out.write("      LINK 6</font></b></p>\r\n");
      out.write("      <p>&nbsp;</p>\r\n");
      out.write("      <p>&nbsp; </p>\r\n");
      out.write("      <p>&nbsp;</p>\r\n");
      out.write("      <p>&nbsp; </p>\r\n");
      out.write("    <p></td>\r\n");
      out.write("    <td width=\"58%\" colspan=\"2\" align=\"right\" valign=\"top\"><form name=\"form1\" method=\"post\" action=\"admin_home.htm\">\r\n");
      out.write("    </form>\r\n");
      out.write("      <p><br>\r\n");
      out.write("      </p>\r\n");
      out.write("    <td width=\"11%\">\r\n");
      out.write("        <p>&nbsp; </p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        ");

Connection con;
    PreparedStatement ps,ps1,ps2,ps3,ps4;
    ResultSet rs;
String s1=request.getParameter("textfield");
	String s2=request.getParameter("textfield2");
String s3=request.getParameter("textfield3");
String s4=request.getParameter("textfield4");
String s5=request.getParameter("textfield5");
String s6=request.getParameter("textfield6");
String s7=request.getParameter("select");
String s8=request.getParameter("select2");
String s9=request.getParameter("select3");
String s10=request.getParameter("select4");
String s11=request.getParameter("select5");
String s12=request.getParameter("select6");
String s13=request.getParameter("select7");
String s14=request.getParameter("select8");
String s15=request.getParameter("select9");
String s16=request.getParameter("textfield7");
String s17=request.getParameter("textfield8");
String dob=s7+"/"+s8+"/"+s9;
try{
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");

        ps=con.prepareStatement("insert into new_user values(?,?,?,?,?,to_date(?,'dd/mon/yyyy'))");
        ps.setString(1,s1);
	ps.setString(2,s2);
	ps.setString(3,s4);
	ps.setString(4,s5);
	ps.setString(5,s6);
        ps.setString(6,dob);
int result=ps.executeUpdate();
ps1=con.prepareStatement("insert into user_email values(?,?)");
ps1.setString(1,s1);
ps1.setString(2,s17);
int result1=ps1.executeUpdate();
ps4=con.prepareStatement("insert into user_contact values(?,?)");
ps4.setString(1,s1);
ps4.setString(2,s16);
int result2=ps4.executeUpdate();
ps2=con.prepareStatement("insert into user_qual values(?,?)");
ps2.setString(1,s1);
ps2.setString(2,s10);
int result3=ps2.executeUpdate();
ps2.setString(1,s1);
ps2.setString(2,s11);
int result4=ps2.executeUpdate();
ps2.setString(1,s1);
ps2.setString(2,s12);
int result5=ps2.executeUpdate();

ps3=con.prepareStatement("insert into user_spec values(?,?)");
ps3.setString(1,s1);
ps3.setString(2,s13);
int result6=ps3.executeUpdate();
ps3.setString(1,s1);
ps3.setString(2,s14);
int result7=ps3.executeUpdate();
ps3.setString(1,s1);
ps3.setString(2,s15);
int result8=ps3.executeUpdate();
if(result==1 && result1==1 && result2==1 && result3==1 && result4==1 && result5==1 && result6==1 && result7==1 && result8==1)
{
    out.println("USERNAME:"+s1);
    out.println("PASSWORD:"+s2);
    }
else
    {
    out.println("USER NOT REGISTERED");
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



      out.write("\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("      <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
      out.write("    </blockquote>      </td>\r\n");
      out.write("    <td width=\"16%\" valign=\"top\">\r\n");
      out.write("      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"100%\" bgcolor=\"#FFFFFF\"><b><font face=\"Arial\" size=\"2\" color=\"#990000\">SIDE\r\n");
      out.write("            HEADING 1</font></b></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"100%\"><br>\r\n");
      out.write("            <font size=\"2\" face=\"Arial\">This is the place for your news or other\r\n");
      out.write("            site information. Another good use for this space is to display\r\n");
      out.write("            advertisements.</font>\r\n");
      out.write("            <p><font size=\"2\" face=\"Arial\">This is the place for your news or\r\n");
      out.write("            other site information. Another good use for this space is to\r\n");
      out.write("            display advertisements.<br>\r\n");
      out.write("          &nbsp;</font></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("      <table border=\"0\" width=\"100%\" bgcolor=\"#008080\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"100%\" bgcolor=\"#FFFFFF\"><b><font face=\"Arial\" size=\"2\" color=\"#990000\">SIDE\r\n");
      out.write("            HEADING 2</font></b></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"100%\"><br>\r\n");
      out.write("            <font size=\"2\" face=\"Arial\">This is the place for your news or other\r\n");
      out.write("            site information. Another good use for this space is to display\r\n");
      out.write("            advertisements.</font>\r\n");
      out.write("            <p><font size=\"2\" face=\"Arial\">This is the place for your news or\r\n");
      out.write("            other site information. Another good use for this space is to\r\n");
      out.write("            display advertisements<br>\r\n");
      out.write("          &nbsp;</font></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("<table border=\"0\" width=\"100%\" bgcolor=\"#000000\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"100%\"><font size=\"1\">&nbsp;</font></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
