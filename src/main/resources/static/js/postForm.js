$(document).ready(function() {
    var baseurl = 'http://' + window.location.href.split('/')[2]
    $('#editor').summernote(
        {
            height: 300,
            toolbar: [
                // [groupName, [list of button]]
                ['style', ['bold', 'italic', 'underline', 'clear']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']]
            ]
        }
    );

    $('#editor').summernote('code','');

    $("#submitBtn").click(function(){
        var html = $("#editor").summernote('code');
        var data = {content: html, active: true}
        data = JSON.stringify(data);
        console.log(html);
        if(html.trim() !== '') {
            $.ajax({
                contentType: 'application/json',
                url: `${baseurl}/api/post`,
                method: 'POST',
                data: data
            }).done(function () {
                swal("Post Created", '', "success")
                $("#editor").summernote('code', '');
            }).fail(function (err) {
                swal('Post failed', 'Something went wrong.', 'error')
            })
        }else{
            swal('Post failed', 'Can not create an empty post', 'error')
        }
    })
});