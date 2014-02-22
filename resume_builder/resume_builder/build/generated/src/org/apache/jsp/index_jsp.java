package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<table width=\"100%\" height=\"172\" border=\"0\" cellspacing=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td width=\"25%\" height=\"168\" align=\"left\" valign=\"top\" bgcolor=\"#1F41B8\"><img src=\"cap.png\" width=\"248\" height=\"161\"></td>\r\n");
      out.write("    <td width=\"75%\" valign=\"middle\"^ bgcolor=\"#1F41B8\"><font size=\"7\">RESUME BUILDER </font></td>\r\n");
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
      out.write("    <td align=\"left\" valign=\"middle\"><strong>User/Administrator Login </strong><blockquote>  \r\n");
      out.write("    <td align=\"right\" valign=\"middle\"><a href=\"user_reg.jsp\"><font color=\"#990000\">New User?Sign Up!</font></a>\r\n");
      out.write("    <td colspan=\"2\">&nbsp;</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  \r\n");
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
      out.write("    <td width=\"58%\" colspan=\"2\" align=\"right\" valign=\"top\"><form name=\"form1\" method=\"post\" action=\"main_success.jsp\">\r\n");
      out.write("        <table width=\"249\" border=\"0\" align=\"center\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"61\">Username</td>\r\n");
      out.write("            <td colspan=\"2\"><label>\r\n");
      out.write("              <input type=\"text\" name=\"textfield\">\r\n");
      out.write("            </label></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td>Password</td>\r\n");
      out.write("            <td colspan=\"2\"><label>\r\n");
      out.write("              <input type=\"password\" name=\"textfield2\">\r\n");
      out.write("            </label></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td>&nbsp;</td>\r\n");
      out.write("            <td colspan=\"2\">&nbsp;</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td>&nbsp;</td>\r\n");
      out.write("            <td width=\"76\"><p>\r\n");
      out.write("              <label></label>\r\n");
      out.write("              <label>\r\n");
      out.write("              <input type=\"submit\" name=\"Submit\" value=\"Login\">\r\n");
      out.write("              </label>\r\n");
      out.write("            </p>            </td>\r\n");
      out.write("            <td width=\"98\"><input type=\"reset\" name=\"Submit2\" value=\"Reset\"></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("      </form>\r\n");
      out.write("      <p><br>\r\n");
      out.write("      </p>\r\n");
      out.write("    <td width=\"11%\">\r\n");
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
