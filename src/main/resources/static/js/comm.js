
//全选
$(function () {
	$('#selectAll').click(function(){
		let that = this;
		$('.eid').prop('checked',that.checked);
	});
});
//安全退出
function logout(){
	//提示确认框
	layer.confirm('是否确定退出?',{
		btn:['确认','取消'],   //配置按钮
		icon:3				 //配置图标
	},function(){
		location.href='/blog/user/loginOut.action';
	});
}
function ChangeDateFormat(time) {
	var datetime = new Date();
	datetime.setTime(time);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}