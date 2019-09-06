package com.kafka.learn.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learn.model.DetailData;

import java.io.IOException;

public class UserdataGenerator {

    private static String jsonString="{  \n" +
            "   \"userId\":\"j11288090\",\n" +
            "   \"visitorId\":\"jas8v98171\",\n" +
            "   \"type\":\"Event\",\n" +
            "   \"Metadata\":{  \n" +
            "      \"messageId\":\"123sfdafas-32487239857dsh98234\",\n" +
            "      \"sentAt\":1534382478,\n" +
            "      \"timestamp\":1534382478,\n" +
            "      \"receivedAt\":0,\n" +
            "      \"apiKey\":\"\",\n" +
            "      \"spaceId\":\"\",\n" +
            "      \"version\":\"v1\"\n" +
            "   },\n" +
            "   \"event\":\"Played Movie\",\n" +
            "   \"eventData\":{  \n" +
            "      \"MovieID\":\"MIM4ddd4\"\n" +
            "   }\n" +
            "}";

   private static DetailData row=null;

  public static DetailData getRow() throws IOException, CloneNotSupportedException {

  // if(row==null){
       row = new ObjectMapper().readValue(jsonString, DetailData.class);
      row.setUserId(userid());
      row.getMetadata().setSentAt(System.currentTimeMillis());
      row.getMetadata().setTimestamp(System.currentTimeMillis());


  /* }
   else{
       row=(DetailData)row.clone();
       row.setUserId(userid());
       row.getMetadata().setSentAt(System.currentTimeMillis());
       row.getMetadata().setTimestamp(System.currentTimeMillis());

   }*/
   return row;
  }


  static String userid(){
      int randomInt = 1000000+(int)(100 * Math.random());
      int randomletter = (int)(10.0 * Math.random());
      char firstletter=(char)('b'+randomletter);
      String output = String.format("%s%d", firstletter, randomInt);

   return output;
  }

    public static void main(String[] args) throws IOException {
        System.out.println(UserdataGenerator.userid());
    }
}

