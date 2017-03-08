treegrid_loaddialog = function(iframe, html, title, width, height, datagrid) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (width ? width : 500),
		height : (height ? height : 500),
		title : title,
		closed : true,
		cache : false,
		modal : true,
		onClose : function() {
			winAudit.dialog('destroy');
		},
		onDestroy : function() {
			  flashTreeGrid(datagrid);
		},
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {		
				top.$("#" + iframe)[0].contentWindow.confirmmethods();
			}
		}, {
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		} ]
	});
	
	winAudit.dialog("open");
	
	if (datagrid != undefined) {
		top.$("#"+iframe)[0].contentWindow.iframe = datagrid;
	}
};


datagrid_loaddialog= function(iframe, html, title, width, height, pagetype, datagrid, row) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (width ? width : 500),
		height : (height ? height : 500),
		title : title,
		closed : true,
		cache : false,
		modal : true,
		onClose : function() {
			winAudit.dialog('destroy');
		},
		onDestroy : function() {
//debugger;			
			 if(datagrid=='treeGrid')
				  flashTreeGrid(datagrid);
			  else	  
				 flashTable(datagrid);
			//flashTable(datagrid);
		},
		buttons : pagetype != 'search' ? [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {		
				top.$("#" + iframe)[0].contentWindow.confirmmethods();
			}
		}, {
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		} ] : [{
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		} ]
	});
	
	winAudit.dialog("open");
	
	if (datagrid != undefined) {
		top.$("#"+iframe)[0].contentWindow.iframe = datagrid;
	}
	
	if (row != undefined) {
		top.$("#"+iframe)[0].contentWindow.row = row;
	}
	
	if (pagetype != null && pagetype != undefined) {
		top.$("#"+iframe)[0].contentWindow.pagetype = pagetype;
	}
	
	
};


/**
 * 
 */
loaddialog = function(html, mtitle, mwidth, mheight) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		resizable : false,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');			
		}
	});

	/*if (id != undefined) {
		top.$("#dlgiframe")[0].contentWindow.id = id;
	}
	
	if (row != null && row != undefined) {
		top.$("#dlgiframe")[0].contentWindow.row = row;
	}
	
	//新加
	if (pagetype != null && pagetype != undefined) {
		top.$("#dlgiframe")[0].contentWindow.pagetype = pagetype;
	}*/

	winAudit.dialog("open");
};



/**
 * 标准调用详情页方法（新增修改查询均可用），勿改。
 * @param iframe iframe_id
 * @param html	对应iframe对象页面内容
 * @param mtitle 窗口标题
 * @param mwidth 宽度
 * @param mheight 高度
 * @param pagetype 页面状态  新增：add  编辑：edit  查看：search
 * @param datagrid 名称，不传对象
 * @param row   行对象
 */
function loaddialogbynewpage(iframe, html, mtitle, mwidth, mheight, pagetype, datagrid, row) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
		onDestroy : function() {
		  if(datagrid=='treeGrid')
			  flashTreeGrid(datagrid);
		  else	  
			 flashTable(datagrid);
		},
		buttons : [{
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				top.$("#" + iframe)[0].contentWindow.confirmmethods();
			}
		},{
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		}]
	});

	if (pagetype != null && pagetype != undefined) {
		top.$("#" + iframe)[0].contentWindow.pagetype = pagetype;
	}
	
	if (row != null && row != undefined) {
		top.$("#" + iframe)[0].contentWindow.row = row;
	}
	
	winAudit.dialog("open");
};



/**
 * 新增装备标准使用（新增修改查询均可用），勿改。
 * @param iframe iframe_id
 * @param html	对应iframe对象页面内容
 * @param mtitle 窗口标题
 * @param mwidth 宽度
 * @param mheight 高度
 * @param pagetype 页面状态  新增：add  编辑：edit  查看：search
 * @param datagrid 名称，不传对象
 * @param row   行对象
 */
