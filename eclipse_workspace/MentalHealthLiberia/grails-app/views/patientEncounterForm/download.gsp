
<%@ page import="edu.gatech.mentalhealthliberia.PatientEncounterForm" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm')}" />
        <title>Analyze Patient Encounters</title>
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
            	
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${patientEncounterFormInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="Generate Report" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
