//jQuery.support.cors = true;
$(document).ready(function(){
    $("#login").click(function(){
        data={"userCode":$("#login-username").val(),"passWord":$("#login-password").val()};
        data=JSON.stringify(data);
        //console.log(data);
        $.ajax({
            url:"http://liwudi.fun/info/login",
            data:data,
            type:"post",
            contentType:'application/json;charset=utf-8',
            success:function(result,status){
                //console.log(result,status);
                try{
                    if(status == "success" && result['code'] == 0){
                        console.log(result);
                        //console.log("success log in");
                        //console.log($("#username").val());
                        $.cookie('userCode',$("#login-username").val(),{ expires: 7, path: '/' });
                        //console.log($.cookie('userCode'));
                        window.location="./index.html";
                    }
                    else{
                        alert("账号密码错误!");
                    }
                } catch (exception){
                    console.error(exception);
                    alert(exception);
                }
            }
        })
    });
});

