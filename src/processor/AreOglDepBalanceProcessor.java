
import java.util.Date;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.kbtg.fds.domain.AccountJournalBalance;
import com.kbtg.fds.domain.DepositBalance;
import com.kbtg.fds.utils.FixedLengthDataUtil;

@Component("areOglDepBalanceProcessor")
public class AreOglDepBalanceProcessor implements ItemProcessor<DepositBalance, AccountJournalBalance> {

	private static final String FIX_GL_AC_NO = "2791010";
	private Date asOfDate;

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) {
		asOfDate = (Date) stepExecution.getJobExecution().getJobParameters().getDate("asOfDate");
	}

	@Override
	public AccountJournalBalance process(DepositBalance depositBalance) throws Exception {
		AccountJournalBalance accountJournalBalance = new AccountJournalBalance();
		accountJournalBalance.setRecType("01");
		accountJournalBalance.setOwnerEntityCode(depositBalance.getRcCd());
		accountJournalBalance.setAmountTypeOrAccountCode(FIX_GL_AC_NO);
		accountJournalBalance.setBalanceStatusCode("");
		accountJournalBalance.setBccOglCustomerCategoryCode("");
		accountJournalBalance.setFillerBccOcc("");
		accountJournalBalance.setBccSourceProductIdGroup("000");
		accountJournalBalance.setFillerBccSpg("");
		accountJournalBalance.setCusCisNoOrOglEntityCode("000");
		accountJournalBalance.setFillerCusCode("");
		accountJournalBalance.setCusBotCustomerType("");
		accountJournalBalance.setFillerCusBot("");
		accountJournalBalance.setCusLineOfBusiness("");
		accountJournalBalance.setFillerCusLob("");
		accountJournalBalance.setCusProjectCode("");
		accountJournalBalance.setFillerPjt("");
		accountJournalBalance.setCusOglSeg10("");
		accountJournalBalance.setFillerCusSeg10("");
		accountJournalBalance.setCusOglSeg11("");
		accountJournalBalance.setFillerCusSeg11("");
		accountJournalBalance.setProductCode("000000000");
		accountJournalBalance.setFillerProductCode("");
		accountJournalBalance.setRef1Number("");
		accountJournalBalance.setRef1Type("");
		accountJournalBalance.setFillerRef1("");
		accountJournalBalance.setRef2Number("");
		accountJournalBalance.setRef2Type("");
		accountJournalBalance.setFillerRef2("");
		accountJournalBalance.setRef3Number("");
		accountJournalBalance.setRef3Type("");
		accountJournalBalance.setFillerRef3("");
		accountJournalBalance.setCurrencyCode("THB");
		accountJournalBalance.setBalanceSign("CR");
		accountJournalBalance.setSourceAmount(depositBalance.getNetDepAmt());
		accountJournalBalance.setBaseAmount(depositBalance.getNetDepAmt());
		accountJournalBalance.setDescription(FixedLengthDataUtil.leftPadSpace(depositBalance.getId().getRefrDocNo(), 50)
				+ FixedLengthDataUtil.leftPadSpace(depositBalance.getCisNo(), 11));
		accountJournalBalance.setLastUpdateDate(FixedLengthDataUtil.rightPadDateTime(asOfDate, "yyyyMMdd"));
		return accountJournalBalance;
	}

}
