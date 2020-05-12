package com.kbtg.fds.batch.step.tasklet;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbtg.fds.repository.CallProcedureRepository;

@Component("clearLeadLoanTasklet")
public class ClearLeadLoanTasklet implements Tasklet {
	
	@Autowired
	private CallProcedureRepository procedureRepository;

	@Override
	public RepeatStatus execute(StepContribution step, ChunkContext context) throws Exception {
		Date asOfDate = (Date) context.getStepContext().getJobParameters().get("asOfDate");
		String transDate = DateFormatUtils.format(asOfDate, "dd/MM/yyyy");
		String result = procedureRepository.clearMlsLeadLoan(transDate);
		
		if(!StringUtils.equals(result, "success")) {
			throw new Exception("Error in database "+result);
		}
		
		return RepeatStatus.FINISHED;
	}
}