function loaddialogbynewpageequip(iframe, html, mtitle, mwidth, mheight, pagetype, datagrid, row) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
		onDestroy : function() {
		  if(datagrid=='treeGrid')
			  flashTreeGrid(datagrid);
		  else	  
			 flashTable(datagrid);
		},
		buttons : [
		{
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				(top.$("#" + iframe)[0].contentWindow).$("#equipstandard_base_form").form("clear");
					top.$("#" + iframe)[0].contentWindow.pagetype = 'add';
			}
		},           
		{
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				console.log(pagetype);
				top.$("#" + iframe)[0].contentWindow.confirmmethods();
			}
		},{
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		}]
	});

	if (pagetype != null && pagetype != undefined) {
		top.$("#" + iframe)[0].contentWindow.pagetype = pagetype;
	}
	
	if (row != null && row != undefined) {
		top.$("#" + iframe)[0].contentWindow.row = row;
	}
	
	winAudit.dialog("open");
};


//删除时全屏遮罩
function loaddialogbydel(iframe, html, mtitle, mwidth, mheight, pagetype, datagrid, row) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
		onDestroy : function() {
		  if(datagrid=='treeGrid')
			  flashTreeGrid(datagrid);
		  else	  
			 flashTable(datagrid);
		},
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				top.$("#" + iframe)[0].contentWindow.confirmmethods();
			}
		},{
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		}] 
	});
	
	winAudit.dialog("open");
};

/**
 * 适用于跳转查询所对应的关联子集
* @param iframe iframe_id
 * @param html	对应iframe对象页面内容
 * @param mtitle 窗口标题
 * @param mwidth 宽度
 * @param mheight 高度
 * @param keyid 外键ID
 */
function checkviewdialog(iframe, html, mtitle, mwidth, mheight,keyid){
	var query = top.$
	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
	});
	winAudit.dialog("open");
	if (keyid != null && keyid != undefined) {
		top.$("#" + iframe)[0].contentWindow.keyid = keyid;
	}
}



/**
 * 项目模块调用详情窗体
 */
function loaddialogbyproject(iframe, html, mtitle, mwidth, mheight, pagetype, datagrid, row) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
		onDestroy : function() {
			flashTable(datagrid);
		},
		buttons : 
			pagetype != 'search'?[{
			text : '发起申报',
			iconCls : 'icon-ok',
			handler : function() {
				top.$("#" + iframe)[0].contentWindow.confirmdeclare();
			}
		},{
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				top.$("#" + iframe)[0].contentWindow.confirmmethods();
			}
		},{
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		}]:
		[{
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		}]
	});

	if (pagetype != null && pagetype != undefined) {
		top.$("#" + iframe)[0].contentWindow.pagetype = pagetype;
	}
	
	if (row != null && row != undefined) {
		top.$("#" + iframe)[0].contentWindow.row = row;
	}
	
	winAudit.dialog("open");
};


function loaddialogbyequip(iframe, html, mtitle, mwidth, mheight, pagetype, datagrid, row) {
	var query = top.$

	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
		onDestroy : function() {
			cleanSelect(datagrid);
			flashTable(datagrid);
		},
		onBeforeOpen:function() {
			//取当前dialog的buttons
			var buttons = winAudit[0].parentElement.children[2].children;

			if(pagetype == 'add') {//新增入口
				buttons[0].style.display = '';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
			} else if(pagetype == 'edit') {//查看模式,进入只有查看功能
				buttons[0].style.display = 'none';
				buttons[1].style.display = '';
				buttons[2].style.display = '';
			} else if(pagetype == 'search') {//审核+查看模式,进入有查看和审核功能
				buttons[0].style.display = 'none';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
			}
		},
		buttons : [{
			text : '下一步',
			iconCls : 'icon-usergo',
			handler : function() {
				top.$("#" + iframe)[0].contentWindow.nextstep();
			}
		},{
			text : '发起申报',
			iconCls : 'icon-ok',
			handler : function() {
				top.$("#" + iframe)[0].contentWindow.confirmdeclare();
			}
		},{text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				top.$("#" + iframe)[0].contentWindow.confirmmethods();
			},
		},{
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		}]
			
	});
	
	if (pagetype != null && pagetype != undefined) {
		top.$("#" + iframe)[0].contentWindow.pagetype = pagetype;
	}
	
	if (row != null && row != undefined) {
		top.$("#" + iframe)[0].contentWindow.row = row;
	}
	
	winAudit.dialog("open");
};



