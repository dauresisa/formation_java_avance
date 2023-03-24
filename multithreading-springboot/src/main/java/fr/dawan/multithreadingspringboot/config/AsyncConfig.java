package fr.dawan.multithreadingspringboot.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/*
 *  @Configuration et @EnableAsync pour configurer la prise en charge des méthodes asynchrones dans une application Spring.
 *  
 *  L'annotation @Configuration indique que la classe est utilisée pour configurer les beans 
 *  dans l'application Spring. 
 *  Cette classe peut contenir des méthodes annotées avec @Bean pour créer et configurer des beans.
 *  
 *  L'annotation @EnableAsync indique que les méthodes annotées avec @Async dans l'application Spring doivent 
 *  être exécutées de manière asynchrone. Les méthodes annotées avec @Async seront exécutées dans des threads 
 *  séparés pour améliorer la performance de l'application.
 */

@Configuration
@EnableAsync
public class AsyncConfig {
	
	
    /*
     *  Lors du lancement de l'application Spring va créer un bean nommée taskExcutor 
     * qui peut être injecté dans d'autres composants de l'application.
     * Cet bean est une instance de l'objet ThreadPoolTaskExecutor 
     * qui est utilisé pour exécuter des tâches asynchrones dans l'application Spring.
     * 
     */
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//corePoolSize : nombre de threads minimum à conserver dans le pool, mm lorsqu'ils sont inactifs
		executor.setCorePoolSize(2);
		//maxPoolSize : nombre maximum autorisés
		executor.setMaxPoolSize(2);
		//queue : taille maximale de la file d'attente des taches en attente d'execution
		executor.setQueueCapacity(100);
		// préfixe pour repérer le thread
		executor.setThreadNamePrefix("userThread-");
		executor.initialize();		
		
		return executor;
		
	};

}
