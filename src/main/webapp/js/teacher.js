/**
 * Created by csdc01 on 2017/4/17.
 */
$(function () {
    $("a").click( function () {
        $("a").css("color", "black");
        $(this).css("color", "dodgerblue");
    })

    $("a").hover(function () {
        $(this).css("color", "dodgerblue");
    },
    function () {

    })

    $("#exit").on('click', function () {
        $.ajax({
            url:"/account/logout",
            type:"delete",
            success:function () {
                window.open("/pages/login.jsp", "_self");
            }
        })
    })
})