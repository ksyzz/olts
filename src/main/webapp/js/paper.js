/**
 * Created by 夜落尽&天未明 on 2017/5/7 0007.
 */
function showPaper(ele){
    var target = $(ele).parent().next();
    var status = target.css("display")
    if (status == "block"){
        target.hide();

    }else {
        target.show();
    }
}