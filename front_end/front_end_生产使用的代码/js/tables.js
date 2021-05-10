
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
                                "&userCode=" + $.cookie("userCode") + "&" + "checkIp=" + "二维码打卡"
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
});

