let getPageName = document.querySelector("title").textContent;
window.addEventListener("resize", () => {
  var windWidth = window.innerWidth;
  if (windWidth < 712) {
    document.querySelector(".showMobile").classList.remove("hide");
    document
      .querySelector(".mobileA")
      .setAttribute(`href`, `./mobile/mobile_${getPageName}.html`);
  } else {
    document.querySelector(".showMobile").classList.add("hide");
  }
});
/*

.head_item:hover {
    color: black;
    font-weight: bold;
    text-shadow: 1px 2px 5px rgb(43, 43, 43);
}

.head_item:active {
    color: aquamarine;
}

*/

let getHeadItem = document.querySelectorAll(".head_item");
for (let i = 0; i < getHeadItem.length; i++) {
  getHeadItem[i].addEventListener("mouseover", () => {
    getHeadItem[i].classList.add("head_itemHover");
  });
  getHeadItem[i].addEventListener("mouseout", () => {
    getHeadItem[i].classList.remove("head_itemHover");
  });
  getHeadItem[i].addEventListener("mousedown", () => {
    getHeadItem[i].classList.add("head_itemActive");
  });
  getHeadItem[i].addEventListener("mouseup", () => {
    getHeadItem[i].classList.remove("head_itemActive");
  });
}

let getHeadName = new Array();
let getHeadNameTrim = new Array();
for (let i = 0; i < getHeadItem.length; i++) {
  getHeadName[i] = getHeadItem[i].textContent.trim();
  getHeadItem[i].setAttribute("href", `./${getHeadName[i]}.html`);
}

let scrollPosition = 0;
let c = 0;
window.addEventListener("scroll", () => {
  scrollPosition = window.scrollY;
  console.log(scrollPosition);
  if (scrollPosition >= 700) {
    document.querySelector(".pageui").classList.remove("dpNone");
    document.querySelector(".goNextAlert").classList.remove("dpNone");
    if (scrollPosition >= 1214) {
      document.querySelector(".pageui").classList.add("dpNone");
      document.querySelector(".goNextAlert").classList.add("dpNone");
      window.scrollTo({
        top: 0,
        behavior: "smooth",
      });
    }
  } else if (scrollPosition <= 500) {
    document.querySelector(".pageui").classList.add("dpNone");
  }
});
let preBtn = document.querySelector("#previousbt");
let nextBtn = document.querySelector("#nexobt");

//스크롤 다 내리면 다음페이지로 가기 버튼활성화 , 누르면 화면 맨위로 올라가면서
//다음페이지 보여주기
