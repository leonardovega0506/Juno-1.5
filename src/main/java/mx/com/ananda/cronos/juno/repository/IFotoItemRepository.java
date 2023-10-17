package mx.com.ananda.cronos.juno.repository;

import mx.com.ananda.cronos.juno.model.entity.ItemFotoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFotoItemRepository extends JpaRepository<ItemFotoModel,Long> {
    Optional<ItemFotoModel> findByItemCode(String itemCode);
}
