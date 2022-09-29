package be.bf.android.vantal.helper;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import be.bf.android.vantal.R;

public class AmenitiesHelper {
    public static int getAmenitiesRessource(String amenity) {
        switch (amenity) {
            case "COFFEE_MACHINE":
                return R.drawable.coffee_machine;
            case "DISHWASHER":
                return R.drawable.dishwasher;
            case "POTS AND PANS":
                return R.drawable.pots_pans;
            case "SMART TV":
                return R.drawable.smart_tv;
            case "WIFI":
                return R.drawable.wifi;
            case "HAIR_DRYER":
                return  R.drawable.hair_dryer;
            case "SHAMPOO":
                return  R.drawable.shampoo;
            case "BODY_SOAP":
                return R.drawable.body_soap;
            case "IRON":
                return  R.drawable.iron_ironing_board;
            case "WASHING_MACHINE":
                return R.drawable.washing_machine;
            case "EXTRA_BLANKETS_AND_PILLOWS":
                return R.drawable.blanket_pillow;
            case "BATHTUB":
                return R.drawable.bathtub;
            case "KITCHEN":
                return R.drawable.kitchen;
            case "CENTRAL_AIR_CONDITIONING":
                return R.drawable.air_conditionning;
            case "FIRE_EXTINGUISHER":
                return R.drawable.fire_extinguisher;
            case "FRIDGE":
                return R.drawable.fridge;
            case "MICROWAVE":
                return R.drawable.microwave;
        }
        return 0;
    }

    public static String getAmenitiesString(String amenity) {
        switch (amenity) {
            case "COFFEE_MACHINE":
                return "Coffe machine";
            case "DISHWASHER":
                return "Dishwasher";
            case "POTS AND PANS":
                return "Pots and Pans";
            case "SMART TV":
                return "Smart TV";
            case "WIFI":
                return "Wifi";
            case "HAIR_DRYER":
                return "Hair Dryer";
            case "SHAMPOO":
                return  "Shampoo";
            case "BODY_SOAP":
                return  "Body Soap";
            case "IRON":
                return "Iron and Ironing Board";
            case "WASHING_MACHINE":
                return "Washing Machine";
            case "EXTRA_BLANKETS_AND_PILLOWS":
                return "Extra Blankets";
            case "BATHTUB":
                return "Bathtub";
            case "KITCHEN":
                return "Kitchen";
            case "CENTRAL_AIR_CONDITIONING":
                return "Air Conditioning";
            case "FIRE_EXTINGUISHER":
                return "Fire Extinguisher";
            case "FRIDGE":
                return "Fridge";
            case "MICROWAVE":
                return "Microwave";
        }
        return null;
    }
}
