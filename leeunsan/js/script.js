var menuList = document.getElementById("menuList");
var header = document.querySelector("header");
var wrapper = document.querySelectorAll(".wrapper");
var dimm = document.querySelector(".dimm");

//화면클릭시 메뉴 숨김 펑션
function back() {
  menuList.classList.remove("on");
  // for (var wrap of wrapper) {
  //   wrap.classList.remove("move", "more");
  // }
  dimm.classList.remove("wrap");
}

//메뉴아이콘토글
function togglemenu() {
  dimm.classList.add("wrap");
  menuList.classList.toggle("on");
  // for (var wrap of wrapper) {
  //   wrap.classList.add("more");
  // }
}

//화면클릭시 메뉴 숨김 실행
dimm.onclick = function () {
  back();
};

// for (var i in wrapper) {
//   wrapper[i].onclick = function () {
//     back();
//   };
// }

//네비 스크롤
document.addEventListener("scroll", function () {
  var scroll_position = window.scrollY;
  var windowWidth = window.width;
  if (scroll_position > 30) {
    header.classList.add("change");
  } else {
    header.classList.remove("change");
  }
});

//로그인
function signIn() {
  // var $logfrm = $("[name=signInFrm]");
  // console.log($logfrm);
  // var $key = $logfrm.find("#id").val();
  //console.log(userid + " " + value);
  //var idvalue = localStorage[$key.val()];

  var userId = $("#id").val();
  var userPwd = $("#password").val();
  console.log(userId);
  console.log(userPwd);
  var userInfo = JSON.parse(localStorage.getItem("userInfo"));
  // console.log(userInfo);

  for (var i in userInfo) {
    if (userId == userInfo[i]["id"] && userPwd == userInfo[i]["pwd"]) {
      alert("로그인");
    } else {
      alert("아이디, 비밀번호가 일치하지 않습니다.");
    }
  }
  $("[name=signInFrm]").each(function () {
    this.reset();
  });
}

//가입 웹스토리지
function signUpInfo() {
  var $frm = $("[name=signUpFrm]");

  console.log(userInfo);

  var idValue = $frm.find("#idUp").val();
  var pwdValue = $frm.find("#pwdUp").val();
  var emailValue = $frm.find("#email").val();

  var regId = /^[a-z].{2,14}$/;
  var regPwd1 = /^.{8,15}$/;
  var regPwd2 = /[\*!@&]/g;
  var regEmail = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;

  if (idValue == "" || pwdValue == "") {
    return false;
  } else if (
    regId.test(idValue) == true &&
    regPwd1.test(pwdValue) == true &&
    regPwd2.test(pwdValue) == true &&
    regEmail.test(emailValue) == true
  ) {
    var userInfo = {
      id: $frm.find("#idUp").val(),
      pwd: $frm.find("#pwdUp").val(),
      email: $frm.find("#email").val(),
    };
    var userInfoEntries = JSON.parse(localStorage.getItem("userInfo")) || [];

    userInfoEntries.push(userInfo);

    //json변환
    var userJson = JSON.stringify(userInfoEntries);

    //저장
    localStorage.setItem("userInfo", userJson);
    alert("가입성공!");
    $("[name=signUpFrm]").each(function () {
      this.reset();
    });
    return true;
  } else {
    alert("아이디 및 비밀번호를 올바른 형식으로 적어주세요");
  }
}
//로그인, 회원가입 창 변환
$(".up-btn").on("click", function () {
  // var $upcontainer = $("#signin section:last");
  // var $incontainer = $("#signin section:first");

  $(".col-signup").toggleClass("col-signup-show");
  $(".col-signin").toggleClass("col-signin-none");

  var $btn = $(".up-btn");
  var btnTxt = $btn.text();

  var $ask = $("#signin p");
  var askTxt = $ask.text();

  $btn.text(btnTxt == "SIGN UP" ? "SIGN IN" : "SIGN UP");
  $ask.text(
    askTxt == "계정이 없으신가요?" ? "이미 회원인가요?" : "계정이 없으신가요?"
  );
});

//베가스 설정
$(function () {
  $(".funk").vegas({
    slides: [
      { src: "./images/ewf.jpg" },
      { src: "./images/koolgang.jpeg" },
      { src: "./images/ed.jpg" },
    ],
    delay: 3500,
    timer: false,
    transition: "fade",
    animation: [
      "kenburnsUp",
      "kenburnsLeft",
      "kenburnsDown",
      "kenburnsRight",
      "kenburnsDownRight",
    ],
    overlay: "./overlays/06.png",
  });
  $(".rock").vegas({
    slides: [
      { src: "./images/acdc.jpg" },
      { src: "./images/uriahheep.jpg" },
      { src: "./images/pf.jpg" },
    ],
    delay: 4000,
    timer: false,
    transition: "fade",
    animation: ["kenburnsDown", "kenburnsUp", "kenburnsLeft", "kenburnsRight"],
    overlay: "./overlays/06.png",
  });
  $(".soul").vegas({
    slides: [
      { src: "./images/jb.jpg" },
      { src: "./images/rs.jpg" },
      { src: "./images/s&d.jpg" },
    ],
    delay: 4500,
    timer: false,
    transition: "fade",
    animation: ["kenburnsUp", "kenburnsDown", "kenburnsLeft", "kenburnsRight"],
    overlay: "./overlays/06.png",
  });
});

//캐로셀
$(function () {
  $(".owl-carousel").owlCarousel({
    responsive: {
      0: {
        items: 1,
        margin: 10,
        loop: true,
        nav: true,
        navText: ["이전", "다음"],
        autoplay: true,
        autoplayTimeout: 3000,
        autoplayHoverPause: true,
      },
      600: {
        items: 3,
        margin: 10,
        loop: true,
        nav: true,
        navText: ["이전", "다음"],
        autoplay: true,
        autoplayTimeout: 3000,
        autoplayHoverPause: true,
      },
    },
  });
});

// $(".in-btn").on("click", function () {
//   var $upcontainer = $("#signin section:last");
//   var $incontainer = $("#signin section:first");
//   // $upcontainer.hide();
//   //$incontainer.toggle("blind").css("display", "flex");

//   $(".col-signup").toggleClass("col-signin-none");
//   $(".col-signin").toggleClass("col-signin-none");
// });
