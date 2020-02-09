package view;

public class AttributesRequestWeather {
    private String latitude;
    private String longitude;
    private int yearStart;
    private int monthStart;
    private int dayStart;
    private int houreStart;
    private int yearFinish;
    private int monthFinish;
    private int dayFinish;
    private int houreFinish;
    private String servisAPI;

    public AttributesRequestWeather() {
    }

    public AttributesRequestWeather(String latitude, String longitude, int yearStart, int monthStart, int dayStart, int houreStart, int yearFinish, int monthFinish, int dayFinish, int houreFinish, String servisAPI) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.yearStart = yearStart;
        this.monthStart = monthStart;
        this.dayStart = dayStart;
        this.houreStart = houreStart;
        this.yearFinish = yearFinish;
        this.monthFinish = monthFinish;
        this.dayFinish = dayFinish;
        this.houreFinish = houreFinish;
        this.servisAPI = servisAPI;
    }

    public int getYearStart() {
        return yearStart;
    }

    public int getMonthStart() {
        return monthStart;
    }

    public int getDayStart() {
        return dayStart;
    }

    public int getHoureStart() {
        return houreStart;
    }

    public int getYearFinish() {
        return yearFinish;
    }

    public int getMonthFinish() {
        return monthFinish;
    }

    public int getDayFinish() {
        return dayFinish;
    }

    public int getHoureFinish() {
        return houreFinish;
    }

    public String getServisAPI() {
        return servisAPI;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setYearStart(int yearStart) {
        this.yearStart = yearStart;
    }

    public void setMonthStart(int monthStart) {
        this.monthStart = monthStart;
    }

    public void setDayStart(int dayStart) {
        this.dayStart = dayStart;
    }

    public void setHoureStart(int houreStart) {
        this.houreStart = houreStart;
    }

    public void setYearFinish(int yearFinish) {
        this.yearFinish = yearFinish;
    }

    public void setMonthFinish(int monthFinish) {
        this.monthFinish = monthFinish;
    }

    public void setDayFinish(int dayFinish) {
        this.dayFinish = dayFinish;
    }

    public void setHoureFinish(int houreFinish) {
        this.houreFinish = houreFinish;
    }

    public void setServisAPI(String servisAPI) {
        this.servisAPI = servisAPI;
    }
}
