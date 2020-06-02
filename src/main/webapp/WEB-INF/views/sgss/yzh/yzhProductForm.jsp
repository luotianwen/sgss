<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>云中鹤商品管理管理</title>
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
		<li><a href="${ctx}/yzh/yzhProduct/">云中鹤商品管理列表</a></li>
		<li class="active"><a href="${ctx}/yzh/yzhProduct/form?id=${yzhProduct.id}">云中鹤商品管理<shiro:hasPermission name="yzh:yzhProduct:edit">${not empty yzhProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="yzh:yzhProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="yzhProduct" action="${ctx}/yzh/yzhProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商品id：</label>
			<div class="controls">
				<form:input path="productid" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品缩略图：</label>
			<div class="controls">
				<img src="${yzhProduct.thumbnailimage}" width="80px">
				<form:input path="thumbnailimage" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌：</label>
			<div class="controls">
				<form:input path="brand" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
				<form:input path="productcate" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品型号：</label>
			<div class="controls">
				<form:input path="productcode" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场价：</label>
			<div class="controls">
				<form:input path="marketprice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议价格：</label>
			<div class="controls">
				<form:input path="retailprice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品产地：</label>
			<div class="controls">
				<form:input path="productplace" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品描述信息：</label>
			<div class="controls">
				<form:input path="features" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">热销商品：</label>
			<div class="controls">
				<form:input path="hot" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品创建时间：</label>
			<div class="controls">
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${yzhProduct.createtime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">无理由退货：</label>
			<div class="controls">
				<form:input path="is7toreturn" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">税率：</label>
			<div class="controls">
				<form:input path="tax" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品详情信息：</label>
			<div class="controls">
				<form:textarea path="productDescription" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				<sys:ueditor replace="productDescription"   />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">移动端商品详情信息：</label>
			<div class="controls">
				<form:textarea path="mobileProductDescription" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="yzh:yzhProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>