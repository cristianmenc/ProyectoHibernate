<html>

<link href="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/css/tabulator.min.css" rel="stylesheet">
<script type="text/javascript"
    src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
                  
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/js/tabulator.min.js"></script>



<body>
<h2>LISTA DE PRESTAMOS REALIZADOS</h2>

<div id="example-table"></div>

<script type="text/javascript">

var basura = function(e) {
	return '<img width="50px" src=../../resources/images/trash.jpg>';
	}
     
     $("#example-table").tabulator({
         layout: "fitColumns",
         columns:[
             {title:"Id", field:"id_prestamo"},
             {title:"Id Socio", field:"socio.id_socio"},
             {title:"Nombre Socio", field:"socio.nombre"},
             {title:"Apellidos Socio", field:"socio.apellidos"},
             {title:"Id Libro", field:"libro.id_libro"},
             {title:"Titulo libro", field:"libro.titulo"},
             {title:"Fecha de Prestamo", field:"f_prestamo"},
             {title:"Fecha de Devolucion", field:"f_devolucion"},
             {title:"Fecha de Devolucion Estimada", field:"f_estimada_devolucion"},
         ],
    });
    
</script>

<script type="text/javascript">
     $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaPrestamos");
</script>

<script>
function myFunction(){
    
    var titulo = $("#titulo").val();
    var autor = $("#autor").val();
    var isbn = $("#isbn").val();
    var genero = $("#genero").val();
    
    var sendInfo = {
            "titulo": titulo,
            "autor": autor,
            "isbn": isbn,
            "genero": genero
        };
        
    $.ajax({
                method : 'POST',
                url : "http://localhost:8080/BibliotecaHibernate/crearPrestamo",

                //Add form data
                data: JSON.stringify(sendInfo),
                contentType: "application/json; charset=utf-8",

                success : function(data) {
                    console.log(data);
                    $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaPrestamos");
                    
                },
                error : function(xhr, status, error) {
                    var err = eval("(" + xhr.responseText + ")");
                    console.log(err);                   
                }
            }); //End of Ajax
           
            //window.location.href = 'http://localhost:8080/BibliotecaSpring/listaLibros';
            
    }   // End of myFucntion
    
    function eliminarLibro(idLibro) {
    	
    	 var sendInfo = {
    	            "id_libro": idLibro
    	        };
    	 
    	$.ajax({
            method : 'POST',
            url : "http://localhost:8080/BibliotecaHibernate/borrarPrestamo",

            //Add form data
            data: JSON.stringify(sendInfo),
            contentType: "application/json; charset=utf-8",
            

            success : function(data) {
                console.log(data);
                $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaPrestamos");
                
            },
            error : function(xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
                console.log(err);                   
            }
        }); //End of Ajax
	}
</script>

</body>
</html>
