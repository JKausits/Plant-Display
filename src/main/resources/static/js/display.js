$(document).ready(function(){
    var baseurl = window.location.href;
    var posts;
    var index = 0;
    var timePer100 = 2000;
    startPageReloadTimer();
    getActivePosts();
    setDate();
    setInterval(function(){setTime()}, 1000);

    function getActivePosts(){
        $.ajax({
            url: `${baseurl}/api/posts/active`,
            method: 'GET',
            success: function(data){
                posts = data;
                scroll();
            }
        })
    }

    function setDate(){
        var date = new Date();
        var dateString = `${date.getMonth() + 1}\\${date.getDate()}\\${date.getFullYear()}`
        $("#date").text(dateString);
    }

    function setTime(){
        var date = new Date();
        var hour = formatHour(date.getHours());
        var minute = formatLessThanTen(date.getMinutes())
        var second = formatLessThanTen(date.getSeconds());
        var time = `${hour}:${minute}:${second}`
        $("#time").text(time);
    }

    function formatHour(hour){
        if(hour > 12){
            hour -= 12;
        }
        hour = formatLessThanTen(hour);
        return hour;
    }

    function formatLessThanTen(time){
        if(10 > time){
            time = '0' + time;
        }
        return time;
    }

    function changePost(){
        var ticker = $("#ticker");

        ticker.html(posts[index].content);

        if(++index >= posts.length) {
            index = 0;
        }

    }

    function scroll(){
        changePost();
        var ticker = $("#ticker");
        var height = ticker.height();
        var time = (2 * height) / 100 * timePer100;
        ticker.css('top', height * 1.5);
        ticker.animate({ "top": -height }, time, "linear", function(){
            setTimeout(function(){scroll();}, 100);
        });
    }

    function startPageReloadTimer(){
        setInterval(function(){location.reload()}, 15 * 60 * 1000)
    }

})