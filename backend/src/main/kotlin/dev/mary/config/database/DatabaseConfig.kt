package dev.mary.config.database

interface DatabaseConfig {
    
    val driver: String
    val host: String
    val port: UShort
    
    val username: String
    val database: String
    val password: String
    
    val jdbcConnectionUrl: String
        get() = "jdbc:$driver://$host:$port/$database"
    
}
