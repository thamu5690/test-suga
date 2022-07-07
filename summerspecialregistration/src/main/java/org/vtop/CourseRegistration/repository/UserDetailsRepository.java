package org.vtop.CourseRegistration.repository;

import org.springframework.stereotype.Repository;
import org.vtop.CourseRegistration.model.UserDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailModel,String>{

}
