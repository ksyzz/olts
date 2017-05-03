
/**
 * Created by csdc01 on 2017/5/2.
 */
window.onunload = function () {
    if($("#commit").attr("disabled")){
        commit();
    }

}
$(function () {
    $("#commit").on('click', function () {
        commit();
        $(this).attr("disabled", true);
    })
})
function commit() {
    var array = $("#paper").serializeArray();
    var examId = $(".body").attr("id");
    var body = "{\"examInfo\":{\"examId\":\""+examId+"\"}, \"answerInfos\":[{";
    var isOption = 0;
    for (var i = 0; i < array.length; i++){
        var name = array[i].name;
        var value = array[i].value;
        if (name == "questionId"){
            if (i == 0){
                body += "\"questionInfo\":{\"questionId\":\""+value+"\"},"
            }else {
                if (isOption != 0){
                    body += "]";
                }
                body += "},{\"questionInfo\":{\"questionId\":\""+value+"\"},"
            }
            isOption = 0;

        }else if (name == "essay_answer"){
            isOption = 0;
            body += "\"essay_answer\":\""+value+"\"";
        }else {
            if (isOption == 0){
                body += "\"answers\":[{\"optionId\":\""+value+"\"}";
            }else {
                body += ",{\"optionId\":\""+value+"\"}";
            }
            isOption ++;
        }
    }
    if (isOption == 0)
        body += "}";
    else
        body += "]}";
    body += "]}";
    var token = $.cookie("token");
    $.ajax({
        url:"/paper/add",
        type:"post",
        contentType:"application/json",
        dataType:"json",
        data:body,
        async:false,
        beforeSend:function (request) {
            request.setRequestHeader("token", token)
        },
        success:function (data) {
            $("#time").stopTime();
            $(".answer").attr("type","text");
            $(".answer").show();
            $("#time").html("总分："+data.score);
            for(var i = 0; i < data.answerInfos.length; i++){
                var id = data.answerInfos[i].questionInfo.questionId;
                var score = data.answerInfos[i].score;
                var type = data.answerInfos[i].questionInfo.questionType;
                if ((type == "RADIO" || type == "MULTIPLE" )){
                    if (score == 0){
                        $("#"+id).prepend("X")
                    }else {
                        $("#"+id).prepend("\\\/")
                    }

                }
            }
        },
        error:function (request) {
            alert(request.responseText);
        }
    })
}
function startExam(timelength) {
    $(".body").show();
    $("#start").remove();
    $("#commit").attr("disabled", false);

    var timeCount = timelength*60;
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
function getClockModel(count) {
    var text =  parseInt(count/3600)+":"+parseInt(count%3600/600)+""+parseInt(count%3600/60%10)+":"+parseInt(count%60/10)+""+count%10;
    return text;
}