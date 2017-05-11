/**
 * Created by 夜落尽&天未明 on 2017/5/7 0007.
 */
var paperInfos;
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

    $("#list").on('click', function () {
        $("#paper_list").show();
        $("#topk_view").hide();
        $("#distribution_view").hide();
    })
})
function distribution(examId) {
    var token = $.cookie("token");
    $("#paper_list").hide();
    $("#topk_view").hide();
    $("#distribution_view").show();
    $("#distribution_view").html("<img src='/exam/get/results/"+examId+"?token="+ token +"'>");
}
function grade(k, examId) {
    if(paperInfos == null){
        var token = $.cookie("token");

        $.ajax({
            url:"/paper/get/"+examId,
            dataType:"json",
            async:false,
            beforeSend:function (request) {
                request.setRequestHeader("token", token)
            },
            type:"get",
            success:function (data) {
                paperInfos = data;

            },
            error:function (request) {
                alert(request.responseText);
            }
        })

    }
    $("#student_list").html("");
    for (var i = 0; i < k && i < paperInfos.length; i++){
        var html = i+1 + ".  姓名" + paperInfos[i].accountInfo.userName + ",学号："+paperInfos[i].accountInfo.id + ",分数：" + paperInfos[i].score + "<br>"
        $("#student_list").append(html);
    }

}
