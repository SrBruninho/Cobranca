$('#confExclusaoTitulo').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	
	var descricaoTitulo = button.data('descricao');
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
});

$(function(){
	$('.js-currency').maskMoney({decimal:',',thousands: '.',allowZero: true});
	$('.js-atualizar-status').on('click', function( event ){
		event.preventDefault()
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber, 
			type: 'PUT'
		});
		
		response.done(function( e ){
			var codTitulo = botaoReceber.data('codigo');
			$('[data-role=' + codTitulo +']').html('<span class="badge bg-success">'+ e +'</span>');
			botaoReceber.hide();
		});
		
		response.fail(function( e ){
			console.log( e );
			alert('Erro ao receber');
		});		
 	});
}); 