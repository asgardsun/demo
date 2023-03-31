import com.example.demo.repository.JdbcTemplateMemberRepository;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberReopository;
import com.example.demo.sercvice.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberReopository memberRepository() {

        return new JpaMemberRepository(em);
    }

}