package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    Page<Paciente> findAllByAtivoTrue(Pageable pag);

    @Query(value = """
            select m.ativo
            from Pacientes m
            where 
            m.id = :id
            """, nativeQuery = true)
    Boolean findAllById(Long id);
}
