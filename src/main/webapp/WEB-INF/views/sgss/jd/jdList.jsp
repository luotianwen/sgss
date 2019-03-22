<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>京东抓取管理</title>
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
		<li class="active"><a href="${ctx}/jd/jd/">京东抓取列表</a></li>
		<shiro:hasPermission name="jd:jd:edit"><li><a href="${ctx}/jd/jd/form">京东抓取添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="jd" action="${ctx}/jd/jd/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>京东编号：</label>
				<form:input path="url" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>京东编号</th>
				<th>状态</th>
				<th>时间</th>
				<shiro:hasPermission name="jd:jd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="jd">
			<tr>
				<td><a href="${ctx}/jd/jd/form?id=${jd.id}">
					${jd.url}
				</a></td>
				<td>
					<fmt:formatDate value="${jd.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fns:getDictLabel(jd.state, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="jd:jd:edit"><td>

					<a href="${ctx}/jd/jd/pacth?url=${jd.url}" onclick="return confirmx('确认要抓取该京东抓取吗？', this.href)">抓取</a>

					<a href="${ctx}/jd/jd/delete?id=${jd.id}" onclick="return confirmx('确认要删除该京东抓取吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>