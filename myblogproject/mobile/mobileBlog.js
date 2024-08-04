let getPageName = $('.pageName').text();
console.log(getPageName);
$(window).on('resize', ()=>{
  var windWidth = window.innerWidth;
  console.log(windWidth);
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


// $(document).ready(function () {
//   let currentPage = 0;
//   const pages = $('.postCont');
//   const totalPages = pages.length;

//   function showPage(page) {
//     pages.addClass('hide');
//     $(pages[page]).removeClass('hide');
//   }

//   $('#previousbt').click(function (e) {
//     e.preventDefault();
//     if (currentPage > 0) {
//       currentPage--;
//       showPage(currentPage);
//     }
//   });

//   $('#nextbt').click(function (e) {
//     e.preventDefault();
//     if (currentPage < totalPages - 1) {
//       currentPage++;
//       showPage(currentPage);
//     }
//   });

//   showPage(currentPage);
// });