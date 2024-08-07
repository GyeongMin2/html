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

