
let parrentFileId = null;

const uploadFile = (e)=>{
	e.preventDefault();
	let files = document.getElementById('selectFiles').files;
	let path = localStorage.getItem("path");
	
	let formData = new FormData();
	formData.append("file",files[0]);
	formData.append("path",path);
	
	let request = new XMLHttpRequest();
	
	request.open("POST", constants.url+"filesServlet");
	request.send(formData)
	
	
}

const createNewFolder = (event) =>{
	event.preventDefault();
	const name = document.getElementById("folder-name").value;
	
	const data = {name:name, path:localStorage.getItem("path")};
	fetch(constants.url + "folderServlet",{method:"POST", headers:{
		 'Content-Type': 'application/json'},body:JSON.stringify(data)}).
		 then(res=>{
		 getFilesForSubject();
		 })
		 .catch(e=>console.log(e));
		 

}

const downloadButton = (name) =>{
	
	console.log(name);
	let path = localStorage.getItem("path");
	
	path = path + "_" + name;
	console.log(path);
	/*
	fetch(constants.url+"downloadServlet?path="+path)
	.then(res=>console.log(res))
	.catch(e=>console.log(e));
	*/
	
	window.location.href = constants.url+"downloadServlet?path="+path;
		
}


const getFilesForSubject = () =>{

	let path = localStorage.getItem("path");
	fetch(constants.url+"filesServlet?pid=-1&sub="+path)
	.then(res=>res.json())
	.then(data=>{
		let root = document.getElementById("filesContainer");
		root.innerHTML = "";
		data.forEach(item=>{
			let tableRow = document.createElement("tr")
			let fileData = document.createElement("td");
			fileData.setAttribute("name", item.name);
			fileData.appendChild(document.createTextNode(item.name));
			let fileDownload = document.createElement("td");
			
			if(item.folder == true)
			{
				fileData.addEventListener("click",handeFolderOnClick );
				tableRow.classList.add('table-info');
				tableRow.classList.add('cursor-pointer');
			}
			if(item.folder == false)
			{
				
				fileDownload.innerHTML = "<button class='btn btn-danger'  name='"+item.name+"' onclick='downloadButton(this.name)'>Download</button>";
			}
			
			tableRow.appendChild(fileData);
			tableRow.appendChild(fileDownload);
			
			root.appendChild(tableRow);
		});
		
		
		
		
		console.log(data);})
	.catch(e=>console.log(e));
	
	
}

const handeFolderOnClick = (e)=>{
	const name = e.target.getAttribute("name");
	let path = localStorage.getItem("path");
	path = path + "_" + name;
	localStorage.setItem("path",path);
	getFilesForSubject();
}

const goBack = ()=>{
	let path = localStorage.getItem("path");
	let arr = path.split("_");
	if(arr.length >1)
	{
		arr.pop();
	}
	path = arr.join('_');	
	localStorage.setItem("path", path);
	getFilesForSubject();
}