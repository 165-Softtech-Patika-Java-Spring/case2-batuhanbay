package com.softtechbootcamp.case2.enums;

public enum ErrorMessage {
    DOES_NOT_EXIST_NAME_PARAMETER("Case2-0-400","Invalid Parameter","Name can not be empty, blank or null!"),
    DOES_NOT_EXIST_PLATE_NUMBER_PARAMETER("Case2-1-400","Invalid Parameter","Plate number can not be empty, blank or null!"),
    DOES_NOT_EXIST_COUNTRY_CODE_PARAMETER("Case2-2-400","Invalid Parameter","Country code can not be empty, blank or null!"),
    NOT_FOUND_COUNTRY_WITH_ID("Case2-3-404","Not Found Entity","There is not a saved country with this id!"),
    NOT_FOUND_COUNTRY_WITH_CODE("Case2-4-404","Not Found Entity","There is not a saved address with this code!"),
    NOT_FOUND_CITY_WITH_ID("Case2-5-404","Not Found Entity","There is not a saved city with this id!"),
    NOT_FOUND_CITY_WITH_PLATE_NUMBER("Case2-5-404","Not Found Entity","There is not a saved city with this plate number!"),
    NOT_FOUND_DISTRICT_WITH_ID("Case2-6-404","Not Found Entity","There is not a saved district with this id!"),
    NOT_FOUND_DISTRICTS_WITH_ID("Case2-7-404","Not Found Entities","There is not saved districts in this city"),
    NOT_FOUND_NEIGHBORHOOD_WITH_ID("Case2-8-404","Not Found Entity","There is not a saved neighborhood with this id!"),
    NOT_FOUND_NEIGHBORHOODS_WITH_ID("Case2-9-404","Not Found Entities","There is not saved neighborhoods in this district"),
    NOT_FOUND_STREET_WITH_ID("Case2-10-404","Not Found Entity","There is not a saved street with this id!"),
    NOT_FOUND_STREETS_WITH_ID("Case2-11-404","Not Found Entities","There is not saved streets in this neighborhood"),
    NOT_FOUND_ADDRESS_WITH_ID("Case2-12-404","Not Found Entity","There is not a saved address with this id! Please change it."),
    HAS_DUPLICATE_PLATE_NUMBER("Case2-13-409","Duplicate property","There is  already saved a plate number like this! Please change it."),
    HAS_DUPLICATE_NAME("Case2-14-409","Duplicate property","There is  already saved a name like this! Please change it."),
    HAS_DUPLICATE_COUNTRY_CODE("Case2-15-409","Duplicate property","There is  already saved a country code like this! Please change it."),
    ;

    private  final String message;
    private  final String detailMessage;
    private  final String code;

    ErrorMessage(String code, String message, String detailMessage) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }


    public String getExceptionCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
