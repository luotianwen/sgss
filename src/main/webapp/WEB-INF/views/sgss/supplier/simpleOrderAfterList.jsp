<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>总售后管理管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.table-striped tbody tr:nth-child(odd) td {
			background-color: #8DBE5A;
		}

	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        // 提示输入对话框
        function promptxcourier(title, href, closed) {
            top.$.jBox("<div class='form-search' style='padding:20px;text-align:center;'>快递公司：<input type='text' id='courier' name='courier'/> </br>快递单号：<input type='text' id='delivernumber' name='delivernumber'/>" +
                " </div>", {
                title: title, submit: function (v, h, f) {
                    if (f.courier == '') {
                        top.$.jBox.tip("请输入快递公司。", 'error');
                        return false;
                    }
                    if (f.delivernumber == '') {
                        top.$.jBox.tip("请输入快递单号。", 'error');
                        return false;
                    }

                    if (typeof href == 'function') {
                        href();
                    } else {
                        resetTip(); //loading();
                        location = href + "&courier=" + encodeURIComponent(f.courier) + "&delivernumber=" + encodeURIComponent(f.delivernumber);
                    }
                }, closed: function () {
                    if (typeof closed == 'function') {
                        closed();
                    }
                }
            });
            return false;
        }
        function promptxbackcourier(title, href, closed) {
            layer.open({
                type: 1,
                shadeClose: true,
                content: '<div class=\'form-search\' style=\'padding:20px;text-align:center;\'>快递公司：<input type=\'text\' id=\'backcourier2\' name=\'backcourier\'/> </br>快递单号：<input type=\'text\' id=\'backnumber2\' name=\'backnumber2\'/></br>快递费用：<input type=\'text\' id=\'backmoney2\' name=\'backmoney\'/></div>',
                btn: ['确定', '取消']
                , yes: function (index, f) {
                    var backcourier=$("#backcourier2").val();
                    var backnumber=$("#backnumber2").val();
                    var backmoney=$("#backmoney2").val();
                    if (backcourier == '') {
                        top.$.jBox.tip("请输入快递公司。", 'error');
                        return false;
                    }
                    if (backnumber == '') {
                        top.$.jBox.tip("请输入快递单号。", 'error');
                        return false;
                    }
                    if (backmoney == '') {
                        top.$.jBox.tip("请输入快递费用。", 'error');
                        return false;
                    }
                    resetTip(); //loading();
                    location = href + "&backcourier=" + encodeURIComponent(backcourier) + "&backnumber=" + encodeURIComponent(backnumber) + "&backmoney=" + encodeURIComponent(backmoney);

                }
                , btn2: function (index, layero) {
                    //按钮【按钮二】的回调
                    layer.close(index);
                    return false;
                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
            return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/supplier/simpleOrderAfter/">总售后管理列表</a></li>
		<shiro:hasPermission name="supplier:simpleOrderAfter:edit"><li><a href="${ctx}/supplier/simpleOrderAfter/form">总售后管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="simpleOrderAfter" action="${ctx}/supplier/simpleOrderAfter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>售后类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('aftersales_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

			<li><label>订单ID：</label>
				<form:input path="orderid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>收件人：</label>
				<form:input path="consignee" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>手机：</label>
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>是否完成：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>退货单号：</label>
				<form:select path="delivernumberOk" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>换货单号：</label>
				<form:select path="backnumberOk" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>退货单号：</label>
				<form:input path="backnumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>换货快递单号：</label>
				<form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${simpleOrderAfter.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${simpleOrderAfter.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>售后类型</th>
				<th>供应商</th>
				<th>序号</th>
				<th>订单ID</th>
				<th>货号</th>
				<th>收件人</th>
				<th>手机</th>
				<th>状态</th>
				<th>退货地址</th>
				<th>退货信息</th>
				<th>换货信息</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="supplier:simpleOrderAfter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="simpleOrderAfter">
			<tr>
				<td>
					${fns:getDictLabel(simpleOrderAfter.type, 'aftersales_type', '')}
			   </td>
				<td>
					${simpleOrderAfter.supplier.name}
				</td>
				<td>
					${simpleOrderAfter.no}
				</td>
				<td>
					${simpleOrderAfter.orderid}
				</td>
				<td>
					${simpleOrderAfter.articleno}
				</td>
				<td>
					${simpleOrderAfter.consignee}
				</td>
				<td>
					${simpleOrderAfter.phone}
				</td>
				<td>
					${fns:getDictLabel(simpleOrderAfter.state, 'yes_no', '')}
				</td>
				<td>
					${simpleOrderAfter.backaddress}
				</td>

				<td>
						${simpleOrderAfter.courier}  ${simpleOrderAfter.delivernumber}<c:if test="${not empty simpleOrderAfter.delivernumber}">

							<a target="_blank"
							   href="https://www.baidu.com/s?ie=UTF-8&wd=${simpleOrderAfter.courier} ${simpleOrderAfter.delivernumber}">查看</a>
						</c:if>
				</td>
				<td>
					${simpleOrderAfter.backcourier} ${simpleOrderAfter.backnumber} 	${simpleOrderAfter.backmoney}
						<c:if test="${not empty simpleOrderAfter.backnumber}">
							<a target="_blank" href="https://www.baidu.com/s?ie=UTF-8&wd=${simpleOrderAfter.backcourier} ${simpleOrderAfter.backnumber}">查看</a>
						</c:if>
				</td>


				<td>
					<fmt:formatDate value="${simpleOrderAfter.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${simpleOrderAfter.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${simpleOrderAfter.remarks}
				</td>
				<shiro:hasPermission name="supplier:simpleOrderAfter:edit"><td>
					<c:if test="${not empty simpleOrderAfter.backaddress && empty simpleOrderAfter.delivernumber }">
						<a href="${ctx}/supplier/simpleOrderAfter/courier?id=${simpleOrderAfter.id}&orderid=${simpleOrderAfter.orderid}"
						   onclick="return promptxcourier('填写快递信息',   this.href)">退货信息</a>
					</c:if>
					<c:if test="${empty simpleOrderAfter.backaddress}">
						<a href="${ctx}/supplier/simpleOrderAfter/backaddress?id=${simpleOrderAfter.id}&orderId=${simpleOrderAfter.orderid}&backaddress="
						   onclick="return promptx('填写退货地址','退货地址信息', this.href)">退货地址</a>
					</c:if>
					<c:if test="${simpleOrderAfter.type==2 &&  empty simpleOrderAfter.backcourier &&not empty simpleOrderAfter.backaddress}">
						<a href="${ctx}/supplier/simpleOrderAfter/backcourier?id=${simpleOrderAfter.id}&orderid=${simpleOrderAfter.orderid}"
						   onclick="return promptxbackcourier('填写快递信息',   this.href)">换货信息</a>
					</c:if>

    				<a href="${ctx}/supplier/simpleOrderAfter/form?id=${simpleOrderAfter.id}">修改</a>
					<a href="${ctx}/supplier/simpleOrderAfter/delete?id=${simpleOrderAfter.id}" onclick="return confirmx('确认要删除该总售后管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>