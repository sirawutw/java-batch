
import java.util.Arrays;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.kbtg.fds.repository" }) //your repository in project
public class PrimaryDatabaseConfig implements EnvironmentAware {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private RelaxedPropertyResolver propertyResolver;

	private RelaxedPropertyResolver hikariPropertyResolver;

	private Environment env;

	@Override
	public void setEnvironment(Environment env) {
		this.env = env;
		this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
		this.hikariPropertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.hikari.");
	}

	@Primary
	@Bean(name = "primaryDataSource", destroyMethod = "close")
	public DataSource dataSource() {
		log.info("===============  Primary Configuring Datasource ===================");
		if (propertyResolver.getProperty("url") == null) {
			log.error(
					"Your database connection pool configuration is incorrect! The application"
							+ "cannot start. Please check your Spring profile, current profiles are: {}",
					Arrays.toString(env.getActiveProfiles()));

			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}
		HikariConfig config = new HikariConfig(); //getProperty from project properties
		config.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
		config.setJdbcUrl(propertyResolver.getProperty("url"));
		config.setUsername(propertyResolver.getProperty("username"));
		config.setPassword(propertyResolver.getProperty("password"));
		config.setConnectionTimeout(Integer.parseInt(hikariPropertyResolver.getProperty("connection-timeout")));
		config.setIdleTimeout(Integer.parseInt(hikariPropertyResolver.getProperty("idle-timeout")));
		config.setMaximumPoolSize(Integer.parseInt(hikariPropertyResolver.getProperty("maximum-pool-size")));
		config.setMinimumIdle(Integer.parseInt(hikariPropertyResolver.getProperty("minimum-idle")));
		config.setConnectionTestQuery("select 1 from dual");
		return new HikariDataSource(config);
	}

}
