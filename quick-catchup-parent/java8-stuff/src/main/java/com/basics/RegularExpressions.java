package com.basics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {

    public static void main(String[] args) {

        String htmlText = "<head> HEAD </head>" +
                            "<body> body </body>" +
                            "<h1> 1st h1 tag </h1>" +
                            "<h2> 1st h2 tag </h2>" +
                            "<h1> 2nd h1 tag </h1>" ;

        System.out.println("Actual Text: " + htmlText);

        String findText = "(<h1>)(.*?)(</h1>)";
        Pattern tagPattern = Pattern.compile(findText);
        Matcher tagMatcher = tagPattern.matcher(htmlText);

        while(tagMatcher.find()){
            System.out.println("Occurence: " + tagMatcher.group(0));
        }

        System.out.println("harry".replaceAll("H|harry","Larry"));

        String usPhone = "(800) 123-45678";
        String ukPhone = "(+44) 77123 14869";

        String usRegEx = "^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{5})$";

        System.out.println(usPhone.replaceAll(usRegEx, "US Phone OK"));
        System.out.println("usPhone = " + usPhone.matches(usRegEx));
        System.out.println("ukPhone = " + ukPhone.matches(usRegEx));

        String ukRegEx = "^([\\(]{1}[\\+]{1}\\d{2}[\\)]{1}[ ]{1}\\d{5}[ ]{1}\\d{5})$";
        System.out.println("usPhone = " + usPhone.matches(ukRegEx));
        System.out.println("ukPhone = " + ukPhone.matches(ukRegEx));

    }
}
