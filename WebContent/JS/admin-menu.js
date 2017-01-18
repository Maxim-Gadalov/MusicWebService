function showAddTrackForm(type){
	param = document.getElementById(type);
	if(param.style.display == "none"){
		param.style.display = "block";
	} else {
		param.style.display = "none";
	}
}