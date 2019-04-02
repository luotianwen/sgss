<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
       function de(id){
           layer.open({
               type: 1,
               shadeClose: true,
               content:"<div  class='form-search' style='padding:20px;text-align:center;'>快递公司：<input type='text' id='backcourier2' name='backcourier'/> </br>快递单号：<input type='text' id='backnumber2' name='backnumber'/></br>快递费用：<input type='text' id='backmoney2' name='backmoney'/></div>",
               btn: ['确定', '取消']
               , yes: function (index, f) {
                   var backcourier=$("#backcourier2").val();
                   var backnumber=$("#backnumber2").val();
                   var backmoney=$("#backmoney2").val();
                   if (backcourier == '') {
                       top.$.jBox.tip("请输入快递公司。", 'error');
                       return false;
                   }
                   if (backnumber == '') {
                       top.$.jBox.tip("请输入快递单号。", 'error');
                       return false;
                   }
                   if (backmoney == '') {
                       top.$.jBox.tip("请输入快递费用。", 'error');
                       return false;
                   }
                   resetTip(); //loading();
                   var url =  "${ctx}/order/order/fast?id="+id+"&expressName=" + encodeURIComponent(backcourier) + "&invoiceNo=" + encodeURIComponent(backnumber) + "&freight=" + encodeURIComponent(backmoney);
                   $.ajax({
                       type : "post",
                       async : false,
                       url : url,
                       success : function(msg) {
                           if(msg=='ok')
                           {
                               top.$.jBox.tip("发货成功。", 'success');
                               $("#searchForm").submit();
                               return true;
                           }
                           else{
                               top.$.jBox.tip("发货失败。", 'error');

                               return false;
                           }
                       },
                       error : function(json) {
                           top.$.jBox.tip("网络异常。", 'error');
                           return false;
                       }
                   });
               }
               , btn2: function (index, layero) {
                   //按钮【按钮二】的回调
                   layer.close(index);
                   return false;
                   //return false 开启该代码可禁止点击该按钮关闭
               }
           });
           return false;
       };
	</script>
</head>

<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/order/">订单管理列表</a></li>
		<%--<shiro:hasPermission name="order:order:edit"><li><a href="${ctx}/order/order/form">订单管理添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/order/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>序号：</label>
				<form:input path="num" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>订单编号：</label>
				<form:input path="ordernumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>收货人电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>快递单号：</label>
				<form:input path="invoiceNo" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>收货人：</label>
				<form:input path="consignee" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>订单编号</th>
				<th>用户</th>
				 <th>收货人</th>
				<th class="sort-column state">订单状态</th>
				<th>支付方式</th>
				<th>总计</th>
				<th>快递信息</th>
				<th class="sort-column orderTime" >下单时间</th>
				<th class="sort-column payTime">付款时间</th>
				<th class="sort-column deliveryTime" >发货时间</th>
				<th>支付交易号</th>
				<th>是否售后</th>
				<th>备注信息</th>
				<shiro:hasPermission name="order:order:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td><a href="${ctx}/order/order/form?id=${order.id}">
					${order.num}
				</a></td>
				<td>
					${order.ordernumber}
				</td>
				<td>
						${order.user.name}
				</td>
				<td>
						${order.consignee} ${order.phone} ${order.address}
				</td>


				<td>
					${fns:getDictLabel(order.state, 'order_state', '')}
				</td>


				<td>
					${fns:getDictLabel(order.payType, 'pay_type', '')}
				</td>
				<td>
						${order.totalPrice}
				</td>
				<td>
						${order.expressName} ${order.invoiceNo} ${order.freight}
				</td>
				<td>
					<fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<td>
					<fmt:formatDate value="${order.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${order.outTradeNo}
				</td>
				<td>

						${fns:getDictLabel(order.refundState, 'yes_no', '')}
				</td>
				<td>
					${order.remarks}
				</td>
				<shiro:hasPermission name="order:order:edit"><td>
    				<a href="${ctx}/order/order/form?id=${order.id}">修改</a>
					<a href="${ctx}/order/order/view?id=${order.id}">查看</a>
					<c:if test="${order.state==20}">
                    <a href="javascript:void(0);" onclick="de('${order.id}')">发货</a>
					</c:if>

                    <c:if test="${order.refundState==1}">
					    <a href="${ctx}/order/orderAfterSales/?ordernumber=${order.ordernumber}" >售后信息</a>
                        <a href="${ctx}/order/orderAfterSalesLog/?ordernumber=${order.ordernumber}">退款信息</a>
                    </c:if>
                    <c:if test="${order.refundState==0}">
                    <a href="${ctx}/order/order/after?id=${order.id}" onclick="return confirmx('确认要申请售后吗？', this.href)">申请售后</a>
                    </c:if>
                    <a href="${ctx}/order/order/delete?id=${order.id}" onclick="return confirmx('确认要删除该订单管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>