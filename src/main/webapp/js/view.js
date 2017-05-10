/**
 * Created by 夜落尽&天未明 on 2017/5/7 0007.
 */
window.onload = function () {
    $("#search").css('color', 'dodgerblue');
    $("#create").css('color', 'black');
}
// $(function () {
//     $("#exit").on('click', function () {
//         var token = $.cookie("token");
//         $.ajax({
//             url:"/account/logout?token="+token,
//             type:"delete",
//             success:function () {
//                 $.cookie('token', '', { expires: -1 });
//                 window.open("/pages/login.jsp", "_self");
//             }
//         })
//     })
// })
function showPaper(ele){
    var target = $(ele).parent().next();
    var status = target.css("display")
    if (status == "block"){
        target.hide();

    }else {
        target.show();
    }
}
$(function () {
    $("#exit").on('click', function () {
        var token = $.cookie("token");
        $.ajax({
            url:"/account/logout?token="+token,
            type:"delete",
            success:function () {
                $.cookie('token', '', { expires: -1 });
                window.open("/pages/login.jsp", "_self");
            }
        })
    })
    $("#topk").on('click', function () {
        $("#paper_list").hide();
        $("#topk_view").show();
        $("#distribution_view").hide();
    })
    $("#distribution").on("click", function () {
        $("#paper_list").hide();
        $("#topk_view").hide();
        $("#distribution_view").show();
    })
    $("#list").on('click', function () {
        $("#paper_list").show();
        $("#topk_view").hide();
        $("#distribution_view").hide();
    })
})
function grade(paperInfos, k) {
    // alert(k);
    //
    // for (var i = 0; i < paperInfos.length; i++){
    //     alert(i);
    //     alert(paperInfos[i]);
    // }

}