package edu.gatech.mentalhealthliberia

class PatientEncounterForm {

	static searchable = true;
	
    static constraints = {
		// the following is a non-nullable, not blank, unique attribute matching the given regular expression
		clinicianID(nullable: false, blank: false, matches:"[A-Za-z0-9]+", unique: true)
		
		
		
		
		/* The following is not needed at this point
		 * ----------------------------------------------------------------
		clinicianTrainingLevel(
			inList:[
				'No formal training',
				'Community health worker training',
				'Physician\'s assistant training',
				'Psychiatric nurse training',
				'Other'])
		referralSource(inList:['Clinician', 'Messages (radio, etc)', 'Other'])
		employmentStatus(inList:['Not working', 'Working'])
		maritalStatus(inList:['Single, never married', 'Married', 'Divorsed', 'Widowed'])
		education(inList:['No formal education', 'Grade (primary) school', 'High school', 'Some university', 'University'])
		countyOfResidence(
			inList:[
				'Bomi',
				'Bong',
				'Gbarpolo',
				'Grand Bassa',
				'Grand Cape Mount',
				'Grand Gedah',
				'Grand Kru',
				'Lofa',
				'Margibi',
				'Maryland',
				'Montserrado',
				'Nimba',
				'River Cess',
				'River Gee',
				'Sinoe',
				'Other'])
		diagnosisPrimary(
			inList:[
				'Major depressive disorder, recurrent',
				'Bipolar disorder, most recent episode depressed',
				'Bipolar disorder, most recent episode manic',
				'Bipolar disorder, most recent episode mixed',
				'Mood disorder NOS',
				'Mood disorder due to medical condition'
				])
		moodDisorder(
			inList:[
				'Full remission',
				'Partial remission',
				'Mild',
				'Moderate',
				'Severe without psychotic features',
				'Severe with psychotic features',
				'Unspecified'])
		anxietyDisorder(
			inList:[
				'Generalized anxiety disorder',
				'Panic disorder with agoraphobia',
				'Panic disorder without agoraphobia',
				'Agoraphobia wihtout history of panic disorder',
				'Specific phobia',
				'Social phobia',
				'Obsessive compulsive disorder',
				'Post-traumatic stress disorder',
				'Acute stress',
				'Anxiety disorder due to general medical condition',
				'Anxiety disorder due to specific medical condition',
				'Anxiety disorder NOS'])
		* --------------------------------------------------------------
		*/
    }
	
	// Basic Information
	String clinicianID;
	String locationOfService;
	String reasonForVisit;
	String clinicianTrainingLevel;
	String referralSource;
	Date dateOfService;
	
	// Patient Demographics and Personal Info
	// Hashed (except for gender)
	// Used to generate unique Patient ID and lookup potential patient matches
	String patientName; // hash of patient name
	String dateOfBirth; // hash of date of birth
	String gender;
	String fathersName; // hash of fathers name
	String placeOfBirth; // hash of place of birth
	
	// Patient Demographics
	String age;
	String countyOfResidence;
	String distanceTraveled;
	String employmentStatus;
	String maritalStatus;
	String education;
	
	// Symptoms and Functioning
	String phq;
	String gaf;
	String cage;
	
	// Diagnosis
	String diagnosisPrimary;
	String moodDisorder;
	String anxietyDisorder;
	String psychoticDisorder;
	String somatoformDisorder;
	String substanceAbuseDisorder;
	String substanceAbuseDisorder2;
	String epilepsy;
	String otherMedicalCondition;
	String secondaryDiagnosis;
	
	// Treatment
	boolean fluoxetine;
	boolean escitalopram;
	boolean sertraline;
	boolean amitriptyline;
	boolean imipramine;
	boolean otherAntidepressant;
	boolean haloperidal;
	boolean haloperidalDecanoateInjection;
	boolean chlorpromazine;
	boolean fluphenazine;
	boolean fluphenazineDecanoateInjection;
	boolean risperidone;
	boolean risperidoneConstaInjection;
	boolean otherAntipsychotic;
	boolean clomipramine;
	boolean clonazepam;
	boolean diazepam;
	boolean lorazepam;
	boolean otherSedative;
	boolean depakote;
	boolean lithium;
	boolean carbamazepine;
	boolean otherMoodStabilizer;
	boolean depakoteAntiepileptic;
	boolean carbmazepineAntiepileptic;
	boolean phenobarbital;
	boolean phenytoin;
	boolean otherAntiepileptic;
	boolean trihexyphenidyl;
	boolean otherAnticholinergic;
	String counseling;
	String individualCounseling;
	String familyPsychoEducation;
	
	// Discharge
	boolean followUpCareMedication;
	boolean followUpCareCounseling;
	String dischargeDisposition;
	boolean reportData;
}
