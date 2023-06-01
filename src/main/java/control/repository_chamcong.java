package control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface repository_chamcong extends JpaRepository<chamcong, chamcongkey>{
	 @Query("SELECT COUNT(u) FROM chamcong u WHERE u.manv=?1 and Month(u.ngay)=?2 and u.trangthai=?3")
     long countNgaycong(String name,int thang,String trangthai);
	 @Query(value="select * FROM chamcong u where u.manv=?1 and Month(u.ngay)=?2",nativeQuery = true)
	 List<chamcong> findChamCong(String manv,int thag);
}
