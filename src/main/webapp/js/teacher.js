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
var edit = false;
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
        // if ($(".paper").attr("id") == null){
        //     alert("请先创建试卷");
        //     return;
        // }
        // if (edit){
        //     alert("请完善上个问题");
        //     return;
        // }
        $(".paper").append($("#radio").html());
        edit = true;
        $(".addOption").on('click', function () {
            $(this).before($("#option").html());
            $(".option").on('change',function () {
                if ($(this).val() == ""){
                    alert("不能为空！");
                    $(this).val("选项");
                }
            })
            $(".delete_option").on('click', function () {
                $(this).parent().remove();
            })
        })
        $(".delete_option").on('click', function () {
            $(this).parent().remove();
        })
        $(".option").on('change',function () {
            if ($(this).val() == ""){
                alert("不能为空！");
                $(this).val("选项");
            }
        })
        $(".cancle").on('click', function () {
            $(this).parent().parent().remove();
            edit = false;
        })
        $(".add").on('click', function () {
            var token = $.cookie("token");
            var $form = $(this).parent();
            var questionInfo = "{";
            var json = $form.serializeArray();
            for(var i=0; i<json.length;i++){
                var name = json[i].name;
                var value = json[i].value;
                if (name =="description"|| name=="score" || name=="analysis" || name=="essay_solution" || name == "questionType"){
                    questionInfo += "\""+name+"\":\""+value+"\"";
                    if (i != json.length-1){
                        questionInfo += ",";
                    }else{
                        questionInfo +="}";
                    }
                }else{
                    questionInfo += "\"options\":["
                    if (name == "content.isSolution"){
                        questionInfo += "{\"correct\":true,"
                        i++;
                        questionInfo += "\"content\":\""+json[i].value+"\"}"
                    }else{
                        questionInfo += "{\"content\":\""+value+"\"}";
                    }
                    if (i != json.length-1){
                        questionInfo += ",";
                    }else {
                        questionInfo += "]}";
                    }
                }
            }

            if ($(this).parent().attr("id") == null){
                $.ajax({
                    url:"/question/add",
                    dataType:"json",
                    type:"post",
                    contentType:"application/json",
                    beforeSend:function (request) {
                      request.setRequestHeader("token", token)
                    },
                    data:questionInfo,
                    success:function (data) {
                        edit = false;
                    },
                    error:function (request) {
                        alert(request.responseText);
                    }
                })
            }
        })

    })
})
