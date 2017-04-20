<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>

	<h1>Welcome to piaozhijia</h1>
	<shiro:authenticated>
		<h5>选择需要为哪个供应商，在哪个景区下创建演艺产品</h5>
		<form
			action="${pageContext.request.contextPath}/apContro/queryInfoForCreate"
			method="get">
			供应商名称： 景区名称： <input type="submit" value="create">
		</form>
	</shiro:authenticated>
</html>