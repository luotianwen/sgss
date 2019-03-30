<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>总订单管理管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
        function addRow(list, idx, tpl, row){
            $(list).append(Mustache.render(tpl, {
                idx: idx, delBtn: true, row: row
            }));
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/supplier/simpleOrder/">总订单管理列表</a></li>
    <li class="active"><a href="${ctx}/supplier/simpleOrder/dispatch?id=${simpleOrder.id}">总订单管理派单</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="simpleOrder" action="${ctx}/supplier/simpleOrder/dispatchSave" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">订单来源：</label>
        <div class="controls">
            <form:select path="sourcetype" class="input-xlarge " disabled="true">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('source_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
   <%-- <div class="control-group">
        <label class="control-label">供应商：</label>
        <div class="controls">
            <form:select path="supplier.id" items="${suppliers}" itemLabel="name" itemValue="id" cssClass="input-xlarge" />
        </div>
    </div>--%>

    <div class="control-group">
        <label class="control-label">三方订单编号：</label>
        <div class="controls">
            <form:input path="tradeid" htmlEscape="false" maxlength="32" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">货号：</label>
        <div class="controls">
            <form:input path="articleno" htmlEscape="false" maxlength="200" class="input-xlarge required" readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">数量：</label>
        <div class="controls">
            <form:input path="num" htmlEscape="false" maxlength="11" class="input-xlarge required " readonly="true"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">类型：</label>
        <div class="controls">
            <form:select path="type" class="input-xlarge " disabled="true">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">单价：</label>
        <div class="controls">
            <form:input path="price" htmlEscape="false" class="input-xlarge required" readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">状态：</label>
        <div class="controls">
            <form:select path="state" class="input-xlarge required" disabled="true">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('simple_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">收件人：</label>
        <div class="controls">
            <form:input path="consignee" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">手机：</label>
        <div class="controls">
            <form:input path="phone" htmlEscape="false" maxlength="50" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">地址：</label>
        <div class="controls">
            <form:input path="address" htmlEscape="false" maxlength="200" class="input-xlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">颜色：</label>
        <div class="controls">
            <form:input path="colour" htmlEscape="false" maxlength="200" class="input-xlarge required " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">规格尺码：</label>
        <div class="controls">
            <form:input path="spec" htmlEscape="false" maxlength="255" class="input-xlarge required" readonly="true"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">备注信息：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">商品sku：</label>
        <div class="controls">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th >选择</th>
                    <th>规格1</th>
                    <th>规格2</th>
                    <th>结算价</th>
                    <th>供应商</th>
                    <th>名称</th>
                    <th>货号 </th>
                    <th>主图</th>
                    <th>市场价</th>
                </tr>
                </thead>
                <tbody id="goodsSkuList">
                </tbody>

            </table>
            <script type="text/template" id="goodsSkuTpl">//<!--
						<tr id="goodsSkuList{{idx}}">
							<td  >

								<input id="supplier{{idx}}_id" name="supplier.id" type="radio" value="{{row.supplier.id}}-{{row.id}}"/>
							</td>
							<td>
								{{row.spec1}}
							</td>
							<td>
								{{row.spec2}}
							</td>
							 <td>
								{{row.settlementPrice}}
							</td>
							<td>
								{{row.supplier.name}}
							</td>
							<td>
								{{row.name}}
							</td>
							 <td>
								{{row.artno}}
							</td>
							<td>
							  <img src="{{row.logo}}">
							</td>
							<td>
								{{row.marketPrice}}
							</td>



						</tr>//-->
            </script>
            <script type="text/javascript">
                var goodsSkuRowIdx = 0, goodsSkuTpl = $("#goodsSkuTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
                $(document).ready(function() {
                    var data = ${fns:toJson(goods)};
                    console.log(data.length);
                    console.log(goodsSkuRowIdx);
                    for (var i=0; i<data.length; i++){

                        addRow('#goodsSkuList', goodsSkuRowIdx, goodsSkuTpl, data[i]);
                        goodsSkuRowIdx = goodsSkuRowIdx + 1;
                    }
                });

            </script>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="supplier:simpleOrder:dispatch"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确定"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>