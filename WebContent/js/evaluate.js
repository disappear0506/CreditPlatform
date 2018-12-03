
function indexOf(arr,element){
    for(var i=0;i<arr.length;i++){
        if(arr[i]==element){
            return i;
        }
    }
}
function InitEvent(){
    var tds = document.getElementById("rating").getElementsByTagName("td");
    var save = document.getElementById("save");
    for(var i =0; i<tds.length;i++){
     
        tds[i].style.cursor="pointer";
        tds[i].onmouseover=function(){
            var tds=document.getElementById("rating").getElementsByTagName("td");
            var index = indexOf(tds,this);
            rating.setAttribute("Rate",index+1);//属性Rate用于click时显示分数
            for(var i=0;i<=index;i++){
                tds[i].innerText = '★'; 
            }
            for (var i=index+1;i<tds.length;i++){
                tds[i].innerText = '☆';
            }
        };
    }
    save.onclick=function(){
        var rating = document.getElementById("rating");
        if(rating.getAttribute("Rate")==null)
        {
        	rating.setAttribute("Rate",0);
        }
        var score=rating.getAttribute("Rate")-3;
        //发送异步请求
        var request=new XMLHttpRequest();
        request.open("POST","changeScore.action",true);
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send("userId="+userId+"&score="+score);
        alert("保存成功！"+score);
    };
    
    
}