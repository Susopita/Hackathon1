package utec.hackathon.SparkyAISystem;

import org.springframework.boot.SpringApplication;

public class TestSparkyAiSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(SparkyAiSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
