<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>云中鹤商品管理管理</title>
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
		<li class="active"><a href="${ctx}/yzh/yzhProduct/">云中鹤商品管理列表</a></li>
		<shiro:hasPermission name="yzh:yzhProduct:edit"><li><a href="${ctx}/yzh/yzhProduct/form">云中鹤商品管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="yzhProduct" action="${ctx}/yzh/yzhProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品id：</label>
				<form:input path="productid" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${yzhProduct.beginCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${yzhProduct.endCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${yzhProduct.beginUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${yzhProduct.endUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<a href="${ctx}/yzh/yzhProduct/tb"> <input class="btn btn-primary" type="button" value="同步商品"></a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品id</th>
				<th>商品名称</th>
				<th>商品</th>
				<th>品牌</th>
				<th>商品型号</th>
				<th>状态</th>
				<th>市场价</th>
				<th>协议价格</th>
				<th>同步</th>
				<th>商品创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="yzh:yzhProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="yzhProduct">
			<tr>
				<td><a href="${ctx}/yzh/yzhProduct/form?id=${yzhProduct.id}">
					${yzhProduct.productid}
				</a></td>
				<td>
					${yzhProduct.name}
				</td>
				<td>
					<img src="${yzhProduct.thumbnailimage}" width="80px">

				</td>
				<td>
					${yzhProduct.brand}
				</td>
				<td>
					${yzhProduct.productcode}
				</td>
				<td>
					${yzhProduct.status}
				</td>
				<td>
					${yzhProduct.marketprice}
				</td>
				<td>
					${yzhProduct.retailprice}
				</td>
				<td>
						${fns:getDictLabel(yzhProduct.sync, 'yes_no', '')}
				</td>
				<td>
					 ${yzhProduct.createtime}
				</td>
				<td>
					<fmt:formatDate value="${yzhProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="yzh:yzhProduct:edit"><td>
					<a href="${ctx}/yzh/yzhProduct/syncform?id=${yzhProduct.id}" target="_blank">同步动力</a>
    				<a href="${ctx}/yzh/yzhProduct/form?id=${yzhProduct.id}">修改</a>
					<a href="${ctx}/yzh/yzhProduct/delete?id=${yzhProduct.id}" onclick="return confirmx('确认要删除该云中鹤商品管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>