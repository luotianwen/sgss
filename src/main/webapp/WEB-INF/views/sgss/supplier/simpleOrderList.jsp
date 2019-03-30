<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>总订单管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            var clipboard = new ClipboardJS('.copy');

            clipboard.on('success', function (e) {
                top.$.jBox.tip("复制" + e.text + "成功");
                e.clearSelection();
            });

            clipboard.on('error', function (e) {

                top.$.jBox.tip("复制" + e.text + "失败");
            });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        } function promptxcourier(title, href, closed) {
            layer.open({
                type: 1,
                shadeClose: true,
                content: '<div class=\'form-search\' style=\'padding:20px;text-align:center;\'>快递公司：<input type=\'text\' id=\'backcourier2\' name=\'backcourier\'/> </br>快递单号：<input type=\'text\' id=\'backnumber2\' name=\'backnumber\'/></br>快递费用：<input type=\'text\' id=\'backmoney2\' name=\'backmoney\'/></div>',
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
                    var url = href + "&courier=" + encodeURIComponent(backcourier) + "&delivernumber=" + encodeURIComponent(backnumber) + "&delivermoney=" + encodeURIComponent(backmoney);
                    $.ajax({
                        type : "post",
                        async : false,
                        url : url,
                        success : function(msg) {
                            if(msg=='ok')
                            {
                                top.$.jBox.tip("发货成功。", 'success');
                                $("#searchForm").submit();
                                return true;
                            }
                            else{
                                top.$.jBox.tip("发货失败。", 'error');

                                return false;
                            }
                        },
                        error : function(json) {
                            top.$.jBox.tip("网络异常。", 'error');
                            return false;
                        }
                    });
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
		<li class="active"><a href="${ctx}/supplier/simpleOrder/">总订单管理列表</a></li>
		<shiro:hasPermission name="supplier:simpleOrder:edit"><li><a href="${ctx}/supplier/simpleOrder/form">总订单管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="simpleOrder" action="${ctx}/supplier/simpleOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单来源：</label>
				<form:select path="sourcetype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('source_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>供应商：</label>
				<form:select path="supplier.id" items="${suppliers}" itemLabel="name" itemValue="id" cssClass="input-medium" />
			</li>
			<li><label>序号：</label>
				<form:input path="no" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>订单id：</label>
				<form:input path="orderid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>三方编号：</label>
				<form:input path="tradeid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('simple_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否有单号：</label>
				<form:select path="isdelivernumber" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>快递单号：</label>
				<form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>收件人：</label>
				<form:input path="consignee" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>手机：</label>
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>类别：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('order_type')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否对账：</label>
				<form:select path="isaccount" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${simpleOrder.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${simpleOrder.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>序号</th>
				<th>订单来源</th>
				<th>供应商</th>
				<th>订单id</th>
				<th>三方订单编号</th>
				<th>货号</th>
				<th>数量</th>
				<th>售价</th>
				<th>快递费用</th>
				<th>总价</th>
				<th>类别</th>
				<th>售后</th>
				<th>状态</th>
				<th>收件人</th>
				<th>快递信息</th>

				<th>是否对账</th>
				<th>创建时间</th>
				<th>发货时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="supplier:simpleOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="simpleOrder">
			<tr>
				<td>
						${simpleOrder.no}
				</td>
				<td>
					${fns:getDictLabel(simpleOrder.sourcetype, 'source_type', '')}
				</td>
				<td>
					${simpleOrder.supplier.name}
				</td>

				<td>
					${simpleOrder.orderid}
				</td>
				<td>
					${simpleOrder.tradeid}
				</td>
				<td>
					${simpleOrder.articleno} <span style="color: red">|</span>
						<c:if test="${not empty simpleOrder.colour}">
					${simpleOrder.colour}<span style="color: red">|</span>
			    	</c:if>
						${simpleOrder.spec}
				</td>
				<td>
						${simpleOrder.num}
				</td>
				<td>
						${simpleOrder.money}
				</td>
				<td>
						${simpleOrder.delivermoney}
				</td>
				<td>
						${simpleOrder.totalmoney}
				</td>
				<td>
						${fns:getDictLabel(simpleOrder.type, 'order_type', '')}
				</td>
				<td>
					${fns:getDictLabel(simpleOrder.afterstate, 'afterstate', '')}
				</td>
				<td>
					${fns:getDictLabel(simpleOrder.state, 'simple_order_state', '')}
				</td>
				<td class="copy" data-clipboard-text="${simpleOrder.consignee}，${simpleOrder.phone}，，${simpleOrder.address}，"
					title="点击复制">
						${simpleOrder.consignee}，${simpleOrder.phone}，，${simpleOrder.address}，
				</td>
				<td>
						${simpleOrder.courier}
						${simpleOrder.delivernumber}
					<c:if test="${not empty simpleOrder.delivernumber}">
						<a target="_blank"
						   href="https://www.baidu.com/s?ie=UTF-8&wd=${simpleOrder.delivernumber}">查看</a>
					</c:if>
				</td>




				<td>
						${fns:getDictLabel(simpleOrder.isaccount, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${simpleOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${simpleOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${simpleOrder.remarks}
				</td>
				<td>
					<c:if test="${   simpleOrder.state==1}">
						<a href="${ctx}/order/supplierorder/noSku?id=${simpleOrder.id}">没货</a>
					</c:if>
					<shiro:hasPermission name="supplier:simpleOrder:dispatch">
						<c:if test="${empty simpleOrder.supplier}">
				          	<a href="${ctx}/supplier/simpleOrder/dispatch?id=${simpleOrder.id}">派单</a>
						</c:if>
					</shiro:hasPermission>
					<c:if test="${empty simpleOrder.supplier}">
					<shiro:hasPermission name="supplier:simpleOrder:edit"><a href="${ctx}/supplier/simpleOrder/form?id=${simpleOrder.id}">修改</a></shiro:hasPermission>
					</c:if>
					<c:if test="${simpleOrder.state==3 and empty simpleOrder.afterstate}">
						<a href="${ctx}/supplier/simpleOrder/after?id=${simpleOrder.id}"
						   onclick="return confirmx('确认要售后吗？', this.href)">售后</a>
					</c:if>
					<c:if test="${  empty simpleOrder.courier}">
						<a href="${ctx}/supplier/simpleOrder/fast?id=${simpleOrder.id}"
						   onclick="return promptxcourier('填写${simpleOrder.consignee}的${simpleOrder.articleno}快递信息',   this.href)">快发</a>

					</c:if>
					<%--<a href="${ctx}/supplier/simpleOrder/delete?id=${simpleOrder.id}" onclick="return confirmx('确认要删除该总订单管理吗？', this.href)">删除</a>
				--%></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>