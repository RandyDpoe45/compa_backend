package com.wesdom.compa.backend.database.jparepositories;

import com.wesdom.compa.backend.database.model.users.UserLastNotificationRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLastNotificationReadJpaRepository extends
        JpaRepository<UserLastNotificationRead,Long> ,
        JpaSpecificationExecutor<UserLastNotificationRead> {

    UserLastNotificationRead findTop1ByAuthUserId(Long id);

}
