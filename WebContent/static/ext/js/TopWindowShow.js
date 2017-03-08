function LoadTopWindow(){
	if(window.top!=null&&window.top.document.URL!=document.URL){
		
		window.top.location = document.URL;
	}

}