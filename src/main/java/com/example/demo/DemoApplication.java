package com.example.demo;

import com.example.demo.Services.*;
import com.example.demo.entities.EntrantSubject;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.FacultySubject;
import com.example.demo.entities.userEntities.Admin;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.TimeZone;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

		ApplicationService applicationService = (ApplicationService)applicationContext.getBean("applicationService");
		EntrantService entrantService = (EntrantService)applicationContext.getBean("entrantService");
		EntrantSubjectService entrantSubjectService = (EntrantSubjectService)applicationContext.getBean("entrantSubjectService");
		FacultyService facultyService = (FacultyService)applicationContext.getBean("facultyService");
		FacultySubjectService facultySubjectService = (FacultySubjectService)applicationContext.getBean("facultySubjectService");
		AdminService adminService = (AdminService)applicationContext.getBean("adminService");

		Faculty chemFac = new Faculty("Хімічна Інженерія", 1);
		FacultySubject fs1 = new FacultySubject(Subjects.UKRAINIAN.name(), 0.2f, chemFac);
		FacultySubject fs2 = new FacultySubject(Subjects.MATH.name(), 0.3f, chemFac);
		FacultySubject fs3 = new FacultySubject(Subjects.CHEMISTRY.name(), 0.4f, chemFac);
		chemFac.setNecessarySubjects(fs1, fs2, fs3, null);
		chemFac.setId(1);
		facultyService.save(chemFac);

		facultySubjectService.save(fs1);
		facultySubjectService.save(fs2);
		facultySubjectService.save(fs3);

		Faculty phisFac = new Faculty("Фізика", 1);
		FacultySubject pfs1 = new FacultySubject(Subjects.UKRAINIAN.name(), 0.1f, phisFac);
		FacultySubject pfs2 = new FacultySubject(Subjects.MATH.name(), 0.3f, phisFac);
		FacultySubject pfs3 = new FacultySubject(Subjects.PHYSICS.name(), 0.5f, phisFac);
		phisFac.setNecessarySubjects(pfs1, pfs2, pfs3, null);
		phisFac.setId(2);
		facultyService.save(phisFac);

		facultySubjectService.save(pfs1);
		facultySubjectService.save(pfs2);
		facultySubjectService.save(pfs3);


		Faculty engFac = new Faculty("Англійська мова", 1);
		FacultySubject efs1 = new FacultySubject(Subjects.UKRAINIAN.name(), 0.3f, engFac);
		FacultySubject efs2 = new FacultySubject(Subjects.HISTORY.name(), 0.2f, engFac);
		FacultySubject efs3 = new FacultySubject(Subjects.ENGLISH.name(), 0.4f, engFac);
		engFac.setNecessarySubjects(efs1, efs2, efs3, null);
		engFac.setId(3);
		facultyService.save(engFac);

		facultySubjectService.save(efs1);
		facultySubjectService.save(efs2);
		facultySubjectService.save(efs3);


		Faculty Fac5 = new Faculty("Біотехнологія", 1);
		FacultySubject fs15 = new FacultySubject(Subjects.UKRAINIAN.name(), 0.1f, Fac5);
		FacultySubject fs25 = new FacultySubject(Subjects.MATH.name(), 0.2f, Fac5);
		FacultySubject fs35 = new FacultySubject(Subjects.CHEMISTRY.name(), 0.3f, Fac5);
		FacultySubject fs45 = new FacultySubject(Subjects.BIOLOGY.name(), 0.3f, Fac5);
		Fac5.setNecessarySubjects(fs15, fs25, fs35, fs45);
		Fac5.setId(4);
		facultyService.save(Fac5);

		facultySubjectService.save(fs15);
		facultySubjectService.save(fs25);
		facultySubjectService.save(fs35);
		facultySubjectService.save(fs45);

		Faculty Fac16 = new Faculty("Компютерні Науки", 1);
		FacultySubject fs116 = new FacultySubject(Subjects.UKRAINIAN.name(), 0.1f, Fac16);
		FacultySubject fs216 = new FacultySubject(Subjects.MATH.name(), 0.5f, Fac16);
		FacultySubject fs316 = new FacultySubject(Subjects.PHYSICS.name(), 0.3f, Fac16);
		Fac16.setNecessarySubjects(fs116, fs216, fs316, null);
		Fac16.setId(5);
		facultyService.save(Fac16);

		facultySubjectService.save(fs116);
		facultySubjectService.save(fs216);
		facultySubjectService.save(fs316);

		Faculty Fac27 = new Faculty("Психологія", 1);
		FacultySubject fs127 = new FacultySubject(Subjects.UKRAINIAN.name(), 0.3f, Fac27);
		FacultySubject fs227 = new FacultySubject(Subjects.HISTORY.name(), 0.3f, Fac27);
		FacultySubject fs327 = new FacultySubject(Subjects.BIOLOGY.name(), 0.3f, Fac27);
		Fac27.setNecessarySubjects(fs127, fs227, fs327, null);
		Fac27.setId(6);
		facultyService.save(Fac27);

		facultySubjectService.save(fs127);
		facultySubjectService.save(fs227);
		facultySubjectService.save(fs327);


		Admin admin = new Admin();
		admin.setEmail("1@1");
		admin.setPassword("1");
		adminService.save(admin);
		Entrant vas = new Entrant("Василь", "Чорний", "11-0.1-2000", 10, "2@2", "2");
		vas.setId(2);

		EntrantSubject es1 = new EntrantSubject(Subjects.UKRAINIAN.name(), 170, vas);
		EntrantSubject es2 = new EntrantSubject(Subjects.MATH.name(), 196, vas);
		EntrantSubject es3 = new EntrantSubject(Subjects.CHEMISTRY.name(), 199, vas);
		EntrantSubject es4 = new EntrantSubject(Subjects.ENGLISH.name(), 175, vas);

		vas.setSubjects(es1, es2, es3, es4);
