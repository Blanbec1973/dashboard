package org.heyner.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Point d'entrée de l'application Dashboard.
 *
 * <p>Cette classe démarre l'application Spring Boot et active la planification
 * des scans périodiques des cibles surveillées.</p>
 */
@SpringBootApplication
@EnableScheduling
public class DashboardApplication {

	/**
	 * Lance l'application.
	 *
	 * @param args arguments de ligne de commande
	 */
	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}
}
