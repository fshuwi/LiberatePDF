<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liberate PDF: Echo</title>
</head>
<body>

	<h1>Echo!</h1>
	<form action="${pageContext.request.contextPath}/echo">
		<input type="text" name="name"> <input type="submit"
			value="echo!">
	</form>

</body>
</html>