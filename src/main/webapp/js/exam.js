
/**
 * Created by csdc01 on 2017/5/2.
 */
function startExam(timelength) {
    $(".body").show();
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