dashboardApp.controller('ModalDialogCtrl',function($scope, $modalInstance, $timeout, $modal, reminder_event) {
	if(!isArray(reminder_event)) {
	  reminder_event = [reminder_event];
	}
	$scope.reminder_events = reminder_event;	
	$scope.disableReminderButton = true;
	$scope.selected_reminder_event = [];	
	$scope.ok = function () {	
		var index = $scope.reminder_events.indexOf($scope.selected_reminder_event);
		$scope.reminder_events.splice(index, 1);
		if($scope.reminder_events == 0){
			$modalInstance.close();				
		}
		else
		{
			$scope.selected_reminder_event = [];
			$scope.disableReminderButton = true;
		}
	};
	$scope.enableButtons = function(event){
		$('#reminder_event_list > li.active').removeClass('active');
		$("#selected_reminder_evt_"+event.eventId).addClass('active');
		$scope.disableReminderButton = false;
		$scope.selected_reminder_event = event;
	};
	
	$scope.reminderDialog = function(event) {		
		var modalInstance = $modal.open({
			templateUrl : 'resources/pages/dialog.html',
			controller : 'ModalDialogCtrl',
			resolve : {
				reminder_event : function() {
					return event;
				}
			}
		});
	};	
	
	$scope.snooze = function(time) {
		var time_ms = parseInt(time) * 60000;
		var index = $scope.reminder_events.indexOf($scope.selected_reminder_event);
		$scope.reminder_events.splice(index, 1);
		if($scope.reminder_events == 0){
			$modalInstance.close();			
		}
		else
		{
			$scope.selected_reminder_event = [];
			$scope.disableReminderButton = true;
		}
		$timeout( function(){ $scope.reminderDialog($scope.selected_reminder_event); }, 5000);	
	};
	
	function isObject (obj) {
	  return obj && (typeof obj === "object");
	}
	
	function isArray (obj) {
	  return isObject(obj) && (obj instanceof Array);
	}
	
});