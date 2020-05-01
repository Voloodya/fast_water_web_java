package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = { "config","controller","dao","entity","service"})
@EnableWebMvc
@EnableTransactionManagement
public class HibernateConfig {
    private static Logger logger=LoggerFactory.getLogger(HibernateConfig.class);

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = null;
        try {
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/db_fastwater?useSSL=false");//"jdbc:mysql://localhost:3306/dbfastwater?useSSL=false"
                                                                                //"jdbc:mysql://localhost:4848/jdbc_dbfastwater"
            dataSource.setUsername("vs");
            dataSource.setPassword("root");
        } catch (Exception e) {
            logger.error("MysqlDataSource bean cannot be created!", e);
        }
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
    //  hibernateProp.put("hibernate.dialect", "org.hibernate.spatial.dialect.mysql.MySQL56SpatialDialect");
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProp.put("connection.driver_class", "com.mysql.jdbc.Driver");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        //DB schema will be updated if needed (update - обновить схему; create - создать схему, уничтожая предыдущие данные;
        //validate - проверить схему, не вносить изменения в БД; create-drop - отказаться от схемы когда SessionFactory закрывается явно,
        //как правило, когда приложение останавливается)
      //  hibernateProp.put("hibernate.hbm2ddl.auto","update");
    //    hibernateProp.put("current_session_context_class","thread");
    //    hibernateProp.put("hibernate.connection.datasource","jdbc_dbfastwater");
        return hibernateProp;
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setPackagesToScan("entity","dao","service");
        sessionFactoryBean.afterPropertiesSet();
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }

}
