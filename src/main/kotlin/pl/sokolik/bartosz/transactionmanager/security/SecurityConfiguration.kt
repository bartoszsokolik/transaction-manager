package pl.sokolik.bartosz.transactionmanager.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import java.util.stream.Collectors

@Configuration
class SecurityConfiguration(private val securityProperties: SecurityUserConfigurationProperties) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val users = securityProperties.users.values.stream()
            .map { credentials -> User.withUsername(credentials.username)
                .passwordEncoder {t -> passwordEncoder().encode(t) }
                .password(credentials.password)
                .roles(credentials.role)
                .build() }
            .collect(Collectors.toList())

        return InMemoryUserDetailsManager(users)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/transactions/**").authenticated()
            .antMatchers(
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
            ).permitAll()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
    }
}
