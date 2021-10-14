
const addUserToClass = (e)=>{
	e.preventDefault();
	const email = document.getElementById("addUserInput").value;
	const subjectId = localStorage.getItem("subjectId");
	const data = {email:email, subjectId:subjectId};
	fetch(constants.url + "subjectUserServlet", {method:"POST", headers:{
		 'Content-Type': 'application/json'},body:JSON.stringify(data)})
		 .then(res=>res.json())
		 .then(data=>console.log(data))
		 .catch(e=>console.log(e));
	
}

const getPosts = () =>{

	let root = document.getElementById("posts");
	root.innerHTML = "";
	fetch(constants.url+"PostsServlet?sid="+localStorage.getItem("subjectId"))
	.then(res=>res.json())
	.then(data=>{
		data.forEach(x=>{
			let container = document.createElement("div");
			container.classList.add("bg-light");
			container.classList.add("w-100");
			container.classList.add("p-3");
			container.classList.add("m-3");
			
			let text = document.createElement("p")
			text.appendChild(document.createTextNode(x.content));
			container.appendChild(text);
			root.appendChild(container);
		});
		
	})
	.catch(e=> console.log(e));
}


const addPost = (e) =>{
	e.preventDefault();
	const text = document.getElementById("new-post").value;
	const data = {content:text,subjectId:localStorage.getItem("subjectId") };
	fetch(constants.url+"PostsServlet",{method:"POST", headers:{
		 'Content-Type': 'application/json'
	},body:JSON.stringify(data)})
	.then(res=>res.json())
	.then(data=>{
	getPosts();
	})
	.catch(e=>console.log(e));
	
}