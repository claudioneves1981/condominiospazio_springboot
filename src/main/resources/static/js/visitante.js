
         function botaoDeletarVisitante(){
            	var codigo = $('#codigo').val();
            	if(codigo != null && codigo.trim()!=''){
            	deleteVisitante(codigo);
            	}
            	document.getElementById('formCadastroVisitante').reset();
            }

            function deleteVisitante(codigo){

        	if(confirm('Deseja realmente deletar?')){

            	$.ajax({
        	    		method: "DELETE",
        	    		url: "visitante/delete",
        	    		data : "codigo=" +codigo ,
        	    		success: function(response){
        	    			$('#'+codigo).remove();
        	    			alert(response);
        	    		}
        	    	}).fail(function(xhr,status,errorThrown){
        	    		alert("Erro ao deletar visitante por codigo:" + xhr.responseText);
        	    	});
        		}
            }

        function editarVisitante(codigo){

            	$.ajax({
        	    		method: "GET",
        	    		url: "visitante/buscar",
        	    		data : "codigo=" + codigo ,
        	    		success: function(response){
        	    			$("#codigo").val(response.codigo);
        	    	    	$("#nome").val(response.nome);
        	    	    	$("#documento").val(response.documento);
        	    	    	$("#tipo option:selected").text(response.tipo);
        	    	    	$("#observacao").val(response.observacao);
        	    	    	$("#modalPesquisarVisitante").modal('hide');
        	    		}
        	    	}).fail(function(xhr,status,errorThrown){
        	    		alert("Erro ao buscar visitante por codigo:" + xhr.responseText);
        	    	});

            }

            function pesquisarVisitante(){
          	  nome = $('#nomeBusca').val();

          	  if(nome != null && nome.trim()!= ''){

          		  $.ajax({
          	    		method: "GET",
          	    		url: "visitante/buscarpornome",
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
          	    				'<td><button type="button" class="btn btn-primary" onclick="editarVisitante('+response[i].codigo+')">Ver</button></td>'+
          	    				'<td><button type="button" class="btn btn-danger" onclick="deleteVisitante('+response[i].codigo+')">Delete</button></td></tr>');
          	    			}
          	    		}
          	    	}).fail(function(xhr,status,errorThrown){
          	    		alert("Erro ao buscar visitante:" + xhr.responseText);
          	    	});
          	  }

            }
          function salvarVisitante(){

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
            		url: "visitante/salvar",
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