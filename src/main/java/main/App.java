package main;

import java.io.File;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import config.*;
import control.*;

public class App {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ctx.getBean("threadPoolTaskExecutor");

		for (String x : args) {
			String[] str = x.split("\\.");
			File file = new File(x);
			if (!file.canRead()) {
				System.out.println("File " + file.toString() + " does't exist or can't be read.");
				continue;
			}
			if (str[str.length - 1].equals("csv")) {
				ThreadController ctrCSV = (ThreadController) ctx.getBean("threadControllerCSV");
				ctrCSV.setFile(file);
				taskExecutor.execute(ctrCSV);
			} else if (str[str.length - 1].equals("json")) {
				ThreadController ctrJSON = (ThreadController) ctx.getBean("threadControllerJSON");
				ctrJSON.setFile(file);
				taskExecutor.execute(ctrJSON);
			} else {
				System.out.println("Can't parse ." + str[str.length - 1] + " file.");
			}
		}
		taskExecutor.shutdown();
	}

}
