package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class search_005femp_005fname_005fsuccess_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("   \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body bgcolor=\"#FFFFFF\" text=\"#990000\" leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n");
      out.write("<table width=\"100%\" height=\"172\" border=\"0\" cellspacing=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"25%\" height=\"168\" align=\"left\" valign=\"top\" bgcolor=\"#1F41B8\"><img src=\"cap.png\" width=\"248\" height=\"161\"></td>\n");
      out.write("    <td width=\"75%\" valign=\"middle\" bgcolor=\"#1F41B8\"><font size=\"7\">RESUME BUILDER </font></td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("<table border=\"0\" width=\"100%\" bgcolor=\"#000000\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"100%\"><font color=\"#FFFFFF\" face=\"Arial\" size=\"2\"><b>WWW.RESUMEBUILDER.COM</b></font></td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" background=\"./JobSearchNewspaper.jpg\">\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"18%\" height=\"380\" valign=\"top\">&nbsp;\n");
      out.write("      <p style=\"margin-left: 20\"><b><font face=\"Arial\" size=\"2\" color=\"#000000\">LINK\n");
      out.write("      1<br>\n");
      out.write("      LINK 2<br>\n");
      out.write("      LINK 3<br>\n");
      out.write("      LINK 4<br>\n");
      out.write("      LINK 5<br>\n");
      out.write("      LINK 6</font></b></p>\n");
      out.write("      <p>&nbsp;</p>\n");
      out.write("      <p>&nbsp; </p>\n");
      out.write("      <p>&nbsp;</p>\n");
      out.write("      <p>a </p>\n");
      out.write("    <p></td>\n");
      out.write("    <td width=\"60%\" valign=\"top\"><p><font size=\"4\"><strong>Enter Employee Details</strong></font></p>\n");
      out.write("\n");
      out.write("      <label></label>\n");
      out.write("      <form name=\"form1\" method=\"post\" action=\"edit_emp1_success.jsp\">\n");
      out.write("        <table width=\"281\" border=\"0\" align=\"center\" bordercolor=\"#F0F0F0\">\n");
      out.write("          <tr>\n");
      out.write("            <td width=\"117\" nowrap>&nbsp;</td>\n");
      out.write("            <td width=\"52\" colspan=\"4\"><label></label></td>\n");
      out.write("            <td width=\"24\">&nbsp;</td>\n");
      out.write("            <td width=\"12\">&nbsp;</td>\n");
      out.write("            <td width=\"23\">&nbsp;</td>\n");
      out.write("          </tr>\n");
      out.write("          <tr>\n");
      out.write("            <td nowrap>&nbsp;</td>\n");
      out.write("            <td colspan=\"4\">&nbsp;</td>\n");
      out.write("            <td>&nbsp;</td>\n");
      out.write("            <td>&nbsp;</td>\n");
      out.write("            <td>&nbsp;</td>\n");
      out.write("          </tr>\n");
      out.write("        ");

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
         ps=con.prepareStatement("select emp_id,first_name,last_name,middle_name from emp_details where lower(first_name) =? or lower(last_name)= ? or lower(middle_name) =?");
        ps.setString(1,s);
        ps.setString(2,s1);
        ps.setString(3,s2);
         rs=ps.executeQuery() ;
   }
catch(Exception e)
        {
    out.println(e);
    }
        
      out.write("\n");
      out.write("   <table width=\"100%\" height=\"172\" border=\"0\" cellspacing=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"25%\" height=\"168\" align=\"left\" valign=\"top\" bgcolor=\"#1F41B8\"><img src=\"cap.png\" width=\"248\" height=\"161\"></td>\n");
      out.write("    <td width=\"75%\" valign=\"middle\" bgcolor=\"#1F41B8\"><font size=\"7\">RESUME BUILDER </font></td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("<table border=\"0\" width=\"100%\" bgcolor=\"#000000\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"100%\"><font color=\"#FFFFFF\" face=\"Arial\" size=\"2\"><b>WWW.RESUMEBUILDER.COM</b></font></td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" background=\"./JobSearchNewspaper.jpg\">\n");
      out.write("  <tr>\n");
      out.write("    <td valign=\"top\">&nbsp;</td>\n");
      out.write("    <td width=\"65%\" align=\"center\" valign=\"middle\"><strong><font size=\"4\">Edit Employee </font></strong>\n");
      out.write("    <td colspan=\"2\">&nbsp;</td>\n");
      out.write("  </tr>\n");
      out.write("\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"15%\" height=\"380\" rowspan=\"2\" valign=\"top\">&nbsp;\n");
      out.write("      <p style=\"margin-left: 20\"><b><font face=\"Arial\" size=\"2\" color=\"#000000\">LINK\n");
      out.write("      1<br>\n");
      out.write("      LINK 2<br>\n");
      out.write("      LINK 3<br>\n");
      out.write("      LINK 4<br>\n");
      out.write("      LINK 5<br>\n");
      out.write("      LINK 6</font></b></p>\n");
      out.write("      <p>&nbsp;</p>\n");
      out.write("      <p>&nbsp; </p>\n");
      out.write("      <p>&nbsp;</p>\n");
      out.write("      <p>&nbsp; </p>\n");
      out.write("    <p></td>\n");
      out.write("    <td height=\"134\" align=\"center\" valign=\"top\"><table width=\"582\" border=\"0\">\n");
      out.write("      <tr>\n");
      out.write("        <td width=\"131\">Employee ID </td>\n");
      out.write("        <td width=\"132\">Name</td>\n");
      out.write("      </tr>\n");
      out.write("\t  ");

	  while(rs.next())
	  {
          String id=null,name=null ;
	  id=rs.getString(1) ;
	   name=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)  ;
	  
      out.write("\n");
      out.write("\t  <tr>\n");
      out.write("        <td>");
