<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>团购订单管理</title>
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
		<li class="active"><a href="${ctx}/tuan/dlTuanOrder/">团购订单列表</a></li>
		<shiro:hasPermission name="tuan:dlTuanOrder:edit"><li><a href="${ctx}/tuan/dlTuanOrder/form">团购订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dlTuanOrder" action="${ctx}/tuan/dlTuanOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>会员电话：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>货号：</label>
				<form:input path="artno" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>会员信息</th>
				<th>收货信息</th>
				<th>商品名称</th>
				<th>货号</th>
				<th>数量</th>

				<th>下单时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="tuan:dlTuanOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dlTuanOrder">
			<tr>
				<td>
						${dlTuanOrder.ordernumber}
				</td>
				<td><a href="${ctx}/tuan/dlTuanOrder/form?id=${dlTuanOrder.id}">
					${dlTuanOrder.username}，${dlTuanOrder.mobile}
				</a></td>
				<td>
						${dlTuanOrder.consignee}，${dlTuanOrder.phone}，， ${dlTuanOrder.address}
				</td>
				<td>
					${dlTuanOrder.name}
				</td>
				<td>
					${dlTuanOrder.artno}，${dlTuanOrder.spec1}，${dlTuanOrder.spec2}
				</td>
				<td>
					${dlTuanOrder.number}
				</td>

				<td>
					<fmt:formatDate value="${dlTuanOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${dlTuanOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dlTuanOrder.remarks}
				</td>
				<shiro:hasPermission name="tuan:dlTuanOrder:edit"><td>
    				<a href="${ctx}/tuan/dlTuanOrder/form?id=${dlTuanOrder.id}">修改</a>
					<a href="${ctx}/tuan/dlTuanOrder/delete?id=${dlTuanOrder.id}" onclick="return confirmx('确认要删除该团购订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>