package pl.sokolik.bartosz.transactionmanager.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "security")
data class SecurityUserConfigurationProperties(val users: Map<String, Credentials>) {

    data class Credentials(val username: String, val password: String, val role: String) {
    }
}
