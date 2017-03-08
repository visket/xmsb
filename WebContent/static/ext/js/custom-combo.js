/**
 * 自定义combo继承结构体系下的所有控件初始化封装 :
 * combobox,combogrid,combotree,combotreegrid
 * @author around
 * 
 */



/**
 * 字典查询 
 * @param ctx = ${ctx }
 * @param comboboxid 
 * @param did
 */
function loadDictionary(ctx, comboboxid, did) {
	$("#" + comboboxid).combobox({
		url: ctx+'/item/findByDictionarycode?dictionarycode='+did,
		valueField : 'id',
		textField : 'itemvalue',	
		editable : false,
	});
};

/**
 * 字典查询 点击下拉框以后进行加载 
 * @param ctx = ${ctx }
 * @param comboboxid 
 * @param did
 */
function loadDictionaryTest(ctx, comboboxid, did) {
	$("#" + comboboxid).combobox({
		valueField : 'id',
		textField : 'itemvalue',	
		editable : false,
		onShowPanel : function(){
			$("#" + comboboxid).combobox('reload',ctx+'/item/findByDictionarycode?dictionarycode='+did);
		}
	});
};

/**
 * 区域查询
 */
function loadAreaCodeAll(ctx, comboboxid) {
	$("#" + comboboxid).combobox({
		url: ctx+'/area/findAreaCodeAll',
		valueField : 'id',
		textField : 'code',	
		editable : false
	});
};

/**
 * 区域树查询
 */
function loadAreaTreeAll(ctx, comboboxid) {
	$("#"+comboboxid).combotree({
        url : ctx+'/area/allTree',
        parentField : 'pid',//pid parentId
        lines : true,
        panelMaxHeight : 180,//设置高度出现滚动条
        panelHeight : 180
    });
};


function loadUserCombogrid(ctx, combogrid, id, text) {
	$('#' + combogrid).combogrid({
		panelWidth:450,
		url: ctx + '/user/combogrid/list',
		idField:'id',
		textField:'name',
		mode:'remote',
		async : false,
		pagination : true,
		rownumbers : true, //显示行号
		showfooter : true,	//显示行尾
		selectOnNavigation : true,
		singleSelect : true,
		pageNumber:1,
		pageSize: 5,
		pageList: [5 ,10],
		fitColumns : true,
		columns:[[
			{hidden:true,field:'id',title:'Item ID',width:60},
			{field:'name',title:'姓名',align:'center',width:60},
			{field:'loginname',title:'账号',align:'center',width:60},
			{field:'sex',title:'性别',align:'center',width:60,
				formatter : function(value) {
                    switch (value) {
                    	case 0: return '男';
                    	case 1: return '女';
                    }
                }},
			{field:'age',title:'年龄',align:'center',width:60},
			{field:'organizationName',title:'单位',align:'center',width:60},
			{field:'status',title:'当前状态',align:'center',width:60,
				formatter : function(value) {			
					if(value==0) { return '正常';
					} else if(value==1){ return '禁用';
					} else { return ""; }
				}}
		]],
		onLoadSuccess:function(data){
			if(id!=''){
				$('#' + combogrid).combogrid('setValue',id);
				$('#' + combogrid).combogrid('setText',text);
			}
			  //if(combogrid=='entertor') $('#'+combogrid).combogrid('disable');
	    },
		onSelect:function(record) {
		}
	});
};

/**
 * 查询所有单位的下拉框
 * @param ctx
 * @param comboboxid
 */
function loadUnitByUnitId(ctx, combogrid, typeId) {
	$("#" + combogrid).combogrid({    
	    panelWidth:470,
	    mode: 'remote',    
	    url: ctx+'/unit/findByUnitId',    
	    idField: 'id',    
	    textField: 'name',
	    fitColumns :true,
	    fit : true,
		border : false,
		striped : true, //显示斑马线, 不同行颜色不同
		rownumbers : true, //显示行号
		pagination : true, //开启分页
		pageNumber : 1, //初始页码
		pageSize : 5, //每页10条
		pageList : [ 5, 10, 20, 50, 100 ], //可选择每页记录数
		sortName : 'createTime',				//按字段排序
		sortOrder : 'desc',				//倒序
		queryParams : {
			typeId : typeId
		},
	    columns: [[
	        {field:'name',title:'单位名称',width:100,align:'center',
	        	formatter: function(value){
	        		if(value==null||value==""){
	        			return "暂无";
	        		}else{
	        			return "<span title ='" +value +"'>" +value +"</span>";
	        		}
	        	}
	        },    
	        {field:'unitLinkman',title:'联系人',width:60,align:'center'},
	        {field:'phone',title:'手机号',width:80,align:'center'},
	        {field:'address',title:'地址',width:150,align:'center',
	        	formatter: function(value){
	        		if(value==null||value==""){
	        			return "暂无";
	        		}else{
	        			return "<span title ='" +value +"'>" +value +"</span>";
	        		}
	        	}
	        },
	    ]],
	    onLoadSuccess:function(data){
			/*if(id!=''){
				$('#' + combogrid).combogrid('setValue',data.rows[0].id);
				$('#' + combogrid).combogrid('setText',data.rows[0].name);
			}*/
	    }
	});
};

/**
 * 查询选定单位的combox
 * @param ctx
 * @param comboboxid
 */
function loadUnitByCombobox(ctx, comboboxid,id) {
	$("#" + comboboxid).combobox({
		url: ctx+'/unit/findUnitByCombobox?id='+id,
		valueField : 'id',
		textField : 'itemvalue',	
		editable : false,
		onLoadSuccess:function(data){		
			if(data.id!=''){			
				$('#' + comboboxid).combobox('setValue',data.id);
				$('#' + comboboxid).combobox('setText',data.name); 
			}
	    }
	});
};

function loadUnitByComboboxPath(ctx, comboboxid,id,path) {
	$("#" + comboboxid).combobox({
		url: ctx+'/unit/findUnitByCombobox?id='+id,
		valueField : 'id',
		textField : 'itemvalue',	
		editable : false,
		onLoadSuccess:function(data){		
			if(data.id!=''){			
				$('#' + comboboxid).combobox('setValue',data.id);
				$('#' + comboboxid).combobox('setText',data.name);				
				$('#unittype').val(data.type);
				return data.type;
				//init_lzjcbznl_table (path);
			}
	    }
	});
};


function loadUnitBy(ctx, comboboxid, id, text) {
	$("#" + comboboxid).combobox({
		url: ctx+'/unit/findUnitByCombobox',
		valueField : 'id',
		textField : 'itemvalue',	
		editable : false,
		queryParams :{
			id : id
		},
		onBeforeLoad : function() {
			if(id != null && id != '') {
				$('#' + comboboxid).combobox('setValue', id);
				$('#' + comboboxid).combobox('setText', text);
			}
		},
		onLoadSuccess:function(data){
			if(data != null) {			
				$('#' + comboboxid).combobox('setValue',data.id);
				$('#' + comboboxid).combobox('setText',data.name);
				$('#' + comboboxid).combobox('setValue',data.id);
			}
	    }
	});
};

//查询所有单位到combobox中
function loadAllUnitByCombobox(ctx, comboboxid) {
	$("#" + comboboxid).combobox({
		url: ctx+'/unit/findAllUnitByCombobox',
		valueField : 'id',
		textField : 'name',	
		editable : true,
	    panelHeight : 160,
		onLoadSuccess:function(data){		
			/*if(data.id!=''){			
				$('#' + comboboxid).combobox('setValue',data.id);
				$('#' + comboboxid).combobox('setText',data.name); 
			}*/
	    }
	});
};
