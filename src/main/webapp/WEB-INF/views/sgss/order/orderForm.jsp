<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理管理</title>
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
		<li><a href="${ctx}/order/order/">订单管理列表</a></li>
		<li class="active"><a href="${ctx}/order/order/form?id=${order.id}">订单管理<shiro:hasPermission name="order:order:edit">${not empty order.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="order:order:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="order" action="${ctx}/order/order/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单编号：</label>
			<div class="controls">
				<form:input path="ordernumber" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人电话：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人：</label>
			<div class="controls">
				<form:input path="consignee" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${order.user.id}" labelName="user.name" labelValue="${order.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款时间：</label>
			<div class="controls">
				<input name="payTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下单时间：</label>
			<div class="controls">
				<input name="orderTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货时间：</label>
			<div class="controls">
				<input name="deliveryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${order.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货单号：</label>
			<div class="controls">
				<form:input path="invoiceNo" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递公司：</label>
			<div class="controls">
				<form:input path="expressName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品总价：</label>
			<div class="controls">
				<form:input path="goodsPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">运费：</label>
			<div class="controls">
				<form:input path="freight" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠价格：</label>
			<div class="controls">
				<form:input path="favorablePrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总计：</label>
			<div class="controls">
				<form:input path="totalPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付方式：</label>
			<div class="controls">
				<form:select path="payType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠券id：</label>
			<div class="controls">
				<form:input path="couponId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">完成时间：</label>
			<div class="controls">
				<input name="completeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${order.completeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="100" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">订单明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>商品id</th>
								<th>skuid</th>
								<th>商品名称</th>
								<th>货号</th>
								<th>商品主图</th>
								<th>规格1</th>
								<th>规格2</th>
								<th>本店售价</th>
								<th>数量</th>
								<th>备注信息</th>
								<shiro:hasPermission name="order:order:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="orderDetailList">
						</tbody>
						<shiro:hasPermission name="order:order:edit"><tfoot>
							<tr><td colspan="12"><a href="javascript:" onclick="addRow('#orderDetailList', orderDetailRowIdx, orderDetailTpl);orderDetailRowIdx = orderDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="orderDetailTpl">//<!--
						<tr id="orderDetailList{{idx}}">
							<td class="hide">
								<input id="orderDetailList{{idx}}_id" name="orderDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="orderDetailList{{idx}}_delFlag" name="orderDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_goods" name="orderDetailList[{{idx}}].goods.id" type="text" value="{{row.goods.id}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_sku" name="orderDetailList[{{idx}}].sku.id" type="text" value="{{row.sku.id}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_name" name="orderDetailList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_artno" name="orderDetailList[{{idx}}].artno" type="text" value="{{row.artno}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_logo" name="orderDetailList[{{idx}}].logo" type="text" value="{{row.logo}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_spec1" name="orderDetailList[{{idx}}].spec1" type="text" value="{{row.spec1}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_spec2" name="orderDetailList[{{idx}}].spec2" type="text" value="{{row.spec2}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_price" name="orderDetailList[{{idx}}].price" type="text" value="{{row.price}}" class="input-small "/>
							</td>
							<td>
								<input id="orderDetailList{{idx}}_number" name="orderDetailList[{{idx}}].number" type="text" value="{{row.number}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<textarea id="orderDetailList{{idx}}_remarks" name="orderDetailList[{{idx}}].remarks" rows="4" maxlength="100" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="order:order:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#orderDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var orderDetailRowIdx = 0, orderDetailTpl = $("#orderDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(order.orderDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#orderDetailList', orderDetailRowIdx, orderDetailTpl, data[i]);
								orderDetailRowIdx = orderDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="order:order:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>