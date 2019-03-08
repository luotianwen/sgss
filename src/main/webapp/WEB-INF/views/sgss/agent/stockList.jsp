<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存管理</title>
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
		<li class="active"><a href="${ctx}/agent/stock/">库存列表</a></li>
		<shiro:hasPermission name="agent:stock:edit"><li><a href="${ctx}/agent/stock/form">库存添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="stock" action="${ctx}/agent/stock/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:input path="brandname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品货号</th>
				<th>商品类别(鞋,服,配)</th>
				<th>品牌</th>
				<th>尺码2</th>
				<th>尺码1</th>
				<th>库存数量</th>
				<th>性别</th>
				<th>市场价</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>销售价</th>
				<th>折扣信息</th>
				<th>货源名</th>
				<shiro:hasPermission name="agent:stock:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="stock">
			<tr>
				<td><a href="${ctx}/agent/stock/form?id=${stock.id}">
					${stock.articleno}
				</a></td>
				<td>
					${stock.division}
				</td>
				<td>
					${stock.brandname}
				</td>
				<td>
					${stock.uksize}
				</td>
				<td>
					${stock.size}
				</td>
				<td>
					${stock.innernum}
				</td>
				<td>
					${stock.sex}
				</td>
				<td>
					${stock.marketprice}
				</td>
				<td>
					<fmt:formatDate value="${stock.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${stock.remarks}
				</td>
				<td>
					${stock.price}
				</td>
				<td>
					${stock.discount}
				</td>
				<td>
					${stock.warehousename}
				</td>
				<shiro:hasPermission name="agent:stock:edit"><td>
    				<a href="${ctx}/agent/stock/form?id=${stock.id}">修改</a>
					<a href="${ctx}/agent/stock/delete?id=${stock.id}" onclick="return confirmx('确认要删除该库存吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>