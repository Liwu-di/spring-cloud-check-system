
$(document).ready(function(){
    var qrcode = new QRCode(document.getElementById("qrcode"), "http://www.liwudi.fun");  // 设置要生成二维码的链接
    $("#submitBiCode").click(function(){
        data={"userCode":$("#login-username").val(),"specialCode":$("#login-password").val()};
        data=JSON.stringify(data);
        $.ajax({
            //url:"http://liwudi.fun/info/login",
            url:"http://localhost:9001/getBiCode",
            data:data,
            type:"POST",
            async: false,
            contentType:'application/json;charset=utf-8',
            success:function(result,status) {
                console.log(result, status);
                try {
                    if (status == "success" && result['code'] == 0) {
                        console.log(result);
                        if (navigator.geolocation) {
                            navigator.geolocation.getCurrentPosition(showPosition);
                        } else {
                            alert("该浏览器不支持获取地理位置。");
                        }
                        function showPosition(position) {
                            qrcode.clear(); // 清除代码
                            // alert("http://localhost:9001/verifyByBi?biCode=" +
                            //     result['data'] +
                            //     "&userCode=" + $.cookie("userCode") + "&" + "checkIp=" + "二维码打卡"
                            //     + "&checkAreaX=" + position.coords.longitude + "&checkAreaY=" + position.coords.latitude
                            // );
                            qrcode.makeCode("http://localhost:9001/verifyByBi?biCode=" +
                                result['data'] +
                                "&userCode=" + $.cookie("userCode") + "&" + "checkIp=" + "%E4%BA%8C%E7%BB%B4%E7%A0%81%E6%89%93%E5%8D%A1"
                                + "&checkAreaX=" + position.coords.longitude + "&checkAreaY=" + position.coords.latitude
                            ); // 生成另外一个二维码
                        }

                    } else {
                        alert("账号密码错误!");
                    }
                } catch (exception) {
                    console.error(exception);
                    alert(exception);
                }
            }
        });
    });

    $("#importCompany").click(function upload() {
        alert("进入上传。。。");
        var formData = new FormData();
        formData.append("file", $("#file")[0].files[0]);
        formData.append("specialKey",$("#specialKey").val());
        $.ajax({
            "type":"post",
            "url":"http://localhost:9000/importCompanyConf",
            "data":formData,
            "cache":false,
            "processData": false,
            "contentType" : false,
            "dataType": 'json',
            "success": function(){
                alert("导入成功");
            },
            "error": function (){
                alert("导入错误")
            }
        });
    });

    $("#importUser").click(function upload() {
        alert("进入上传。。。");
        var formData = new FormData();
        formData.append("file", $("#file_user")[0].files[0]);
        formData.append("specialKey",$("#specialKey_user").val());
        $.ajax({
            "type":"post",
            "url":"http://localhost:9000/importUserInfo",
            "data":formData,
            "cache":false,
            "processData": false,
            "contentType" : false,
            "dataType": 'json',
            "success": function(){
                alert("导入成功");
            },
            "error": function (){
                alert("导入错误")
            }
        });
    });

    $("#exportCheckInfo").click(function download() {
        data={"specialKey":$("#specialKey_check").val()};
        data=JSON.stringify(data);
        var url = 'http://localhost:9000/exportCheckInfo?specialKey='+$("#specialKey_check").val();
        window.location(url);
    });

    $( ".datepicker" ).datepicker({
        numberOfMonths: 3,
        showButtonPanel: true
    });

    $("#sendQ").bind("click",function Q(){
        data={"startTime":$("#datepicker_qs").val(),
            "endTime":$("#datepicker_qe").val(),
            "userCode":$.cookie("userCode"),
            "email":$("#email_q").val(),
            "isAskForLeave":"1"};
        data=JSON.stringify(data);
        alert("请等待");
        $.ajax({
            "type":"post",
            "url":"http://localhost:9000/vocation",
            "data":data,
            "contentType": "application/json;charset=UTF-8",
            "success": function(){
                alert("请假成功");
            },
            "error": function (){
                alert("请假失败");
            }
        });
    });

    $("#sendX").bind("click",function X(){

        data={"startTime":$("#datepicker_xs").val(),
            "endTime":$("#datepicker_xe").val(),
            "userCode":$.cookie("userCode"),
            "email":$("#email_q").val(),
            "isAskForLeave":"0"};
        data=JSON.stringify(data);
        alert("请等待");
        $.ajax({
            "type":"post",
            "url":"http://localhost:9000/vocation",
            "data":data,
            "contentType": "application/json;charset=UTF-8",
            "success": function(){
                alert("销假成功");
            },
            "error": function (){
                alert("销假失败");
            }
        });

    });

});