/**
 * 审核专用-勿动勿用
 * @param iframe
 * @param html
 * @param mtitle
 * @param mwidth
 * @param mheight
 * @param pagetype
 * @param datagrid
 * @param row
 */
function loaddialogbyaudit(iframe, html, mtitle, mwidth, mheight, pagetype, datagrid, row) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
		onDestroy:function() {
			cleanSelect(datagrid);
			flashTable(datagrid);
		},
		onBeforeOpen:function() {
			//取当前dialog的buttons
			var buttons = winAudit[0].parentElement.children[2].children;
			//buttons = buttons.children;
			
			if(pagetype == 'default') {//默认模式，进入无任何权限
				buttons[0].style.display = 'none';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = 'none';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'search') {//查看模式,进入只有查看功能
				buttons[0].style.display = '';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = 'none';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'audit_search') {//审核+查看模式,进入有查看和审核功能
				buttons[0].style.display = '';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = '';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'audit') {
				buttons[0].style.display = 'none';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = '';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'office_search') {
				buttons[0].style.display = 'none';
				buttons[1].style.display = '';
				buttons[2].style.display = 'none';
				buttons[3].style.display = 'none';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'expert_search') {
				buttons[0].style.display = 'none';
				buttons[1].style.display = 'none';
				buttons[2].style.display = '';
				buttons[3].style.display = 'none';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'submit') {
				buttons[0].style.display = 'none';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = 'none';
				buttons[4].style.display = '';
			} 
		},
		buttons : [{
				text : '查看审核意见',
		    	iconCls : 'icon-search',
		    	handler : function() {
				top.$("#" + iframe)[0].contentWindow.auditinfosearch();
		    	}
			},{
				text : '分发处室',
				iconCls : 'icon-list',
				handler : function() {
					top.$("#" + iframe)[0].contentWindow.disponseOffice();
				}
			},{
				text : '分发专家',
				iconCls : 'icon-usergo',
				handler : function() {
					top.$("#" + iframe)[0].contentWindow.disponseExpert();
				}
			},{
				text : '审核',
		    	iconCls : 'icon-useredit',
		    	handler : function() {
				top.$("#" + iframe)[0].contentWindow.auditmethods();
		    	}
			},{
				text : '提交',
				iconCls : 'icon-save',
				handler : function() {
					top.$("#" + iframe)[0].contentWindow.confirmmethods();
				}
			},{
				text : '取消',
				iconCls : 'icon-undo',
				handler : function() {
					winAudit.dialog('destroy');
				}
			}]
	});

	if (pagetype != null && pagetype != undefined) {
		top.$("#" + iframe)[0].contentWindow.pagetype = pagetype;
	}
	
	if (row != null && row != undefined) {
		top.$("#" + iframe)[0].contentWindow.row = row;
	}
	
	if (winAudit != null && winAudit != undefined) {
		top.$("#" + iframe)[0].contentWindow.winAudit = winAudit;
	}
	
	winAudit.dialog("open");
};


/**科技项目添加编辑*/
function loaddialogbyscience(iframe, html, mtitle, mwidth, mheight, pagetype, datagrid, row) {
	var query = top.$

	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
		onDestroy : function() {
			cleanSelect(datagrid);
			flashTable(datagrid);
		},
		buttons : [{
			text : '下一步',
			iconCls : 'icon-usergo',
			handler : function() {		
				top.$("#" + iframe)[0].contentWindow.nextstep();
			}
		},{text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				top.$("#" + iframe)[0].contentWindow.confirmmethods();
			},
		},{
			text : '取消',
			iconCls : 'icon-undo',
			handler : function() {
				winAudit.dialog('destroy');
			}
		}]		
	});
	
	if (pagetype != null && pagetype != undefined) {
		top.$("#" + iframe)[0].contentWindow.pagetype = pagetype;
	}
	
	if (row != null && row != undefined) {
		top.$("#" + iframe)[0].contentWindow.row = row;
	}
	
	winAudit.dialog("open");
};
/**
 * 科技项目审核专用-勿动勿用
 * @param iframe
 * @param html
 * @param mtitle
 * @param mwidth
 * @param mheight
 * @param pagetype
 * @param datagrid
 * @param row
 */
