/**
 * 自定义form扩展js。
 * 封装了获取各类控件取值方法。
 * 
 * 
 */



function gettextboxvalue(field) {
	return $('#'+field).textbox('getValue');
}

function getcomboboxvalue(field) {
	return $('#'+field).combobox('getValue');
}

function getdateboxvalue(field) {
	return $('#'+field).datebox('getValue');
}



/**
 * 序列化封装 form的值
 * @param form  form对象
 */
function serializeObject(form) { 
	var obj = {};
	$.each(form.serializeArray(), function (index) {
		
		if (obj[this['name']]) {
			obj[this['name']] = obj[this['name']] + "," + this['value'];
		} else {
			obj[this['name']] = this['name'];
		}
	});
	return obj;
}
