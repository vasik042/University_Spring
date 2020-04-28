package com.example.demo;

import com.example.demo.Services.*;
import com.example.demo.entities.Entrant;
import com.example.demo.entities.EntrantSubject;
import com.example.demo.entities.Faculty;
import com.example.demo.entities.FacultySubject;
import com.example.demo.repositories.EntrantSubjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
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

		Faculty chemFac = new Faculty("Politehnika", 20);

		FacultySubject fs1 = new FacultySubject(Subjects.UKRAINIAN.name(), 0.2f, chemFac);
		FacultySubject fs2 = new FacultySubject(Subjects.MATH.name(), 0.3f, chemFac);
		FacultySubject fs3 = new FacultySubject(Subjects.CHEMISTRY.name(), 0.4f, chemFac);

		chemFac.setNecessarySubjects(fs1, fs2, fs3, null);

		Date date = new Date(22);
		Entrant vas = new Entrant("Vas", "Chor", date, 10);

		EntrantSubject es1 = new EntrantSubject(Subjects.UKRAINIAN.name(), 170, vas);
		EntrantSubject es2 = new EntrantSubject(Subjects.MATH.name(), 196, vas);
		EntrantSubject es3 = new EntrantSubject(Subjects.CHEMISTRY.name(), 199, vas);
		EntrantSubject es4 = new EntrantSubject(Subjects.ENGLISH.name(), 175, vas);

		vas.setSubjects(es1, es2, es3, es4);

		facultyService.save(chemFac);
		entrantService.save(vas);

		facultySubjectService.save(fs1);
		facultySubjectService.save(fs2);
		facultySubjectService.save(fs3);

		entrantSubjectService.save(es1);
		entrantSubjectService.save(es2);
		entrantSubjectService.save(es3);
		entrantSubjectService.save(es4);

		applicationService.save(vas, chemFac);

		applicationContext.close();
	}

}
