$(".popupBtn").on("click", () => {
  $(".bg").addClass("show");
});
$(".clsBtn").on("click", () => {
  $(".bg").removeClass("show");
});
$(".red").on("click", () => {
  $("p").toggleClass("colorRed");
});
