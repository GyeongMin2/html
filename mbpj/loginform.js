const idValue = {
  idInfo: [
    { name: "홍길동", id: "1234", pw: "4321" },
    { name: "강강민", id: "456", pw: "654" },
    { name: "홍동동", id: "dfas234", pw: "124xcz" },
    { name: "강길동", id: "1asd123", pw: "4124xcv" },
    { name: "경길동", id: "11231234", pw: "412rxc1" },
    { name: "민길동", id: "12asdfa4", pw: "zxc1221" },
    { name: "곽길동", id: "1acvz2134", pw: "tuio54" },
    { name: "김동길", id: "1123d34", pw: "3tgf90" },
    { name: "이길동", id: "1213cvc4", pw: "12214xcvz1" },
    { name: "강경민", id: "kgmksw", pw: "kgmksw" },
  ],
};
const loadIdArr = [];
const loadPwArr = [];
let idCheck = 0;
for (let a = 0; a < idValue.idInfo.length; a++) {
  loadIdArr.push(idValue.idInfo[a].id)
  loadPwArr.push(idValue.idInfo[a].pw)
}

let loginBtn = document.querySelector(".loginbtn")
loginBtn.addEventListener("click", getId);
function getId() {
  console.log(document.querySelector("#id").value)
  console.log(document.querySelector("#pw").value)
  let userId = document.querySelector("#id").value;
  let userPw = document.querySelector("#pw").value;
  if (loginCheck(userId, userPw) == true) {
    document.querySelector('.black-bg').classList.remove('hide');
    document.querySelector('.sucLogin').textContent = `${idValue.idInfo[idCheck].name}님 로그인 성공!`
  } else {
    document.querySelector('.black-bg').classList.remove('hide');
    document.querySelector('.sucLogin').textContent = "아이디 또는 비밀번호가 잘못되었습니다."
  }
}
function loginCheck(id, pw) {
  idCheck = loadIdArr.indexOf(`${id}`)
  if (idCheck == -1) {
    return false;
  } else if (pw == loadPwArr[idCheck]) {
    return true;
  }
}
document.querySelector('.clsBtn').addEventListener('click', () => {
  document.querySelector('.black-bg').classList.add('hide');
})