
/**
 * Created by csdc01 on 2017/5/2.
 */
$(function () {
    $("#commit").on('click', function () {
        var array = $("#paper").serializeArray();
        var examId = $(".body").attr("id");
        var body = "{\"examInfo\":{\"examId\":\""+examId+"\"}, \"answerInfos\":";
        for (var i = 0; i < array.length; i++){
            var name = array[i].name;
            var value = array[i].value;

            添加拼接json字符串代码
        }
    })
})
function startExam(timelength) {
    $(".body").show();
    $("#start").remove();
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