/**
 * 
 */

const setAnchorToStorage =(e)=>{
}


const storeUserId = () =>{
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const id = urlParams.get('id');
	if(id!=null)
	localStorage.setItem("userid", id);
}


const fetchSubjects = ()=>
{
	let subjects = [];
	fetch(constants.url + "classesServlet",{ credentials: 'include'})
	.then(res=>res.json()).then(data=>{subjects=data;console.log(data);
	let container = document.getElementById("subjectsContainer");
	let s="<div class='d-flex flex-wrap'>";
	subjects.forEach(x=>{
		s+="<div class='card card-item' ><label><a class='a-link text-dark' href='./subject.jsp?id="+ x.id+ "'>" + x.name + "</a></label></div>";
		
	});
	s+="</div>";
	
	container.innerHTML = s;
	
	}).catch(e=>console.log(e));
	
	
}


const storeData =() =>{
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const id = urlParams.get('id');
	console.log(id);
	if(id!="" || id!=null)
	{
		localStorage.setItem("subjectId", id);
		
	}
}


const fetchPosts = () =>{
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const id = urlParams.get('id');
	
	console.log(id);
	
}

const getSubjectName = () =>{
}



const createSubject = (e) =>{
	e.preventDefault();
	const name = document.getElementById("newSubjectName").value;
	const data = {name:name}
	fetch(constants.url +"classesServlet", {method:"POST", headers:{
		 'Content-Type': 'application/json'
	},body:JSON.stringify(data)})
	.then(res=>fetchSubjects())
	.catch(e=>console.log(e));
}



