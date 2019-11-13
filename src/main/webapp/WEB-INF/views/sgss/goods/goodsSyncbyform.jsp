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
            <a style="cursor: pointer" onclick="opentm('${goods.artno}')">查询价格</a>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">主图：</label>
        <div class="controls">
            <form:textarea id="logo" path="logo" htmlEscape="false"  rows="2"  class="input-xxlarge required"/>
            <a href="http://op.yoyound.com${goods.logo}"   <%--download="logo.jpg" --%> target="_blank"><img
                    src="http://op.yoyound.com${goods.logo}" width="60px"> 下载</a>
           <%-- <input type="button" onclick="addpic('logo',1,'HW',100,100)" class="btn " value="上传"/> <input type="button"
                                                                                                          class="btn  "
                                                                                                          onclick="$('#logo').val('')"
                                                                                                          value="清空"/>--%>

        </div>
    </div>


    <div class="control-group">
        <label class="control-label">品牌：</label>
        <div class="controls">
            <select id="brand.id" name="brand.id" class="input-xlarge required">

                    <option value="-1">-请选择-</option><option value="86">21st csooter</option><option value="261">3M</option><option value="10">ACA</option><option value="115">AQ护具</option><option value="209">BABYLISS</option><option value="402">Bio Star</option><option value="106">Black Diamond</option><option value="120">BOSCH博世</option><option value="319">Bose</option><option value="99">CalvinKlein</option><option value="117">CASIO</option><option value="254">CERRUTI1881</option><option value="330">CODA蔻达</option><option value="431">COEN ANDERS</option><option value="194">COMPRESPORT</option><option value="159">CRAFT</option><option value="113">Crispi</option><option value="407">Cuisinart</option><option value="271">DELESY</option><option value="144">Discovery</option><option value="165">Dowell/多为</option><option value="164">Dr.Wee</option><option value="349">ENERGETICS</option><option value="101">ETIREL</option><option value="102">ETIREL</option><option value="320">FLEXFIT</option><option value="389">FORUOR</option><option value="163">FUNKO</option><option value="292">GeekAire</option><option value="237">GoPRO</option><option value="100">GREGORY</option><option value="406">guzzini</option><option value="202">Hatson</option><option value="289">HOWaw</option><option value="68">hugoboss</option><option value="426">humangear</option><option value="162">IDO</option><option value="62">JANSPORT</option><option value="138">Jason Hill Quality Goods</option><option value="185">JOHNBOSS</option><option value="405">koziol</option><option value="135">LACOSTE</option><option value="392">le coq sportif</option><option value="131">LEE</option><option value="308">LEKI</option><option value="327">LG</option><option value="152">Lotto</option><option value="118">LOWA</option><option value="225">Masters</option><option value="221">MAXIMS</option><option value="151">MD </option><option value="217">MG-V</option><option value="104">mico sport</option><option value="297">micro米高</option><option value="214">MINE寐</option><option value="137">MLB</option><option value="227">mops</option><option value="153">MUNKEES</option><option value="429">MVP</option><option value="52">NB</option><option value="139">NBA</option><option value="98">NEW ERA</option><option value="145">NIKKO</option><option value="403">Omnioutil</option><option value="294">Onlyhome</option><option value="404">oppo</option><option value="63">OSPREY</option><option value="274">OUDON</option><option value="306">PARKER</option><option value="171">PEGASI</option><option value="311">POC</option><option value="313">POVOS奔腾</option><option value="328">PRO TOUCH</option><option value="329">PRO-TEC</option><option value="399">remix</option><option value="375">REUSCH</option><option value="397">Rigamonti</option><option value="376">Sea to Summit</option><option value="396">shark</option><option value="358">SKG</option><option value="356">SMARTWOOL</option><option value="355">SNOOPY</option><option value="353">SOFSOLE</option><option value="400">StefanPlast</option><option value="395">subcrew</option><option value="377">TCL</option><option value="394">tecnopre</option><option value="285">TECNOPRO</option><option value="332">TIGER虎牌446</option><option value="105">TOSHIBA</option><option value="393">undefeated</option><option value="398">Vacuumsaver</option><option value="317">vivo</option><option value="401">VoNo</option><option value="416">WEDGWOOD</option><option value="412">X-BIONIC</option><option value="384">YHANA</option><option value="383">YOYOUND</option><option value="380">ZIPPO </option><option value="304">万宝龙</option><option value="114">万斯</option><option value="252">世军</option><option value="253">世军</option><option value="280">东菱</option><option value="367">丝蕴</option><option value="24">中粮</option><option value="28">乐基因</option><option value="307">乐扣乐扣</option><option value="43">乐斯菲斯</option><option value="78">乐美雅</option><option value="301">九安</option><option value="26">九阳</option><option value="168">云南白药</option><option value="38">亚瑟士</option><option value="146">亨得</option><option value="96">伊海诗</option><option value="391">伊莱克斯</option><option value="390">优贝</option><option value="30">伯希和</option><option value="183">佳奥</option><option value="129">佳明</option><option value="386">傲胜OSIM</option><option value="169">兄弟捷登</option><option value="372">光明</option><option value="335">全瓷时代</option><option value="256">公牛</option><option value="436">其他</option><option value="90">内野家纺</option><option value="336">军拓</option><option value="147">凯威</option><option value="174">加维利亚</option><option value="337">动哈</option><option value="323">勃兰匠记</option><option value="85">北极狐</option><option value="298">北欧欧慕</option><option value="36">匡威</option><option value="255">十足酷</option><option value="326">华为</option><option value="340">博朗</option><option value="344">博朗</option><option value="208">博洋宝贝</option><option value="324">卡宴</option><option value="53">卡帕</option><option value="427">卡帝乐鳄鱼</option><option value="428">卡帝乐鳄鱼</option><option value="273">卡萨帝</option><option value="136">卡蒙</option><option value="295">卡西诺</option><option value="41">卡骆驰</option><option value="170">双河</option><option value="6">双立人</option><option value="182">古船</option><option value="350">史丹利</option><option value="205">和正</option><option value="206">和正</option><option value="173">品胜</option><option value="259">哆啦A梦</option><option value="200">哈尔斯</option><option value="44">哥伦比亚</option><option value="157">喜德盛</option><option value="196">喜玛格</option><option value="346">喜芙妮</option><option value="435">嗨小艺人</option><option value="188">嘉宝</option><option value="211">嘉炖</option><option value="341">嘟迪</option><option value="181">土匠坊</option><option value="132">土拨鼠</option><option value="42">圣康尼</option><option value="325">域邦</option><option value="387">塞翁福</option><option value="4">外交官</option><option value="268">多样屋</option><option value="288">大嘴猴</option><option value="275">奥克斯</option><option value="161">奥罗拉</option><option value="19">奥鼎康</option><option value="283">好媳妇</option><option value="247">好美特</option><option value="130">始祖鸟</option><option value="108">威克多</option><option value="97">威尔胜</option><option value="143">威露士</option><option value="158">安尼泰科</option><option value="51">安德玛</option><option value="240">家乐事</option><option value="266">家魔仕</option><option value="91">宾利</option><option value="212">富安娜 </option><option value="74">小巨蛋</option><option value="77">小狗</option><option value="103">小米</option><option value="134">小罐茗茶</option><option value="58">尤尼克斯</option><option value="339">山力士</option><option value="243">山水</option><option value="269">山瑞</option><option value="176">巴塔哥尼亚</option><option value="215">布尼斯</option><option value="361">平流层</option><option value="23">幸运谷</option><option value="75">幸运谷</option><option value="197">康佳</option><option value="94">康宁</option><option value="126">康巴赫</option><option value="388">开途</option><option value="267">弗兰西诺 </option><option value="244">张一元</option><option value="35">彪马</option><option value="87">德世朗</option><option value="207">德铂</option><option value="186">志高</option><option value="223">惠而浦</option><option value="64">慕山</option><option value="220">慕思</option><option value="3">戴森</option><option value="226">戴洛伦家纺</option><option value="203">拜尔</option><option value="13">捷瑞特</option><option value="228">探拓</option><option value="48">探路者</option><option value="156">摩力</option><option value="155">摩飞</option><option value="46">斯伯丁</option><option value="39">斯凯奇</option><option value="192">斯多美</option><option value="434">斯立德</option><option value="437">新东方</option><option value="438">新东方</option><option value="92">新秀丽</option><option value="154">旭升</option><option value="230">智伴</option><option value="231">智伴</option><option value="441">智能邦</option><option value="442">智能邦</option><option value="432">最生活</option><option value="133">本因</option><option value="31">杉木</option><option value="374">杉木山装</option><option value="29">李宁</option><option value="179">李维斯</option><option value="218">杜邦</option><option value="70">松下</option><option value="257">果邦汇</option><option value="258">果邦汇</option><option value="80">柔先生</option><option value="187">格兰仕</option><option value="286">格来德</option><option value="125">梦洁家纺</option><option value="242">梵卡莎</option><option value="213">棉之爱</option><option value="293">榄圣</option><option value="128">欧亚马</option><option value="60">欧克利</option><option value="150">欧博</option><option value="210">欧姆龙</option><option value="199">欧普照明</option><option value="127">欧美达</option><option value="287">歌然</option><option value="322">水乡宝</option><option value="123">水星家纺</option><option value="82">汉道</option><option value="20">沁园</option><option value="439">沃品</option><option value="277">沐恩</option><option value="79">沙宣</option><option value="229">沸点</option><option value="167">法国红酒</option><option value="16">法拉萨</option><option value="315">法拉萨</option><option value="281">波米欧利</option><option value="300">泰尼卡</option><option value="190">泰昌</option><option value="232">泰邦</option><option value="124">洁丽雅</option><option value="233">洪福来</option><option value="234">浦诺菲</option><option value="284">海尔</option><option value="191">海牌</option><option value="140">海贼王</option><option value="422">添柏岚</option><option value="250">满洲稻</option><option value="224">澳得迈</option><option value="241">灿坤</option><option value="180">炊大皇</option><option value="264">焕醒</option><option value="282">熊本熊</option><option value="321">爱乐优</option><option value="260">爱仕达</option><option value="236">爱华仕</option><option value="265">爱唯仕</option><option value="18">爱奇屋</option><option value="291">爱康</option><option value="166">爱沃德</option><option value="276">爱登宝罗</option><option value="81">爱路客</option><option value="57">牧高笛</option><option value="22">狮王</option><option value="59">狼爪</option><option value="47">猛犸象</option><option value="299">王老吉</option><option value="249">王记膳燕</option><option value="272">玛瑞莎</option><option value="296">班尼路</option><option value="302">瑞士维氏</option><option value="309">申川</option><option value="310">申川</option><option value="312">百得</option><option value="88">百福</option><option value="314">百草味</option><option value="189">百达星联</option><option value="334">碧然德</option><option value="360">福临门</option><option value="363">福玛特</option><option value="364">米技</option><option value="369">素万</option><option value="370">索利斯Solis</option><option value="111">红双喜</option><option value="373">纵贯线</option><option value="177">维科</option><option value="195">罗技</option><option value="12">罗莱</option><option value="73">罗莱家纺</option><option value="32">美中宜和</option><option value="69">美国西屋</option><option value="72">美旅</option><option value="54">美津浓</option><option value="359">美特飞</option><option value="66">美的</option><option value="84">美看</option><option value="67">美膳雅</option><option value="2">耐克</option><option value="9">联创</option><option value="357">肖邦</option><option value="149">肯励</option><option value="7">膳魔师</option><option value="354">自由电</option><option value="352">舒华</option><option value="362">舒莱狮</option><option value="351">艺色</option><option value="366">艾丝雅兰</option><option value="365">艾贝拉</option><option value="222">艾铂赫</option><option value="251">芬帝</option><option value="368">花姿</option><option value="17">花王</option><option value="204">芳恩家纺</option><option value="122">苏泊尔</option><option value="347">苹果</option><option value="440">苹果</option><option value="175">茗振</option><option value="333">荣事达</option><option value="331">莉碧儿</option><option value="148">莱德斯</option><option value="40">萨洛蒙</option><option value="109">蒙特罗</option><option value="425">蓝之羽</option><option value="83">蓝旅</option><option value="219">蓝月亮</option><option value="107">薏凡特</option><option value="424">蘭之魅</option><option value="11">虎牌</option><option value="423">西王食品</option><option value="421">西铁城</option><option value="238">诺依曼</option><option value="201">诺诗兰</option><option value="420">诺贝达</option><option value="419">象博士</option><option value="8">象印</option><option value="184">贝利安</option><option value="263">贝尔顺</option><option value="172">赛乐</option><option value="430">赛巴迪</option><option value="56">赞斯特</option><option value="371">超维 Choworld</option><option value="348">路客</option><option value="270">车管家</option><option value="21">迈乐</option><option value="55">迈克达威</option><option value="417">迈森兰</option><option value="198">迪士尼</option><option value="418">迪穆</option><option value="141">迪阿多纳</option><option value="25">速比涛</option><option value="61">酷乐</option><option value="415">酷彩</option><option value="414">金丝莉</option><option value="413">金壹农</option><option value="303">金稻成</option><option value="411">金银物语</option><option value="410">金龙鱼</option><option value="110">锐步</option><option value="76">阿玛尼</option><option value="385">阿芙</option><option value="1">阿迪达斯</option><option value="279">陌上炊烟</option><option value="433">雪狼</option><option value="381">雷明顿</option><option value="93">韶音</option><option value="248">顽鹿</option><option value="45">颂拓</option><option value="318">领豪</option><option value="379">飞利浦</option><option value="378">飞科</option><option value="409">首农</option><option value="408">香雪</option><option value="278">馨亭</option><option value="338">驼峰</option><option value="142">骄马</option><option value="37">鬼冢虎</option><option value="290">魔行</option><option value="305">鱼跃</option><option value="5">鳄鱼</option><option value="50">麦利金</option><option value="49">麦格霍斯</option><option value="235">黄蚂蚁</option><option value="116">黑冰</option></select>


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
            <%--<input type="button" onclick="addpic('details',1,'W',0,800)" class="btn " value="上传"/>--%>
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
                    <th>北移结算价<input id="prices" class="input-small valid" type="text" value="" maxlength="20"> <input
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