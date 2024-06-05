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
<form method="get" action="AddAgent.jsp">
		<center>
			First Name : 
			<input type="text" name="firstName" /> <br/><br/>
			Last Name :
			<input type="text" name="lastName" /> <br/><br/>
			Username : 
			<input type="text" name="username" /> <br/><br/>
			Password : 
			<input type="text" name="password" /> <Br/><br/>
			Email : 
			<input type="text" name="email" /> <Br/><br/>
			City : 
			<input type="text" name="city" /> <Br/><br/>
			<input type="submit" value="Add Agent" />
		</center>
	</form>
	<c:if test="${param.firstName !=null && param.lastName !=null}">
		<jsp:useBean id="beanDao" class="com.java.agent.AgentDaoImpl" />
		<jsp:useBean id="beanAgent" class="com.java.agent.Agent" />
		<jsp:setProperty property="*" name="beanAgent"/>
		<c:out value="${beanDao.addAgentDao(beanAgent)}" />
	</c:if>
</body>
</html>