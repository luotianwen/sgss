<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理管理</title>
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
		<li class="active"><a href="${ctx}/goods/suppliergoods/">商品管理列表</a></li>
		<shiro:hasPermission name="goods:suppliergoods:edit"><li><a href="${ctx}/goods/suppliergoods/form">商品管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="goods" action="${ctx}/goods/suppliergoods/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>货号：</label>
				<form:input path="artno" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:input path="brand.name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>

			<li><label>上架状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${goods.beginCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${goods.endCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

				<th>品牌</th>
				<th>供应商</th>
				<th>分类</th>
				<th>名称</th>
				<th>货号</th>
				<th>主图</th>
				<th>序号</th>
				<th>市场售价</th>
				<th>上架状态</th>
				<th class="sort-column createDate">上传时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="goods:suppliergoods:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="goods">
			<tr>
				<td>
						${goods.brand.name}
				</td>
				<td>
						${goods.supplier.name}
				</td>
				<td>
						${goods.categoryName}
				</td>
				<td><a href="${ctx}/goods/goods/form?id=${goods.id}">
					${goods.name}
				</a></td>
				<td>
					${goods.artno}
				</td>
				<td>
					<img src="${goods.logo}" width="80px">

				</td>
				<td>
					${goods.sort}
				</td>
				<td>
					${goods.marketPrice}
				</td>


				<td>
					${fns:getDictLabel(goods.state, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${goods.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<td>
					${goods.remarks}
				</td>
				<shiro:hasPermission name="goods:suppliergoods:edit"><td>
    				<a href="${ctx}/goods/suppliergoods/form?id=${goods.id}">修改</a>
				<%--	<a href="${ctx}/goods/suppliergoods/delete?id=${goods.id}" onclick="return confirmx('确认要删除该商品管理吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>