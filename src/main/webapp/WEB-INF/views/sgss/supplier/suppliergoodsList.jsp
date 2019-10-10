<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $(":checkbox[name='goodsIds']").click(function () {
                $("#checkId").attr('checked', $(":checkbox[name='goodsIds']").length == $(":checkbox[name='goodsIds']:checked").length);
            });
            $(":checkbox[name='checkId']").click(function () {
                checkAll(this, 'goodsIds');
            });
		});
        function checkup() {
            var num = $("input[type='checkbox']:checked").length;
            if (num == 0) {
                top.$.jBox.alert("请选择你要上架的数据");
            } else {
                confirmx('确定要上架已选中的数据吗？', repairUp);
            }

        }
        function checkdown() {
            var num = $("input[type='checkbox']:checked").length;
            if (num == 0) {
                top.$.jBox.alert("请选择你要下架的数据");
            } else {
                confirmx('确定要下架已选中的数据吗？', repairDown);
            }

        }
        function repairUp(){
            repairUpOrDown(1);
        }
        function repairDown(){
            repairUpOrDown(0);
        }
        function repairUpOrDown(iiii) {

            var ids = [];
            $("input[name='goodsIds']:checked").each(function () {
                ids.push($(this).val());
            });
            var delIds = ids.join(",");
            var url="${ctx}/goods/suppliergoods/upordown?remarks=" + delIds+"&tstate="+iiii;
            $.ajax({
                type : "post",
                async : false,
                url : url,
                success : function(msg) {
                    if(msg=='ok')
                    {
                        $("#searchForm").submit();
                    }

                },
                error : function(json) {

                    return false;
                }
            });
        }
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
				<form:select path="brand.id" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${brands}" itemLabel="name" itemValue="id"  htmlEscape="false"/>
				</form:select>
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

			<li class="btns">
				<a href="javascript:void(0)" onclick="checkdown()" class="btn btn-primary">下架</a>
				<a href="javascript:void(0)" onclick="checkup()" class="btn btn-primary">上架</a>
				<a href="/doc.docx"   class="btn btn-warning">下载文档</a>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type=checkbox name="checkId" id="checkId">选择</th>
				<th>品牌</th>
				<th>供应商</th>
				<th>分类</th>
				<th>名称</th>
				<th class="sort-column artno">货号</th>
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
				<td><input type="checkbox" name="goodsIds" value="${goods.id}"/>${goods.num}</td>
				<td>
						${goods.brand.name}
				</td>
				<td>
						${goods.supplier.name}
				</td>
				<td>
						${goods.categoryName}
				</td>
				<td>
					${goods.name}
				</td>
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
					<a href="${ctx}/goods/suppliergoods/copy?id=${goods.id}" onclick="return confirmx('确认要复制该商品管理吗？', this.href)">复制</a>

				<%--	<a href="${ctx}/goods/suppliergoods/delete?id=${goods.id}" onclick="return confirmx('确认要删除该商品管理吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>