window.addEventListener("load", function(e) {
	var result = JSON.parse(localStorage.getItem("result"));
	var outbody = JSON.parse(localStorage.getItem("body"));
	var body = document.getElementById("body");
	console.log(outbody)
	if(result!=null){
		if(result.fromput!=null){
			var ele = document.createElement("h2");
			ele.innerHTML = result.fromput;
			body.appendChild(ele);		
		}
		if(result.out!=null){
			var ele = document.createElement("h2");
			ele.innerHTML = result.out;
			body.appendChild(ele);
		}
		if(result.newid != null){
			var ele = document.createElement("h2");
			ele.innerHTML = result.newid	;
			body.appendChild(ele);
		}
	}
	ele=document.createElement("h1");
	ele.innerHTML="STUDENT DETAILS";
	body.appendChild(ele);
	var table = document.createElement("table");
	var header = table.createTHead();
	var row = header.insertRow();
			row.insertCell(0).style.textAlign="center";row.insertCell(0).innerHTML = "<h2>id</h2>";
			row.insertCell(1).innerHTML = "<h2>name</h2>";
			row.insertCell(2).innerHTML = "<h2>dob</h2>";
			row.insertCell(3).innerHTML = "<h2>phone</h2>";
		if(outbody!=null){
			for(var i=0;i<outbody.Students.length;i++){
			var rowdata=JSON.parse(outbody.Students[i]);
			var row = table.insertRow();
			row.insertCell(0).style.textAlign="center";
			row.insertCell(0).innerHTML = rowdata.id;
			row.insertCell(1).innerHTML = rowdata.name;
			row.insertCell(2).innerHTML = rowdata.dob;
			row.insertCell(3).innerHTML = rowdata.phone;
		}
		}
		console.log("out body ",outbody);
		body.appendChild(table);
	
})