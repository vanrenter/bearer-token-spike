package spike.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@Import({OpenAmConfiguration.class})
public class BeansConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        DefaultBearerTokenResolver bearerTokenResolver=new DefaultBearerTokenResolver();
        bearerTokenResolver.setAllowUriQueryParameter(true);

        security.authorizeHttpRequests(requests -> requests

                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> {
                    oauth2.opaqueToken(Customizer.withDefaults());
                    oauth2.bearerTokenResolver(bearerTokenResolver);
                })

                .cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable());
        return security.build();
    }

}
