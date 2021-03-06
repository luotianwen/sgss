<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="replace" type="java.lang.String" required="true" description="需要替换的textarea编号"%>
<%@ attribute name="height" type="java.lang.String" required="false" description="编辑器高度"%>
<%@ attribute name="readonly" type="java.lang.Boolean" required="false" description="是否查看模式"%>
<script type="text/javascript">include('umeditor_lib','${ctxStatic}/umeditor/',['themes/default/css/umeditor.css', 'umeditor.config.js','umeditor.min.js','lang/zh-cn/zh-cn.js']);</script>
<script type="text/javascript">

	var ${replace}um =  UM.getEditor("${replace}",{
        autoHeightEnabled:false,
        scaleEnabled:true//设置不自动调整高度
        //scaleEnabled {Boolean} [默认值：false]//是否可以拉伸长高，(设置true开启时，自动长高失效)
    });
    //<c:if test="${not empty height}">
    ${replace}um.setHeight(${height});
    //</c:if>
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