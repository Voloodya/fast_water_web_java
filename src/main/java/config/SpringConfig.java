package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = { "config","controller","dao","entity","service","weatherAPI"})
public class SpringConfig {

//    @Bean
//    public JdbcTemplate getJdbcTemplate(){
//       return new JdbcTemplate(getDataSource());
//    }

    //Bean с настройками для подключения к БД
//    @Bean
//    public DataSource getDataSource(){
//        DriverManagerDataSource dataSource=new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/dbfastwater?useSll=false");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        return  dataSource;
//    }

}
