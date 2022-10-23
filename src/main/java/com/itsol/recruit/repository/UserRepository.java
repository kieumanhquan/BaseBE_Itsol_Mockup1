package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Unit;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.repoext.UserRepositoryExt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryExt {

    @Modifying
    @Query("update User u set u.password = :password where u.userName = :userName")
    int updateUserPasswordName(@Param("userName") String userName,@Param("password") String password);

    @Query("update User u set u.password = :password where u.email = :email")
    int updateUserPassword(@Param("email") String email,
                           @Param("password") String password);
    @Query("select u from User u where u.userName =?1")
    User findByUserName(String userName);

    @Query("select u from User u where u.email = :email")
    User findByEmail(String email);

    //trangcode
    @Query("select u from User u where u.phoneNumber =?1")
    User findByPhoneNumber (String phoneNumber);

    @Query("select u from User u where u.cccd =?1")
    User findByCCCD (String cccd);

    @Query("SELECT u FROM User u where " +
            "(u.unit = :unitDm  or :unitDm is null) " +
            " and (lower(u.fullName)  like '%' ||  lower(:fullName) || '%' or :name is null)" +
            " and (lower(u.email)  like '%' ||  lower(:email) || '%' or :email is null)" +
            " and (lower(u.literacy)  like '%' ||  lower(:literacy) || '%' or :literacy is null)" +
            " and (lower(u.position)  like '%' ||  lower(:position) || '%' or :position is null)" +
            " and (u.salary  = :salary  or :salary is null)" +
            " and (u.birthDay = :birthDay  or :birthDay is null)" +
            "and   (u.unit  =:unit or :unit is null)")
    Page<User> findByKey(
            Pageable pageable,
            @Param("fullName") String fullName,
            @Param("email") String email,
            @Param("literacy") String literacy,
            @Param("position" )String position,
            @Param("salary") Long salary,
            @Param("birthDay") Date birthDay,
            @Param("unit") Unit unit,
            @Param("unitDm") Unit unitDm
    );

}
