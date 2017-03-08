



/**
 * 构造方法：创建校验对象，此方法为简单主键校验
 * @param tablename
 * @param id
 * @returns
 */
function Valid(tablename, id) {
	this.tablename = tablename;
	this.id = id;
	this.fields = [];
	this.values = [];
	this.titles = [];
	this.status = false;
	this.statusField = null;
	this.statusValue = null;
	
	/**
	 * 设置过滤判定参数
	 * @param title 字段中文名
	 * @param field 字段名
	 * @param value 字段值
	 */
	this.setFilter = function(title, field, value) {
		this.titles.push(title);
		this.fields.push(field);
		this.values.push(value);
	};
	
	/**
	 * 设置是否处理逻辑删除的内容
	 * @param field 字段名
	 */
	this.setStatus = function(field) {
		this.statusField = field;
		this.status = true;
	};
};


/**
 * 通用数据唯一性校验， 自定义返回字段message
 * @param ctxURL  相对路径
 * @param valid	Valid对象
 */
function custom_Valid(ctxURL, valid) {
	var flag = false;
	$.ajax({
		async : false,
		type : 'POST',
		url : ctxURL+'/valid/base',
		data : {id:valid.id, tablename:valid.tablename, fields:valid.fields.toString(), 
			values:valid.values.toString(), titles:valid.titles.toString(), statusField:valid.statusField},
		beforeSend : function() {
			top.$.messager.progress({text : '正在验证数据...'});
		},
		success : function(data) {
			top.$.messager.progress('close');
			flag = data.success;
			if(!data.success) {
				top.$.messager.alert({title:'系统提示', msg:data.msg, showType:'warning'});
			}
			return flag;
		}
	});
	return flag;
};






/**
 * 通用数据唯一性校验， 自定义返回字段message
 * @param ctxURL  相对路径
 * @param tablename	表名
 * @param id	对象名称
 * @param fields	待验证的字段名
 * @param values	字段值
 */
function validate(ctxURL, tablename, id, fields, values, titles) {
	var flag = false;
	$.ajax({
		async : false,
		type : 'POST',
		url : ctxURL+'/valid/base',
		data : {id:id, tablename:tablename, fields:fields.toString(), 
			values:values.toString(), titles:titles.toString()},
		beforeSend : function() {
			top.$.messager.progress({text : '正在验证数据...'});
		},
		success : function(data) {
			top.$.messager.progress('close');
			flag = data.success;
			if(!data.success) {
				top.$.messager.alert({title:'系统提示', msg:data.msg, showType:'warning'});
			}
			return flag;
		}
	});
	return flag;
};