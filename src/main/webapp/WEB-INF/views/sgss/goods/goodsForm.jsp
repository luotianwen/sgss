<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
                    var tol =$("#logoPreview li img").length;
                    var itol =$("#imgsPreview li img").length;
                    var html = detailsue.getContent().length;
                    var sks =$("#goodsSkuList tr").length;


                   if(tol==0){
                       top.$.jBox.error("主图必填","error",{persistent:true,opacity:0});
                       return false;
				   }
                    if(itol==0){
                        top.$.jBox.error("多图必填","error",{persistent:true,opacity:0});
                        return false;
                    }
                    if(html==0){
                        top.$.jBox.error("详情必填","error",{persistent:true,opacity:0});
                        return false;
                    }
                    if(sks==0){
                        top.$.jBox.error("商品sku必填","error",{persistent:true,opacity:0});
                        return false;
                    }
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
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
        function generatestocks() {
            var price=$("#stocks").val();
            if(price.length==0||price==0){
                top.$.jBox.tip("库存必填","erroe",{persistent:true,opacity:0});
                return false;
            }
            for (var i=0;i<goodsSkuRowIdx;i++){
                $("#goodsSkuList"+i+"_stock").val(price);
            }

        }
        function generatemarketprices() {
            var price=$("#marketprices").val();
            if(price.length==0||price==0){
                top.$.jBox.tip("市场价格必填","erroe",{persistent:true,opacity:0});
                return false;
            }
            for (var i=0;i<goodsSkuRowIdx;i++){
                $("#goodsSkuList"+i+"_marketPrice").val(price);
            }
            zk();

        }

        function changePrice(i) {
            var price=$("#goodsSkuList"+i+"_price").val();
            if(price.length==0||price==0){
                top.$.jBox.tip("价格必填","erroe",{persistent:true,opacity:0});
                return false;
            }
            zk();

        }
        function changeSettlementPrice(i) {
            var price=$("#goodsSkuList"+i+"_settlementPrice").val();
            if(price.length==0||price==0){
                top.$.jBox.tip("结算价格必填","erroe",{persistent:true,opacity:0});
                return false;
            }

            zk();

        }
        function generatesettlementPrices() {
            var price=$("#settlementPrices").val();
            if(price.length==0||price==0){
                top.$.jBox.tip("结算价格必填","erroe",{persistent:true,opacity:0});
                return false;
            }
            for (var i=0;i<goodsSkuRowIdx;i++){
                $("#goodsSkuList"+i+"_settlementPrice").val(price);
            }
            zk();
        }
        function zk(){
            for (var i=0;i<goodsSkuRowIdx;i++){
                var _marketPrice=$("#goodsSkuList"+i+"_marketPrice").val();
                var _price=$("#goodsSkuList"+i+"_price").val();
                $("#goodsSkuList"+i+"_discount").val(_price/_marketPrice);
                var _settlementPrice=$("#goodsSkuList"+i+"_settlementPrice").val();
                $("#goodsSkuList"+i+"_settlementDiscount").val(_settlementPrice/_marketPrice);
            }
		}
		function generatePrice() {
			var price=$("#prices").val();
			if(price.length==0||price==0){
                top.$.jBox.tip("价格必填","erroe",{persistent:true,opacity:0});
                return false;
			}
            for (var i=0;i<goodsSkuRowIdx;i++){
                $("#goodsSkuList"+i+"_price").val(price);
            }
            zk();

        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/goods/goods/">商品管理列表</a></li>
		<li class="active"><a href="${ctx}/goods/goods/form?id=${goods.id}">商品管理<shiro:hasPermission name="goods:goods:edit">${not empty goods.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="goods:goods:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="goods" action="${ctx}/goods/goods/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<shiro:hasPermission name="goods:goods:viewsup">
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:select path="supplier.id" items="${suppliers}" itemLabel="name" itemValue="id" cssClass="input-xlarge" />

			 	</div>
		</div>
	   </shiro:hasPermission>
	 <shiro:lacksPermission name="goods:goods:viewsup">
		<%--<form:hidden path="supplier.id"/>--%>
		 <input type="hidden" name="supplier.id">
	</shiro:lacksPermission>
		 <div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
				<sys:treeselect id="categoryId" name="categoryId" value="${goods.categoryId}" labelName="categoryName" labelValue="${goods.categoryName}"
								title="分类" url="/category/scategory/treeData"  checked="true"  notAllowSelectParent="true" dataMsgRequired="必须选择" cssClass="required" allowClear="true"  />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号(先填货号再选图)：</label>
			<div class="controls">
				<form:input path="artno" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主图：</label>
			<div class="controls">
				<form:hidden id="logo" path="logo" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="logo" type="images" uploadPath="/goods/main" selectMultiple="false" attrno="artno"/>

			</div>
		</div>
        <div class="control-group">
            <label class="control-label">图片(多图):</label>
            <div class="controls">
                <form:hidden id="imgs" path="imgs" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <sys:ckfinder input="imgs" type="images" uploadPath="/goods/main" selectMultiple="true"  attrno="artno" />


            </div>
        </div>
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">市场售价：</label>
			<div class="controls">
				<form:input path="marketPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">本店售价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">品牌：</label>
			<div class="controls">
            <form:select path="brand.id" items="${brands}" itemLabel="name" itemValue="id" cssClass="input-xlarge required" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上架状态：</label>
			<div class="controls">
				<form:radiobuttons path="state" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
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
				<form:textarea id="details" htmlEscape="true" path="detail.details" rows="4"  class="input-xxlarge" cssStyle="width:55%;height:400px;"/>
				<%--<sys:ckeditor replace="details" uploadPath="/goods/goods"    />--%>
				<sys:ueditor replace="details"   />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">本店销量：</label>
			<div class="controls">
				<form:input path="sales" htmlEscape="false" class="input-xlarge required"/>
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
				<input id="gg2" class="input-xlarge " type="text">以英文逗号隔开(S,M,L)<input   class="btn btn-primary" type="button" value="生成sku" onclick="generateSku()">
			</div>
		</div>
        <shiro:hasPermission name="goods:goods:pass">
            <div class="control-group">
                <label class="control-label">审核状态：</label>
                <div class="controls">
                    <form:radiobuttons path="pass" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
                </div>
            </div>
        </shiro:hasPermission>
        <shiro:lacksPermission name="goods:goods:pass">
            <div class="control-group">
                <label class="control-label">审核状态：</label>
                <div class="controls">
                    <form:radiobutton path="pass"  value="0"   label="否" htmlEscape="false" class="required"/>
                </div>
            </div>
        </shiro:lacksPermission>

			<div class="control-group">
				<label class="control-label">商品sku：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th><form:input path="spec1" htmlEscape="false" maxlength="20" class="input-small "/></th>
								<th><form:input path="spec2" htmlEscape="false" maxlength="20" class="input-small "/></th>
								<th>本店售价<input id="prices"   class="input-small valid" type="text" value="" maxlength="20"> <input id="btnPrice" class="btn btn-primary" type="button" value="价格" onclick="generatePrice()">  <%--<form:input path="prices" htmlEscape="false" maxlength="5" class="input-small "/> --%></th>
								<th>市场价<input id="marketprices"   class="input-small valid" type="text" value="" maxlength="20"> <input id="btnPrice" class="btn btn-primary" type="button" value="市场价" onclick="generatemarketprices()">  <%--<form:input path="prices" htmlEscape="false" maxlength="5" class="input-small "/> --%></th>
								<shiro:hasPermission name="goods:goods:settlement">
								<th>结算价<input id="settlementPrices"   class="input-small valid" type="text" value="" maxlength="20"> <input id="btnPrice" class="btn btn-primary" type="button" value="结算价" onclick="generatesettlementPrices()">  <%--<form:input path="prices" htmlEscape="false" maxlength="5" class="input-small "/> --%></th>
                                 <th>折扣</th>
                                   <th>结算折扣</th>
								</shiro:hasPermission>
								<th>库存<input id="stocks" name="stocks" class="input-small valid" type="text" value="" maxlength="20"> <input id="btnStock" class="btn btn-primary" type="button" value="库存" onclick="generatestocks()"> </th>
								<shiro:hasPermission name="goods:goods:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="goodsSkuList">
						</tbody>
						<shiro:hasPermission name="goods:goods:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#goodsSkuList', goodsSkuRowIdx, goodsSkuTpl);goodsSkuRowIdx = goodsSkuRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
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
								<input id="goodsSkuList{{idx}}_settlementPrice" name="goodsSkuList[{{idx}}].settlementPrice" onchange="changeSettlementPrice({{idx}})" type="text" value="{{row.settlementPrice}}" class="input-small required"/>
							</td>

							<td>
								<input id="goodsSkuList{{idx}}_discount" name="goodsSkuList[{{idx}}].discount" type="text" value="{{row.discount}}" class="input-small required"/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_settlementDiscount" name="goodsSkuList[{{idx}}].settlementDiscount" onchange="changeSettlementPrice({{idx}})" type="text" value="{{row.settlementDiscount}}" class="input-small" readonly />
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
						var goodsSkuRowIdx = 0, goodsSkuTpl = $("#goodsSkuTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(goods.goodsSkuList)};
							for (var i=0; i<data.length; i++){

								addRow('#goodsSkuList', goodsSkuRowIdx, goodsSkuTpl, data[i]);
								goodsSkuRowIdx = goodsSkuRowIdx + 1;
							}
						});
                        function generateSku(){
                            var _gg1=$("#gg1").val().split(",");
                            var _gg2=$("#gg2").val().split(",");
                            for (var _gg11 in _gg1){
                                for (var _gg22 in _gg2){
                                    data={};
                                    data.spec1=_gg1[_gg11];
                                    data.spec2=_gg2[_gg22];
                                    data.sort=goodsSkuRowIdx;
                                    addRow('#goodsSkuList', goodsSkuRowIdx, goodsSkuTpl,data );
                                    goodsSkuRowIdx = goodsSkuRowIdx + 1;
                                }
                            }
						}
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="goods:goods:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>