import org.apache.shiro.crypto.hash.Sha512Hash

class BootStrap {

    def init = { servletContext ->
		
		// setup test users and roles
		// TODO: replace these with actual permenant database entries
		
		def adminRole = new ShiroRole(name: "admin")
		adminRole.addToPermissions("patientEncounterForm:*") // create, modify, delete any form
		adminRole.addToPermissions("shiroUser:*") // create, modify, delete any user
		adminRole.addToPermissions("searchable:*")
		adminRole.save()
		
		def adminUser = new ShiroUser(username: "admin", passwordHash: new Sha512Hash("admin").toHex())
		adminUser.addToRoles(adminRole)
		adminUser.save()
		
		def analystRole = new ShiroRole(name: "analyst")
		analystRole.addToPermissions("patientEncounterForm:show,list,analyze")
		analystRole.addToPermissions("searchable:*")
		analystRole.save()
		
		def analystUser = new ShiroUser(username: "analyst", passwordHash: new Sha512Hash("analyst").toHex())
		analystUser.addToRoles(analystRole)
		analystUser.save()
		
		def clinicianRole = new ShiroRole(name: "clinician")
		clinicianRole.addToPermissions("patientEncounterForm:show,list,upload,download") // also allow form upload and client download
		clinicianRole.addToPermissions("searchable:*")
		clinicianRole.save()
		
		def clinicianUser = new ShiroUser(username: "clinician", passwordHash: new Sha512Hash("clinician").toHex())
		clinicianUser.addToRoles(clinicianRole)
		clinicianUser.save()
    }
    def destroy = {
    }
}
