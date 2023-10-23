package mx.com.ananda.cronos.juno.repository;

import mx.com.ananda.cronos.juno.model.entity.RegistroTiempoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRegistroTiempoRepository extends JpaRepository<RegistroTiempoModel,Long> {
    List<RegistroTiempoModel> findByDocNum(Long docNum);
}
