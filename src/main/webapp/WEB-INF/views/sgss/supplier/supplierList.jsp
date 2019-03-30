<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理管理</title>
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
		<li class="active"><a href="${ctx}/supplier/supplier/">供应商管理列表</a></li>
		<shiro:hasPermission name="supplier:supplier:edit"><li><a href="${ctx}/supplier/supplier/form">供应商管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="supplier" action="${ctx}/supplier/supplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系手机：</label>
				<form:input path="phone" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>系统用户：</label>
				<sys:treeselect id="user" name="user.id" value="${supplier.user.id}" labelName="user.name" labelValue="${supplier.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
				<th>性别</th>
				<th>联系电话</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>状态</th>
				<th>系统用户</th>
				<shiro:hasPermission name="supplier:supplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="supplier">
			<tr>
				<td><a href="${ctx}/supplier/supplier/form?id=${supplier.id}">
					${supplier.name}
				</a></td>
				<td>
					${fns:getDictLabel(supplier.sex, 'sex', '')}
				</td>
				<td>
					${supplier.phone}
				</td>
				<td>
					<fmt:formatDate value="${supplier.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${supplier.remarks}
				</td>
				<td>
					${fns:getDictLabel(supplier.state, 'yes_no', '')}
				</td>
				<td>
					${supplier.user.name}
				</td>
				<shiro:hasPermission name="supplier:supplier:edit"><td>
    				<a href="${ctx}/supplier/supplier/form?id=${supplier.id}">修改</a>
					<a href="${ctx}/supplier/supplier/delete?id=${supplier.id}" onclick="return confirmx('确认要删除该供应商管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>