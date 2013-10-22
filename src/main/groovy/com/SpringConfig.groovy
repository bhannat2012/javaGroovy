package com

import org.springframework.context.annotation.*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.*

import javax.sql.DataSource


@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass=true)
class SpringConfig {

    @Bean @Scope("prototype")
    Account prototypeAccount(){
        new Account(id:1,name:'akhil',timeStamp:new Date())
    }
    @Bean
    DataSource dataSource(){
         new EmbeddedDatabaseBuilder().
                            setType(EmbeddedDatabaseType.H2).
                            addDefaultScripts().
                            addScript("schema.sql").
                            addScript("test-data.sql").
                            build()
    }

    @Bean
    JdbcTemplate jdbcTemplate( DataSource dataSource  ){
        new JdbcTemplate(dataSource)
    }
}
