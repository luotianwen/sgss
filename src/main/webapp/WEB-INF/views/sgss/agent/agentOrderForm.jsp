<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现申请管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/agent/agentOrder/">提现申请列表</a></li>
		<li class="active"><a href="${ctx}/agent/agentOrder/form?id=${agentOrder.id}">提现申请<shiro:hasPermission name="agent:agentOrder:edit">${not empty agentOrder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="agent:agentOrder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="agentOrder" action="${ctx}/agent/agentOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="agent.id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提现状态：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提现金额：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理：</label>
			<div class="controls">
				<form:input path="agent.name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">提现明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>订单号</th>
								<th>金额</th>
							</tr>
						</thead>
						<tbody id="agentOrderDetailList">
						</tbody>

					</table>
					<script type="text/template" id="agentOrderDetailTpl">//<!--
						<tr id="agentOrderDetailList{{idx}}">
							<td class="hide">
								<input id="agentOrderDetailList{{idx}}_id" name="agentOrderDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="agentOrderDetailList{{idx}}_delFlag" name="agentOrderDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="agentOrderDetailList{{idx}}_ordernumber" name="agentOrderDetailList[{{idx}}].ordernumber" type="text" readonly value="{{row.ordernumber}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<input id="agentOrderDetailList{{idx}}_money" name="agentOrderDetailList[{{idx}}].money" type="text" value="{{row.money}}" class="input-small "/>
							</td>

						</tr>//-->
					</script>
					<script type="text/javascript">
						var agentOrderDetailRowIdx = 0, agentOrderDetailTpl = $("#agentOrderDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(agentOrder.agentOrderDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#agentOrderDetailList', agentOrderDetailRowIdx, agentOrderDetailTpl, data[i]);
								agentOrderDetailRowIdx = agentOrderDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="agent:agentOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>