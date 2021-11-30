window.addEventListener("load", function(e) {
	console.log("Doc ready", e)
	var test=true;
	const name1 = document.getElementById('name1')
	const dob = document.getElementById('dob')
	const phone = document.getElementById('phone')
	const namenull = document.getElementById('namenull')
	const validdate = document.getElementById('validdate')
	const validphone = document.getElementById('validphone')
	document.getElementById("fid").addEventListener("submit", function(event) {
		if (name1.value.length === 0) {
			namenull.innerHTML = 'Name cannot be Null';
			test=false;
		}
		else if (name1.value.length < 3) {
			namenull.innerHTML = 'Enter a valid name';
			test=false;
		}
		if (dob.value.length === 0) {
			validdate.innerHTML = 'select dob';
			test=false;
		}
		else if (new Date(dob.value).getFullYear() < 2000 || new Date(dob.value).getTime() > new Date().getTime()) {
			validdate.innerHTML = 'invalid date';
			test=false;
		}if(phone.value.length == 0){	
			validphone.innerHTML = 'Enter phone number';
			test=false;
		}
		else if(phone.value.length != 10){	
			validphone.innerHTML = 'invalid phone number';
			test=false;
		}
		if(test){
			fetch("/students",{
			method :"POST",
			headers: {"content-type": "application/json"},
			body : JSON.stringify({ name1: name1.value, dob: dob.value, phone: phone.value})
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
	}
	)
	
	document.getElementById("fid").addEventListener("reset",function(){
		namenull.innerHTML = '';
		validphone.innerHTML = '';
		validdate.innerHTML = '';
	})	

})

function getsdata(){
	fetch("./students?id=&test=get+all",{
		method:"GET",
	}).then(function(resp){
		return resp.json ();
	}).then(function(body){
		localStorage.setItem("body",JSON.stringify(body));
		window.location.assign("Data.html");
	}).catch(console.log)
}
