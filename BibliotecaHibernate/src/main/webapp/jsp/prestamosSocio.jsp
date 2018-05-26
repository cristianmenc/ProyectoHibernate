<html>

<link href="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/css/tabulator.min.css" rel="stylesheet">
<script type="text/javascript"
    src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
                  
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/js/tabulator.min.js"></script>



<body>
<h2>LISTA DE PRESTAMOS DEPENDIENDO DEL SOCIO</h2>
<h3>Tabla socios</h3>

<div id="table-socios"></div>
<h3>Historial de prestamos</h3>
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
        	 mostrarPrestamos(id_socio, nombre);
       }
    });
    
</script>

<script type="text/javascript">
     $("#table-socios").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaSocios");
</script>


<script>

var create = false;
function mostrarPrestamos(id_socio, nombre) {
	if(create == false){
		$("#table-libros").tabulator({
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
	            ]
	      }
	   );
		create = true;
	}
	
	var sendInfo = {
            "socio": {"id_socio":id_socio},
        };
        
    $.ajax({
                method : 'POST',
                url : "http://localhost:8080/BibliotecaHibernate/listaPrestamosSocio",

                //Add form data
                data: JSON.stringify(sendInfo),
                contentType: "application/json; charset=utf-8",

                success : function(data) {
                    console.log(data);
                    $("#table-libros").tabulator("setData",data);
                },
                error : function(xhr, status, error) {
                    var err = eval("(" + xhr.responseText + ")");
                    console.log(err);                   
                }
            }); 
	//$("#table-libros").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaPrestamosSocio/"+id_socio);
}



</script>

</body>
</html>
