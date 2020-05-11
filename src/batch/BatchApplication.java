import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@EnableBatchProcessing
@ImportResource("classpath:jobs/job-config.xml")
public class BatchApplication {

	public static void main(String[] args)
			throws BeansException, JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException, IOException {

		Logger log = LoggerFactory.getLogger(BatchApplication.class);

		SpringApplication app = new SpringApplication(BatchApplication.class);
		ConfigurableApplicationContext ctx = app.run(args);
		JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

		log.info("===================== jobLauncher jobName : {}", args[1]);

		if (StringUtils.isEmpty(args[1]) || StringUtils.isEmpty(args[2])) {
			log.info("Please provide a valid Job name as first application parameter");
			System.exit(0);
		}

		Job job = ctx.getBean(args[1], Job.class);
		JobParameters jobParameters = new JobParametersBuilder().addDate("asOfDate", buildRunningDate(args[2]))
				.addLong("time", System.currentTimeMillis()).toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		BatchStatus batchStatus = jobExecution.getStatus();
		while (batchStatus.isRunning()) {
			log.info("================== Still running.... ===================");
			Thread.sleep(1000);
		}
		boolean chkdiff = false;
		for (StepExecution step : jobExecution.getStepExecutions()) {
			log.info(String.format("================== Step exit status: %s", step.getExitStatus().getExitCode()));
			log.info(String.format("================== Step exit status description : %s",
					step.getExitStatus().getExitDescription()));
			String exitDesc = jobExecution.getExitStatus().getExitDescription();
			if (StringUtils.equalsIgnoreCase(exitDesc, "DIFF")) {
				chkdiff = true;
				break;
			}
		}

		ExitStatus exitStatus = jobExecution.getExitStatus();
		String exitCode = exitStatus.getExitCode();
		log.info(String.format("================== Exit status: %s", exitCode));
		JobInstance jobInstance = jobExecution.getJobInstance();
		log.info(String.format("================== Name of the job %s", jobInstance.getJobName()));
		log.info(String.format("================== job instance Id: %d", jobInstance.getId()));

		if (StringUtils.equalsIgnoreCase(jobInstance.getJobName(), "sendMailReconcileCTNTJob") && chkdiff) {
			log.info(String.format("================== job exit code: 2"));
			System.exit(2);
		} else if (StringUtils.equalsIgnoreCase(exitCode, "COMPLETED")) {
			log.info(String.format("================== job exit code: 0"));
			System.exit(0);
		} else {
			log.info(String.format("================== job exit code: 1"));
			System.exit(1);
		}
	}

	private static Date buildRunningDate(String asOfDate) {
		Date runningDate = new Date();
		try {
			runningDate = DateUtils.parseDate(asOfDate, "yyyyMMdd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return runningDate;
	}

}
