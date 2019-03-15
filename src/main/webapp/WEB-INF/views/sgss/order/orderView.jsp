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
	</ul><br/>
	<form:form id="inputForm" modelAttribute="order" action="${ctx}/order/order/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="11"  readonly="true" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单编号：</label>
			<div class="controls">
				<form:input path="ordernumber" htmlEscape="false"  readonly="true" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人电话：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false"  readonly="true" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人：</label>
			<div class="controls">
				<form:input path="consignee" htmlEscape="false"  readonly="true" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false"  readonly="true" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户：</label>
			<div class="controls">
				<%--<form:input path="user.id" htmlEscape="false" maxlength="200" class="input-xlarge "/>--%>
				<form:input path="user.name" htmlEscape="false"  readonly="true"  maxlength="200" class="input-xlarge "/>
			<%--	<sys:treeselect id="user" name="user.id" value="${order.user.id}" labelName="user.name" labelValue="${order.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			--%></div>
		</div>
		<div class="control-group">
			<label class="control-label">付款时间：</label>
			<div class="controls">
				<input name="payTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下单时间：</label>
			<div class="controls">
				<input name="orderTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge " disabled="true">
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
					 />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货单号：</label>
			<div class="controls">
				<form:input path="invoiceNo" htmlEscape="false"  readonly="true" maxlength="50" class="input-xlarge "/>
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
				<form:input path="goodsPrice" htmlEscape="false"  readonly="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">运费：</label>
			<div class="controls">
				<form:input path="freight" htmlEscape="false"  readonly="true" class="input-xlarge "/>
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
				<form:input path="totalPrice" htmlEscape="false"  readonly="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付方式：</label>
			<div class="controls">
				<form:select path="payType" class="input-xlarge " disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠券：</label>
			<div class="controls">
				<form:hidden path="coupon.id"/>
				<form:input path="coupon.name" htmlEscape="false" maxlength="32" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">完成时间：</label>
			<div class="controls">
				<input name="completeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${order.completeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					 />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" readonly="true" rows="4" maxlength="100" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">订单明细：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>商品名称</th>
								<th>货号</th>
								<th>商品主图</th>
								<th>规格1</th>
								<th>规格2</th>
								<th>本店售价</th>
								<th>数量</th>
								<th>备注信息</th>
								<th>分销折扣</th>
								<th>分销金额</th>
								<%--<shiro:hasPermission name="order:order:edit"><th width="10">&nbsp;</th></shiro:hasPermission>--%>
							</tr>
						</thead>
						<tbody id="orderDetailList">
						</tbody>

					</table>
					<script type="text/template" id="orderDetailTpl">//<!--
						<tr id="orderDetailList{{idx}}">
							<td class="hide">

							</td>

							<td tilte="{{row.name}}">
							 {{row.name}}
							</td>
							<td>
								 {{row.artno}}
							</td>
							<td>
							 <input id="orderDetailList{{idx}}logo" name="orderDetailList[{{idx}}].logo" type="hidden" value="{{row.logo}}"/>
                            <sys:ckfinder input="orderDetailList{{idx}}logo" type="images" uploadPath="/goods/goods" selectMultiple="false" readonly="true" maxWidth="60" maxHeight="60"/>
							</td>
							<td>
								 {{row.spec1}}
							</td>
							<td>
								 {{row.spec2}}
							</td>
							<td>
								 {{row.price}}
							</td>
							<td>
								 {{row.number}}
							</td>
							<td>
								  {{row.remarks}}
							</td>
							<td>
								  {{row.discount}}
							</td>
							<td>
								  {{row.agentMoney}}
							</td>

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
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>