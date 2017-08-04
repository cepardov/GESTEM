$(document).ready(function(){
    $('.button-collapse').sideNav({});
    $('select').material_select();
    $('.modal').modal({
            dismissible: true, // Modal can be dismissed by clicking outside of the modal
            opacity: .5, // Opacity of modal background
            inDuration: 300, // Transition in duration
            outDuration: 200, // Transition out duration
            startingTop: '4%', // Starting top style attribute
            endingTop: '10%' // Ending top style attribute
        }
    );
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
