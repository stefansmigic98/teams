
let socket = null;



function connect() {
    return new Promise(function(resolve, reject) {
		const userId = localStorage.getItem("userid");
        let server = new WebSocket(constants.socketUrl+"chat/"+userId,"protocolOne");
          server.onmessage = function() {
          	console.log("stiglo");
            fetchChat(localStorage.getItem("chatid"));
			fetchInbox();
        };
        
        server.onopen = function() {
            resolve(server);
        };
      
        
        server.onerror = function(err) {
            reject(err);
        };

    });
}



const connectToScoket = ()=>{
	
	connect().then(x=>{
	socket = x;
	socket.onmessage = (event)=>{
	
		fetchChat(localStorage.getItem("chatid"));
		fetchInbox();
	} 
	
	});
	
	
}


const newChat = () =>{
	let user = document.getElementById("new-user").value;
	console.log(user);
	const data = {
		user1id: localStorage.getItem("userid"),
		user2email:user	
	}

	fetch(constants.url + "chatServlet", {method:"POST", headers:{
		'Content-Type': 'application/json'},body:JSON.stringify(data)})
	.then(res=>res.json())
	.then(data1=>{
		const chatId = data1.response;
		fetchChat(chatId);
		
	})
	.catch(err=>console.log(err));
}

const fetchChat = (chatId)=>{
	fetch(constants.url + "chatServlet?cid="+chatId)
	.then(res=>res.json())
	.then(data=>{
		localStorage.setItem("chatid", chatId);
		let root = document.getElementById("chat-area");
		root.innerHTML = "";
		data.forEach(item=>{
			let message = document.createElement("div");
			message.className = "rounded py-2 px-3 mr-3 mt-3";
			
			if(item.senderId == localStorage.getItem("userid"))
			{
				message.classList.add("msg-send");
				message.classList.add("bg-info");
			}
			else
			{
				message.classList.add("msg-recive");
				message.classList.add("bg-light");
			}
			
			message.appendChild(document.createTextNode(item.message));
			
			root.appendChild(message);
			root.scrollTop = root.scrollHeight;
		});
	})
	.catch(e=>console.log(e));
	
}


const fetchInbox = ()=>{
	fetch(constants.url + "inboxServlet")
	.then(res=>res.json())
	.then(data=>{
		console.log(data);
		let root = document.getElementById("inbox-list");
		root.innerHTLM = "";
		data.forEach(item=>{
			let chatInfo = document.createElement("div");
			chatInfo.classList.add('list-group-item');
			chatInfo.setAttribute("name", item.chatId);
			chatInfo.setAttribute("senderName", item.senderName);
			let sender = document.createElement("span");
			sender.appendChild(document.createTextNode(item.senderName));
			sender.classList.add("text-primary");
			chatInfo.appendChild(sender);
	
			chatInfo.appendChild(document.createElement("br"));
			chatInfo.appendChild(document.createTextNode(item.message));
			chatInfo.addEventListener("click",(e)=>{
				document.getElementById("sender-name").innerHTML = e.target.getAttribute('senderName')
				fetchChat(e.target.getAttribute('name'));
			} );
			
			
			root.appendChild(chatInfo);
		})
	})
	.catch(e=>console.log(e));
}

const sendMessage = (e)=>
{
	e.preventDefault();
	let text = document.getElementById("new-message").value;
	if(text === "")
		return;
	console.log(text);
	
	const data = {chatId:localStorage.getItem("chatid"),message:text};
	socket.send(JSON.stringify(data));
	setTimeout( function(){fetchChat(localStorage.getItem("chatid"));},1000);
	
		

	
	
}