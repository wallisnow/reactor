package com.example.reactor.base.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JacksonArray {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void toList() throws IOException {
        String json = "{\n" +
                "    \"organization_ids\":{\n" +
                "        \"default\":{\n" +
                "            \"include\":[\n" +
                "                \"3.93\",\n" +
                "                \"3.93.*\"\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";

        ObjectNode jsonNodes = mapper.readValue(json, ObjectNode.class);

        boolean organization_ids = jsonNodes.has("organization_ids");
        log.info("{}", jsonNodes);
        log.info("{}", organization_ids);

        //boolean defaults = jsonNodes.has("default");
        //false
        //log.info("{}", defaults);


        JsonNode aDefault = jsonNodes.get("organization_ids").get("default");
        log.info("{}", aDefault);

        JsonNode include = aDefault.get("include");

        ObjectReader reader = mapper.readerFor(new TypeReference<List<String>>() {});
        List<String> list = reader.readValue(include);

        log.info("{}", list);
    }


    @Test
    void toList2() throws IOException {
        String json = "\"groups\": [\n" +
                "    \"cnx_device_twin_all_view\",\n" +
                "    \"event-processor:all:modify\",\n" +
                "    \"cnx_device_management_administration\",\n" +
                "    \"event-processor:all:view\",\n" +
                "    \"messaging:device-twin:lwm2m.firmware.update\",\n" +
                "    \"cnx_device_twin_all_modify\",\n" +
                "    \"device-twin-model:all:view\",\n" +
                "    \"device-twin-model:all:modify\",\n" +
                "    \"cnx_device_twin_model_all_modify\",\n" +
                "    \"cnx_event_processor_all_view\",\n" +
                "    \"cnx_audit_report_api.read\",\n" +
                "    \"cnx_messaging_device_twin_all_modify\",\n" +
                "    \"cnx_event_processor_all_modify\",\n" +
                "    \"messaging:device-twin:lwm2m\",\n" +
                "    \"cnx_messaging_device_twin_all_view\",\n" +
                "    \"cnx_security_manager\",\n" +
                "    \"um20_change_password\",\n" +
                "    \"device-twin:all:modify\",\n" +
                "    \"cnx_security_manager_readonly\",\n" +
                "    \"cnx_device_twin_model_all_view\",\n" +
                "    \"cnx_view_device_management\",\n" +
                "    \"device-twin:all:view\"\n" +
                "  ]";

        ObjectNode jsonNodes = mapper.readValue(json, ObjectNode.class);

        ObjectReader reader = mapper.readerFor(new TypeReference<List<String>>() {});
        List<String> list = reader.readValue(jsonNodes.get("groups"));

        log.info("{}", list);
    }
}
