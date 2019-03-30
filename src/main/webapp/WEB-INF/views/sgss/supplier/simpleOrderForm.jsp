<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>总订单管理管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/supplier/simpleOrder/">总订单管理列表</a></li>
		<li class="active"><a href="${ctx}/supplier/simpleOrder/form?id=${simpleOrder.id}">总订单管理<shiro:hasPermission name="supplier:simpleOrder:edit">${not empty simpleOrder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="supplier:simpleOrder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="simpleOrder" action="${ctx}/supplier/simpleOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单来源：</label>
			<div class="controls">
				<form:select path="sourcetype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('source_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:select path="supplier.id" items="${suppliers}" itemLabel="name" itemValue="id" cssClass="input-xlarge" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">三方订单编号：</label>
			<div class="controls">
				<form:input path="tradeid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="11" class="input-xlarge required "/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">售后状态：</label>
			<div class="controls">
				<form:select path="afterstate" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('aftersales_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('simple_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">快递公司：</label>
			<div class="controls">
				<form:input path="courier" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递单号：</label>
			<div class="controls">
				<form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递费用：</label>
			<div class="controls">
				<form:input path="delivermoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">收件人：</label>
			<div class="controls">
				<form:input path="consignee" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">颜色：</label>
			<div class="controls">
				<form:input path="colour" htmlEscape="false" maxlength="200" class="input-xlarge required "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格尺码：</label>
			<div class="controls">
				<form:input path="spec" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">总价：</label>
			<div class="controls">
				<form:input path="totalmoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>--%>
		<%--<div class="control-group">
			<label class="control-label">是否对账：</label>
			<div class="controls">
				<form:select path="isaccount" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">供货价：</label>
			<div class="controls">
				<form:input path="settlementPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利润：</label>
			<div class="controls">
				<form:input path="profit" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="supplier:simpleOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>