out.println(id);
      out.write("</td>\n");
      out.write("        <td>");
out.println(name);
      out.write("</td>\n");
      out.write("      </tr>\n");
      out.write("\t  ");
}
	  
      out.write("\n");
      out.write("    </table>\n");
      out.write("\n");
      out.write("    <td width=\"4%\" rowspan=\"2\">\n");
      out.write("        <p>&nbsp; </p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("      <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("        <p>&nbsp;</p>\n");
      out.write("    </blockquote>      </td>\n");
      out.write("    <td width=\"16%\" rowspan=\"2\" valign=\"top\">\n");
      out.write("      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("        <tr>\n");
      out.write("          <td width=\"100%\" bgcolor=\"#FFFFFF\"><b><font face=\"Arial\" size=\"2\" color=\"#990000\">SIDE\n");
      out.write("            HEADING 1</font></b></td>\n");
      out.write("        </tr>\n");
      out.write("      </table>\n");
      out.write("      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("        <tr>\n");
      out.write("          <td width=\"100%\"><br>\n");
      out.write("            <font size=\"2\" face=\"Arial\">This is the place for your news or other\n");
      out.write("            site information. Another good use for this space is to display\n");
      out.write("            advertisements.</font>\n");
      out.write("            <p><font size=\"2\" face=\"Arial\">This is the place for your news or\n");
      out.write("            other site information. Another good use for this space is to\n");
      out.write("            display advertisements.<br>\n");
      out.write("          &nbsp;</font></td>\n");
      out.write("        </tr>\n");
      out.write("      </table>\n");
      out.write("      <table border=\"0\" width=\"100%\" bgcolor=\"#008080\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("        <tr>\n");
      out.write("          <td width=\"100%\" bgcolor=\"#FFFFFF\"><b><font face=\"Arial\" size=\"2\" color=\"#990000\">SIDE\n");
      out.write("            HEADING 2</font></b></td>\n");
      out.write("        </tr>\n");
      out.write("      </table>\n");
      out.write("      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("        <tr>\n");
      out.write("          <td width=\"100%\"><br>\n");
      out.write("            <font size=\"2\" face=\"Arial\">This is the place for your news or other\n");
      out.write("            site information. Another good use for this space is to display\n");
      out.write("            advertisements.</font>\n");
      out.write("            <p><font size=\"2\" face=\"Arial\">This is the place for your news or\n");
      out.write("            other site information. Another good use for this space is to\n");
      out.write("            display advertisements<br>\n");
      out.write("          &nbsp;</font></td>\n");
      out.write("        </tr>\n");
      out.write("    </table>    </td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td align=\"center\" valign=\"top\"><form name=\"form1\" method=\"post\" action=\"edit_emp1.jsp\">\n");
      out.write("       <table width=\"245\" border=\"0\">\n");
      out.write("        <tr>\n");
      out.write("          <td width=\"92\" height=\"56\" align=\"left\">Employee ID </td>\n");
      out.write("          <td width=\"153\"><label>\n");
      out.write("            <input type=\"text\" name=\"textfield12\">\n");
      out.write("          </label></td>\n");
      out.write("        </tr>\n");
      out.write("      </table>\n");
      out.write("      <p>\n");
      out.write("        <label>\n");
      out.write("        <input type=\"submit\" name=\"Submit\" value=\"Edit\">\n");
      out.write("        </label>\n");
      out.write("      </p>\n");
      out.write("    </form>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("<table border=\"0\" width=\"100%\" bgcolor=\"#000000\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td width=\"100%\"><font size=\"1\">&nbsp;</font></td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
