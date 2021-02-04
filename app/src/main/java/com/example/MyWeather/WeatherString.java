package com.example.MyWeather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherString {
    private String data = "";
    private String str0 = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtNcst?serviceKey=";
    private String serviceKey = "J1uWhMdL85rUtm1%2FogRUgAehBm4pAZ2QJBmZ8ytsj9q5VPkKoPyTuUWItYfi5LAXgTA%2Bmn2ERVnNZDQsfwYxpg%3D%3D";
    private String str1 = "&pageNo=1&numOfRows=8&dataType=XML&base_date=";
    private String str2 = "&base_time=";
    private String str3 = "&nx=59" + "&ny=122";
    private String strDate;
    private String strTime;
    private int parserEvent;

    private boolean b_T1H = false;
    private boolean b_RN1 = false;
    private boolean b_REH = false;
    private boolean b_PTY = false;
    private WeatherValue weatherValue = new WeatherValue();

    WeatherString() {
        strDate = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
        strTime = new SimpleDateFormat("HHmm").format(new Date(System.currentTimeMillis()));
        strTime = strTime.substring(0,1) + (char)((int)strTime.charAt(1)-1) + strTime.substring(2);
    }
    public String getStr(){
        String Myurl = str0 + serviceKey + str1 +strDate + str2 + strTime + str3;
            try {
                URL url = new URL(Myurl);
                XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserCreator.newPullParser();
                parser.setInput(url.openStream(), "UTF-8");

                parserEvent = parser.getEventType();
                while (parserEvent != XmlPullParser.END_DOCUMENT){
                    switch( parserEvent ) {
                        case XmlPullParser.START_DOCUMENT : break;
                        case XmlPullParser.START_TAG :
                            if(b_PTY && parser.getName().equals("obsrValue")){
                                parser.next();
                                weatherValue.setPTY(parser.getText());
                                b_PTY = false;
                            }
                            else if(b_REH && parser.getName().equals("obsrValue")){
                                parser.next();
                                weatherValue.setREH(parser.getText());
                                b_REH = false;
                            }
                            else if(b_RN1 && parser.getName().equals("obsrValue")){
                                parser.next();
                                weatherValue.setRN1(parser.getText());
                                b_RN1 = false;
                            }
                            else if(b_T1H && parser.getName().equals("obsrValue")){
                                parser.next();
                                weatherValue.setT1H(parser.getText());
                                b_T1H = false;
                            }
                            break;
                        case XmlPullParser.TEXT :
                            if(parser.getText().equals("PTY"))
                                b_PTY = true;
                            else if(parser.getText().equals("REH"))
                                b_REH = true;
                            else if(parser.getText().equals("RN1"))
                                b_RN1 = true;
                            else if(parser.getText().equals("T1H"))
                                b_T1H = true;
                            break;
                        case XmlPullParser.END_TAG : break;
                        }
                     parserEvent = parser.next();
                 }
            }catch(Exception e){
                return e.getMessage();
            }
        data = weatherValue.getPTY() + weatherValue.getREH() + weatherValue.getRN1() + weatherValue.getT1H();
        return data;
    }
}
