<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <p>Welcome to Password Reset Page</p>
	 <form method="get" action="PasswordReset.jsp">
		<center>
		    username : 
			<input type="text" name="username" value="${param.username}" /> <br/><br/>
		    password : 
			<input type="password" name="password" /> <br/><br/>
			 Reset Password : 
			<input type="password" name="resetPassword" /> <br/><br/>
			<input type="submit" value="Update Password" />
		</center>
	</form>
	<c:if test="${param.username !=null && param.password !=null}">
		<c:if test="${param.password == param.resetPassword}">
			<jsp:useBean id="beanDao" class="com.java.agent.AgentDaoImpl" />
			<c:out value="${beanDao.updatePassword(param.username, param.password)}" />
		</c:if>
	</c:if>
	
</body>
</html>