let getPageName = $('.pageName').text();
$(window).on('resize', ()=>{
  var windWidth = window.innerWidth;
  if(windWidth > 712) {
    $('.mobileA').removeClass('hide');
    $('.mobileA').attr(`href`,`../${getPageName}.html`);
  }else{
    $('.mobileA').addClass('hide');
  }
});

$('.navbar-toggler').on('click', () => {
  $(".list-group").toggleClass("hide");
})


