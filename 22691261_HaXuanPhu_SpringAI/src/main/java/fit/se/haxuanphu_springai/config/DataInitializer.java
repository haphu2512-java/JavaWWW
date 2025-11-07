package fit.se.haxuanphu_springai.config;



import fit.se.haxuanphu_springai.entity.User;
import fit.se.haxuanphu_springai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            admin.setEnabled(true);
            userRepository.save(admin);
            System.out.println("✅ Created admin user: username=admin, password=admin123");
        }

        if (userRepository.findByUsername("customer").isEmpty()) {
            User customer = new User();
            customer.setUsername("customer");
            customer.setPassword(passwordEncoder.encode("customer123"));
            customer.setRole("ROLE_CUSTOMER");
            customer.setEnabled(true);
            userRepository.save(customer);
            System.out.println("✅ Created customer user: username=customer, password=customer123");
        }

        if (userRepository.findByUsername("guest").isEmpty()) {
            User guest = new User();
            guest.setUsername("guest");
            guest.setPassword(passwordEncoder.encode("guest123"));
            guest.setRole("ROLE_GUEST");
            guest.setEnabled(true);
            userRepository.save(guest);
            System.out.println("✅ Created guest user: username=guest, password=guest123");
        }
    }
}

