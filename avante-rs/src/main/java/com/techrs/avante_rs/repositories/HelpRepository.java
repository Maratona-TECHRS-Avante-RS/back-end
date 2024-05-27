package com.techrs.avante_rs.repositories;

import com.techrs.avante_rs.domain.Help;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface HelpRepository extends JpaRepository<Help, Long> {

    boolean existsByIdAndUserCreatorId(Long idHelp, Long idUser);

    @Query(value = """
        SELECT h
        FROM Help h
        JOIN User u ON u.id = :userId
        JOIN User uc ON uc.id = h.userCreator.id
        JOIN Address uad ON uad.id = u.address.id
        LEFT JOIN Address had ON had.id = COALESCE(h.address.id, uc.address.id)
        WHERE
            (h.userCreator.id != :userId) AND
            (h.createdAt >= :thresholdDate) AND
            ((:urgencyLevelId IS NULL) OR (h.urgencyType.id = :urgencyLevelId)) AND
            ((:helpTypeId IS NULL) OR (h.helpType.id = :helpTypeId)) AND
            ((:distance IS NULL))
    """)//OR (haversine(uad.coordinateX, uad.coordinateY, had.coordinateX, had.coordinateY)  <= CAST(:distance AS double) ))
    Page<Help> findAllWithParams(@Param("urgencyLevelId") Long urgencyLevelId,
                                 @Param("helpTypeId") Long helpTypeId,
                                 @Param("distance") Long distance,
                                 @Param("userId") Long userId,
                                 @Param("thresholdDate") LocalDateTime threshouldDate,
                                 Pageable pageable);

    Page<Help> findAllByUserCreatorId(Long idUser, Pageable pageable);
}
