$(document).ready(function (){
    $("#ipGpsCheck").click(function (){

        var geolocation = new qq.maps.Geolocation("KOJBZ-PFNWJ-QC2FT-FATZJ-WQXQZ-S6FIS", "myapp");
        geolocation.getLocation(showPosition);

        function showPosition(position) {
            console.log(position)
            data={"userCode":$.cookie("userCode"), "checkIp":(returnCitySN["cname"]+' '+returnCitySN["cip"]), "checkAreaX":position.lng, "checkAreaY":position.lat};
            data=JSON.stringify(data);
            console.log(data);
            $.ajax({
                url:"http://liwudi.fun/verify/verify",
                data:data,
                type:"post",
                contentType:'application/json;charset=utf-8',
                success:function(result,status){
                    //console.log(result,status);
                    try{
                        if(status == "success" && result['code'] == 0){
                            console.log("success check in!");
                            alert("打卡成功");
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
            // localStorage.setItem('cityName',position.city)
            // localStorage.setItem('provinceName',position.province)
            // localStorage.setItem('distrinctName',position.district)
            // localStorage.setItem('cityId',position.adcode)
            // localStorage.setItem('addr',position.addr)
            // localStorage.setItem('gps',JSON.stringify({lat:position.lat,lng:position.lng})) //火星坐标(gcj02)，腾讯、Google、高德通用
        };

    });
})