package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class delete_005femp_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("<meta name=\"GENERATOR\" content=\"Microsoft FrontPage 4.0\">\r\n");
      out.write("<meta name=\"ProgId\" content=\"FrontPage.Editor.Document\">\r\n");
      out.write("<title>resumebuilder.com</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body bgcolor=\"#FFFFFF\" text=\"#990000\" leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\r\n");

Connection con;
PreparedStatement ps=null;
  ResultSet rs=null;
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");
		ps=con.prepareStatement("select emp_id,first_name,middle_name,last_name from emp_details");
		rs=ps.executeQuery() ;
        }
catch(Exception e)
        {
    out.println(e);
    }

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
      out.write("    <td width=\"65%\" align=\"center\" valign=\"middle\"><strong><font size=\"4\">Delete Employee </font></strong>\r\n");
      out.write("    <td colspan=\"2\">&nbsp;</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"15%\" height=\"380\" rowspan=\"2\" valign=\"top\">&nbsp;\r\n");
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
      out.write("    <td height=\"134\" align=\"center\" valign=\"top\"><table width=\"582\" border=\"0\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td width=\"131\">Employee ID </td>\r\n");
      out.write("        <td width=\"132\">Name</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("\t  ");

	  while(rs.next())
	  {
          String id=null,name=null ;
	  id=rs.getString(1) ;
	   name=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)  ;
	  
      out.write("\r\n");
      out.write("\t  <tr>\r\n");
      out.write("        <td>");
out.println(id);
      out.write("</td>\r\n");
      out.write("        <td>");
out.println(name);
      out.write("</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("\t  ");
}
	  
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("\r\n");
      out.write("    <td width=\"4%\" rowspan=\"2\">\r\n");
      out.write("        <p>&nbsp; </p>\r\n");
      out.write("        <p>&nbsp;</p>\r\n");
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
      out.write("    <td width=\"16%\" rowspan=\"2\" valign=\"top\">\r\n");
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
      out.write("  <tr>\r\n");
      out.write("    <td align=\"center\" valign=\"top\"><form name=\"form1\" method=\"post\" action=\"delete_emp_success.jsp\">\r\n");
      out.write("       <table width=\"245\" border=\"0\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"92\" height=\"56\" align=\"left\">Employee ID </td>\r\n");
      out.write("          <td width=\"153\"><label>\r\n");
      out.write("            <input type=\"text\" name=\"textfield12\">\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("      <p>\r\n");
      out.write("        <label>\r\n");
      out.write("        <input type=\"submit\" name=\"Submit\" value=\"Delete\">\r\n");
      out.write("        </label>\r\n");
      out.write("      </p>\r\n");
      out.write("    </form>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("<table border=\"0\" width=\"100%\" bgcolor=\"#000000\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"100%\"><font size=\"1\">&nbsp;</font></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
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
