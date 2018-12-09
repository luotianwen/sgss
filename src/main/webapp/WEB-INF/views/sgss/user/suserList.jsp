<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理管理</title>
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
		<li class="active"><a href="${ctx}/user/suser/">用户管理列表</a></li>
		<shiro:hasPermission name="user:suser:edit"><li><a href="${ctx}/user/suser/form">用户管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="suser" action="${ctx}/user/suser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名称</th>
				<th>头像</th>
				<th>积分</th>
				<th>手机号</th>
				<th>昵称</th>
				<th>状态</th>
				<th>注册时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="user:suser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="suser">
			<tr>
				<td><a href="${ctx}/user/suser/form?id=${suser.id}">
					${suser.name}
				</a></td>
				<td>
					<img src="${suser.headImg}" style="width: 80px">
				</td>
				<td>
					${suser.integral}
				</td>
				<td>
					${suser.phone}
				</td>
				<td>
					${suser.nickname}
				</td>
				<td>
					${fns:getDictLabel(suser.state, 'yes_no', '')}
				</td>

				<td>
					<fmt:formatDate value="${suser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${suser.remarks}
				</td>
				<shiro:hasPermission name="user:suser:edit"><td>
    				<a href="${ctx}/user/suser/form?id=${suser.id}">修改</a>
					<a href="${ctx}/user/suser/delete?id=${suser.id}" onclick="return confirmx('确认要删除该用户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>