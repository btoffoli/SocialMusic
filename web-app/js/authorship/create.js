$(document).ready(function() {
    $("#name").autocomplete({
        source: function(request, response){
            $.ajax({
                url: "loadAuthorshipNames", // remote datasource
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
        }

    });
});