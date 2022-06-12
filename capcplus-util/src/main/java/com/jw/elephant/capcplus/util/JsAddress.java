package com.jw.elephant.capcplus.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsAddress {
    private Long id;
    private String name;
    private List<JsCity> cityList;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JsCity{
        private Long id;
        private String name;
        private List<JsArea> areaList;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JsArea{
        private Long id;
        private String name;
    }
}
