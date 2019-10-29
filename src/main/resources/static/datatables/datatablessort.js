$(document).ready(function () 
{
	$('#dtBasicExample').DataTable(
	{        		
		"language":{
        					"lengthMenu": "Wyświetl _MENU_ wierszy na stronę",
            				"zeroRecords": "Brak danych do wyświetlenia",
            				"info": "Strona _PAGE_ z _PAGES_",
            				"infoEmpty": "Brak danych do wyświetlenia",
            				"infoFiltered": "(wyszukane spośród _MAX_ wszystkich wierszy)",
            				"search": "Wyszukaj:",
            				"paginate": 
            							{
                						"first":      "Pierwsza",
                						"last":       "Ostatnia",
                						"next":       "Następna",
                						"previous":   "Poprzednia"
            							}
        			},
    });
	$('.dataTables_length').addClass('bs-select');


});


