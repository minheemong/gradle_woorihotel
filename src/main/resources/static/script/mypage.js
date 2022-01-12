
function idcheck(){
	var url = "fineIdPwd";
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250";
	window.open(url, "IdCheck", opt);
}//완료


function findPw(){
	var url = "findPwForm";
	var opt = "toolbar=no,menubar=no,scrollbars=no,resizable=no,width=700,";
	opt = opt + "height=500, top=300, left=300";
	window.open(url, "Find Id/Pw", opt);
}

function go_profilePw(){
	document.profileForm.action ="profilePw";
	document.profileForm.submit(); 
}

function quit(){
	alert("탈퇴가 완료되었습니다" );
	document.quitOk.action ="quit";
	document.quitOk.submit(); 
}


function bookCancelCheck(){
	var result =  document.frm.result.value;
	if(result == 0){
		var con = confirm("취소하시겠습니까?");
		if(con == true) return true;	
		else return false;
	}else if(result == 1){
		var con = confirm("약관에 의하여 취소시 위약금이 발생할 수 있습니다.\n취소하시겠습니까?");
		if(con == true) return true;
		else return false;	
	}else {
		return false;
	}
}




function changeroom(){

	var url = "changeRoom?bdseq="+document.frm.bdseq.value;
	var pop = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=250";
	window.open(url, "ChangeRoom", pop);
}
