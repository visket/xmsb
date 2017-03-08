/**
* 数据校验，用于唯一性校验，发起后台访问。
 * @param ctx  上下文相对路径
 * @param objName 对象名称
 * @param validateField 验证的字段
 * @param validateValue 字段值
 * @returns true / false
 */
function validateField(ctx, objName, validateField, validateValue) {
		var flag = null;
		$.ajax({
			async : false,
   			url : ctx + '/data/select/validate.do',
   			type : 'POST',
   			data: {objName: objName, validateField: validateField, validateValue: validateValue},
   			success : function(data) {
   				if (data == "true") {
   					flag = true;	
   				}else{
   					flag = false;
   					//$.messager.show({title:"系统提示",msg:'已存在的音频库编号,请重填!',timeout:5000,showType:'slide'});
   				}
   			}
		});
		return flag;
	}



/**
 * 通用数据唯一性校验， 自定义返回字段message
 * @param ctxURL  相对路径
 * @param objName	对象名称
 * @param validateName	验证不通过，返回提示有问题的字段名
 * @param validateField	待验证的字段名
 * @param validateValue	字段值
 */
function data_validate(ctxURL, objId, objName, validateName, validateField, validateValue) {
	var flag = false;
	$.ajax({
		async : false,
		type : 'POST',
		url : ctxURL+'/data/select/validate.do',
		data : {objName:objName, objId:objId, validateField:validateField, validateValue:validateValue},
		beforeSend : function() {
		},
		success : function(data) {
			data = JSON.parse(data);
			flag = data;
			if(!data) {
				$.messager.show({title:"系统提示",msg:"‘"+validateName+"’列属性‘"+validateValue+"’重复!",timeout:5000,showType:'slide'});
			}
		}
	});
	return flag;
};



/**
 * datagrid验证行是否输入完全，传入3个参数
 * @param fid 父表或关联ID
 * @param datagrid DataGrid名称
 * @param rowindex 行号
 * @return true / false
 */
var datagrid_validateRow = function(fid, datagrid, rowindex) {
	if(fid == null || fid == undefined || fid == '') {
		$.messager.show({title:"系统提示", msg: '请在保存头部记录后再填写子表信息!', timeout:5000, showType:'slide'});
		return false;
	}

	if(!$('#'+datagrid).datagrid('validateRow', rowindex)) {
		$.messager.show({title:"系统提示", msg: '请填写必填项!', timeout:5000, showType:'slide'});
		return false;
	}
	

	return true;
};



/**
 * 查询父级容器下中type=file 的元素，并返回
 * @param parentId 父级ID
 * @return type=file
 */
var getFileboxObject = function(parentId) {
	var fileobj = '';
	var list = document.getElementById(parentId).getElementsByTagName("input");
	 for(var i = 0; i < list.length && list[i]; i++) {
	       //判断是否为文本框
	       if(list[i].type=="file") {
	    	   fileobj = list[i];
	       }
	 }
	 return fileobj;
};





var initDataContextIsUsed = function(objdIv, style) {
	var div = document.getElementById(objdIv);
	if(style == 'block') div.style.display = '';
	else div.style.display = 'none';
};


var setImagePreview = function(fileId, imageId) {
	var fileInput = getFileboxObject(fileId);
	var files = fileInput.files;
	var imgObj = document.getElementById(imageId);
	
	if(files && files[0]) {
		imgObj.src = window.URL.createObjectURL(files[0]);
	}
};


var autoDialogHeight = function(winAudit, objdIv, addheight) {
	var h = winAudit.height;
	if(h < 400) {
		winAudit.height = h + addheight;
	}
	initProofDataIsUsed(objdIv, '');
};



var loadComboboxBySex = function(comboboxid) {
	$('#' + comboboxid).combobox({
		valueField : 'sexValue',
		textField : 'sexName',
		data : [{sexValue : '0', sexName : '男'}, 
				{sexValue : '1',sexName : '女'}],
		editable : false
	});
	
};



var loadComboboxBySelect = function(comboboxid) {
	$('#' + comboboxid).combobox({
		valueField : 'value',
		textField : 'key',
		data : [{value : '0', key : '否'}, 
				{value : '1', key : '是'}],
		editable : false
	});
	
};



/**
 * 字典查询 
 * @param ctx = ${ctx }
 * @param comboboxid 
 * @param did
 */
var loadDictionary = function(ctx, comboboxid, did) {
	$("#" + comboboxid).combobox({
		url: ctx+'/item/findByDictionarycode?dictionarycode='+did,
		valueField : 'id',
		textField : 'itemvalue',
		editable : false,
	});
};

var loadDictionaryE = function(ctx, comboboxid, did) {
	$("#" + comboboxid).combobox({
		url: ctx+'/item/findByDictionarycode?dictionarycode='+did,
		valueField : 'id',
		textField : 'itemvalue',
		editable : true,
	});
};

