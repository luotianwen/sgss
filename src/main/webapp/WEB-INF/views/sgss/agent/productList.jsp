<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
		<li class="active"><a href="${ctx}/agent/product/">商品列表</a></li>
		<shiro:hasPermission name="agent:product:edit"><li><a href="${ctx}/agent/product/form">商品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/agent/product/" method="post" class="breadcrumb form-search">
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
				<th>商品货号</th>
				<th>商品描述信息</th>
				<th>商品类别(鞋,服,配)</th>
				<th>商品上市季节</th>
				<th>品牌</th>
				<th>性别</th>
				<th>商品颜色</th>
				<th>市场价</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>销售价</th>
				<shiro:hasPermission name="agent:product:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td><a href="${ctx}/agent/product/form?id=${product.id}">
					${product.articleno}
				</a></td>
				<td>
					${product.descr}
				</td>
				<td>
					${product.division}
				</td>
				<td>
					${product.quarter}
				</td>
				<td>
					${product.brandname}
				</td>
				<td>
					${product.sex}
				</td>
				<td>
					${product.colour}
				</td>
				<td>
					${product.marketprice}
				</td>
				<td>
					<fmt:formatDate value="${product.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${product.remarks}
				</td>
				<td>
					${product.price}
				</td>
				<shiro:hasPermission name="agent:product:edit"><td>
    				<a href="${ctx}/agent/product/form?id=${product.id}">修改</a>
					<a href="${ctx}/agent/product/delete?id=${product.id}" onclick="return confirmx('确认要删除该商品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>