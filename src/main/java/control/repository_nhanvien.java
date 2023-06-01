package control;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
@Repository
public interface repository_nhanvien extends JpaRepository<nhanvien, String>{

//	@Query("SELECT o FROM nhanvien o WHERE o.tennv=?1")
	List<nhanvien> findByTennv(String tennv);
//		@Query("SELECT o FROM nhanvien o WHERE o.chucvu=?1")
	List<nhanvien> findByChucvu(String chucvu);
	@Query(value="update nhanvien set tennv=?1,dc=?2,sdt=?3 where manv=?4",nativeQuery = true)
	@Modifying
	@Transactional
	int update(String tennv,String dc,String sdt,String manv);

}
