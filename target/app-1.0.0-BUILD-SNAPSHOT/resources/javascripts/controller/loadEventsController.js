dashboardApp.controller('getEventListCtrl', function( $scope, $http, $interval,$modal) {
	$scope.events = [];
	$scope.disableButton = false;
	$scope.selectedEventId = "";
	$scope.previousComment = "";
	$scope.reminderEventList = [];	
	
	$scope.init = function() {   
		
	    $("#user_details").hovercard({
	        detailsHTML: '<form>' +
            '<br/>' +
        '<ul><li class="glyphicon glyphicon-envelope" style="padding-left:;font-size:20px;" /><li class="glyphicon glyphicon-earphone" style="font-size:20px;padding-left:15px;" />'+
            '<li class="glyphicon glyphicon-comment" style="font-size:20px;padding-left:15px;" /></ul><br/> ' +
        '</form>',
	        width: 300,
	        top: 40,
	        left: 70,
	        cardImgSrc: 'resources/images/mickey.jpeg'
	    });	
		
		var responsePromise = $http.get("fetchEvents");
		var reminder_events = [];
		responsePromise.success(function(data, status, headers, config) {				
			angular.forEach(data, function(value, key) {
				if (value.eventType == "reminderEvent") {					
					reminder_events.push(value);
					$scope.reminderEventList.push(value);
				}
				else
				{   
				   if ($scope.events.length >= 1) {
					 var found = false;
				     for (var i = 0 ; i < $scope.events.length; i++) {
					   if ($scope.events[i].eventId == value.eventId) {
						   $scope.events[i] = value; 
						   found = true;
						   break;
					   }					   				   
				    }
				    if(!found) {
				       $scope.events.push(value);
				    }
				 }
				else {
				   $scope.events.push(value);
				}
			  }
			});							
			
			if (reminder_events.length >= 1) {					
				var modalInstance = $modal.open({
					templateUrl : 'resources/pages/dialog.html',
					controller : 'ModalDialogCtrl',
					resolve : {
						reminder_event : function() {
							return reminder_events;
						}
					}
				});
			}			
		});
		responsePromise.error(function(data, status, headers, config) {
			//error handling
		});
	};
	
	$scope.commentRequired = function(selectedStatus) {		
		if(selectedStatus == 'CLOSED')
		  $scope.disableButton = true;
		else
		  $scope.disableButton = false;
	};
		 
	$scope.enableDisableButton = function(){	   
	   if(angular.element(comment).val().length ==0 && $("#statusOptions").val()=="CLOSED"){
		   $scope.disableButton = true;
	   }
	   else {
		   $scope.disableButton = false;		   
	   }
	};
	$interval( function(){ $scope.init(); }, 30000);
	$scope.displaySelectedEventDetails = function(event) {		
		$('#event_list > li.active').removeClass('active');
		$("#list_event_"+event.eventId).addClass('active');
		$scope.selectedEventData = formatEventData(event);	
		$scope.selectedEventId = event.eventId;
		$scope.previousComment = event.comments;
	};
	
	$scope.clearSearch = function() {
		$scope.searchText = "";
	};
	
	$scope.updateSelectedEventData = function() {
	   for (var i = 0 ; i < $scope.events.length; i++) {
	     if ($scope.events[i].eventId == $scope.selectedEventId) {
		   $scope.events[i].comments = angular.element(comment).val();
		   $scope.events[i].status = angular.element(statusOptions).val(); 		
		   $scope.events[i].severity = angular.element(severityOptions).val();
		   $scope.previousComment = angular.element(comment).val();
		   angular.element(comment).val("");
		   break;
		  }					   				   
		}	
		 
	};
	
	$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
	  $('#event_list > li').first().addClass('active');
	  if($scope.events.length >= 1) {
	    eventId = $('#event_list > li').first().attr('id').split("_")[2];
		for (var i = 0 ; i < $scope.events.length; i++) {
		  if ($scope.events[i].eventId == eventId) {
		    $scope.displaySelectedEventDetails($scope.events[i]);
			break;
		  }
		}			  			 
	  }	
	});
	
	$scope.toggle = function(event) {
		$("#flagged_"+event.eventId).toggleClass("flagged");
		
		for (var i = 0 ; i < $scope.events.length; i++) {
			   if ($scope.events[i].eventId == event.eventId) {
				   if(event["is_flagged"] && event["is_flagged"] == "flagged"){
					   event["is_flagged"] = "unflagged";
					}
				   else {
					 event["is_flagged"] = "flagged";   
				   }				   
				   $scope.events[i] = event; 				   
				   break;
			   }					   				   
		    }		   
	 };
	 
	 $scope.hideShow = function(event) {		
		if ( $("#hideShow_"+event.eventId).hasClass("glyphicon-eye-close") ) {
			$("#hideShow_"+event.eventId).removeClass("glyphicon-eye-close");
			$("#hideShow_"+event.eventId).addClass("glyphicon-eye-open");	
			$("#hideShow_"+event.eventId).tooltip({content: "Hide Event"});
			} 
	   else {
			$("#hideShow_"+event.eventId).removeClass("glyphicon-eye-open");
		    $("#hideShow_"+event.eventId).addClass("glyphicon-eye-close");	
		    $("#hideShow_"+event.eventId).tooltip({content: "Show Event"});
			}
		for (var i = 0 ; i < $scope.events.length; i++) {
			   if ($scope.events[i].eventId == event.eventId) {
				   if(event["is_hidden"] && event["is_hidden"] == "hide"){
					   event["is_hidden"] = "show";
					}
				   else {
					 event["is_hidden"] = "hide";   
				   }				   
				   $scope.events[i] = event; 				   
				   break;
			   }					   				   
		    }	
	 };
    	 
	
	function formatEventData(selectedEvent) {
	  var selectedEventDetailedData = [];
	  if (selectedEvent.eventType == "reminderEvent") {		  		 
		  selectedEventDetailedData = [{label: 'Task Name', value: selectedEvent.taskName},
		                               {label: 'Due Date', value: selectedEvent.dueDate},
		                               {label: 'Priority', value: selectedEvent.priority}];
	  }
	  else if (selectedEvent.eventType == "tradeEvent") {
		  if (selectedEvent.tradeEventType == "TradeCancelledEvent") {
			  selectedEventDetailedData = [{label: 'Trade ID', value: selectedEvent.tradeIdentifier},
			                               {label: 'Cancellation Date', value: selectedEvent.eventTime},
			                               {label: 'Reason', value: selectedEvent.cancellationComments}];					  
		  }
		  else if (selectedEvent.tradeEventType == "TradeAmendedEvent") {
			  selectedEventDetailedData = [{label: 'Trade ID', value: selectedEvent.tradeIdentifier},
			                               {label: 'Amendment Date', value: selectedEvent.eventTime},
			                               {label: 'Previous Value', value: selectedEvent.oldValue},
			                               {label: 'New Value', value: selectedEvent.newValue}];				   
		  }
		  else if (selectedEvent.tradeEventType == "TradeCreatedEvent") {
			  selectedEventDetailedData = [{label: 'Trade ID', value: selectedEvent.tradeIdentifier},
			                               {label: 'Security Identifier', value: selectedEvent.securityIdentifier},
			                               {label: 'Created Date', value: selectedEvent.eventTime},
			                               {label: 'Quantity', value: selectedEvent.quantity},
			                               {label: 'Price', value: selectedEvent.price},
			                               {label: 'Name of the Trader', value: selectedEvent.trader, traderId: "T1"}];				  
		  }			  
	  }
	  else if (selectedEvent.eventType == "exceptionEvent") {
		  selectedEventDetailedData = [{label: 'Exception Code', value: selectedEvent.code},
		                               {label: 'Exception Description', value: selectedEvent.description},
		                               {label: 'Severity', value: selectedEvent.severity},
		                               {label: 'Status', value: selectedEvent.status}];		  
	  }
	  return selectedEventDetailedData;
	}	
	
});

dashboardApp.directive('onFinishRender', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function () {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
});
