<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商品管理管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

            $("#inputForm").validate({
                submitHandler: function (form) {
                    var zk=true;
                    var f=false;
                    var d2= $("#goodsSkuList0_discount").val();
                    for (var i = 0; i < goodsSkuRowIdx; i++) {
                       var d= $("#goodsSkuList" + i + "_discount").val();
                           if(d<0.3){
                               f=true;
                               top.$.jBox.tip("折扣太低", "error", {persistent: true, opacity: 0});
                               break;
                           }
                        if(d2!=d){
                            zk=false;
                            f=true;
                            break;
                        }


                    }
                    if(!zk){
                        top.$.jBox.confirm("折扣不一致,是否继续",'系统提示',function(v,h,a){
                            if(v=='ok'){
                                f=false;
                                loading('正在提交，请稍等...');
                                form.submit();
                            }
                            return true;
                        } );
                    }
                    else{
                        if(!f){
                            loading('正在提交，请稍等...');
                            form.submit();
                        }
                    }



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
                $("#goodsSkuList" + i + "_discount").val(_price / _marketPrice );
            }
        }
        function generatediscount(){
            var price=$("#discount1").val();
            if(price.length==0||price==0){
                top.$.jBox.tip("折扣必填","erroe",{persistent:true,opacity:0});
                return false;
            }
            for (var i=0;i<goodsSkuRowIdx;i++){
                $("#goodsSkuList"+i+"_discount").val(price);
            }
            discountprice();
        }
        function discountprice(){
            for (var i=0;i<goodsSkuRowIdx;i++){
                var _marketPrice=$("#goodsSkuList"+i+"_marketPrice").val();
                var _discount= $("#goodsSkuList"+i+"_discount").val();
                $("#goodsSkuList"+i+"_price").val(_marketPrice*_discount);
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


        var curHtid ;
        function addpic(htid, isthumbnail,thumbnailmode,thumbnailheight ,thumbnailwidth ) {
            curHtid = htid;
            var iHeight = 600;
            var iWidth = 800;
            var iTop = 50; //(window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
            var iLeft =  300; //(window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
            var Url ='../../../upload.jsp?IsThumbnail=' + isthumbnail + '&ThumbnailMode='+ thumbnailmode +'&ThumbnailHeight=' + thumbnailheight + '&ThumbnailWidth=' + thumbnailwidth;
            window.open(Url,'addpicWin','height=' + iHeight + ', width=' + iWidth + ', top=' + iTop + ', left=' + iLeft + ', toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
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
                var keys =  serverPath + "|" + imgObj[i] + "|" + imgObj[i]+ ";";
                $("#"+curHtid).val($("#"+curHtid).val()+keys);
            }

        }
        function opentm(articleno){
            window.open("http://www.tianmasport.com/ms/order/quickOrder.shtml?articleno="+articleno,'tmopen');

        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active">同步商品</li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="goods" action="${ctx}/goods/goods/saveSync" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">分类：</label>
        <div class="controls">
            <select id="categoryId" name="categoryId" class="input-xlarge required">
                <option value="">-请选择-</option>
                <option value="17">跑步鞋</option>
                <option value="18">篮球鞋</option>
                <option value="19">足球鞋</option>
                <option value="20">网球鞋</option>
                <option value="27">其它</option>
                <option value="28">拖鞋</option>
                <option value="29">凉鞋</option>
                <option value="30">羽毛球鞋</option>
                <option value="31">多用途训练鞋</option>
                <option value="32">运动包</option>
                <option value="33">T恤</option>
                <option value="34">POLO</option>
                <option value="35">背心</option>
                <option value="36">衬衫</option>
                <option value="37">短裤</option>
                <option value="38">中长裤</option>
                <option value="39">长裤</option>
                <option value="40">短裙</option>
                <option value="41">内衣</option>
                <option value="42">羽绒服</option>
                <option value="43">卫衣</option>
                <option value="44">外套</option>
                <option value="58">跑步鞋</option>
                <option value="59">篮球鞋</option>
                <option value="60">其它</option>
                <option value="61">羽毛球鞋</option>
                <option value="62">运动包</option>
                <option value="63">T恤</option>
                <option value="64">裤子</option>
                <option value="65">羽绒服</option>
                <option value="66">外套</option>
                <option value="67">器材</option>
                <option value="45">篮球</option>
                <option value="46">足球</option>
                <option value="47">羽毛球</option>
                <option value="48">护具</option>
                <option value="49">帽子</option>
                <option value="50">袜子</option>
                <option value="51">网球</option>
                <option value="52">器材</option>
                <option value="53">厨房用具</option>
                <option value="54">家纺</option>
                <option value="55">家居用品</option>
                <option value="56">商旅用品</option>
                <option value="57">生活家电</option>
                <option value="70">健康膳食</option>
                <option value="68">爆款推荐</option>
                <option value="69">咪咕文化健身器材专区</option>
                <option value="73">2019新品推荐</option>
                <option value="74">抄底一线品牌</option>
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
            <a style="cursor: pointer" onclick="opentm('${goods.artno}')">查询价格</a>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">主图：</label>
        <div class="controls">
            <form:textarea id="logo" path="logo" htmlEscape="false"  rows="2"  class="input-xxlarge required"/>

            <a href="http://op.yoyound.com${goods.logo}"   <%--download="logo.jpg" --%> target="_blank"><img src="http://op.yoyound.com${goods.logo}" width="60px"> 下载</a>
         <%--   <input type="button"  onclick="addpic('logo',1,'HW',100,100)" class="btn " value="上传" /> <input type="button" class="btn  "  onclick="$('#logo').val('')" value="清空"/>
--%>
        </div>
    </div>



    <div class="control-group">
        <label class="control-label">品牌：</label>
        <div class="controls">

            <select id="brand.id" name="brand.id" class="input-xlarge required">
                <option value="">-请选择-</option>
                <option value="273">21st scooter</option>
                <option value="79">ACA</option>
                <option value="266">AfterShokz/韶音</option>
                <option value="247">AIGLE</option>
                <option value="461">AMERICAN TOURISTER美旅</option>
                <option value="29">ANEMAQEN/阿尼玛卿</option>
                <option value="150">Apple/苹果</option>
                <option value="135">AQ</option>
                <option value="23">ARCTERYX/始祖鸟</option>
                <option value="26">ASICS/亚瑟士</option>
                <option value="312">AWAYES</option>
                <option value="291">AXE</option>
                <option value="388">A自由</option>
                <option value="328">BABYLISS</option>
                <option value="217">baffy</option>
                <option value="30">BD/黑钻</option>
                <option value="137">BEASUN/贝尔顺</option>
                <option value="335">BLACK DECKER</option>
                <option value="194">BLACKICE/黑冰</option>
                <option value="375">BOSCH博世</option>
                <option value="325">bose博士</option>
                <option value="35">BOSS</option>
                <option value="147">BRS/兄弟捷登</option>
                <option value="128">BUFF/百福</option>
                <option value="198">BULL公牛</option>
                <option value="64">Calvin Klein</option>
                <option value="165">Camelbak/驼峰</option>
                <option value="188">CARTELO鳄鱼</option>
                <option value="409">CASIO</option>
                <option value="394">CASSINO卡西诺</option>
                <option value="360">CERRUTI1881</option>
                <option value="397">CHIGO志高</option>
                <option value="446">CODA蔻达</option>
                <option value="94">CRAFT</option>
                <option value="242">CRISPI</option>
                <option value="464">Cuisinart</option>
                <option value="182">Debo德铂</option>
                <option value="329">DELESY</option>
                <option value="127">Dowell/多为</option>
                <option value="155">Dr.Wee</option>
                <option value="36">Dustie达氏</option>
                <option value="233">dustie达氏</option>
                <option value="378">EMPORIO ARMANI</option>
                <option value="138">ENERGETICS</option>
                <option value="132">EX2/伊海诗</option>
                <option value="258">FLEXFIT</option>
                <option value="462">FORUOR</option>
                <option value="47">GARMIN/佳明</option>
                <option value="122">Geek&nbsp;Aire</option>
                <option value="265">GREGORY</option>
                <option value="467">guzzini</option>
                <option value="214">HAERS/哈尔斯</option>
                <option value="209">HALCYON和正</option>
                <option value="250">Hardwear山浩</option>
                <option value="277">hatson</option>
                <option value="267">HIMAGET喜玛格</option>
                <option value="389">HOWaw</option>
                <option value="468">HUGOBOSS</option>
                <option value="70">I DO</option>
                <option value="105">Jack Wolfskin</option>
                <option value="203">JanSport</option>
                <option value="402">JOHN BOSS</option>
                <option value="129">JULBO/嘉宝</option>
                <option value="24">KAILAS/凯乐石</option>
                <option value="431">Kappa</option>
                <option value="156">keds</option>
                <option value="130">KEEN/科恩</option>
                <option value="65">KENZO（虎头）</option>
                <option value="471">koziol</option>
                <option value="48">LEKI</option>
                <option value="302">LG</option>
                <option value="131">LIZARD/蜥蜴</option>
                <option value="27">LOWA</option>
                <option value="125">LP</option>
                <option value="324">luma</option>
                <option value="285">marasil玛瑞莎</option>
                <option value="99">MARROW</option>
                <option value="257">MASTERS</option>
                <option value="348">MAXIMS</option>
                <option value="151">mckinley</option>
                <option value="352">MD</option>
                <option value="276">MEIKAN</option>
                <option value="101">MG-V</option>
                <option value="239">micro米高</option>
                <option value="331">MINE寐</option>
                <option value="275">MONTECH蒙特罗</option>
                <option value="100">MOPS</option>
                <option value="119">NAL乐基因</option>
                <option value="106">NATHAN</option>
                <option value="93">NB</option>
                <option value="96">NBA</option>
                <option value="153">NewEra</option>
                <option value="254">NIKKO</option>
                <option value="377">NITTAYA</option>
                <option value="133">NORTHLAND/诺诗兰</option>
                <option value="432">OLAF LEGENG 奥拉芙</option>
                <option value="472">Omnioutil</option>
                <option value="146">Onlyhome</option>
                <option value="212">oppo</option>
                <option value="32">OSPREY</option>
                <option value="208">OUDON</option>
                <option value="408">Panasonic松下</option>
                <option value="405">PARKER</option>
                <option value="49">PATAGONIA巴塔哥尼亚</option>
                <option value="415">PEGASI</option>
                <option value="261">POC</option>
                <option value="197">POVOS奔腾</option>
                <option value="136">PROTOUCH</option>
                <option value="262">REUSCH</option>
                <option value="470">Rigamonti</option>
                <option value="25">SALOMON/萨洛蒙</option>
                <option value="158">SAMSTRONG/杉木山装</option>
                <option value="121">SAUCONY/索康尼</option>
                <option value="124">SEA&nbsp;TO&nbsp;SUMMIT</option>
                <option value="177">SELENO/萨里诺</option>
                <option value="326">shark</option>
                <option value="143">SKG</option>
                <option value="264">SMARTWOOL</option>
                <option value="190">SNOOPY</option>
                <option value="98">SOFSOLE</option>
                <option value="112">Speedo速比涛</option>
                <option value="398">STANLEY史丹利</option>
                <option value="474">StefanPlast</option>
                <option value="457">Stratic平流层</option>
                <option value="123">SUNREE/山力士</option>
                <option value="28">SUUNTO/颂拓</option>
                <option value="426">SYOSS丝蕴</option>
                <option value="15">TAPA</option>
                <option value="54">TCL</option>
                <option value="114">TECNOPRO</option>
                <option value="22">THE NORTH FACE</option>
                <option value="269">THERM-A-REST</option>
                <option value="356">TIGER虎牌</option>
                <option value="390">TOSHIBA</option>
                <option value="31">TREK崔克</option>
                <option value="313">T优家纺</option>
                <option value="473">Vacuumsaver</option>
                <option value="126">VASQUE/威斯</option>
                <option value="248">VAUEDE</option>
                <option value="86">VICTOR</option>
                <option value="213">vivo</option>
                <option value="469">VoNo</option>
                <option value="351">WEDGWOOD</option>
                <option value="391">Whirlpool</option>
                <option value="134">X-SOCKS</option>
                <option value="172">YHANA</option>
                <option value="392">ZIPPO</option>
                <option value="435">zippo</option>
                <option value="475">Ψ˼Զ</option>
                <option value="298">七度空间</option>
                <option value="439">万宝龙</option>
                <option value="89">万斯</option>
                <option value="454">三瑞</option>
                <option value="287">世军</option>
                <option value="77">东菱</option>
                <option value="204">丝语棠</option>
                <option value="456">中体倍力</option>
                <option value="339">中粮</option>
                <option value="338">中茶</option>
                <option value="20">乐扣乐扣</option>
                <option value="57">乐美雅</option>
                <option value="244">乐飞叶</option>
                <option value="314">九安</option>
                <option value="357">九阳</option>
                <option value="293">云南白药</option>
                <option value="478">亨得</option>
                <option value="9">伊莱克斯</option>
                <option value="289">优贝</option>
                <option value="246">伯希和</option>
                <option value="414">佳奥</option>
                <option value="403">佳洁士</option>
                <option value="232">傲胜OSIM</option>
                <option value="234">充值卡</option>
                <option value="420">光明</option>
                <option value="385">全瓷时代</option>
                <option value="17">内野</option>
                <option value="323">军拓</option>
                <option value="240">冠军</option>
                <option value="436">凌美</option>
                <option value="344">加维利亚</option>
                <option value="152">动哈</option>
                <option value="444">动物之光</option>
                <option value="180">勃兰匠记</option>
                <option value="7">北京移动</option>
                <option value="149">北极狐</option>
                <option value="10">北欧欧慕</option>
                <option value="110">匡威</option>
                <option value="428">十足酷</option>
                <option value="211">华为</option>
                <option value="410">博朗</option>
                <option value="228">博洋宝贝</option>
                <option value="365">卡宴</option>
                <option value="91">卡帕</option>
                <option value="216">卡萨帝Casarte</option>
                <option value="117">卡蒙</option>
                <option value="84">卡骆驰</option>
                <option value="382">双河</option>
                <option value="56">双立人</option>
                <option value="381">古船</option>
                <option value="173">可欣</option>
                <option value="321">史丹利</option>
                <option value="72">史努比</option>
                <option value="268">咪咕</option>
                <option value="306">品胜</option>
                <option value="43">哆啦A梦</option>
                <option value="178">哥仑布/Kolumb</option>
                <option value="111">哥伦比亚Columbia</option>
                <option value="272">喜德盛</option>
                <option value="411">喜芙妮</option>
                <option value="168">嘉炖</option>
                <option value="362">嘉铭桐城</option>
                <option value="271">嘟迪</option>
                <option value="167">土匠坊</option>
                <option value="243">土拨鼠</option>
                <option value="231">域邦</option>
                <option value="458">塞翁福</option>
                <option value="13">外交官</option>
                <option value="68">多样屋</option>
                <option value="53">大嘴猴</option>
                <option value="215">奥克斯</option>
                <option value="253">奥索卡</option>
                <option value="434">奥罗拉</option>
                <option value="430">奥鼎康</option>
                <option value="395">好媳妇</option>
                <option value="481">好时达</option>
                <option value="447">好美特</option>
                <option value="451">如艺</option>
                <option value="109">威尔胜</option>
                <option value="290">威露士</option>
                <option value="169">安尼泰科</option>
                <option value="107">安德玛</option>
                <option value="88">安踏</option>
                <option value="367">家乐事</option>
                <option value="21">家魔仕</option>
                <option value="76">宾利</option>
                <option value="52">富安娜</option>
                <option value="477">小巨蛋</option>
                <option value="220">小狗</option>
                <option value="187">小米</option>
                <option value="343">小罐茗茶</option>
                <option value="297">小迪小妮</option>
                <option value="51">尤尼克斯</option>
                <option value="141">山水</option>
                <option value="148">山瑞</option>
                <option value="181">峰尚美</option>
                <option value="245">布来亚克</option>
                <option value="102">希格SIGG</option>
                <option value="286">幸运谷</option>
                <option value="59">康佳</option>
                <option value="311">康宁</option>
                <option value="366">康巴赫</option>
                <option value="42">弗兰西诺</option>
                <option value="379">张一元</option>
                <option value="85">彪马</option>
                <option value="345">御之满</option>
                <option value="196">德世朗</option>
                <option value="374">德国海牌</option>
                <option value="445">快乐厨房（Happy kitchen）</option>
                <option value="255">快乐狐狸</option>
                <option value="251">思凯乐</option>
                <option value="140">惠而浦</option>
                <option value="263">慕山</option>
                <option value="401">慕思</option>
                <option value="80">戴森</option>
                <option value="229">戴洛伦</option>
                <option value="438">拓乐</option>
                <option value="346">拜尔</option>
                <option value="235">捷瑞特JOROTO</option>
                <option value="309">捷豹JAGUAR</option>
                <option value="279">探拓</option>
                <option value="4">探路者</option>
                <option value="33">摩力</option>
                <option value="219">摩飞</option>
                <option value="423">文正</option>
                <option value="241">斐乐</option>
                <option value="115">斯伯丁</option>
                <option value="95">斯凯奇</option>
                <option value="210">斯多美</option>
                <option value="164">新宝</option>
                <option value="12">新秀丽</option>
                <option value="425">旭升</option>
                <option value="305">智伴</option>
                <option value="368">本因Boii</option>
                <option value="142">朵彩</option>
                <option value="6">李宁</option>
                <option value="97">李维斯</option>
                <option value="161">杜邦</option>
                <option value="206">松下</option>
                <option value="252">极星</option>
                <option value="283">果派</option>
                <option value="380">果邦汇</option>
                <option value="170">柔先生</option>
                <option value="349">格兰仕</option>
                <option value="230">格来德</option>
                <option value="205">梦洁</option>
                <option value="422">梵卡莎</option>
                <option value="443">梵希卡</option>
                <option value="427">棉之爱</option>
                <option value="288">榄圣</option>
                <option value="157">欧亚马</option>
                <option value="195">欧克利</option>
                <option value="202">欧博</option>
                <option value="442">欧夹</option>
                <option value="407">欧姆龙</option>
                <option value="413">欧普照明</option>
                <option value="359">欧美达</option>
                <option value="18">歌然</option>
                <option value="386">武当山</option>
                <option value="340">每日坚果</option>
                <option value="453">氏氏美</option>
                <option value="315">水乡宝</option>
                <option value="63">水星家纺</option>
                <option value="418">汇源</option>
                <option value="118">汉道</option>
                <option value="460">沐恩</option>
                <option value="327">沙宣</option>
                <option value="259">沸点</option>
                <option value="304">法国红酒</option>
                <option value="316">法拉萨</option>
                <option value="226">波米欧利</option>
                <option value="284">泰尼卡</option>
                <option value="330">泰昌</option>
                <option value="179">泰邦</option>
                <option value="174">洁丽雅</option>
                <option value="373">洪福来</option>
                <option value="437">派克</option>
                <option value="307">浦诺菲</option>
                <option value="162">海尔</option>
                <option value="376">海贼王</option>
                <option value="295">清风</option>
                <option value="480">温斯顿</option>
                <option value="448">满婷</option>
                <option value="318">满洲稻</option>
                <option value="201">澳得迈</option>
                <option value="37">灿坤</option>
                <option value="225">炊大皇</option>
                <option value="334">焕醒</option>
                <option value="369">熊本熊</option>
                <option value="186">爱乐优</option>
                <option value="145">爱仕达</option>
                <option value="171">爱华仕</option>
                <option value="75">爱唯仕</option>
                <option value="429">爱奇屋</option>
                <option value="67">爱家</option>
                <option value="238">爱康</option>
                <option value="399">爱沃德</option>
                <option value="183">爱登宝罗</option>
                <option value="159">爱路客</option>
                <option value="139">牧高笛</option>
                <option value="303">狮王</option>
                <option value="108">狼爪</option>
                <option value="280">猛犸象</option>
                <option value="400">王老吉</option>
                <option value="452">王记膳燕</option>
                <option value="249">玛丁图</option>
                <option value="281">班尼路</option>
                <option value="440">瑞士军刀</option>
                <option value="406">瑞士维氏</option>
                <option value="192">申川</option>
                <option value="8">百得</option>
                <option value="384">百草味</option>
                <option value="336">百达星连</option>
                <option value="14">碧然德</option>
                <option value="19">礼想家</option>
                <option value="337">福临门</option>
                <option value="347">福玛特</option>
                <option value="82">米技</option>
                <option value="320">素万</option>
                <option value="433">索利斯Solis</option>
                <option value="237">红双喜</option>
                <option value="383">红星</option>
                <option value="176">纵贯线</option>
                <option value="62">维科</option>
                <option value="294">维达</option>
                <option value="396">罗技</option>
                <option value="61">罗莱</option>
                <option value="221">美国西屋Westinghous</option>
                <option value="74">美旅</option>
                <option value="92">美津浓</option>
                <option value="274">美洲狮</option>
                <option value="236">美特飞</option>
                <option value="222">美的</option>
                <option value="449">美缘登</option>
                <option value="465">美膳雅</option>
                <option value="189">美菱</option>
                <option value="50">耐克</option>
                <option value="78">联创</option>
                <option value="66">肖邦</option>
                <option value="69">膳魔师</option>
                <option value="184">自由电</option>
                <option value="160">舒华</option>
                <option value="282">舒莱狮</option>
                <option value="354">艺色</option>
                <option value="455">艾丝雅兰</option>
                <option value="361">艾贝拉</option>
                <option value="175">艾铂赫</option>
                <option value="341">芬帝</option>
                <option value="296">花姿</option>
                <option value="300">花王</option>
                <option value="353">芳恩</option>
                <option value="58">苏泊尔</option>
                <option value="299">苏菲</option>
                <option value="441">英国泰通</option>
                <option value="227">茗振</option>
                <option value="83">茵宝</option>
                <option value="191">荣事达</option>
                <option value="370">莉碧儿</option>
                <option value="223">莱德斯</option>
                <option value="372">菲仕朗</option>
                <option value="419">蒙牛</option>
                <option value="358">蓝之羽</option>
                <option value="322">蓝旅</option>
                <option value="387">蓝月亮</option>
                <option value="278">薏凡特</option>
                <option value="199">蘭之魅</option>
                <option value="421">西王食品</option>
                <option value="73">西铁城</option>
                <option value="332">诺依曼NOYOKE</option>
                <option value="200">诺贝达</option>
                <option value="450">诺顿</option>
                <option value="270">象博士</option>
                <option value="207">象印</option>
                <option value="310">贝利安</option>
                <option value="120">赞斯特</option>
                <option value="466">超维Choworld</option>
                <option value="333">车管家</option>
                <option value="371">迈克达威</option>
                <option value="104">迈森兰</option>
                <option value="193">迪士尼</option>
                <option value="166">迪穆</option>
                <option value="224">酷彩</option>
                <option value="60">金丝莉</option>
                <option value="364">金壹农</option>
                <option value="144">金稻</option>
                <option value="71">金银物语</option>
                <option value="417">金龙鱼</option>
                <option value="90">锐步</option>
                <option value="479">阳澄湖</option>
                <option value="350">阿芙</option>
                <option value="34">阿迪达斯</option>
                <option value="218">阿道夫</option>
                <option value="319">陌上炊烟</option>
                <option value="308">雷明顿</option>
                <option value="301">露华浓</option>
                <option value="476">顽鹿</option>
                <option value="317">领豪（Russell Hobbs）</option>
                <option value="81">飞利浦</option>
                <option value="424">飞科</option>
                <option value="363">首农</option>
                <option value="416">香雪</option>
                <option value="11">馨亭</option>
                <option value="55">马克西姆</option>
                <option value="292">高露洁</option>
                <option value="87">鬼冢虎</option>
                <option value="256">魔行</option>
                <option value="5">鸟巢</option>
                <option value="260">鸭嘴兽</option>
                <option value="103">麦格霍斯</option>
                <option value="116">黄蚂蚁</option>
                <option value="488">mvp</option>
                <option value="489">艾美特</option>
                <option value="490">BOBOT </option>
                <option value="491">BWT</option>
                <option value="492">TOMMY </option>
                <option value="493">DKNY</option>
                <option value="494">Contigo</option>
                <option value="495">斯立德</option>
                <option value="497">雪狼</option>
                <option value="498">厨夫人</option>
                <option value="499">绿盾</option>
                <option value="500">茉汰</option>
                <option value="501">小黄人</option>
                <option value="502">澳司迪</option>
                <option value="503">国殷</option>
                <option value="504">宝洁</option>
                <option value="505">妮飘</option>
                <option value="506">天然之扉v</option>
                <option value="507">白元</option>
                <option value="508">布尼斯</option>
                <option value="509">赛巴迪</option>
                <option value="510">最生活</option>
                <option value="511">乐克尔</option>
                <option value="512">鱼跃</option>
                <option value="513">IBX</option>
                <option value="514">霍尼韦尔</option>
                <option value="515">八瑞祥</option>
                <option value="517">宫颐府</option>
                <option value="518">倍思</option>
                <option value="519">现代</option>
                <option value="520">香港美诚</option>
                <option value="521">法蒂欧 </option>
                <option value="522">香港帝皇 </option>
                <option value="523">凯威 </option>
                <option value="524"> tourmark </option>
                <option value="525">福旺稻稼 </option>
                <option value="526">金号 </option>
                <option value="527"> 大迈</option>
                <option value="528"> hello Kitty </option>
                <option value="529"> 奔富酒园 </option>
                <option value="530"> 纳尔斯伯爵  </option>
                <option value="531">皇家塞尔塔   </option>
                <option value="532"> 塞上优果 </option>
                <option value="533"> 良泡</option>
                <option value="534"> 馨海渔港</option>
                <option value="535"> 偕牌牧场 </option>
                <option value="536"> 古渡茯茶  </option>
                <option value="537">福臻夷萃 </option>
                <option value="538">绿伯爵 </option>
                <option value="539">生机绿源 </option>
                <option value="540">哥利斯 </option>
                <option value="541">香港美心 </option>
                <option value="542">林氏渔夫 </option>
                <option value="543">活海鲜 </option>
                <option value="544">星巴克 </option>


                <option value="545"> 科沃斯   </option>
                <option value="546">诺伊曼 </option>

                <option value="548">  强生</option>
                <option value="549">  华帝 </option>
                <option value="550">  ELLE   </option>
                <option value="551">都市太太   </option>
                <option value="552"> 斯阁睿  </option>
                <option value="553"> WMF  </option>
                <option value="554"> 伯尔尼斯</option>
                <option value="555">  小鸭</option>
                <option value="556">  伊莱特   </option>
                <option value="557">Targus </option>
                <option value="558">WRC </option>
                <option value="559">LEXON  </option>
                <option value="560">HDST  </option>
                <option value="561">DALEN  </option>
                <option value="562">五月树  </option>
                <option value="563">堂皇 </option>
                <option value="564"> LUFTRUM  </option>
                <option value="565">  AICHEN爱妻   </option>
                <option value="566"> LotusGrill  </option>
                <option value="567"> Marna </option>
                <option value="568">  美居苹果</option>



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
                <a href="http://op.yoyound.com${item}"  <%-- download="${status.index+1}.jpg"--%> target="_blank" ><img src="http://op.yoyound.com${item}" width="60px"> 下载</a>
             </c:forEach>
            <form:textarea id="details" path="detail.details" htmlEscape="false"  rows="4"  class="input-xxlarge required"/>
           <%--  <input type="button"  onclick="addpic('details',1,'W',0,800)" class="btn " value="上传" />--%>
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
                    <th>成本</th>
                    <th>成本折扣</th>
                    <th>动力结算价<input id="prices" class="input-small valid" type="text" value="" maxlength="20"> <input
                            id="btnPrice" class="btn btn-primary" type="button" value="价格"
                            onclick="generatePrice()"> <%--<form:input path="prices" htmlEscape="false" maxlength="5" class="input-small "/> --%>
                    </th>
                    <th>市场价<input id="marketprices" class="input-small valid" type="text" value="" maxlength="20">
                        <input id="btnPrice" class="btn btn-primary" type="button" value="市场价"
                               onclick="generatemarketprices()"> <%--<form:input path="prices" htmlEscape="false" maxlength="5" class="input-small "/> --%>
                    </th>
                    <shiro:hasPermission name="goods:goods:settlement">

                        <th>折扣<input id="discount1"   class="input-small valid" type="text" value="" maxlength="20"> <input   class="btn btn-primary" type="button" value="折扣" onclick="generatediscount()"> </th>
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
								 {{row.settlementPrice}}
							</td>
							<td>
								 {{row.settlementDiscount}}
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