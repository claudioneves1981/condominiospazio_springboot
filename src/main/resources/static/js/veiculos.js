
         $.ajax({
          	  		method: "GET",
          	  		url: "brands",
          	  		success: function(response){
          	  			$('#brand > option').remove();
          	  			for (var i = 0; i < response.length; i++){
          	 				$('#brand').append('<option value="'+response[i].code+'">'+response[i].name+'</option>');
          	 			}
          	 			$("#brand").click(function () {
                           	        $("#brand").each(function () {
                           	          $(this).find("option").each(function () {
                           	            if ($(this).attr("selected")) {
                          				    var brand = $(this).val();
                          					getModel("brands", brand);
                           	             $(this).removeAttr("selected");
                           	            }
                           	          });
                           	        });
                           	   $("#brand").find("option:selected").attr("selected", true);
                           	})
          	  		}
          	  	});



          function getModel(url, brand){

          	 var url = url+"/"+brand+"/models";

          		$.ajax({
          	  				method: "GET",
          	  				url: url,
          	  				success: function(response){
          	  				$('#model > option').remove();
          	  				for (var i = 0; i < response.length; i++){
          	 				$('#model').append('<option value="'+response[i].code+'">'+response[i].name+'</option>');
          	 				}
          	  			}

          	  			});

          				$("#model").click(function () {
           	        		$("#model").each(function () {
           	         			 $(this).find("option").each(function () {
           	            			if ($(this).attr("selected")) {

           	            	 $(this).removeAttr("selected");
           	            }
           	          });
           	        });
           	       $("#model").find("option:selected").attr("selected", true);
           		})
          }

         function pesqusarMorador(){

              var cpf = $('#cpf').val();

                	$.ajax({
            	    		method: "GET",
            	    		url: "morador/buscarcpf",
            	    		data : "cpf=" + cpf ,
            	    		success: function(response){
            	    		$("#codigo").val(response.codigo);
                			$("#nome").val(response.nome);
            	    		}
            	    	}).fail(function(xhr,status,errorThrown){
            	    		alert("Erro ao buscar morador por codigo:" + xhr.responseText);
            	    	});
        
                }
                
                
        
        
         function botaoDeletarVeiculo(){
            	var codigo = $('#codigo').val();
            	var cpf = $('#cpf').val();
            	if((codigo != null && codigo.trim()!='') && (cpf != null && cpf.trim()!='')){
            	deleteVeiculo(codigo, cpf);
            	}
            	document.getElementById('formCadastroVeiculo').reset();
            }

            function deleteVeiculo(codigo, cpf){

            url = "veiculo/delete/"+cpf+"/"+codigo;

        	if(confirm('Deseja realmente deletar?')){

            	$.ajax({
        	    		method: "DELETE",
        	    		url: url,

        	    		success: function(response){
        	    			$('#'+codigo).remove();
        	    			alert(response);
        	    		}
        	    	}).fail(function(xhr,status,errorThrown){
        	    		alert("Erro ao deletar veiculo por codigo:" + xhr.responseText);
        	    	});
        		}
            }

        function editarVeiculo(codigo){

            	$.ajax({
        	    		method: "GET",
        	    		url: "veiculo/buscar",
        	    		data : "codigo=" + codigo ,
        	    		success: function(response){
        	    			$("#codigo").val(response.morador.codigo);
        	    	    	$("#nome").val(response.morador.nome);
        	    	    	$("#brand option:selected").text(response.marca.name);
        	    	    	$("#model option:selected").text(response.modelo.name);
        	    	    	$("#cor").val(response.cor);
        	    	    	$("#placa").val(response.placa);
        	    	    	$("#modalPesquisarVeiculo").modal('hide');
        	    		}
        	    	}).fail(function(xhr,status,errorThrown){
        	    		alert("Erro ao buscar veiculo por codigo:" + xhr.responseText);
        	    	});

            }

            function pesquisarVeiculo(){
          	  nome = $('#nomeBusca').val();

          	  if(nome != null && nome.trim()!= ''){

          		  $.ajax({
          	    		method: "GET",
          	    		url: "veiculo/buscarporplaca",
          	    		data : "placa=" + placa ,
          	    		success: function(response){
          	    			$('#tabelaresultados > tbody > tr').remove();
          	    			for (var i = 0; i < response.length; i++){
          	    				$('#tabelaresultados > tbody').append('<tr id="'+response[i].codigo+'">'+
          	    				'<td>'+response[i].morador.codigo+'</td>'+
          	    				'<td>'+response[i].morador.nome+'</td>'+
          	    				'<td>'+response[i].marca.name+'</td>'+
          	    				'<td>'+response[i].modelo.name+'</td>'+
          	    				'<td>'+response[i].cor+'</td>'+
          	    				'<td>'+response[i].placa+'</td>'+
          	    				'<td><button type="button" class="btn btn-primary" onclick="editarVeiculo('+response[i].codigo+')">Ver</button></td>'+
          	    				'<td><button type="button" class="btn btn-danger" onclick="deleteVeiculo('+response[i].codigo+')">Delete</button></td></tr>');
          	    			}
          	    		}
          	    	}).fail(function(xhr,status,errorThrown){
          	    		alert("Erro ao buscar veiculo:" + xhr.responseText);
          	    	});
          	  }

            }
          function salvarVeiculo(){

            	var codigo = $("#codigo").val();
            	var cpf = $("cpf").val();
            	var marca = $("#brand option:selected").text();
            	var modelo = $("#modelo option:selected").text();
            	var cor = $("#cor").val();
            	var placa = $("#placa").val();

            	url = "veiculo/salvar/"+cpf;

            	$.ajax({
            		method: "POST",
            		url: url,
            		data : JSON.stringify({
            		veiculo : {
            		    modelo : {
            		    name : modelo,
            		    marca : {
            		        name : marca
            		        }
            		    cor : cor,
            		    placa : placa
            		    },
            		}),
            		contentType: "application/json; charset=utf-8",
            		success: function(response){
            			$("#codigo").val(response.codigo);
            			alert("Salvo com Sucesso!")
            		}
            	}).fail(function(xhr,status,errorThrown){
            		alert("Erro ao Salvar:" + xhr.responseText);
            	});
            }