//
//		Entrant vas2 = new Entrant("Олег", "Білий", "11-0.1-2000", 8, "3@3", "3");
//		vas2.setId(3);
//
//		EntrantSubject es12 = new EntrantSubject(Subjects.UKRAINIAN.name(), 185, vas2);
//		EntrantSubject es22 = new EntrantSubject(Subjects.MATH.name(), 164, vas2);
//		EntrantSubject es32 = new EntrantSubject(Subjects.CHEMISTRY.name(), 150, vas2);
//		EntrantSubject es42 = new EntrantSubject(Subjects.PHYSICS.name(), 171, vas2);
////
////		vas2.setSubjects(es12, es22, es32, es42);
//
//		Entrant vas3 = new Entrant("Марія", "Сіра", "11-0.1-2000", 12, "4@4", "4");
//		vas3.setId(4);
//
//		EntrantSubject es13 = new EntrantSubject(Subjects.UKRAINIAN.name(), 200, vas3);
//		EntrantSubject es23 = new EntrantSubject(Subjects.HISTORY.name(), 200, vas3);
//		EntrantSubject es33 = new EntrantSubject(Subjects.ENGLISH.name(), 200, vas3);
//
//		vas3.setSubjects(es13, es23, es33, null);
//
//		entrantService.save(admin);
		entrantService.save(vas);
//		entrantService.save(vas2);
//		entrantService.save(vas3);
//
//
		entrantSubjectService.save(es1);
		entrantSubjectService.save(es2);
		entrantSubjectService.save(es3);
		entrantSubjectService.save(es4);
//
//		entrantSubjectService.save(es12);
//		entrantSubjectService.save(es22);
//		entrantSubjectService.save(es32);
//		entrantSubjectService.save(es42);
//
//		entrantSubjectService.save(es13);
//		entrantSubjectService.save(es23);
//		entrantSubjectService.save(es33);
//
		applicationService.save(vas, chemFac);
//		applicationService.save(vas2, phisFac);
//		applicationService.save(vas3, engFac);

//		applicationContext.close();
	}

}
