<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单售后管理</title>
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
		<li class="active"><a href="${ctx}/order/orderAfterSales/">订单售后列表</a></li>
		<shiro:hasPermission name="order:orderAfterSales:edit"><li><a href="${ctx}/order/orderAfterSales/form">订单售后添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orderAfterSales" action="${ctx}/order/orderAfterSales/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="ordernumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>售后状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('aftersales_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>申请时间</th>
				<th>售后类型</th>
				<th>售后状态</th>
				<th>备注信息</th>
				<shiro:hasPermission name="order:orderAfterSales:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderAfterSales">
			<tr>
				<td><a href="${ctx}/order/orderAfterSales/form?id=${orderAfterSales.id}">
					${orderAfterSales.num}
				</a></td>
				<td>
					${orderAfterSales.ordernumber}
				</td>
				<td>
					${orderAfterSales.user.name}
				</td>
				<td>
					<fmt:formatDate value="${orderAfterSales.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(orderAfterSales.type, 'aftersales_type', '')}
				</td>
				<td>
					${fns:getDictLabel(orderAfterSales.state, 'aftersales_state', '')}
				</td>

				<td>
					${orderAfterSales.remarks}
				</td>
				<shiro:hasPermission name="order:orderAfterSales:edit"><td>
    				<a href="${ctx}/order/orderAfterSales/form?id=${orderAfterSales.id}">修改</a>
					<a href="${ctx}/order/orderAfterSales/delete?id=${orderAfterSales.id}" onclick="return confirmx('确认要删除该订单售后吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>