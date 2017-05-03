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
function assertEdit() {
    var a = false;
    $(".paper").children(".question").each(function (index) {

        if ($(this).css("display") == "block"){
            a = true;
        }
    })
    return a;
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
                    $this.parent().attr("id", data.examId);
                    $(".paper").prepend(data.examId)
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
        if ($(".paper").attr("id") == null){
            alert("请先创建试卷");
            return;
        }
        if (assertEdit()){
            alert("请完善上个问题");
            return;
        }
        $(".paper").append($("#radio").html());
        $(".addOption").on('click', function () {
            $(this).before($("#radio_option").html());
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
        question();
    })
    $("#addMultiple").on('click', function () {
        if ($(".paper").attr("id") == null){
            alert("请先创建试卷");
            return;
        }
        if (assertEdit()){
            alert("请完善上个问题");
            return;
        }
        $(".paper").append($("#multiple").html());
        $(".addOption").on('click', function () {
            $(this).before($("#multiple_option").html());
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
        question();
    })
    $("#addEssay").on('click', function () {
        if ($(".paper").attr("id") == null){
            alert("请先创建试卷");
            return;
        }
        if (assertEdit()){
            alert("请完善上个问题");
            return;
        }
        $(".paper").append($("#essay").html());
        question();
    })

})
function modify(ele) {
    var $this = $(ele);
    if (assertEdit()){
        alert("请完善上个问题");
        return;
    }
    $this.prev().show();
    $this.hide();
}
function question() {

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
        var token = $.cookie("token");

        var id = $(this).parent().parent().attr("id");
        if (id != null){
            $.ajax({
                url:"/question/delete/"+id,
                type:"delete",
                async:false,
                beforeSend:function (request) {
                    request.setRequestHeader("token", token)
                },
                error:function (request) {
                    alert(request.responseText);
                    return;
                }
            })
        }
        $(this).parent().parent().next().remove();
        $(this).parent().parent().remove();
    })
    $(".add").on('click', function () {
        var token = $.cookie("token");
        var $form = $(this).parent();
        var questionInfo = "{";
        var json = $form.serializeArray();
        var first = true;
        for(var i=0; i<json.length;i++){
            var name = json[i].name;
            var value = json[i].value;
            if (name =="description"|| name=="score" || name=="analysis" || name=="essay_solution" || name == "questionType"){
                if (value == ""){
                    alert(name+"can not be null");
                    return;
                }
                questionInfo += "\""+name+"\":\""+value+"\"";
                if (i != json.length-1){
                    questionInfo += ",";
                }else{
                    questionInfo +="}";
                }
            }else{
                if (first){
                    questionInfo += "\"options\":[";
                    first=false;
                }
                if (name == "option.isSolution"){
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
        var url;
        var type;
        if ($form.parent().attr("id") == null){
            url = "/question/add/"+$(".paper").attr("id");
            type = "post";
        }else {
            url =  "/question/modify/"+ $form.parent().attr("id");
            type="put";
        }
        $.ajax({
            url:url,
            dataType:"json",
            type:type,
            async:false,
            contentType:"application/json",
            beforeSend:function (request) {
                request.setRequestHeader("token", token)
            },
            data:questionInfo,
            success:function (data) {
                $form.parent().attr("id",data.questionId);
                $form.parent().hide();
                $form.parent().next().show();
                $form.parent().next().html(Mustache.render($("#detail").html(),data));

            },
            error:function (request) {
                alert(request.responseText);
            }
        })

    })

}