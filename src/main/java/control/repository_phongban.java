package control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface repository_phongban extends JpaRepository<phongban, String> {

}
