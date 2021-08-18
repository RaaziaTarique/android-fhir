import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.fhir.shaded.common.io.Files;
import com.google.fhir.shaded.protobuf.GeneratedMessageV3;
import com.google.fhir.shaded.protobuf.Message;
import com.google.gson.JsonParser;
import com.google.fhir.shaded.protobuf.Message.Builder;
import com.google.fhir.common.JsonFormat;
import com.google.fhir.shaded.protobuf.TextFormat;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;


public class JsonFormatBase {

  protected JsonFormat.Parser jsonParser
      = JsonFormat.Parser.withDefaultTimeZone(ZoneId.systemDefault());

  protected TextFormat.Parser textParser = TextFormat.getParser();

  protected JsonFormat.Printer jsonPrinter = JsonFormat.getPrinter();

  protected String loadJson(String filename) throws IOException {
    File file = new File("D:/android-fhir/" + filename);
    return Files.asCharSource(file, UTF_8).read();
  }

  protected void parseToProto(String name, com.google.fhir.shaded.protobuf.Message.Builder builder)
      throws IOException {

    String filename = "/d/android-fhir/example.json";

    String realFileName = name + ".json";

//    jsonParser.merge(loadJson(realFileName), builder);


    jsonParser.merge("{\n"
        + "  \"active\": true,\n"
        + "  \"deceasedBoolean\": false,\n"
        + "  \"gender\": \"male\",\n"
        + "  \"address\": [\n"
        + "    {\n"
        + "      \"use\": \"home\",\n"
        + "      \"period\": {\n"
        + "        \"start\": \"1974-12-25\"\n"
        + "      },\n"
        + "      \"postalCode\": \"3999\",\n"
        + "      \"type\": \"both\",\n"
        + "      \"district\": \"Rainbow\",\n"
        + "      \"line\": [\n"
        + "        \"534 Erewhon St\"\n"
        + "      ],\n"
        + "      \"text\": \"534 Erewhon St PeasantVille, Rainbow, Vic  3999\",\n"
        + "      \"state\": \"Vic\",\n"
        + "      \"city\": \"PleasantVille\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"id\": \"examle\",\n"
        + "  \"name\": [\n"
        + "    {\n"
        + "      \"use\": \"official\",\n"
        + "      \"family\": \"Chalmers\",\n"
        + "      \"given\": [\n"
        + "        \"Peter\",\n"
        + "        \"James\"\n"
        + "      ]\n"
        + "    },\n"
        + "    {\n"
        + "      \"given\": [\n"
        + "        \"Jim\"\n"
        + "      ],\n"
        + "      \"use\": \"usual\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"_birthDate\": {\n"
        + "    \"extension\": [\n"
        + "      {\n"
        + "        \"url\": \"http://hl7.org/fhir/StructureDefinition/patient-birthTime\",\n"
        + "        \"valueDateTime\": \"1974-12-25T14:35:45-05:00\"\n"
        + "      }\n"
        + "    ]\n"
        + "  },\n"
        + "  \"resourceType\": \"Patient\",\n"
        + "  \"telecom\": [\n"
        + "    {\n"
        + "      \"rank\": 2,\n"
        + "      \"value\": \"(03) 3410 5613\",\n"
        + "      \"system\": \"phone\",\n"
        + "      \"use\": \"mobile\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"birthDate\": \"1974-12-25\"\n"
        + "}", builder);


  }

  protected void parseToJson(File file, Message.Builder builder) throws IOException {
    textParser.merge(Files.asCharSource(file, UTF_8).read(), builder);

    System.out.println(jsonPrinter.print(builder));
  }

}