/**
 * 区域编码查询
*/
var loadAreaCodeAll = function(ctx,comboboxid) {
	
	$("#" + comboboxid).combobox({
		url: ctx+'/area/findAreaCodeAll',
		valueField : 'id',
		textField : 'code',	
		editable : false,
	});
	
};


/**
 * 字典查询 
 * @param ctx = ${ctx }
 * @param comboboxid 
 * @param did
 */
var loadCombotree = function(ctx, combotreeid, did) {
	$("#" + combotreeid).combotree({
		url: ctx+'/item/findByDictionarycode?dictionarycode='+did,
		valueField : 'id',
		textField : 'itemvalue',	
		editable : false,
	});
};

/**
 * 通用combobox查询 
 * @param ctx = ${ctx }
 * @param path  如  /person/dictionary/findItem.do
 * @param comboboxid 
 * @param id 
 * @param value ： valueField
 * @param text ： textField
 */
var loadDictionaryByPath = function(ctx, path, comboboxid, id, value, text) {
	$("#" + comboboxid).combobox({
		url: ctx + path + '?id=' + id,
		valueField : value,
		textField : text,	
		editable : false
	});
};


/** 
* 取消DataGrid中的行选择(适用于Jquery Easy Ui中的dataGrid) 
* 注意：解决了无法取消"全选checkbox"的选择,不过，前提是必须将列表展示 
* 数据的DataGrid所依赖的Table放入html文档的最全面，至少该table前没有 
* 其他checkbox组件。 
* 
*@param dataTableId 将要取消所选数据记录的目标table列表id 
*/  
function clearSelect(dataTableId) {
    $('#' + dataTableId).datagrid('clearSelections');  
    //取消选择DataGrid中的全选  
    $("input[type='checkbox']").eq(0).attr("checked", false);  
}  



/** 
* 在DataGrid中获取所选记录的id,多个id用逗号分隔 
* 注：该方法使用的前提是：DataGrid的idField属性对应到列表Json数据中的字段名必须为id 
* @param dataTableId目标记录所在的DataGrid列表table的id 
* 
* @return 所选记录的id字符串(多个id用逗号隔开) 
*/  
function getSelectIds(dataTableId, noOneSelectMessage) {  
    var rows = $('#' + dataTableId).datagrid('getSelections');  
    var num = rows.length;  
    var ids = null;  
    if (num < 1) {  
        if (null != noOneSelectMessage) $.messager.alert('提示消息', noOneSelectMessage, 'info');  
        return null;  
    } else {  
        for (var i = 0; i < num; i++) {  
            if (null == ids || i == 0) {  
                ids = rows[i].id;  
            } else {  
                ids = ids + "," + rows[i].id;  
            }  
        }  
        return ids;  
    }  
}



/** 
* 删除所选记录(适用于From表单)(删除的依据字段是id) 
* 注：该方法属性对应到列表Json数据中的字段名必须为id 
* 另外，后台代码必须在操作完之后以ajax的形式返回Json格式的提示信息，提示的json格式信息中必须有一个 
* message字段，存放本次删除操作成功与失败等一些提示操作用户的信息。 
* 
* @param formId将要删除记录所在form的id
* @param id将要删除的对象ID
* @param requestURL与后台服务器进行交互，进行具体删除操作的请求路径 
* @param confirmMessage 删除确认信息 
*/ 
function deleteObjById(formId, id, requestURL, confirmMessage) {
	if (null == confirmMessage || typeof (confirmMessage) == "undefined" || "" == confirmMessage) {  
        confirmMessage = "确定删除所选记录?";  
    }
	$.messager.confirm('确认', confirmMessage, function (r) {  
        if (r) {
        	if(id == null || id == 0) {
        		$('#' + formId).form('clear');
        		$.messager.alert('提示消息', '删除本地数据成功！', 'info');
        	} else { 
        		$.getJSON(requestURL, { "id": id }, function (data) {  
                    if (null != data && null != data.message && "" != data.message) {
                    	$('#' + formId).form('clear');
                        $.messager.alert('提示消息', data.message, 'info');
                    } else {  
                        $.messager.alert('提示消息', '删除失败！', 'warning');  
                    } 
                });
        	}
        }
     });
	
}








