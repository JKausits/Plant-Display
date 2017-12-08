$(document).ready(function(){
    var baseurl = 'http://' + window.location.href.split('/')[2]
    var table = document.getElementById('posts')

    //Function for toggling activate
    $(".toggle-deactivate-button").click(function(){
        var btn = $(this);
        var rowIndex = btn.closest('td').parent()[0].sectionRowIndex;
        var content = $(table.rows[rowIndex].cells[0]).html();
        var active = btn.hasClass('btn-danger');
        var id = $(table.rows[rowIndex].cells[2]).text()
        var data = {content, active}

        $.ajax({
            contentType: 'application/json',
            url: `${baseurl}/api/post/${id}`,
            type: 'PATCH',
            data: JSON.stringify(data)
        }).done(function(){
            toggleButtons(btn)
        })



    })

    //Function for deleting row
    $(".delete-button").click(function () {
        var btn = $(this);
        var rowIndex = btn.closest('td').parent()[0].sectionRowIndex;
        var id = $(table.rows[rowIndex].cells[2]).text()

        $.ajax({url: `${baseurl}/api/post/${id}`, method: 'DELETE'}).then(function(){
            table.deleteRow(rowIndex);
        })


    })

    //Toggles the activate/deactivate buttons text and color
    function toggleButtons(btn){
        btn.toggleClass('btn-success');
        btn.toggleClass('btn-danger');
        btn.text(btn.hasClass('btn-success') ? 'Deactivate' : 'Activate');
    }
})
