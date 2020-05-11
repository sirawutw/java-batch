
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.transform.FieldExtractor;

import com.kbtg.fds.domain.AccountJournalBalance;
import com.kbtg.fds.utils.FixedLengthDataUtil;

public class AreOglBalanceExtractor implements FieldExtractor<AccountJournalBalance> {

	@Override
	public Object[] extract(AccountJournalBalance data) {
		List<String> record = new ArrayList<String>();
		record.add(FixedLengthDataUtil.leftPadZero(data.getRecType(), 2));
		record.add(FixedLengthDataUtil.leftPadZero(String.valueOf(data.getLineNo()), 15));
		record.add(FixedLengthDataUtil.rightPadSpace(data.getOwnerEntityCode(), 10));
		record.add(FixedLengthDataUtil.rightPadSpace(data.getAmountTypeOrAccountCode(), 15));
		record.add(FixedLengthDataUtil.rightPadSpace(data.getBalanceStatusCode(), 15));
		record.addAll(extractBalanceCategoryCodeData(data));
		record.addAll(extractCustomerBussinessLineProjectTransactionInformationData(data));
		record.add(FixedLengthDataUtil.leftPadZero(data.getProductCode(), 9));
		record.add(FixedLengthDataUtil.leftPadSpace(data.getFillerProductCode(), 1));
		record.addAll(extractReferenceCodeData(data));
		record.add(FixedLengthDataUtil.rightPadSpace(data.getCurrencyCode(), 15));
		record.add(FixedLengthDataUtil.leftPadSpace(data.getBalanceSign(), 2));
		if (data.getSourceAmount().compareTo(BigDecimal.ZERO) < 0) {
			record.add("-" + FixedLengthDataUtil.leftPadZero(data.getSourceAmount().setScale(2, RoundingMode.HALF_UP)
					.toString().replaceAll("\\.", "").replaceAll("-", ""), 19));
		} else {
			record.add(FixedLengthDataUtil.leftPadZero(
					data.getSourceAmount().setScale(2, RoundingMode.HALF_UP).toString().replaceAll("\\.", ""), 20));
		}
		if (data.getBaseAmount().compareTo(BigDecimal.ZERO) < 0) {
			record.add("-" + FixedLengthDataUtil.leftPadZero(data.getBaseAmount().setScale(2, RoundingMode.HALF_UP)
					.toString().replaceAll("\\.", "").replaceAll("-", ""), 19));
		} else {
			record.add(FixedLengthDataUtil.leftPadZero(
					data.getBaseAmount().setScale(2, RoundingMode.HALF_UP).toString().replaceAll("\\.", ""), 20));
		}
		record.add(FixedLengthDataUtil.leftPadSpace(data.getDescription(), 140));
		record.add(FixedLengthDataUtil.leftPadSpace(data.getLastUpdateDate(), 8));
		record.add(FixedLengthDataUtil.leftPadSpace(data.getFiller(), 5));

		return record.toArray(new String[] {});
	}

	private List<String> extractBalanceCategoryCodeData(AccountJournalBalance data) {
		List<String> balanceCategoryCodeRecord = new ArrayList<String>();
		balanceCategoryCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getBccOglCustomerCategoryCode(), 3));
		balanceCategoryCodeRecord.add(FixedLengthDataUtil.leftPadSpace(data.getFillerBccOcc(), 2));
		balanceCategoryCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getBccSourceProductIdGroup(), 10));
		balanceCategoryCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getFillerBccSpg(), 5));
		return balanceCategoryCodeRecord;
	}

	private List<String> extractCustomerBussinessLineProjectTransactionInformationData(AccountJournalBalance data) {
		List<String> customerBussinessLineProjectTransactionInformation = new ArrayList<String>();
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.rightPadSpace(data.getCusCisNoOrOglEntityCode(), 10));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getFillerCusCode(), 1));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getCusBotCustomerType(), 4));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getFillerCusBot(), 1));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getCusLineOfBusiness(), 3));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getFillerCusLob(), 1));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getCusProjectCode(), 4));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getFillerPjt(), 1));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getCusOglSeg10(), 7));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getFillerCusSeg10(), 1));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getCusOglSeg11(), 4));
		customerBussinessLineProjectTransactionInformation
				.add(FixedLengthDataUtil.leftPadSpace(data.getFillerCusSeg11(), 3));
		return customerBussinessLineProjectTransactionInformation;
	}

	private List<String> extractReferenceCodeData(AccountJournalBalance data) {
		List<String> referenceCodeRecord = new ArrayList<String>();
		referenceCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getRef1Number(), 20));
		referenceCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getRef1Type(), 6));
		referenceCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getFillerRef1(), 1));
		referenceCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getRef2Number(), 20));
		referenceCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getRef2Type(), 6));
		referenceCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getFillerRef2(), 1));
		referenceCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getRef3Number(), 20));
		referenceCodeRecord.add(FixedLengthDataUtil.rightPadSpace(data.getRef3Type(), 6));
		return referenceCodeRecord;
	}
}
