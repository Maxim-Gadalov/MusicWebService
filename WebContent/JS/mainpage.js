function showSecondContent(type1,type2){
 param=document.getElementById(type1);
 param2=document.getElementById(type2);
 if(param.style.display == "none") {
 param.style.display = "block";
 param2.className = "pointer-rotate";
 }else{ 
 param.style.display = "none";
 param2.className = "pointer";
 }
}
function hidetxt(type){
	param = document.getElementById(type);
	if(param.style.display == "none"){
		param.style.display = "block";
	} else{
		param.style.display = "none";
		}
}