/** 
*删除所选记录(适用于Jquery Easy Ui中的dataGrid)(删除的依据字段是id) 
*注：该方法会自动将所选记录的id(DataGrid的idField属性对应到列表Json数据中的字段名必须为id) 
*另外，后台代码必须在操作完之后以ajax的形式返回Json格式的提示信息，提示的json格式信息中必须有一个 
*message字段，存放本次删除操作成功与失败等一些提示操作用户的信息。 
* 
*@param dataTableId将要删除记录所在的列表table的id 
*@param requestURL与后台服务器进行交互，进行具体删除操作的请求路径 
*@param confirmMessage 删除确认信息 
*/  
function deleteByTableId(dataTableId, requestURL, confirmMessage) {
	if (null == confirmMessage || typeof (confirmMessage) == "undefined" || "" == confirmMessage) {  
        confirmMessage = "确定删除所选记录?";  
    }
	var row = $('#' + dataTableId).datagrid('getSelected');
	if(row == null) {
		$.messager.alert('提示消息', '请选择你要删除的记录!', 'info');
	} else {
		$.messager.confirm('确认', confirmMessage, function (r) {  
            if (r) {  
                $.getJSON(requestURL, { "id": row.id }, function (data) {  
                    if (null != data && null != data.message && "" != data.message) {  
                    	$.messager.show({title:"系统提示",msg:data.message,timeout:5000,showType:'slide'});
                        flashTable(dataTableId);
                    } else {  
                    	$.messager.show({title:"系统提示",msg:'删除失败!',timeout:5000,showType:'slide'});
                    }  
                    clearSelect(dataTableId);  
                });  
            }  
        });
	}
}







/** 
*删除所选记录(适用于Jquery Easy Ui中的dataGrid)(删除的依据字段是id) 
*注：该方法会自动将所选记录的id(DataGrid的idField属性对应到列表Json数据中的字段名必须为id) 
*动态组装成字符串，多个id使用逗号隔开(如：1,2,3,8,10)，然后存放入变量ids中传入后台，后台 
*可以使用该参数名从request对象中获取所有id值字符串，此时在组装sql或者hql语句时可以采用in 
*关键字来处理，简介方便。 
*另外，后台代码必须在操作完之后以ajax的形式返回Json格式的提示信息，提示的json格式信息中必须有一个 
*message字段，存放本次删除操作成功与失败等一些提示操作用户的信息。 
* 
*@param dataTableId将要删除记录所在的列表table的id 
*@param requestURL与后台服务器进行交互，进行具体删除操作的请求路径 
*@param confirmMessage 删除确认信息 
*/
function deleteNoteById(dataTableId, requestURL, confirmMessage) {  
    if (null == confirmMessage || typeof (confirmMessage) == "undefined" || "" == confirmMessage) {  
        confirmMessage = "确定删除所选记录?";  
    }
    var rows = $('#' + dataTableId).datagrid('getSelections');  
    var num = rows.length;  
    var ids = null;  
    if (num < 1) {  
    	top.$.messager.alert('提示消息', '请选择你要删除的记录!', 'info');  
    } else {  
    	top.$.messager.confirm('确认', confirmMessage, function (r) {  
            if (r) {  
                for (var i = 0; i < num; i++) {  
                    if (null == ids || i == 0) {  
                        ids = rows[i].id;  
                    } else {  
                        ids = ids + "," + rows[i].id;  
                    }  
                }  
                $.getJSON(requestURL, { "ids": ids }, function (data) {
                	$.messager.show({title:"系统提示", msg:data.msg, timeout:5000, showType:'slide' });
                    if (null != data && null != data.msg && "" != data.msg) {
                    	flashTable(dataTableId);
                    	clearSelect(dataTableId);
                    } 
                });  
            }  
        });  
    }  
}  



/** 
*删除所选记录(适用于Jquery Easy Ui中的dataGrid)(删除的依据字段是id) 
*注：该方法会自动将所选记录的id(DataGrid的idField属性对应到列表Json数据中的字段名必须为id) 
*另外，后台代码必须在操作完之后以ajax的形式返回Json格式的提示信息，提示的json格式信息中必须有一个 
*message字段，存放本次删除操作成功与失败等一些提示操作用户的信息。 
* 
* @param datagrid 将要删除记录所在的datagrid
* @param id 将要删除记录的id
* @param requestURL 与后台服务器进行交互，进行具体删除操作的请求路径 
* @param confirmMessage 删除确认信息 
*/  
function deleteById(datagrid, id, requestURL, confirmMessage) {
	if (null == confirmMessage || typeof (confirmMessage) == "undefined" || "" == confirmMessage) {  
        confirmMessage = "确定删除所选记录?";  
    }
	if(id == null || id == '') {
		top.$.messager.alert('提示消息', '请选择你要删除的记录!', 'info');
	} else {
		top.$.messager.confirm('确认', confirmMessage, function (r) {  
            if (r) {  
                $.getJSON(requestURL, {"id": id }, function (data) {
                	
                    if (null != data && null != data.msg && "" != data.msg) {  
                    	$.messager.show({title:"系统提示",msg:data.msg,timeout:5000,showType:'slide'});
                        flashTable(datagrid);
                    } else {  
                    	$.messager.show({title:"系统提示",msg:'删除失败!',timeout:5000,showType:'slide'});
                    }
                });  
            }  
        });
	}
}






