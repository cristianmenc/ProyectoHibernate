<html>

<link href="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/css/tabulator.min.css" rel="stylesheet">
<script type="text/javascript"
    src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
                  
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/js/tabulator.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


<body>
<h2>Testing tabulator...</h2>

<div id="example-table"></div>

<button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-black">Insertar nuevo libro</button>

  <div id="id01" class="w3-modal">
    <div class="w3-modal-content">
      <header class="w3-container w3-teal"> 
        <span onclick="document.getElementById('id01').style.display='none'" 
        class="w3-button w3-display-topright">&times;</span>
        <h2>Insertar nuevo Libro</h2>
      </header>
      <div id="formularioNuevo" class="w3-container">
        <p>Titulo: <input type="text" id="titulo"></input></p>
		<p>Autor: <input type="text" id="autor"></input></p>
		<p>ISBN: <input type="text" id="isbn"></input></p>
		<p>Genero: <input type="text" id="genero"></input></p>
      </div>
      <footer class="w3-container w3-teal">
        <button onclick="myFunction()">Enviar</button>
      </footer>
    </div>
  </div>

<div>

 <div id="formularioModif" class="w3-modal">
    <div class="w3-modal-content">
      <header class="w3-container w3-teal"> 
        <span onclick="document.getElementById('id01').style.display='none'" 
        class="w3-button w3-display-topright">&times;</span>
        <h2>Modificar libro</h2>
      </header>
      <div class="w3-container">
        <input type="hidden" id="idLibro">
<p>Titulo: <input type="text" id="tituloModif"></input></p>
<p>Autor: <input type="text" id="autorModif"></input></p>
<p>ISBN: <input type="text" id="isbnModif"></input></p>
<p>Genero: <input type="text" id="generoModif"></input></p>
      </div>
      <footer class="w3-container w3-teal">
        <button onclick="modificarLibro()">Modificar libro</button>
      </footer>
    </div>
  </div>


<script type="text/javascript">

var basura = function(e) {
	return '<img width="50px" src=../resources/images/trash.jpg>';
	}
	
var editar = function(e) {
	return '<img width="50px" src=../resources/images/edit.png>';
	}
     
     $("#example-table").tabulator({
    	 layout: "fitColumns",
         columns:[
             {title:"Id", field:"id_libro"},
             {title:"Titulo", field:"titulo"},
             {title:"Autor", field:"autor"},
             {title:"ISBN", field:"isbn"},
             {title:"Genero", field:"genero"},
             {title:"Fecha de Alta", field:"f_alta"},
             {title:"Fecha de Baja", field:"f_baja"},
             {title:"Borrar", formatter:basura, cellClick:function(e, cell){
                var row = cell.getRow();
                var rowIndex = row.getIndex();
                var idDelete = row.getData().id_libro;
                if (confirm(row.getData().id_libro)) {
                	eliminarLibro(idDelete);
				}
             }},
             {title:"Modificar", formatter:editar, cellClick:function(e, cell){
                 var row = cell.getRow();
                 var rowIndex = row.getIndex();
                 var idDelete = row.getData().id_libro;
                 if (confirm("Quieres modificar este libro "+row.getData().titulo)) {
                	 document.getElementById('formularioModif').style.display='block';
               	  $("#idLibro").val(row.getData().id_libro);
               	  $("#tituloModif").val(row.getData().titulo);
               	  $("#autorModif").val(row.getData().autor);
               	  $("#isbnModif").val(row.getData().isbn);
               	  $("#generoModif").val(row.getData().genero);
 				}
             }
             }
         ],
    });
    
</script>

<script type="text/javascript">
     $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaLibros");
</script>

<script>

function myFunction(){
    
	document.getElementById('id01').style.display='none';
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
                url : "http://localhost:8080/BibliotecaHibernate/crearLibro",

                //Add form data
                data: JSON.stringify(sendInfo),
                contentType: "application/json; charset=utf-8",

                success : function(data) {
                    console.log(data);
                    $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaLibros");
                    
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
            url : "http://localhost:8080/BibliotecaHibernate/borrarLibro",

            //Add form data
            data: JSON.stringify(sendInfo),
            contentType: "application/json; charset=utf-8",
            

            success : function(data) {
                console.log(data);
                $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaLibros");
                
            },
            error : function(xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
                console.log(err);                   
            }
        }); //End of Ajax
	}
    
    function buscarLibro(){
    	var id = $("#id").val();
    	alert(id);
        
        var sendInfo = {
                "id_socio": id
            };
            
        $.ajax({
                    method : 'GET',
                    //url : "http://localhost:8080/BibliotecaHibernate/listaSocios/"+id,

                    //Add form data
                    data: JSON.stringify(sendInfo),
                    contentType: "application/json; charset=utf-8",

                    success : function(data) {
                        console.log(data);
                        $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaSocios/"+id);
                        
                    },
                    error : function(xhr, status, error) {
                        var err = eval("(" + xhr.responseText + ")");
                        console.log(err);                   
                    }
                }); //End of Ajax
               
                //window.location.href = 'http://localhost:8080/BibliotecaSpring/listaSocios';
                
          // End of myFucntion
        
    }
    function modificarLibro(){
    	document.getElementById('formularioModif').style.display='none';
    	var idLibro = $("#idLibro").val();
    	var isbn = $("#isbnModif").val();
        var titulo = $("#tituloModif").val();
        var autor = $("#autorModif").val();
        var genero = $("#generoModif").val();
        
        var sendInfo = {
        		"id_libro": idLibro,
                "titulo": titulo,
                "autor": autor,
                "isbn": isbn,
                "genero": genero
            };
            
        $.ajax({
                    method : 'POST',
                    url : "http://localhost:8080/BibliotecaHibernate/modificarLibro",

                    //Add form data
                    data: JSON.stringify(sendInfo),
                    contentType: "application/json; charset=utf-8",

                    success : function(data) {
                        console.log(data);
                        $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaLibros");
                        
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
