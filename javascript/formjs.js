let obj = new Object();
//이름2자이상 10자 이내,필수
//아이디 : 8자리 이상 20자 이내,필수
//성별 : 필수
//이메일 : 10자리이상 200자리 이내,필수
//취미 : 선택
//지역 : 선택
const frm = document.querySelector("#frm");
document.querySelector("#subBtn").addEventListener("click", function () {
  obj.userName = document.querySelector("#name").value;
  obj.userId = document.querySelector("#id").value;
  obj.userSexMale = document.querySelector("#sex_0").checked;
  obj.userSexFemale = document.querySelector("#sex_1").checked;
  obj.userEmail = document.querySelector("#email").value;
  obj.userIntro = document.querySelector("#intro").value;
  checkObj(obj);
});

function checkObj(obj) {
  if (
    validateUserName(obj.userName) &&
    validateUserId(obj.userId) &&
    validateUserSex(obj.userSexMale, obj.userSexFemale) &&
    validateUserEmail(obj.userEmail) &&
    validateUserIntro(obj.userIntro)
  ) {
    alert("회원가입 성공~!");
    console.log(obj);
    frm.action = "https://www.naver.com";
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

function validateUserId(userId) {
  if (userId.length >= 8 && userId.length <= 20) {
    console.log("아이디 합격");
    return true;
  } else {
    alert("아이디를 8글자 이상 20글자 이하로 입력하세요");
    return false;
  }
}

function validateUserSex(userSexMale, userSexFemale) {
  if (userSexMale || userSexFemale) {
    console.log("성별 선택됨");
    console.log("남자: " + userSexMale);
    console.log("여자: " + userSexFemale);
    return true;
  } else {
    alert("성별은 필수 입력란입니다.");
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

function validateUserIntro(userIntro) {
  if (userIntro.length >= 1 && userIntro.length <= 1000) {
    console.log("자기소개 합격");
    return true;
  } else {
    alert("자기소개는 1글자 이상 1000자 이내로 작성해주세요");
    return false;
  }
}
let trtd = 1;

document.querySelector("#plusBtn").addEventListener("click", () => {
  let row = document.querySelector("#tbody").insertRow(1);
  for (i = 0; i < 3; i++) {
    let creatCell = row.insertCell(i);
    creatCell.appendChild(document.createElement("input", "text"));
  }
  let btn = row.insertCell(3);
  let make = document.createElement("button");
  let cc = btn.appendChild(make);
  make.className = "cansBtn";
});
