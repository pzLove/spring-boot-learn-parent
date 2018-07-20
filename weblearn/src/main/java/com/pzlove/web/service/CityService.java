package com.pzlove.web.service;

import com.pzlove.web.mapper.CityMapper;
import com.pzlove.web.pojo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pzlove
 * @date 2018-07-11 上午10:49
 **/
@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    public City findCityById(Long id) {
        return cityMapper.findCityById(id);
    }

    public List<City> findAllCity() {
        return cityMapper.findAllCity();
    }

    public void saveCity(City city) {
        cityMapper.saveCity(city);
    }

    public void updateCity(City city) {
        cityMapper.updateCity(city);
    }

    public void deleteCity(Long id) {
        cityMapper.deleteCity(id);
    }
}
