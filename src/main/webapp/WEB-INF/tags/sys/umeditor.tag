<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="replace" type="java.lang.String" required="true" description="需要替换的textarea编号"%>
<%@ attribute name="height" type="java.lang.String" required="false" description="编辑器高度"%>
<%@ attribute name="readonly" type="java.lang.Boolean" required="false" description="是否查看模式"%>
<script type="text/javascript">include('ueditor_lib','${ctxStatic}/ueditor/',['themes/default/css/umeditor.css','third-party/jquery.min.js','umeditor.config.js','umeditor.min.js','lang/zh-cn/zh-cn.js']);</script>
<script type="text/javascript">

	var ${replace}um =  UM.getEditor("${replace}");
    ${replace}um.setHeight(${height});
    //<c:if test="${readonly}">
        ${replace}setDisabled();
    // </c:if>
    function ${replace}setDisabled() {
        ${replace}um.setDisabled('fullscreen');
        ${replace}disableBtn("enable");
    }
    function ${replace}disableBtn(str) {
        ${replace}um.setDisabled('fullscreen');
        var ${replace}div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(${replace}div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
</script>