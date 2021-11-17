package br.com.inatel.book_wishlist;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@SpringBootApplication
@EnableCaching
@EnableSpringDataWebSupport //support for pagination
@EnableSwagger2
public class BookWishlistApplication {

	public static void main(String[] args) {
//		(Connection conn = DriverManager.getConnection("jdbc:mysql://${MYSQL_CONTAINER}:3306/bootdb?useSSL=false&allowPublicKeyRetrieval=true", "root", "root"))
//		try  {
//        String url = "jdbc:mysql://${MYSQL_CONTAINER}:3306/bootdb?useSSL=false&allowPublicKeyRetrieval=true";
//        String user = "root";
//        String password = "root";
//        System.out.println("Connecting to MySQL ");
//        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//        
//        Connection conn = DriverManager.getConnection("jdbc:mysql://${MYSQL_CONTAINER}:3306/bootdb?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
//	        System.out.println(conn.isValid(0));
//	        System.out.println("Connection was successful");
	        SpringApplication.run(BookWishlistApplication.class, args);
//	    } catch (Exception e) {
//	        System.err.println("Error connecting to database");
//	    }
//		
	}
	
	
	
//	log.info("BookWishlistApplication is running");
//	log.error("Something is wrong here");

}
