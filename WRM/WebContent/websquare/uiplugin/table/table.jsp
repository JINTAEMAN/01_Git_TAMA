<%@ page contentType="text/html;charset=UTF-8"
language="java"
errorPage=""
import="java.lang.*"
%><%
	
	StringBuffer sb = new StringBuffer();
	sb.append("<div>");
	sb.append("<table>");
	for ( int i = 0 ; i < 1000 ; i++ ){
		sb.append("<tr>");
		for( int j = 0 ; j < 100 ; j++){
			sb.append("<td style='wdith:30px'>");
			sb.append( i+"_"+j );
			sb.append("</td>");
		}
		sb.append("</tr>");
	}
	sb.append("</table>");
	sb.append("</div>");
	out.println( sb.toString() );
%>
