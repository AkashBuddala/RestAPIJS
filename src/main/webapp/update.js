window.addEventListener("load", function(e) {
var test = true;
var updateid = document.getElementById('updateid');
var id = document.getElementById('id')
var enterid = document.getElementById('enterid')
const name1 = document.getElementById('name1')
const dob = document.getElementById('dob')
const phone = document.getElementById('phone')
const namenull = document.getElementById('namenull')
const validdate = document.getElementById('validdate')
const validphone = document.getElementById('validphone')
updateid.addEventListener("submit",function(event){
	if(id.value.length == 0){
		enterid.innerHTML='Enter id';
		test=false;
	}
	if (name1.value.length === 0) {
			test=false;
			namenull.innerHTML = 'Name cannot be Null';
			event.preventDefault();
		}
		else if (name1.value.length < 3) {
			test=false;
			namenull.innerHTML = 'Enter a valid name';
			event.preventDefault();
		}
		if (dob.value.length === 0) {
			test=false;
			validdate.innerHTML = 'select dob';
			event.preventDefault();
		}
		else if (new Date(dob.value).getFullYear() < 2000 || new Date(dob.value).getTime() > new Date().getTime()) {
			test=false;
			validdate.innerHTML = 'invalid date';
			event.preventDefault();
		}if(phone.value.length == 0){
			test=false;	
			validphone.innerHTML = 'Enter phone number';
			event.preventDefault();
		}
		else if(phone.value.length != 10){
			test=false;	
			validphone.innerHTML = 'invalid phone number';
			event.preventDefault();
		}	
	if(test == true){
		fetch("./students",{
			method :"PUT",
			headers: {"content-type": "application/json"},
			body : JSON.stringify({sid: id.value, name1: name1.value, dob: dob.value, phone: phone.value})
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
		window.location.assign("Data.html");
	}).catch(console.log)
}