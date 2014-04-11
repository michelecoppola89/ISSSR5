<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MacroService</title>
</head>
<body bgcolor="e3d268">

	<h1 align="center" style="color:#F20000">MacroService Manual</h1>

	<c:choose>
	
		<c:when test="${!list_ms.isEmpty()}">
	
			<c:forEach var="ms" items="${list_ms}">
	
				<hr width="100%">
			
				<h2 style="color:#069">${ms.idCode}</h2>

				<li><h3>Description</h3></li>
					${ms.description}
		
				<li><h3>Number of operand</h3></li>
					${ms.numOperand}
		
				<li><h3>Keywords</h3></li>
				<c:forEach var="elem" items="${ms.keywords}">
					<c:out value="${elem}"></c:out>
				</c:forEach>
		
			</c:forEach>
		</c:when>
		
		<c:when test="${list_ms.isEmpty()}">
				<h2>No MacroService Found</h2>
		</c:when>
	
	</c:choose>

</body>
</html>