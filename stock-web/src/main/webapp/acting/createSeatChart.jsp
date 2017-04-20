<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加座位图信息</title>
		<script type="text/javascript" src="jquery-1.7.2.js"></script>
	</head>
<body>
	<form method = "post" action="${pageContext.request.contextPath}/seatChartContro/addSeatChart">
		<table>
			<tr><th colspan="2">添加座位图</th></tr>
			<tr><td>景区名称：</td><td><input type="text" name="scenicName"/><input type="button" onclick="initArea()" value="获取区域"/></td></tr>
			<tr><th colspan="2"><br/></th></tr>
			<tr><td>选择区域信息：</td><td id="areaCheckSign"></td></tr>
			<tr><th colspan="2"><br/></th></tr>
			<tr><td>排序号：</td><td><input type="text" name="sort"/></td></tr>
			<tr><th colspan="2"><br/></th></tr>
			<tr><td>座位图描述(不超过16个字,例：B区单号)：</td><td><input type="text" name="code"/></td></tr>
			<tr><th colspan="2"><br/></th></tr>
			<tr><td>座位图信息：</td><td><textarea name="seatChart" cols="100" rows="20"></textarea></td></tr>
			<tr><th colspan="2"><br/></th></tr>
			<tr><td colspan="2" align="center"><input type="submit" value="提交"/></td></tr>
		</table>
	</form>
	<script type="text/javascript">
		function initArea(){
			var scenicName = $("input[name=scenicName]").val();
			if(null == scenicName || scenicName == ''){
				return;
			}
			var urlpath = "${pageContext.request.contextPath}/seatChartContro/getAreaList";

			$.ajax({
			    url: urlpath,
			    data:{"scenicName" : scenicName},
			    type: 'post',
			    dataType: 'json',
			    contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			    timeout: 1000,
			    error: function(){
			        alert('Error loading data');
			    },
			    success: function(rd){
			        var json =  rd;
			        var areahtml = "";
			        for(var i = 0;i<json.length;i++){
			        	areahtml += "<input name=\"areaId\" type=\"radio\" value=\""+json[i].areaId+"\" />"+json[i].areaName+"&nbsp;&nbsp;";
			        }
			        $("#areaCheckSign").html(areahtml);
			    }
			});
		}
	</script>
</body>
</html>