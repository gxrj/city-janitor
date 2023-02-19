package io.github.gxrj.janitory.core.Call;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CallRepository extends JpaRepository<Call, UUID> { 
    
    Optional<Call> findByProtocol( String protocol );

    List<Call> findByStatus( Status status, Sort sort );
    List<Call> findByDuty_Name( String dutyName, Sort sort );
    List<Call> findByAddress_ZipCode( String zipCode, Sort sort );
    List<Call> findByAddress_PubPlace( String pubPlace, Sort sort );
    List<Call> findByAddress_District_Name( String district, Sort sort );
    List<Call> findByDestination_Name( String deptName, Sort sort );
    List<Call> findByAuthor_Email( String email, Sort sort );

    @Query( "select o from Ocorrencia o where o.creationDate between ?1 and ?2 and o.destination.name = ?3" )
    List<Call> searchByDestinationBetween( 
                            LocalDateTime start, LocalDateTime end, String deptName );
}
