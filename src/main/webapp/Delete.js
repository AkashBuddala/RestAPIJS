window.addEventListener("load", function(e) {
var deleteid = document.getElementById('deleteid');
var id = document.getElementById('sid')
var enterid = document.getElementById('enterid')
deleteid.addEventListener("submit",function(event){
	if(id.value.length == 0){
		enterid.innerHTML='Enter id';
	}else{
		fetch("/students?id="+id.value,{
			method :"DELETE",
		}).then(function(resp){
			console.log("from response",resp);
			return resp.json();
		}).then(function(body){
			console.log("body: ", body);
			localStorage.setItem("result",JSON.stringify(body));
			getsdata();
			
		})
		.catch(console.log);
	}
		event.preventDefault();
})
})

function getsdata(){
	fetch("./students?id=&test=get+all",{
		method:"GET",
	}).then(function(resp){
		return resp.json ();
	}).then(function(body){
		localStorage.setItem("body",JSON.stringify(body));
		console.log("get:",body);
		window.location.assign("Data.html");
	}).catch(console.log)
}