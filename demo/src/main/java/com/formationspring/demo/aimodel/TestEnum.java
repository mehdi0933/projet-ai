
import org.example.SupportedAi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestEnum implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Supported AI from module: " + SupportedAi.MISTRAL);
    }
}
