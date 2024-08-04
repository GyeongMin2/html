let getPageName = document.querySelector('title').textContent
window.addEventListener('resize', ()=>{
  var windWidth = window.innerWidth;
  if(windWidth < 712) {
    document.querySelector('.showMobile').classList.remove('hide');
    document.querySelector('.mobileA').setAttribute(`href`,`./mobile/mobile_${getPageName}.html`);
  }else{
    document.querySelector('.showMobile').classList.add('hide');
  }
});
