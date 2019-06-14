<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ page import="com.thinkgem.jeesite.common.config.Global" %>
<%
    String logo=request.getParameter("logo");
    String IsThumbnail=request.getParameter("IsThumbnail");
    String ThumbnailMode=request.getParameter("ThumbnailMode");
    String ThumbnailHeight=request.getParameter("ThumbnailHeight");
    String ThumbnailWidth=request.getParameter("ThumbnailWidth");
    System.out.println(Global.getUserfilesBaseDir());
%>
<!DOCTYPE html>
<!-- saved from url=(0127)http://m.p-ubit.com/Application/YBCustom/html/addpic.html?IsThumbnail=1&ThumbnailMode=HW&ThumbnailHeight=100&ThumbnailWidth=100 -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>图片上传</title>
    <meta id="viewport" name="viewport" content="width=750">

    <style>
        html, body {
            margin: 0;
            padding: 0;
        }

        ul, li {
            margin: 0;
            padding: 0;
        }

        .image-div {
            padding: 30px 50px;
            border-radius: 10px;
            background-color: #fff;
            margin: 40px 30px 0;
        }

        .image-div .title {
            height: 70px;
            line-height: 70px;
            font-size: 32px;
            color: #5f5f5f;
            font-weight: 500;
        }

        .upload-div,
        .show-div {
            /*margin-top: 20px;*/
        }

        .upload-div .upload-ul,
        .show-div .show-image-ul {
            letter-spacing: -0.5em;
        }

        .upload-div .upload-ul .upload-li,
        .show-div .show-image-ul .show-image-li {
            height: 131px;
            width: 131px;
            margin-right: 22px;
            letter-spacing: normal;
            display: inline-block;
            margin-top: 20px;
        }

        .show-div .show-image-ul .show-image-li img {
            height: 100%;
            width: 100%;
        }

        .upload-div .upload-ul .upload-li:nth-child(4n),
        .show-div .show-image-ul .show-image-li:nth-child(4n) {
            margin-right: 0;
        }

        .upload-div .upload-ul .upload-li .item {
            height: 100%;
            width: 100%;
            border-radius: 10px;
            border: 3px dashed #97def1;
            position: relative;
        }

        .upload-div .upload-ul .upload-li .item.image {
            border: none;
            font-size: 0;
        }

        .upload-div .upload-ul .upload-li .item .delete-image {
            position: absolute;
            height: 25px;
            top: -12.5px;
            left: -12.5px;
        }

        .upload-div .upload-ul .upload-li .item .upload-image {
            height: 100%;
            width: 100%;
            border-radius: 10px;
            vertical-align: initial;
        }

        .img-input-form {
            position: absolute;
            height: 131px;
            width: 131px;
            z-index: 999;
        }

        .img-input-form input {
            position: absolute;
            top: 0;
            left: 0;
            height: 131px;
            width: 131px;
        }

        .photo-span {
            display: inline-block;
            position: absolute;
            height: 32px;
            width: 39px;
            border-radius: 5px;
            border: 3px solid #97def1;
            left: 50%;
            top: 50%;
            margin-top: -16px;
            margin-left: -19.5px;
        }

        .circle-span {
            display: inline-block;
            position: absolute;
            height: 14px;
            width: 14px;
            border-radius: 7px;
            border: 3px solid #97def1;
            left: 50%;
            top: 50%;
            margin-top: -7px;
            margin-left: -7px;
        }

        .circle-solid-span {
            display: inline-block;
            position: absolute;
            height: 4px;
            width: 4px;
            border-radius: 2px;
            background-color: #97def1;
            left: 50%;
            top: 50%;
            margin-top: -10px;
            margin-left: 9px;
        }

        .btm-btn {

            margin-left: 20%;
            margin-top: 5px;
            margin-bottom: 5px;
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
        }
        .btm-btnUpload {
            height: 40px;
            width: 100px;
            border-radius: 5px;
            text-align: center;
            font-size: 16px;
            color: #fff;
            background-color: #3498db;
            margin-left: 50%;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        .z_photo{
            padding: 18px;
            border:2px dashed #E7E6E6;
            /*padding: 18px;*/
        }

        .aui-btn-default{

            margin:10px auto;
            background-color: #FFF;
            border: 1px solid #EAEAEA;
            box-shadow: 0 2px 4px 0 #EFF1FA;
            border-radius: 2px;
            min-height: 64px;
            padding-top: 15px;
            padding-right: 50px;
            text-align: left;
            padding-left:110px;
        }

        .aui-btn-account{
            background: #F45858;
            color: #FFF;
            border-radius: 2px;
            padding: 7px 20px;
            font-size: 12px;
        }

    </style>
</head>

<body>
<div class="content" id="content">
    <div class="image-div" id="uploadDiv">
        <div class="title"   style="padding-right: 50px;">
            多图上传
            <!--确定上传按钮
            <input type="button" value="开始上传" id="btn_ImgUpStart" class="btm-btnUpload "    onclick="fileUpload();" />-->
        </div>
        <div class="upload-div z_photo" id="uploadImageDiv">
            <ul class="upload-ul" id="uploadUL">
                <!--默认的点击窗口
                    在添加了图片之后，循环在这个前面insert图片的li
                -->
                <li class="upload-li" id="uploadBtn">
                    <form class="img-input-form" enctype="multipart/form-data" style="opacity: 0;">
                        <!-- <input type="file" multiple onchange="selectImage(this)" accept="image/gif, image/jpeg, image/png" id="upload" > -->
                        <input type="file" id="checkFile" accept="image/*"  capture="camera" multiple="" onchange="selectImage(this)" id="upload">
                    </form>
                    <div class="item">
                        <span class="photo-span"></span>
                        <span class="circle-span"></span>
                        <span class="circle-solid-span"></span>
                    </div>
                </li>
            </ul>
        </div>
        <div class="aui-btn-default" id="imageTitle">
            <input id="btnOK" name="btnOK" type="button" class="btm-btn " onclick="SubmitSelect();" value="确定">
            <input name="btnCancle" type="button" class="btm-btn" style=" background-color: #e7e7e7; color: black;" onclick="window.returnValue = &quot;&quot;; window.close();" value="取消">
        </div>

    </div>
</div>
<script src="http://m.p-ubit.com/Application/YBCustom/js/jquery-1.8.3.min.js"></script>
<script src="http://m.p-ubit.com/Application/YBCustom/js/yutil-1.0.1.js"></script>
<script type="text/javascript" src="http://m.p-ubit.com/Application/YBCustom/js/common.js"></script>
<script type="text/javascript" charset="utf-8">
    var reVal = '';
    var ThumbnailHeight = '<%=ThumbnailHeight%>';//设置生成缩略图高度
    var ThumbnailWidth = '<%=ThumbnailWidth%>';//设置生成缩略图宽度
    var ThumbnailMode = '<%=ThumbnailMode%>';//生成缩略图的方式
    var IsThumbnail = '<%=IsThumbnail%>';//是否设置生成缩略图图片(0:使用原图；1：生成缩略图)
    var IsPicWaterMark;//是否设置生成图片水印图片
    var IsTextWaterMark;//是否设置生成文字水印图片

    $(document).ready(function () {

    })

    var uploadImgIndex = 0;
    var imgArr = [];
    var path="<%=Global.getUserfilesBaseDir()%>";
    function init(){
     /*  var logo=path+"<%=logo%>";
       alert(logo);
        fso = new ActiveXObject("Scripting.FileSystemObject");
        v2 = fso.GetFile(logo);
        //$("#checkFile").val(logo);
         selectImage(v2);*/

    }
    function selectImage(imgFile){
        var allFile = imgFile.files;
        var totalLen = allFile.length;
        if(yValidate.checkNotEmpty(imgArr) && imgArr.length>0){
            totalLen = totalLen + imgArr.length;
        }

        for(var i=0;i<allFile.length;i++){
            var file = allFile[i];
            if(yValidate.checkNotEmpty(imgArr) && imgArr.length>0){
                /* if(imgArr.length <4){*/
                imgArr.push(file);
                //  }
            }else{
                imgArr.push(file);
            }
            //添加一层过滤
            var rFilter = /^(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)$/i;
            if(!rFilter.test(file.type)) {
                alert("文件格式必须为图片");
                return;
            }
            var reader = new FileReader();
            reader.readAsDataURL(file); //用文件加载器加载文件
            //文件加载完成
            reader.onload = function(e) {
                //计算最后一个窗口right边距，当时处于第4个的时候，right=0
                /*  if((allFile.length + 1)%4 == 0){
                      document.getElementById("uploadBtn").style.marginRight = "0px";
                  }*/
                //以下就是将所有上传的图片回显到页面上，如果需要用canvas进行剪裁再回显以下代码就放入到canvas中
                var li = document.createElement('li');
                li.id = "upload_"+uploadImgIndex;
                document.getElementById("uploadBtn").style.display = "";
                uploadImgIndex++;
                li.className = "upload-li";
                li.innerHTML = '<div class="item image">'+
                    '<img class="upload-image" src="'+e.target.result+'"/>'+
                    '<img class="delete-image" src="http://byzhgh.p-ubit.com/Application/YBCustom/images/image-delete.png"/>' +
                    '</div>';
                document.getElementById("uploadUL").insertBefore(li, document.getElementById("uploadBtn"));
                var uploadArr = document.getElementById("uploadUL").querySelectorAll("li");
                var len = uploadArr.length ;
                if(len > 4){
                    document.getElementById("uploadBtn").style.display = "none";
                }

            };
            reader.onloadend = function(e) {
                $(".delete-image").off('click');
                $(".delete-image").on('click',function(){
                    // alert("dasd");
                    var li = $(this).parent().parent()[0];
                    var index = $(".upload-ul .upload-li").index(li);
                    var liId = li.id;
                    $("#"+liId).remove();
                    imgArr.splice(index,1);
                    document.getElementById("uploadBtn").style.display = "";
                });
            }
        }
    }

    function SubmitSelect() {
        fileUpload();
        if (reVal != "") {
            window.opener.continue_addpicNew(reVal);
            window.close();
        } else {
            alert('请先上传图片！');
        }
    }

    function fileUpload() {
        var param = new FormData();
        var data;
        var url = "http://byzhgh.p-ubit.com/Application/YBCustom/ajaxService/UploadHandler.ashx";
        for (var i = 0; i < imgArr.length; i++) {
            param.append('file[]', imgArr[i], i);
        }
        if (imgArr.length == 0)
        {
            alert("请先选择要上传的文件，再点击上传");
            return;
        }
        param.append("IsThumbnail", IsThumbnail);
        param.append("ThumbnailMode", ThumbnailMode);
        param.append("ThumbnailWidth", ThumbnailWidth);
        param.append("ThumbnailHeight", ThumbnailHeight);
        param.append("paramType", "ajaxFileUpload");
        $.ajax({
            url: url,
            type: 'POST',
            data: param,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            dataType: "json",
            success: function (data) {
                data = eval(data);
                if (data.isok == "1") {
                    // alert("上传成功！");
                    reVal = data.msg;

                } else {
                    alert("上传失败！");
                }

            },
            error: function () {
                //  $("body").mLoading("hide");
                alert("上传失败");
            }
        });
    }
    init();
</script>



</body></html>