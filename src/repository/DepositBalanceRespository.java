
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kbtg.fds.domain.DepositBalance;
import com.kbtg.fds.domain.DepositBalanceId;

@Repository
public interface DepositBalanceRespository extends JpaRepository<DepositBalance, DepositBalanceId> {
	List<DepositBalance> findByNetDepAmtNotIn(BigDecimal amount);
}
