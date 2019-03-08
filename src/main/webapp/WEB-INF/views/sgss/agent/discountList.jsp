<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>折扣管理</title>
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
		<li class="active"><a href="${ctx}/agent/discount/">折扣列表</a></li>
		<shiro:hasPermission name="agent:discount:edit"><li><a href="${ctx}/agent/discount/form">折扣添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="discount" action="${ctx}/agent/discount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>折扣</th>
				<shiro:hasPermission name="agent:discount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="discount">
			<tr>
				<td><a href="${ctx}/agent/discount/form?id=${discount.id}">
					${discount.name}
				</a></td>
				<td>
					${discount.discount}
				</td>
				<shiro:hasPermission name="agent:discount:edit"><td>
    				<a href="${ctx}/agent/discount/form?id=${discount.id}">修改</a>
					<a href="${ctx}/agent/discount/delete?id=${discount.id}" onclick="return confirmx('确认要删除该折扣吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>