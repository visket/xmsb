var equipProvince_path;
var init_equipProvince_list_datagrid = function(path){

	equipProvince_path=path;
	
	organizationTree = $('#organizationTree').tree({
        url : equipProvince_path+'/organization/treeProvince',
        parentField : 'pid',
        lines : true,
        onClick : function(node) {
          if(node.id==28){
        	  top.$.messager.alert({title:'系统提示', msg:'请选择处室!', showType:'warning'});
        	  return false;
          }
        	  
        	$.ajax({
        		type : 'POST',
        		url : path+'/equip/findEquipProvince.do?id='+node.id,
        		success:function(data){
      			
        		//必须reload否则保存后再点击保存会重复写入数据库
        		//$('#'+tableName).datagrid('reload',{typeId:typeId,gradetype:$('#gradetype').val(),eqbaseId:$('#id').val()});
        		$('#id').val(data.id);
        		$('#unitId').val(data.unitId);//点击左边树形结构后获得unitID
        		$('#orgId').val(node.id);
        		$('#declareDetails').show();//需要写在$('#live_tabs').show()之前,否则会影响第一个子列显示不全      		
        		if(data.id==null){
        			$('#live_tabs').hide();
        			$('#nextstep').show();
            		$('#backstep').hide();
            		$('#save').hide();
            		$('#compile').val('');
            		$('#compileshow').numberbox('setValue','');
            		$('#internalorg').val('');
            		$('#internalorgshow').numberbox('setValue','');
            		$('#applytimeStr').datebox('setValue',''); 		
            		$('#applyname').textbox('setValue','');
            		$('#internalorgshow').numberbox("enable");
                	$('#compileshow').numberbox("enable");
        		}else{
        			$('#live_tabs').show();
            		findEqchild();
        			$('#compile').val(data.compile);
        			$('#compileshow').numberbox('setValue',data.compile);
            		$('#internalorg').val(data.internalorg);
            		$('#internalorgshow').numberbox('setValue',data.internalorg);
            		$('#applytimeStr').datebox('setValue',data.applytime); 		
            		$('#applyname').textbox('setValue',data.applyname);
                   	$('#internalorgshow').numberbox("disable");
                	$('#compileshow').numberbox("disable");
            		$('#nextstep').hide();
            		$('#backstep').show();
            		$('#save').show();
        		}
        		 
        		}
        	});
        }
    });
	
};