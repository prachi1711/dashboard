<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
     <link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet">
     <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
     <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
     <script src="<c:url value="/resources/javascripts/assets/jquery-1.11.2.min.js" />"></script>     
     <script src="<c:url value="/resources/javascripts/assets/angular.min.js" />"></script>
     <script src="<c:url value="/resources/javascripts/assets/bootstrap.min.js" />"></script>
     <script src="<c:url value="/resources/javascripts/assets/hovercard.js" />"></script>
     <script type="text/javascript" src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.6.0.js"></script>              
     <script src="<c:url value="/resources/javascripts/module/dashboard.js" />"></script>
     <script src="<c:url value="/resources/javascripts/controller/loadEventsController.js" />"></script>     
     <script src="<c:url value="/resources/javascripts/controller/modalDialogController.js" />"></script>     
     
     <title>Dashboard</title>
  </head>
  <body>
    <div class="container-fluid" id="dashboard_container" ng-app="myDashboard" ng-controller="getEventListCtrl">
    
        <div class="row" id="dashboard_header">
           <div class="col-md-12">
             <div class="col-md-8">
               <a class="user-profile-image" href="">
    			        <img src="resources/images/mickey.jpeg" class="user-icon">
                </a> 
                <span class="user-name" id="user_details" style="padding-left: 10px; font-size: 18px"> Mickey Mouse </span> 
             </div>             
             <div class="col-md-3">
	           <div class="btn-group" style="margin-top:15px;">
	             <span id="searchicon" class="glyphicon glyphicon-search"></span>
	  			 <input id="searchinput" type="search" class="form-control" placeholder="Search" ng-model="searchHeaderText">
	  			 <span id="searchclear" class="glyphicon glyphicon-remove-circle"></span>
			   </div> 
			 </div>
			 <div class="col-md-1">
               <ul class="header_icons">
                 <li class="glyphicon glyphicon-star-empty user-header-icon" > </li>
                 <li class="glyphicon glyphicon-envelope user-header-icon" > </li>
                 <li class="glyphicon glyphicon-th-list user-header-icon" > </li>               
                 <li class="glyphicon glyphicon-cog user-header-icon " > </li>
               </ul>
             </div>
           </div>
        </div>        

         <div class="row" style="margin:5px auto;height:100%">
           
           <div class="col-md-3" style="border:1px solid #ddd; background-color: white;height:88%;">
             <div class="row" style="background-color: #337ab7; height:8%;"> 
               <span id="events_list_header">Events </span>
               <div class="btn-group" style="float: right;margin-top: 7px;padding-right: 18px;">  				  
                 <select id="filterOptions" ng-model="selectVal" style="height: 27px;box-sizing: border-box;" >
                   <option value="" selected>Showing: All</option>
                   <option value="tradeEvent">Trade Events</option>
                   <option value="exceptionEvent">Exception Events</option>
  				   <option value="flagged">Flagged Events</option>
  				   <option value="hide">Hidden Events</option>
    			 </select>
               </div> 
             </div>                      		
        	 <div class="btn-group" style="width:99%;margin-top:5px;">
               <span id="searchicon" class="glyphicon glyphicon-search"></span>
  			   <input id="searchInput" type="search" class="form-control" placeholder="Search" ng-model="searchText">
  			   <span id="searchclear" class="glyphicon glyphicon-remove-circle" ng-click="clearSearch()"></span>
			 </div> 		 
    		 <div id="searchlist" class="list-group" data-ng-init="init()" style="width:99%;">
    		   <ul id = "event_list" class="list-group" style="overflow-y:auto;max-height:83%">
	    	     <li class="list-group-item" id="list_event_{{x.eventId}}" ng-repeat="x in events | filter:selectVal | filter:searchText | orderBy:'timeStamp':true" ng-click="displaySelectedEventDetails(x)" on-finish-render="ngRepeatFinished">
		    	   <dl>
		  		     <dt><span class="glyphicon glyphicon-flag" style="float:left;" ng-click="toggle(x)" id="flagged_{{x.eventId}}" data-toggle="tooltip" data-placement="right" title="Flag Event"></span></dt>
		  			 <dd><h4 class="list-group-item-heading" style="padding-left:30px;">{{ x.eventId }}</h4></a></dd>
				   </dl>
				   <dl>
		  		     <dt><span class="glyphicon glyphicon-eye-open" style="float:left;" ng-click="hideShow(x)" id="hideShow_{{x.eventId}}" data-toggle="tooltip" data-placement="right" title="Hide Event"></span></dt>
		  			 <dd><p class="list-group-item-text" style="padding-left:30px;">Event Type: {{ x.eventType }}</p></dd>
				   </dl> 
				   <span style="float: right; margin-top: -80px;"> {{x.timeStamp | date:'h:mm a'}}</span>      		     			    			   			    			                               
	  			 </li>  			  
  			   </ul>  			         	        
             </div>              
           </div>

           <div class="col-md-6">
             <div class="container-fluid" id="selected_event_content" style="height:88%">
             <div class="row" style="height: 8%;background-color: ghostwhite;">
             <span><h3 style="padding-left:15px;line-height:4px;">{{selectedEventId}}</h3></span>
	           <ul class="content-icons">
			         <li class="glyphicon glyphicon-envelope user-header-icon" data-toggle="tooltip" data-placement="bottom" title="Mail Event"> </li>
			         <li class="glyphicon glyphicon-lock user-header-icon" data-toggle="tooltip" data-placement="bottom" title="Close Event"  > </li>
			         <li class="glyphicon glyphicon-user user-header-icon" data-toggle="tooltip" data-placement="bottom" title="Assign Event" > </li>
			         <li class="glyphicon glyphicon-share-alt user-header-icon " data-toggle="tooltip" data-placement="bottom" title="Forward Event" > </li>               
			         <li class="glyphicon glyphicon-flag user-header-icon " data-toggle="tooltip" data-placement="bottom" title="Flag Event"  > </li>
			         <li class="glyphicon glyphicon-share user-header-icon " data-toggle="tooltip" data-placement="bottom" title="Share Event" > </li>		               
			         <li class="glyphicon glyphicon-link user-header-icon " data-toggle="tooltip" data-placement="bottom" title="Link Event" > </li>
			         <li class="glyphicon glyphicon-paperclip user-header-icon " data-toggle="tooltip" data-placement="bottom" title="Attachment" > </li>		               		               
	           </ul>
             </div>
             
             <div class="row">              
               <ul class="list-group">
    		 	 <li class="list-group-item" ng-repeat="x in selectedEventData">       		 	    		 	    		
	    	       <h4 class="list-group-item-heading">{{x.label}}: </h4>
	    	       <span ng-switch on="x.label">
				     <span ng-switch-when="Severity" class="ng-scope" style="float: right;margin-top: -25px;margin-right: 320px;">
				       <select id="severityOptions" data-ng-model="x.value" class="ng-pristine ng-valid ng-touched" style="width: 100px;height: 30px;">                  
                  		 <option value="HIGH">High</option>
                  		 <option value="CRITICAL">Critical</option>
                  		 <option value="MEDIUM">Medium</option>
  				  		 <option value="LOW">Low</option>  				  
    			 	   </select>
				     </span>
				     <span ng-switch-when="Status" style="float: right;margin-top: -25px;margin-right: 320px;">				    
				       <select id="statusOptions" name="statusOptions" data-ng-model="x.value" ng-change="commentRequired(x.value)" style="width: 100px;height: 30px;">
                  	     <option value="OPEN">Open</option>
                  		 <option value="CLOSED">Closed</option>                  
    			 	   </select>
				    </span>
				    <span ng-switch-when="Name of the Trader" class="event-timestamp"> {{x.value }}</span>
				    <span ng-switch-default class="event-timestamp"> {{ x.value }} </span>				   
				  </span>	    	         			      			     	   
	    	    </li>
	    	  </ul>               	                	  			 
            </div>
          
            <div class="row" style="padding-left:30px;">
              <ul class="nav nav-tabs" id="case_actions_list" style="border-bottom: 1px solid transparent;">
                <li class="active">
                  <a href="#tab_a" data-toggle="tab" style="border: 1px solid #ddd;color: white;background-color: grey;">Notes</a>
                </li>
                <li>
                  <a href="#tab_b" data-toggle="tab" >Add Attachments</a>
                </li>                 
                <li>
                  <a href="#tab_c" data-toggle="tab">Transaction</a>
                </li>                               
                <li>
                  <a href="#tab_c" data-toggle="tab">Linked Cases</a>
                </li>
                <li>
                  <a href="#tab_c" data-toggle="tab">Similar Cases</a>
                </li>
              </ul>
              <div class="tab-content" style="width:95%;">
                <div class="tab-pane active" id="tab_a">                    
                  <h4>Previous Comment: </h4> <h5> {{previousComment}}</h5>                            			  				
                  <textarea class="form-control" name="comment" id="comment" placeholder="Add Comments" rows="3" ng-keyup="enableDisableButton()"></textarea>
                  <span id="required_msg" ng-show="disableButton" style="color:red;" >**Comment Required  </span>
                </div>
                     
                <div class="tab-pane" id="tab_b">
                  <div class="form-group">
    			    <label for="exampleInputFile">File input</label>
    				<input type="file" id="exampleInputFile">
    				<p class="help-block">.doc,.pdf,.png,.jpeg.</p>
  				  </div>
                </div>                
              </div>
              <div style="float:right;line-height:100px;margin-right:30px;">
                <button type="submit" class="btn btn-primary" id="update_btn" ng-disabled="disableButton" ng-click="updateSelectedEventData()" >Update</button>
                <button type="submit" class="btn btn-default">Cancel</button>
              </div>
            </div>
          </div>
        </div>
          
        <div class="col-md-3">            
		  <div class="panel panel-primary">
		    <div class="panel-heading"> Reminder Events </div>
			<div class="panel-footer">
			  <ul class="list-group" style="max-height:75%;overflow-y:auto;">
                <li class="list-group-item" ng-repeat="x in reminderEventList">    		     	  
    	          <a href="#" id="selected_event_{{x.eventId}}">
    	            <h4 class="list-group-item-heading">Task Name: {{ x.taskName }}</h4>
    	          </a>
                  <p class="list-group-item-text">Due Date: {{ x.dueDate }}</p> 
                  <p class="list-group-item-text">Priority: {{ x.priority }}</p>                
  	            </li>  			  
  	          </ul> 
			</div>
		  </div>		    		 
        </div>
        
      </div>
    </div>    
  </body>      
</html>

 