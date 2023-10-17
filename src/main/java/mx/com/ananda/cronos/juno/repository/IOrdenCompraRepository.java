package mx.com.ananda.cronos.juno.repository;

import mx.com.ananda.cronos.juno.model.entity.OrdenCompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenCompraRepository extends JpaRepository<OrdenCompraModel,Long> {
}
