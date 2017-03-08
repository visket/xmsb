/**
 * 自定义Datagrid扩展js。
 * 封装了刷新Datagrid的方法，清空选择框的方法。
 * 封装获取选定行数据的对象集合。
 * 
 * 
 * 
 */


/** 
*刷新DataGrid列表(适用于Jquery Easy Ui中的dataGrid) 
*注：建议采用此方法来刷新DataGrid列表数据(也即重新加载数据)，不建议直接使用语句 
*$('#dataTableId').datagrid('reload');
*来刷新列表数据，因为采用后者，如果日后在修改项目时，要在系统中的所有刷新处进行其他一些操作，
*那么你将要修改系统中所有涉及刷新的代码，这个工作量非常大，而且容易遗漏；但是如果使用本方法来刷新列表，
*那么对于这种修该需求将很容易做到，而去不会出错，不遗漏。 
* 
*@param dataTableId将要刷新数据的DataGrid依赖的table列表id 
*/  
function flashTable(dataTableId) {  
    $('#' + dataTableId).datagrid('reload');
}

function flashTreeGrid(treeGridId) {
	$('#' + treeGridId).treegrid('reload');
}


/**清空复选框*/
function cleanSelect(dataTableId){
	$('#' + dataTableId).datagrid('clearSelections');  
}


/**
 * 通用设置清空查询栏或 form表单内的控件信息
 * @param toolbar
 */
function clearTableToolbar(toolbar) {
	$('#' + toolbar).form('clear');
}


/** 
* 在DataGrid中获取所选记录的id,多个id用逗号分隔 
* 注：该方法使用的前提是：DataGrid的idField属性对应到列表Json数据中的字段名必须为id 
* @param dataTableId 目标记录所在的DataGrid列表table的id 
* @param field 需要提取的字段名
* @return 所选记录的id字符串(多个id用逗号隔开) 
*/  
function getSelectByField(dataTableId, field) {
    var rows = $('#' + dataTableId).datagrid('getSelections');
    var num = rows.length;  
    var fields = null;  
    if (num < 1) {
        return null;  
    } else {  
        for (var i = 0; i < num; i++) {  
            if (null == fields || i == 0) {  
                fields = rows[i][field];  
            } else {  
                fields = fields + "," + rows[i][field];  
            }  
        }  
        return fields;  
    }  
}


/**
 * 获取dataGrid中所需要字段field的值，以mergeField为占位符拼接，可以自定义查询数据模式selectModel，以getSelections或getRows
 * @param datagridId DataGrid的ID
 * @param selectModel	数据来源模式，getSelections或getRows
 * @param mergeField	字符串合并方式，自由传占位符
 * @param field	需要取值的列名
 */
function getDataGridCustomFieldValues(datagridId, selectModel, mergeField, field) {
	var rows = $('#' + datagridId).datagrid(selectModel);
    var num = rows.length;  
    var fields = null;  
    if (num < 1) {
        return '';  
    } else {  
        for (var i = 0; i < num; i++) {  
            if (null == fields || i == 0) {  
                fields = rows[i][field];  
            } else {  
                fields = fields + mergeField + rows[i][field];  
            }  
        }  
        return fields;  
    } 
}


/**
 * 获取dataGrid中所需要字段field的值，该值是对象类型，以数组形式封装
 * @param datagridId
 * @param selectModel
 * @param field
 * @returns {Array}
 */
function getDataGridCustomFieldForObjects(datagridId, selectModel, field) {
	var rows = $('#' + datagridId).datagrid(selectModel);
    var num = rows.length;  
    var fields = [];  
    if (num < 1) {
        return fields;  
    } else {  
        for (var i = 0; i < num; i++) {  
        	fields[i] = rows[i][field]; 
        }  
        return fields;  
    } 
}



/**
 * 将datagrid行的数据转化成json格式 [{},{}]
 * @param rows 表示行
 * @param colNames 表示需要转换的列名的一个数组
 */
var formatRowsToJson = function(rows,colNames){
	var jsonData="[";
	var result="{";
	var json ="";
	if(colNames.length<1 || rows.length<1){
		return jsonData+"]";
	}
	for(var i=0; i<rows.length; i++){
		for(var j=0; j<colNames.length; j++){
			if(rows[i][colNames[j]]){
				json = json + '"'+colNames[j]+'":'+ ensureDataGridFieldTypeToJson(rows[i][colNames[j]])+",";
			}
		}
		if(json.length>0){
			json = json.substring(0, json.length-1);
			result = result + json +"},{";
			json="";
		}else{
			return jsonData+"]";
		} 
	}
	jsonData = jsonData + result.substring(0, result.length-2) + "]";
	return jsonData;
};


/**
 * 将数据转化成json时确定数据类型的返回值
 * @param value 值
 * @returns 返回它原来的值或带引号的默认值
 */
var ensureDataGridFieldTypeToJson = function(value) {
	if (typeof value == "string") {
		if(value){
			return '"'+value+'"';
		}else{
			return '';
		}
	}
	if (typeof value == "number") {
		if(value){
			return value;
		}else{
			return 0;
		}
	}
};


/**
 * 获取当前编辑行的索引
 * @param target
 * @returns
 */
function getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}



