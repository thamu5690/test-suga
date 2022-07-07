package org.vtop.CourseRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.StudentCurrentCgpaModel;

@Repository
public interface StudentCurrentCgpaRepository extends JpaRepository<StudentCurrentCgpaModel,String> {

}
