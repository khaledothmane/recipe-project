package com.khaledothmane.recipeproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

//@Configuration
public class SecurityConfig /* extends WebSecurityConfigurerAdapter */ {

    /**
     * Autowiring data source for jdbc authentication

    @Autowired
    DataSource dataSource;
     */

    //@Override
    protected void configure(/*AuthenticationManagerBuilder auth*/) throws Exception {
        /**
         * An in memory authentication for test purpose.
        auth.inMemoryAuthentication()
                .withUser("foo")
                .password("foopass")
                .authorities("ROLE_USER")
                .and()
                .withUser("bar")
                .password("barpass")
                .authorities("ROLE_USER");
         **/


        /**
         * Using jdbc authentication
        auth.jdbcAuthentication().dataSource(dataSource)
                .withUser("arther")
                .password("secret")
                .roles("USER");
        */

    }
}
