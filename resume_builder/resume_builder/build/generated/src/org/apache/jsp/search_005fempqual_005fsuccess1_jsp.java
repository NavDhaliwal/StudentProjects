package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class search_005fempqual_005fsuccess1_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\n");
      out.write("<meta name=\"GENERATOR\" content=\"Microsoft FrontPage 4.0\">\n");
      out.write("<meta name=\"ProgId\" content=\"FrontPage.Editor.Document\">\n");
      out.write("<title>resumebuilder.com</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body bgcolor=\"#FFFFFF\" text=\"#990000\" leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n");
      out.write(" ");

        Connection con;
PreparedStatement ps=null,ps1=null,ps2=null;
String s=null ;
s=request.getParameter("select4");

if(s.equals("NULL"))
    s="%" ;
//out.println(s) ;
  String s1=null ;
  s1=request.getParameter("select");
 if(s1.equals("NULL"))
    s1="%" ;
  //out.println(s1) ;
  String s2=null ;
  s2=request.getParameter("select2");
  if(s2.equals("NULL"))

    s2="%" ;
    //out.println(s2) ;
  ResultSet rs=null,rs1=null,rs2=null;
try{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 		con=DriverManager.getConnection("jdbc:odbc:pro","system","agent47");
         ps=con.prepareStatement("select emp_id,qualifications from emp_qual where qualifications like ?");
          ps1=con.prepareStatement("select emp_id,qualifications from emp_qual where qualifications like ?");
           ps2=con.prepareStatement("select emp_id,qualifications from emp_qual where qualifications like ?");
       ps.setString(1,s);
        rs=ps.executeQuery();
ps1.setString(1,s1);
        rs1=ps1.executeQuery() ;
        /*while(rs.next())
            {
            String id1=rs.getString(1) ;
            while(rs1.next())
                {
                String id2=rs1.getString(1);
                while(rs2.next())
                    {

                if(id1.equals(id2))
                    {
                    out.println(id1);
                    }
                }
                }
            rs1.close() ;
            rs1=ps1.executeQuery() ;
            //out.println(rs.getString(1));
            //out.println(rs.getString(2));
            } */
      
        ps2.setString(1,s2);
        rs2=ps2.executeQuery() ;
       // rs.next();
       // out.println(rs.getString(1));
   }
catch(Exception e)
        {
    out.println("////////////////////////////////////////"+e);
    e.printStackTrace();
    }

        
      out.write("\n");
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
      out.write("    <td height=\"134\" align=\"center\" valign=\"top\"><table width=\"660\" border=\"0\">\n");
      out.write("      <tr>\n");
      out.write("        <td width=\"131\">Employee ID </td>\n");
      out.write("        <td width=\"132\">Qualification1</td>\n");
      out.write("        <td width=\"132\">Qualification2</td>\n");
      out.write("        <td width=\"132\">Qualification3</td>\n");
      out.write("\n");
      out.write("      </tr>\n");
      out.write("\t   ");

      String id1,id2,id3 ;
                 String qual1,qual2,qual3 ;

	  while(rs.next())
	  {
          id1=rs.getString(1);
                 qual1=rs.getString(2) ;
        while(rs1.next())
            {
            id2=rs1.getString(1);
                 qual2=rs1.getString(2) ;

            while(rs2.next())
                {
                 
                 
                   id3=rs2.getString(1);
                 qual3=rs2.getString(2) ;
                 int flag=1;
                 if(id1==null||id2==null||id3==null||qual1==null||qual2==null||qual3==null)
                     flag=0 ;
                 if(id1.equals(id2)&id1.equals(id3)&qual1.equals(qual2)&qual2.equals(qual3))
                     {
                     
      out.write("\n");
      out.write("                     <tr>\n");
      out.write("                     <td>");
out.println(id1);
      out.write("</td>\n");
      out.write("                     <td>");
out.println(qual1);
      out.write("</td>\n");
      out.write("                     <td>");
out.println(qual2);
      out.write("</td>\n");
      out.write("                     <td>");
out.println(qual3);
      out.write("</td>\n");
      out.write("                     </tr>\n");
      out.write("               ");
 }
                 }
           rs2.close() ;
           rs2=ps2.executeQuery() ;

            }
       rs1.close();
       rs1=ps1.executeQuery() ;
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
      out.write("    <td align=\"center\" valign=\"top\"><form name=\"form1\" method=\"post\" action=\"search_empid_success.jsp\">\n");
      out.write("       <table width=\"245\" border=\"0\">\n");
      out.write("        <tr>\n");
      out.write("          <td width=\"92\" height=\"56\" align=\"left\">Employee ID </td>\n");
      out.write("          <td width=\"153\"><label>\n");
      out.write("            <input name=\"textfield\" type=\"text\" id=\"textfield\">\n");
      out.write("          </label></td>\n");
      out.write("        </tr>\n");
      out.write("      </table>\n");
      out.write("      <p>\n");
      out.write("        <label>\n");
      out.write("        <input type=\"submit\" name=\"Submit\" value=\"Show Details\">\n");
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
      out.write("</body>\n");
      out.write("\n");
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
