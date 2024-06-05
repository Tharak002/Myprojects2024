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
<form method="get" action="Password.jsp">
		<center>
			Customer UserName : 
			<input type="text" name="username" /> <br/><br/>
			<input type="submit" value="Reset Password" />
		</center>
	</form>
	<c:if test="${param.username!=null}">
		<jsp:useBean id="beanDao" class="com.java.agent.AgentDaoImpl" />
		<c:set var="customer" value="${beanDao.searchByUserName(param.username)}" />
		<c:if test="${agent!=null}">
			<jsp:forward page="ChangePassword.jsp" />
		</c:if>
	</c:if>
</body>
</html>