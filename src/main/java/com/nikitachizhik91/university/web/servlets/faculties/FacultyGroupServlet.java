package com.nikitachizhik91.university.web.servlets.faculties;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.FacultyManager;
import com.nikitachizhik91.university.domain.impl.FacultyManagerImpl;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/facultyGroup")
public class FacultyGroupServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(FacultyGroupServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started deleteGroupFromFaculty() method.");

		FacultyManager facultyManager = new FacultyManagerImpl();
		String groupId = request.getParameter("groupId");
		String facultyId = request.getParameter("facultyId");

		try {
			facultyManager.deleteGroupFromFaculty(Integer.parseInt(groupId));

		} catch (NumberFormatException e) {
			log.error("The id=" + groupId + " is wrong.", e);
			throw new WebException("The id=" + groupId + " is wrong.", e);

		} catch (DomainException e) {
			log.error("Cannot delete group with id=" + groupId + " from faculty with id=" + facultyId, e);
			throw new WebException("Cannot delete group with id=" + groupId + " from faculty with id=" + facultyId, e);
		}

		response.sendRedirect("faculty?facultyId=" + facultyId);

		log.trace("Finished deleteGroupFromFaculty() method.");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Started addGroup() method.");

		String groupId = request.getParameter("groupId");
		String facultyId = request.getParameter("facultyId");
		FacultyManager facultyManager = new FacultyManagerImpl();

		try {
			facultyManager.addGroup(Integer.parseInt(facultyId), Integer.parseInt(groupId));

		} catch (DomainException e) {
			log.error("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);
			throw new WebException("Cannot add group with id=" + groupId + " to faculty with id=" + facultyId, e);

		} catch (NumberFormatException e) {
			log.error("The id=" + facultyId + " is wrong.", e);
			throw new WebException("The id=" + facultyId + " is wrong.", e);
		}
		response.sendRedirect("faculty?facultyId=" + facultyId);

		log.trace("Finished addGroup() method.");
	}
}
