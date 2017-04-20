<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<head>
<title>add area</title>
</head>
<body>
	<shiro:authenticated>
		<form
			action="${pageContext.request.contextPath}/areaContro/createArea"
			method="post">
			<table>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td>区域名称<span style="color: red">*</span></td>
						<td>区域编码<span style="color: red">*</span></td>
					</tr>
					<tr>
						<td><input hidden="hidden" type="text" name="scenicId"
							value="${param.scenicId}" /></td>
						<td><input type="text" name="name" /></td>
						<td><input type="text" name="code" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="reset" value="重置"></td>
						<td colspan="2"><input type="submit" value="提交"></td>
					</tr>
				</tbody>
			</table>
		</form>
</body>
</shiro:authenticated>
</html>