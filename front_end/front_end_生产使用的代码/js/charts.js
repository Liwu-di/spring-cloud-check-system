$(document).ready(function (){
    $("#ipGpsCheck").click(function (){

        //prod code
        //var geolocation = new qq.maps.Geolocation("KOJBZ-PFNWJ-QC2FT-FATZJ-WQXQZ-S6FIS", "myapp");
        //geolocation.getLocation(showPosition);

        //test
        if (navigator.geolocation)
        {
            navigator.geolocation.getCurrentPosition(showPosition);
        }

        else
        {
            alert("该浏览器不支持获取地理位置。");
        }

        // function showPosition(position)
        // {
        //     console.log("纬度: " + position.coords.latitude +
        //         "<br>经度: " + position.coords.longitude);
        //     alert("纬度: " + position.coords.latitude +
        //         "<br>经度: " + position.coords.longitude);
        // }

        function showPosition(position) {
            console.log(position)
            data={"userCode":$.cookie("userCode"), "checkIp":(returnCitySN["cname"]+' '+returnCitySN["cip"]), "checkAreaX":position.coords.longitude, "checkAreaY":position.coords.latitude};
            data=JSON.stringify(data);
            console.log(data);
            $.ajax({
                //url:"http://liwudi.fun/verify/verify",
                url:"http://localhost:9001/verify",
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