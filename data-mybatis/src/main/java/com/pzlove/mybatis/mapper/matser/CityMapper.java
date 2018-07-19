package com.pzlove.mybatis.mapper.matser;

import com.pzlove.mybatis.domain.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pzlove
 * @date 2018-07-11 上午10:51
 **/
//@Mapper
@Repository
public interface CityMapper {

    City findCityById(Long id);

    List<City> findAllCity();

    void saveCity(City city);

    void updateCity(City city);

    void deleteCity(Long id);
}
