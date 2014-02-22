package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class edit_005femp1_005fsuccess_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<body>\r\n");

Connection con;
    PreparedStatement ps=null,ps1=null,ps2=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null,ps8=null,ps9=null,ps10=null,ps11=null,ps12=null,ps13=null,ps14=null,ps15=null,ps16=null,ps17=null,ps18=null,ps19=null;
    //ResultSet rs8=null,rs9=null,rs10=null,rs11=null,rs12=null,rs13=null;
String a;
int p=0;
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
ps2=con.prepareStatement("update emp_details set middle_name=s4 where emp_id=s1");
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
ps14=con.prepareStatement("insert into emp_qual values(?,?)");
ps14.setString(1,s1);
ps14.setString(2,s7);
int result3=ps14.executeUpdate();
ps15.setString(1,s1);
ps15.setString(2,s8);
int result4=ps15.executeUpdate();
ps16.setString(1,s1);
ps16.setString(2,s9);
int result5=ps16.executeUpdate();

ps13=con.prepareStatement("delete from emp_spec where emp_id=?");
ps13.setString(1,s1);
int rs13=ps13.executeUpdate();
ps17=con.prepareStatement("insert into emp_spec values(?,?)");
ps17.setString(1,s1);
ps17.setString(2,s10);
int result6=ps17.executeUpdate();
ps18.setString(1,s1);
ps18.setString(2,s11);
int result7=ps18.executeUpdate();
ps19.setString(1,s1);
ps19.setString(2,s12);
int result8=ps19.executeUpdate();




       
if(rs==1&&rs1==1&&rs2==1&&rs3==1&&rs4==1&&rs5==1&&rs6==1&&rs7==1&&rs8==1&&rs10==1&&result==1 && result1==1 && result3==1 && result4==1 && result5==1 && result6==1 && result7==1 && result8==1)
            {
            out.println("EMPLOYEE UPDATED");
 
      out.write("\r\n");
      out.write("             <p>&nbsp;</p>\r\n");
      out.write(" ");

            //out.println("USERNAME:"+s3);
	//out.println("REGISTRATION NO:"+a);
        
        }
else
{
out.println("EMPLOYEE NOT UPDATED");
 
      out.write("\r\n");
      out.write("             <p>&nbsp;</p>\r\n");
      out.write(" ");

            out.println("FORM INCOMPLETE OR USERNAME ALREADY EXSISTS:");

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
