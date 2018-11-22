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
		 <div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
				<sys:treeselect id="categoryId" name="categoryId" value="${goods.categoryId}" labelName="categoryName" labelValue="${goods.categoryName}"
								title="分类" url="/category/scategory/treeData"  checked="true" cssClass="" allowClear="true"  />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="artno" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主图：</label>
			<div class="controls">
				<form:hidden id="logo" path="logo" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="logo" type="images" uploadPath="/goods/goods" selectMultiple="false"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
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
		</div>
		<div class="control-group">
			<label class="control-label">品牌：</label>
			<div class="controls">
            <form:select path="brand.id" items="${brands}" itemLabel="name" itemValue="id" cssClass="input-xlarge" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上架状态：</label>
			<div class="controls">
				<form:radiobuttons path="state" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
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
				<form:textarea id="details" htmlEscape="true" path="detail.details" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="details" uploadPath="/goods/goods"  />
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">商品图片：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>主图</th>
								<th>序号</th>
								<shiro:hasPermission name="goods:goods:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="goodsPicList">
						</tbody>
						<shiro:hasPermission name="goods:goods:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#goodsPicList', goodsPicRowIdx, goodsPicTpl);goodsPicRowIdx = goodsPicRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="goodsPicTpl">//<!--
						<tr id="goodsPicList{{idx}}">
							<td class="hide">
								<input id="goodsPicList{{idx}}_id" name="goodsPicList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="goodsPicList{{idx}}_delFlag" name="goodsPicList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="goodsPicList{{idx}}logo" name="goodsPicList[{{idx}}].logo" type="hidden" value="{{row.logo}}"/>
								<sys:ckfinder input="goodsPicList{{idx}}logo" type="images" uploadPath="/goods/goods" selectMultiple="false"/>
							</td>
							<td>
								<input id="goodsPicList{{idx}}_sort" name="goodsPicList[{{idx}}].sort" type="text" value="{{row.sort}}" maxlength="20" class="input-small  digits"/>
							</td>
							<shiro:hasPermission name="goods:goods:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#goodsPicList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var goodsPicRowIdx = 0, goodsPicTpl = $("#goodsPicTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(goods.goodsPicList)};
							for (var i=0; i<data.length; i++){
								addRow('#goodsPicList', goodsPicRowIdx, goodsPicTpl, data[i]);
								goodsPicRowIdx = goodsPicRowIdx + 1;
							}
						});
					</script>
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
								<th>本店售价<form:input path="prices" htmlEscape="false" maxlength="5" class="input-small "/><button onclick="generatePrice()"/> </th>
								<th>序号</th>
								<th>库存</th>
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
								<input id="goodsSkuList{{idx}}_spec1" name="goodsSkuList[{{idx}}].spec1" type="text" value="{{row.spec1}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_spec2" name="goodsSkuList[{{idx}}].spec2" type="text" value="{{row.spec2}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_price" name="goodsSkuList[{{idx}}].price" type="text" value="{{row.price}}" class="input-small "/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_sort" name="goodsSkuList[{{idx}}].sort" type="text" value="{{row.sort}}" maxlength="20" class="input-small  digits"/>
							</td>
							<td>
								<input id="goodsSkuList{{idx}}_stock" name="goodsSkuList[{{idx}}].stock" type="text" value="{{row.stock}}" maxlength="11" class="input-small "/>
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