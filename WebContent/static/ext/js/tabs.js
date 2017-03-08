function addPanel(ctx,id, strtitle, strsrc) {
	if ($('#tabs').tabs('exists', strtitle)) {
		$('#tabs').tabs('select', strtitle);
	} else {
//console.info("strsrc="+strsrc+"---ctx="+ctx);		
		$('#tabs').tabs('add',{
					title : strtitle,
					content : '<iframe width="100%" height="100%" id="iframe'+ id
					+ '" style="border:0" src="' +strsrc+'" ></iframe>',
					closable : true,
					id : "a"+id,
		}); 
	}
}
		// 打开新页面的方法，实际为为tabs添加一个新tab，里包含iframe框架页面
function LoadMain(str)// 根据的高度计算中间tab和iframe的高度，在每次重绘去调用执行本方法
{
//	var iframe = $('#iframe' + str);
//	var iframeHeight = parseInt(iframe.parent().parent().parent().parent().height());
//
//	iframe.height((iframeHeight - 51) + 'px');
//	iframe.parent().height((iframeHeight - 51) + 'px');
//	iframe.parent().css("padding", "10px");

}
	
function Ishome(){
	if(('.tabs-selected').text()=="综合态势"){
		return true;
	}
	else{
		return false;
	}
}