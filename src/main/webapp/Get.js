window.addEventListener("load", function(e) {
	var id = document.getElementById("sid");
	var invalid = document.getElementById("invalidid")
	var getid = document.getElementById("getid");
	var getall = document.getElementById("getall")
	getid.addEventListener("submit", function(event) {
		if(id.value.length === 0){
			invalid.innerHTML = 'Enter a value';
		}
		else {
			fetch("./students?id=" + id.value, {
				method: "GET",
			}).then(function(resp) {
				return resp.json();
			}).then(function(body) {
				localStorage.setItem("result", JSON.stringify(body));
				localStorage.setItem("body", JSON.stringify(body));
				window.location.assign("Data.html");
			}).catch(console.log)
		}
		event.preventDefault();
	})
	getid.addEventListener("reset",function(){
		invalid.innerHTML = '';
	})	
	getall.addEventListener("click",function(){
		fetch("./students?id=&test=get+all",{
		method:"GET",
	}).then(function(resp){
		return resp.json ();
	}).then(function(body){
		localStorage.setItem("result",JSON.stringify(body));
		localStorage.setItem("body",JSON.stringify(body));
		window.location.assign("Data.html");
	}).catch(console.log)
	})
})