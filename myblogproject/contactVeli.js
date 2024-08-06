let obj = new Object();
//이름2자이상 10자 이내,필수
//아이디 : 8자리 이상 20자 이내,필수
//성별 : 필수
//이메일 : 10자리이상 200자리 이내,필수
//취미 : 선택
//지역 : 선택
const frm = document.querySelector("#frm");
document.querySelector(".subBtn").addEventListener("click", function () {
  obj.userName = document.querySelector("#name").value;
  obj.userEmail = document.querySelector("#email").value;
  obj.userTelNum = document.querySelector("#telnum").value;
  obj.userMessage = document.querySelector("#message").value;
  checkObj(obj);
});

function checkObj(obj) {
  if (
    validateUserName(obj.userName) &&
    validateUserEmail(obj.userEmail) &&
    validateUserTelNum(obj.userTelNum) &&
    validateUserMessage(obj.userMessage)
  ) {
    alert("contact 전송");
    console.log(obj);
    frm.action = "#";
    frm.method = "post";
    frm.submit();
  }
}

function validateUserName(userName) {
  if (userName.length >= 2 && userName.length <= 10) {
    console.log("이름 합격");
    return true;
  } else {
    alert("이름을 2글자 이상 10글자 이하로 입력하세요");
    return false;
  }
}

function validateUserEmail(userEmail) {
  if (userEmail.length >= 10 && userEmail.length <= 200) {
    console.log("이메일 합격");
    return true;
  } else {
    alert("이메일은 10자리 이상 200자리 이내로 입력해주세요");
    return false;
  }
}

function validateUserTelNum(userTelNum) {
  if (userTelNum.includes("-")) {
    alert("전화번호는 '-'를 제외한 숫자만 입력해주세요");
    return false;
  } else if (userTelNum.length >= 10 && userTelNum.length <= 20) {
    console.log("전화번호 합격");
    return true;
  } else {
    alert("전화번호는 10자리 이상 , 20자리 이하로 입력해주세요");
  }
}

function validateUserMessage(userMessage) {
  if (userMessage.length >= 1 && userMessage.length <= 1000) {
    console.log("메시지 합격");
    return true;
  } else {
    alert("메시지는 1글자 이상 1000자 이내로 작성해주세요");
    return false;
  }
}
