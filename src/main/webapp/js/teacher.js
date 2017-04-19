/**
 * Created by csdc01 on 2017/4/17.
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

    $(".create_exam").on('click', function () {
        var title = $("#title").val();
        var token = $.cookie("token");
        var password = $("#password").val();
        var $this = $(this);
        var time_limited = $("#time_limited").val();
        if (title == "" || time_limited == ""){
            alert("标题和时长不能为空！");
            return;
        }
        if ($this.html() == "创建"){
            $.ajax({
                url:"/exam/add",
                type:"post",
                data:{title:title, password:password, time_limited:time_limited, token:token},
                dataType:"json",
                success:function (data) {
                    $this.html("修改");
                    $this.parent().attr("id", data.id);
                },
                error:function (request) {
                    alert(request.responseText);
                }
            })
        }else{
            var id =  $this.parent().attr("id");
            var url = "/exam/modify/"+id+"?title="+title+"&password="+password+"&time_limited="+time_limited+"&token="+token;
            $.ajax({
                url: url,
                type:"put",
                error:function (request) {
                    alert(request.responseText);
                }
            })
        }

    })
    $("#addRadio").on('click', function () {

    })
})
