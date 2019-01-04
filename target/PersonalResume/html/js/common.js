function echo(msg){
	console.log(msg);
}
function recordGuest(){
	
}

function replace(str, before, after) {
    var reg = "/" + before + "/g";
    return str.replace(eval(reg), after);
}
function params() {
    var args = window.location.search;
    if (args == ""){
    	return {};
	}
	args = args.substr(1);
    args = replace(args, "&", '","');
    args = '({"' + replace(args, "=", '":"') + '"})';
    return eval(args);
}