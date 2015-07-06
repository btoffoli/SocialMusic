$(document).ready(function() {
    $("#name").autocomplete({
        source: function(request, response){
            var albumId = $('#album').val()
            request.albumId = albumId;
            $.ajax({
                url: "loadMusicNames", // remote datasource
                data: request,
                complete: function() {
                    //alert('complete...')
                },
                success: function(data){
                    response(data); // set the response
                    //alert('teste')
                },
                error: function(){ // handle server errors
                    alert('Falhou....')
                }
            });
        },
        minLength: 4,
        select: function( event, ui ) {
            console.log(ui)
            //log( ui.item ?
            //"Selected: " + ui.item.label :
            //"Nothing selected, input was " + this.value);
        },
        //open: function() {
        //    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
        //},
        //close: function() {
        //    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
        //}
    });
});