<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加演艺信息</title>
		<script type="text/javascript" src="jquery-1.7.2.js"></script>
	</head>
<body>

	<form method = "post"  name="creatActingForm" action="${pageContext.request.contextPath}/actingContro/createActingInfo">
		<table width="900px" height="50px" name="actingTable">
			<tr>
				<th colspan="4" align="left">录入演艺基本信息：</th>
			</tr>
			<tr height="30px">
				<td >供应商名称：</td><td><input type="text" name="supplierName"/></td>
				<td >景区名称：</td><td><input type="text" name="scenicName"/></td>
				<td >是否选座：</td>
				<td >
					<input type="radio" name="whetherSeat" value="1" />需要
					<input type="radio" name="whetherSeat" value="2" checked/>不需要
				</td>
			</tr>
		</table>
		<br/>
		<table width="600px" name="areaTable">
			<tr>
				<th colspan="2" align="left">录入区域基本信息：</th>
			</tr>
			<tr>
				<td>区域名称：</td><td><input type="text" name="areaVoList[0].areaName"/></td>
				<td>区域标识(例如:vip)：</td><td><input type="text" name="areaVoList[0].areaSign"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="加个区域" onclick="addArea(this)"/></td>
			</tr>
		</table>
		<br/>
		<table name="screeningsTable">
			<tr>
				<th colspan="5" align="left">录入场次基本信息：时间格式举例（早上9点开演，结束时间12点，停售时间不设置默认为结束时间）：开始：0900  结束：1200  停售:1130</th>
			</tr>
			<tr>
				<td>场次名称：<input type="text" name="screeningsVOList[0].screeningsName"/></td>
				<td>场次标识(例如:1)：<input type="text" name="screeningsVOList[0].screeningsSign"/></td>
				<td>开始时间(时分)：<input type="text" name="screeningsVOList[0].screeningsStart"/></td>
				<td>结束时间(时分)：<input type="text" name="screeningsVOList[0].screeningsEnd"/></td>
				<td>停售时间(时分)：<input type="text" name="screeningsVOList[0].screeningsSaleEnd"/></td>
			</tr>
			<tr>
				<td colspan="5"><input type="button" value="加个场次" onclick="addScreenings(this)"/></td>
			</tr>
			<tr>
				<th colspan="5" align="left"><br/></th>
			</tr>
			<tr>
				<th colspan="5" align="left"><input type="submit" value="添加演艺"/></th>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		var areaCount = 0,screeningsCount = 0;
		function addArea(obj){
			if($("table[name=areaTable]").find("tr").length == 10){
				return;
			}
			areaCount++;
			var areaHtml = 
				"<tr>"+
					"<td>区域名称：</td><td><input type=\"text\" name=\"areaVoList["+areaCount+"].areaName\"/></td>"+
					"<td>区域标识：</td><td><input type=\"text\" name=\"areaVoList["+areaCount+"].areaSign\"/>&nbsp;<a href=javascript:void; onclick=\"del(this)\" >删除</a></td>"+
				"</tr>";
			$(obj).parents("tr").before(areaHtml);
		}
		function addScreenings(obj){
			if($("table[name=screeningsTable]").find("tr").length == 10){
				return;
			}
			screeningsCount++;
			var screeningsHtml = 
				"<tr>"+
					"<td>场次名称： <input type=\"text\" name=\"screeningsVOList["+screeningsCount+"].screeningsName\"/></td>"+
					"<td>场次标识： <input type=\"text\" name=\"screeningsVOList["+screeningsCount+"].screeningsSign\"/></td>"+
					"<td>演出开始时间(时分)： <input type=\"text\" name=\"screeningsVOList["+screeningsCount+"].screeningsStart\"/></td>"+
					"<td>演出结束时间(时分)： <input type=\"text\" name=\"screeningsVOList["+screeningsCount+"].screeningsEnd\"/></td>"+
					"<td>演出停售时间(时分)： <input type=\"text\" name=\"screeningsVOList["+screeningsCount+"].screeningsSaleEnd\"/>&nbsp;<a href=javascript:void; onclick=\"del(this)\" >删除</a></td>"+
				"</tr>";
			$(obj).parents("tr").before(screeningsHtml);
		}
		function del(obj){
			$(obj).parents("tr").remove();
		}
	</script>
</body>
</html>