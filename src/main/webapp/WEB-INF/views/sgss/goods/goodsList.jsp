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
        function checkpass() {
            var num = $("input[type='checkbox']:checked").length;
            if (num == 0) {
                top.$.jBox.alert("请选择你要通过的数据");
            } else {
                confirmx('确定要通过已选中的数据吗？', repairPass);
            }

        }
       var dong;
        function checkdong(val) {
            var num = $("input[type='checkbox']:checked").length;
            if (num == 0) {
                top.$.jBox.alert("请选择你要操作的数据");
            } else {
                dong=val;
                confirmx('确定要通过已选中的数据吗？', repairDong);
            }

        }


        function repairDong() {
            var ids = [];
            $("input[name='goodsIds']:checked").each(function () {
                ids.push($(this).val());
            });
            var delIds = ids.join(",");
            var url="${ctx}/goods/goods/passsync?remarks=" + delIds+"&tpass="+dong;
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
        function checknopass() {
            var num = $("input[type='checkbox']:checked").length;
            if (num == 0) {
                top.$.jBox.alert("请选择你要不通过的数据");
            } else {
                confirmx('确定要不通过已选中的数据吗？', repairNot);
            }

        }
        function repairPass(){
            repairPassOrNot(1);
        }
        function repairNot(){
            repairPassOrNot(0);
        }
        function repairPassOrNot(iiii) {
            var ids = [];
            $("input[name='goodsIds']:checked").each(function () {
                ids.push($(this).val());
            });
            var delIds = ids.join(",");
            var url="${ctx}/goods/goods/passornot?remarks=" + delIds+"&tpass="+iiii;
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
            var url="${ctx}/goods/goods/upordown?remarks=" + delIds+"&tstate="+iiii;
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
        function pagesize(a) {
            $("#pageSize").val(a);
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/goods/goods/">商品管理列表</a></li>
		<shiro:hasPermission name="goods:goods:edit"><li><a href="${ctx}/goods/goods/form">商品管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="goods" action="${ctx}/goods/goods/" method="post" class="breadcrumb form-search">
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

            <shiro:hasPermission name="goods:goods:viewsup">
			<li><label>供应商：</label>
				<form:select path="supplier.id" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${suppliers}" itemLabel="name" itemValue="id"   htmlEscape="false"/>
				</form:select>
			</li>

                <li><label>创建者：</label>
                    <sys:treeselect id="createBy" name="createBy.id" value="${goods.createBy.id}" labelName="createBy.name" labelValue="${goods.createBy.name}"
                                    title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true"/></li>

            </shiro:hasPermission>
			<li><label>上架状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>审核状态：</label>
				<form:select path="pass" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>动力状态：</label>
				<form:select path="sync" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>北移状态：</label>
				<form:select path="syncby" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>市场售价：</label>
				<form:input path="beginMarketPrice" htmlEscape="false" maxlength="100" class="input-mini"/> -
				<form:input path="endMarketPrice" htmlEscape="false" maxlength="100" class="input-mini"/>
			</li>
			<li><label>本店售价：</label>
				<form:input path="beginPrice" htmlEscape="false" maxlength="100" class="input-mini"/> -
				<form:input path="endPrice" htmlEscape="false" maxlength="100" class="input-mini"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${goods.beginCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${goods.endCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>每页条数：</label>
				<form:select path="page.pageSize" class="input-medium" onchange="pagesize(this.value)">
					<form:option value="10" label="10"/>
					<form:option value="20" label="20"/>
					<form:option value="30" label="30"/>
					<form:option value="50" label="50"/>

				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>

			<li class="btns">
				<a href="javascript:void(0)" onclick="checkdown()" class="btn btn-primary">下架</a>
				<a href="javascript:void(0)" onclick="checkup()" class="btn btn-primary">上架</a>
				<a href="javascript:void(0)" onclick="checkdong(1)" class="btn btn-primary">已动力</a>
				<a href="javascript:void(0)" onclick="checkdong(2)" class="btn btn-primary">已移动</a>
				<shiro:hasPermission name="goods:goods:pass">
				<a href="javascript:void(0)" onclick="checkpass()" class="btn btn-primary">通过</a>
				<a href="javascript:void(0)" onclick="checknopass()" class="btn btn-primary">不通过</a>
				</shiro:hasPermission>
				<a href="/doc.docx"   class="btn btn-warning">下载文档</a>
			</li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type=checkbox name="checkId" id="checkId">选择</th>
				<th  class="sort-column b.name">品牌</th>
                <shiro:hasPermission name="goods:goods:viewsup">
				<th  class="sort-column s.name">供应商</th>
                </shiro:hasPermission>
				<th   >分类</th>
				<th>名称</th>
				<th class="sort-column artno">货号</th>
				<th>主图</th>
				<th  >序号</th>
				<th class="sort-column marketPrice">市场售价</th>
				<th class="sort-column price">本店售价</th>
				<th class="sort-column sales">销量</th>
				<th class="sort-column state">上架</th>
				<th  >动力</th>
			<%--	<th  >北移</th>--%>
				<th class="sort-column pass">审核</th>
                <th  >上传者</th>
				<th class="sort-column createDate">添加时间</th>
				<th class="sort-column updateDate">更新时间</th>
				<%--<th>备注信息</th>--%>
				<shiro:hasPermission name="goods:goods:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="goods">
			<tr>
				<td><input type="checkbox" name="goodsIds" value="${goods.id}"/>${goods.num}</td>
				<td>
						${goods.brand.name}
				</td>
                <shiro:hasPermission name="goods:goods:viewsup">
				<td>
						${goods.supplier.name}
				</td>
                </shiro:hasPermission>
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

					<c:if test="${fn:startsWith(goods.logo,'http')}">
						<img src="${goods.logo}" width="60px">
					</c:if>
					<c:if test="${!fn:startsWith(goods.logo,'http')}">
						<img src="http://image.yoyound.com${goods.logo}" width="60px">
					</c:if>


				</td>
				<td>
					${goods.sort}
				</td>
				<td>
					${goods.marketPrice}
				</td>
				<td>
					${goods.price}
				</td>
				<td>
						${goods.sales}
				</td>
				<td>
					${fns:getDictLabel(goods.state, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(goods.sync, 'yes_no', '')}
			</td>
				<%--<td>
						${fns:getDictLabel(goods.syncby, 'yes_no', '')}
				</td>--%>
				<td>
						${fns:getDictLabel(goods.pass, 'yes_no', '')}
				</td>
                <td>
                        ${goods.createBy.name}
                </td>
				<td>
					<fmt:formatDate value="${goods.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${goods.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>
					${goods.remarks}
				</td>--%>
				<td><shiro:hasPermission name="goods:goods:edit"><a href="${ctx}/goods/goods/form?id=${goods.id}" target="_blank">修改</a></shiro:hasPermission>
                    <shiro:hasPermission name="goods:goods:pass">
						<a href="${ctx}/goods/goods/form?id=${goods.id}" target="_blank">审核</a>
						<a href="${ctx}/goods/goods/delete?id=${goods.id}" onclick="return confirmx('确认要删除该商品管理吗？', this.href)">删除</a>
					</shiro:hasPermission>


                    <shiro:hasPermission name="goods:goods:pass"><a href="${ctx}/goods/goods/view?id=${goods.id}">查看</a></shiro:hasPermission>
					 <a href="${ctx}/goods/goods/copy?id=${goods.id}" onclick="return confirmx('确认要复制该商品管理吗？', this.href)">复制</a>
					<%--<a href="${ctx}/tuan/dlGoodsTuan/copyForm?goodsId=${goods.id}" onclick="return confirmx('确认要团购吗？', this.href)">团购</a>--%>
					<shiro:hasPermission name="goods:goods:pass">
						<%--<c:if test="${goods.sync==0}">--%>
							<a href="${ctx}/goods/goods/syncform?id=${goods.id}" target="_blank">同步动力</a>
						<%--</c:if>--%>

					<%--	<c:if test="${goods.syncby==0}">
							<a href="${ctx}/goods/goods/syncbyform?id=${goods.id}">同步北移</a>
						</c:if>--%>
					</shiro:hasPermission>

			 	</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>