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
            zk();

        }

        function changePrice(i) {
            var price = $("#goodsSkuList" + i + "_price").val();
            if (price.length == 0 || price == 0) {
                top.$.jBox.tip("价格必填", "erroe", {persistent: true, opacity: 0});
                return false;
            }
            zk();

        }

        function changeSettlementPrice(i) {
            var price = $("#goodsSkuList" + i + "_settlementPrice").val();
            if (price.length == 0 || price == 0) {
                top.$.jBox.tip("结算价格必填", "erroe", {persistent: true, opacity: 0});
                return false;
            }

            zk();

        }

        function generatesettlementPrices() {
            var price = $("#settlementPrices").val();
            if (price.length == 0 || price == 0) {
                top.$.jBox.tip("结算价格必填", "erroe", {persistent: true, opacity: 0});
                return false;
            }
            for (var i = 0; i < goodsSkuRowIdx; i++) {
                $("#goodsSkuList" + i + "_settlementPrice").val(price);
            }
            zk();
        }

        function zk() {
            for (var i = 0; i < goodsSkuRowIdx; i++) {
                var _marketPrice = $("#goodsSkuList" + i + "_marketPrice").val();
                var _price = $("#goodsSkuList" + i + "_price").val();
                $("#goodsSkuList" + i + "_discount").val(_price / _marketPrice * 10);
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
            zk();

        }


        var curHtid;

        function addpic(htid, isthumbnail, thumbnailmode, thumbnailheight, thumbnailwidth) {
            curHtid = htid;
            var iHeight = 600;
            var iWidth = 800;
            var iTop = 50; //(window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
            var iLeft = 300; //(window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
            var Url = '../../../uploadby.jsp?IsThumbnail=' + isthumbnail + '&ThumbnailMode=' + thumbnailmode + '&ThumbnailHeight=' + thumbnailheight + '&ThumbnailWidth=' + thumbnailwidth;
            window.open(Url, 'addpicWin', 'height=' + iHeight + ', width=' + iWidth + ', top=' + iTop + ', left=' + iLeft + ', toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
        }

        /* function addpic(logo) {
             var iHeight = 600;
             var iWidth = 800;
             var iTop = 50; //(window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
             var iLeft =  300; //(window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
             var Url ='../../../upload.jsp?IsThumbnail=1&ThumbnailMode=HW&ThumbnailHeight=100&ThumbnailWidth=100&logo='+logo;
             window.open(Url,'addpicWin','height=' + iHeight + ', width=' + iWidth + ', top=' + iTop + ', left=' + iLeft + ', toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');

         }*/

        function continue_addpicNew(reResult) {
            console.log(reResult);
            var serverPath = reResult.split('|')[1];
            var imgObj = reResult.split('|')[0].split(';');
            for (var i = 0; i < reResult.split(';').length; i++) {
                var keys = serverPath + "|" + imgObj[i] + "|" + imgObj[i] + ";";
                $("#" + curHtid).val($("#" + curHtid).val() + keys);
            }

        }


    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active">同步商品</li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="goods" action="${ctx}/goods/goods/saveSyncby" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">分类：</label>
        <div class="controls">
            <select id="categoryId" name="categoryId" class="input-xlarge required">
                <option value="">-请选择-</option>
                <option value="1">服饰</option>
                <option value="2">鞋包</option>
                <option value="3">器材</option>
                <option value="4">家居</option>
                <option value="5">家电</option>
                <option value="6">商旅</option>
            </select>

        </div>
    </div>
    <div class="control-group">
        <label class="control-label">名称：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="100" class="input-xxlarge required"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">货号：</label>
        <div class="controls">
            <form:input path="artno" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">主图：</label>
        <div class="controls">
            <form:input id="logo" path="logo" htmlEscape="false" maxlength="200" class="input-xxlarge"/>
            <a href="http://op.yoyound.com${goods.logo}"   <%--download="logo.jpg" --%> target="_blank"><img
                    src="http://op.yoyound.com${goods.logo}" width="60px"> 下载</a>
            <input type="button" onclick="addpic('logo',1,'HW',100,100)" class="btn " value="上传"/> <input type="button"
                                                                                                          class="btn  "
                                                                                                          onclick="$('#logo').val('')"
                                                                                                          value="清空"/>

        </div>
    </div>


    <div class="control-group">
        <label class="control-label">品牌：</label>
        <div class="controls">
            <select id="brand.id" name="brand.id" class="input-xlarge required">
                <option value="-1">-请选择-</option>
                <option value="10">ACA</option>
                <option value="6">双立人</option>
                <option value="4">外交官</option>
                <option value="3">戴森</option>
                <option value="13">捷瑞特</option>
                <option value="12">罗莱</option>
                <option value="2">耐克</option>
                <option value="9">联创</option>
                <option value="7">膳魔师</option>
                <option value="11">虎牌</option>
                <option value="8">象印</option>
                <option value="1">阿迪达斯</option>
                <option value="5">鳄鱼</option>
            </select>

        </div>
    </div>


    <div class="control-group">
        <label class="control-label">备注信息：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="2" maxlength="100" class="input-xxlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">详情:</label>
        <div class="controls">
            <c:forEach items="${imgs}" var="item" varStatus="status">
                <a href="http://op.yoyound.com${item}"  <%-- download="${status.index+1}.jpg"--%> target="_blank"><img
                        src="http://op.yoyound.com${item}" width="60px"> 下载</a>
            </c:forEach>
            <form:textarea id="details" path="detail.details" htmlEscape="false" rows="4"
                           class="input-xxlarge required"/>
            <input type="button" onclick="addpic('details',1,'W',0,800)" class="btn " value="上传"/>
        </div>
    </div>


    <div class="control-group">
        <label class="control-label">规格1：</label>
        <div class="controls">
            <input id="gg1" class="input-xlarge " type="text">以英文逗号隔开(红色,黑色,白色)
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">规格2：</label>
        <div class="controls">
            <input id="gg2" class="input-xlarge " type="text">以英文逗号隔开(S,M,L)<input class="btn btn-primary" type="button"
                                                                                   value="生成sku"
                                                                                   onclick="generateSku()">
        </div>
    </div>


    <div class="control-group">
        <label class="control-label">商品sku：</label>
        <div class="controls">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th class="hide"></th>
                    <th><form:input path="spec1" htmlEscape="false" maxlength="20" class="input-small "/></th>
                    <th><form:input path="spec2" htmlEscape="false" maxlength="20" class="input-small "/></th>
                    <th>北移结算价<input id="prices" class="input-small valid" type="text" value="" maxlength="20"> <input
                            id="btnPrice" class="btn btn-primary" type="button" value="价格"
                            onclick="generatePrice()"> <%--<form:input path="prices" htmlEscape="false" maxlength="5" class="input-small "/> --%>
                    </th>
                    <th>市场价<input id="marketprices" class="input-small valid" type="text" value="" maxlength="20">
                        <input id="btnPrice" class="btn btn-primary" type="button" value="市场价"
                               onclick="generatemarketprices()"> <%--<form:input path="prices" htmlEscape="false" maxlength="5" class="input-small "/> --%>
                    </th>
                    <shiro:hasPermission name="goods:goods:settlement">

                        <th>折扣</th>
                    </shiro:hasPermission>
                    <th>库存<input id="stocks" name="stocks" class="input-small valid" type="text" value=""
                                 maxlength="20"> <input id="btnStock" class="btn btn-primary" type="button" value="库存"
                                                        onclick="generatestocks()"></th>
                    <shiro:hasPermission name="goods:goods:edit">
                        <th width="10">&nbsp;</th>
                    </shiro:hasPermission>
                </tr>
                </thead>
                <tbody id="goodsSkuList">
                </tbody>
                <shiro:hasPermission name="goods:goods:edit">
                    <tfoot>
                    <tr>
                        <td colspan="7"><a href="javascript:"
                                           onclick="addRow('#goodsSkuList', goodsSkuRowIdx, goodsSkuTpl);goodsSkuRowIdx = goodsSkuRowIdx + 1;"
                                           class="btn">新增</a></td>
                    </tr>
                    </tfoot>
                </shiro:hasPermission>
            </table>
            <script type="text/template" id="goodsSkuTpl">//<!--
						<tr id="goodsSkuList{{idx}}">
							<td class="hide">
								<input id="goodsSkuList{{idx}}_id" name="goodsSkuList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="goodsSkuList{{idx}}_delFlag" name="goodsSkuList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_spec1" name="goodsSkuList[{{idx}}].spec1" type="text" value="{{row.spec1}}" maxlength="20" class="input-small required"/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_spec2" name="goodsSkuList[{{idx}}].spec2" type="text" value="{{row.spec2}}" maxlength="20" class="input-small"/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_price" name="goodsSkuList[{{idx}}].price" type="text" value="{{row.price}}"  onchange="changePrice({{idx}})" class="input-small required"/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_marketPrice" name="goodsSkuList[{{idx}}].marketPrice" type="text" value="{{row.marketPrice}}" class="input-small required"/>
							</td>
							<shiro:hasPermission name="goods:goods:settlement">


							<td>
								<input id="goodsSkuList{{idx}}_discount" name="goodsSkuList[{{idx}}].discount" type="text" value="{{row.discount}}" class="input-small required"/>
							</td>
							</shiro:hasPermission>

							<td>
								<input id="goodsSkuList{{idx}}_stock" name="goodsSkuList[{{idx}}].stock" type="text" value="{{row.stock}}" maxlength="11" class="input-small required"/>
							</td>
							<shiro:hasPermission name="goods:goods:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#goodsSkuList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
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

                function generateSku() {
                    var _gg1 = $("#gg1").val().split(",");
                    var _gg2 = $("#gg2").val().split(",");
                    for (var _gg11 in _gg1) {
                        for (var _gg22 in _gg2) {
                            data = {};
                            data.spec1 = _gg1[_gg11];
                            data.spec2 = _gg2[_gg22];
                            data.sort = goodsSkuRowIdx;
                            addRow('#goodsSkuList', goodsSkuRowIdx, goodsSkuTpl, data);
                            goodsSkuRowIdx = goodsSkuRowIdx + 1;
                        }
                    }
                }
            </script>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="goods:goods:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                            value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>