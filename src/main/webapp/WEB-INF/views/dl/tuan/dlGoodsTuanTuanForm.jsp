<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>团购商品管理</title>
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
		<li><a href="${ctx}/tuan/dlGoodsTuan/">团购商品列表</a></li>
		<li class="active">配置价格</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dlGoodsTuan" action="${ctx}/tuan/dlGoodsTuan/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		

		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="1000" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="artno" htmlEscape="false" maxlength="200" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主图：</label>
			<div class="controls">
				<form:hidden id="logo" path="logo" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="logo" type="images" uploadPath="/dlgoods" selectMultiple="false" attrno="artno" readonly="true"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">市场售价：</label>
			<div class="controls">
				<form:input path="marketPrice" htmlEscape="false" class="input-xlarge " readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">团购价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="state" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>


			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">规格1：</label>
			<div class="controls">
				<form:input path="spec1" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格2：</label>
			<div class="controls">
				<form:input path="spec2" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="100" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">轮播图：</label>
			<div class="controls">
				<form:hidden id="imgs" path="imgs" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="imgs" type="images" uploadPath="/dlgoods" selectMultiple="true"  attrno="artno" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算价：</label>
			<div class="controls">
				<form:input path="costPrice" htmlEscape="false" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品详情：</label>
			<div class="controls">
				<form:textarea id="details" htmlEscape="true" path="details" rows="4"  class="input-xxlarge" cssStyle="width:55%;height:400px;"/>
				<sys:ueditor replace="details"   />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">团购开始日期：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${dlGoodsTuan.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">团购结束日期：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${dlGoodsTuan.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>

			<div class="control-group">
				<label class="control-label">sku：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th><form:input path="spec1" htmlEscape="false" maxlength="20"  class="input-small " readonly="true"/></th>
								<th><form:input path="spec2" htmlEscape="false" maxlength="20" class="input-small " readonly="true"/></th>

								<th>库存  </th>

							</tr>
						</thead>
						<tbody id="dlTuanGoodsSkuList">
						</tbody>
						 <tfoot>
							 	</tfoot>
					</table>
					<script type="text/template" id="dlTuanGoodsSkuTpl">//<!--
						<tr id="dlTuanGoodsSkuList{{idx}}">
							<td class="hide">
								<input id="dlTuanGoodsSkuList{{idx}}_id" name="dlTuanGoodsSkuList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dlTuanGoodsSkuList{{idx}}_delFlag" name="dlTuanGoodsSkuList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dlTuanGoodsSkuList{{idx}}_spec1" name="dlTuanGoodsSkuList[{{idx}}].spec1" type="text" value="{{row.spec1}}" maxlength="2000" readonly="true" class="input-small "/>
							</td>
							<td>
								<input id="dlTuanGoodsSkuList{{idx}}_spec2" name="dlTuanGoodsSkuList[{{idx}}].spec2" type="text" value="{{row.spec2}}" maxlength="2000" readonly="true" class="input-small "/>
							</td>

							<td>
								<input id="dlTuanGoodsSkuList{{idx}}_stock" name="dlTuanGoodsSkuList[{{idx}}].stock" type="text" value="{{row.stock}}" maxlength="11"  class="input-small "/>
							</td>

						</tr>//-->
					</script>
					<script type="text/javascript">
						var dlTuanGoodsSkuRowIdx = 0, dlTuanGoodsSkuTpl = $("#dlTuanGoodsSkuTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dlGoodsTuan.dlTuanGoodsSkuList)};
							for (var i=0; i<data.length; i++){
								addRow('#dlTuanGoodsSkuList', dlTuanGoodsSkuRowIdx, dlTuanGoodsSkuTpl, data[i]);
								dlTuanGoodsSkuRowIdx = dlTuanGoodsSkuRowIdx + 1;
							}
						});

					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="tuan:dlGoodsTuan:tuanForm"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>