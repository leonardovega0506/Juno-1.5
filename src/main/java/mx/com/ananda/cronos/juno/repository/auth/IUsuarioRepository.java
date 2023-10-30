package mx.com.ananda.cronos.juno.repository.auth;

import mx.com.ananda.cronos.juno.model.auth.UsuarioModel;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface IUsuarioRepository extends JpaRepository<UsuarioModel,Long> {

    UsuarioModel findByUsername(String username);
}
