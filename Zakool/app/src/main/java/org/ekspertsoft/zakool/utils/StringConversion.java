package org.ekspertsoft.zakool.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StringConversion {

    private static Map<String, String> stateMap = new HashMap<String, String>();

    static {
        stateMap.put("AL", "Alabama");
        stateMap.put("AK", "Alaska");
        stateMap.put("AZ", "Arizona");
        stateMap.put("AR", "Arkansas");
        stateMap.put("CA", "California");
        stateMap.put("CO", "Colorado");
        stateMap.put("CT", "Connecticut");
        stateMap.put("DE", "Delaware");
        stateMap.put("DC", "District of Columbia");
        stateMap.put("FL", "Florida");
        stateMap.put("GA", "Georgia");
        stateMap.put("HI", "Hawaii");
        stateMap.put("ID", "Idaho");
        stateMap.put("IL", "Illinois");
        stateMap.put("IN", "Indiana");
        stateMap.put("IA", "Iowa");
        stateMap.put("KS", "Kansas");
        stateMap.put("KY", "Kentucky");
        stateMap.put("LA", "Louisiana");
        stateMap.put("ME", "Maine");
        stateMap.put("MD", "Maryland");
        stateMap.put("MA", "Massachusetts");
        stateMap.put("MI", "Michigan");
        stateMap.put("MN", "Minnesota");
        stateMap.put("MS", "Mississippi");
        stateMap.put("MO", "Missouri");
        stateMap.put("MT", "Montana");
        stateMap.put("NE", "Nebraska");
        stateMap.put("NV", "Nevada");
        stateMap.put("NH", "New Hampshire");
        stateMap.put("NJ", "New Jersey");
        stateMap.put("NM", "New Mexico");
        stateMap.put("NY", "New York");
        stateMap.put("NC", "North Carolina");
        stateMap.put("ND", "North Dakota");
        stateMap.put("OH", "Ohio");
        stateMap.put("OK", "Oklahoma");
        stateMap.put("OR", "Oregon");
        stateMap.put("PA", "Pennsylvania");
        stateMap.put("RI", "Rhode Island");
        stateMap.put("SC", "South Carolina");
        stateMap.put("SD", "South Dakota");
        stateMap.put("TN", "Tennessee");
        stateMap.put("TX", "Texas");
        stateMap.put("UT", "Utah");
        stateMap.put("VT", "Vermont");
        stateMap.put("VA", "Virginia");
        stateMap.put("WA", "Washington");
        stateMap.put("WV", "West Virginia");
        stateMap.put("WI", "Wisconsin");
        stateMap.put("WY", "Wyoming");
        stateMap.put("AS", "American Samoa");
        stateMap.put("FM", "Federated States of Micronesia");
        stateMap.put("GU", "Guam");
        stateMap.put("MH", "Marshall Islands");
        stateMap.put("MP", "Northern Marianas");
        stateMap.put("PW", "Palau");
        stateMap.put("PR", "Puerto Rico");
        stateMap.put("VI", "Virgin Islands");
    }

    public static Double toDouble(final String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return 0.0;
        }
    }

    public static int toInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static String toUSCurrencyFormat(final int value) {
        try {
            return DecimalFormat.getCurrencyInstance(Locale.US).format(value);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String toCategory(final String code) {
        switch (code) {
            case "1":
                return "Degree-granting, graduate with no undergraduate degrees";
            case "2":
                return "Degree-granting, primarily baccalaureate or above";
            case "3":
                return "Degree-granting, not primarily baccalaureate or above";
            case "4":
                return "Degree-granting, associate's and certificates";
            case "5":
                return "Nondegree-granting, above the baccalaureate";
            case "6":
                return "Nondegree-granting, sub-baccalaureate";
            default:
                return "N/A";
        }
    }

    public static String toType(final String code) {
        switch (code) {
            case "1":
                return "Public";
            case "2":
                return "Private not-for-profit";
            case "3":
                return "Private for-profit";
            default:
                return "N/A";
        }
    }

    public static String toSize(final String code) {
        switch (code) {
            case "1":
                return "Under 1,000";
            case "2":
                return "1,000 - 4,999";
            case "3":
                return "5,000 - 9,999";
            case "4":
                return "10,000 - 19,999";
            case "5":
                return "20,000 and above";
            default:
                return "N/A";
        }
    }

    public static String toLevel(final String code) {
        switch (code) {
            case "1":
                return "Four or more years";
            case "2":
                return "At least 2 but less than 4 years";
            case "3":
                return "Less than 2 years (below associate)";
            default:
                return "N/A";
        }
    }

    public static String toState(final String code) {
        return stateMap.get(code)!=null? stateMap.get(code):code;
    }

}
