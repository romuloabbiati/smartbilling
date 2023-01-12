$('#deleteConfirmationModal').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var billId = button.data('id');
	
	alert(billId);
	
});