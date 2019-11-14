package pl.pzu.trak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//@SpringBootApplication
//public class TrakApplication extends SpringBootServletInitializer {
//
//    public static void main(String[] args) {
//        SpringApplication.run(TrakApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(TrakApplication.class);
//    }
//}

@SpringBootApplication
public class TrakApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrakApplication.class, args);
	}
}


//rola uzytkownika w systemie datasteward superdatasteward
//To są role w FOCUS 
//tzn. dla każdej spółki która jest w Focus/syzyf uzytkownik będzie miał tą samą rolę