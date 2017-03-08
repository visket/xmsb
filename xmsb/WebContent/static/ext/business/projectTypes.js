
/**
 * 封装需要判断的项目类型
 * 
 */
var Project_type = {
	tbz : 'XMZT_TBZ', //填报中
	th : 'XMZT_TH',//退回
	ysl : 'XMZT_YSL',
	sbz : 'XMZT_SBZ',
	shz : 'XMZT_SHZ',
	spz : 'XMZT_SPZ',
	ysp : 'XMZT_YSP',
	ywc : 'XMZT_YWC',
	//重置所有按钮
	init_linkButton_show : function(toolbar) {
		$('#'+toolbar+' .easyui-linkbutton').show();
	},
	
	set_linkButton_show : function(row) {
		if(this.tbz == row.statusId || this.th == row.statusId) {
			$('#equi_edit').hide();
			$('#equi_declare').hide();
			$('#equi_del').hide();
		}
	}
};