function loaddialogbyscienceaudit(iframe, html, mtitle, mwidth, mheight, pagetype, datagrid, row) {
	var query = top.$
	var winAudit = query(html).dialog({
		width : (mwidth ? mwidth : 500),
		height : (mheight ? mheight : 500),
		title : mtitle,
		closed : true,
		cache : false,
		modal : true,
		onClose:function(){
			winAudit.dialog('destroy');
		},
		onDestroy:function() {
			cleanSelect(datagrid);
			flashTable(datagrid);
		},
		onBeforeOpen:function() {
			//取当前dialog的buttons
			var buttons = winAudit[0].parentElement.children[2].children;
			//buttons = buttons.children;
			
			if(pagetype == 'default') {//默认模式，进入无任何权限
				buttons[0].style.display = 'none';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = 'none';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'search') {//查看模式,进入只有查看功能
				buttons[0].style.display = '';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = 'none';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'audit_search') {//审核+查看模式,进入有查看和审核功能
				buttons[0].style.display = '';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = '';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'office_search') {//分发处室+查看
				buttons[0].style.display = '';
				buttons[1].style.display = '';
				buttons[2].style.display = 'none';
				buttons[3].style.display = 'none';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'expert_search') {//分发专家+查看
				buttons[0].style.display = '';
				buttons[1].style.display = 'none';
				buttons[2].style.display = '';
				buttons[3].style.display = 'none';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'audit') {//审核
				buttons[0].style.display = 'none';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = '';
				buttons[4].style.display = 'none';
			} else if(pagetype == 'submit') {//提交
				buttons[0].style.display = 'none';
				buttons[1].style.display = 'none';
				buttons[2].style.display = 'none';
				buttons[3].style.display = 'none';
				buttons[4].style.display = '';
			} 
		},
		buttons : [{
				text : '查看审核意见',
		    	iconCls : 'icon-search',
		    	handler : function() {
				top.$("#" + iframe)[0].contentWindow.auditinfosearch();
		    	}
			},{
				text : '分发处室',
				iconCls : 'icon-list',
				handler : function() {
					top.$("#" + iframe)[0].contentWindow.disponseOffice();
				}
			},{
				text : '分发专家',
				iconCls : 'icon-usergo',
				handler : function() {
					top.$("#" + iframe)[0].contentWindow.disponseExpert();
				}
			},{
				text : '审核',
		    	iconCls : 'icon-useredit',
		    	handler : function() {
				top.$("#" + iframe)[0].contentWindow.auditmethods();
		    	}
			},{
				text : '提交',
				iconCls : 'icon-save',
				handler : function() {
					top.$("#" + iframe)[0].contentWindow.confirmmethods();
				}
			},{
				text : '取消',
				iconCls : 'icon-undo',
				handler : function() {
					winAudit.dialog('destroy');
				}
			}]
	});

	if (pagetype != null && pagetype != undefined) {
		top.$("#" + iframe)[0].contentWindow.pagetype = pagetype;
	}
	
	if (row != null && row != undefined) {
		top.$("#" + iframe)[0].contentWindow.row = row;
	}
	
	if (iframe != null && iframe != undefined) {
		top.$("#" + iframe)[0].contentWindow.iframe = iframe;
	}
	
	if (winAudit != null && winAudit != undefined) {
		top.$("#" + iframe)[0].contentWindow.winAudit = winAudit;
	}
	
	
	
	winAudit.dialog("open");
};
