package com.pzlove.mybatis.domain;

import lombok.*;

/**
 * @author pzlove
 * @date 2018-07-11 上午10:44
 **/
@NoArgsConstructor
@AllArgsConstructor
@Setter@Getter
@ToString
public class City {
    private int id;
    private int provinceId;
    private String cityName;
    private String description;
}
