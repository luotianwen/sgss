<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>售后退款日志管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/orderAfterSalesLog/">售后退款日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderAfterSalesLog" action="${ctx}/order/orderAfterSalesLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="ordernumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>微信退款单号：</label>
				<form:input path="refundId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>商户系统内部的退款单号：</label>
				<form:input path="outRefundNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>退款时间</th>
				<th>退款金额</th>
				<th>备注信息</th>
				<th>微信退款单号</th>
				<th>商户系统内部的退款单号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderAfterSalesLog">
			<tr>
				<td><a href="${ctx}/order/orderAfterSalesLog/form?id=${orderAfterSalesLog.id}">
					${orderAfterSalesLog.ordernumber}
				</a></td>
				<td>
						${orderAfterSalesLog.returnAmount/100}元
				</td>
				<td>
					<fmt:formatDate value="${orderAfterSalesLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${orderAfterSalesLog.remarks}
				</td>
				<td>
					${orderAfterSalesLog.refundId}
				</td>
				<td>
					${orderAfterSalesLog.outRefundNo}
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>