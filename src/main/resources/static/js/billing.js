$('#deleteConfirmationModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var billId = button.data('id');
	var billDescription = button.data('description');
	
	var modal = $(this);
	var form = modal.find('form');
	
	var action = form.data('base-url');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + billId);
	
	modal.find('.modal-body span').html('Are you sure you wish to delete the following bill <strong>' + billDescription + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: '.', thousands: ',', allowZero: true});
	
	$('.js-update-status').on('click', function(event) {
		event.preventDefault();
		
		var receiveButton = $(event.currentTarget);
		var receiveUrl = receiveButton.attr('href');
		
		var response = $.ajax({
			url: receiveUrl,
			type: 'PUT'
		});
		
		
		response.done(function(e) {
			var billId = receiveButton.data('id');
			$('[data-role=' + billId + ']').html('<span class="label label-success">' + e + '</span>');
			receiveButton.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Could not receive your payment!');
		});
		
	});
});