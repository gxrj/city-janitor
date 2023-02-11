package io.github.gxrj.janitory.core.Call;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CallRepository extends JpaRepository<Call, UUID> { 
    
    Optional<Call> findByProtocol( String protocol );

    List<Call> findByStatus( Status status );
    List<Call> findByDuty_Name( String dutyName );
    List<Call> findByAddress_ZipCode( String zipCode );
    List<Call> findByAddress_PubPlace( String pubPlace );
    List<Call> findByAddress_District_Name( String district );
    List<Call> findByDestination_Name( String deptName );
    List<Call> findByAuthor_Email( String email );

    @Query( "select c from Call where c.creationDate between ?1 and ?2 where c.destination.name = ?3" )
    List<Call> searchByDestionationBetween( 
                            LocalDateTime start, LocalDateTime end, String deptName );
}
