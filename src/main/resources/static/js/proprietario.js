function botaoDeletarProprietario(){
    	var codigo = $('#codigo').val();
    	if(codigo != null && codigo.trim()!=''){
    	deleteProprietario(codigo);
    	}
    	document.getElementById('formCadastroProprietario').reset();
    }

    function deleteProprietario(codigo){

	if(confirm('Deseja realmente deletar?')){

    	$.ajax({
	    		method: "DELETE",
	    		url: "proprietario/delete",
	    		data : "codigo=" + codigo ,
	    		success: function(response){
	    			$('#'+codigo).remove();
	    			alert(response);
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao deletar proprietario por codigo:" + xhr.responseText);
	    	});
		}
    }

function editarProprietario(codigo){

    	$.ajax({
	    		method: "GET",
	    		url: "proprietario/buscar",
	    		data : "codigo=" + codigo ,
	    		success: function(response){
	    		$("#codigo").val(response.codigo);
    			$("#nome").val(response.nome);
    			$("#apto").val(response.apto.apto);
				$("#bloco").val(response.apto.bloco);
				$("#documento").val(response.documento);
				$("#telefone").val(response.contato.telefone);
				$("#email").val(response.contato.email);
				$("#endereco").val(response.endereco.endereco);
				$("#numero").val(response.endereco.numero);
				$("#complemento").val(response.endereco.complemento);
				$("#bairro").val(response.endereco.bairro);
				$("#cep").val(response.endereco.cep);
				$("#cidade").val(response.endereco.cidade);
				$("#estado").val(response.endereco.estado);
	    	    $("#modalPesquisarProprietario").modal('hide');
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao buscar proprietario por codigo:" + xhr.responseText);
	    	});

    }

    function pesquisarProprietario(){
  	  nome = $('#nomeBusca').val();

  	  if(nome != null && nome.trim()!= ''){

  		  $.ajax({
  	    		method: "GET",
  	    		url: "proprietario/buscarpornome",
  	    		data : "nome=" + nome ,
  	    		success: function(response){
  	    			$('#tabelaresultados > tbody > tr').remove();
  	    			for (var i = 0; i < response.length; i++){
  	    				$('#tabelaresultados > tbody').append('<tr id="'+response[i].codigo+'">'+
  	    				'<td>'+response[i].codigo+'</td>'+
  	    				'<td>'+response[i].dataCadastro+'</td>'+
  	    				'<td>'+response[i].nome+'</td>'+
  	    				'<td>'+response[i].contato.telefone+'</td>'+
  	    				'<td>'+response[i].documento+'</td>'+
  	    				'<td>'+response[i].contato.email+'</td>'+
  	    				'<td>'+response[i].apto.bloco+'</td>'+
  	    				'<td>'+response[i].apto.apto+'</td>'+
  	    				'<td>'+response[i].tipo+'</td>'+
  	    				'<td><button type="button" class="btn btn-primary" onclick="editarProprietario('+response[i].codigo+')">Ver</button></td>'+
  	    				'<td><button type="button" class="btn btn-danger" onclick="deleteProprietario('+response[i].codigo+')">Delete</button></td></tr>');
  	    			}
  	    		}
  	    	}).fail(function(xhr,status,errorThrown){
  	    		alert("Erro ao buscar proprietario:" + xhr.responseText);
  	    	});
  	  }

    }

    function pesquisarTodosProprietarios(){

      		  $.ajax({
      	    		method: "GET",
      	    		url: "proprietario/listatodos",
      	    		success: function(response){
      	    			$('#tabelaresultados > tbody > tr').remove();
      	    			for (var i = 0; i < response.length; i++){
      	    				$('#tabelaresultados > tbody').append('<tr id="'+response[i].codigo+'">'+
      	    				'<td>'+response[i].codigo+'</td>'+
      	    				'<td>'+response[i].dataCadastro+'</td>'+
      	    				'<td>'+response[i].nome+'</td>'+
      	    				'<td>'+response[i].contato.telefone+'</td>'+
      	    				'<td>'+response[i].documento+'</td>'+
      	    				'<td>'+response[i].contato.email+'</td>'+
      	    				'<td>'+response[i].apto.bloco+'</td>'+
      	    				'<td>'+response[i].apto.apto+'</td>'+
      	    				'<td>'+response[i].tipo+'</td>'+
      	    				'<td><button type="button" class="btn btn-primary" onclick="editarProprietario('+response[i].codigo+')">Ver</button></td>'+
      	    				'<td><button type="button" class="btn btn-danger" onclick="deleteProprietario('+response[i].codigo+')">Delete</button></td></tr>');
      	    			}
      	    		}
      	    	}).fail(function(xhr,status,errorThrown){
      	    		alert("Erro ao buscar proprietario:" + xhr.responseText);
      	    	});
        }
  function salvarProprietario(){

    	var codigo = $("#codigo").val();
    	var nome = $("#nome").val();
	   	var apto = $("#apto").val();
		var bloco = $("#bloco").val();
		var documento = $("#documento").val();
		var telefone = $("#telefone").val();
		var email = $("#email").val();
		var endereco = $("#endereco").val();
		var numero = $("#numero").val();
		var complemento = $("#complemento").val();
		var bairro = $("#bairro").val();
		var cep = $("cep").val();
		var cidade = $("#cidade").val();
		var estado = $("#estado").val();
		var tipo = "";

		if($('input[nome="tipo"]').is(':checked')){

		tipo = "PROPRIETARIO"

		}else{

		tipo = "PROPRIETARIO_MORADOR"

		}

    	if(nome == null || nome != null && nome.trim()==''){
    		$("#nome").focus();
    		alert('informe o nome');
    		return;
    	}


		if(bloco == null || bloco != null && bloco.trim()==''){
    		$("#bloco").focus();
    		alert('informe o bloco');
    		return;
    	}

    	if(apto == null || apto != null && apto.trim()==''){
    		$("#apto").focus();
    		alert('informe o apto');
    		return;
    	}

    	if(documento == null || documento != null && documento.trim()==''){
    		$("#documento").focus();
    		alert('informe o documento');
    		return;
    	}

    	if(telefone == null || telefone != null && telefone.trim()==''){
    		$("#telefone").focus();
    		alert('informe o telefone');
    		return;
    	}

    	$.ajax({
    		method: "POST",
    		url: "proprietario/salvar",
    		data : JSON.stringify(
    		{
    		    codigo : codigo,
    		    nome : nome,
    		    documento : documento,
    		    contato : {
    		        telefone : telefone,
    		        email : email
    		    },
    		    numero : numero,
    		    apto : {
    		        bloco : bloco,
    		        apto : apto
    		    },
    		    endereco : {
    		        endereco : endereco,
    		        complemento : complemento,
    		        cep : cep,
    		        bairro : bairro,
    		        cidade : cidade ,
    		        estado : estado
    		    },
    		    tipo : tipo
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