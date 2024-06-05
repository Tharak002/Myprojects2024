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
     <p>Change Password Page in Construction</p>
	 <form method="post" action="ChangePassword.jsp">
		<center>
			Agent Id : 
			<input type="number" name="agentId" value="${sessionScope.agentId}" readonly="readonly"/>
			<br/><br/>
			Auth Code : 
			<input type="text" name="authCode" /> <br/><br/>
			New Password : 
			<input type="password" name="pwd" /> <br/><br/>
			Confirm Password : 
			<input type="password" name="cpwd" /> <br/><br/>
			<input type="submit" value="Update Password" />
		</center>
	</form>
	<c:if test="${param.agentId!=null}">
		<c:if test="${param.pwd == param.cpwd}">
			<jsp:useBean id="beanDao" class="com.java.agent.AgentDaoImpl" />
			<c:out value="${beanDao.changePassword(param.agentId,param.pwd,param.authCode)}" />
		</c:if>
	</c:if>
</body>
</html>