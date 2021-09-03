package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.users.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserJpaRepository extends JpaRepository<AuthUser,Long>, JpaSpecificationExecutor<AuthUser> {

    @Query("SELECT au FROM AuthUser au WHERE au.username = ?1 OR au.email = ?1")
    AuthUser getAuthUserByUsernameOrEmail(String text);

}
