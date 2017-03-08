$(document).ready(function() {

    $.ajax({  
        type: 'POST',
        dataType: "json",
        url:strurl + '/menulist',
        success: function(data){
            $.each(data,function(i,n){
                $('#navMenu').accordion('add',{
                    id:n.mid,
                    title: n.text,
                    selected: i==0?true:false,
                    content:'<ul id="tree'+i+'"></ul>',
                    iconCls:'icon-tip'
                });
            });
        }
    });
        
    $('#navMenu').accordion({
        onSelect: function(title,index){
//debugger;
            var p = $('#navMenu').accordion('getSelected');
        	var id = p.attr("id");      	
        	$("ul[id='tree"+index+"']").tree({
                 url:strurl + '/treelist?id='+id,	
                 onClick:function(node){
                	 addPanel(strurl, node.id,node.text,node.url);
                 }
            });
        }
    });
    
});