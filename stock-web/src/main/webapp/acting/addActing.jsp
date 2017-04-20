<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<head>
<title>add acting</title>
</head>
<body>
	<shiro:authenticated>
		<form
			action="${pageContext.request.contextPath}/actingContro/createActing"
			method="post">
			<table>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>状态</td>
						<td>是否选座</td>
						<td>演艺名称<span style="color: red">*</span></td>
						<td>演艺描述</td>
					</tr>
					<tr>
						<td><input hidden="hidden" type="text" name="scenicId"
							value="${param.scenicId}" /></td>
						<td><input hidden="hidden" type="text" name="supplierId"
							value="${param.supplierId}" /></td>
						<td><input type="text" name="state" /></td>
						<td><input type="text" name="whetherNeedSeat" /></td>
						<td><input type="text" name="name" /></td>
						<td><input type="text" name="remarks" /></td>
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