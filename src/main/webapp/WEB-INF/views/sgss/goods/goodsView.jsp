<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商品管理管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });

        function addRow(list, idx, tpl, row) {
            $(list).append(Mustache.render(tpl, {
                idx: idx, delBtn: true, row: row
            }));
            $(list + idx).find("select").each(function () {
                $(this).val($(this).attr("data-value"));
            });
            $(list + idx).find("input[type='checkbox'], input[type='radio']").each(function () {
                var ss = $(this).attr("data-value").split(',');
                for (var i = 0; i < ss.length; i++) {
                    if ($(this).val() == ss[i]) {
                        $(this).attr("checked", "checked");
                    }
                }
            });
        }

        function delRow(obj, prefix) {
            var id = $(prefix + "_id");
            var delFlag = $(prefix + "_delFlag");
            if (id.val() == "") {
                $(obj).parent().parent().remove();
            } else if (delFlag.val() == "0") {
                delFlag.val("1");
                $(obj).html("&divide;").attr("title", "撤销删除");
                $(obj).parent().parent().addClass("error");
            } else if (delFlag.val() == "1") {
                delFlag.val("0");
                $(obj).html("&times;").attr("title", "删除");
                $(obj).parent().parent().removeClass("error");
            }
        }

        function generatestocks() {
            var price = $("#stocks").val();
            if (price.length == 0 || price == 0) {
                top.$.jBox.tip("库存必填", "erroe", {persistent: true, opacity: 0});
                return false;
            }
            for (var i = 0; i < goodsSkuRowIdx; i++) {
                $("#goodsSkuList" + i + "_stock").val(price);
            }

        }

        function generatemarketprices() {
            var price = $("#marketprices").val();
            if (price.length == 0 || price == 0) {
                top.$.jBox.tip("市场价格必填", "erroe", {persistent: true, opacity: 0});
                return false;
            }
            for (var i = 0; i < goodsSkuRowIdx; i++) {
                $("#goodsSkuList" + i + "_marketPrice").val(price);
            }

        }

        function changeSettlementPrice(i) {
            var price = $("#goodsSkuList" + i + "_settlementPrice").val();
            if (price.length == 0 || price == 0) {
                top.$.jBox.tip("结算价格必填", "erroe", {persistent: true, opacity: 0});
                return false;
            }
            var _marketPrice = $("#goodsSkuList" + i + "_marketPrice").val();
            $("#goodsSkuList" + i + "_discount").val(price / _marketPrice * 10);

        }

        function generatesettlementPrices() {
            var price = $("#settlementPrices").val();
            if (price.length == 0 || price == 0) {
                top.$.jBox.tip("结算价格必填", "erroe", {persistent: true, opacity: 0});
                return false;
            }
            for (var i = 0; i < goodsSkuRowIdx; i++) {
                var _marketPrice = $("#goodsSkuList" + i + "_marketPrice").val();
                var _price = $("#goodsSkuList" + i + "_price").val();
                $("#goodsSkuList" + i + "_settlementPrice").val(price);
                $("#goodsSkuList" + i + "_discount").val(price / _marketPrice * 10);
            }

        }

        function generatePrice() {
            var price = $("#prices").val();
            if (price.length == 0 || price == 0) {
                top.$.jBox.tip("价格必填", "erroe", {persistent: true, opacity: 0});
                return false;
            }
            for (var i = 0; i < goodsSkuRowIdx; i++) {
                $("#goodsSkuList" + i + "_price").val(price);
            }

        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/goods/goods/">商品管理列表</a></li>
    <li class="active"><a href="${ctx}/goods/goods/view?id=${goods.id}">商品管理查看</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="goods" action="${ctx}/goods/goods/view" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">供应商：</label>
        <div class="controls">
            <form:input path="supplier.name" disabled="true" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">分类：</label>
        <div class="controls">
            <form:input path="categoryName" disabled="true" />

        </div>
    </div>
    <div class="control-group">
        <label class="control-label">名称：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required" disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">货号(先填货号在选图)：</label>
        <div class="controls">
            <form:input path="artno" htmlEscape="false" maxlength="20" class="input-xlarge required" disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">主图：</label>
        <div class="controls">
            <form:hidden id="logo" path="logo" htmlEscape="false" maxlength="200" class="input-xlarge"/>
            <sys:ckfinder input="logo" type="images" uploadPath="/goods/main" selectMultiple="false" attrno="artno"
                          readonly="true"/>

        </div>
    </div>
    <div class="control-group">
        <label class="control-label">序号：</label>
        <div class="controls">
            <form:input path="sort" htmlEscape="false" maxlength="20" class="input-xlarge  digits" disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">市场售价：</label>
        <div class="controls">
            <form:input path="marketPrice" htmlEscape="false" class="input-xlarge " disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">本店售价：</label>
        <div class="controls">
            <form:input path="price" htmlEscape="false" class="input-xlarge " disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">品牌：</label>
        <div class="controls">
            <form:input path="brand.name" htmlEscape="false" class="input-xlarge " disabled="true"/>


        </div>
    </div>
    <div class="control-group">
        <label class="control-label">上架状态：</label>
        <div class="controls">
            <form:radiobuttons path="state" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                               htmlEscape="false" class="required" disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">审核状态：</label>
        <div class="controls">
            <form:radiobuttons path="pass" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                               htmlEscape="false" class="required" disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注信息：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="2" maxlength="100" class="input-xxlarge "
                           disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">详情:</label>
        <div class="controls">
            <form:textarea id="details" htmlEscape="true" path="detail.details" rows="4" class="input-xxlarge"
                           cssStyle="width: 50%"/>
                <%--<sys:ckeditor replace="details" uploadPath="/goods/goods"    />--%>
            <sys:umeditor replace="details" readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">图片(多图):</label>
        <div class="controls">
            <form:hidden id="imgs" path="imgs" htmlEscape="false" maxlength="200" class="input-xlarge"/>
            <sys:ckfinder input="imgs" type="images" uploadPath="/goods/main" selectMultiple="true" attrno="artno"
                          readonly="true"/>


        </div>
    </div>
    <div class="control-group">
        <label class="control-label">本店销量：</label>
        <div class="controls">
            <form:input path="sales" htmlEscape="false" class="input-xlarge required" disabled="true"/>
        </div>
    </div>


    <div class="control-group">
        <label class="control-label">商品sku：</label>
        <div class="controls">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th class="hide"></th>
                    <th><form:input path="spec1" htmlEscape="false" maxlength="20" class="input-small "
                                    disabled="true"/></th>
                    <th><form:input path="spec2" htmlEscape="false" maxlength="20" class="input-small "
                                    disabled="true"/></th>
                    <th>本店售价</th>
                    <th>市场价</th>
                    <th>结算价</th>
                    <th>折扣</th>
                    <th>结算折扣</th>
                    <th>毛利</th>
                    <th>毛利率</th>
                    <th>库存</th>
                </tr>
                </thead>
                <tbody id="goodsSkuList">
                </tbody>

            </table>
            <script type="text/template" id="goodsSkuTpl">//<!--
						<tr id="goodsSkuList{{idx}}">
							<td class="hide">
								<input id="goodsSkuList{{idx}}_id" name="goodsSkuList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="goodsSkuList{{idx}}_delFlag" name="goodsSkuList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input   disabled="true" type="text" value="{{row.spec1}}" maxlength="20" class="input-small required"/>
							</td>
							<td>
								<input  disabled="true" type="text" value="{{row.spec2}}" maxlength="20" class="input-small required"/>
							</td>
							<td>
								<input   disabled="true" type="text" value="{{row.price}}" class="input-small required"/>
							</td>
							<td>
								<input   disabled="true" type="text" value="{{row.marketPrice}}" class="input-small required"/>
							</td>
							<td>
								<input  disabled="true"  type="text" value="{{row.settlementPrice}}" class="input-small required"/>
							</td>
							<td>
								<input   type="text" disabled="true" value="{{row.discount}}" class="input-small required"/>
							</td>
<td>
								<input   type="text" disabled="true" value="{{row.settlementDiscount}}" class="input-small required"/>
							</td>
							<td>
								<input   type="text" disabled="true" value="{{row.profit}}" class="input-small required"/>
							</td>
<td>
								<input   type="text" disabled="true" value="{{row.profitDiscount}}%" class="input-small required"/>
							</td>
							<td>
								<input  disabled="true" value="{{row.stock}}" maxlength="11" class="input-small required"/>
							</td>

						</tr>//-->
            </script>
            <script type="text/javascript">
                var goodsSkuRowIdx = 0,
                    goodsSkuTpl = $("#goodsSkuTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g, "");
                $(document).ready(function () {
                    var data = ${fns:toJson(goods.goodsSkuList)};
                    for (var i = 0; i < data.length; i++) {

                        addRow('#goodsSkuList', goodsSkuRowIdx, goodsSkuTpl, data[i]);
                        goodsSkuRowIdx = goodsSkuRowIdx + 1;
                    }
                });

            </script>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>