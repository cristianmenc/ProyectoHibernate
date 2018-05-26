<html>

<link href="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/css/tabulator.min.css" rel="stylesheet">
<script type="text/javascript"
    src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
                  
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/js/tabulator.min.js"></script>



<body>
<h2>Testing tabulator</h2>
<h3>Tabla socios</h3>

<div id="table-socios"></div>
<h3>Tabla libros</h3>
<div id="table-libros"></div>

<script type="text/javascript">
     
     $("#table-socios").tabulator({
         layout: "fitColumns",
         columns:[
             {title:"Id", field:"id_socio"},
             {title:"Nombre", field:"nombre"},
             {title:"Apellidos", field:"apellidos"},
             {title:"DNI", field:"dni"},
             {title:"Fecha Nacimiento", field:"f_nacimiento"},
             {title:"Fecha de Alta", field:"f_alta"},
             {title:"Fecha de Baja", field:"f_baja"},
         ],
         rowClick:function(e, row){
        	 var id_socio = row.getData().id_socio;
        	 var nombre = row.getData().nombre;
          	if (confirm("Esta seguro que quiere este socio?")) {
				mostrarLibros(id_socio, nombre);
			}
       }
    });
    
</script>

<script type="text/javascript">
     $("#table-socios").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaSociosNoSancionados");
</script>


<script>

function mostrarLibros(id_socio, nombre) {
	$("#table-libros").tabulator({
		layout: "fitColumns",
        columns:[
            {title:"Id", field:"id_libro"},
            {title:"Titulo", field:"titulo"},
            {title:"Autor", field:"autor"},
            {title:"ISBN", field:"isbn"},
            {title:"Genero", field:"genero"},
            {title:"Fecha de Alta", field:"f_alta"},
            {title:"Fecha de Baja", field:"f_baja"},
            ],
        	rowClick:function(e, row){
        		var id_libro = row.getData().id_libro;
        		var titulo = row.getData().titulo;
              	if (confirm("Esta seguro que quiere este socio "+nombre+" y este libro "+titulo+"?")) {
    				crearPrestamo(id_socio , id_libro);
    			}
			}
      }
   );
	$("#table-libros").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaLibrosNoPrestados");
}


function crearPrestamo(id_socio , id_libro){
    
    var sendInfo = {
            "socio": {"id_socio":id_socio},
            "libro": {"id_libro": id_libro}
        };
        
    $.ajax({
                method : 'POST',
                url : "http://localhost:8080/BibliotecaHibernate/crearPrestamo",

                //Add form data
                data: JSON.stringify(sendInfo),
                contentType: "application/json; charset=utf-8",

                success : function(data) {
                    console.log(data);
                    //window.location.href = 'http://localhost:8080/BibliotecaSpring/listaPrestamo.jsp';
                    window.location.href = 'http://localhost:8080/BibliotecaHibernate/jsp/listaPrestamo.jsp';
                },
                error : function(xhr, status, error) {
                    var err = eval("(" + xhr.responseText + ")");
                    console.log(err);                   
                }
            }); //End of Ajax
           
            
            
     // End of myFucntion
    
	}
</script>

</body>
</html>
