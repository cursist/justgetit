package be.vdab.justgetit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    // TODO: juist maken
    //rollen uit DB
    private static final String USERS_BY_USERNAME =
            "select accountnaam as username, wachtwoord as password, actief as enabled" +
                    " from klanten where accountnaam = ?";
    private static final String AUTHORITIES_BY_USERNAME =
            "select klanten.accountnaam as username, rollen.naam as authorities" +
                    " from klanten inner join gebruikersrollen" +
                    " on klanten.klantid = gebruikersrollen.klantid" +
                    " inner join rollen" +
                    " on rollen.rolid = gebruikersrollen.rolid" +
                    " where klanten.accountnaam= ?";

    //uitgelezen klantenrollen
    @Bean
    JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
        JdbcDaoImpl impl = new JdbcDaoImpl();
        impl.setDataSource(dataSource);
        return impl;
    }
    //hardcoded rollen
    private static final String MANAGER = "manager";
    private static final String BEDIENDE = "bediende";
    private static final String KLANT = "klant";

    //hardcoded rollen
    /*@Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.builder().username("testManager").password("{noop}ikzaag")
                        .authorities(MANAGER).build(),
                User.builder().username("testBediende").password("{noop}ikkreun")
                        .authorities(BEDIENDE).build(),
                User.builder().username("testKlant").password("{noop}ikkoning")
                        .authorities(KLANT).build());
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .mvcMatchers("/home/**")
//                .mvcMatchers("/categorieen/**")
//                .mvcMatchers("/mandje/**")
//                .mvcMatchers("/product/**")
//                .mvcMatchers("/productZoeken/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .and().logout()
                .logoutSuccessUrl("/")
                .and().authorizeRequests()
                .mvcMatchers("/manager").hasAuthority(MANAGER)
                .mvcMatchers("/bediende").hasAnyAuthority(BEDIENDE)
                .mvcMatchers("/klant").hasAnyAuthority(KLANT)
                .mvcMatchers("/**").permitAll();
    }
}