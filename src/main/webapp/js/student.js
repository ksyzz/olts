/**
 * Created by csdc01 on 2017/4/24.
 */
window.onunload = function () {
    var token = $.cookie("token");
    $.ajax({
        url:"/account/logout?token="+token,
        async:false,
        type:"delete",
        success:function () {
            $.cookie('token', '', { expires: -1 });
        }
    })
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
    $("#join").on('click', function () {
        var examId = $("#examId").val();
        var password = $("#password").val();
        var studentId = $("#studentId").val();
        if (examId == "" || password == "" ||studentId == ""){
            alert("请补充完整试卷信息");
            return;
        }
        $.ajax({
            url:"/exam/get",
            type:"get",
            data:{studentId:studentId, examId:examId, password:password},
            dataType:"json",
            success:function (data) {
                // window.open("/pages/exam.jsp?examInfo="+JSON.stringify(data), "_self");
                // startExam(data);
            },
            error:function (request) {
                $(".error").html(request.responseText);
            }
        })

    })
})
function startExam(data) {
    $(".index").html(Mustache.render($("#test").html(), data));
    var timeCount = data.time_limited*60;
        $("#time").everyTime("1s",function () {
            timeCount--;
            if (timeCount == 0){
                $("#time").stopTime();
                commit();
            }
            var clock = getClockModel(timeCount);
            $(this).html(clock);
        })
}
function commit() {

}
function getClockModel(count) {
    var text =  parseInt(count/3600)+":"+parseInt(count%3600/600)+""+parseInt(count%3600/60%10)+":"+parseInt(count%60/10)+""+count%10;
    return text;
}