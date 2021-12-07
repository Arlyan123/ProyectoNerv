package com.ArleySebastian.ProyectoNerv;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class ProyectoNervApplication {
	//implements CommandLineRunner
//@Autowired 
//BCryptPasswordEncoder passEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoNervApplication.class, args);
	}

/*@Override
	public void run(String... args) throws Exception {
		String pass1 = "user";
		String pass2 ="admin";
		
		System.out.println(passEncoder.encode(pass1));
		System.out.println(passEncoder.encode(pass2));
}
	*/

}
