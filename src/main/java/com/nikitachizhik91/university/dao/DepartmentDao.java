package com.nikitachizhik91.university.dao;

import java.util.List;

import com.nikitachizhik91.university.model.Department;

public interface DepartmentDao extends Crud<Department> {

	List<Department> findDepartmentsWithoutFaculty() throws DaoException;
}
