package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import control.*;
import read.*;
import write.*;

@Configuration
public class AppConfig {

	@Bean(name = "threadPoolTaskExecutor")
	ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(4);
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return threadPoolTaskExecutor;
	}

	@Bean
	@Scope("prototype")
	public Reader readerCSV() {
		return new ReaderCSV();
	}

	@Bean
	@Scope("prototype")
	public Reader readerJSON() {
		return new ReaderJSON();
	}

	@Bean
	@Scope("prototype")
	public ResultWriteInterface writeSTD() {
		return new StdoutResult();
	}

	@Bean
	@Scope("prototype")
	public Controller controllerCSV() {
		Controller controllerCSV = new Controller();
		controllerCSV.setReader(readerCSV());
		controllerCSV.setResultWriter(writeSTD());
		return controllerCSV;
	}

	@Bean
	@Scope("prototype")
	public Controller controllerJSON() {
		Controller controllerJSON = new Controller();
		controllerJSON.setReader(readerJSON());
		controllerJSON.setResultWriter(writeSTD());
		return controllerJSON;
	}

	@Bean(name = "threadControllerCSV")
	@Scope("prototype")
	public ThreadController threadControllerCSV() {
		ThreadController threadControllerCSV = new ThreadController();
		threadControllerCSV.setController(controllerCSV());
		return threadControllerCSV;
	}

	@Bean(name = "threadControllerJSON")
	@Scope("prototype")
	public ThreadController threadControllerJSON() {
		ThreadController threadControllerJSON = new ThreadController();
		threadControllerJSON.setController(controllerJSON());
		return threadControllerJSON;
	}

}
