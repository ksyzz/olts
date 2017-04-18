/**
 * Created by csdc01 on 2017/4/17.
 */
$(function () {
    $("#exit").on('click', function () {
        $.ajax({
            url:"/account/logout",
            type:"delete",
            success:function () {
                window.open("/pages/login.jsp", "_self");
            }
        })
    })
    $("#addRadio").on('click', function () {

    })
})
