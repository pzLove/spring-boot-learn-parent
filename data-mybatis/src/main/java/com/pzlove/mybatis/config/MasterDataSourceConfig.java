package com.pzlove.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {
    private  final Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    private static final String ORACLE_DRIVER_NAME ="oracle.jdbc.driver.OracleDriver";
    private static final String MYSQL_DRIVER_NAME ="com.mysql.jdbc.Driver";
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.pzlove.mybatis.mapper.matser";
    private static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Value("${spring.master.datasource.url}")
    private String url;

    @Value("${spring.master.datasource.username}")
    private String user;

    @Value("${spring.master.datasource.password}")
    private String password;

    @Value("${spring.master.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "masterDataSource")
    @Primary
    @ConditionalOnProperty(value = "spring.master.datasource.flag",havingValue = "true")
    /**
     * @Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
     * 「多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean」
     * @MapperScan 扫描 Mapper 接口并容器管理，包路径精确到 master，
     * 为了和下面 cluster 数据源做到精确区分 @Value 获取全局配置文件 application.properties 的 kv 配置,
     * 并自动装配 sqlSessionFactoryRef 表示定义了 key ，表示一个唯一 SqlSessionFactory 实例
     * @ConditionalOnProperty 如果配置文件中没有配置spring.master.datasource.flag，则不在容器中装载oneDataSource
     */
    public DataSource masterDataSource() {
        logger.info("开始加载主数据源.......");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    @ConditionalOnBean(name = "masterDataSource")
    /**
     * 加载事务相关
     * 表示spring容器中如果没有masterDataSource数据源，则不需要装载masterTransactionManager
     */
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name ="masterSqlSessionFactory")
    @Primary
    @ConditionalOnBean(name = "masterDataSource")
    /**
     * 表示spring容器中如果没有masterDataSource数据源，则不需要装载masterTransactionManager
     */
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource,@Qualifier("masterPageHelper") PageHelper masterPageHelper)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setPlugins(new Interceptor[]{masterPageHelper});
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean("masterPageHelper")
    @Primary
    /**
     * 加载分页插件（或使用自定义分页filter）
     */
    public PageHelper pageHelper() {
        logger.info("=======master.MyBatisConfiguration.pageHelper()===============");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        String dialect;
        logger.info("=====datasource===" + this.driverClass);
        if(ORACLE_DRIVER_NAME.equals(this.driverClass)){
            dialect ="oracle";
        }else if(MYSQL_DRIVER_NAME.equals(this.driverClass)){
            dialect ="mysql";
        }else {
            dialect="others";
        }
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect",dialect);
        logger.info("=====dateSourcePluginProperties======");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}