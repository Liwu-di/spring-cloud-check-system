$(document).ready(function(){
    data={"userCode":$.cookie("userCode")};
    data=JSON.stringify(data); 
    $.ajax({
        url:"http://liwudi.fun/info/selectByUserCode",
        data:data,
        type:"post",
        contentType:'application/json;charset=utf-8',
        success:function(result,status){
            //console.log(result,status);
            try{
                if(status == "success" && result['code'] == 0){
                    console.log(result);
                    for(var i=0;i<result.data.length;i++){
                    
                        $("#myTable tbody").append(
                            '<tr> ' +
                                '<td >' + (result.data[i].checkSuccess == 0 ? "成功" : "失败") + '</td>' + 
                                '<td >' + result.data[i].userCode + '</td>' +
                                '<td >' + result.data[i].checkIp + '</td>' +
                                '<td >' + result.data[i].checkAreaX + '</td>' + 
                                '<td >' + result.data[i].checkAreaY + '</td>' +
                                '<td >' + result.data[i].companyCode + '</td>' +
                                '<td >' + result.data[i].checkDate + '</td>' +
                            '</tr>'
                        );
                    }
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