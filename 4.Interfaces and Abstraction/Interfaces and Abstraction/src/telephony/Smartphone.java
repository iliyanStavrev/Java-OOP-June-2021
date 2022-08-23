package telephony;

import java.util.ArrayList;
import java.util.List;

public class Smartphone implements Callable,Browsable{
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder out = new StringBuilder();
        for (String number : numbers) {
            boolean isInvalid = false;
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))){
                    out.append("Invalid number!").append(System.lineSeparator());
                    isInvalid = true;
                    break;
                }
            }
            if (!isInvalid){
                out.append("Calling... " + number).append(System.lineSeparator());
            }
        }
        return out.toString();
    }
    @Override
    public String browse() {
        StringBuilder out = new StringBuilder();
        for (String url : urls) {
            boolean isInvalid = false;
            for (int i = 0; i < url.length(); i++) {
                if (Character.isDigit(url.charAt(i))){
                    out.append("Invalid URL!").append(System.lineSeparator());
                    isInvalid = true;
                    break;
                }
            }
            if (!isInvalid){
                out.append("Browsing: " + url + "!").append(System.lineSeparator());
            }

        }
        return out.toString();
    }

}
