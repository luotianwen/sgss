<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理管理</title>
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
		<li class="active"><a href="${ctx}/agent/agent/">代理列表</a></li>
		<shiro:hasPermission name="agent:agent:edit"><li><a href="${ctx}/agent/agent/form">代理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="agent" action="${ctx}/agent/agent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%--<li><label>联系电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>--%>
			<li><label>联系手机：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${agent.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${agent.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>联系地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>微信名称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="32" class="input-medium"/>
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
				<%--<th>联系电话</th>--%>
				<th>联系手机</th>
				<th>状态</th>
				<th>代理级别</th>
				<th>联系地址</th>
				<th>微信名称</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="agent:agent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="agent">
			<tr>
				<td><a href="${ctx}/agent/agent/form?id=${agent.id}">
					${agent.name}
				</a></td>
			<%--	<td>
					${agent.phone}
				</td>--%>
				<td>
					${agent.mobile}
				</td>

				<td>
					${fns:getDictLabel(agent.state, 'yes_no', '')}
				</td>
				<td>
						${agent.discountName}
				</td>

				<td>
					${agent.address}
				</td>
				<td>
					${agent.nickname}
				</td>
				<td>
					<fmt:formatDate value="${agent.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${agent.remarks}
				</td>
				<shiro:hasPermission name="agent:agent:edit"><td>
    				<a href="${ctx}/agent/agent/form?id=${agent.id}">修改</a>
					<a href="${ctx}/agent/agent/delete?id=${agent.id}" onclick="return confirmx('确认要删除该代理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>