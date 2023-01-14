$('#deleteConfirmationModal').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var billId = button.data('id');
	var billDescription = button.data('description');
	
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('base-url');
	if(!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + billId);
	
	modal.find('.modal-body span').html('Are you sure you wish to delete the following bill <strong>' + billDescription + '</strong>?')
	
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
});