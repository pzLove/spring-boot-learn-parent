package com.pzlove.web.mapper;

import com.pzlove.web.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pzlove
 * @date 2018-07-11 上午10:51
 **/
@Mapper
@Repository
public interface CityMapper {

    City findCityById(Long id);

    List<City> findAllCity();

    void saveCity(City city);

    void updateCity(City city);

    void deleteCity(Long id);
}
