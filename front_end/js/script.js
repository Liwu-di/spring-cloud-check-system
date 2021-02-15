	
$(document).ready(function(){

	init();
	function init(){
		data={"userCode":$.cookie("userCode")};
		data=JSON.stringify(data); 
		$.ajax({
            url:"http://liwudi.fun/info/selectUser",
            data:data,
            type:"post",
            contentType:'application/json;charset=utf-8',
            success:function(result,status){
                try{
                    if(status == "success" && result['code'] == 0){
						$("#username").html(result.data.userName);
						$("#code").html(result.data.userCode);
						$("#password").html(result.data.passWord);
						$("#idCard").html(result.data.idCard);
                    }
                    else{
                        alert("错误!");
                    }
                } catch (exception){
                    console.error(exception);
                    alert(exception);
                }
            }
        });
	};

	

	$("#submit").click(function(){
		const form = document.getElementById('form');
		const username = document.getElementById('username');
		const email = document.getElementById('code');
		const password = document.getElementById('password');
		const password2 = document.getElementById('idCard');
		const usernameValue = username.value.trim();
		const emailValue = email.value.trim();
		const passwordValue = password.value.trim();
		const password2Value = password2.value.trim();
	
		if(usernameValue === '') {
			alert('用户名不能为空');
		} 
		
		if(emailValue === '') {
			alert('用户编码不能为空');
		} 
		
		if(passwordValue === '') {
			alert('密码不能为空');
		} 
		
		if(password2Value === '') {
			alert('身份证不能为空');
		}
		data={"userCode":$("code").val(),
		"userName":$("username").val(),
		"passWord":$("password").val(),
		"idCard":$("idCard").val()};
		data=JSON.stringify(data); 
		$.ajax({
            url:"http://liwudi.fun/info/updateUser",
            data:data,
            type:"post",
            contentType:'application/json;charset=utf-8',
            success:function(result,status){
                try{
                    if(status == "success" && result['code'] == 0){
						$("#username").html(result.data.userName);
						$("#code").html(result.data.userCode);
						$("#password").html(result.data.passWord);
						$("#idCard").html(result.data.idCard);
                    }
                    else{
                        alert("错误!");
                    }
                } catch (exception){
                    console.error(exception);
                    alert(exception);
                }
            }
        });
	});
});