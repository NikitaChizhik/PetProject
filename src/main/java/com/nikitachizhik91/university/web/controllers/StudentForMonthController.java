package com.nikitachizhik91.university.web.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.LessonManager;
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.model.Lesson;
import com.nikitachizhik91.university.model.Student;
import com.nikitachizhik91.university.web.WebException;

@Controller
public class StudentForMonthController {

	private final static Logger log = LogManager.getLogger(StudentForMonthController.class.getName());

	@Autowired
	private StudentManager studentManager;
	@Autowired
	private LessonManager lessonManager;

	@GetMapping(value = "/studentTimetableForMonth")
	public ModelAndView showAllStudents(ModelAndView model) throws WebException {

		log.trace("Get request to find all students");

		List<Student> students = null;

		try {
			students = studentManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all students.", e);
			throw new WebException("Cannot find all students.", e);
		}

		model.addObject("students", students);
		model.setViewName("findStudentTimetableForMonth");

		log.trace("Found all students");

		return model;
	}

	@PostMapping(value = "/displayStudentTimetableForMonth")
	public ModelAndView displayStudentTimetableForDay(@RequestParam("studentId") String studentId,
			@RequestParam("date") String dateString) throws WebException {

		log.trace("Get request to find student timetable for month,with student id=" + studentId + " and date="
				+ dateString);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
		Date date = null;

		try {

			date = formatter.parse(dateString);
		} catch (ParseException e) {

			log.error("Date=" + date + " is wrong.", e);
			throw new WebException("Date=" + date + " is wrong.", e);
		}

		List<Lesson> lessons = null;
		List<Student> students = null;
		Student student = null;

		try {

			student = studentManager.findById(Integer.parseInt(studentId));

			lessons = lessonManager.getStudentTimetableForMonth(Integer.parseInt(studentId), date);

			students = studentManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
			throw new WebException(
					"Cannot getStudentTimetableForDay() for student with id=" + studentId + " and Date :" + date, e);
		} catch (NumberFormatException e) {

			log.error("The student id=" + studentId + " is wrong.", e);
			throw new WebException("The student id=" + studentId + " is wrong.", e);
		}

		ModelAndView model = new ModelAndView();

		model.addObject("students", students);
		model.addObject("lessons", lessons);
		model.addObject("student", student);
		model.setViewName("findStudentTimetableForMonth");

		log.trace("Found " + lessons.size() + " lessons for student with id=" + studentId + " and date=" + dateString);

		return model;
	}
}
