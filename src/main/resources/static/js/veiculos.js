
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

          		$.ajax({
          	  				method: "GET",
          	  				url: url+"/"+brand+"/models",
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

         function pesquisarMorador(){

                	$.ajax({
            	    		method: "GET",
            	    		url: "morador/buscarcpf",
            	    		data : "cpf=" + $('#cpf').val(),
            	    		success: function(response){
            	    		$("#codigo").val(response.documento);
                			$("#nome").val(response.nome);
            	    		}
            	    	}).fail(function(xhr,status,errorThrown){
            	    		alert("Erro ao buscar morador por codigo:" + xhr.responseText);
            	    	});
        
         }

         function botaoDeletarVeiculo(){
            	var id = $('#id').val();
            	var cpf = $('#cpf').val();
            	if((id != null && id.trim()!='') && (cpf != null && cpf.trim()!='')){
            	    deleteVeiculo(id, cpf);
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
        	    		    $("#id").val(response.id);
        	    	    	$("#brand option:selected").text(response.modelo.marca.name);
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

          	  placa= $('#nomeBusca').val();
          	  codigo = $('#codigo').val();

          	  if(placa != null && placa.trim()!= ''){

          		  $.ajax({
          	    		method: "GET",
          	    		url: "veiculo/buscarporplaca",
          	    		data : "placa=" + placa ,
          	    		success: function(response){
          	    			$('#tabelaresultados > tbody > tr').remove();
          	    			//for (var i = 0; i < response.length; i++){
          	    				$('#tabelaresultados > tbody').append('<tr id="'+response.id+'">'+
          	    				'<td>'+response.morador+'</td>'+
          	    				'<td>'+response.modelo.name+'</td>'+
          	    				'<td>'+response.modelo.marca.name+'</td>'+
          	    				'<td>'+response.cor+'</td>'+
          	    				'<td>'+response.placa+'</td>'+
          	    				'<td><button type="button" class="btn btn-primary" onclick="editarVeiculo('+response.id+')">Ver</button></td>'+
          	    				'<td><button type="button" class="btn btn-danger" onclick="deleteVeiculo('+response.id+','+codigo+')">Delete</button></td></tr>');
          	    			//}
          	    		}
          	      }).fail(function(xhr,status,errorThrown){
          	    		alert("Erro ao buscar veiculo:" + xhr.responseText);
          	      });
          	  }

        }

        function salvarVeiculo(){


                var id = $("#id").val();
            	var cpf = $("#cpf").val();
            	var marca = $("#brand option:selected").text();
            	var modelo = $("#model option:selected").text();
            	var cor = $("#cor").val();
            	var placa = $("#placa").val();

            	url = "veiculo/salvar/"+cpf;

            	$.ajax({
            		method: "POST",
            		url: url,
            		data : JSON.stringify({
            		id : id,
            		modelo : {
            		    name : modelo,
            		    marca : {
            		        name : marca
            		    }
            		},
            		cor : cor,
            		placa : placa
            		}),
            		contentType: "application/json; charset=utf-8",
            		success: function(response){
            			$("#id").val(response.id);
            			alert("Salvo com Sucesso!")
            		}
            	}).fail(function(xhr,status,errorThrown){
            		alert("Erro ao Salvar:" + xhr.responseText);
            	});

        }