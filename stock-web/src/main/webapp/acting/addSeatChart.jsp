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
			action="${pageContext.request.contextPath}/seatChartContro/createSeatChart"
			method="post">
			<table>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td>区域ID<span style="color: red">*</span></td>
						<td>排序</td>
						<td>座位图<span style="color: red">*</span></td>
					</tr>
					<tr>
						<td><input hidden="hidden" type="text" name="scenicId"
							value="${param.scenicId}" /></td>
						<td><input type="text" name="areaId" /></td>
						<td><input type="text" name="sort" /></td>
						<td><input type="text" name="seatMaps" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="reset" value="重置"></td>
						<td colspan="2"><input type="submit" value="提交"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</shiro:authenticated>
</body>
</html>