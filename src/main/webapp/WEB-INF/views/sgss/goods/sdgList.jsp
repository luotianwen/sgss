<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>动力折扣管理</title>
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
		<li class="active"><a href="${ctx}/goods/sdg/">动力折扣列表</a></li>
		<shiro:hasPermission name="goods:sdg:edit"><li><a href="${ctx}/goods/sdg/form">动力折扣添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sdg" action="${ctx}/goods/sdg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>货号：</label>
				<form:input path="artno" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>折扣：</label>
				<form:input path="discount" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>货号</th>
				<th>折扣</th>
				<shiro:hasPermission name="goods:sdg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sdg">
			<tr>
				<td><a href="${ctx}/goods/sdg/form?id=${sdg.id}">
					${sdg.artno}
				</a></td>
				<td>
					${sdg.discount}
				</td>
				<shiro:hasPermission name="goods:sdg:edit"><td>
    				<a href="${ctx}/goods/sdg/form?id=${sdg.id}">修改</a>
					<a href="${ctx}/goods/sdg/delete?id=${sdg.id}" onclick="return confirmx('确认要删除该动力折扣吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>