package mx.com.ananda.cronos.juno.repository;

import mx.com.ananda.cronos.juno.model.entity.OrdenCompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrdenCompraRepository extends JpaRepository<OrdenCompraModel,Long> {
    Optional<OrdenCompraModel> findByDocNum(Long docNum);

}
