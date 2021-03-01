package com.mvp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tushar
 * Created on 11/06/18.
 */

public class Alerts {

    public static void showError(Context context, String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String operatorForm = "{\"success\":true,\"data\":[{\"form_template_widgets\":[{\"id\":\"w_1\",\"label\":\"Load number\",\"code\":\"loadno\",\"icon\":\"number.png\",\"required\":false,\"auto_increment\":true,\"default_to_last_collected\":false,\"form_widget_type\":\"number\"},{\"id\":\"w_2\",\"label\":\"Date\",\"code\":\"date\",\"icon\":\"date.png\",\"required\":false,\"form_widget_type\":\"date\"},{\"id\":\"w_3\",\"label\":\"Time\",\"code\":\"time\",\"icon\":\"time.png\",\"required\":false,\"form_widget_type\":\"time\"},{\"id\":\"w_6\",\"label\":\"Truck\",\"code\":\"truck\",\"icon\":\"single_option.png\",\"required\":false,\"form_widget_type\":\"single_choice\",\"options_obj\":{\"total_option_count\":4,\"options\":[{\"id\":\"o_1\",\"value\":\"Option 1\"},{\"id\":\"o_2\",\"value\":\"Option 2\"},{\"id\":\"o_3\",\"value\":\"Option 3\"},{\"id\":\"o_4\",\"value\":\"OTHER\"}]}},{\"id\":\"w_7\",\"label\":\"Truck Registration Number\",\"code\":\"truckregino\",\"icon\":\"text.png\",\"required\":false,\"form_widget_type\":\"text\",\"conditions\":[{\"operand_1\":\"w_6\",\"operator\":\"eq\",\"operand_2\":\"o_4\"}]},{\"id\":\"w_5\",\"label\":\"Operator\",\"code\":\"operator\",\"icon\":\"single_option.png\",\"required\":false,\"form_widget_type\":\"single_choice\",\"options_obj\":{\"total_option_count\":4,\"options\":[{\"id\":\"o_1\",\"value\":\"Option 1\"},{\"id\":\"o_2\",\"value\":\"Option 2\"},{\"id\":\"o_3\",\"value\":\"Option 3\"},{\"id\":\"o_4\",\"value\":\"OTHER\"}]}},{\"id\":\"w_4\",\"label\":\"Operator name\",\"code\":\"Operatorname\",\"icon\":\"text.png\",\"required\":false,\"form_widget_type\":\"text\",\"conditions\":[{\"operand_1\":\"w_5\",\"operator\":\"eq\",\"operand_2\":\"o_4\"}]},{\"id\":\"w_8\",\"label\":\"Driver Name\",\"code\":\"driver\",\"icon\":\"text.png\",\"form_widget_type\":\"text\"},{\"id\":\"w_11\",\"label\":\"Carrying Quantity\",\"code\":\"cqty\",\"icon\":\"rating.png\",\"form_widget_type\":\"rating\",\"rating_type\":\"Progress Bar\",\"properties\":{\"min_value\":500,\"max_value\":10000,\"steps\":500}},{\"id\":\"w_12\",\"label\":\"Repeat Load\",\"code\":\"rl\",\"icon\":\"boolean_option.png\",\"form_widget_type\":\"boolean_choice\"}],\"deleted_form_template_widgets\":[],\"files_uploaded\":[],\"status\":true,\"publish_status\":\"pending\",\"sizeOccupied\":2291,\"_id\":\"5f1007592a91ab4a0146fd45\",\"name\":\"Operator info form\",\"shape\":\"point\",\"symbol\":\"app/assets/symbology/circle_filled_yellow.png\",\"color\":\"#f9c404\",\"total_form_template_widget_count\":12,\"created_by\":\"5e1e97ffa014d12ff6f54532\",\"updated_by\":\"5e1e97ffa014d12ff6f54532\",\"organisation_id\":\"5e1e97ffa014d12ff6f54531\",\"layer_id\":\"5f1007592a91ab3e5f46fd44\",\"createdAt\":\"2020-07-16T07:52:57.580Z\",\"updatedAt\":\"2020-08-10T05:14:54.346Z\",\"__v\":0,\"assigned_user_ids\":[\"5e4d00ec111a5f6362c056df\",\"5e1e9836a014d12ff6f54534\"],\"created_date\":1597036993,\"updated_date\":1597036993,\"upload_file_path\":\"/data-collections\",\"upload_file_base_path\":\"https://sgp1.digitaloceanspaces.com/tos-fileserver/uat\"}],\"count\":1,\"error\":[]}";

    public static String propertyForm = "{\"success\":true,\"data\":[{\"form_template_widgets\":[{\"id\":\"w_1\",\"label\":\"Property Information\",\"code\":\"pinfo\",\"icon\":\"static_text.png\",\"form_widget_type\":\"static_text\",\"sub_heading\":null},{\"id\":\"w_2\",\"label\":\"Property Type\",\"code\":\"ptype\",\"icon\":\"single_option.png\",\"form_widget_type\":\"single_choice\",\"options_obj\":{\"total_option_count\":3,\"options\":[{\"id\":\"o_1\",\"value\":\"House Hold\"},{\"id\":\"o_2\",\"value\":\"Commercial\"},{\"id\":\"o_3\",\"value\":\"CT/PT\"}]}},{\"id\":\"w_3\",\"label\":\"CT-PTs\",\"code\":\"ctpt\",\"icon\":\"single_option.png\",\"form_widget_type\":\"single_choice\",\"options_obj\":{\"total_option_count\":3,\"options\":[{\"id\":\"o_1\",\"value\":\"CT-PT1\"},{\"id\":\"o_2\",\"value\":\"CT-PT2\"},{\"id\":\"o_3\",\"value\":\"CT-PT3\"}]},\"conditions\":[{\"operand_1\":\"w_2\",\"operator\":\"eq\",\"operand_2\":\"o_3\"}]},{\"id\":\"w_7\",\"label\":\"Building Name\",\"code\":\"name\",\"icon\":\"text.png\",\"form_widget_type\":\"text\"},{\"id\":\"w_12\",\"label\":\"Owner Name\",\"code\":\"on\",\"icon\":\"text.png\",\"form_widget_type\":\"text\"},{\"id\":\"w_8\",\"label\":\"Address\",\"code\":\"Address\",\"icon\":\"text.png\",\"form_widget_type\":\"text\"},{\"id\":\"w_5\",\"label\":\"Area\",\"code\":\"area\",\"icon\":\"text.png\",\"form_widget_type\":\"text\"},{\"id\":\"w_13\",\"label\":\"ULB NAME\",\"code\":\"UN\",\"icon\":\"single_option.png\",\"form_widget_type\":\"single_choice\",\"options_obj\":{\"total_option_count\":4,\"options\":[{\"id\":\"o_1\",\"value\":\"Option 1\"},{\"id\":\"o_2\",\"value\":\"Option 2\"},{\"id\":\"o_3\",\"value\":\"Option 3\"},{\"id\":\"o_4\",\"value\":\"Option 4\"}]}},{\"id\":\"w_6\",\"label\":\"Ward Number\",\"code\":\"wno\",\"icon\":\"number.png\",\"form_widget_type\":\"number\"},{\"id\":\"w_9\",\"label\":\"Slum/Non-slum\",\"code\":\"slum\",\"icon\":\"boolean_option.png\",\"form_widget_type\":\"boolean_choice\"},{\"id\":\"w_10\",\"label\":\"Containment Type\",\"code\":\"ctype\",\"icon\":\"single_option.png\",\"form_widget_type\":\"single_choice\",\"options_obj\":{\"total_option_count\":3,\"options\":[{\"id\":\"o_1\",\"value\":\"Containment Type1\"},{\"id\":\"o_2\",\"value\":\"Containment Type2\"},{\"id\":\"o_3\",\"value\":\"Containment Type3\"}]}},{\"id\":\"w_11\",\"label\":\"Desludging period\",\"code\":\"dperiod\",\"icon\":\"text.png\",\"form_widget_type\":\"text\"}],\"deleted_form_template_widgets\":[],\"files_uploaded\":[],\"status\":true,\"publish_status\":\"pending\",\"sizeOccupied\":1843,\"_id\":\"5f1167a7acfd588b5b74aeab\",\"name\":\"Property Information\",\"shape\":\"point\",\"symbol\":\"app/assets/symbology/circle_filled_yellow.png\",\"color\":\"#f9c404\",\"total_form_template_widget_count\":13,\"created_by\":\"5e1e97ffa014d12ff6f54532\",\"updated_by\":\"5e1e97ffa014d12ff6f54532\",\"organisation_id\":\"5e1e97ffa014d12ff6f54531\",\"layer_id\":\"5f1167a7acfd586c3c74aeaa\",\"createdAt\":\"2020-07-17T08:56:07.646Z\",\"updatedAt\":\"2020-08-10T05:17:26.299Z\",\"__v\":0,\"assigned_user_ids\":[\"5e4d00ec111a5f6362c056df\",\"5e1e9836a014d12ff6f54534\"],\"created_date\":1597037065,\"updated_date\":1597037065,\"upload_file_path\":\"/data-collections\",\"upload_file_base_path\":\"https://sgp1.digitaloceanspaces.com/tos-fileserver/uat\"}],\"count\":1,\"error\":[]}";

    public static String loadInfoForm = "{\n" +
            "  \"count\" : 1,\n" +
            "  \"data\" : [ {\n" +
            "    \"__v\" : 0,\n" +
            "    \"_id\" : \"5f1168ffacfd584d9b74aeb4\",\n" +
            "    \"assigned_user_ids\" : [ \"5e4d00ec111a5f6362c056df\", \"5e1e9836a014d12ff6f54534\" ],\n" +
            "    \"color\" : \"#f9c404\",\n" +
            "    \"createdAt\" : \"2020-07-17T09:01:51.611Z\",\n" +
            "    \"created_by\" : \"5e1e97ffa014d12ff6f54532\",\n" +
            "    \"created_date\" : 1594976869,\n" +
            "    \"form_template_widgets\" : [ {\n" +
            "      \"code\" : \"linfo\",\n" +
            "      \"form_widget_type\" : \"static_text\",\n" +
            "      \"icon\" : \"static_text.png\",\n" +
            "      \"id\" : \"w_1\",\n" +
            "      \"label\" : \"Load Information\"\n" +
            "    }, {\n" +
            "      \"code\" : \"ph\",\n" +
            "      \"form_widget_type\" : \"single_image\",\n" +
            "      \"icon\" : \"single_image.png\",\n" +
            "      \"id\" : \"w_2\",\n" +
            "      \"label\" : \"Photo\"\n" +
            "    }, {\n" +
            "      \"auto_increment\" : true,\n" +
            "      \"code\" : \"rnumber\",\n" +
            "      \"default_to_last_collected\" : false,\n" +
            "      \"form_widget_type\" : \"number\",\n" +
            "      \"icon\" : \"number.png\",\n" +
            "      \"id\" : \"w_3\",\n" +
            "      \"label\" : \"Receipt number\",\n" +
            "      \"required\" : false\n" +
            "    }, {\n" +
            "      \"code\" : \"tfbedno\",\n" +
            "      \"form_widget_type\" : \"number\",\n" +
            "      \"icon\" : \"number.png\",\n" +
            "      \"id\" : \"w_4\",\n" +
            "      \"label\" : \"TF Bed Number\"\n" +
            "    } ],\n" +
            "    \"layer_id\" : \"5f1168ffacfd585aa774aeb3\",\n" +
            "    \"name\" : \"Load Information\",\n" +
            "    \"organisation_id\" : \"5e1e97ffa014d12ff6f54531\",\n" +
            "    \"publish_status\" : \"pending\",\n" +
            "    \"shape\" : \"point\",\n" +
            "    \"sizeOccupied\" : 977,\n" +
            "    \"status\" : true,\n" +
            "    \"symbol\" : \"app/assets/symbology/circle_filled_yellow.png\",\n" +
            "    \"total_form_template_widget_count\" : 4,\n" +
            "    \"updatedAt\" : \"2020-07-17T09:01:51.611Z\",\n" +
            "    \"updated_by\" : \"5e1e97ffa014d12ff6f54532\",\n" +
            "    \"updated_date\" : 1594976869,\n" +
            "    \"upload_file_base_path\" : \"https://sgp1.digitaloceanspaces.com/tos-fileserver/uat\",\n" +
            "    \"upload_file_path\" : \"/data-collections\"\n" +
            "  } ],\n" +
            "  \"success\" : true\n" +
            "}";
}
