<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单售后管理</title>
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


        function de(id){
            layer.open({
                type: 1,
                shadeClose: true,
                content:"<div  class='form-search' style='padding:20px;text-align:center;'>快递公司：<input type='text' id='backcourier2' name='backcourier'/> </br>快递单号：<input type='text' id='backnumber2' name='backnumber'/></br>快递费用：<input type='text' id='backmoney2' name='backmoney'/></div>",
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
                    var url =  "${ctx}/order/orderAfterSales/fast?id="+id+"&exchangeExpressName=" + encodeURIComponent(backcourier) + "&exchangeInvoiceNo=" + encodeURIComponent(backnumber) + "&exchangeFreight=" + encodeURIComponent(backmoney);
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
        };


        // 提示输入对话框
        function returnMoney(id){
            layer.open({
                type: 1,
                shadeClose: true,
                content:"<div   >退款金额：<input type='text' id='returnMoney2'  />元</div>",
                btn: ['确定', '取消']
                , yes: function (index, f) {
                    var returnMoney2=$("#returnMoney2").val();
                    if (returnMoney2 == '') {
                        top.$.jBox.tip("请输入退款金额。", 'error');
                        return false;
                    }
                    resetTip(); //loading();
                    var url =  "${ctx}/order/orderAfterSales/returnMoney?id="+id+"&returnAmount=" + (returnMoney2*100) ;
                    $.ajax({
                        type : "post",
                        async : false,
                        url : url,
                        success : function(msg) {
                            if(msg=='ok')
                            {
                                top.$.jBox.tip("操作成功。", 'success');
                                $("#searchForm").submit();
                                return true;
                            }
                            else{
                                top.$.jBox.tip("操作失败。", 'error');

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
        // 提示输入对话框
        function promptx(id){
            layer.open({
                type: 1,
                shadeClose: true,
                content:"<div  class='form-search' style='padding:20px;text-align:center;'>退货收件人：<input type='text' id='returnconsignee2'  /> </br>收件人电话：<input type='text' id='returnphone2' /></br>退货地址：<input type='text' id='returnaddress2'  /></div>",
                btn: ['确定', '取消']
                , yes: function (index, f) {
                    var returnconsignee2=$("#returnconsignee2").val();
                    var returnphone2=$("#returnphone2").val();
                    var returnaddress2=$("#returnaddress2").val();
                    if (returnconsignee2 == '') {
                        top.$.jBox.tip("请输入退货收件人。", 'error');
                        return false;
                    }
                    if (returnphone2 == '') {
                        top.$.jBox.tip("请输入收件人电话。", 'error');
                        return false;
                    }
                    if (returnaddress2 == '') {
                        top.$.jBox.tip("请输入地址。", 'error');
                        return false;
                    }
                    resetTip(); //loading();
                    var url =  "${ctx}/order/orderAfterSales/backaddress?id="+id+"&returnPhone=" + encodeURIComponent(returnphone2) + "&returnConsignee=" + encodeURIComponent(returnconsignee2) + "&returnAddress=" + encodeURIComponent(returnaddress2);
                    $.ajax({
                        type : "post",
                        async : false,
                        url : url,
                        success : function(msg) {
                            if(msg=='ok')
                            {
                                top.$.jBox.tip("操作成功。", 'success');
                                $("#searchForm").submit();
                                return true;
                            }
                            else{
                                top.$.jBox.tip("操作失败。", 'error');

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
		<li class="active"><a href="${ctx}/order/orderAfterSales/">订单售后列表</a></li>
		<%--<shiro:hasPermission name="order:orderAfterSales:edit"><li><a href="${ctx}/order/orderAfterSales/form">订单售后添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="orderAfterSales" action="${ctx}/order/orderAfterSales/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="ordernumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>售后类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('aftersales_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>售后状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('aftersales_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>退款状态：</label>
				<form:select path="refundState" class="input-medium">
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
				<th>序号</th>
				<th>订单编号</th>
				<th>用户</th>
				<th>申请时间</th>

				<th>售后类型</th>
				<th>售后状态</th>
				<th>退款状态</th>
				<th>退款金额</th>
				<th>退货信息</th>
				<th>退货单号</th>
				<th>换货信息</th>
				<th>换货单号</th>
				<th>备注信息</th>
				<shiro:hasPermission name="order:orderAfterSales:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderAfterSales">
			<tr>
				<td><a href="${ctx}/order/orderAfterSales/form?id=${orderAfterSales.id}">
					${orderAfterSales.num}
				</a></td>
				<td>
					${orderAfterSales.ordernumber}
				</td>
				<td>
					${orderAfterSales.user.name}
				</td>
				<td>
					<fmt:formatDate value="${orderAfterSales.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>

					${fns:getDictLabel(orderAfterSales.type, 'aftersales_type', '')}
				</td>
				<td>
					${fns:getDictLabel(orderAfterSales.state, 'aftersales_state', '')}
				</td>
				<td>

						${fns:getDictLabel(orderAfterSales.refundState, 'yes_no', '')}
				</td>
				<td>
						${orderAfterSales.returnAmount/100}元
				</td>

				<td>
						${orderAfterSales.returnConsignee},${orderAfterSales.returnPhone},${orderAfterSales.returnAddress}
				</td>
				<td>
						${orderAfterSales.returnExpressName} ${orderAfterSales.returnInvoiceNo}
				</td>
				<td>
						${orderAfterSales.exchangeConsignee},${orderAfterSales.exchangePhone},${orderAfterSales.exchangeAddress}
				</td>
				<td>
						${orderAfterSales.exchangeExpressName} ${orderAfterSales.exchangeInvoiceNo} ${orderAfterSales.exchangeFreight}
				</td>
				<td>
					${orderAfterSales.remarks}
				</td>
				<shiro:hasPermission name="order:orderAfterSales:edit"><td>
					<c:if test="${orderAfterSales.refundState==0}">
    				<a href="${ctx}/order/orderAfterSales/form?id=${orderAfterSales.id}">修改</a>
					<c:if test="${orderAfterSales.state==10}">
						<a href="javascript:void(0)"
						   onclick="return promptx('${orderAfterSales.id}')">同意</a>
				    </c:if>
					<c:if test="${orderAfterSales.state==30}">
						<a   href="javascript:void(0)" onclick="return de('${orderAfterSales.id}')">换货单号</a>
					</c:if>

					<a href="${ctx}/order/orderAfterSales/delete?id=${orderAfterSales.id}" onclick="return confirmx('确认要删除该订单售后吗？', this.href)">删除</a>
					</c:if>
					<a   href="javascript:void(0)" onclick="return returnMoney('${orderAfterSales.id}')">退款</a>
					<a href="${ctx}/order/order/?ordernumber=${orderAfterSales.ordernumber}">订单信息</a>
                    <a href="${ctx}/order/orderAfterSalesLog/?ordernumber=${orderAfterSales.ordernumber}">退款信息</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>

