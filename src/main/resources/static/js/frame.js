$(function () {
    //轮播悬停触发箭头显示与隐藏
    $('#main-banner').hover(function () {
        $('.carousel-control').fadeIn();
    }, function () {
        $('.carousel-control').fadeOut();
    });
    //轮播设置
    $('.carousel').carousel({
        interval: 5000
    });

    $(window).scroll(function () {
        if ($(window).scrollTop() > 100) {
            $('.backtop').fadeIn(500);
        } else {
            $('.backtop').fadeOut(500);
        }
    });
    $('.backtop').on('click', function () {
        $('html,body').animate({scrollTop: 0}, 500);
    });
    (function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }
    })(jQuery);
    let $indexJsp = $("a[href='index.jsp']");
    $indexJsp.attr("href", "index.jsp");
    let $messageJsp = $("a[href='message.jsp']");
    $messageJsp.attr("href", "message.jsp");
    let $aboutJsp = $("a[href='about.jsp']");
    $aboutJsp.attr("href", "about.jsp");
    $.get("getBannerAll.action", function (data) {
        $.each(data, function (index, item) {
            let str = "";
            if (index === 0) {
                str += "<li data-target='#main-banner' data-slide-to='" + index + "' class='active'></li>";
            } else {
                str += "<li data-target='#main-banner' data-slide-to='" + index + "'></li>";
            }
            $("ol[class='carousel-indicators']").append(str);
            if (index === 0) {
                str = "<div class='item active'><img src='static/img/banner/" + item.img + "'>";
            } else {
                str = "<div class='item'><img src='static/img/banner/" + item.img + "'>";
            }
            str += "<div class='carousel-caption'><h2>" + item.name + "</h2>";
            str += "<p>" + item.summary + "</p></div></div>";
            $("div[class='carousel-inner']").append(str);
        })
    });
    $.get("getCategoryAll.action", function (data) {
        $.each(data, function (index, item) {
            let str = "";
            str += "<li><a href='category.jsp?" + "id=" + item.id + "'>" + item.name + "</a></li>";
            $("ul[class='dropdown-menu']").append(str);
        })

    });
    let split = window.location.pathname.split("/");
    let page = split[split.length - 1];
    if (page === "about.jsp") {
        $aboutJsp.addClass("top-active");
    } else if (page === "message.jsp") {
        $messageJsp.addClass("top-active");
    } else if (page === "category.jsp") {
        $("a[class='dropdown-toggle']").addClass("top-active");
    } else if (page === "index.jsp") {
        $indexJsp.addClass("top-active");
    }
});

function ChangeDateFormat(time) {
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}