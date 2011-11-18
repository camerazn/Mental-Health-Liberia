
<%@ page import="edu.gatech.mentalhealthliberia.PatientEncounterForm" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm')}" />
        <title>Analyze Patient Encounters</title>
        <g:javascript library="prototype" />
        <script type="text/javascript" lang="Javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" lang="Javascript">

          // Load Google visualization packages
          google.load('visualization', '1', {'packages':['annotatedtimeline']});
        
          function updateReport(response) {
        	  var json = eval('(' + response.responseText + ')');
        	  drawTimelineSeries(json);
          }

          function drawTimelineSeries(values) {
        	  var data = new google.visualization.DataTable();

        	  data.addColumn('date', 'Date');
        	  data.addColumn('number', 'PHQ');
        	  data.addColumn('number', 'GAF');
        	  data.addColumn('number', 'CAGE');

        	  data.addRows(values);
              
        	  var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('timelineSeries'));
              chart.draw(data, {displayAnnotations: true});
          }

          function updateCategoryField(selectBox) {
			switch (selectBox.value) {
			case "all":
				document.getElementById("clinicianIDField").style.display = "none";
				document.getElementById("countyField").style.display = "none";
				break;
			case "clinician":
				document.getElementById("clinicianIDField").style.display = "block";
				document.getElementById("countyField").style.display = "none";
				break;
			case "county":
				document.getElementById("clinicianIDField").style.display = "none";
				document.getElementById("countyField").style.display = "block";
				break;
			default:
				// Should never happen, but just in case...
				window.alert("Unknown Category Field: " + selectBox.value);
				break;
			}
          }
        </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
            	<div id="timelineSeries">
            	
            	</div>
            	<div id="controls">
            		<g:formRemote name="reportControls" url="[ controller: 'patientEncounterForm', action:'generateReport' ]" onSuccess="updateReport(e)">
            		<table>
            			<tbody>
            				<tr><td><label for="startDate">Start Date</label></td>
            				<td><g:datePicker name="startDate" noSelection="['':'-Choose-']"></g:datePicker></td></tr>
            				<tr><td><label for="endDate">End Date</label></td>
            				<td><g:datePicker name="endDate" noSelection="['':'-Choose-']"></g:datePicker></td></tr>
            				<tr><td><label for="category">Category</label></td>
            				<td>
            					<select name="category" onchange="updateCategoryField(this)">
								  <option value="all">All</option>
								  <option value="clinician">Clinician</option>
								  <option value="region">County</option>
								</select>
            				</td>
            				</tr>
            				<tr id="clinicianIDField">
            					<td><input type="text" name="clinicianID"></input></td>
            				</tr>
            				<tr id="countyField">
            					<td>
            						<select name="county">
            							<option value="somecounty">Some county</option>
            						</select>
            					</td>
            				</tr>
            			</tbody>
   					</table>
		            <div class="buttons">
		                <g:form>
		                    <g:hiddenField name="id" value="${patientEncounterFormInstance?.id}" />
		                    <span class="button"><g:actionSubmit class="edit" action="edit" value="Generate Report" /></span>
		                </g:form>
		            </div>
            		</g:formRemote>
            	</div>
            </div>
        </div>
    </body>
</html>
