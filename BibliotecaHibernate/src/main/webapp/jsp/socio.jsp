<html>

<link href="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/css/tabulator.min.css" rel="stylesheet">
<script type="text/javascript"
    src="http://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
                  
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.3/js/tabulator.min.js"></script>



<body>
<h2>Testing tabulator...</h2>

<p><input type="text" id="id"></p>
<button onclick="buscarSocio()">Enviar</button>
<div id="example-table"></div>

<script type="text/javascript">

var basura = function(e) {
	return '<img width="50px" src=../../resources/images/trash.jpg>';
	}
     
     $("#example-table").tabulator({
    	 layout: "fitColumns",
         columns:[
             {title:"Id", field:"id_socio"},
             {title:"Nombre", field:"nombre"},
             {title:"Apellidos", field:"apellidos"},
             {title:"DNI", field:"dni"},
             {title:"Fecha Nacimiento", field:"f_nacimiento"},
             {title:"Fecha de Alta", field:"f_alta"},
             {title:"Fecha de Baja", field:"f_baja"},
             {title:"Borrar", formatter:basura, cellClick:function(e, cell){
                var row = cell.getRow();
                var rowIndex = row.getIndex();
                var idDelete = row.getData().id_socio;
                if (confirm(row.getData().id_socio)) {
                	eliminarSocio(idDelete);
				}
             }},
         ],
    });
    
</script>

<script type="text/javascript">
     $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaSocios");
</script>

<p>Nombre: <input type="text" id="nombre"></input></p>
<p>Apellidos: <input type="text" id="apellidos"></input></p>
<p>Dni: <input type="text" id="dni"></input></p>
<p>Fecha nacimiento: <input type="date" id=f_nacimiento></p>
<button onclick="myFunction()">Enviar</button>

<script>
function myFunction(){
    
    var dni = $("#dni").val();
    var nombre = $("#nombre").val();
    var apellidos = $("#apellidos").val();
    var f_nacimiento = $("#f_nacimiento").val();
    
    var sendInfo = {
            "nombre": nombre,
            "apellidos": apellidos,
            "dni": dni,
            "f_nacimiento": f_nacimiento,
        };
        
    $.ajax({
                method : 'POST',
                url : "http://localhost:8080/BibliotecaHibernate/crearSocio",

                //Add form data
                data: JSON.stringify(sendInfo),
                contentType: "application/json; charset=utf-8",

                success : function(data) {
                    console.log(data);
                    $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaSocios");
                    
                },
                error : function(xhr, status, error) {
                    var err = eval("(" + xhr.responseText + ")");
                    console.log(err);                   
                }
            }); //End of Ajax
           
            //window.location.href = 'http://localhost:8080/BibliotecaSpring/listaSocios';
            
    }   // End of myFucntion
    
    function eliminarSocio(idSocio) {
    	
    	 var sendInfo = {
    	            "id_socio": idSocio
    	        };
    	 
    	$.ajax({
            method : 'POST',
            url : "http://localhost:8080/BibliotecaHibernate/borrarSocio",

            //Add form data
            data: JSON.stringify(sendInfo),
            contentType: "application/json; charset=utf-8",
            

            success : function(data) {
                console.log(data);
                $("#example-table").tabulator("setData","http://localhost:8080/BibliotecaHibernate/listaSocios");
                
            },
            error : function(xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
                console.log(err);                   
            }
        }); //End of Ajax
	}
    
    function buscarSocio(){
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
</script>

</body>
</html>
