<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>add acting</title>
</head>
<body>
	<shiro:authenticated>
		<form
			action="${pageContext.request.contextPath}/apContro/createActProduct"
			method="post">
			<table align="center" border="1">
				<tbody>
					<tr>
						<td align="center">演艺</td>
						<td align="center">区域</span></td>
						<td align="center">场次</td>
					</tr>
					<tr>
						<td><select name="actingId">
								<option></option>
								<c:if test="${actingProduct != null }">
									<c:if test="${actingProduct.actings != null }">
										<c:forEach items="${actingProduct.actings}" var="ap">
											<option value="${ap.id }">${ap.name }</option>
										</c:forEach>
									</c:if>
								</c:if>
						</select></td>
						<td width="200"><c:if test="${actingProduct != null }">
								<c:if test="${actingProduct.areas != null }">
									<c:forEach items="${actingProduct.areas}" varStatus="status"
										var="area">
									<input hidden="true" checked="checked"
											type="checkbox" name="areas[${status.index}].name"
											value="${area.name}" />
										<input type="checkbox" name="areas[${status.index}].id"
											value="${area.id}" />
											${area.name}<br>
									</c:forEach>
								</c:if>
							</c:if></td>
						<td width="200"><c:if test="${actingProduct != null }">
								<c:if test="${actingProduct.screeingses != null }">
									<c:forEach items="${actingProduct.screeingses}"
										varStatus="status" var="screeings">
									<input hidden="true" checked="checked"
											type="checkbox" name="screeingses[${status.index}].name"
											value="${screeings.name}" />
										<input type="checkbox" name="screeingses[${status.index}].id"
											value="${screeings.id}" />
											${screeings.name}<br>
									</c:forEach>
								</c:if>
							</c:if></td>


					</tr>
					<tr>
						<td colspan="3" align="center"><input type="reset" value="重置" />
							<input type="submit" value="提交" /></td>
					</tr>
					<tr>
						<td colspan="3" align="center"><h5>如场次、区域不满足现有需求，可进行如下创建操作</h5></td>
					</tr>
					<tr>
						<td colspan="3" align="center"><a
							href="${pageContext.request.contextPath}/acting/addActing.jsp?scenicId=${actingProduct.scenicId}&supplierId=${actingProduct.supplierId}">
								创建演艺</a> <a
							href="${pageContext.request.contextPath}/acting/addArea.jsp?scenicId=${actingProduct.scenicId}">
								创建区域</a> <a
							href="${pageContext.request.contextPath}/acting/addSeatChart.jsp?scenicId=${actingProduct.scenicId}">
								添加座位</a> <a
							href="${pageContext.request.contextPath}/acting/addScreeings.jsp?scenicId=${actingProduct.scenicId}">
								创建场次</a></td>
					</tr>
				</tbody>
			</table>
		</form>
	</shiro:authenticated>
</body>
</html>