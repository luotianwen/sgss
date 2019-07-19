<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>团购商品管理</title>
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
		<li class="active"><a href="${ctx}/tuan/dlGoodsTuan/">团购商品列表</a></li>
		<shiro:hasPermission name="tuan:dlGoodsTuan:edit"><li><a href="${ctx}/tuan/dlGoodsTuan/form">团购商品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dlGoodsTuan" action="${ctx}/tuan/dlGoodsTuan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label>货号：</label>
				<form:input path="artno" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>开始日期：</label>
				<input name="beginBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dlGoodsTuan.beginBeginDate}" pattern="yyyy-MM-dd  HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dlGoodsTuan.endBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="beginEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dlGoodsTuan.beginEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dlGoodsTuan.endEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a href="${ctx}/tuan/dlGoodsTuan/copyForm" class="btn btn-primary" >商城复制商品</a> </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>名称</th>
				<th>货号</th>
				<th>市场售价</th>
				<th>团购价</th>
				<th>状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>结算价</th>
				<th>团购开始日期</th>
				<th>团购结束日期</th>
				<shiro:hasPermission name="tuan:dlGoodsTuan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dlGoodsTuan">
			<tr>
				<td><a href="${ctx}/tuan/dlGoodsTuan/form?id=${dlGoodsTuan.id}">
					${dlGoodsTuan.num}
				</a></td>
				<td>
					${dlGoodsTuan.name}
				</td>
				<td>
					${dlGoodsTuan.artno}
				</td>
				<td>
					${dlGoodsTuan.marketPrice}
				</td>
				<td>
					${dlGoodsTuan.price}
				</td>
				<td>
					${fns:getDictLabel(dlGoodsTuan.state, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${dlGoodsTuan.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dlGoodsTuan.remarks}
				</td>
				<td>
					${dlGoodsTuan.costPrice}
				</td>
				<td>
					<fmt:formatDate value="${dlGoodsTuan.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${dlGoodsTuan.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><shiro:hasPermission name="tuan:dlGoodsTuan:edit">
    				<a href="${ctx}/tuan/dlGoodsTuan/form?id=${dlGoodsTuan.id}">修改</a>

					<a href="${ctx}/tuan/dlGoodsTuan/delete?id=${dlGoodsTuan.id}" onclick="return confirmx('确认要删除该团购商品吗？', this.href)">删除</a>
				</shiro:hasPermission>
					<shiro:hasPermission name="tuan:dlGoodsTuan:tuanForm">
						<a href="${ctx}/tuan/dlGoodsTuan/tuanForm?id=${dlGoodsTuan.id}">配置团购</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>