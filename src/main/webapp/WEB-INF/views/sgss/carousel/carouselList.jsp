<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>轮播图管理</title>
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
		<li class="active"><a href="${ctx}/carousel/carousel/">轮播图列表</a></li>
		<shiro:hasPermission name="carousel:carousel:edit"><li><a href="${ctx}/carousel/carousel/form">轮播图添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="carousel" action="${ctx}/carousel/carousel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品</th>
				<th>主图</th>
				<th>序号</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="carousel:carousel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="carousel">
			<tr>
				<td><a href="${ctx}/carousel/carousel/form?id=${carousel.id}">
					${carousel.goodsId}
				</a></td>
				<td>
					<img src="${carousel.logo}" width="60px" />

				</td>
				<td>
					${carousel.sort}
				</td>
				<td>
					<fmt:formatDate value="${carousel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${carousel.remarks}
				</td>
				<shiro:hasPermission name="carousel:carousel:edit"><td>
    				<a href="${ctx}/carousel/carousel/form?id=${carousel.id}">修改</a>
					<a href="${ctx}/carousel/carousel/delete?id=${carousel.id}" onclick="return confirmx('确认要删除该轮播图吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>