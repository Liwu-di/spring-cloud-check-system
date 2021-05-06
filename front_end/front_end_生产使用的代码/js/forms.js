$(document).ready(function (){

    data={"userCode":$.cookie("userCode")};
    data=JSON.stringify(data);
    $("#account").val($.cookie("userCode"));
    $.ajax({
        //url:"http://liwudi.fun/info/selectUser",
        url:"http://localhost:9000/selectUser",
        data:data,
        type:"post",
        contentType:'application/json;charset=utf-8',
        success:function(result,status){
            //console.log(result,status);
            try{
                if(status == "success" && result['code'] == 0){
                    $("#name").val(result.data.userName);
                    $.cookie('idCard',result.data.idCard,{ expires: 7, path: '/' });

                }

                else{
                    alert(result.msg);
                }
            } catch (exception){
                console.error(exception);
                alert(exception);
            }
        }
    });

    $("#submit").click(function (){
        if($("#idCard").val()==null
            && $("#idCard").val()==""
            && $("#idCard").val()===undefined){
            alert("身份证号不能为空");
        }
        else if($("#name").val()==null
            && $("#name").val()==""
            && $("#name").val()===undefined){
            alert("姓名不能为空");
        }
        else if($("#account").val()==null
            && $("#account").val()==""
            && $("#account").val()===undefined){
            alert("账号不能为空");
        }
        else if($("#password").val()==null
            && $("#password").val()==""
            && $("#password").val()===undefined){
            alert("密码不能为空");
        }
        else if($("#idCard").val()!=$.cookie("idCard")){
            alert("身份证不匹配");
        }
        else{
            data={"userCode":$.cookie("userCode"),
                "userName":$("#name").val(),
                "passWord":$("#password").val(),
                "idCard":$("#idCard").val()
            };
            data=JSON.stringify(data);
            $.ajax({
                //url:"http://liwudi.fun/info/updateUser",
                url:"http://localhost:9000/updateUser",
                data:data,
                type:"post",
                contentType:'application/json;charset=utf-8',
                success:function(result,status){
                    //console.log(result,status);
                    try{
                        if(status == "success" && result['code'] == 0){
                            alert("修改信息成功");
                            $("#name").val(result.data.userName);
                            $("#account").val($.cookie("userCode"));
                            $("#password").val("");
                            $("#idCard").val("");

                        }
                        else{
                            alert(result.msg);
                        }
                    } catch (exception){
                        console.error(exception);
                        alert(exception);
                    }
                }
            });
        }
    });
})