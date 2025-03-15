
         $.ajax({
          	  		method: "GET",
          	  		url: "https://fipe.parallelum.com.br/api/v2/cars/brands",
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
                          					getModel("https://fipe.parallelum.com.br/api/v2/cars/brands", brand);
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

         function editarMorador(codigo){
         
                 
        
                	$.ajax({
            	    		method: "GET",
            	    		url: "morador/buscar",
            	    		data : "codigo=" + codigo ,
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
            	if(codigo != null && codigo.trim()!=''){
            	deleteVeiculo(codigo);
            	}
            	document.getElementById('formCadastroVeiculo').reset();
            }

            function deleteVeiculo(codigo){

        	if(confirm('Deseja realmente deletar?')){

            	$.ajax({
        	    		method: "DELETE",
        	    		url: "veiculo/delete",
        	    		data : "codigo=" +codigo ,
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
        	    			$("#codigo").val(response.codigo);
        	    	    	$("#nome").val(response.nome);
        	    	    	$("#documento").val(response.documento);
        	    	    	$("#tipo option:selected").text(response.tipo);
        	    	    	$("#observacao").val(response.observacao);
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
          	    		url: "veiculo/buscarpornome",
          	    		data : "nome=" + nome ,
          	    		success: function(response){
          	    			$('#tabelaresultados > tbody > tr').remove();
          	    			for (var i = 0; i < response.length; i++){
          	    				$('#tabelaresultados > tbody').append('<tr id="'+response[i].codigo+'">'+
          	    				'<td>'+response[i].codigo+'</td>'+
          	    				'<td>'+response[i].nome+'</td>'+
          	    				'<td>'+response[i].documento+'</td>'+
          	    				'<td>'+response[i].tipo+'</td>'+
          	    				'<td>'+response[i].observacao+'</td>'+
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
            	var nome = $("#nome").val();
            	var documento = $("#documento").val();
            	var tipo = $("#tipo option:selected").text();
            	var observacao = $("#observacao").val();


            	if(nome == null || nome != null && nome.trim()==''){
            		$("#nome").focus();
            		alert('informe o nome');
            		return;
            	}

            	if(documento == null || documento != null && documento.trim()==''){
            		$("#documento").focus();
            		alert('informe o documento');
            		return;
            	}

            	$.ajax({
            		method: "POST",
            		url: "veiculo/salvar",
            		data : JSON.stringify({codigo : codigo, nome : nome, documento : documento, tipo : tipo, observacao : observacao}),
            		contentType: "application/json; charset=utf-8",
            		success: function(response){
            			$("#codigo").val(response.codigo);
            			alert("Salvo com Sucesso!")
            		}
            	}).fail(function(xhr,status,errorThrown){
            		alert("Erro ao Salvar:" + xhr.responseText);
            	});
            }