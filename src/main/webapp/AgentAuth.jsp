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
 <form method="get" action="AgentAuth.jsp">
		<center>
			username : 
			<input type="text" name="username" /> <br/><br/>
			 AuthCode : 
			<input type="text" name="authCode" /> <br/><br/>
			<input type="submit" value="Authenticate" />
		</center>
	</form>
	
	<c:if test="${param.username !=null && param.authCode !=null}">
		<jsp:useBean id="beanDao" class="com.java.agent.AgentDaoImpl" />
		<c:set var="count" value="${beanDao.authenticate(param.username, param.authCode)}" />
		<c:out value="${count}" />
		<c:if test="${count==1}">
		  <jsp:forward page="PasswordReset.jsp" />
		
		</c:if>	
		</c:if>
</body>
</html>