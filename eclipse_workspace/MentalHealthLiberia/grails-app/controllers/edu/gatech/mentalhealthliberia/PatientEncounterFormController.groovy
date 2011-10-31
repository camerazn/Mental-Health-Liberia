package edu.gatech.mentalhealthliberia

import java.io.BufferedReader;
import java.io.InputStreamReader;

import grails.converters.*
import org.codehaus.groovy.grails.web.json.*; // package containing JSONObject, JSONArray,...

class PatientEncounterFormController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [patientEncounterFormInstanceList: PatientEncounterForm.list(params), patientEncounterFormInstanceTotal: PatientEncounterForm.count()]
    }

    def create = {
        def patientEncounterFormInstance = new PatientEncounterForm()
        patientEncounterFormInstance.properties = params
        return [patientEncounterFormInstance: patientEncounterFormInstance]
    }

    def save = {
        def patientEncounterFormInstance = new PatientEncounterForm(params)
        if (patientEncounterFormInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm'), patientEncounterFormInstance.id])}"
            redirect(action: "show", id: patientEncounterFormInstance.id)
        }
        else {
            render(view: "create", model: [patientEncounterFormInstance: patientEncounterFormInstance])
        }
    }

    def show = {
        def patientEncounterFormInstance = PatientEncounterForm.get(params.id)
        if (!patientEncounterFormInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm'), params.id])}"
            redirect(action: "list")
        }
        else {
            [patientEncounterFormInstance: patientEncounterFormInstance]
        }
    }

    def edit = {
        def patientEncounterFormInstance = PatientEncounterForm.get(params.id)
        if (!patientEncounterFormInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [patientEncounterFormInstance: patientEncounterFormInstance]
        }
    }

    def update = {
        def patientEncounterFormInstance = PatientEncounterForm.get(params.id)
        if (patientEncounterFormInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (patientEncounterFormInstance.version > version) {
                    
                    patientEncounterFormInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm')] as Object[], "Another user has updated this PatientEncounterForm while you were editing")
                    render(view: "edit", model: [patientEncounterFormInstance: patientEncounterFormInstance])
                    return
                }
            }
            patientEncounterFormInstance.properties = params
            if (!patientEncounterFormInstance.hasErrors() && patientEncounterFormInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm'), patientEncounterFormInstance.id])}"
                redirect(action: "show", id: patientEncounterFormInstance.id)
            }
            else {
                render(view: "edit", model: [patientEncounterFormInstance: patientEncounterFormInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def patientEncounterFormInstance = PatientEncounterForm.get(params.id)
        if (patientEncounterFormInstance) {
            try {
                patientEncounterFormInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'patientEncounterForm.label', default: 'PatientEncounterForm'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def upload = {
		println "upload fired";
		if (request.method == "POST") {
			println params.data;
			
			PatientEncounterForm patientEncounterForm = new PatientEncounterForm(JSON.parse(params.data));
			if (patientEncounterForm.save()) {
				render(status: 200, text: 'Success');
			} else {
				render(status: 503, text: 'Failed to save form');
			}
		} else {
			render(status: 503, text: 'Bad request');
		}
	}
	
	def search = {
		
	}
}