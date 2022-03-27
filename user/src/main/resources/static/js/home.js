//域名
const domain = 'http://localhost:8082';
function addTag(){
    $('#Attr').modal(
        {
            keyboard: true

        });
}


$('#doSearch').click(function () {
    let url = $('#info').val();
    let option = $('#option').val();
    let attr = $('#current').html();
    switch (option) {
        case '0': return false;
        case '1': $.post(
            domain + "/script/run",
            {
                attr: attr,
                url: url
            }
        ).done(function () {
            alert('success');
        }).fail(function () {
            alert('fail');
        });
        //case '2': $.post();
    }
});