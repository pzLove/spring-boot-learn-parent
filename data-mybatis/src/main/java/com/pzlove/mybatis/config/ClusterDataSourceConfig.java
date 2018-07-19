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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {
    private  final Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    private static final String ORACLE_DRIVER_NAME ="oracle.jdbc.driver.OracleDriver";
    private static final String MYSQL_DRIVER_NAME ="com.mysql.jdbc.Driver";
    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.pzlove.mybatis.mapper.cluster";
    private static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Value("${spring.cluster.datasource.url}")
    private String url;

    @Value("${spring.cluster.datasource.username}")
    private String user;

    @Value("${spring.cluster.datasource.password}")
    private String password;

    @Value("${spring.cluster.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "clusterDataSource")
    @ConditionalOnProperty(value = "spring.cluster.datasource.flag",havingValue = "true")
    public DataSource clusterDataSource() {
        logger.info("开始加载辅数据源.......");
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Bean(name = "clusterTransactionManager")
    @ConditionalOnBean(name = "clusterDataSource")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "clusterSqlSessionFactory")
    @ConditionalOnBean(name = "clusterDataSource")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource,@Qualifier("clusterPageHelper") PageHelper clusterPageHelper)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setPlugins(new Interceptor[]{clusterPageHelper});
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean("clusterPageHelper")
    /**
     * 加载分页插件（或使用自定义分页filter）
     */
    public PageHelper pageHelper() {
        logger.info("=======cluster.MyBatisConfiguration.pageHelper()===============");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        String dialect;
        logger.info("=====datasource===" + this.driverClass);
        if(ORACLE_DRIVER_NAME.equals(this.driverClass)){
            dialect ="oracle";
        }else if(MYSQL_DRIVER_NAME.equals(this.driverClass)){
            dialect ="mysql";
        }else {
            dialect="";
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