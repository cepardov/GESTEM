$(document).ready(function(){
    $('.button-collapse').sideNav({});
    $('select').material_select();
});

$(document).ready(function(){
    // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
});

function showToast(message, duration){
    Materialize.toast(message, duration);
}
