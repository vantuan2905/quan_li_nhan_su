package control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface repository_account extends JpaRepository<accounts, String>{
	@Query(value="update accounts set password=?1 where manv=?2",nativeQuery = true)
	@Modifying
	@Transactional
	public void updateAccount(String pass,String manv);
}
