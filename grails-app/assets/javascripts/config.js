$(document).ready(function(){
    $('.button-collapse').sideNav({});
    $('select').material_select();

});

$(document).ready(function(){
    // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
});

$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 100, // Creates a dropdown of 15 years to control year,
    today: 'Hoy',
    clear: 'Borrar',
    close: 'Ok',
    closeOnSelect: true, // Close upon selecting a date,
    format: 'dd/mm/yyyy'
});

function showToast(message, duration){
    Materialize.toast(message, duration);
}
