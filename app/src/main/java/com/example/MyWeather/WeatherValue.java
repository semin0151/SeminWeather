package com.example.MyWeather;

public class WeatherValue {
    private String PTY;    // 강수형태 코드
    private String REH;    // 습도 %
    private String RN1; // 1시간 강수량
    private String T1H; // 기온
    /*
    private double UUU; // 동서바람성분
    private int VEC;    // 풍향
    private double VVV; // 남북바람성분
    private double WSD; // 풍속
     */

    public void setPTY(String PTY) {
        this.PTY = PTY;
    }

    public void setREH(String REH) {
        this.REH = REH;
    }

    public void setRN1(String RN1) {
        this.RN1 = RN1;
    }

    public void setT1H(String t1H) {
        T1H = t1H;
    }

    public String getPTY() {
        String str = "강수형태 : ";
        switch (PTY){
            case "0":
                str += "없음\n";
                break;
            case "1" :
                str += "비\n";
                break;
            case "2" :
                str += "비/눈\n";
                break;
            case "3" :
                str += "눈\n";
                break;
            case "4" :
                str += "소나기\n";
                break;
            case "5" :
                str += "빗방울\n";
                break;
            case "6" :
                str += "빗방울/눈날림\n";
                break;
            case "7" :
                str += "눈날림\n";
                break;
        }

        return str;
    }

    public String getREH() {
        return "습도 : " + REH + "%\n";
    }

    public String getRN1() {
        return "1시간 강수량 : " + RN1 + "mm\n";
    }

    public String getT1H() {
        return "기온 : " + T1H + "℃\n";
    }
}
