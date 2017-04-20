<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<head>
<title>add screeings</title>
</head>
<body>
	<shiro:authenticated>
		<form
			action="${pageContext.request.contextPath}/screeinsContro/createScreeings"
			method="post">
			<table>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td>演艺标识<span style="color: red">*</span></td>
						<td>场次名称<span style="color: red">*</span></td>
						<td>演出时间<span style="color: red">*</span></td>
						<td>结束时间<span style="color: red">*</span></td>
						<td>停售时间<span style="color: red">*</span></td>
					</tr>
					<tr>
						<td><input hidden="hidden" type="text" name="scenicId"
							value="${param.scenicId}" /></td>
						<td><input type="text" name="code" /></td>
						<td><input type="text" name="name" /></td>
						<td><input type="text" name="startTime" /></td>
						<td><input type="text" name="endTime" /></td>
						<td><input type="text" name="endSaleTime" /></td>
					</tr>
					<tr>
						<td colspan="3"><input type="reset" value="重置"></td>
						<td colspan="3"><input type="submit" value="提交"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</shiro:authenticated>
</body>
</html>