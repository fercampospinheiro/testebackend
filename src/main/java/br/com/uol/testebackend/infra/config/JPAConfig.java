//package br.com.uol.testebackend.infra.config;
//import java.util.Properties;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource("classpath:application.properties")
//public class JPAConfig{
//
//   @Inject private Environment env; 
//    
//    @Bean
//    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan(new String[] { "br.com.uol.testebackend" });
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        Properties jpaProp = new Properties();
//        jpaProp.setProperty("spring.jpa.hibernate.ddl-auto", "update");
//        
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(jpaProp);
//
//        return em;
//    }
//    
//    @Bean
//    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.createEntityManager();
//    }
//
//   @Bean
//   public DataSource dataSource(){
//      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//      dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
//      dataSource.setUrl(env.getProperty("spring.datasource.url"));
//      dataSource.setUsername( env.getProperty("spring.datasource.username") );
//      dataSource.setPassword( env.getProperty("spring.datasource.password") );
//      return dataSource;
//   }
//
//   @Bean
//   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
//      JpaTransactionManager transactionManager = new JpaTransactionManager();
//      transactionManager.setEntityManagerFactory(emf);
//
//      return transactionManager;
//   }
//   
//}