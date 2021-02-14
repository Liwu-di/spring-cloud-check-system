

jQuery.support.cors = true;
$(document).ready(function(){
	$("#btn_login").click(function(){
        data={"userCode":$("#username").val(),"passWord":$("#password").val()};
        data=JSON.stringify(data); 
        console.log(data);
        $.ajax({
            url:"http://localhost:9000/login",
            data:data,
            type:"post",
            contentType:'application/json;charset=utf-8',
            success:function(result,status){
                //console.log(result,status);
                try{
                    if(status == "success" && result['code'] == 0){
                        //console.log("success log in");
                        console.log($("#username").val());
                        $.cookie('userCode',$("#username").val(),{ expires: 7, path: '/' });
                        console.log($.cookie('userCode'));//window.location="./main.html";
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
    
